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
String fcscictid=(String)request.getSession().getAttribute("fcscictid");
String fcscictnm=(String)request.getSession().getAttribute("fcscictnm");
String fcscictgctid=(String)request.getSession().getAttribute("fcscictgctid");
String fcscgctnm=(String)request.getSession().getAttribute("fcscgctnm");
	List<Formatter> header = new ArrayList<Formatter>();
	//header.add(new Formatter("fctctid", "用户编号"));
	//header.add(new Formatter("ictnm", "用户姓名"));
	//header.add(new Formatter("fcscid", "复诊听力编号"));
	header.add(new Formatter("ictnm","用户姓名"));
	header.add(new Formatter("ictgctid","所属团体"));
	header.add(new Formatter("fctcdt", "复诊日期"));
	header.add(new Formatter("fctnt", "备注"));
  
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增","newSCSC(document.forms[0])");
	buttons.put("查看详情","queryScscDetail(document.all.tableform)");
	buttons.put("删除","deleteSCSC(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fcscid", "复诊听力编号");
	hidden.put("fctcdt", "复诊日期");
	hidden.put("ictid", "用户编号");
	hidden.put("ictgctid", "所属团体编号");
	hidden.put("gctnm", "所属团体");
	hidden.put("ictnm", "用户姓名");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
/* 	session.setAttribute("tableheader", "个人客户复诊信息表");//表头 */
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/CityJson.js"></script>
<script src="/huiermis/js/DistrictJson.js"></script>
<script src="/huiermis/js/ProJson.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<script language="javascript">
	function newSCSC(obj) {
		//var t = editObj("chk");
		//if(!t){
		//	return t;
		//}
		/*  var ictid=$("input[name=ictid]").val(); 
		alert(ictid); */
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=enterAddSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function deleteSCSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=deleteSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function queryScscDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetailSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	$(document).ready(function(){	

		 $("#ictid").val("<%=fcscictid%>");
		 $("#ictnm").val("<%=fcscictnm%>");  
		 $("#ictgctid").val("<%=fcscictgctid%>");
		 $("#gctnm").val("<%=fcscgctnm%>");
	}); 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="用户复诊声场查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">

			 <tr>
				<lemis:texteditor property="ictid" label="用户编号" required="false" value="<%=fcscictid%>"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false" value="<%=fcscictnm%>"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="ictgctid" label="团体编号" required="false" value="<%=fcscictgctid%>"
					disable="true" />
				<lemis:texteditor property="gctnm" label="所属团体" required="false" value="<%=fcscgctnm%>"
					disable="true" />
	
			</tr>
		</html:form>
	</table>
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="用户复诊声场信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


