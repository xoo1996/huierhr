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
	header.add(new Formatter("astgctnm", "客户名称"));
	header.add(new Formatter("astpdtnm","固定资产名称"));
	header.add(new Formatter("astprc", "价格", TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("astut", "单位"));
	header.add(new Formatter("astqnt", "数量"));
	header.add(new Formatter("astdt", "登记日期"));
	header.add(new Formatter("astnote", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增","add(document.all.tableform)");
	buttons.put("修改","modify(document.all.tableform)");
	buttons.put("删除","del(document.all.tableform)");
	buttons.put("关闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("astid","流水号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "astgctid", "客户代码"));
	editors.add(new Editor("text", "astpdtnm", "固定资产名称"));
	editors.add(new Editor("text", "pdtut", "单位"));
	editors.add(new Editor("date", "start", "登记日期从"));
	editors.add(new Editor("date", "end", "到"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
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
		
		$("input[name=astgctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=astgctid]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=astgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		

	
	
	<%-- var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=astpdtid]").autocomplete(products,{
		max : 10,
		matchContains : true,
		formatItem: function(data, i, max) {
			return data[0].substring(0,data[0].indexOf(":"));
		}
	});
	$("input[name=astpdtid]").result(function(event, data, formatted) {
		if (data){
			var pid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=astpdtid]").val(pid);
		}
	}); --%>
	
	//$("input[name=astpdtid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=astpdtid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=astpdtid]").result(function(event, data, formatted) {
							if (data){
								var pid=data.proid;
								var pnm = data.proname;
								$("input[name=astpdtid]").val(pid);

							}
						});
					}
			});
		//});
      });
	
	function add(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/asset/new.jsp"/>';
		obj.submit();
	}
	
	function modify(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=showAsset&"/>' + getAlldata(obj);
		//obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/asset/modify.jsp"/>';
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
		obj.action='<html:rewrite page="/BusinessAction.do?method=deleteAsset&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="固定资产查询" />
	<lemis:query action="/BusinessAction.do?method=assetQuery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		topic="固定资产信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>