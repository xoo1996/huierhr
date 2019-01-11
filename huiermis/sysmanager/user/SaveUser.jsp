<!--sysmanager/user/SaveUser.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.manage.user.form.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.radf.manage.entity.Sc06"%>
<%@ page import="org.radf.manage.user.dto.*"%>
<%@ page import="org.radf.plat.commons.ClassHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String function = "";
	String title = "";
	function = request.getParameter("function");
	//��� sc06List
	ArrayList sc06List = (ArrayList) request.getAttribute("sc06List");
	ArrayList sc06List2 = (ArrayList) request.getAttribute("sc06List2");
	//��� sc04List
	ArrayList sc04List = (ArrayList) request.getAttribute("sc04List");
	ArrayList sc04List2 = (ArrayList) request.getAttribute("sc04List2");

	
	if ("add".equals(function)) {
		title = "�����Ա";
	} else if ("mod".equals(function)) {
		title = "�޸���Ա";
	}

	Map buttons = new LinkedHashMap();
	if ("add".equals(function)) {
		//title = "�����Ա";
		buttons.put("�� ��", "addData(document.forms[0])");
	} else if ("mod".equals(function)) {
		//title = "�޸���Ա";
		buttons.put("�� ��", "saveData(document.forms[0])");
	}
	buttons.put("�� ��", "document.forms[0].reset();");
	buttons.put("�� ��", "go2Page(\"sysmanager\",\"\")");
	buttons.put("�� ��", "closeWindow(\"UserForm\")");
	pageContext.setAttribute("button", buttons);
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
<title>��Ա����Ϣ</title>
<lemis:base />
</head>
<lemis:body>
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
	</table>
	<!--���ⲿ��-->
	<lemis:title title="<%=title%>" />
	<lemis:tabletitle title="��Ա�б���Ϣ" />
	<table width="95%" border="0" align="center" cellpadding="3"
		cellspacing="0" class="tableInput">
		<html:form action="/userAction.do?method=userAdd" method="POST">
			<lemis:editorlayout />
			<html:hidden property="bsc001" />
      		<html:hidden property="bsc008" />
			<!--¼�벿��-->
			<tr>
				<td height="0" align="right" nowrap><font color='#FF0000'>*</font>�½��˺�<br>
				</td>
				<%if ("mod".equals(function)) { %>
					<td height="0" align="center"><lemis:text property="bsc011" maxlength="10" 
						label="�û�����" required="true" disable="true"></lemis:text></td>
				<%} else{%>
					<td height="0" align="center"><lemis:text property="bsc011" maxlength="10" 
						label="�û�����" required="true" disable="false"></lemis:text></td>
				<%} %>
				<td height="0" align="right" nowrap><font color='#FF0000'>*</font>�û�����<br>
				</td>
				<td height="0" align="center"><lemis:text property="bsc012" maxlength="40" 
					label="�û�����" required="true" disable="false"></lemis:text></td>
			</tr>
			<%if ("mod".equals(function)) { %>
			<tr>
				<td height="0" align="right" nowrap></font>��������<br>
				</td>
				<td height="0" align="center"><lemis:text property="aab300" maxlength="64" 
					label="��������" required="false" readonly="true"></lemis:text></td>
				<td height="0" align="right" nowrap></font>��������<br>
				</td>
				<td height="0" align="center"><lemis:text property="bsc009" maxlength="64" 
					label="��������" required="false" readonly="true"></lemis:text></td>
			</tr>
			<%} %>
			<tr>

				<td height="0" align="right" nowrap><font color='#FF0000'>*</font>��ϵ�绰<br>
				</td>
				<td height="0" align="center"><lemis:text property="aae005"  maxlength="64" 
					label="��ϵ�绰" required="true" disable="false"></lemis:text></td>
				<lemis:codelisteditor type="gctarea" isSelect="true" label="��������" redisplay="true" required="true"  />
			</tr>
			<!--������ɫ����-->
			<tr>
				<td height="0" align="right" nowrap><font color='#FF0000'>*</font>������ɫ</td>
				<td width=40%>
				<table cellspacing="0" cellpadding="0"
					style="height:100px" align="left">
					<tr>
						<td align="center">��ѡ��ɫ</td>
						<td align="center" style="width:10px"></td>
						<td align="center">��ѡ��ɫ</td>
					</tr>
					<tr>
						<td align="center"><select name="roleListLeft" multiple
							size="18"
							ondblclick="javascript:moveRight('userForm','roleListLeft','roleListRight',false)">
							<%
									if (sc06List != null && sc06List.size() > 0) {
									Sc06 sc06 = new Sc06();
									for (int i = 0; i < sc06List.size(); i++) {
										ClassHelper.copyProperties(sc06List.get(i), sc06);
							%>
							<option value="<%=sc06.getBsc014()%>"><%=sc06.getBsc015()%></option>
							<%
										}
										}
							%>
						</select></td>
						<td style="width:10px" align="center">
						<table>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('userForm','roleListLeft','roleListRight',false)">&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveRight('userForm','roleListLeft','roleListRight',true)">&#62;&#62;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('userForm','roleListLeft','roleListRight',false)">&#60;</a></td>
							</tr>
							<tr>
								<td><a class="ALink"
									href="javascript:moveLeft('userForm','roleListLeft','roleListRight',true)">&#60;&#60;</a></td>
							</tr>
						</table>
						</td>
						<td align="left"><select name="roleListRight" multiple
							size="18"
							ondblclick="javascript:moveLeft('userForm','roleListLeft','roleListRight',false)">
							<%
									if (sc06List2 != null && sc06List2.size() > 0) {
									Sc05DTO sc05dto = new Sc05DTO();
									for (int i = 0; i < sc06List2.size(); i++) {
										ClassHelper.copyProperties(sc06List2.get(i),
										sc05dto);
							%>
							<option value="<%=sc05dto.getBsc014()%>"><%=sc05dto.getBsc015()%></option>
							<%
										}
										}
							%>
						</select></td>
					</tr>
				</table>
				</td>
				<!--������������-->
				<lemis:texteditor property="bsc008" label="��������" disable="false" 
					required="true" />
				
			</tr>
			
			
			<%
			UserForm uf = (UserForm) request.getSession().getAttribute("userForm");
			%>
			<input id="bsc010t" type="hidden" value="<%=uf.getBsc010() %>" />
			<input id="bsc011t" type="hidden" value="<%=uf.getBsc011() %>" />
			<input id="roleList" type="hidden" />

		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
