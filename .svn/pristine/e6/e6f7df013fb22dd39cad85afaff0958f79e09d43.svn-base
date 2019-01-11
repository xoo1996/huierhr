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

//	List<Button> buttons=new ArrayList<Button>();
//	buttons.add(new Button("back","返回","ord020003","history.back()"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
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
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
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
 $("input[name=fdtdprc]").attr('readonly','readonly');
	/* $("input[name=pdtnm]").attr('readonly','readonly'); */
	
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
	$("input[name=fdtqnt]").bind("blur",function(e){
		compute1(e);
	});
/* 	.bind("blur",function(e){
		compute2(e);
	}); */
	
	/* $("input[name=fdtprc]").bind("blur",function(e){
		compute2(e);
	}); */
 });
 
	function commit(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=c&tp2=s&"/>'+getAlldata(document.all.tableform);
	if (confirm("确实要录入订单并提交吗？")) {
		obj.submit();
	}
};

function save(obj) {
	obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=b&tp2=s&"/>'+getAlldata(document.all.tableform);
	if (confirm("确实要录入订单吗？")) {
		obj.submit();
	}
}
	
		
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
	<lemis:title title="订单明细录入" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
			<%-- 	<lemis:texteditor property="folctid" label="客户代码" disable="true" /> 
			<lemis:texteditor property="folctnm" label="所属团体" disable="true" />  --%>
		
			</tr>
			<tr>
				
			<%-- 	<td>所属团体</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" property="foldt" label="订货日期"
					required="false" disable="true" />
				<td>订货日期</td>
					<td><lemis:operdate />
					<td>经办人</td>
				<td><lemis:operator /></td> --%>
				<lemis:texteditor property="gctnm" label="申请单位" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="订货日期"></lemis:texteditor>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细录入"
		action="/OrderAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>