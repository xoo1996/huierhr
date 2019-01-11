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
	header.add(new Formatter("supname", "待审批人",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("详 情","detailapa(document.all.tableform)");
	buttons.put("修 改", "modifyapa(document.all.tableform)");
	buttons.put("删 除", "deleteapa(document.all.tableform)");
	buttons.put("审核流程","verifynem(document.all.tableform)");
	buttons.put("离职交接","leavenem(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("nemid", "流程ID");
	hidden.put("apatype", "流程类型");
	hidden.put("nemstate", "流程状态");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "nemid", "流程ID"));
	editors.add(new Editor("text", "apatype", "流程类型"));
	editors.add(new Editor("text", "nemstate", "流程状态"));
	editors.add(new Editor("text", "nemname", "申请人姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "流程审批进度查询");//表头

%>
<html>
<script src="/huiermis/js/jquery.mobile-1.4.5.js"></script>
	<script language="javascript">
		//离职交接
		function leavenem(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=printApa&type=lea&"/>'+getAlldata(obj);			
			obj.submit();
			}	
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
		//修改配置项详细信息
  		function modifyapa(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=modifyApa&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//删除配置项
  		function deleteapa(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该信息吗?")) {
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=deleteApa&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="流程审批进度查询" />
		<lemis:query action="/ApaOperAction.do?method=queryApa" editorMeta="editor" topic="流程审批进度查询" />
		<lemis:table action="ApaOperAction.do" headerMeta="header" topic="流程信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


