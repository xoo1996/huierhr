package org.radf.plat.commons;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;

/**
 * ��Date��صĴ�����,������ʵ����
 * 
 * @author zqb
 * @version 1.0
 */
public abstract class DateUtil {

	/**
	 * �õ�Ӧ�÷�������ǰ���ڣ�ֻ�������գ�û��ʱ����
	 * 
	 * @return java.util.Date
	 */
	public static java.sql.Date getDate() {
		Calendar oneCalendar = Calendar.getInstance();
		return getDate(oneCalendar.get(Calendar.YEAR), oneCalendar
				.get(Calendar.MONTH) + 1, oneCalendar.get(Calendar.DATE));
	}

	/**
	 * ���������ꡢ�¡��գ��õ�����(java.util.Date����)��ע�⣺ֻ�������գ�û��ʱ���롣
	 * �ꡢ�¡��ղ��Ϸ�����IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4λ��
	 * @param MM
	 *            ��
	 * @param dd
	 *            ��
	 * @return ����
	 */
	public static java.sql.Date getDate(int yyyy, int MM, int dd) {
		return getDate(yyyy, MM, dd, 0, 0, 0);
	}

	/**
	 * ���������ꡢ�¡��ա�ʱ���֡��룬�õ�����(java.util.Date����)�� �ꡢ�¡��ղ��Ϸ�����IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4λ��
	 * @param MM
	 *            ��
	 * @param dd
	 *            ��
	 * @param HH
	 *            ʱ
	 * @param mm
	 *            ��
	 * @param ss
	 *            ��
	 * @return
	 */
	public static java.sql.Date getDate(int yyyy, int MM, int dd, int HH,
			int mm, int ss) {
		if (!verityDate(yyyy, MM, dd))
			throw new IllegalArgumentException("������Ч��ʱ��");
		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd, HH, mm, ss);
		return new java.sql.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * ��ȡӦ�÷�����ϵͳʱ�䣬���������գ�����ʱ����
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date getSystemCurrentTime() {
		long currentTime = System.currentTimeMillis();
		return new java.sql.Date(currentTime);		
		
	}

	/**
	 * ��ȡӦ�÷�����ϵͳʱ�䣬���������գ�����ʱ���룬�����ո�ʽ������
	 * 
	 * @param type
	 *            ��ʽ�ַ���
	 * @return
	 */
	public static String getSystemCurrentTime(String type) {
		Calendar calendar = Calendar.getInstance();
		Object obj = null;
		java.util.Date date = calendar.getTime();
		return converToString(date, type);
	}
	
	/**
	 * ���Oracle��ʽ���ַ���ʱ�䣺YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param date
	 * @return
	 */
	public static String getOracleFormatDateStr(java.util.Date date) {
		return converToString(date, "YYYY-MM-DD HH24:MI:SS");
	}

	/**
	 * ����ʱ���ʽ��ת��:java.util.Date to java.sql.Date
	 * 
	 * @param java.util.Date
	 *            date
	 * @return java.sql.Date
	 */
	public static java.sql.Date converUtilTOSql(java.util.Date date) {
		java.sql.Date d = java.sql.Date.valueOf(date.toString());
		return d;
	}

	/**
	 * ����ʱ���ʽ��ת����java.sql.Date to java.util.Date
	 * 
	 * @param java.sql.Date
	 *            date
	 * @return java.util.Date
	 */
	public static java.util.Date converSqlTOUtil(java.sql.Date date) {
		return (java.util.Date) date;
	}

