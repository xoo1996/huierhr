<!-- cfgmgmt/queryCI.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ciid", "耳机ID"));
	header.add(new Formatter("ciname", "耳机名称"));
	header.add(new Formatter("citype", "耳机类型"));
	header.add(new Formatter("cilocation", "耳机位置"));
	header.add(new Formatter("cistate", "耳机状态"));
	header.add(new Formatter("cisupplier", "耳机供应商"));

	List<Button> buttons = new ArrayList<Button>();
	buttons.add(new Button("printCI", "耳机项详细信息", "cus010201", "detailci(document.all.tableform)"));
	buttons.add(new Button("close", "关 闭", "cus999993","closeWindow(\"\")"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ciid", "耳机ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ciid", "耳机ID"));
	editors.add(new Editor("text", "ciname", "耳机名称"));
	editors.add(new Editor("text","citype","耳机类型"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "配置项信息表");//表头

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
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="耳机项查询" />
		<lemis:query action="/CIOperAction.do?method=queryCI&menuId=queryCI" editorMeta="editor" topic="耳机项信息查询" />
		<lemis:table action="CIOperAction.do" headerMeta="header" topic="耳机项信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


