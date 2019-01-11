package org.radf.plat.commons;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.jsp.PageContext;

import oracle.sql.CLOB;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionServlet;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
import org.radf.plat.util.global.GlobalErrorCode;

/**
 * <p>
 * 标题: 类型转换类
 * </p>
 * <p>
 * 说明: 用于不同数据类型间的转换，以及15到18位身份证号码转换
 * </p>
 * <p>
 * 项目: Rapid Application Development Framework
 * </p>
 * <p>
 * 版权: Copyright 2008 - 2017
 * </p>
 * <p>
 * 时间: 2005-10-31 13:08:18
 * </p>
 * 
 * @author zqb
 * @version 1.0
 */

public class TypeCast {
	private static Log a;

	/**
	 * 把一个String类型的数据，转换成int型的。如果转换失败，抛出ManageInputException
	 * 
	 * @param str
	 * @param paraName
	 * @param isCanNull
	 * @return int
	 * @throws ManageInputException
	 */
	public static int stringToInt(String str, String paraName, boolean isCanNull)
			throws ManageInputException {
		if (str == null || str.equals("")) {
			if (isCanNull)
				return NullFlag.INTNULL;
			else
				throw new ManageInputException(
						GlobalErrorCode.INPUTPARAMTYPEERRORCODE, " 输入的参数："
								+ paraName + "为空，请输入");
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			throw new ManageInputException(
					GlobalErrorCode.INPUTPARAMTYPEERRORCODE, " 输入的参数："
							+ paraName + "错误，无法转换成 int 型 ");
		}
	}

	/**
	 * 把一个String类型的数据，转换成short型的。如果转换失败，抛出ManageInputException
	 * 
	 * @param str
	 * @param paraName
	 * @param isCanNull
	 * @return int
	 * @throws ManageInputException
	 */
	public static short stringToShort(String str, String paraName,
			boolean isCanNull) throws ManageInputException {
		if (str == null || str.equals("")) {
			if (isCanNull)
				return NullFlag.SHORTNULL;
			else
				throw new ManageInputException(
						GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
								+ paraName + "为空，请输入 |");
		}
		try {
			return Short.parseShort(str);
		} catch (NumberFormatException nfe) {
			throw new ManageInputException(
					GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
							+ paraName + "错误，无法转换成 short 型 |");
		}
	}

