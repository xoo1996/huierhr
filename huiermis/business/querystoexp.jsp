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
	//header.add(new Formatter("gctid","客户代码"));
	header.add(new Formatter("gctnm", "客户名称"));	
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("ictnm", "个人客户"));	
	header.add(new Formatter("stogrpnm", "商品名称"));
	header.add(new Formatter("pdtprc","零售价"));
	header.add(new Formatter("storqnt", "剩余数量"));
	header.add(new Formatter("stodate", "入库日期"));
	header.add(new Formatter("days","压库天数"));

	Map<String,String> hidden = new LinkedHashMap<String,String>();
	hidden.put("gctid","客户代码");
	hidden.put("stogrpnm","商品名称");
	hidden.put("gctarea","所属区域");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","gctnm","客户名称"));
	editors.add(new Editor("text","stogrpnm","商品名称"));
	editors.add(new Editor("money","prices","耳背机价格高于"));
	//editors.add(new Editor("text","gcttype","店属类型"));
	editors.add(new Editor("money","pricesbox","盒式机价格高于"));
	editors.add(new Editor("text","gctarea","所属区域"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修改压库期", "modify(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden",hidden);
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
	
	$("input[name=gctnm]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=gctnm]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=gctnm]").val(gnm);
			$(this).parent().next().find("input").val(gnm);
		}
	});
	

	


<%-- var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
$("input[name=stogrpnm]").autocomplete(products,{
	max : 10,
	matchContains : true
}); 

$("input[name=stogrpnm]").result(function(event, data, formatted) {
	if (data){
		var pid = data[0].substring(0,data[0].indexOf("="));
		var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
		$("input[name=stogrpnm]").val(pnm);
	}
}); --%>

//$("input[name=stogrpnm]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
		 dataType:'json',
		 error:function(){
		   alert('获取数据错误！');
		 },
		 success:function(data){
					$("input[name=stogrpnm]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.proid + "=" + data.proname);
						}
					});	
					$("input[name=stogrpnm]").result(function(event, data, formatted) {
						if (data){
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=stogrpnm]").val(pnm);

						}
					});
				}
		});
//});
});

</script>

<script language="javascript">
	function modify(obj)
	{
		obj.action='<html:rewrite page="/BusinessAction.do?method=queryExd"/>';
		obj.submit();
	};
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="库存超期查询" />
	<lemis:query action="/BusinessAction.do?method=stoexpquery" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="库存详细信息"
		hiddenMeta="hidden"  mode="radio"  />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


