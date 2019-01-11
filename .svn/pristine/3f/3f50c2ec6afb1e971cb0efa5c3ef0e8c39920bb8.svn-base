<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	
	header.add(new Formatter("gcltnm", "客户名称"));
	header.add(new Formatter("stoproid", "商品代码"));
	header.add(new Formatter("stoproname", "商品名称"));
	header.add(new Formatter("stoamount", "数量"));
	header.add(new Formatter("stoamountun", "商品单位"));
	header.add(new Formatter("stoproprice", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("stoactype", "动作"));
	header.add(new Formatter("stodate","日期","color:#000000;", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("storemark","备注"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("用户查询","check(document.all.tableform)");
	buttons.put("收 费","shoufei(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stoproid", "商品代码");
	hidden.put("stoid","库存编码");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "stoproid", "商品代码"));
	editors.add(new Editor("text", "stoactype", "动作"));	
	editors.add(new Editor("text", "pdtut", "商品单位"));
	editors.add(new Editor("date","start","入库日期从"));
	editors.add(new Editor("date","end","到"));
	
	
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
	function check(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/charge&page=/clientquery.jsp"/>';
		obj.submit();
	}
	function shoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ChargeAction.do?method=normalChargeDetail&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	<%--  $(document).ready(function(e) {
			var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

			$("input[name=stoproid]").autocomplete(products,{
				max : 10,
				matchContains : true
			}); 
			$("input[name=stoproid]").result(function(event, data, formatted) {
				if (data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=stoproid]").val(gid);
					var id = $(this).parent().find("input").attr("gid");
				}
			});
		}); --%>
		
	$(document).ready(function(){	
		//$("input[name=stoproid]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('获取数据错误！');
				 },
				 success:function(data){
							$("input[name=stoproid]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=stoproid]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=stoproid]").val(pid);
									var id = $(this).parent().find("input").attr("pid");

								}
							});
						}
				});
		//});
	
	});
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="收费查询" />
	<lemis:query action="/ChargeAction.do?method=normalQuery" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="收费信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


