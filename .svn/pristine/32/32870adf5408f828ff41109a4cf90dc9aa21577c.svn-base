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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("folno", "订单号"));
	header.add(new Formatter("folctid", "客户代码"));
	header.add(new Formatter("folctnm", "客户名称"));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("foltype","订单类型"));
	header.add(new Formatter("foloprnm", "录单员"));
	header.add(new Formatter("folnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("打印普通订单","print_huier(document.all.tableform)");
	//buttons.put("杰闻打印","print_jiewen(document.all.tableform)");
	buttons.put("发货","delivery(document.all.tableform)");
	//buttons.put("修改订单","modify(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folno", "订单号码");
	hidden.put("folctid", "客户代码");
	hidden.put("folctnm", "客户名称");
	hidden.put("foltype", "订单类型");
	hidden.put("foldt", "订货日期");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	editors.add(new Editor("text", "folctid", "客户代码"));
	editors.add(new Editor("date","foldt", "订货日期"));
	
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
	function print_huier(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=huier&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	function print_jiewen(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=jiewen&"/>' + getAlldata(obj);
		obj.submit();
	};
	//发货
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	};
	$(document).ready(function(){
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
				$(this).parent().next().find("input").val(gnm);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="待发货订单查询" />
	<lemis:query action="/OrderAction.do?method=query&order=delivery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="订单信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


