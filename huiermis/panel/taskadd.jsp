<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("配置零件","configure(document.all.tableform)");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("返 回","history.back()");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("panelList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=tskpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0].substring(data[0].indexOf("=")+1);
				$("input[name=tskpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});
	});
</script>

<script language="javascript">
	function configure(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		//obj.action = '<html:rewrite page="/PanelAction.do?method=configure&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="新增任务单" />
	<lemis:tabletitle title="任务单信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/PanelAction.do?method=configure" method="POST">
			<tr>
				<lemis:texteditor property="tskpnlnm" label="面板型号" disable="false"
					required="true" />
				<lemis:texteditor property="tskpnlqnt" label="面板数量" disable="false"
					required="true" maxlength="4" />
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="true"
					label="要求完成日期" disable="false" format="true" />
			</tr>
			<tr>
				<td>制单人</td>
				<td><lemis:operator /></td>
				<td>任务单下达日期</td>
				<td><lemis:operdate /></td>
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="面板要求" disable="false"
					required="false" colspan="5" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
