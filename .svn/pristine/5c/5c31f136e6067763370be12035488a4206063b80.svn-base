package org.radf.plat.commons;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.radf.plat.log.LogHelper;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NoConnectionException;

public class CommonDB {
	static LogHelper log = new LogHelper("CommonDB.class");
 
	/**
	 * 获取指定名称得到组织结构代码
	 * 
	 * @return 结果8 位数
	 * @throws AppException
	 */
	public static String dogetinvcmpcd(Connection conn, String sequenceName)
			throws NoConnectionException, SQLException, AppException {
		String seqs = "";

		String logno = "";
		String lognotemp = "";
		int li = 0, lsum = 0;
		int[] lwgroup = { 3, 7, 9, 10, 5, 8, 4, 2 };
		int lcheckcodee = 0, lcheckcode = 0;
		try {
			seqs = DBUtil.getSequence(conn, sequenceName);
		} catch (SQLException se) {
			throw se;
		}

		lognotemp = seqs;

		// ////// /
		lognotemp = "0000000" + lognotemp;
		lognotemp = lognotemp.substring(lognotemp.length() - 7, lognotemp
				.length());
		lognotemp = "A" + lognotemp;

		// ////// /
		for (li = 1; li <= 8; li++) {
			if (((int) lognotemp.charAt(li - 1) >= (int) '0')
					&& ((int) lognotemp.charAt(li - 1) <= (int) '9')) {
				lsum = lsum
						+ new Integer(lognotemp.substring(li - 1, li))
								.intValue() * lwgroup[li - 1];
			} else if (((int) Character.toUpperCase(lognotemp.charAt(li - 1)) >= (int) 'A')
					&& ((int) Character.toUpperCase(lognotemp.charAt(li - 1)) <= (int) 'Z')) {
				lsum = lsum
						+ ((int) Character
								.toUpperCase(lognotemp.charAt(li - 1)) - 55)
						* lwgroup[li - 1];
			}
		}
		// //////
		for (li = 1; li <= 8; li++) {
			if ((((int) lognotemp.charAt(li - 1) >= (int) '0') && ((int) lognotemp
					.charAt(li - 1) <= (int) '9'))
					|| (((int) Character.toUpperCase(lognotemp.charAt(li - 1)) >= (int) 'A') && ((int) Character
							.toUpperCase(lognotemp.charAt(li - 1)) <= (int) 'Z')))
				continue;
			else
				break;
		}
		if (li <= 8) {
			throw new AppException("非法人单位代码生成失败");
		} else {
			lcheckcode = 11 - lsum % 11;
			if (lcheckcode >= 0 && lcheckcode <= 9)
				lcheckcodee = lcheckcode;
			else if (lcheckcode == 10)
				lcheckcodee = (int) 'X';
			else if (lcheckcode == 11)
				lcheckcodee = 0;

			lcheckcodee = lcheckcodee + 1;

			if (lcheckcodee <= 9)
				logno = lognotemp + lcheckcodee;
			else if (lcheckcodee == 10)
				logno = lognotemp + "X";
			else if (lcheckcodee > 10)
				logno = lognotemp + "Y";
		}
		return logno;
	}

	/**
	 * 根据传入序列号 返回
	 * 
	 * @param s
	 * @param i
	 * @param s1
	 * @return
	 * @throws com.lbs.commons.op.OPException
	 */
	public static java.lang.String getSequence(Connection conn,
			java.lang.String s, int i, java.lang.String s1)
			throws SQLException, NoConnectionException {
		String s2 = "";
		try {
			s2 = DBUtil.getSequence(conn, s);
		} catch (SQLException se) {
			log.debug(s);
			throw se;
		}
		if (i == 0 && "0".equals(s1))
			return s2;
		if (i == 0 && !"0".equals(s1))
			return s1 + s2;
		if (i != 0 && "0".equals(s1))
			return StringUtil.fillZero(s2, i);
		if (i != 0 && !"0".equals(s1)) {
			s2 = StringUtil.fillZero(s2, i);
			return s1 + s2.substring(s1.length());
		} else {
			return s2;
		}
	}

