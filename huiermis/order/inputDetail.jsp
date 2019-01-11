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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("批量录入","batchSubmit(document.all.tableform)");
	buttons.put("惠耳打印","huier_print()");
	buttons.put("捷文打印","jiewen_print()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtprc","单价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("money","fdtprc","单价","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","数量","true"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	function huier_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderAction.do?method=print&type=huier&"
				+ getAlldata(document.all.tableform);
	};
	function jiewen_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderAction.do?method=print&type=jiewen&"
				+ getAlldata(document.all.tableform);
	};

 <%-- $(document).ready(function(e) {
		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

		$("input[name=fdtpid]").autocomplete(products,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		}); 
		$("input[name=fdtpid]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(6);
				$("#fdtpid" + suffix).val(pid);
				$("#pdtnm" + suffix).val(pnm);
				$("#fdtprc" + suffix).val(prc);
				$("#fdtqnt" + suffix).val(1);
			}
		});
	}); --%>
 
$(document).ready(function(e) {
 //$("input[name=fdtpid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=fdtpid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=fdtpid]").result(function(event, data, formatted) {
							if (data){
								var pid=data.proid;
								var pnm = data.proname;
								var prc=data.proprc;
								var id = $(this).parent().find("input").attr("id");
								var suffix = id.substr(6);
								$("#fdtpid" + suffix).val(pid);
								$("#pdtnm" + suffix).val(pnm);
								$("#fdtprc" + suffix).val(prc);
								$("#fdtqnt" + suffix).val(1);

							}
						});
					}
			});
	//});
});
</script> 
<script language="javascript">
	function batchSubmit() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderDetailAction.do?method=batchSubmit&"
				+ getAlldata(document.all.tableform) + "fdtfno="
				+ $("input[name=folno]").val();
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
	}
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="订单明细录入" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<lemis:texteditor property="folctid" label="客户代码" disable="true" />
				<lemis:texteditor property="folctnm" label="客户名称" disable="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" property="foldt" label="订货日期"
					required="false" disable="true" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细录入"
		action="/OrderDetailAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>