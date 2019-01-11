<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="org.radf.plat.commons.TypeCast" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<lemis:base/>
<lemis:body><lemis:errors/>
  <%
  String url = TypeCast.objToString(request.getAttribute("deleURL"));
  url = url.replaceAll(";deleURL;","&");
//  response.sendRedirect((String)request.getAttribute("rurl"));
  %>
<script language="javascript">
function page_init() { 
  location.href = "<%=url%>";
}
</script>
</lemis:body>
</html>

