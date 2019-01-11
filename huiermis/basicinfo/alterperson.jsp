<!-- /basicinfo/alterperson.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%
	String stringData = "";
	QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
	if(null != qi){
		stringData = qi.getStringData();
	}
	String menuId=request.getParameter("menuId");
	PersonForm fm = (PersonForm)session.getAttribute("QueryPersonForm");
	if (menuId==null||"".equals(menuId)) {
		if (fm!=null) {
			menuId=fm.getMenuId();
		}
	}
	//获取上一步的url
	String url=request.getParameter("url");
	if (url==null||"".equals(url)) {
		if (fm!=null) {
			url=fm.getUrl();
		}
	}
	if (url==null) {
		url="";
	}
    List header=new ArrayList();
    header.add(new Formatter("aac002","公民身份号码"));
    header.add(new Formatter("aac003","姓名"));
    header.add(new Formatter("aac004","性别"));
	header.add(new Formatter("ac01.aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("aac005","民族"));
    header.add(new Formatter("aac011","文化程度"));
	List buttons=new ArrayList();
	buttons.add(new Button("printperson", "查看个人详细", "bas020306", "detail(document.all.tableform)"));
	//buttons.add(new Button("detail","详 细","bas020201","detail()"));
	buttons.add(new Button("mod","变 更","bas020202","editData()"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"QueryPersonForm\")"));
    
    Map hidden=new LinkedHashMap();
    hidden.put("aac001","个人编号");
    hidden.put("aac002","公民身份号码");
  	List editors=new ArrayList();
   	editors.add(new Editor("card","aac002","公民身份号码"));
   	editors.add(new Editor("text","aac003","姓名"));
   	editors.add(new Editor("text","acc025","劳动手册号"));
    pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("editor",editors);
    pageContext.setAttribute("header",header);
    pageContext.setAttribute("button",buttons);
    
    session.setAttribute("tableheader","人员信息表");//表头
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人变更"/>
		<lemis:query action="queryPerson.do?method=query&amp;menuId=alter" editorMeta="editor" topic="个人信息查询"/>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:table action="queryPerson.do" headerMeta="header" topic="个人信息" hiddenMeta="hidden" mode="radio"/>
		<lemis:buttons buttonMeta="button"/>
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
    <lemis:clean names="QueryPersonForm"/>
</html>
<script language="javascript">
	function editData(){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=alter" +
		"&buttonId=bas020205" + 
		"&stringData=" + document.all("stringData").value + "&" + getAlldata(document.all.tableform)+
		"&url1=<%=url%>&url2="+(location.href+"&menuId=alter").replace(/&/g,";amp;")+'&' ;
	}
	function detail(){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=alter&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform)+"&url1=<%=url%>&url2="
		+(location.href+"&menuId=<%=menuId%>").replace(/&/g,";amp;")+'&' ;
	}
		  function detail(obj){
		  	var t = editObj("chk");
			if(!t){
			return t;
			}

			obj.action = '<html:rewrite page="/personOperAction.do?method=printPerson&"/>'+getAlldata(obj);			
			obj.submit();
  }
</script>
