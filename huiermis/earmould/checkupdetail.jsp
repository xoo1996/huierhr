<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("��������","saveData(document.forms[0])");
    buttons.put("�� ��","document.forms[0].reset();");
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
</script>
<script type="text/javascript">
   $(document).ready(function(){
         $('#tmeappear').val('yes');
         $('#tmeden').val('yes');
         $('#tmeckt').val('yes');
  });
</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="��ģ�������" />
	<lemis:tabletitle title="������" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarMouldAction.do?method=updateCheckup"
			method="POST">
			<tr>
				<lemis:texteditor property="tmeno" label="������" required="true"
					disable="true" />
				<lemis:texteditor property="tmecltnm" label="�û�����" required="true"
					disable="true" />
				<lemis:texteditor property="tmepnm" label="��������" disable="true"
					required="true" />
			</tr>
			<tr>
			    <lemis:texteditor property="tmesid" label="��ģ���" disable="true"/>
			    <lemis:codelisteditor type="tmemat" label="��ģ����" required="false" isSelect="false"/>
			    <lemis:codelisteditor type="tmecls" label="���"  required="false" isSelect="false"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="tmeappear" label="��ۼ���" required="true"
					isSelect="true" />
				<lemis:codelisteditor type="tmeden" label="�����Լ���" required="true"
					isSelect="true"  />
				<lemis:codelisteditor type="tmeckt" label="�ʼ���" required="true"
					isSelect="true" />
			</tr>
			<tr>
				<td>�ʼ�Ա</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>�ʼ�����</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>