/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */

package org.radf.apps.customization.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.form.BusinessForm;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.Store;
import org.radf.apps.customization.facade.CustomizationFacade;
import org.radf.apps.customization.form.CustomizationForm;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 定制机管理
 */
public class CustomizationAction extends ActionLeafSupport {

	/**
	 * 查询跳转
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().removeAttribute("SqlForConfirm");
		req.getSession().removeAttribute("SqlForMake");
		req.getSession().removeAttribute("SqlForInstall");
		req.getSession().removeAttribute("SqlForComplete");
		String menuId = req.getParameter("menuId");
		String forward = menuId;
		CustomizationForm cf = new CustomizationForm();
		cf.setTmksid(null);
		ClassHelper.copyProperties(cf, form);
		return mapping.findForward(forward);
	}

	/**
	 * 定制机删除
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		CustomizationForm cf = (CustomizationForm) form;
		String tmksid = request.getParameter("tmksid");
		Customization cz = new Customization();
		try {
			ClassHelper.copyProperties(cf, cz);
			CustomizationFacade facade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除定制记录成功!");
				FindLog.insertLog(request, tmksid, "删除定制机");
				return go2Page(request, mapping, "customization");
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
	 * 显示定制机详细信息
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();
		try {
			ClassHelper.copyProperties(czForm, cz);
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.printCI(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), czForm);
				return mapping.findForward("view");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 修改定制机信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();
		try {
			ClassHelper.copyProperties(czForm, cz);
			if (cz.getFolsta().equals("finish")
					|| cz.getFolsta().equals("charged")
					|| cz.getFolsta().equals("recback")
					|| cz.getFolsta().equals("recoiled")
					|| cz.getFolsta().equals("recoiledhead")
					|| cz.getFolsta().equals("recpass")) {
				super.saveSuccessfulMsg(request, "发货后维修信息不可修改！");
				return mapping.findForward("backspace");
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.printCI(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), czForm);
				return mapping.findForward("alterci");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 保存新增后的定制机信息
	 */
	public ActionForward saveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// CustomizationForm cf = new CustomizationForm();
		String company = req.getParameter("company");
		String num = req.getParameter("num");
		String gid = req.getParameter("gid");
		String cid = req.getParameter("cid");
		String cnm = req.getParameter("cnm");
		String type = req.getParameter("type");
		List<Customization> czList = new Vector<Customization>();
		if (num.equals("1")) {
			String plr = req.getParameter("plr");
			if (plr.equals("l")) {
				String lpid = req.getParameter("pid");
				Customization cz = new Customization();
				cz.setTmkcltid(cid);
				cz.setTmkcpy(company);
				cz.setTmkplr("0");
				cz.setTmkpid(lpid);
				czList.add(cz);
			} else if (plr.equals("r")) {
				String rpid = req.getParameter("pid");
				Customization cz = new Customization();
				cz.setTmkcltid(cid);
				cz.setTmkcpy(company);
				cz.setTmkplr("1");
				cz.setTmkpid(rpid);
				czList.add(cz);
			}
		} else if (num.equals("2")) {
			String lpid = req.getParameter("lpid");
			String rpid = req.getParameter("rpid");
			Customization cz0 = new Customization();
			cz0.setTmkcltid(cid);
			cz0.setTmkcpy(company);
			cz0.setTmkplr("0");
			cz0.setTmkpid(lpid);
			czList.add(cz0);
			Customization cz1 = new Customization();
			cz1.setTmkcltid(cid);
			cz1.setTmkcpy(company);
			cz1.setTmkplr("1");
			cz1.setTmkpid(rpid);
			czList.add(cz1);
		}

