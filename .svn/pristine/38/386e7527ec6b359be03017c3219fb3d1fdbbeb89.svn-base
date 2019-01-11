<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
	//buttons.put("返 回","go2Page(\"customization\",\"\")");
	buttons.put("返 回","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>

<script language="javascript">
	/* $(document).ready(function(){
	
	//如果生产状态不是订单未确认、配料待确认或者配料有误，则不能修改面板编号
	if (document.all("tmkpst").value != "0"&& document.all("tmkpst").value != "1"&& document.all("tmkpst").value != "2") {
					$("input[name=tmkpnl]").attr('readonly', 'readonly');
				}
			}); */
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="定制信息" />
	<lemis:tabletitle title="定制信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=saveModifiedCI"
			method="POST">
			<tr>
				<html:hidden property="folsta" />
				<lemis:texteditor property="tmkfno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmkcltnm" label="用户姓名" required="true"
					disable="true" />
				<html:hidden property="tmkcltid" />
				<lemis:texteditor property="tmkpnm" label="耳机名称" disable="true"
					required="true" />
				<html:hidden property="tmkpid" />
			</tr>
			<tr>
				<lemis:texteditor property="tmksid" label="机身编号" disable="false"
					required="true" />
				<lemis:codelisteditor type="tmkplr" label="耳机类型" isSelect="true"
					required="true" />
				<lemis:codelisteditor type="tmkpst" label="生产状态" isSelect="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkpnl" label="面板编号" required="false"
					disable="false" maxlength="20" />
				<lemis:texteditor property="tmkpls" label="增益" required="false"
					disable="false" maxlength="20" />
				<lemis:formateditor mask="date" format="true" property="tmkpdt"
					label="计划完工日期" disable="false" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkurg" isSelect="true" label="是否加急"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="tmkmixer" isSelect="true" label="配料员"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:formateditor mask="date" format="true" property="tmkmixdt"
					label="配料日期" required="false" disable="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkshmk" isSelect="true" label="外壳制作者"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:codelisteditor type="tmkshinst" isSelect="true" label="外壳安装者"
					redisplay="true" required="false" dataMeta="userList" />
				<lemis:formateditor mask="date" format="true" property="tmkfmdt"
					label="完工日期" required="false" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" format="true" property="tmkrependdt"
					label="保修截止日期" required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="tmknt" label="备注" disable="false" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

