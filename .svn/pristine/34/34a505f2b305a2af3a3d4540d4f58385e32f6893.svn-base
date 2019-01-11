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
 * 求职登记管理
 */
public class Rec0201Action extends ActionLeafSupport {

	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * 跳转登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据menuid显示页面</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 跳转登记页面
	 * @throws 跳转登记页面出错
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
	 * 进入求职意愿页面，查询出某一人员的相关信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据求职信息是否有效,查询数据</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 进入求职意愿页面，查询出某一人员的相关信息
	 * @throws 进入求职意愿页面，查询出某一人员的相关信息出错
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
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findPersonInfo(requestEnvelop);
			// 处理返回结果
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
	 * 查看个人求职信息初始化<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、显示当前有效的求职信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看个人求职信息初始化
	 * @throws 查看个人求职信息初始化出错
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
			// 获取接口
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc20);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.viewPersonApply(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list != null) {
					ClassHelper.copyProperties(list.get(0), form);
				} else {
					saveSuccessfulMsg(request, "该人员不存在求职信息！");
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
	 * 修改个人求职信息初始化<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改当前有效的求职信息</br>
     * 2、如果在推荐中不能修改</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改个人求职信息初始化
	 * @throws 修改个人求职信息初始化出错
	 */
	public ActionForward modPersonApply(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// log.info("InitPersonApplyAction:页面数据初始化");
		request.getSession().setAttribute("modPA",
				(String) request.getParameter("modPA"));
		Rec0201Form form = (Rec0201Form) actionForm;
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");

		try {
			// 获取接口
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			form.setAae017(dto1.getBsc001());
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findPersonApply(requestEnvelop);
			// 处理返回结果
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
	 * 删除个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除求职信息</br>
     * 2、如果在推荐中不能删除</tt>
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 删除个人求职信息初始化
	 * @throws 删除个人求职信息初始化出错
	 */
	public ActionForward delPersonApply(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Rec0201Form form = (Rec0201Form) actionForm;
		Cc20 cc20 = new Cc20();
		ClassHelper.copyProperties(form, cc20);

		try {
			// 获取接口
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", cc20);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delPersonApply(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除成功");
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
	 * 个人新加的基本信息的添加，个人求职信息的添加，个人求职意向的添加<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、保存基本信息和求职信息都不存在的个人求职</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 个人新加的基本信息的添加，个人求职信息的添加，个人求职意向的添加
	 * @throws 个人新加的基本信息的添加，个人求职信息的添加，个人求职意向的添加出错
	 */
	public ActionForward savePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// log.info("保存数据...");\
		String menuId = req.getSession().getAttribute("menuId").toString();
		String forward = "modiperson";
		Rec0201Form form1 = (Rec0201Form) form;
		Rec0201DTO dto = new Rec0201DTO();

		try {
			// 设定经办信息
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			form1.setAae011(dto1.getBsc010());
			form1.setAae017(dto1.getBsc001());
			ClassHelper.copyProperties(form1, dto);
			dto.setAae036(DateUtil.getSystemCurrentTime());
			// 调用接口
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			/* mapRequest.put("type", type); */
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.savePerson(requestEnvelop);
			// 处理返回结果
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
				super.saveSuccessfulMsg(req, "个人求职信息新增成功！");
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
	 * 修改个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改当前有效的求职信息</br>
     * 2、如果在推荐中不能修改</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改个人求职信息
	 * @throws 修改个人求职信息出错
	 */
	public ActionForward modPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// log.info("保存数据...");
		String menuId = req.getSession().getAttribute("menuId").toString();
		Rec0201Form form1 = (Rec0201Form) form;
		Rec0201DTO dto = new Rec0201DTO();
		String forward = "modiperson2";

		try {
			ClassHelper.copyProperties(form1, dto);
			// 设定经办信息
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			dto.setAae011(dto1.getBsc010());
			dto.setAae017(dto1.getBsc001());
			dto.setAae036(DateUtil.getSystemCurrentTime());
			// 调用接口
			Rec02Facade facade = (Rec02Facade) getService("Rec02Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modPerson(requestEnvelop);
			// 处理返回结果
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
				saveSuccessfulMsg(req, "个人求职信息保存成功！");
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
	 * 新增个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、如果当前有有效的求职信息,不能新增</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 修改个人求职信息
	 * @throws 修改个人求职信息出错
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
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", form);
			// mapRequest.put("type", type);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modPersonApply(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);

			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功");
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