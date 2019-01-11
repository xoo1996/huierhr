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
	buttons.put("生成订单","saveData(document.forms[0])");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctid", "客户代码");
	
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
	<lemis:title title="订单新增" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarOrderAction.do?method=saveOrder" method="POST">
			<tr>
				<lemis:texteditor property="folctid" label="客户代码" required="true"
					disable="false" maxlength="6"/>
				<lemis:texteditor property="folctnm" label="客户名称" required="true"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<td>订货日期</td>
				<td><lemis:operdate /></td>
				<lemis:texteditor property="folnt" label="备注" disable="false"
					maxlength="20" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>

			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>