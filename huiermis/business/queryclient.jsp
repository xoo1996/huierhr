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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ivtyear","年"));
	header.add(new Formatter("gctid","客户代码"));
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gender", "性别"));
	header.add(new Formatter("amount", "人数"));
	header.add(new Formatter("total","总人数"));

	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("gcltid", "客户代码"));
	hidden.add(new Formatter("ivtyear", "年"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","年份","true"));
	editors.add(new Editor("text","ivtmonths","月从","true"));
	editors.add(new Editor("text","ivtmontht","到","true"));
	editors.add(new Editor("text","ivtgcltid","客户代码"));
	editors.add(new Editor("text","ages","年龄从"));
	editors.add(new Editor("text","aget","到"));
	editors.add(new Editor("text","ictgender","性别"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">

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
	<lemis:title title="用户统计查询" />
	<lemis:query action="/BusinessAction.do?method=clientquery" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="用户详细信息"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


