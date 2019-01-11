// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.lbs.cp.taglib.Formatter;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.PageQueryInfo;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.taglib:
//            Formatter, Editor, TagConstants, TagUtil

public class ViewTableTag extends TagSupport {

	Log logger;

	protected boolean exportExcel;

	protected boolean excelShowHidden;

	protected boolean scroll_x;

	protected String action;

	protected String topic;

	private String _fldint;

	protected String resultSet;

	protected String headerMeta;

	private String _fldif;

	protected String mode;

	private String _fldcase;

	private String _fldvoid;

	private String b;

	private String _fldgoto;

	protected String orderResult;

	private List _fldtry;

	protected List header;

	private boolean _fldnull;

	private boolean d;

	private int e;

	private int _fldbyte;

	private String _flddo;

	protected PageQueryInfo qi;

	StringBuffer hiddenSb;

	StringBuffer hiddenHeaderSb;

	StringBuffer hiddenBodySb;

	private int c;

	private HttpServletRequest _fldfor;

	private HttpServletResponse a;

	private int _fldchar;

	private int _fldlong;

	private int _fldelse;

	private String _fldnew;

	public ViewTableTag() {
		logger = LogFactory.getLog(org.radf.plat.taglib.ViewTableTag.class);
		exportExcel = true;
		excelShowHidden = false;
		scroll_x = false;
		resultSet = GlobalNames.QUERY_DATA;
		_fldif = "";
		mode = "";
		_fldcase = "";
		_fldvoid = "";
		b = "true";
		_fldgoto = "true";
		orderResult = "true";
		_fldtry = null;
		header = null;
		_fldnull = false;
		d = false;
		e = 0;
		_fldbyte = 0;
		_flddo = "";
		qi = null;
		hiddenSb = null;
		hiddenHeaderSb = null;
		hiddenBodySb = null;
		c = GlobalNames.PAGE_SIZE;
	}

	public int getPageSize() {
		return c;
	}

	public void setPageSize(int i) {
		c = i;
	}

	public int doStartTag() throws JspException {
		try {
		    init();
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer.append(renderHeader());
			if (scroll_x)
				stringbuffer
						.append("<div style=\"width:100%;overflow-x:auto;overflow-y:visible\">");
			a(stringbuffer);
			if (scroll_x)
				stringbuffer.append("</div>");
			if ("true".equalsIgnoreCase(getPilot()))
				stringbuffer.append(renderPilot());
			ResponseUtils.write(pageContext, stringbuffer.toString());
			//pageContext.removeAttribute(stringbuffer.toString(),3);
			return 1;
		} catch (JspException jspexception) {
			jspexception.printStackTrace();
			throw jspexception;
		}
	}

