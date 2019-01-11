/**
 * Rec0201Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personapply.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cc20;
import org.radf.apps.recommendation.personapply.dto.Rec0201DTO;
import org.radf.apps.recommendation.personapply.facade.Rec02Facade;
import org.radf.apps.recommendation.personapply.form.Rec0201Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;

/**
 * ��ְ�Ǽǹ���
 */
public class Rec0201Action extends ActionLeafSupport {

	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * ��ת�Ǽ�ҳ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1������menuid��ʾҳ��</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ת�Ǽ�ҳ��
	 * @throws ��ת�Ǽ�ҳ�����
	 */
	public ActionForward findPer(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "modiperson";
		ClassHelper.copyProperties(req.getSession().getAttribute("Rec02Form"),
				form);
		String menuId = (String) req.getSession().getAttribute("menuId");
		return mapping.findForward(forward);

	}

	/**
	 * ������ְ��Ըҳ�棬��ѯ��ĳһ��Ա�������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��������ְ��Ϣ�Ƿ���Ч,��ѯ����</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ������ְ��Ըҳ�棬��ѯ��ĳһ��Ա�������Ϣ
	 * @throws ������ְ��Ըҳ�棬��ѯ��ĳһ��Ա�������Ϣ����
	 */
	public ActionForward modPer(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Rec0201Form pf = (Rec0201Form) form;
		req.getSession().setAttribute("modPA",
				(String) req.getParameter("modPA"));
		String menuId = req.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		Rec0201DTO dto = new Rec0201DTO();
		LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		String forward = "modiperson2";
		dto.setAac001(pf.getAac001());
		try {
			dto.setAae017(dto1.getBsc001());
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.findPersonInfo(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				dto = (Rec0201DTO) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(dto, pf);
				pf.setAae043(DateUtil.getSystemCurrentTime().toString());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(forward);
	}

	/**
	 * �鿴������ְ��Ϣ��ʼ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ʾ��ǰ��Ч����ְ��Ϣ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �鿴������ְ��Ϣ��ʼ��
	 * @throws �鿴������ְ��Ϣ��ʼ������
	 */
	public ActionForward viewPersonApply(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("viewPA",
				(String) request.getParameter("viewPA"));
		Rec0201Form form = (Rec0201Form) actionForm;
		Cc20 cc20 = new Cc20();
		cc20.setAac001(form.getAac001());
		cc20.setAcc200(form.getAcc200());
		/*
		 * if("".equals(cc20.getAcc200())||cc20.getAcc200()==null) {
		 * cc20.setAcb208("0"); } else { cc20.setAcb208(null); }
		 */
		cc20.setFileKey("rec02_009");
		try {
			// ��ȡ�ӿ�
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc20);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.viewPersonApply(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), form);
				} else {
					saveSuccessfulMsg(request, "����Ա��������ְ��Ϣ��");
					return actionMapping.findForward("backspace");
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return actionMapping.findForward("backspace");
		}
		return actionMapping.findForward("viewPersonApply");
	}

	/**
	 * �޸ĸ�����ְ��Ϣ��ʼ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸ĵ�ǰ��Ч����ְ��Ϣ</br>
     * 2��������Ƽ��в����޸�</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸ĸ�����ְ��Ϣ��ʼ��
	 * @throws �޸ĸ�����ְ��Ϣ��ʼ������
	 */
	public ActionForward modPersonApply(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// log.info("InitPersonApplyAction:ҳ�����ݳ�ʼ��");
		request.getSession().setAttribute("modPA",
				(String) request.getParameter("modPA"));
		Rec0201Form form = (Rec0201Form) actionForm;
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");

		try {
			// ��ȡ�ӿ�
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			form.setAae017(dto1.getBsc001());
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.findPersonApply(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				form = (Rec0201Form) ((HashMap) resEnv.getBody()).get("beo");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return actionMapping.findForward("backspace");
			}

		} catch (Exception e) {
			saveErrors(request, e);
			return actionMapping.findForward("backspace");
		}

		return actionMapping.findForward("modPersonApply");
	}

