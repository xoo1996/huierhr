/**
 * Rec0802Action.java 2008/03/27
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

import org.radf.apps.commons.entity.Cb25;
import org.radf.apps.recommendation.ownpintorgan.dto.Rec0801DTO;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec0801Facade;
import org.radf.apps.recommendation.ownpintorgan.form.Rec0802Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;

/**
 * 民办职业介绍机构年检
 */
public class Rec0802Action extends ActionLeafSupport {

	/**
	 * 初始机构年检登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、初始化页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始机构年检登记页面
	 * @throws 初始机构年检登记页面出错
	 */
	public ActionForward initAddorganyc(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "";
		Rec0802Form form = (Rec0802Form) actionForm;
		String aae001 = form.getAae001();
		Rec0801DTO dto = new Rec0801DTO();
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.initaddorganyc(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				List ls = (ArrayList) map.get("beo");
				form = new Rec0802Form();
				ClassHelper.copyProperties(ls.get(0), form);
				form.setSsjqnm(OptionDictSupport.getCodeName(request, "AAE017",
						form.getCce001()));
				form.setAae001(aae001);
				forward = "addorganyearcheck";
				ClassHelper.copyProperties(form, actionForm);
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
	 * 保存机构年检信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、保存年检信息<br>
     * 2、一年只有一条年检纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 保存机构年检信息
	 * @throws 保存机构年检信息出错
	 */
	public ActionForward saveorganyc(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0802Form form = (Rec0802Form) actionForm;
		Cb25 dto = new Cb25();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// 经办人
			form.setAae017(dto1.getBsc001());// 经办机构
			form.setAae019(dto1.getBsc008());// 经办科室
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// 经办时间
			ClassHelper.copyProperties(form, dto);
			Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
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
	 * 初始化修改年检机构信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取年检信息，初始化页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化修改年检机构信息
	 * @throws 初始化修改年检机构信息出错
	 */
	public ActionForward initmodifyorganyc(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0802Form form = (Rec0802Form) actionForm;
		Rec0801DTO dto = new Rec0801DTO();
		dto.setFileKey("rec08_005");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
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
				forward = "initmodifyorganyc";
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
	 * 修改机构年检信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、保存年检信息<br>
     * 2、一年只有一条年检纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改机构年检信息
	 * @throws 修改机构年检信息出错
	 */
	public ActionForward modifyorganyc(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse res) throws Exception {

		Rec0802Form form = (Rec0802Form) actionForm;
		Cb25 dto = new Cb25();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// 修改人员
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// 修改时间
		try {
			ClassHelper.copyProperties(form, dto);
			dto.setFileKey("cb25_update");
			// 获取接口
			Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
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
				super.saveSuccessfulMsg(request, "修改失败");
				return mapping.findForward("backspace");
			}
			return go2Page(request, mapping, "recommendation");

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 删除机构年检信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除错误的年检信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除机构年检信息
	 * @throws 删除机构年检信息出错
	 */
	public ActionForward delorganyc(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0802Form form = (Rec0802Form) actionForm;
		Cb25 dto = new Cb25();
		dto.setFileKey("cb25_delete");
		ClassHelper.copyProperties(form, dto);
		if (("".equals(dto.getAcb240()) || dto.getAcb240() == null)
				|| ("".equals(dto.getAae001()) || dto.getAae001() == null)) {
			saveSuccessfulMsg(request, "没有查询到条信息！");
			return mapping.findForward("backspace");
		}
		Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
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
	 * 查看机构年检详细信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、显示机构的详细信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看机构年检详细信息
	 * @throws 查看机构年检详细信息出错
	 */
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0802Form form = (Rec0802Form) actionForm;
		Cb25 dto = new Cb25();
		dto.setFileKey("rec08_005");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0801Facade facade = (Rec0801Facade) getService("Rec0801Facade");
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
				forward = "vieworganyc";
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
