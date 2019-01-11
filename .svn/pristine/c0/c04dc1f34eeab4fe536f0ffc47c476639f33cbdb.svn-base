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
   // String pdtid = (String)request.getSession().getAttribute("pdtid");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("disgctnm", "客户名称"));
	header.add(new Formatter("dispdtid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtcls", "耳机类别"));
	header.add(new Formatter("pdttype", "助听器品牌"));
	header.add(new Formatter("pdtprc", "单价"));
	header.add(new Formatter("discount", "扣率"));
	header.add(new Formatter("disprice", "成本价"));
	header.add(new Formatter("disnote", "备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtprc","单价"));
	batchInput.add(new Editor("text", "discount", "扣率"));
	batchInput.add(new Editor("nnnnn.nn", "disprice", "成本价"));
	batchInput.add(new Editor("text", "disnote", "备注"));

	List<Editor> editor = new ArrayList<Editor>();
	editor.add(new Editor("text","disgctid","客户代码"));
	editor.add(new Editor("text","gcttype","客户类型"));
	editor.add(new Editor("text","pdtid","商品代码"));
	
	Map<String,String> hidden=new LinkedHashMap<String,String>();
	hidden.put("dispdtid","产品代码");
	hidden.put("disgctid","客户代码");
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("批量保存","saveData(document.all.tableform)");
	buttons.put("返回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
	
    pageContext.setAttribute("button", buttons);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("batchInput", batchInput);
    pageContext.setAttribute("hidden", hidden);
    pageContext.setAttribute("editor", editor);
    
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
function saveData() {
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	t = preCheckForBatch();//对必录项校验
	if (!t) {
		return t;
	}
	window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/product/ProductAction.do?method=disbatchSubmit&"
			+ getAlldata(document.all.tableform);
}
</script>
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
		
		$("input[name=pdtprc]").attr('disabled','disabled');
		
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=disgctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=disgctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=disgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
    <lemis:title title="商品扣率添加" />
   <lemis:query action="/ProductAction.do?method=alterquery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header" topic="产品信息" hiddenMeta="hidden" 
	     batchInputMeta="batchInput" mode="checkbox" batchInputType="update"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>