/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
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
 * ���ƻ�����
 */
public class CustomizationAction extends ActionLeafSupport {

	/**
	 * ��ѯ��ת
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
	 * ���ƻ�ɾ��
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
			// ��Application�������HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "ɾ�����Ƽ�¼�ɹ�!");
				FindLog.insertLog(request, tmksid, "ɾ�����ƻ�");
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
	 * ��ʾ���ƻ���ϸ��Ϣ
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
			// ��Application�������HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.printCI(requestEnvelop);
			// �����ؽ��
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
	 * �޸Ķ��ƻ���Ϣ
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
				super.saveSuccessfulMsg(request, "������ά����Ϣ�����޸ģ�");
				return mapping.findForward("backspace");
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Customization> mapRequest = new HashMap<String, Customization>();
			mapRequest.put("beo", cz);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.printCI(requestEnvelop);
			// �����ؽ��
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
	 * ����������Ķ��ƻ���Ϣ
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
			// ��ȡ����ӿ�
			CustomizationFacade facade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			mapRequest.put("gid", gid);
			mapRequest.put("cid", cid);
			mapRequest.put("cnm", cnm);
			mapRequest.put("company", company);
			mapRequest.put("opr", dto1.getBsc011());
			mapRequest.put("tye", type);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmkfno1 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno1");
				String tmkfno2 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno2");
				if ("j".equals(type)) {
					if (tmkfno2 == null || "".equals(tmkfno2))
						super.saveSuccessfulMsg(req, "���Ŷ���¼���ɹ��������ţ�" + tmkfno1);
					else
						super.saveSuccessfulMsg(req, "���Ŷ���¼���ɹ��������ţ�" + tmkfno1
								+ "," + tmkfno2);
				} else {
					if (tmkfno2 == null || "".equals(tmkfno2))
						super.saveSuccessfulMsg(req, "�ݶ�����¼���ɹ��������ţ�" + tmkfno1);
					else
						super.saveSuccessfulMsg(req, "�ݶ�����¼���ɹ��������ţ�" + tmkfno1
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
	 * �����޸ĺ�Ķ��ƻ���Ϣ
	 */
	public ActionForward saveModifiedCI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();
		try {
			// �趨������Ϣ
			ClassHelper.copyProperties(czForm, cz);
			LoginDTO login = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			cz.setStooprcd(login.getBsc010());
			// ��ȡ����ӿ�
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.modifyCI(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�޸Ķ�����Ϣ�ɹ�!");
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
	 * ���ݲ�ͬ�������ѯ���ƻ�������Ϣ(֧��ģ����ѯ)
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
				if (cz.getTmksid() == null || "".equals(cz.getTmksid())) { // �����������
					req.getSession().removeAttribute("SqlForConfirm");
					req.getSession().removeAttribute("SqlForMake");
					req.getSession().removeAttribute("SqlForInstall");
					req.getSession().removeAttribute("SqlForComplete");
					hql = queryEnterprise(cz);
				} else {
					if (hql == null || "".equals(hql)) { // hqlΪ��
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
					} else if (hql.indexOf(cz.getTmksid()) == -1) { // ���ظ�
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ��ѯά����ʷ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * �������Ͻ���
	 */
	public ActionForward arrange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("arrange");
	}

	/**
	 * �����ʼ����
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
	 * ���ϣ����¶��ƻ���Ϣ
	 */
	public ActionForward updateArrangement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CustomizationForm czForm = (CustomizationForm) form;
		Customization cz = new Customization();

		try {
			// �趨������Ϣ
			ClassHelper.copyProperties(czForm, cz);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			cz.setTmkmixer(dto1.getBsc011());
			cz.setTmkmixdt(DateUtil.getDate());

			cz.setStooprcd(dto1.getBsc010());
			// cz.setStoproid(czForm.getTmkpnl());

			// ��ȡ����ӿ�
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.updateArrangement(requestEnvelop);
			// �����ؽ��
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

				System.out.println("data.txt����·����" + path);
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
				super.saveSuccessfulMsg(request, "���ƻ����ϳɹ�!");
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
	 * ���Ķ��ƻ�״̬
	 */
	public ActionForward changeStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String require = request.getParameter("require");
		String tmksid = request.getParameter("tmksid");
		Customization cz = new Customization();
		CustomizationForm cf = (CustomizationForm) form;
		if (null == tmksid || "".equalsIgnoreCase(tmksid)) {
			saveSuccessfulMsg(request, "����Ϊ�գ������²�ѯ");
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", cz);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.changeStatus(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if ("startMake".equals(require)) {
					super.saveSuccessfulMsg(request, "��ʼ������ǳɹ���");
				} else if ("startInstall".equals(require)) {
					super.saveSuccessfulMsg(request, "��ʼ��װ��ǳɹ���");
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
	 * �������Ķ��ƻ�״̬
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
		String[] codeList = data.getParameterValues("tmksid"); // ���ƻ�������
		String[] fnoList = data.getParameterValues("tmkfno"); // ������
		String[] pnlList = data.getParameterValues("tmkpnl");// �����
		try {
			int size = codeList.length;
			List<Customization> czList = new Vector<Customization>();
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			for (int i = 0; i < size; i++) {
				Customization cz = new Customization();
				cz.setTmksid(codeList[i]);
				cz.setTmkfno(fnoList[i]);
				cz.setTmkpnl(pnlList[i]);// �����
				if ("startMake".equals(require)) {
					cz.setTmkshmk(dto.getBsc011());
					cz.setTmkpst("4");
					cz.setTmkshmkdt(DateUtil.getDate()); // �����������(2012/1/30)
				} else if ("startInstall".equals(require)) {
					cz.setTmkshinst(dto.getBsc011());
					cz.setTmkshct(dto.getBsc011());
					cz.setTmkpst("5");
					cz.setTmkshinstdt(DateUtil.getDate()); // ��ǰ�װ����(2012/1/30)
				}
				System.out.println("test:" + cz.getTmkshmkdt());
				czList.add(cz);
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			mapRequest.put("require", require);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.batchChange(requestEnvelop);
			// �����ؽ��
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
	 * ��������ȷ��(ͬʱ�����ʼ��¼)
	 */
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("SqlForConfirm");
		request.getSession().removeAttribute("SqlForMake");
		request.getSession().removeAttribute("SqlForInstall");
		request.getSession().removeAttribute("SqlForComplete");
		SubmitDataMap data = new SubmitDataMap(request);
		String[] codeList = data.getParameterValues("tmksid"); // ���ƻ�������
		String[] fnoList = data.getParameterValues("tmkfno"); // ������
		String[] pidList = data.getParameterValues("tmkpid"); // ��Ʒ����
		String[] cltList = data.getParameterValues("tmkcltnm"); // ����
		String[] pnlList = data.getParameterValues("tmkpnl"); // ����
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
				cz.setTmkfmdt(DateUtil.getDate());// �깤����
				czList.add(cz);
			}
			CustomizationFacade czFacade = (CustomizationFacade) getService("CustomizationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", czList);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.complete(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// if(flag==0)
				// {
				super.saveSuccessfulMsg(request, "�����������ȷ�ϳɹ���");
				return go2Page(request, mapping, "customization");
				// }
				// else
				// {
				// super.saveSuccessfulMsg(request,
				// "�����������ȷ�ϳɹ�������������Ϊ000000���޷�ͨ���������ȷ�ϣ�");
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
	 * ��ӡ�ʼ챨��
	 */
	public ActionForward printTestReport(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\report6.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("folno", cf.getTmkfno());

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
	 * ���ƻ�������ǹ�����ͳ��
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
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			String tmkshmk = null;
			tmkshmk = cf.getTmkshmk();
			File reportFile = null;
			// ��jrxml�ļ�
			File jrxmlFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\work_custom.jrxml"));
			if ("".equals(tmkshmk) || tmkshmk == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_2.jasper"));
				parameters.put("pid", cf.getTmkshmk());// �����߱��
			}

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("edata", cf.getTmkfmdt());// ������

			parameters.put("sdata", cf.getTmkpdt());// ������

			System.out.println("cf.getTmkpdt():" + cf.getTmkpdt());

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
			System.out.println("�ڿ���̨��ʾһ�±����ļ�������·��:" + reportFile.getPath());

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
			// ɾ����¼������Ŀ���
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			// ɾ�������ColumnHeader
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			// ���Ա߿�
			exporter.setParameter(
					JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,
					"GB2312");
			// ����GridLine
			// ��С������䵥Ԫ��
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

	/* ��Ĥ�ʼ��ӡ */
	@SuppressWarnings("deprecation")
	public ActionForward ermozjprint(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			String tmkshmk = null;
			tmkshmk = cf.getTmkshmk();// �����߱��
            
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
            Date start=cf.getStart();
            Date end=cf.getEnd();
            String strzjstart=cf.getZjstart();
            String strzjend=cf.getZjend();
            String strzjstartsec=cf.getZjstartsec();
            String strzjendsec=cf.getZjendsec();
            String strstart=sdf.format(start);  
            String strend=sdf.format(end);
            String zjstart1=strstart+" "+strzjstart+":"+strzjstartsec+":00";//���ڣ�Сʱ�����ӣ���
            String zjend1=strend+" "+strzjend+":"+strzjendsec+":00";//���ڣ�Сʱ�����ӣ���
			/*Date zjstart = DateUtil.converToDate(cf.getZjstart());
			Date zjend = DateUtil.converToDate(cf.getZjend());*/
			/*Date zjstart=sdf.parse(cf.getZjstart());
			Date zjend=sdf.parse(cf.getZjend());*/
			File reportFile = null;
			
			
			reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\ermozjprint.jasper"));

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			Timestamp zjstart=new Timestamp(System.currentTimeMillis());  
			Timestamp zjend=new Timestamp(System.currentTimeMillis());  
			zjstart = Timestamp.valueOf(zjstart1);  
			zjend = Timestamp.valueOf(zjend1);  
		
			parameters.put("pid", tmkshmk);
			parameters.put("zjstart", zjstart);// ������
			parameters.put("zjend", zjend);// ������
                          
		

			// ���ӵ����ݿ�
			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");

			// 2012/2/5�޸�
			// res.setHeader("content-disposition",
			// "attachment;filename=yuebaobiao.pdf"); //PDF�����ֲ���Ϊ���ģ����򱨴�

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
	 * ���ƻ���װ��ǹ�����ͳ��
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

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			String tmkshinst = null;
			tmkshinst = cf.getTmkshinst();
			File reportFile = null;
			if ("".equals(tmkshinst) || tmkshinst == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_4.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\work_custom_3.jasper"));
				parameters.put("pid", cf.getTmkshinst());// �����߱��
			}

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("edata", cf.getTmkfmdt());// ������

			parameters.put("sdata", cf.getTmkpdt());// ������

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 * ���ƻ�ÿ�չ�����ͳ��
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

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\custom_Schedule.jasper"));

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("mdata", cf.getTmkpdt());// ������

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 * ���ƻ�������ͳ��
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

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\work_custom_5.jasper"));

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("edata", cf.getTmkfmdt());// ������

			parameters.put("sdata", cf.getTmkpdt());// ������

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 * ���ɶ��ƻ�������
	 */
	public ActionForward barcode(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\barcode.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();
			System.out.println(cf.getTmkfno());
			parameters.put("tmkfno", cf.getTmkfno());

			// �ڿ���̨��ʾһ�±����ļ�������·��
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