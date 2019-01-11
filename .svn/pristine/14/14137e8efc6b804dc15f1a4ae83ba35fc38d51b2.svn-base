<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link rel="stylesheet" type="text/css" href="/huiermis/css/jquery.autocomplete.css" />
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%  
	String totalYSK = (String)request.getSession().getAttribute("totalYSK");

    Map<String, String> buttons = new LinkedHashMap<String, String>();
    buttons.put("结 账","jiezhang(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("actstayear","年"));
	header.add(new Formatter("actstamonth","月"));
	/* header.add(new Formatter("actstagcltid","客户代码")); */
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gctarea", "所属区域"));
	header.add(new Formatter("actsta", "结账状态"));
	header.add(new Formatter("totalysk","回款金额"));
	header.add(new Formatter("actstanote", "备注",true));

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","actstayear","年","true"));
	editors.add(new Editor("text","actstamonth","月","true"));
	editors.add(new Editor("text","actstagcltid","客户代码"));
	editors.add(new Editor("text","actsta","结账状态"));
	editors.add(new Editor("text","gctarea","所属区域"));
	editors.add(new Editor("text","gcttype","客户类型"));
	
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("actstayear", "年");
	hidden.put("actstamonth", "月");
	hidden.put("actstagcltid", "客户代码");


	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
%>
<script language="javascript">	
	$(document).ready(function(e) {
		
       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=actstagcltid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=actstagcltid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=actstagcltid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		/*$("input[name=ivtpamnt]").bind("propertychange",function(e) {
			alert('Handler for .change() called.');
			$("input[name=ivtpamnt]").each(function(index) {
				alert(index + ': ' + $(this).val());
				totalYSK += $(this).val();
			});
		});*/
	});
	function jiezhang(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=yuejiequery&mark=jiameng&"/>' + getAlldata(obj);
		obj.submit();
		//window.location.href = "/" + lemis.WEB_APP_NAME
		//		+ "/business/BusinessAction.do?method=batchSubmit&"
		//		+ getAlldata(document.all.tableform);
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="月度结账查询" />
	<lemis:query action="/BusinessAction.do?method=JMyuejiequery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header" topic="月度结账明细" hiddenMeta="hidden"
		mode="radio"  />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
