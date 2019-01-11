<!--sysmanager/log/Sc11List.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			QueryInfo qi = (QueryInfo) pageContext.findAttribute(GlobalNames.QUERY_INFO);
			String stringData = "";
			if(qi!=null)
			stringData = qi.getStringData();
			
			List header = new ArrayList();
			header.add(new Formatter("sc11.bsc030", "流水号"));
			header.add(new Formatter("sc11.bsc011", "用户名称"));
			header.add(new Formatter("sc11.bsc012", "登陆名称"));
			header.add(new Formatter("sc11.bsc026", "操作ip"));
			header.add(new Formatter("sc11.bsc031", "操作交易"));
			//header.add(new Formatter("sc11.bsc047", "菜单编号"));
			//header.add(new Formatter("sc11.bsc033", "菜单名称"));
			header.add(new Formatter("sc11.bsc027", "操作时间"));
			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc012", "用户名称"));
			editors.add(new Editor("text", "bsc026", "操作的ip"));
			editors.add(new Editor("date", "bsc027", "操作时间"));
			//editors.add(new Editor("text", "bsc047", "菜单编号"));
			//editors.add(new Editor("text", "bsc033", "菜单名称"));
			Map hidden = new LinkedHashMap();
			hidden.put("bsc030", "编号");
			
			Map<String, String> buttons = new LinkedHashMap<String, String>();
			buttons.put("数据迁移","trans(document.forms[0])");
			
			pageContext.setAttribute("button", buttons);
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			session.setAttribute("tableheader", "操作日志信息表");
%>
<html>
<lemis:base />
	<lemis:body>
		<lemis:title title="操作信息记录" />
		<lemis:query action="/logsc11Action.do?method=FindSc11List"	editorMeta="editor" hiddenMeta="queryHiddens" topic="操作信息查询" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="logsc11Action.do" headerMeta="header" topic="操作信息列表" hiddenMeta="hidden"  />
		<lemis:buttons buttonMeta="button" />   
		
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
    <lemis:clean names="sc11Form" />
</html>
<script language="javascript">
function trans() {
	if(confirm("是否迁移数据？")){
		  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/logsc11Action.do?method=trans&flag=oper";
		}else{
	    	return false;
	  	}
};

</script>

