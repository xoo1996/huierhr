<!-- /unemployment/personmanagement/leadinout/progressBarStatus.jsp -->
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="org.radf.plat.control.progressBar"%>
<%@ page language="java" import="org.radf.plat.control.progressBarFactory"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style type="text/css">
<!--
body {margin:0; 
padding:0;}
.STYLE1 {
	color: #0054e3;
	font-size: 14px;
}
.STYLE2 {
	color: #FFFFFF;
	font-weight: bold;
	font-size: 12px;
}
-->
</style>

<html>
<% 
		String strProgressBarName = (String) request.getParameter("progressBarName");
    	progressBarFactory m_barFactory = progressBarFactory.getFactory();
    	progressBar m_bar = m_barFactory.getProgressBarEx(strProgressBarName);
		int percent = m_bar.getNPercent();
		if(!m_bar.isBCompleted()){ 
%>
    	<script language="javascript">
	    <!--
			function refresh(){
				document.location=location.href;
			}
			setTimeout("refresh()",500);
	     -->
	    </script>
<%
		}else{
			m_barFactory.delProgressBar(strProgressBarName);
%>
		<script language="javascript">
		<!--
			parent.window.close_window();
		-->
		</script>
<%
		}
		if(percent >= 95){
%>
			<script language="javascript">
			<!--
				parent.window.close_window();
			-->
			</script>
<%
		}
%>
<body background="/lemis/images/load_bg.jpg">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#0066be"><tr><td bgcolor="#ffffff"  style="border:1px solid #0054e3">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
	<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td  style="border:#0054e3 1px solid;  padding:2px" height="25px" bgcolor="#FFFFFF">
        
	      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    	  	<tr>
    	  	<%if(0 == percent) {
	    	  	out.println("<td align='center'><font size=1>用户操作还未开始或已结束</font></td>");
    	  	}else { %>
		  	<%  for(int i = 0;i < percent; i += 10) {%>
	  			<td bgcolor="0066be">&nbsp;</td>
		  	<%  }%>
		  	<%  for(int i = percent;i <= 100; i += 10){%>
	  			<td>&nbsp;</td>
		  	<%  }
    	  	  }
		  	%>
    	  	</tr>
	 	  </table>
	 	  
	 	 </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="35" align="center"><span class="STYLE1">
          <%if(0 != percent)
	          out.println(percent);%> %</span></td>
        </tr>
	</table>
  </td></tr></table>
</body>
</html>


