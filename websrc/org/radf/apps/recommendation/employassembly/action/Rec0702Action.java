/**
 * Rec0702Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.insuranceagent.companyagency.dto.Ins01DTO;
import org.radf.apps.insuranceagent.companyagency.form.Ins01Form;
import org.radf.apps.recommendation.employassembly.dto.Rec0702DTO;
import org.radf.apps.recommendation.employassembly.facade.Rec0702Facade;
import org.radf.apps.recommendation.employassembly.form.Rec0702Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.Money;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * �����Ƹ��λ��Ϣ����
 */
public class Rec0702Action extends ActionLeafSupport {

	/**
	 * ����ո�λλҳ��<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1���Է�ҳ��ǩ��ʽ��ѯcb21�ж�Ӧ��λ��Ϣ<br>
	 * 2���������� ���޸ġ�ɾ�����鿴�ȹ���</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �ո�λλҳ��
	 * @throws �ո�λλ����
	 */
	public ActionForward toNext(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO dto = new Rec0702DTO();
		ActionForward af = new ActionForward(
				"/recommendation/employassembly//ManageInvitePosition.jsp");
		String url = "";
		String hql = null;
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec07_002");
		try {
			url = (String) request.getParameter("nextP");
			if (url != null && url.length() > 0) {
				request.getSession().setAttribute("nextP", url);
			}
			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.toNext(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(list.get(0), dto);
				request.getSession().setAttribute("Rec0702DTO", dto);
				// ��ѯ��λ��Ϣ
				dto.setFileKey("rec07_003");
				hql = queryEnterprise(dto);
				af = init(
						request,
						"/recommendation/employassembly/ManageInvitePosition.jsp",
						hql, "2");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return actionMapping.findForward("backspace");
			}

		} catch (Exception re) {
			saveErrors(request, re);
			return actionMapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * �鿴��Ƹ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1������ѡ������ݲ�ѯ��cb20�е�����������ʾ����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴��Ƹ��Ϣҳ��
	 * @throws �鿴��Ƹ��Ϣ����
	 */
	public ActionForward viewEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmptyPostAction:���ݿ�λ��Ų���һ����Ƹ��λ����ϸ��Ϣ");
		// 0.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		if (form.getAcb200() == null) {
			String acb200 = "";
			acb200 = (String) request.getSession().getAttribute("acb200");
			form.setAcb200(acb200);
		}
		if (form.getAcb210() == null) {
			String acb210 = "";
			acb210 = (String) request.getSession().getAttribute("acb210");
			form.setAcb210(acb210);
		}
		if (form.getAab001() == null) {
			String aab001 = "";
			aab001 = (String) request.getSession().getAttribute("aab001");
			form.setAab001(aab001);
		}
		String url = "";

		try {
			// 1.ȷ���ַ�����

			url = "viewEmptyPost";
			request.getSession().setAttribute("viewP",
					(String) request.getParameter("viewP"));

			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				form = (Rec0702Form) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(form, actionForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}

	/**
	 * Ϊ��ʼ����Ƹ��λλ��Ϣ���ӽ����ṩ����<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1��������λ����ҳ��<br>
	 * 2���ṩ�û��������ı��湦��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ����Ƹ��λ��Ϣҳ��
	 * @throws ��ʼ����Ƹ��λ��Ϣ����
	 */
	public ActionForward addEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitAddEmptyPostAction:������Ƹ��Ų���һ�������Ƹ����ϸ��Ϣ");
		// 0.�������

		Rec0702Form form = (Rec0702Form) actionForm;
		super.resetToken(request);
		String url = "";
		try {
			// ȷ���ַ�����

			url = "addEmptyPost";

			request.getSession().setAttribute("addP",
					(String) request.getParameter("addP"));
			form = new Rec0702Form();
			ParaUtil pu = new ParaUtil();
			String aaeyxq = (pu.getParaV("zpyxq", "zpyxq", "rec")).toString();
			form.setAaeyxq(aaeyxq);
			String ac21hb = (pu.getParaV("zpzdgz", "zpzdgz", "rec")).toString();
			form.setAc21hb(ac21hb);
			Date aaedate = DateUtil.getDate();
			form.setAae030(aaedate.toString());
			form.setAae031(DateUtil.getStepDay(aaedate,
					TypeCast.stringToInt(aaeyxq, "��Ƹ��Ч��", false)).toString());
			ClassHelper.copyProperties(form, actionForm);

		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}

	/**
	 * ������Ƹ��λ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1����Ӧcb20�����������ݲ���cb21����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ������Ƹ��λ��Ϣҳ��
	 * @throws ������Ƹ��λ����
	 */
	public ActionForward saveEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("AddEmptyPostAction:������Ƹ��λ��Ϣ");
		// 1.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO input = new Rec0702DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		// ActionForward af = new
		// ActionForward("/recommendation/employassembly/ManageInvitePosition.jsp");
		String aab001 = request.getParameter("aab001");
		String acb200 = request.getParameter("acb200");
		input.setAcb200(acb200);
		input.setAab001(aab001);
		String hql = null;

		try {

			ClassHelper.copyProperties(form, input);

			// ��ȡ��������Ϣ
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAae036(DateUtil.getSystemCurrentTime());

			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", input);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.addEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "������Ƹ��λ��Ϣ�ɹ���");
				return go2Page(request, mapping, "recommendation", "2");

				/*
				 * saveToken(request); Rec0702DTO dto = new Rec0702DTO();
				 * dto.setAcb200(acb200); dto.setFileKey("rec07_003"); hql =
				 * queryEnterprise(dto); return
				 * init(request,"/recommendation/employassembly/ManageInvitePosition.jsp",
				 * hql);
				 */} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward("backspace");
	}

	/**
	 * Ϊ��ʼ����Ƹ��λ�޸Ľ����ṩ����<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1����ѯ��cb21���е�ָ�����ݣ�����ʾҳ����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ����Ƹ��λ�޸Ľ���ҳ��
	 * @throws ��ʼ����Ƹ��λ�޸Ľ������
	 */
	public ActionForward modEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmptyPostAction:���ݿ�λ��Ų���һ����Ƹ��λ����ϸ��Ϣ");
		// 0.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702Form output = (Rec0702Form) actionForm;
		String url = "";
		try {
			// 1.ȷ���ַ�����
			url = "modEmptyPost";

			request.getSession().setAttribute("modP",
					(String) request.getParameter("modP"));

			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				output = (Rec0702Form) ((HashMap) resEnv.getBody()).get("beo");
				ParaUtil pu = new ParaUtil();
				String aaeyxq = (pu.getParaV("zpyxq", "zpyxq", "rec"))
						.toString();
				output.setAaeyxq(aaeyxq);
				String ac21hb = (pu.getParaV("zpzdgz", "zpzdgz", "rec"))
						.toString();
				output.setAc21hb(ac21hb);
				ClassHelper.copyProperties(output, actionForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}

	/**
	 * �޸���Ƹ��λ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1�����û��޸ĺ�����ݱ��浽cb21���У�������������ձ�ṹ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸���Ƹ��λ��Ϣҳ��
	 * @throws �޸���Ƹ��λ��Ϣ����
	 */
	public ActionForward modsaveEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO output = new Rec0702DTO();
		String hql = null;
		String menuId = request.getSession().getAttribute("menuId").toString();
		try {
			ClassHelper.copyProperties(form, output);

			// ��ȡ��������Ϣ
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			output.setAae011(dto1.getBsc010());
			output.setAae017(dto1.getBsc001());
			output.setAae036(DateUtil.getSystemCurrentTime());
			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", output);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modsaveEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "�޸���Ƹ��λ��Ϣ�ɹ���");
				return go2Page(request, mapping, "recommendation", "2");

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward("backspace");
	}

	/**
	 * ע��һ����λ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1����ѡ�еĸ�λ��Ϣ��ע���������Ѹ�λ״̬��Ϊע�����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ע��һ����λ��Ϣҳ��
	 * @throws ע��һ����λ��Ϣ����
	 */
	public ActionForward delEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		// log.info("DelEmptyPostAction:������Ƹ��λ��Ϣ");
		// 1.�������
		Rec0702Form input = (Rec0702Form) actionForm;
		Cb21 cb21 = new Cb21();
		cb21.setAcb210(input.getAcb210());
		cb21.setAcb208("1");// ������Ч
		cb21.setFileKey("cb21_update");
		try {
			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb21);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.delEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "�ɹ�ע��һ����λ��Ϣ��");
				/*
				 * Rec0702DTO dto = new Rec0702DTO();
				 * dto.setAcb200(input.getAcb200());
				 * dto.setFileKey("rec07_003"); String hql =
				 * queryEnterprise(dto); return init( request,
				 * "/recommendation/employassembly/ManageInvitePosition.jsp",
				 * hql);
				 */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		// return mapping.findForward("delEmptyPost");
		return go2Page(request, mapping, "recommendation", "2");

	}

