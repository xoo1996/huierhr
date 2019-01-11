package org.radf.plat.commons;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForward;

public class ActionUtil {
	public ActionUtil() {
	}

	public static org.apache.struts.action.ActionForward exportDataAsExcel(
			javax.servlet.http.HttpServletRequest httpservletrequest,
			java.util.List list, java.util.LinkedHashMap linkedhashmap,
			java.lang.String s) throws java.lang.Exception {
		java.util.Iterator iterator = linkedhashmap.keySet().iterator();
		if (list == null || !list.iterator().hasNext() || linkedhashmap == null
				|| linkedhashmap.size() == 0 || s == null)
			return null;
		boolean flag = true;
		// java.lang.String as[] = (java.lang.String[]) null;
		java.lang.Object obj = list.iterator().next();
		/*
		 * if (obj instanceof java.lang.Object[]) { flag = false; as =
		 * ActionUtil.getQueryFields(s); }
		 */
		org.apache.poi.hssf.usermodel.HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		org.apache.poi.hssf.usermodel.HSSFSheet hssfsheet = hssfworkbook
				.createSheet("sheet1");
		try {
			org.apache.poi.hssf.usermodel.HSSFRow hssfrow = hssfsheet
					.createRow(0);
			for (int i = 0; iterator.hasNext(); i++) {
				java.lang.String s1 = (java.lang.String) linkedhashmap
						.get(iterator.next());
				org.apache.poi.hssf.usermodel.HSSFCell hssfcell = hssfrow
						.createCell((short) i);
				//hssfcell.setEncoding((short) 1);
				hssfcell.setCellValue(s1);
			}

			int j = 1;
			for (java.util.Iterator iterator1 = list.iterator(); iterator1
					.hasNext();) {
				org.apache.poi.hssf.usermodel.HSSFRow hssfrow1 = hssfsheet
						.createRow(j);
				java.lang.Object obj1 = iterator1.next();
				java.util.Iterator iterator2 = linkedhashmap.keySet()
						.iterator();
				for (int k = 0; iterator2.hasNext(); k++) {
					java.lang.String s2 = (java.lang.String) iterator2.next();
					java.lang.Object obj2 = null;
					org.apache.poi.hssf.usermodel.HSSFCell hssfcell1 = hssfrow1
							.createCell((short) k);
					// if (flag)
					obj2 = org.apache.commons.beanutils.PropertyUtils
							.getSimpleProperty(obj1, s2);
					/*
					 * else obj2 = ((java.lang.Object[]) obj1)[ActionUtil
					 * .getIndex(as, s2)];
					 */
					//hssfcell1.setEncoding((short) 1);
					hssfcell1.setCellValue(obj2 != null ? obj2.toString() : "");
				}

				j++;
			}

			httpservletrequest.setAttribute("excelData", hssfworkbook);
		} catch (java.lang.NoSuchMethodException nosuchmethodexception) {
			nosuchmethodexception.printStackTrace();
			throw new Exception(
					"\u5BFC\u51FAexcel\u6570\u636E\u65F6\u53D1\u751F\u5F02\u5E38\uFF01",
					nosuchmethodexception);
		} catch (java.lang.reflect.InvocationTargetException invocationtargetexception) {
			invocationtargetexception.printStackTrace();
			throw new Exception(
					"\u5BFC\u51FAexcel\u6570\u636E\u65F6\u53D1\u751F\u5F02\u5E38\uFF01",
					invocationtargetexception);
		} catch (java.lang.IllegalAccessException illegalaccessexception) {
			illegalaccessexception.printStackTrace();
			throw new Exception(
					"\u5BFC\u51FAexcel\u6570\u636E\u65F6\u53D1\u751F\u5F02\u5E38\uFF01",
					illegalaccessexception);
		}
		org.apache.struts.action.ActionForward actionforward = new ActionForward(
				"/downloadServlet?type=excel&name=data");
		actionforward.setContextRelative(true);
		return actionforward;
	}

