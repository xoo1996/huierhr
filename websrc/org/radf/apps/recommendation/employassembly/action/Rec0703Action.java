/**
 * Rec0703Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cb23;
import org.radf.apps.recommendation.employassembly.facade.Rec0703Facade;
import org.radf.apps.recommendation.employassembly.form.Rec0703Form;
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
 * �����Ƹ������Ϣ����
 */
public class Rec0703Action extends ActionLeafSupport {

	/**
	 *��������Ƹ����ά��ҳ�棨���Ӵ����Ƹ����ά���˵���<br>
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
	 * @return �����Ƹ����ҳ��
	 * @throws �����Ƹ��������
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryQs".equalsIgnoreCase(menuId)) {
			forward = "queryQs";// ����
		}
		return mapping.findForward(forward);

	}

	/**
	 * ��ѯ�����Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ѯ��������������</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ�����Ƹ������Ϣҳ��
	 * @throws ��ѯ�����Ƹ������Ϣ����
	 */
	public ActionForward enterquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 cb23 = new Cb23();
		ClassHelper.copyProperties(form, cb23);
		cb23.setFileKey("rec07_010");
		// ��ȡSQL���
		String hql = queryEnterprise(cb23);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/employassembly/enterqueryqs.jsp", hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼��");
		}
		return af;
	}

	/**
	 * ��ʼ���ӻ�������Ƹ������Ϣҳ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ȡ�����������ʼ��ҳ��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ���ӻ�������Ƹ������Ϣҳ��
	 * @throws ��ʼ���ӻ�������Ƹ������Ϣҳ�����
	 */
	public ActionForward initAddqs(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		String forward;
		form = new Rec0703Form();
		ClassHelper.copyProperties(form, actionForm);
		forward = "addqs";
		return mapping.findForward(forward);
	}

	/**
	 * ��������Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��������������Ϣ�����ݿ�</br>
	 * 2��ÿ������ֻ��һ����Ч��������¼</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��������Ƹ������Ϣҳ��
	 * @throws ��������Ƹ������Ϣ����
	 */
	public ActionForward saveqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// ������
			form.setAae017(dto1.getBsc001());// �������
			form.setAae019(dto1.getBsc008());// �������
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// ����ʱ��
			ClassHelper.copyProperties(form, dto);
			Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.saveqs(requestEnvelop);
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
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * ��ʼ�޸Ļ������Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ȡ�����������ʼ��ҳ��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ʼ�޸Ļ������Ƹ������Ϣ
	 * @throws ��ʼ�޸Ļ������Ƹ������Ϣ����
	 */
	public ActionForward initmodifyqs(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		dto.setFileKey("cb23_select");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
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
				forward = "initmodifyqs";
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
	 * �޸Ĵ����Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸Ĵ��������Ϣ�����ݿ�</br>
	 * 2��ÿ������ֻ��һ����Ч��������¼</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸Ĵ����Ƹ������Ϣ
	 * @throws �޸Ĵ����Ƹ������Ϣ����
	 */
	public ActionForward modifyqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// �޸���Ա
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// �޸�ʱ��

		try {
			ClassHelper.copyProperties(form, dto);
			dto.setAae100(null);
			dto.setFileKey("cb23_update");
			// ��ȡ�ӿ�
			Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modifyCommon(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�޸ĳɹ�");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				mapping.findForward("backspace");
			}
			return go2Page(request, mapping, "recommendation");

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		// return mapping.findForward("saveemploy");
	}

	/**
	 * ע�������Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��ע�����������Ϣ�����ݿ�</br>
	 * 2��ÿ������ֻ��һ����Ч��������¼</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ע�������Ƹ������Ϣ
	 * @throws ע�������Ƹ������Ϣ����
	 */
	public ActionForward writeoffqs(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// �޸���Ա
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// �޸�ʱ��
		ClassHelper.copyProperties(form, dto);
		if ("1".equals(dto.getAae100())) {
			dto.setAae100("0");
		} else {
			dto.setAae100("1");
		}

		Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", dto);
		mapRequest.put("beo1", "zx");
		requestEnvelop.setBody(mapRequest);
		ResponseEnvelop resEnv = facade.modifyCommon(requestEnvelop);
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "�޸ĳɹ�");

		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * ɾ�������Ƹ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��ɾ�����������Ϣ�����ݿ�</br>
	 * 2������������Ѿ���ʹ�þͲ���ɾ��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ɾ�������Ƹ������Ϣ
	 * @throws ɾ�������Ƹ������Ϣ����
	 */
	public ActionForward delqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		dto.setFileKey("cb23_delete");
		ClassHelper.copyProperties(form, dto);
		if ("".equals(dto.getAcb230()) || dto.getAcb230() == null) {
			saveSuccessfulMsg(request, "û����Ϣ,����ɾ����");
			return mapping.findForward("backspace");
		}
		Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
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
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * �鿴�����Ƹ������ϸ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���鿴���������Ϣ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴�����Ƹ������ϸ��Ϣ
	 * @throws �鿴�����Ƹ������ϸ��Ϣ����
	 */
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		dto.setFileKey("cb23_select");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
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
				forward = "viewqs";
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
