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
 * 委托招聘登记
 */
public class Rec0401Action extends ActionLeafSupport {
	/**
	 * 为初始化委托招聘信息修改界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取ab01单位基本信息，初始化页面<br>
     * 2、招聘类别为委托招聘<br>
     * 3、页面数据项包括表cb20得数据项<br></tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 委托招聘信息
	 * @throws 委托招聘信息出错
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
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", ab01);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.initaddEmployInvite(requestEnvelop);
			// 处理返回结果
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
	 * 新增委托招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、数据保存后方能进入下一步登记招聘岗位信息<br>
     * 2、插入数据到cb20单位招聘信息表</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 新增委托招聘信息
	 * @throws 新增委托招聘信息出错
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
			saveErrors(request, new Exception("数据已经进行了保存，请确认没有重复保存！！"));
		} else {
			try {
				ClassHelper.copyProperties(form, cb20);
				// 获取经办人信息
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				cb20.setAae011(dto1.getBsc010());
				cb20.setAae017(dto1.getBsc001());
				cb20.setAae036(DateUtil.getSystemCurrentTime());
				// 调用接口
				Rec0401Facade facade = (Rec0401Facade) getService("Rec0401Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", cb20);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.addEmployInvite(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					saveSuccessfulMsg(request, "新增单位招聘信息成功！");
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