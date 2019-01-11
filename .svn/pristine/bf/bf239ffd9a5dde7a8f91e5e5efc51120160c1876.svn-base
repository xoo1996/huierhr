package org.radf.apps.client.group.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.group.form.GroupClientForm;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 团体客户管理
 */
public class GroupClientAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public GroupClientAction() {
	}

	/**
	 * 根据客户ID返回客户名称
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryGCName(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		GroupClient gc = new GroupClient();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		try {
			gc.setGctid(req.getParameter("clientID"));

			GroupClientFacade gcFacade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", gc);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = gcFacade.queryGCName(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询团体客户名称成功!");
				// 获得团体客户名称
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), gc);
				String clientName = gc.getGctnm();
				orderForm.setFolctnm(clientName);
				res.setCharacterEncoding("GBK");
				res.getWriter().write(clientName);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			super.saveErrors(req, e);
			res.setCharacterEncoding("GBK");
			res.getWriter().write("客户代码输入有误，请检查");
		}
		return null;
	}

	/**
	 * 新增团体客户
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		GroupClient gc = new GroupClient();
		GroupClientForm gcf = (GroupClientForm) form;
		try {
			ClassHelper.copyProperties(gcf, gc);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			gc.setGctoprcd(dto1.getBsc010());
			gc.setGctdate(DateUtil.getSystemCurrentTime());
			GroupClientFacade facade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", gc);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存团体客户信息成功!");
				String gctid = (String) ((HashMap) resEnv.getBody())
						.get("gctid");
				// 获得从业务层返回的日志信息
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, gctid, workString);
				// ClassHelper.copyProperties(list.get(0), sc);
				return mapping.findForward("add");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 查询团体客户信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		GroupClient gc = new GroupClient();
		GroupClientForm gcf = (GroupClientForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
		if ("modify".equals(order)) {
			forward = "/client/group/modify.jsp";
		} else
			forward = "/client/group/query.jsp";
		try {
			ClassHelper.copyProperties(gcf, gc);
			gc.setFileKey("clt02_001");
			String hql = queryEnterprise(gc);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 修改团体客户信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String gctid = req.getParameter("gctid");
		GroupClient gc = new GroupClient();
		GroupClientForm gcf = (GroupClientForm) form;

		if (null == gctid || "".equalsIgnoreCase(gctid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(gcf, gc);
			GroupClientFacade facade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, GroupClient> mapRequest = new HashMap<String, GroupClient>();
			mapRequest.put("beo", gc);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");// 团体客户信息
				ClassHelper.copyProperties(listci.get(0), gcf);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward("alter");
	}

	/**
	 * 保存修改后的团体客户信息
	 */
	public ActionForward saveModified(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		GroupClient gc = new GroupClient();
		GroupClientForm gcf = (GroupClientForm) form;
		try {
			// 设定经办信息
			ClassHelper.copyProperties(gcf, gc);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			gc.setGctoprcd(dto1.getBsc010());
			gc.setGctdate(DateUtil.getSystemCurrentTime());
			// 获取服务接口
			GroupClientFacade facade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", gc);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改团体客户信息成功!");
				// 下一个页面，还是配置项查询修改页面
				// cf.setCiid(null);
				// cf.setCiname(null);
				// cf.setCitype(null);
				return mapping.findForward("modify");
				// return queryCI(mapping, form, request, response);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 删除团体客户信息
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String gctid = req.getParameter("gctid");
		GroupClient gc = new GroupClient();
		GroupClientForm gcf = (GroupClientForm) form;
		if (null == gctid || "".equalsIgnoreCase(gctid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(gcf, gc);
			GroupClientFacade facade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, GroupClient> mapRequest = new HashMap<String, GroupClient>();
			mapRequest.put("beo", gc);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除团体客户信息成功!");
				FindLog.insertLog(req, gctid, "删除团体客户信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward("modify");
	}
}