	/*
	 * public static java.lang.String saveImage2System(
	 * javax.servlet.http.HttpServletRequest httpservletrequest,
	 * org.apache.struts.upload.FormFile formfile) throws java.lang.Exception {
	 * java.lang.Boolean boolean1 = (java.lang.Boolean) httpservletrequest
	 * .getAttribute("org.apache.struts.upload.MaxLengthExceeded"); if (boolean1
	 * != null && boolean1.booleanValue()) throw new Exception(
	 * "\u4E0A\u4F20\u6587\u4EF6\u8D85\u8FC7\u7CFB\u7EDF\u6700\u5927\u9650\u5236\uFF01"
	 * ); if (formfile == null) return null; java.lang.String s =
	 * com.lbs.commons.FileNameGenerator.nextFileName(); if
	 * (formfile.getFileSize() == 0) return null; java.lang.String s1 =
	 * httpservletrequest.getSession() .getServletContext().getRealPath(
	 * com.lbs.commons.GlobalNames.TEMP_PHOTO_PATH); java.lang.String s2 = s1 +
	 * "/" + s; java.io.InputStream inputstream = formfile.getInputStream();
	 * java.io.FileOutputStream fileoutputstream = new FileOutputStream(s2); int
	 * i = 0; byte abyte0[] = new byte[8192]; while ((i =
	 * inputstream.read(abyte0, 0, 8192)) != -1) fileoutputstream.write(abyte0,
	 * 0, i); fileoutputstream.close(); inputstream.close(); return s; }
	 * 
	 * public static java.lang.String getPhotoFullURL(java.lang.String s) {
	 * return com.lbs.commons.GlobalNames.WEB_APP +
	 * com.lbs.commons.GlobalNames.TEMP_PHOTO_PATH + s; }
	 * 
	 * public static java.lang.String[] getQueryFields(java.lang.String s) {
	 * java.lang.String as[] = com.lbs.commons.actions.ActionUtil
	 * .getRudeFields(s); if (as != null) { for (int i = 0; i < as.length; i++)
	 * { int k=as[i].indexOf(" as "); if (k !=-1) { as[i] = as[i].substring(k +
	 * 4).trim(); }else{ int j = as[i].indexOf("."); if (-1 != j) { as[i] =
	 * as[i].substring(j + 1).trim(); if (as[i].length() > 6) as[i] =
	 * as[i].substring(0, 6); } else as[i] = as[i].trim();} }
	 * 
	 * } return as; }
	 * 
	 * public static java.lang.String[] getRudeFields(java.lang.String s) { if
	 * (s == null) return null; int i = s.toLowerCase().indexOf("select"); int j
	 * = s.toLowerCase().indexOf("from"); java.lang.String as[] =
	 * (java.lang.String[]) null; if (-1 != i && i < j) as = s.substring(i + 6,
	 * j).split(","); return as; }
	 * 
	 * public static void setOrderState( javax.servlet.http.HttpServletRequest
	 * httpservletrequest, java.lang.String s) { if (s == null) return; s =
	 * com.lbs.commons.StringHelper.dealOrderBy(s); if
	 * (s.indexOf(com.lbs.commons.GlobalNames.ORDER_BY) > -1) { java.lang.String
	 * s1 = s.substring( s.lastIndexOf(com.lbs.commons.GlobalNames.ORDER_BY) +
	 * 8) .trim(); if (s.endsWith("desc")) {
	 * httpservletrequest.setAttribute("orderBy", s1.substring(0, s1.length() -
	 * 4).trim()); httpservletrequest.setAttribute("order", "desc"); } else if
	 * (s.endsWith("asc")) { httpservletrequest.setAttribute("orderBy",
	 * s1.substring(0, s1.length() - 3).trim());
	 * httpservletrequest.setAttribute("order", "asc"); } else {
	 * httpservletrequest.setAttribute("orderBy", s1); } } }
	 * 
	 * public static java.lang.String getKeyByHql(
	 * javax.servlet.http.HttpServletRequest httpservletrequest,
	 * java.lang.String s) { if (s == null) return null; java.lang.String s1 =
	 * null; s = s.trim(); javax.servlet.http.HttpSession httpsession =
	 * httpservletrequest .getSession(); com.lbs.commons.TransiantHashMap
	 * transianthashmap = (com.lbs.commons.TransiantHashMap) httpsession
	 * .getAttribute(com.lbs.commons.GlobalNames.HQL_MAP); if (transianthashmap
	 * == null) { transianthashmap = new TransiantHashMap(); s1 =
	 * java.lang.String.valueOf(java.lang.System.currentTimeMillis());
	 * transianthashmap.put(s1, s);
	 * httpsession.setAttribute(com.lbs.commons.GlobalNames.HQL_MAP,
	 * transianthashmap); } else { java.util.Iterator iterator =
	 * transianthashmap.keySet().iterator(); for (java.util.Iterator iterator1 =
	 * transianthashmap.values() .iterator(); iterator1.hasNext();) {
	 * java.lang.String s2 = (java.lang.String) iterator1.next();
	 * java.lang.String s3 = (java.lang.String) iterator.next(); if
	 * (s.equals(s2)) return s3; }
	 * 
	 * s1 = java.lang.String.valueOf(java.lang.System.currentTimeMillis());
	 * transianthashmap.put(s1, s); } return s1; }
	 * 
	 * public static java.lang.String getHqlByKey(
	 * javax.servlet.http.HttpServletRequest httpservletrequest,
	 * java.lang.String s) { if (s == null) return null; java.lang.String s1 =
	 * null; com.lbs.commons.TransiantHashMap transianthashmap =
	 * (com.lbs.commons.TransiantHashMap) httpservletrequest
	 * .getSession().getAttribute(com.lbs.commons.GlobalNames.HQL_MAP); if
	 * (transianthashmap != null) s1 = (java.lang.String)
	 * transianthashmap.get(s); return s1; }
	 */

