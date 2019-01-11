<!--/recommendation/personapply/formword.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="org.radf.apps.recommendation.personapply.form.Rec0201Form" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
Rec0201Form form1 = (Rec0201Form) request.getSession().getAttribute("form");
String acc200=form1.getAcc200();
String acc210=form1.getAcc210();
String aac004=form1.getAac004();
String aac001=form1.getAac001();
String aca111=form1.getAca111();
String menuId=request.getSession().getAttribute("menuId").toString();
%>
<html><lemis:base />
<body onload="check()">
</body>
<script language="javascript">
  function check() {
	var truthBeTold = window.confirm("是否匹配出与该人员相关的单位?");
	if(truthBeTold){
		//这里直接来跳转action
	 window.location.href="/lemis/recommendation/Rec0103Action.do?method=PersonRecommendSPost&acc200=<%=acc200%>&aac001=<%=aac001%>&acc210=<%=acc210%>&aac004=<%=aac004%>&aca111=<%=aca111%>&menuId=<%=menuId%>";
    }
	else  { 
		go2Page("recommendation","1");
	}
  }
</script>
</html>
