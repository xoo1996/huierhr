/**
 * EmployerAction.java 2008/03/27
 * 
 * Copyright (c) 2008 项目: Rapid Application Development Framework
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */

package org.radf.apps.basicinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.basicinfo.form.QueryEmployerForm;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 单位信息查询
 */
public class QueryEmployerAction extends ActionLeafSupport{
    
    /**
	 * 单位基本信息查询入口
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
	 */
    
    public ActionForward entryQueryEmp(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryEmployerForm empForm = (QueryEmployerForm) actionForm;
		if ("enterpriseWriteOff".equals(request.getParameter("menuId"))
				|| ("enterpriseQuery".equals(request.getParameter("menuId")))) {
			request.setAttribute("empState", "1");//单位注销：单位状态可修改
		} else {
			empForm.setAae119("1");//单位状态：登记在册
		}
		request.getSession().setAttribute("menuId",request.getParameter("menuId"));
		return mapping.findForward("enterpriseList");
	}

    /**
	 * 查询单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
	 */

    public ActionForward queryEnterprise(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String menuid = request.getParameter("menuId");
		request.getSession().setAttribute("mod","basicinfo");
		QueryEmployerForm empForm = (QueryEmployerForm) actionForm;
		Ab01 emp=new Ab01(); //单位实体
		ClassHelper.copyProperties(empForm,emp);
		emp.setFileKey("bas01_000");
        //获取SQL语句
        String hql = queryEnterprise(emp);
		ActionForward forward = super.init(request,"/basicinfo/enterpriseList.jsp", hql,"1");
		request.setAttribute("menuId",menuid);
        // 检查是否存在？
        if (null == request.getAttribute(GlobalNames.QUERY_DATA))
        {
            super.saveSuccessfulMsg(request, "没有查询到符合条件的记录！");
        }
		return forward;
	}
    /**
	 * 返回到基本信息列表页面
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
	 */
    public ActionForward back(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        return mapping.findForward("enterpriseList");
    }
}
