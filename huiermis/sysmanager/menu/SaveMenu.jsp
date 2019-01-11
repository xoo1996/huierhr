<!--sysmanager/menu/SaveMenu.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.manage.menu.form.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			String function = "";
            String title = "";
            function = request.getParameter("function");
            String flag= new String();
            if ("add".equals(function))
            {
                title = "添加菜单";
            }
            else if("mod".equals(function))
            {
                title = "修改菜单";
            }
                       
            Map buttons = new LinkedHashMap();
            if ("add".equals(function))
            {
               //title = "添加菜单";
                buttons.put("添 加", "addData(document.forms[0])");
                flag="false";
            }
            else if("mod".equals(function))
            {
               //title = "修改菜单";
                buttons.put("保 存", "saveData(document.forms[0])");
                flag="true";
            }

			buttons.put("重 置", "document.forms[0].reset();");
			buttons.put("返 回", "go2Page(\"sysmanager\",\"\")");
            buttons.put("关 闭", "closeWindow(\"MenuForm\")");
            pageContext.setAttribute("button", buttons);
                     
            TreeMap bsc021 = new TreeMap();
            bsc021.put("1","菜单");
            bsc021.put("0","原子");
            bsc021.put("2","按钮");
			pageContext.setAttribute("bsc021",bsc021);
			
            TreeMap bsc024 = new TreeMap();
            bsc024.put("1","是");
            bsc024.put("0","否");
			pageContext.setAttribute("bsc024",bsc024);

%>
<html>
	<head>
	<title>菜单表信息</title>
	<lemis:base/>
	</head>
	<lemis:body>
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr>
   				<td height="10"></td>
			</tr>
		</table>
		<!--标题部分-->
		<lemis:title title="<%=title%>" />
		<lemis:tabletitle title="菜单列表信息"/>
		<table width="95%" border="0" align="center" cellpadding="3" cellspacing="0" class="tableInput">
			<html:form action="/menuAction.do?method=menuAdd" method="POST" onsubmit="return checkValue(this)">
			<tr>
  			  	<td width="17%" height="0" align="right"  nowrap><font color='#FF0000'>*</font>菜单ID<br></td>
  			 	<td width="16%" height="0" align="center" ><lemis:text maxlength='9' property="bsc016" label="菜单ID" required="true" disable="<%=flag %>"/></td>
			    <td width="17%" height="0" align="right"  nowrap>链接<br></td>
				<td width="50%" height="0" align="center" ><lemis:text property="bsc017" disable="false"></lemis:text></td>
			</tr>
  			<tr>
				<td height="0" align="right"  nowrap>父ID<br></td>
				<td height="0" align="center" ><lemis:text maxlength='9'  property="bsc019" required="false" disable="false" /></td>
				<td width="17%" height="0" align="right"  nowrap><font color='#FF0000'>*</font>标题<br></td>
				<td width="17%" height="0" align="center" ><lemis:text property="bsc018" label="标题" required="true" disable="false"></lemis:text></td>
			</tr>
  			<tr>
				<td height="0" align="right"  nowrap><font color='#FF0000'>*</font>序号<br></td>
				<td height="0" align="center" ><lemis:formatinput mask="nnnnn" property="bsc020" label="序号" required="true" disable="false" /></td>
				<td height="0" align="right"  nowrap><font color='#FF0000'>*</font>类型<br></td>
				<td height="0" align="center" ><lemis:codelist type="bsc021" label="类型" required="true" dataMeta="bsc021" /></td>
  			</tr>
  			<tr >
				<!--  <td height="0" align="right"  nowrap><font color='#FF0000'>*</font>是否记日志<br></td>
				<td height="0" align="center" ><lemis:codelist type="bsc024" label="是否记日志"  required="true" dataMeta="bsc024" /></td>
				-->
				<td height="0" align="right"  nowrap>描述<br></td>
				<td height="0" align="center" colspan="3"><lemis:text property="bsc022" label="描述" disable="false" required="true" /></td>
  			</tr>
  			<tr >
				<td height="0" align="right"  nowrap>开发人员<br></td>
				<td height="0" align="center" ><lemis:text property="bsc023" disable="false"></lemis:text></td>
				<td height="0" align="right"  nowrap><br></td>
				<td height="0" align="center" ></td>
  			</tr>
			<%
				//MenuForm mf = (MenuForm) request.getAttribute("menuForm");
			%>
  			
  			<html:hidden property="bsc016t" />
  			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom/>
		<lemis:clean names="menuForm"/>
		<lemis:errors />
	</lemis:body>
</html>
<script language="javascript">

	function addData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=modifySc08&function=<%=function%>";
		obj.submit();
	}
	
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=modifySc08&function=<%=function%>" +
		"&bsc016=" + document.all("bsc016t").value;;
		obj.submit();
		//alert(obj.action);
	}
	
	function toBack(){
		window.history.back();
	} 
</script>    

