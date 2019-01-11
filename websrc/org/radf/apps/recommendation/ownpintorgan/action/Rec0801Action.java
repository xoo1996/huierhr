/**
 * Rec0801Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.entity.Cb24;
import org.radf.apps.commons.entity.Cb25;
import org.radf.apps.recommendation.ownpintorgan.form.Rec0801Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * ���ְҵ���ܻ������
 */
public class Rec0801Action extends ActionLeafSupport {

	/**
	 * �������ְҵ���ܻ������Ǽ�ҳ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ʼ��ҳ��<br></tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return �������ְҵ���ܻ������Ǽ�ҳ��
	 * @throws �������ְҵ���ܻ������Ǽ�ҳ�����
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		String forward = "backspace";
		if ("queryOrgan".equalsIgnoreCase(menuId)) {
			forward = "queryOrgan";// ��ѯ������Ϣ,�Խ��еǼǲ���
		}
		if ("organManager".equalsIgnoreCase(menuId)) {
			forward = "organManager";// ��ѯ������Ϣ,�Խ��л���ά��
		}
		return mapping.findForward(forward);

	}

	/**
	 * ��ѯ������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ѯ�����������б���ʾ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ������Ϣ
	 * @throws ��ѯ������Ϣ����
	 */
	public ActionForward enterqueryorgan(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0801Form form = (Rec0801Form) actionForm;
		Cb24 cb24 = new Cb24();
		ClassHelper.copyProperties(form, cb24);
		cb24.setFileKey("rec08_001");
		// ��ȡSQL���
		String hql = queryEnterprise(cb24);
		ActionForward af = new ActionForward();
		af = super.init(request, "/recommendation/ownpintorgan/organlist.jsp",
				hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼��");
		}
		return af;
	}

	/**
	 * ��ѯ���������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ѯ�������Ϣ<br>
     * 2��һ��һ����¼</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ���������Ϣ
	 * @throws ��ѯ���������Ϣ����
	 */
	public ActionForward organManager(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Rec0801Form form = (Rec0801Form) actionForm;
		Cb25 cb25 = new Cb25();
		ClassHelper.copyProperties(form, cb25);
		cb25.setFileKey("rec08_004");
		// ��ȡSQL���
		String hql = queryEnterprise(cb25);
		ActionForward af = new ActionForward();
		af = super.init(request,
				"/recommendation/ownpintorgan/organyearchecklist.jsp", hql);
		if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
			super.saveSuccessfulMsg(request, "û�в�ѯ�����������ļ�¼��");
		}
		return af;
	}

}
