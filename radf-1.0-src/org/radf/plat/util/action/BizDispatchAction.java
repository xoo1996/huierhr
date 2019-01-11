// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.util.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import org.radf.login.common.LoginUtil;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ExceptionUtil;
import org.radf.plat.util.exception.ManageInputException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalNames;

public abstract class BizDispatchAction extends Action {

	private static LogHelper sysLog;

	protected Class clazz;

	protected static Logger log;

	protected static MessageResources messages = MessageResources
			.getMessageResources("org.apache.struts.actions.LocalStrings");

	protected HashMap methods;

	protected Class types[];

	/**
	 * 构造函数
	 */
	public BizDispatchAction() {
		sysLog = new LogHelper(
				org.radf.plat.util.action.BizDispatchAction.class.getName());
		clazz = getClass();
		methods = new HashMap();
		types = (new Class[] { org.apache.struts.action.ActionMapping.class,
				org.apache.struts.action.ActionForm.class,
				javax.servlet.http.HttpServletRequest.class,
				javax.servlet.http.HttpServletResponse.class });
	}

	/**
	 * 只有在action中操作错误下使用 保存错误日志参数只有两个 如果aexception为null不保存日志
	 * 
	 * @param httpservletrequest
	 * @param aexception
	 */
	public void saveErrors(HttpServletRequest httpservletrequest,
			Exception aexception[]) {
		if (aexception == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			saveLog(httpservletrequest, aexception, null);
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					aexception);
		}
	}

	/**
	 * 保存错误日志参数只有三个 如果aexception为null不保存日志
	 * 
	 * @param httpservletrequest
	 * @param aexception
	 * @param as
	 */
	public void saveErrors(HttpServletRequest httpservletrequest,
			Exception aexception[], String as[]) {
		if (as == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			saveLog(httpservletrequest, aexception, as);
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					as);
		}
	}

	/**
	 * 保存错误日志参数只有两个 如果aexception为null不保存日志
	 * 
	 * @param httpservletrequest
	 * @param exception
	 */

	public void saveErrors(HttpServletRequest httpservletrequest,
			Exception exception) {
		Exception aexception[] = (Exception[]) null;
		if (exception != null)
			aexception = (new Exception[] { exception });
		if (exception == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			saveLog(httpservletrequest, aexception, null);
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					aexception);
		}
	}

	/**
	 * 保存错误日志参数只有三个 把错误信息放在httpservletrequest中 如果aexception为null不保存日志
	 * 
	 * @param httpservletrequest
	 * @param exception
	 * @param s
	 */
	public void saveErrors(HttpServletRequest httpservletrequest,
			Exception exception, String s) {
		String as[] = { s };
		if (exception == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			saveLog(httpservletrequest, new Exception[] { exception }, as);
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					as);
		}
	}

	/**
	 * 不保存错误信息 把错误信息放在httpservletrequest中在jsp中能获取 以弹出提示筐
	 * 
	 * @param httpservletrequest
	 * @param s
	 */
	public void saveSuccessfulMsg(HttpServletRequest httpservletrequest,
			String s) {
		if (s == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			String as[] = { s };
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					as);
		}
	}

	
	
	/**
	 * 页面跳转错误
	 * 
	 * @param httpservletrequest
	 * @param s
	 * @param s1
	 */
	public void bizForward(HttpServletRequest httpservletrequest, String s,
			String s1) {
		if (s == null) {
			httpservletrequest
					.removeAttribute(org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE);
		} else {
			String as[] = { s };
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					as);
			httpservletrequest.setAttribute(
					org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE,
					s1);
		}
	}

	/**
	 * 错误日志保存
	 * 
	 * @param httpservletrequest
	 * @param aexception
	 * @param as
	 */
	private void saveLog(HttpServletRequest httpservletrequest,
			Exception aexception[], String as[]) {
		if (aexception == null)
			return;
		String as1[] = new String[aexception.length];
		RequestEnvelopHead loginform = (RequestEnvelopHead) httpservletrequest.getSession()
				.getAttribute("RequestEnvelopHead");
		if (loginform == null)
			return;
		if (as != null) {
			for (int i = 0; i < aexception.length; i++)
				as1[i] = as[i] + ": " + aexception[i].toString();

		} else {
			for (int j = 0; j < aexception.length; j++) {
				as1[j] = aexception[j].toString();
				if (aexception[j].getCause() != null)
					as1[j] = as1[j] + ":  "
							+ aexception[j].getCause().toString();
			}

		}
		for (int k = 0; k < as1.length; k++)
			sysLog.log(as1[k]);

	}

	/**
	 * 可以记录操作模块的信息
	 * 
	 * @param httpservletrequest
	 * @param actionform
	 */
	public final void doStart(HttpServletRequest httpservletrequest,
			ActionForm actionform) {
		
	}

	/**
	 * 记录操作结束模块的信息
	 * 
	 * @param httpservletrequest
	 * @param actionform
	 */
	public final void doEnd(HttpServletRequest httpservletrequest,
			ActionForm actionform) {
	}

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = actionmapping.getParameter();
		if (s == null) {
			String s1 = messages.getMessage("dispatch.handler", actionmapping
					.getPath());
			log.error(s1);
			httpservletresponse.sendError(500, s1);
			return null;
		} else {
			String s2 = httpservletrequest.getParameter(s);
			return dispatchMethod(actionmapping, actionform,
					httpservletrequest, httpservletresponse, s2);
		}
	}

	/**
	 * 如果当前method为空或者是null 页面指向400找不到页面
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	protected ActionForward unspecified(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = messages.getMessage("dispatch.parameter", actionmapping
				.getPath(), actionmapping.getParameter());
		log.error(s);
		httpservletresponse.sendError(400, s);
		return null;
	}

	/**
	 * 最基本方法实现action类
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param s
	 * @return
	 * @throws Exception
	 */
	protected ActionForward dispatchMethod(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, String s) throws Exception {
		if (s == null)
			return unspecified(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		Method method = null;
		try {
			method = getMethod(s);
		} catch (NoSuchMethodException nosuchmethodexception) {
			String s1 = messages.getMessage("dispatch.method", actionmapping
					.getPath(), s);
			log.error(s1, nosuchmethodexception);
			httpservletresponse.sendError(500, s1);
			return null;
		}
//		String s1 = httpservletrequest.getServletPath();
//		httpservletrequest.setAttribute("method",s);
//		httpservletrequest.setAttribute("servletpath",method.getDeclaringClass());
//		StringBuffer stringbuffer = new StringBuffer(s1);
//		 if(!"commonQuery".equals(s)){
//			 stringbuffer.append("?method=").append(s); 
//		 }
//		 String menuId = httpservletrequest.getParameter("menuId");
//		 if(menuId != null)
//	            stringbuffer.append("&menuId=").append(menuId);
//		  if(stringbuffer.indexOf("?") < 0)
//	        {
//	            int i = stringbuffer.indexOf("&");
//	            if(i >= 0)
//	                stringbuffer.replace(i, i + 1, "?");
//	        }
//		  String s5 = stringbuffer.toString();
//		  //获取菜单结果集
//		  ArrayList listmenu=(ArrayList)httpservletrequest.getSession().getAttribute(GlobalNames.FUNCTION_LIST);
//		  if(listmenu!=null){
//			  for(Iterator iterator = listmenu.iterator(); iterator.hasNext();)
//		        {
//				  //将当前操作人的操作菜单给数据此方法很重要当前暂时这么处理
//				  
//				  Sc08 functiondto = (Sc08)iterator.next();
//				 if( functiondto.getBsc017()==null)
//					 functiondto.setBsc017("");
//				  if(s5.toLowerCase().equals(functiondto.getBsc017().toLowerCase()))
//		            {
//					  //帮当前操作人员所操作的菜单放入userfunctiondto
//					  httpservletrequest.getSession().setAttribute("userfunctiondto",functiondto);
//					  String type = functiondto.getBsc021();
//				        if(!GlobalNames.MENU_BUTTON.equals(type))
//				            httpservletrequest.getSession().setAttribute(GlobalNames.NAVIGATION, a.a(httpservletrequest, functiondto.getBsc016()));
//						
//		            }
//				  
//		        }
//		  }
		ActionForward actionforward = null;
		try {
			Object aobj[] = { actionmapping, actionform, httpservletrequest,
					httpservletresponse };
			// 获取人员信息
			LoginDTO dto = (LoginDTO) httpservletrequest.getSession().getAttribute("LoginDTO");
			// 如果人员信息不为空 可以记录操作日志
			if (dto != null) {
				doStart(httpservletrequest, actionform);
				actionforward = (org.apache.struts.action.ActionForward) method
						.invoke(this, aobj);
				doEnd(httpservletrequest, actionform);
			} else {
				actionforward = (org.apache.struts.action.ActionForward) method
						.invoke(this, aobj);
			}
		} catch (ClassCastException classcastexception) {
			String s2 = messages.getMessage("dispatch.return", actionmapping
					.getPath(), s);
			log.error(s2, classcastexception);
			httpservletresponse.sendError(500, s2);
			return null;
		} catch (IllegalAccessException illegalaccessexception) {
			String s3 = messages.getMessage("dispatch.error", actionmapping
					.getPath(), s);
			log.error(s3, illegalaccessexception);
			httpservletresponse.sendError(500, s3);
			return null;
		} catch (InvocationTargetException invocationtargetexception) {
			Throwable throwable = invocationtargetexception
					.getTargetException();
			if (throwable instanceof Exception) {
				HashMap map=new HashMap();
				map.put("msg",(Exception) throwable);
				map.put("type","1");
				map.put("Originates","1");
				//ExceptionSupport(method.getDeclaringClass().toString(),null, invocationtargetexception,null);
				sysLog.error((((Exception) throwable).toString()));
				httpservletrequest.setAttribute("message",map);
				actionforward=new ActionForward(GlobalNames.WEB_APP+"/errorpage.jsp");
				LoginUtil.Sc012ogAdd(null,0000000, String.valueOf((Exception) throwable));
			} else {
				String s4 = messages.getMessage("dispatch.error", actionmapping
						.getPath(), s);
				log.error(s4, invocationtargetexception);
				httpservletresponse.sendError(500, s4);
				LoginUtil.Sc012ogAdd(null,500, s4);
				return null;
			}
		}
		return actionforward;
	}

	/**
	 * 获取传入方法
	 * 
	 * @param s
	 * @return
	 * @throws java.lang.NoSuchMethodException
	 */
	protected java.lang.reflect.Method getMethod(java.lang.String s)
			throws java.lang.NoSuchMethodException {
		java.util.HashMap hashmap = methods;
		java.lang.reflect.Method method;
		method = (java.lang.reflect.Method) methods.get(s);
		if (method == null) {
			method = clazz.getMethod(s, types);
			methods.put(s, method);
		}
		return method;
	}
	/**
	 * 异常处理
	 * 
	 * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,ManageInputException,EventResponse)
	 * @param className
	 * @param value
	 * @param thw
	 * @param returnValue
	 * @return
	 */
	protected EventResponse ExceptionSupport(String className,
			RequestEnvelop value, ManageInputException me,
			EventResponse returnValue) {
		return ExceptionUtil
				.ExceptionSupport(className, value, me, returnValue);
	}

	/**
	 * 异常处理
	 * 
	 * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,AppException,EventResponse)
	 * @param className
	 * @param value
	 * @param thw
	 * @param returnValue
	 * @return
	 */
	protected EventResponse ExceptionSupport(String className,
			RequestEnvelop value, AppException ae, EventResponse returnValue) {
		return ExceptionUtil
				.ExceptionSupport(className, value, ae, returnValue);
	}

	/**
	 * 异常处理
	 * 
	 * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,Exception,EventResponse)
	 * @param className
	 * @param value
	 * @param thw
	 * @param returnValue
	 * @return
	 */
	protected EventResponse ExceptionSupport(String className,
			RequestEnvelop value, Exception ex, EventResponse returnValue) {
		return ExceptionUtil
				.ExceptionSupport(className, value, ex, returnValue);
	}

	/**
	 * ACTION调用产生的异常处理，根据详情描述组装错误信息
	 * 
	 * @param s
	 *            详细的错误信息
	 * @return
	 */
	protected String ExceptionSupport(String s) {
		return ExceptionUtil.buildMsg(GlobalErrorCode.ACTIONEXCEPTIONCODE, s,
				"ACTION调用失败");
	}

	
}
