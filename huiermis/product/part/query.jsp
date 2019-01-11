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
	header.add(new Formatter("pdtid", "商品代码"));
	//header.add(new Formatter("acypdtid", "零件货号"));
	header.add(new Formatter("pdtnm", "零件名称"));
	header.add(new Formatter("pdtmod", "零件型号"));
	//header.add(new Formatter("acybthnum", "零件批号"));
	header.add(new Formatter("pdtclnm", "商品类别"));
	header.add(new Formatter("pdtminsto","最小库存量"));
	header.add(new Formatter("pdtnt", "备注"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新 增","add(document.all.tableform)");
 	buttons.put("修 改","modify(document.all.tableform)"); 
	buttons.put("删 除","del(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pdtid","商品代码");
	hidden.put("pdtnm","零件名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "pdtid", "商品代码"));
	//editors.add(new Editor("text", "acypdtid", "零件货号"));
	editors.add(new Editor("text", "pdtnm", "零件名称"));
	editors.add(new Editor("text", "pdtmod", "零件型号"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">


$(document).ready(function(e) {
	//$("input[name=pdtid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryParts',
			 data:"proType=nom",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=pdtid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=pdtid]").result(function(event, data, formatted) {
							if (data){
								var pid = data.proid;
								var pnm = data.proname;
								$("input[name=pdtid]").val(pid);
								$("input[name=pdtnm]").val(pnm);
									
							}
						});
			     }
		    });
	    //});
	 });


	function add(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/product&page=/part/new.jsp"/>';
		obj.submit();
	};
	function modify(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=modify1&"/>' + getAlldata(obj);
		obj.submit();
	};
	function del(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if(!confirm("确定删除？")){
			window.event.reutrnValue=false;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=delete&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="零件管理" />
	<lemis:query action="/ProductAction.do?method=query&mark=part"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="零件信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>