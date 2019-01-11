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
	header.add(new Formatter("tmkfno", "订单号"));;
	header.add(new Formatter("tmksid", "定制机条形码"));
	header.add(new Formatter("tmkplr", "耳机类型"));
	header.add(new Formatter("tmkpnl","面板编码"));
	header.add(new Formatter("pdtnm","面板名称"));
	header.add(new Formatter("tmkpst", "生产状态"));
	header.add(new Formatter("tmkurg", "是否加急"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("开始制作外壳","make(document.all.tableform)");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmksid","定制机条形码");
	hidden.put("tmkfno","订单号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmksid","定制机条形码"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=tmksid]").focus();
		$("input[name=tmksid]").blur(function(){
			$("#queryForm").submit();
			//make(document.all.tableform);
		});
	});
	//制作外壳
	function make(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		//if (confirm("确实要开始制作外壳吗？")) {
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=changeStatus&require=startMake&"/>' + getAlldata(obj);
			obj.submit();
		//} else
		//	return t;
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制机查询" />
	<lemis:query action="/CustomizationAction.do?method=query&order=make"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/CustomizationAction.do" headerMeta="header"
		topic="定制机信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


