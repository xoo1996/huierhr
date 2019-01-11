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
 * ְҵ���ܹ�������걨
 */
public class Rec06Action extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());
     
	 
	     /**
		 * ��ѯְҵ���ܹ�������걨
	     * 
	     * @param	mapping		��ӳ����
	     * @param	form		������ʵ��
	     * @param	req		    �ͻ��˵�HTTP��������
	     * @param	res     	�ͻ��˵�HTTP��������
	     * 
	     * @return	ְҵ���ܹ�������걨��Ϣ�б�
	     * @throws	��ѯ����
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
	            // ����Ƿ���ڣ�
	            if (null == req.getAttribute(GlobalNames.QUERY_DATA))
	            {
	                String msg = "û�в�ѯ�����������ļ�¼��";
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
		 * ����ְҵ���ܹ������ҳ��
		 * 
		 * @param mapping
		 *            ��ӳ����
		 * @param form
		 *            ������ʵ��
		 * @param request
		 *            �ͻ��˵�HTTP��������
		 * @param response
		 *            �ͻ��˵�HTTP��������
		 * @return ���뵥λ��Ƹ�Ǽ�ҳ��
		 * @throws ���뵥λ��Ƹ�Ǽ�ҳ�����
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
		 * ���ɱ�����Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	������Ϣ�б�ҳ��
	     * @throws	���ɳ���
		 */
		public ActionForward born(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//������
				dto.setAae017(dto1.getBsc001());//�������
				dto.setCce001(fm.getCce001());//ͳ�ƻ���
				dto.setAae036(DateUtil.getSystemCurrentTime());//����ʱ��
				dto.setBrt996(fm.getYear());//ͳ�����
				dto.setBsc006(fm.getBsc006());//�������
				// ��Application�������HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// ��ȡ�ӿ�
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//��HashMap�������requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.born(requestEnvelop);
				//�����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "�������ɳɹ�!");
			   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			   		request.getSession().setAttribute("listrt99", list);
			   		if(list==null){
			   			super.saveSuccessfulMsg(request, "������δ���ɣ�");
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
		 * ���ܱ�����Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	������Ϣ�б�ҳ��
	     * @throws	���ɳ���
		 */
		public ActionForward born_hz(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//������
				dto.setAae017(dto1.getBsc001());//�������
				dto.setAae019(dto1.getBsc008());//�������
				dto.setAae036(DateUtil.getSystemCurrentTime());//����ʱ��
				dto.setBrt996(fm.getYear());//ͳ�����
				dto.setCce001(fm.getCce001());//ͳ�ƻ���
				// ��Application�������HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// ��ȡ�ӿ�
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//��HashMap�������requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.born_hz(requestEnvelop);
				//�����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "������ܳɹ�!");
			   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			   		request.getSession().setAttribute("listrt99", list);
			   		if(list==null){
			   			super.saveSuccessfulMsg(request, "������δ���ɣ�");
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
		 * ��ʾ������Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	��ѯ����
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
		fm.setBrt996(fm.getYear());//ͳ�����
	   	//Rt99 rt99 = new Rt99();
	   	//Lab01DTO dto = new Lab01DTO();	    	
	   	ClassHelper.copyProperties(fm, dto);
	   	LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
		"LoginDTO");
	   	dto.setAae017(dto1.getBsc001());//�������
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.viewReport(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "������δ���ɣ�");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("viewreport");

	   }
		
		/**
		 * ��ӡ������Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	��ѯ����
		 */
		public ActionForward printRt99(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
//		Rec06Form fm = (Rec06Form) actionForm;
		Rec06DTO dto = new Rec06DTO();
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("brt996");
		dto.setCce001(cce001);
		dto.setBrt996(brt996);
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.viewReport(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "������δ���ɣ�");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("printreport");

	   }
		
		/**
		 * ��ӡ������Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	��ѯ����
		 */
		public ActionForward printRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
//		Rec06Form fm = (Rec06Form) actionForm;
		Rt98 dto = new Rt98();
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("brt996");
		dto.setCce001(cce001);
		dto.setBrt996(brt996);
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", dto);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.viewRt98(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		request.getSession().setAttribute("listrt99", list);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "������δ���ɣ�");
	   		}
	   		
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("printreport");

	   }
		
		/**
		 * ��ʾ�ϱ�������Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	��ѯ����
		 */
		public ActionForward viewRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
		Rec06Form fm = (Rec06Form) actionForm;
        //�ж��Ƿ����޸�Ȩ��
		String cce001=request.getParameter("cce001");
		String brt996=request.getParameter("year");
		Rec06DTO dto = new Rec06DTO();
		fm.setBrt996(fm.getYear());
	   	Rt98 rt98 = new Rt98();
	   	//Lab01DTO dto = new Lab01DTO();	    	
	   	ClassHelper.copyProperties(fm, rt98);
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", rt98);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.viewRt98(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
	   		String exist = (String) ((HashMap) resEnv.getBody()).get("exist");
	   		request.getSession().setAttribute("listrt98", list);
	   		request.getSession().setAttribute("exist", exist);
	   		if(list==null){
	   			super.saveSuccessfulMsg(request, "������δ�ϱ���");
	   		}
	   	} else {
	   		String[] aa = StringUtil
	   				.getAsStringArray(returnValue.getMsg(), "|");
	   		super.saveSuccessfulMsg(request, aa[3]);
	   	}
   		
	   		return mapping.findForward("modifyreport");

	   }
		
		/**
		 * ���汨����Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	�������
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
		
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", v99);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.saveReport(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		super.saveSuccessfulMsg(request, "����ɹ�!");
	   		
	   		
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
		 * ���汨����Ϣ
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ������Ϣ
	     * @throws	�������
		 */
		public ActionForward saveRt98(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
        //�ж��Ƿ����޸�Ȩ��
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
		
	   	// ��ȡ�ӿ�
	   	Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
	   	RequestEnvelop requestEnvelop = new RequestEnvelop();
	   	EventResponse returnValue = new EventResponse();
	   	// ��Application�������HashMap
	   	HashMap mapRequest = new HashMap();
	   	mapRequest.put("beo", v98);
	   	mapRequest.put("cce001", cce001);
	   	mapRequest.put("brt996", brt996);
	   	// ��HashMap�������requestEnvelop
	   	requestEnvelop.setBody(mapRequest);
	   	// ���ö�Ӧ��Facadeҵ������
	   	ResponseEnvelop resEnv = facade.saveRt98(requestEnvelop);
	   	// �����ؽ��
	   	returnValue = processRevt(resEnv);
	   	if (returnValue.isSucessFlag()) {
	   		super.saveSuccessfulMsg(request, "����ɹ�!");
	   		
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
		 * �����ϱ�
	     * 
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��ʾ�����б���Ϣ
	     * @throws	 �ϱ�����
		 */
		public ActionForward upcast(ActionMapping mapping, ActionForm actionForm,
		         HttpServletRequest request, HttpServletResponse response)throws Exception {
			Rec06Form fm = (Rec06Form) actionForm;
			Rec06DTO dto = new Rec06DTO();
			try {
				LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
						"LoginDTO");
				dto.setAae011(dto1.getBsc010());//������
				dto.setAae017(dto1.getBsc001());//�������
				dto.setCce001(fm.getCce001());//ͳ�ƻ���
				dto.setAae036(DateUtil.getSystemCurrentTime());//����ʱ��
				dto.setBrt996(fm.getYear());//ͳ�����
				dto.setBsc006(fm.getBsc006());//�������
				// ��Application�������HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				// ��ȡ�ӿ�
				Rec06Facade facade = (Rec06Facade) getService("Rec06Facade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				//��HashMap�������requestEnvelop
				requestEnvelop.setBody(mapRequest);
				//���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.upcastReport(requestEnvelop);
				//�����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "�����ϱ��ɹ�!");
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
