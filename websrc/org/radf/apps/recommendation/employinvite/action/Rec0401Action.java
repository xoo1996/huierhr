/**
 * Rec0401Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employinvite.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.recommendation.employinvite.facade.Rec0401Facade;
import org.radf.apps.recommendation.employinvite.form.Rec0401Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
/**
 * ί����Ƹ�Ǽ�
 */
public class Rec0401Action extends ActionLeafSupport {
	/**
	 * Ϊ��ʼ��ί����Ƹ��Ϣ�޸Ľ����ṩ����<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ȡab01��λ������Ϣ����ʼ��ҳ��<br>
     * 2����Ƹ���Ϊί����Ƹ<br>
     * 3��ҳ�������������cb20��������<br></tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ί����Ƹ��Ϣ
	 * @throws ί����Ƹ��Ϣ����
	 */
	public ActionForward initaddEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		Rec0401Form form = (Rec0401Form) actionForm;
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");

		String url1 = "";
		url1 = "addEmployInvite";
		String url = "";
		Ab01 ab01 = new Ab01();
		super.resetToken(request);
		try {
			url = (String) request.getParameter("addE");
			if (url == null || "".equals(url)) {
				url = (String) request.getSession().getAttribute("addE");
			}
			request.getSession().setAttribute("addE", url);
			ClassHelper.copyProperties(form, ab01);
			ab01.setFileKey("ab01_select");
			Rec0401Facade facade = (Rec0401Facade) getService("Rec0401Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", ab01);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.initaddEmployInvite(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(list.get(0), form);
				ClassHelper.copyProperties(form, actionForm);
				form.setAae043(DateUtil.getSystemCurrentTime().toString());
				form.setAae030(DateUtil.getSystemCurrentTime().toString());
				form.setAcb206(form.getAab004());
				form.setAae004(form.getAae004());
				form.setAae005(form.getAae005());
				request.getSession().setAttribute("form", form);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(url1);
	}
	/**
	 * ����ί����Ƹ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1�����ݱ�����ܽ�����һ���Ǽ���Ƹ��λ��Ϣ<br>
     * 2���������ݵ�cb20��λ��Ƹ��Ϣ��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ����ί����Ƹ��Ϣ
	 * @throws ����ί����Ƹ��Ϣ����
	 */
	public ActionForward addEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		Rec0401Form form = (Rec0401Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		Cb20 cb20 = new Cb20();
		Rec0401Form output = null;
		Rec0401Form form1 = new Rec0401Form();

		if (isTokenValid(request)) {
			saveErrors(request, new Exception("�����Ѿ������˱��棬��ȷ��û���ظ����棡��"));
		} else {
			try {
				ClassHelper.copyProperties(form, cb20);
				// ��ȡ��������Ϣ
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				cb20.setAae011(dto1.getBsc010());
				cb20.setAae017(dto1.getBsc001());
				cb20.setAae036(DateUtil.getSystemCurrentTime());
				// ���ýӿ�
				Rec0401Facade facade = (Rec0401Facade) getService("Rec0401Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// ��Application�������HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", cb20);
				// ��HashMap�������requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// ���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.addEmployInvite(requestEnvelop);
				// �����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					saveSuccessfulMsg(request, "������λ��Ƹ��Ϣ�ɹ���");
					cb20 = (Cb20) ((HashMap) resEnv.getBody()).get("beo");
					form.setAcb200(cb20.getAcb200());
					ClassHelper.copyProperties(cb20, actionForm);
					form1 = (Rec0401Form) request.getSession().getAttribute(
							"form");
					ClassHelper.copyProperties(cb20, form1);
					request.getSession().setAttribute("form", form1);
					saveToken(request);
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					saveSuccessfulMsg(request, aa[3]);
				}

			} catch (Exception re) {
				saveErrors(request, re);
			}
		}

		return mapping.findForward("addEmployInvite");
	}

}