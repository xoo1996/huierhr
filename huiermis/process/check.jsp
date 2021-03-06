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
	header.add(new Formatter("nemid", "流程ID",TagConstants.DF_CENTER));
	header.add(new Formatter("apatype", "流程类型",TagConstants.DF_CENTER));
	header.add(new Formatter("nemname", "申请人姓名",TagConstants.DF_CENTER));
	header.add(new Formatter("nemappdt", "申请时间",TagConstants.DF_CENTER));
	header.add(new Formatter("nemstate", "流程状态",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("审核流程记录","verifynem(document.all.tableform)");
	buttons.put("流程详情","detailapa(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("nemid", "流程ID");
	hidden.put("apatype", "流程类型");
	//hidden.put("nemstate", "流程状态");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "nemid", "流程ID"));
	editors.add(new Editor("text", "apatype", "流程类型"));
	editors.add(new Editor("text", "nemname", "申请人姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "归档流程查询");//表头

%>
<html>
	<script language="javascript">
		//审核流程
		function verifynem(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=query&"/>'+getAlldata(obj);			
			obj.submit();
			}	
		//显示详细信息
  		function detailapa(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=printApa&"/>'+getAlldata(obj);			
			obj.submit();
  		}
		
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="归档流程查询" />
		<lemis:query action="/ApaOperAction.do?method=queryApa&type=check" editorMeta="editor" topic="归档流程查询" />
		<lemis:table action="ApaOperAction.do" headerMeta="header" topic="流程信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


