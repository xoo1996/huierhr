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

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%  

    Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("批量保存","batchSubmit(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "团体客户"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "商品单价"));
	header.add(new Formatter("temp01", "数量"));
	header.add(new Formatter("temp02", "销售额"));
	header.add(new Formatter("discount", "扣率"));
	header.add(new Formatter("ivtpamnt", "成本价"));

	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text", "pdtprc", "商品单价"));
	batchInput.add(new Editor("text", "discount", "折扣率"));
	batchInput.add(new Editor("text", "ivtpamnt", "成本价"));
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtpdtid", "产品ID");
	hidden.put("ivtgcltid", "团体客户ID");
	hidden.put("ivttype","类型");
	hidden.put("ivtid","月结id");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "年","true"));
	editors.add(new Editor("text", "ivtmonth", "月","true"));
	editors.add(new Editor("text", "ivtgcltid", "团体代码","true"));

	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("batchInput", batchInput);
%>

<script language="javascript">	

	function compute(e) {
		var id = $(e.target).attr("id");
		var suffix = id.substr(8);
		var value = $("#pdtprc" + suffix).val() * $("#discount" + suffix).val();
		$("#ivtpamnt" + suffix).val(value);
	};
	
	 $(document).ready(function(e) {
			$("input[name=ivtpamnt]").bind("click", function(e) {
				compute(e);
			});
			$("input[name=pdtprc]").attr('disabled', 'disabled');
			
			var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
			
			$("input[name=ivtgcltid]").autocomplete(shops,{
				max : 10,
				matchContains : true
			});
			$("input[name=ivtgcltid]").result(function(event, data, formatted) {
				if (data){
					var gnm = data[0].substring(data[0].indexOf("=")+1);
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=ivtgcltid]").val(gid);
					$(this).parent().next().find("input").val(gid);
				}
			});
			
	});


	function batchSubmit(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=batchdis"/>';
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="添加成本价" />
	<lemis:query action="/BusinessAction.do?method=yuedisquery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		batchInputMeta="batchInput" topic="账单明细" hiddenMeta="hidden"
		mode="checkbox" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
