<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
   	List<Button> buttons=new ArrayList<Button>();
	buttons.add(new Button("back", "返回", "cus999991", "history.back()"));
	pageContext.setAttribute("buttons",buttons);
 %>
<html>
	<script src="/lemis/js/lemisTree.js"></script>
	
	<lemis:base />
	<body >
	<lemis:title title="查看配置项详细信息"/>

	<table class="tableinput" >
	<html:form action="/CIOperAction.do" >
		<tr height="35">
			<lemis:texteditor property="ciid" label="配置项ID" required="false" disable="true" maxlength="20" />
			<lemis:texteditor property="ciname" label="配置项名称" required="false" disable="true" maxlength="20" />
			<lemis:codelisteditor type="citype" isSelect="false" label="配置项类型" redisplay="false" required="false" />
		</tr>
		<tr height="35">
			<lemis:texteditor property="cidescription" label="配置项描述" required="false" disable="true" maxlength="50" colspan="7" />
		</tr>
		<tr height="35">
			<lemis:codelisteditor type="cistate" isSelect="false" label="配置项状态" redisplay="false" required="false" />
			<lemis:texteditor property="cisupplier" label="配置项供应商" required="false" disable="true" maxlength="20" />
			<lemis:texteditor property="cicharger" label="负责人" required="false" disable="true" maxlength="20" />
		</tr>
		<tr height="35">
			<lemis:texteditor property="ciadder" label="创建人" required="false" disable="true" maxlength="20" />
			<lemis:formateditor mask="date" property="ciadddt" required="false" label="创建日期" disable="true" format="true"/>
			<lemis:formateditor mask="date" property="cidepredt" required="false" label="折旧结束日期" disable="true" format="true"/>
		</tr>
		<tr height="35">
			<lemis:texteditor property="cigrtdt" label="保修日期" required="false" disable="true" maxlength="20" />
		</tr>
	</html:form>
	</table>
	
	<lemis:buttons buttonMeta="buttons" />	
	
	</body>
</html>