	/**
	 * (ԭ������getFullDate) ת�����ڸ�ʽ���ַ��������ݴ��ݵ�ʱ����ָ����ʽ��ʱ����Ϣ��
	 * ת���ĸ�ʽ���Ϳ������ж��壨������yyyy��MM��ddʱHH��mm��ss����<br>
	 * (1)ת����yyyy-MM-dd HH:mm:ss��ʽ��2005-5-25 10:50:24<br>
	 * (2)ת����yyyy��MM��dd�� HH:mm:ss��ʽ��2005��5��25�� 10:50:24<br>
	 * (3)ת����yyyyMMddHHmmss��ʽ��20061024152356<br>
	 * (4)ת����yyyy-MM-dd��ʽ��2006-12-11<br>
	 * (5)ת����yyyyMMdd��ʽ��20061211<br>
	 * (5)ת����yyyyMM��ʽ��200612<br>
	 * (6)ת������nullʱ����ʽĬ��Ϊyyyy-MM-dd HH:mm:ss
	 * 
	 * @param Date
	 * @param type
	 *            ��ʽ����
	 * @return
	 */
	public static String converToString(java.util.Date dt, String type) {
		String returnStr = null;
		if (dt == null) {
			return null;
		} else {
			// YYYY-MM-DD HH24:MI:SS
			type = StringUtil.StringReplace("YYYY", "yyyy", type);
			type = StringUtil.StringReplace("DD", "dd", type);
			type = StringUtil.StringReplace("SS", "ss", type);
			type = StringUtil.StringReplace("hh24", "HH", type);
			type = StringUtil.StringReplace("HH24", "HH", type);
			type = StringUtil.StringReplace("MI", "mm", type);
			type = StringUtil.StringReplace("mi", "mm", type);
		}
		if (type == null || type.trim().equals("")) {
			returnStr = DateFormat.getDateTimeInstance().format(dt);
		} else {
			SimpleDateFormat f = new SimpleDateFormat(type);
			returnStr = f.format(dt);
		}
		return returnStr;
	}

	/**
	 * ת�����ڸ�ʽ�����ת�����Ͳ������򷵻�ԭ���ַ��������ԭʼ�ַ���Ϊnull�򷵻�null��<br>
	 * (1)��yyyyMMddHHmmss��ʽתΪyyyy-MM-dd HH:mm:ss��ʽ<br>
	 * (2)��yyyy-MM-dd��ʽתΪyyyyMMdd��ʽ������ԭʼ��ʽ�м�ָ�������<br>
	 * (3)��yyyy-MM-dd��ʽתΪyyyyMM��ʽ<br>
	 * 
	 * @param s
	 * @param type
	 * @exception AppException
	 * @return
	 */
	public static String converToString(String s, int type) throws AppException {
		String strRet = null;
		if (s == null || s.equals("")) {
			return null;
		}
		switch (type) {
		case 1:
			try {
				strRet = s.substring(0, 4) + "-" + s.substring(4, 6) + "-"
						+ s.substring(6, 8) + " " + s.substring(8, 10) + ":"
						+ s.substring(10, 12) + ":" + s.substring(12, 14);
			} catch (Exception e) {
				throw new AppException(GlobalErrorCode.INPUTPARAMTYPEERRORCODE,
						"���Ϸ���ʱ���ַ���(��ȷ��ʽӦ��ΪyyyyMMddHHmmss)");
			}
			break;
		case 2:
			try {
				strRet = s.substring(0, 4) + s.substring(5, 7)
						+ s.substring(8, 10);
			} catch (Exception e) {
				throw new AppException(GlobalErrorCode.INPUTPARAMTYPEERRORCODE,
						"���Ϸ���ʱ���ַ���(��ȷ��ʽӦ��Ϊyyyy-MM-dd)");
			}
			break;
		case 3:
			int yyyy,
			MM;
			try {
				Date oneDay = TypeCast.stringToDate(s, "", true);
				Calendar ca = Calendar.getInstance();
				ca.clear();
				ca.setTime(oneDay);
				yyyy = ca.get(Calendar.YEAR);
				MM = ca.get(Calendar.MONTH) + 1;
			} catch (Exception e) {
				throw new AppException(GlobalErrorCode.INPUTPARAMTYPEERRORCODE,
						"���Ϸ���ʱ���ַ���(��ȷ��ʽӦ��Ϊyyyy-MM-dd)");
			}
			if (yyyy < 1000 || yyyy > 9999)
				throw new AppException(GlobalErrorCode.INPUTPARAMTYPEERRORCODE,
						"��������");
			if (MM < 10) {
				strRet = "0" + MM;
			} else {
				strRet = "" + MM;
			}
			strRet = yyyy + strRet;
			break;
		default:
			strRet = s;
		}
		return strRet;
	}

