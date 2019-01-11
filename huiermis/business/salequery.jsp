<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gctprovince", "所在省"));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("gcttype", "店属类型"));
	header.add(new Formatter("pdttype", "产品品牌"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc","零售价"));
	header.add(new Formatter("temp02", "回款数量"));
	header.add(new Formatter("temp01", "回款金额", "color:#000000;", TagConstants.DT_MONEY));
	//header.add(new Formatter("temp12", "维修费", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("temp04", "省回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp06", "区域回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp08", "品牌回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp10", "商品回款金额", "color:#000000;", TagConstants.DT_MONEY,true));
	
	//新增四个头
 	header.add(new Formatter("temp13", "定制机数量", "color:#aaaaaa;", true));
	header.add(new Formatter("temp14", "定制机金额", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp15", "耳背机盒式机数量", "color:#000000;",true));
	header.add(new Formatter("temp16", "耳背机盒式机金额", "color:#000000;", TagConstants.DT_MONEY,true)); 

	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("ivtgcltid", "客户代码"));
	hidden.add(new Formatter("ivtyear", "年"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","从年份","true"));
	editors.add(new Editor("text","ivtmonths","月","true"));
	editors.add(new Editor("text","ivtyearEnd","到年","true"));
	editors.add(new Editor("text","ivtmontht","月","true"));
	editors.add(new Editor("text","ivtgcltid","客户代码"));
	editors.add(new Editor("text","gctarea","所属区域"));
	editors.add(new Editor("text","gcttype","店属类型"));
	editors.add(new Editor("text","gctprovince","所在省"));
	editors.add(new Editor("text","pdttype","产品品牌"));
	editors.add(new Editor("text","pdtnm","商品名称"));
	//editors.add(new Editor("text","repfee","维修类型"));
	
	//2012-3-26新增字段
	editors.add(new Editor("text","ivttype","商品类型"));
	editors.add(new Editor("text","pdtcls","耳机类别"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
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
							$("input[name=pdtnm]").val(pnm);

						}
					});
				}
		});
//});

});
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


