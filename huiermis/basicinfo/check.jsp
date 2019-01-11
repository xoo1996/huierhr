<!-- basicinfo/check.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body onload="check()">

</body>
</html>
<script language="javascript">
  function check() {
      
	 window.location.href="/lemis/laborcontract/QueryPersonAction.do?method=entryQuery";
  }
</script>
