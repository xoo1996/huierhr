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
	<lemis:title title="回款表" />
	 <lemis:tabletitle title="打印回款表" />
	<table class="TableInput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=huikuang" method="POST">
			<tr>
			    <lemis:texteditor property="ivtgcltid" required="true" label="客户代码"
					disable="false" />
				<lemis:texteditor property="ivtyear" required="true" label="年"
					disable="false" maxlength="4" />
		   </tr>
		   <tr></tr>
		   <tr>
				<lemis:texteditor property="ivtmonth" required="true" label="从月"
					disable="false" maxlength="2" />
				<lemis:texteditor property="ivtpqnt" required="true" label="到月"
					disable="false" maxlength="2" />
				
			</tr>
		</html:form>
	</table>
	<div align="right"><input type="button"
		name="openwin" value="&nbsp;打&nbsp;&nbsp;印&nbsp;" class='buttonGray'
		onclick=
	print(document.forms[0]);;
> <input type="reset"
		name="Submit" value="&nbsp;重&nbsp;&nbsp;置&nbsp;" class='buttonGray'></div>
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
