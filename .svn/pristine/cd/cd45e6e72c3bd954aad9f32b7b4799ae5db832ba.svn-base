<!-- basicinfo/queryperson.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%//获取minuId
			String menuId = request.getParameter("menuId");
			PersonForm fm = (PersonForm) session
					.getAttribute("QueryPersonForm");
			if (menuId == null || "".equals(menuId)) {
				if (fm != null) {
					menuId = fm.getMenuId();
				}
			}
			//获取上一步的url
			String url = request.getParameter("url");
			if (url == null || "".equals(url)) {
				if (fm != null) {
					url = fm.getUrl();
				}
			}
			if (url == null) {
				url = "";
			}
			String stringData = "";
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			if (null != qi) {
				stringData = qi.getStringData();
			}
			System.out.println("##url1##"+request.getParameter("url1"));
			System.out.println("##url2##"+request.getParameter("url2"));
			List header = new ArrayList();
			header.add(new Formatter("aac002", "公民身份号码"));
			header.add(new Formatter("aac003", "姓名"));
			header.add(new Formatter("aac004", "性别"));
			header.add(new Formatter("ac01.aac006", "出生日期",
			TagConstants.DF_LEFT, TagConstants.DT_YEAR_MONTH_DATE));
			header.add(new Formatter("aac005", "民族"));
			header.add(new Formatter("aac011", "文化程度"));

			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			qh.put("url", null);
			pageContext.setAttribute("queryHiddens", qh);
			List buttons = new ArrayList();
			buttons.add(new Button("printperson", "查看个人详细", "bas020306", "detail(document.all.tableform)"));
			//buttons.add(new Button("detail", "详 细", "bas020301", "detail(document.all.tableform)"));
//			buttons.add(new Button("edit", "修 改", "bas020302","editData()"));
			buttons.add(new Button("close", "关 闭", "bas999999","closeWindow(\"PersonForm,OperPersonForm,QueryPersonForm\")"));

			Map hidden = new LinkedHashMap();
			hidden.put("aac001", "个人编号");
			hidden.put("aac002", "公民身份号码");
			List editors = new ArrayList();
			editors.add(new Editor("card", "aac002", "公民身份号码"));
			editors.add(new Editor("text", "aac003", "姓名"));
			editors.add(new Editor("text","acc025","劳动手册号"));
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "人员信息表");//表头
			%>
<html>
<lemis:base />
<lemis:body>
	<lemis:title title="个人查询" />
	<lemis:query action="/queryPerson.do?method=query" editorMeta="editor" hiddenMeta="queryHiddens" topic="个人信息查询" />
	<html:hidden property="stringData" value="<%=stringData%>" />
	<lemis:table action="queryPerson.do" headerMeta="header" topic="个人信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:clean names="QueryPersonForm" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
<script language="javascript">
	function editData(){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=query&buttonId=bas020305&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform)+"&url1=<%=url%>&url2="
		+(location.href+"&menuId=<%=menuId%>&aac001="+document.all("aac001").value).replace(/&/g,";amp;")+'&';
	}
//	function detail(obj){
//		var t = editObj("chk");
//		if(!t){
//			return t;
//		}
//		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=query&stringData=" +
//		document.all("stringData").value + "&" + getAlldata(document.all.tableform)+
//		"&url2=" + (location.href+"&menuId=<%=menuId%>"+"&"+getAlldata(obj)).replace(/&/g,";amp;")+
//		"&url1=" + (location.href+"&menuId=<%=menuId%>").replace(/&/g,";amp;")+'&';
//	}
	
		  //打印
  function detail(obj){
			var t = editObj("chk");
			if(!t){
			return t;
			}
			obj.action = '<html:rewrite page="/personOperAction.do?method=printPerson&"/>'+getAlldata(obj);			
			obj.submit();
  }
</script>

