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
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gctprovince", "所在省"));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("gcttype", "店属类型"));
	header.add(new Formatter("pdttype", "产品品牌"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("temp02", "回款数量"));
	header.add(new Formatter("temp01", "回款金额", TagConstants.DF_RIGHT, TagConstants.DT_MONEY));
	header.add(new Formatter("temp12", "维修费", TagConstants.DF_RIGHT, TagConstants.DT_MONEY));
	header.add(new Formatter("temp04", "省回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp06", "区域回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp08", "品牌回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp10", "商品回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	
	//新增四个头
 	header.add(new Formatter("temp13", "定制机数量", "color:#aaaaaa;", true));
	header.add(new Formatter("temp14", "定制机金额", TagConstants.DF_RIGHT, TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp15", "普通商品数量", "color:#000000;",true));
	header.add(new Formatter("temp16", "普通商品金额", TagConstants.DF_RIGHT, TagConstants.DT_MONEY,true)); 

	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("ivtgcltid", "客户代码"));
	hidden.add(new Formatter("ivtyear", "年"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","年份","true"));
	editors.add(new Editor("text","ivtmonths","月从","true"));
	editors.add(new Editor("text","ivtmontht","到","true"));
	editors.add(new Editor("text","ivtgcltid","客户代码"));
	editors.add(new Editor("text","gctarea","所属区域"));
	editors.add(new Editor("text","gcttype","店属类型"));
	editors.add(new Editor("text","gctprovince","所在省"));
	editors.add(new Editor("text","pdttype","产品品牌"));
	editors.add(new Editor("text","pdtnm","商品名称"));
	editors.add(new Editor("text","repfee","维修类型"));
	
	//2012-3-26新增字段
	editors.add(new Editor("text","ivttype","商品类型"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("区域销售统计","area(document.all.tableform)");
	buttons.put("品牌销售统计","brand(document.all.tableform)");
	buttons.put("用户统计","client(document.all.tableform)");
	buttons.put("价格统计","price(document.all.tableform)");
	buttons.put("配机统计","audio(document.all.tableform)");
	
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">

$(document).ready(function(){
	

	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=ivtgcltid]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=ivtgcltid]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=ivtgcltid]").val(gid);
			$(this).parent().next().find("input").val(gid);
		}
	});


<%-- var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

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
}); --%>

//$("input[name=pdtnm]").bind("click",function(){
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
							$("input[name=pdtnm]").val(pnm);;

						}
					});
				}
		});
//});

});

</script>

<script>
	function area(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/queryqyxs.jsp"/>';
		obj.submit();
	};
	function brand(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/queryppxs.jsp"/>';
		obj.submit();
	};
	function client(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/queryclient.jsp"/>';
		obj.submit();
	};
	function price(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/queryprice.jsp"/>';
		obj.submit();
	};
	function audio(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/queryaudio.jsp"/>';
		obj.submit();
	};
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="产品销售查询" />
	<lemis:query action="/BusinessAction.do?method=salequery" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="销售详细信息"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


