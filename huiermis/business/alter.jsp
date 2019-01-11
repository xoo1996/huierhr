<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String ictnm = (String)(request.getSession().getAttribute("ictnm"));
    String temp01 = (String)(request.getSession().getAttribute("temp01"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("����","history.go(-2)");
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
	};
	function change() {
		var i1 =  $("input[name=ivtlmqnt]").val();
		var i2 =  $("input[name=ivtlsqnt]").val();
		alert("shfcj" +i1 + i2);
		var i3 = parseInt(i1) + parseInt(i2);
	    $("input[name=ivt]").val(i3);
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="�޸ı���" />
	<lemis:tabletitle title="�½���ϸ��Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=saveModified" method="POST">
		<html:hidden property="ivtcltid"/>
		<html:hidden property="ivtpdtid"/>
		    <tr>
				<lemis:texteditor property="ivtyear" label="��" required="false"
					 disable="true" />
				<lemis:texteditor property="ivtmonth" label="��" disable="true"
					required="false" />
				<lemis:texteditor property="ivtgcltid" label="�ͻ�����" disable="true"
					required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="ictnm" label="���˿ͻ�" required="false"
					 value="<%=ictnm %>" disable="true" />
				<lemis:texteditor property="pdtnm" label="��Ʒ����" disable="true"
					required="false" />
				<lemis:texteditor property="pdtprc" label="�۸�" required="true"
					disable="false" />
			</tr>
			<tr>
					<lemis:texteditor property="ivtlmqnt" label="�����" required="true"
						disable="false" />
					<lemis:texteditor property="ivtlsqnt" label="������"  required="true"
						disable="false"  />
					<lemis:texteditor property="temp01" label="С��"  required="true" 
					 value="<%=temp01 %>" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="ivtpqnt" label="�ؿ���"
						required="true" disable="false" />
					<lemis:texteditor property="ivtdiscount" label="����"
						required="true" disable="false" />
					<lemis:texteditor property="ivtpamnt" label="Ӧ�տ�"
						required="true" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="ivtnote" label="��ע" required="false"
						disable="false" />
					
				</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

