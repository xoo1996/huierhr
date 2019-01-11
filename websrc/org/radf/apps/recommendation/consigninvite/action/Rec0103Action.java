/**
 * Rec0103Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.commons.entity.Cc20;
import org.radf.apps.commons.entity.Cc21;
import org.radf.apps.recommendation.consigninvite.dto.Rec0103DTO;
import org.radf.apps.recommendation.consigninvite.dto.Rec010404DTO;
import org.radf.apps.recommendation.consigninvite.facade.Rec0103Facade;
import org.radf.apps.recommendation.consigninvite.form.Rec0103Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 人员推荐
 */
public class Rec0103Action extends ActionLeafSupport {
	// 定义log
	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * 求职人员查询跳转<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据界面查询条件生成jsp页面<br>
     * 2、根据推荐所需要显示的字段定制分页标签属性</tt>
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
	public ActionForward entryQuery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String menuId = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuId);
		String forward = menuId;
		Rec0103Form fm = new Rec0103Form();
		ClassHelper.copyProperties(fm, actionForm);
		return mapping.findForward(forward);
	}

	/**
	 * 求职人员查询页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据aac001字段关联ac01(人员基本信息表)</tt>
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
	 * @return 求职人员列表信息
	 * @throws 求职人员查询出错
	 */
	public ActionForward SearchPersonFR(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		Rec0103DTO input = new Rec0103DTO();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, input);
			if (form.getNnn001() == null || "".equals(form.getNnn001())) {
				input.setNnn001(Short.valueOf("0"));

			}
			if (form.getNnn002() == null || "".equals(form.getNnn002())) {
				input.setNnn002(Short.valueOf("100"));

			}
			if (form.getA036ae() != null && !(form.getA036ae().equals(""))) {
				input.setA036ae(DateUtil.getStepDay(input.getA036ae(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/PersonRecommend.jsp";
			// 获取sql语句
			input.setFileKey("rec01_001");

			// 组合sql语句
			String hql = queryEnterprise(input);
			// 查询
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;

	}


	 /**
	 * 跳转到岗位查询页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据界面查询条件生成jsp页面<br>
     * 2、根据推荐的岗位所需要显示的字段定制分页标签属性</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 岗位查询页面
	 * @throws 岗位查询出错
	 */
	public ActionForward PersonRecommendFR(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		// String menuId = request.getParameter("menuId");
		String forward = "personrecommendresult";
		request.getSession().setAttribute("acc200", form.getAcc200());
		request.getSession().setAttribute("acc210", form.getAcc210());
		request.getSession().setAttribute("aac001", form.getAac001());
		return mapping.findForward(forward);

	}

	/**
	 * 匹配推荐<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息<br>
     * 3、如果求职信息有效,并且岗位信息也同时有效,则推荐并打印推荐信</tt>
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 匹配推荐页面
	 * @throws 匹配推荐出错
	 */
	public ActionForward Recommend(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		Rec0103DTO input = new Rec0103DTO();
		Rec0103DTO output = null;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = mapping.findForward("recommend");
		Rec0103Facade facade = (Rec0103Facade) getService("Rec0103Facade");
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		// ActionForward af = new ActionForward();
		String forward = "recommend"; // 查看
		try {
			ClassHelper.copyProperties(form, input);
			if (menuId.equals("searchperson") || menuId.equals("searchpost")
					|| menuId.equals("personrecommend")
					|| menuId.equals("postrecommend")
					|| menuId.equals("queryPersonApply")) {
				input.setBcb991("0");
			}

			// 传入根据主键查询SQL语句

			// 如果form为空，从session中取得，放到dto
			if (form.getAcc200() == null) {
				String acc200 = "";
				acc200 = (String) request.getSession().getAttribute("acc200");
				input.setAcc200(acc200);
			}
			if (form.getAcc210() == null) {
				String acc210 = "";
				acc210 = (String) request.getSession().getAttribute("acc210");
				input.setAcc210(acc210);
			}
			if (form.getAac001() == null) {
				String aac001 = "";
				aac001 = (String) request.getSession().getAttribute("aac001");
				input.setAac001(aac001);
			}
			if (form.getAcb200() == null) {
				String acb200 = "";
				acb200 = (String) request.getSession().getAttribute("acb200");
				input.setAcb200(acb200);
			}
			if (form.getAcb210() == null) {
				String acb210 = "";
				acb210 = (String) request.getSession().getAttribute("acb210");
				input.setAcb210(acb210);
			}
			if (form.getAab001() == null) {
				String aab001 = "";
				aab001 = (String) request.getSession().getAttribute("aab001");
				input.setAcb210(aab001);
			}
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAae036(DateUtil.getSystemCurrentTime());
			input.setAcc222(dto1.getAab300());
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.Recommend(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				output = (Rec0103DTO) map.get("output");
				output.setAae011(OptionDictSupport.getCodeName(request,
						"AAE011", output.getAae011()));
				output.setAae017(OptionDictSupport.getCodeName(request,
						"AAE017", output.getAae017()));
				request.getSession().setAttribute("output", output);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(forward);
	}

	/**
	 * 单位选人<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息<br>
     * 3、如果求职信息有效,并且岗位信息也同时有效,则确认该单位选择了此人<br>
     * 4、页面要支持多选,即同时选择多人<br>
     * 5、被选中人在推荐表cc22中显示为未联系状态</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 单位选人页面
	 * @throws 单位选人出错
	 */
	public ActionForward dwxr(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 定义变量
		Collection collection = null;
		collection = (Collection) TypeCast.getEntities(new SubmitDataMap(
				request), Rec010404DTO.class);
		Rec0103Form form = (Rec0103Form) actionForm;
		Rec0103DTO input = new Rec0103DTO();
		Rec0103DTO output = null;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = mapping.findForward("recommend");
		Rec0103Facade facade = (Rec0103Facade) getService("Rec0103Facade");
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		// ActionForward af = new ActionForward();
		String forward = "recommend"; // 查看
		try {
			ClassHelper.copyProperties(form, input);
			if (menuId.equals("searchperson") || menuId.equals("searchpost")
					|| menuId.equals("personrecommend")
					|| menuId.equals("postrecommend")
					|| menuId.equals("queryPersonApply")) {
				input.setBcb991("0");
			}

			// 传入根据主键查询SQL语句

			// 如果form为空，从session中取得，放到dto
			if (form.getAcc200() == null) {
				String acc200 = "";
				acc200 = (String) request.getSession().getAttribute("acc200");
				input.setAcc200(acc200);
			}
			if (form.getAcc210() == null) {
				String acc210 = "";
				acc210 = (String) request.getSession().getAttribute("acc210");
				input.setAcc210(acc210);
			}
			if (form.getAac001() == null) {
				String aac001 = "";
				aac001 = (String) request.getSession().getAttribute("aac001");
				input.setAac001(aac001);
			}
			if (form.getAcb200() == null) {
				String acb200 = "";
				acb200 = (String) request.getSession().getAttribute("acb200");
				input.setAcb200(acb200);
			}
			if (form.getAcb210() == null) {
				String acb210 = "";
				acb210 = (String) request.getSession().getAttribute("acb210");
				input.setAcb210(acb210);
			}
			if (form.getAab001() == null) {
				String aab001 = "";
				aab001 = (String) request.getSession().getAttribute("aab001");
				input.setAcb210(aab001);
			}
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAae036(DateUtil.getSystemCurrentTime());
			input.setAcc222(dto1.getAab300());
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("input", input);
			mapRequest.put("collection", collection);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.dwxr(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				super.saveSuccessfulMsg(request, "单位选人成功！");
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "recommendation", "2");
	}

	/** 
	 * 个人推荐空岗查询<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 个人推荐空岗查询页面
	 * @throws 个人推荐空岗查询出错
	 */
	public ActionForward PersonRecommendSPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		Rec0103DTO input = new Rec0103DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, input);

			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/PersonRecommendResult.jsp";
			// 获取sql语句

			input.setFileKey("rec01_003");

			input.setAca111(StringUtil.dealZero(input.getAca111()));
			if ("".equals(input.getAac011()) || input.getAac011() == null)
				input.setAac011("10");
			// 组合sql语句
			String hql = queryEnterprise(input);
			// 查询
			af = super.init(request, forward, hql, "2");
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**	 * 
	 * 单位选人岗位查询<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 单位选人岗位查询页面
	 * @throws 单位选人岗位查询出错
	 */
	public ActionForward SearchPostFR(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		Rec0103DTO input = new Rec0103DTO();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据s
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/PostRecommend.jsp";
			// 获取sql语句

			input.setFileKey("rec01_003");
			input.setAca111(StringUtil.dealZero(input.getAca111()));
			if ("".equals(input.getAac011()) || input.getAac011() == null)
				input.setAac011("10");
			// 组合sql语句
			String hql = queryEnterprise(input);
			// 查询
			af = super.init(request, forward, hql, "1");
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;

	}

	
	 /**
	 * 单位选人求职信息查询跳转<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据界面查询条件生成jsp页面<br>
     * 2、根据推荐所需要显示的字段定制分页标签属性</tt>
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 单位选人求职信息查询跳转页面
	 * @throws 单位选人求职信息查询跳转出错
	 */
	public ActionForward PostRecommendFR(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		ActionForward forward = new ActionForward();

		request.getSession().setAttribute("acb200", form.getAcb200());
		request.getSession().setAttribute("acb210", form.getAcb210());
		request.getSession().setAttribute("aab001", form.getAab001());
		return mapping.findForward("ppostRecommendResult");
	}

	
	 /**
	 * 单位选人求职信息查询<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据aac001字段关联ac01(人员基本信息表)</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 单位选人求职信息查询页面
	 * @throws 单位选人求职信息查询出错
	 */
	public ActionForward PostRecommendSPerson(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		Rec0103DTO input = new Rec0103DTO();
		ClassHelper.copyProperties(form, input);
		if (form.getNnn001() == null || "".equals(form.getNnn001())) {
			input.setNnn001(Short.valueOf("0"));

		}
		if (form.getNnn002() == null || "".equals(form.getNnn002())) {
			input.setNnn002(Short.valueOf("100"));

		}
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/PostRecommendResult.jsp";
			// 获取sql语句
			input.setFileKey("rec01_001");
			input.setAca111(StringUtil.dealZero(input.getAca111()));
			String hql = queryEnterprise(input);
			// 查询
			af = super.init(request, forward, hql, "2");
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;

	}

	/**	 * 
	 * 岗位和求职信息的匹配情况<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据求职表和岗位表中的要求显示是否匹配<br>
     * 2、比如性别:岗位务要求,求职人员为女,则显示匹配</tt>
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 岗位和求职信息的匹配情况页面
	 * @throws 岗位和求职信息的匹配情况出错
	 */
	public ActionForward viewppqk(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 定义变量
		Rec0103Form form = (Rec0103Form) actionForm;
		Rec0103DTO input = new Rec0103DTO();
		Cb21 cb21 = new Cb21();
		Cc20 cc20 = new Cc20();
		Ac01 ac01 = new Ac01();
		Ab01 ab01 = new Ab01();
		String menuId = request.getSession().getAttribute("menuId").toString();

		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		// ActionForward af = new ActionForward();
		String forward = "viewppqk"; // 查看
		try {
			ClassHelper.copyProperties(form, input);
			if (menuId.equals("searchperson") || menuId.equals("searchpost")
					|| menuId.equals("personrecommend")
					|| menuId.equals("postrecommend")
					|| menuId.equals("queryPersonApply")) {
				input.setBcb991("0");
			}

			// 传入根据主键查询SQL语句

			// 如果form为空，从session中取得，放到dto
			if (form.getAcc200() == null) {
				String acc200 = "";
				acc200 = (String) request.getSession().getAttribute("acc200");
				input.setAcc200(acc200);
			}
			if (form.getAcc210() == null) {
				String acc210 = "";
				acc210 = (String) request.getSession().getAttribute("acc210");
				input.setAcc210(acc210);
			}
			if (form.getAac001() == null) {
				String aac001 = "";
				aac001 = (String) request.getSession().getAttribute("aac001");
				input.setAac001(aac001);
			}
			if (form.getAcb200() == null) {
				String acb200 = "";
				acb200 = (String) request.getSession().getAttribute("acb200");
				input.setAcb200(acb200);
			}
			if (form.getAcb210() == null) {
				String acb210 = "";
				acb210 = (String) request.getSession().getAttribute("acb210");
				input.setAcb210(acb210);
			}
			if (form.getAab001() == null) {
				String aab001 = "";
				aab001 = (String) request.getSession().getAttribute("aab001");
				input.setAcb210(aab001);
			}

			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			input.setFileKey("cb21_select");
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), cb21);
				}
				request.getSession().setAttribute("cb21", cb21);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}

			input.setFileKey("cc20_select");
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), cc20);
				}
				request.getSession().setAttribute("cc20", cc20);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}
			input.setFileKey("rec01_002");
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				if (list != null) {
					Vector vec = new Vector();
					for (int i = 0; i < list.size(); i++) {
						Cc21 cc21 = new Cc21();
						ClassHelper.copyProperties(list.get(i), cc21);
						vec.add(cc21);
					}
					request.getSession().setAttribute("cc21vec", vec);
				}

			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}

			input.setFileKey("ac01_select");
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), ac01);
				}
				request.getSession().setAttribute("ac01", ac01);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}
			input.setFileKey("ab01_select");
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), ab01);
				}
				request.getSession().setAttribute("ab01", ab01);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward(forward);
	}
}