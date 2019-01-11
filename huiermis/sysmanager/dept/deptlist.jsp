<!--sysmanager/dept/deptlist.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.manage.department.form.DeptForm"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			DeptForm fm = (DeptForm) session.getAttribute("deptForm");
			List buttons=new ArrayList();
			
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			String stringData = "";
			if(qi!=null){
				stringData = qi.getStringData();
			}
			if(qi!=null){
			buttons.add(new Button("addName","机构别名信息增加","sys010201","addData(document.forms[0])"));
			}
			buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"deptForm\")"));

			List header = new ArrayList();
			header.add(new Formatter("sc01.aab003", "机构编号"));
			header.add(new Formatter("sc01.aab300", "机构名称"));
			header.add(new Formatter("sc01.aae005", "联系电话"));
			header.add(new Formatter("sc01.aab304", "联系人"));
			header.add(new Formatter("sc01.bsc002", "机构简称"));
			header.add(new Formatter("sc01.aae100", "有效标记"));
			header.add(new Formatter("sc01.aae006", "机构地址"));
			Map qh = new LinkedHashMap();
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "aab003", "机构编号"));
			editors.add(new Editor("text", "aab300", "机构名称"));
			editors.add(new Editor("text", "aae005", "联系电话"));
			editors.add(new Editor("text", "aab304", "联系人"));
			editors.add(new Editor("text", "bsc002", "机构简称"));
			editors.add(new Editor("text", "aae100", "有效标记"));
			Map hidden = new LinkedHashMap();
            hidden.put("bsc001", "编号");
			pageContext.setAttribute("hidden", hidden);
            pageContext.setAttribute("editor", editors);
            pageContext.setAttribute("header", header);
            pageContext.setAttribute("button", buttons);
            session.setAttribute("tableheader","机构信息表");

%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="机构别名登记" />
		<lemis:query action="/deptAction.do?method=DepartmentList" editorMeta="editor" hiddenMeta="queryHiddens" topic="机构信息查询" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="deptAction.do" headerMeta="header" topic="机构信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	<lemis:clean names="deptForm" />
	</lemis:body>	
</html>
<script language="javascript">
	// 新增
	function addData(obj){
	obj.action = '<html:rewrite page="/sc02Action.do?method=findSc02"/>'
	+"&stringData=" +document.all("stringData").value+"&" + getAlldata(document.all.tableform);
	obj.submit();
	}
</script>
