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
    editors.add(new Editor("text","store","部门/惠耳点"));
    editors.add(new Editor("text","startyear","开始年份","true"));
    editors.add(new Editor("text","startmonth","月份","true"));
    editors.add(new Editor("text","endyear","结束年份","true"));
    editors.add(new Editor("text","endmonth","月份","true"));
    
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("useremployid","工号"));
    header.add(new Formatter("name","姓名"));
    header.add(new Formatter("store","惠耳点"));
    header.add(new Formatter("year","年"));
    header.add(new Formatter("month","月"));
    header.add(new Formatter("yingfa","应发工资"));
    header.add(new Formatter("shifa","实发工资"));


    
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
	<lemis:query action="/StatisticsAction.do?method=queryByPerson"
		editorMeta="editor" topic="查询条件" />
	<lemis:table topic="明细" action="/StatisticsAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
