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


  String bvictid=(String)request.getSession().getAttribute("bvictid");
  String bvictnm=(String)request.getSession().getAttribute("bvictnm");
  String bvgctnm=(String)request.getSession().getAttribute("bvgctnm");
  String bvage=(String)request.getSession().getAttribute("bvage");
  String bvictgender=(String)request.getSession().getAttribute("bvictgender");
  String bvicttel=(String)request.getSession().getAttribute("bvicttel");
  String bvictaddr=(String)request.getSession().getAttribute("bvictaddr");
  String a;
  if(bvictgender.equals("1")){
	  a="男";
  }else a="女";
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("gctnm", "团体名称"));
	header.add(new Formatter("bvdate1", "第一次回访日期"));
	header.add(new Formatter("bvdate2", "第二次回访日期"));
	header.add(new Formatter("bvdate3", "第三次回访日期"));
	header.add(new Formatter("bvassess", "最终效果评定"));
  	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增调查表","newSCTL(document.forms[0])");
	buttons.put("查看详情","querySctlDetail(document.all.tableform)");
	buttons.put("修改","updateSCTL(document.all.tableform)");
	buttons.put("删除","deleteSCTL(document.all.tableform)");
	buttons.put("打 印","print(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictage", "年龄");
	hidden.put("ictaddr", "地址");
	hidden.put("icttel", "电话");
	hidden.put("ictid", "用户编号");
	hidden.put("ictnm", "用户姓名");
	hidden.put("ictgender", "性别");
	hidden.put("gctnm", "所属团体");
	hidden.put("bvid", "售后服务调查表编号");
	
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
	function newSCTL(obj) {
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=enterAddBV&"/>' + getAlldata(obj);
		obj.submit();
	};
	function deleteSCTL(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=deleteBV&"/>' + getAlldata(obj);
		obj.submit();
	};
	function querySctlDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetailBV&type=show&"/>' + getAlldata(obj);
		obj.submit();
	};
	function updateSCTL(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetailBV&type=update&"/>' + getAlldata(obj);
		obj.submit();
	};
	//print
	function print(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
	obj.action = '<html:rewrite page="/SingleClientAction.do?method=printVisit&"/>'+getAlldata(obj);			
	obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="售后服务调查查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
	         <tr>
				<lemis:texteditor property="ictid" label="用户编号" required="false" value="<%=bvictid%>"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false" value="<%=bvictnm%>"
					disable="true" />
				<lemis:texteditor property="gctnm" label="所属团体" required="false" value="<%=bvgctnm%>"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="ictage" label="年龄" required="false" value="<%=bvage%>"
					disable="true" />
				<lemis:texteditor property="ictgender" label="性别" required="false" value="<%=a%>"
					disable="true" />
				<lemis:texteditor property="icttel" label="电话" required="false" value="<%=bvicttel%>"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="ictaddr" label="地址" required="false" value="<%=bvictaddr%>"
					disable="true" colspan="20"/>
			</tr>
		</html:form>
	</table>
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="调查表信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


