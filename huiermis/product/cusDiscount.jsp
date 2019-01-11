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
	/* header.add(new Formatter("pdtid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtcls", "耳机类别")); */
	header.add(new Formatter("tdsnm", "助听器品牌"));
	header.add(new Formatter("tdspvoin", "省内扣率"));
	header.add(new Formatter("tdspvoout", "省外扣率"));
	header.add(new Formatter("tdsnt", "备注"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("明细","detail(document.all.tableform)");
	buttons.put("批量修改","batchmd(document.all.tableform)");
	 /*buttons.put("删除","del(document.all.tableform)"); */
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tdsnm","助听器品牌");
	hidden.put("pdttype","助听器品牌");
	
	List<Editor> editors = new ArrayList<Editor>();
	/* editors.add(new Editor("text", "pdtid", "商品代码"));
	editors.add(new Editor("text", "pdtnm", "商品名称")); */
	editors.add(new Editor("text", "pdttype", "助听器品牌"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text", "tdspvoin", "省内扣率"));
	batchInput.add(new Editor("text", "tdspvoout", "省外扣率"));
	batchInput.add(new Editor("text", "tdsnt", "备注"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("batchInput", batchInput);
	pageContext.setAttribute("button", buttons);
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>


<script language="javascript">
	
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
			+ "/product/ProductAction.do?method=batchModTypDis&"
			+ getAlldata(document.all.tableform);
};

	function detail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=queryProByTp&"/>' + getAlldata(obj);
		obj.submit();
	};
	function del(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=delete&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>



<lemis:base />
<lemis:body>
	<lemis:title title="助听器品牌最小扣率管理" />
	<lemis:query action="/ProductAction.do?method=query&mark=typDis"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="品牌最小扣率信息" hiddenMeta="hidden" mode="checkbox" batchInputMeta="batchInput"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


