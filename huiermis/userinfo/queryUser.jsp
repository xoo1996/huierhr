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
	header.add(new Formatter("usergender", "性别",TagConstants.DF_CENTER));
	header.add(new Formatter("bsc009", "部门",TagConstants.DF_CENTER));
	header.add(new Formatter("positionname", "岗位",TagConstants.DF_CENTER));
	header.add(new Formatter("dangan", "档案号",TagConstants.DF_CENTER));
	header.add(new Formatter("useridno", "身份证号",TagConstants.DF_CENTER));
	header.add(new Formatter("usermobilephone", "联系电话",TagConstants.DF_CENTER));
	header.add(new Formatter("userjoindate", "入职日期",TagConstants.DF_CENTER));
	header.add(new Formatter("usermajor", "专业",TagConstants.DF_CENTER));
	header.add(new Formatter("userheightestedu", "最高学历",TagConstants.DF_CENTER));
	header.add(new Formatter("workage", "工龄",TagConstants.DF_CENTER));
	header.add(new Formatter("gctarea", "所属区域",TagConstants.DF_CENTER));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("详情","showDetail(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("useremployid", "用户代码");

		
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "username", "员工名称"));
	editors.add(new Editor("text", "useremployid", "员工工号"));	
	editors.add(new Editor("text", "userdepartmentid", "部门"));	
	editors.add(new Editor("text", "positionname", "岗位"));	
	
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
	
	/* 智能输入 */
	var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
	$("input[name=userdepartmentid]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=userdepartmentid]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=userdepartmentid]").val(gid);
			$(this).parent().next().find("input").val(gnm);
		}
	});
});
	function showDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/UserInfoAction.do?method=showDetail&tp=normal&"/>' + getAlldata(obj) +'&mtd=show';
		obj.submit();
	};
/* 	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/userinfo&page=/addUser.jsp"/>';
		obj.submit();
	} */
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="用户查询" />
	<lemis:query action="/UserInfoAction.do?method=query&tp=normal" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="UserInfoAction.do" headerMeta="header" topic="用户信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


