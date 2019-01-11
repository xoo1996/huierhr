<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保存检测结果","saveData(document.forms[0])");
    buttons.put("打印检测报告","print(document.forms[0])"); 
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
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
	function print(obj) {
		obj.action = '<html:rewrite page="/CustomizationAction.do?method=printTestReport&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="质量检测" />
	<lemis:tabletitle title="检验结果" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=updateTest"
			method="POST">
			<tr>
				<lemis:texteditor property="tmkfno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmksid" label="定制机条形码" disable="true"
					required="true" />
				<lemis:texteditor property="tmkpnm" label="助听器名称" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmktest1" label="外观" isSelect="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmktest2" label="饱和声输出" required="true"
					disable="false" />
				<lemis:texteditor property="tmktest3" label="最大值增益" required="true"
					disable="false" />
				<lemis:texteditor property="tmktest4" label="1600Hz增益"
					required="true" disable="false" />
			</tr>
			<tr>		
				<lemis:codelisteditor type="tmktest5" label="频率响应范围" isSelect="true"
					required="true" />
				<lemis:texteditor property="tmktest6" label="等效输入噪声" required="true"
					disable="false" />			
				<lemis:texteditor property="tmktest7" label="电池电流" required="true"
					disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="tmktest8" label="总谐波失真(500Hz)"
					required="true" disable="false" />
				<lemis:texteditor property="tmktest9" label="总谐波失真(800Hz)"
					required="true" disable="false" />
				<lemis:texteditor property="tmktest10" label="总谐波失真(1600Hz)"
					required="true" disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkchk" label="检验结果" required="true"
					disable="false" />
			</tr>
			<tr>
				<td>质检员</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>质检日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

