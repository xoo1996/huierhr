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
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc","零售价"));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("gcttype", "所属类型"));
	header.add(new Formatter("pdttype", "产品品牌"));
	header.add(new Formatter("temp01", "结存数"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("显示详情","batchmd(document.all.tableform)");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtpdtid","商品代码");
	hidden.put("ivtyear", "年");
	hidden.put("ivtmonth", "月");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "年","true"));
	editors.add(new Editor("text", "ivtmonth", "月","true"));
	editors.add(new Editor("text", "gctarea", "所属区域"));
	editors.add(new Editor("text", "gcttype", "所属类型"));
	editors.add(new Editor("text", "pdttype", "产品品牌"));
	editors.add(new Editor("text", "pdtnm", "商品名称"));
	
	
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

<%-- 	$(document).ready(function(){
		
		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
	
		$("input[name=pdtnm]").autocomplete(products,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=pdtnm]").result(function(event, data, formatted) {
			if (data){
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				$("input[name=pdtnm]").val(pnm);
			}
		});
	}); --%>
	
	
	$(document).ready(function(){
		$("input[name=pdtnm]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('获取数据错误！');
				 },
				 success:function(data){
							$("input[name=pdtnm]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=pdtnm]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=pdtnm]").val(pnm);
	
								}
							});
						}
				});
		});
	});
	function batchmd(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=jcdetail&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="商品结存查询" />
	<lemis:query action="/BusinessAction.do?method=jcquery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		topic="结存信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>