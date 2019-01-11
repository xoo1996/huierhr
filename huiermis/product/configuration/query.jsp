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
	//header.add(new Formatter("pnlid", "面板编码"));
	header.add(new Formatter("pnlnm", "面板型号"));
	header.add(new Formatter("pnlnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修 改","modify(document.all.tableform)");
	buttons.put("删 除","del(document.all.tableform)");
	//buttons.put("面板配置详情","detail(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pnlid","面板编码");
	hidden.put("pnlnm","面板型号");
	hidden.put("cfgacyidori", "修改前的零件代码");
	hidden.put("cfgtemperature", "温度");

	
	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "pnlid", "面板编码"));
	editors.add(new Editor("text", "pnlnm", "面板型号"));
	
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
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
	function modify(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ConfigurationAction.do?method=modify&"/>' + getAlldata(obj);
		obj.submit();
	};
	function del(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ConfigurationAction.do?method=delete&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<script language="javascript">
<%-- 	$(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("pnlnmList")%>".replace("[","").replace("]","").split(", ");
		
		$("input[name=pnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=pnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0];
				$("input[name=pnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});
	});  --%>
	
$(document).ready(function(){
	//$("input[name=pnlnm]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryPanels',
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=pnlnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.pnlnm);
							}
						});	
						$("input[name=pnlnm]").result(function(event, data, formatted) {
							if (data){
								var pnlnm=data.pnlnm;
								$("input[name=pnlnm]").val(pnlnm);
								$(this).parent().next().find("input").val(pnlnm);

									
									
							}
						});
					}
			});
	//});
});
</script>


<lemis:base />
<lemis:body>
	<lemis:title title="查改配置" />
	<lemis:query action="/ConfigurationAction.do?method=query"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ConfigurationAction.do" headerMeta="header"
			topic="配置信息" hiddenMeta="hidden" mode="radio" />
	
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


