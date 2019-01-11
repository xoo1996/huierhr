/**
 * Rec03Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.query.dto.Rec0301DTO;
import org.radf.apps.recommendation.query.dto.Rec03DTO;
import org.radf.apps.recommendation.query.facade.Rec03Facade;
import org.radf.apps.recommendation.query.form.Rec03Form;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 职业综合信息查询
 */
public class Rec03Action extends ActionLeafSupport {

	public Rec03Action() {
		super();

	}

	/**
	 * 查询跳转
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
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuid = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuid);
		String forward = menuid;
		Rec03Form fm = new Rec03Form();
		ClassHelper.copyProperties(fm, actionForm);
		return mapping.findForward(forward);
	}

	/**
	 * 用于查询单位用工信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 用于查询单位用工信息
	 * @throws 用于查询单位用工信息出错
	 */
	public ActionForward queryPrint(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Rec03Form form = (Rec03Form) actionForm;
		request.getSession().setAttribute("Rec03Form", form);
		ActionForward af = new ActionForward();
		String menuId = request.getSession().getAttribute("menuId").toString();
		LogHelper log = new LogHelper(this.getClass());
		Rec03DTO dto = new Rec03DTO();
		log.log("张贴打印");
		try {

			ClassHelper.copyProperties(form, dto);
			if (form.getA043ae() != null && !(form.getA043ae().equals(""))) {
				dto.setA043ae(DateUtil.getStepDay(dto.getA043ae(), 1));
			}
			if (form.getA030ae() != null && !(form.getA030ae().equals(""))) {
				dto.setA030ae(DateUtil.getStepDay(dto.getA030ae(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/query/queryPrint.jsp";
			// 获取sql语句

			dto.setFileKey("rec03_001");

			// 组合sql语句
			String hql = queryEnterprise(dto);
			// 查询
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 查看单位用工信息用于张贴打印
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看单位用工信息用于张贴打印
	 * @throws 查看单位用工信息用于张贴打印出错
	 */
	public ActionForward viewPrintPost(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String postid[] = request.getParameterValues("acb210");// 要求查询指定记录
		String menuId = request.getSession().getAttribute("menuId").toString();
		String page = request.getParameter("page");
		Rec03Form form = (Rec03Form) actionForm;
		form = (Rec03Form) request.getSession().getAttribute("Rec03Form");
		Rec0301DTO rec0301dto = new Rec0301DTO();
		ActionForward af = new ActionForward();
		Rec03DTO dto = new Rec03DTO();

		try {
			String temp = "";
			if (postid != null) {
				for (int j = 0; j < postid.length; j++) {
					if (j == 0) {
						temp += " and cb21.acb210 in (";
					}

					if (j < postid.length - 1) {

						temp += "'" + postid[j] + "',";
					} else {

						temp += "'" + postid[j] + "') ";
					}
				}

			}
			// --------------查询张贴表的发布名称，报名起止时间－－－－－－－－－
			ClassHelper.copyProperties(form, dto);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());

			dto.setFileKey("rec03_002");
			// 获取接口
			Rec03Facade facade = (Rec03Facade) getService("Rec03Facade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			mapRequest.put("beo1", temp);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.queryEmployer(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				int i = list.size();

				if (i == 0) {
					super.saveErrors(request, new AppException(
							"没有符合条件的信息，请重新选择查询条件！"));
				}
				if (i == 1) {
					ClassHelper.copyProperties(list.get(0), rec0301dto);
					request.getSession().setAttribute("rec0301dto", rec0301dto);
				} else {
					rec0301dto = new Rec0301DTO();
					request.getSession().setAttribute("rec0301dto", rec0301dto);
				}
			}

			dto.setFileKey("rec03_005");
			// 获取接口
			facade = (Rec03Facade) getService("Rec03Facade");
			requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			// 将Application对象放入HashMap
			mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			mapRequest.put("beo1", temp);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.queryEmployer(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				int i = list.size();

				if (i == 0) {
					super.saveErrors(request, new AppException(
							"没有符合条件的信息，请重新选择查询条件！"));
					return mapping.findForward("backspace");
				}
				request.getSession().setAttribute("data", list);
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		if ("A4s".equals(page))
			return mapping.findForward("ztprintA4s");
		if ("A4h".equals(page))
			return mapping.findForward("ztprintA4h");
		if ("A3h".equals(page))
			return mapping.findForward("ztprintA3h");
		if ("A3s".equals(page))
			return mapping.findForward("ztprintA3s");
		return mapping.findForward("backspace");
	}

	/**
	 * 查询求职信息
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询求职信息
	 * @throws 查询求职信息出错
	 */
	public ActionForward queryPersonApply(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec03Form form = (Rec03Form) actionForm;
		Rec03DTO dto = new Rec03DTO();
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		ClassHelper.copyProperties(form, dto);
		if (form.getNnn001() == null || "".equals(form.getNnn001())) {
			dto.setNnn001(Short.valueOf("0"));

		}
		if (form.getNnn002() == null || "".equals(form.getNnn002())) {
			dto.setNnn002(Short.valueOf("100"));

		}
		StringBuffer hqlf = null;
		try {
			// 页面跳转
			String forward = "/recommendation/query/queryPersonApply.jsp";
			dto.setFileKey("rec03_003");
			String hql = super.queryEnterprise(dto);
			// 查询
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;

	}

	/**
	 * 查询推荐信息查询
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询推荐信息查询
	 * @throws 查询推荐信息查询出错
	 */
	public ActionForward queryRecommend1(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec03Form form = (Rec03Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();
		Rec03DTO dto = new Rec03DTO();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, dto);
			if (form.getA030ae() != null && !(form.getA030ae().equals(""))) {
				dto.setA030ae(DateUtil.getStepDay(dto.getA030ae(), 1));
			}
			if (form.getA031ae() != null && !(form.getA031ae().equals(""))) {
				dto.setA031ae(DateUtil.getStepDay(dto.getA031ae(), 1));
			}
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae017(dto1.getBsc001());
			// 页面跳转
			String forward = "/recommendation/query/queryRecommend.jsp";
			// 获取sql语句
			dto.setFileKey("rec03_004");
			StringUtil.dealZero(dto.getAca111());
			// 组合sql语句
			String hql = queryEnterprise(dto);
			// 查询
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 用人单位信息查询
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 用人单位信息查询
	 * @throws 用人单位信息查询出错
	 */
	public ActionForward queryEmployerRecommend(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		Rec03Form form = (Rec03Form) actionForm;
		String menuId = request.getSession().getAttribute("menuId").toString();
		ActionForward af = new ActionForward();

		Rec03DTO dto = new Rec03DTO();
		try {
			// 处理页面数据
			ClassHelper.copyProperties(form, dto);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			//dto.setAae017(dto1.getBsc001());//登记机构查询出来总是操作员所在的机构，故注释掉
			// 页面跳转
			String forward = "/recommendation/query/queryEmployerRecommend.jsp";
			// 获取sql语句
            if(dto.getAcb208().equalsIgnoreCase("0")){
			  dto.setFileKey("rec01_010");
            }else if(dto.getAcb208().equalsIgnoreCase("1")){
				dto.setFileKey("rec01_011");
            }else{
				dto.setFileKey("rec01_015");
            }
		
			Short s = new Short("200");
			// 组合sql语句
			if(dto.getAcb222().intValue()==0){
				dto.setAcb222(s);
			}
			String hql = queryEnterprise(dto);
			// 查询
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;

	}

}
