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
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("date", "folpdt", "日期","true"));
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("temp01", "计划出货数"));
	header.add(new Formatter("temp02", "实际完成数"));
	header.add(new Formatter("temp03", "未完成数"));
	header.add(new Formatter("temp04", "完成百分率"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("查询","stat(document.forms[0])");
	//buttons.put("修改订单","modify(document.all.tableform)");
	buttons.put("重置","resetForm(document.forms[0])");

	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	//统计查询
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=queryStat"/>';
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:errors />
	<lemis:title title="定制机每日出货统计" />

	<lemis:query action="/OrderAction.do?method=queryStat"
		editorMeta="editor" topic="统计条件" />
	<lemis:table action="OrderAction.do" headerMeta="header"
		topic="每日出货统计信息" mode="radio" pilot="false" orderResult="false" />
	<lemis:bottom />
</lemis:body>
</html>


