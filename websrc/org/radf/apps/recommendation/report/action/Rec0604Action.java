/**
 * Rec0604Action.java 2008/04/28
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author sk
 * @version 1.0
 */
package org.radf.apps.recommendation.report.action;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.recommendation.report.facade.Rec0604Facade;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;

public class Rec0604Action extends ActionLeafSupport {	
	/**
	 * ְҵ�����ۺ��±�
	 * 
	 * @param mapping	��ӳ����
	 * @param form	������ʵ��
	 * @param request	�ͻ��˵�HTTP��������
	 * @param response	�ͻ��˵�HTTP��������
	 * @return ����ͳ��
	 * @throws ����ͳ�Ƴ���
	 */
	public ActionForward reportCreate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String type = request.getParameter("type");	
			ParaUtil para = new ParaUtil();
			String areaCode = para.getParaV("regioncode", "regioncode", "sys");
			System.out.println(year);
			System.out.println(month);
			System.out.println(type);
			System.out.println(areaCode);
		try{			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("year",year);
			mapRequest.put("month",month);
			mapRequest.put("type",type);
			mapRequest.put("areaCode",areaCode);
			requestEnvelop.setBody(mapRequest);
			// ��÷���ӿ�
			Rec0604Facade facade = (Rec0604Facade) getService("Rec0604Facade");
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.reportCreate(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
                HashMap map = (HashMap) resEnv.getBody();
	            StringBuffer sb = (StringBuffer) map.get("dto");		
				if(null != sb && sb.toString().length() > 0) {
					response.setContentType("bin");
					response.addHeader("Content-Disposition","attachment; filename=Data.txt");
					int len = sb.toString().getBytes().length;
					response.getOutputStream().write(sb.toString().getBytes(),0,len);
					return null;
				} else {
					this.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼1!");
				}
			} else {
                String[] errorMsg = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
                request.setAttribute("errorMsg", errorMsg[3]);
                super.saveSuccessfulMsg(request, errorMsg[3]);
            }
		}catch(Exception ex) {
			ex.printStackTrace();
            request.setAttribute("errorMsg", "û�в�ѯ�����������ļ�¼2");
            super.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼3");
		}
		
		return mapping.findForward("back");
	}
}
