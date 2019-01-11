<!--sysmanager/role/RoleList.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.radf.manage.entity.Sc06"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色列表</title>
</head>
<link href="<html:rewrite forward="css"/>" rel="stylesheet"
	type="text/css">
<body>
<table>
	<tr>
		<td width="100%" align="center" nowrap><FONT size="3" color="0000FF">
		角色列表 </FONT></td>
	</tr>

<%
			String link = null;
			Collection roles = (Collection) pageContext
					.findAttribute("allRoles");
			if (null != roles) {
				for (Iterator it = roles.iterator(); it.hasNext();) {
					Sc06 sc06 = (Sc06) it.next();
					link = GlobalNames.WEB_APP
							+ "/sysmanager/roleAction.do?method=findModulesByRoleid&bsc014="
							+ sc06.getBsc014().toString() + "&bsc015="
							+ sc06.getBsc015();
%>
	<tr>
		<td width="100%" nowrap><a href="<%=link%>" target="modulelist"	class="ALink"> <%=sc06.getBsc015()%> </a></td>
	</tr>
	<%}
			}

	%>
</table>
<lemis:errors />
</body>
</html>

