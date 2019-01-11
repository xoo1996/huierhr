<!--sysmanager/role/userrole.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.radf.manage.entity.Sc05"%>
<%@ page import="org.radf.manage.entity.Sc06"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ page import="org.radf.plat.commons.OptionDictSupport"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="java.util.*"%>
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
	<lemis:title title="添加角色" />
	<lemis:tabletitle title="角色信息" />
	<%String bsc014 = "";
			List buttons = new ArrayList();
			if (request.getParameter("bsc014") != null)
				bsc014 = request.getParameter("bsc014");
			Sc06 rd = (Sc06) pageContext.findAttribute(GlobalNames.UPDATE_DATA);
			Iterator usersIter = (Iterator) pageContext.findAttribute("usersIter");
			Iterator freeUsersIter = (Iterator) pageContext.findAttribute("freeUsersIter");
	%>

	<table border="0" align="center" cellpadding="3" cellspacing="0"
		class="tableInput">
		<html:form action="/roleAction.do?method=finduserbyroleid">

			<input type="hidden" name="bsc015" value=""> <html:hidden
				property="userList" />
			<tr>
				<td width="8%" height="0" align="right" nowrap><font color="#FF0000">*</font>选择角色名称<br>
				</td>
				<div id=div_hint
					style="font-size:12px;color:red;display:none;position:absolute; z-index:2; top:200;background-color: #F7F7F7; layer-background-color: #0099FF; border: 1px #9c9c9c solid;filter:Alpha(style=0,opacity=80,finishOpacity=100);"></div>

				<td width="87%" height="0"><select style='font-size:12px'
					name='bsc014' class='select' required='false'
					onChange="QueryRoleMenuList(this.form)"
					>
					<%=OptionDictSupport.getComboBoxString("SC06", bsc014)%>
				</select></td>
			</tr>
			<%if (usersIter != null || freeUsersIter != null) {%>
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
						</select></td>
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
						</select></td>
					</tr>
				</table>
				</td>
			</tr>
	</table>
	</td>
	</tr>
	</table>
	<%buttons.add(new Button("save", "保 存", "sys050201",
						"saveData(document.forms[0])"));
				pageContext.setAttribute("button", buttons);

	%>
	<%}
	%>
	</html:form>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
</html>
<script language="javascript">
function check(){
	document.sc06Form.userList.value = getSelectedData('sc06Form','userListRight');
	document.sc06Form.bsc015.value='0';
	return true;
}
function QueryRoleMenuList(form)
{
	form.submit();  
}
</script>
<script language="javascript">
function saveData(obj){
	if(!checkValue(obj)){
		return false;
	}
	if(!check()){
		return false;
	}
	obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/roleAction.do?method=finduserbyroleid&type=save";
	obj.submit();
}
</script>

