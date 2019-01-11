<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>>
<%@ page import="org.radf.plat.commons.DateUtil" %>>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    Date dt = new Date();
	dt = DateUtil.getStepDay(dt,2);
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	String str = f.format(dt);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
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
	<!--<lemis:clean names="CustomizationForm" force="true" />-->
	<lemis:errors />
	<lemis:title title="配料" />
	<lemis:tabletitle title="定制机信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do?method=updateArrangement"
			method="POST">
			<tr>
				<lemis:texteditor property="tmkfno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmkcltid" label="用户代码" required="true"
					disable="true" />
				<lemis:texteditor property="tmkcltnm" label="用户姓名" required="true"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tmksid" label="机身编号" disable="true"
					required="true" />
				<lemis:texteditor property="tmkpid" label="产品代码" disable="true"
					required="true" />
				<lemis:texteditor property="tmkpnm" label="助听器名称" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmkurg" label="是否加急" required="true"
					isSelect="true" />
				<lemis:texteditor property="tmkpnl" label="面板编码" required="true"
					disable="false" maxlength="20" />
				<lemis:formateditor mask="date" property="tmkpdt" required="true"
					label="计划完工日期" disable="false" format="true" value="<%=str%>"/>
			</tr>
			<tr>
				<td>配料员</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>配料日期</td>
				<td><lemis:operdate /></td>
			</tr>
			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

