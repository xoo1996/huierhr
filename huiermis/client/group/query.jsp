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
	header.add(new Formatter("gctid", "客户编号"));
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gcttype", "类型"));
	header.add(new Formatter("gctcnm", "联系人"));
	header.add(new Formatter("gcttel", "联系电话"));
	header.add(new Formatter("gctfax", "传真"));
	header.add(new Formatter("gctptcd", "邮编"));
	header.add(new Formatter("gctstart", "开业时间"));
	header.add(new Formatter("gctaddr", "联系地址", true));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("gctprovince", "省份"));
	header.add(new Formatter("gctsname", "全称", true));
	header.add(new Formatter("gctnt", "备注", true));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("个人客户详细信息","detailci(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctid", "客户ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "gctid", "客户编号"));
	editors.add(new Editor("text", "gctnm", "客户名称"));
	editors.add(new Editor("text","gcttype","客户类别"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);

%>
<html>
<script language="javascript">
	//显示详细信息
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/CIOperAction.do?method=printCI&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="团体客户查询" />
	<lemis:query action="/GroupClientAction.do?method=query"
		editorMeta="editor" topic="团体客户信息查询" />
	<lemis:table action="GroupClientAction.do" headerMeta="header"
		topic="团体客户信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


