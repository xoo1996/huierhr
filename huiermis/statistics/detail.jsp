<%@page import="com.lbs.cp.taglib.Formatter"%>
<%@page import="org.radf.plat.taglib.Editor"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返回","window.history.back();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    List<Editor> editors = new ArrayList<Editor>();
	
	 
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("username","姓名"));
    header.add(new Formatter("useremployid","工号"));
    header.add(new Formatter("danganid","档案号"));
    header.add(new Formatter("bsc009","部门"));
    header.add(new Formatter("aaa103","区域"));
    header.add(new Formatter("userjoindate","入职时间"));
    header.add(new Formatter("userleavedate","离职时间"));
    header.add(new Formatter("userpositionnow","岗位"));
    header.add(new Formatter("userheightestedu","学历"));

    
    Map<String, String> hidden = new LinkedHashMap<String, String>();

	

	pageContext.setAttribute("header",header);
	pageContext.setAttribute("editor",editors);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

</script>
<lemis:base />
<lemis:body>
	
	
	<lemis:title title="明细查看" />
<%-- 	<lemis:query action="/StatisticsAction.do?method=queryByPerson"
		editorMeta="editor" topic="查询条件" /> --%>
	<lemis:table topic="明细" action="/StatisticsAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
