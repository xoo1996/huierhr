<!--/recommendation/personapply/check.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body onload="check()">
</body>
<script language="javascript">
  function check() {
	var truthBeTold = window.confirm("是否新添该人员基本信息?");
	if(truthBeTold){
		//这里直接来跳转action
	 window.location.href="/lemis/recommendation/Rec0201Action.do?method=findPer";
    }
	else  { window.history.back();}
  }
</script>
</html>
