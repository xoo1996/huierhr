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
	hidden.put("tskid", "任务单号");
	hidden.put("tskpnlnm","面板型号");
	hidden.put("tsdsid","面板序号");

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
	<lemis:title title="面板质检详情" />
	<lemis:tabletitle title="任务单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do?method=saveQA" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="任务单号" disable="true" />
				<lemis:texteditor property="tskpnlnm" label="面板型号" disable="true" />
				<lemis:texteditor property="tskpnlqnt" label="面板数量" disable="true" />
				<html:hidden property="tsksta"/>
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
					<lemis:texteditor property="tsdsid" label="面板序号" required="false" disable="true"/>
					<lemis:texteditor property="pnlqapnl" label="面板编码"  
						required="true" disable="false" />
					<td>检验日期</td>
					<td><lemis:operdate/></td>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="pnlqaopra" isSelect="true" label="检验员"
						redisplay="true" required="true" dataMeta="userList" /> --%>
					<td>检验员</td>
					<td><lemis:operator/>
					<lemis:codelisteditor type="qachka" label="检验结果" isSelect="true"
						required="true" />
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