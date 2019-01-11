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
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("pdtnm", "耳模名称"));
	header.add(new Formatter("tmeappear","外观"));
	header.add(new Formatter("tmeden", "气密性"));
	header.add(new Formatter("tmeckt", "质检结果"));
	header.add(new Formatter("tmesta", "生产状态"));
	header.add(new Formatter("tmemat", "耳模类型"));
	header.add(new Formatter("tmegif", "类别"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("确认完成", "completeProduce(document.all.tableform)");
	buttons.put("关 闭", "closeWindow(\"\")");
    
	Map<String,String> hidden=new LinkedHashMap<String,String>();
	hidden.put("tmeno","订单号");
	hidden.put("tmecltid","病人代码");
	hidden.put("tmepid","产品代码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmecltnm","用户姓名"));
	editors.add(new Editor("text","tmeno","订单号"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	function completeProduce() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确认已完成质检并完工吗？")) {
			window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/earmould/EarMouldAction.do?method=completeall&"
					+ getAlldata(document.all.tableform);
		}
		else
			return t;
	}
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="耳模查询" />
	<lemis:query
		action="/EarMouldAction.do?method=query&order=completeall"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/EarMouldAction.do" headerMeta="header"
		topic="耳模信息" hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>