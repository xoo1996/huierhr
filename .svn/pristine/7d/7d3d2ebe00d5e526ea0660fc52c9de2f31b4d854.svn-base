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
	header.add(new Formatter("tmkcltid", "用户代码"));
	header.add(new Formatter("tmkcltnm", "用户姓名"));
	header.add(new Formatter("tmkgctnm", "所属团体"));
	header.add(new Formatter("tmkfno", "订单号"));
	header.add(new Formatter("tmkpnm", "助听器名称"));
	header.add(new Formatter("tmksid", "机身编号"));
	header.add(new Formatter("tmkplr", "耳机类型"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("查看用户详情","userDetail(document.all.tableform)");
	buttons.put("查看维修历史","repairHistory(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkcltid", "用户代码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkgctid", "所属团体代码");
	hidden.put("tmkgctnm", "所属团体名称");
	hidden.put("tmkfno", "订单号");
	hidden.put("tmkpid", "耳机代码");
	hidden.put("tmkpnm", "耳机名称");
	hidden.put("tmksid", "机身编号");
	
	hidden.put("ictid","用户代码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","repcltnm","用户姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	function userDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite href="../client/SingleClientAction.do?method=view&"/>' + getAlldata(obj);
		obj.submit();
	};
	function repairHistory(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite href="../customization/CustomizationAction.do?method=queryHistory&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制记录查询" />
	<lemis:query action="/RepairAction.do?method=queryCus"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="定制记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


