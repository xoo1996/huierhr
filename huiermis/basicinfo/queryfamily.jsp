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
    buttons.put("�� ��","saveData(this.form)");
    buttons.put("�� ��","editData()");
	buttons.put("�� ��","closeWindow(\"queryPersonForm,ResumesForm\")");
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="����ϵ"/>
		<lemis:tabletitle title="���˻�����Ϣ"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac002" label="������ݺ���"/>
				<lemis:texteditor property="aac003" label="����"/>
				<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="false" redisplay="true"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aac005" label="����" isSelect="false" redisplay="true"/>
				<lemis:texteditor property="aac026" label="��ͥ��ַ"/>
				<lemis:texteditor property="aae005" label="��ϵ�绰"/>
			</tr>
		</table>
		<lemis:tabletitle title="����ϵ��Ϣ"/>
		<table class="tableinput">
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac0c1" label="��ϵ" disable="false" maxlength="20"/>
				<lemis:texteditor property="aab004" label="��λ" disable="false" maxlength="80"/>
			</tr>
			<lemis:texteditor property="aae013" label="��ע" disable="false" maxlength="100" colspan="5"/>
			<tr>
				<td>������</td>
				<td><lemis:operator/></td>
				<td>�������</td>
				<td colspan="2"><lemis:operorg/></td>
				<td>��������</td>
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
	alert("��ʼ���ڲ������ڵ�ǰ���ڣ���ȷ�ϣ�");
	obj.aae030.focus();
	return false;
	}
	if(aae031 != "" ){
	if (!(compareDate(aae031, aae030))) {
	alert("��ֹ���ڲ���������ʼ���ڣ���ȷ�ϣ�");
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
