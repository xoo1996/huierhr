package org.radf.apps.contract.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Contract;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.contract.facade.ContractFacade;
import org.radf.apps.contract.form.ContractForm;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import bsh.ParseException;

public class ContractAction extends ActionLeafSupport{
	//查询员工列表
	public ActionForward queryUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ContractForm cForm = (ContractForm) form;
		String forward = null;
		
		forward = "/contract/userquery.jsp";

		ActionForward af = new ActionForward();
		Contract contract = new Contract();
		try {
			ClassHelper.copyProperties(cForm, contract);
			contract.setFileKey("con_000001");
			String hql = queryEnterprise(contract);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	//跳转到添加合同页面
	public ActionForward toAddContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "addContract";
		ContractForm cform = (ContractForm)form;
		Contract contract = new Contract();
		ClassHelper.copyProperties(cform, contract);
		Connection con = null;
		try {
			//取到当前最大的合同编号，并加1
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql="select max(conid) as conid from tblcontract";
			List result= (Vector)DBUtil.querySQL(con,sql) ;
			if (result.size() > 0) {
				HashMap<String, String> map = (HashMap<String, String>) result.get(0);
				String conid = map.get("1");
				String a = conid.split("HT")[1];
				int b = Integer.parseInt(a) + 1;
				String c = String.valueOf(b);
				int length = c.length();
				for(int i = 0; i < 5-length; i ++){
					c = "0" + c;
				}
				cform.setConid("HT" + c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	//保存新添加的合同信息
	public ActionForward addContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "addContract";
		ContractForm cform = (ContractForm)form;
		Contract contract = new Contract();
		ClassHelper.copyProperties(cform, contract);
		ResponseEnvelop resEnv = null;
		ContractFacade Facade = (ContractFacade) getService("ContractFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("dto", contract);
		requestEnvelop.setBody(mapRequest);
		resEnv = Facade.addContract(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(req, "保存合同成功！");	
			return mapping.findForward("query");
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(req, aa[3]);
			return mapping.findForward("backspace");
		}
	}
	//查询合同列表
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ContractForm cForm = (ContractForm) form;
		String forward = null;
		String tp = req.getParameter("tp");
		if(tp.equals("update")){
			forward = "/contract/queryupdate.jsp";
		}else{
			forward = "/contract/query.jsp";
		}

		ActionForward af = new ActionForward();
		Contract contract = new Contract();
		try {
			ClassHelper.copyProperties(cForm, contract);
			contract.setFileKey("con_000003");
			String hql = queryEnterprise(contract);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	//跳转到修改合同页面
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "updateContract";
		ContractForm cform = (ContractForm)form;
		Contract contract = new Contract();
		ClassHelper.copyProperties(cform, contract);
		
		return mapping.findForward(forward);
	}
	//保存修改之后的合同
	public ActionForward saveContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ContractForm cform = (ContractForm)form;
		Contract contract = new Contract();
		ClassHelper.copyProperties(cform, contract);
		ResponseEnvelop resEnv = null;
		ContractFacade Facade = (ContractFacade) getService("ContractFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("dto", contract);
		requestEnvelop.setBody(mapRequest);
		resEnv = Facade.saveContract(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(req, "修改合同成功！");	
			return mapping.findForward("queryupdate");
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(req, aa[3]);
			return mapping.findForward("backspace");
		}
	}
	//删除合同
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ContractForm cform = (ContractForm)form;
		Contract contract = new Contract();
		ClassHelper.copyProperties(cform, contract);
		ResponseEnvelop resEnv = null;
		ContractFacade Facade = (ContractFacade) getService("ContractFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("dto", contract);
		requestEnvelop.setBody(mapRequest);
		resEnv = Facade.delContract(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(req, "删除合同成功！");	
			return mapping.findForward("backspace");
			//return mapping.findForward("queryupdate");
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(req, aa[3]);
			return mapping.findForward("backspace");
		}
	}
	//查询合同列表尚未发起续签合同
		public ActionForward queryExpire(ActionMapping mapping, ActionForm form,
				HttpServletRequest req, HttpServletResponse res) throws Exception {
			ContractForm cForm = (ContractForm) form;
			String forward = null;
			forward = "/contract/queryExpire.jsp";

			ActionForward af = new ActionForward();
			Contract contract = new Contract();
			try {
				ClassHelper.copyProperties(cForm, contract);
				contract.setFileKey("con_000006");
				String hql = queryEnterprise(contract);
				af = super.init(req, forward, hql);
				// 检查是否存在？
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "没有查询到符合条件的记录！";
					super.saveSuccessfulMsg(req, msg);
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
			return af;
		}
		
		public ActionForward testContract(ActionMapping mapping, ActionForm form,
				HttpServletRequest req, HttpServletResponse res) throws Exception {
			
			
			String sql_query="select u.userbirthday,u.useridno FROM tblcontract c LEFT OUTER JOIN tbluser u ON c.useremployid = u.useremployid";
			Connection con = null;
			boolean convertSuccess=true;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String useridno=null;
			String originuseridno=null;
			
				con = DBUtil.getConnection();
				java.sql.Statement pstmt1;
				pstmt1 = con.createStatement();
				java.sql.ResultSet rs1 = pstmt1.executeQuery(sql_query);
				while(rs1.next()){
					try {
						useridno = rs1.getString("useridno");
						originuseridno = rs1.getString("useridno");
						useridno=useridno.substring(6,14);
						format.setLenient(false);
					    format.parse(useridno);		
					} catch (Exception e) {
						//this.saveErrors(req, e);
						convertSuccess=false;
						System.out.println("身份证号："+originuseridno+"截取："+useridno);
					}
				}
				rs1.close();
				pstmt1.close();
				DBUtil.closeConnection(con);
			
				
			
				
			return null;
		}
		
		//查询合同列表已经发起续签合同
				public ActionForward queryContract(ActionMapping mapping, ActionForm form,
						HttpServletRequest req, HttpServletResponse res) throws Exception {
					ContractForm cForm = (ContractForm) form;
					String forward = null;
					forward = "/contract/queryContract.jsp";

					ActionForward af = new ActionForward();
					Contract contract = new Contract();
					try {
						ClassHelper.copyProperties(cForm, contract);
						contract.setFileKey("con_000007");
						String hql = queryEnterprise(contract);
						af = super.init(req, forward, hql);
						// 检查是否存在？
						if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
							String msg = "没有查询到符合条件的记录！";
							super.saveSuccessfulMsg(req, msg);
						}
					} catch (AppException ex) {
						this.saveErrors(req, ex);
					} catch (Exception e) {
						this.saveErrors(req, e);
					}
					return af;
				}
}
