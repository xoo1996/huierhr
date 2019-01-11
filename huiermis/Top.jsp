<%@ page contentType="text/html; charset=GBK"
	import="org.radf.plat.util.global.GlobalNames,java.util.Iterator,java.util.Collection"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>

<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<link href="<html:rewrite forward='css'/>" rel="stylesheet"
	type="text/css">
<link href="<html:rewrite forward='menu'/>" rel="stylesheet"
	type="text/css"><script src="<html:rewrite forward='globals'/>"></script>
<script src="<html:rewrite forward='top'/>"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<html>
<head>
<title>logo</title>
<%//从用户会话中获取登录用户信息
if(session==null){
	out.println("<script language=javascript>alert('超时，请登录!');window.parent.location='http://"+request.getRemoteAddr()+":"+request.getServerPort()+"/" + GlobalNames.WEB_APP + "/logonDialog.jsp';</script>");
	return;
}
LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
if(dto==null){
	out.println("<script language=javascript>window.parent.location='http://"+request.getRemoteAddr()+":"+request.getServerPort()+"/" + GlobalNames.WEB_APP + "/logonDialog.jsp';</script>");
	return;
}
%>
<script type="text/javascript">
$(document).ready(function(){
	
	var grCli=<%=dto.getBsc014()%>;
	var count = <%=dto.getNotinumber()%>;
	var count2 = <%=dto.getPronumber()%>;
	if(grCli==920||grCli==1){
		$("#notice").html("即将到期合同："+count+"个");
	}
	$("#notice2").html("待审批流程："+count2+"个");
	
});
	function goContract(){
		window.parent.mainFrame.location.href = "/" + lemis.WEB_APP_NAME + "/contract/ContractAction.do?method=queryExpire";
	//	$("#notice").attr("href","");
	}
	function goContract2(){
		window.parent.mainFrame.location.href = "/" + lemis.WEB_APP_NAME + "/process/ApaOperAction.do?method=queryApa&type=verify&";
	}
</script>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; 
  document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

var iFirst = 0;
var iLast = 5;
<%
String js = "var ids = new Array(0);\n";
Collection mainMenu = (Collection)session.getAttribute(GlobalNames.MAIN_MENU);
if(null != mainMenu){
	js = "var ids = new Array(" + mainMenu.size() + ")\n";
	int i = 0;
	for(Iterator it = mainMenu.iterator();it.hasNext();){
		Sc08 tmp = (Sc08)it.next();
		js = js + "ids[" + i + "] = \"TD_ID_" + tmp.getBsc016() + "\";\n";
  		i++;
	}
}
pageContext.getOut().println(js);
%>
function next(){
 	 if (iLast>=ids.length-1) return;
	document.all.item(ids[++iLast]).style.display="";
	document.all.item(ids[iFirst++]).style.display="none";
}
function prior(){
	if (iFirst<=0) return;
	document.all.item(ids[--iFirst]).style.display="";
	document.all.item(ids[iLast--]).style.display="none";
}
function isRollShow(){
	return true;
}
var currentMenuName = "";
function FUNC_MENUTABLE_MENU_CLICK(menuName, aimFrm, menuLocation, menuTarget){
	var tdelement,aelement;
	if (currentMenuName != ""){
		tdelement=document.all("TD_ID_"+currentMenuName);
		aelement=document.all("A_ID_"+currentMenuName);
//		if (tdelement != null)
//		tdelement.className = "LoginBodyFontSize";
//		if (aelement != null)
//		aelement.className = "astyle";
	}
	currentMenuName = menuName;
	tdelement=document.all("TD_ID_"+currentMenuName);
	aelement=document.all("A_ID_"+currentMenuName);
//	if (tdelement != null)
//		tdelement.className = "tdfocusstyle";
//	if (aelement != null)
//		aelement.className = "afocusstyle";
	if (menuLocation != ""){
		if (menuTarget == "")
			window.location.href=menuLocation;
		else{
			var targetobj = top.document.all(menuTarget);
			targetobj.src=menuLocation;
		}
	}
	if (aimFrm != ""){
		var aimobj = top.document.all(aimFrm);
		var aimsrc = aimobj.src;
		var index = aimsrc.indexOf("?", 0);
		if (index == -1)
			aimsrc = aimsrc + "?" + "parentName" + "=" + menuName;
		else{
			index = aimsrc.indexOf("parentName", index);
			if (index == -1)
				aimsrc = aimsrc + "&" + "parentName" + "=" + menuName;
			else{
				aimsrc = aimsrc.substring(0, index);
				aimsrc = aimsrc + "parentName" + "=" + menuName;
			}
		}
		aimobj.src=aimsrc;
	}

    var mainFrame = top.document.all("mainFrame");
	 mainFrame.src="/" + lemis.WEB_APP_NAME + "/Main.htm";
}

function reLogon(){
 
  var dd= Math.random();
  window.location.href = "/" + lemis.WEB_APP_NAME + "/logonAction.do?method=userLogout&type='"+dd+"'";
}
function logoff(){
 var dd= Math.random();
var mainFrame = top.document.all("mainFrame");
  mainFrame.src= "/" + lemis.WEB_APP_NAME + "/Main.htm";
  changeLeft();
  hiddenTop();
  window.location.href = "/" + lemis.WEB_APP_NAME + "/logonAction.do?method=userLogoff&type='"+dd+"'";
}

//-->

</script>

