package org.radf.manage.role.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.login.dto.LoginDTO;
import org.radf.manage.entity.Sc08;
import org.radf.manage.entity.Sc06;
import org.radf.manage.entity.Sc07;
import org.radf.manage.role.facade.RoleXFacade;
import org.radf.manage.role.form.Sc06Form;
import org.radf.manage.entity.Sc05;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.cp.a.a;
import org.radf.plat.cp.a.c;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalNames;

public class RoleXAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	/**
	 * 获取所有角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAllRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		ActionForward forward = new ActionForward();

		HashMap mapRequest = new HashMap();
		if (GlobalNames.SUPER_USER.equals(dto.getBsc011())) {
			Sc06 sc06 = new Sc06();
			sc06.setFileKey("SC06_select");
			mapRequest.put("beo", sc06);
		} else {
			Sc07 sc07 = new Sc07();
			sc07.setFileKey("sys03_002");
			sc07.setBsc010(dto.getBsc010());
			mapRequest.put("beo", sc07);
		}
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		requestEnvelop.setBody(mapRequest);
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ResponseEnvelop resEnv = facade.find(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		ArrayList list = new ArrayList();
		if (returnValue.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValue.getBody();
			ArrayList list1 = (ArrayList) map1.get("beo");

			if (list1 != null && list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					Sc06 returnsc06 = new Sc06();
					ClassHelper.copyProperties(list1.get(i), returnsc06);
					list.add(returnsc06);
				}
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.getSession().setAttribute("allRoles", list);
		return mapping.findForward("roleList");
	}

	/**
	 * 根据角色编号获取一级菜单
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward findModulesByRoleid(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Sc06 sc06 = new Sc06();
		sc06.setBsc014(request.getParameter("bsc014"));
		sc06.setBsc015(request.getParameter("bsc015"));
		sc06.setFileKey("sys03_000");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		mapRequest.put("qz", "1");
		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.findsc08bybsc016(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValue.getBody();
			ArrayList list1 = (ArrayList) map1.get("list");
			if (list1 != null && list1.size() > 0) {
				request.setAttribute("modules", list1);
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.setAttribute(GlobalNames.UPDATE_DATA, sc06);
		return mapping.findForward("modulelist");
	}

	/**
	 * 根据一级菜单编号获取所有下级菜单
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward findFunctionListOfModule(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Sc06 sc06 = new Sc06();
		sc06.setBsc014(request.getParameter("bsc014"));
		sc06.setBsc015(request.getParameter("bsc015"));
		if (sc06.getBsc014() == null)
			return mapping.findForward("rightTree");
		/*List list1 = (List) request.getSession().getAttribute(
				GlobalNames.FUNCTION_LIST);*/
		ArrayList list1 = new ArrayList();
		RequestEnvelop requestEnvelopsc08 = new RequestEnvelop();
		HashMap mapRequestsc08 = new HashMap();
		Sc08 sc08 = new Sc08();
		sc08.setFileKey("sys04_002");
		sc08.setBsc016(request.getParameter("bsc016"));
		sc08.setBsc016(sc08.getBsc016().substring(0,3));
		mapRequestsc08.put("beo", sc08);
		mapRequestsc08.put("qz", "0");
		requestEnvelopsc08.setBody(mapRequestsc08);
		FACADESupport facadefind = (FACADESupport) getService("FACADESupport");
		ResponseEnvelop resEnvsc08 = facadefind.find(requestEnvelopsc08);
		EventResponse returnValuesc08 = processRevt(resEnvsc08);
		if (returnValuesc08.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValuesc08.getBody();
			ArrayList list2 = (ArrayList) map1.get("beo");
			if (list2 != null ) {
				for (int i = 0; i < list2.size(); i++) {
					sc08 = new Sc08();
					ClassHelper.copyProperties(list2.get(i), sc08);
					
						list1.add(sc08);
					
				}
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValuesc08.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		
		sc06.setFileKey("sys03_000");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		mapRequest.put("qz", "0");
		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.findsc08bybsc016(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValue.getBody();
			ArrayList list2 = (ArrayList) map1.get("list");
			if (list2 != null ) {
				request.setAttribute("modules", list2);
				String s = request.getParameter("bsc016");
				List list4 = c._mthdo(list1, s);
				List list5 = c._mthdo(list2, s);
				a.a(request, list4, list5);
				request.setAttribute(GlobalNames.UPDATE_DATA, sc06);
				request.setAttribute("rootFuncID", s);
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.setAttribute(GlobalNames.UPDATE_DATA, sc06);
		return mapping.findForward("rightTree");
	}

	/**
	 * 保存角色菜单权限
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveFunctionList(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String s = request.getParameter("rootFuncID");
		String as[] = request.getParameterValues("function");
		Sc06 sc06 = new Sc06();
		sc06.setBsc014(request.getParameter("roleid"));
		sc06.setBsc015(request.getParameter("roledesc"));
		sc06.setFileKey("sys03_000");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		mapRequest.put("qz", "0");
		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.findsc08bybsc016(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValue.getBody();
			ArrayList list2 = (ArrayList) map1.get("list");
			if (list2 != null ) {
				List list3 = c._mthdo(list2, s);
				mapRequest.put("beo", sc06);
				mapRequest.put("list", list3);
				mapRequest.put("as", as);
				resEnv = facade.saveFunctionList(requestEnvelop);
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					mapRequest.put("qz", "1");
					resEnv = facade.findsc08bybsc016(requestEnvelop);
					returnValue = processRevt(resEnv);
					if (returnValue.isSucessFlag()) {
						map1 = (HashMap) returnValue.getBody();
						ArrayList list4 = (ArrayList) map1.get("list");
						if (list4 != null && list4.size() > 0) {
							request.setAttribute("modules", list4);
						}
					}
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.getSession().setAttribute(GlobalNames.UPDATE_DATA, sc06);
		return mapping.findForward("RefreshModuleList");
	}

	/**
	 * 获取所有角色信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAllSc06(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/role/sc06list.jsp";
		ActionForward af = new ActionForward(forward);
		Sc06Form sc06Form = (Sc06Form) form;
		Sc06 sc06 = new Sc06();
		ClassHelper.copyProperties(sc06Form, sc06);
		sc06.setFileKey("sys03_001");
		String hql = queryEnterprise(sc06);
		af = super.init(request, forward, hql);
		return af;
	}

	/**
	 * 角色维护跳转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward Sc06find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuId = request.getParameter("menuId");
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Iterator iterator = null;
		Iterator iterator1 = null;
		Iterator iterator2 = null;
		Sc06Form sc06Form = (Sc06Form) form;
		Sc06 sc06 = new Sc06();
		ClassHelper.copyProperties(sc06Form, sc06);
		if (menuId.equals("mod")) {
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", sc06);
			mapRequest.put("dto", dto);
			requestEnvelop.setBody(mapRequest);
			RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
			ResponseEnvelop resEnv = facade.findsc06bybsc014(requestEnvelop);
			EventResponse returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map1 = (HashMap) returnValue.getBody();
				iterator1 = (Iterator) map1.get("iterator");
				iterator2 = (Iterator) map1.get("iterator2");
				sc06 = (Sc06) map1.get("sc06");
				request.setAttribute("usersIter", iterator1);
				request.setAttribute("freeUsersIter", iterator2);
				request.setAttribute(GlobalNames.UPDATE_DATA, sc06);
				return mapping.findForward(menuId);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}

		} else if (menuId.equals("add")) {
			Sc05 sc05 = new Sc05();
			if (GlobalNames.SUPER_USER.equals(dto.getBsc011())) {
				sc05.setAae100(dto.getAae100());
				sc05.setFileKey("SC05_select");
			} else {
				sc05.setBsc001(dto.getBsc001());
				sc05.setFileKey("sys03_004");
			}
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", sc05);
			requestEnvelop.setBody(mapRequest);
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			EventResponse returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map1 = (HashMap) returnValue.getBody();
				if (map1.get("beo") != null) {
					ArrayList list = (ArrayList) map1.get("beo");
					ArrayList list1 = new ArrayList();

					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							sc05 = new Sc05();
							ClassHelper.copyProperties(list.get(i), sc05);
							list1.add(sc05);
						}
						iterator = list1.iterator();
					}
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		}
		request.setAttribute("freeUsersIter", iterator);
		return mapping.findForward(menuId);
	}

	/**
	 * 角色信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSc06(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/roleAction.do?method=findAllSc06";
		Sc06Form sc06Form = (Sc06Form) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc06 sc06 = new Sc06();
		sc06.setAae011(dto.getBsc010());
		sc06.setAae036(DateUtil.getSystemCurrentTime());
		ClassHelper.copyProperties(sc06Form, sc06);
		String[] userlist = StringUtil.getAsStringArray(sc06Form.getUserList(),";");
		String edit=sc06.getBsc014();
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		mapRequest.put("userlist", userlist);
		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.saveSc06(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			if (edit != null && !edit.equals("")) {
				super.saveSuccessfulMsg(request, "修改角色成功！");
			} else {
				super.saveSuccessfulMsg(request, "增加角色成功！");
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return new ActionForward(forward);
	}

	/**
	 * 角色信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSc06(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Sc06 sc06 = new Sc06();
		Sc06Form sc06Form = (Sc06Form) form;
		ClassHelper.copyProperties(sc06Form, sc06);
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.deleteSc06(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "删除角色成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return go2Page(request,mapping,"sysmanager");
	}

	/**
	 * 角色维护跳转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward finduserbyroleid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getParameter("bsc014") == null) {
			return mapping.findForward("userrole");
		}
		Sc06Form sc06Form = (Sc06Form) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");

		Sc06 sc06 = new Sc06();
		ClassHelper.copyProperties(sc06Form, sc06);
		Iterator iterator1 = null;
		Iterator iterator2 = null;
		//sc06.setBsc014(request.getParameter("hiddenTrCode"));
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc06);
		mapRequest.put("dto", dto);
		if (sc06Form.getBsc015().equals("0")) {
			String[] userlist = StringUtil.getAsStringArray(sc06Form
					.getUserList(), ";");
			mapRequest.put("userlist", userlist);
		}

		requestEnvelop.setBody(mapRequest);
		RoleXFacade facade = (RoleXFacade) getService("RoleXFacade");
		ResponseEnvelop resEnv = facade.findsc06bybsc014(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap map1 = (HashMap) returnValue.getBody();
			iterator1 = (Iterator) map1.get("iterator");
			iterator2 = (Iterator) map1.get("iterator2");
			sc06 = (Sc06) map1.get("sc06");
			request.setAttribute("usersIter", iterator1);
			request.setAttribute("freeUsersIter", iterator2);
			request.setAttribute(GlobalNames.UPDATE_DATA, sc06);
			if(request.getParameter("type")!=null)
			super.saveSuccessfulMsg(request, "保存成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return mapping.findForward("userrole");
	}
}
