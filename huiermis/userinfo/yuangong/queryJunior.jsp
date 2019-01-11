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
 String tar=(String)request.getAttribute("taraccount");
	List<Formatter> header = new ArrayList<Formatter>();
	
	header.add(new Formatter("useremployid", "工号",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "用户名称",TagConstants.DF_CENTER));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
 	buttons.put("详情","showDetail(document.all.tableform)"); 
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
  	hidden.put("account", "已绑定账号");
/* 	hidden.put("username", "用户名称"); */
	hidden.put("useremployid", "用户代码"); 
/* 	hidden.put("taraccount", "所绑定账号");  */

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "账号"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
/* 	batchInput.add(new Editor("text","useremployid", "工号","false"));	
	batchInput.add(new Editor("text","account", "工号","false")); */	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);	
	
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script language="javascript">

	$(document).ready(function(){
		var taraccount = '<%=request.getAttribute("taraccount")%>'; 
 		$("input[name='taraccount']").val(taraccount);
		$("input[name='taraccount']").attr("readonly","true");
	});
	
	/* 详情 */
	function showDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/UserInfoAction.do?method=showDetail&tp=normal&"/>' + getAlldata(obj) +'&mtd=geren';
		obj.submit();
	};

/* 	function setValue(){
		var val = $("input[name='taraccount']").eq(0);
		var val2 = $("input[name='taraccount']").eq(1);
		
		val2.attr("value",val.attr("value"));
	};
 */
/* 绑定 */
/* 	function bind(obj) {	
		obj.action = '<html:rewrite page="/BindAccAction.do?method=toQueryAccount&"/>' + "taraccount=" + $("input[name='taraccount']").eq(0).attr("value") + "";
		obj.submit();
	}; */

	/* 批量解绑 */
 /* 	function unwrap(obj) {
 		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BindAccAction.do?method=batchUnwrapAccUser&"/>' + "taraccount=" + $("input[name='taraccount']").eq(0).attr("value");
		obj.submit();
	};  */
/* 	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/userinfo&page=/addUser.jsp"/>';
		obj.submit();
	} */
	
	
	
	
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="下级档案信息" />
    <table class="tableinput">
				<COLGROUP><COL width='12%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			</table>
	<lemis:query action="/UserInfoAction.do?method=queryJuniorInfo" editorMeta="editor" topic="登录账号" />	
	<lemis:table action="UserInfoAction.do" headerMeta="header" topic="下级信息" 
		hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


