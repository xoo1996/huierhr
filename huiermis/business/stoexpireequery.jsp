<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("stogrcliid", "客户代码"));
	header.add(new Formatter("stogrpnm","客户名称"));
	header.add(new Formatter("stoproname", "商品名称"));
	header.add(new Formatter("storqnt", "剩余数量"));
	header.add(new Formatter("stoexp", "库存期限"));
	header.add(new Formatter("stodate", "入库日期","color:#000000;",TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("stoisexp", "是否到期"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("打 印","print()"); 
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stogrcliid","客户代码");
	hidden.put("stoproid","商品代码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "stogrcliid", "客户代码"));
	editors.add(new Editor("text", "stoproid", "商品代码"));
	editors.add(new Editor("text", "stoisexp", "是否到期"));
 	editors.add(new Editor("date", "start", "入库日期从"));
	editors.add(new Editor("date", "end", "到"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=stogrcliid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=stogrcliid]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=stogrcliid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});
	
	function add(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/amortize/new.jsp"/>';
		obj.submit();
	}
	
	function modify(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=showAmortize&"/>' + getAlldata(obj);
		obj.submit();
	}
	
	function del(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		if(!confirm("确定删除此条记录？")){
			return false;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=deleteAmortize&"/>' + getAlldata(obj);
		obj.submit();
	}
	
	function print() {
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=amortizeReport&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="库存超期查询" />
	<lemis:query action="/BusinessAction.do?method=amortizeQuery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		topic="库存信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>