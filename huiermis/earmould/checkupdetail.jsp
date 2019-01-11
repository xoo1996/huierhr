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
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
</script>
<script type="text/javascript">
   $(document).ready(function(){
         $('#tmeappear').val('yes');
         $('#tmeden').val('yes');
         $('#tmeckt').val('yes');
  });
</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="耳模质量检测" />
	<lemis:tabletitle title="检验结果" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarMouldAction.do?method=updateCheckup"
			method="POST">
			<tr>
				<lemis:texteditor property="tmeno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmecltnm" label="用户姓名" required="true"
					disable="true" />
				<lemis:texteditor property="tmepnm" label="耳机名称" disable="true"
					required="true" />
			</tr>
			<tr>
			    <lemis:texteditor property="tmesid" label="耳模编号" disable="true"/>
			    <lemis:codelisteditor type="tmemat" label="耳模类型" required="false" isSelect="false"/>
			    <lemis:codelisteditor type="tmecls" label="类别"  required="false" isSelect="false"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="tmeappear" label="外观检验" required="true"
					isSelect="true" />
				<lemis:codelisteditor type="tmeden" label="气密性检验" required="true"
					isSelect="true"  />
				<lemis:codelisteditor type="tmeckt" label="质检结果" required="true"
					isSelect="true" />
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