	public static int getIndex(org.radf.plat.commons.QueryInfo queryinfo,
			java.lang.String s) {
		if (s == null || "".equalsIgnoreCase(s.trim())) {
			return -1;
		} else {
			java.lang.String as[] = queryinfo.getQueryFields();
			return org.radf.plat.commons.ActionUtil.getIndex(as, s);
		}
	}

	public static int getIndex(java.lang.String as[], java.lang.String s) {
		if (as != null) {
			for (int i = 0; i < as.length; i++)
				if (s.equalsIgnoreCase(as[i]))
					return i;

			return -1;
		} else {
			return -1;
		}
	}

	public static void handleProxyRequest(
			javax.servlet.http.HttpServletResponse httpservletresponse,
			java.lang.String s) throws java.lang.Exception {
		java.lang.StringBuffer stringbuffer = new StringBuffer("#");
		// stringbuffer.append(org.radf.plat.commons.StringUtil.ChineseStringToUTF(s));
		stringbuffer.append(s);
		stringbuffer.append("#");
		try {
			httpservletresponse.setContentType("text/xml;charset=utf-8");
			httpservletresponse.getWriter().println(stringbuffer.toString());
		} catch (java.io.IOException ioexception) {
			httpservletresponse.getWriter().print("error!");
		}
	}

