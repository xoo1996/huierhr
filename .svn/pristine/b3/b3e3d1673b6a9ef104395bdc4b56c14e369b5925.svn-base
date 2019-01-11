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
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    List<Editor> editors = new ArrayList<Editor>();
    editors.add(new Editor("text","name","姓名"));
    editors.add(new Editor("text","useremployid","工号"));
   // editors.add(new Editor("text","store","部门/惠耳点"));
    editors.add(new Editor("date","leavestart","开始时间"));
    editors.add(new Editor("date","leaveend","结束时间"));
    editors.add(new Editor("text","resttype","休假类型"));
    
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("useremployid","工号"));
    header.add(new Formatter("name","姓名"));
    header.add(new Formatter("part","所属部门"));
    header.add(new Formatter("bsc011","账号"));
    header.add(new Formatter("resttype","休假类型"));
    header.add(new Formatter("leavelong","休假天数"));
    header.add(new Formatter("leavestart","开始时间"));
    header.add(new Formatter("leaveend","结束时间"));
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();

	pageContext.setAttribute("header",header);
	pageContext.setAttribute("editor",editors);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String count = (String)request.getSession().getAttribute("count");
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
	
	
	<lemis:title title="休假统计" />
	<lemis:query action="/StatisticsAction.do?method=queryLeave"
		editorMeta="editor" topic="查询条件" />
	<lemis:table topic="明细" action="/StatisticsAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	天数合计：<input type="text" id="sum" value="<%=count%>" style="width: 50px">
	
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