	/**
	 * ת���ַ���ʱ������Ϊָ����ʽ���ַ���ʱ�䡣 ԭʼʱ���ַ���ֻ�������������е�һ���� (1)��yyyyMMddHHmmss��ʽ
	 * (2)��yyyyMMdd��ʽ (3)��yyyy-MM-dd HH:mm:ss��ʽ (4)��yyyy-MM-dd��ʽ��
	 * ���ص��ַ������Ϳ������ж��壬���У�yyyy-�ꡢMM-�¡�dd-�ա�HH-ʱ��mm-�֡�ss-��
	 * 
	 * @param dt
	 * @param type
	 * @return
	 * @throws AppException
	 */
	public static String converToString(String dt, String type) {
		Date oneDay = converToDate(dt);
		return converToString(oneDay, type);

	}

	/**
	 * ת���ַ�����ʽ����Ϊjava.sql.Date���ڣ����ԭʼ�ַ���Ϊnull�򷵻�null�� �Զ�֧�ִ����¸�ʽ�ַ���ת����<br>
	 * (1)��yyyyMMddHHmmss��ʽ (2)��yyyyMMdd��ʽ (3)��yyyy-MM-dd HH:mm:ss��ʽ
	 * (4)��yyyy-MM-dd��ʽ (5)��yyyy-MM��ʽ(�Զ���Ϊ1��)
	 * 
	 * @param s
	 * @param type
	 * @exception AppException
	 * @return java.sql.Date
	 */
	public static java.util.Date converToDate(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		if (s.length() == 6) {
			s = s + "01";
		}
		String yyyy, MM, dd;
		String HH = "00", mm = "00", ss = "00";
		int len = s.length();
		if (len == 8 && CommonVerify.numberVerify(s)) {
			yyyy = s.substring(0, 4);
			MM = s.substring(4, 6);
			dd = s.substring(6, 8);
		} else if (len == 14) {
			yyyy = s.substring(0, 4);
			MM = s.substring(4, 6);
			dd = s.substring(6, 8);
			HH = s.substring(8, 10);
			mm = s.substring(10, 12);
			ss = s.substring(12, 14);
		} else {
			String val = "-";
			if (s.indexOf("/") >= 0) {
				val = "/";
			}
			String temp;
			yyyy = s.substring(0, s.indexOf(val));
			MM = s.substring(s.indexOf(val) + 1, s.lastIndexOf(val));
			temp = s.substring(s.lastIndexOf(val) + 1, s.length());
			if (temp.indexOf(" ") > 0) {
				// ��ʱ����
				dd = temp.substring(0, temp.indexOf(" "));
				temp = temp.substring(temp.indexOf(" ") + 1, temp.length());
				HH = temp.substring(0, temp.indexOf(":"));
				mm = temp.substring(temp.indexOf(":") + 1, temp
						.lastIndexOf(":"));
				ss = temp.substring(temp.lastIndexOf(":") + 1, temp.length());
				if (ss.indexOf(".") > 0) {
					ss = ss.substring(0, ss.lastIndexOf("."));
				}
			} else {
				dd = temp;
			}
		}

		return getDate(Integer.parseInt(yyyy), Integer.parseInt(MM), Integer
				.parseInt(dd), Integer.parseInt(HH), Integer.parseInt(mm),
				Integer.parseInt(ss));
	}

