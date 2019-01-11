/**
 * Rec08Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cb24;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec08Facade;
import org.radf.apps.recommendation.ownpintorgan.form.Rec08Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * ���ְҵ���ܻ�������
 */
public class Rec08Action extends ActionLeafSupport {

	/**
	 * �������ְҵ���ܻ���ά��ҳ��
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �������ְҵ���ܻ���ά��ҳ��
	 * @throws �������ְҵ���ܻ���ά��ҳ�����
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryOrgan".equalsIgnoreCase(menuId)) {
			forward = "queryOrgan";// ��ѯ������Ϣ
		}
		return mapping.findForward(forward);

	}

	/**
	 * ��ѯ���ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ���ְҵ���ܻ�����Ϣ
	 * @throws ��ѯ���ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward enterquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 cb24 = new Cb24();
		ClassHelper.copyProperties(form, cb24);
		cb24.setFileKey("rec08_001");
		// ��ȡSQL���
		String hql = queryEnterprise(cb24);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/ownpintorgan/enterqueryorgan.jsp", hql, "1");
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼��");
		}
		return af;
	}

	/**
	 * ��ʼ���������ְҵ���ܻ�����Ϣҳ��
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ���������ְҵ���ܻ�����Ϣҳ��
	 * @throws ��ʼ���������ְҵ���ܻ�����Ϣҳ�����
	 */
	public ActionForward initAddorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		String forward;
		form = new Rec08Form();
		ClassHelper.copyProperties(form, actionForm);
		forward = "addorgan";
		return mapping.findForward(forward);
	}

	/**
	 * �������ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �������ְҵ���ܻ�����Ϣ
	 * @throws �������ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward saveorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// ������
			form.setAae017(dto1.getBsc001());// �������
			form.setAae019(dto1.getBsc008());// �������
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// ����ʱ��
			ClassHelper.copyProperties(form, dto);
			Rec08Facade facade = (Rec08Facade) getService("Rec08Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.saveorgan(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "���ӳɹ�");
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
		return go2Page(request, mapping, "recommendation", "1");
	}

	/**
	 * ��ʼ���޸����ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ���޸����ְҵ���ܻ�����Ϣ
	 * @throws ��ʼ���޸����ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward initmodifyorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		dto.setFileKey("cb24_select");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec08Facade facade = (Rec08Facade) getService("Rec08Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.search(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				List ls = (ArrayList) map.get("beo");
				ClassHelper.copyProperties(ls.get(0), form);
				form.setSsjqnm(OptionDictSupport.getCodeName(request, "AAE017",
						form.getCce001()));
				forward = "initmodifyorgan";
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
		return mapping.findForward(forward);
	}

	/**
	 * �޸����ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸����ְҵ���ܻ�����Ϣ
	 * @throws �޸����ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward modifyorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// �޸���Ա
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// �޸�ʱ��
		ClassHelper.copyProperties(form, dto);
		dto.setFileKey("cb24_update");
		Rec08Facade facade = (Rec08Facade) getService("Rec08Facade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", dto);
		requestEnvelop.setBody(mapRequest);
		ResponseEnvelop resEnv = facade.modifyCommon(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "�޸ĳɹ�");
			return go2Page(request, mapping, "recommendation");
		} else {
			super.saveSuccessfulMsg(request, "�޸�ʧ��");
			return mapping.findForward("backspace");
		}
	}

	/**
	 * ɾ�����ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ɾ�����ְҵ���ܻ�����Ϣ
	 * @throws ɾ�����ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward delorgan(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		dto.setFileKey("cb24_delete");
		ClassHelper.copyProperties(form, dto);
		if ("".equals(dto.getAcb240()) || dto.getAcb240() == null) {
			saveSuccessfulMsg(request, "û�в�ѯ������Ϣ��");
			return mapping.findForward("backspace");
		}
		Rec08Facade facade = (Rec08Facade) getService("Rec08Facade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", dto);
		requestEnvelop.setBody(mapRequest);
		ResponseEnvelop resEnv = facade.delete(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "ɾ���ɹ�");

		} else {
			super.saveSuccessfulMsg(request, "ɾ��ʧ��");
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * �鿴���ְҵ���ܻ�����Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴���ְҵ���ܻ�����Ϣ
	 * @throws �鿴���ְҵ���ܻ�����Ϣ����
	 */
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		dto.setFileKey("cb24_select");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec08Facade facade = (Rec08Facade) getService("Rec08Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.search(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				List ls = (ArrayList) map.get("beo");
				ClassHelper.copyProperties(ls.get(0), form);
				form.setSsjqnm(OptionDictSupport.getCodeName(request, "AAE017",
						form.getCce001()));
				forward = "vieworgan";
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
		return mapping.findForward(forward);
	}
}
