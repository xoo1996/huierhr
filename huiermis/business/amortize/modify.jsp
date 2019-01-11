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
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
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
<lemis:errors />
<lemis:title title="修改摊销信息" />
	<lemis:tabletitle title="摊销信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=modifyAmortize" method="POST">
			<tr>
				<lemis:texteditor property="arzgctnm" label="客户名称" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="arzdt"
					label="登记日期" disable="true" required="true" />
				<lemis:texteditor property="arzoprnm" label="登记员" disable="true"
					required="true" />
			</tr>
			
			<tr>
				<lemis:texteditor property="arzitem" label="摊销项目" disable="true"
					required="true" />
				<lemis:texteditor property="arzamount" label="摊销总金额" disable="true"
					required="true" />
				<lemis:texteditor property="arzmonth" label="摊销月份" disable="true"
					required="true" />
			</tr>
			
			<tr>
				<lemis:texteditor property="arzmon" label="摊销金额" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="arzstdt" 
					label="摊销开始时间" disable="true" required="true" />
				<lemis:formateditor mask="date" format="true" property="arzexpdt" 
					label="摊销结束时间" disable="true" required="true" />
			</tr>
			
			<tr>
				<lemis:codelisteditor type="arzisexp" label="是否到期" isSelect="true" redisplay="true"
					required="true" />
				<lemis:texteditor property="arzcontract" label="合同期限" disable="false"
					required="false" />
				<lemis:texteditor property="arznt" label="备注" disable="false"
					required="false" />
			</tr>
			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
<lemis:base/>

</lemis:body>

</html>