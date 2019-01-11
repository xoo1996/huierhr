<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	//共7个属性项
	header.add(new Formatter("folno", "订单号"));
	header.add(new Formatter("folctnm", "客户名称"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("folnt", "备注")); 

	   
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folno","订单号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	editors.add(new Editor("text", "folctid", "客户代码"));
	editors.add(new Editor("text", "folsta", "订单状态"));
	editors.add(new Editor("text", "foltype", "订单类型"));
	editors.add(new Editor("date","start","订货日期从"));
	editors.add(new Editor("date","end","到"));
	
	
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
	$(document).ready(function(){
		$("input[name=folnt]").val("");
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=folctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=folctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=folctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>

    <lemis:title title="订单状态查询" />
	<lemis:query action="/OrderStateAction.do?method=stateQuery" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="OrderStateAction.do" headerMeta="header" topic="订单信息"
		hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


