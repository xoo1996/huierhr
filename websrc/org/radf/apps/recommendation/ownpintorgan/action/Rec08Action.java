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
 * 民办职业介绍机构管理
 */
public class Rec08Action extends ActionLeafSupport {

	/**
	 * 进入民办职业介绍机构维护页面
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 进入民办职业介绍机构维护页面
	 * @throws 进入民办职业介绍机构维护页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryOrgan".equalsIgnoreCase(menuId)) {
			forward = "queryOrgan";// 查询机构信息
		}
		return mapping.findForward(forward);

	}

	/**
	 * 查询民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询民办职业介绍机构信息
	 * @throws 查询民办职业介绍机构信息出错
	 */
	public ActionForward enterquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 cb24 = new Cb24();
		ClassHelper.copyProperties(form, cb24);
		cb24.setFileKey("rec08_001");
		// 获取SQL语句
		String hql = queryEnterprise(cb24);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/ownpintorgan/enterqueryorgan.jsp", hql, "1");
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

	/**
	 * 初始化增加民办职业介绍机构信息页面
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化增加民办职业介绍机构信息页面
	 * @throws 初始化增加民办职业介绍机构信息页面出错
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
	 * 保存民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 保存民办职业介绍机构信息
	 * @throws 保存民办职业介绍机构信息出错
	 */
	public ActionForward saveorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// 经办人
			form.setAae017(dto1.getBsc001());// 经办机构
			form.setAae019(dto1.getBsc008());// 经办科室
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// 经办时间
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
				super.saveSuccessfulMsg(request, "增加成功");
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
	 * 初始化修改民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化修改民办职业介绍机构信息
	 * @throws 初始化修改民办职业介绍机构信息出错
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
	 * 修改民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改民办职业介绍机构信息
	 * @throws 修改民办职业介绍机构信息出错
	 */
	public ActionForward modifyorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// 修改人员
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// 修改时间
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
			super.saveSuccessfulMsg(request, "修改成功");
			return go2Page(request, mapping, "recommendation");
		} else {
			super.saveSuccessfulMsg(request, "修改失败");
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 删除民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除民办职业介绍机构信息
	 * @throws 删除民办职业介绍机构信息出错
	 */
	public ActionForward delorgan(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec08Form form = (Rec08Form) actionForm;
		Cb24 dto = new Cb24();
		dto.setFileKey("cb24_delete");
		ClassHelper.copyProperties(form, dto);
		if ("".equals(dto.getAcb240()) || dto.getAcb240() == null) {
			saveSuccessfulMsg(request, "没有查询到条信息！");
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
			super.saveSuccessfulMsg(request, "删除成功");

		} else {
			super.saveSuccessfulMsg(request, "删除失败");
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * 查看民办职业介绍机构信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看民办职业介绍机构信息
	 * @throws 查看民办职业介绍机构信息出错
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
