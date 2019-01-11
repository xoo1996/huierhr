/**
 * ResumesAction.java 2008/03/27
 * 
 * Copyright (c) 2008 项目: Rapid Application Development Framework
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */

package org.radf.apps.basicinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cc0b;
import org.radf.apps.basicinfo.facade.ResumesFacade;
import org.radf.apps.basicinfo.form.PersonForm;
import org.radf.apps.basicinfo.form.ResumesForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
/**
 * 简历信息管理
 */
public class ResumesAction extends ActionLeafSupport
{
    public ActionForward findresume(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        PersonForm pf = (PersonForm) form;
        pf.setAac003(new String(pf.getAac003()));
        Cc0b cc0b = new Cc0b();
        cc0b.setAac001(pf.getAac001());
        cc0b.setFileKey("bas02_002");
        String hql = queryEnterprise(cc0b);
        String forward = "/basicinfo/resume.jsp";
        ActionForward af = new ActionForward(forward);
        try
        {
            af = super.init(req, forward, hql,"2");
        }
        catch (Exception e)
        {
            super.saveErrors(req, new Exception("查询失败！"));
        }
        return af;
    }

    /**
     * 增加简历
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	跳转至简历增加页面
     * @throws	跳转出错
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        ResumesForm rf = (ResumesForm) form;
        // 清除缓存中的历史数据
        rf.setAcc0b0("");
        rf.setAac045("");
        rf.setAcc0b4("");
        rf.setAae013("");
        rf.setAae030("");
        rf.setAae031("");
        return new ActionForward("/resumesinfo.jsp");
    }

    /**
     * 修改简历
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	跳转至简历修改页面
     * @throws	跳转出错
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        ResumesForm rf = (ResumesForm) form;
        Cc0b cc0b = new Cc0b();
        String id = rf.getAcc0b0();
        if ((null == id) || ("".equals(id)))
        {
            this.saveErrors(req, new Exception("主键信息出错，请重新选择！"));
        }
        else
        {
            cc0b.setAcc0b0(id);
            // 提取详细数据
            ResumesFacade facade = (ResumesFacade) getService("ResumesFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            cc0b.setFileKey("cc0b_select");
            mapRequest.put("beo", cc0b);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.findResumes(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
                ClassHelper.copyProperties(list.get(0), rf);
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveErrors(req, new AppException(aa[3]));
            }
        }
        return new ActionForward("/resumesinfo.jsp");
    }

    /**
     * 返回当前简历列表页面
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	简历列表页面
     * @throws	返回出错
     */
    public ActionForward back(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        ResumesForm rf = (ResumesForm) form;
        Cc0b cc0b = new Cc0b();
        cc0b.setAac001(rf.getAac001());
        cc0b.setFileKey("bas02_002");
        String hql = queryEnterprise(cc0b);
        String forward = "/basicinfo/resume.jsp";
        ActionForward af = new ActionForward(forward);
        try
        {
            af = super.init(req, forward, hql);
        }
        catch (Exception e)
        {
            super.saveErrors(req, new Exception("查询失败！"));
        }
        return af;
    }

    /**
     * 保存简历信息
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	简历列表页面
     * @throws	保存出错
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        ResumesForm rf = (ResumesForm) form;
        Cc0b cc0b = new Cc0b();

        ClassHelper.copyProperties(rf, cc0b);
        // 设定经办信息
		LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		cc0b.setAae011(dto1.getBsc010());
		cc0b.setAae017(dto1.getBsc001());

        cc0b.setAae036(DateUtil.getSystemCurrentTime());
        ResumesFacade facade = (ResumesFacade) getService("ResumesFacade");
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        EventResponse returnValue = new EventResponse();
        // 将Application对象放入HashMap
        HashMap mapRequest = new HashMap();
        // person.setFileKey("ac01_insert");
        mapRequest.put("beo", cc0b);
        // 将HashMap对象放入requestEnvelop
        requestEnvelop.setBody(mapRequest);
        // 调用对应的Facade业务处理方法
        ResponseEnvelop resEnv = facade.saveResumes(requestEnvelop);
        // 处理返回结果
        returnValue = processRevt(resEnv);
        if (returnValue.isSucessFlag())
        {
            super.saveSuccessfulMsg(req, "保存成功!");
            String workString = (String)((HashMap) resEnv.getBody()).get("workString");
            FindLog.insertLog(req,cc0b.getAac001(),workString);
        }
        else
        {
            String[] aa = StringUtil
                    .getAsStringArray(returnValue.getMsg(), "|");
            super.saveErrors(req, new AppException(aa[3]));
        }
        //return back(mapping, form, req, res);
		return go2Page(req,mapping,"basicinfo","2");
    }

    /**
     * 删除简历信息
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	简历列表页面
     * @throws	删除出错
     */
    public ActionForward clear(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        ResumesForm sf = (ResumesForm) form;
        Cc0b cc0b = new Cc0b();
        ClassHelper.copyProperties(sf, cc0b);
        ResumesFacade facade = (ResumesFacade) getService("ResumesFacade");
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        EventResponse returnValue = new EventResponse();
        // 将Application对象放入HashMap
        HashMap mapRequest = new HashMap();
        cc0b.setFileKey("cc0b_delete");
        mapRequest.put("beo", cc0b);
        // 将HashMap对象放入requestEnvelop
        requestEnvelop.setBody(mapRequest);
        // 调用对应的Facade业务处理方法
        ResponseEnvelop resEnv = facade.removeResumes(requestEnvelop);
        // 处理返回结果
        returnValue = processRevt(resEnv);
        if (returnValue.isSucessFlag())
        {
            super.saveSuccessfulMsg(req, "删除成功!");
            FindLog.insertLog(req,cc0b.getAac001(),"删除简历信息");
        }
        else
        {
            String[] aa = StringUtil
                    .getAsStringArray(returnValue.getMsg(), "|");
            super.saveErrors(req, new AppException(aa[3]));
        }
        //return back(mapping, form, req, res);
		return go2Page(req,mapping,"basicinfo","2");
    }

}