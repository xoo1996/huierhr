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
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("惠耳出货", "huier_delivery(document.forms[1])");
	buttons.put("杰闻出货", "jiewen_delivery(document.forms[1])");
	buttons.put("返回", "window.history.back();");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("fdtfno", "订单号"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("gctnm", "团体客户"));
	header.add(new Formatter("fdtcltnm", "个人客户"));
	header.add(new Formatter("fdtpid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtprc", "售价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtsqnt", "发货"));
	header.add(new Formatter("fdtnt", "备注"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("fdtpid", "商品代码");
	hidden.put("pdtnm", "商品名称");
	hidden.put("folctid", "客户代码");
	hidden.put("foltype", "订单类型");

	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("nnnn","fdtsqnt","发货数量","true"));
	batchInput.add(new Editor("text","fdtnt","备注"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("hidden", hidden);
	
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
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);

		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=foldesnm]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=foldesnm]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=foldesnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
		
	});
	function huier_delivery(obj) {
		if (!checkValue(document.forms[0])) {
			return false;
		}
		if (!delObj("chk")) {
			return false;
		}
		if (!preCheckForBatch()) {
			return false;
		}
		/*
		if (confirm("确认发货吗？")) {
			//window.location.href = "/"
					//+ lemis.WEB_APP_NAME
					//+ "/order/OrderDetailAction.do?method=batchDelivery&require=kefu&type=huier&way="
					//+ $("#folway").val() + "&sno="
					//+ $("input[name=folsno]").val() + "&des="
					//+ $("input[name=foldes]").val() + "&"
					//+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=huier&way="/>'
				+$("#folway").val() + "&sno="
			    + $("input[name=folsno]").val() + "&des="
			    + $("input[name=foldes]").val() + "&"
			    + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.submit();
		}
		*/
		
		$("input[value=惠耳出货]").attr("disabled","true");
	    $("input[value=杰闻出货]").attr("disabled","true");
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>order/OrderDetailAction.do?method=before_batchDelivery&require=kefu&type=huier&way=' + $("#folway").val() + '&sno='
					+ $("input[name=folsno]").val() + '&des=' + $("input[name=foldes]").val() 
					+ '&' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F'),
			 //data:"proType=nom",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
				 if(data == null || data == ''){
					 if (confirm("确认发货吗？")) {
							window.location.href = "/"
									+ lemis.WEB_APP_NAME
									+ "/order/OrderDetailAction.do?method=batchDelivery&require=kefu&type=huier&way="
									+ $("#folway").val() + "&sno="
									+ $("input[name=folsno]").val() + "&des="
									+ $("input[name=foldes]").val() + "&"
									+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');

							//obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=huier&way="/>'
								//+$("#folway").val() + "&sno="
							   // + $("input[name=folsno]").val() + "&des="
							    //+ $("input[name=foldes]").val() + "&"
							    //+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
							//obj.submit();
					}else{
						$("input[value=惠耳出货]").attr("disabled","");
					    $("input[value=杰闻出货]").attr("disabled","");
					}
				 }else{
					 if (confirm("订单已发货不能重复发货，是否继续打印？")) {
							window.location.href = "/"
									+ lemis.WEB_APP_NAME
									+ "/order/OrderDetailAction.do?method=batchDelivery_print&require=kefu&type=huier&way="
									+ $("#folway").val() + "&sno="
									+ $("input[name=folsno]").val() + "&des="
									+ $("input[name=foldes]").val() + "&"
									+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');

							//obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=huier&way="/>'
								//+$("#folway").val() + "&sno="
							   // + $("input[name=folsno]").val() + "&des="
							    //+ $("input[name=foldes]").val() + "&"
							    //+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
							//obj.submit();
					}else{
						$("input[value=惠耳出货]").attr("disabled","");
					    $("input[value=杰闻出货]").attr("disabled","");
					}
			     }
			}
		});
		
	};
	function jiewen_delivery(obj) {
		if (!checkValue(document.forms[0])) {
			return false;
		}
		if (!delObj("chk")) {
			return false;
		}
		if (!preCheckForBatch()) {
			return false;
		}
		/*
		if (confirm("确认发货吗？")) {
			//window.location.href = "/"
					//+ lemis.WEB_APP_NAME
					//+ "/order/OrderDetailAction.do?method=batchDelivery&require=kefu&type=jiewen&way="
					//+ $("#folway").val() + "&sno="
					//+ $("input[name=folsno]").val() + "&des="
					//+ $("input[name=foldes]").val() + "&"
					//+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=jiewen&way="/>'
				+$("#folway").val() + "&sno="
			    + $("input[name=folsno]").val() + "&des="
			    + $("input[name=foldes]").val() + "&"
			    + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.submit();
		}
		*/
		
		$("input[value=惠耳出货]").attr("disabled","true");
	    $("input[value=杰闻出货]").attr("disabled","true");
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>order/OrderDetailAction.do?method=before_batchDelivery&require=kefu&type=jiewen&way=' + $("#folway").val() + '&sno='
					+ $("input[name=folsno]").val() + '&des=' + $("input[name=foldes]").val() 
					+ '&' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F'),
			 //data:"proType=nom",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
				 if(data == null || data == ''){
					 if (confirm("确认发货吗？")) {
						 window.location.href = "/"
								+ lemis.WEB_APP_NAME
								+ "/order/OrderDetailAction.do?method=batchDelivery&require=kefu&type=jiewen&way="
								+ $("#folway").val() + "&sno="
								+ $("input[name=folsno]").val() + "&des="
								+ $("input[name=foldes]").val() + "&"
								+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');

								//obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=jiewen&way="/>'
									//+$("#folway").val() + "&sno="
								    //+ $("input[name=folsno]").val() + "&des="
								    //+ $("input[name=foldes]").val() + "&"
								    //+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
								//obj.submit();
					}else{
						$("input[value=惠耳出货]").attr("disabled","");
					    $("input[value=杰闻出货]").attr("disabled","");
					}
				 }else{
					 if (confirm("订单已发货不能重复发货，是否继续打印？")) {
							window.location.href = "/"
									+ lemis.WEB_APP_NAME
									+ "/order/OrderDetailAction.do?method=batchDelivery_print&require=kefu&type=jiewen&way="
									+ $("#folway").val() + "&sno="
									+ $("input[name=folsno]").val() + "&des="
									+ $("input[name=foldes]").val() + "&"
									+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');

							//obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&require=kefu&type=jiewen&way="/>'
								//+$("#folway").val() + "&sno="
							   // + $("input[name=folsno]").val() + "&des="
							    //+ $("input[name=foldes]").val() + "&"
							    //+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
							//obj.submit();
					}else{
						$("input[value=惠耳出货]").attr("disabled","");
					    $("input[value=杰闻出货]").attr("disabled","");
					}
			     }
			}
		});
		
	};
</script>
<script>
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="出货" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderDetailAction.do?method=print" method="POST">
			<tr>
				<td>发货员</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>发货日期</td>
				<td><lemis:operdate /></td>
			</tr>
			<tr>
				<lemis:texteditor property="foldesnm" label="发往" disable="false"
					required="true" />
				<html:hidden property="foldes"/> 
				<lemis:codelisteditor type="folway" isSelect="true" label="发货方式"
					redisplay="true" required="true" dataMeta="folwayList"/>
				<lemis:texteditor property="folsno" label="快件号" disable="false"
					required="false" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细" action="/OrderDetailAction.do"
		headerMeta="header" hiddenMeta="hidden" mode="checkbox"
		batchInputMeta="batchInput" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


