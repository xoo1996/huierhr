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
	buttons.put("确 认", "con(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("pdtid","零件代码"));
	header.add(new Formatter("pdtnm","零件名称"));
	header.add(new Formatter("pdtmod","零件型号"));
	header.add(new Formatter("tcfnum","零件货号"));
	header.add(new Formatter("tcfbth","零件批号"));
	header.add(new Formatter("tcfnt","备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
 	batchInput.add(new Editor("text","pdtid","零件代码","false"));
 	/*batchInput.add(new Editor("text","acypdtnm","零件名称","true"));
	batchInput.add(new Editor("text","acytyp","零件型号","true")); */
	batchInput.add(new Editor("text","tcfnum","零件货号","false"));
	batchInput.add(new Editor("text","tcfbth","零件批号","true"));//必填
	batchInput.add(new Editor("text","tcfnt","备注","false"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid","任务单号");
	
	hidden.put("tskpnlnm","面板型号");
	hidden.put("tskpnlqnt","面板数量");

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
	$(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("panelList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=acyid]").attr('readonly','readonly');
		
		$("input[name=tskpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0].substring(data[0].indexOf("=")+1);
				//var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=tskpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});

	});
</script>
<script language="javascript">
	$(document).ready(function(){
		var users = "<%=session.getServletContext().getAttribute("userList")%>".replace("{","").replace("}","").split(", ");
		
		$(":checkbox:enabled").attr('checked', true);
		
		$("input[name=tskbilopr]").autocomplete(users,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskbilopr]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=tskbilopr]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>
<script language="javascript">
	function con(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if(!confirm("确定配件无误?")){
			window.event.returnValue = false;
		}else{
			/* window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/task/TaskAction.do?method=confirm&tskid="
					+ $("input[name=tskid]").val() + "&tskpnlqnt=" + $("input[name=tskpnlqnt]").val() + "&"
					+ getAlldata(document.all.tableform); */
			obj.action='<html:rewrite page="/TaskAction.do?method=confirm"/>';
			obj.submit();
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
				<td>审核人</td>
				<td><lemis:operator /></td>
			</tr>
			
			<tr>
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="任务下达日期" disable="true" format="true" />
				<lemis:texteditor property="tskdmd" label="面板要求" disable="true"  colspan="3"/>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="零件信息明细"  
		action="/TaskAction.do" headerMeta="header" hiddenMeta="hidden"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" pageSize="20"
		batchInputType="update" pilot="true" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>