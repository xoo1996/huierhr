<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
   	List<Button> buttons=new ArrayList<Button>();
	buttons.add(new Button("back", "����", "cus999991", "history.back()"));
	pageContext.setAttribute("buttons",buttons);
 %>
<html>
	<script src="/lemis/js/lemisTree.js"></script>
	
	<lemis:base />
	<body >
	<lemis:title title="�鿴��������ϸ��Ϣ"/>

	<table class="tableinput" >
	<html:form action="/CIOperAction.do" >
		<tr height="35">
			<lemis:texteditor property="ciid" label="������ID" required="false" disable="true" maxlength="20" />
			<lemis:texteditor property="ciname" label="����������" required="false" disable="true" maxlength="20" />
			<lemis:codelisteditor type="citype" isSelect="false" label="����������" redisplay="false" required="false" />
		</tr>
		<tr height="35">
			<lemis:texteditor property="cidescription" label="����������" required="false" disable="true" maxlength="50" colspan="7" />
		</tr>
		<tr height="35">
			<lemis:codelisteditor type="cistate" isSelect="false" label="������״̬" redisplay="false" required="false" />
			<lemis:texteditor property="cisupplier" label="�����Ӧ��" required="false" disable="true" maxlength="20" />
			<lemis:texteditor property="cicharger" label="������" required="false" disable="true" maxlength="20" />
		</tr>
		<tr height="35">
			<lemis:texteditor property="ciadder" label="������" required="false" disable="true" maxlength="20" />
			<lemis:formateditor mask="date" property="ciadddt" required="false" label="��������" disable="true" format="true"/>
			<lemis:formateditor mask="date" property="cidepredt" required="false" label="�۾ɽ�������" disable="true" format="true"/>
		</tr>
		<tr height="35">
			<lemis:texteditor property="cigrtdt" label="��������" required="false" disable="true" maxlength="20" />
		</tr>
	</html:form>
	</table>
	
	<lemis:buttons buttonMeta="buttons" />	
	
	</body>
</html>