</html>

<script language="javascript">
$(document).ready(function(){
/* �������� */
var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
$("input[name=bsc008]").autocomplete(shops,{
	max : 10,
	matchContains : true
});
$("input[name=bsc008]").result(function(event, data, formatted) {
	if (data){
		var gnm = data[0].substring(data[0].indexOf("=")+1);
		var gid = data[0].substring(0,data[0].indexOf("="));
		$("input[name=bsc008]").val(gid);
		$(this).parent().next().find("input").val(gnm);
	}
});
});

function page_init(){ //����ǩ�����ܸı�

}
	function addData(obj){
		if(!checkValue(obj)){
			return false;
		}
		if(!check()){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=modifySc05&function=<%=function%>" +
		"&bsc013=" + hex_md5("888888")+
		"&aae100=1" +
		 "&roleList=" + document.all("roleList").value;  
		
		
		obj.submit();
	}
	
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		if(!check()){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=modifySc05&function=<%=function%>" +
		"&bsc010=" + document.all("bsc010t").value +
		"&bsc011t=" + document.all("bsc011t").value +
		"&roleList=" + document.all("roleList").value;
		
		obj.submit();
	}
	
	function toBack(){
		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=goBack";
	}
	
	function confirmPassword(){
  var pd = document.userForm.passwd.value;
  var confirm = document.userForm.confirm.value;
  if(pd != confirm){
  	alert("�����ȷ�����벻һ�£�");
    return false;
  }
  return true;
}

//У���û�id
	function IsDigit(cCheck) { return (('0'<=cCheck) && (cCheck<='9')); }
	function IsAlpha(cCheck) { return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z'))) }
	function VerifyInput(strUserID){
	    if (strUserID == ""){
	        alert("�����������û���");
	        document.frmUserInfo.UserID.focus();
	        return false;
	    }
	    for (nIndex=0; nIndex<strUserID.length; nIndex++){
	        cCheck = strUserID.charAt(nIndex);
	        if ( nIndex==0 && ( cCheck =='-' || cCheck =='_') ){
	            alert("�û������ַ�����Ϊ��ĸ������");
	            document.userForm.userid.focus();
	            return false;
	        }
	
	        if (!(IsDigit(cCheck) || IsAlpha(cCheck) || cCheck=='-' || cCheck=='_' )){
	            alert("�û���ֻ��ʹ��Ӣ����ĸ�������Լ�-��_���������ַ�����Ϊ��ĸ������");
	            document.userForm.userid.focus();
	            return false;
	        }
	    }
	    return true;
	}
		
function check(){

	document.userForm.roleList.value = getSelectedData('userForm','roleListRight');
	
	return true;
} 
</script>

