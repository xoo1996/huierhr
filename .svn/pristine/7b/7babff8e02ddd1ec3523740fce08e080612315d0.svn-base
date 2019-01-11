<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("批量录入", "batchSubmit(document.all.tableform)");
	buttons.put("关 闭", "closeWindow(\"\")");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ivtcltnm", "个人客户"));
	header.add(new Formatter("ivtpdtid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "单价"));
	header.add(new Formatter("ivtlmqnt", "结存数"));
	header.add(new Formatter("ivtlsqnt", "补入数"));
	header.add(new Formatter("temp01", "小计"));
	header.add(new Formatter("ivtpqnt", "回款数"));
	header.add(new Formatter("ivtdiscount", "扣率"));
	header.add(new Formatter("ivtpamnt", "应收款"));
	header.add(new Formatter("temp02", "月结存", TagConstants.DF_RIGHT));
	header.add(new Formatter("ivtnote", "添加备注"));

	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("money", "pdtprc", "单价", true));
	batchInput.add(new Editor("nnnn", "ivtpqnt", "本月回款数", "true"));
	batchInput.add(new Editor("text", "ivtdiscount", "折扣率"));
	batchInput.add(new Editor("money", "ivtpamnt", "应收款", "true"));
	batchInput.add(new Editor("text", "ivtnote", "备注"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtid", "id");

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput", batchInput);
	pageContext.setAttribute("header", header);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script language="javascript">
	function compute(e) {
		var id = $(e.target).attr("id");
		var suffix = id.substr(11);
		var discount;
		if ($("#ivtdiscount" + suffix).val() == "") {
			discount = 1;
			$("#ivtdiscount" + suffix).val("1.0");
		} else {
			discount = $("#ivtdiscount" + suffix).val();
		}
		var value = $("#pdtprc" + suffix).val() * $("#ivtpqnt" + suffix).val()
				* discount;
		$("#ivtpamnt" + suffix).val(value);
	};

	$(document).ready(function(e) {
		$("input[name=ivtdiscount]").bind("blur", function(e) {
			compute(e);
		});
	});

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
				+ "/business/BusinessAction.do?method=batchSubmit&"
				+ getAlldata(document.all.tableform);
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="月度结帐" />
	<lemis:tabletitle title="月报表基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ivtgcltid" label="客户代码" disable="true" />
				<lemis:texteditor property="ivtyear" label="年份" disable="true" />
				<lemis:texteditor property="ivtmonth" label="月份" disable="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="月报表明细" action="/BusinessAction.do"
		headerMeta="header" mode="checkbox" batchInputMeta="batchInput"
		hiddenMeta="hidden" orderResult="true" batchInputType="update"
		pilot="true" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


