<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修改","batchSubmit(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","window.history.back();");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtcltid","用户代码"));
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("pdtprc","原价"));
	header.add(new Formatter("fdtprc","售价"));
	header.add(new Formatter("fdtqnt","数量"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	//batchInput.add(new Editor("text","fdtcltid","病人代码"){});
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称"));
	batchInput.add(new Editor("nnnnn.nn","pdtprc","原价"));
	batchInput.add(new Editor("nnnnn.nn","fdtprc","售价"));
	batchInput.add(new Editor("text","fdtqnt","数量"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script language="javascript">
	function createQueryString(e) {
		var pID = $(e.target).val();
		var queryString = {
			productID : pID
		};
		return queryString;
	};

	function startRequest(e) {
		$.getJSON("/huiermis/order/OrderDetailAction.do?method=queryPdtName",
				createQueryString(e), function(data) {
					var id = $(e.target).attr("id");
					$("#pdtnm" + id.substr(6)).val(data[0].productName);
					$("#pdtprc" + id.substr(6)).val(data[0].price);
					$("#fdtprc" + id.substr(6)).val(data[0].price);
					$("#fdtqnt" + id.substr(6)).val(1);
				});
		/*$.post("/huiermis/order/OrderDetailAction.do?method=queryPdtName",
				createQueryString(e), function(data) {
					var id = $(e.target).attr("id");
					$("#pdtnm" + id.substr(6)).val(data);
				}, "JSON");*/
	};

	$(document).ready( function(e) {
		$("input[name=fdtpid]").bind("blur", function(e) {
			startRequest(e);
		});
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
				+ "/order/OrderDetailAction.do?method=batchModify&"
				+ getAlldata(document.all.tableform) + "fdtfno="
				+ $("input[name=folno]").val();
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="修改订单明细" />
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
				<lemis:texteditor property="oprnm" label="经办人" disable="true" />
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<lemis:texteditor property="foldt" label="订货日期" disable="true" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="修改订单明细"
		action="/OrderDetailAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


