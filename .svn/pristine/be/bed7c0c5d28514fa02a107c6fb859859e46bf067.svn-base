<!--sysmanager/log/Sc13List.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			QueryInfo qi = (QueryInfo) pageContext.findAttribute(GlobalNames.QUERY_INFO);
			String stringData = "";
			if(qi!=null)
			stringData = qi.getStringData();
			
			List header = new ArrayList();
			header.add(new Formatter("sc13.bsc046", "流水号"));
			header.add(new Formatter("sc13.bsc011", "用户名称"));
			header.add(new Formatter("sc13.bsc037", "原科室编号"));
			header.add(new Formatter("sc13.bsc038", "原科室名称"));
			header.add(new Formatter("sc13.bsc039", "原机构号"));
			header.add(new Formatter("sc13.bsc040", "原机构名称"));
			header.add(new Formatter("sc13.bsc041", "目前室编号"));
			header.add(new Formatter("sc13.bsc042", "目前室名称"));
			header.add(new Formatter("sc13.bsc043", "目前机构号"));
			header.add(new Formatter("sc13.bsc044", "目前机构名称"));
			header.add(new Formatter("sc13.bsc045", "变更时间"));
			header.add(new Formatter("sc13.aae011", "操作人"));
			
			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc012", "用户名称"));
			editors.add(new Editor("text", "bsc037", "原科室编号"));
			editors.add(new Editor("text", "bsc038", "原科室名称"));
			editors.add(new Editor("text", "bsc039", "原机构号"));
			editors.add(new Editor("text", "bsc040", "原机构名称"));
			editors.add(new Editor("text", "bsc041", "目前室编号"));
			editors.add(new Editor("text", "bsc042", "目前室名称"));
			editors.add(new Editor("text", "bsc043", "目前机构号"));
			editors.add(new Editor("text", "bsc044", "目前机构名称"));
			editors.add(new Editor("date", "bsc045", "变更时间"));
			Map hidden = new LinkedHashMap();
			hidden.put("bsc025", "编号");
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			session.setAttribute("tableheader", "变更日志信息表");
%>
<html>
<lemis:base />
	<lemis:body>
		<lemis:title title="变更信息记录" />
		<lemis:query action="/logsc13Action.do?method=FindSc13List"	editorMeta="editor" hiddenMeta="queryHiddens" topic="变更信息查询" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="logsc13Action.do" headerMeta="header" topic="变更信息列表" hiddenMeta="hidden"  />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="sc13Form" />
</html>
