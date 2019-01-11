//*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.kpdus.com/jad.html
//Decompiler options: packimports(3) radix(10) lradix(10) 
//Source File Name:   SqlDateConverter.java
package org.apache.commons.beanutils;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import org.radf.plat.commons.CommonVerify;



public final class DateConvert implements Converter {

	public DateConvert() {
		defaultValue = null;
		useDefault = true;
		defaultValue = null;
		useDefault = false;
	}

	public DateConvert(Object defaultValue) {
		this.defaultValue = null;
		useDefault = true;
		this.defaultValue = defaultValue;
		useDefault = true;
	}

	public Object convert(Class type, Object value) {
		if (value == null)
			if (useDefault)
				return defaultValue;
			else
				throw new ConversionException("No value specified");
		if (value instanceof Date)
			return value;
		Date ss = null;
		try {
			ss = (Date) converToDate(value.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ss;
		// if(useDefault)
		// return defaultValue;
		// else
		// throw new ConversionException(e);
	}

	private Object defaultValue;

	private boolean useDefault;

	/**
	 * 转换字符串格式日期为java.sql.Date日期，如果原始字符串为null则返回null。 自动支持从以下格式字符串转换：<br>
	 * (1)从yyyyMMddHHmmss格式 (2)从yyyyMMdd格式 (3)从yyyy-MM-dd HH:mm:ss格式
	 * (4)从yyyy-MM-dd格式
	 * 
	 * @param s
	 * @param type
	 * @exception AppException
	 * @return java.sql.Date
	 */
	public static java.sql.Date converToDate(String s) throws Exception {
		 if(s==null|| s.equals("")){
	            return null;
	        }
	        if(s.length()==6){
	            s = s + "01";
	        }
	        if(s.length()==7){
	        	s = s + "-01";
	        }
	        String yyyy,MM,dd;
	        String HH="00",mm="00",ss="00";
	        int len = s.length();
	        if(len==8&&CommonVerify.numberVerify(s)){
	            yyyy=s.substring(0,4);
	            MM = s.substring(4,6);
	            dd = s.substring(6,8);
	        }else if(len==14){
	            yyyy=s.substring(0,4);
	            MM = s.substring(4,6);
	            dd = s.substring(6,8);
	            HH = s.substring(8,10);
	            mm = s.substring(10,12);
	            ss = s.substring(12,14);
	        }else{
	            String val = "-";
	            if(s.indexOf("/")>=0){
	                val = "/";
	            }
				if(s.indexOf(".")>=0){
					val = ".";
				}
	            String temp;
	            yyyy = s.substring(0, s.indexOf(val));
	            MM = s.substring(s.indexOf(val) + 1, s.lastIndexOf(val));
	            temp = s.substring(s.lastIndexOf(val) + 1,s.length());
	            if(temp.indexOf(" ")>0){
	                //有时分秒
	                dd = temp.substring(0,temp.indexOf(" "));
	                temp = temp.substring(temp.indexOf(" ")+1,temp.length());
	                HH = temp.substring(0, temp.indexOf(":"));
	                mm = temp.substring(temp.indexOf(":") + 1, temp.lastIndexOf(":"));
	                ss = temp.substring(temp.lastIndexOf(":") + 1,temp.length());
	            }else{
	                dd = temp;
	            }
	        }

		return getDate(Integer.parseInt(yyyy), Integer.parseInt(MM), Integer
				.parseInt(dd), Integer.parseInt(HH), Integer.parseInt(mm),
				Integer.parseInt(ss));
	}

	/**
	 * 根据所给年、月、日、时、分、秒，得到日期(java.sql.Date类型)。 年、月、日不合法会抛IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * @param HH
	 *            时
	 * @param mm
	 *            分
	 * @param ss
	 *            秒
	 * @return
	 */
	public static java.sql.Date getDate(int yyyy, int MM, int dd, int HH,
			int mm, int ss) {
		if (!verityDate(yyyy, MM, dd))
			throw new IllegalArgumentException("不是有效的时间");
		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd, HH, mm, ss);
		return new java.sql.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * 根据所给年、月、日，检验是否为合法日期。
	 * 
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * @return
	 */
	public static boolean verityDate(int yyyy, int MM, int dd) {
		boolean flag = false;

		if (MM >= 1 && MM <= 12 && dd >= 1 && dd <= 31) {
			if (MM == 4 || MM == 6 || MM == 9 || MM == 11) {
				if (dd <= 30)
					flag = true;
			} else if (MM == 2) {
				if (yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0) {
					if (dd <= 29)
						flag = true;
				} else if (dd <= 28)
					flag = true;

			} else
				flag = true;

		}
		return flag;
	}

	/**
	 * 判断传入的字符串是否是１０进制数字 依次取出字符串的每一位,判断取出的字符的ascii码是否在'0'和'9'对应ascii之间
	 * 
	 * @param name
	 * @return boolean
	 * @roseuid 3E486D4E009B
	 */
	public static boolean numberVerify(String name) {
		boolean isNumberChar = true;
		int i = 0;
		char x;
		while (isNumberChar || i >= name.length()) {
			x = name.charAt(i);
			if (x < '0' || x > '9')
				isNumberChar = false;
			i++;
		}
		return isNumberChar;
	}

}
/*******************************************************************************
 * DECOMPILATION REPORT *** DECOMPILED FROM:
 * E:\yfbeclipse\workspace\emistzsw\lemis\WEB-INF\lib\commons-beanutils.jar
 * TOTAL TIME: 219 ms JAD REPORTED MESSAGES/ERRORS: Couldn't fully decompile
 * method convert Couldn't resolve all exception handlers in method convert EXIT
 * STATUS: 0 CAUGHT EXCEPTIONS:
 ******************************************************************************/
