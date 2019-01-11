package org.radf.manage.department.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.jfree.chart.title.Title;
import org.radf.login.dto.LoginDTO;
import org.radf.manage.department.facade.DepartmentFacade;
import org.radf.manage.department.form.DeptForm;
import org.radf.manage.department.form.Sc02Form;
import org.radf.manage.entity.Sc01;
import org.radf.manage.entity.Sc02;
import org.radf.manage.entity.Sc03;
import org.radf.manage.entity.Sc04;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.cp.a.a;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalNames;

public class DepartmentAction extends ActionLeafSupport {
	/**
	 * 获取当前部门所在的树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadDepartmentTree(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		DeptForm deptform = (DeptForm) form;
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		String sqlkey = request.getParameter("sqlkey");
		mapRequest.put("beo", sqlkey);
		requestEnvelop.setBody(mapRequest);
		DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
		ResponseEnvelop resEnv = facade.findsc01andsc05(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			HashMap map = (HashMap) returnValue.getBody();
			ArrayList list = (ArrayList) map.get("list");
			a._mthif(request, list);
		}
		return mapping.findForward("sc01Tree");
	}

	/**
	 * 根据部门编号获取部门信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findByKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) form;
		Sc01 sc01 = new Sc01();
		String s = request.getParameter("groupid");
		if (s == null || s.equals("00")) {
			if (deptform.getTreeid() == null || deptform.getTreeid().equals(""))
				deptform.setTreeid("00");
			if (deptform.getAab003() == null || deptform.getAab003().equals(""))
				deptform.setAab003("00");
			if (deptform.getAab300() == null || deptform.getAab300().equals(""))
				deptform.setAab300("机构管理");
		} else {
			if (s.substring(0, 1).equals("G")) {
				deptform.setTreeid(s);
				//ClassHelper.copyProperties(deptform, sc01);
				sc01.setFileKey("sys01_005");
				sc01.setAab003(s.substring(s.indexOf("_") + 1));
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", sc01);
				requestEnvelop.setBody(mapRequest);
				DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
				ResponseEnvelop resEnv = facade.findsc01andsc04(requestEnvelop);
				EventResponse returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					HashMap map1 = (HashMap) returnValue.getBody();
					ArrayList list1 = (ArrayList) map1.get("list");
					if (list1 != null && list1.size() > 0)
						ClassHelper.copyProperties(list1.get(0), deptform);
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
			} else {
				String forward="edit";
				deptform.setTreeid(s);
				Sc04 sc04 = new Sc04();
				//ClassHelper.copyProperties(deptform, sc04);
				sc04.setBsc008(s.substring(s.indexOf("_") + 1));
				sc04.setFileKey("SC04_select");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", sc04);
				requestEnvelop.setBody(mapRequest);
				DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
				ResponseEnvelop resEnv = facade.findsc04(requestEnvelop);
				EventResponse returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					HashMap map1 = (HashMap) returnValue.getBody();
					ArrayList list1 = (ArrayList) map1.get("list");
					if (list1 != null && list1.size() > 0)
						ClassHelper.copyProperties(list1.get(0), deptform);
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
				request.getSession().setAttribute("deptForm", deptform);
				if (s.substring(0, 1).equals("K")) {forward="editks";}
				return mapping.findForward(forward);
			}
		}
		request.getSession().setAttribute("deptForm", deptform);
		return mapping.findForward("edit");
	}

//	/**
//	 * 增加时候如果机构编号大于4并且最后面是01的那么是增加科室
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward findDept(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DeptForm deptform = (DeptForm) form;
//		ActionForward af = new ActionForward();
//		af = new ActionForward("/dept/deptaddmod.jsp");
//		if (deptform.getTreeid() != null) {
//			String treeid = deptform.getTreeid();
//			if ((treeid.substring(treeid.indexOf("_") + 1).length() >= 4
//					&& treeid.substring(treeid.indexOf("_") + 1).endsWith("01")&&treeid.substring(treeid.indexOf("_") + 1).length() <8)||treeid.substring(treeid.indexOf("_") + 1).length() == 8) {
//				deptform.setBsc008(null);
//				request.getSession().setAttribute("deptForm", deptform);
//				af = new ActionForward("/dept/ksaddmod.jsp");
//			}else
//			{
//				deptform.setBsc001(null);
//			}
//		}
//		return af;
//	}
	
	/**
	 * 增加时候如果机构编号大于4并且最后面是01的那么是增加科室（复制1）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) form;
		ActionForward af = new ActionForward();
		af = new ActionForward("/dept/deptaddmod.jsp");
//		if (deptform.getTreeid() != null) {
//			String treeid = deptform.getTreeid();
//			if ((treeid.substring(treeid.indexOf("_") + 1).length() >= 4
//					&& treeid.substring(treeid.indexOf("_") + 1).endsWith("01")&&treeid.substring(treeid.indexOf("_") + 1).length() <8)||treeid.substring(treeid.indexOf("_") + 1).length() == 8) {
//				deptform.setBsc008(null);
//				request.getSession().setAttribute("deptForm", deptform);
//				af = new ActionForward("/dept/ksaddmod.jsp");
//			}else
//			{
//				deptform.setBsc001(null);
//			}
//		}
		deptform.setBsc001(null);
		request.getSession().setAttribute("deptForm", deptform);
		return af;
	}
	/**
	 * 增加时候如果机构编号大于4并且最后面是01的那么是增加科室（复制2）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDept1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) form;
		ActionForward af = new ActionForward();
		af = new ActionForward("/dept/deptaddmod.jsp");
		if (deptform.getTreeid() != null) {
			String treeid = deptform.getTreeid();
			if (treeid.substring(treeid.indexOf("_") + 1).length() <=8) {
				deptform.setBsc008(null);
				request.getSession().setAttribute("deptForm", deptform);
				af = new ActionForward("/dept/ksaddmod.jsp");
			}else
			{
				deptform.setBsc001(null);
			}
		}
		return af;
	}
	/**
	 * 保存机构信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc01 sc01 = new Sc01();
		ClassHelper.copyProperties(deptform, sc01);
		sc01.setAae100("1");
		sc01.setAae011(dto.getBsc010());
		sc01.setAae036(dto.getAae036());
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("sc01", sc01);
		requestEnvelop.setBody(mapRequest);
		DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
		ResponseEnvelop resEnv = facade.addSc01(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "添加机构成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.setAttribute("refreshTree", "true");
		request.setAttribute("treeid", deptform.getTreeid());
		return new ActionForward("/deptAction.do?method=findByKey&groupid="
				+ deptform.getTreeid());
	}

	/**
	 * 保存科室信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveKs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc04 sc04 = new Sc04();
		sc04.setAae100("1");
		sc04.setBsc008(deptform.getBsc008());
		sc04.setBsc005(deptform.getBsc005());
		sc04.setBsc009(deptform.getBsc009());
		sc04.setBsc001(deptform.getTreeid().substring(
				deptform.getTreeid().indexOf("_") + 1));
		sc04.setAae011(dto.getBsc010());
		sc04.setAae036(dto.getAae036());
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("sc04", sc04);
		requestEnvelop.setBody(mapRequest);
		DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
		ResponseEnvelop resEnv = facade.addSc04(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "添加科室成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		request.setAttribute("refreshTree", "true");
		request.setAttribute("treeid", deptform.getTreeid());
		return new ActionForward("/deptAction.do?method=findByKey&groupid="
				+ deptform.getTreeid());
	}

	/**
	 * 注销机构和科室
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhuxiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptForm deptform = (DeptForm) request.getSession().getAttribute(
				"deptForm");
		if (deptform.getTreeid().substring(0, 1).equals("G")) {
			
			Connection con = null;
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String queryhql = "";// 存在子机构查询
			String sn=deptform.getTreeid().substring(2);
			queryhql="select * from sc01 where aae100='1' and aab003 like '"+sn+"%'";
			List result = (Vector) DBUtil.querySQL(con,queryhql);
			if(null != result && result.size() !=0 && result.size()<=1)
			{
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
				Connection con1 = null;
				con1 = DBUtil.getConnection();
				queryhql="select * from sc04 where aae100='1' and bsc001='"+deptform.getBsc001()+"'";
				List result1 = (Vector) DBUtil.querySQL(con1,queryhql);
				DBUtil.rollback(con1);
				DBUtil.closeConnection(con1);
				if(null == result1 || result1.size() ==0)
				{
				}else{
					super.saveSuccessfulMsg(request, "存在子科室，请先删除子科室！!");
					return  mapping.findForward("backspace");
				}
				
			}else {
				super.saveSuccessfulMsg(request, "存在子机构，请先删除子机构！!");
				return  mapping.findForward("backspace");
			}
			
			Sc01 sc01 = new Sc01();
			sc01.setBsc001(deptform.getBsc001());
			sc01.setFileKey("sys01_002");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", sc01);
			requestEnvelop.setBody(mapRequest);
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			ResponseEnvelop resEnv = facade.remove(requestEnvelop);
			EventResponse returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				sc01.setFileKey("sys01_004");
				mapRequest.put("beo", sc01);
				requestEnvelop.setBody(mapRequest);
				resEnv = facade.remove(requestEnvelop);
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "机构注销成功！");
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} else if (deptform.getTreeid().substring(0, 1).equals("K")) {
			Sc04 sc04 = new Sc04();
			sc04.setBsc008(deptform.getBsc008());
			sc04.setFileKey("sys01_003");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", sc04);
			requestEnvelop.setBody(mapRequest);
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			ResponseEnvelop resEnv = facade.remove(requestEnvelop);
			EventResponse returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "科室注销成功！");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}

		}
		request.setAttribute("refreshTree", "true");
		request.setAttribute("treeid", deptform.getTreeid());
		return new ActionForward("/deptAction.do?method=findByKey&groupid=00");
	}

	/**
	 * 获取机构信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward DepartmentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/dept/deptlist.jsp";
		ActionForward af = new ActionForward(forward);
		DeptForm deptform = (DeptForm) form;
		Sc01 sc01 = new Sc01();
		ClassHelper.copyProperties(deptform, sc01);
		sc01.setFileKey("sys01_006");
		String hql = queryEnterprise(sc01);
		af = super.init(request, forward, hql);
		return af;
	}

	/**
	 * 获取机构别名信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findSc02(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/dept/sc02list.jsp";
		ActionForward af = new ActionForward(forward);
		Sc02Form deptform = (Sc02Form) form;
		Sc02 sc02 = new Sc02();
		deptform.setBsc001(request.getParameter("bsc001"));
		ClassHelper.copyProperties(deptform, sc02);
		sc02.setFileKey("sys01_007");
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		sc02.setAae017(dto.getBsc001());
		String hql = queryEnterprise(sc02);
		af = super.init(request, forward, hql,"2");
		request.setAttribute("bsc001",request.getParameter("bsc001"));
		return af;
	}

	/**
	 * 机构别名增加页面跳转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward Sc02Add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "Sc02Add";
		request.setAttribute("bsc001",request.getSession().getAttribute("bsc001"));
		return mapping.findForward(forward);
	}
	/**
	 * 保存科室信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSc02(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Sc02Form deptform = (Sc02Form) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc02 sc02 = new Sc02();
		sc02.setBsc001((String)request.getSession().getAttribute("bsc001"));
		sc02.setBsc004(deptform.getBsc004());
		sc02.setBsc005(deptform.getBsc005());
		sc02.setAae011(dto.getBsc010());
		sc02.setAae017(dto.getBsc001());
		sc02.setAae036(dto.getAae036());
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc02);
		requestEnvelop.setBody(mapRequest);
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ResponseEnvelop resEnv = facade.create(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "添加机构别名成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return new ActionForward("/sc02Action.do?method=findSc02&bsc001="+sc02.getBsc001());
	}
	/**
	 * 保存科室信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSc02(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Sc02Form deptform = (Sc02Form) form;
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc02 sc02 = new Sc02();
		sc02.setBsc001((String)request.getSession().getAttribute("bsc001"));
		sc02.setBsc004(deptform.getBsc004());
		sc02.setBsc005(deptform.getBsc005());
		sc02.setAae011(dto.getBsc010());
		sc02.setAae036(dto.getAae036());
		sc02.setFileKey("SC02_delete");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc02);
		
		requestEnvelop.setBody(mapRequest);
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ResponseEnvelop resEnv = facade.remove(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "删除机构别名成功！");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return new ActionForward("/sc02Action.do?method=findSc02&bsc001="+sc02.getBsc001());
	}
	
	/**
	 * 查询机构统计信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward deptGroupQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String forward = "/sysmanager/dept/DeptGroupQuery.jsp";
		ActionForward af = new ActionForward(forward);
		DeptForm deptForm = (DeptForm) form;
		Sc03 sc03 = new Sc03();
		ClassHelper.copyProperties(deptForm, sc03);
		sc03.setBsc007(sc03.getBsc006());
		sc03.setBsc006(null);
		sc03.setFileKey("sys01_008");
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		sc03.setAae017(dto.getBsc001());
		String hql = queryEnterprise(sc03);
		af = super.init(req, forward, hql);

		TreeMap treemap = new TreeMap();
		ArrayList list=(ArrayList)req.getAttribute(GlobalNames.QUERY_DATA);
		int num=0;
		if(list!=null)
		{
			for(int i=1;i<=32;i++)
			{   boolean boo=false;
				for (int j=0;j<list.size();j++)
				{
					ClassHelper.copyProperties(list.get(j),sc03);
					if((i+"").equals(sc03.getBsc007()))
					{
						boo=true;
						break;
					}
				}
				if(boo==false)
				{   num=num+1; 
				    if(num<=10){
						if(i<10)
						{
							treemap.put("0"+i, i+"");
						}else
						{
							treemap.put(i+"", i+"");
						}
				    }else
					{
						break;
					}
					
					
				}
			}
		}else
		{
			for(int i=1;i<=10;i++)
			{   
				if(i<10)
				{
					treemap.put("0"+i, i+"");
				}else
				{
					treemap.put(i+"", i+"");
				}
			}
		}
		
		req.getSession().getServletContext().setAttribute("BS007C", treemap);
		
		
		// 检查查询结果数据是否存在
		if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			String msg = "没有查询到符合条件的记录！";
			super.saveSuccessfulMsg(req, msg);
		}

		return af;
	}
	
	/**
	 * 查看修改机构统计信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward deptGroupSav(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String function = req.getParameter("function");
		String bsc006 = req.getParameter("bs006c");
		String bsc007 = req.getParameter("bsc007");
		String forward = "/dept/SaveDeptGroup.jsp";

		DeptForm deptForm = (DeptForm) form;
		deptForm.setBs007c(bsc007);
		deptForm.setBsc006(bsc006);
		Sc03 sc03 = new Sc03();
		sc03.setBsc006(bsc006);

		if ("add".equalsIgnoreCase(function)) {
			// 传给修改页面空白的 FormBean
			req.getSession().setAttribute("DeptForm", new DeptForm());
			forward = "/dept/SaveDeptGroup.jsp?function=add";
		} else if ("mod".equalsIgnoreCase(function)) {

			forward = "/dept/SaveDeptGroup.jsp?function=mod";

		}

		ClassHelper.copyProperties(deptForm, sc03);
		sc03.setFileKey("sys01_009");

		// 获取接口
		FACADESupport facadeSupp = (FACADESupport) getService("FACADESupport");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc03);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facadeSupp.find(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
			if("add".equalsIgnoreCase(function))
			{
				deptForm = new DeptForm();
				ClassHelper.copyProperties(deptForm, form);
			}else
			{
				if(list!=null)
				{
					ClassHelper.copyProperties(list.get(0), deptForm);
					deptForm.setBs007c(deptForm.getBsc007());
					
				}
			}
			
			
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(req, aa[3]);
			return mapping.findForward("backspace");
		}
		
	
		//获得 sc01 机构表的数据
		Sc01 sc01=new Sc01();
		sc01.setBsc003(bsc007);
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		sc01.setBsc001(dto.getBsc001());
		sc01.setAab003(dto.getAab003());
		mapRequest.put("beo", sc01);
		mapRequest.put("function", function);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法 find sc01
		DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
		resEnv = facade.findSc01(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List list = (ArrayList) ((HashMap) resEnv.getBody()).get("sc01List");
			List list2 = (ArrayList) ((HashMap) resEnv.getBody()).get("sc01List2");
			if (list!= null) {
				req.setAttribute("sc01List", list);
			}
			if (list2!= null) {
				req.setAttribute("sc01List2", list2);
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(req, aa[3]);
			return mapping.findForward("backspace");
		}

		return new ActionForward(forward);
	}
	public ActionForward deptGroupDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String function = request.getParameter("function");
		String bsc006 = request.getParameter("bsc006");
		String bsc007 = request.getParameter("bsc007");
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
		Sc03 sc03 = new Sc03();
		sc03.setBsc006(bsc006);
		sc03.setBsc007(bsc007);
		Sc01 sc01 = new Sc01();
		sc01.setBsc003(bsc007);
		sc01.setFileKey("sys01_014");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sc01);		
		requestEnvelop.setBody(mapRequest);
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ResponseEnvelop resEnv = facade.modify(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			sc03.setFileKey("sys01_017");
			requestEnvelop = new RequestEnvelop();
			mapRequest.put("beo", sc03);		
			requestEnvelop.setBody(mapRequest);			
			resEnv = facade.remove(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除虚拟机构成功！");
			} else {
				String[] aa = StringUtil
						.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		
		
		return go2Page(request,mapping,"sysmanager","1");
	}
	/**
	 * 保存修改机构表统计信息sc01以及机构统计表sc03
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifySc01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {

		DeptForm deptForm = (DeptForm) form;
		Sc03 sc03 = new Sc03();
		String function = request.getParameter("function");
		String groupListStr = request.getParameter("groupList");
		String bsc006 = request.getParameter("bsc006");
		String bsc007 = request.getParameter("bsc007");
		String forward = "/deptAction.do?method=deptGroupSav&function=" + "mod";
		String[] groupList = StringUtil.getAsStringArray(groupListStr, ";");


        if("0".equalsIgnoreCase(bsc007.substring(0,1)))
		{
			bsc007=bsc007.substring(1);
		}
		ClassHelper.copyProperties(deptForm, sc03);
		sc03.setBsc006(bsc006);
		sc03.setBsc007(bsc007);
		LoginDTO loginform = (LoginDTO) request.getSession().getAttribute(
		"LoginDTO");
		sc03.setAae011(loginform.getBsc010()); // 操作人
		sc03.setAae017(loginform.getBsc001());
		sc03.setAae036(DateUtil.getSystemCurrentTime()); // 操作时间
		if ("add".equals(function)) {

			
			forward = "/deptAction.do?method=deptGroupQuery";

		}

		// 获取接口
		DepartmentFacade facade = (DepartmentFacade) getService("DepartmentFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();

		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("sc03", sc03);
		mapRequest.put("function", function);
		mapRequest.put("groupList", groupList);

		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);

		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.modifySc01NSc03(requestEnvelop);

		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			TreeMap treemap = (TreeMap)servlet.getServletContext().getAttribute("BSC006");
            if(treemap != null)
                treemap.put(sc03.getBsc007(),sc03.getBsc006());//往bsc006的缓存中增加数据
			super.saveSuccessfulMsg(request, "保存成功!");
		} else {

			String[] msg = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveSuccessfulMsg(request, msg[3]);

		}

		//return new ActionForward(forward);
		return go2Page(request,mapping,"sysmanager","1");
	}
}