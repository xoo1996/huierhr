/**
 * <p>标题: 出错信息拼装</p>
 * <p>说明: 封装错误信息为制定格式，系统错误信息的格式为：</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-10-1114:28:59</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.util.exception;

import java.sql.SQLException;

import org.radf.login.common.LoginUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

public class ExceptionUtil {

	/**
	 * 异常信息封装：由入口或出口参数组装产生的异常ManageInputException
	 * ManageInputException异常需要完整的在此方法中封装成"时间|错误码|详细信息|概述信息|"格式
	 * 
	 * @param className
	 *            类名
	 * @param RequestEnvelop
	 *            action调用facade时的入口参数
	 * @param ManageInputException
	 *            产生的异常类
	 * @param EventResponse
	 *            需要返回的结果对象
	 * @return EventResponse 加入异常封装后的返回结果对象
	 */
	public static EventResponse ExceptionSupport(String className,
			RequestEnvelop value, ManageInputException me,
			EventResponse returnValue) {
		if (GlobalNames.DEBUG_OUTPUT_FLAG) {
			me.printStackTrace();
		}
		try {
			if (returnValue == null)
				returnValue = new EventResponse();
			// 错误号
			int exceptionLogCode = ExceptionUtil.buildErrorCode(me
					.getErrorCode(), GlobalErrorCode.INPUTPARAMTYPEERRORCODE);
			// 返回客户端的概要错误信息
			String SmaCusString = ExceptionUtil.buildCusMsg(me.getMessage(),
					GlobalErrorMsg.ACTION_MANAGE_INPUT);
			// 错误详情(总控上下文信息)
			String ZKContextString = "访问" + className
					+ "时发生ManageInputException错误";
			// 生成客户端的详细错误信息
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);

			// 返回客户端详细信息 = 错误码|详细信息|概述信息|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// 返回客户端信息 = 时间|错误码|详细信息|概述信息|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));

			// 保存错误日志
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception ex) {
			// 如果出现意外，返回统一的错误
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.INPUTPARAMTYPEERRORCODE
							+ "|系统发生意外错误：ActionSupport->1 | "
							+ GlobalErrorMsg.ACTION_MANAGE_INPUT));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * Action中代码执行所抛出的用户级AppException处理，不是IMP层的异常
	 * 
	 * @param className
	 * @param RequestEnvelop
	 * @param AppException
	 * @param EventResponse
	 * @return EventResponse
	 */
	public static EventResponse ExceptionSupport(String className,
			RequestEnvelop value, AppException ae, EventResponse returnValue) {
		if (GlobalNames.DEBUG_OUTPUT_FLAG) {
			ae.printStackTrace();
		}
		try {
			// 错误号
			int exceptionLogCode = ExceptionUtil.buildErrorCode(ae
					.getErrorCode(), GlobalErrorCode.REMOTEEXCEPTIONCODE);
			// 返回客户端的概要错误信息
			String SmaCusString = ExceptionUtil.buildCusMsg(ae.getMessage(),
					GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE);
			// 错误详情(总控上下文信息)
			String ZKContextString = "访问" + className + "时发生AppException错误: "
					+ GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE;
			// 生成客户端的详细错误信息
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);
			// 返回客户端详细信息 = 错误码|详细信息|概述信息|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// 返回客户端信息 = 时间|错误码|详细信息|概述信息|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));
			// 保存错误日志
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception ex) {
			ex.printStackTrace();
			// 如果出现意外，返回统一的错误
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.REMOTEEXCEPTIONCODE
							+ "|系统发生意外错误：ActionSupport->2 | "
							+ GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * Action本层产生的不可控异常的处理
	 * 
	 * @param className
	 * @param RequestEnvelop
	 * @param Exception
	 * @param EventResponse
	 * @return EventResponse
	 */
	public static EventResponse ExceptionSupport(String className,
			RequestEnvelop value, Exception ex, EventResponse returnValue) {
		if (GlobalNames.DEBUG_OUTPUT_FLAG) {
			ex.printStackTrace();
		}
		try {
			// 错误号
			int exceptionLogCode = GlobalErrorCode.EXCEPTIONCODE;
			// 返回客户端的概要错误信息
			String SmaCusString = ExceptionUtil.buildCusMsg(ex.getMessage(),
					GlobalErrorMsg.EXCEPTIONMESSAGE);
			// 错误详情(总控上下文信息)
			String ZKContextString = "访问" + className + "时发生Exception错误: "
					+ GlobalErrorMsg.EXCEPTIONMESSAGE;
			// 生成客户端的详细错误信息
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);
			// 返回客户端详细信息 = 错误码|详细信息|概述信息|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// 返回客户端信息 = 时间|错误码|详细信息|概述信息|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));
			// 保存错误日志
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception x) {
			ex.printStackTrace();
			// 如果出现意外，返回统一的错误
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.EXCEPTIONCODE
							+ "|系统发生意外错误：ActionSupport->2 | "
							+ GlobalErrorMsg.EXCEPTIONMESSAGE));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * 生成客户端错误概要信息，格式为：|概述信息| 如果上层传递的异常中错误不为空则返回上层传递的错误，否则传递本层的错误
	 * 
	 * @param custMsg
	 *            上层传递的错误
	 * @param globalMsg
	 *            本层产生的错误
	 * @return String 最后要返回客户端的错误
	 */
	public static String buildCusMsg(String custMsg, String globalMsg) {
		if (custMsg == null) {
			return formatCusMsg(globalMsg);
		} else {
			if (custMsg.trim().equalsIgnoreCase("")) {
				return formatCusMsg(globalMsg);
			} else {
				return formatCusMsg(custMsg);
			}
		}

	}

	/**
	 * 格式化客户端AppException错误信息，格式为：详细信息|概述信息|
	 * 
	 * @param contextMsg
	 *            详细信息
	 * @param custMsg
	 *            概要信息
	 * @return
	 */
	public static String formatContextMsg(String contextMsg, String custMsg) {
		return contextMsg + custMsg;
	}

	/**
	 * 格式化返回客户端的详细错误信息，格式为：错误码|详细信息|概述信息|
	 * 
	 * @param code
	 *            错误码
	 * @param errMsg
	 *            错误详细信息|错误概要信息|
	 * @return
	 */
	public static String formatAppExceptionMsg(int code, String errMsg) {
		return code + "|" + errMsg;
	}

	/**
	 * 封装返回客户端的错误信息，返回格式：错误码|详细信息|概述信息|
	 * 
	 * @param code
	 *            错误码
	 * @param ZKContextString
	 *            上下文信息（详细信息）
	 * @param CusString
	 *            概要信息
	 * @return String 封装好的错误信息
	 */
	public static String buildMsg(int errCode, String ZKContextString,
			String SmaCusString) {
		// 生成客户端的详细错误信息
		String cusString = formatContextMsg(ZKContextString, SmaCusString);

		// 返回客户端详细信息 = 错误编码code|详细错误信息msg|概要错误信息
		String detailCusString = formatAppExceptionMsg(errCode, cusString);

		return detailCusString;
	}

	/**
	 * 给客户端信息加入时间信息，格式为：时间|错误码|详细信息|概述信息|
	 * 
	 * @param msg
	 *            错误码|详细信息|概述信息|
	 * @return
	 */
	public static String processCusMsg(String msg) {
		return DateUtil.getSystemCurrentTime("yyyy-MM-dd HH:mm:ss") + " | "
				+ msg;
	}

	/**
	 * 生成返回的错误码 如果上层传递的异常中错误编码合法，则返回的是上层传递的错误码，否则传递本层产生的错误码
	 * 
	 * @param ExceptionCode
	 *            上层传递的错误码
	 * @param GlobalCode
	 *            本层产生的错误码
	 * @return int 最后要返回客户端的错误码
	 */
	public static int buildErrorCode(int ExceptionCode, int GlobalCode) {
		// 错误号
		if (ExceptionCode == 0) {
			return GlobalCode;
		} else {
			return ExceptionCode;
		}
	}

	/**
	 * 保存错误日志
	 * 
	 * @param RequestEnvelopHead
	 *            head IMP返回的参数头部分,RequestEnvelop.getHead()
	 * @param className
	 *            类名
	 * @param exceptionLogCode
	 *            错误码
	 * @param detailCusString
	 *            错误上下文详细信息
	 */
	public static void saveLog(RequestEnvelopHead head, String className,
			int exceptionLogCode, String detailCusString) {
		try {
			// 日志信息
			LogHelper log = new LogHelper(className);
			log.log(head, exceptionLogCode, detailCusString);
			log = null;
			LoginUtil.Sc012ogAdd(head, exceptionLogCode, detailCusString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 保存错误日志
	 * 
	 * @param RequestEnvelop
	 *            value IMP返回的参数
	 * @param className
	 *            类名
	 * @param exceptionLogCode
	 *            错误码
	 * @param detailCusString
	 *            错误上下文详细信息
	 */
	public static void saveLog(RequestEnvelop value, String className,
			int exceptionLogCode, String detailCusString) {
		try {
			// 日志信息
			LogHelper log = new LogHelper(className);
			if (value == null) {
				log.log(null, exceptionLogCode, detailCusString);
			} else {
				log.log(value.getHead(), exceptionLogCode, detailCusString);
			}
			log = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 得到字符串中"|"之间的内容，用于错误处理。
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String toCus(String sourceStr) {
		String[] strArr;
		strArr = StringUtil.getAsStringArray(sourceStr, "|");
		if (strArr.length <= 1)
			return sourceStr;
		else
			return "|" + strArr[1] + "|";
	}

	/**
	 * 封装返回客户端的错误信息，返回格式：错误码|详细信息|概述信息|
	 * 
	 * @param code
	 *            错误码
	 * @param ZKContextString
	 *            上下文信息（详细信息）
	 * @param CusString
	 *            概要信息
	 * @return String 封装好的错误信息
	 */
	public static SQLException OraBulidMsg(SQLException se) {
		SQLException aa = null;
		String ee = se.getMessage();
		String cc = ee.substring(0, 9);
		if (cc.equals("ORA-00001")) {
			aa = new SQLException("保存的信息已存在");
		} else if (cc.endsWith("ORA-01400")) {
			aa = new SQLException("保存的信息不完整");
		} else if (cc.endsWith("ORA-12899")) {
			aa = new SQLException("保存的信息数据值过大");
		} else if (cc.endsWith("ORA-20000")) {
			aa = new SQLException("获取系统日期失败，可能没有正确设置系统日期");
		} else if (cc.endsWith("ORA-20001")) {
			aa = new SQLException("获取系统日期失败，可能没有正确设置系统日期");
		} else if (cc.endsWith("ORA-20002")) {
			aa = new SQLException("险种缴费方式不正确，请检查");
		} else if (cc.endsWith("ORA-20003")) {
			aa = new SQLException("当前该人员的单位编号为空，请核对该人员");
		} else if (cc.endsWith("ORA-20004")) {
			aa = new SQLException("险种缴费方式为空，请检查");
		} else {
			aa = se;
		}
		return aa;
	}

	/**
	 * 格式化客户端错误概要信息
	 * 
	 * @param msg
	 * @return
	 */
	private static String formatCusMsg(String msg) {
		if (msg == null) {
			return "||";
		} else if (msg.startsWith("|")) {
			return msg;
		} else {
			return "|" + msg + "|";
		}
	}

	public static void main(String[] args) {
		String msg = "abc|vvv|123456";
		System.out.println(toCus(msg));
	}
}
