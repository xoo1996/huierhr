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
 * 大会招聘期数信息管理
 */
public class Rec0703Action extends ActionLeafSupport {

	/**
	 *进入大会招聘期数维护页面（连接大会招聘期数维护菜单）<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、跳转到指定页面，清空相应form数据</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 大会招聘期数页面
	 * @throws 大会招聘期数出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryQs".equalsIgnoreCase(menuId)) {
			forward = "queryQs";// 申请
		}
		return mapping.findForward(forward);

	}

	/**
	 * 查询大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询大会期数表的数据</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询大会招聘期数信息页面
	 * @throws 查询大会招聘期数信息出错
	 */
	public ActionForward enterquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 cb23 = new Cb23();
		ClassHelper.copyProperties(form, cb23);
		cb23.setFileKey("rec07_010");
		// 获取SQL语句
		String hql = queryEnterprise(cb23);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/employassembly/enterqueryqs.jsp", hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

	/**
	 * 初始增加化灵大会招聘期数信息页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取大会期数并初始化页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始增加化灵大会招聘期数信息页面
	 * @throws 初始增加化灵大会招聘期数信息页面出错
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
	 * 保存大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、保存大会期数信息到数据库</br>
	 * 2、每个机构只有一条有效的期数纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 保存大会招聘期数信息页面
	 * @throws 保存大会招聘期数信息出错
	 */
	public ActionForward saveqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// 经办人
			form.setAae017(dto1.getBsc001());// 经办机构
			form.setAae019(dto1.getBsc008());// 经办科室
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// 经办时间
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
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * 初始修改化大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取大会期数并初始化页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始修改化大会招聘期数信息
	 * @throws 初始修改化大会招聘期数信息出错
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
	 * 修改大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改大会期数信息到数据库</br>
	 * 2、每个机构只有一条有效的期数纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改大会招聘期数信息
	 * @throws 修改大会招聘期数信息出错
	 */
	public ActionForward modifyqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// 修改人员
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// 修改时间

		try {
			ClassHelper.copyProperties(form, dto);
			dto.setAae100(null);
			dto.setFileKey("cb23_update");
			// 获取接口
			Rec0703Facade facade = (Rec0703Facade) getService("Rec0703Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyCommon(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "修改成功");
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
	 * 注销大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、注销大会期数信息到数据库</br>
	 * 2、每个机构只有一条有效的期数纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 注销大会招聘期数信息
	 * @throws 注销大会招聘期数信息出错
	 */
	public ActionForward writeoffqs(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// 修改人员
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// 修改时间
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
			super.saveSuccessfulMsg(request, "修改成功");

		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * 删除大会招聘期数信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除大会期数信息到数据库</br>
	 * 2、如果该期数已经被使用就不能删除</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除大会招聘期数信息
	 * @throws 删除大会招聘期数信息出错
	 */
	public ActionForward delqs(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0703Form form = (Rec0703Form) actionForm;
		Cb23 dto = new Cb23();
		dto.setFileKey("cb23_delete");
		ClassHelper.copyProperties(form, dto);
		if ("".equals(dto.getAcb230()) || dto.getAcb230() == null) {
			saveSuccessfulMsg(request, "没有信息,不能删除！");
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
			super.saveSuccessfulMsg(request, "删除成功");

		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation");
	}

	/**
	 * 查看大会招聘期数详细信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查看大会期数信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看大会招聘期数详细信息
	 * @throws 查看大会招聘期数详细信息出错
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