	private void a(StringBuffer stringbuffer) throws JspException {
		stringbuffer
				.append("<table  width='95%' border='0' align='center' cellpadding='0' cellspacing='0' class='tableList'><tr><td class=''>");
		stringbuffer
				.append(" <table id='resultset' width='100%' border='0' align='center' cellspacing='1' > ");
		stringbuffer.append("<tr align='center'>");
		if ("checkbox".equalsIgnoreCase(mode)) {
			stringbuffer
					.append("<td width='3%' height='0' class='tableHead' >");
			stringbuffer
					.append("<input type='checkbox' name='checkall' class='check' onclick=\"selectall(document.all('chk'))\">");
			stringbuffer.append("</td> ");
		} else if (!"".equals(mode) && mode != null)
			stringbuffer
					.append("<td width='3%' height='0' class='tableHead' >&nbsp;</td>");
		Map map = null;
		if (!"".equals(getHiddenMeta().trim()))
			map = (Map) pageContext.findAttribute(getHiddenMeta());
		for (Iterator iterator = header.iterator(); iterator.hasNext();) {
			Formatter formatter = (Formatter) iterator.next();
			if (!formatter.isHidden()) {
				stringbuffer
						.append("<td height='0' nowrap class='tableHead' >");
				if (_fldnull) {
					String s = formatter.getCode();
					Editor editor = a(s);
					if (editor != null && editor.getRequired() != null
							&& "true".equalsIgnoreCase(editor.getRequired()))
						stringbuffer.append("<font color='#FF0000'>*</font>");
				}
				stringbuffer.append(formatter.getLabel());
				stringbuffer.append("</td>");
			}
		}

		stringbuffer
				.append("</tr><form id='tableform' name='tableform' id='tableform' method='post'>");
		Collection collection = (Collection) pageContext
				.findAttribute(getResultSet());
		int i = 0;
		boolean flag = true;
		if (collection != null) {
			boolean flag1 = true;
			if (collection != null) {
				Iterator iterator1 = collection.iterator();
				if (iterator1.hasNext()) {
					Object obj = iterator1.next();
					if (obj instanceof Object[])
						flag1 = false;
				}
			}
			for (Iterator iterator2 = collection.iterator(); iterator2
					.hasNext();) {
				a(i + 1);
				stringbuffer.append("<tr ");
				flag = a(stringbuffer, flag);
				Object obj1 = iterator2.next();
				try {
					if (mode != null && !"".equals(mode)) {
						stringbuffer
								.append("<td width='3%' height='0' style='text-valign:center;' nowrap ><input type='");
						stringbuffer.append(mode);
						stringbuffer
								.append("' name='chk' class='check' id ='subCheckbox");
						stringbuffer.append(i + 1);
						stringbuffer.append("' value='").append(i + 1).append(
								"' ");
						if ("checkbox".equalsIgnoreCase(mode))
							stringbuffer
									.append(" onclick=\"checkItem('chk', document.all('checkall'))\"");
						else if (i == 0 && "radio".equalsIgnoreCase(mode))
							stringbuffer.append(" checked='true'");
						else if (!"radio".equalsIgnoreCase(mode))
							stringbuffer
									.append(" name='chk' class='check' disabled='true'");
						if (_fldnull && "insert".equalsIgnoreCase(_flddo))
							stringbuffer.append(" disabled='true'");
						stringbuffer.append(" ></td>");
					}
					a(stringbuffer, hiddenHeaderSb, hiddenBodySb, false, flag1,
							obj1, i + 1);
					if (map != null)
						a(stringbuffer, map, i + 1, false, flag1, obj1);
					stringbuffer.append("</tr>");
					if (d)
						_mthif(stringbuffer);
				} catch (Exception exception) {
					exception.printStackTrace();
					throw new JspException("table标签异常", exception);
				}
				i++;
			}

		}
		if ("true".equalsIgnoreCase(getAppendBlank())) {
			for (int j = 0; j < c - i; j++) {
				stringbuffer.append("<tr");
				flag = a(stringbuffer, flag);
				stringbuffer
						.append("<td height=\"0\" align=\"center\" nowrap >");
				if (mode != null && !"".equals(mode)) {
					stringbuffer.append("<input type=\"");
					stringbuffer.append(mode);
					stringbuffer.append("\" name=\"chk\" value=\"");
					stringbuffer.append(j + i + 1);
					stringbuffer.append("\" class='check' ");
					if (!_fldnull || _fldnull
							&& "update".equalsIgnoreCase(_flddo))
						stringbuffer.append(" disabled='true'");
					stringbuffer.append("/></td>");
				}
				a(j + i + 1);
				a(stringbuffer, hiddenHeaderSb, hiddenBodySb, true, true, null,
						j + i + 1);
				if (map != null && _fldnull
						&& !"update".equalsIgnoreCase(_flddo))
					a(stringbuffer, map, j + i + 1, true, true, null);
				stringbuffer.append("</tr>");
				if (d)
					_mthif(stringbuffer);
			}

		}
		stringbuffer.append(_mthdo());
		stringbuffer.append("</form></table></td></tr></table>");
	}

	private void _mthif(StringBuffer stringbuffer) {
		stringbuffer.append(hiddenSb.toString());
		stringbuffer.append(hiddenHeaderSb.toString());
		stringbuffer.append("</tr><tr>");
		stringbuffer.append(hiddenBodySb.toString());
		stringbuffer.append("</tr></table></td></tr>");
	}

