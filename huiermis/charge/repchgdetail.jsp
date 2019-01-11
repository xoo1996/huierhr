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
	buttons.put("�� ��","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	/*$(document).ready(function(){
		$("select[name=folischg]").val("1");
	});  */
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("ȷʵҪ�շ���")) {
			obj.submit();
		}
		else
			return t;
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="ά���շ�����" />
	<lemis:tabletitle title="ά����Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveRepairCharge">
			<tr>
				<html:hidden property="repidentity" />
				<lemis:texteditor property="repfolid" label="������" required="true"
					disable="true" />
				<lemis:texteditor property="repcltnm" label="�û�����" required="true"
					disable="true" />
				<lemis:texteditor property="reppnm" label="�������ͺ�" disable="true"
					required="true" />
				<html:hidden property="pdtid" />
			</tr>
			<tr>
				<lemis:texteditor property="repid" label="������" disable="true"
					required="true" />
				<lemis:texteditor property="repgctnm" label="���޵�λ" disable="true"
					required="true" />
				<html:hidden property="repgctid" />
				<lemis:formateditor mask="date" format="true" property="repdate"
					label="��������" disable="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="repfree" isSelect="false" label="������"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="reppreamt" isSelect="false"
					label="ά�޷�֪ͨ" redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="false" label="�������"
					redisplay="true" required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="��������" disable="true"
					required="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repnote" label="��ע" disable="true"
					required="false" colspan="5" />
			</tr>
			<lemis:tabletitle title="ά�޽��" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="repconfirmed" label="����ȷ��"
						disable="true" required="false" colspan="5" />
				</tr>
				<tr>
					<lemis:texteditor property="repaction1" label="ά�޴�ʩһ"
						disable="true" required="false" />
					<lemis:texteditor property="repaction2" label="ά�޴�ʩ��"
						disable="true" required="false" />
					<lemis:texteditor property="repaction3" label="ά�޴�ʩ��"
						disable="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="repaction4" label="ά�޴�ʩ��"
						disable="true" required="false" />
					<lemis:texteditor property="repaction5" label="ά�޴�ʩ��"
						disable="true" required="false" />
					<lemis:texteditor property="repaction6" label="ά�޴�ʩ��"
						disable="true" required="false" />
				</tr>
				<tr>
				   <lemis:texteditor property="repaprc" label="����Ԥ��" disable="true"
						required="false" />
					<lemis:texteditor property="repamt" label="���úϼ�" disable="true"
						required="false" />
					<lemis:codelisteditor type="repoprcd" isSelect="false" label="ά��Ա"
						redisplay="true" required="false" dataMeta="userList" />
					
				</tr>
				<tr>
				     <lemis:codelisteditor type="repregnames" isSelect="false"
						label="����ά��Ա" redisplay="true" required="false"
						dataMeta="userList" />
					<lemis:texteditor property="repattention" label="ע������"
						disable="true" required="false" colspan="3" />
				</tr>
				<tr>
					<lemis:texteditor property="reptip" label="��ܰ��ʾ1" disable="true"
						required="false" />
					<lemis:texteditor property="reptip1" label="��ע" disable="true"
						required="false" />
					<lemis:formateditor mask="date" format="true" property="repfdate"
						label="�깤����" required="false" />
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="folischg" isSelect="true" label="ά�޷��Ƿ�����"
						redisplay="true" required="true" /> --%>
					<td>�շ�����</td>
					<td><lemis:operdate/></td>
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

