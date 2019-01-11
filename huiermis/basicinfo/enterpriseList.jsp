<!-- basicinfo/enterpriseList.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.apps.basicinfo.form.QueryEmployerForm" %>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	QueryEmployerForm empForm = (QueryEmployerForm) session
	.getAttribute("QueryEmployerForm");
	Editor editor;
	String title = "";
	String buttonId="";
	String menuid = request.getParameter("menuId");
	if (menuid == null || "".equals(menuid)) {
		if(session.getAttribute("menuId")!=null){
			menuid = (String)session.getAttribute("menuId");
			request.setAttribute("1", menuid);
		}
	}
	
	List editors = new ArrayList();
	editors.add(new Editor("text", "aab007", "营业执照号"));
	editors.add(new Editor("text", "aab003", "组织机构代码"));
	editors.add(new Editor("text", "aab002", "社会保险登记证编码"));
	editors.add(new Editor("text", "aab004", "单位名称"));
	editors.add(new Editor("text", "aab043", "单位拼音码"));
	editor = new Editor("text", "aae119", "单位状态");
	


	if ((!"enterpriseWriteOff".equals(request.getParameter("menuId")))
	&& (!"enterpriseQuery".equals(request.getParameter("menuId")))
	&& (!"enterpriseReg".equals(request.getParameter("menuId")))
	&& (!"enterpriseAlter".equals(request.getParameter("menuId")))
	&& (!"enterpriseWriteOff".equals(request.getAttribute("1")))
	&& (!"enterpriseQuery".equals(request.getAttribute("1")))
	&& (!"enterpriseReg".equals(request.getAttribute("1")))
	&& (!"enterpriseAlter".equals(request.getAttribute("1")))) {
	editor.setDisable(false);
	}
	else if ("enterpriseReg".equals(menuid)
	|| "empObtainWork".equals(menuid)) {
	title = "单位登记";
	}
	else if ("enterpriseAlter".equals(menuid)) {
	title = "单位变更";
	buttonId = "bas010205";
	}
	else if ("enterpriseWriteOff".equals(menuid)) {
	title = "单位注销";
	}
	else if ("enterpriseQuery".equals(menuid)) {
	title = "单位查询";
	}
	if ("uniteCop".equals(menuid)){
	title="目标合并单位查询";
    }
    if("enterpriseDel".equals(menuid)){
    title = "单位查询";
    }
    
    
	

	Map hiddens = new LinkedHashMap();
	hiddens.put("menuId", menuid);
	pageContext.setAttribute("hiddens", hiddens);

	Map hidden = new LinkedHashMap();
	hidden.put("aab001", "单位编号");
	hidden.put("aae119", "单位状态");
	
	List header = new ArrayList();
	header.add(new Formatter("ab01.aab003", "组织机构代码"));
	header.add(new Formatter("ab01.aab004", "单位名称"));
	header.add(new Formatter("ab01.aab020", "经济类型"));
	header.add(new Formatter("ab01.aab019", "单位类型",
	TagConstants.DF_CENTER));
	header.add(new Formatter("ab01.aae004", "联系人"));
	header.add(new Formatter("ab01.aae005", "联系电话"));
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden", hidden);
	
	List buttons=new ArrayList();//使用ArrayList
	QueryInfo queryinfo = (QueryInfo) request.getAttribute("queryinfo");
	String tableMode = "radio";
	
	if (queryinfo != null && queryinfo.getTotalNum() > 0) {
	if ("enterpriseReg".equals(menuid)) {
	buttons.add(new Button("view", "查看单位详细", "bas010101", "viewEmp(document.all.tableform);"));
	} else if ("enterpriseAlter".equals(menuid)) {
	buttons.add(new Button("view", "查看单位详细", "bas010201", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("alter", "变 更", "bas010202", "alterEmp(document.all.tableform);"));	
	} else if ("enterpriseWriteOff".equals(menuid)) {
	buttons.add(new Button("view", "查看单位详细","bas010301", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("writeOff","注 销","bas010302", "writeOffEmp(document.all.tableform);"));
	} else if ("enterpriseQuery".equals(menuid)) {
	buttons.add(new Button("view","查看单位详细","bas010401", "viewEmp(document.all.tableform);"));
//	buttons.add(new Button("edit","修 改","bas010402", "editEmp(document.all.tableform);"));
	}else if("enterpriseDel".equals(menuid)){
	buttons.add(new Button("view","查看单位详细","bas010101", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("del","删 除","bas010501", "delEmp(document.all.tableform);"));
	} 
	else if("uniteCop".equals(menuid)){
	buttons.add(new Button("view","查看单位详细","bas010601", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("uniteCop","下一步","bas010602", "uniteCop(document.all.tableform);"));
	
	}
	
	}

	if ("enterpriseReg".equals(menuid)
	|| "empObtainWork".equals(menuid)) {
		if(session.getAttribute("queryInfo1")!=null){
			buttons.add(new Button("add","增 加","bas010102","addEmp(document.forms[0]);"));
		}
	}
	
	buttons.add(new Button("close", "关 闭", "bas999999", "closeWindow(\"QueryEmployerForm\")"));
	pageContext.setAttribute("buttons", buttons);
	session.setAttribute("tableheader", "单位信息表");//表头
	session.setAttribute("menuId", menuid);
	
	
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="<%=title%>" />
		<lemis:query action="/queryEmployerAction.do?method=queryEnterprise" editorMeta="editor" hiddenMeta="hiddens" topic="单位查询" />
		<lemis:table topic="单位列表" action="/queryEmployerAction.do" headerMeta="header" hiddenMeta="hidden" mode="<%=tableMode%>" />
		<lemis:buttons buttonMeta="buttons" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">		
		// 查看单位基本信息
		function viewEmp (obj){
			var para = getAlldata(obj);
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+'menuId=<%=menuid%>&'+para;
			// 增加URL地址，并将URL地址的&符号转化成";amp;"。
			obj.action = obj.action + "&url=" + location.href.replace(/&/g,";amp;")+';amp;menuId=<%=menuid%>';
			location.href=obj.action;
		}
				
		//新增单位基本信息
		function addEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=entryAddEmployer&"/>'+'menuId=<%=menuid%>'
			+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}

		//变更单位基本信息
		function alterEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+'menuId=<%=menuid%>'+
			"&buttonId=<%=buttonId%>"+
			"&operation=edit2&"+getAlldata(obj)+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}
			
			
		//注销单位基本信息
		function writeOffEmp(obj) {
			if(confirm("确定要注销该单位?"))
			{
				obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer&"/>'+'menuId=<%=menuid%>&'
				+getAlldata(obj)+ "operation=writeOff&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
				location.href=obj.action;
			}
		}
			
		function editEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&operation=edit2&"/>'+'menuId=<%=menuid%>&'
			+getAlldata(obj)+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}
		//删除单位基本信息
		function delEmp(obj) {
			if(confirm("确定要删除该单位?"))
			{
			obj.action = '<html:rewrite page="/employerAction.do?method=delEmployer&"/>'+getAlldata(obj);
			obj.submit();
			}
		}
		
	//单位合并	
	function uniteCop(obj){
	   obj.action = '<html:rewrite page="/UniteEmployAction.do?method=initEnterEmploy&"/>'+getAlldata(obj)+"&newaab001="+getSelectData(obj,'aab001');
	   location.href=obj.action;
	}
	
</script>
