/**
 * <p>����: ������Ϣƴװ</p>
 * <p>˵��: ��װ������ϢΪ�ƶ���ʽ��ϵͳ������Ϣ�ĸ�ʽΪ��</p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: 2005-10-1114:28:59</p>
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
	 * �쳣��Ϣ��װ������ڻ���ڲ�����װ�������쳣ManageInputException
	 * ManageInputException�쳣��Ҫ�������ڴ˷����з�װ��"ʱ��|������|��ϸ��Ϣ|������Ϣ|"��ʽ
	 * 
	 * @param className
	 *            ����
	 * @param RequestEnvelop
	 *            action����facadeʱ����ڲ���
	 * @param ManageInputException
	 *            �������쳣��
	 * @param EventResponse
	 *            ��Ҫ���صĽ������
	 * @return EventResponse �����쳣��װ��ķ��ؽ������
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
			// �����
			int exceptionLogCode = ExceptionUtil.buildErrorCode(me
					.getErrorCode(), GlobalErrorCode.INPUTPARAMTYPEERRORCODE);
			// ���ؿͻ��˵ĸ�Ҫ������Ϣ
			String SmaCusString = ExceptionUtil.buildCusMsg(me.getMessage(),
					GlobalErrorMsg.ACTION_MANAGE_INPUT);
			// ��������(�ܿ���������Ϣ)
			String ZKContextString = "����" + className
					+ "ʱ����ManageInputException����";
			// ���ɿͻ��˵���ϸ������Ϣ
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);

			// ���ؿͻ�����ϸ��Ϣ = ������|��ϸ��Ϣ|������Ϣ|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// ���ؿͻ�����Ϣ = ʱ��|������|��ϸ��Ϣ|������Ϣ|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));

			// ���������־
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception ex) {
			// ����������⣬����ͳһ�Ĵ���
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.INPUTPARAMTYPEERRORCODE
							+ "|ϵͳ�����������ActionSupport->1 | "
							+ GlobalErrorMsg.ACTION_MANAGE_INPUT));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * Action�д���ִ�����׳����û���AppException��������IMP����쳣
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
			// �����
			int exceptionLogCode = ExceptionUtil.buildErrorCode(ae
					.getErrorCode(), GlobalErrorCode.REMOTEEXCEPTIONCODE);
			// ���ؿͻ��˵ĸ�Ҫ������Ϣ
			String SmaCusString = ExceptionUtil.buildCusMsg(ae.getMessage(),
					GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE);
			// ��������(�ܿ���������Ϣ)
			String ZKContextString = "����" + className + "ʱ����AppException����: "
					+ GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE;
			// ���ɿͻ��˵���ϸ������Ϣ
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);
			// ���ؿͻ�����ϸ��Ϣ = ������|��ϸ��Ϣ|������Ϣ|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// ���ؿͻ�����Ϣ = ʱ��|������|��ϸ��Ϣ|������Ϣ|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));
			// ���������־
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception ex) {
			ex.printStackTrace();
			// ����������⣬����ͳһ�Ĵ���
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.REMOTEEXCEPTIONCODE
							+ "|ϵͳ�����������ActionSupport->2 | "
							+ GlobalErrorMsg.REMOTEEXCEPTIONMESSAGE));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * Action��������Ĳ��ɿ��쳣�Ĵ���
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
			// �����
			int exceptionLogCode = GlobalErrorCode.EXCEPTIONCODE;
			// ���ؿͻ��˵ĸ�Ҫ������Ϣ
			String SmaCusString = ExceptionUtil.buildCusMsg(ex.getMessage(),
					GlobalErrorMsg.EXCEPTIONMESSAGE);
			// ��������(�ܿ���������Ϣ)
			String ZKContextString = "����" + className + "ʱ����Exception����: "
					+ GlobalErrorMsg.EXCEPTIONMESSAGE;
			// ���ɿͻ��˵���ϸ������Ϣ
			String cusString = ExceptionUtil.formatContextMsg(ZKContextString,
					SmaCusString);
			// ���ؿͻ�����ϸ��Ϣ = ������|��ϸ��Ϣ|������Ϣ|
			String detailCusString = ExceptionUtil.buildMsg(exceptionLogCode,
					ZKContextString, SmaCusString);

			// ���ؿͻ�����Ϣ = ʱ��|������|��ϸ��Ϣ|������Ϣ|
			returnValue.setMsg(ExceptionUtil.processCusMsg(detailCusString));
			// ���������־
			ExceptionUtil.saveLog(value, className, exceptionLogCode,
					detailCusString);
		} catch (Exception x) {
			ex.printStackTrace();
			// ����������⣬����ͳһ�Ĵ���
			returnValue.setMsg(ExceptionUtil
					.processCusMsg(GlobalErrorCode.EXCEPTIONCODE
							+ "|ϵͳ�����������ActionSupport->2 | "
							+ GlobalErrorMsg.EXCEPTIONMESSAGE));
		}
		returnValue.setSucessFlag(false);
		return returnValue;
	}

	/**
	 * ���ɿͻ��˴����Ҫ��Ϣ����ʽΪ��|������Ϣ| ����ϲ㴫�ݵ��쳣�д���Ϊ���򷵻��ϲ㴫�ݵĴ��󣬷��򴫵ݱ���Ĵ���
	 * 
	 * @param custMsg
	 *            �ϲ㴫�ݵĴ���
	 * @param globalMsg
	 *            ��������Ĵ���
	 * @return String ���Ҫ���ؿͻ��˵Ĵ���
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
	 * ��ʽ���ͻ���AppException������Ϣ����ʽΪ����ϸ��Ϣ|������Ϣ|
	 * 
	 * @param contextMsg
	 *            ��ϸ��Ϣ
	 * @param custMsg
	 *            ��Ҫ��Ϣ
	 * @return
	 */
	public static String formatContextMsg(String contextMsg, String custMsg) {
		return contextMsg + custMsg;
	}

	/**
	 * ��ʽ�����ؿͻ��˵���ϸ������Ϣ����ʽΪ��������|��ϸ��Ϣ|������Ϣ|
	 * 
	 * @param code
	 *            ������
	 * @param errMsg
	 *            ������ϸ��Ϣ|�����Ҫ��Ϣ|
	 * @return
	 */
	public static String formatAppExceptionMsg(int code, String errMsg) {
		return code + "|" + errMsg;
	}

	/**
	 * ��װ���ؿͻ��˵Ĵ�����Ϣ�����ظ�ʽ��������|��ϸ��Ϣ|������Ϣ|
	 * 
	 * @param code
	 *            ������
	 * @param ZKContextString
	 *            ��������Ϣ����ϸ��Ϣ��
	 * @param CusString
	 *            ��Ҫ��Ϣ
	 * @return String ��װ�õĴ�����Ϣ
	 */
	public static String buildMsg(int errCode, String ZKContextString,
			String SmaCusString) {
		// ���ɿͻ��˵���ϸ������Ϣ
		String cusString = formatContextMsg(ZKContextString, SmaCusString);

		// ���ؿͻ�����ϸ��Ϣ = �������code|��ϸ������Ϣmsg|��Ҫ������Ϣ
		String detailCusString = formatAppExceptionMsg(errCode, cusString);

		return detailCusString;
	}

	/**
	 * ���ͻ�����Ϣ����ʱ����Ϣ����ʽΪ��ʱ��|������|��ϸ��Ϣ|������Ϣ|
	 * 
	 * @param msg
	 *            ������|��ϸ��Ϣ|������Ϣ|
	 * @return
	 */
	public static String processCusMsg(String msg) {
		return DateUtil.getSystemCurrentTime("yyyy-MM-dd HH:mm:ss") + " | "
				+ msg;
	}

	/**
	 * ���ɷ��صĴ����� ����ϲ㴫�ݵ��쳣�д������Ϸ����򷵻ص����ϲ㴫�ݵĴ����룬���򴫵ݱ�������Ĵ�����
	 * 
	 * @param ExceptionCode
	 *            �ϲ㴫�ݵĴ�����
	 * @param GlobalCode
	 *            ��������Ĵ�����
	 * @return int ���Ҫ���ؿͻ��˵Ĵ�����
	 */
	public static int buildErrorCode(int ExceptionCode, int GlobalCode) {
		// �����
		if (ExceptionCode == 0) {
			return GlobalCode;
		} else {
			return ExceptionCode;
		}
	}

	/**
	 * ���������־
	 * 
	 * @param RequestEnvelopHead
	 *            head IMP���صĲ���ͷ����,RequestEnvelop.getHead()
	 * @param className
	 *            ����
	 * @param exceptionLogCode
	 *            ������
	 * @param detailCusString
	 *            ������������ϸ��Ϣ
	 */
	public static void saveLog(RequestEnvelopHead head, String className,
			int exceptionLogCode, String detailCusString) {
		try {
			// ��־��Ϣ
			LogHelper log = new LogHelper(className);
			log.log(head, exceptionLogCode, detailCusString);
			log = null;
			LoginUtil.Sc012ogAdd(head, exceptionLogCode, detailCusString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ���������־
	 * 
	 * @param RequestEnvelop
	 *            value IMP���صĲ���
	 * @param className
	 *            ����
	 * @param exceptionLogCode
	 *            ������
	 * @param detailCusString
	 *            ������������ϸ��Ϣ
	 */
	public static void saveLog(RequestEnvelop value, String className,
			int exceptionLogCode, String detailCusString) {
		try {
			// ��־��Ϣ
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
	 * �õ��ַ�����"|"֮������ݣ����ڴ�����
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
	 * ��װ���ؿͻ��˵Ĵ�����Ϣ�����ظ�ʽ��������|��ϸ��Ϣ|������Ϣ|
	 * 
	 * @param code
	 *            ������
	 * @param ZKContextString
	 *            ��������Ϣ����ϸ��Ϣ��
	 * @param CusString
	 *            ��Ҫ��Ϣ
	 * @return String ��װ�õĴ�����Ϣ
	 */
	public static SQLException OraBulidMsg(SQLException se) {
		SQLException aa = null;
		String ee = se.getMessage();
		String cc = ee.substring(0, 9);
		if (cc.equals("ORA-00001")) {
			aa = new SQLException("�������Ϣ�Ѵ���");
		} else if (cc.endsWith("ORA-01400")) {
			aa = new SQLException("�������Ϣ������");
		} else if (cc.endsWith("ORA-12899")) {
			aa = new SQLException("�������Ϣ����ֵ����");
		} else if (cc.endsWith("ORA-20000")) {
			aa = new SQLException("��ȡϵͳ����ʧ�ܣ�����û����ȷ����ϵͳ����");
		} else if (cc.endsWith("ORA-20001")) {
			aa = new SQLException("��ȡϵͳ����ʧ�ܣ�����û����ȷ����ϵͳ����");
		} else if (cc.endsWith("ORA-20002")) {
			aa = new SQLException("���ֽɷѷ�ʽ����ȷ������");
		} else if (cc.endsWith("ORA-20003")) {
			aa = new SQLException("��ǰ����Ա�ĵ�λ���Ϊ�գ���˶Ը���Ա");
		} else if (cc.endsWith("ORA-20004")) {
			aa = new SQLException("���ֽɷѷ�ʽΪ�գ�����");
		} else {
			aa = se;
		}
		return aa;
	}

	/**
	 * ��ʽ���ͻ��˴����Ҫ��Ϣ
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
