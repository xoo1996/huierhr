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
    buttons.put("保 存","saveData(document.forms[0])");
	buttons.put("返回","history.go(-2)");
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
	<lemis:title title="修改报表" />
	<lemis:tabletitle title="月结详细信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=saveModified" method="POST">
		<html:hidden property="ivtcltid"/>
		<html:hidden property="ivtpdtid"/>
		    <tr>
				<lemis:texteditor property="ivtyear" label="年" required="false"
					 disable="true" />
				<lemis:texteditor property="ivtmonth" label="月" disable="true"
					required="false" />
				<lemis:texteditor property="ivtgcltid" label="客户代码" disable="true"
					required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="ictnm" label="个人客户" required="false"
					 value="<%=ictnm %>" disable="true" />
				<lemis:texteditor property="pdtnm" label="商品名称" disable="true"
					required="false" />
				<lemis:texteditor property="pdtprc" label="价格" required="true"
					disable="false" />
			</tr>
			<tr>
					<lemis:texteditor property="ivtlmqnt" label="结存数" required="true"
						disable="false" />
					<lemis:texteditor property="ivtlsqnt" label="补入数"  required="true"
						disable="false"  />
					<lemis:texteditor property="temp01" label="小计"  required="true" 
					 value="<%=temp01 %>" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="ivtpqnt" label="回款数"
						required="true" disable="false" />
					<lemis:texteditor property="ivtdiscount" label="扣率"
						required="true" disable="false" />
					<lemis:texteditor property="ivtpamnt" label="应收款"
						required="true" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="ivtnote" label="备注" required="false"
						disable="false" />
					
				</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

