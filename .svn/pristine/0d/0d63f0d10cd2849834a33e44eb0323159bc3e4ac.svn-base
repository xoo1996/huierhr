/**
 * PersonOperAction.java 2008/03/27
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
import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.basicinfo.facade.PersonFacade;
import org.radf.apps.basicinfo.form.PersonForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
/**
 * 人员信息管理
 */
public class PersonOperAction extends ActionLeafSupport
{
     /**
     * 保存个人基本信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 1、根据菜单ID进行页面跳转<br><tt>  
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	个人基本信息页面
     * @throws	查询出错
	 */
    public ActionForward savePerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
        PersonForm pf = (PersonForm) form;
        Ac01 person = new Ac01();
        String menuId = request.getParameter("menuId");
        try
        {
            person.setAac006(null);
            checkPerson(pf);
            // 设定经办信息
            ClassHelper.copyProperties(pf, person);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute("LoginDTO");
			person.setAae011(dto1.getBsc010());
			person.setAae017(dto1.getBsc001());
            person.setAae036(DateUtil.getSystemCurrentTime());
            // 获取接口
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            // person.setFileKey("ac01_insert");
            mapRequest.put("person", person);
            mapRequest.put("menuid", menuId);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.savePerson(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                super.saveSuccessfulMsg(request, "保存成功!");
                String aac001 = (String)((HashMap) resEnv.getBody()).get("aac001");
                String workString = (String)((HashMap) resEnv.getBody()).get("workString");
                FindLog.insertLog(request,aac001,workString);
				if(menuId!=null&&menuId.equals("queryemployer")){
					return mapping.findForward("check");
				}else if(menuId!=null&&menuId.equals("certificatemapp")){
					return mapping.findForward("check1");
				}
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveSuccessfulMsg(request,aa[3]);
                return mapping.findForward("backspace");
            }
        }
        catch (Exception e)
        {
            super.saveErrors(request, e); 
            return mapping.findForward("backspace");
        }
        //return redirect(request, mapping);
		return go2Page(request,mapping,"basicinfo");
    }

    /**
     * 人员注销
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	人员基本信息页面
     * @throws	注销出错
     */
    public ActionForward writeOff(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
        String aac001 = request.getParameter("aac001");
        String menuid = request.getParameter("menuId");
        Ac01 person = new Ac01();
        if (null == aac001 || "".equalsIgnoreCase(aac001))
        {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        }
        else
        {
            person.setAac001(aac001);
            person.setAae100("0");
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            person.setFileKey("ac01_update");
            mapRequest.put("beo", person);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.writeOff(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                saveSuccessfulMsg(request, "注销成功");
                FindLog.insertLog(request,aac001,"注销人员基本信息");
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        return redirect(request, mapping);
    }

    /**
     * 重定向到个人查询的页面
     * 
     * @param	request	    客户端的HTTP请求连接
     * @param	mapping		表单映射器
     *      
     * @return	人员基本信息页面
     * @throws	个人查询出错
     */
    private ActionForward redirect(HttpServletRequest request,
            ActionMapping mapping)
    {
        String url = request.getParameter("url2");
        if (url != null)
        {
            url = url.replaceAll(";amp;", "&");
        }
        request.setAttribute("url", url);
        return mapping.findForward("redirect");
    }

    /**
     * 验证人员FORM信息是否合法
     * 
     * @param person
     * @throws AppException
     */
    public void checkPerson(PersonForm person) throws AppException
    {
        // 出生日期和当前日期比较
        if (null != person.getAac006()
                && person.getAac006()
                        .compareTo(DateUtil.getSystemCurrentTime("yyyy-MM-dd")) >= 0)
        {
            throw new AppException("系统提示：出生日期不能大于当前日期！");
        }
        // 参加工作日期和当前日期比较
        if ((null != person.getAac007())
                && (!"".equals(person.getAac007()))
                && (person.getAac007()
                        .compareTo(DateUtil.getSystemCurrentTime("yyyy-MM-dd")) > 0))
        {
            throw new AppException("系统提示：参加工作日期大于当前日期！");
        }
        // 出生日期和参加工作日期比较
        if ((null != person.getAac007()) && (!"".equals(person.getAac007()))
                && (person.getAac006().compareTo(person.getAac007()) > 0))
        {
            throw new AppException("系统提示：出生日期不能大于参加工作日期！");
        }
    }
    
    /**
     * 人员删除<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 1、调用存储过程判断该人员是否有业务信息，如果有业务信息则不能删除<br><tt>   
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	人员信息列表
     * @throws	删除出错
     */
    public ActionForward delPerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
    	PersonForm fm = (PersonForm) form;
    	String aac001 = request.getParameter("aac001");
         Ac01 ac01 = new Ac01();
        if (null == aac001 || "".equalsIgnoreCase(aac001))
        {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        }
        else
        {

            ClassHelper.copyProperties(fm, ac01);
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            
            mapRequest.put("beo", ac01);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.delPerson(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                saveSuccessfulMsg(request, "删除成功");
                FindLog.insertLog(request,aac001,"删除人员基本信息");
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        return go2Page(request,mapping,"basicinfo");
        //return redirect(request, mapping);
    }
    
    /**
     * 打印人员所有信息<br>
     * 
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 1、查询出人员基本信息、人员合同信息、失业就业信息、失业金审核信息、失业金发放信息、农合工补肋信息、个人参保、失业保险个人应缴实缴明细<br><tt>   
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	人员信息列表
     * @throws	删除出错
     */
    public ActionForward printPerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
    	PersonForm fm = (PersonForm) form;
    	String aac001 = request.getParameter("aac001");
         Ac01 ac01 = new Ac01();
        if (null == aac001 || "".equalsIgnoreCase(aac001))
        {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        }
        else
        {

            ClassHelper.copyProperties(fm, ac01);
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap mapRequest = new HashMap();
            
            mapRequest.put("beo", ac01);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.printPerson(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
        		List listac01 = (ArrayList) ((HashMap) resEnv.getBody()).get("beoac01");//人员基本信息
        		request.getSession().setAttribute("listac01", listac01);
        		ClassHelper.copyProperties(listac01.get(0), fm);
        		fm.setSsjqnm(TypeCast.getidtoname(
        				fm.getCce001(),
                        "CCE001",
                        servlet));
            	
            	List listcc59 = (ArrayList) ((HashMap) resEnv.getBody()).get("beocc59");//人员合同信息

        		request.getSession().setAttribute("listcc59", listcc59);
        		
        		List listcc02 = (ArrayList) ((HashMap) resEnv.getBody()).get("beocc02");//失业信息

        		request.getSession().setAttribute("listcc02", listcc02);
        		
        		List listcc03 = (ArrayList) ((HashMap) resEnv.getBody()).get("beocc03");//就业信息

        		request.getSession().setAttribute("listcc03", listcc03);
        		
        		List listjc10 = (ArrayList) ((HashMap) resEnv.getBody()).get("beojc10");//失业金审核信息

        		request.getSession().setAttribute("listjc10", listjc10);
        		
        		List listjc22 = (ArrayList) ((HashMap) resEnv.getBody()).get("beojc22");//失业金发放信息

        		request.getSession().setAttribute("listjc22", listjc22);
        		
        		List listjc40 = (ArrayList) ((HashMap) resEnv.getBody()).get("beojc40");//农合工补肋信息

        		request.getSession().setAttribute("listjc40", listjc40);
        		
        		List listac02 = (ArrayList) ((HashMap) resEnv.getBody()).get("beoac02");//个人参保

        		request.getSession().setAttribute("listac02", listac02);
        		
        		List listjc01 = (ArrayList) ((HashMap) resEnv.getBody()).get("beojc01");//失业保险个人应缴实缴明细

        		request.getSession().setAttribute("listjc01", listjc01);
						
				
				
				List listcc9a = (ArrayList) ((HashMap) resEnv.getBody()).get("beocc9a");// 灵活就业补贴
				
				request.getSession().setAttribute("listcc9a", listcc9a);
				
				List listcc9d = (ArrayList) ((HashMap) resEnv.getBody()).get("beocc9d");// 一次性再就业优惠补助
				
				request.getSession().setAttribute("listcc9d", listcc9d);
        		
        		List listrec = (ArrayList) ((HashMap) resEnv.getBody()).get("beorec");//职业介绍
        		request.getSession().setAttribute("listrec", listrec);
  
        		
            	
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        if (request.getParameter("mode")!=null){
        	return mapping.findForward("printperson");
        	
        }else{
        	return mapping.findForward("personall");
        }
        //return redirect(request, mapping);
    }
    
    
    
    
}