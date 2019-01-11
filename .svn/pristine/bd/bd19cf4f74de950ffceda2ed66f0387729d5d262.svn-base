<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gctprovince", "所在省"));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("ncdypname","验配师姓名"));
	header.add(new Formatter("cltnm","客户姓名"));
	header.add(new Formatter("foltype","订单类型"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "原价","color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtdisc","扣率"));
	header.add(new Formatter("fdtprc", "售价","color:#000000;", TagConstants.DT_MONEY));/*售价*/
	//header.add(new Formatter("recmoney", "退机费","color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("sta", "状态"));
	header.add(new Formatter("folchgdt", "销售日期"));
	//header.add(new Formatter("fdtodt", "退机日期"));
	header.add(new Formatter("fdtisspdisc", "是否是特别申请"));
	header.add(new Formatter("folnt", "收费备注"));
	
	
	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("gctnm", "客户名称"));
	hidden.add(new Formatter("pdtnm", "商品名称"));
	hidden.add(new Formatter("ictgctid","所属团体"));
	hidden.add(new Formatter("cltnm","客户姓名"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","gctnm","客户名称"));
	editors.add(new Editor("date","start","销售日期从"));
	editors.add(new Editor("date","end","到"));
	//editors.add(new Editor("date","stui","退机日期从"));
	//editors.add(new Editor("date","etui","到"));
	editors.add(new Editor("text","pdttype","品牌类型"));
	editors.add(new Editor("text","pdtnm","商品名称"));
	editors.add(new Editor("text","foltype","订单类型"));
	editors.add(new Editor("text","cltnm","客户姓名"));
	editors.add(new Editor("text","gctarea","所属区域"));
	editors.add(new Editor("text","ncdypname","验配师姓名"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String grCli_cx=dto.getBsc011();
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
	//HF0001 销售一区(区域一) HF0010 销售二区（区域二）  HF0011销售三区 (区域三) HF0012 销售三区   HF0019 销售四区 (区域四)
	//HF0005  销售五区 (区域五)  HF0014 销售七区  (区域七)  销售六区（杭州区）
	var grCli_cx="<%=grCli_cx%>";
    //$("input[name=gctnm]").val(grCli_cx);
	if(grCli_cx=="HF0001"){
		$("select[name=gctarea]").val("区域一");
		$("select[name=gctarea]").attr("disabled",true);
		
		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域一");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
		    $("select[name=gctarea]").val("区域一");
		});
	}
	if(grCli_cx=="HF0010"){
		$("select[name=gctarea]").val("区域二");
		$("select[name=gctarea]").attr("disabled",true);
		
		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域二");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("区域二");
		});
	}
	if(grCli_cx=="HF0011" || grCli_cx=="HF0012"){
		$("select[name=gctarea]").val("区域三");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域三");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("区域三");
		});
	}
	if(grCli_cx=="HF0019"){
		$("select[name=gctarea]").val("区域四");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域四");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("区域四");
		});
	}
	if(grCli_cx=="HF0005"){
		$("select[name=gctarea]").val("区域五");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域五");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("区域五");
		});
	}
	if(grCli_cx=="HF0014"){
		$("select[name=gctarea]").val("区域七");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=重置[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("区域七");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=查询[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("区域七");
		});
	}
	/*
	else{
		var shops = "0123456789销售一区=杭州区,0123456789销售二区=区域二,0123456789销售三区=区域三,0123456789销售四区=区域四,0123456789销售五区=区域五,0123456789销售六区=区域六,0123456789销售七区=区域七,0123456789=其他类".split(",");
		alert(shops);
		$("input[name=gctarea]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=gctarea]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=gctarea]").val(gnm);
			}
		});
	}
    */
	
	
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	if(grCli=="1501000000")
	{
		$("input[name=gctnm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
			return data[0].substring(0);
			}
		});
	
		$("input[name=gctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=gctnm]").val(gnm);
			
				}
			});
	}
	else
	{
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=gctnm]").attr("readonly","true");
		$("input[value=重置[R]]").bind("click",function(e){
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}

});

	<%--var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=gctnm]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=gctnm]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			//var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=gctnm]").val(gnm);
			$(this).parent().next().find("input").val(gnm);
		}
	});  --%>


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
							$("input[name=pdtnm]").val(pnm);

						}
					});
				}
		//});
});


</script>
<lemis:base />
<lemis:body>
	<lemis:title title="实时销售查询" />
	<lemis:query action="/BusinessAction.do?method=realtimequery" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="销售信息"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


