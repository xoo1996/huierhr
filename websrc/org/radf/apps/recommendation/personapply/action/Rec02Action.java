/**
 * Rec02Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personapply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.personapply.dto.Rec02DTO;
import org.radf.apps.recommendation.personapply.form.Rec02Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * ��ְ�Ǽǲ�ѯ
 */
public class Rec02Action extends ActionLeafSupport {

	private LogHelper log = new LogHelper(this.getClass());

	/**
	 * ���������ְ�Ǽ�ҳ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1������menuid��תҳ��</br>
     * 2������ն�Ӧ��form��Ϣ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ���������ְ�Ǽ�ҳ��
	 * @throws ���������ְ�Ǽ�ҳ�����
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		req.getSession().setAttribute("menuId", menuId);
		String forward = "queryPersonApply";
		Rec02Form fm = new Rec02Form();
		ClassHelper.copyProperties(fm, form);
		return mapping.findForward(forward);
	}

	/**
	 * ��ѯ������ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���鿴��ǰ��Ч����ְ��Ϣ</tt>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param form
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * @return ��ѯ������ְ��Ϣ
	 * @throws ��ѯ������ְ��Ϣ����
	 */
	public ActionForward searchPersonApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Rec02Form sf = (Rec02Form) form;
		String aac002 = sf.getAac002();
		String hql = null;
		ActionForward af = new ActionForward();
		Rec02DTO dto = new Rec02DTO();
		String forward = "/recommendation/personapply/QueryPersonApply.jsp";
		try {
			ClassHelper.copyProperties(sf, dto);
			dto.setFileKey("rec02_001");
			hql = queryEnterprise(dto);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				if ((null != aac002) && (!"".equals(aac002))) {
					sf.setAac005("01");// ��������Ĭ����Ϊ����
					sf.setAac006(DateUtil.getBirtday(aac002));
					sf.setAac004(DateUtil.getGender(aac002));
				}
				req.getSession().setAttribute("Rec0201Form", sf);
				af = new ActionForward("/personapply/check.jsp");
			}
		} catch (Exception e) {
			saveErrors(req, e);
		}
		return af;
	}

}