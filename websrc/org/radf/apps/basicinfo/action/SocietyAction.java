/**
 * SocietyAction.java 2008/03/27
 * 
 * Copyright (c) 2008 
 * ��Ŀ: Rapid Application Development Framework
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

import org.radf.apps.commons.entity.Cc0c;
import org.radf.apps.basicinfo.facade.SocietyFacade;
import org.radf.apps.basicinfo.form.PersonForm;
import org.radf.apps.basicinfo.form.SocietyForm;
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
 * ��������ϵ��Ϣ����
 */
public class SocietyAction extends ActionLeafSupport
{

    /**
     * ���ݸ��˻�����Ϣ���и�������ϵ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward findsociety(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        PersonForm pf = (PersonForm) form;
        Cc0c cc0c = new Cc0c();
        cc0c.setAac001(pf.getAac001());
        cc0c.setFileKey("bas02_004");
        String hql = queryEnterprise(cc0c);
        String forward = "/basicinfo/society.jsp";
        ActionForward af = new ActionForward(forward);
        try
        {
            af = super.init(req, forward, hql,"2");
        }
        catch (Exception e)
        {
            super.saveErrors(req, new Exception("��ѯʧ�ܣ�"));
        }
        return af;
    }

    /**
     * �����˸�����ϵ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SocietyForm queryForm = (SocietyForm) form;
        // ��������е���ʷ����
        queryForm.setAcc0c0("");
        queryForm.setAac002("");
        queryForm.setAac003("");
        queryForm.setAcc0c1("");
        queryForm.setAab004("");
        return new ActionForward("/societyinfo.jsp");
    }

    /**
     * �޸�����ϵ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SocietyForm sf = (SocietyForm) form;
        Cc0c cc0c = new Cc0c();
        ClassHelper.copyProperties(sf, cc0c);
        if ((null == cc0c.getAcc0c0()) || ("".equals(cc0c.getAcc0c0())))
        {
            super.saveErrors(req, new Exception("����������ѯ��ϸ��Ϣʧ�ܣ�"));
        }
        else
        {
            // ��ȡ��ϸ����
            SocietyFacade facade = (SocietyFacade) getService("SocietyFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap mapRequest = new HashMap();
            cc0c.setFileKey("cc0c_select");
            mapRequest.put("beo", cc0c);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.findSociety(requestEnvelop);
            // �����ؽ��
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
        return new ActionForward("/societyinfo.jsp");
    }

    /**
     * ���ص�ǰ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward back(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SocietyForm sf = (SocietyForm) form;
        Cc0c cc0c = new Cc0c();
        cc0c.setAac001(sf.getAac001());
        cc0c.setFileKey("bas02_004");
        String hql = queryEnterprise(cc0c);
        String forward = "/basicinfo/society.jsp";
        ActionForward af = new ActionForward(forward);
        try
        {
            af = super.init(req, forward, hql);
        }
        catch (Exception e)
        {
            super.saveErrors(req, new Exception("��ѯʧ�ܣ�"));
        }
        return af;
    }

    /**
     * ��������ϵ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SocietyForm sf = (SocietyForm) form;
        Cc0c cc0c = new Cc0c();

        ClassHelper.copyProperties(sf, cc0c);
		LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		cc0c.setAae011(dto1.getBsc010());
		cc0c.setAae017(dto1.getBsc001());
        cc0c.setAae036(DateUtil.getSystemCurrentTime());
        try
        {
            SocietyFacade facade = (SocietyFacade) getService("SocietyFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap mapRequest = new HashMap();
            mapRequest.put("beo", cc0c);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.saveSociety(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                super.saveSuccessfulMsg(req, "����ɹ�!");
                String workString = (String)((HashMap) resEnv.getBody()).get("workString");
                FindLog.insertLog(req,cc0c.getAac001(),workString);
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                throw new AppException(aa[3]);
            }
        }
        catch (Exception e)
        {
            super.saveErrors(req, e);
        }
        //return back(mapping, form, req, res);
		return go2Page(req,mapping,"basicinfo","2");
    }

    /**
     * ɾ������ϵ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��������ϵ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward clear(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        SocietyForm sf = (SocietyForm) form;
        Cc0c cc0c = new Cc0c();
        String acc0c0 = req.getParameter("acc0c0");
        cc0c.setAcc0c0(acc0c0);
        SocietyFacade facade = (SocietyFacade) getService("SocietyFacade");
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        EventResponse returnValue = new EventResponse();
        // ��Application�������HashMap
        HashMap mapRequest = new HashMap();
        cc0c.setFileKey("cc0c_delete");
        mapRequest.put("beo", cc0c);
        // ��HashMap�������requestEnvelop
        requestEnvelop.setBody(mapRequest);
        // ���ö�Ӧ��Facadeҵ������
        ResponseEnvelop resEnv = facade.removeSociety(requestEnvelop);
        // �����ؽ��
        returnValue = processRevt(resEnv);
        if (returnValue.isSucessFlag())
        {
            super.saveSuccessfulMsg(req, "ɾ���ɹ�!");
            FindLog.insertLog(req,cc0c.getAac001(),"ɾ����������ϵ");
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