	/**
	 * ɾ��������ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��ɾ����ְ��Ϣ</br>
     * 2��������Ƽ��в���ɾ��</tt>
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ɾ��������ְ��Ϣ��ʼ��
	 * @throws ɾ��������ְ��Ϣ��ʼ������
	 */
	public ActionForward delPersonApply(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Rec0201Form form = (Rec0201Form) actionForm;
		Cc20 cc20 = new Cc20();
		ClassHelper.copyProperties(form, cc20);

		try {
			// ��ȡ�ӿ�
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc20);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.delPersonApply(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "ɾ���ɹ�");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return actionMapping.findForward("backspace");
			}

		} catch (Exception e) {
			saveErrors(request, e);
			return actionMapping.findForward("backspace");
		}

		return go2Page(request, actionMapping, "recommendation", "1");
	}

	/**
	 * �����¼ӵĻ�����Ϣ����ӣ�������ְ��Ϣ����ӣ�������ְ��������<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1�����������Ϣ����ְ��Ϣ�������ڵĸ�����ְ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �����¼ӵĻ�����Ϣ����ӣ�������ְ��Ϣ����ӣ�������ְ��������
	 * @throws �����¼ӵĻ�����Ϣ����ӣ�������ְ��Ϣ����ӣ�������ְ�������ӳ���
	 */
	public ActionForward savePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// log.info("��������...");\
		String menuId = req.getSession().getAttribute("menuId").toString();
		String forward = "modiperson";
		Rec0201Form form1 = (Rec0201Form) form;
		Rec0201DTO dto = new Rec0201DTO();

		try {
			// �趨������Ϣ
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			form1.setAae011(dto1.getBsc010());
			form1.setAae017(dto1.getBsc001());
			ClassHelper.copyProperties(form1, dto);
			dto.setAae036(DateUtil.getSystemCurrentTime());
			// ���ýӿ�
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			/* mapRequest.put("type", type); */
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.savePerson(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				Rec0201DTO ls = (Rec0201DTO) ((HashMap) resEnv.getBody())
						.get("beo");

				if (ls != null) {
					ClassHelper.copyProperties(ls, dto);
					form1.setAcc200(dto.getAcc200());
					form1.setAcc210(dto.getAcc210());
					form1.setAac001(dto.getAac001());
				}
				super.saveSuccessfulMsg(req, "������ְ��Ϣ�����ɹ���");
				String data = "&aac002=" + dto.getAac002();
				req.getSession().setAttribute("menuId", menuId);
				req.getSession().setAttribute("form", form1);
				forward = "forward";

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(req, aa[3]);
				forward = "backspace";
			}
		} catch (Exception e) {
			saveErrors(req, e);
			forward = "backspace";
		}
		return mapping.findForward(forward);
		// return redirect(req,mapping);
	}

	/**
	 * �޸ĸ�����ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸ĵ�ǰ��Ч����ְ��Ϣ</br>
     * 2��������Ƽ��в����޸�</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸ĸ�����ְ��Ϣ
	 * @throws �޸ĸ�����ְ��Ϣ����
	 */
	public ActionForward modPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// log.info("��������...");
		String menuId = req.getSession().getAttribute("menuId").toString();
		Rec0201Form form1 = (Rec0201Form) form;
		Rec0201DTO dto = new Rec0201DTO();
		String forward = "modiperson2";

		try {
			ClassHelper.copyProperties(form1, dto);
			// �趨������Ϣ
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			dto.setAae011(dto1.getBsc010());
			dto.setAae017(dto1.getBsc001());
			dto.setAae036(DateUtil.getSystemCurrentTime());
			// ���ýӿ�
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modPerson(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				Rec0201DTO ls = (Rec0201DTO) ((HashMap) resEnv.getBody())
						.get("beo");

				if (ls != null) {
					ClassHelper.copyProperties(ls, dto);
					form1.setAcc200(dto.getAcc200());
					form1.setAcc210(dto.getAcc210());
					form1.setAac001(dto.getAac001());
				}
				saveSuccessfulMsg(req, "������ְ��Ϣ����ɹ���");
				String data = "&aac002=" + dto.getAac002();
				req.getSession().setAttribute("menuId", menuId);
				req.getSession().setAttribute("form", form1);
				forward = "forward";
				/*
				 * String forward =
				 * "/recommendation/personapply/QueryPersonApply.jsp";
				 * dto.setFileKey("SearchPersonApplyAction_select01"); String
				 * hql = queryEnterprise(dto); return super.init(req, forward,
				 * hql);
				 */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(req, aa[3]);
				forward = "backspace";
			}
		} catch (Exception e) {
			saveErrors(req, e);
			forward = "backspace";
		}
		// return mapping.findForward(forward);
		// return redirect(req,mapping);
		return mapping.findForward(forward);
		// return go2Page(req, mapping, "recommendation");
	}

	/**
	 * ����������ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1�������ǰ����Ч����ְ��Ϣ,��������</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �޸ĸ�����ְ��Ϣ
	 * @throws �޸ĸ�����ְ��Ϣ����
	 */
	public ActionForward savePersonApply(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0201Form form = (Rec0201Form) actionForm;
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae011(dto1.getBsc010());
		form.setAae017(dto1.getBsc001());
		form.setAae036(DateUtil.getSystemCurrentTime().toString());
		// String type = TypeCast.getDept(form.getAae017());
		try {
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// mapRequest.put("type", type);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modPersonApply(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);

			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "����ɹ�");
				return go2Page(request, mapping, "recommendation", "1");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}

}