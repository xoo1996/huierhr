<!-- cfgmgmt/queryCI.jsp -->
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
	header.add(new Formatter("tmkfno", "订单号"));;
	header.add(new Formatter("tmksid", "定制机条形码"));
	header.add(new Formatter("tmkplr", "耳机类型"));
	header.add(new Formatter("tmkpnm","耳机名称"));
	header.add(new Formatter("tmkpst", "生产状态"));
	header.add(new Formatter("tmkurg", "是否加急"));
	//header.add(new Formatter("tmkpst", "定制机状态", "color:#FF0000;"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.add(new Button("printCI", "耳机项详细信息", "cfg010201", "detailci(document.all.tableform)"));
	buttons.put("开始质检", "test(document.all.tableform)");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmksid","定制机条形码");
	hidden.put("tmkfno","订单号");
	hidden.put("tmkpnm","耳机名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tmksid", "定制机条形码"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	function test(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("确实要开始质检吗？")) {
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=test&"/>' + getAlldata(obj);
			obj.submit();
		} else
			return t;
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制机查询" />
	<lemis:query
		action="/CustomizationAction.do?method=query&order=test"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/CustomizationAction.do" headerMeta="header"
		topic="定制机信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