	/**
	 * ɾ��һ����λ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1��ɾ����λ��Ϣ</br>
	 * 2��ɾ���ĸ�λ��Ϣ������û���Ƽ����ģ���cc22����û�иø�λ��Ŷ�Ӧ�ļ�¼</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ɾ��һ����λҳ��
	 * @throws ɾ��һ����λ��Ϣ����
	 */
	public ActionForward deleteEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		// log.info("DelEmptyPostAction:������Ƹ��λ��Ϣ");
		// 1.�������
		Rec0702Form input = (Rec0702Form) actionForm;
		Cb21 cb21 = new Cb21();
		cb21.setAcb210(input.getAcb210());
		try {
			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb21);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.deleteEmptyPost(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "�ɹ�ɾ��һ����λ��Ϣ��");
				/*
				 * Rec0702DTO dto = new Rec0702DTO();
				 * dto.setAcb200(input.getAcb200());
				 * dto.setFileKey("rec07_003"); String hql =
				 * queryEnterprise(dto); return init( request,
				 * "/recommendation/employassembly/ManageInvitePosition.jsp",
				 * hql);
				 */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		// return mapping.findForward("delEmptyPost");
		return go2Page(request, mapping, "recommendation", "2");

	}

	/**
	 * ����ָ����menuidҳ��<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1����ת��ָ��ҳ�棬�����Ӧform����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ����������Ϣά��ҳ��
	 * @throws ����������Ϣά��ҳ�����
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		String forward = "";
		if (menuId.equals("queryDhzp")) {
			forward = "queryDhzp";
		} else {
			forward = "queryEmployInvite";
		}
		req.getSession().setAttribute("menuId", menuId);
		Rec0702Form fm = new Rec0702Form();
		ClassHelper.copyProperties(fm, form);
		return mapping.findForward(forward);
	}

	/**
	 * ��ѯ�����Ƹ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1������������ѯcb20��ҵ�����ݣ����б���ʾ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ�����Ƹ��Ϣҳ��
	 * @throws ��ѯ�����Ƹ��Ϣ����
	 */
	public ActionForward queryEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �������
		Rec0702Form form = (Rec0702Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();

		// log.info("QueryEmployInviteAction:��ѯ�����Ƹ��Ϣ");
		ActionForward af = new ActionForward(
				"/recommendation/employassembly/QueryEmployInvite.jsp");
		Rec0702DTO input = new Rec0702DTO();
		String hql = null;

		// ���ýӿڵ�ʵ�ֽ��в�ѯ
		try {
			// Copy form��ʵ��dto
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			input.setFileKey("rec07_004");
			hql = queryEnterprise(input);
			af = init(request,
					"/recommendation/employassembly/QueryEmployInvite.jsp", hql);
			Collection data = (Collection) request
					.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null) {
				saveSuccessfulMsg(request, "û�з�����������Ϣ��");
			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return af;
	}

	/**
	 * �鿴��λ��Ƹ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1������ϸ��Ϣ��������ʾ��λ������Ƹ����Ϣ��������Դ��cb20��ab01</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴��λ��Ƹ��Ϣҳ��
	 * @throws �鿴��λ��Ƹ��Ϣ����
	 */
	public ActionForward viewEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmployInviteAction:������Ƹ��Ų���һ�������Ƹ����ϸ��Ϣ");
		// 0.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO dto = new Rec0702DTO();
		dto.setAab001(form.getAab001());
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec07_005");
		String url = "";
		try {
			// 1.ȷ���ַ�����
			url = "viewEmployInvite";
			request.getSession().setAttribute("viewE",
					(String) request.getParameter("viewE"));

			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.viewEmployInvite(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), form);
				} else {
					saveSuccessfulMsg(request, "û�д��������Ӧ���ܲ鿴��");
					return mapping.findForward("backspace");
				}
				if (form.getAcb209() != null) {
					form.setAcb209(new Money(form.getAcb209()).getAmount()
							.toString());
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(url);
	}
	
	/**
	 * �鿴�����Ƹ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1������ϸ��Ϣ��������ʾ��λ������Ƹ����Ϣ��������Դ��cb20��ab01</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴��λ��Ƹ��Ϣҳ��
	 * @throws �鿴��λ��Ƹ��Ϣ����
	 */
	public ActionForward viewEmployDhzp(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmployInviteAction:������Ƹ��Ų���һ�������Ƹ����ϸ��Ϣ");
		// 0.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO dto = new Rec0702DTO();
		dto.setAab001(form.getAab001());
		dto.setAcb200(form.getAcb200());
		String jsp = "/recommendation/employassembly/enterqueryDhzp.jsp";
		ActionForward af = new ActionForward();
		try {
			ClassHelper.copyProperties(form, dto);
			// ��ȡSQL���
			dto.setFileKey("rec07_007");
			String hql = queryEnterprise(dto);
			af = super.init(request, jsp, hql);
			List data = (List) request.getAttribute("querydata");
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "û�������������ݣ�");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * Ϊ��ʼ�������Ƹ��Ϣ�޸Ľ����ṩ����<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1����ȡcb20���ݣ�����ʼ��ҳ�棬�ṩ���û��޸�</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ�������Ƹ��Ϣ�޸�ҳ��
	 * @throws ��ʼ�������Ƹ��Ϣ�޸Ľ������
	 */
	public ActionForward modEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmployInviteAction:������Ƹ��Ų���һ�������Ƹ����ϸ��Ϣ");
		// 0.�������
		Rec0702Form form = (Rec0702Form) actionForm;
		Rec0702DTO dto = new Rec0702DTO();
		dto.setAab001(form.getAab001());
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec07_005");
		String url = "";
		try {
			// 1.ȷ���ַ�����
			url = "modEmployInvite";
			request.getSession().setAttribute("modE",
					(String) request.getParameter("modE"));

			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.viewEmployInvite(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), form);
				} else {
					saveSuccessfulMsg(request, "û�д��������Ӧ�����޸ģ�");
					return mapping.findForward("backspace");
				}
				if (form.getAcb209() != null) {
					form.setAcb209(new Money(form.getAcb209()).getAmount()
							.toString());
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(url);
	}

	/**
	 * �޸Ĵ����Ƹ��Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 1������¼����Ϣ�޸�cb20����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸Ĵ����Ƹҳ��
	 * @throws �޸Ĵ����Ƹ����
	 */
	public ActionForward modsaveEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		Rec0702Form form = (Rec0702Form) actionForm;
		Cb20 cb20 = new Cb20();
		try {
			ClassHelper.copyProperties(form, cb20);
			cb20.setFileKey("cb20_update");
			Rec0702Facade facade = (Rec0702Facade) getService("Rec0702Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb20);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modEmployInvite(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "�޸���Ƹ��Ϣ�ɹ���");
				return go2Page(request, mapping, "recommendation");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}

		return mapping.findForward("modEmployInvite");
	}

}