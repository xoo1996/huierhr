package org.radf.apps.business.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.FeeForm;
import org.radf.apps.business.form.SaleForm;
import org.radf.apps.commons.entity.Fee;
import org.radf.apps.commons.entity.Sale;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;

//import com.lowagie.text.xml.xmp.DublinCoreSchema;
/**
 * ���ò���
 */
public class SaleAction extends ActionLeafSupport {
	
	/**
	 * ��ת
	 * @param 
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		String gctid = req.getParameter("mgctid");
		Integer myear = Integer.parseInt(req.getParameter("myear"));
		Integer mmonth = Integer.parseInt(req.getParameter("mmonth"));
	    String forward = menuId;
	    ActionForward af = new ActionForward();
	    Sale sale = new Sale(); 
	    if("save".equals(menuId)){
	    	String sql = "select count(*) from tblmonth where mgctid='"+gctid+"' and myear='"+myear+"' and mmonth='"+mmonth+"'";
	    	Connection con = DBUtil.getConnection();
	    	List result = (Vector) DBUtil.querySQL(con,sql);
	    	Integer count = Integer.parseInt(((HashMap) result.get(0)).get("1").toString()) ;
	    	if(count >0){
	    		super.saveSuccessfulMsg(req, "���½������Ϣ��¼��!");
	    		return mapping.findForward("backspace");
	    	}
	    }else if("savent".equals(menuId)){
	    	String note = "";
	    	SaleForm sf = new SaleForm();
			sf.setMgctid(gctid);
			sf.setMyear(myear);
			sf.setMmonth(mmonth);
			String sql = "select actstanote from tblactsta where  actstayear= '" +
					myear + "' and actstamonth='" + mmonth + "' and actstagcltid='" + gctid + "'";
			Connection con = DBUtil.getConnection();
	    	List result = (Vector) DBUtil.querySQL(con,sql);
	    	if(result.size() > 0){
	    		Object value = ((HashMap)result.get(0)).get("1");
	    		if(value==null){
	    			note="";
	    		}else{
	    			note = ((HashMap)result.get(0)).get("1").toString();
	    		}
	    	}
	    	sf.setMnote(note);
			ClassHelper.copyProperties(sf, form);
			return mapping.findForward(forward);
	    }
	    SaleForm sf = new SaleForm();
		sf.setMgctid(gctid);
		sf.setMyear(myear);
		sf.setMmonth(mmonth);
		ClassHelper.copyProperties(sf, form);
		return mapping.findForward(forward);
	}
	/**
	 * �������
	 * @param mappi
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward savesale(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Sale sa = new Sale();
		SaleForm saf = (SaleForm) form;
		try {
			ClassHelper.copyProperties(saf, sa);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sa.setMop(dto1.getBsc011());
			sa.setMopdt(DateUtil.getSystemCurrentTime());
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sa);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "¼���½������Ϣ�ɹ�!");
				String gctid = (String) ((HashMap) resEnv.getBody())
						.get("mgctid");
				// ��ô�ҵ��㷵�ص���־��Ϣ
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, gctid, workString);
				return mapping.findForward("backspace");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 * ¼����˵����ʱ�ע
	 */
	@SuppressWarnings("unchecked")
	public ActionForward savent(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Sale sa = new Sale();
		SaleForm saf = (SaleForm) form;
		ActionForward af = new ActionForward();
		//String forward = "/business/xiaozhang.jsp";
		try {
			ClassHelper.copyProperties(saf, sa);
			
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			//��Application�������HashMap
			HashMap<String, Sale> mapRequest = new HashMap<String, Sale>();
			mapRequest.put("beo", sa);
			//��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			//���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.savent(requestEnvelop);
			//�����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "¼�����ʱ�ע�ɹ���");
				return go2Page(req,mapping,"business");

				//return mapping.findForward("xiaozhang");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward("backspace");
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		    Sale sa = new Sale();
		    SaleForm saf = (SaleForm) form;
			saf.setMgctid(req.getParameter("mgctid"));
			saf.setMyear(Integer.parseInt(req.getParameter("myear")));
			saf.setMmonth(Integer.parseInt(req.getParameter("mmonth")));
			ClassHelper.copyProperties(saf, sa);
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Sale> mapRequest = new HashMap<String, Sale>();
			mapRequest.put("beo", sa);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.querysale(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// �ͻ�������Ϣ
			
				ClassHelper.copyProperties(listci.get(0), saf);
				return mapping.findForward("edit");
				
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
	}	
	
	
	public ActionForward editsale(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Sale sa = new Sale();
		SaleForm saf = (SaleForm) form;
		
		try {
			ClassHelper.copyProperties(saf, sa);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sa.setMop(dto1.getBsc011());
			sa.setMopdt(DateUtil.getSystemCurrentTime());
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sa);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.edit(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "�޸��½������Ϣ�ɹ�!");
				// ��ô�ҵ��㷵�ص���־��Ϣ
				FindLog.insertLog(req, sa.getMgctid(), "�޸��½����");
				return mapping.findForward("backspace");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}



}
