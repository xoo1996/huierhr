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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("保 存", "saveData(document.forms[0])");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
	function saveData(obj) {
		obj.submit();
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="退机面板质检" />
	<lemis:tabletitle title="任务单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do?method=saveRecoilQA" method="POST">
			<tr>
				<%-- <lemis:texteditor property="pnlorgnm" label="原定制机型号" disable="false" required="true"/> --%>
				<lemis:texteditor property="pnlnm" label="面板型号" disable="false" required="true"/>
				<lemis:texteditor property="pnlid" label="新的面板编码" disable="false" required="true"/>
				<lemis:codelisteditor type="qachka" label="检验结果" isSelect="true"
						required="true" />
			</tr>
			<tr>
				<td>检验员</td>
				<td><lemis:operator /></td>
				<td>检验日期</td>
				<td><lemis:operdate/></td>
			</tr>
			<tr>
				<lemis:texteditor property="pnlnt" label="备注" disable="false" colspan="4" />
				
			</tr>
			
		</html:form>
	</table>

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>