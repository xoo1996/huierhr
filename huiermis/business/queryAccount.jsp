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
	header.add(new Formatter("ivtgcltid", "�ͻ�����"));
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("temp01", "Ӧ�տ�","color:#000000;", TagConstants.DT_MONEY));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "��","true"));
	editors.add(new Editor("text", "ivtmonth", "��","true"));
	editors.add(new Editor("text", "ivtgcltid", "�ͻ�����"));

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>

<lemis:base />
<lemis:body>
	<lemis:title title="Ӧ�տ��ѯ" />
	<lemis:query action="/BusinessAction.do?method=queryAccount" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="Ӧ�տ���Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


