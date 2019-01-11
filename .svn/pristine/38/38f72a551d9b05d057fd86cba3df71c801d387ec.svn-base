<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工请假申请流程修改" />
		<lemis:tabletitle title="请假时间" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=rest" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="true" disable="true" />
					<lemis:formateditor mask="date" property="restsdt" required="true" label="起始时间" disable="false" format="true" />
					<lemis:formateditor mask="date" property="restedt" required="true" label="结束时间" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="请假类别" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="restnum" label="请假天数" required="false" disable="false" />
					<lemis:codelisteditor type="resttype" isSelect="true" label="请假类别" redisplay="true" required="true" />
					<lemis:texteditor property="restother" label="其他" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemname" label="申请人" required="true" disable="false" />
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" />
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="申请时间" disable="false" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="请假事由" />
				<table class="tableinput">
				<tr>
					<html:textarea property="restreason" rows="5" disabled="false"></html:textarea>
				</tr>
				
				</table>
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="restnote" rows="5" disabled="false"></html:textarea>
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

