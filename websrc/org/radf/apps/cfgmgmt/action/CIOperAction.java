/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
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
 * ��Ա��Ϣ����
 */
public class CIOperAction extends ActionLeafSupport {
 
    /**
     * ������ɾ��<br> 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ϣ�б�
     * @throws	ɾ������
     */
    public ActionForward deleteCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception {
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
        Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "����Ϊ�գ������²�ѯ");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();           
            mapRequest.put("beo", tblitsmci);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.deleteCI(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "ɾ��������ɹ�!");
                FindLog.insertLog(request,ciid,"ɾ����������Ϣ");           
            } else {
                String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
                super.saveErrors(request, new AppException(aa[3]));
            }
        }
        //��һ��ҳ�棬�����������ѯ�޸�ҳ��
        fm.setCiid(null);
        fm.setCiname(null);
        fm.setCitype(null);
        return mapping.findForward("modifyci");
    }
       
    /**
     * ��ʾĳ������������Ϣ<br>
     * 
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	������������Ϣ�б�
     * @throws	����
     */
    public ActionForward printCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
    	Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "����Ϊ�գ������²�ѯ");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();            
            mapRequest.put("beo", tblitsmci);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.printCI(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
        		List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");//��������Ϣ
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
     * �޸�ĳ������������Ϣ<br>
     * 
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	�޸�������������Ϣ�б�
     * @throws	����
     */
    public ActionForward modifyCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	CIForm fm = (CIForm) form;
    	String ciid = request.getParameter("ciid");
    	Tblitsmci tblitsmci = new Tblitsmci();
        if (null == ciid || "".equalsIgnoreCase(ciid)) {
            saveSuccessfulMsg(request, "����Ϊ�գ������²�ѯ");
        } else {
            ClassHelper.copyProperties(fm, tblitsmci);
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap<String, Tblitsmci> mapRequest = new HashMap<String, Tblitsmci>();            
            mapRequest.put("beo", tblitsmci);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.printCI(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
        		List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");//��������Ϣ
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
     * �������������������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br> 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������������ҳ��
     * @throws	��ѯ����
	 */
    public ActionForward saveNewCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception {
    	CIForm cf = (CIForm)form;
        Tblitsmci tblitsmci = new Tblitsmci();
        try {
            // �趨������Ϣ
            ClassHelper.copyProperties(cf, tblitsmci);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute("LoginDTO");
            tblitsmci.setCiadder(dto1.getBsc010());
            tblitsmci.setCiadddt(DateUtil.getSystemCurrentTime());
            // ��ȡ����ӿ�
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap<String, Object> mapRequest = new HashMap<String, Object>();
            mapRequest.put("beo", tblitsmci);
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.saveCI(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "������������Ϣ�ɹ�!");
                //������������������
                String ciid = (String)((HashMap) resEnv.getBody()).get("ciid");
                //��ô�ҵ��㷵�ص���־��Ϣ
                String workString = (String)((HashMap) resEnv.getBody()).get("workString");
                FindLog.insertLog(request,ciid,workString);
                //��һ��ҳ�棬����������Ǽ�ҳ��
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
     * �����޸ĺ����������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������������ҳ��
     * @throws	��ѯ����
	 */
    public ActionForward saveModifiedCI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	CIForm cf = (CIForm)form;
        Tblitsmci tblitsmci = new Tblitsmci();
        try {
            // �趨������Ϣ
            ClassHelper.copyProperties(cf, tblitsmci);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute("LoginDTO");
            tblitsmci.setCiadder(dto1.getBsc010());
            tblitsmci.setCiadddt(DateUtil.getSystemCurrentTime());
            // ��ȡ����ӿ�
            CIFacade facade = (CIFacade) getService("CIFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap<String, Object> mapRequest = new HashMap<String, Object>();
            mapRequest.put("beo", tblitsmci);
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.modifyCI(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag()){
                super.saveSuccessfulMsg(request, "�޸���������Ϣ�ɹ�!");
                //��һ��ҳ�棬�����������ѯ�޸�ҳ��
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
     * ��ѯ�����������Ϣ(֧��ģ����ѯ)
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������Ϣ�б�ҳ��
     * @throws	��ѯ����
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
            // ����Ƿ���ڣ�
            if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
                String msg = "û�в�ѯ�����������ļ�¼��";
                super.saveSuccessfulMsg(req, msg);
            }
        }catch (AppException ex) {
            this.saveErrors(req, ex);
        }catch (Exception e) {
            this.saveErrors(req, e);
        }
        if ("modifyCI".equalsIgnoreCase(req.getParameter("menuId"))) {
        	//�Ƿ���Ϊ�޸Ķ��õĲ�ѯ
            return mapping.findForward("modifyci");         	
        }
        return mapping.findForward("queryci"); 
    }
}