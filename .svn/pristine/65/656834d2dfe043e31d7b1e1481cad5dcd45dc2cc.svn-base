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
	header.add(new Formatter("useremployid", "下级工号",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "用户名称",TagConstants.DF_CENTER));
	header.add(new Formatter("superiorid", "上级工号",TagConstants.DF_CENTER));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("查看绑定详情","detail(document.all.tableform)");
	buttons.put("绑定","bind(document.all.tableform)");
	buttons.put("解绑","unwrap(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("useremployid", "下级工号");
	hidden.put("superiorid", "上级工号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "qsuperiorid", "绑定工号*"));
	editors.add(new Editor("text", "username", "用户名称"));
	editors.add(new Editor("text", "qjuniorid", "下级工号"));	
	
	
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
<script language="javascript">

$(document).ready(function(){
	$("input[name='qsuperiorid']").attr("readonly","true");
});


/* 绑定 */
function bind(obj) {
	var t = editObj("chk");
	if (!t) {
		return t;
	}
	/* build(obj); */
	var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
	var qjuniorid = $("input[name='qjuniorid']").eq(0).attr("value");
	obj.action = '<html:rewrite page="/BindSXJAction.do?method=bindSXJ&"/>'  + getAlldata(obj) + "qsuperiorid=" + qsuperiorid + "&qjuniorid=" + qjuniorid ;
	obj.submit();
};

	/* 解绑 */
	function unwrap(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
		var qjuniorid = $("input[name='qjuniorid']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindSXJAction.do?method=unWrapSXJ&"/>' + getAlldata(obj)  + "qsuperiorid=" + qsuperiorid + "&qjuniorid=" + qjuniorid ;
		obj.submit();
	};
	function detail(obj) {
		var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindSXJAction.do?method=queryInfo&"/>'  + "qsuperiorid=" + qsuperiorid;
		obj.submit();
	} 
	
	
	function build(obj){
		var form = document.getElementById("bind");
		var d = getAlldata(obj);
		var data = getRightData(d);
		$.each(data,function(i,n){
			alert("3");
			var old = form.innerHTML;
			var news = data(i);
			form.innerHTML = old + news;
		});
	}
	
	function getRightData(data){
		alert("1");
		var lists = data.split("&");
		var names = new Array();
		var values = new Array();
		var inputs = new Array();
		$.each(lists,function(i,n){
			alert("2");
			names[i] = n.split("=")[0];
			values[i] = n.split("=")[1];
			inputs[i] = '<input type="hidden" name="' + names[i] +'" value="' + values[i] + '"/>';
		});
		return inputs;
	}
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="账号绑定" />
    <table class="tableinput">
				<COLGROUP><COL width='12%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			<%-- 	<tr>
					<lemis:texteditor property="accountno1" label="账号" disable="false" required="false"/>
					<td></td><td></td>
					<td></td><td></td>
				</tr>
				 --%>
			</table>
	<lemis:query action="/BindSXJAction.do?method=queryEmployee" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="BindSXJAction.do" headerMeta="header" topic="用户信息"
		hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
	
	
</lemis:body>
</html>


