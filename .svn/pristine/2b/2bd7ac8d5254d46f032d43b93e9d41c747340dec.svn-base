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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("qafno", "订单号"));
	header.add(new Formatter("qacltnm", "用户姓名"));
	header.add(new Formatter("qasid", "机身编号"));
	header.add(new Formatter("qapnm", "助听器型号"));
	header.add(new Formatter("qatype", "质检类别"));
	header.add(new Formatter("qastatus", "质检状态"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("质检步骤1","test1before(document.all.tableform)");
	buttons.put("质检步骤2","test(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("qaid", "质检流水号");
	hidden.put("qafno", "订单号");
	hidden.put("qasid", "机身编号");
	hidden.put("qapnm", "助听器型号");
	hidden.put("qacltnm", "用户姓名");
	hidden.put("qatype", "质检类别");
	hidden.put("qatest1","外观");
	hidden.put("qatest2","饱和声输出");
	hidden.put("qatest3","最大值增益");
	hidden.put("qatest4","1600Hz增益");
	hidden.put("qatest5","频率响应范围");
	hidden.put("qatest6","等效输入噪声");
	hidden.put("qatest7","电池电流");
	hidden.put("qatest8","总谐波失真(500Hz)");
	hidden.put("qatest9","总谐波失真(800Hz)");
	hidden.put("qatest10","总谐波失真(1600Hz)");
	hidden.put("qachk","检验结果");
	hidden.put("qapid","商品代码");
	hidden.put("pdttype","商品品牌类型");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","qafno","订单号"));
	editors.add(new Editor("text","qasid","机身编号"));
	editors.add(new Editor("text","qacltnm", "用户姓名"));
	editors.add(new Editor("text","qatype", "质检类别"));
	editors.add(new Editor("text","pdtcls","耳机类别"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=qasid]:first").focus();
		$("input[name=qasid]:first").val("");
		$("input[name=qasid]").blur(function() {
			if ($("input[name=qasid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function test1before(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/QAAction.do?method=test1before&"/>' + getAlldata(obj);
		obj.submit();
	}
	function test(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/QAAction.do?method=test&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="质检" />
	<lemis:query action="/QAAction.do?method=query&order=test"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="QAAction.do" headerMeta="header" topic="等待质检记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


