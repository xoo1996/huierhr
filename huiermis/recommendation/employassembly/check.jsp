<!--/recommendation/employassembly/check.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="org.radf.apps.recommendation.employassembly.form.Rec07Form" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Rec07Form fm = (Rec07Form) request.getSession().getAttribute("Rec07Form");
String aab002=fm.getAab002();
String aab003=fm.getAab003();
String aab004=fm.getAab004();
String aab007=fm.getAab007();
String aab043=fm.getAab043();
%>
<html>
<body onload="check()">
</body>
<script language="javascript">
  function check() {
	var truthBeTold = window.confirm("是否新添该单位基本信息?");
	if(truthBeTold){
		//这里直接来跳转action

	 window.location.href="/lemis/basicinfo/employerAction.do?method=entryAddEmployer&mod=recommendation&aab002=<%=aab002%>&aab003=<%=aab003%>&aab004=<%=aab004%>&aab007=<%=aab007%>&aab043=<%=aab043%>";

    }
	else  { window.history.back();}
  }
</script>
</html>
