<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("维修完成","finish(document.forms[0])");
    buttons.put("等待费用通知","sleep(document.forms[0])");
	buttons.put("返回","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	
	function finish(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("保存数据并确认维修完成？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=finish&tp=ear"/>' + getAlldata(obj);
			obj.submit();
		}
	};
	
	function sleep(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("保存数据并等待费用通知？")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=saveModified&require=sleep&tp=ear"/>' + getAlldata(obj);
			obj.submit();
		}
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="录入维修措施" />
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
				<html:hidden property="reppid" />
				<html:hidden property="repcpy"/>
			</tr>
			<tr>
				<lemis:texteditor property="repid" label="机身编号" disable="true"
					required="true" />
				<lemis:texteditor property="repgctnm" label="送修单位" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="repdate"
					label="送修日期" disable="true" required="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="repfree" isSelect="false" label="保修期"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="reppreamt" isSelect="false"
					label="维修费通知" redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="false" label="修理类别"
					redisplay="true" required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="故障详情" disable="true"
					required="false" colspan="5" />
			</tr>

			<lemis:tabletitle title="维修结果" />
			<table class="tableinput">
				<lemis:editorlayout />
				
				
				<tr>
					<lemis:texteditor property="repaction1" label="维修措施" disable="false"
						required="false"  />
					<lemis:formateditor mask="nnnn.nn" property="repamt" label="费用合计"
						disable="false" required="true" />
					<td>维修员</td>
					<td><lemis:operator/></td>	
				</tr>
				<tr>
					<lemis:texteditor property="repnote" label="备注" disable="false"
						required="false" colspan="5" />
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

