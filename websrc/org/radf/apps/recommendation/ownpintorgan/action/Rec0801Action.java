/**
 * Rec0801Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cb24;
import org.radf.apps.commons.entity.Cb25;
import org.radf.apps.recommendation.ownpintorgan.form.Rec0801Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 民办职业介绍机构年检
 */
public class Rec0801Action extends ActionLeafSupport {

	/**
	 * 进入民办职业介绍机构年检登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、初始化页面<br></tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 进入民办职业介绍机构年检登记页面
	 * @throws 进入民办职业介绍机构年检登记页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryOrgan".equalsIgnoreCase(menuId)) {
			forward = "queryOrgan";// 查询机构信息,以进行登记操作
		}
		if ("organManager".equalsIgnoreCase(menuId)) {
			forward = "organManager";// 查询机构信息,以进行机构维护
		}
		return mapping.findForward(forward);

	}

	/**
	 * 查询机构信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询民办机构，并列表显示</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询机构信息
	 * @throws 查询机构信息出错
	 */
	public ActionForward enterqueryorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0801Form form = (Rec0801Form) actionForm;
		Cb24 cb24 = new Cb24();
		ClassHelper.copyProperties(form, cb24);
		cb24.setFileKey("rec08_001");
		// 获取SQL语句
		String hql = queryEnterprise(cb24);
		ActionForward af = new ActionForward();
		af = super.init(request, "/recommendation/ownpintorgan/organlist.jsp",
				hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

	/**
	 * 查询机构年检信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询出年检信息<br>
     * 2、一年一条纪录</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询机构年检信息
	 * @throws 查询机构年检信息出错
	 */
	public ActionForward organManager(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0801Form form = (Rec0801Form) actionForm;
		Cb25 cb25 = new Cb25();
		ClassHelper.copyProperties(form, cb25);
		cb25.setFileKey("rec08_004");
		// 获取SQL语句
		String hql = queryEnterprise(cb25);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/ownpintorgan/organyearchecklist.jsp", hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

}