	/*
	 * //罗文东 public static java.lang.String getIndexlwd(java.lang.String as[],
	 * java.lang.String s) { String s1=""; int JJ=0; //s=s.replaceAll("+","!");
	 * if (as != null) { for (int i = 0; i < as.length; i++) { if
	 * (s.equalsIgnoreCase(as[i])) return i;
	 * 
	 * if (s.indexOf("!")>=0 || s.indexOf("-")>=0 || s.indexOf("*")>=0 ||
	 * s.indexOf("/")>=0) {
	 * 
	 * JJ=s.indexOf(as[i]);
	 * 
	 * if(JJ>=0)
	 * 
	 * { if (s1.equals("")) {if (JJ==0) s1=i+""; else s1=s.substring(JJ-1,JJ)+i;
	 * 
	 * 
	 * if (s.length()>=JJ+7) s1=s1+s.substring(JJ+6,JJ+7);
	 * 
	 * } else {
	 * 
	 * if (JJ==0) s1=i+s1; else s1=s1+s.substring(JJ-1,JJ)+i; }
	 * 
	 * } }else{ if (s.equalsIgnoreCase(as[i])) return i+""; }
	 * 
	 * } if (s1.equals("")) return "-1"; else return s1; } else { return "-1"; }
	 * } public static String getIndexlwd(com.lbs.apps.query.QueryInfo
	 * queryinfo, java.lang.String s) { if (s == null ||
	 * "".equalsIgnoreCase(s.trim())) { return "-1"; } else { java.lang.String
	 * as[] = queryinfo.getQueryFields(); return
	 * com.lbs.commons.actions.ActionUtil.getIndexlwd(as, s); } } //罗文东 public
	 * static java.util.List getDataFromExcel( org.apache.struts.upload.FormFile
	 * formfile) throws java.lang.Exception { java.util.ArrayList arraylist =
	 * new ArrayList(); java.io.InputStream inputstream =
	 * formfile.getInputStream(); org.apache.poi.hssf.usermodel.HSSFWorkbook
	 * hssfworkbook = new HSSFWorkbook( inputstream); for (int i = 0; i <
	 * hssfworkbook.getNumberOfSheets(); i++) {
	 * org.apache.poi.hssf.usermodel.HSSFSheet hssfsheet = hssfworkbook
	 * .getSheetAt(i); for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows();
	 * j++) if (j != 0) { org.apache.poi.hssf.usermodel.HSSFRow hssfrow =
	 * hssfsheet .getRow(j); short word0 = hssfrow.getLastCellNum();
	 * java.lang.Object aobj[] = new java.lang.Object[word0]; Object obj = null;
	 * for (int k = 0; k < word0; k++) { org.apache.poi.hssf.usermodel.HSSFCell
	 * hssfcell = hssfrow .getCell((short) k); if (hssfcell != null) switch
	 * (hssfcell.getCellType()) { case HSSFCell.CELL_TYPE_NUMERIC: // '\0'
	 * 
	 * POI FAQ says: Excel stores dates as numbers therefore the only way to
	 * determine if a cell is actually stored as a date is to look at the
	 * formatting. There is a helper method in HSSFDateUtil (since the 1.7.0-dev
	 * release) that checks for this.
	 * 
	 * double d = hssfcell.getNumericCellValue(); // test if a date! if
	 * (HSSFDateUtil.isCellDateFormatted(hssfcell)) { // Calendar cal =
	 * Calendar.getInstance(); // cal.setTime(HSSFDateUtil.getJavaDate(d)); //
	 * aobj[k] = String.valueOf(cal.get(Calendar.YEAR)) + "/" // +
	 * String.valueOf(cal.get(Calendar.MONTH) + 1) + "/" // +
	 * String.valueOf(cal.get(Calendar.DAY_OF_MONTH)); aobj[k] = new
	 * java.sql.Date((HSSFDateUtil.getJavaDate(d)).getTime()); // aobj[k] =
	 * (HSSFDateUtil.getJavaDate(d)); } else { int d_int = (int)d; if (d_int ==
	 * d) //Maybe it's only a digital characers, // eg. "2", "34", etc aobj[k] =
	 * (new StringBuffer(String.valueOf(d_int))).toString(); else //Take it as a
	 * digital number aobj[k] = (new
	 * StringBuffer(String.valueOf(d))).toString(); } break;
	 * 
	 * case HSSFCell.CELL_TYPE_STRING: // '\001' aobj[k] =
	 * hssfcell.getStringCellValue(); break;
	 * 
	 * case HSSFCell.CELL_TYPE_FORMULA: // '\002' aobj[k] =
	 * hssfcell.getCellFormula(); break;
	 * 
	 * case HSSFCell.CELL_TYPE_BLANK: // '\003' aobj[k] = null; break;
	 * 
	 * case HSSFCell.CELL_TYPE_BOOLEAN: // '\004' aobj[k] = new Boolean(hssfcell
	 * .getBooleanCellValue()); break;
	 * 
	 * case HSSFCell.CELL_TYPE_ERROR: // '\005' aobj[k] = new
	 * Byte(hssfcell.getErrorCellValue()); break;
	 * 
	 * default: aobj[k] = null; break; } else aobj[k] = null; }
	 * 
	 * arraylist.add(((java.lang.Object) (aobj))); }
	 * 
	 * }
	 * 
	 * return arraylist; }
	 * 
	 * public static java.util.List getDataFromTxt(
	 * org.apache.struts.upload.FormFile formfile) throws java.lang.Exception {
	 * return null; }
	 * 
	 * 
	 * 
	 * public static synchronized boolean isTokenValid(
	 * javax.servlet.http.HttpServletRequest httpservletrequest) {
	 * javax.servlet.http.HttpSession httpsession = httpservletrequest
	 * .getSession(false); if (httpsession == null) return false;
	 * java.lang.String s = (java.lang.String) httpsession
	 * .getAttribute(com.lbs.commons.GlobalNames.TOKEN_KEY); if (s == null) {
	 * return false; } else { java.lang.String s1 =
	 * com.lbs.commons.actions.ActionUtil .a(httpservletrequest); return
	 * s.equals(s1); } }
	 * 
	 * public static synchronized void resetToken(
	 * javax.servlet.http.HttpServletRequest httpservletrequest) {
	 * javax.servlet.http.HttpSession httpsession = httpservletrequest
	 * .getSession(false); if (httpsession == null) { return; } else {
	 * httpsession.removeAttribute(com.lbs.commons.GlobalNames.TOKEN_KEY);
	 * return; } }
	 * 
	 * public static synchronized void saveToken(
	 * javax.servlet.http.HttpServletRequest httpservletrequest) {
	 * javax.servlet.http.HttpSession httpsession = httpservletrequest
	 * .getSession(); java.lang.String s = com.lbs.commons.actions.ActionUtil
	 * .a(httpservletrequest);
	 * httpsession.setAttribute(com.lbs.commons.GlobalNames.TOKEN_KEY, s); }
	 * 
	 * private static java.lang.String a( javax.servlet.http.HttpServletRequest
	 * httpservletrequest) { java.lang.String s =
	 * httpservletrequest.getRequestURL().append("?") .append(
	 * com.lbs.commons.actions.ActionUtil
	 * .getAllRequestPara(httpservletrequest)) .toString(); return
	 * com.lbs.cp.a.b.a(s); }
	 * 
	 * public static java.lang.String getAllRequestPara(
	 * javax.servlet.http.HttpServletRequest httpservletrequest) {
	 * java.util.Enumeration enumeration = httpservletrequest
	 * .getParameterNames(); java.lang.StringBuffer stringbuffer = new
	 * StringBuffer(); while (enumeration.hasMoreElements()) { java.lang.String
	 * s = (java.lang.String) enumeration.nextElement(); java.lang.String as[] =
	 * httpservletrequest.getParameterValues(s); for (int i = 0; i < as.length;
	 * i++) { stringbuffer.append(s); stringbuffer.append("=");
	 * stringbuffer.append(as[i]); if (i != as.length) stringbuffer.append("&");
	 * }
	 * 
	 * } return stringbuffer.toString(); }
	 */
}
