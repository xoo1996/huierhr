package org.radf.plat.util.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.plat.cp.tree.Trees;
import org.radf.plat.util.global.GlobalNames;

public class ActionCommon extends ActionLeafSupport {
	/**
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward menudisp(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward("/Left.jsp");
		String s2 = request.getParameter(GlobalNames.MENU_NAME);
		Trees trees = (Trees) request.getSession().getAttribute(
				GlobalNames.MENU_TREE);
		request.getSession().setAttribute(GlobalNames.MENU_XML,
				org.radf.plat.cp.a.a.a(trees, s2));
		return forward;
	}
}
