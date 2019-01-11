<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String menuId = (String) request.getParameter("menuId");
    
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ivtgcltid", "客户代码"));
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("temp01", "应收款","color:#000000;", TagConstants.DT_MONEY));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "年","true"));
	editors.add(new Editor("text", "ivtmonth", "月","true"));
	editors.add(new Editor("text", "ivtgcltid", "客户代码"));

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>

<lemis:base />
<lemis:body>
	<lemis:title title="应收款查询" />
	<lemis:query action="/BusinessAction.do?method=queryAccount" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="应收款信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


