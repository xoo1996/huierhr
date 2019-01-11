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
	List<Formatter> header = new LinkedList<Formatter>();
	header.add(new Formatter("tsdtskid","任务单号"));
	header.add(new Formatter("tsdsid","面板序号"));
	header.add(new Formatter("tsdpnlid","面板编码"));
	header.add(new Formatter("tsksta","面板状态"));
	header.add(new Formatter("pnlqaopra","质检员"));
	header.add(new Formatter("qachka","检验结果"));
	header.add(new Formatter("pnlqadt","检验日期"));
	header.add(new Formatter("pnlqant","备注"));
	
/* 	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","tsdtskid","任务单号","false"));
	batchInput.add(new Editor("text","tsdsid","任务单中面板序号","false"));
	batchInput.add(new Editor("text","tsdpnlid","面板编码","false"));
	batchInput.add(new Editor("text","tsksta","面板状态","false"));
	batchInput.add(new Editor("text","pnlqaopra","质检员","false"));
	batchInput.add(new Editor("text","pnlqadt","检验日期","false"));
	batchInput.add(new Editor("text","pnlqant","备注","false")); */
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("质 检", "quality()");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid", "任务单号");
	hidden.put("tskpnlnm","面板型号");
	hidden.put("tsdsid","任务单中面板序号");
	hidden.put("tsksta", "面板状态");
	hidden.put("qachka", "质检结果");

	pageContext.setAttribute("header",header);
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
/* 	function quality() {
	
	var t = editObj("chk");
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/task/TaskAction.do?method=qualitya&"
				+ getAlldata(document.all.tableform);
	} */
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="任务单面板明细" />
	<lemis:tabletitle title="任务单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
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
		</html:form>
	</table>

	<lemis:table action="/TaskAction.do" headerMeta="header" topic="任务单面板明细"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />

	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>