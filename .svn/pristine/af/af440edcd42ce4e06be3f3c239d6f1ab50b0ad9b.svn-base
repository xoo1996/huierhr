<!-- basicinfo/queryfamily.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String stringData = request.getParameter("stringData");
	String url=request.getParameter("url2");
	String today = DateUtil.getDate().toString();
	if (url==null) {url="";}
    	Map buttons=new LinkedHashMap();
    buttons.put("保 存","saveData(this.form)");
    buttons.put("返 回","editData()");
	buttons.put("关 闭","closeWindow(\"queryPersonForm,ResumesForm\")");
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="社会关系"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac002" label="公民身份号码"/>
				<lemis:texteditor property="aac003" label="姓名"/>
				<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aac005" label="民族" isSelect="false" redisplay="true"/>
				<lemis:texteditor property="aac026" label="家庭地址"/>
				<lemis:texteditor property="aae005" label="联系电话"/>
			</tr>
		</table>
		<lemis:tabletitle title="社会关系信息"/>
		<table class="tableinput">
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac0c1" label="关系" disable="false" maxlength="20"/>
				<lemis:texteditor property="aab004" label="单位" disable="false" maxlength="80"/>
			</tr>
			<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="5"/>
			<tr>
				<td>经办人</td>
				<td><lemis:operator/></td>
				<td>经办机构</td>
				<td colspan="2"><lemis:operorg/></td>
				<td>经办日期</td>
				<td colspan="3"><lemis:operdate/></td>
			</tr>
			<html:hidden property="aac001"/>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<INPUT name="today" value="<%=today%>" type="hidden" >
			<lemis:buttons buttonMeta="button"/>
			</html:form>
		</table>		
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	function saveData(obj){
	if(!checkValue(obj)){
	return false;
	}
	var aae030 = obj.aae030.value;
	var aae031 = obj.aae031.value;
	var today  = obj.today.value;
	if (!(compareDate(today, aae030))) {
	alert("起始日期不能晚于当前日期，请确认！");
	obj.aae030.focus();
	return false;
	}
	if(aae031 != "" ){
	if (!(compareDate(aae031, aae030))) {
	alert("终止日期不能早于起始日期，请确认！");
	obj.aae031.focus();
	return false;
	}
	}
	obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/resumesAction.do?method=save"+"&url2=<%=url%>"+'&';
	obj.submit();
	}
	function editData(){
	var obj = document.all.ResumesForm;
	obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/resumesAction.do?method=back"+"&url2=<%=url%>"+'&';
	obj.submit();
	}
</script>
