<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"prefix="html"%>
	<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("打 印","print(document.forms[0])");
	buttons.put("关 闭","closeWindow(\"\")");
	
	
	pageContext.setAttribute("button", buttons);
%>

<script language="javascript">
	function print(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var com =  document.getElementById("gctcom").value;
		if(com==""){
			alert("听力公司不能为空");
			return false;
		}	
		obj.submit();
	};
	
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=ivtgcltid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=ivtgcltid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ivtgcltid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="月报表" />
	<lemis:tabletitle title="打印月报表"/>
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=report" method="POST">
			<tr>
			    <lemis:texteditor property="ivtgcltid" required="true" label="团体代码" 
					disable="false" />
				<lemis:texteditor property="ivtyear" label="年" required="true"
					disable="false" />
				<lemis:texteditor property="ivtmonth" label="月" required="true"
					disable="false" />
			</tr>
			<tr>
			    <lemis:codelisteditor type="gctcom" isSelect="true" label="听力公司"
					redisplay="true" required="false" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button"/>
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>

