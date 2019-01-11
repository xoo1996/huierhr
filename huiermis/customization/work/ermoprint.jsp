<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src="/huiermis/js/laydate/laydate.js"></script>
<script language="javascript">
	function print(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="��Ĥ�ʼ��ӡ" />
	<lemis:tabletitle title="��Ĥ�ʼ��ӡ" />
	<table class="TableInput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=ermozjprint"
			method="POST">
			<tr>
				<lemis:texteditor property="tmkshmk" required="true" label="�ʼ�Ա���"
					disable="false" />
			</tr>

			<tr>
			    <lemis:formateditor mask="date" format="true" property="start"
					label="ʱ���" required="true" disable="false" />
				<lemis:texteditor property="zjstart" required="true" label="����"
					disable="false"
					 />
					 <lemis:texteditor property="zjstartsec" value="00" required="true" label="����"
					disable="false"
					 />
			</tr>
			<tr>
				 <lemis:formateditor mask="date" format="true" property="end"
					label="��" required="true" disable="false" />
				<lemis:texteditor property="zjend" required="true" label="����"
					disable="false"
					 />
					 <lemis:texteditor property="zjendsec" required="true" value="00" label="����"
					disable="false"
					 />
			</tr>
		</html:form>
	</table>
	<br />
	<div align="right">
		<input type="button" name="openwin" value="&nbsp;��&nbsp;&nbsp;ӡ&nbsp;"
			class='buttonGray' onclick="print(document.forms[0])"> <input
			type="reset" name="Submit" value="&nbsp;��&nbsp;&nbsp;��&nbsp;"
			class='buttonGray'>
	</div>
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
