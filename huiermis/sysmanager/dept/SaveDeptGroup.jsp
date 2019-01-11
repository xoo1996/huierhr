<!--sysmanager/dept/SaveDeptGroup.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.radf.manage.entity.Sc01"%>
<%@ page import="org.radf.plat.commons.ClassHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String function = "";
	String title = "";
	function = request.getParameter("function");

	//获得 sc01List
	ArrayList sc01List = (ArrayList)request.getAttribute("sc01List");
	ArrayList sc01List2 = (ArrayList)request.getAttribute("sc01List2");

	String flag="";
	if ("add".equals(function)) {
		title = "添加机构统计";
		flag="false";
	} else if ("mod".equals(function)) {
		title = "修改机构统计";
		flag="true";
	}

	Map buttons = new LinkedHashMap();
	if ("add".equals(function)) {
		//title = "添加机构统计";
		buttons.put("添 加", "addData(document.forms[0])");
	} else if ("mod".equals(function)) {
		//title = "修改机构统计";
		buttons.put("保 存", "saveData(document.forms[0])");
	}

	buttons.put("重 置", "document.forms[0].reset();");
	buttons.put("返 回", "toBack()");
	buttons.put("关 闭", "closeWindow(\"DeptForm\")");
	pageContext.setAttribute("button", buttons);
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
<html>
<head>
<title>机构统计表信息</title>
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
	<lemis:title title="<%=title%>" />
	<lemis:tabletitle title="机构统计列表信息" />
	<table width="95%" border="0" align="center" cellpadding="3"
		cellspacing="0" class="tableInput">
		<html:form action="/deptAction.do" method="POST">
			
			<!--录入部分-->
			<tr>
				<td height="0" width="8%" align="left" nowrap><font color='#FF0000'>*</font>虚拟统计名称<br>
				</td>
				<td height="0" align="left"><lemis:text property="bsc006" required="true" label="虚拟统计名称" maxlength="40" 
					disable="<%=flag%>"></lemis:text></td>
			</tr>
			<tr>
				<% if ("add".equals(function)) {%>
					 <lemis:codelisteditor type="bs007c" label="虚拟统计位数" required="true" redisplay="true" isSelect="true"/>	
				<%} else if ("mod".equals(function)) {%>

					 <lemis:texteditor property="bs007c" label="虚拟统计位数" disable="true" maxlength="25" required="true"/>	
				<%}%>
			  
			</tr>

			<!--机构部分-->
			<tr>
				<td height="0" align="left" nowrap><font color='#FF0000'>*</font>统计机构</td>
				<td>
				<table cellspacing="0" cellpadding="0"
					style="height:100px" align="left">
					<tr>
						<td align="center">可选机构</td>
						<td align="center" style="width:10px"></td>
						<td align="center">已选机构</td>
					</tr>
					<tr>
						<td align="center"><select name="groupListLeft" multiple
							size="18"
							ondblclick="javascript:moveRight('deptForm','groupListLeft','groupListRight',false)">
							<%
							if (sc01List!=null&&sc01List.size()>0) {
								Sc01 sc01 = new Sc01();
								for(int i=0;i<sc01List.size();i++){
									ClassHelper.copyProperties(sc01List.get(i), sc01);
									%>
									<option value="<%=sc01.getBsc001()%>"><%=sc01.getAab300()%></option>
									<%
								}
							}
							%>
						</select></td>
						<td style="width:10px" align="center">
						<table>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('deptForm','groupListLeft','groupListRight',false)">&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('deptForm','groupListLeft','groupListRight',true)">&#62;&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('deptForm','groupListLeft','groupListRight',false)">&#60;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('deptForm','groupListLeft','groupListRight',true)">&#60;&#60;</a></td>
							</tr>
						</table>
						</td>
						<td align="left"><select name="groupListRight" multiple
							size="18"
							ondblclick="javascript:moveLeft('deptForm','groupListLeft','groupListRight',false)">
							<%
							if (sc01List2!=null&&sc01List2.size()>0) {
								Sc01 sc01 = new Sc01();
								for(int i=0;i<sc01List2.size();i++){
									ClassHelper.copyProperties(sc01List2.get(i), sc01);
									%>
									<option value="<%=sc01.getBsc001()%>"><%=sc01.getAab300()%></option>
									<%
								}
							}
							%>
						</select></td>
					</tr>
				</table>
				</td>
			</tr>
			<input id="groupList" type="hidden" />
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
	<lemis:errors /><lemis:clean names="deptForm" />
</lemis:body>
</html>
<script language="javascript">
function page_init(){

}
	function addData(obj){
		if(!checkValue(obj)){
			return false;
		}
		if(!check()){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=modifySc01&function=<%=function%>" +
		"&bsc006=" + document.all("bsc006").value + 
		"&bsc007=" + document.all("bs007c").value + 
		"&groupList=" + document.all("groupList").value;

		obj.submit();
	}
	
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		if(!check()){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=modifySc01&function=<%=function%>" +
		"&bsc006=" + document.all("bsc006").value + 
		"&bsc007=" + document.all("bs007c").value + 
		"&groupList=" + document.all("groupList").value;
		
		obj.submit();
	}
	
	function toBack(){
		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=deptGroupQuery";
	}
	

		
	function check(){
		document.deptForm.groupList.value = getSelectedData('deptForm','groupListRight');
		var groupListRight = document.all('deptForm').all('groupListRight');
 		if(groupListRight.length){
 		   if(groupListRight.length > 1){

 		     return true;
		    }
 		 }else if(groupListRight.length==0){
  			alert("统计机构不能为空！");

 		 	return false;
 		 }
			return true;

	}
</script>

