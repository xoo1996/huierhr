<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function print(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
	function danjia(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite page="/FeeAction.do?method=price"/>';
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="查询双耳率" />
	 <lemis:tabletitle title="查询双耳率" />
	<table class="TableInput">
		<lemis:editorlayout />
		<html:form action="/FeeAction.do?method=doubleratio" method="POST">
			<tr>
				<lemis:texteditor property="feeyear" required="true" label="开始年份"
					disable="false" maxlength="4" />
				<lemis:texteditor property="startMonth" required="true" label="开始月份"
					disable="false" maxlength="4" />
			
			
				<lemis:texteditor property="endYear" required="true" label="结束年份"
					disable="false" maxlength="4" />
			</tr>
			<tr>
				<lemis:texteditor property="endMonth" required="true" label="结束月份"
					disable="false" maxlength="4" />
			</tr>
		</html:form>
	</table>
	<div align="right">
	<input type="button" name="openwin" value="&nbsp;查询双耳率&nbsp;&nbsp;&nbsp;" class='buttonGray' onclick=print(document.forms[0]);;> 
	<input type="button" name="danerdanjia" value="&nbsp;查询单耳单价&nbsp;&nbsp;&nbsp;" class='buttonGray' onclick=danjia(document.forms[0]);;> 
	<input type="reset" name="Submit" value="&nbsp;重&nbsp;&nbsp;置&nbsp;" class='buttonGray'></div>
	<lemis:errors />
	<lemis:bottom/>
</lemis:body>
</html>
