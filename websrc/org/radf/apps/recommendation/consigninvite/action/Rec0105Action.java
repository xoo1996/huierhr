/**
 * Rec0105Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.consigninvite.dto.Rec0103DTO;
import org.radf.apps.recommendation.consigninvite.dto.Rec0105DTO;
import org.radf.apps.recommendation.consigninvite.facade.Rec0105Facade;
import org.radf.apps.recommendation.consigninvite.form.Rec0105Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 推荐反馈信息的处理与查询
 */
public class Rec0105Action extends ActionLeafSupport {
	// 定义log
	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * 推荐信息查询处理页面跳转<br>
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
	 * @return 推荐信息查询处理页面跳转信息
	 * @throws 推荐信息查询处理页面跳转出错
	 */
	public ActionForward entryQuery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String menuId = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuId);
		String forward = menuId;
		Rec0105Form fm = new Rec0105Form();
		fm.setAcc223("0");
		ClassHelper.copyProperties(fm, actionForm);
		return mapping.findForward(forward);
	}

	/**
	 * 推荐信息查询<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据查询条件查询数据<br>
     * 2、查询cc22表中ACC223(推荐状态)未0,6,7三种的数据</tt>
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息查询信息
	 * @throws 推荐信息查询出错
	 */
	public ActionForward SRecommendFeedback(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		request.getSession().setAttribute("acc223", form.getAcc223());
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, input);
			if (form.getA014ce() != null && !(form.getA014ce().equals(""))) {
				input.setA014ce(DateUtil.getStepDay(input.getA014ce(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/RecommendFeedback.jsp";
			// 获取sql语句
			input.setFileKey("rec01_005");

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
	 * 推荐信息反馈成功<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将查询cc22表中ACC223(推荐状态)='0'的数据,并将其修改为'1'</tt> 
     * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息反馈成功信息
	 * @throws 推荐信息反馈成功出错
	 */
	public ActionForward RecommendFBSuccess(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = mapping.findForward("recommendfeedback");

		try {
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAcc226(dto1.getAab300());
			input.setAae036(DateUtil.getSystemCurrentTime());
			Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.RecommendFBSuccess(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功!");
				return go2Page(request, mapping, "recommendation", "1");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				af = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			af = mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 推荐信息反馈信息恢复推荐中<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将查询cc22表中ACC223(推荐状态)='1'或者'2'的数据,并将其修改为'0',即可以重新反馈</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息反馈信息恢复推荐中信息
	 * @throws 推荐信息反馈信息恢复推荐中出错
	 */
	public ActionForward RecommendFBhftj(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = mapping.findForward("recommendfeedback");

		try {
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAcc226(dto1.getAab300());
			input.setAae036(DateUtil.getSystemCurrentTime());
			Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.RecommendFBhftj(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "数据已恢复到推荐中!");
				return go2Page(request, mapping, "recommendation", "1");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				af = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			af = mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 推荐信息反馈失败<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将查询cc22表中ACC223(推荐状态)='0'的数据,并将其修改为'2',即本次推荐失败</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息反馈失败信息
	 * @throws 推荐信息反馈失败出错
	 */
	public ActionForward RecommendFBLost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = mapping.findForward("recommendfeedback");
		String rep = request.getParameter("respon");
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		try {
			ClassHelper.copyProperties(form, input);
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAcc226(dto1.getAab300());
			input.setAae036(DateUtil.getSystemCurrentTime());
			// 返回反馈的信息放入dto
			input.setAcc22e(rep);
			Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.RecommendFBLost(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功!");
				return go2Page(request, mapping, "recommendation", "1");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				af = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			af = mapping.findForward("backspace");
		}
		return af;

	}

	/**
	 * 推荐信息删除<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除本次推荐信息,用于错误推荐的,同时剪除求职信息的冻结</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息删除信息
	 * @throws 推荐信息删除出错
	 */
	public ActionForward RecommendFBdel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = mapping.findForward("recommendfeedback");
		String rep = request.getParameter("respon");
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		try {
			ClassHelper.copyProperties(form, input);
			input.setAae011(dto1.getBsc010());
			input.setAae017(dto1.getBsc001());
			input.setAcc226(dto1.getAab300());
			input.setAae036(DateUtil.getSystemCurrentTime());
			// 返回反馈的信息放入dto
			input.setAcc22e(rep);
			Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.RecommendFBdel(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除成功!");
				return go2Page(request, mapping, "recommendation", "1");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				af = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			af = mapping.findForward("backspace");
		}
		return af;

	}

	/**
	 * 推荐信息查看<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查看显示选中的推荐信息</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐信息查看信息
	 * @throws 推荐信息查看出错
	 */
	public ActionForward viewRec(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws Exception {

		String acc220 = request.getParameter("acc220");
		Rec0105DTO input = new Rec0105DTO();
		Rec0103DTO output = new Rec0103DTO();
		String forward = "recommendback";
		String menuId = request.getSession().getAttribute("menuId").toString();

		Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
		try {
			// 传入根据主键查询SQL语句
			input.setAcc220(acc220);
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("input", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.viewRec(requestEnvelop);
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
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

		return mapping.findForward(forward);
	}

	/**
	 * 推荐反馈详细信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查看显示选中的推荐信息和反馈信息</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐反馈详细信息
	 * @throws 推荐反馈详细信息出错
	 */
	public ActionForward viewrecommendHistory(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		Rec0105DTO output = new Rec0105DTO();
		Rec0105DTO input = new Rec0105DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		Rec0105Facade facade = (Rec0105Facade) getService("Rec0105Facade");
		// ActionForward af = new ActionForward();
		String forward = "viewrecommendhistory"; // 查看
		try {
			// 传入根据主键查询SQL语句
			ClassHelper.copyProperties(form, input);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 获取sql语句
			input.setFileKey("rec01_008");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", input);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade
					.findRecommendHistory(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {

				HashMap map = (HashMap) resEnv.getBody();

				List ls = (ArrayList) map.get("beo");

				if (ls != null && ls.size() > 0) {
					ClassHelper.copyProperties(ls.get(0), form);
				}
				request.getSession().setAttribute("Rec0105Form", form);
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

		return mapping.findForward(forward);
	}

	/**
	 * 推荐反馈历史信息查询<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询cc22表中的所有状态的信息,包括推荐中、推荐失败，推荐成功、为联系、联系成功、联系失败</tt> 
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 推荐反馈历史信息查询
	 * @throws 推荐反馈历史信息查询出错
	 */
	public ActionForward recommendHistory(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec0105Form form = (Rec0105Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		Rec0105DTO input = new Rec0105DTO();
		ActionForward af = new ActionForward();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, input);
			if (form.getA014ce() != null && !(form.getA014ce().equals(""))) {
				input.setA014ce(DateUtil.getStepDay(input.getA014ce(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			input.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/consigninvite/queryRecommendHistory.jsp";
			// 获取sql语句

			input.setFileKey("rec01_009");

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
}