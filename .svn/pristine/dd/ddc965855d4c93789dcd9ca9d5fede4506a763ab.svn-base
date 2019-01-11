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

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%  

	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("gctnm","所属团体"));
	header.add(new Formatter("stoprotype","商品类别"));
	header.add(new Formatter("stoproid","商品代码"));
	header.add(new Formatter("stoproname","商品名称"));
	header.add(new Formatter("pdtprc","单价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("stoamount","数量"));
	header.add(new Formatter("pdtut","数量单位"));
	header.add(new Formatter("stoactype","动作"));
	header.add(new Formatter("stodate","日期"));
	header.add(new Formatter("storemark","备注",true));
	header.add(new Formatter("isrepair","维修机"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","stoactype","动作"));
	editors.add(new Editor("date","start","日期从"));
	editors.add(new Editor("date","end","到"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	//hidden.put("stogrcliid", "");
	hidden.put("gctnm", "所属团体");
	hidden.put("stoproname","商品名称");

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	//pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	
%> 

<script language="javascript">	

$(document).ready(function(e) {
	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");

	var grClis = "<%=session.getServletContext().getAttribute("grCliList")%>".replace("{","").replace("}","").split(", ");

	
	
	if(grClis.length==1)
	{
		$("input[name=gctnm]").val(grClis[0].substring(grClis[0].indexOf("=")+1,grClis[0].length));
		$("input[name=gctnm]").attr("readonly","true");
	/* 	$("input:hidden[name=stogrcliid]").attr("value",grClis[0].substring(0,grClis[0].indexOf("=")));
		alert($("input:hidden[name=stogrcliid]").val()); */
		
	}
	$("input[name=gctnm]").autocomplete(shops,{
		max:10,
		matchContains:true,
		formatItem:function(data,i,max){
			return data[0].substring(0);
		}
	});

	$("input[name=gctnm]").result(function(event,data,formatted){
		if(data){
			
			var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
			$("input[name=gctnm]").val(gnm);
			
			/* alert($("input:hidden[name=stogrcliid]").val()); */
		}
	});
/* 	$("input[name=gctnm]").bind("blur",function( ){
	
		var gid = grClis[0].substring(0,grClis[0].indexOf("="));
		$("input:hidden[name=stogrcliid]").attr("value",gid);
	 }); */
 });
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="库存明细" />
	<lemis:query action="/InStoreAction.do?method=queryDetail"
		editorMeta="editor" topic="查询条件" hiddenMeta="hidden"/>
	<lemis:table action="/InStoreAction.do" headerMeta="header"
	 topic="库存明细" mode="radio" orderResult="false" hiddenMeta="hidden"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
