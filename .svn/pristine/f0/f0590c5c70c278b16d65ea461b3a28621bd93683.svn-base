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
	buttons.put("添加绑定","bind(document.all.tableform)");
 	buttons.put("批量解绑","batchunwrap(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
/*  	hidden.put("username", "用户名称"); */
	hidden.put("useremployid", "本工号"); 
	hidden.put("superiorid", "上级工号"); 
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "qsuperiorid", "需绑定工号"));
	
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

/* function setValue(){
	var val = $("input[name='taraccount']").eq(0);
	var val2 = $("input[name='taraccount']").eq(1);
	
	val2.attr("value",val.attr("value"));
}; */

/* 绑定 */
function bind(obj) {
	var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
	if(qsuperiorid=="undefined" || qsuperiorid==""){
		alert("账号不能为空！");
		return false;
	}
	obj.action = '<html:rewrite page="/BindSXJAction.do?method=toBindJsp&"/>' + 'qsuperiorid=' + qsuperiorid;
	obj.submit();
	
	
	/*  setValue();
	var t = editObj("chk");
	if (!t) {
		 obj.action = '<html:rewrite page="/BindAction.do?method=unWrapAccUser&"/>' + getAlldata(obj);
		obj.submit();
		
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/userinfo/bindAccount.jsp?"
		+ getAlldata(obj); 
	}else{
		var taraccount = $("input[name='taraccount']").eq(0);
		obj.action = '<html:rewrite page="/BindAction.do?method=unWrapAccUser&"/>' + 'taraccount=' + taraccount.attr("value");
		obj.submit();
	} */
	
};

	/* 解绑 */
	function batchunwrap(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindSXJAction.do?method=batchUnwrapSXJ&"/>' + "qsuperiorid=" + qsuperiorid;
		obj.submit();

	}; 
/* 	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/userinfo&page=/addUser.jsp"/>';
		obj.submit();
	} */
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="上下级信息查询" />
	<lemis:query action="/BindSXJAction.do?method=queryInfo" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="BindSXJAction.do" headerMeta="header" topic="下级信息"
		hiddenMeta="hidden" mode="checkbox"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


