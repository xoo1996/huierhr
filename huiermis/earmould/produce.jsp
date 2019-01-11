<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("保存", "saveData(document.forms[0])");
	buttons.put("重 置", "document.forms[0].reset();");
	buttons.put("关 闭", "closeWindow(\"\")");
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
  <lemis:base/>
  <lemis:errors />
  <lemis:title title="制作耳模"/>
  <lemis:tabletitle title="耳模信息" />
  <table class="tableinput">
   		<lemis:editorlayout/>
   		<html:form action="/EarMouldAction.do?method=updateProduce" method="post">
   			<tr>
				<lemis:texteditor property="tmeno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmecltnm" label="用户姓名" disable="true"
					required="true" />
				<lemis:codelisteditor type="tmemat" label="耳模类型" required="true"
					isSelect="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="tmegif" label="类别" required="true"
					isSelect="false" />
				<lemis:formateditor mask="date" property="tmepdt" required="true"
					label="计划完工日期" disable="false" format="true" />
			</tr>
			<tr>
				<td>制作者</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>制作日期</td>
				<td><lemis:operdate /></td>
			</tr>
   		</html:form>
  </table>
  <lemis:buttons buttonMeta="button" />
  <lemis:bottom />
</lemis:body>
</html>