/**
 * Rec0804Action.java 2008/03/27
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

import org.radf.apps.commons.entity.Cb26;
import org.radf.apps.recommendation.ownpintorgan.dto.Rec0803DTO;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec0803Facade;
import org.radf.apps.recommendation.ownpintorgan.form.Rec0804Form;
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
 * 民办职业介绍机构日常投诉
 */
public class Rec0804Action extends ActionLeafSupport {

	/**
	 * 初始机构日常投诉登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、显示投诉登记页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始机构日常投诉登记页面
	 * @throws 初始机构日常投诉登记页面出错
	 */
	public ActionForward initAddorgancom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "";
		Rec0804Form form = (Rec0804Form) actionForm;
		Rec0803DTO dto = new Rec0803DTO();
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			dto.setFileKey("rec08_003");
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.search(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				List ls = (ArrayList) map.get("beo");
				form = new Rec0804Form();
				ClassHelper.copyProperties(ls.get(0), form);
				forward = "addorgancom";
				form.setSsjqnm(OptionDictSupport.getCodeName(request, "AAE017",
						form.getCce001()));
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
	 * 保存机构日常投诉<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据录入信息保存</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 保存机构日常投诉
	 * @throws 保存机构日常投诉出错
	 */
	public ActionForward saveorgancom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0804Form form = (Rec0804Form) actionForm;
		Cb26 dto = new Cb26();
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			form.setAae011(dto1.getBsc010());// 经办人
			form.setAae017(dto1.getBsc001());// 经办机构
			form.setAae019(dto1.getBsc008());// 经办科室
			form.setAae036(DateUtil.getSystemCurrentTime().toString());// 经办时间
			ClassHelper.copyProperties(form, dto);
			Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.saveorgancom(requestEnvelop);
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
	 * 初始化修改机构日常投诉信息页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询选中的投诉信息并显示页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化修改机构日常投诉信息页面
	 * @throws 初始化修改机构日常投诉信息页面出错
	 */
	public ActionForward initmodifyorgancom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0804Form form = (Rec0804Form) actionForm;
		Rec0803DTO dto = new Rec0803DTO();
		dto.setFileKey("rec08_007");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
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
				forward = "initmodifyorgancom";
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
	 * 修改机构日常投诉信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、把修改信息保存到数据库</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改机构日常投诉信息
	 * @throws 修改机构日常投诉信息出错
	 */
	public ActionForward modifyorgancom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse res) throws Exception {

		Rec0804Form form = (Rec0804Form) actionForm;
		Cb26 dto = new Cb26();
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		form.setAae101(dto1.getBsc010());// 修改人员
		form.setAae136(DateUtil.getSystemCurrentTime().toString());// 修改时间
		try {
			ClassHelper.copyProperties(form, dto);
			dto.setFileKey("cb26_update");
			// 获取接口
			Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
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
	 * 删除机构日常投诉信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除投诉信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除机构日常投诉信息
	 * @throws 删除机构日常投诉信息出错
	 */
	public ActionForward delorgancom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0804Form form = (Rec0804Form) actionForm;
		Cb26 dto = new Cb26();
		dto.setFileKey("cb26_delete");
		ClassHelper.copyProperties(form, dto);
		if (("".equals(dto.getAcb260()))) {
			saveSuccessfulMsg(request, "没有查询到条信息！");
			return mapping.findForward("backspace");
		}
		Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
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
	 * 查看机构机构日常投诉信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查看投诉信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看机构机构日常投诉信息
	 * @throws 查看机构机构日常投诉信息出错
	 */
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Rec0804Form form = (Rec0804Form) actionForm;
		Cb26 dto = new Cb26();
		dto.setFileKey("rec08_007");
		String forward;
		try {
			ClassHelper.copyProperties(form, dto);
			Rec0803Facade facade = (Rec0803Facade) getService("Rec0803Facade");
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
				forward = "vieworgancom";
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
