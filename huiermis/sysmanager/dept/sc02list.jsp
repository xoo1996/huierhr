<!--sysmanager/dept/sc02list.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.manage.department.form.Sc02Form"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			Sc02Form fm = (Sc02Form) session.getAttribute("sc02Form");
			List buttons=new ArrayList();
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			String stringData = "";
			stringData = qi.getStringData();
			
			buttons.add(new Button("add","增 加","sys010202","addData(document.forms[0])"));
			buttons.add(new Button("del","删 除","sys010203","editData(document.all.tableform)"));
			buttons.add(new Button("back","返 回","sys010204", "go2Page(\"sysmanager\",\"1\")"));
			buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"deptForm\")"));
			
			List header = new ArrayList();
			header.add(new Formatter("sc02.bsc004", "机构别名"));
			header.add(new Formatter("sc02.bsc005", "业务类别"));
			
			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			qh.put("bsc001", request.getAttribute("bsc001"));
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc004", "机构别名"));
			editors.add(new Editor("text", "bsc005", "业务类别"));
			
			Map hidden = new LinkedHashMap();
            hidden.put("bsc001", "编号");
			hidden.put("bsc005", "编号");
			hidden.put("bsc004", "编号");
			pageContext.setAttribute("hidden", hidden);
            pageContext.setAttribute("editor", editors);
            pageContext.setAttribute("header", header);
            pageContext.setAttribute("button", buttons);
            session.setAttribute("tableheader","机构信息表");
			request.getSession().setAttribute("bsc001",request.getAttribute("bsc001"));
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="机构别名登记" />
		<lemis:query action="/sc02Action.do?method=findSc02" editorMeta="editor" hiddenMeta="queryHiddens" topic="机构别名信息查询" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<input name="bsc001" value="<%=request.getAttribute("bsc001")%>" type="hidden" />
		<lemis:table action="sc02Action.do" headerMeta="header" topic="机构别名信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="deptForm" />	
</html>
<script language="javascript">
	// 新增
	function addData(obj){
	obj.action = '<html:rewrite page="/sc02Action.do?method=Sc02Add&menuId=add"/>'
	+"&stringData=" +document.all("stringData").value;
	obj.submit();
	}
    function editData(obj){
    var t = editObj("chk");
		if(!t){
			return t;
		}
    if(confirm("确定要删除吗?"))
			{
			obj.action = '<html:rewrite page="/sc02Action.do?method=deleteSc02&menuId=mod"/>'
			+"&stringData=" +document.all("stringData").value+"&"+getAlldata(obj);
			obj.submit();
			}
		
	}
	function goBack() {
 		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=DepartmentList&stringData=" +
      	document.all("stringData").value ;
	}
</script>
