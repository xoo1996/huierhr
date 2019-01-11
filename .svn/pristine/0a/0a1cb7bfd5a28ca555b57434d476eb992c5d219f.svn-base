/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */

package org.radf.apps.cfgmgmt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Tblitsmci;
import org.radf.apps.cfgmgmt.facade.CIFacade;
import org.radf.apps.cfgmgmt.form.CIForm;
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
import org.radf.plat.util.global.GlobalNames;
/**
 * 人员信息管理
 */
public class CIOperAction extends ActionLeafSupport {
 
    /**
     * 配置项删除<br> 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	信息列表
     * @throws	删除出错
     */
    public ActionForward deleteCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception {
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
        Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();           
            mapRequest.put("beo", tblitsmci);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.deleteCI(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "删除配置项成功!");
                FindLog.insertLog(request,ciid,"删除配置项信息");           
            } else {
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        //下一个页面，还是配置项查询修改页面
        fm.setCiid(null);
        fm.setCiname(null);
        fm.setCitype(null);
        return mapping.findForward("modifyci");
    }
       
    /**
     * 显示某配置项所有信息<br>
     * 
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	配置项完整信息列表
     * @throws	出错
     */
    public ActionForward printCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
    	Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();            
            mapRequest.put("beo", tblitsmci);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.printCI(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
        		List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");//配置项信息
        		request.getSession().setAttribute("listci", listci);
        		ClassHelper.copyProperties(listci.get(0), fm);
            } else{
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }

        return mapping.findForward("ciall");

    }    
    
    /**
     * 修改某配置项所有信息<br>
     * 
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	修改配置项完整信息列表
     * @throws	出错
     */
    public ActionForward modifyCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
    	Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "主键为空，请重新查询");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();            
            mapRequest.put("beo", tblitsmci);
            // 将HashMap对象放入requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.printCI(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
        		List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");//配置项信息
        		request.getSession().setAttribute("listci", listci);
        		ClassHelper.copyProperties(listci.get(0), fm);
            } else{
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        
    	return mapping.findForward("alterci");
    	
    }
    
    /**
     * 
     * 保存新增后的配置项信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br> 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	新增配置项输入页面
     * @throws	查询出错
	 */
    public ActionForward saveNewCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception {
    	CIForm cf = (CIForm)form;
        Tblitsmci tblitsmci = new Tblitsmci();
        try {
            // 设定经办信息
            ClassHelper.copyProperties(cf, tblitsmci);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute("LoginDTO");
            tblitsmci.setCiadder(dto1.getBsc010());
            tblitsmci.setCiadddt(DateUtil.getSystemCurrentTime());
            // 获取服务接口
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap<String, Object> mapRequest = new HashMap<String, Object>();
            mapRequest.put("beo", tblitsmci);
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.saveCI(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "新增配置项信息成功!");
                //获得新增的配置项代码
                String ciid = (String)((HashMap) resEnv.getBody()).get("ciid");
                //获得从业务层返回的日志信息
                String workString = (String)((HashMap) resEnv.getBody()).get("workString");
                FindLog.insertLog(request,ciid,workString);
                //下一个页面，还是配置项登记页面
                return mapping.findForward("newci");
            }else {
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveSuccessfulMsg(request,aa[3]);
                return mapping.findForward("backspace");
            }
        }
        catch (Exception e){
            super.saveErrors(request, e); 
            return mapping.findForward("backspace");
        }

    }
    
    /**
     * 
     * 保存修改后的配置项信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	新增配置项输入页面
     * @throws	查询出错
	 */
    public ActionForward saveModifiedCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	CIForm cf = (CIForm)form;
        Tblitsmci tblitsmci = new Tblitsmci();
        try {
            // 设定经办信息
            ClassHelper.copyProperties(cf, tblitsmci);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute("LoginDTO");
            tblitsmci.setCiadder(dto1.getBsc010());
            tblitsmci.setCiadddt(DateUtil.getSystemCurrentTime());
            // 获取服务接口
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // 将Application对象放入HashMap
            HashMap<String, Object> mapRequest = new HashMap<String, Object>();
            mapRequest.put("beo", tblitsmci);
            requestEnvelop.setBody(mapRequest);
            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.modifyCI(requestEnvelop);
            // 处理返回结果
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "修改配置项信息成功!");
                //下一个页面，还是配置项查询修改页面
                cf.setCiid(null);
                cf.setCiname(null);
                cf.setCitype(null);
                return mapping.findForward("modifyci");
                //return queryCI(mapping, form, request, response);
            }else {
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveSuccessfulMsg(request,aa[3]);
                return mapping.findForward("backspace");
            }
        }
        catch (Exception e){
            super.saveErrors(request, e); 
            return mapping.findForward("backspace");
        }

    }
    
    /**
     * 查询配置项简略信息(支持模糊查询)
     * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	配置项信息列表页面
     * @throws	查询出错
     */
    public ActionForward queryCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception {

        CIForm ciform = (CIForm) form;
        Tblitsmci ci = new Tblitsmci();
        try {
            ClassHelper.copyProperties(ciform, ci);
            ci.setFileKey("cfg01_002");
            String hql = queryEnterprise(ci);
            super.init(req, "", hql);
            // 检查是否存在？
            if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
                String msg = "没有查询到符合条件的记录！";
                super.saveSuccessfulMsg(req, msg);
            }
        }catch (AppException ex) {
            this.saveErrors(req, ex);
        }catch (Exception e) {
            this.saveErrors(req, e);
        }
        if ("modifyCI".equalsIgnoreCase(req.getParameter("menuId"))) {
        	//是否是为修改而用的查询
            return mapping.findForward("modifyci");         	
        }
        return mapping.findForward("queryci"); 
    }
}