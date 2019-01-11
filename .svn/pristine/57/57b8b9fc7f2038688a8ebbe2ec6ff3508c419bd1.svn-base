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
	header.add(new Formatter("ictid", "用户编号"));
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("ictgctnm", "所属团体"));
	header.add(new Formatter("ictpnm", "家长姓名"));
	header.add(new Formatter("ictgender", "性别"));
	header.add(new Formatter("ictbdt", "出生日期"));
	header.add(new Formatter("ictaddr", "联系地址", true));
	header.add(new Formatter("ictpcd", "邮政编码"));
	header.add(new Formatter("icttel", "联系电话"));
	header.add(new Formatter("ictphis", "使用过何种助听器"));
	header.add(new Formatter("ictsrc", "来源"));
	header.add(new Formatter("ictnt", "备注"));
	header.add(new Formatter("ictdate", "登记日期"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("定制查询 ","dingzhi(document.all.tableform)");
	buttons.put("返 回", "history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "用户编号");
	hidden.put("ictnm", "用户姓名");
	hidden.put("ictgctid", "所属团体");
	
/* 	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ictnm", "用户姓名"));
	editors.add(new Editor("text", "ictgctid", "所属团体"));
	editors.add(new Editor("text", "ictsid", "机身编号")); */
	
	pageContext.setAttribute("hidden", hidden);
	//pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	//session.setAttribute("tableheader", "个人客户信息表");//表头
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

<script language="javascript">
	function dingzhi(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryCus&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>
				
<lemis:base />
<lemis:body>
	<lemis:title title="统计的个人客户信息" />
	<%-- <lemis:query action="/SingleClientAction.do?method=query"
		editorMeta="editor" topic="个人客户信息查询" />
	
	
		<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?"
			method="POST">
			<lemis:tabletitle title="所有个人客户信息" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="ivtyear" label="年份" required="false"
						disable="true" />
					<lemis:texteditor property="ictgctnm" label="所属团体" required="false"
						disable="true" />
					<lemis:texteditor property="ictgctid" label="所属团体代码" required="false"
						disable="true" />
				</tr>
			</table>
		</html:form>
	</table>	 --%>

	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="个人客户信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


