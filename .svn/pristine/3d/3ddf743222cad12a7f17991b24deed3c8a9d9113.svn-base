<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
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
	<lemis:title title="用户复诊记录新增" />
	<lemis:tabletitle title="用户复诊信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=addSC" method="POST">
			<html:hidden property="ictid" />
			<tr>
				<lemis:formateditor mask="date" property="fctcdt" label="复诊日期"
					required="true" disable="false" />
				<lemis:texteditor property="fctreason" label="复诊原因" required="false"
					disable="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="fctdgn" label="检查情况" required="false"
					disable="false" colspan="7" />
			</tr>
			<tr>
				<lemis:texteditor property="fcttmt" label="处理意见" required="false"
					disable="false" colspan="7" />
			</tr>
			<tr>
				<lemis:texteditor property="fctdoc" label="医生" required="false"
					disable="false" />
				<lemis:texteditor property="fctnt" label="备注" required="false"
					disable="false" colspan="5" />
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