		try {
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			// 获取服务接口
			CustomizationFacade facade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			mapRequest.put("gid", gid);
			mapRequest.put("cid", cid);
			mapRequest.put("cnm", cnm);
			mapRequest.put("company", company);
			mapRequest.put("opr", dto1.getBsc011());
			mapRequest.put("tye", type);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmkfno1 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno1");
				String tmkfno2 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno2");
				if ("j".equals(type)) {
					if (tmkfno2 == null || "".equals(tmkfno2))
						super.saveSuccessfulMsg(req, "杰闻定制录单成功！订单号：" + tmkfno1);
					else
						super.saveSuccessfulMsg(req, "杰闻定制录单成功！订单号：" + tmkfno1
								+ "," + tmkfno2);
				} else {
					if (tmkfno2 == null || "".equals(tmkfno2))
						super.saveSuccessfulMsg(req, "惠耳定制录单成功！订单号：" + tmkfno1);
					else
						super.saveSuccessfulMsg(req, "惠耳定制录单成功！订单号：" + tmkfno1
								+ "," + tmkfno2);
				}

				CustomizationForm czForm = (CustomizationForm) form;
				czForm.setTmkfno(tmkfno1);
				return mapping.findForward("modify");
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
	 * 
	 * 保存修改后的定制机信息
	 */
	public ActionForward saveModifiedCI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();
		try {
			// 设定经办信息
			ClassHelper.copyProperties(czForm, cz);
			LoginDTO login = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			cz.setStooprcd(login.getBsc010());
			// 获取服务接口
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.modifyCI(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "修改定制信息成功!");
				return go2Page(request, mapping, "customization");
				// return queryCI(mapping, form, request, response);
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
	 * 根据不同的需求查询定制机简略信息(支持模糊查询)
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		String forward = null;
		String fileKey = null;
		ActionForward af = null;
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();
		if ("arrange".equals(order)) {
			forward = "/customization/queryArrange.jsp";
			fileKey = "cus01_001";
		} else if ("confirm".equals(order)) {
			forward = "/customization/queryConfirm.jsp";
			fileKey = "cus01_003";
		} else if ("make".equals(order)) {
			forward = "/customization/queryMake.jsp";
			fileKey = "cus01_004";
		} else if ("install".equals(order)) {
			forward = "/customization/queryInstall.jsp";
			fileKey = "cus01_005";
		} else if ("test".equals(order)) {
			forward = "/customization/select4.jsp";
			fileKey = "cus01_010";
		} else if ("complete".equals(order)) {
			forward = "/customization/queryComplete.jsp";
			fileKey = "cus01_010";
		} else if ("modify".equals(order)) {
			forward = "/customization/modifyCI.jsp";
			fileKey = "cus01_000";
		} else if ("erbeiji".equals(order)) {
			forward = "/customization/erbeijiquery.jsp";
			fileKey = "cus01_016";
		} else {
			forward = "/customization/query.jsp";
			fileKey = "cus01_000";
		}
		try {
			ClassHelper.copyProperties(czForm, cz);
			cz.setFileKey(fileKey);
			String hql = null;
			if ("confirm".equals(order) || "make".equals(order)
					|| "install".equals(order) || "complete".equals(order)) {
				if ("confirm".equals(order))
					hql = (String) req.getSession().getAttribute(
							"SqlForConfirm");
				else if ("make".equals(order))
					hql = (String) req.getSession().getAttribute("SqlForMake");
				else if ("install".equals(order))
					hql = (String) req.getSession().getAttribute(
							"SqlForInstall");
				else if ("complete".equals(order))
					hql = (String) req.getSession().getAttribute(
							"SqlForComplete");
				if (cz.getTmksid() == null || "".equals(cz.getTmksid())) { // 输入框无内容
					req.getSession().removeAttribute("SqlForConfirm");
					req.getSession().removeAttribute("SqlForMake");
					req.getSession().removeAttribute("SqlForInstall");
					req.getSession().removeAttribute("SqlForComplete");
					hql = queryEnterprise(cz);
				} else {
					if (hql == null || "".equals(hql)) { // hql为空
						hql = queryEnterprise(cz);
						hql += " and (tmksid='" + cz.getTmksid() + "')";
						if ("confirm".equals(order))
							req.getSession().setAttribute("SqlForConfirm", hql);
						else if ("make".equals(order))
							req.getSession().setAttribute("SqlForMake", hql);
						else if ("install".equals(order))
							req.getSession().setAttribute("SqlForInstall", hql);
						else if ("complete".equals(order))
							req.getSession()
									.setAttribute("SqlForComplete", hql);
					} else if (hql.indexOf(cz.getTmksid()) == -1) { // 不重复
						if (hql.indexOf("and") == -1) {
							hql += " and (tmksid='" + cz.getTmksid() + "')";
						} else {
							hql = hql.substring(0, hql.length() - 1);
							hql += " or tmksid='" + cz.getTmksid() + "')";
						}
						if ("confirm".equals(order))
							req.getSession().setAttribute("SqlForConfirm", hql);
						else if ("make".equals(order))
							req.getSession().setAttribute("SqlForMake", hql);
						else if ("install".equals(order))
							req.getSession().setAttribute("SqlForInstall", hql);
						else if ("complete".equals(order))
							req.getSession()
									.setAttribute("SqlForComplete", hql);
					}
				}
			} else {
				hql = queryEnterprise(cz);
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
	 * 查询维修历史
	 */
	public ActionForward queryHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		CustomizationForm czf = (CustomizationForm) form;
		// RepairForm rf = (RepairForm)form;
		ActionForward af = new ActionForward();
		String forward = "/repair/queryHistory.jsp";
		String filekey = "rep01_005";
		try {
			rep.setRepcltid(czf.getTmkcltid());
			if (null != czf.getTmksid() && !"".equals(czf.getTmksid())) {
				rep.setRepid(czf.getTmksid());
			}
			/*
			 * else if(null != rf.getRepid() && !"".equals(rf.getRepid())){
			 * rep.setRepid(rf.getRepid()); }
			 */
			rep.setFileKey(filekey);
			String hql = queryEnterprise(rep);
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

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pdtID = request.getParameter("pdtid");
		String cltID = (String) request.getSession().getAttribute("cltID");
		CustomizationForm czForm = (CustomizationForm) form;
		System.out.println(pdtID + " " + cltID);
		czForm.setTmkpid(pdtID);
		czForm.setTmkcltid(cltID);
		return mapping.findForward("register");
	}

	/**
	 * 进入配料界面
	 */
	public ActionForward arrange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("arrange");
	}

	/**
	 * 进入质检界面
	 */
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("test");
	}

	public ActionForward make(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Customization cz = new Customization();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			cz.setTmkshmk(dto1.getBsc010());
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward("arrange");
	}

	/**
	 * 配料－更新定制机信息
	 */
	public ActionForward updateArrangement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();

		try {
			// 设定经办信息
			ClassHelper.copyProperties(czForm, cz);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			cz.setTmkmixer(dto1.getBsc011());
			cz.setTmkmixdt(DateUtil.getDate());

			cz.setStooprcd(dto1.getBsc010());
			// cz.setStoproid(czForm.getTmkpnl());

			// 获取服务接口
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.updateArrangement(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String gctnm = (String) ((HashMap) resEnv.getBody())
						.get("gctnm");
				String expire = (String) ((HashMap) resEnv.getBody()).get(
						"expire").toString();
				String foldt = (String) ((HashMap) resEnv.getBody()).get(
						"foldt").toString();
				String foldt1 = foldt.substring(0, 6);
				String isDelay = (String) ((HashMap) resEnv.getBody()).get(
						"isDelay").toString();
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				// .getRealPath("/data");

				System.out.println("data.txt所在路径：" + path);
				// String dateTime = "2012-01";
				// String fileName = "data-" + "" + ".txt";
				// FileWriter fw = new FileWriter(path + "data"+foldt1+".txt",
				// true);
				FileWriter fw = new FileWriter(path + "data.txt", true);
				// PrintWriter pw = new PrintWriter(fw);
				fw.write(cz.getTmkcltnm());
				fw.write(",");
				fw.write(cz.getTmksid());
				fw.write(",");
				fw.write(gctnm);
				fw.write(",");
				fw.write(cz.getTmkpnl());
				fw.write(",");
				fw.write(cz.getTmkpnm());
				fw.write(",");
				fw.write(expire);
				fw.write(",");
				fw.write(isDelay + "\r\n");
				fw.flush();
				fw.close();
				super.saveSuccessfulMsg(request, "定制机配料成功!");
				return go2Page(request, mapping, "customization");
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
	 * 更改定制机状态
	 */
	public ActionForward changeStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String require = request.getParameter("require");
		String tmksid = request.getParameter("tmksid");
		Customization cz = new Customization();
		CustomizationForm cf = (CustomizationForm) form;
		if (null == tmksid || "".equalsIgnoreCase(tmksid)) {
			saveSuccessfulMsg(request, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			ClassHelper.copyProperties(cf, cz);
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			if ("startMake".equals(require)) {
				cz.setTmkshmk(dto.getBsc011());
				cz.setTmkpst("4");
			} else if ("startInstall".equals(require)) {
				cz.setTmkshinst(dto.getBsc011());
				cz.setTmkshct(dto.getBsc011());
				cz.setTmkpst("5");
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.changeStatus(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if ("startMake".equals(require)) {
					super.saveSuccessfulMsg(request, "开始制作外壳成功！");
				} else if ("startInstall".equals(require)) {
					super.saveSuccessfulMsg(request, "开始安装外壳成功！");
				}
				return go2Page(request, mapping, "customization");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 批量更改定制机状态
	 */
	public ActionForward batchChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("SqlForConfirm");
		request.getSession().removeAttribute("SqlForMake");
		request.getSession().removeAttribute("SqlForInstall");
		request.getSession().removeAttribute("SqlForComplete");
		String require = request.getParameter("require");
		SubmitDataMap data = new SubmitDataMap(request);
		String[] codeList = data.getParameterValues("tmksid"); // 定制机条形码
		String[] fnoList = data.getParameterValues("tmkfno"); // 订单号
		String[] pnlList = data.getParameterValues("tmkpnl");// 面板编号
		try {
			int size = codeList.length;
			List<Customization> czList = new Vector<Customization>();
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			for (int i = 0; i < size; i++) {
				Customization cz = new Customization();
				cz.setTmksid(codeList[i]);
				cz.setTmkfno(fnoList[i]);
				cz.setTmkpnl(pnlList[i]);// 面板编号
				if ("startMake".equals(require)) {
					cz.setTmkshmk(dto.getBsc011());
					cz.setTmkpst("4");
					cz.setTmkshmkdt(DateUtil.getDate()); // 外壳制作日期(2012/1/30)
				} else if ("startInstall".equals(require)) {
					cz.setTmkshinst(dto.getBsc011());
					cz.setTmkshct(dto.getBsc011());
					cz.setTmkpst("5");
					cz.setTmkshinstdt(DateUtil.getDate()); // 外壳安装日期(2012/1/30)
				}
				System.out.println("test:" + cz.getTmkshmkdt());
				czList.add(cz);
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			mapRequest.put("require", require);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.batchChange(requestEnvelop);
			// 处理返回结果
			String workString = (String) ((HashMap) resEnv.getBody())
					.get("workString");
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, workString);
				return go2Page(request, mapping, "customization");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 批量生产确认(同时生成质检记录)
	 */
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("SqlForConfirm");
		request.getSession().removeAttribute("SqlForMake");
		request.getSession().removeAttribute("SqlForInstall");
		request.getSession().removeAttribute("SqlForComplete");
		SubmitDataMap data = new SubmitDataMap(request);
		String[] codeList = data.getParameterValues("tmksid"); // 定制机条形码
		String[] fnoList = data.getParameterValues("tmkfno"); // 订单号
		String[] pidList = data.getParameterValues("tmkpid"); // 产品代码
		String[] cltList = data.getParameterValues("tmkcltnm"); // 病人
		String[] pnlList = data.getParameterValues("tmkpnl"); // 病人
		// Integer flag=0;
		try {
			int size = codeList.length;
			List<Customization> czList = new Vector<Customization>();
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			for (int i = 0; i < size; i++) {
				Customization cz = new Customization();
				cz.setTmksid(codeList[i]);
				cz.setTmkfno(fnoList[i]);
				cz.setTmkpid(pidList[i]);
				cz.setTmkcltnm(cltList[i]);
				cz.setTmkpnl(pnlList[i]);
				// if(pnlList[i].equals("000000"))
				// {
				// flag=1;
				// }
				cz.setTmkpst("6");
				cz.setTmkfmdt(DateUtil.getDate());// 完工日期
				czList.add(cz);
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.complete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// if(flag==0)
				// {
				super.saveSuccessfulMsg(request, "批量生产完成确认成功！");
				return go2Page(request, mapping, "customization");
				// }
				// else
				// {
				// super.saveSuccessfulMsg(request,
				// "批量生产完成确认成功！其中面板编码为000000的无法通过生产完成确认！");
				// return go2Page(request, mapping, "customization");
				// }

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 打印质检报告
	 */
	public ActionForward printTestReport(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\report6.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("folno", cf.getTmkfno());

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			// System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
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
	 * 定制机制作外壳工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward worknum1(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tmkshmk = null;
			tmkshmk = cf.getTmkshmk();
			File reportFile = null;
			// 有jrxml文件
			File jrxmlFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\work_custom.jrxml"));
			if ("".equals(tmkshmk) || tmkshmk == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_2.jasper"));
				parameters.put("pid", cf.getTmkshmk());// 制作者编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", cf.getTmkfmdt());// 到日期

			parameters.put("sdata", cf.getTmkpdt());// 从日期

			System.out.println("cf.getTmkpdt():" + cf.getTmkpdt());

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println("在控制台显示一下报表文件的物理路径:" + reportFile.getPath());

			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report";

			// JasperPrint rptpnt =
			// JasperManager.fillReport(reportFile.getPath(),parameters, conn);
			// 2012-2-3

			System.out.println("jrxmlFile.getPath()):" + jrxmlFile.getPath());
			// JasperCompileManager.compileReportToFile(jrxmlFile.getPath());

			JasperReport JasperReports = (JasperReport) JRLoader
					.loadObject(reportFile.getPath());
			JasperPrint rptpnt = JasperFillManager.fillReport(JasperReports,
					parameters, conn);
			System.out.println("reportFile.getPath():" + reportFile.getPath());
			// JasperPrint rptpnt =
			// JasperFillManager.fillReport(reportFile.getPath(),
			// parameters,conn);

			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			// 删除记录最下面的空行
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			// 删除多余的ColumnHeader
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			// 忽略边框
			exporter.setParameter(
					JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,
					"GB2312");
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);

			/*
			 * res.setContentType("application/pdf;charset=gb2312");
			 * res.setHeader("content-disposition", "attachment;filename=" +
			 * reportclass + ".pdf"); JasperPrint rptpnt =
			 * JasperManager.fillReport(reportFile.getPath(),parameters, conn);
			 * JRPdfExporter exporter = new JRPdfExporter();
			 * exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			 * exporter
			 * .setParameter(JRExporterParameter.OUTPUT_STREAM,ouputStream);
			 * exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,
			 * Boolean.TRUE);
			 * exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,
			 * "GB2312");
			 */

			exporter.exportReport();
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			// res.setContentType("text/html;charset=GB2312");
			// PrintWriter out = res.getWriter();
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/* 耳膜质检打印 */
	@SuppressWarnings("deprecation")
	public ActionForward ermozjprint(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tmkshmk = null;
			tmkshmk = cf.getTmkshmk();// 制作者编号
            
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
            Date start=cf.getStart();
            Date end=cf.getEnd();
            String strzjstart=cf.getZjstart();
            String strzjend=cf.getZjend();
            String strzjstartsec=cf.getZjstartsec();
            String strzjendsec=cf.getZjendsec();
            String strstart=sdf.format(start);  
            String strend=sdf.format(end);
            String zjstart1=strstart+" "+strzjstart+":"+strzjstartsec+":00";//日期，小时，分钟，秒
            String zjend1=strend+" "+strzjend+":"+strzjendsec+":00";//日期，小时，分钟，秒
			/*Date zjstart = DateUtil.converToDate(cf.getZjstart());
			Date zjend = DateUtil.converToDate(cf.getZjend());*/
			/*Date zjstart=sdf.parse(cf.getZjstart());
			Date zjend=sdf.parse(cf.getZjend());*/
			File reportFile = null;
			
			
			reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\ermozjprint.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			Timestamp zjstart=new Timestamp(System.currentTimeMillis());  
			Timestamp zjend=new Timestamp(System.currentTimeMillis());  
			zjstart = Timestamp.valueOf(zjstart1);  
			zjend = Timestamp.valueOf(zjend1);  
		
			parameters.put("pid", tmkshmk);
			parameters.put("zjstart", zjstart);// 从日期
			parameters.put("zjend", zjend);// 到日期
                          
		

			// 连接到数据库
			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");

			// 2012/2/5修改
			// res.setHeader("content-disposition",
			// "attachment;filename=yuebaobiao.pdf"); //PDF的名字不能为中文，否则报错

			res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
	
		} catch (Exception e) {
			// res.setContentType("text/html;charset=GB2312");
			// PrintWriter out = res.getWriter();
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 定制机安装外壳工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward worknum2(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tmkshinst = null;
			tmkshinst = cf.getTmkshinst();
			File reportFile = null;
			if ("".equals(tmkshinst) || tmkshinst == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_4.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_3.jasper"));
				parameters.put("pid", cf.getTmkshinst());// 制作者编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", cf.getTmkfmdt());// 到日期

			parameters.put("sdata", cf.getTmkpdt());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(
					reportFile.getPath(), parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);
			exporter.exportReport();

			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			// res.setContentType("text/html;charset=GB2312");
			// PrintWriter out = res.getWriter();
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 定制机每日工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward schedule(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\custom_Schedule.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("mdata", cf.getTmkpdt());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "schedule_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(
					reportFile.getPath(), parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);
			exporter.exportReport();

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
	 * 定制机工作的统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward workcount(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\work_custom_5.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", cf.getTmkfmdt());// 到日期

			parameters.put("sdata", cf.getTmkpdt());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(
					reportFile.getPath(), parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);
			exporter.exportReport();

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
	 * 生成定制机条形码
	 */
	public ActionForward barcode(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\barcode.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型

			// 连接到数据库
			conn = DBUtil.getConnection();
			System.out.println(cf.getTmkfno());
			parameters.put("tmkfno", cf.getTmkfno());

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			// res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			// ServletOutputStream ouputStream = res.getOutputStream();
			// ouputStream.write(bytes, 0, bytes.length);
			// ouputStream.flush();
			// ouputStream.close();

			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
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
}