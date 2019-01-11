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
	buttons.put("确定", "delivery(document.forms[0])");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtcltid", "病人代码");
	hidden.put("fdtpid", "商品代码");

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
	<lemis:title title="出货" />
	<lemis:tabletitle title="选择发货方式" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=modifyOrder" method="POST">
			<tr>
				<lemis:codelisteditor type="folway" isSelect="true" label="发货方式"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
</lemis:body>
</html>