</head>
<%
String onLoad = "MM_preloadImages('"+GlobalNames.WEB_APP+"/images/p_b.gif','"+GlobalNames.WEB_APP+"/images/n_b.gif')";

%>
<body leftmargin="0" topmargin="0" onLoad="<%=onLoad%>" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr id="sub1">
    <td width="150"><img src="<%=GlobalNames.WEB_APP%>/images/logo1.jpg" width="150" height="63" ></td>
    <td width="100%" background="<%=GlobalNames.WEB_APP%>/images/banner41_new.jpg">
       <p>&nbsp;</p>
       <p align=RIGHT valign=BOTTOM>
       <a id="notice" style="position:absolute; color:#000; top:40; right:20;font-size: 15;font-weight:bold" href="javascript:goContract()"></a>
       <a id="notice2" style="position:absolute; color:#000; top:20; right:20;font-size: 15;font-weight:bold" href="javascript:goContract2()"></a>
          <!-- <MARQUEE width="25%" style="HEIGHT: 15px" scrollAmount=1 scrollDelay=5 direction=left behavior=alternate onmouseover=stop() onmouseout=start()><FONT size=2><FONT face=宋体><FONT color=#ff0000>真诚 &nbsp;团结&nbsp;尽责 &nbsp;创新 &nbsp;科学 &nbsp;实效</FONT></FONT></FONT></MARQUEE> -->
         <!--   <MARQUEE width="3%" style="HEIGHT: 15px"></MARQUEE> -->
       </p>   
    </td>
  </tr>
</table>
<table width="100%" height="22" border="0" cellpadding="0" cellspacing="0" bgcolor="C1C1C1">
  <tr align="right">
    <td height="25"  class="user"  align="center"  nowrap width=90>
	    <img src="<%=GlobalNames.WEB_APP%>/images/arrow_right.gif" id="button" onClick="changeLeft()" style="cursor:hand" title="请先选择子系统！" />
	    <img src="<%=GlobalNames.WEB_APP%>/images/arrow_up.gif" id="main1"  onClick="expandIt1(1)" style="cursor:hand" title="隐藏LOGO" />	
		 <img src="<%=GlobalNames.WEB_APP%>/images/login.gif" style="cursor:hand" title="重登录" onClick="reLogon()"/>
		<img src="<%=GlobalNames.WEB_APP%>/images/logoff.gif" style="cursor:hand" title="下线" onClick="logoff()"/>
   </td>
    <td class="user" align="center" width="210" ><font color="#FFCC00">欢迎《<%=dto.getBsc012()%>》用户登录该系统</font></td>

    <!--<td class="user" align="center" nowrap width="300" nowrap>隶属机构:<%=dto.getAab300()%></td>-->
   
    <%
         StringBuffer menu = new StringBuffer();
         int display = 0;
         if(null != mainMenu){%>
    		<td width="17" height="25"><img src="<%=GlobalNames.WEB_APP%>/images/top_menu_left.gif" /></td>
    		<td ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0" bgcolor="C1C1C1">
        	<tr  valign="bottom" class="LoginBodyFontSize">
        <%
			for(Iterator it = mainMenu.iterator();it.hasNext();){
				Sc08 tmp = (Sc08)it.next();
          		String title = tmp.getBsc018();
  				String funcid = tmp.getBsc016();
  				 menu.append("<TD class=\"menu\" valign=\"middle\" id=\"TD_ID_"+ funcid
                 +  "\" noWrap align=\"right\" "
                 + "style=" + (display > 5 ?"display:none" : "\"\"" ) + "><A class=\"ALink\""
                 + " id=\"A_ID_" + funcid
                 + "\"  href=\"javascript:FUNC_MENUTABLE_MENU_CLICK('"
                 + funcid+ "', '', '" + GlobalNames.WEB_APP + tmp.getBsc017()
                 + "', 'leftFrame')\" style=\"position:relative;left:-10;top:2\"> "
                 + title + " </A> </TD>\n");
                 display++;
			}
            for(int index = 0; index < 5 - display; index++){
               menu.append("<TD class=\"menu\" "
                 +  "\" noWrap align=\"center\"  "
                 + "style=''" + "></TD>\n");
            }

          pageContext.getOut().println(menu.toString());


          String leftButton = "MM_swapImage('Image3','','"+ GlobalNames.WEB_APP+"/images/p_b.gif',1)";
          String rightButton = "MM_swapImage('Image2','','"+GlobalNames.WEB_APP+"/images/n_b.gif',1)";

          %>

          <td width=50 class="menu" align=right >
           <a href="#" class="ALink" onClick=" javascript:prior();" onMouseOut="MM_swapImgRestore()" onMouseOver="<%=leftButton%>"><img src="<%=GlobalNames.WEB_APP%>/images/p_a.gif" name="Image3" width="15" height="16" border="0" style="position:relative;left:-10;top:0"></a>
           <a href="#" class="ALink" onClick="javascript:next();" onMouseOut="MM_swapImgRestore()" onMouseOver="<%=rightButton%>"><img src="<%=GlobalNames.WEB_APP%>/images/n_a.gif" name="Image2" width="15" height="16" border="0" style="position:relative;left:-10;top:0"></a>
          </td>
        </tr>
      </table>
      </td>
      <%}else{
        %>
        <td width="17" valign="top" bgcolor="#0066BE" width="613" ><img src="<%=GlobalNames.WEB_APP%>/images/nomenu.gif" /></td>
      <%}
      %>
  </tr>
</table>
<lemis:clean names="LogonForm" />
</body>
</html>
