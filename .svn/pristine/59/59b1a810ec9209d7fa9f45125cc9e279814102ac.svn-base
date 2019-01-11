/**
 * EmployerAction.java 2008/03/27
 * 
 * Copyright (c) 2008 项目: Rapid Application Development Framework
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

import org.radf.apps.basicinfo.dto.EmployMergeDTO;
import org.radf.apps.basicinfo.facade.EmployerFacade;
import org.radf.apps.basicinfo.form.EmployerForm;
import org.radf.apps.commons.entity.Ab01;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ActionUtil;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 单位信息管理
 */
public class EmployerAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	/**
	 * 查看单位基本信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 1、根据菜单ID进行页面跳转<br><tt>  
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
	 */
	public ActionForward viewEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
		Ab01 emp = new Ab01();
		String aab001 = fm.getAab001();
		if (aab001 != null) {
			emp.setAab001(fm.getAab001());
			try {
				// 传入根据主键查询SQL语句
				emp.setFileKey("ab01_select");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", emp);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.retriveEmp(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {

					HashMap map = (HashMap) resEnv.getBody();

					List ls = (ArrayList) map.get("beo");

					if (ls != null && ls.size() > 0) {
						ClassHelper.copyProperties(ls.get(0), fm);
					}
					String dwldyw = (String) map.get("ldywbeo");
					fm.setDwldyw(dwldyw);
					fm.setSsjqnm(TypeCast.getidtoname(
	        				fm.getAab301(),
	                        "CCE001",
	                        servlet));
				} else {
					String[] errorMsg = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					request.setAttribute("errorMsg", errorMsg[3]);
					super.saveSuccessfulMsg(request, errorMsg[3]);
					return mapping.findForward("backspace");
				}
			} catch (Exception e) {
				super.saveErrors(request, e);
				return mapping.findForward("backspace");
			}
		} else {
			super.saveErrors(request, new AppException("请选择单位！"));
		}
		String forward = "viewEmployer"; // 查看单位信息
		String operation = request.getParameter("operation");
		String menuId = request.getParameter("menuId");
		if ("edit".equals(operation)) {
			setOperaUser(request, fm);
			if ("enterpriseAlter".equals(menuId)) {
				// 单位变更
				forward = "editEmployer";
				request.setAttribute("alter", "1");
			} else if ("enterpriseQuery".equals(menuId)) {
				// 单位查询
				forward = "editEmployer";
				request.setAttribute("alter", "1");
			} else {
				// 修改单位信息
				forward = "editEmployer";
			}
		}
		if ("edit2".equals(operation)) {
			setOperaUser(request, fm);
			if ("enterpriseAlter".equals(menuId)) {
				// 单位变更
				forward = "editEmployer";
				request.setAttribute("alter", "2");
			} else if ("enterpriseQuery".equals(menuId)) {
				// 单位查询
				forward = "editEmployer";
				request.setAttribute("alter", "2");
			} else {
				// 修改单位信息
				forward = "editEmployer";
			}
		}
		if ("1".equals((String) request.getParameter("fl"))) {
			this.saveSuccessfulMsg(request, "修改单位基本信息成功！");
		}
		return mapping.findForward(forward);
	}

	/**
	 * 更新单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward updateEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		// 调用服务接口
		String forward = "viewEmployer"; // 查看单位信息
		Ab01 emp = new Ab01();
		String aab001 = fm.getAab001();
		String menuid = request.getParameter("menuId");
		try {
			setOperaUser(request, fm);
			// 设定经办信息
			LoginDTO loginForm = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			ClassHelper.copyProperties(fm, emp);
			emp.setAae011(loginForm.getBsc010());
			emp.setAae017(loginForm.getBsc001());
			emp.setAae036(DateUtil.getSystemCurrentTime());
			// 获取接口
			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("emp", emp);
			mapRequest.put("menuid", menuid);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.updateEmp(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功!");
				FindLog.insertLog(request, emp.getAab001(), "更新单位基本信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		// return mapping.findForward(forward);
		String mod = (String) request.getSession().getAttribute("mod");
		return go2Page(request, mapping, mod, "1");
	}

	/**
	 * 设定经办人信息
	 * 
	 * @param request
	 * @param fm
	 */
	private void setOperaUser(HttpServletRequest request, EmployerForm fm) {
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");

		fm.setAae011(dto.getBsc010());
		fm.setAae017(dto.getBsc001());
		fm.setAae036(DateUtil.getSystemCurrentTime("yyyy-MM-dd HH24:mi:ss"));
	}

	/**
	 * 新增单位基本信息入口
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward entryAddEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		request.getSession().setAttribute("employerForm", fm);
		// setOperaUser(request, fm);
		return mapping.findForward("addEmployer");
	}

	/**
	 * 新增单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward addEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "保存单位信息错误：";
		EmployerForm empForm = (EmployerForm) actionForm;
		ActionForward af = mapping.findForward("viewEmployer");
		Ab01 dto = new Ab01();
		try {
			ClassHelper.copyProperties(empForm, dto);
			// 初试化经办人、经办机构、经办时间
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			dto.setAae011(dto1.getBsc010());
			dto.setAae017(dto1.getBsc001());
			dto.setAae036(DateUtil.getSystemCurrentTime());
			// 获取接口
			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("empdto", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.addEmp(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			String getStr = "";
			Ab01 emp = null;
			if (returnValue.isSucessFlag()) {
				emp = (Ab01) ((HashMap) resEnv.getBody()).get("AB01");
				ClassHelper.copyProperties(emp, empForm);
				super.saveSuccessfulMsg(request, "新增单位基本信息成功!");
				FindLog.insertLog(request, emp.getAab001(), "新增单位基本信息");
			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, errorMsg[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			e.printStackTrace();
			return mapping.findForward("backspace");
		}
		// return af;
		String mod = (String) request.getSession().getAttribute("mod");
		return go2Page(request, mapping, mod, "1");
	}

	/**
	 * 变更单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward alterEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		// 调用服务接口
		String forward = "viewEmployer"; // 查看单位信息
		Ab01 emp = new Ab01();
		String aab001 = fm.getAab001();
		String menuid = request.getParameter("menuId");
		try {
			setOperaUser(request, fm);
			// 设定经办信息
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			ClassHelper.copyProperties(fm, emp);
			emp.setAae011(dto.getBsc010());
			emp.setAae017(dto.getBsc001());
			emp.setAae036(DateUtil.getSystemCurrentTime());
			// 获取接口
			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("emp", emp);
			mapRequest.put("menuid", menuid);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.alterEmp(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功!");
				FindLog.insertLog(request, emp.getAab001(), "变更单位基本信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		// return mapping.findForward(forward);
		String mod = (String) request.getSession().getAttribute("mod");
		return go2Page(request, mapping, mod, "1");
	}

	/**
	 * 注销单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward writeOffEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		String aab001 = request.getParameter("aab001");
		String menuid = request.getParameter("menuId");
		Ab01 ab01 = new Ab01();
		String aae119 = request.getParameter("aae119");
		if (aae119.equals("3")) {
			super.saveSuccessfulMsg(request, "该单位已经注销");
			return mapping.findForward("backspace");
		}
		if (null == aab001 || "".equalsIgnoreCase(aab001)) {
			super.saveSuccessfulMsg(request, "主键为空，请重新查询");
		} else {
			ab01.setAab001(aab001);
			ab01.setAae119("3");
			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			ab01.setFileKey("ab01_update");
			mapRequest.put("beo", ab01);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.writeOff(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "注销成功");
				FindLog.insertLog(request, aab001, "注销单位基本信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		// return mapping.findForward("employerList");
		String mod = (String) request.getSession().getAttribute("mod");
		return go2Page(request, mapping, mod, "1");

	}

	/**
	 * 进入被合并单位查询页面
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward initEnterEmploy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm pf = (EmployerForm) form;

		String newaab001 = request.getParameter("newaab001");
		pf.setNewaab001(newaab001);

		EmployMergeDTO dto = new EmployMergeDTO();

		try {
			ClassHelper.copyProperties(pf, dto);
			dto.setFileKey("bas01_008");

			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// mapRequest.put("cu", userform);
			requestEnvelop.setBody(mapRequest);
			// 调用接口的实现来进行查询
			ResponseEnvelop resEnv = facade.findCommon(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap map = (HashMap) resEnv.getBody();
				ArrayList list = (ArrayList) map.get("beo");
				ClassHelper.copyProperties(list.get(0), pf);
				ClassHelper.copyProperties(pf, form);
				request.getSession().setAttribute("UniteEmployForm", pf);

			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				request.setAttribute("errorMsg", errorMsg[3]);
				super.saveSuccessfulMsg(request, "该单位已注销,请核对!");
				return mapping.findForward("backspace");
			}

		} catch (AppException ae) {
			saveErrors(request, ae);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		}

		return mapping.findForward("initqueryemploy");
	}

	/**
	 * 查找被合并的单位
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward queryUniteOtherEmploy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EmployerForm ef = (EmployerForm) form;
		EmployMergeDTO dto = new EmployMergeDTO();
		ActionForward af = new ActionForward();
		try {
			ClassHelper.copyProperties(ef, dto);
			dto.setFileKey("bas01_009");
			String forjsp = "/basicinfo/initqueryemploy.jsp";
			String hql = queryEnterprise(dto);
			af = super.init(request, forjsp, hql, "2");
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (null == data || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没找到符合条件的数据!");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;

	}

	/**
	 * 合并单位 调用存储过程
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward saveUniteEmploy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm ef = (EmployerForm) form;
		EmployMergeDTO dto = new EmployMergeDTO();
		ActionForward af = new ActionForward();
		af = mapping.findForward("enterpriseList");
		try {
			ClassHelper.copyProperties(ef, dto);
			String newaab001 = dto.getNewaab001();
			String oldaab001 = dto.getOldaab001();
			if (newaab001 == null || oldaab001 == null || newaab001.equals("")
					|| "".equals(oldaab001)) {

				super.saveSuccessfulMsg(request, "主键为空，请重新查询");

			} else {
				HashMap map = new HashMap();
				map.put("beo", dto);

				EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");

				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();

				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(map);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.callPro(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "单位合并成功!");
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveErrors(request, new AppException(aa[3]));
					return mapping.findForward("backspace");
				}

			}

		} catch (AppException ex) {
			super.saveErrors(request, ex);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return go2Page(request, mapping, "basicinfo", "1");
	}

	/**
	 * 注销单位基本信息
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward delEmployer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EmployerForm fm = (EmployerForm) actionForm;
		String aab001 = request.getParameter("aab001");
		String menuid = request.getParameter("menuId");
		Ab01 ab01 = new Ab01();

		if (null == aab001 || "".equalsIgnoreCase(aab001)) {
			super.saveSuccessfulMsg(request, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(fm, ab01);
			EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", ab01);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delEmployer(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除成功");
				FindLog.insertLog(request, aab001, "删除单位基本信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		// return mapping.findForward("employerList");
		String mod = (String) request.getSession().getAttribute("mod");
		return go2Page(request, mapping, mod, "1");

	}

	/**
	 * 验证单位合并
	 * 
     * @param	mapping		表单映射器
     * @param	actionForm	表单数据实体
     * @param	request	    客户端的HTTP请求连接
     * @param	response   	客户端的HTTP返回连接
     * 
     * @return	单位基本信息页面
     * @throws	查询出错
     */
	public ActionForward validateUniteEmploy(ActionMapping mapping,
			ActionForm form, HttpServletRequest req,
			HttpServletResponse response) throws Exception {

		EmployerForm ef = (EmployerForm) form;
		EmployMergeDTO dto = new EmployMergeDTO();

		ClassHelper.copyProperties(ef, dto);
		String newaab001 = dto.getNewaab001();
		String oldaab001 = dto.getOldaab001();
		
		StringBuffer sb = new StringBuffer("");		
		
		if (newaab001 == null || oldaab001 == null || newaab001.equals("")
				|| "".equals(oldaab001)) {
			dto.setResult("alert('程序错误，未得到单位内码,请重新查询!');v=false;");
			sb.append(dto.getResult()).append("$");
		} else {
			dto.setResult("v=true;");		
			String retstr = "v=true;$";

			try {
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);				
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				requestEnvelop.setBody(mapRequest);
				EmployerFacade facade = (EmployerFacade) getService("EmployerFacade");
				ResponseEnvelop resEnv = facade.validateUniteEmploy(requestEnvelop);			
				EventResponse returnValue = new EventResponse();
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					
					retstr = (String) ((HashMap) resEnv.getBody()).get("retstr");					

				} else {
					dto.setResult("alert('程序出现未知错误');v=false;");
				}
			} catch (Exception e) {
				dto.setResult("alert('程序出现未知错误');v=false;");
			}
			sb.append(dto.getResult()).append("$")
			  .append(retstr);
		}

		ActionUtil.handleProxyRequest(response, sb.toString());
		return null;
	}

}