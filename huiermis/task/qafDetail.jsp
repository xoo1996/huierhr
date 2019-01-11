<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��", "saveData(document.forms[0])");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskpnlnm","����ͺ�");

	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
	function saveData(obj) {
		if(!checkValue(obj)){
			return false;
		}
		obj.submit();
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�������ʼ�" />
	<lemis:tabletitle title="���񵥻�����Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do?method=saveQAF" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="���񵥺�" disable="true" />
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="true" />
				<lemis:texteditor property="tskpnlqnt" label="�������" disable="true" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="false"
					label="Ҫ���������" disable="true" format="true" />
				<lemis:texteditor property="tskbilopr" label="�Ƶ���" disable="true" />
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="�����´�����" disable="true" format="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="���Ҫ��" disable="true"  colspan="5"/>
			</tr>
			
			
			<lemis:tabletitle title="�ʼ���Ϣ"/>
			<table class="tableInput">
				<lemis:editorlayout/>
				
				<tr>
					<lemis:texteditor property="pnlqapnl" label="���񵥱��뷶Χ"  
						required="true" disable="true" colspan="4"/>
					<td>��������</td>
					<td><lemis:operdate/></td>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="pnlqaopra" isSelect="false" label="����Ա1"
						redisplay="true" required="true" dataMeta="userList" /> --%>
					<lemis:texteditor property="pnlqaopra" label="����Ա1"  
						required="true" disable="true"/>
					<lemis:texteditor property="pnlqapnla" label="����Ա1������"  
						required="true" disable="true" colspan="4"/>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="qachka" label="������1" isSelect="false"
						required="true" /> --%>
					<lemis:texteditor property="pnlqarsta" label="������1"  
						required="true" disable="true"/>
				</tr>
				
				<tr>
					<lemis:codelisteditor type="pnlqaoprb" isSelect="true" label="����Ա2"
						redisplay="true" required="true" dataMeta="userList" />
					<lemis:texteditor property="pnlqapnlb" label="����Ա2������"  
						required="true" disable="false" colspan="4"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkb" label="������2" isSelect="true"
						required="true" />
				</tr>
				
				<tr>
					<lemis:codelisteditor type="pnlqaoprc" isSelect="true" label="����Ա3"
						redisplay="true" required="false" dataMeta="userList" />
					<lemis:texteditor property="pnlqapnlc" label="����Ա3������"  
						required="false" disable="false" colspan="4"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkc" label="������3" isSelect="true"
						required="false" />
				</tr>
				
				<tr>
					<lemis:texteditor property="pnlqant" label="��ע" disable="false" colspan="6"/>
				</tr>
				
			</table>
			
		</html:form>
	</table>

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>