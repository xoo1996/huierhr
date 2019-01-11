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
	header.add(new Formatter("useremployid", "员工工号",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "员工姓名",TagConstants.DF_CENTER));
	header.add(new Formatter("bsc009", "所属部门",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("绑 定","bind(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("useremployid", "员工工号");
	hidden.put("username", "员工姓名");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "账号"));
	editors.add(new Editor("text", "useremployid", "员工工号"));
	editors.add(new Editor("text", "username", "员工姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "账号-上级工号绑定");//表头

%>
<html>
	<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src="/huiermis/js/gbk.js"></script>
<script language="javascript">
	
		//绑定信息
  		function bind(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			var acc = $("input[name='taraccount']").val();
			if(confirm("确定要绑定该账号-上级工号吗？")){
				obj.action = '<html:rewrite page="/UserInfoAction.do?method=bindAccSup&"/>'+ "acc=" + acc+"&"+getAlldata(obj);			
				obj.submit();
			}
  		}
  		
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="账号-上级工号绑定" />
		<lemis:query action="/UserInfoAction.do?method=queryAccSup&tp=sup" editorMeta="editor" topic="员工信息查询" />
		<lemis:table action="UserInfoAction.do" headerMeta="header" topic="员工信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


