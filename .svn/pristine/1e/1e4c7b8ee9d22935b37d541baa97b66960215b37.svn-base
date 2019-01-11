/**
 * Rec06Action.java 2008/04/03
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.recommendation.statistic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Rt99;
import org.radf.apps.commons.entity.Rt98;
import org.radf.apps.recommendation.statistic.facade.Rec06Facade;
import org.radf.apps.recommendation.statistic.dto.Rec06DTO;
import org.radf.apps.recommendation.statistic.form.Rec06Form;
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
 * 职业介绍工作情况年报
 */
public class Rec06Action extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());
     
	 
	     /**
		 * 查询职业介绍工作情况年报
	     * 
	     * @param	mapping		表单映射器
	     * @param	form		表单数据实体
	     * @param	req		    客户端的HTTP请求连接
	     * @param	res     	客户端的HTTP返回连接
	     * 
	     * @return	职业介绍工作情况年报信息列表
	     * @throws	查询出错
		 */
	    public ActionForward query(ActionMapping mapping, ActionForm form,
	            HttpServletRequest req, HttpServletResponse res) throws Exception
	    {	       
	        String forward = "/recommendation/statistic/condition.jsp";
			Rec06Form cf = (Rec06Form) form;
			Rec06DTO dto = new Rec06DTO();
	        ActionForward af = new ActionForward(forward);
	        try
	        {
	            
	            dto.setBrt996(cf.getYear());
	            dto.setFileKey("rec06_001");
	            String hql = queryEnterprise(dto);
	            af = super.init(req, forward, hql,"1");
	            // 检查是否存在？
	            if (null == req.getAttribute(GlobalNames.QUERY_DATA))
	            {
	                String msg = "没有查询到符合条件的记录！";
	                super.saveSuccessfulMsg(req, msg);
	            }
	        }
	        catch (AppException ex)
	        {
	            this.saveErrors(req, ex);
	        }
	        catch (Exception e)
	        {
	            this.saveErrors(req, e);
	        }
	        return af;
	    }
	    
		/**
		 * 进入职业介绍工作情况页面
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
			String forward="";
			if(menuId.equals("up")){
			  forward = "viewreport";
			}
			if(menuId.equals("modify")){
				  forward = "modifyreport";
			}

			return mapping.findForward(forward);
		}
	    
		/**
		 * 生成报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	报表信息列表页面
	     * @throws	生成出错
		 */
		public ActionForward born(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//经办人
				dto.setAae017(dto1.getBsc001());//经办机构
				dto.setCce001(fm.getCce001());//统计机构
				dto.setAae036(DateUtil.getSystemCurrentTime());//经办时间
				dto.setBrt996(fm.getYear());//统计年度
				dto.setBsc006(fm.getBsc006());//虚拟机构
				// 将Application对象放入HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// 获取接口
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.born(requestEnvelop);
				//处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "报表生成成功!");
			   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			   		request.getSession().setAttribute("listrt99", list);
			   		if(list==null){
			   			super.saveSuccessfulMsg(request, "报表尚未生成！");
			   		}
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
							"|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
			} catch (Exception e) {
				super.saveErrors(request, e);
				return mapping.findForward("backspace");
			}
			return mapping.findForward("viewreport");
			//return go2Page(request,mapping,"recommendation", "1");
		}
		/**
		 * 汇总报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	报表信息列表页面
	     * @throws	生成出错
		 */
		public ActionForward born_hz(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//经办人
				dto.setAae017(dto1.getBsc001());//经办机构
				dto.setAae019(dto1.getBsc008());//经办科室
				dto.setAae036(DateUtil.getSystemCurrentTime());//经办时间
				dto.setBrt996(fm.getYear());//统计年度
				dto.setCce001(fm.getCce001());//统计机构
				// 将Application对象放入HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// 获取接口
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.born_hz(requestEnvelop);
				//处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "报表汇总成功!");
			   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			   		request.getSession().setAttribute("listrt99", list);
			   		if(list==null){
			   			super.saveSuccessfulMsg(request, "报表尚未生成！");
			   		}
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
							"|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("viewreport");
					//return mapping.findForward("backspace");
				}
			} catch (Exception e) {
				super.saveErrors(request, e);
				return mapping.findForward("backspace");
			}
			return mapping.findForward("viewreport");
		}
		
		/**
		 * 显示报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	查询出错
		 */
		public ActionForward initUpdate(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
		Rec06Form fm = (Rec06Form) actionForm;
		Rec06DTO dto = new Rec06DTO();
		String mode=request.getParameter("mode");
		if(mode!=null){
			String brt996=request.getParameter("brt996");
			fm.setBrt996(brt996);
		}
		fm.setBrt996(fm.getYear());//统计年份
	   	//Rt99 rt99 = new Rt99();
	   	//Lab01DTO dto = new Lab01DTO();	    	
	   	ClassHelper.copyProperties(fm, dto);
	   	LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
		"LoginDTO");
	   	dto.setAae017(dto1.getBsc001());//经办机构
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.viewReport(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "报表尚未生成！");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("viewreport");

	   }
		
		/**
		 * 打印报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	查询出错
		 */
		public ActionForward printRt99(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
//		Rec06Form fm = (Rec06Form) actionForm;
		Rec06DTO dto = new Rec06DTO();
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("brt996");
		dto.setCce001(cce001);
		dto.setBrt996(brt996);
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.viewReport(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "报表尚未生成！");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("printreport");

	   }
		
		/**
		 * 打印报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	查询出错
		 */
		public ActionForward printRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
