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
	header.add(new Formatter("repfolid", "订单号"));
	header.add(new Formatter("repgctnm", "客户名称"));
	header.add(new Formatter("repcltnm", "用户姓名"));
	header.add(new Formatter("repid", "机身编号"));
	header.add(new Formatter("reppnm", "产品名称"));
	header.add(new Formatter("repdate", "送修日期"));
	header.add(new Formatter("repgctid", "送修单位"));
	header.add(new Formatter("reppdate", "计划完工"));
	header.add(new Formatter("repsta", "维修状态"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("开始制作外壳","makeshell(document.all.tableform)");
	buttons.put("开始维修","repair(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "维修流水号");
	hidden.put("reppnm", "助听器型号");
	hidden.put("repfolid", "订单号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","订单号"));
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","repcltnm","用户姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
		$("input[name=repid]:first").focus();
		$("input[name=repid]:first").val("");
		$("input[name=repid]").blur(function() {
			if ($("input[name=repid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function repair(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确实要开始维修吗？")) {
			window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/repair/RepairAction.do?method=batchChange&"
					+ getAlldata(document.all.tableform)
					+ "require=startRepair";
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
	}
	function makeshell(obj){
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确实要开始制作外壳吗？")) {
			window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/repair/RepairAction.do?method=batchMakeshell&"
			+ getAlldata(document.all.tableform)
			+ "require=startRepair";
		}
		else
			return	t;
		
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:query action="/RepairAction.do?method=query&order=repair"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


