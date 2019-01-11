package org.radf.plat.util.action;

/**
 * <p>标题: Struts Action的扩充类</p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-10-916:44:54</p>
 *
 * @author xbyan
 * @version 1.0
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.PageQueryInfo;
import org.radf.plat.commons.QueryInfo;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.TransiantHashMap;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.taglib.TagConstants;
import org.radf.plat.util.FacadeFactory;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ExceptionUtil;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

import com.lbs.cp.taglib.Formatter;

public abstract class ActionLeafSupport extends BizDispatchAction {

	/** forward标识 */
	protected static final String ADD = "add";

	protected static final String ADD_SAVE = "addsave";

	protected static final String MOD = "mod";

	protected static final String MOD_SAVE = "modsave";

	protected static final String DEL = "del";

	protected static final String LIST = "list";

	protected static final String SUCCESS = "success";

	protected static final String FAILURE = "failure";

	protected static final String ERROR = "error";

	protected String id = "";// 主键

	private static final Logger mm = null;

	private Log log;

	private static LogHelper sysLog;

	public ActionLeafSupport() {
		log = LogFactory
				.getLog(org.radf.plat.util.action.ActionLeafSupport.class);
		sysLog = new LogHelper(
				org.radf.plat.util.action.ActionLeafSupport.class);
	}

	/**
	 * @author xbyan
	 * @param arg2:HttpServletRequest
	 * @return void
	 * @describe 初始化方法
	 */
	public void init(HttpServletRequest arg2) throws Exception {
		// _log.trace("init enter.");
		if (arg2.getParameter("id") != null) {
			id = arg2.getParameter("id");
		}
		Enumeration paramNames = arg2.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
		}
	}

	/**
	 * @author xbyan
	 * @param arg0:ActionMapping
	 * @return ActionForward
	 * @describe:跳转到添加页面
	 */
	public ActionForward add(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// _log.trace("add enter.");
		return arg0.findForward(ActionLeafSupport.ADD);
	}

	/**
	 * 获取application对象,当前用户session对象
	 * 
	 * @param attrName
	 * @return obj
	 */
	public Object getApplicationObject(String attrName) {
		return servlet.getServletContext().getAttribute(attrName);
	}

	public Object getSessionObject(HttpServletRequest req, String attrName) {
		Object sessionObj = null;
		HttpSession session = req.getSession();
		sessionObj = session.getAttribute(attrName);
		return sessionObj;
	}

	/**
	 * 判断用户是否登录
	 */
	public boolean isLoggedIn(HttpServletRequest request) {
		Object obj = getSessionObject(request, "user");
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 数据校验区
	 */
	protected void checkLength(ActionMapping mapping,
			HttpServletRequest request, String key, String msg, String value,
			int length) {
		if ((value == null) || (value.length() < length)) {
			errorForward(mapping, request, key, msg);
		}
	}

	protected void checkNotEmpty(ActionMapping mapping,
			HttpServletRequest request, String key, String msg, String value) {
		if ((value == null) || (value.length() == 0)) {
			errorForward(mapping, request, key, msg);
		}
	}

	/**
	 * 所有继承类必须重写的方法
	 */

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("query enter.");
		return mapping.findForward(ActionLeafSupport.MOD);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("list enter.");
		return mapping.findForward(ActionLeafSupport.LIST);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("update enter.");
		return mapping.findForward(ActionLeafSupport.LIST);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("delete enter.");
		return mapping.findForward(ActionLeafSupport.LIST);
	}

	/**
	 * 错误跳转控制
	 */
	public ActionForward errorForward(ActionMapping mapping,
			HttpServletRequest request, String key, String msg) {
		request.setAttribute("key", key);
		request.setAttribute("msg", msg);
		return mapping.findForward(ActionLeafSupport.ERROR);
	}

	/**
	 * 一些常用功能实现
	 */
	private ActionForward checkAccess(ActionMapping mapping,
			HttpServletRequest request) {
		// _log.trace("checkAccess enter.");
		return null;
	}

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("back enter.");
		return null;
	}

	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// _log.trace("download enter.");
		return null;
	}

	/**
	 * 获取Facade接口服务
	 * 
	 * @see
	 * @param facade
	 * @return
	 * @throws AppException
	 */
	protected Object getService(String facade) throws AppException {
		return FacadeFactory.getService(facade);
	}

	/**
	 * <p>
	 * 根据IMP层返回的结果集ResponseEnvelop，生成ACTION和外层所解析的EventResponse。
	 * 如果IMP层出现异常，或者解析出现错误，则生成的错误信息：
	 * </p>
	 * <p>
	 * isSucessFlag：判断是否出错
	 * </p>
	 * <p>
	 * getMsg：如果出错则获得错误信息，错误信息在本层统一封装为“错误码|详细信息|概述信息|”格式
	 * </p>
	 * <p>
	 * 如果IMP没有异常，则IMP返回的结果信息可以通过getBody()获得：
	 * </p>
	 * <p>
	 * 如果IMP返回的resEnv.getBody()本身就是HashMap，则返回的getBody即这个HashMap
	 * </P>
	 * <p>
	 * 如果IMP返回的resEnv.getBody()不是HashMap，则返回的对象通过getBody().get("out")获得
	 * </p>
	 * 
	 * @param resEnv
	 * @return
	 */
	protected EventResponse processRevt(ResponseEnvelop resEnv) {
		// 返回结果判断，并生成返回参数
		EventResponse retValue = new EventResponse();
		if (resEnv == null) {
			// 成功
			retValue.setSucessFlag(true);
			// 返回的retValue.setBody
			retValue.setBody(null);
		} else if (resEnv.getHead() != null) {
			if (resEnv.getHead().getCode() != 0) {
				// 失败
				retValue.setSucessFlag(false);
				retValue.setMsg(resEnv.getHead().getMessage());
				// 保存错误日志
				ExceptionUtil.saveLog((RequestEnvelopHead) null,
						"ActionLeafSupport", resEnv.getHead().getCode(), resEnv
								.getHead().getMessage());
			} else {
				// 成功
				retValue.setSucessFlag(true);
				retValue.setMsg(resEnv.getHead().getMessage());
				try {
					retValue.setBody(buildBody(resEnv.getBody()));
				} catch (Exception e) {
					retValue.setSucessFlag(false);
					retValue.setMsg(ExceptionUtil.buildMsg(0, e.getMessage(),
							GlobalErrorMsg.SYS_DATE_NOTFOUNT));
				}
			}
		} else { // resEnv.getHead=null
			retValue.setSucessFlag(true);
			try {
				retValue.setBody(buildBody(resEnv.getBody()));
			} catch (Exception e) {
				retValue.setSucessFlag(false);
				retValue.setMsg(ExceptionUtil.buildMsg(0, e.getMessage(),
						GlobalErrorMsg.SYS_DATE_NOTFOUNT));
			}
		}
		return retValue;
	}

	private HashMap buildBody(Object body) {
		if (body instanceof HashMap) {
			return (HashMap) body;
		} else {
			HashMap map = new HashMap();
			map.put("out", body);
			return map;
		}
	}

	/**
	 * 创建者 syg
	 */
	private static class a {

		private ActionForward actforward;

		private boolean _fldfor;

		private ServletContext a;

		private HttpServletRequest request;

		private HttpServletResponse response;

		private Collection coll;

		private PageQueryInfo pagequery;

		public void _mthif() throws Exception {
			String s = pagequery.getPageQueryAction();
			if ("expExcel".equals(s) || "expExcelAll".equals(s)) {
				actforward = a();
				_fldfor = true;
			}
		}

		private ActionForward a() throws UnsupportedEncodingException,
				IOException, Exception {
			javax.servlet.ServletOutputStream servletoutputstream = response
					.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			a(request, coll, ((OutputStream) (servletoutputstream)));
			servletoutputstream.flush();
			servletoutputstream.close();
			return null;
		}

		private void a(HttpServletRequest httpservletrequest,
				Collection collection, OutputStream outputstream)
				throws Exception {
			String s = httpservletrequest.getParameter("headerMeta");
			if (s == null || "".equals(s.trim()))
				return;
			String as[] = s.split("[ ,]");
			int i = as.length / 3;
			String as1[] = new String[i];
			String as2[] = new String[i];
			int ai[] = new int[i];
			HSSFWorkbook hssfworkbook = new HSSFWorkbook();
			HSSFSheet hssfsheet = null;
			// 分多个sheet导出去掉的
			/*
			 * HSSFSheet hssfsheet = hssfworkbook.createSheet("sheet1"); HSSFRow
			 * hssfrow0 = hssfsheet.createRow(0); HSSFCell hssfcell0 =
			 * hssfrow0.createCell((short) 5); hssfcell0.setEncoding((short) 1);
			 * String tableheader = (String) httpservletrequest.getSession()
			 * .getAttribute("tableheader"); hssfcell0.setCellValue("XXX表"); if
			 * (tableheader != null) hssfcell0.setCellValue(tableheader);
			 * hssfsheet.addMergedRegion(new Region(0, (short) 5, 0, (short)
			 * 15)); HSSFRow hssfrow = hssfsheet.createRow(1); for (int k = 0; k <
			 * i; k++) { int j = k * 3; as2[k] = as[j]; ai[k] =
			 * Integer.parseInt(as[j + 2]); as1[k] = as[j + 1]; HSSFCell
			 * hssfcell = hssfrow.createCell((short) k);
			 * hssfcell.setEncoding((short) 1); hssfcell.setCellValue(as1[k]); }
			 */
			// 分多个sheet导出去掉的
			if (collection != null) {
				Object obj = collection.iterator().next();
				boolean flag = true;
				if (obj instanceof Object[])
					flag = false;
				String s1 = "获取数据错误！";
				ServletContext servletcontext = a;
				int l = 2;
				int p = 0;// 分多个sheet导出
				long ii = Long.parseLong("0");// //分多个sheet导出
				List header = (List) request.getSession()
						.getAttribute("header");
				for (Iterator iterator = collection.iterator(); iterator
						.hasNext();) {

					// 分多个sheet导出
					if (ii % Long.parseLong("65534") == 0) {
						p = p + 1;
						l = 2;
						hssfsheet = hssfworkbook.createSheet("sheet" + p);
						HSSFRow hssfrow0 = hssfsheet.createRow(0);
						HSSFCell hssfcell0 = hssfrow0.createCell((short) 5);
						//hssfcell0.setEncoding((short) 1);
						String tableheader = (String) httpservletrequest
								.getSession().getAttribute("tableheader");
						hssfcell0.setCellValue("XXX表");
						if (tableheader != null)
							hssfcell0.setCellValue(tableheader);
						hssfsheet.addMergedRegion(new Region(0, (short) 5, 0,
								(short) 15));
						HSSFRow hssfrow = hssfsheet.createRow(1);
						for (int k = 0; k < i; k++) {
							int j = k * 3;
							as2[k] = as[j];
							ai[k] = Integer.parseInt(as[j + 2]);
							as1[k] = as[j + 1];
							HSSFCell hssfcell = hssfrow.createCell((short) k);
							//hssfcell.setEncoding((short) 1);
							hssfcell.setCellValue(as1[k]);
						}
					}
					ii = ii + Long.parseLong("1");
					// 分多个sheet导出

					Object obj2 = iterator.next();
					HSSFRow hssfrow1 = hssfsheet.createRow(l++);
					// lwd20070930为了导出格式问题

					int i1 = 0;
					for (Iterator iterator1 = header.iterator(); iterator1
							.hasNext();) {
						Formatter formatter = (Formatter) iterator1.next();
						String s0 = formatter.getCode();
						if (s0 != null && !s0.equals("")) {
							int index = s0.indexOf(".");
							if (index >= 0) {
								s0 = s0.substring(index + 1);
							}

						}
						String s01 = formatter.getType();
						String s02 = formatter.getFormat();
						String s03 = formatter.getLabel();

						Object obj1 = null;
						// if (flag)

						try {
							obj1 = PropertyUtils.getSimpleProperty(obj2, s0);
						} catch (IllegalAccessException illegalaccessexception) {

						} catch (InvocationTargetException invocationtargetexception) {

						} catch (NoSuchMethodException nosuchmethodexception) {

						} catch (Exception exception) {

						}

						// else
						// obj1 = ((Object[]) obj2)[ai[i1]];
						if (obj1 == null)
							obj1 = "";
						if (s01 != null) {
							if (TagConstants.DT_MONEY.equalsIgnoreCase(s01))
								obj1 = (new org.radf.plat.commons.Money(obj1
										.toString())).toString();
							if (TagConstants.DT_YEAR_MONTH
									.equalsIgnoreCase(s01))
								obj1 = DateUtil
										.getStepMonth(obj1.toString(), 0);
							if (TagConstants.DT_MONTH.equalsIgnoreCase(s01))
								obj1 = DateUtil
										.month2YearMonth(obj1.toString());
							// 用与分页中显示年月日格式的日期 by cb
							if (TagConstants.DT_YEAR_MONTH_DATE
									.equalsIgnoreCase(s01)) {
								obj1 = DateUtil.converToString(obj1.toString(),
										"yyyy-MM-dd");
							}
							//							
						}

						TreeMap treemap = (TreeMap) servletcontext
								.getAttribute(s0.toUpperCase());
						if (obj1 == null)
							obj1 = " ";
						if (treemap != null)
							obj1 = treemap.get(obj1);
						if (obj1 == null)
							obj1 = " ";

						HSSFCell hssfcell1 = hssfrow1.createCell((short) i1);
						//hssfcell1.setEncoding((short) 1);

						// 为了导出显示数字bylwd20071204
						if (TagConstants.H_NUMBER.equalsIgnoreCase(s01)
								&& obj1 != null && !"".equals(obj1)) {
							hssfcell1.setCellValue(obj1 != null
									&& !"".equals(obj1) ? Double
									.parseDouble(obj1.toString()) : Double
									.parseDouble("0"));
						} else {
							hssfcell1.setCellValue(obj1 != null ? obj1
									.toString() : "");
						}
						//						

						i1 = i1 + 1;
					}

					// lwd end
					/*
					 * for (int i1 = 0; i1 < i; i1++) { Object obj1 = null; if
					 * (flag) try { obj1 = PropertyUtils.getSimpleProperty(obj2,
					 * as2[i1]); } catch (Exception exception) { Exception
					 * exception1 = new Exception(s1, exception);
					 * mm.error(exception1); throw exception1; } else obj1 =
					 * ((Object[]) obj2)[ai[i1]]; TreeMap treemap = (TreeMap)
					 * servletcontext .getAttribute(as2[i1].toUpperCase()); if
					 * (obj1 == null) obj1 = " "; if (treemap != null) obj1 =
					 * treemap.get(obj1); if (obj1 == null) obj1 = " "; HSSFCell
					 * hssfcell1 = hssfrow1.createCell((short) i1);
					 * hssfcell1.setEncoding((short) 1);
					 * hssfcell1.setCellValue(obj1 != null ? obj1.toString() :
					 * ""); }
					 */

				}

			}
			hssfworkbook.write(outputstream);
		}

		private byte[] _mthint() throws Exception {
			String s = request.getParameter("headerMeta");
			if (s == null || "".equals(s.trim()))
				return new byte[0];
			String as[] = s.split("[ ,]");
			int i = as.length / 3;
			String as1[] = new String[i];
			String as2[] = new String[i];
			int ai[] = new int[i];
			StringBuffer stringbuffer = new StringBuffer();
			boolean flag = false;
			for (int k = 0; k < i; k++) {
				int j = k * 3;
				as2[k] = as[j];
				ai[k] = Integer.parseInt(as[j + 2]);
				as1[k] = as[j + 1];
				if (flag)
					stringbuffer.append("'");
				stringbuffer.append(as1[k]).append("\t");
			}

			stringbuffer.append("\n");
			if (coll != null) {
				Object obj = coll.iterator().next();
				boolean flag1 = true;
				if (obj instanceof Object[])
					flag1 = false;
				String s1 = "获取数据错误！";
				ServletContext servletcontext = a;
				for (Iterator iterator = coll.iterator(); iterator.hasNext(); stringbuffer
						.append("\n")) {
					Object obj2 = iterator.next();
					for (int l = 0; l < i; l++) {
						Object obj1 = null;
						if (flag1)
							try {
								obj1 = PropertyUtils.getSimpleProperty(obj2,
										as2[l]);
							} catch (Exception exception) {
								Exception exception1 = new Exception(s1,
										exception);
								mm.error(exception1);
								throw exception1;
							}
						else
							obj1 = ((Object[]) obj2)[ai[l]];
						TreeMap treemap = (TreeMap) servletcontext
								.getAttribute(as2[l].toUpperCase());
						if (obj1 == null)
							obj1 = "";
						if (treemap != null)
							obj1 = treemap.get(obj1);
						if (flag)
							stringbuffer.append("'");
						if (obj1 == null)
							obj1 = "";
						stringbuffer.append(a(obj1));
						stringbuffer.append("\t");
					}

				}

			}
			try {
				return stringbuffer.toString().getBytes("GBK");
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
				unsupportedencodingexception.printStackTrace();
				throw new Exception("不支持的字符集", unsupportedencodingexception);
			}
		}

		private Object a(Object obj) {
			obj = obj != null ? obj : " ";
			if (obj.getClass().getName().equals("java.sql.Timestamp")) {
				Timestamp timestamp = (Timestamp) obj;
				obj = new Date(timestamp.getTime());
			}
			return obj;
		}

		public ActionForward _mthfor() {
			return actforward;
		}

		public void a(ActionForward actionforward) {
			actforward = actionforward;
		}

		public boolean _mthdo() {
			return _fldfor;
		}

		public void a(boolean flag) {
			_fldfor = flag;
		}

		public a(ServletContext servletcontext,
				HttpServletRequest httpservletrequest,
				HttpServletResponse httpservletresponse, Collection collection,
				PageQueryInfo pagequeryinfo) {
			a = servletcontext;
			request = httpservletrequest;
			response = httpservletresponse;
			coll = collection;
			pagequery = pagequeryinfo;
		}
	}

	/**
	 * 分页查询错误处理方法
	 * 
	 * @param exception
	 * @param actionmapping
	 * @param actionform
	 * @param pagequeryinfo
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	protected ActionForward handleException(Exception exception,
			ActionMapping actionmapping, ActionForm actionform,
			PageQueryInfo pagequeryinfo, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		super.saveErrors(httpservletrequest, exception);
		return processResult(actionmapping, actionform, pagequeryinfo,
				httpservletrequest, httpservletresponse);
	}

	/**
	 * 公共查询方法
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public final ActionForward commonQuery(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return pageQuery(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	/**
	 * 分页查询
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public final ActionForward pageQuery(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String query_info = httpservletrequest.getParameter(GlobalNames.QUERY_INFO);
		PageQueryInfo pagequeryinfo = null;
		// 获取页面上显示的行数
		String Pagenum = httpservletrequest.getParameter("Pagenum");
		// 如果获取页数是空或者null获取是中文字符的话 设置页数为空
		// 判断Pagenum中是否全部为数字 不是的话设置页数为空
		if (!StringUtils.isNumeric(Pagenum))
			Pagenum = "";
		if (StringUtils.isNotBlank(query_info)) {
			pagequeryinfo = PageQueryInfo.deserialFromText(query_info);
		} else {
			String pageSize = httpservletrequest.getParameter("pageSize");
			pagequeryinfo = initPageQueryInfo(httpservletrequest);
			if (Pagenum != null && !Pagenum.equals("")) {
				pageSize = Pagenum;
			}

			if (StringUtils.isNotBlank(pageSize))
				pagequeryinfo.setPageSize(Integer.parseInt(pageSize));
			pageSize = httpservletrequest.getParameter("curPageNo");
			if (StringUtils.isNotBlank(pageSize))
				pagequeryinfo.setCurPageNo(Integer.parseInt(pageSize));
		}
		// 获取初始的行数
		int pagesizeold = pagequeryinfo.getPageSize();
		if (StringUtils.isNotBlank(Pagenum)) {
			try {
				Pagenum(Integer.parseInt(Pagenum), pagequeryinfo);
			} catch (NumberFormatException numberformatexception) {
				throw new AppException("输入的行数不能解析为数字!");
			}
		}
		String s2 = httpservletrequest
				.getParameter(GlobalNames.PAGE_QUERY_ACTION);
		pagequeryinfo.setPageQueryAction(s2);
		try {
			int i = pagequeryinfo.getRowCount();
			int j = pagequeryinfo.getPageCount();
			int l = pagequeryinfo.getPageSize();
			String s3 = httpservletrequest.getParameter("reCount");
			boolean flag = "true".equals(s3);
			if (i < 0 || flag) {
				i = initRowCount(httpservletrequest, actionform, pagequeryinfo);
				pagequeryinfo.setRowCount(i);
			}
			if ((j < 0 || flag) && l > 0) {
				int i1 = i % l;
				int k = i / l;
				if (i1 > 0)
					k++;
				pagequeryinfo.setPageCount(k);
			}
			if (pagesizeold != pagequeryinfo.getPageSize()) {
				int i1 = i % l;
				int k = i / l;
				if (i1 > 0)
					k++;
				pagequeryinfo.setPageCount(k);
			}
			// 获取页数
			String s4 = httpservletrequest.getParameter("toPage");
			// 如果获取页数是空或者null获取是中文字符的话 设置页数为空
			// 判断s4中是否全部为数字 不是的话设置页数为空
			if (!StringUtils.isNumeric(s4))
				s4 = "";
			if (StringUtils.isNotBlank(s4)) {
				try {
					a(Integer.parseInt(s4) - 1, pagequeryinfo,
							httpservletrequest);
				} catch (NumberFormatException numberformatexception) {
					throw new AppException("输入的页码不能解析为数字!");
				}
			} else {
				// if (" ".equals(s4))
				// return go2QueryPage(httpservletrequest);//处理跳转到第几页已经处理了toPage
				// 等于空就无意义了
				if ("first".equals(s2))
					a(0, pagequeryinfo, httpservletrequest);
				else if ("next".equals(s2))
					a(pagequeryinfo.getCurPageNo() + 1, pagequeryinfo,
							httpservletrequest);
				else if ("previous".equals(s2))
					a(pagequeryinfo.getCurPageNo() - 1, pagequeryinfo,
							httpservletrequest);
				else if ("last".equals(s2))
					a(pagequeryinfo.getPageCount() - 1, pagequeryinfo,
							httpservletrequest);
			}
			Collection collection = fetchPageData(httpservletrequest,
					actionform, pagequeryinfo);
			httpservletrequest.setAttribute("ctxPath", httpservletrequest
					.getContextPath());
			httpservletrequest.setAttribute("servletPath", httpservletrequest
					.getServletPath());
			httpservletrequest.setAttribute(GlobalNames.QUERY_INFO,
					pagequeryinfo);
			httpservletrequest.setAttribute(GlobalNames.QUERY_DATA, collection);
			a a1 = new a(servlet.getServletContext(), httpservletrequest,
					httpservletresponse, collection, pagequeryinfo);
			a1._mthif();
			if (a1._mthdo())
				return a1._mthfor();
			else
				return processResult(actionmapping, actionform, pagequeryinfo,
						httpservletrequest, httpservletresponse);
		} catch (Exception exception) {
			mm.error("分页查询异常：" + exception.getMessage(), exception);
			if (mm.isDebugEnabled())
				exception.printStackTrace();
			return handleException(exception, actionmapping, actionform,
					pagequeryinfo, httpservletrequest, httpservletresponse);
		}
	}

	/**
	 * 根据传入的页数判断 如果小于0显示0页 否则返回当前页数
	 * 
	 * @param i
	 * @param pagequeryinfo
	 */
	private void a(int i, PageQueryInfo pagequeryinfo,
			HttpServletRequest httpservletrequest) {
		if (i < 0)
			i = 0;
		if (i >= pagequeryinfo.getPageCount())
			i = pagequeryinfo.getPageCount() - 1;
		pagequeryinfo.setCurPageNo(i);
		// 为了返回当前页 lwd
		String back = (String) httpservletrequest.getSession().getAttribute(
				"queryInfopageno");
		QueryInfo queryinfo = (QueryInfo) httpservletrequest.getSession()
				.getAttribute("queryInfo" + back);
		if (queryinfo != null) {
			queryinfo.setCurPageNo(i);
			httpservletrequest.getSession().setAttribute("queryInfo" + back,
					queryinfo);// lwd
		}
		// 为了返回当前页
	}

	/**
	 * 根据传入行数判断 如果小于等于0的显示10行 否则返回当前行数
	 * 
	 * @param i
	 * @param pagequeryinfo
	 */
	private void Pagenum(int i, PageQueryInfo pagequeryinfo) {
		if (i <= 0)
			i = 10;
		pagequeryinfo.setPageSize(i);
	}

	/**
	 * 分页查询公共方法
	 * 
	 * @param httpservletrequest
	 * @param s
	 *            跳转页面
	 * @param s1
	 *            sql语句
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(HttpServletRequest httpservletrequest, String forward,
			String hql) throws Exception {
		return a(httpservletrequest, forward, hql, false, GlobalNames.PAGE_SIZE);
	}

	public ActionForward init(HttpServletRequest httpservletrequest, String s,
			String s1, String back) throws Exception {
		return a(httpservletrequest, s, s1, false, GlobalNames.PAGE_SIZE, back);
	}

	// 罗文东lwd
	/*
	 * public org.apache.struts.action.ActionForward go2Page( HttpServletRequest
	 * httpservletrequest) throws java.lang.Exception { QueryInfo queryinfo =
	 * new QueryInfo(); queryinfo = (QueryInfo)
	 * httpservletrequest.getSession().getAttribute( "queryInfo1"); String s1 =
	 * queryinfo.getHqlKey(); setOrderState(httpservletrequest, s1);
	 * httpservletrequest.setAttribute("queryInfo", queryinfo); return
	 * commonQuery(null, null, httpservletrequest, null); } public
	 * org.apache.struts.action.ActionForward go2Page( HttpServletRequest
	 * httpservletrequest, String back) throws java.lang.Exception { QueryInfo
	 * queryinfo = new QueryInfo(); if (back == null || "".equals(back)) { back =
	 * "1"; } queryinfo = (QueryInfo)
	 * httpservletrequest.getSession().getAttribute( "queryInfo" + back); String
	 * s1 = queryinfo.getHqlKey(); setOrderState(httpservletrequest, s1);
	 * httpservletrequest.setAttribute("queryInfo", queryinfo); return
	 * commonQuery(null, null, httpservletrequest, null); }
	 */

	public org.apache.struts.action.ActionForward go2Page(
			HttpServletRequest httpservletrequest, ActionMapping mapping,
			String mod, String back) throws java.lang.Exception {
		QueryInfo queryinfo = new QueryInfo();
		if (back == null || "".equals(back)) {
			back = "1";
		}

		httpservletrequest.setAttribute("go2pagemod", mod);
		httpservletrequest.setAttribute("go2pagenum", back);
		return mapping.findForward("go2Page");

	}

	public org.apache.struts.action.ActionForward go2Page(
			HttpServletRequest httpservletrequest, ActionMapping mapping,
			String mod) throws java.lang.Exception {
		QueryInfo queryinfo = new QueryInfo();

		httpservletrequest.setAttribute("go2pagemod", mod);
		httpservletrequest.setAttribute("go2pagenum", "1");
		return mapping.findForward("go2Page");

	}

	// 罗文东lwd
	public org.apache.struts.action.ActionForward go2Page(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest httpservletrequest, HttpServletResponse res)
			throws java.lang.Exception {
		super.saveSuccessfulMsg(httpservletrequest, null);
		String back = (String) httpservletrequest.getParameter("back");
		QueryInfo queryinfo = new QueryInfo();
		if (back == null || "".equals(back)) {
			back = "1";
		}
		queryinfo = (QueryInfo) httpservletrequest.getSession().getAttribute(
				"queryInfo" + back);
		if (queryinfo == null)
			return mapping.findForward("backspace");
		String s1 = queryinfo.getHqlKey();
		setOrderState(httpservletrequest, s1);
		queryinfo.setRowCount(-1);
		queryinfo.setPageCount(-1);
		httpservletrequest.setAttribute("queryInfo", queryinfo);
		return commonQuery(null, null, httpservletrequest, null);

	}

	/**
	 * 分页查询公共方法 可以定义修改默认行数
	 * 
	 * @param httpservletrequest
	 * @param s
	 *            跳转页面
	 * @param s1
	 *            sql语句
	 * @param i
	 *            获取行数
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(HttpServletRequest httpservletrequest, String s,
			String s1, int i) throws Exception {
		return a(httpservletrequest, s, s1, false, i);
	}

	public ActionForward init(HttpServletRequest httpservletrequest, String s,
			String s1, int i, String back) throws Exception {
		return a(httpservletrequest, s, s1, false, i, back);
	}

	/**
	 * 分页查询公共方法 可以定义修改默认行数
	 * 
	 * @param httpservletrequest
	 * @param s
	 * @param s1
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(HttpServletRequest httpservletrequest, String s,
			String s1, boolean flag) throws Exception {
		return a(httpservletrequest, s, s1, flag, 0);
	}

	public ActionForward init(HttpServletRequest httpservletrequest, String s,
			String s1, boolean flag, String back) throws Exception {
		return a(httpservletrequest, s, s1, flag, 0, back);
	}

	/**
	 * 设置当前查询参数
	 * 
	 * @param httpservletrequest
	 * @param s
	 * @param s1
	 * @param flag
	 * @param i
	 * @return
	 * @throws Exception
	 */
	private ActionForward a(HttpServletRequest httpservletrequest, String forward,
			String hql, boolean flag, int i) throws Exception {
		QueryInfo queryinfo = new QueryInfo();
		queryinfo.setForward(forward);
		queryinfo.setRetriveAllDataFlag(flag);
		queryinfo.setPageSize(i);
		queryinfo.setHqlKey(getKeyByHql(httpservletrequest, hql));
		queryinfo.setForward(forward);
		// queryinfo.setRudeFields(ActionUtil.getRudeFields(s1));
		// queryinfo.setQueryFields(ActionUtil.getQueryFields(s1));
		httpservletrequest.setAttribute("queryInfo", queryinfo);
		/*
		 * if (httpservletrequest.getSession().getAttribute("queryInfo")!=null) {
		 * QueryInfo queryinfo1 = new QueryInfo();
		 * queryinfo1=(QueryInfo)httpservletrequest.getSession().getAttribute("queryInfo");
		 * String s11 = getHqlByKey(httpservletrequest, queryinfo1.getHqlKey());
		 * String sf = queryinfo1.getForward(); if(!s1.equals(s11) ||
		 * !s.equals(sf)) {
		 * httpservletrequest.getSession().setAttribute("queryInfo4",
		 * httpservletrequest.getSession().getAttribute("queryInfo3"));//lwd
		 * httpservletrequest.getSession().setAttribute("queryInfo3",
		 * httpservletrequest.getSession().getAttribute("queryInfo"));//lwd } }
		 */

		httpservletrequest.getSession().setAttribute("queryInfo1", queryinfo);// lwd
		httpservletrequest.getSession().setAttribute("queryInfopageno", "1");// lwd为了go2page返回当前页
		setOrderState(httpservletrequest, hql);
		return commonQuery(null, null, httpservletrequest, null);
	}

	private ActionForward a(HttpServletRequest httpservletrequest, String s,
			String s1, boolean flag, int i, String back) throws Exception {
		QueryInfo queryinfo = new QueryInfo();
		queryinfo.setForward(s);
		queryinfo.setRetriveAllDataFlag(flag);
		queryinfo.setPageSize(i);
		queryinfo.setHqlKey(getKeyByHql(httpservletrequest, s1));
		queryinfo.setForward(s);
		// queryinfo.setRudeFields(ActionUtil.getRudeFields(s1));
		// queryinfo.setQueryFields(ActionUtil.getQueryFields(s1));
		httpservletrequest.setAttribute("queryInfo", queryinfo);
		/*
		 * if (httpservletrequest.getSession().getAttribute("queryInfo")!=null) {
		 * QueryInfo queryinfo1 = new QueryInfo();
		 * queryinfo1=(QueryInfo)httpservletrequest.getSession().getAttribute("queryInfo");
		 * String s11 = getHqlByKey(httpservletrequest, queryinfo1.getHqlKey());
		 * String sf = queryinfo1.getForward(); if(!s1.equals(s11) ||
		 * !s.equals(sf)) {
		 * httpservletrequest.getSession().setAttribute("queryInfo4",
		 * httpservletrequest.getSession().getAttribute("queryInfo3"));//lwd
		 * httpservletrequest.getSession().setAttribute("queryInfo3",
		 * httpservletrequest.getSession().getAttribute("queryInfo"));//lwd } }
		 */
		if (back == null || "".equals(back)) {
			back = "1";
		}
		httpservletrequest.getSession().setAttribute("queryInfo" + back,
				queryinfo);// lwd
		httpservletrequest.getSession().setAttribute("queryInfopageno", back);// lwd为了go2page返回当前页
		setOrderState(httpservletrequest, s1);
		return commonQuery(null, null, httpservletrequest, null);
	}

	/**
	 * 导出当前数据数据到excl
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward expExcel(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		RequestEnvelop requestEnvelop = null;
		EventResponse returnValue = null;
		HashMap mapRequest = null;
		String s = httpservletrequest.getParameter("hqlKey");
		String s1 = getHqlByKey(httpservletrequest, s);
		int i = Integer.parseInt(httpservletrequest.getParameter("curPageNo"));
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		Collection collection = null;
		try {
			mapRequest.put("beo", s1);
			mapRequest.put("offset", String.valueOf(i));
			mapRequest.put("count", String.valueOf(GlobalNames.PAGE_SIZE));
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				collection = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");

			}

			// collection = facade.commonQuery(s1, i, GlobalNames.PAGE_SIZE);
		} catch (Exception exception) {
			saveErrors(httpservletrequest, exception);
		}
		return a(httpservletrequest, httpservletresponse, collection);
	}

	/**
	 * 导出全部数据到excl
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward expExcelAllData(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		RequestEnvelop requestEnvelop = null;
		EventResponse returnValue = null;
		HashMap mapRequest = null;
		String s = httpservletrequest.getParameter("hqlKey");
		String s1 = getHqlByKey(httpservletrequest, s);
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		Collection collection = null;
		try {
			mapRequest.put("beo", s1);
			mapRequest.put("offset", String.valueOf(-1));
			mapRequest.put("count", String.valueOf(GlobalNames.PAGE_SIZE));
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				collection = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");

			}
		} catch (Exception exception) {
			saveErrors(httpservletrequest, exception);
		}
		return a(httpservletrequest, httpservletresponse, collection);
	}

	/**
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param collection
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	private ActionForward a(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Collection collection)
			throws UnsupportedEncodingException, IOException, Exception {
		httpservletrequest.setCharacterEncoding("GBK");
		javax.servlet.ServletOutputStream servletoutputstream = httpservletresponse
				.getOutputStream();
		httpservletresponse.reset();
		httpservletresponse.setContentType("application/vnd.ms-excel");
		byte abyte0[] = a(httpservletrequest, collection);
		httpservletresponse.setContentLength(abyte0.length);
		servletoutputstream.write(abyte0);
		servletoutputstream.flush();
		servletoutputstream.close();
		return null;
	}

	/**
	 * @param httpservletrequest
	 * @param collection
	 * @return
	 * @throws Exception
	 */
	private byte[] a(HttpServletRequest httpservletrequest,
			Collection collection) throws Exception {
		String s = httpservletrequest.getParameter("headerMeta");
		if (s == null || "".equals(s.trim()))
			return new byte[0];
		String as[] = s.split("[ ,]");
		int i = as.length / 3;
		String as1[] = new String[i];
		String as2[] = new String[i];
		int ai[] = new int[i];
		StringBuffer stringbuffer = new StringBuffer();
		boolean flag = false;
		for (int k = 0; k < i; k++) {
			int j = k * 3;
			as2[k] = as[j];
			ai[k] = Integer.parseInt(as[j + 2]);
			as1[k] = as[j + 1];
			if (flag)
				stringbuffer.append("'");
			stringbuffer.append(as1[k]).append("\t");
		}

		stringbuffer.append("\n");
		if (collection != null) {
			Object obj = collection.iterator().next();
			boolean flag1 = true;
			if (obj instanceof Object[])
				flag1 = false;
			String s1 = "获取数据错误！";
			ServletContext servletcontext = servlet.getServletContext();
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append("\n")) {
				Object obj2 = iterator.next();
				for (int l = 0; l < i; l++) {
					Object obj1 = null;
					if (flag1)
						try {
							obj1 = PropertyUtils
									.getSimpleProperty(obj2, as2[l]);
						} catch (Exception exception) {
							Exception exception1 = new Exception(s1, exception);
							log.error(exception1);
							throw exception1;
						}
					else
						obj1 = ((Object[]) obj2)[ai[l]];
					TreeMap treemap = (TreeMap) servletcontext
							.getAttribute(as2[l].toUpperCase());
					if (obj1 == null)
						obj1 = "";
					if (treemap != null)
						obj1 = treemap.get(obj1);
					if (flag)
						stringbuffer.append("'");
					if (obj1 == null)
						obj1 = "";
					stringbuffer.append(a(obj1));
					stringbuffer.append("\t");
				}

			}

		}
		try {
			return stringbuffer.toString().getBytes("GBK");
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			unsupportedencodingexception.printStackTrace();
			throw new Exception("不支持的字符集", unsupportedencodingexception);
		}
	}

	/**
	 * 日期字符出路将当前对象转化有 时 分 秒的
	 * 
	 * @param obj
	 * @return
	 */
	private Object a(Object obj) {
		obj = obj != null ? obj : " ";
		if (obj.getClass().getName().equals("java.sql.Timestamp")) {
			Timestamp timestamp = (Timestamp) obj;
			obj = new Date(timestamp.getTime());
		}
		return obj;
	}

	/**
	 * @param httpservletrequest
	 */
	public void beforeAction(HttpServletRequest httpservletrequest) {
	}

	/**
	 * 获取分页查询总记录数
	 * 
	 * @param httpservletrequest
	 * @param actionform
	 * @param pagequeryinfo
	 * @return
	 * @throws Exception
	 */
	protected int initRowCount(HttpServletRequest httpservletrequest,
			ActionForm actionform, PageQueryInfo pagequeryinfo)
			throws Exception {
		RequestEnvelop requestEnvelop = null;
		EventResponse returnValue = null;
		HashMap mapRequest = null;
		QueryInfo queryinfo = (QueryInfo) pagequeryinfo;
		String s = getHqlByKey(httpservletrequest, queryinfo.getHqlKey());
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		requestEnvelop = new RequestEnvelop();
		returnValue = new EventResponse();
		// 将Application对象放入HashMap
		mapRequest = new HashMap();
		mapRequest.put("beo", s);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.getCount(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		int i = 0;
		if (returnValue.isSucessFlag()) {
			i = Integer.parseInt((String) ((HashMap) resEnv.getBody())
					.get("count"));
		}
		return i;
	}

	/**
	 * 将actionForm中的内容转换成sql语句返回
	 * 
	 * @param actionForm
	 * @return
	 * @throws Exception
	 */
	protected String queryEnterprise(Object obj) throws Exception {
		String sql = DBUtil.getSQLByObject(obj, 0);
		return sql;
	}

	/**
	 * 获取结果
	 * 
	 * @param httpservletrequest
	 * @param actionform
	 * @param pagequeryinfo
	 * @return
	 * @throws Exception
	 */
	protected Collection fetchPageData(HttpServletRequest httpservletrequest,
			ActionForm actionform, PageQueryInfo pagequeryinfo)
			throws Exception {
        Connection con=null;
        con=DBUtil.getConnection();
		RequestEnvelop requestEnvelop = null;
		EventResponse returnValue = null;
		HashMap mapRequest = null;
		FACADESupport facade = (FACADESupport) getService("FACADESupport");
		Collection collection = null;
		QueryInfo queryinfo = (QueryInfo) pagequeryinfo;
		String s = getHqlByKey(httpservletrequest, queryinfo.getHqlKey());
		/*
		 * author zyf
		 * 	List o=(Vector)DBUtil.querySQL(con, s);
		    Object obj=((HashMap)o.get(0));
		    ClassHelper.copyProperties(obj, actionform);
		 */
		
		String s1 = pagequeryinfo.getPageQueryAction();
		if ("order".equals(s1)) {
			String s2 = httpservletrequest.getParameter("orderBy");
			String s3 = httpservletrequest.getParameter("order");
			if (s3 == null)
				s3 = "";
			s = StringUtil.dealOrderBy(s);
			if (s2 != null)
				if (s.indexOf(GlobalNames.ORDER_BY) > -1)
					s = s.substring(0, s.lastIndexOf(GlobalNames.ORDER_BY))
							+ GlobalNames.ORDER_BY
							+ " "
							+ s2
							+ " "
							+ s3
							+ ","
							+ s.substring(s.lastIndexOf(GlobalNames.ORDER_BY)
									+ GlobalNames.ORDER_BY.length() + 1, s
									.length());// 20071109bylwd用于分页排序，让数据排序不乱
				else
					s = s + " " + GlobalNames.ORDER_BY + " " + s2 + " " + s3;// 20071109bylwd用于分页排序，让数据排序不乱
			queryinfo.setHqlKey(getKeyByHql(httpservletrequest, s));
			// 为了返回当前页 lwd
			String back = (String) httpservletrequest.getSession().getAttribute(
					"queryInfopageno");
			QueryInfo queryinfofh = (QueryInfo) httpservletrequest.getSession()
					.getAttribute("queryInfo" + back);
			if (queryinfofh != null) {
				queryinfofh.setHqlKey(getKeyByHql(httpservletrequest, s));
				httpservletrequest.getSession().setAttribute("queryInfo" + back,
						queryinfo);// lwd
			}
			// 为了返回当前页
			httpservletrequest.setAttribute("orderBy", s2);
			httpservletrequest.setAttribute("order", s3);
		}
		if ("expExcelAll".equals(s1) || queryinfo.isRetriveAllDataFlag()) {
			mapRequest = new HashMap();
			mapRequest.put("beo", s);
			mapRequest.put("offset", String.valueOf(-1));
			mapRequest.put("count", String.valueOf(0));
			requestEnvelop = new RequestEnvelop();
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				collection = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");

			}
			// collection = facade.commonQuery(s, -1, 0);
		} else {
			mapRequest = new HashMap();
			mapRequest.put("beo", s);
			mapRequest.put("offset", String.valueOf(queryinfo.getCurPageNo()
					* queryinfo.getPageSize() + 1));
			mapRequest.put("count", String.valueOf(queryinfo.getPageSize()));
			requestEnvelop = new RequestEnvelop();
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				collection = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");

			}
		}
		return collection;
	}

	/**
	 * 处理分页结果页面跳转
	 * 
	 * @param actionmapping
	 * @param actionform
	 * @param pagequeryinfo
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	protected ActionForward processResult(ActionMapping actionmapping,
			ActionForm actionform, PageQueryInfo pagequeryinfo,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		beforeAction(httpservletrequest);
		ActionForward actionforward = null;
		actionforward = new ActionForward(((QueryInfo) pagequeryinfo)
				.getForward());
		actionforward.setContextRelative(true);
		return actionforward;
	}

	/**
	 * 初始化分页查询参数
	 * 
	 * @param httpservletrequest
	 * @return
	 * @throws Exception
	 */
	protected PageQueryInfo initPageQueryInfo(
			HttpServletRequest httpservletrequest) throws Exception {
		QueryInfo queryinfo = (QueryInfo) httpservletrequest
				.getAttribute("queryInfo");
		if (queryinfo == null) {
			queryinfo = new QueryInfo();
			String s = httpservletrequest.getParameter("stringData");
			if (s != null) {
				String as[] = s.split(";");
				queryinfo.setHqlKey(as[0]);
				queryinfo.setForward(as[1]);
				queryinfo.setTotalNum(Integer.parseInt(as[2]));
				queryinfo.setTotalPageNo(Integer.parseInt(as[3]));
				queryinfo.setCurPageNo(Integer.parseInt(as[4]));
				queryinfo.setPageSize(Integer.parseInt(as[5]));
				queryinfo.setOtherInfo(as[6]);
				return queryinfo;
			}
			String s1 = httpservletrequest.getParameter("hqlKey");
			queryinfo.setHqlKey(s1);
			queryinfo.setForward(httpservletrequest.getParameter("forward"));
			String s2 = httpservletrequest.getParameter("totalNum");
			if (StringUtils.isNotBlank(s2))
				queryinfo.setRowCount(Integer.parseInt(s2));
			s2 = httpservletrequest.getParameter("totalPageNo");
			if (StringUtils.isNotBlank(s2))
				queryinfo.setPageCount(Integer.parseInt(s2));
			s2 = httpservletrequest.getParameter("totalPageNo");
			if (StringUtils.isNotBlank(s2))
				queryinfo.setPageCount(Integer.parseInt(s2));
			s2 = httpservletrequest.getParameter("curPageNo");
			if (StringUtils.isNotBlank(s2))
				queryinfo.setCurPageNo(Integer.parseInt(s2));
			s2 = httpservletrequest.getParameter("pageSize");
			if (StringUtils.isNotBlank(s2))
				queryinfo.setPageSize(Integer.parseInt(s2));
			String s3 = getHqlByKey(httpservletrequest, s1);
			queryinfo
					.setOtherInfo(httpservletrequest.getParameter("otherInfo"));
		}
		return queryinfo;
	}

	/**
	 * @param httpservletrequest
	 * @param s
	 * @return
	 */
	public static String getHqlByKey(HttpServletRequest httpservletrequest,
			String s) {
		if (s == null)
			return null;
		String s1 = null;
		TransiantHashMap transianthashmap = (TransiantHashMap) httpservletrequest
				.getSession().getAttribute(GlobalNames.HQL_MAP);
		if (transianthashmap != null)
			s1 = (String) transianthashmap.get(s);
		return s1;
	}

	/**
	 * @param httpservletrequest
	 * @param s
	 * @return
	 */
	public static String getKeyByHql(HttpServletRequest httpservletrequest,
			String s) {
		if (s == null)
			return null;
		String s1 = null;
		s = s.trim();
		HttpSession httpsession = httpservletrequest.getSession();
		TransiantHashMap transianthashmap = (TransiantHashMap) httpsession
				.getAttribute(GlobalNames.HQL_MAP);
		if (transianthashmap == null) {
			transianthashmap = new TransiantHashMap();
			s1 = String.valueOf(System.currentTimeMillis());
			transianthashmap.put(s1, s);
			httpsession.setAttribute(GlobalNames.HQL_MAP, transianthashmap);
		} else {
			Iterator iterator = transianthashmap.keySet().iterator();
			for (Iterator iterator1 = transianthashmap.values().iterator(); iterator1
					.hasNext();) {
				String s2 = (String) iterator1.next();
				String s3 = (String) iterator.next();
				if (s.equals(s2))
					return s3;
			}

			s1 = String.valueOf(System.currentTimeMillis());
			transianthashmap.put(s1, s);
		}
		return s1;
	}

	/**
	 * @param httpservletrequest
	 * @param s
	 */
	public static void setOrderState(HttpServletRequest httpservletrequest,
			String s) {
		if (s == null)
			return;
		s = StringUtil.dealOrderBy(s);
		if (s.indexOf(GlobalNames.ORDER_BY) > -1) {
			java.lang.String s1 = s.substring(
					s.lastIndexOf(GlobalNames.ORDER_BY) + 8).trim();
			if (s.endsWith("desc")) {
				httpservletrequest.setAttribute("orderBy", s1.substring(0,
						s1.length() - 4).trim());
				httpservletrequest.setAttribute("order", "desc");
			} else if (s.endsWith("asc")) {
				httpservletrequest.setAttribute("orderBy", s1.substring(0,
						s1.length() - 3).trim());
				httpservletrequest.setAttribute("order", "asc");
			} else {
				httpservletrequest.setAttribute("orderBy", s1);
			}
		}
	}

	/**
	 * 执行成功跳转页面
	 * 
	 * @param httpservletrequest
	 * @param s
	 *            提示筐显示内容
	 * @param s1
	 *            显示类型 0 -错误页面 1-提示筐（默认）
	 */
	public ActionForward saveSuccessfulMsg(
			HttpServletRequest httpservletrequest, String s, String s1) {
		if (s == null || s.equals("")) {
			s = "操作成功";
		}
		if (s1 == null || s1.equals("")) {
			s1 = "1";
		}
		HashMap map = new HashMap();
		map.put("msg", s);
		map.put("type", s1);
		map.put("Originates", "0");
		httpservletrequest.setAttribute("message", map);
		ActionForward actionforward = new ActionForward("/errorpage.jsp");
		return actionforward;
	}

	/**
	 * 执行失败跳转页面
	 * 
	 * @param httpservletrequest
	 * @param s
	 * @param s1
	 */
	public ActionForward saveFailureMsg(HttpServletRequest httpservletrequest,
			EventResponse returnValue, String s1) {
		if (s1 == null || s1.equals("")) {
			s1 = "1";
		}
		HashMap map = new HashMap();
		map.put("msg", returnValue.getMsg());
		map.put("type", s1);
		map.put("Originates", "0");
		// sysLog.error(returnValue.getMsg());
		httpservletrequest.setAttribute("message", map);
		ActionForward actionforward = new ActionForward("/errorpage.jsp");
		return actionforward;
	}

	/**
	 * 框架统一处理成功和失败
	 * 
	 * @param httpservletrequest
	 * @param returnValue
	 * @param s1
	 * @return
	 */
	public ActionForward saveSuccessfulAndFailureMsg(
			HttpServletRequest httpservletrequest, EventResponse Env,
			String s2, String s1) {
		ActionForward forward = new ActionForward();
		if (Env.isSucessFlag()) {
			forward = saveSuccessfulMsg(httpservletrequest, s2, s1);
		} else {
			forward = saveFailureMsg(httpservletrequest, Env, s1);
		}
		return forward;
	}

}
