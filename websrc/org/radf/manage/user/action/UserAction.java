package org.radf.manage.user.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.manage.user.form.UserForm;
import org.radf.manage.entity.Sc05;
import org.radf.manage.user.dto.Sc05DTO;
import org.radf.manage.user.facade.UserFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.login.dto.LoginDTO;

public class UserAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	/**
	 * 查询人员信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward userQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserForm userForm = (UserForm) form;
		Sc05DTO sc05 = new Sc05DTO();
		String forward = "/sysmanager/user/UserQuery.jsp";
		ActionForward af = new ActionForward(forward);
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		ClassHelper.copyProperties(userForm, sc05);
		sc05.setFileKey("sys02_000");
		sc05.setAab003(dto.getAab003());
		String hql = queryEnterprise(sc05);
		af = super.init(req, forward, hql);

		// 检查查询结果数据是否存在
		if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			String msg = "没有查询到符合条件的记录！";
			super.saveSuccessfulMsg(req, msg);
		}

		return af;
	}

	/**
	 * 保存修改人员信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifySc05(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		UserForm userform = (UserForm) form;
		Sc05 sc05 = new Sc05();
		String function = request.getParameter("function");
		String bsc011t = request.getParameter("bsc011t");
		String roleListStr = request.getParameter("roleList");
		String forward = "/userAction.do?method=userSav&function=" + "mod";
		String[] roleList = StringUtil.getAsStringArray(roleListStr, ";");
		LoginDTO loginform = (LoginDTO) request.getSession().getAttribute(
				"LoginDTO");

		ClassHelper.copyProperties(userform, sc05);

		if ("add".equals(function)) {
			sc05.setBsc013(request.getParameter("bsc013"));
			sc05.setAae011(loginform.getBsc010()); // 操作人
			sc05.setAae036(DateUtil.getSystemCurrentTime()); // 操作时间
			forward = "/userAction.do?method=userQuery";

		}

		// 获取接口
		UserFacade facade = (UserFacade) getService("UserFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();

		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("sc05", sc05);
		mapRequest.put("aae011", loginform.getBsc010());
		mapRequest.put("bsc011t", bsc011t);
		mapRequest.put("function", function);
		mapRequest.put("roleList", roleList);

		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);

		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.modifySc05(requestEnvelop);

		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "保存成功!");
		} else {

			forward = "/userAction.do?method=userSav&function=" + function;
			String[] msg = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(request, msg[3]);

		}

		return new ActionForward(forward);
	}

	/**
	 * 查看修改人员信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward userSav(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String function = req.getParameter("function");
		String bsc010 = req.getParameter("bsc010");
		String forward = "/user/SaveUser.jsp";
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");

		UserForm userForm = (UserForm) form;
		Sc05DTO sc05 = new Sc05DTO();
		sc05.setBsc010(bsc010);
		UserFacade facade = (UserFacade) getService("UserFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		ResponseEnvelop resEnv = null;

		if ("add".equalsIgnoreCase(function)) {

			ActionForward af = new ActionForward();
			UserForm userFormpf = new UserForm();
			ClassHelper.copyProperties(userFormpf, form);
			// 传给修改页面空白的 FormBean
			req.getSession().setAttribute("UserForm", userFormpf);
			forward = "/user/SaveUser.jsp?function=add";

		} else if ("mod".equalsIgnoreCase(function)) {

			forward = "/user/SaveUser.jsp?function=mod";
			ClassHelper.copyProperties(userForm, sc05);
			sc05.setFileKey("sys02_009");
			mapRequest.put("beo", sc05);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			resEnv = facade.findSc05(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.get(0) != null && !"add".equalsIgnoreCase(function)) {
					ClassHelper.copyProperties(list.get(0), userForm);
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
			}

		}

		// 获取接口

		// 获得 sc06 角色表的数据
		mapRequest.put("sc", "sc06");
		mapRequest.put("bsc010", bsc010);
		mapRequest.put("function", function);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法 find sc06
		resEnv = facade.findSc04NSc06(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List list = (ArrayList) ((HashMap) resEnv.getBody())
					.get("sc06List");
			List list2 = (ArrayList) ((HashMap) resEnv.getBody())
					.get("sc06List2");
			if (list != null) {
				req.setAttribute("sc06List", list);
			}
			if (list2 != null) {
				req.setAttribute("sc06List2", list2);
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(req, aa[3]);
		}

		// 获得 sc04 角色表的数据
		mapRequest.put("sc", "sc04");
		mapRequest.put("bsc010", bsc010);
		mapRequest.put("aab003", dto.getAab003());
		mapRequest.put("function", function);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法 find sc04
		resEnv = facade.findSc04NSc06(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List list = (ArrayList) ((HashMap) resEnv.getBody())
					.get("sc04List");
			List list2 = (ArrayList) ((HashMap) resEnv.getBody())
					.get("sc04List2");
			if (list != null) {
				req.setAttribute("sc04List", list);
			}
			if (list2 != null) {
				req.setAttribute("sc04List2", list2);
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
	public ActionForward userDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		String bsc010 = request.getParameter("bsc010");

		// 获取接口
		UserFacade facade = (UserFacade) getService("UserFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("bsc010", bsc010);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.delSc05(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "注销成功!");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);

		}

		return mapping.findForward("userQuery");
	}

	/**
	 * 返回查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward goBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		return mapping.findForward("userQuery");
	}

	/**
	 * 密码重置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward passwdReset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		String function = request.getParameter("function");
		String bsc010 = request.getParameter("bsc010");
		String passwd = request.getParameter("passwd");
		String newpasswd = request.getParameter("newpasswd");
		String forward = "/user/AlterPassword.jsp";
		ActionForward af = new ActionForward(forward);

		if (!"alter".equalsIgnoreCase(function)
				&& !"reset".equalsIgnoreCase(function))
			return af;

		UserFacade facade = (UserFacade) getService("UserFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("bsc010", bsc010);
		mapRequest.put("oldpwd", passwd);
		mapRequest.put("newpasswd", newpasswd);
		mapRequest.put("function", function);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.alterPwd(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "更改成功!");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);

		}

		if (function != null && function.equals("reset"))
			af = new ActionForward("/userAction.do?method=userQuery");
		return af;
	}

	public ActionForward initKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String snID = req.getParameter("snID");
		String ugctid = req.getParameter("ugctid");

		UserFacade facade = (UserFacade) getService("UserFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("snID", snID);
		mapRequest.put("ugctid", ugctid);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.initKey(requestEnvelop);
		// 处理返回结果
		return null;
	}

	public ActionForward ukeyQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserForm userForm = (UserForm) form;
		Sc05DTO sc05 = new Sc05DTO();
		String forward = "/sysmanager/user/UkeyManage.jsp";
		ActionForward af = new ActionForward(forward);
		String flag = req.getParameter("flag");

		ClassHelper.copyProperties(userForm, sc05);
		if (null != flag) {
			sc05.setUgctid("");
			sc05.setBsc012("");
			sc05.setUkeysn("");
			sc05.setIswork("");
		}
		sc05.setFileKey("ukey01_00");

		String hql = queryEnterprise(sc05);
		af = super.init(req, forward, hql);

		// 检查查询结果数据是否存在
		if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			String msg = "没有查询到符合条件的记录！";
			super.saveSuccessfulMsg(req, msg);
		}

		return af;
	}

	public ActionForward loss(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserForm userForm = (UserForm) form;
		Sc05DTO sc05 = new Sc05DTO();
		ResponseEnvelop resEnv = null;
		ActionForward af = new ActionForward();
		try {
			String forward = "";
			ClassHelper.copyProperties(userForm, sc05);
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			if (sc05.getIswork().equals("0")) {
				sc05.setIswork("1");
			} else {
				sc05.setIswork("0");
			}
			sc05.setUopdate(DateUtil.getDate());
			mapRequest.put("beo", sc05);
			requestEnvelop.setBody(mapRequest);
			UserFacade facade = (UserFacade) getService("UserFacade");
			resEnv = facade.loss(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String msg = "操作成功！";
				super.saveSuccessfulMsg(req, msg);
				return new ActionForward(   
						"/userAction.do?method=ukeyQuery&flag=0");
			} else {
				return new ActionForward(
						"/userAction.do?method=ukeyQuery&flag=0");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return new ActionForward("/userAction.do?method=ukeyQuery&flag=0");
		}
	}

	public ActionForward getState(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String bsc011 = req.getParameter("bsc011");
		Connection con = null;
		ResultSet result = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "select * from sc05 where bsc011='" + bsc011
					+ "' and aae101='1'";
			PreparedStatement pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			if (result.next() == false) {
				// kong
				res.getWriter().write("0");
			} else {
				res.getWriter().write("1");
			}
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
		} catch (Exception ex) {
		}
		return null;
	}

	public ActionForward ukeyOperate(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String flag = req.getParameter("flag");
		UserForm userForm = (UserForm) form;
		Sc05DTO sc05 = new Sc05DTO();
		ResponseEnvelop resEnv = null;
		ActionForward af = new ActionForward();
		try {
			String forward = "";
			ClassHelper.copyProperties(userForm, sc05);
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			if (flag.equals("openAll")) {
				sc05.setAae101("1");
			}
			if (flag.equals("closeAll")) {
				sc05.setAae101("0");
			}
			if (flag.equals("open")) {
				sc05.setAae101("1");
			}
			if (flag.equals("close")) {
				sc05.setAae101("0");
			}
			mapRequest.put("flag", flag);
			mapRequest.put("beo", sc05);
			requestEnvelop.setBody(mapRequest);
			UserFacade facade = (UserFacade) getService("UserFacade");
			resEnv = facade.ukeyOperate(requestEnvelop);
			returnValue = processRevt(resEnv);
			return new ActionForward("/userAction.do?method=userQuery");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return new ActionForward("/userAction.do?method=userQuery");
		}

	}

	public ActionForward adminAlterPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		String bsc010 = request.getParameter("bsc010");
		String forward = "/user/AdminAlterPassword.jsp";
		ActionForward af = new ActionForward(forward);
		Connection con = null;
		ResultSet result = null;
		String bsc013="";
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "select bsc013 from sc05 where bsc010='" + bsc010 + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next()==true) {
				bsc013 = result.getString("bsc013");
			}
			request.getSession().setAttribute("bsc013", bsc013);
			DBUtil.commit(con);
			
		} catch (Exception ex) {
		}
		return af;
	}
}