//		Rec06Form fm = (Rec06Form) actionForm;
		Rt98 dto = new Rt98();
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("brt996");
		dto.setCce001(cce001);
		dto.setBrt996(brt996);
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.viewRt98(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "报表尚未生成！");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("printreport");

	   }
		
		/**
		 * 显示上报报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	查询出错
		 */
		public ActionForward viewRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
		Rec06Form fm = (Rec06Form) actionForm;
        //判断是否有修改权限
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("year");
		Rec06DTO dto = new Rec06DTO();
		fm.setBrt996(fm.getYear());
	   	Rt98 rt98 = new Rt98();
	   	//Lab01DTO dto = new Lab01DTO();	    	
	   	ClassHelper.copyProperties(fm, rt98);
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", rt98);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.viewRt98(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		String exist = (String) ((HashMap) resEnv.getBody()).get("exist");
	   		request.getSession().setAttribute("listrt98", list);
	   		request.getSession().setAttribute("exist", exist);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "报表尚未上报！");
	   		}
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("modifyreport");

	   }
		
		/**
		 * 保存报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	保存出错
		 */
		public ActionForward save(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {

		String brt999[]=request.getParameterValues("brt999");
		String brt001[]=request.getParameterValues("brt001");
		String brt002[]=request.getParameterValues("brt002");
		String brt003[]=request.getParameterValues("brt003");
		String brt004[]=request.getParameterValues("brt004");
		String brt005[]=request.getParameterValues("brt005");
		String brt006[]=request.getParameterValues("brt006");
		String brt007[]=request.getParameterValues("brt007");
		String brt008[]=request.getParameterValues("brt008");
		String brt009[]=request.getParameterValues("brt009");
		String brt010[]=request.getParameterValues("brt010");
		String brt011[]=request.getParameterValues("brt011");
		String brt012[]=request.getParameterValues("brt012");
		String brt013[]=request.getParameterValues("brt013");
		String brt014[]=request.getParameterValues("brt014");
		Vector v99=new Vector();
		for(int i=0;i<brt999.length;i++){
			Rt99 rt99 = new Rt99();
			rt99.setBrt001(brt001[i]);
			rt99.setBrt002(brt002[i]);
			rt99.setBrt003(brt003[i]);
			rt99.setBrt004(brt004[i]);
			rt99.setBrt005(brt005[i]);
			rt99.setBrt006(brt006[i]);
			rt99.setBrt007(brt007[i]);
			rt99.setBrt008(brt008[i]);
			rt99.setBrt009(brt009[i]);
			rt99.setBrt010(brt010[i]);
			rt99.setBrt011(brt011[i]);
			rt99.setBrt012(brt012[i]);
			rt99.setBrt013(brt013[i]);
			rt99.setBrt014(brt014[i]);
			rt99.setBrt999(brt999[i]);
			rt99.setFileKey("rt99_update");
			v99.add(rt99);
		}
		
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", v99);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.saveReport(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		super.saveSuccessfulMsg(request, "保存成功!");
	   		
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
	   	return mapping.findForward("viewreport");
	    //return initUpdate(mapping,actionForm,request,response);
	   	//return go2Page(request,mapping,"recommendation", "1");
	   }
		
		/**
		 * 保存报表信息
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表信息
	     * @throws	保存出错
		 */
		public ActionForward saveRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
        //判断是否有修改权限
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("brt996");
		String brt999[]=request.getParameterValues("brt999");
		String brt001[]=request.getParameterValues("brt001");
		String brt002[]=request.getParameterValues("brt002");
		String brt003[]=request.getParameterValues("brt003");
		String brt004[]=request.getParameterValues("brt004");
		String brt005[]=request.getParameterValues("brt005");
		String brt006[]=request.getParameterValues("brt006");
		String brt007[]=request.getParameterValues("brt007");
		String brt008[]=request.getParameterValues("brt008");
		String brt009[]=request.getParameterValues("brt009");
		String brt010[]=request.getParameterValues("brt010");
		String brt011[]=request.getParameterValues("brt011");
		String brt012[]=request.getParameterValues("brt012");
		String brt013[]=request.getParameterValues("brt013");
		String brt014[]=request.getParameterValues("brt014");
		Vector v98=new Vector();
		for(int i=0;i<brt999.length;i++){
			Rt98 rt98 = new Rt98();
			rt98.setBrt001(brt001[i]);
			rt98.setBrt002(brt002[i]);
			rt98.setBrt003(brt003[i]);
			rt98.setBrt004(brt004[i]);
			rt98.setBrt005(brt005[i]);
			rt98.setBrt006(brt006[i]);
			rt98.setBrt007(brt007[i]);
			rt98.setBrt008(brt008[i]);
			rt98.setBrt009(brt009[i]);
			rt98.setBrt010(brt010[i]);
			rt98.setBrt011(brt011[i]);
			rt98.setBrt012(brt012[i]);
			rt98.setBrt013(brt013[i]);
			rt98.setBrt014(brt014[i]);
			rt98.setBrt999(brt999[i]);
			rt98.setFileKey("rt98_update");
			v98.add(rt98);
		}
		
	   	// 获取接口
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// 将Application对象放入HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", v98);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// 将HashMap对象放入requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// 调用对应的Facade业务处理方法
	   	ResponseEnvelop resEnv = facade.saveRt98(requestEnvelop);
	   	// 处理返回结果
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		super.saveSuccessfulMsg(request, "保存成功!");
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
	   	return mapping.findForward("modifyreport");
	    //return viewRt98(mapping,actionForm,request,response);
	   	//return go2Page(request,mapping,"recommendation", "1");
	   }
		/**
		 * 报表上报
	     * 
	     * @param	mapping		表单映射器
	     * @param	actionForm	表单数据实体
	     * @param	request	    客户端的HTTP请求连接
	     * @param	response   	客户端的HTTP返回连接
	     * 
	     * @return	显示报表列表信息
	     * @throws	 上报出错
		 */
		public ActionForward upcast(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//经办人
				dto.setAae017(dto1.getBsc001());//经办机构
				dto.setCce001(fm.getCce001());//统计机构
				dto.setAae036(DateUtil.getSystemCurrentTime());//经办时间
				dto.setBrt996(fm.getYear());//统计年度
				dto.setBsc006(fm.getBsc006());//虚拟机构
				// 将Application对象放入HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// 获取接口
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.upcastReport(requestEnvelop);
				//处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "报表上报成功!");
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
							"|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("viewreport");
					//return mapping.findForward("backspace");
				}
			} catch (Exception e) {
				super.saveErrors(request, e);
				return mapping.findForward("backspace");
			}
			return mapping.findForward("viewreport");
			//return go2Page(request,mapping,"recommendation", "1");
		}
}
