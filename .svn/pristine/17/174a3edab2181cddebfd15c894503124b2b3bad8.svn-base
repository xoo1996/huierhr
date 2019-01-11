/**
 * Rec0803Action.java 2008/03/27
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

import org.radf.apps.recommendation.ownpintorgan.dto.Rec0803DTO;
import org.radf.apps.recommendation.ownpintorgan.form.Rec0803Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 民办职业介绍机构日常投诉
 */
public class Rec0803Action extends ActionLeafSupport {

	/**
	 * 进入民办职业介绍机构日常检查投诉登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据民办机构的信息登记记录日常投诉信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 进入民办职业介绍机构日常检查投诉登记页面
	 * @throws 进入民办职业介绍机构日常检查投诉登记页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryOrgan".equalsIgnoreCase(menuId)) {
			forward = "queryOrgan";// 查询机构信息,以进行日常投诉登记操作
		}
		if ("complaintManager".equalsIgnoreCase(menuId)) {
			forward = "complaintManager";// 查询日常投诉信息,以进行维护
		}
		return mapping.findForward(forward);

	}

	/**
	 * 查询机构信息<br>
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
	 * @return 查询机构信息
	 * @throws 查询机构信息出错
	 */
	public ActionForward enterqueryorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0803Form form = (Rec0803Form) actionForm;
		Rec0803DTO cb26 = new Rec0803DTO();
		ClassHelper.copyProperties(form, cb26);
		cb26.setFileKey("rec08_001");
		// 获取SQL语句
		String hql = queryEnterprise(cb26);
		ActionForward af = new ActionForward();
		af = super.init(request, "/recommendation/ownpintorgan/organlist2.jsp",
				hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

	/**
	 * 查询机构年检信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、显示机构年检的详细信息</tt>
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
	public ActionForward organComplaint(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0803Form form = (Rec0803Form) actionForm;
		Rec0803DTO cb26 = new Rec0803DTO();
		ClassHelper.copyProperties(form, cb26);
		cb26.setFileKey("rec08_006");
		// 获取SQL语句
		String hql = queryEnterprise(cb26);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/ownpintorgan/organcomplaintlist.jsp", hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
		}
		return af;
	}

}
