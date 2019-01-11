<!--sysmanager/dept/GroupMainFrame.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<frameset cols="200,*" frameborder="no" border="0"  width='400' framespacing="0">
  <frame src="<%=GlobalNames.WEB_APP%>/sysmanager/deptAction.do?method=loadDepartmentTree" name="treeFrame" nodeid="0" noresize>
  <frame src="<%=GlobalNames.WEB_APP%>/sysmanager/deptAction.do?method=findByKey" name="displayFrame">
</frameset>
</html>

