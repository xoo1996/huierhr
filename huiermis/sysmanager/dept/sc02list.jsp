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
			
			buttons.add(new Button("add","�� ��","sys010202","addData(document.forms[0])"));
			buttons.add(new Button("del","ɾ ��","sys010203","editData(document.all.tableform)"));
			buttons.add(new Button("back","�� ��","sys010204", "go2Page(\"sysmanager\",\"1\")"));
			buttons.add(new Button("close","�� ��","sys999999","closeWindow(\"deptForm\")"));
			
			List header = new ArrayList();
			header.add(new Formatter("sc02.bsc004", "��������"));
			header.add(new Formatter("sc02.bsc005", "ҵ�����"));
			
			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			qh.put("bsc001", request.getAttribute("bsc001"));
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc004", "��������"));
			editors.add(new Editor("text", "bsc005", "ҵ�����"));
			
			Map hidden = new LinkedHashMap();
            hidden.put("bsc001", "���");
			hidden.put("bsc005", "���");
			hidden.put("bsc004", "���");
			pageContext.setAttribute("hidden", hidden);
            pageContext.setAttribute("editor", editors);
            pageContext.setAttribute("header", header);
            pageContext.setAttribute("button", buttons);
            session.setAttribute("tableheader","������Ϣ��");
			request.getSession().setAttribute("bsc001",request.getAttribute("bsc001"));
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="���������Ǽ�" />
		<lemis:query action="/sc02Action.do?method=findSc02" editorMeta="editor" hiddenMeta="queryHiddens" topic="����������Ϣ��ѯ" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<input name="bsc001" value="<%=request.getAttribute("bsc001")%>" type="hidden" />
		<lemis:table action="sc02Action.do" headerMeta="header" topic="����������Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="deptForm" />	
</html>
<script language="javascript">
	// ����
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
    if(confirm("ȷ��Ҫɾ����?"))
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
