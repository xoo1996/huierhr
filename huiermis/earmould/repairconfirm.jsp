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
	header.add(new Formatter("repcpy", "维修厂商"));
	header.add(new Formatter("repamt", "维修费", TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("录入维修措施","input(document.all.tableform)");
	buttons.put("查看","detailci(document.all.tableform)");
	buttons.put("维修完成","finish(document.all.tableform)");
	buttons.put("不维修","stop(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "维修流水号");
	hidden.put("repid", "产品编号");
	hidden.put("reppid", "产品ID");
	hidden.put("repamt", "维修费");
	hidden.put("repfolid", "订单号");
	hidden.put("repcpy", "维修厂商");
	hidden.put("repcltnm", "用户姓名");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","订单号"));
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","repcltnm", "用户姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=repid]:first").focus();
		$("input[name=repid]:first").val("");
		$("input[name=repid]").blur(function() {
			if ($("input[name=repid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	//显示详细信息
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=print&"/>' + getAlldata(obj);
		obj.submit();
	};
	function input(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=repair&require=repair&"/>' + getAlldata(obj);
		obj.submit();
	};
	function finish(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("确实要确认维修完成吗？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=change&require=finish&tp=ear&"/>' + getAlldata(obj);
			obj.submit();
		} else
			return t;
	};
	function stop(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("不维修并取消订单？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=change&require=stop&tp=ear&"/>' + getAlldata(obj);
			obj.submit();
		}
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:query action="/RepairAction.do?method=query&order=repconfirm"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


