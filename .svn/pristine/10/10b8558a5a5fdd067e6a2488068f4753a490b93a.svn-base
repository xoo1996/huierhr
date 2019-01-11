<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tskid", "任务单号"));
	header.add(new Formatter("tskpnlnm", "面板型号"));
	header.add(new Formatter("tskpnlqnt", "面板数量"));
	header.add(new Formatter("tsksta", "面板状态"));
	header.add(new Formatter("tskdfdt", "要求完成日期"));
	header.add(new Formatter("tskbgndt", "任务下达日期"));
	header.add(new Formatter("tskbilopr", "制单人"));
	header.add(new Formatter("tskadtopr", "审核人"));
	header.add(new Formatter("tskmkopr", "生产人员"));
	header.add(new Formatter("tskmkdt", "生产日期"));
	header.add(new Formatter("tsdpdopr", "生产部主管"));
	header.add(new Formatter("tskdmd", "面板要求", true));

	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("审 核","detail()");
	//buttons.put("确认","confirm(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid", "任务单号");
	hidden.put("tskpnlnm","面板型号");
	hidden.put("tskpnlqnt","面板数量");
	hidden.put("tskdfdt","要求完成日期");
	hidden.put("tskbilopr","制单人");
	hidden.put("tskbgndt","任务下达日期");
	hidden.put("tskdmd","面板要求");
	hidden.put("tsksta","面板状态");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tskid", "任务单号"));
	editors.add(new Editor("text", "tskpnlnm", "面板型号"));
	editors.add(new Editor("text", "tskbilopr", "制单人"));
	editors.add(new Editor("date","start","任务下达日期从"));
	editors.add(new Editor("date","end","到"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
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
  		function detail() {
  			var t = editObj("chk");
  			if(!t){
  				return t;
  			}
  			window.location.href = "/" + lemis.WEB_APP_NAME
  					+ "/task/TaskAction.do?method=confirmDetail&"
  					+ getAlldata(document.all.tableform);
  		};
  		
  		function confirm(obj){
  			var t = editObj("chk");
  			if(!t){
  				return t;
  			}
  			obj.action='<html:rewrite page="/TaskAction.do?method=affirm"/>';
  			obj.submit();
  		}
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="配件确认查询" />
	<lemis:query action="/TaskAction.do?method=query&order=confirm" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="/TaskAction.do" headerMeta="header" topic="任务单信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


