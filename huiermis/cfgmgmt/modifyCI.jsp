<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ciid", "定制机ID"));
	header.add(new Formatter("ciname", "定制机名称"));
	header.add(new Formatter("citype", "定制机类型"));
	header.add(new Formatter("cilocation", "定制机位置"));
	header.add(new Formatter("cistate", "定制机状态"));
	header.add(new Formatter("cisupplier", "定制机供应商"));

	List<Button> buttons = new ArrayList<Button>();
	buttons.add(new Button("printCI", "定制机详细信息", "cus010201", "detailci(document.all.tableform)"));
	buttons.add(new Button("modifyCI", "修改定制机", "cus010301", "modifyci(document.all.tableform)"));
	buttons.add(new Button("deleteCI", "删除定制机", "cus010302", "deleteci(document.all.tableform)"));
	buttons.add(new Button("close", "关 闭", "cus999993","closeWindow(\"\")"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ciid", "定制机ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ciid", "定制机ID"));
	editors.add(new Editor("text", "ciname", "定制机名称"));
	editors.add(new Editor("text","citype","定制机类型"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "定制机信息表");//表头

%>
<html>
	<script language="javascript">
		//显示详细信息
  		function detailci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CIOperAction.do?method=printCI&"/>'+getAlldata(obj);			
			obj.submit();
  		}
		//修改配置项详细信息
  		function modifyci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CIOperAction.do?method=modifyCI&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//删除配置项
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该定制机信息吗?")) {
				obj.action = '<html:rewrite page="/CIOperAction.do?method=deleteCI&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="定制机修改查询" />
		<lemis:query action="/CIOperAction.do?method=queryCI&menuId=modifyCI" editorMeta="editor" topic="定制机信息查询" />
		<lemis:table action="CIOperAction.do" headerMeta="header" topic="定制机信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