	/**
	 * �õ���date����ָ���������date
	 * 
	 * @param date
	 * @param intBetween
	 * @return date����intBetween�����������
	 */
	public static java.sql.Date getStepYear(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.YEAR, intBetween);
		return new java.sql.Date(calo.getTime().getTime());
	}

	/**
	 * ��ȡ����ʱ���n���µ�ʱ��
	 * 
	 * @param date
	 * @param intBetween
	 * @return date����intBetween�����������
	 */
	public static java.util.Date getStepMonth(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.MONTH, intBetween);
		return new java.util.Date(calo.getTime().getTime());
	}

	/**
	 * ��ȡ����ʱ���n���µ�yyyyMM(ԭ���Ĵ˷�������getYearAndMonth)�� ����Ϊ����ʾ���Ժ󣬸���ʾ����ǰ��ʱ���ַ�����ʽ����Ϊ��<br>
	 * (1)��yyyyMMddHHmmss��ʽ<br>
	 * (2)��yyyyMMdd��ʽ<br>
	 * (3)��yyyy-MM-dd HH:mm:ss��ʽ<br>
	 * (4)��yyyy-MM-dd��ʽ<br>
	 * (5)��yyyyMM��ʽ<br>
	 * 
	 * @param inputDate
	 * @param step
	 * @return
	 */
	public static String getStepMonth(String dateStr, int step) {
		if (dateStr != null && dateStr.length() == 6)
			dateStr = dateStr + "01";
		if(dateStr == null || "".equals(dateStr))
		{
			return "";
		}
		Date oneDay = converToDate(dateStr);
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(oneDay);

		ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) + step);

		int yyyy = ca.get(Calendar.YEAR);
		int MM = ca.get(Calendar.MONTH) + 1;
		String month = "" + MM;

		if (MM < 10)
			month = "0" + MM;
		return "" + yyyy + month;
	}

	/**
	 * ������������ʼʱ��,���������������ֹʱ��
	 * 
	 * @param startDate
	 * @param day
	 * @return ��ֹʱ��
	 */
	public static java.sql.Date getStepDay(java.util.Date date, int step) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, step);
		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * ������������ʼʱ��,���������������ֹʱ�䣬��ת����ָ����ʽ�ַ���
	 * 
	 * @param date
	 * @param step
	 * @param type
	 * @return
	 */
	public static String getStepDay(java.util.Date date, int step, String type) {
		return converToString(getStepDay(date, step), type);
	}

	/**
	 * ������������ʼ,��ֹʱ�������������� ���ʱ��ֻ��¼�������֣�����1��2�㵽3��23�㣬���2��21СʱҲ������2��
	 * 
	 * @param startDate
	 * @param endDate
	 * @return �������
	 */
	public static int getIntervalDay(java.util.Date startDate,
			java.util.Date endDate) {
        if(startDate==null||endDate==null){
            return 0;
        }
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		double day = interval / (1000 * 60 * 60 * 24);
		int intervalday = (int) (day);
		if (day > intervalday)
			intervalday++;
		return intervalday;
	}
    /**
     * ������������ʼ,��ֹʱ��������������
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalSecond(java.util.Date startDate,
            java.util.Date endDate) {
        if(startDate==null||endDate==null){
            return 0;
        }
        long startdate = startDate.getTime();
        long enddate = endDate.getTime();
        long interval = enddate - startdate;
        double second = interval / 1000;
        int intervalSecond = (int)second;
        if(second>intervalSecond)
            intervalSecond++;
        return intervalSecond;
    }
	/**
	 * ������������ʼ,��ֹʱ���������������� ����ṩ�Ŀ�ʼʱ��ͽ���ʱ�䲻�Ϸ����򷵻�0
	 * 
	 * @param startDate
	 *            YYYYMM
	 * @param startDate
	 *            YYYYMM
	 * @return �������
	 */
	public static int getIntervalMonth(String s, String s1) {
		try {
			String startDate = converToString(s, "yyyyMM");
			String endDate = converToString(s1, "yyyyMM");
			int i = Integer.parseInt(startDate.substring(0, 4)) * 12
					+ Integer.parseInt(startDate.substring(4, 6));
			int j = Integer.parseInt(endDate.substring(0, 4)) * 12
					+ Integer.parseInt(endDate.substring(4, 6));
			int intervalMonth = j - i;
			return intervalMonth;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * ������������ʼ,��ֹʱ��������������
	 * 
	 * @param Date
	 * @param Date
	 * @return
	 */
	public static int getIntervalMonth(java.util.Date startDate,
			java.util.Date endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
		String str = f.format(startDate);
		String end = f.format(endDate);
		return getIntervalMonth(str, end);
	}

	/**
	 * ��ȡ��ǰ�����
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		int yyyy = calendar.get(1);
		return yyyy;
	}
	/**
	 * ���ص�ǰ�ɷѻ��������
	 * 
	 * @author llchao
	 * @date 2007-3-8 14:24:07
	 * @param nowdate
	 * @return
	 */
	public static String baseYear(Date nowdate) {
		String year = null;
		int yearint = Integer.parseInt(nowdate.toString().substring(0, 4));
		int monthint = Integer.parseInt(nowdate.toString().substring(5, 7));
		if (monthint < 7) {
			year = String.valueOf(yearint - 1);
		}
		return year;
	}
	/**
	 * �õ������ַ�������ݲ��֣�������4λ���֣�������ǺϷ���ݣ�����׳�����
	 * 
	 * @param dateStr
	 * @return
	 */
	public static int getYear(String dateStr) {
		Date oneDay = converToDate(dateStr);
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(oneDay);
		int yyyy = ca.get(Calendar.YEAR);
		return yyyy;
	}
	/**
	 * �õ������ַ�������ݲ��֣�������4λ���֣�������ǺϷ���ݣ�����׳�����
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getstrYear(String dateStr) {
		Date oneDay = converToDate(dateStr);
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(oneDay);
		int yyyy = ca.get(Calendar.YEAR);
		return TypeCast.intToString(yyyy);
	}
	/**
	 * �õ����£�yyyyMM��ʽ ���鳬 ���ڵõ�����ҵ���еĲ��˿�ʼ�ں�
	 * 
	 * @param dateStr
	 * @return
	 * @throws ApplicationException
	 */
	public static String getYearAndMonth(String dateStr) throws AppException {
		Date oneDay = TypeCast.stringToDate(dateStr, "", true);
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(oneDay);
		int yyyy = ca.get(Calendar.YEAR);
		if (yyyy < 1000 || yyyy > 9999)
			throw new AppException("������꣡");
		int MM = ca.get(Calendar.MONTH) + 1;
		String month = "" + MM;
		if (MM < 10)
			month = "0" + MM;
		return "" + yyyy + month;
	}

	/**
	 * �Ƚ�yyyymm��yyyymm�Ĵ�С
	 * 
	 * @param dateStr
	 * @return
	 * @throws ApplicationException
	 */
	 public static boolean yearMonthGreatEqual(String s, String s1)
	    {
	        String s2 = s.substring(0, 4);
	        String s3 = s1.substring(0, 4);
	        String s4 = s.substring(4, 6);
	        String s5 = s1.substring(4, 6);
	        if(Integer.parseInt(s2) > Integer.parseInt(s3))
	            return true;
	        if(Integer.parseInt(s2) == Integer.parseInt(s3))
	            return Integer.parseInt(s4) >= Integer.parseInt(s5);
	        else
	            return false;
	    }

	 /**
		 * �������Integer���͵�����ת����"X��X��"��ʽ���ַ���
		 * 
		 * @param month
		 *            Integer
		 * @return String
		 */
		public static String month2YearMonth(Integer month) {
			String yearMonth = "";
			int smonth = 0;
			int year = 0;
			int rmonth = 0;

			if ((month == null) || (month.equals(new Integer("0")))) {
				return "0��";
			}

			smonth = month.intValue();
			year = smonth / 12;
			rmonth = smonth - 12 * year;

			if (year > 0) {
				yearMonth = TypeCast.intToString(year) + "��";
			}
			if (rmonth > 0) {
				yearMonth += TypeCast.intToString(rmonth) + "����";
			}

			return yearMonth;
		}
	/**
	 * ԭ�ں�i����֮ǰ����֮����ں�ֵ.200310��5��Ϊ200403
	 * 
	 * @param str
	 *            String
	 * @param how
	 *            int
	 * @return String
	 */
	public static String getAddIssue(String str, int how) {
		String issue = str; // ԭ�ںŸ�ʽΪ��200302
		int i = how; // i����֮��

		int n_year = Integer.parseInt(issue) / 100;
		int n_month = Integer.parseInt(issue) % 100;
		int aY = i / 12;
		int aM = i % 12;
		n_year = n_year + aY;
		n_month = n_month + aM;
		if (n_month > 12) {
			n_year = n_year + 1;
			n_month = n_month - 12;
		}
		if (n_month <= 0) {
			n_year = n_year - 1;
			n_month = 12 + n_month;
		}
		if (n_month < 10) {
			issue = ((Integer.toString(n_year).trim()) + '0' + ((Integer
					.toString(n_month).trim())));
		} else {
			issue = ((Integer.toString(n_year).trim()) + ((Integer
					.toString(n_month).trim())));
		}

		return issue;
	}
	/**
	 * �õ���һ���ں����£�yyyyMM��ʽ
	 * 
	 * @param dateStr
	 * @return
	 * @throws ApplicationException
	 */

	public static String increaseYearMonth(String s) {
		int i = (new Integer(s.substring(0, 4))).intValue();
		int j = (new Integer(s.substring(4, 6))).intValue();
		if (++j <= 12 && j >= 10)
			return s.substring(0, 4) + (new Integer(j)).toString();
		if (j < 10)
			return s.substring(0, 4) + "0" + (new Integer(j)).toString();
		else
			return (new Integer(i + 1)).toString() + "0"
					+ (new Integer(j - 12)).toString();
	}
	/**
	 * �õ����£�yyyyMM��ʽ
	 * 
	 * @param dateStr
	 * @return
	 * @throws ApplicationException
	 */
	 public static String convertDateToYearMonth(java.util.Date date)
	    {
	        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMM", new DateFormatSymbols());
	        return simpledateformat.format(date);
	    }
	 public static String convertDateToYearMonthDay(java.util.Date date) {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd",
					new DateFormatSymbols());
			return simpledateformat.format(date);
		}
	 /**
		 * ������ķѿ�������ת����"XXXX��XX��"��ʽ���ַ���
		 * 
		 * @param month
		 *            Integer
		 * @return String
		 */
		public static String period2YearMonth(String ym) {
			if (ym == null) {
				return "";
			} else {
				String year = ym.substring(0, 4);
				String month = ym.substring(4, 6);
				return year + "��" + month + "��";
			}
		}

	/**
	 * ����ָ��ʱ�䵱�µ����һ�죬��ʽ��yyyy��MM��dd��
	 * 
	 * @param ss
	 * @return
	 */
	public static String getLastDay(String ss) {
		String s = null;
		if (ss.length() != 6) {
			s = converToString(ss, "yyyyMM");
		} else {
			s = ss;
		}
		int i = Integer.parseInt(s.substring(0, 4));
		int j = Integer.parseInt(s.substring(4, 6));
		String s1 = "";
		if (j == 2) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
				s1 = "29";
			else
				s1 = "28";
		} else if (j == 4 || j == 6 || j == 9 || j == 11)
			s1 = "30";
		else
			s1 = "31";
        String s2="0"+String.valueOf(j);
        s2=s2.substring(s2.length()-2,s2.length());
		return String.valueOf(i) + "" + s2 + "" + s1 + "";//ԭ������x��x��x�յ���ʽ��Ҷ���޸�
	}

	/**
	 * ����ָ��ʱ�䵱�µ����һ�죬��ʽ��yyyy��MM��dd��
	 * 
	 * @param dt
	 * @return
	 */
	public static String getLastDay(java.util.Date dt) {
		String s = converToString(dt, "yyyyMM");
		return getLastDay(s);
	}

    /**
     * 
     * @author yp
     * @date 2007-2-2 10:07:02
     * @param dt
     * @return
     */
    public static String getFirstDay(Date dt) {
        String s = converToString(dt, "yyyyMM");
        return s+"01";
    }
	/**
	 * ���ص�ǰʱ����¸��µ�һ�죬��ʽyyyyMMdd
	 * 
	 * @return
	 */
	public static String getFirstDayOfNextMonth() {
		String s = converToString(getDate(), "yyyyMMdd");
		return getStepMonth(s.substring(0, 6), 1) + "01";
	}

	/**
	 * ��ȡָ��ʱ����ϸ����£���ʽyyyyMM
	 * 
	 * @param dt
	 * @return
	 */
	public static String descreaseYearMonth(String dt) {
		String s = converToString(dt, "yyyyMMdd");
		int i = (new Integer(s.substring(0, 4))).intValue();
		int j = (new Integer(s.substring(4, 6))).intValue();
		if (--j >= 10)
			return s.substring(0, 4) + (new Integer(j)).toString();
		if (j > 0 && j < 10)
			return s.substring(0, 4) + "0" + (new Integer(j)).toString();
		else
			return (new Integer(i - 1)).toString()
					+ (new Integer(j + 12)).toString();
	}

	/**
	 * ����ӳ���ʱ�䵽ָ��ʱ�������
	 * 
	 * @param birthday
	 *            ����ʱ��
	 * @param endDate
	 *            �������ֹʱ��
	 * @return int
	 */
	public static int getAge(Date birthday, Date endDate) {
		int month = getIntervalMonth(birthday, endDate);
		if (month <= 0) {
			return 0;
		} else {
			return month / 12;
		}
	}

	/**
	 * �������֤����������
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static int getAge(String id) throws Exception {
		int i = -1;
		int j = id.length();
		String s1 = "";
		if (j == 15) {
			s1 = id.substring(6, 8);
			s1 = "19" + s1;
		} else if (j == 18)
			s1 = id.substring(6, 10);
		else
			throw new Exception("��������֤��");
		int k = Calendar.getInstance().get(1);
		i = k - (new Integer(s1)).intValue();
		return i;
	}

	/**
	 * �Խ���Ϊ���գ����������ȡ��������
	 * 
	 * @param age
	 * @return
	 */
	public static Date getBirtday(int age) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		long l = calendar.getTimeInMillis();
		calendar.set(calendar.get(1) - age, calendar.get(2), calendar.get(5));
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * �������֤�����ȡ��������(��ȷ�����֤���س������ڣ�����ķ��ص�ǰ���ݿ������)
	 * 
	 * @param id
	 * @return
	 * @exception Exception
	 */
	public static String getBirtday(String id) throws Exception {
		String birthday = "";
		int idLength = id.length();
		String yy = "";
		int YY = 0;
		String mm = "";
		int MM = 0;
		String dd = "";
		int DD = 0;
		boolean leapYear = false;
		String today = converToString(new Date(), "yyyy-mm-dd");

		if (idLength == 15) {
			yy = "19" + id.substring(6, 8);
			mm = id.substring(8, 10);
			dd = id.substring(10, 12);
		} else if (idLength == 18) {
			yy = id.substring(6, 10);
			mm = id.substring(10, 12);
			dd = id.substring(12, 14);
		} else {
			return (converToString(new Date(), "yyyy-mm-dd"));
		}
		YY = (new Integer(yy)).intValue();
		MM = (new Integer(mm)).intValue();
		DD = (new Integer(dd)).intValue();
		if (YY < 1900 || YY > 2200) {
			return (today);
		}

		if (((YY % 4) != 0) && ((YY % 100) != 0)) { // �ж��Ƿ�Ϊ����
			leapYear = false;
		} else {
			leapYear = true;
		}
		if (MM == 2) {
			if (leapYear) {
				if (DD < 1 || DD > 29) {
					return (today);
				}
			} else {
				if (DD < 1 || DD > 28) {
					return (today);
				}
			}
		}
		if ((MM == 1) || (MM == 3) || (MM == 5) || (MM == 7) || (MM == 8)
				|| (MM == 10) || (MM == 12)) {
			if (DD < 1 || DD > 31) {
				return (today);
			}
		}
		if ((MM == 4) || (MM == 6) || (MM == 9) || (MM == 11)) {
			if (DD < 1 || DD > 30) {
				return (today);
			}
		}
		birthday = yy + "-" + mm + "-" + dd;
		return birthday;
	}

	/**
	 * �������֤�����ȡ�Ա�(����ֵ��1���У�2��Ů����Ϊ���֤�������)
	 * 
	 * @param id
	 * @return
	 * @exception Exception
	 */
	public static String getGender(String iDCard) {
		int gender = 3;
		System.out.print(iDCard);
		if (iDCard.length() == 15) {
			gender = (new Integer(iDCard.substring(14, 15))).intValue() % 2;
		} else if (iDCard.length() == 18) {
			int number17 = (new Integer(iDCard.substring(16, 17))).intValue();
			gender = number17 % 2;
		}
		if (gender == 1) {
			return "1";
		} else if (gender == 0) {
			return "2";
		} else {
			return "";
		}
	}

	/**
	 * ���������ꡢ�¡��գ������Ƿ�Ϊ�Ϸ����ڡ�
	 * 
	 * @param yyyy
	 *            4λ��
	 * @param MM
	 *            ��
	 * @param dd
	 *            ��
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
	 * ��������ַ����Ƿ���ȷ����ʽ��yyyy-MM-dd
	 * 
	 * @param dateString
	 * @return
	 */
	public static boolean checkDateString(String dateString) {
		boolean check = false;
		try {
			Date oneDay = TypeCast.stringToDate(dateString, "", true);
			Calendar ca = Calendar.getInstance();
			ca.clear();
			ca.setTime(oneDay);
			int yyyy = ca.get(Calendar.YEAR);
			if (yyyy >= 1000 && yyyy <= 9999)
				check = true;
		} catch (Exception e) {
			check = false;
		}
		return check;
	}

	/**
	 * �Ƚ�����ʱ������£������һ���ȵڶ������򷵻�true�����򷵻�false
	 * 
	 * @param dt
	 * @param s1
	 * @return
	 */
	public static boolean yearMonthGreater(String dt1, String dt2) {
		return yearMonthGreater(converToDate(dt1), converToDate(dt2));
	}

	/**
	 * �Ƚ�����ʱ������£������һ���ȵڶ������򷵻�true�����򷵻�false
	 * 
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static boolean yearMonthGreater(Date dt1, java.util.Date dt2) {
		String s = converToString(dt1, "yyyyMM");
		String s1 = converToString(dt2, "yyyyMM");
		String s2 = s.substring(0, 4);
		String s3 = s1.substring(0, 4);
		String s4 = s.substring(4, 6);
		String s5 = s1.substring(4, 6);
		if (Integer.parseInt(s2) > Integer.parseInt(s3))
			return true;
		if (Integer.parseInt(s2) == Integer.parseInt(s3))
			return Integer.parseInt(s4) > Integer.parseInt(s5);
		else
			return false;
	}

	/**
	 * ��������ת����x��x���¸�ʽ��ʾ�� ��month2YearMonth("16")="1��4����"
	 * 
	 * @param s
	 * @return
	 */
	public static String month2YearMonth(String s) {
		String s1 = "";
		if (s == null || "0".equals(s) || "".equals(s.trim()))
			return "0��";
		int i = Integer.parseInt(s);
		int j = i / 12;
		int k = i % 12;
		if (j > 0)
			s1 = j + "��";
		if (k > 0)
			s1 = s1 + k + "����";
		return s1;
	}

//�õ���ǰ���� by lwd
  public static String getCurrentYearMonth()
    {
        Calendar calendar = Calendar.getInstance();
        String s = (new Integer(calendar.get(1))).toString();
        String s1 = null;
        if(calendar.get(2) < 9)
            s1 = "0" + (new Integer(calendar.get(2) + 1)).toString();
        else
            s1 = (new Integer(calendar.get(2) + 1)).toString();
        return s + s1;
    }

	public static void main(String[] args) {
		try {
			java.sql.Date dt1 = getDate();
			java.util.Date dt2 = getSystemCurrentTime();
			String dt3 = "2006/12/05 15:30:40";
			String dt4 = "200611";
			String dt5 = "200711";
			System.out.println(DateUtil.converToString(getSystemCurrentTime(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println(DateUtil.converToString(dt2,"yyyy-MM-dd HH:mm:ss"));
			System.out.println(DateUtil.getIntervalSecond(dt2,DateUtil.converToDate(dt3)));
			System.out.println(dt1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}