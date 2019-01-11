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
    buttons.put("�� ��","saveData(document.all.PersonFamilyForm)");
    buttons.put("�� ��","editData()");
	buttons.put("�� ��","closeWindow(\"queryPersonForm,ResumesForm\")");
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="��������ϵ"/>
		<lemis:tabletitle title="���˻�����Ϣ"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="������ݺ���"/>
					<lemis:texteditor property="aac003" label="����"/>
					<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="false" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" label="����" isSelect="false" redisplay="true"/>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="aac021" label="ʧҵ֤����" />
				</tr>
				<html:hidden property="stringData" value="<%=stringData%>"/>
			</html:form>
		</table>
		<lemis:tabletitle title="����ϵ��Ϣ"/>
		<table class="tableinput">
		<html:form action="/queryFamily.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
			<lemis:formateditor mask="card" property="aac002" label="������ݺ���" required="true" disable="false"/>
            <lemis:texteditor property="aac003" label="����" disable="false" required="true"/>
            <lemis:texteditor property="acc0c1" label="��ϵ����" disable="false" required="true"/>
			</tr>
			<tr>
			<lemis:texteditor property="aab004" label="��λ����" disable="false"  maxlength="30"/>
			<lemis:texteditor property="aae013" label="��ע" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator/></td>
				<td>�������</td>
				<td ><lemis:operorg/></td>
				<td>��������</td>
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
