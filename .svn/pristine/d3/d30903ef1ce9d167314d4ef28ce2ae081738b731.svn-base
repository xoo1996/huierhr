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
	header.add(new Formatter("fdtfno", "订单号"));
	header.add(new Formatter("gctnm", "团体客户"));
	header.add(new Formatter("fdtcltnm", "个人客户"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtsqnt", "发货"));
	header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "订货日期"));
	//header.add(new Formatter("folsdt", "发货日期"));
	//header.add(new Formatter("folway", "发货方式"));
	//header.add(new Formatter("folsno", "快件号"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("folnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("发 货","delivery()");
	//buttons.put("显 示","show()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folctid", "客户ID");
	hidden.put("gctnm", "客户名称");
	hidden.put("pdtnm", "商品名称");
	hidden.put("folsta","订单状态");
	hidden.put("foltype","订单类型");
	//hidden.put("fdtpid", "产品代码");
	//hidden.put("fdtsqnt", "发货数量");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	editors.add(new Editor("text", "folctid", "客户代码"));
	editors.add(new Editor("text","folsta","订单状态"));
	editors.add(new Editor("text","foltype","订单类型"));
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
		$("select[name=folsta]").val("waiting");
	});
</script>
<script language="javascript">
	//发货
	function delivery(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		//var folsta=$("input:select[name=folsta]").selected().val();  folsta="+folsta+"&"
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderDetailAction.do?method=enterDelivery&"
				+ getAlldata(document.all.tableform);
	};
	function show() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		var result = window.showModalDialog(
				"/huiermis/switchAction.do?prefix=/order&page=/selectWay.jsp",
				null, "dialogWidth:650px;dialogHeight:200px;help:no;status:no");
		if (result) {
			window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/order/OrderDetailAction.do?method=batchDelivery&way="
					+ result + "&require=kefu&"
					+ getAlldata(document.all.tableform);
		}
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="订单查询" />
	<lemis:query
		action="/OrderAction.do?method=queryAllDetail&require=delivery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="OrderDetailAction.do" headerMeta="header" topic="订单信息"
		hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


