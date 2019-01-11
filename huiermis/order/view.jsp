<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
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
	<lemis:title title="���ƻ���Ϣ��ѯ" />
	<lemis:tabletitle title="���ƻ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=saveModifiedCI"
			method="POST">
			<tr>
				<lemis:texteditor property="tmkfno" label="������" required="true"
					disable="true" />
				<lemis:texteditor property="tmkcltnm" label="�û�����" required="true"
					disable="true" />
				<lemis:texteditor property="tmkpnm" label="��������" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmksid" label="������" disable="true"
					required="true" />
				<lemis:codelisteditor type="tmkplr" label="��������" isSelect="false"
					required="true" />
				<lemis:codelisteditor type="tmkpst" label="����״̬" isSelect="false"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkpnl" label="�����" required="false"
					disable="true" maxlength="20" />
				<lemis:texteditor property="tmkpls" label="����" required="false"
					disable="true" maxlength="20" />
				<lemis:formateditor mask="date" format="true" property="tmkpdt"
					label="�ƻ��깤����" disable="true" required="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkurg" isSelect="false" label="�Ƿ�Ӽ�"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="tmkmixer" isSelect="false" label="����Ա"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:formateditor mask="date" format="true" property="tmkmixdt"
					label="��������" required="false" disable="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkshmk" isSelect="false" label="���������"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:codelisteditor type="tmkshinst" isSelect="false"
					label="��ǰ�װ��" redisplay="true" required="false" dataMeta="userList" />
				<lemis:texteditor property="tmknt" label="��ע" disable="true" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" format="true" property="tmkfmdt"
					label="�깤����" required="false" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

