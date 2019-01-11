package org.radf.manage.menu.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.login.dto.LoginDTO;
import org.radf.manage.entity.Sc08;
import org.radf.manage.menu.dto.MenuDTO;
import org.radf.manage.menu.facade.MenuFacade;
import org.radf.manage.menu.form.MenuForm;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

public class MenuAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	/**
	 * 查询菜单信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward menuQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		MenuForm menuForm = (MenuForm) form;
		Sc08 sc08 = new Sc08();
		String forward = "/sysmanager/menu/MenuQuery.jsp";
		ActionForward af = new ActionForward(forward);
		ClassHelper.copyProperties(menuForm, sc08);
		sc08.setFileKey("sys04_000");
		String hql = queryEnterprise(sc08);
		af = super.init(req, forward, hql);
		// 检查查询结果数据是否存在
		if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			String msg = "没有查询到符合条件的记录！";
			super.saveSuccessfulMsg(req, msg);
		}

		return af;
	}

	/**
	 * 保存修改菜单信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifySc08(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		MenuForm menuform = (MenuForm) form;
		if (menuform.getBsc016()!=null && !"".equals(menuform.getBsc016()))
		{
			menuform.setBsc016(menuform.getBsc016().toLowerCase());
		}
		if (menuform.getBsc019()!=null && !"".equals(menuform.getBsc019()))
		{
			menuform.setBsc019(menuform.getBsc019().toLowerCase());
		}
		Sc08 sc08DTO = new Sc08();
		String function = request.getParameter("function");
		String forward = "save";
		ClassHelper.copyProperties(menuform, sc08DTO);
		if ("add".equals(function)) {
			LoginDTO loginform = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			sc08DTO.setAae011(loginform.getBsc010()); // 操作人
			sc08DTO.setAae036(DateUtil.getSystemCurrentTime()); // 操作时间
			forward = "menuQuery";
		}
		// 获取接口
		MenuFacade facade = (MenuFacade) getService("MenuFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("sc08DTO", sc08DTO);
		mapRequest.put("function", function);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.modifySc08(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "保存成功!");
		} else {
			String[] msg = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(request, msg[3]);
		}

		return mapping.findForward(forward);
	}

	/**
	 * 查看修改菜单信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward menuSav(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String function = req.getParameter("function");
		String bsc016 = req.getParameter("bsc016");
		String forward = "/menu/SaveMenu.jsp";
		MenuForm menuForm = (MenuForm) form;
		Sc08 sc08 = new Sc08();
		sc08.setBsc016(bsc016);
		if ("add".equalsIgnoreCase(function)) {
			ActionForward af = new ActionForward();
			// 传给修改页面空白的 FormBean
			req.getSession().setAttribute("MenuForm", new MenuForm());
			af = new ActionForward("/menu/SaveMenu.jsp?function=add");
			return af;
		} else if ("mod".equalsIgnoreCase(function)) {

			forward = "/menu/SaveMenu.jsp?function=mod";
		}
		ClassHelper.copyProperties(menuForm, sc08);
		sc08.setFileKey("sys04_001");

		// 获取接口
		MenuFacade facade = (MenuFacade) getService("MenuFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc08);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.findSc08(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			if (list.get(0) != null) {
				ClassHelper.copyProperties(list.get(0), menuForm);
				menuForm.setBsc016t(menuForm.getBsc016());
				
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(req, aa[3]);
		}
	
		
		return new ActionForward(forward);
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward menuDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		String[] bsc016 = request.getParameterValues("bsc016");
		// 获取接口
		MenuFacade facade = (MenuFacade) getService("MenuFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("bsc016", bsc016);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.delSc08(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "删除成功!");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);

		}
		return mapping.findForward("menuQuery");
	}
	public ActionForward menuorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		//String[] bsc016 = request.getParameterValues("bsc016");
		Collection collection = null;
		collection = (Collection)TypeCast.getEntities(new SubmitDataMap(request),MenuDTO.class);
		// 获取接口
		MenuFacade facade = (MenuFacade) getService("MenuFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("collection", collection);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.orderSc08(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "排序成功!");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);

		}
		return mapping.findForward("menuQuery");
	}
}