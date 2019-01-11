<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("费用已通知","notify(document.forms[0])");
    buttons.put("不维修","stop(document.forms[0])");
	buttons.put("返回","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script language="javascript">
	function notify(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("保存数据并继续厂修？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=saveModified&require=out"/>' + getAlldata(obj);
			obj.submit();
		}
	};
	function stop(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("不维修并取消订单？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=change&require=stop&"/>' + getAlldata(obj);
			obj.submit();
		}
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="费用通知" />
	<lemis:tabletitle title="维修信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/RepairAction.do?method=saveModified" method="POST">
			<tr>
				<html:hidden property="repidentity" />
				<lemis:texteditor property="repfolid" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="repcltnm" label="用户姓名" required="true"
					disable="true" />
				<lemis:texteditor property="reppnm" label="助听器型号" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="repid" label="机身编号" disable="true"
					required="true" />
				<lemis:texteditor property="repgctid" label="送修单位" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="repdate"
					label="送修日期" disable="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="repfree" isSelect="true" label="保修期"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="reppreamt" isSelect="true" label="维修费通知"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="true" label="修理类别"
					redisplay="true" required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="故障详情" disable="false"
					required="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repnote" label="备注" disable="false"
					required="false" colspan="5" />
			</tr>
			<lemis:tabletitle title="维修结果" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="repcpy" isSelect="false" label="维修厂商"
						redisplay="true" required="false" />
					<lemis:formateditor mask="nnnn.nn" property="repamt" label="维修费用"
						disable="false" required="true" />
					<td colspan="2" style="text-align: left">※请修改为实际费用</td>
				</tr>
				<tr>
					<lemis:texteditor property="repconfirmed" label="厂修措施"
						disable="false" required="false" colspan="7" />
				</tr>
				<tr>
					<td>经办人</td>
					<td><lemis:operator /></td>
					<td>经办机构</td>
					<td><lemis:operorg /></td>
					<td>经办日期</td>
					<td><lemis:operdate /></td>
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

