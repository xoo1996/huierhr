/**
 * Rec04Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employinvite.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.recommendation.employinvite.form.Rec04Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 委托招聘信息管理
 */
public class Rec04Action extends ActionLeafSupport {

	/**
	 * 查看人员信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询出求职信息和人员基本信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查看人员信息
	 * @throws 查看人员信息出错
	 */
	public ActionForward findPer(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Rec04Form fm = (Rec04Form) req.getSession().getAttribute("Rec04Form");
		String s = "enterpriseReg";
		StringBuffer sbb = new StringBuffer(
				"/basicinfo/employerAction.do?method=entryAddEmployer&menuId=");
		sbb.append(s).append("&aab003=").append(fm.getAab003()).append(
				"&aab002=").append(fm.getAab002());
		sbb.append("&aab004=").append(fm.getAab004()).append("&aab043=")
				.append(fm.getAab043()).append("&aab007=").append(
						fm.getAab007());
		// log.info("个人信息登记.");
		return mapping.findForward(sbb.toString());

	}

	/**
	 * 进入单位招聘登记页面<br>
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
	 * @return 进入单位招聘登记页面
	 * @throws 进入单位招聘登记页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		String forward = "queryEmployer";
		req.getSession().setAttribute("menuId", menuId);
		Rec04Form fm = new Rec04Form();
		ClassHelper.copyProperties(fm, form);
		return mapping.findForward(forward);
	}

	/**
	 * 查询单位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询出单位招聘信息和单位基本信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询单位信息
	 * @throws 查询单位信息出错
	 */
	public ActionForward queryEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 定义变量
		request.getSession().setAttribute("mod", "recommendation");
		Rec04Form form = (Rec04Form) actionForm;
		String forward = "queryEmployer";
		ActionForward af = new ActionForward();
		Ab01 ab01 = new Ab01();
		ab01.setAab004(form.getAab004());
		ab01.setAab003(form.getAab003());
		ClassHelper.copyProperties(form, ab01);
		ab01.setFileKey("rec04_001");
		// 调用接口进行查询
		try {
			String hql = queryEnterprise(ab01);
			af = super.init(request,
					"/recommendation/employinvite/QueryEmployer.jsp", hql);
			Collection data = (Collection) request
					.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null) {
				request.getSession().setAttribute("Rec04Form", form);
				forward = "check";
			}
		} catch (Exception e) {
			saveErrors(request, new Exception("查询失败！"));
		}
		return mapping.findForward(forward);
	}

}