<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
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
	<lemis:title title="�ؿ��" />
	 <lemis:tabletitle title="��ӡ�ؿ��" />
	<table class="TableInput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=huikuang" method="POST">
			<tr>
			    <lemis:texteditor property="ivtgcltid" required="true" label="�ͻ�����"
					disable="false" />
				<lemis:texteditor property="ivtyear" required="true" label="��"
					disable="false" maxlength="4" />
		   </tr>
		   <tr></tr>
		   <tr>
				<lemis:texteditor property="ivtmonth" required="true" label="����"
					disable="false" maxlength="2" />
				<lemis:texteditor property="ivtpqnt" required="true" label="����"
					disable="false" maxlength="2" />
				
			</tr>
		</html:form>
	</table>
	<div align="right"><input type="button"
		name="openwin" value="&nbsp;��&nbsp;&nbsp;ӡ&nbsp;" class='buttonGray'
		onclick=
	print(document.forms[0]);;
> <input type="reset"
		name="Submit" value="&nbsp;��&nbsp;&nbsp;��&nbsp;" class='buttonGray'></div>
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
