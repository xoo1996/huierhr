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
		<lemis:title title="员工离职申请流程修改" />
		<lemis:tabletitle title="基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=lea" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="true" disable="true" />
					<lemis:texteditor property="nemname" label="姓名" required="true" disable="true" />
					<lemis:texteditor property="nemapplyid" label="员工id" required="true" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="申请时间" disable="true" format="true" />
					<lemis:texteditor property="nempart" label="所属团体" required="true" disable="false"/>
					<lemis:codelisteditor type="nemtype" isSelect="true" label="员工类型" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="leasdt" required="false" label="入职时间" disable="false" format="true" />
					<lemis:formateditor mask="date" property="leaedt" required="false" label="拟离职时间" disable="false" format="true"/>
					<lemis:codelisteditor type="leatype" isSelect="true" label="离职类别" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemsoc" label="社保办停情况" required="true" disable="false"  colspan="3"/>
				</tr>
				<lemis:tabletitle title="离职原因 " />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="leareason" rows="5" disabled="false"></html:textarea>
				</tr>
					
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

