<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

    List<Button> buttons=new ArrayList<Button>();
    buttons.add(new Button("save","�� ��","cus010101","saveData(document.forms[0])"));
	buttons.add(new Button("back", "����", "cus999991", "history.back()"));
    pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="��������Ϣ�޸�" />
		<lemis:tabletitle title="��������Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/CIOperAction.do?method=saveModifiedCI" method="POST">
				<tr>
					<lemis:texteditor property="ciid" label="������ID" required="true" disable="true" maxlength="20" />
					<lemis:texteditor property="ciname" label="����������" required="true" disable="false" maxlength="20" />
					<lemis:codelisteditor type="citype" isSelect="true" label="����������" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cilocation" label="������λ��" required="false" disable="false" maxlength="20" />
					<lemis:texteditor property="civersion" label="������汾" required="false" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:texteditor property="cidescription" label="����������" required="false" disable="false" maxlength="60" colspan="5" />
				</tr>
				<tr>
					<lemis:codelisteditor type="cistate" isSelect="true" label="������״̬" redisplay="true" required="true" />
					<lemis:texteditor property="cisupplier" label="�����Ӧ��" required="false" disable="false" maxlength="50" colspan="7" />					
				</tr>
				<tr>
					<lemis:texteditor property="cicharger" label="������" disable="false"  maxlength="20"/>
					<lemis:formateditor mask="date" property="cidepredt" required="false" label="�۾ɽ�������" disable="false" format="true"/>
					<lemis:formateditor mask="date" property="cigrtdt" required="false" label="��������" disable="false" format="true"/>
				</tr>
				<tr>
					<td>������</td>
					<td><lemis:operator/></td>
					<td>�������</td>
					<td ><lemis:operorg/></td>
					<td>��������</td>
					<td><lemis:operdate/></td>
				</tr>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

