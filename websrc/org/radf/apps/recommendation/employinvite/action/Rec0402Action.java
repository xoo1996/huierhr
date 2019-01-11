/**
 * Rec0402Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employinvite.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.recommendation.employinvite.dto.Rec0402DTO;
import org.radf.apps.recommendation.employinvite.facade.Rec0402Facade;
import org.radf.apps.recommendation.employinvite.form.Rec0402Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.Money;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;
/**
 * 委托招聘岗位信息管理
 */
public class Rec0402Action extends ActionLeafSupport {
	/**
	 * 进入空岗位位页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、以分页标签新式查询cb21中对应岗位信息<br>
     * 2、设置新增 、修改、删除、查看等功能</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 空岗位位页面
	 * @throws 空岗位位出错
	 */
	public ActionForward toNext(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

	
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402DTO dto = new Rec0402DTO();
		ActionForward af = new ActionForward("/recommendation/employinvite/ManageInvitePosition.jsp");
		String url = "";
		String hql = null;
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec04_002");
		try {
			url = (String) request.getParameter("nextP");
			if (url != null && url.length() > 0) {
				request.getSession().setAttribute("nextP", url);
			}
			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.toNext(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(list.get(0), dto);
				request.getSession().setAttribute("Rec0402DTO", dto);
				// 查询空位信息
				dto.setFileKey("rec04_003");
				hql = queryEnterprise(dto);
				af = init(
						request,
						"/recommendation/employinvite/ManageInvitePosition.jsp",
						hql, "2");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return actionMapping.findForward("backspace");
			}

		} catch (Exception re) {
			saveErrors(request, re);
			return actionMapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 查看招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据选择的数据查询出cb20中的所有数据显示出来</tt>
	 * 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看招聘信息页面
	 * @throws 查看招聘信息出错
	 */
	public ActionForward viewEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmptyPostAction:根据空位编号查找一个招聘空位的详细信息");
		// 0.定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		if (form.getAcb200() == null) {
			String acb200 = "";
			acb200 = (String) request.getSession().getAttribute(
					"acb200");
			form.setAcb200(acb200);
		}
		if (form.getAcb210() == null) {
			String acb210 = "";
			acb210 = (String) request.getSession().getAttribute(
					"acb210");
			form.setAcb210(acb210);
		}
		if (form.getAab001() == null) {
			String aab001 = "";
			aab001 = (String) request.getSession().getAttribute(
					"aab001");
			form.setAab001(aab001);
		}
		String url = "";

		try {
			// 1.确定分发方向

			url = "viewEmptyPost";
			request.getSession().setAttribute("viewP",
					(String) request.getParameter("viewP"));

			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				form = (Rec0402Form) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(form,actionForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}

	/**
	 * 为初始化招聘空位信息增加界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、产生岗位新增页面<br>
     * 2、提供用户输入完后的保存功能</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化招聘空位信息页面
	 * @throws 初始化招聘空位信息出错
	 */
	public ActionForward addEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitAddEmptyPostAction:根据招聘编号查找一个委托招聘的详细信息");
		// 0.定义变量

		Rec0402Form form = (Rec0402Form) actionForm;
		super.resetToken(request);
		String url = "";
		try {
			// 确定分发方向

			url = "addEmptyPost";

			request.getSession().setAttribute("addP",
					(String) request.getParameter("addP"));
			form = new Rec0402Form();
			ParaUtil pu = new ParaUtil();
			String aaeyxq = (pu.getParaV("zpyxq", "zpyxq", "rec")).toString();
			String ac21hb = (pu.getParaV("zpzdgz", "zpzdgz", "rec")).toString();
			form.setAaeyxq(aaeyxq);
			form.setAc21hb(ac21hb);
			Date aaedate = DateUtil.getDate();
			form.setAae030(aaedate.toString());
			form.setAae031(DateUtil.getStepDay(aaedate,
					TypeCast.stringToInt(aaeyxq, "招聘有效期", false)).toString());
			ClassHelper.copyProperties(form, actionForm);

		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}


	/**
	 * 新增招聘空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、对应cb20表主建将数据插入cb21表中</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 新增招聘空位信息页面
	 * @throws 新增招聘空位出错
	 */
	public ActionForward saveEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("AddEmptyPostAction:新增招聘空位信息");
		// 1.定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402DTO input = new Rec0402DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		// ActionForward af = new
		// ActionForward("/recommendation/employinvite/ManageInvitePosition.jsp");
		String aab001 = request.getParameter("aab001");
		String acb200 = request.getParameter("acb200");
		input.setAcb200(acb200);
		input.setAab001(aab001);
		String hql = null;

		try {

			ClassHelper.copyProperties(form, input);

			// 获取经办人信息
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAae036(DateUtil.getSystemCurrentTime());

			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.addEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "新增招聘空位信息成功！");
				return go2Page(request, mapping, "recommendation", "2");

				/*
				 * saveToken(request); Rec0402DTO dto = new Rec0402DTO();
				 * dto.setAcb200(acb200); dto.setFileKey("rec04_003"); hql =
				 * queryEnterprise(dto); return
				 * init(request,"/recommendation/employinvite/ManageInvitePosition.jsp",
				 * hql);
				 */} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward("backspace");
	}

