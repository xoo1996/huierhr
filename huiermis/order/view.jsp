<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返回","history.back()");
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
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="定制机信息查询" />
	<lemis:tabletitle title="定制机信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=saveModifiedCI"
			method="POST">
			<tr>
				<lemis:texteditor property="tmkfno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmkcltnm" label="用户姓名" required="true"
					disable="true" />
				<lemis:texteditor property="tmkpnm" label="耳机名称" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmksid" label="机身编号" disable="true"
					required="true" />
				<lemis:codelisteditor type="tmkplr" label="耳机类型" isSelect="false"
					required="true" />
				<lemis:codelisteditor type="tmkpst" label="生产状态" isSelect="false"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkpnl" label="面板编号" required="false"
					disable="true" maxlength="20" />
				<lemis:texteditor property="tmkpls" label="增益" required="false"
					disable="true" maxlength="20" />
				<lemis:formateditor mask="date" format="true" property="tmkpdt"
					label="计划完工日期" disable="true" required="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkurg" isSelect="false" label="是否加急"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="tmkmixer" isSelect="false" label="配料员"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:formateditor mask="date" format="true" property="tmkmixdt"
					label="配料日期" required="false" disable="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkshmk" isSelect="false" label="外壳制作者"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:codelisteditor type="tmkshinst" isSelect="false"
					label="外壳安装者" redisplay="true" required="false" dataMeta="userList" />
				<lemis:texteditor property="tmknt" label="备注" disable="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" format="true" property="tmkfmdt"
					label="完工日期" required="false" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

