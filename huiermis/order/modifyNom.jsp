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
/* 	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保存","save(document.forms[0])");
    buttons.put("提交","commit(document.forms[0])"); */
/* 	buttons.put("批量录入","batchSubmit(document.all.tableform)");
	buttons.put("惠耳打印","huier_print()");
	buttons.put("捷文打印","jiewen_print()");
	buttons.put("关 闭","closeWindow(\"\")"); */
	
//	List<Button> buttons=new ArrayList<Button>();
//	buttons.add(new Button("hedz","保 存","ord020004","save(document.forms[0])"));
//	buttons.add(new Button("jwdz","提 交","ord020005","commit(document.forms[0])"));
//	buttons.add(new Button("back","返 回","ord020006","history.back()"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保存","save(document.forms[0])"); 
    buttons.put("提交","commit(document.forms[0])"); 
    buttons.put("返回","history.back()"); 
	
	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtdprc","价格",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	/* header.add(new Formatter("fdtdisc","扣率")); */
	header.add(new Formatter("fdtqnt","数量"));
	/* header.add(new Formatter("fdtprc","现价")); */
	header.add(new Formatter("fdtinnt","备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("text","fdtdprc","价格","true"));
	/* batchInput.add(new Editor("text","fdtdisc","扣率","true")); */
	batchInput.add(new Editor("text","fdtqnt","数量","true"));
	/* batchInput.add(new Editor("text","fdtprc","现价","true")); */

	batchInput.add(new Editor("text","fdtinnt","备注","false"));
	
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtpid", "商品代码");
	hidden.put("fdtdprc", "原价");
	hidden.put("fdtdisc", "扣率");
	hidden.put("fdtqnt", "数量");
	hidden.put("fdtprc", "现价");
	hidden.put("fdtinnt", "备注");
	
	pageContext.setAttribute("hidden", hidden);
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

 $(document).ready(function(e) {
<%-- 		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

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
				$("#fdtdprc" + suffix).val(prc);
			/* 	$("#fdtqnt" + suffix).val(1); */
			}
		}); --%>
		 /* $("input[name=chk]").attr("checked",true); */
		 $("input[name=fdtdprc]").attr('readonly','readonly');
 		 $("input[name=pdtnm]").attr('readonly','readonly'); 
 		 $(":checkbox:enabled").attr('checked', true);
 		 
 		function chk(e) {
 			var id = $(e.target).attr("id");  //拿到控件id值id='fdtpid_row1'
 			var suffix = id.substr(10); 
 			var suffix2 = id.substr(6); 
 			var fdtid=$("#fdtpid" + suffix2).val();
 	        if(fdtid!='')
 	        {
 	        	$("input[name=chk][value="+suffix+"]").attr("checked",true);
 	        }
 	        else
 	        {
 	        	$("input[name=chk][value="+suffix+"]").attr("checked",false);
 	        }
 				
 		};
 		$("input[name=fdtpid]").bind("blur",function(e){
 			chk(e);
 		});
 
	//$("input[name=fdtpid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=nom",
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
								var pid = data.proid;
								var pnm = data.proname;
								var prc=data.proprc;
								var id = $(this).parent().find("input").attr("id");
									var suffix = id.substr(6);
									$("#fdtpid" + suffix).val(pid);
									$("#pdtnm" + suffix).val(pnm);
									$("#fdtdprc" + suffix).val(prc);
									
							}
						});
					}
			});
	//});
 }); 

	$("input[name=fdtqnt]").bind("blur",function(e){
		compute1(e);
	});
	
	$("input[name=fdtpid]").bind("click",function(e){
     clear(e);
	}).bind("select",function(e){
		clear(e);
	});

	function commit(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		var chk="";
		/* $("input[name='chk']:checkbox").each(function () {   
			if ($(this).attr("checked")) {   
				chk+=$(this).val()+',';
			}
		
		}); */
		
		 $('input[type="checkbox"][name="chk"]:checked').each(
	                function() {
	                      chk=chk+","+ $(this).val();
	                }
	            );

    obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=modifyNom'
				+'&tp=c&tp2=m&chk="/>'+chk+"&"
				+getAlldata(document.all.tableform); 
				
	if (confirm("确实要录入订单并提交吗？")) {
		obj.submit();
	}
};

function save(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	t = preCheckForBatch();//对必录项校验
	if (!t) {
		return t;
	}
	var chk="";
	 $('input[type="checkbox"][name="chk"]:checked').each(
                function() {
                      chk=chk+","+ $(this).val();
                }
            );
	obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=modifyNom'
			+'&tp=b&tp2=m&chk="/>'+chk+"&"
			+getAlldata(document.all.tableform);
	if (confirm("确实要修改并保存订单吗？")) {
		obj.submit();
	}
}

function compute1(e) {
	var id = $(e.target).attr("id");
	  var suffix = id.substr(6); 
/* 	var id=id.split("-");
	var suffix=id[1];  */
	var value = $("#fdtdprc" + suffix).val()*$("#fdtdisc" + suffix).val()*$("#fdtqnt" + suffix).val();
	$("#fdtprc" + suffix).val(value);
};
function compute2(e) {
	var id = $(e.target).attr("id");  //拿到控件id值id='fdtpid_row1'
	var suffix = id.substr(6); 
	/* var id=id.split("-");
	var suffix=id[1]; */
	var value = $("#fdtprc" + suffix).val()/$("#fdtqnt" + suffix).val()/$("#fdtdprc" + suffix).val();
	//value = changeTwoDecimal(value);
	//value = value.toString().substring(0, value.toString().indexOf(".")+ 3);
	value = value.toFixed(2);//保留两位有效数字
	$("#fdtdisc" + suffix).val(value);
	
};	


function clear(e) {
	var id = $(e.target).attr("id");  //拿到控件id值id='fdtpid_row1'
	var suffix = id.substr(6); 
	$("#fdtqnt" + suffix).val('');
	$("#fdtprc" + suffix).val('');
};
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
				+ "/order/OrderAction.do?method=saveNomOrder&"
				+ getAlldata(document.forms[0]);
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
	}
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="订单修改明细" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			 <tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
<%-- 				<lemis:texteditor property="folctid" label="客户代码" disable="true" /> 
			<lemis:texteditor property="folctnm" label="所属团体" disable="true" />  --%>
		
			</tr>
			<tr>
				
				<%-- <td>所属团体</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" property="foldt" label="订货日期"
					required="false" disable="true" />
				<td>订货日期</td>
					<td><lemis:operdate />
					<td>经办人</td>
				<td><lemis:operator /></td> --%>
				<lemis:texteditor property="gctnm" label="申请单位" disable="true" required="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="订货日期"></lemis:texteditor>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细录入"
		action="/OrderAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>