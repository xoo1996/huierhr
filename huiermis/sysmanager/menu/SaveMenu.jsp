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
                title = "��Ӳ˵�";
            }
            else if("mod".equals(function))
            {
                title = "�޸Ĳ˵�";
            }
                       
            Map buttons = new LinkedHashMap();
            if ("add".equals(function))
            {
               //title = "��Ӳ˵�";
                buttons.put("�� ��", "addData(document.forms[0])");
                flag="false";
            }
            else if("mod".equals(function))
            {
               //title = "�޸Ĳ˵�";
                buttons.put("�� ��", "saveData(document.forms[0])");
                flag="true";
            }

			buttons.put("�� ��", "document.forms[0].reset();");
			buttons.put("�� ��", "go2Page(\"sysmanager\",\"\")");
            buttons.put("�� ��", "closeWindow(\"MenuForm\")");
            pageContext.setAttribute("button", buttons);
                     
            TreeMap bsc021 = new TreeMap();
            bsc021.put("1","�˵�");
            bsc021.put("0","ԭ��");
            bsc021.put("2","��ť");
			pageContext.setAttribute("bsc021",bsc021);
			
            TreeMap bsc024 = new TreeMap();
            bsc024.put("1","��");
            bsc024.put("0","��");
			pageContext.setAttribute("bsc024",bsc024);

%>
<html>
	<head>
	<title>�˵�����Ϣ</title>
	<lemis:base/>
	</head>
	<lemis:body>
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr>
   				<td height="10"></td>
			</tr>
		</table>
		<!--���ⲿ��-->
		<lemis:title title="<%=title%>" />
		<lemis:tabletitle title="�˵��б���Ϣ"/>
		<table width="95%" border="0" align="center" cellpadding="3" cellspacing="0" class="tableInput">
			<html:form action="/menuAction.do?method=menuAdd" method="POST" onsubmit="return checkValue(this)">
			<tr>
  			  	<td width="17%" height="0" align="right"  nowrap><font color='#FF0000'>*</font>�˵�ID<br></td>
  			 	<td width="16%" height="0" align="center" ><lemis:text maxlength='9' property="bsc016" label="�˵�ID" required="true" disable="<%=flag %>"/></td>
			    <td width="17%" height="0" align="right"  nowrap>����<br></td>
				<td width="50%" height="0" align="center" ><lemis:text property="bsc017" disable="false"></lemis:text></td>
			</tr>
  			<tr>
				<td height="0" align="right"  nowrap>��ID<br></td>
				<td height="0" align="center" ><lemis:text maxlength='9'  property="bsc019" required="false" disable="false" /></td>
				<td width="17%" height="0" align="right"  nowrap><font color='#FF0000'>*</font>����<br></td>
				<td width="17%" height="0" align="center" ><lemis:text property="bsc018" label="����" required="true" disable="false"></lemis:text></td>
			</tr>
  			<tr>
				<td height="0" align="right"  nowrap><font color='#FF0000'>*</font>���<br></td>
				<td height="0" align="center" ><lemis:formatinput mask="nnnnn" property="bsc020" label="���" required="true" disable="false" /></td>
				<td height="0" align="right"  nowrap><font color='#FF0000'>*</font>����<br></td>
				<td height="0" align="center" ><lemis:codelist type="bsc021" label="����" required="true" dataMeta="bsc021" /></td>
  			</tr>
  			<tr >
				<!--  <td height="0" align="right"  nowrap><font color='#FF0000'>*</font>�Ƿ����־<br></td>
				<td height="0" align="center" ><lemis:codelist type="bsc024" label="�Ƿ����־"  required="true" dataMeta="bsc024" /></td>
				-->
				<td height="0" align="right"  nowrap>����<br></td>
				<td height="0" align="center" colspan="3"><lemis:text property="bsc022" label="����" disable="false" required="true" /></td>
  			</tr>
  			<tr >
				<td height="0" align="right"  nowrap>������Ա<br></td>
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