	/**
	 * 把一个String类型的数据转换成long型。如果转换失败，抛出ManageInputException
	 * 
	 * @param str
	 * @param paraName
	 * @param isCanNull
	 * @return
	 * @throws ManageInputException
	 */
	public static long stringToLong(String str, String paraName,
			boolean isCanNull) throws ManageInputException {
		if (str == null || str.equals("")) {
			if (isCanNull)
				return NullFlag.LONGNULL;
			else
				throw new ManageInputException(
						GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
								+ paraName + "为空，请输入 |");
		}
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			throw new ManageInputException(
					GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
							+ paraName + "错误，无法转换成 long 型 |");
		}
	}

	/**
	 * 把一个String类型的数据转换成double型的。如果转换失败，抛出ManageInputException
	 * 
	 * @param str
	 * @param paraName
	 * @param isCanNull
	 * @return
	 * @throws ManageInputException
	 */
	public static double stringToDouble(String str, String paraName,
			boolean isCanNull) throws ManageInputException {
		if (str == null || str.equals("")) {
			if (isCanNull)
				return NullFlag.DOUBLENULL;
			else
				throw new ManageInputException(
						GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
								+ paraName + "为空，请 输入 |");
		}
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			throw new ManageInputException(
					GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
							+ paraName + "错误，无法转换成 double 型 |");
		}
	}

	/**
	 * .123 转换成 0.123
	 * 
	 * @param str
	 * @param paraName
	 * @param isCanNull
	 * @return
	 * @throws ManageInputException
	 */
	public static String doubleFillZero(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}

		if ("0".equals(str)) {
			return str;
		}
		
		String redstr = str;
		if (".".equals(redstr.substring(0, 1))) {
			redstr = "0"+""+redstr;
		}
		
		redstr = StringUtil.dealZero(redstr);
		if (".".equals(redstr.substring(redstr.length() - 1, redstr.length()))) {
			redstr = redstr.substring(0, redstr.length() - 1);
		}

		return redstr;

	}

	/**
	 * 将yyyy-MM-dd格式的时间转换成java.sql.Date对象 For Example:
	 * TypeCast.stringToDate("1995-1-5","parameter name",false);
	 * 
	 * @param strDate
	 *            A String whose should be parsed.
	 * @param paraName
	 * @param isCanNull
	 *            if this parameter is false,strDate must be not null.
	 * @return java.sql.Date
	 * @throws ManageInputException
	 */
	public static Date stringToDate(String strDate, String paraName,
			boolean isCanNull) throws ManageInputException {
		strDate = strDate.trim();
		if (strDate == null || strDate.equals("")) {
			if (isCanNull)
				return null;
			else
				throw new ManageInputException(
						GlobalErrorCode.NULLINPUTPARAMCODE, "|  输入的参数："
								+ paraName + "为空，请 输入 |");
		}
		Date targetDate = null;
		try {
			targetDate = DateUtil.converToDate(strDate);
		} catch (Exception e) {
			throw new ManageInputException(
					GlobalErrorCode.INPUTPARAMTYPEERRORCODE, "|  输入的参数："
							+ paraName + "无法转换成时间类型 |");
		}
		return targetDate;
	}

	/**
	 * 字符串转换，如果值是null且允许为空，则返回""； 不允许为空，throws
	 * ManageInputException，字符串不为空，返回strSource
	 * 
	 * @param strSource
	 *            要判断的字符串
	 * @param paraName
	 *            参数名称
	 * @param isCanNull
	 *            是否可以为空
	 * @return
	 * @throws ManageInputException
	 */
	public static String stringToString(String strSource, String paraName,
			boolean isCanNull) throws ManageInputException {
		if (strSource == null) {
			if (isCanNull)
				return "";
			else
				throw new ManageInputException(
						GlobalErrorCode.NULLINPUTPARAMCODE, " 输入的参数："
								+ paraName + "为空");
		} else if (strSource.equalsIgnoreCase("") && (!isCanNull)) {
			throw new ManageInputException(GlobalErrorCode.NULLINPUTPARAMCODE,
					" 输入的参数：" + paraName + "为空");
		}
		return strSource;
	}

	/**
	 * 字符串转换，如果是null，返回empty String
	 * 
	 * @param strSource
	 * @return
	 */
	public static String stringToString(String strSource) {
		if (strSource == null)
			return "";
		else
			return strSource;
	}

	/**
	 * 字符串转换成Integer，输入必须是
	 * 
	 * @param strSource
	 *            String
	 * @return Integer
	 */
	public static Integer stringToInteger(String strSource) {
		if (strSource == null || strSource.trim().equals("")) {
			return new Integer("0");
		} else if (strSource.indexOf("年") > 1 || strSource.indexOf("月") > 1
				|| strSource.indexOf("日") > 1) {
			strSource.replaceAll("年", "");
			strSource.replaceAll("月", "");
			strSource.replaceAll("日", "");
			return new Integer(strSource);
		} else {
			if (CommonVerify.numberVerify(strSource)) {
				return new Integer(strSource);
			} else {
				return new Integer("0");
			}
		}
	}

	/**
	 * int类型转换成String类型。
	 * 
	 * @param para
	 * @return
	 */
	public static String intToString(int para) {
		if (para == NullFlag.INTNULL) {
			return "";
		} else {
			return "" + para;
		}
	}

	/**
	 * Integer类型转换成String类型，自动转化null类型
	 * 
	 * @param para
	 * @return
	 */
	public static String intToString(Integer para) {
		if (para == null || para.doubleValue() == NullFlag.INTNULL) {
			return "";
		} else {
			return intToString(para.intValue());
		}
	}

	/**
	 * short类型转换成String类型。
	 * 
	 * @param para
	 * @return
	 */
	public static String shortToString(short para) {
		if (para == NullFlag.SHORTNULL) {
			return "";
		} else {
			return "" + para;
		}
	}

	/**
	 * short类型转换成String类型。
	 * 
	 * @param para
	 * @return
	 */
	public static String shortToString(int para) {
		return shortToString((short) para);
	}

	/**
	 * Short类型转换成String类型，自动转化null类型
	 * 
	 * @param para
	 * @return
	 */
	public static String shortToString(Short para) {
		if (para == null) {
			return "";
		} else {
			return shortToString(para.shortValue());
		}
	}

	/**
	 * Short类型转换成String类型，自动转化null类型
	 * 
	 * @param para
	 * @return
	 */
	public static String shortToString(Integer para) {
		if (para == null || para.doubleValue() == NullFlag.INTNULL) {
			return "";
		} else {
			return shortToString(para.shortValue());
		}
	}

	/**
	 * 把一个double型的数据转换成一个字符串，用于显示。 按照货币表示：如果没有小数或小数少于2位则自动补足小数2位，大于2位则四舍五入成2位
	 * (例如0为0.00； 19为19.00；24.1为24.10，25.225为25.23）
	 * 
	 * @param para
	 * @return
	 */
	public static String doubleToString(double para) {
		if (para == NullFlag.DOUBLENULL) {
			return "";
		}

		NumberFormat nf = new DecimalFormat("###.##");
		String strValue = nf.format(para);
		if (strValue.indexOf(".") == -1)
			strValue = strValue + ".00";
		else if (strValue.substring(strValue.indexOf(".") + 1).length() == 1)
			strValue = strValue + "0";
		return strValue;
	}

	/**
	 * Double类型转换成String类型，自动转化null类型 按照货币表示：如果没有小数或小数少于2位则自动补足小数2位，大于2位则四舍五入成2位
	 * 
	 * @param para
	 * @return
	 */
	public static String doubleToString(Double para) {
		if (para == null || para.doubleValue() == NullFlag.DOUBLENULL) {
			return "";
		} else {
			return doubleToString(para.doubleValue());
		}
	}

	/**
	 * 把一个double型的数据转换成一个字符串，用于显示。
	 * 
	 * @param para
	 * @return
	 */
	public static String doubleToString2(double para) {
		if (para == NullFlag.DOUBLENULL) {
			return "";
		} else {
			return "" + para;
		}
	}

	/**
	 * Double类型转换成String类型，自动转化null类型
	 * 
	 * @param para
	 * @return
	 */
	public static String doubleToString2(Double para) {
		if (para == null || para.doubleValue() == NullFlag.DOUBLENULL) {
			return "";
		} else {
			return doubleToString2(para.doubleValue());
		}
	}

	/**
	 * long型转换成String类型。
	 * 
	 * @param para
	 * @return
	 */
	public static String longToString(long para) {
		if (para == NullFlag.LONGNULL) {
			return "";
		} else {
			return "" + para;
		}
	}

	/**
	 * Long类型转换成String类型，自动转化null类型
	 * 
	 * @param para
	 * @return
	 */
	public static String longToString(Long para) {
		if (para == null || para.doubleValue() == NullFlag.LONGNULL) {
			return "";
		} else {
			return longToString(para.longValue());
		}
	}

	/**
	 * 按照四舍五入把一个double型的数据转换成一个long，用于显示。
	 * 
	 * @param para
	 * @return
	 */
	public static long doubleTolong(double para) {
		if (para == NullFlag.DOUBLENULL) {
			return NullFlag.LONGNULL;
		} else {
			NumberFormat nf = new DecimalFormat("###");
			String strValue = nf.format(para);
			return Long.parseLong(strValue);
		}
	}

	/**
	 * 按照四舍五入把一个double型的数据转换成一个long，用于显示。
	 * 
	 * @param para
	 * @return
	 */
	public static long doubleTolong(Double para) {
		if (para == null || para.doubleValue() == NullFlag.DOUBLENULL) {
			return NullFlag.LONGNULL;
		} else {
			NumberFormat nf = new DecimalFormat("###");
			String strValue = nf.format(para);
			return Long.parseLong(strValue);
		}
	}

	// /**
	// * 将日期类型转换成"yyyy-MM-dd"格式字符串，如果日期null则返回""。
	// * @see DateUtil#converToString
	// * @param date
	// * @return
	// */
	// public static String simpleDateToString(Date date) {
	// if (date == null)
	// return "";
	// return DateUtil.converToString(date,"yyyy-MM-dd");
	// }
	//
	// /**
	// * 将日期类型转换成"yyyy-MM-dd HH:mm:ss"格式字符串，如果日期null则返回""。
	// * @see DateUtil#converToString
	// * @param date
	// * @return
	// */
	// public static String dateTimeToString(Date date) {
	// if (date == null)
	// return "";
	// return DateUtil.converToString(date,null);
	// }

	/**
	 * 非金额的double类型数据转换成String类型，直接转换，不定义格式
	 * 
	 * @param para
	 * @return
	 */
	public static String notMoneyDoubleToString(double para) {
		return "" + para;
	}

	/**
	 * 把一个double型的数据转换成一个字符串，用于显示。 按照货币表示：0为0.00； 19为19.00；24.1为24.10；
	 * 25.225为25.23。
	 * 
	 * @param para
	 * @return
	 */
	public static String moneyDoubleToString(double para) {
		return doubleToString(para);
	}

	/**
	 * 将15位的身份证号码转换成18位身份证号码
	 * 
	 * @param identityCode
	 * @return
	 */
	public static String transformIndentityCode(String identityCode) {
		String newIdentityCode = "";
		if (identityCode.length() == 15) {
			identityCode = identityCode.substring(0, 6) + "19"
					+ identityCode.substring(6, 15);
			int[] w = new int[19];
			w[2] = 2;
			w[3] = 4;
			w[4] = 8;
			w[5] = 5;
			w[6] = 10;
			w[7] = 9;
			w[8] = 7;
			w[9] = 3;
			w[10] = 6;
			w[11] = 1;
			w[12] = 2;
			w[13] = 4;
			w[14] = 8;
			w[15] = 5;
			w[16] = 10;
			w[17] = 9;
			w[18] = 7;
			int i = 0, sum = 0;
			for (i = 1; i <= 17; i++) {
				int temp = new Integer(identityCode.substring(i - 1, i))
						.intValue();
				sum = sum + temp * w[19 - i];
			}
			sum = sum % 11;
			String a18;
			switch (sum) {
			case 0:
			case 1:
				a18 = (1 - sum) + "";
				break;
			case 2:
				a18 = "X";
				break;
			default:
				a18 = (12 - sum) + "";
			}
			a18 = a18.substring(0, 1);

			newIdentityCode = identityCode + a18;

		} else if (identityCode.length() == 18) {
			newIdentityCode = identityCode.substring(0, 6)
					+ identityCode.substring(8, 17);
		} else {
			newIdentityCode = identityCode;
		}
		return newIdentityCode;
	}

	/**
	 * byteToHEX() 用来把一个byte类型的数转换成十六进制的ASCII表示，
	 */
	public static String byteToHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 将Clob类型数据转换成String
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 */
	public static String clobToString(Clob clob) throws SQLException {
		if (clob != null) {
			long len = clob.length();
			return clob.getSubString(1L, (int) len);
		} else {
			return null;
		}
	}

	/**
	 * 将数据库ResultSet中的指定Clob类型字段值转换成String
	 * 
	 * @param rs
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public static String clobToString(ResultSet rs, String column)
			throws SQLException {
		CLOB clob = (CLOB) rs.getClob(column);
		return clobToString(clob);
	}

	/**
	 * 将Blob类型字段值转换成输入流InputStream
	 * 
	 * @param blob
	 * @return
	 * @throws SQLException
	 */
	public static InputStream blobToInputStream(Blob blob) throws SQLException {
		if (blob != null) {
			InputStream in = blob.getBinaryStream();
			return in;
		} else {
			return null;
		}
	}

	/**
	 * 将数据库ResultSet中的指定Blob类型字段值转换成输入流InputStream
	 * 
	 * @param rs
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public static InputStream blobToInputStream(ResultSet rs, String column)
			throws SQLException {
		return blobToInputStream(rs.getBlob(column));
	}

	/**
	 * 将blob类型字段值转换成byte数组
	 * 
	 * @param blob
	 * @return
	 * @throws SQLException
	 */
	public static byte[] blobToBytes(Blob blob) throws SQLException {
		if (blob != null) {
			long len = blob.length();
			byte[] data = blob.getBytes(1L, (int) len);
			return data;
		} else {
			return null;
		}
	}

	/**
	 * 将数据库ResultSet中的指定blob类型字段值转换成byte数组
	 * 
	 * @param rs
	 * @param column
	 *            要取blob数据的列名
	 * @return
	 * @throws SQLException
	 */
	public static byte[] blobToBytes(ResultSet rs, String column)
			throws SQLException {
		return blobToBytes(rs.getBlob(column));
	}

	/**
	 * 将blob类型的数据转换成string
	 * 
	 * @param blob
	 * @return String
	 * @exception SQLException
	 * @exception IOException
	 */
	public static String blobToString(Blob blob) throws SQLException,
			IOException {
		long length = 0;
		if (blob == null) {
			return null;
		}
		InputStream instream = blob.getBinaryStream();
		byte[] buffer = new byte[(int) blob.length()];
		instream.read(buffer);
		/*
		 * while((length = instream.read(buffer))!= -1){ System.out.print("Read " +
		 * length + " bytes: "); for (int i=0; i<length; i++)
		 * System.out.print(buffer[i]+" "); System.out.println(); }
		 */
		instream.close();
		// System.out.println("return String === "+returnStr);
		return new String(buffer);
	}

	/**
	 * 将BigDecimal转化为Money类型返回。
	 * 
	 * @param money
	 *            BigDecimal
	 * @return Money
	 * @author raofh
	 */
	public static Money bigDecimal2Money(BigDecimal money) {
		if (money == null) {
			return Money.ZERO;
		} else if (money.intValue() < 0) {
			return Money.ZERO;
		} else {
			return new Money(money);
		}
	}

	/**
	 * 将输入的参数转化为非空(null)的字符串
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 * @author raofh
	 */
	public static String objToString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将数据库ResultSet中的指定blob类型字段值的数据转换成string
	 * 
	 * @param rs
	 * @param column
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String blobToString(ResultSet rs, String column)
			throws SQLException, IOException {
		return blobToString(rs.getBlob(column));
	}

	public static void main(String[] args) {

		System.out.println(doubleFillZero("444.00000001"));

	}

	/**
	 * 返回BigDecimal值
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static BigDecimal objToBigDecimal(Object obj) {
		if (obj == null) {
			return BigDecimal.valueOf(0);
		} else {
			try {
				if (obj instanceof BigDecimal) {
					return (BigDecimal) obj;
				} else if (obj instanceof String) {
					return new BigDecimal((String) obj);
				} else {
					return BigDecimal.valueOf(0);
				}
			} catch (Exception e) {
				return BigDecimal.valueOf(0);
			}
		}
	}

	/**
	 * 将输入的参数转化为非空(null)的字符串
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 * @author raofh
	 */
	public static String aab301Trim(String aab301) {
		if (aab301 == null || "".equals(aab301.trim())) {
			return "";
		} else {
			while (aab301.substring(aab301.length() - 1, aab301.length())
					.equals("0")) {
				aab301 = aab301.substring(0, aab301.length() - 1);
			}
			return aab301;
		}
	}

	public static String GetItemName(PageContext pageContext, String key,
			String value) {
		String name = "";
		TreeMap tm = (TreeMap) pageContext.findAttribute(key);
		if (tm == null) {
			name = "";
			return name;
		} else if (null != tm.get(value)) {
			name = (String) tm.get(value);
		}
		return name;
	}

	/**
	 * 返回Money值
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static Money objToMoney(Object obj) {
		if (obj == null) {
			return Money.ZERO;
		} else {
			try {
				if (obj instanceof BigDecimal) {
					return new Money((BigDecimal) obj);
				} else if (obj instanceof String) {
					return new Money(new BigDecimal((String) obj));
				} else {
					return Money.ZERO;
				}
			} catch (Exception e) {
				return Money.ZERO;
			}
		}
	}

	/**
	 * 计算工龄
	 * 
	 * @author 叶鹏
	 * @date 2006-10-20 16:02:48
	 * @param startym
	 *            参加工作年月
	 * @param endym
	 *            退休年月
	 * @return
	 */
	public static String getJobAge(String startym, String endym)
			throws AppException {
		String jobage = null;
		try {
			int start = Integer.parseInt(startym.substring(0, 4));
			int end = Integer.parseInt(endym.substring(0, 4));
			jobage = "" + (end - start);

		} catch (Exception e) {
			throw new AppException("工龄计算出错");
		}
		return jobage;
	}

	/**
	 * 得到实际缴费到年月，下一年的06月
	 * 
	 * @author 叶鹏
	 * @date 2006-10-21 11:02:47
	 * @param year
	 *            当年的年份
	 * @return
	 */
	public static String getRealFeeYM(String year) throws AppException {
		String ym = "";
		try {
			int start = Integer.parseInt(year.substring(0, 4));
			ym = (start + 1) + "06";
		} catch (Exception e) {
			// throw new AppException("实际缴费到年月计算出错！");
		}
		return ym;

	}

	/**
	 * 计算应缴费用
	 * 
	 * @author 叶鹏
	 * @date 2006-10-21 11:41:22
	 * @param feeitem
	 *            缴费项目，档案费，养老等等
	 * @param startym
	 *            缴费开始年月
	 * @param endym
	 *            缴费结束年月
	 * @param year
	 *            缴费年度
	 * @param base
	 *            基数
	 * @param prop
	 *            比例
	 * @param feetype
	 *            按年度交，还是按月度交
	 * @return
	 */
	public static BigDecimal getRealFee(String feeitem, String startym,
			String endym, String year, BigDecimal base, BigDecimal prop,
			String feetype) {
		BigDecimal fee = new BigDecimal(0);
		return fee;
	}

	/**
	 * 得到所属年度
	 * 
	 * @author 叶鹏
	 * @date 2006-10-25 18:59:42
	 * @return
	 */
	public static String getCurYD() {
		String yd = "2006";
		// 获取当前年度，要进行修改
		return yd;
	}

	public static String getStepMonth(String curmonth, int step) {
		String nextmonth = "";
		try {
			int yyyy;
			int mm;
			int newm;
			String nyyyy;
			String nmm;
			if (curmonth.length() == 6) {
				yyyy = Integer.parseInt(curmonth.substring(0, 4));
				mm = Integer.parseInt(curmonth.substring(4, 6));
				newm = yyyy * 12 + mm - 1 + step;
				nyyyy = "" + (newm / 12);
				nmm = "" + (newm % 12 + 1);
				if (nmm.length() == 1)
					nmm = "0" + nmm;
				nextmonth = nyyyy + nmm;
			}

		} catch (Exception e) {

		}
		return nextmonth;
	}

	public static String getidtoname(String id, String field,
			ActionServlet servlet) throws java.lang.Exception {

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

	public static Collection getEntities(SubmitDataMap submitdatamap,
			Class class1) {
		try {
			return Convert.getCollection(submitdatamap, class1);
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
			return null;
		} catch (InstantiationException instantiationexception) {
			a.error(instantiationexception.toString());
			return null;
		} catch (InvocationTargetException invocationtargetexception) {
			a.error(invocationtargetexception.toString());
		}
		return null;
	}

	public static String array2String(String[] array, String separator) {
		StringBuffer ret = new StringBuffer("");

		if (array == null || array.length <= 0) {
			return "";
		}

		for (int i = 0; i < array.length; i++) {
			ret.append(separator).append(array[i]);
		}

		return ret.substring(separator.length());
	}

	public static String array2String(String[] array) {
		StringBuffer ret = new StringBuffer("");

		if (array == null || array.length <= 0) {
			return "";
		}

		for (int i = 0; i < array.length; i++) {
			ret.append("sum(").append(array[i]).append(") ").append(
					getname(array[i] + "_" + i) + ",");
		}

		return ret.substring(0, ret.length() - 1);
	}

	/**
	 * 得到自段名字如 ab01.aab001 返回aab001
	 * 
	 * @param value
	 * @return
	 */
	public static String getname(String value) {
		String name = null;
		if (value == null || "".equals(value)) {
			return value;
		}
		int index = value.indexOf(".");
		if (index < 0) {
			name = value;
		} else {
			name = value.substring(index + 1);
		}

		return name;
	}

	/**
	 * 返回6位数的缴费手册号
	 * 
	 * @author llchao
	 * @date 2007-6-4 13:55:02
	 * @param bjc026
	 * @return
	 */
	public static String returnBjc026(String bjc026) {
		String strbjc026 = "";
		String bj026c = String.valueOf(Integer.parseInt(bjc026) + 1);
		int len = bj026c.length();
		switch (len) {
		case 1:
			strbjc026 = "00000" + bj026c;
			break;
		case 2:
			strbjc026 = "0000" + bj026c;
			break;
		case 3:
			strbjc026 = "000" + bj026c;
			break;
		case 4:
			strbjc026 = "00" + bj026c;
			break;
		case 5:
			strbjc026 = "0" + bj026c;
			break;
		case 6:
			strbjc026 = "" + bj026c;
			break;
		}

		return strbjc026.trim();

	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double GetROUND_UP(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double GetROUND_DOWN(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double GetROUND_HALF_EVEN(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String Null2Space(String s) {
		return s == null ? "" : s;
	}

}