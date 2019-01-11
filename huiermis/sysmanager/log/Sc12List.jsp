<!--sysmanager/log/Sc12List.jsp-->
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
			header.add(new Formatter("sc12.bsc036", "��ˮ��"));
			header.add(new Formatter("sc12.bsc027", "������ʱ��"));
			header.add(new Formatter("sc12.bsc034", "������"));			
			header.add(new Formatter("sc12.bsc035", "������Ϣ"));
			Map qh = new LinkedHashMap();
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("date", "bsc027", "������ʱ��"));
			editors.add(new Editor("text", "bsc034", "������"));
			Editor sc12error=new Editor("text", "bsc035", "������Ϣ");
			sc12error.setMaxlength(500);
			editors.add(sc12error);
			Map hidden = new LinkedHashMap();
			hidden.put("bsc036", "���");
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			session.setAttribute("tableheader", "������־��Ϣ��");

%>
<html>
<lemis:base />
	<lemis:body>
		<lemis:title title="������Ϣ��¼" />
		<lemis:query action="/logsc12Action.do?method=FindSc12List" editorMeta="editor" hiddenMeta="queryHiddens" topic="������Ϣ��ѯ" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="logsc12Action.do" headerMeta="header" topic="������Ϣ�б�" hiddenMeta="hidden"  />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="sc12Form" />
</html>

