<!--sysmanager/role/ModuleList.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.radf.manage.entity.Sc06"%>
<%@ page import="org.radf.manage.entity.Sc08"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<html>
<head>
<title>角色列表</title>
</head>
<link href="<html:rewrite forward="css"/>" rel="stylesheet"
	type="text/css"><script src="<html:rewrite forward="globals"/>"></script>
<body onload="init();">
<table>
	<tr>
		<td width="100%" align="center" nowrap><FONT size="3" color="0000FF">
		一级菜单列表 </FONT></td>
	</tr>
	<%String link = "";
			String title = "";
			String ls_card;
			Collection modules = (Collection) pageContext
					.findAttribute("modules");
			Collection allModules = (Collection) pageContext
					.findAttribute(GlobalNames.MAIN_MENU);
			Sc06 sc06 = (Sc06) pageContext
					.findAttribute(GlobalNames.UPDATE_DATA);
			if (null != sc06) {
				for (Iterator it = allModules.iterator(); it.hasNext();) {
					Sc08 sc08 = (Sc08) it.next();
					link = GlobalNames.WEB_APP
							+ "/sysmanager/roleAction.do?method=findFunctionListOfModule&bsc014="
							+ sc06.getBsc014() + "&bsc015=" + sc06.getBsc015()
							+ "&bsc016=" + sc08.getBsc016();
					title = sc08.getBsc018();
					if (null != modules && modules.contains(sc08))
						title = title + "(√)";
%>
	<tr>
		<td width="100%" nowrap><a href="<%=link%>" target="right"
			class="ALink"> <%=title%> </a></td>
	</tr>
	<%}
				pageContext.removeAttribute("modules",
						pageContext.SESSION_SCOPE);
				pageContext.removeAttribute(GlobalNames.UPDATE_DATA,
						pageContext.SESSION_SCOPE);
			}

	%>
</table>
<lemis:errors />
</body>
</html>
<script language="javascript">
function init(){
	var rightFrame = parent.document.all("right");
	if(rightFrame)
		rightFrame.src = '<html:rewrite page="/rightTree.do" />';
}
</script>

