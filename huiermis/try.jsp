<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<html:form action="/logonAction.do?method=userLogin" method="post">
<input name="bsc011" type="text" size="21" style="width:160;height:20" >
<input name="bsc013" type="password" size="21" style="width:160;height:20">
<input type="submit" name="Ìá½»">
<input type="reset" name="È¡Ïû">
</html:form>
</body>
</html>
