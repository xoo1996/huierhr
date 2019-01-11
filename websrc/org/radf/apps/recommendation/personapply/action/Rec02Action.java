/**
 * Rec02Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personapply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.personapply.dto.Rec02DTO;
import org.radf.apps.recommendation.personapply.form.Rec02Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 求职登记查询
 */
public class Rec02Action extends ActionLeafSupport {

	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * 进入个人求职登记页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据menuid跳转页面</br>
     * 2、并清空对应的form信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 进入个人求职登记页面
	 * @throws 进入个人求职登记页面出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		req.getSession().setAttribute("menuId", menuId);
		String forward = "queryPersonApply";
		Rec02Form fm = new Rec02Form();
		ClassHelper.copyProperties(fm, form);
		return mapping.findForward(forward);
	}

	/**
	 * 查询个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查看当前有效的求职信息</tt>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询个人求职信息
	 * @throws 查询个人求职信息出错
	 */
	public ActionForward searchPersonApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Rec02Form sf = (Rec02Form) form;
		String aac002 = sf.getAac002();
		String hql = null;
		ActionForward af = new ActionForward();
		Rec02DTO dto = new Rec02DTO();
		String forward = "/recommendation/personapply/QueryPersonApply.jsp";
		try {
			ClassHelper.copyProperties(sf, dto);
			dto.setFileKey("rec02_001");
			hql = queryEnterprise(dto);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				if ((null != aac002) && (!"".equals(aac002))) {
					sf.setAac005("01");// 设置民族默认项为汉族
					sf.setAac006(DateUtil.getBirtday(aac002));
					sf.setAac004(DateUtil.getGender(aac002));
				}
				req.getSession().setAttribute("Rec0201Form", sf);
				af = new ActionForward("/personapply/check.jsp");
			}
		} catch (Exception e) {
			saveErrors(req, e);
		}
		return af;
	}

}