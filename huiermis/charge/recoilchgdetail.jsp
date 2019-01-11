<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("�� ��","history.back()");
	
	//List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "folno", "������"));

	 //pageContext.setAttribute("editors", editors);
    pageContext.setAttribute("button", buttons);
%>


<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('select[name=folischg]').val("1");
		$('input[name=fdtdprc]').attr("readonly","readonly");
		$('input[name=balance]').bind('blur',function(){
			var fdtprc = $('input[name=fdtprc]').val();
			var balance = $('input[name=balance]').val();
			$('input[name=fdtdprc]').val(fdtprc-balance);
		});
	});
</script>

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
    <lemis:title title="���ƻ��շ�����" />
	<lemis:tabletitle title="�շ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveRecoilCharge"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="������" disable="true" required="true" 
					 maxlength="6" />
				
				<lemis:texteditor property="foltype" label="��������" disable="true"
					required="true" maxlength="20" />
				<lemis:texteditor property="folctnm" label="���Ƶ�λ" disable="true"
					required="true" />
			</tr>
			<tr>
			
				<lemis:codelisteditor type="folischg" isSelect="true" label="�Ƿ����շ�"
					redisplay="true" required="true" />
				<lemis:texteditor property="cltnm" label="�û�����" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				<%-- <lemis:texteditor property="deposit" label="����" required="true"
					disable="true" maxlength="30" /> --%>
				<lemis:texteditor property="ictpcd" label="�ʱ�" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="��ϵ�绰" required="false"
					disable="true" maxlength="20" />
				<td>�շ�����</td>
				<td><lemis:operdate/></td>
			</tr>
			<tr>
				<lemis:texteditor property="pdtnm" label="���ƻ��ͺ�" disable="true"
					required="false"/>
				<lemis:texteditor property="ictaddr" label="�û���ַ" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			<tr>
				<html:hidden property="pdtid" />
				<html:hidden property="pdtut"/>
				<lemis:texteditor property="fdtprc" label="�ۼ�" disable="true"
					required="false"  />
				<lemis:texteditor property="balance" label="�˻���" disable="false"
					required="true"  />
				<lemis:texteditor property="fdtdprc" label="�˻��û�����" disable="false" 
					required="false"  />
				<%--  <lemis:formateditor mask="########" property="balance"  label="�˻���" disable="false" 
					required="true" /> --%>
				
			</tr>
			<tr>
				<lemis:texteditor property="folnt" label="��ע" required="false"
					disable="false" colspan="5" maxlength="80" />
				
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


