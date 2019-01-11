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
	header.add(new Formatter("store", "惠耳点",TagConstants.DF_CENTER));
	header.add(new Formatter("useremployid", "人员id",TagConstants.DF_CENTER));
	header.add(new Formatter("yingfa", "应发工资",TagConstants.DF_CENTER));
	header.add(new Formatter("shifa", "实发工资",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	//buttons.put("新 建","verifyapa(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("useremployid", "人员id");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "useremployid", "人员id"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "工资表查询");//表头

%>
<html>
	<script language="javascript">
	
		//审核信息
  		function verifyapa(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=newApa&type=rest&"/>'+getAlldata(obj);			
			obj.submit();
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="工资表查询" />
		<lemis:query action="/ApaOperAction.do?method=queryApa&type=salary" editorMeta="editor" topic="员工信息查询" />
		<lemis:table action="ApaOperAction.do" headerMeta="header" topic="工资表信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


