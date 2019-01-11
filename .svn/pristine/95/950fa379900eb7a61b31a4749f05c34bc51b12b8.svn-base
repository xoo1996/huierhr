/**
 * 项目: Rapid Application Development Framework
 * 所在包名称:   org.radf.manage.param.action
 * 类名称:      ParamAction.java
 * 创建者:      syg
 * 创建时间:     2006-11-14
 */
package org.radf.manage.param.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.manage.param.dto.AA01DTO;
import org.radf.manage.param.entity.Aa10;
import org.radf.manage.param.facade.ParamFacade;
import org.radf.manage.param.form.AA01Form;
import org.radf.manage.param.form.AA10Form;
import org.radf.plat.commons.ActionUtil;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 创建者 syg
 */
public class ParamAction extends ActionLeafSupport {

	private String className = this.getClass().getName();

	/**
	 * 增加aa01参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addaa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AA01Form af = (AA01Form) form;
		AA01DTO dto = new AA01DTO();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ActionForward forward = mapping.findForward("parameterlist");
		ClassHelper.copyProperties(af, dto);
		String menuId=(String)request.getSession().getAttribute("menuId");
		if("gs".equals(menuId))
		{
			dto.setAaa001(af.getAa001a());
			dto.setAae100("2");
		}else if("lc".equals(menuId))
		{   dto.setAaa001(af.getA001aa());
		    dto.setAaa005(af.getAa005a());
			dto.setAae100("3");
		}else{
			dto.setAae100("1");
		}
		dto.setFileKey("aa01_insert");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", dto);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.create(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(request, "保存成功!");
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			forward = mapping.findForward("backspace");
		}
		return go2Page(request,mapping,"sysmanager");
	}



	public ActionForward findaa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AA01Form af = (AA01Form) form;
		AA01DTO dto = new AA01DTO();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ActionForward forward = mapping.findForward("editparameter");
		ClassHelper.copyProperties(af, dto);
		dto.setFileKey("aa01_select");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", dto);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.find(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
					.get("beo");
			ClassHelper.copyProperties(list.get(0), af);
			String menuId=(String)request.getSession().getAttribute("menuId");
			if("gs".equals(menuId))
			{
				af.setAa001a(af.getAaa001());
				
			}else if("lc".equals(menuId))
			{   af.setA001aa(af.getAaa001());
			    af.setAa005a(af.getAaa005());
			}else{
			}
		} else {
			String[] aa = StringUtil
					.getAsStringArray(returnValue.getMsg(), "|");
			super.saveSuccessfulMsg(request, aa[3]);
			return mapping.findForward("backspace");
		}
		return forward;
	}

	public String getidtoname(String id, String field)
			throws java.lang.Exception {

		javax.servlet.ServletContext servletcontext = servlet
				.getServletContext();
		java.util.TreeMap treemap = (java.util.TreeMap) servletcontext
				.getAttribute(field.toUpperCase());
		Object obj = id;
		if (obj == null)
			obj = "";
		if (treemap != null)
			obj = treemap.get(obj);
		if (obj == null)
			obj = "";

		return obj.toString();
	}

	/**
	 * linke 进入代码表查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enteraa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "addparameter";
		return mapping.findForward(forward);
	}

	/**
	 * 修改aa01参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateaa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception  {
		AA01Form aa01form = (AA01Form) form;
		AA01DTO dto = new AA01DTO();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ActionForward forward = mapping.findForward("parameterlist");
		try {
			ClassHelper.copyProperties(aa01form, dto);
			String menuId=(String)request.getSession().getAttribute("menuId");
			if("gs".equals(menuId))
			{
				dto.setAaa001(aa01form.getAa001a());
				dto.setAae100("2");
			}else if("lc".equals(menuId))
			{   dto.setAaa001(aa01form.getA001aa());
			    dto.setAaa005(aa01form.getAa005a());
				dto.setAae100("3");
			}else
			{
				dto.setAae100("1");
			}
			
            dto.setFileKey("aa01_update");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "保存成功!");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				forward = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return go2Page(request,mapping,"sysmanager");
	}

	/**
	 * 撤除aa01参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteaa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception  {
		AA01Form aa01form = (AA01Form) form;
		AA01DTO dto = new AA01DTO();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		try {
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			ClassHelper.copyProperties(aa01form, dto);
			String menuId=(String)request.getSession().getAttribute("menuId");
			if("gs".equals(menuId))
			{
				dto.setAaa001(aa01form.getAa001a());
				dto.setAae100("2");
			}else if("lc".equals(menuId))
			{   dto.setAaa001(aa01form.getA001aa());
			    dto.setAaa005(aa01form.getAa005a());
				dto.setAae100("3");
			}else
			{
				dto.setAae100("1");
			}
            dto.setFileKey("aa01_delete");
			// person.setFileKey("ac01_insert");
			mapRequest.put("beo", dto);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.remove(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除成功!");
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
		return go2Page(request,mapping,"sysmanager");
	}

	/**
	 * 查询aa01信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward selectaa01(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AA01Form aa01form = (AA01Form) form;
		String menuId=(String)request.getParameter("menuId");
		if(menuId!=null && !"".equals(menuId))
		   request.getSession().setAttribute("menuId",menuId);
		menuId=(String)request.getSession().getAttribute("menuId");
		AA01DTO dto = new AA01DTO();
		ActionForward af = new ActionForward();
		ClassHelper.copyProperties(aa01form, dto);
		
		if("gs".equals(menuId))
		{
			dto.setAaa001(aa01form.getAa001a());
			dto.setAae100("2");
			dto.setFileKey("sys06_002");
		}else if("lc".equals(menuId))
		{   dto.setAaa001(aa01form.getA001aa());
		    dto.setAaa005(aa01form.getAa005a());
			dto.setAae100("3");
			dto.setFileKey("sys06_003");
		}else{
			dto.setAae100("1");
			dto.setFileKey("sys06_000");
		}
		String forward = "/sysmanager/param/ParameterList.jsp";
		
		String hql = queryEnterprise(dto);
		af = super.init(request, forward, hql);
		List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
		if (data == null || data.size() == 0) {
			super.saveSuccessfulMsg(request, "没符合条件的数据！");
		}
		return af;
	}

	/**
	 * 增加aa10参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addaa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AA10Form af = (AA10Form) form;
		af.setAaa107(af.getAae140());
		Aa10 aa10 = new Aa10();
		ParamFacade facade = (ParamFacade) getService("ParamFacade");
		ActionForward forward = mapping.findForward("codelist");
		try {
			ClassHelper.copyProperties(af, aa10);
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.AddAa10(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
                TreeMap treemap = (TreeMap)servlet.getServletContext().getAttribute(aa10.getAaa100());
                if(treemap != null)
                    treemap.put(aa10.getAaa102(), aa10.getAaa103());//往AA10的缓存中增加数据
				super.saveSuccessfulMsg(request, "保存成功!");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				forward = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return go2Page(request,mapping,"sysmanager","1");
	}

	/**
	 * 修改aa01参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateaa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AA10Form af = (AA10Form) form;
		af.setAaa107(af.getAae140());
		Aa10 aa10 = new Aa10();
		ParamFacade facade = (ParamFacade) getService("ParamFacade");
		ActionForward forward = mapping.findForward("codelist");
		try {
			ClassHelper.copyProperties(af, aa10);
			aa10.setFileKey("aa10_update");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			// person.setFileKey("ac01_insert");
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifAa10(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
                TreeMap treemap = (TreeMap)servlet.getServletContext().getAttribute(aa10.getAaa100());
                if(treemap != null)
                    treemap.put(aa10.getAaa102(), aa10.getAaa103());//修改Aa10缓存中数据
				super.saveSuccessfulMsg(request, "保存成功!");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				forward = mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return go2Page(request,mapping,"sysmanager","1");
	}

	/**
	 * 撤除aa01参数信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteaa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AA10Form af = (AA10Form) form;
		Aa10 aa10 = new Aa10();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ClassHelper.copyProperties(af, aa10);
		try {
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			aa10.setFileKey("aa10_delete");
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.remove(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				  TreeMap treemap = (TreeMap)servlet.getServletContext().getAttribute(aa10.getAaa100());
                  if(treemap != null)
                      treemap.remove(aa10.getAaa102());//删除AA10缓存中数据
				super.saveSuccessfulMsg(request, "删除成功!");
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
		return go2Page(request,mapping,"sysmanager");
	}

	/**
	 * 查询aa01信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward selectaa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AA10Form aa10form = (AA10Form) form;
		if("".equals(aa10form.getAae140())||aa10form.getAae140()==null)
		{
			aa10form.setAae140("bas");
		}
		aa10form.setAaa107(aa10form.getAae140());
		
		Aa10 aa10 = new Aa10();
		ActionForward af = new ActionForward();
		try {
			ClassHelper.copyProperties(aa10form, aa10);
			
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			aa10.setFileKey("sys06_004");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
                .get("beo");
				if(list!=null)
				{TreeMap treemap =new TreeMap();
				 for(int i=0;i<list.size();i++)
				 {
					 Aa10 aa10lb=new Aa10();
					 ClassHelper.copyProperties(list.get(i), aa10lb);
					 treemap.put(aa10lb.getAaa100(), aa10lb.getAaa101());//往缓存中增加数据
					 
				 }
				 servlet.getServletContext().setAttribute("AAA100",treemap);
					
				}	
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
			
			
			
			String forward = "/sysmanager/param/CodeList.jsp";
			aa10.setFileKey("sys06_001");
			String hql = queryEnterprise(aa10);
			af = super.init(request, forward, hql);
			List data = (List) request.getAttribute(GlobalNames.QUERY_DATA);
			if (data == null || data.size() == 0) {
				super.saveSuccessfulMsg(request, "没符合条件的数据！");
			}
		} catch (Exception e) {
			super.saveErrors(request, new Exception("查询失败"));
			return mapping.findForward("backspace");
		}
		return af;
	}
	public ActionForward selectdmdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AA10Form aa10form = (AA10Form) form;
		if("".equals(aa10form.getAae140())||aa10form.getAae140()==null)
		{
			aa10form.setAae140("bas");
		}
		aa10form.setAaa107(aa10form.getAae140());
		StringBuffer sb = new StringBuffer();
		sb.append("<select style='font-size:12px' name='aaa100' id='aaa100' class='select' label='代码类别' >");
		sb.append("<option value='' selected> 请选择</option>");
		Aa10 aa10 = new Aa10();
		try {
			ClassHelper.copyProperties(aa10form, aa10);
			
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			aa10.setFileKey("sys06_004");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			//<option value='A001AA' >流程类别1</option>
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
                .get("beo");
				if(list!=null)
				{
				 for(int i=0;i<list.size();i++)
				 {
					 Aa10 aa10lb=new Aa10();
					 ClassHelper.copyProperties(list.get(i), aa10lb);
					 sb.append("<option value='"+aa10lb.getAaa100()+"'>"+aa10lb.getAaa101()+"</option>");					 
				 }
				}	
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
			sb.append("</select>");
			ActionUtil.handleProxyRequest(response,sb.toString());
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	public ActionForward findaa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AA10Form af = (AA10Form) form;
		Aa10 aa10 = new Aa10();
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		ActionForward forward = mapping.findForward("editcodelist");
		ClassHelper.copyProperties(af, aa10);
		try {
			aa10.setFileKey("aa10_select");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", aa10);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ArrayList list = (ArrayList) ((HashMap) resEnv.getBody())
                .get("beo");
				ClassHelper.copyProperties(list.get(0), af);
				af.setAae140(af.getAaa107());
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
		return forward;
	}
	/**
	 * linke 进入代码表查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enteraa10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "addcodelist";
		return mapping.findForward(forward);
	}
}
