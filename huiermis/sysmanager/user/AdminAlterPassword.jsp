<!--sysmanager/user/AlterPassword.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	LoginDTO dto = (LoginDTO) request.getSession().getAttribute("LoginDTO");
	String bsc010 = (String)request.getParameter("bsc010");
	String bsc011 = (String)request.getParameter("bsc011");
	String bsc013 = (String)request.getSession().getAttribute("bsc013");
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修改","saveData(document.forms[0])");
	buttons.put("关 闭","closeWindow(\"\")");

	List buttons1=new ArrayList();
	buttons1.add(new Button("mod","修 改1","sys020200","saveData(document.forms[0])"));
	buttons1.add(new Button("close","关 闭1","sys999999","closeWindow(\"userForm\")"));
	
	pageContext.setAttribute("button", buttons);
	
%>

<html>
<head>
<title>人员表信息</title>
<lemis:base />
</head>
<lemis:body>
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
	</table>
	<!--标题部分-->
	<lemis:title title="更改用户密码" />
	<lemis:tabletitle title="密码信息" />
	<table width="95%" border="0" align="center" cellpadding="3"
		cellspacing="0" class="tableInput">
			<lemis:editorlayout />
		<html:form action="/userAction.do?method=passwdReset&function=alter"
			method="POST">
			<tr>
				<input type="hidden" name="bsc010">
				<td height="0" align="right" nowrap>用户名称</td>
				<td height="0" align="center"><input name=bsc011
					type="text" class="text" size="20" readonly></td>
			</tr>
			<tr>
				<td height="0" align="right" nowrap>旧密码</td>
				<td height="0" align="center"><input name="passwd"
					type="password" class="text" size="20" disabled></td>

			</tr>
			<tr>
				<td height="0" align="right" nowrap>新密码</td>
				<td height="0" align="center"><input name="newpasswd"
					type="password" class="text" size="20" ></td>

			</tr>
			<tr>
				<td height="0" align="right" nowrap>确认密码</td>
				<td height="0" align="center"><input name="confirm"
					type="password" class="text" size="20" ></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
</html>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
<script language="javascript">
	function page_init(){ //方法签名不能改变
		document.all("bsc011").value="<%=bsc011%>";
		document.all("bsc010").value="<%=bsc010%>";
	}
	function saveData(obj){
		if(!ConfirmPassword()){
			return false;
		}
		    if(confirm("此操作不能回退，确信要重置您选中的用户密码吗？")){
			//密码处理
			document.all("passwd").value = "<%=bsc013%>";
			document.all("newpasswd").value = hex_md5(document.all("newpasswd").value);
			document.all("confirm").value = hex_md5(document.all("confirm").value);
			obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=passwdReset&function=alter" +
			"&passwd=" + document.all("passwd").value + 
			"&newpasswd=" + document.all("newpasswd").value;
			obj.submit();
  			}else{
    			return false;
  			}
	}
	function ConfirmPassword(){
 	 var pd = document.userForm.newpasswd.value;
   	 if((null == pd) ||( "" == pd)){
  		alert("新密码不能为空！");
 	 	document.userForm.pd.focus();
  	  return false;
 	 }
 	 var confirm = document.userForm.confirm.value;
 	 if(pd != confirm){
  		alert("新密码和确认密码不一致！");
  		document.userForm.newpasswd.focus();
  	  return false;
  	}
  	return true;
	}
</script>
