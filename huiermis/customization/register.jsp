<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("录单","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="耳机定制项信息新增" />
	<lemis:tabletitle title="耳机定制项信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=saveNew"
			method="POST">
			<tr>
				<lemis:texteditor property="dgnctid" label="用户编号" required="true"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<lemis:texteditor property="dgnlid" label="左耳" required="true"
					disable="false" maxlength="20" />
				<lemis:texteditor property="dgnrid" label="右耳" required="true"
					disable="false" maxlength="20" />
				<lemis:codelisteditor type="tmkcpy" isSelect="true" label="定制厂商"
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
	<lemis:bottom />
</lemis:body>
</html>

