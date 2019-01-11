<!-- recommendation/personguidance/add.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%
	List buttons = new ArrayList();
	buttons.add(new Button("Btn_save", "�� ��", "rec050107","saveData(document.forms[0])"));
	buttons.add(new Button("Btn_back", "�� ��", "rec999997","go2Page(\"recommendation\",\"1\")"));
	buttons.add(new Button("Btn_close", "�� ��", "rec999999","closeWindow(\"Rec0201Form\")"));
	pageContext.setAttribute("button", buttons);

%>

<lemis:base />
<html>

<lemis:body>
	<lemis:errors />
	<lemis:title title="����ָ������" />
	<html:form action="/Rec05Action.do" method="POST">
		<lemis:tabletitle title="����ָ������" />
		<table class="tableInput">
			<lemis:editorlayout />	
			<tr>
			    <html:hidden property="aac001" />
				<lemis:formateditor mask="card" property="aac002" label="������ݺ���"	disable="true" required="false" />
				<lemis:texteditor property="aac003" label="����" required="false" disable="true" />
				<lemis:codelisteditor type="aac004" isSelect="false" redisplay="true" label="�Ա�" />
			</tr>
			<tr>
				<lemis:formateditor mask="date" property="acc231" label="ָ������"	disable="false" required="true" />
				<lemis:texteditor property="acc232" label="ָ������" required="true" disable="false" maxlength="60"/>
				<lemis:texteditor property="acc233" label="ָ����Ա" required="true" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<lemis:texteditor property="acc234" label="ָ��Ч��" required="false" disable="false" maxlength="20"/>
				<lemis:texteditor property="acc235" label="�����������" required="false" disable="false" maxlength="60"/>
				<lemis:codelisteditor type="acc236" isSelect="true" redisplay="true" label="�Ƿ�μ�ְҵ��ѵ" />
			</tr>
			<tr>
				<lemis:texteditor property="acc237" label="ְҵ��ѵ���" disable="false" maxlength="100" colspan="5" />

			</tr>

			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		</table>

	</html:form>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>
<script language="javascript" >
  

	function saveData(obj){
	
	if(!checkValue(obj)){
	return false;
	}
	obj.action ="/" + lemis.WEB_APP_NAME + "/recommendation/Rec05Action.do?method=add&";
	obj.submit();
	}
	

</script>

