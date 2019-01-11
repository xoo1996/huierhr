<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("����","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="����ͻ���Ϣ�޸�" />
	<lemis:tabletitle title="����ͻ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/GroupClientAction.do?method=saveModified"
			method="POST">
			<tr>
				<lemis:texteditor property="gctid" label="�ͻ�����" disable="true"
					required="true" maxlength="6" />
				<lemis:texteditor property="gctnm" label="�ͻ�����" disable="false"
					required="true" maxlength="20" />
				<lemis:codelisteditor type="gcttype" isSelect="true" label="����"
					redisplay="true" required="true" />
			</tr>
			<tr>
			
				<lemis:codelisteditor type="gctarea" isSelect="true" label="��������"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="gctprovince" isSelect="true"
					label="����ʡ��" redisplay="true" required="true" />
				<lemis:texteditor property="gctptcd" label="��������" required="false"
					disable="false" maxlength="6" />
			</tr>
			<tr>
				
				<lemis:texteditor property="gctemail" label="Email" required="false"
					disable="false" maxlength="30" />
				<lemis:texteditor property="gctcnm" label="��ϵ��" required="false"
					disable="false" maxlength="10" />
				<lemis:texteditor property="gcttel" label="��ϵ�绰" required="false"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<lemis:formateditor mask="date" property="gctstart" label="��ҵʱ��"
					required="false" disable="false" />
				<lemis:texteditor property="gctaddr" label="��ϵ��ַ" disable="false"
					required="false" colspan="3" maxlength="80" />
			</tr>
			<tr>
				<lemis:codelisteditor type="gctvalid" isSelect="true" label="�Ƿ���Ч"
					redisplay="true" required="true" />
				<lemis:texteditor property="gctsname"  label="����ȫ��" maxlength="50"
					disable="false" required="true" colspan="3" />
			</tr>
			<tr> 
				<lemis:texteditor property="gctnt" label="��ע" required="false"
					disable="false" colspan="5" maxlength="80" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

