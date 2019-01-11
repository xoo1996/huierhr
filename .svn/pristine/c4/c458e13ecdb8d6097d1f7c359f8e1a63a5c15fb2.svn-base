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
	<lemis:title title="团体客户信息修改" />
	<lemis:tabletitle title="团体客户信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/GroupClientAction.do?method=saveModified"
			method="POST">
			<tr>
				<lemis:texteditor property="gctid" label="客户代码" disable="true"
					required="true" maxlength="6" />
				<lemis:texteditor property="gctnm" label="客户名称" disable="false"
					required="true" maxlength="20" />
				<lemis:codelisteditor type="gcttype" isSelect="true" label="类型"
					redisplay="true" required="true" />
			</tr>
			<tr>
			
				<lemis:codelisteditor type="gctarea" isSelect="true" label="所属区域"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="gctprovince" isSelect="true"
					label="所在省份" redisplay="true" required="true" />
				<lemis:texteditor property="gctptcd" label="邮政编码" required="false"
					disable="false" maxlength="6" />
			</tr>
			<tr>
				
				<lemis:texteditor property="gctemail" label="Email" required="false"
					disable="false" maxlength="30" />
				<lemis:texteditor property="gctcnm" label="联系人" required="false"
					disable="false" maxlength="10" />
				<lemis:texteditor property="gcttel" label="联系电话" required="false"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<lemis:formateditor mask="date" property="gctstart" label="开业时间"
					required="false" disable="false" />
				<lemis:texteditor property="gctaddr" label="联系地址" disable="false"
					required="false" colspan="3" maxlength="80" />
			</tr>
			<tr>
				<lemis:codelisteditor type="gctvalid" isSelect="true" label="是否有效"
					redisplay="true" required="true" />
				<lemis:texteditor property="gctsname"  label="名称全称" maxlength="50"
					disable="false" required="true" colspan="3" />
			</tr>
			<tr> 
				<lemis:texteditor property="gctnt" label="备注" required="false"
					disable="false" colspan="5" maxlength="80" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

