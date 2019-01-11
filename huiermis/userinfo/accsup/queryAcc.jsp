<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("taraccount", "账号",TagConstants.DF_CENTER));
	header.add(new Formatter("bsc012", "账号名称",TagConstants.DF_CENTER));
	header.add(new Formatter("useremployid", "上级工号",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "员工姓名",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("绑 定","bind(document.all.tableform)");
	buttons.put("解 绑","disbind(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("taraccount", "账号");
	hidden.put("useremployid", "上级工号");
	hidden.put("bsc012", "账号名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "账号"));
	editors.add(new Editor("text", "useremployid", "上级工号"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "账号-上级工号绑定");//表头

%>
<html>
	<script language="javascript">
	
		//绑定信息
  		function bind(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/UserInfoAction.do?method=tobindAccSup&"/>'+getAlldata(obj);			
			obj.submit();
  		}
  	//绑定信息
  		function disbind(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要解绑该账号-上级工号吗？")){
				obj.action = '<html:rewrite page="/UserInfoAction.do?method=disbindAccSup&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="账号-上级工号绑定" />
		<lemis:query action="/UserInfoAction.do?method=queryAccSup&tp=acc" editorMeta="editor" topic="账号信息查询" />
		<lemis:table action="UserInfoAction.do" headerMeta="header" topic="账号-上级工号信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


