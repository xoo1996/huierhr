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
	//buttons.put("任务单提交","con()");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("pdtid","零件代码"));
	header.add(new Formatter("acypdtid","零件货号"));
	header.add(new Formatter("pdtnm","零件名称"));
	header.add(new Formatter("pdtmod","零件型号"));
	header.add(new Formatter("acybthnum","零件批号"));
	header.add(new Formatter("cfgnt","备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtid","零件代码","true"));
	batchInput.add(new Editor("text","acypdtid","零件货号","false"));
	batchInput.add(new Editor("text","pdtnm","零件名称","true"));
	batchInput.add(new Editor("text","pdtmod","零件型号","true"));
	batchInput.add(new Editor("text","acybthnum","零件批号","false"));
	batchInput.add(new Editor("text","cfgnt","备注","false"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid","任务单号");

	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
	function con() {
		if(!confirm("确定提交任务单?")){
			window.event.returnValue = false;
		}else{
			window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/task/TaskAction.do?method=confirm&tskid="
					+ $("input[name=tskid]").val();
		}
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="面板配置信息确认" />
	<lemis:tabletitle title="面板配置基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="任务单号" disable="true"/>
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
		</html:form>
	</table>

	<lemis:table topic="零件信息明细"  
		action="/TaskAction.do" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="true" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>