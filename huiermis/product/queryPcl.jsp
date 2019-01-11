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
	header.add(new Formatter("pclid", "商品类别代码"));
	header.add(new Formatter("pcllarge", "商品大类"));
	header.add(new Formatter("pclmid", "商品中类"));
	header.add(new Formatter("pclsmall", "商品小类"));
	/* header.add(new Formatter("pdtprc", "耳机单价"));
	header.add(new Formatter("pdtclnm", "商品类别"));
	header.add(new Formatter("pdtnt", "备注")); */
	//header.add(new Formatter("tmkpst", "定制机状态", "color:#FF0000;"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增","add(document.all.tableform)");
	buttons.put("修改","modify(document.all.tableform)");
	buttons.put("删除","del(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pclid","商品类别代码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "pclid", "商品类别代码"));
/* 	editors.add(new Editor("text", "pdtnm", "商品名称"));
	editors.add(new Editor("text", "pdttype", "助听器品牌")); */
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>


<script language="javascript">
<%-- $(document).ready(function(){
	var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=pdtid]").autocomplete(products,{
		max : 10,
		matchContains : true
	});
	$("input[name=pdtid]").result(function(event, data, formatted) {
		if (data){
			var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
			var pid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=pdtid]").val(pid);
			$("input[name=pdtnm]").val(pnm);
			$(this).parent().next().find("input").val(pid);
		}
	});
}); --%>

$(document).ready(function(){
//$("input[name=pdtid]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
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
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=pdtid]").val(pid);
							$("input[name=pdtnm]").val(pnm);
							$(this).parent().next().find("input").val(pid);

						}
					});
				}
		});
//});
});	
	function add(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/product&page=/newPcl.jsp"/>';
		obj.submit();
	};
	function modify(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=modifyPcl&"/>' + getAlldata(obj);
		obj.submit();
	};
	function del(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=deletePcl&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>



<lemis:base />
<lemis:body>
	<lemis:title title="商品类别管理" />
	<lemis:query action="/ProductAction.do?method=queryPcl"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="类别信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


