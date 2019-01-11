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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("���ɶ���","saveData(document.forms[0])");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctid", "�ͻ�����");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.submit();
	};
</script>


<lemis:base />
<lemis:body>
	<div id="target"></div>
	<lemis:title title="��������" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarOrderAction.do?method=saveOrder" method="POST">
			<tr>
				<lemis:texteditor property="folctid" label="�ͻ�����" required="true"
					disable="false" maxlength="6"/>
				<lemis:texteditor property="folctnm" label="�ͻ�����" required="true"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<td>��������</td>
				<td><lemis:operdate /></td>
				<lemis:texteditor property="folnt" label="��ע" disable="false"
					maxlength="20" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>

			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>