	/**
	 * 为初始化招聘空位修改界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询出cb21表中的指定数据，并显示页面上</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化招聘空位修改界面页面
	 * @throws 初始化招聘空位修改界面出错
	 */
	public ActionForward modEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmptyPostAction:根据空位编号查找一个招聘空位的详细信息");
		// 0.定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402Form output = (Rec0402Form) actionForm;
		String url = "";
		try {
			// 1.确定分发方向
			url = "modEmptyPost";

			request.getSession().setAttribute("modP",
					(String) request.getParameter("modP"));

			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				output = (Rec0402Form) ((HashMap) resEnv.getBody()).get("beo");
				ParaUtil pu = new ParaUtil();
				String aaeyxq = (pu.getParaV("zpyxq", "zpyxq", "rec"))
						.toString();
				String ac21hb = (pu.getParaV("zpzdgz", "zpzdgz", "rec")).toString();
				output.setAaeyxq(aaeyxq);
				output.setAc21hb(ac21hb);
				ClassHelper.copyProperties(output, actionForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward(url);
	}

	/**
	 * 修改招聘空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将用户修改后的数据保存到cb21表中，具体数据项参照表结构</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改招聘空位信息页面
	 * @throws 修改招聘空位信息出错
	 */
	public ActionForward modsaveEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402DTO output = new Rec0402DTO();
		String hql = null;
		String menuId = request.getSession().getAttribute("menuId").toString();
		try {
			ClassHelper.copyProperties(form, output);

			// 获取经办人信息
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			output.setAae011(dto1.getBsc010());
			output.setAae017(dto1.getBsc001());
			output.setAae036(DateUtil.getSystemCurrentTime());
			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", output);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modsaveEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "修改招聘空位信息成功！");
				return go2Page(request, mapping, "recommendation", "2");
				
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return mapping.findForward("backspace");
	}

	/**
	 * 注销一个空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将选中的岗位信息，注销掉，即把岗位状态改为注销标记</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 注销一个空位信息页面
	 * @throws 注销一个空位信息出错
	 */
	public ActionForward delEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		// log.info("DelEmptyPostAction:冻结招聘空位信息");
		// 1.定义变量
		Rec0402Form input = (Rec0402Form) actionForm;
		Cb21 cb21 = new Cb21();
		cb21.setAcb210(input.getAcb210());
		cb21.setAcb208("1");// 设置无效
		cb21.setFileKey("cb21_update");
		try {
			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb21);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "成功注销一个空位信息。");
				/*Rec0402DTO dto = new Rec0402DTO();
				dto.setAcb200(input.getAcb200());
				dto.setFileKey("rec04_003");
				String hql = queryEnterprise(dto);
				return init(
						request,
						"/recommendation/employinvite/ManageInvitePosition.jsp",
						hql);*/
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		//return mapping.findForward("delEmptyPost");
		return go2Page(request, mapping, "recommendation", "2");

	}
	/**
	 * 删除一个空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除岗位信息</br>
     * 2、删除的岗位信息必须是没有推荐过的，即cc22表中没有该岗位编号对应的纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除一个空位页面
	 * @throws 删除一个空位信息出错
	 */
	public ActionForward deleteEmptyPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		// log.info("DelEmptyPostAction:冻结招聘空位信息");
		// 1.定义变量
		Rec0402Form input = (Rec0402Form) actionForm;
		Cb21 cb21 = new Cb21();
		cb21.setAcb210(input.getAcb210());
		try {
			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb21);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteEmptyPost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "成功删除一个空位信息。");
				/*Rec0402DTO dto = new Rec0402DTO();
				dto.setAcb200(input.getAcb200());
				dto.setFileKey("rec04_003");
				String hql = queryEnterprise(dto);
				return init(
						request,
						"/recommendation/employinvite/ManageInvitePosition.jsp",
						hql);*/
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		//return mapping.findForward("delEmptyPost");
		return go2Page(request, mapping, "recommendation", "2");

	}
	/**
	 * 进入用人信息维护页面<br>
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
	 * @return 进入用人信息维护页面
	 * @throws 进入用人信息维护页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		String forward = "queryEmployInvite";
		req.getSession().setAttribute("menuId", menuId);		
		Rec0402Form fm=new Rec0402Form();
		ClassHelper.copyProperties(fm,form);
		return mapping.findForward(forward);
	}

	/**
	 * 查询委托招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据条件查询cb20的业务数据，并列表显示</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询委托招聘信息页面
	 * @throws 查询委托招聘信息出错
	 */
	public ActionForward queryEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();

		// log.info("QueryEmployInviteAction:查询委托招聘信息");
		ActionForward af = new ActionForward(
				"/recommendation/employinvite/QueryEmployInvite.jsp");
		Rec0402DTO input = new Rec0402DTO();
		String hql = null;

		// 调用接口的实现进行查询
		try {
			// Copy form到实体dto
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			input.setFileKey("rec04_004");
			hql = queryEnterprise(input);
			af = init(request,
					"/recommendation/employinvite/QueryEmployInvite.jsp", hql);
			Collection data = (Collection) request
					.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null) {
				saveSuccessfulMsg(request, "没有符合条件的信息！");
			}
		} catch (Exception re) {
			saveErrors(request, re);
		}
		return af;
	}

	/**
	 * 查看单位招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、以详细信息的形势显示单位本次招聘的信息，数据来源于cb20、ab01</tt>
	 * 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看单位招聘信息页面
	 * @throws 查看单位招聘信息出错
	 */
	public ActionForward viewEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmployInviteAction:根据招聘编号查找一个委托招聘的详细信息");
		// 0.定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402DTO dto = new Rec0402DTO();
		dto.setAab001(form.getAab001());
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec04_005");
		String url = "";
		try {
			// 1.确定分发方向
			url = "viewEmployInvite";
			request.getSession().setAttribute("viewE",
					(String) request.getParameter("viewE"));

			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.viewEmployInvite(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(list.get(0), form);
				if (form.getAcb209() != null) {
					form.setAcb209(new Money(form.getAcb209()).getAmount()
							.toString());
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(url);
	}

	/**
	 * 为初始化委托招聘信息修改界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取cb20数据，并初始化页面，提供给用户修改</tt>
	 * 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 初始化委托招聘信息修改页面
	 * @throws 初始化委托招聘信息修改界面出错
	 */
	public ActionForward modEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		// log.info("InitModEmployInviteAction:根据招聘编号查找一个委托招聘的详细信息");
		// 0.定义变量
		Rec0402Form form = (Rec0402Form) actionForm;
		Rec0402DTO dto = new Rec0402DTO();
		dto.setAab001(form.getAab001());
		dto.setAcb200(form.getAcb200());
		dto.setFileKey("rec04_005");
		String url = "";
		try {
			// 1.确定分发方向
			url = "modEmployInvite";
			request.getSession().setAttribute("modE",
					(String) request.getParameter("modE"));

			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.viewEmployInvite(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(list.get(0), form);
				if (form.getAcb209() != null) {
					form.setAcb209(new Money(form.getAcb209()).getAmount()
							.toString());
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(url);
	}

	/**
	 * 修改委托招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据录入信息修改cb20数据</tt>
	 * 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改委托招聘页面
	 * @throws 修改委托招聘出错
	 */
	public ActionForward modsaveEmployInvite(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {

		Rec0402Form form = (Rec0402Form) actionForm;
		Cb20 cb20 = new Cb20();
		try {
			ClassHelper.copyProperties(form, cb20);
			cb20.setFileKey("cb20_update");
			Rec0402Facade facade = (Rec0402Facade) getService("Rec0402Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cb20);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modEmployInvite(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				saveSuccessfulMsg(request, "修改招聘信息成功！");
				return go2Page(request, mapping, "recommendation");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);

			}
		} catch (Exception re) {
			saveErrors(request, re);
		}

		return mapping.findForward("modEmployInvite");
	}

}