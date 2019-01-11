<!-- basicinfo/familyred.jsp -->
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
    buttons.put("保 存","saveData(document.all.PersonFamilyForm)");
    buttons.put("返 回","editData()");
	buttons.put("关 闭","closeWindow(\"queryPersonForm,ResumesForm\")");
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人社会关系"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="公民身份号码"/>
					<lemis:texteditor property="aac003" label="姓名"/>
					<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" label="民族" isSelect="false" redisplay="true"/>
					<lemis:codelisteditor type="aac011" label="文化程度" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="aac021" label="失业证号码" />
				</tr>
				<html:hidden property="stringData" value="<%=stringData%>"/>
			</html:form>
		</table>
		<lemis:tabletitle title="社会关系信息"/>
		<table class="tableinput">
		<html:form action="/queryFamily.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
			<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="true" disable="false"/>
            <lemis:texteditor property="aac003" label="姓名" disable="false" required="true"/>
            <lemis:texteditor property="acc0c1" label="关系描述" disable="false" required="true"/>
			</tr>
			<tr>
			<lemis:texteditor property="aab004" label="单位名称" disable="false"  maxlength="30"/>
			<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator/></td>
				<td>经办机构</td>
				<td ><lemis:operorg/></td>
				<td>经办日期</td>
				<td ><lemis:operdate/></td>
			</tr>

			<html:hidden property="acc0c0"/>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<INPUT name="today" value="<%=today%>" type="hidden">
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
	obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/queryFamily.do?method=saveFamily"+"&url2=<%=url%>";
	obj.submit();
	}
	function editData(){
	var obj = document.all.PersonFamilyForm;
	
	obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/queryFamily.do?method=back"+"&url2=<%=url%>";
	obj.submit();
	}
</script>
