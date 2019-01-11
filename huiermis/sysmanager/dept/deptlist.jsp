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
			buttons.add(new Button("addName","����������Ϣ����","sys010201","addData(document.forms[0])"));
			}
			buttons.add(new Button("close","�� ��","sys999999","closeWindow(\"deptForm\")"));

			List header = new ArrayList();
			header.add(new Formatter("sc01.aab003", "�������"));
			header.add(new Formatter("sc01.aab300", "��������"));
			header.add(new Formatter("sc01.aae005", "��ϵ�绰"));
			header.add(new Formatter("sc01.aab304", "��ϵ��"));
			header.add(new Formatter("sc01.bsc002", "�������"));
			header.add(new Formatter("sc01.aae100", "��Ч���"));
			header.add(new Formatter("sc01.aae006", "������ַ"));
			Map qh = new LinkedHashMap();
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "aab003", "�������"));
			editors.add(new Editor("text", "aab300", "��������"));
			editors.add(new Editor("text", "aae005", "��ϵ�绰"));
			editors.add(new Editor("text", "aab304", "��ϵ��"));
			editors.add(new Editor("text", "bsc002", "�������"));
			editors.add(new Editor("text", "aae100", "��Ч���"));
			Map hidden = new LinkedHashMap();
            hidden.put("bsc001", "���");
			pageContext.setAttribute("hidden", hidden);
            pageContext.setAttribute("editor", editors);
            pageContext.setAttribute("header", header);
            pageContext.setAttribute("button", buttons);
            session.setAttribute("tableheader","������Ϣ��");

%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="���������Ǽ�" />
		<lemis:query action="/deptAction.do?method=DepartmentList" editorMeta="editor" hiddenMeta="queryHiddens" topic="������Ϣ��ѯ" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="deptAction.do" headerMeta="header" topic="������Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	<lemis:clean names="deptForm" />
	</lemis:body>	
</html>
<script language="javascript">
	// ����
	function addData(obj){
	obj.action = '<html:rewrite page="/sc02Action.do?method=findSc02"/>'
	+"&stringData=" +document.all("stringData").value+"&" + getAlldata(document.all.tableform);
	obj.submit();
	}
</script>
