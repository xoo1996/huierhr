package org.radf.apps.client.single.action;

import java.awt.Frame;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.xml.crypto.Data;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.charge.facade.ChargeFacade;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.ReDiagnose;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import java.text.SimpleDateFormat;

/**
 * 个人客户信息管理
 */
public class SingleClientAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public SingleClientAction() {
	}

	/**
	 * 新增个人客户(包括诊治与听力图)
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		Diagnose dg = new Diagnose();
		SingleClientForm scForm = (SingleClientForm) form;
		try {
			/*if (scForm.getIctgctid() == null || "".equals(scForm.getIctgctid())) {
				super.saveSuccessfulMsg(req, "请正确录入所属团体");
				return mapping.findForward("backspace");
			}*/
			ClassHelper.copyProperties(scForm, sc);
			ClassHelper.copyProperties(scForm, dg);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());
			sc.setIctdate(DateUtil.getDate());
			if(!"1501000000".equals(dto1.getBsc001())){
				sc.setIctgctid(dto1.getBsc011());//如果是直属店则讲所属团体设置为登录帐号名
			}else{
				if( "A".equals(sc.getIctgctid().substring(0, 1)) && !"A0065".equals(sc.getIctgctid()) ){
					super.saveSuccessfulMsg(req, "直属店客户不可以在总部输！");//如果总部输入则出A0065外的其他直属店都不可以在这里增加个人客户
					return mapping.findForward("backspace");
				}
			}
			if (dg.getDgnlid() == null || "".equals(dg.getDgnlid())) {
				dg.setDgnldprc(null);
			}
			if (dg.getDgnrid() == null || "".equals(dg.getDgnrid())) {
				dg.setDgnrdprc(null);
			}

			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			agList.add(lg4000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(scForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(scForm.getLq500());
			agList.add(lq500);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(scForm.getLq1000());
			agList.add(lq1000);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(scForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(scForm.getLq4000());
			agList.add(lq4000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			agList.add(rg500);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			agList.add(rg4000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(scForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(scForm.getRq500());
			agList.add(rq500);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(scForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(scForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(scForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(scForm.getRq6000());
			agList.add(rq6000);

			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			
//			Connection con = null;
//			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
//			String forward = "/client/single/queryClient.jsp";
//			String queryhql = "";// 用户重命名查询
//			queryhql ="select * from tblindclient where ictgctid='"+scForm.getIctgctid()+"'and ictnm='"+scForm.getIctnm()+"'and ictgender='"+scForm.getIctgender()+"'and ictbdt='"+scForm.getIctbdt()+"'";
//			ActionForward af = new ActionForward();
//			af = init(req, forward, queryhql, "1");
//			if (null != req.getAttribute(GlobalNames.QUERY_DATA)) {
//				String msg = "重命名！";
//				super.saveSuccessfulMsg(req, msg);
//			}
			
			ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
				if(null != req.getParameter("type") && req.getParameter("type").equals("o"))
				{
					return mapping.findForward("inputCustomOrder");
				}
				else
				{
					String ictid = (String) ((HashMap) resEnv.getBody())
					.get("ictid");
			         return new ActionForward(
					"/SingleClientAction.do?method=view&ictid=" + ictid);
				}
				
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
	 * 新增个人客户复诊记录
	 */
	public ActionForward addSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReDiagnose rd = new ReDiagnose();
		SingleClientForm scForm = (SingleClientForm) form;
		String ictid = (String) req.getSession().getAttribute("ictid");
		try {
			ClassHelper.copyProperties(scForm, rd);
			rd.setFctctid(ictid);
			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", rd);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = scFacade.saveSC(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人客户复诊记录成功!");
				String fctctid = (String) ((HashMap) resEnv.getBody())
						.get("fctctid");
				ClassHelper.copyProperties(rd, scForm);

				return go2Page(req, mapping, "client", "1");
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
	 * 新增个人客户言语评估
	 */
	public ActionForward addSCYYPG(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
         
		String forward = "";
		
		try {
			ClassHelper.copyProperties(scForm, sc);
			

			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
//			mapRequest.put("beo1", dg);
	
			requestEnvelop.setBody(mapRequest);
				ResponseEnvelop resEnv = scFacade.saveSCYYPG(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人言语评估成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCYYPG.jsp";
				
					ClassHelper.copyProperties(scForm, singleClient1);
					
					singleClient1.setFileKey("fcyp_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 新增个人客户复诊言语评估
	 */
	public ActionForward addSCTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
				SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
         String type=req.getParameter("type");
		String forward = "";
		
		try {
		
			ClassHelper.copyProperties(scForm, sc);
			
//			ClassHelper.copyProperties(scForm, dg);
		/*	LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());//操作员代码
			sc.setIctdate(DateUtil.getDate());//日期
			*/
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			lg250.setAdgshie(scForm.getLgyb250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			lg500.setAdgshie(scForm.getLgyb500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			lg750.setAdgshie(scForm.getLgyb750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			lg1000.setAdgshie(scForm.getLgyb1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(scForm.getLg1500());
			lg1500.setAdgshie(scForm.getLgyb1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			lg2000.setAdgshie(scForm.getLgyb2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(scForm.getLg3000());
			lg3000.setAdgshie(scForm.getLgyb3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			lg4000.setAdgshie(scForm.getLgyb4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(scForm.getLg6000());
			lg6000.setAdgshie(scForm.getLgyb6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(scForm.getLq250());
			lq250.setAdgshie(scForm.getLqyb250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(scForm.getLq500());
			lq500.setAdgshie(scForm.getLqyb500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(scForm.getLq750());
			lq750.setAdgshie(scForm.getLqyb750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(scForm.getLq1000());
			lq1000.setAdgshie(scForm.getLqyb1000());
			agList.add(lq1000);
			Audiogram lq1500 = new Audiogram();
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(scForm.getLq1500());
			lq1500.setAdgshie(scForm.getLqyb1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(scForm.getLq2000());
			lq2000.setAdgshie(scForm.getLqyb2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(scForm.getLq3000());
			lq3000.setAdgshie(scForm.getLqyb3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(scForm.getLq4000());
			lq4000.setAdgshie(scForm.getLqyb4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(scForm.getLq6000());
			lq6000.setAdgshie(scForm.getLqyb6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			rg250.setAdgshie(scForm.getRgyb250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			rg500.setAdgshie(scForm.getRgyb500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(scForm.getRg750());
			rg750.setAdgshie(scForm.getRgyb750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			rg1000.setAdgshie(scForm.getRgyb1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(scForm.getRg1500());
			rg1500.setAdgshie(scForm.getRgyb1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			rg2000.setAdgshie(scForm.getRgyb2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(scForm.getRg3000());
			rg3000.setAdgshie(scForm.getRgyb3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			rg4000.setAdgshie(scForm.getRgyb4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(scForm.getRg6000());
			rg6000.setAdgshie(scForm.getRgyb6000());
			agList.add(rg6000);
			
			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(scForm.getRq250());
			rq250.setAdgshie(scForm.getRqyb250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(scForm.getRq500());
			rq500.setAdgshie(scForm.getRqyb500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(scForm.getRq750());
			rq750.setAdgshie(scForm.getRqyb750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(scForm.getRq1000());
			rq1000.setAdgshie(scForm.getRqyb1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(scForm.getRq1500());
			rq1500.setAdgshie(scForm.getRqyb1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(scForm.getRq2000());
			rq2000.setAdgshie(scForm.getRqyb2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(scForm.getRq3000());
			rq3000.setAdgshie(scForm.getRqyb3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(scForm.getRq4000());
			rq4000.setAdgshie(scForm.getRqyb4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(scForm.getRq6000());
			rq6000.setAdgshie(scForm.getRqyb6000());
			agList.add(rq6000);

			//插入左右耳声场
			Audiogram ls250 = new Audiogram();
			ls250.setAdglre("L");
			ls250.setAdgtp("S");
			ls250.setAdgfq(250);
			ls250.setAdgadt(scForm.getLs250());
			agList.add(ls250);
			Audiogram ls500 = new Audiogram();
			ls500.setAdglre("L");
			ls500.setAdgtp("S");
			ls500.setAdgfq(500);
			ls500.setAdgadt(scForm.getLs500());
			agList.add(ls500);
			Audiogram ls750 = new Audiogram();
			ls750.setAdglre("L");
			ls750.setAdgtp("S");
			ls750.setAdgfq(750);
			ls750.setAdgadt(scForm.getLs750());
			agList.add(ls750);
			Audiogram ls1000 = new Audiogram();
			ls1000.setAdglre("L");
			ls1000.setAdgtp("S");
			ls1000.setAdgfq(1000);
			ls1000.setAdgadt(scForm.getLs1000());
			agList.add(ls1000);
			Audiogram ls1500 = new Audiogram();
			ls1500.setAdglre("L");
			ls1500.setAdgtp("S");
			ls1500.setAdgfq(1500);
			ls1500.setAdgadt(scForm.getLs1500());
			agList.add(ls1500);
			Audiogram ls2000 = new Audiogram();
			ls2000.setAdglre("L");
			ls2000.setAdgtp("S");
			ls2000.setAdgfq(2000);
			ls2000.setAdgadt(scForm.getLs2000());
			agList.add(ls2000);
			Audiogram ls3000 = new Audiogram();
			ls3000.setAdglre("L");
			ls3000.setAdgtp("S");
			ls3000.setAdgfq(3000);
			ls3000.setAdgadt(scForm.getLs3000());
			agList.add(ls3000);
			Audiogram ls4000 = new Audiogram();
			ls4000.setAdglre("L");
			ls4000.setAdgtp("S");
			ls4000.setAdgfq(4000);
			ls4000.setAdgadt(scForm.getLs4000());
			agList.add(ls4000);
			Audiogram ls6000 = new Audiogram();
			ls6000.setAdglre("L");
			ls6000.setAdgtp("S");
			ls6000.setAdgfq(6000);
			ls6000.setAdgadt(scForm.getLs6000());
			agList.add(ls6000);

			Audiogram rs250 = new Audiogram();
			rs250.setAdglre("R");
			rs250.setAdgtp("S");
			rs250.setAdgfq(250);
			rs250.setAdgadt(scForm.getRs250());
			agList.add(rs250);
			Audiogram rs500 = new Audiogram();
			rs500.setAdglre("R");
			rs500.setAdgtp("S");
			rs500.setAdgfq(500);
			rs500.setAdgadt(scForm.getRs500());
			agList.add(rs500);
			Audiogram rs750 = new Audiogram();
			rs750.setAdglre("R");
			rs750.setAdgtp("S");
			rs750.setAdgfq(750);
			rs750.setAdgadt(scForm.getRs750());
			agList.add(rs750);
			Audiogram rs1000 = new Audiogram();
			rs1000.setAdglre("R");
			rs1000.setAdgtp("S");
			rs1000.setAdgfq(1000);
			rs1000.setAdgadt(scForm.getRs1000());
			agList.add(rs1000);
			Audiogram rs1500 = new Audiogram();
			rs1500.setAdglre("R");
			rs1500.setAdgtp("S");
			rs1500.setAdgfq(1500);
			rs1500.setAdgadt(scForm.getRs1500());
			agList.add(rs1500);
			Audiogram rs2000 = new Audiogram();
			rs2000.setAdglre("R");
			rs2000.setAdgtp("S");
			rs2000.setAdgfq(2000);
			rs2000.setAdgadt(scForm.getRs2000());
			agList.add(rs2000);
			Audiogram rs3000 = new Audiogram();
			rs3000.setAdglre("R");
			rs3000.setAdgtp("S");
			rs3000.setAdgfq(3000);
			rs3000.setAdgadt(scForm.getRs3000());
			agList.add(rs3000);
			Audiogram rs4000 = new Audiogram();
			rs4000.setAdglre("R");
			rs4000.setAdgtp("S");
			rs4000.setAdgfq(4000);
			rs4000.setAdgadt(scForm.getRs4000());
			agList.add(rs4000);
			Audiogram rs6000 = new Audiogram();
			rs6000.setAdglre("R");
			rs6000.setAdgtp("S");
			rs6000.setAdgfq(6000);
			rs6000.setAdgadt(scForm.getRs6000());
			agList.add(rs6000);
			
			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
//			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
				ResponseEnvelop resEnv = scFacade.saveSCTL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if("update".equals(type)){
				super.saveSuccessfulMsg(req, "修改个人听力图成功!");
				}else{
					
					super.saveSuccessfulMsg(req, "保存个人听力图成功!");

				}
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCTL.jsp";
				
					ClassHelper.copyProperties(scForm, singleClient1);
					
					singleClient1.setFileKey("fctl_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 新增售后服务调查表-回访记录
	 */
	public ActionForward addBV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		SingleClientForm scForm = (SingleClientForm) form;
		String type=request.getParameter("type");
		SingleClient sc=new SingleClient();
		try {
			ClassHelper.copyProperties(scForm, sc);
			// 获取服务接口
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveBV(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
					if("update".equals(type)){
					super.saveSuccessfulMsg(request, "修改售后服务调查表成功!");
					}else{
							super.saveSuccessfulMsg(request, "保存售后服务调查表成功!");
					}
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/queryBV.jsp";
				
					ClassHelper.copyProperties(scForm, singleClient1);
					
					singleClient1.setFileKey("bv_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(request, forward1, hql);
				return af;
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}
	
	
	/**
	 * 新增个人客户复诊言语评估
	 */
	public ActionForward addSCSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
				SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
         
		String forward = "";
		
		try {
		
			ClassHelper.copyProperties(scForm, sc);
			
//			ClassHelper.copyProperties(scForm, dg);
		/*	LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());//操作员代码
			sc.setIctdate(DateUtil.getDate());//日期
			*/
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("S");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("S");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("S");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("S");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("S");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(scForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("S");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("S");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(scForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("S");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("S");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(scForm.getLg6000());
			agList.add(lg6000);


			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("S");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("S");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("S");
			rg750.setAdgfq(750);
			rg750.setAdgadt(scForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("S");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("S");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(scForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("S");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("S");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(scForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("S");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("S");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(scForm.getRg6000());
			agList.add(rg6000);


			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
//			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			
			requestEnvelop.setBody(mapRequest);
				ResponseEnvelop resEnv = scFacade.saveSCSC(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人听力图成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCSC.jsp";
				
					ClassHelper.copyProperties(scForm, singleClient1);
					
					singleClient1.setFileKey("fcsc_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 根据客户ID返回客户名称
	 */
	public ActionForward queryGCName(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		GroupClient gc = new GroupClient();
		SingleClientForm scForm = (SingleClientForm) form;
		try {
			gc.setGctid(req.getParameter("clientID"));

			GroupClientFacade gcFacade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", gc);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = gcFacade.queryGCName(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询团体客户名称成功!");
				// 获得团体客户名称
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), gc);
				String clientName = gc.getGctnm();
				scForm.setIctgctnm(clientName);
				res.setCharacterEncoding("GBK");
				res.getWriter().write(clientName);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}

	/**
	 * 查询个人客户信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
		if ("modify".equals(order)) {
			forward = "/client/single/modify.jsp";
		} else
			forward = "/client/single/query.jsp";
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
//			String gctid = dto.getBsc011();
//			if(!"1501000000".equals(dto.getBsc001())){
//				scForm.setIctgctid(gctid);//如果不是总部，则设置客户代码为登录名
//			}else{
//				if(!"".equals(scForm.getIctgctid())&& "A".equals(scForm.getIctgctid().substring(0, 1))){
//					if("A0065".equals(scForm.getIctgctid())){//A0065为听力总部所代表的客户代码
//						scForm.setIctgctid("A0065");
//					}else{
//						super.saveSuccessfulMsg(req, "总部无法查看直属店用户");//如果是总部，并且输入了以A开头的客户代码，则过滤掉。
//						return mapping.findForward("backspace");
//					}
//				}
//			}
			ClassHelper.copyProperties(scForm, sc);
			sc.setFileKey("clt01_000");
			String hql = queryEnterprise(sc);
			
			//如果选择年龄条件
			Integer ages = scForm.getAges();
			Integer aget = scForm.getAget();
			if(ages != 0 && !"".equals(ages)){
				hql += " and round(months_between(sysdate,t.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				hql += " and round(months_between(sysdate,t.ictbdt)/12)<='" + aget + "'";
			}
			if(null!=scForm.getStart()&&!"".equals(scForm.getStart()))
			{
				hql+=" and t.ictdate>=to_date('"+scForm.getStart()+"','yyyy-MM-DD')";
			}
			if(null!=scForm.getEnd()&&!"".equals(scForm.getEnd()))
			{
				hql+=" and  t.ictdate<=to_date('" +scForm.getEnd()+"','yyyy-MM-DD')";
			}
			//是否已配机
			if(null!=scForm.getPjsta()&&!"".equals(scForm.getPjsta()))
			{
				if("0".equals(scForm.getPjsta()))
				{
					hql+=" and t.ictid not in (select dgnctid from tbldiagnose) " +
						"and t.ictid not in(select c.chgcltid from tblnorchg c, tblnorchgdetail d where c.chgid=d.ncdid and d.ncdpid in(select pdtid from tblproduct where pdtnm like '%耳背机%'))";
				}
				if("1".equals(scForm.getPjsta()))
				{
					hql+=" and (t.ictid in (select dgnctid from tbldiagnose) " +
							"or t.ictid in(select c.chgcltid from tblnorchg c, tblnorchgdetail d where c.chgid=d.ncdid and d.ncdpid in(select pdtid from tblproduct where pdtcls='BTE')))";
				}
			}
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

	/**
	 * 客户档案模块中查询个人客户
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//String tp=req.getParameter("tp");
		String forward = "/client/single/queryClient.jsp";
		String fileKey = "clt05_000";



		ActionForward af = new ActionForward();
		SingleClientForm singleForm = (SingleClientForm) form;
		SingleClient single = new SingleClient();
		try {

			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			String gctid = dto.getBsc011();
			if (!"1501000000".equals(dto.getBsc001())) {
				singleForm.setIctgctid(gctid);// 如果不是总部，则设置客户代码为登录名
			} else {
				if (null != singleForm.getIctgctid()
						&& !"".equals(singleForm.getIctgctid())
						&& "A".equals(singleForm.getIctgctid().substring(0, 1))) {
					if ("A0065".equals(singleForm.getIctgctid())) {// A0065为听力总部所代表的客户代码
						singleForm.setIctgctid("A0065");
					} else {
//						singleForm.setIctgctid("直属店不能在总部下订单");// 如果是总部，并且输入了以A开头的客户代码，则过滤掉。
//						super.saveSuccessfulMsg(req, "直属店不能在总部下订单");
//						return mapping.findForward("backspace");
					}
				}
			}
			ClassHelper.copyProperties(singleForm, single);
			single.setFileKey(fileKey);
			String hql = queryEnterprise(single);
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
	
	/**
	 * 跳转到新增个人客户页面
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward forwardClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictnm = req.getParameter("ictnm");

		SingleClientForm singleForm = (SingleClientForm) form;
		//SingleClient single = new SingleClient();
		//HttpSession session=req.getSession();
		if(null != ictnm){
			singleForm.setIctnm(ictnm);
		}
		//String tp=req.getParameter("tp");
		String forward="addClient";
		ClassHelper.copyProperties(singleForm, form);
		return mapping.findForward(forward);
	}
	
	
	/**
	 * 新增个人客户信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward addClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		Diagnose dg = new Diagnose();
		SingleClientForm singleForm = (SingleClientForm)form; 
		//OrderHeaderForm scForm = (OrderHeaderForm) form;
		String forward="";
		String ictPro,ictCity,ictCounty;
		String ictPhone,ictLandLine;
		String tel = "";
		try {
			ClassHelper.copyProperties(singleForm, sc);
			ClassHelper.copyProperties(singleForm, dg);
			transToString(singleForm,sc);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());
			sc.setIctdate(DateUtil.getDate());
			if(!"1501000000".equals(dto1.getBsc001())){
				sc.setIctgctid(dto1.getBsc011());//直属店则插入直属店客户代码
			}
			
			if (dg.getDgnlid() == null || "".equals(dg.getDgnlid())) {
				dg.setDgnldprc(null);
			}
			if (dg.getDgnrid() == null || "".equals(dg.getDgnrid())) {
				dg.setDgnrdprc(null);
			}
			ictPro = sc.getIctpro()==null?"":sc.getIctpro().split(",")[1];//省份
			ictCity = sc.getIctcity()==null?"":sc.getIctcity().split(",")[1];//市
			ictCounty = sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[1];//县
			ictPhone = sc.getIctphone()==null?"":sc.getIctphone();//手机
			ictLandLine = sc.getIctlandline()==null?"":sc.getIctlandline();//固定电话
/*			sc.setIctpro(sc.getIctpro()==null?"":sc.getIctpro().split(",")[0]);
			sc.setIctcity(sc.getIctcity()==null?"":sc.getIctcity().split(",")[0]);
			sc.setIctcounty(sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[0]);*/
			if(ictPro!=null&&!"".equals(ictPro)){
				sc.setIctaddr(ictPro+ictCity+ictCounty+sc.getIctdetailaddr());
			}
			if(!"".equals(ictPhone)){
				tel = tel + ictPhone + "    ";
			}
			if(!"".equals(ictLandLine)){
				tel = tel + ictLandLine;
			}
			if(!"".equals(tel)&&tel!=null){
				sc.setIcttel(tel);
			}
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(singleForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(singleForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(singleForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(singleForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(singleForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(singleForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(singleForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(singleForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(singleForm.getLg6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(singleForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(singleForm.getLq500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(singleForm.getLq750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(singleForm.getLq1000());
			agList.add(lq1000);

			Audiogram lq1500 = new Audiogram();
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(singleForm.getLq1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(singleForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(singleForm.getLq3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(singleForm.getLq4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(singleForm.getLq6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(singleForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(singleForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(singleForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(singleForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(singleForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(singleForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(singleForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(singleForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(singleForm.getRg6000());
			agList.add(rg6000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(singleForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(singleForm.getRq500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(singleForm.getRq750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(singleForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(singleForm.getRq1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(singleForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(singleForm.getRq3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(singleForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(singleForm.getRq6000());
			agList.add(rq6000);

			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			mapRequest.put("type", req.getParameter("type"));
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List<SingleClient> listci = (ArrayList) mapResponse.get("sc");
//				SingleClient scli = (SingleClient) mapResponse.get("sc");// 个人客户信息
				ClassHelper.copyProperties(listci.get(0), singleForm);	
				req.getSession().setAttribute("ictid", singleForm.getIctid()); //生产听力图时获取客户id

				return go2Page(req,mapping,"client");

				
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
	 * 查询个人客户定制记录
	 */
	public ActionForward queryCus(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Customization cz = new Customization();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = "/client/single/queryCus.jsp";
		try {
			cz.setTmkcltid(scForm.getIctid());
			cz.setFileKey("cus01_013");
			String hql = queryEnterprise(cz);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无定制记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 查询个人客户维修记录
	 */
	public ActionForward queryRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = "/client/single/queryRep.jsp";
		try {
			rep.setRepcltid(scForm.getIctid());
			rep.setFileKey("rep01_005");
			String hql = queryEnterprise(rep);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无维修记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 查询个人客户退机记录
	 */
	public ActionForward queryTui(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Order ord = new Order();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = "/client/single/queryTui.jsp";
		try {
			ord.setFolctid(scForm.getIctid());
			ord.setFileKey("ord06_000");
			String hql = queryEnterprise(ord);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无退机记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 查询个人客户复诊信息
	 */
	public ActionForward querySC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReDiagnose rd = new ReDiagnose();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = "/client/single/querySC.jsp";
		try {
			ClassHelper.copyProperties(scForm, rd);
			rd.setFctctid(scForm.getIctid());
			rd.setFileKey("reDiagnose_select");
			String hql = queryEnterprise(rd);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无复诊记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 查询个人客户复诊听力信息
	 */
	public ActionForward queryFZTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient=new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		String forward = "/client/single/querySCTL.jsp";
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			
			singleClient.setFileKey("fctl_select");
			req.getSession().setAttribute("fctlictid",scForm.getIctid());
			req.getSession().setAttribute("fctlictgctid", scForm.getIctgctid());
			req.getSession().setAttribute("fctlgctnm",scForm.getGctnm());
			req.getSession().setAttribute("fctlictnm", scForm.getIctnm());
			String hql = queryEnterprise(singleClient);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无复诊记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			// 检查是否存在？
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	/**
	 * 查询个人客户售后服务调查信息
	 */
	
	public ActionForward queryBV(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient=new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		//age
		Date icdbt=scForm.getIctbdt();
		Calendar c = Calendar.getInstance();  
		c.setTime(icdbt);  
		int year1=c.get(Calendar.YEAR);
		c.setTime(DateUtil.getSystemCurrentTime());
		int year2=c.get(Calendar.YEAR);
		scForm.setIctage(String.valueOf(year2-year1));
		String forward = "/client/single/queryBV.jsp";
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			
			singleClient.setFileKey("bv_select");			//sql？
			req.getSession().setAttribute("bvictid",scForm.getIctid());
			req.getSession().setAttribute("bvgctnm",scForm.getGctnm());
			req.getSession().setAttribute("bvictnm", scForm.getIctnm());
			req.getSession().setAttribute("bvictgender", scForm.getIctgender());
			req.getSession().setAttribute("bvage", scForm.getIctage());
			req.getSession().setAttribute("bvicttel", scForm.getIcttel());
			req.getSession().setAttribute("bvictaddr", scForm.getIctaddr());
			String hql = queryEnterprise(singleClient);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无回访记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			// 检查是否存在？
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 查询个人客户复诊言语评估
	 */
	public ActionForward queryFZYYPG(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient=new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		req.getSession().setAttribute("fcypictid",scForm.getIctid());
		req.getSession().setAttribute("fcypictgctid", scForm.getIctgctid());
		req.getSession().setAttribute("fcypgctnm",scForm.getGctnm());
		req.getSession().setAttribute("fcypictnm", scForm.getIctnm());
		String forward = "/client/single/querySCYYPG.jsp";
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			
			singleClient.setFileKey("fcyp_select");
			String hql = queryEnterprise(singleClient);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无复诊记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			// 检查是否存在？
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	/**
	 * 查询个人客户复诊声场信息
	 */
	public ActionForward queryFZSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient=new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		ActionForward af = new ActionForward();
		req.getSession().setAttribute("fcscictid",scForm.getIctid());
		req.getSession().setAttribute("fcscictgctid", scForm.getIctgctid());
		req.getSession().setAttribute("fcscgctnm",scForm.getGctnm());
		req.getSession().setAttribute("fcscictnm", scForm.getIctnm());
		String forward = "/client/single/querySCSC.jsp";
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			
			singleClient.setFileKey("fcsc_select");
			String hql = queryEnterprise(singleClient);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无复诊记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			// 检查是否存在？
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	
	/**
	 * 查看个人客户详细信息
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String tp=req.getParameter("tp");
		if (ictid == null) {
			ictid = (String) req.getAttribute("ictid");
		}
		req.getSession().setAttribute("ictid", ictid);
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(scForm, sc);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String,Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("tp", tp);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
				ClassHelper.copyProperties(listci.get(0), scForm);
				
				ClassHelper.copyProperties(listci2.get(0), scForm);
				if(null != listci1 && listci1.size() !=0 ){
					ClassHelper.copyProperties(listci1.get(0), scForm);
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward("view");
	}

	/**
	 * 修改个人客户详细信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String tp=req.getParameter("tp");
		req.getSession().setAttribute("ictid", ictid);
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(scForm, sc);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String,Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("tp", tp);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				if(null!=tp&&!"".equals(tp)&&!"m".equals(tp))
	            {
					List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
					ClassHelper.copyProperties(listci1.get(0), scForm);
	            }
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
				ClassHelper.copyProperties(listci.get(0), scForm);			
				ClassHelper.copyProperties(listci2.get(0), scForm);
				if((scForm.getIctphone()==null||scForm.getIctphone()=="")&&(scForm.getIcttel()!=null||scForm.getIcttel()!="")){
					scForm.setIctphone(scForm.getIcttel());
				}
				req.getSession().setAttribute("gumo", scForm.getGumo());
				req.getSession().setAttribute("jiancha", scForm.getJiancha());
				req.getSession().setAttribute("chuandao", scForm.getChuandao());
				req.getSession().setAttribute("ganyin", scForm.getGanyin());
				req.getSession().setAttribute("chuli", scForm.getChuli());
				req.getSession().setAttribute("ictpro", scForm.getIctpro());
				req.getSession().setAttribute("ictcity", scForm.getIctcity());
				req.getSession().setAttribute("ictcounty", scForm.getIctcounty());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		   return mapping.findForward("alter");
	}

	/**
	 * 保存修改后的个人客户信息
	 */
	public ActionForward saveModified(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		Diagnose dg = new Diagnose();
		SingleClientForm scForm = (SingleClientForm) form;
		String ictPro,ictCity,ictCounty;
		String ictPhone,ictLandLine;
		String tel = "";
		try {
			// 设定经办信息
			ClassHelper.copyProperties(scForm, sc);
			ClassHelper.copyProperties(scForm, dg);
			transToString(scForm,sc);
			dg.setDgnctid(scForm.getIctid());
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc010());
			
			ictPro = sc.getIctpro()==null?"":sc.getIctpro().split(",")[1];//省份
			ictCity = sc.getIctcity()==null?"":sc.getIctcity().split(",")[1];//市
			ictCounty = sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[1];//县
			ictPhone = sc.getIctphone()==null?"":sc.getIctphone();//手机
			ictLandLine = sc.getIctlandline()==null?"":sc.getIctlandline();//固定电话
/*			sc.setIctpro(sc.getIctpro()==null?"":sc.getIctpro().split(",")[0]);
			sc.setIctcity(sc.getIctcity()==null?"":sc.getIctcity().split(",")[0]);
			sc.setIctcounty(sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[0]);*/
			if(ictPro!=null&&!"".equals(ictPro)){
				sc.setIctaddr(ictPro+ictCity+ictCounty+sc.getIctdetailaddr());
			}
			if(!"".equals(ictPhone)){
				tel = tel + ictPhone + "    ";
			}
			if(!"".equals(ictLandLine)){
				tel = tel + ictLandLine;
			}
			if(!"".equals(tel)&&tel!=null){
				sc.setIcttel(tel);
			}
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdgctid(sc.getIctid());
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdgctid(sc.getIctid());
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdgctid(sc.getIctid());
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdgctid(sc.getIctid());
			lg1000.setAdgctid(sc.getIctid());
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdgctid(sc.getIctid());
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(scForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdgctid(sc.getIctid());
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdgctid(sc.getIctid());
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(scForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdgctid(sc.getIctid());
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdgctid(sc.getIctid());
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(scForm.getLg6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdgctid(sc.getIctid());
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(scForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdgctid(sc.getIctid());
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(scForm.getLq500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdgctid(sc.getIctid());
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(scForm.getLq750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdgctid(sc.getIctid());
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(scForm.getLq1000());
			agList.add(lq1000);
			Audiogram lq1500 = new Audiogram();
			lq1500.setAdgctid(sc.getIctid());
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(scForm.getLq1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdgctid(sc.getIctid());
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(scForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdgctid(sc.getIctid());
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(scForm.getLq3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdgctid(sc.getIctid());
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(scForm.getLq4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdgctid(sc.getIctid());
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(scForm.getLq6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdgctid(sc.getIctid());
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdgctid(sc.getIctid());
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdgctid(sc.getIctid());
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(scForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdgctid(sc.getIctid());
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdgctid(sc.getIctid());
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(scForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdgctid(sc.getIctid());
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdgctid(sc.getIctid());
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(scForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdgctid(sc.getIctid());
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdgctid(sc.getIctid());
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(scForm.getRg6000());
			agList.add(rg6000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdgctid(sc.getIctid());
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(scForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdgctid(sc.getIctid());
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(scForm.getRq500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdgctid(sc.getIctid());
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(scForm.getRq750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdgctid(sc.getIctid());
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(scForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdgctid(sc.getIctid());
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(scForm.getRq1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdgctid(sc.getIctid());
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(scForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdgctid(sc.getIctid());
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(scForm.getRq3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdgctid(sc.getIctid());
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(scForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdgctid(sc.getIctid());
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(scForm.getRq6000());
			agList.add(rq6000);
			// 获取服务接口
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改个人客户信息成功!");
				// 下一个页面，还是配置项查询修改页面
				// cf.setCiid(null);
				// cf.setCiname(null);
				// cf.setCitype(null);
				return mapping.findForward("modify");
				// return queryCI(mapping, form, request, response);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 删除个人客户信息
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		try {
			ClassHelper.copyProperties(scForm, sc);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", sc);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除个人客户信息成功!");
				FindLog.insertLog(req, ictid, "删除个人客户信息");
				return go2Page(req, mapping, "client");
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
	 * 删除个人客户复诊信息
	 */
	public ActionForward deleteSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReDiagnose rd = new ReDiagnose();
		SingleClientForm scForm = (SingleClientForm) form;
		String ictid = (String) req.getSession().getAttribute("ictid");
		try {
			ClassHelper.copyProperties(scForm, rd);
			rd.setFctctid(ictid);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, ReDiagnose> mapRequest = new HashMap<String, ReDiagnose>();
			mapRequest.put("beo", rd);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteSC(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除用户复诊记录成功!");
				FindLog.insertLog(req, scForm.getIctid(), "删除用户复诊记录");
				return go2Page(req, mapping, "client");
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
	 * 删除个人客户复诊听力图
	 */
	public ActionForward deleteSCTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
//		String ictid = (String) req.getSession().getAttribute("ictid");
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", singleClient);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteSCTL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除用户复诊记录成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCTL.jsp";
					ClassHelper.copyProperties(scForm, singleClient1);
					singleClient1.setFileKey("fctl_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 删除个人客户售后服务调查表
	 */
	public ActionForward deleteBV(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", singleClient);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			String a=scForm.getBvid();
			ResponseEnvelop resEnv = facade.deleteBV(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除用户售后服务调查表成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/queryBV.jsp";
					ClassHelper.copyProperties(scForm, singleClient1);
					singleClient1.setFileKey("bv_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 删除个人客户复诊言语评估
	 */
	public ActionForward deleteSCYYPG(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
//		String ictid = (String) req.getSession().getAttribute("ictid");
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", singleClient);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteSCYYPG(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除用户复诊言语评估记录成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCYYPG.jsp";
					ClassHelper.copyProperties(scForm, singleClient1);
					singleClient1.setFileKey("fcyp_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 删除个人客户复诊言语评估
	 */
	public ActionForward deleteSCSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient singleClient = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
//		String ictid = (String) req.getSession().getAttribute("ictid");
		try {
			ClassHelper.copyProperties(scForm, singleClient);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", singleClient);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteSCSC(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除用户复诊记录成功!");
				ActionForward af = new ActionForward();
				SingleClient singleClient1=new SingleClient();
				String forward1 = "/client/single/querySCSC.jsp";
					ClassHelper.copyProperties(scForm, singleClient1);
					singleClient1.setFileKey("fcsc_select");
					String hql = queryEnterprise(singleClient1);
					af = super.init(req, forward1, hql);
				return af;
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
	 * 进入定制页面
	 */
	public ActionForward customize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		System.out.println(id);
		return mapping.findForward("customize");
	}
	/**
	 * 进入新增售后服务调查表页面//
	 */
	public ActionForward enterAddBK(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		return mapping.findForward("addBK");
	}
	/**
	 * 进入新增用户复诊记录页面
	 */
	public ActionForward enterAddSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClientForm scForm = (SingleClientForm) form;
		scForm.setFctcdt(null);
		return mapping.findForward("addSC");
	}
	/**
	 * 打印功能
	 */
	
		public ActionForward printVisit(ActionMapping mapping, ActionForm form,
				HttpServletRequest req, HttpServletResponse res) throws Exception {
			SingleClientForm header = (SingleClientForm) form;
			Connection conn = null;
			try {
				File reportFile = null;
				// 报表编译之后生成的.jasper 文件的存放位置
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\report_bv.jasper"));
				// 传递报表中用到的参数值
				Map<String, Object> parameters = new HashMap<String, Object>();
				// "Name"是报表中定义过的一个参数名称,其类型为String 型
				parameters.put("bvid", header.getBvid());
				// 连接到数据库
				conn = DBUtil.getConnection();

				byte[] bytes = JasperRunManager.runReportToPdf(
						reportFile.getPath(), parameters, conn);

				res.setContentType("application/pdf");
				// res.setContentLength(bytes.length);
				ServletOutputStream ouputStream = res.getOutputStream();
				// 自动打印
				PdfReader reader = new PdfReader(bytes);
				StringBuffer script = new StringBuffer();
				script.append(
						"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
						.append("\r\nthis.closeDoc();");
				ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
				PdfStamper stamp = new PdfStamper(reader, bos);
				stamp.setViewerPreferences(PdfWriter.HideMenubar
						| PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
				stamp.addJavaScript(script.toString());
				stamp.close();
				ouputStream.write(bos.toByteArray());
				ouputStream.flush();
				ouputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection(conn);
			}
			return null;
		}
		
	/**
	 * 进入新增用户回访记录页面
	 */
	public ActionForward enterAddBV(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		SingleClientForm scForm = (SingleClientForm) form;
//		scForm.setFctcdt(null);
		SingleClientForm sc = (SingleClientForm) form;
		sc.setBvassess("undo");
		String bvno=sc.getIctid();
		Connection con =DBUtil.getConnection();
		List result = (Vector) DBUtil.querySQL(con,
				"SELECT m.tmksid,p.pdtnm,m.tmkplr,a.foldt FROM tblmaking m LEFT OUTER JOIN tblproduct p ON p.pdtid=m.tmkpid LEFT OUTER JOIN tblfolio a ON a.folno=m.tmkfno WHERE tmkcltid='"+bvno+"' ORDER BY a.foldt DESC");
		
		if(result.size()>0){
		Date dt1 = (Date) ((HashMap) result.get(0)).get("4");	
		String ear1=(String) ((HashMap) result.get(0)).get("3");
		if(dt1!=null&&result.size()>1){
			Date dt2 = (Date) ((HashMap) result.get(1)).get("4");	
			String ear2=(String) ((HashMap) result.get(1)).get("3");
			if(dt2!=null&&dt1.equals(dt2)){
			if(ear1.equals("0")){
				sc.setBvleftnum((String) ((HashMap) result.get(0)).get("1"));
				sc.setBvlefttype((String) ((HashMap) result.get(0)).get("2"));
				sc.setBvrightnum((String) ((HashMap) result.get(1)).get("1"));
				sc.setBvrighttype((String) ((HashMap) result.get(1)).get("2"));
				}else{
					sc.setBvrightnum((String) ((HashMap) result.get(0)).get("1"));
					sc.setBvrighttype((String) ((HashMap) result.get(0)).get("2"));
					sc.setBvleftnum((String) ((HashMap) result.get(1)).get("1"));
					sc.setBvlefttype((String) ((HashMap) result.get(1)).get("2"));
				}
			}else {
				if(ear1.equals("0")){
				sc.setBvleftnum((String) ((HashMap) result.get(0)).get("1"));
				sc.setBvlefttype((String) ((HashMap) result.get(0)).get("2"));
				}else{
					sc.setBvrightnum((String) ((HashMap) result.get(0)).get("1"));
					sc.setBvrighttype((String) ((HashMap) result.get(0)).get("2"));
				}
			}
			}else{
				if(ear1.equals("0")){
					sc.setBvleftnum((String) ((HashMap) result.get(0)).get("1"));
					sc.setBvlefttype((String) ((HashMap) result.get(0)).get("2"));
					}else{
						sc.setBvrightnum((String) ((HashMap) result.get(0)).get("1"));
						sc.setBvrighttype((String) ((HashMap) result.get(0)).get("2"));
					}
			}
		}
		return mapping.findForward("addBV");
	}
	/**
	 * 进入新增用户复诊听力图气岛和骨岛页面
	 */
	public ActionForward enterAddSCTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClientForm scForm = (SingleClientForm) form;
		scForm.setFctcdt(null);
		return mapping.findForward("addSCTL");
	}
	/**
	 * 进入新增用户言语评估
	 */
	public ActionForward enterAddSCYYPG(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClientForm scForm = (SingleClientForm) form;
		scForm.setFctcdt(null);
		return mapping.findForward("addSCYYPG");
	}
	/**
	 * 进入新增用户声场记录
	 */
	public ActionForward enterAddSCSC(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClientForm scForm = (SingleClientForm) form;
		scForm.setFctcdt(null);
		return mapping.findForward("addSCSC");
	}
	public ActionForward checkSingleName(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		String forward = "";
		try {
			ClassHelper.copyProperties(scForm, sc);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());//操作员代码
			sc.setIctdate(DateUtil.getDate());//日期
			if(!"1501000000".equals(dto1.getBsc001())) {
				sc.setIctgctid(dto1.getBsc011());// 直属店则插入直属店客户代码//所属团体代码
			}

//			String ictnm = req.getParameter("ictnm");
//			sc.setIctnm(ictnm);
//			String ictgender = req.getParameter("ictgender");
//			sc.setIctgender(ictgender);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//			//Date ictbdt = sdf.parse(req.getParameter("ictbdt")+" 00:00:00");
//			Date ictbdt = sdf.parse(sc.getIctbdt()+" 00:00:00");
//			sc.setIctbdt(ictbdt);
			
			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			
			ResponseEnvelop resEnv = scFacade.checkSingleName(requestEnvelop);
			// 处理返回结果
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				if(null!=(HashMap)resEnv.getBody())
				{
					jsonStr+="{tdspvo:'yes'}";
					jsonStr+="]";
					res.getWriter().write(jsonStr);
				}
				else
				{
					jsonStr+="{tdspvo:''}";
					jsonStr+="]";
					res.getWriter().write(jsonStr);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
		}
		return null;
		
	}
	public ActionForward addClient1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		//Diagnose dg = new Diagnose();
		//OrderHeaderForm scForm = (OrderHeaderForm) form;
		//String tp = req.getParameter("tp");
		String forward = "";
		String ictPro,ictCity,ictCounty;
		String ictPhone,ictLandLine;
		String tel = "";
		try {
			/*
			 * if (scForm.getIctgctid() == null ||
			 * "".equals(scForm.getIctgctid())) { super.saveSuccessfulMsg(req,
			 * "请正确录入所属团体"); return mapping.findForward("backspace"); }
			 */
			ClassHelper.copyProperties(scForm, sc);
			transToString(scForm,sc);
//			ClassHelper.copyProperties(scForm, dg);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());//操作员代码
			sc.setIctdate(DateUtil.getDate());//日期
			ictPro = sc.getIctpro()==null?"":sc.getIctpro().split(",")[1];//省份
			ictCity = sc.getIctcity()==null?"":sc.getIctcity().split(",")[1];//市
			ictCounty = sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[1];//县
			ictPhone = sc.getIctphone()==null?"":sc.getIctphone();//手机
			ictLandLine = sc.getIctlandline()==null?"":sc.getIctlandline();//固定电话
			/*sc.setIctpro(sc.getIctpro()==null?"":sc.getIctpro().split(",")[0]);
			sc.setIctcity(sc.getIctcity()==null?"":sc.getIctcity().split(",")[0]);
			sc.setIctcounty(sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[0]);*/
			if(ictPro!=null&&!"".equals(ictPro)){
				sc.setIctaddr(ictPro+ictCity+ictCounty+sc.getIctdetailaddr());
			}
			if(!"".equals(ictPhone)){
				tel = tel + ictPhone + "    ";
			}
			if(!"".equals(ictLandLine)){
				tel = tel + ictLandLine;
			}
			if(!"".equals(tel)&&tel!=null){
				sc.setIcttel(tel);
			}
			if(!"1501000000".equals(dto1.getBsc001())) {
				sc.setIctgctid(dto1.getBsc011());// 直属店则插入直属店客户代码//所属团体代码
			}

//			if (dg.getDgnlid() == null || "".equals(dg.getDgnlid())) {
//				dg.setDgnldprc(null);
//			}
//			if (dg.getDgnrid() == null || "".equals(dg.getDgnrid())) {
//				dg.setDgnrdprc(null);
//			}

			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(scForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(scForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(scForm.getLg6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(scForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(scForm.getLq500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(scForm.getLq750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(scForm.getLq1000());
			agList.add(lq1000);

			Audiogram lq1500 = new Audiogram();
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(scForm.getLq1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(scForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(scForm.getLq3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(scForm.getLq4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(scForm.getLq6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(scForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(scForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(scForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(scForm.getRg6000());
			agList.add(rg6000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(scForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(scForm.getRq500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(scForm.getRq750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(scForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(scForm.getRq1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(scForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(scForm.getRq3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(scForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(scForm.getRq6000());
			agList.add(rq6000);



			
			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
//			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			mapRequest.put("type", req.getParameter("type"));
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
		
			
//			Connection con = null;
//			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
//			forward = "/client/single/queryClient.jsp";
//			String queryhql = "";// 用户重命名查询
//			queryhql ="select * from tblindclient where ictgctid='"+scForm.getIctgctid()+"' and ictnm='"+scForm.getIctnm()+"' and ictgender='"+scForm.getIctgender()+"' and ictbdt=to_date('"+scForm.getIctbdt()+ "','yyyy-mm-dd')";
//			List result = (Vector) DBUtil.querySQL(con,queryhql);
//			if(null != result && result.size() !=0 ){
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//				Object[] options = {"确定","取消"};
//				int response=JOptionPane.showOptionDialog(null, "此用户已经存在！是否继续添加该用户？", "提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//				if(response==0)
//				{ 
////					String msg = "继续！";
////					super.saveSuccessfulMsg(req, msg);
//					ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
//					// 处理返回结果
//					returnValue = processRevt(resEnv);
//					if (returnValue.isSucessFlag()) {
//						super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
//						HashMap mapResponse = (HashMap) resEnv.getBody();
//						List<SingleClient> listci = (ArrayList) mapResponse.get("sc");
//						// SingleClient scli = (SingleClient) mapResponse.get("sc");//
//						// 个人客户信息
//						ClassHelper.copyProperties(listci.get(0), scForm);
//						req.getSession().setAttribute("ictid", scForm.getIctid()); // 生产听力图时获取客户id
//		
//		                forward="queryClient";		
//						return mapping.findForward(forward);
//		
//					} else {
//						String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
//								"|");
//						super.saveSuccessfulMsg(req, aa[3]);
//						return mapping.findForward("backspace");
//					}
//					
//				}
//				else
//				{ 
////					String msg = "取消！";
////					super.saveSuccessfulMsg(req, msg);
//					return mapping.findForward("backspace");
//				}
//			}
//			else{
//				ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
//				// 处理返回结果
//				returnValue = processRevt(resEnv);
//				if (returnValue.isSucessFlag()) {
//					super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
//					HashMap mapResponse = (HashMap) resEnv.getBody();
//					List<SingleClient> listci = (ArrayList) mapResponse.get("sc");
//					// SingleClient scli = (SingleClient) mapResponse.get("sc");//
//					// 个人客户信息
//					ClassHelper.copyProperties(listci.get(0), scForm);
//					req.getSession().setAttribute("ictid", scForm.getIctid()); // 生产听力图时获取客户id
//	
//	                forward="queryClient";		
//					return mapping.findForward(forward);
//	
//				} else {
//					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
//							"|");
//					super.saveSuccessfulMsg(req, aa[3]);
//					return mapping.findForward("backspace");
//				}
//			}
			
		
			
			
			ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List<SingleClient> listci = (ArrayList) mapResponse.get("sc");
				// SingleClient scli = (SingleClient) mapResponse.get("sc");//
				// 个人客户信息
				ClassHelper.copyProperties(listci.get(0), scForm);
				req.getSession().setAttribute("ictid", scForm.getIctid()); // 生产听力图时获取客户id

                forward="queryClient";		
				return mapping.findForward(forward);

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
	public void transToString(SingleClientForm form,SingleClient sc){
		sc.setGumo(trans(form.getGumo()));
		sc.setJiancha(trans(form.getJiancha()));
		sc.setChuandao(trans(form.getChuandao()));
		sc.setGanyin(trans(form.getGanyin()));
		sc.setChuli(trans(form.getChuli()));
	}
	public String trans(String[] str){
		if(str!=null&&str.length>0){
			String result="";
			for(int i =0; i <str.length;i++){
				result += str[i]+",";
			}
			return result;
		}else return "";
	}
	/**
	 * 修改个人客户详细信息
	 */
	public ActionForward showdetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String tp=req.getParameter("tp");
		req.getSession().setAttribute("ictid", ictid);
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(scForm, sc);
			SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String,Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("tp", tp);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				if(null!=tp&&!"".equals(tp)&&!"m".equals(tp))
	            {
					List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
					ClassHelper.copyProperties(listci1.get(0), scForm);
	            }
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
				ClassHelper.copyProperties(listci.get(0), scForm);			
				ClassHelper.copyProperties(listci2.get(0), scForm);
				if((scForm.getIctphone()==null||scForm.getIctphone()=="")&&(scForm.getIcttel()!=null||scForm.getIcttel()!="")){
					scForm.setIctphone(scForm.getIcttel());
				}
				req.getSession().setAttribute("gumo", scForm.getGumo());
				req.getSession().setAttribute("jiancha", scForm.getJiancha());
				req.getSession().setAttribute("chuandao", scForm.getChuandao());
				req.getSession().setAttribute("ganyin", scForm.getGanyin());
				req.getSession().setAttribute("chuli", scForm.getChuli());
				req.getSession().setAttribute("ictpro", scForm.getIctpro());
				req.getSession().setAttribute("ictcity", scForm.getIctcity());
				req.getSession().setAttribute("ictcounty", scForm.getIctcounty());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		   return mapping.findForward("showdetail");
	}


/**
 * 显示复诊听力图详情
 */
public ActionForward showdetailSCTL(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
	/*String ictid = req.getParameter("ictid");
	String tp=req.getParameter("tp");*/
	/*req.getSession().setAttribute("ictid", ictid);
*/	
	   SingleClient sc = new SingleClient();
	    SingleClientForm scForm = (SingleClientForm) form;
	    String type=req.getParameter("type");
	    req.getSession().setAttribute("fcttlid", scForm.getFcttlid());
		ClassHelper.copyProperties(scForm, sc);
		SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String,Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("beo", sc);
	
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.printSCTL(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap mapResponse = (HashMap) resEnv.getBody();
			List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
			List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
			List listci3 = (ArrayList) mapResponse.get("beo3");// 声场信息
			ClassHelper.copyProperties(listci.get(0), scForm);			
			ClassHelper.copyProperties(listci2.get(0), scForm);
			ClassHelper.copyProperties(listci3.get(0), scForm);
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveErrors(req, new AppException(aa[3]));
		}
	  if("show".equals(type)){
	   return mapping.findForward("showdetailSCTL");
	   }else {
		return mapping.findForward("showUpdateDetailSCTL");
	    }
}

/**
 * 显示售后服务调查表详情
 */
public ActionForward showdetailBV(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
	/*String ictid = req.getParameter("ictid");
	String tp=req.getParameter("tp");*/
	/*req.getSession().setAttribute("ictid", ictid);
*/	
	   SingleClient sc = new SingleClient();
	    SingleClientForm scForm = (SingleClientForm) form;
	    String type=req.getParameter("type");
	    req.getSession().setAttribute("bvid", scForm.getBvid());
		ClassHelper.copyProperties(scForm, sc);
		SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String,Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("beo", sc);
	
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.printBV(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap mapResponse = (HashMap) resEnv.getBody();
			List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
			ClassHelper.copyProperties(listci.get(0), scForm);			
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveErrors(req, new AppException(aa[3]));
		}
	  if("show".equals(type)){
	   return mapping.findForward("showBV");
	   }else {
		return mapping.findForward("showUpdateBV");
	    }
}

/**
 * 显示复诊听力图详情
 */
public ActionForward showdetailSCSC(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
	/*String ictid = req.getParameter("ictid");
	String tp=req.getParameter("tp");*/
	/*req.getSession().setAttribute("ictid", ictid);
*/	
	SingleClient sc = new SingleClient();
	SingleClientForm scForm = (SingleClientForm) form;
	  req.getSession().setAttribute("fcscid", scForm.getFcscid());
		ClassHelper.copyProperties(scForm, sc);
		SingleClientFacade facade = (SingleClientFacade) getService("SingleClientFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String,Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("beo", sc);
	
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.printSCSC(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap mapResponse = (HashMap) resEnv.getBody();
			List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
			List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
			ClassHelper.copyProperties(listci.get(0), scForm);			
			ClassHelper.copyProperties(listci2.get(0), scForm);
			
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveErrors(req, new AppException(aa[3]));
		}
	
	   return mapping.findForward("showdetailSCSC");
}
/**
 * 回访记录统计
 */
	public ActionForward bvCount(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		SingleClientForm scForm = (SingleClientForm) form;
		String forward = "/client/single/bvcount.jsp";
		ActionForward af = new ActionForward();
		try {
			ClassHelper.copyProperties(scForm, sc);
			sc.setFileKey("bv_count");// TZ_EXP_SQL
			String hql = queryEnterprise(sc);
			
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