	private void a(StringBuffer stringbuffer, Map map, int i, boolean flag,
			boolean flag1, Object obj) {
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); stringbuffer
				.append("'/>")) {
			String s = (String) iterator.next();
			Object obj1;
			if (flag)
				obj1 = "";
			else
				obj1 = a(flag1, obj, s);
			stringbuffer.append("<input type='hidden' value='");
			stringbuffer.append(obj1);
			stringbuffer.append("' id='");
			stringbuffer.append(s);
			stringbuffer.append("_hrow");
			stringbuffer.append(i);
			stringbuffer.append("' name='");
			stringbuffer.append(s);
		}

	}

	private void a(int i) {
		if (d) {
			hiddenHeaderSb = new StringBuffer();
			hiddenBodySb = new StringBuffer();
			hiddenSb = new StringBuffer("<tr valign='top' id='sub");
			hiddenSb.append(i);
			hiddenSb.append("' style='display:none'> ");
			hiddenSb.append("<td colspan='");
			hiddenSb.append((header.size() - e) + 1);
			hiddenSb.append("'><table class='hiddenTable'><tr>");
		}
	}

	private void a(StringBuffer stringbuffer, StringBuffer stringbuffer1,
			StringBuffer stringbuffer2, boolean flag, boolean flag1,
			Object obj, int i) throws JspException {
		boolean flag2 = false;
		for (Iterator iterator = header.iterator(); iterator.hasNext();) {
			Formatter formatter = (Formatter) iterator.next();
			String s = formatter.getCode();
			if (s != null && !s.equals("")) {
				int index = s.indexOf(".");
				if (index >= 0) {
					s = s.substring(index + 1);
				}

			}
			String s1 = formatter.getType();
			String s2 = formatter.getFormat();
			String s3 = formatter.getLabel();
			boolean flag3 = formatter.isHidden();
			Object obj1;
			if (flag) {
				obj1 = "";
				s1 = "blank";
			} else {
				obj1 = a(flag1, obj, s);
			}
			if (flag3) {
				stringbuffer1.append("<td class='hiddenTableHead'>");
				stringbuffer1.append(s3);
				stringbuffer1.append("</td>");
				stringbuffer2.append("<td>");
				stringbuffer2.append(a(s, obj1.toString(), s1, i, flag));
				stringbuffer2.append("</td>");
			} else {
				stringbuffer.append("<td height='0' style='");
				stringbuffer.append(s2);
				stringbuffer.append("word-break:break-all;");
				if (d && a(s) == null) {
					stringbuffer.append("CURSOR: hand;' ");
					stringbuffer.append(" onclick=\"expandIt('");
					stringbuffer.append(i);
					stringbuffer.append("')\">");
				} else {
					stringbuffer.append("'>");
				}
				stringbuffer.append(a(s, obj1.toString(), s1, i, flag));
				stringbuffer.append("</td>");
			}
			flag3 = false;
		}

	}

	private boolean a(StringBuffer stringbuffer, boolean flag) {
		if (flag) {
			stringbuffer.append(" class='listColorA'>");
			flag = false;
		} else {
			stringbuffer.append(" class='listColorB'>");
			flag = true;
		}
		return flag;
	}

	private Object a(boolean flag, Object obj, String s) {
		Object obj1 = null;
		boolean flag1 = a(s) == null;
		try {
			if (flag)
				obj1 = PropertyUtils.getSimpleProperty(obj, s);
			else
				obj1 = ((Object[]) obj)[qi.getFieldIndex(s)];
		} catch (IllegalAccessException illegalaccessexception) {
			if (flag1)
				logger.error(illegalaccessexception.toString());
		} catch (InvocationTargetException invocationtargetexception) {
			if (flag1)
				logger.error(invocationtargetexception.toString());
		} catch (NoSuchMethodException nosuchmethodexception) {
			if (flag1)
				logger.error(nosuchmethodexception.toString());
		} catch (Exception exception) {
			if (flag1)
				if (exception instanceof ArrayIndexOutOfBoundsException)
					logger.error(exception.toString()
							+ ",或许是table标签中的header中的字段：" + s + "不在查询结果中，请检查！");
				else
					logger.error(exception.toString());
		}
		obj1 = obj1 != null ? obj1 : "";
		if (obj1.getClass().getName().equals("java.sql.Timestamp")) {
			Timestamp timestamp = (Timestamp) obj1;
			if(timestamp.getHours()==0 && timestamp.getMinutes()==0 && timestamp.getSeconds()==0)
			{
				obj1 = new Date(timestamp.getTime()); //原处理方法
			}else{
				obj1 = DateUtil.getOracleFormatDateStr(new java.util.Date(timestamp.getTime())); //syg修改 在分页在组件中显示时分秒
			}
			//obj1 = new Date(timestamp.getTime()); //原处理方法
			//obj1 = DateUtil.getOracleFormatDateStr(new java.util.Date(timestamp.getTime())); //syg修改 在分页在组件中显示时分秒
			
		}
		return obj1;
	}

	protected String renderHeader() throws JspException {
		String s = pageContext.getRequest().getParameter("order");
		String s1 = pageContext.getRequest().getParameter("orderBy");
		HttpServletResponse httpservletresponse = (HttpServletResponse) pageContext
				.getResponse();
		String s2 = httpservletresponse.encodeURL(RequestUtils
				.getActionMappingURL(action, pageContext));
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("\n<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0' ><tr><td><table width='100%' height='21' border='0' cellpadding='0' cellspacing='0'><tr>");
		stringbuffer.append("<form name='orderForm' id='orderForm' action='");
		stringbuffer
				.append("' method='post'><td align='left' valign='bottom' style=\"word-break:keep-all\">");
		stringbuffer.append("<TABLE class='tableTitle'><TR><TD>");
		stringbuffer.append(topic);
		stringbuffer.append("</TD></TR></TABLE>");
		stringbuffer
				.append("</td><td width='10' class=''>&nbsp;</td><td>&nbsp;</td><td width='40%'>&nbsp;</td>");
		if ("true".equalsIgnoreCase(getOrderResult())) {
			stringbuffer.append("<td width='90' align='left' >");
			stringbuffer
					.append("<select class='select' name='orderBy' id='orderBy'>");
			for (Iterator iterator = header.iterator(); iterator.hasNext(); stringbuffer
					.append("</option>")) {
				Formatter formatter = (Formatter) iterator.next();
				String s3 = formatter.getLabel();
				s3 = s3.replaceAll("&nbsp;", "");
				String s4 = formatter.getCode();
				stringbuffer.append("<option value='");
				// 修改shneyg
				// if (qi != null && qi.getRudeFields() != null
				// && -1 != qi.getFieldIndex(s4)) {
				// String as[] = qi.getRudeFields();
				// stringbuffer.append(as[qi.getFieldIndex(s4)]);
				// } else {
				// stringbuffer.append(s4);
				// }
				stringbuffer.append(s4);
				if (s4.equals(s1))
					stringbuffer.append("' selected>");
				else
					stringbuffer.append("'>");
				stringbuffer.append(s3);
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("</td><td width='50' ><select class='select' name='order' id='order' >\n");
			if ("desc".equals(s))
				stringbuffer
						.append("<option value='asc'>升序</option><option value='desc' selected>降序</option>");
			else
				stringbuffer
						.append("<option value='asc' selected>升序</option><option value='desc'>降序</option>");
			stringbuffer.append("</select></td><td>\n");
			if (qi != null
					&& pageContext.findAttribute(GlobalNames.QUERY_DATA) != null)
				stringbuffer
						.append("<input type='submit' name='sort' value='排序' class='buttonGray' onclick='return orderPage()'/>");
			else
				stringbuffer
						.append("<input type='button' name='sort' value='排序' class='buttonGray' disabled=true/>");
			stringbuffer.append("</td>");
		}
		stringbuffer.append("</form></tr></table></td></tr></table>");
		return stringbuffer.toString();
	}

	protected String renderPilot() {
		String s = "";
		String s1 = "";

		boolean flag = pageContext.findAttribute(getResultSet()) != null
				&& qi != null;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("<table class='tableInput' width='95%' >");
		stringbuffer
				.append("<form name ='pageQueryForm' action='")
				.append(_fldnew)
				.append("?method=commonQuery")
				.append("' method='post' id='pageQueryForm'>")
				.append(
						"<input type='hidden' id='headerMeta' name='headerMeta' value='")
				.append(_mthif())
				.append("'/>")
				.append("<input type='hidden' id='toPage' name='toPage'/>")
				.append("<input type='hidden' id='Pagenum' name='Pagenum'/>")
				.append(
						"<input type='hidden' id='pageQueryAction' name='pageQueryAction'/>")
				.append(
						"<input type='hidden' id='queryinfo' name='queryinfo' value='")
				.append(qi != null ? qi.serial2text() : "").append("'/>")
				.append("</form>");
		stringbuffer
				.append("<tr><td width='10%' style='text-align:center;' nowrap>总计:</td><td width='10%' style='text-align:center;' nowrap>");
		HttpServletResponse httpservletresponse = (HttpServletResponse) pageContext
				.getResponse();
		String s2 = httpservletresponse.encodeURL(RequestUtils
				.getActionMappingURL(action, pageContext));
		stringbuffer.append(_fldchar);
		stringbuffer
				.append("条记录</td><td width='4%'><input name='toPageInput' id='toPageInput' type='text' class='text'  style='width:50px' ");
		if (_fldlong > 1)
			stringbuffer.append(" value='").append(_fldlong).append("' ");
		stringbuffer.append("size='0'>");

		if (_fldelse > 1) {
			stringbuffer
					.append(
							"<input type='button' name='goPage' value='转到' class='buttonGray' onclick='pageQuery(document.all.toPageInput.value,\"\",false")
					.append(")'");

		} else {
			stringbuffer
					.append("<input type='button' value='转到' class='buttonGray' disabled='true' />");

		}
		stringbuffer
				.append("</td><td width='4%' style='text-align:center;' nowrap>行数<input name='PagenumInput' id='PagenumInput' type='text' class='text' style='width:50px' ");
		if(qi!=null)
		stringbuffer.append(" value='").append(qi.getPageSize()).append("' ");
		stringbuffer.append("size='0'>");
		
		if (_fldelse > 1) {

			stringbuffer
					.append("<input type='button' name='goPagenum' value='确定' class='buttonGray' onclick='pageQuerysum(document.all.PagenumInput.value,\"\",false)'");
		} else {
			stringbuffer
					.append("<input type='button' value='确定' class='buttonGray' disabled='true' />");
		}

		stringbuffer
				.append("</td><td width='28%'  style='text-align:center;' nowrap >共");
		stringbuffer.append(_fldelse);
		stringbuffer.append("页  当前是第");
		stringbuffer.append(_fldlong);
		stringbuffer.append("页 </td>");
		String s3 = pageContext.getRequest().getParameter("order");
		String s4 = pageContext.getRequest().getParameter("orderBy");
		String s5 = "";
		if (StringUtils.isNotBlank(s3))
			s5 = s5 + "&order=" + s3;
		if (StringUtils.isNotBlank(s4))
			s5 = s5 + "&orderBy=" + s4;
		if (_fldlong > 1) {
			a(stringbuffer, "first", "false", "最前一页", s5);
			a(stringbuffer, "previous", "false", "上一页", s5);
		} else {
			stringbuffer
					.append("<td width='8%'  style='text-align:center;'  nowrap>最前一页</td>");
			stringbuffer
					.append("<td width='8%' style='text-align:center;' nowrap>上一页</td>");
		}
		if (_fldlong < _fldelse) {
			a(stringbuffer, "next", "false", "下一页", s5);
			a(stringbuffer, "last", "false", "最后一页", s5);
		} else {
			stringbuffer
					.append("<td width='8%' style='text-align:center;' nowrap>下一页</td>");
			stringbuffer
					.append("<td width='8%' style='text-align:center;' nowrap>最后一页</td>");
		}
		if (isExportExcel()){
			if (_fldelse > 0) {
				a(stringbuffer, "expExcel", "true", "导出当前页", s5);
				a(stringbuffer, "expExcelAll", "true", "导出全部", s5);
			} else {
				stringbuffer
						.append("<td width='8%' style='text-align:center;' nowrap>导出当前页</td>");
				stringbuffer
						.append("<td width='8%' style='text-align:center;' nowrap>导出全部</td>");
			}}
		stringbuffer.append("</tr></table>");
		stringbuffer.append(a());
		return stringbuffer.toString();
	}

	private void a(StringBuffer stringbuffer, String s, String s1, String s2,
			String s3) {
		stringbuffer
				.append(
						"<td width='8%' style='text-align:center;' nowrap><a href='javascript:pageQuery(\"\",\"")
				.append(s).append("\",").append(s1).append(",\"").append(s3)
				.append("\")' class='BLink'>").append(s2).append("</a></td>");
	}

	public void release() {
		super.release();
		action = null;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String s) {
		action = s;
	}

	public String getHeaderMeta() {
		return headerMeta;
	}

	public void setHeaderMeta(String s) {
		headerMeta = s;
	}

	public String getResultSet() {
		return resultSet;
	}

	public void setResultSet(String s) {
		resultSet = s;
	}

	public boolean isExcelShowHidden() {
		return excelShowHidden;
	}

	public void setExcelShowHidden(boolean flag) {
		excelShowHidden = flag;
	}

	public boolean isExportExcel() {
		return exportExcel;
	}

	public void setExportExcel(boolean flag) {
		exportExcel = flag;
	}

	public String getPanelAction() {
		return _fldint;
	}

	public void setPanelAction(String s) {
		_fldint = s;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String s) {
		topic = s;
	}

	public String getHiddenMeta() {
		return _fldif;
	}

	public void setHiddenMeta(String s) {
		_fldif = s;
	}

	public String getMode() {
		return mode;
	}

	public String getOrderInPageMeta() {
		return _fldvoid;
	}

	public String getBatchInputMeta() {
		return _fldcase;
	}

	public String getAppendBlank() {
		return _fldgoto;
	}

	public String getPilot() {
		return b;
	}

	public String getOrderResult() {
		return orderResult;
	}

	public String getBatchInputType() {
		return _flddo;
	}

	public void setMode(String s) {
		mode = s;
	}

	public void setOrderInPageMeta(String s) {
		_fldvoid = s;
	}

	public void setBatchInputMeta(String s) {
		_fldcase = s;
	}

	public void setAppendBlank(String s) {
		_fldgoto = s;
	}

	public void setPilot(String s) {
		b = s;
	}

	public void setOrderResult(String s) {
		orderResult = s;
	}

	public void setBatchInputType(String s) {
		_flddo = s;
	}

	private String a(String s, String s1, String s2, int i, boolean flag)
			throws JspException {
		if (_fldnull) {
			Editor editor = a(s);
			if (flag) {
				if (!"update".equalsIgnoreCase(_flddo) && editor != null)
					return a(editor, s1, i);
			} else if (!"insert".equalsIgnoreCase(_flddo) && editor != null)
				return a(editor, s1, i);
		}
		if (s2 != null) {
			if (TagConstants.DT_MONEY.equalsIgnoreCase(s2))
				s1 = (new org.radf.plat.commons.Money(s1)).toString();
			if (TagConstants.DT_YEAR_MONTH.equalsIgnoreCase(s2))
				s1 = DateUtil.getStepMonth(s1, 0);
			if (TagConstants.DT_MONTH.equalsIgnoreCase(s2))
				s1 = DateUtil.month2YearMonth(s1);
			//用与分页中显示年月日格式的日期  by cb
            if (TagConstants.DT_YEAR_MONTH_DATE.equalsIgnoreCase(s2)){
                s1 = DateUtil.converToString(s1,"yyyy-MM-dd");
            }
		}
		TreeMap treemap = (TreeMap) pageContext.findAttribute(s.toUpperCase());
		if (treemap != null)
			s1 = (String) treemap.get(s1);
		return s1 != null ? s1 : "";
	}

	private Editor a(String s) {
		if (!_fldnull)
			return null;
		if (s == null)
			return null;
		for (Iterator iterator = _fldtry.iterator(); iterator.hasNext();) {
			Editor editor = (Editor) iterator.next();
			if (s.equalsIgnoreCase(editor.getCode()))
				return editor;
		}

		return null;
	}

	private String a(Editor editor, String s, int i) throws JspException {
		String s1 = editor.getCode();
		String s2 = editor.getRequired();
		String s3 = editor.getLabel();
		String s4 = editor.getType();
		String s5 = editor.getDefaultValue();
		int j = editor.getMaxlength();
		String s6 = s1 + "_row" + i;
		if (s == null || "".equals(s))
			s = s5 != null ? s5 : "";
		TreeMap treemap = (TreeMap) pageContext.findAttribute(s1.toUpperCase());
		if (treemap != null)
			return TagUtil.renderSelect(treemap, s1, s3, s2, s, s6, "");
		if (s4.equals("text"))
			return TagUtil.renderText(s1, s3, s2, s, s6, j);
		else
			return TagUtil.renderFormatInput(s1, s3, s2, s, s6, j, s4, true,
					null);
	}

	private String _mthdo() {
		StringBuffer stringbuffer = new StringBuffer("");
		Map map = (Map) pageContext.findAttribute(GlobalNames.PAGE_SUM);
		if (map != null) {
			if ("".equals(mode) || mode == null)
				stringbuffer.append("<tr>");
			else
				stringbuffer
						.append("<tr><td height=\"0\" align=\"center\" class='pageFooter'  nowrap >小计</td>");
			for (Iterator iterator = header.iterator(); iterator.hasNext(); stringbuffer
					.append("</td>")) {
				Formatter formatter = (Formatter) iterator.next();
				String s = formatter.getCode();
				if (s != null && !s.equals("")) {
					int index = s.indexOf(".");
					if (index >= 0) {
						s = s.substring(index + 1);
					}

				}
				String s1 = formatter.getFormat();
				Object obj = map.get(s);
				if (obj == null)
					obj = "&nbsp;";
				stringbuffer
						.append("<td height='0' class='pageFooter' style='");
				stringbuffer.append(s1);
				stringbuffer.append("'>");
				stringbuffer.append(obj);
			}

			stringbuffer.append("</tr>");
		}
		Map map1 = (Map) pageContext.findAttribute(GlobalNames.TOTAL_SUM);
		if (map1 != null) {
			if ("".equals(mode) || mode == null)
				stringbuffer.append("<tr>");
			else
				stringbuffer
						.append("<tr><td height=\"0\" align=\"center\" class='pageFooter'  nowrap >合计</td>");
			for (Iterator iterator1 = header.iterator(); iterator1.hasNext(); stringbuffer
					.append("</td>")) {
				Formatter formatter1 = (Formatter) iterator1.next();
				String s2 = formatter1.getCode();
				if (s2 != null && !s2.equals("")) {
					int index = s2.indexOf(".");
					if (index >= 0) {
						s2 = s2.substring(index + 1);
					}

				}
				String s3 = formatter1.getFormat();
				Object obj1 = map1.get(s2);
				if (obj1 == null)
					obj1 = "&nbsp;";
				stringbuffer
						.append("<td height='0' class='pageFooter' style='");
				stringbuffer.append(s3);
				stringbuffer.append("'>");
				stringbuffer.append(obj1);
			}

			stringbuffer.append("</tr>");
		}
		return stringbuffer.toString();
	}

	protected void init() {
		_fldfor = (HttpServletRequest) pageContext.getRequest();
		a = (HttpServletResponse) pageContext.getResponse();
		_fldnew = RequestUtils.getActionMappingURL(action, pageContext);
		qi = (PageQueryInfo) pageContext.findAttribute(GlobalNames.QUERY_INFO);
		if (qi == null) {
			String s = pageContext.getRequest().getParameter(
					GlobalNames.QUERY_INFO);
			if (StringUtils.isNotBlank(s))
				qi = PageQueryInfo.deserialFromText(s);
			if(s==null){
				logger = LogFactory.getLog(org.radf.plat.taglib.ViewTableTag.class);
				exportExcel = true;
				excelShowHidden = false;
				scroll_x = false;
				resultSet = GlobalNames.QUERY_DATA;
				_fldif = "";
				mode = "";
				_fldcase = "";
				_fldvoid = "";
				//b = "true";
				_fldgoto = "true";
				//orderResult = "true";
				_fldtry = null;
				header = null;
				_fldnull = false;
				d = false;
				e = 0;
				_fldbyte = 0;
				_flddo = "";
				qi = null;
				hiddenSb = null;
				hiddenHeaderSb = null;
				hiddenBodySb = null;
				c = GlobalNames.PAGE_SIZE;
				_fldchar=0;
				_fldlong=0;
				_fldelse=0;
				
			}
		}
		if (qi != null) {
			_fldchar = qi.getRowCount();
			_fldlong = qi.getCurPageNo() + 1;
			_fldelse = qi.getPageCount();
		}
		header = (List) pageContext.findAttribute(getHeaderMeta());
		_fldtry = (List) pageContext.findAttribute(getBatchInputMeta());
		if (_fldtry != null) {
			_fldnull = true;
			_fldbyte = _fldtry.size();
		}
		e=0;//20070918bylwd
		pageContext.getSession().setAttribute("header",header);//lwd20070930为了导出数据库格式问题
		for (Iterator iterator = header.iterator(); iterator.hasNext();) {
			Formatter formatter = (Formatter) iterator.next();
			if (formatter.isHidden()) {
				d = true;
				e++;
			}
		}

	}

	private String _mthif() {
		StringBuffer stringbuffer = new StringBuffer();
		if (excelShowHidden) {
			Map map = null;
			if (!"".equals(getHiddenMeta().trim()))
				map = (Map) pageContext.findAttribute(getHiddenMeta());
			if (map != null) {
				String s;
				for (Iterator iterator = map.keySet().iterator(); iterator
						.hasNext(); stringbuffer.append(s).append(" ").append(
						(String) map.get(s)).append(" ").append(
						qi.getFieldIndex(s)).append(","))
					s = (String) iterator.next();

			}
		}   
		List list = null;
		if (!"".equals(getHeaderMeta().trim()))
			list = (List) pageContext.findAttribute(getHeaderMeta());
		if (list != null) {
			for (Iterator iterator1 = list.iterator(); iterator1.hasNext();) {
				Formatter formatter = (Formatter) iterator1.next();
				String code = formatter.getCode();
				if (code != null && !code.equals("")) {
					int index = code.indexOf(".");
					if (index >= 0) {
						code = code.substring(index + 1);
					}

				}
				stringbuffer.append(code).append(" ").append(
						formatter.getLabel()).append(" ");
				if (qi != null)
					stringbuffer.append(qi.getFieldIndex(code)).append(",");
			}

		}
		return stringbuffer.toString();
	}

	private String a() {
		String s = "";
		if (qi != null)
			s = qi.getStateUrl().toString();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(" \n<script language=\"javascript\"> \n");
		stringbuffer
				.append(" function pageQuery(toPage,action,newWindow,appendUrl) { \n");
		stringbuffer.append(" var urlParams='").append(s).append("'; \n");
		stringbuffer.append(" var form=document.all.pageQueryForm; \n");
		stringbuffer.append(" form.toPage.value=toPage; \n");
		stringbuffer.append(" var url='").append(_fldnew).append(
				"?method=commonQuery'\n");
		stringbuffer.append(" form.pageQueryAction.value=action; \n");
		stringbuffer.append(" if (toPage!=\"\")  \n");
		stringbuffer.append(" url=url+\"&toPage=\"+toPage; \n");
		stringbuffer
				.append(" form.action=url+\"&pageQueryAction=\"+action+urlParams+appendUrl; \n");
		stringbuffer.append(" var target=form.target; \n");
		stringbuffer.append(" if (newWindow) { \n");
		stringbuffer.append(" form.target='_blank'; \n");
		stringbuffer.append(" } \n");
		stringbuffer.append(" form.submit(); \n");
		stringbuffer.append(" form.target=target; \n");
		stringbuffer.append(" } \n");

		stringbuffer.append(" function orderPage() { \n");
		stringbuffer.append(" var form=document.all.pageQueryForm; \n");
		stringbuffer.append(" var orderform=document.all.orderForm; \n");
		stringbuffer
				.append(" var url='&orderBy='+orderform.orderBy.value+'&order='+orderform.order.value; \n");
		stringbuffer.append(" pageQuery('','order',false,url); \n");
		stringbuffer.append(" return false; \n");
		stringbuffer.append(" } \n");

		stringbuffer
				.append(" function pageQuerysum(Pagenum,action,newWindow,appendUrl) { \n");

		stringbuffer.append(" var urlParams='").append(s).append("'; \n");
		stringbuffer.append(" var form=document.all.pageQueryForm; \n");
		stringbuffer.append(" form.Pagenum.value=Pagenum; \n");
		stringbuffer.append(" var url='").append(_fldnew).append(
				"?method=commonQuery'\n");
		stringbuffer.append(" form.pageQueryAction.value=action; \n");
		stringbuffer.append(" if (Pagenum!=\"\")  \n");
		stringbuffer.append(" url=url+\"&Pagenum=\"+Pagenum; \n");
		stringbuffer
				.append(" form.action=url+\"&pageQueryAction=\"+action+urlParams+appendUrl; \n");
		stringbuffer.append(" var target=form.target; \n");
		stringbuffer.append(" if (newWindow) { \n");
		stringbuffer.append(" form.target='_blank'; \n");
		stringbuffer.append(" } \n");
		stringbuffer.append(" form.submit(); \n");
		stringbuffer.append(" form.target=target; \n");
		stringbuffer.append(" } \n");

		stringbuffer.append(" </script> \n");

		return stringbuffer.toString();
	}

	public boolean isScroll_x() {
		return scroll_x;
	}

	public void setScroll_x(boolean flag) {
		scroll_x = flag;
	}
}
