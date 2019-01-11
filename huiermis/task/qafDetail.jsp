<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("保 存", "saveData(document.forms[0])");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskpnlnm","面板型号");

	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
	function saveData(obj) {
		if(!checkValue(obj)){
			return false;
		}
		obj.submit();
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="面板完成质检" />
	<lemis:tabletitle title="任务单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do?method=saveQAF" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="任务单号" disable="true" />
				<lemis:texteditor property="tskpnlnm" label="面板型号" disable="true" />
				<lemis:texteditor property="tskpnlqnt" label="面板数量" disable="true" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="false"
					label="要求完成日期" disable="true" format="true" />
				<lemis:texteditor property="tskbilopr" label="制单人" disable="true" />
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="任务下达日期" disable="true" format="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="面板要求" disable="true"  colspan="5"/>
			</tr>
			
			
			<lemis:tabletitle title="质检信息"/>
			<table class="tableInput">
				<lemis:editorlayout/>
				
				<tr>
					<lemis:texteditor property="pnlqapnl" label="任务单编码范围"  
						required="true" disable="true" colspan="4"/>
					<td>检验日期</td>
					<td><lemis:operdate/></td>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="pnlqaopra" isSelect="false" label="检验员1"
						redisplay="true" required="true" dataMeta="userList" /> --%>
					<lemis:texteditor property="pnlqaopra" label="检验员1"  
						required="true" disable="true"/>
					<lemis:texteditor property="pnlqapnla" label="检验员1面板编码"  
						required="true" disable="true" colspan="4"/>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="qachka" label="检验结果1" isSelect="false"
						required="true" /> --%>
					<lemis:texteditor property="pnlqarsta" label="检验结果1"  
						required="true" disable="true"/>
				</tr>
				
				<tr>
					<lemis:codelisteditor type="pnlqaoprb" isSelect="true" label="检验员2"
						redisplay="true" required="true" dataMeta="userList" />
					<lemis:texteditor property="pnlqapnlb" label="检验员2面板编码"  
						required="true" disable="false" colspan="4"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkb" label="检验结果2" isSelect="true"
						required="true" />
				</tr>
				
				<tr>
					<lemis:codelisteditor type="pnlqaoprc" isSelect="true" label="检验员3"
						redisplay="true" required="false" dataMeta="userList" />
					<lemis:texteditor property="pnlqapnlc" label="检验员3面板编码"  
						required="false" disable="false" colspan="4"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="qachkc" label="检验结果3" isSelect="true"
						required="false" />
				</tr>
				
				<tr>
					<lemis:texteditor property="pnlqant" label="备注" disable="false" colspan="6"/>
				</tr>
				
			</table>
			
		</html:form>
	</table>

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>