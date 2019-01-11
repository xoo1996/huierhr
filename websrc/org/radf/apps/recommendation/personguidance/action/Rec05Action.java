/**
 /**
 * Rec05Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personguidance.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cc23;
import org.radf.apps.recommendation.personguidance.dto.Rec05DTO;
import org.radf.apps.recommendation.personguidance.facade.Rec05Facade;
import org.radf.apps.recommendation.personguidance.form.Rec05Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 职业指导管理
 */
public class Rec05Action extends ActionLeafSupport {

	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * 人员查询跳转
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询页面
	 * @throws 查询出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		req.getSession().setAttribute("menuId", menuId);
		String forward = "";
		forward = menuId;
		Rec05Form fm = new Rec05Form();
		ClassHelper.copyProperties(fm, form);
		return mapping.findForward(forward);

	}

	/**
	 * 人员查询
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 人员查询页面
	 * @throws 人员查询出错
	 */
	public ActionForward searchPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Rec05Form pf = (Rec05Form) form;
		String menuId = req.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		Rec05DTO dto = new Rec05DTO();
		String forward = "/recommendation/personguidance/QueryPerson.jsp";
		ClassHelper.copyProperties(pf, dto);
		dto.setFileKey("rec05_001");
		try {
			String hql = queryEnterprise(dto);
			af = init(req, forward, hql);
			List data = (List) req.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(req, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(req, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 职业指导新增初始化
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 职业指导新增初始化
	 * @throws 职业指导新增初始化出错
	 */
	public ActionForward initadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Rec05Form pf = (Rec05Form) form;
		Rec05Form form1 = new Rec05Form();
		form1.setAac001(pf.getAac001());
		form1.setAac002(pf.getAac002());
		form1.setAac003(pf.getAac003());
		form1.setAac004(pf.getAac004());
		ClassHelper.copyProperties(form1, form);
		String forward = "addpersonguidance";
		String menuId = (String) req.getSession().getAttribute("menuId");
		return mapping.findForward(forward);

	}

	/**
	 * 查询历史职业指导
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询历史职业指导
	 * @throws 查询历史职业指导出错
	 */
	public ActionForward queryhis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Rec05Form pf = (Rec05Form) form;
		String menuId = req.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		Rec05DTO dto = new Rec05DTO();
		String forward = "/recommendation/personguidance/QueryHis.jsp";
		ClassHelper.copyProperties(pf, dto);
		dto.setFileKey("rec05_002");
		try {
			String hql = queryEnterprise(dto);
			af = init(req, forward, hql, "2");
			List data = (List) req.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(req, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(req, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 保存职业指导
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 保存职业指导
	 * @throws 保存职业指导出错
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getSession().getAttribute("menuId").toString();
		Rec05Form form1 = (Rec05Form) form;
		Cc23 cc23 = new Cc23();

		try {
			// 设定经办信息
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			form1.setAae011(dto1.getBsc010());
			form1.setAae017(dto1.getBsc001());
			ClassHelper.copyProperties(form1, cc23);
			cc23.setAae036(DateUtil.getSystemCurrentTime());
			// 调用接口
			Rec05Facade facade = (Rec05Facade) getService("Rec05Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc23);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功!");
				return go2Page(req, mapping, "recommendation", "1");

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

	}

	/**
	 * 查看职业指导
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看职业指导
	 * @throws 查看职业指导出错
	 */
	public ActionForward viewguidance(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Rec05Form pf = (Rec05Form) form;
		Cc23 cc23 = new Cc23();
		ClassHelper.copyProperties(pf, cc23);
		String forward = "viewguidance";
		String menuId = (String) req.getSession().getAttribute("menuId");
		try {

			// 调用接口
			Rec05Facade facade = (Rec05Facade) getService("Rec05Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc23);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findpersonguidance(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				cc23 = (Cc23) ((HashMap) resEnv.getBody()).get("beo");
				if (cc23 != null) {
					ClassHelper.copyProperties(cc23, pf);

				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(forward);

	}

	/**
	 * 修改职业指导初始化
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改职业指导初始化
	 * @throws 修改职业指导初始化出错
	 */
	public ActionForward initedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Rec05Form pf = (Rec05Form) form;
		Cc23 cc23 = new Cc23();
		ClassHelper.copyProperties(pf, cc23);
		String forward = "editpersonguidance";
		String menuId = (String) req.getSession().getAttribute("menuId");
		try {

			// 调用接口
			Rec05Facade facade = (Rec05Facade) getService("Rec05Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc23);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findpersonguidance(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				cc23 = (Cc23) ((HashMap) resEnv.getBody()).get("beo");
				if (cc23 != null) {
					ClassHelper.copyProperties(cc23, pf);

				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(forward);

	}

	/**
	 * 修改职业指导
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改职业指导
	 * @throws 修改职业指导出错
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getSession().getAttribute("menuId").toString();
		Rec05Form form1 = (Rec05Form) form;
		Cc23 cc23 = new Cc23();

		try {
			// 设定经办信息
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			form1.setAae011(dto1.getBsc010());
			form1.setAae017(dto1.getBsc001());
			ClassHelper.copyProperties(form1, cc23);
			cc23.setAae036(DateUtil.getSystemCurrentTime());
			// 调用接口
			Rec05Facade facade = (Rec05Facade) getService("Rec05Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc23);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.edit(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改成功!");
				return go2Page(req, mapping, "recommendation", "2");

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

	}
}