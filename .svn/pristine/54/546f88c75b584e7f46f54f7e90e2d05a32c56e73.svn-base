<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("pdtid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtcls", "耳机类别"));
	header.add(new Formatter("pdttype", "助听器品牌"));
	header.add(new Formatter("pdtprc", "单价"));
	header.add(new Formatter("discount", "扣率"));
	header.add(new Formatter("disprice", "成本价"));
	header.add(new Formatter("pdtnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("添加扣率","add(document.all.tableform)");
	buttons.put("进入修改","modify(document.all.tableform)");
	buttons.put("批量修改","batchmd(document.all.tableform)");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pdtid","商品代码");
	hidden.put("pdtcls", "耳机类别");
	hidden.put("pdttype", "助听器品牌");
	hidden.put("pdtprc", "耳机单价");
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtprc","单价"));
	batchInput.add(new Editor("text","discount","扣率"));
	batchInput.add(new Editor("money","disprice","成本价"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "pdtid", "商品代码"));
	editors.add(new Editor("text", "pdtnm", "商品名称"));
	editors.add(new Editor("text", "pdtp", "产品类别"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	
%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function compute(e) {
		var id = $(e.target).attr("id");
		var suffix = id.substr(8);
		var value = $("#pdtprc" + suffix).val() * $("#discount" + suffix).val();
		$("#disprice" + suffix).val(value);
	};

	$(document).ready(function(e) {
		$("input[name=discount]").bind("blur", function(e) {
			compute(e);
		});
		$("input[name=pdtprc]").attr('disabled', 'disabled');
	});

	function modify(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=editdis&"/>' + getAlldata(obj);
		obj.submit();
	};
	function batchmd(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/product/ProductAction.do?method=add&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="商品扣率维护" />
	<lemis:query action="/ProductAction.do?method=disquery&id=pdis"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="产品信息" hiddenMeta="hidden" mode="checkbox"
		batchInputMeta="batchInput" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>