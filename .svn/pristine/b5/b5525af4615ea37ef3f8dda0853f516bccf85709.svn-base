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
   // String pdtid = (String)request.getSession().getAttribute("pdtid");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("ivtfee", "价格"));
	header.add(new Formatter("temp01", "结存数"));
	header.add(new Formatter("ivttype", "订单类型"));
	
	List<Editor> editor = new ArrayList<Editor>();
	editor.add(new Editor("text","ivtgcltid","客户代码"));
	editor.add(new Editor("text", "ivtyear", "年"));
	editor.add(new Editor("text", "ivtmonth", "月"));
	editor.add(new Editor("text","ivtpdtid","商品代码",true));
	
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
	
    pageContext.setAttribute("button", buttons);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("editor", editor);
    
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(e) {
		
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
    <lemis:title title="结存详情" />
   <lemis:query action="/BusinessAction.do?method=jcdetail"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header" topic="结存信息" 
	     mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>