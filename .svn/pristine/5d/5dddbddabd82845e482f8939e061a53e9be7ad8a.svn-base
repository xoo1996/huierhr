<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("惠耳打印","huier_print(document.forms[0])");
	buttons.put("杰闻打印","jiewen_print(document.forms[0])");
	buttons.put("返回","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function print(obj) {
		obj.action = '<html:rewrite page="/QAAction.do?method=printTestReport3&"/>' + getAlldata(obj);
		obj.submit();
	};
	function huier_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/qa/QAAction.do?method=printTestReport3&type=huier&id="
				+ $("input[name=qaid]").val();
	};
	function jiewen_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/qa/QAAction.do?method=printTestReport3&type=jiewen&id="
				+ $("input[name=qaid]").val();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="修改质检记录" />
	<lemis:tabletitle title="质检基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/QAAction.do?method=saveModified" method="POST">
			<html:hidden property="qaid" />
			<tr>
				<lemis:texteditor property="qafno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="qasid" label="机身编号" disable="true"
					required="true" />
				<lemis:texteditor property="qapnm" label="助听器型号" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="qacltnm" label="用户姓名" required="true"
					disable="true" />
				<lemis:codelisteditor type="qatype" label="质检类别" isSelect="false"
					required="true" />
				<lemis:codelisteditor type="qastatus" label="质检状态" isSelect="false"
					required="true" />
			</tr>
			<lemis:tabletitle title="质检结果" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="qatest1" label="外观" isSelect="false"
						required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="qatest2" label="饱和声输出" required="false"
						disable="true" />
					<lemis:texteditor property="qatest3" label="最大值增益" required="false"
						disable="true" />
					<lemis:texteditor property="qatest4" label="1600Hz增益(检1)"
						required="false" disable="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="qatest5" label="频率响应范围(检1)"
						isSelect="false" required="false" />
					<lemis:texteditor property="qatest6" label="等效输入噪声"
						required="false" disable="true" />
					<lemis:texteditor property="qatest7" label="电池电流" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="qatest8" label="总谐波失真(500Hz)"
						required="false" disable="true" />
					<lemis:texteditor property="qatest9" label="总谐波失真(800Hz)"
						required="false" disable="true" />
					<lemis:texteditor property="qatest10" label="总谐波失真(1600Hz)"
						required="false" disable="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="qachk" label="检验结果" required="true"
						isSelect="false" />
					<lemis:texteditor property="qatest11" label="高频平均值(检2)" required="true"
						disable="true" />
					<lemis:texteditor property="qatestft" label="频率响应范围(检2)" required="true"
						disable="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkopr" isSelect="false" label="质检员1"
						redisplay="true" required="false" dataMeta="userList" />
					<td>经办机构</td>
					<td><lemis:operorg /></td>
					<lemis:formateditor mask="date" format="true" property="qachkdt"
						label="质检日期" required="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkopr2" isSelect="false" label="质检员2"
						redisplay="true" required="false" dataMeta="userList" />
					<td>经办机构</td>
					<td><lemis:operorg /></td>
					<lemis:formateditor mask="date" format="true" property="qachkdt2"
						label="质检日期" required="false" />
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

