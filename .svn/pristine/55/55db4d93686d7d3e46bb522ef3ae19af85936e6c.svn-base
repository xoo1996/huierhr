<!--sysmanager/role/AddRole.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.radf.manage.entity.Sc05"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色信息</title>
<lemis:base />
</head>
<lemis:body>
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
	</table>
	<!--录入部分3-->
	<lemis:title title="添加角色" />
	<lemis:tabletitle title="角色信息" />
	<%Iterator usersIter = (Iterator) pageContext
					.findAttribute("usersIter");
			Iterator freeUsersIter = (Iterator) pageContext
					.findAttribute("freeUsersIter");
			String stringData = request.getParameter("stringData");

			%>
	<table border="0" align="center" cellpadding="3" cellspacing="0"
		class="tableInput">
		<html:form action="/roleAction.do?method=saveSc06">
			<tr>
				<td width="8%" height="0" align="right" nowrap><font color="#FF0000">*</font>角色描述<br>
				</td>
				<td width="87%" height="0">
				<html:hidden property="bsc014" />
				<html:hidden property="userList" />
				<lemis:text property="bsc015" disable="false" maxlength="30" /></td>
				<html:hidden property="stringData" value="<%=stringData%>" />
			</tr>
			<tr>
				<td height="0" align="right" nowrap>用户成员</td>
				<td>
				<table cellspacing="0" cellpadding="0"
					style="height:100px" align="left">
					<tr>
						<td align="center">可选用户</td>
						<td align="center"></td>
						<td align="center">已选用户</td>
					</tr>
					<tr>
						<td align="center"><select name="userListLeft" multiple size="18"
							ondblclick="javascript:moveRight('sc06Form','userListLeft','userListRight',false)">
							<%while ((null != freeUsersIter) && (freeUsersIter.hasNext())) {
									Sc05 su = (Sc05) freeUsersIter.next();

							%>
							<option value="<%=su.getBsc010()%>"><%=su.getBsc012()%></option>
							<%}%>
						</select>
						</td>
						<td style="width:10px" align="center">
						<table>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('sc06Form','userListLeft','userListRight',false)">&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('sc06Form','userListLeft','userListRight',true)">&#62;&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('sc06Form','userListLeft','userListRight',false)">&#60;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('sc06Form','userListLeft','userListRight',true)">&#60;&#60;</a></td>
							</tr>
						</table>
						</td>
						<td align="left"><select name="userListRight" multiple size="18"
							ondblclick="javascript:moveLeft('sc06Form','userListLeft','userListRight',false)">
							<%while ((null != usersIter) && (usersIter.hasNext())) {
									Sc05 su = (Sc05) usersIter.next();
							%>
							<option value="<%=su.getBsc010()%>"><%=su.getBsc012()%></option>
							<%}%>
						</select>
						</td>
					</tr>
				</table>
				</td>
			</tr>
	</table>
	</td>
	</tr>
	</table>
	<table width="95%" height="35" border="0" align="center"
		cellspacing="0" class="tableInput">
		<tr>
			<td class="action"><input type="submit" value="保 存"
				onclick="return check();" class="buttonGray"> <input type="button"
				name="close" value="返 回" onclick='go2Page("sysmanager","")' class="buttonGray"></td>
		</tr>
		</html:form>
	</table>
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
</html>
<script language="javascript">
	function page_init(){
		document.all("bsc015").value="";
		if(document.all("bsc014").value!=null&&document.all("bsc014").value!=""){
			document.all("bsc014").value="";
		}
	}
	//设置页面元素的CSS
	//eleName 页面元素名称
	//className 要切换的CSS名称
	function goBack() {
 		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/roleAction.do?method=findAllSc06&stringData=" +
      	document.all("stringData").value ;
	}

	function check(){
  	var bsc015 = document.sc06Form.bsc015.value;
  	if((null == bsc015) ||( "" == bsc015)){
  		alert("角色名称不能为空！");
    	  document.sc06Form.bsc015.focus();
    	return false;
  	}
  	document.sc06Form.userList.value = getSelectedData('sc06Form','userListRight');
  	return true;
	}
</script>

