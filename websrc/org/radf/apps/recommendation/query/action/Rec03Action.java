/**
 * Rec03Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.query.dto.Rec0301DTO;
import org.radf.apps.recommendation.query.dto.Rec03DTO;
import org.radf.apps.recommendation.query.facade.Rec03Facade;
import org.radf.apps.recommendation.query.form.Rec03Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * ְҵ�ۺ���Ϣ��ѯ
 */
public class Rec03Action extends ActionLeafSupport {

	public Rec03Action() {
		super();

	}

	/**
	 * ��ѯ��ת
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯҳ��
	 * @throws ��ѯ����
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuid = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuid);
		String forward = menuid;
		Rec03Form fm = new Rec03Form();
		ClassHelper.copyProperties(fm, actionForm);
		return mapping.findForward(forward);
	}

	/**
	 * ���ڲ�ѯ��λ�ù���Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ���ڲ�ѯ��λ�ù���Ϣ
	 * @throws ���ڲ�ѯ��λ�ù���Ϣ����
	 */
	public ActionForward queryPrint(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Rec03Form form = (Rec03Form) actionForm;
		request.getSession().setAttribute("Rec03Form", form);
		ActionForward af = new ActionForward();
		String menuId = request.getSession().getAttribute("menuId").toString();
		LogHelper log = new LogHelper(this.getClass());
		Rec03DTO dto = new Rec03DTO();
		log.log("������ӡ");
		try {

			ClassHelper.copyProperties(form, dto);
			if (form.getA043ae() != null && !(form.getA043ae().equals(""))) {
				dto.setA043ae(DateUtil.getStepDay(dto.getA043ae(), 1));
			}
			if (form.getA030ae() != null && !(form.getA030ae().equals(""))) {
				dto.setA030ae(DateUtil.getStepDay(dto.getA030ae(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());
			// ҳ����ת
			String forward = "/recommendation/query/queryPrint.jsp";
			// ��ȡsql���

			dto.setFileKey("rec03_001");

			// ���sql���
			String hql = queryEnterprise(dto);
			// ��ѯ
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
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
	 * �鿴��λ�ù���Ϣ����������ӡ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴��λ�ù���Ϣ����������ӡ
	 * @throws �鿴��λ�ù���Ϣ����������ӡ����
	 */
	public ActionForward viewPrintPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String postid[] = request.getParameterValues("acb210");// Ҫ���ѯָ����¼
		String menuId = request.getSession().getAttribute("menuId").toString();
		String page = request.getParameter("page");
		Rec03Form form = (Rec03Form) actionForm;
		form = (Rec03Form) request.getSession().getAttribute("Rec03Form");
		Rec0301DTO rec0301dto = new Rec0301DTO();
		ActionForward af = new ActionForward();
		Rec03DTO dto = new Rec03DTO();

		try {
			String temp = "";
			if (postid != null) {
				for (int j = 0; j < postid.length; j++) {
					if (j == 0) {
						temp += " and cb21.acb210 in (";
					}

					if (j < postid.length - 1) {

						temp += "'" + postid[j] + "',";
					} else {

						temp += "'" + postid[j] + "') ";
					}
				}

			}
			// --------------��ѯ������ķ������ƣ�������ֹʱ�䣭����������������
			ClassHelper.copyProperties(form, dto);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());

			dto.setFileKey("rec03_002");
			// ��ȡ�ӿ�
			Rec03Facade facade = (Rec03Facade) getService("Rec03Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			mapRequest.put("beo1", temp);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.queryEmployer(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				int i = list.size();

				if (i == 0) {
					super.saveErrors(request, new AppException(
							"û�з�����������Ϣ��������ѡ���ѯ������"));
				}
				if (i == 1) {
					ClassHelper.copyProperties(list.get(0), rec0301dto);
					request.getSession().setAttribute("rec0301dto", rec0301dto);
				} else {
					rec0301dto = new Rec0301DTO();
					request.getSession().setAttribute("rec0301dto", rec0301dto);
				}
			}

			dto.setFileKey("rec03_005");
			// ��ȡ�ӿ�
			facade = (Rec03Facade) getService("Rec03Facade");
			requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			// ��Application�������HashMap
			mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			mapRequest.put("beo1", temp);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			resEnv = facade.queryEmployer(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				int i = list.size();

				if (i == 0) {
					super.saveErrors(request, new AppException(
							"û�з�����������Ϣ��������ѡ���ѯ������"));
					return mapping.findForward("backspace");
				}
				request.getSession().setAttribute("data", list);
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		if ("A4s".equals(page))
			return mapping.findForward("ztprintA4s");
		if ("A4h".equals(page))
			return mapping.findForward("ztprintA4h");
		if ("A3h".equals(page))
			return mapping.findForward("ztprintA3h");
		if ("A3s".equals(page))
			return mapping.findForward("ztprintA3s");
		return mapping.findForward("backspace");
	}

	/**
	 * ��ѯ��ְ��Ϣ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ��ְ��Ϣ
	 * @throws ��ѯ��ְ��Ϣ����
	 */
	public ActionForward queryPersonApply(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �������
		Rec03Form form = (Rec03Form) actionForm;
		Rec03DTO dto = new Rec03DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		ClassHelper.copyProperties(form, dto);
		if (form.getNnn001() == null || "".equals(form.getNnn001())) {
			dto.setNnn001(Short.valueOf("0"));

		}
		if (form.getNnn002() == null || "".equals(form.getNnn002())) {
			dto.setNnn002(Short.valueOf("100"));

		}
		StringBuffer hqlf = null;
		try {
			// ҳ����ת
			String forward = "/recommendation/query/queryPersonApply.jsp";
			dto.setFileKey("rec03_003");
			String hql = super.queryEnterprise(dto);
			// ��ѯ
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
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
	 * ��ѯ�Ƽ���Ϣ��ѯ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ�Ƽ���Ϣ��ѯ
	 * @throws ��ѯ�Ƽ���Ϣ��ѯ����
	 */
	public ActionForward queryRecommend1(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �������
		Rec03Form form = (Rec03Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		Rec03DTO dto = new Rec03DTO();
		try {
			// ����ҳ������
			ClassHelper.copyProperties(form, dto);
			if (form.getA030ae() != null && !(form.getA030ae().equals(""))) {
				dto.setA030ae(DateUtil.getStepDay(dto.getA030ae(), 1));
			}
			if (form.getA031ae() != null && !(form.getA031ae().equals(""))) {
				dto.setA031ae(DateUtil.getStepDay(dto.getA031ae(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());
			// ҳ����ת
			String forward = "/recommendation/query/queryRecommend.jsp";
			// ��ȡsql���
			dto.setFileKey("rec03_004");
			StringUtil.dealZero(dto.getAca111());
			// ���sql���
			String hql = queryEnterprise(dto);
			// ��ѯ
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
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
	 * ���˵�λ��Ϣ��ѯ
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ���˵�λ��Ϣ��ѯ
	 * @throws ���˵�λ��Ϣ��ѯ����
	 */
	public ActionForward queryEmployerRecommend(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �������
		Rec03Form form = (Rec03Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();

		Rec03DTO dto = new Rec03DTO();
		try {
			// ����ҳ������
			ClassHelper.copyProperties(form, dto);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			//dto.setAae017(dto1.getBsc001());//�Ǽǻ�����ѯ�������ǲ���Ա���ڵĻ�������ע�͵�
			// ҳ����ת
			String forward = "/recommendation/query/queryEmployerRecommend.jsp";
			// ��ȡsql���
            if(dto.getAcb208().equalsIgnoreCase("0")){
			  dto.setFileKey("rec01_010");
            }else if(dto.getAcb208().equalsIgnoreCase("1")){
				dto.setFileKey("rec01_011");
            }else{
				dto.setFileKey("rec01_015");
            }
		
			Short s = new Short("200");
			// ���sql���
			if(dto.getAcb222().intValue()==0){
				dto.setAcb222(s);
			}
			String hql = queryEnterprise(dto);
			// ��ѯ
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "û�������������ݣ�");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;

	}

}
