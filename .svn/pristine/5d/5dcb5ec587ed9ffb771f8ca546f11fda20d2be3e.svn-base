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
	//header.add(new Formatter("fctctid", "用户编号"));
	//header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("fctcdt", "复诊日期"));
	header.add(new Formatter("fctreason", "复诊原因"));
	header.add(new Formatter("fctdgn", "检查情况"));
	header.add(new Formatter("fcttmt", "处理意见"));
	header.add(new Formatter("fctdoc", "医生"));
	header.add(new Formatter("fctnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增","newSC(document.all.tableform)");
	buttons.put("删除","deleteSC(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fctctid", "用户编号");
	hidden.put("fctcdt", "复诊日期");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "个人客户复诊信息表");//表头
%>
<html>
<script language="javascript">
	function newSC(obj) {
		//var t = editObj("chk");
		//if(!t){
		//	return t;
		//}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=enterAddSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function deleteSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=deleteSC&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="用户复诊查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="用户编号" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false"
					disable="true" />
				<lemis:texteditor property="ictgctid" label="所属团体" required="false"
					disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="用户复诊信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


