<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title> 惠耳用户信息管理平台 </title>
</head>
<frameset name="switchTop" rows="95,*" cols="*" frameborder="no"  border="0" framespacing="0" >
  <frame src="Top.jsp?type=<%=java.lang.Math.random()%>" name="topFrame" scrolling="NO" noresize >
  <frameset name="switchLeft" cols="170,*" frameborder="no" border="0" framespacing="0" rows="*">
    <frame src="Left.jsp" name="leftFrame" scrolling="auto" >
    <frame src="Main.jsp" name="mainFrame">
  </frameset>
</frameset>
<noframes>
</noframes>

</html>

