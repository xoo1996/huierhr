<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	
	header.add(new Formatter("ictid", "用户代码"));
	header.add(new Formatter("ictnm", "用户名称"));
	header.add(new Formatter("ictgender", "性别"));
	header.add(new Formatter("icttel", "联系电话"));
	header.add(new Formatter("ictaddr", "联系地址"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("收 费","shoufei(document.all.tableform)");
	buttons.put("新增用户","news(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "用户代码");
	hidden.put("ictnm","用户名称");
	hidden.put("ictgender","性别");
	hidden.put("icttel","联系电话");
	hidden.put("ictaddr","联系地址");
	hidden.put("folctnm","所属团体客户");
	hidden.put("ictgctid","团体客户代码");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ictnm", "用户名称"));
	editors.add(new Editor("text", "ictgender", "性别"));	
	editors.add(new Editor("text", "ictaddr", "联系地址"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

	function shoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ChargeAction.do?method=saveChgid&tp=erbeiji&"/>' + getAlldata(obj);
		obj.submit();
	};
	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/charge&page=/addclient.jsp"/>';
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="用户查询" />
	<lemis:query action="/ChargeAction.do?method=clientQuery&tp=erbei" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="用户信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


