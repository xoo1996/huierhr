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
	header.add(new Formatter("folno", "订单号"));
	header.add(new Formatter("folctid", "团体客户"));
	header.add(new Formatter("ictnm", "个人客户"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtsqnt", "数量"));
	header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folsdt", "发货日期"));
	header.add(new Formatter("folway", "发货方式"));
	header.add(new Formatter("folsno", "快件号"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("folnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("发货","delivery(document.all.tableform)");
	//buttons.put("修改订单","modify(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folctid", "客户代码", "true"));
	editors.add(new Editor("date", "start", "时间从", "true"));
	editors.add(new Editor("date", "end", "到", "true"));
	
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	//发货
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="销帐" />
	<lemis:query action="/BusinessAction.do?method=check" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="帐务明细信息"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


