/**
 * SkillAction.java 2008/03/27
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

import org.radf.apps.commons.entity.Cc0d;
import org.radf.apps.basicinfo.facade.SkillFacade;
import org.radf.apps.basicinfo.form.PersonForm;
import org.radf.apps.basicinfo.form.SkillForm;
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
 * 个人技能信息管理
 */
public class SkillAction extends ActionLeafSupport
{
    /**
     * 根据个人基本信息罗列个人基本技能
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @throws	跳转出错
     */
    public ActionForward findskill(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        PersonForm pf = (PersonForm) form;
        Cc0d cc0d = new Cc0d();
        cc0d.setAac001(pf.getAac001());
        cc0d.setFileKey("bas02_005");
        String hql = queryEnterprise(cc0d);
        String forward = "/basicinfo/skill.jsp";
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
     * 增加技能
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SkillForm sf = (SkillForm) form;
        // 清除缓存中的历史数据
        sf.setAcc0d0("");
        sf.setAae013("");
        sf.setAcc0d1("");
        sf.setAcc0d2("");
        sf.setAcc041("");
        sf.setAca111("");
        sf.setAca112("");
        sf.setAcc0d5("");
        return new ActionForward("/skillinfo.jsp");
    }

    /**
     * 修改技能
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @return ActionForward
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        SkillForm sf = (SkillForm) form;
        Cc0d cc0d = new Cc0d();
        ClassHelper.copyProperties(sf, cc0d);
        if ((null == cc0d.getAcc0d0()) || ("".equals(cc0d.getAcc0d0())))
        {
            super.saveErrors(req, new Exception("主键出错，查询详细信息失败！"));
        }
        else
        {
            // 提取详细数据
            SkillFacade facade = (SkillFacade) getService("SkillFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            cc0d.setFileKey("cc0d_select");
            mapRequest.put("beo", cc0d);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.findSkill(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
                ClassHelper.copyProperties(list.get(0), sf);
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveErrors(req, new AppException(aa[3]));
            }
        }
        return new ActionForward("/skillinfo.jsp");
    }

    /**
     * 返回当前
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @return ActionForward
     */
    public ActionForward back(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SkillForm sf = (SkillForm) form;
        Cc0d cc0d = new Cc0d();
        cc0d.setAac001(sf.getAac001());
        cc0d.setFileKey("bas02_005");
        String hql = queryEnterprise(cc0d);
        String forward = "/basicinfo/skill.jsp";
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
     * 保存技能信息
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SkillForm sf = (SkillForm) form;
        Cc0d cc0d = new Cc0d();

        ClassHelper.copyProperties(sf, cc0d);
        // 设定经办信息
		LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		cc0d.setAae011(dto1.getBsc010());
		cc0d.setAae017(dto1.getBsc001());
        cc0d.setAae036(DateUtil.getSystemCurrentTime());
        SkillFacade facade = (SkillFacade) getService("SkillFacade");
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        EventResponse returnValue = new EventResponse();
        // 将Application对象放入HashMap
        HashMap mapRequest = new HashMap();
        // person.setFileKey("ac01_insert");
        mapRequest.put("beo", cc0d);
        // 将HashMap对象放入requestEnvelop
        requestEnvelop.setBody(mapRequest);
        // 调用对应的Facade业务处理方法
        ResponseEnvelop resEnv = facade.saveSkill(requestEnvelop);
        // 处理返回结果
        returnValue = processRevt(resEnv);
        if (returnValue.isSucessFlag())
        {
            super.saveSuccessfulMsg(req, "保存成功!");
            String workString = (String)((HashMap) resEnv.getBody()).get("workString");
            FindLog.insertLog(req,cc0d.getAac001(),workString);
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
     * 删除技能信息
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本技能列表页面
     * @return ActionForward
     */
    public ActionForward clear(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SkillForm sf = (SkillForm) form;
        Cc0d cc0d = new Cc0d();
        ClassHelper.copyProperties(sf, cc0d);
        SkillFacade facade = (SkillFacade) getService("SkillFacade");
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        EventResponse returnValue = new EventResponse();
        // 将Application对象放入HashMap
        HashMap mapRequest = new HashMap();
        cc0d.setFileKey("cc0d_delete");
        mapRequest.put("beo", cc0d);
        // 将HashMap对象放入requestEnvelop
        requestEnvelop.setBody(mapRequest);
        // 调用对应的Facade业务处理方法
        ResponseEnvelop resEnv = facade.removeSkill(requestEnvelop);
        // 处理返回结果
        returnValue = processRevt(resEnv);
        if (returnValue.isSucessFlag())
        {
            super.saveSuccessfulMsg(req, "删除成功!");
            FindLog.insertLog(req,cc0d.getAac001(),"删除个人技能信息");
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