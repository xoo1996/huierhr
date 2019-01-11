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
	
	header.add(new Formatter("useremployid", "工号",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "用户名称",TagConstants.DF_CENTER));
	header.add(new Formatter("account", "账号",TagConstants.DF_CENTER));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("查看账号绑定详情","detail(document.all.tableform)");
	buttons.put("绑定","bind(document.all.tableform)");
	buttons.put("解绑","unwrap(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("username", "用户名称");
	hidden.put("useremployid", "用户代码");

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "所绑定账号"));
	editors.add(new Editor("text", "qusername", "用户名称"));
	editors.add(new Editor("text", "quseremployid", "用户工号"));	
	
	
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
<script src="/huiermis/js/gbk.js"></script>
<script language="javascript">


$(document).ready(function(){
	$("input[name='taraccount']").attr("readonly","true");
});


function setValue(){
	var val = $("input[name='taraccount']").eq(0);
	var val2 = $("input[name='taraccount']").eq(1);
	val2.attr("value",val.attr("value"));
};

/* 绑定 */
function bind(obj) {
/* 	 setValue(); */
	var t = editObj("chk");
	if (!t) {
		return t;
	}
	var form = build(obj);
	form.action = '<html:rewrite page="/BindAccAction.do?method=bindAccUser"/>';
	form.submit();
	/*  var taraccount = $("input[name='taraccount']").eq(0).attr("value");
	var qusername = $("input[name='qusername']").eq(0).val();
	var quseremployid = $("input[name='quseremployid']").eq(0).val();
	var username1 = $("input[name='username1']").val(); */
	/*
	obj.action = '<html:rewrite page="/BindAccAction.do?method=bindAccUser&"/>' + getAlldata(obj)  + "taraccount=" + taraccount 
 				+"&qusername=" + qusername
 				+"&quseremployid=" + quseremployid;
 	obj.submit();  */ 
	
/* 	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/userinfo/BindAccAction.do?method=bindAccUser&" +
	 getAlldata(document.all.tableform) + "&taraccount=" + taraccount
	 + "&qusername=" + qusername
	 + "&quseremployid=" + quseremployid
	 +"&username1=" + username1;   */

};

	/* 解绑 */
	function unwrap(obj) {
		 setValue();
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		var form = build(obj);
		form.action = '<html:rewrite page="/BindAccAction.do?method=unWrapAccUser"/>';
		form.submit();
	/* 	var taraccount = $("input[name='taraccount']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindAccAction.do?method=unWrapAccUser&"/>' + getAlldata(obj) + "taraccount=" + taraccount;
		alert(obj.action);
		obj.submit(); */
	};
	
	
	function detail(obj) {
		var taraccount = $("input[name='taraccount']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindAccAction.do?method=queryAccountInfo&"/>'  + "taraccount=" + taraccount;
		obj.submit();
	} 
	
	
	function build(obj){
		var form = document.getElementById("bind");
		var d = getAlldata(obj);
		var data = getRightData(d);
		$.each(data,function(i,n){
			var old = form.innerHTML;
			var news = data[i];
			form.innerHTML = old + news;
		});
		var taraccount = $("input[name='taraccount']").eq(0).attr("value");
		var qusername = $("input[name='qusername']").eq(0).attr("value");
		var quseremployid = $("input[name='quseremployid']").eq(0).attr("value");
		var old = form.innerHTML;
		var new1 = '<input type="hidden" name="taraccount" value="' + taraccount + '"/>';
		var new2 = '<input type="hidden" name="qusername" value="' + qusername + '"/>';
		var new3 = '<input type="hidden" name="quseremployid" value="' + quseremployid + '"/>';
		form.innerHTML = old + new1 + new2 +new3;
		return form;
	}
	
	function getRightData(data){
		var lists = data.split("&");
		var names = new Array();
		var values = new Array();
		var inputs = new Array();
		$.each(lists,function(i,n){
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
	<lemis:query action="/BindAccAction.do?method=queryAccount" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="UserInfoAction.do" headerMeta="header" topic="用户信息"
		hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
	<form id="bind" method="post">	
	</form>
</lemis:body>
</html>


