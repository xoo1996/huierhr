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
	header.add(new Formatter("pdtprc", "耳机单价"));
	header.add(new Formatter("pdtdiscount", "商品扣率"));
	header.add(new Formatter("pdtnt", "备注"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("批量保存","batchSubmit(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pdtid","商品代码");
	
	List<Editor> batchupdate = new ArrayList<Editor>();
	batchupdate.add(new Editor("text","pdtdiscount","商品扣率"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "pdtid", "商品代码"));
	editors.add(new Editor("text", "pdtnm", "商品名称"));
	editors.add(new Editor("text", "pdtp", "产品类别"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchupdate", batchupdate);
%>
<html>
<script language="javascript">
function batchSubmit() {
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/product/ProductAction.do?method=batchSubmit&"
			+ getAlldata(document.all.tableform);
   };
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="商品扣率管理" />
	<lemis:query action="/ProductAction.do?method=disquery&id=updis"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/ProductAction.do" headerMeta="header" topic="产品信息" hiddenMeta="hidden" 
	    mode="radio" batchInputMeta="batchupdate" mode="checkbox" batchInputType="update"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


