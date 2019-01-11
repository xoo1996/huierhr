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
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","审核","clt020201","examine(document.all.tableform)")); //tableform\orderHeaderForm
buttons.add(new Button("jcdz","回退","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","返回","clt020203","history.back()"));

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtdprc","价格",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	header.add(new Formatter("fdtinnt","备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("text","fdtdprc","价格","true"));
	batchInput.add(new Editor("text","fdtqnt","数量","true"));
	batchInput.add(new Editor("text","fdtinnt","备注","false"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folno","订单号");
	hidden.put("foltype","订单类型");
	hidden.put("folsta","订单状态");
	hidden.put("gctnm","团体客户");
	hidden.put("foldt","订货日期");
	hidden.put("folsta","订单状态");
	
	
	pageContext.setAttribute("hidden", hidden);
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
	<%-- 	var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
         
		 $("input[name=chk]").attr("checked",true);
		
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
			}
		}); --%>
	 $("input[name=fdtdprc]").attr('readonly','readonly');
	 $("input[name=pdtnm]").attr('readonly','readonly'); 
	 $(":checkbox:enabled").attr('checked', true);
// 	 $('input[type="checkbox"][name="chk"]').each(
//              function(i) {
//             	 var fdtpid=$("input[name=fdtpid]").val();
//             	 alert(i);
//             	 var fdtpid=$("#fdtpid_row" + (i+1)).val();
//             	 alert(fdtpid);
//             	 if(fdtpid!='')
//             	 {
//             		 $("input[name=chk][value="+i+"]").attr("checked",true);
//             		 alert(fdtpid);
//             	 }
//             	 else if(fdtpid == ''){
//             		 $("input[name=chk][value="+i+"]").attr("checked",false);
//             		 alert("success");
//             	 }
            	
//              }
//          );
	/*  var id = $("input[name=fdtpid]").attr("id");
		var suffix = id.substr(6);
		$("#subCheckbox" + suffix).attr("checked",true); */
		
	
	/*  function createQueryString(e) {
			var eID = $(e.target).val();
			var queryString = {
				EarId : eID
			};
			return queryString;
		};
		function startRequest(e) {
			$.getJSON("/huiermis/order/OrderAction.do?method=queryMinSto",
					createQueryString(e), function(data) {
						$("input[name=tmeprc]").val(data[0].price);
					});
		}; */
	
 });
 
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&folType=nom&type=o&folsta="/>' + $('input:hidden[name=folsta]').val();
        if(confirm("确定要回退订单吗？"))
        {
		   obj.submit();
        }
	};
	
	function examine(obj){
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=e&folType=nom&type=o"/>';
		if(confirm("确定订单审核通过吗？"))
        {
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
	<lemis:title title="订单明细" />
	<html:form action="/OrderAction.do?method=examineOrder&tp1=e&folType=nom&type=o" method="POST">
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />

			 <tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<%--<lemis:texteditor property="folctid" label="客户代码" disable="true" /> 
			<lemis:texteditor property="folctnm" label="所属团体" disable="true" /> --%>
		       <html:hidden property="foltype"/>
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
				<lemis:texteditor property="gctnm" label="申请单位" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="订货日期"></lemis:texteditor>
			</tr>
	</table>

	<lemis:table topic="订单明细录入"
		action="/OrderAction.do?method=batchSubmit" headerMeta="header" hiddenMeta="hidden"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
	</html:form>
</lemis:body>
</html>