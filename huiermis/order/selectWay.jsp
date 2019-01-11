<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("ȷ��", "delivery(document.forms[0])");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtcltid", "���˴���");
	hidden.put("fdtpid", "��Ʒ����");

	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function delivery(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		window.returnValue=$('#fdtsway').val();
		window.close();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="����" />
	<lemis:tabletitle title="ѡ�񷢻���ʽ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=modifyOrder" method="POST">
			<tr>
				<lemis:codelisteditor type="folway" isSelect="true" label="������ʽ"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
</lemis:body>
</html>


