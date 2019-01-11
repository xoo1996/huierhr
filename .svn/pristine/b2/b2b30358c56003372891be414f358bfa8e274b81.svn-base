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
	header.add(new Formatter("tmeno", "订单号"));
	header.add(new Formatter("tmegctnm","客户名称"));
	header.add(new Formatter("tmesid", "耳模编号"));
	header.add(new Formatter("tmecltnm", "用户姓名"));
	header.add(new Formatter("pdtnm", "耳模名称"));
	header.add(new Formatter("tmesta", "生产状态"));
	header.add(new Formatter("tmepdt","计划完成"));
	header.add(new Formatter("tmemat", "耳模类型"));
	header.add(new Formatter("tmefree", "是否赠送"));
	header.add(new Formatter("tmecls", "类别"));
	header.add(new Formatter("tmeurgent","是否加急"));
	header.add(new Formatter("tment", "备注"));

	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("确认制作完成", "batchConfirm(document.all.tableform)");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmeno", "订单号");
	hidden.put("tmesid", "耳模编号");
	hidden.put("tmecltnm","用户姓名");

	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "tmecltnm", "用户姓名"));
	editors.add(new Editor("text", "tmeno", "订单号"));
	editors.add(new Editor("text", "tmesid", "耳模编号"));
	editors.add(new Editor("text","tmecltnm","用户姓名"));

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
		$("input[name=tmesid]:first").focus();
		$("input[name=tmesid]:first").val("");
		$("input[name=tmeno]:first").val("");
		$("input[name=tmecltnm]:first").val("");
		$("input[name=tmesid]").blur(function() {
			if ($("input[name=tmesid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function batchConfirm() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确认已制作完该耳模吗？")) {
			window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/earmould/EarMouldAction.do?method=complete&"
					+ getAlldata(document.all.tableform);
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
	};
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="耳模查询" />
	<lemis:query action="/EarMouldAction.do?method=query&order=confirm"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/EarMouldAction.do" headerMeta="header"
		topic="耳模信息" hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>