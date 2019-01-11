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
	buttons.put("入 库", "save(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("tsdtskid","任务单号"));
	header.add(new Formatter("tsdsid","面板序号"));
	header.add(new Formatter("tsdpnlid","面板编码"));
	header.add(new Formatter("tsksta","面板状态"));
	header.add(new Formatter("pnlqaopra","质检员"));
	header.add(new Formatter("qachka","检验结果"));
	header.add(new Formatter("pnlqadt","检验日期"));
	header.add(new Formatter("pnlqant","备注"));
	
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid", "任务单号");
	hidden.put("tskpnlnm","面板型号");
	hidden.put("tskpnlqnt","面板数量");
	hidden.put("tskdfdt","要求完成日期");
	hidden.put("tskbilopr","制单人");
	hidden.put("tskbgndt","任务下达日期");
	hidden.put("tskdmd","面板要求");
	hidden.put("tsksta","面板状态");
	hidden.put("tsdpnlid","面板编码");
	hidden.put("tsdsid","面板序号");
	


	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
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
	$(document).ready(function(){
		var pnlid = $("#pnlqapnl_hrow1");
		$("input:hidden[name=pnlqapnl]").val(pnlid);
		
		var tskid = $('#tskid_hrow1').val();
		var tskpnlnm = $("#tskpnlnm_hrow1").val();
		var tskpnlqnt = $("#tskpnlqnt_hrow1").val();
		var tskdfdt = $("#tskdfdt_hrow1").val();
		var tskbilopr = $("#tskbilopr_hrow1").val();
		var tskbgndt = $("#tskbgndt_hrow1").val();
		var tskdmd = $("#tskdmd_hrow1").val();
		$('.tableInput label:first').text(tskid);
		$('.tableInput label:eq(1)').text(tskpnlnm);
		$('.tableInput label:eq(2)').text(tskpnlqnt);
		$('.tableInput label:eq(3)').text(tskdfdt);
		$('.tableInput label:eq(4)').text(tskbilopr);
		$('.tableInput label:eq(5)').text(tskbgndt);
		$('.tableInput label:last').text(tskdmd);

		$('input[name=chk]').attr('checked','checked');
	});
	function save(obj) {
		var t = delObj("chk");
		if(!t){
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
			
	 	obj.action = '<html:rewrite page="/TaskAction.do?method=batchStore"/>';
		obj.submit();
		/*	 window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/task/TaskAction.do?method=store&tskid="
				+ $("#tskid_hrow1").val() + "&tskpnlnm="
				+ $("#tskpnlnm_hrow1").val() + "&tskpnlqnt="
				+ $("#tskpnlqnt_hrow1").val();  */
		};

</script>
<lemis:base />
<lemis:body>

	
		
		
	<lemis:title title="入库信息确认" />
	<lemis:tabletitle title="质检基本信息" />

		
 	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
			<tr>
				<html:hidden property="tskid"/>
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
	
	<lemis:table action="/TaskAction.do" headerMeta="header" topic="任务单面板明细"
		hiddenMeta="hidden" mode="checkbox"  pilot="true" />

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>