	public static java.util.List getDataFromExcel(
			org.apache.struts.upload.FormFile formfile)
			throws java.lang.Exception {
		java.util.ArrayList arraylist = new ArrayList();
		java.io.InputStream inputstream = formfile.getInputStream();
		org.apache.poi.hssf.usermodel.HSSFWorkbook hssfworkbook = new HSSFWorkbook(
				inputstream);
		for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
			org.apache.poi.hssf.usermodel.HSSFSheet hssfsheet = hssfworkbook
					.getSheetAt(i);
			for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++)
				if (j != 0) {
					org.apache.poi.hssf.usermodel.HSSFRow hssfrow = hssfsheet
							.getRow(j);
					short word0 = hssfrow.getLastCellNum();
					java.lang.Object aobj[] = new java.lang.Object[word0];
					Object obj = null;
					for (int k = 0; k < word0; k++) {
						org.apache.poi.hssf.usermodel.HSSFCell hssfcell = hssfrow
								.getCell((short) k);
						if (hssfcell != null)
							switch (hssfcell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC: // '\0'
								/*
								 * POI FAQ says: Excel stores dates as numbers
								 * therefore the only way to determine if a cell
								 * is actually stored as a date is to look at
								 * the formatting. There is a helper method in
								 * HSSFDateUtil (since the 1.7.0-dev release)
								 * that checks for this.
								 */
								double d = hssfcell.getNumericCellValue();
								// test if a date!
								if (HSSFDateUtil.isCellDateFormatted(hssfcell)) {
									// Calendar cal = Calendar.getInstance();
									// cal.setTime(HSSFDateUtil.getJavaDate(d));
									// aobj[k] =
									// String.valueOf(cal.get(Calendar.YEAR)) +
									// "/"
									// + String.valueOf(cal.get(Calendar.MONTH)
									// + 1) + "/"
									// +
									// String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
									aobj[k] = new java.sql.Date((HSSFDateUtil
											.getJavaDate(d)).getTime());
									// aobj[k] = (HSSFDateUtil.getJavaDate(d));
								} else {
									int d_int = (int) d;
									if (d_int == d)
										// Maybe it's only a digital characers,
										// eg. "2", "34", etc
										aobj[k] = (new StringBuffer(String
												.valueOf(d_int))).toString();
									else
										// Take it as a digital number
										aobj[k] = (new StringBuffer(String
												.valueOf(d))).toString();
								}
								break;

							case HSSFCell.CELL_TYPE_STRING: // '\001'
								aobj[k] = hssfcell.getStringCellValue();
								break;

							case HSSFCell.CELL_TYPE_FORMULA: // '\002'
								aobj[k] = hssfcell.getCellFormula();
								break;

							case HSSFCell.CELL_TYPE_BLANK: // '\003'
								aobj[k] = null;
								break;

							case HSSFCell.CELL_TYPE_BOOLEAN: // '\004'
								aobj[k] = new Boolean(hssfcell
										.getBooleanCellValue());
								break;

							case HSSFCell.CELL_TYPE_ERROR: // '\005'
								aobj[k] = new Byte(hssfcell.getErrorCellValue());
								break;

							default:
								aobj[k] = null;
								break;
							}
						else
							aobj[k] = null;
					}

					arraylist.add(((java.lang.Object) (aobj)));
				}

		}

		return arraylist;
	}

    public static java.util.List getDataFromExcel(
            org.apache.struts.upload.FormFile formfile,int startline)
            throws java.lang.Exception {
        java.util.ArrayList arraylist = new ArrayList();
        java.io.InputStream inputstream = formfile.getInputStream();
        org.apache.poi.hssf.usermodel.HSSFWorkbook hssfworkbook = new HSSFWorkbook(
                inputstream);
        for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
            org.apache.poi.hssf.usermodel.HSSFSheet hssfsheet = hssfworkbook
                    .getSheetAt(i);
            for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++)
                if (j >startline) {
                    org.apache.poi.hssf.usermodel.HSSFRow hssfrow = hssfsheet
                            .getRow(j);
                    short word0 = hssfrow.getLastCellNum();
                    java.lang.Object aobj[] = new java.lang.Object[word0];
                    Object obj = null;
                    for (int k = 0; k < word0; k++) {
                        org.apache.poi.hssf.usermodel.HSSFCell hssfcell = hssfrow
                                .getCell((short) k);
                        if (hssfcell != null)
                            switch (hssfcell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // '\0'
                                /*
                                 * POI FAQ says: Excel stores dates as numbers
                                 * therefore the only way to determine if a cell
                                 * is actually stored as a date is to look at
                                 * the formatting. There is a helper method in
                                 * HSSFDateUtil (since the 1.7.0-dev release)
                                 * that checks for this.
                                 */
                                double d = hssfcell.getNumericCellValue();
                                // test if a date!
                                if (HSSFDateUtil.isCellDateFormatted(hssfcell)) {
                                    // Calendar cal = Calendar.getInstance();
                                    // cal.setTime(HSSFDateUtil.getJavaDate(d));
                                    // aobj[k] =
                                    // String.valueOf(cal.get(Calendar.YEAR)) +
                                    // "/"
                                    // + String.valueOf(cal.get(Calendar.MONTH)
                                    // + 1) + "/"
                                    // +
                                    // String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                                    aobj[k] = new java.sql.Date((HSSFDateUtil
                                            .getJavaDate(d)).getTime());
                                    // aobj[k] = (HSSFDateUtil.getJavaDate(d));
                                } else {
                                    int d_int = (int) d;
                                    if (d_int == d)
                                        // Maybe it's only a digital characers,
                                        // eg. "2", "34", etc
                                        aobj[k] = (new StringBuffer(String
                                                .valueOf(d_int))).toString();
                                    else
                                        // Take it as a digital number
                                        aobj[k] = (new StringBuffer(String
                                                .valueOf(d))).toString();
                                }
                                break;

                            case HSSFCell.CELL_TYPE_STRING: // '\001'
                                aobj[k] = hssfcell.getStringCellValue();
                                break;

                            case HSSFCell.CELL_TYPE_FORMULA: // '\002'
                                aobj[k] = hssfcell.getCellFormula();
                                break;

                            case HSSFCell.CELL_TYPE_BLANK: // '\003'
                                aobj[k] = null;
                                break;

                            case HSSFCell.CELL_TYPE_BOOLEAN: // '\004'
                                aobj[k] = new Boolean(hssfcell
                                        .getBooleanCellValue());
                                break;

                            case HSSFCell.CELL_TYPE_ERROR: // '\005'
                                aobj[k] = new Byte(hssfcell.getErrorCellValue());
                                break;

                            default:
                                aobj[k] = null;
                                break;
                            }
                        else
                            aobj[k] = null;
                    }

                    arraylist.add(((java.lang.Object) (aobj)));
                }

        }

        return arraylist;
    }    
    /**
     * 读取地税托收反馈
     * @author llchao
     * @date 2007-3-12 16:46:45
     * @param formfile
     * @return
     * @throws java.lang.Exception
     */
    public static java.util.List feedback(
            org.apache.struts.upload.FormFile formfile)
            throws java.lang.Exception
    {
        java.util.ArrayList arraylist = new ArrayList();
        InputStream inputstream = formfile.getInputStream();
        String filename = formfile.getFileName();
        //BufferedReader br = new BufferedReader(inputstream);
        
        inputstream.read();
        
        return null;
    }
	public static Object objToClass(Object[] obj, Class obj1, String[] prop)
			throws AppException {
		Object result = null;
		obj = trimArray(obj);
		try {
			result = obj1.newInstance();
			for (int i = 0; i < obj.length; i++) {
				BeanUtils.copyProperty(result, prop[i], obj[i]);
			}
		} catch (InvocationTargetException ite) {
			throw new AppException("没有指定的目标！", ite);
		} catch (IllegalAccessException iae) {
			throw new AppException("访问属性时出错！", iae);
		} catch (InstantiationException ie) {
			throw new AppException("实例化对象时出错！", ie);
		}

		return result;
	}
	public static Object[] trimArray(Object[] obj) {
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof String) {
				obj[i] = TypeCast.objToString(obj[i]).trim();
			}
		}

		return obj;
	}
    
    /**
     * 将输入的参数转化为非空(null)的字符串
     * 
     * @param obj
     *            Object
     * @return String
     * @author raofh
     */
    public static String trimToNotNull(String aab301) {
        if (aab301 == null || "".equals(aab301.trim())) {
            return "%";
        } else if(aab301!=null&&aab301.length()>=12){
            aab301 = aab301.substring(0,12);
            while (aab301.substring(aab301.length() - 1, aab301.length())
                    .equals("0")) {
                aab301 = aab301.substring(0, aab301.length() - 1);
            }
            return aab301;
        }else{
            return "%";
        }
    }
	/**
	 * 获取缴费比例和基数
	 * 
	 * @author 叶鹏
	 * @date 2006-10-25 18:17:25
	 * @param year
	 *            缴费年度
	 * @param feetype
	 *            缴费类型，企业、事业,管理对象
	 * @param feeitem
	 *            缴费项目
	 * @return
	 */
//	public static HashMap getBaseProp(String year, String feetype,
//			String feeitem) {
//		// 医保交费标准默认为当前
//		HashMap hm = (HashMap) OptionDict.getAa81map().get(
//				feetype + "::" + feeitem + "::" + year + "::0");
//
//		// 以下部分用来测试，实际代码下次加
//		// hm.put("base","1100");
//		// hm.put("prop","0.05");
//		return hm;
//	}

	/**
	 * 获取缴费比例和基数
	 * 
	 * @author 叶鹏
	 * @date 2006-10-25 18:17:25
	 * @param year
	 *            缴费年度
	 * @param feetype
	 *            缴费类型，企业、事业,管理对象
	 * @param feeitem
	 *            缴费项目
	 * @param med
	 *            医疗保险交费标准，0当前，1参照，2，按照
	 * @return
	 */
//	public static HashMap getBaseProp(String year, String feetype,
//			String feeitem, String med) {
//
//		HashMap hm = (HashMap) OptionDict.getAa81map().get(
//				feetype + "::" + feeitem + "::" + year + "::" + med);
//
//		// 以下部分用来测试，实际代码下次加
//		// hm.put("base","1100");
//		// hm.put("prop","0.05");
//		return hm;
//	}

}
