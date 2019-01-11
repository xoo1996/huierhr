<%@ page contentType="text/html; charset=GBK"  %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<head>
<title>欢迎登陆惠耳用户信息管理平台</title>
<link href="<html:rewrite forward='css'/>" rel="stylesheet" type="text/css">
<script src="<html:rewrite forward='md5'/>"> </script>
<script src="<html:rewrite forward='globals'/>"> </script>
<script language="javascript">
<!--
//设置页面元素的CSS
//eleName 页面元素名称 
//className 要切换的CSS名称
function setClass(eleName,clsName) {
	document.all(eleName).className = clsName;
}
var topFrame = top.document.all("topFrame");
if(topFrame){
 // topFrame.src = "/" + lemis.WEB_APP_NAME + "/Top.jsp";
}
//-->
</script>

<script language="JavaScript">
function init(){
	  
	document.all("bsc011").focus();
	 if(window.parent.length>1){ 
        window.parent.location="/" + lemis.WEB_APP_NAME + "/logonDialog.jsp"; 
    }
}

function onLogin() {
  document.logonForm.bsc013.value = hex_md5(document.logonForm.bsc013.value);
  document.logonForm.submit();
  
}

function checkKey()
{
  if(13 == window.event.keyCode){
    onLogin();
  }
}
document.onkeydown=checkKey;



</script></head>
<%
	String message=(String)request.getAttribute("errorMsg");

	if(message==null){
	message="&nbsp;";
	
}else{
	out.println("<script language=javascript>\n alert("
			+ "'" + message.trim() + "'"
			+ ");;history.back();</script>");
}

%>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<body onload="init();">
<html:form action="/logonAction.do?method=userLogin" method="post">
<table height="341" border="0" align="center" cellspacing="0" style="width:563px;text-align:center;background-image:url('images/login_new2.jpg')">
  <tr>
    <td width="563" align="left" valign="top">
      <table width="100%" height="220" border="0" cellspacing="0" style="background-color:transparent">
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background-color:transparent">
        <tr>
          <td width="39%">&nbsp;</td>
          <td width="9%" class="LoginBodyFontSize">用户名：</td>
          <td width="29%" height="30"><input name="bsc011" type="text" size="21" style="width:160;height:20" value=""></td>
          <td width="23%">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td class="LoginBodyFontSize">密&nbsp;&nbsp;码：</td>
          <td ><input name="bsc013" type="password" size="21" style="width:160;height:20" value=""></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td height="50"><table width="144" height="21" border="0" cellpadding="0" cellspacing="0" style="background-color:transparent;text-align:center">
              <tr>
                <td width="62" style="text-align:center" valign="bottom" class="ButtonA" id="hhh" onMouseOver="setClass('hhh','ButtonA1')" onMouseOut="setClass('hhh','ButtonA')">
                  <a onclick="onLogin();" class="BLink" style="cursor:pointer" >  登 录</a></td>
                <td width="14"></td>
                <td width="62" style="text-align:center" valign="bottom" class="ButtonA" id="ggg" onMouseOver="setClass('ggg','ButtonA1')" onMouseOut="setClass('ggg','ButtonA')">
                  <a onclick="window.close();" class="BLink" style="cursor:pointer" >  退  出 </a></td>
                  <td width="12"></td>
              </tr>
            </table></td>
          <td valign="bottom">
              </td>
        </tr>
      </table> </td>
  </tr>
</table>
</html:form>
<lemis:errors />
</body>
</html>

