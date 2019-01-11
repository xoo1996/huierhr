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
    editors.add(new Editor("text","store","惠耳点"));
    editors.add(new Editor("text","stipayitem","统计项目"));
    editors.add(new Editor("text","startyear","开始年份"));
    editors.add(new Editor("text","startmonth","开始月份"));
    editors.add(new Editor("text","endyear","结束年份"));
    editors.add(new Editor("text","endmonth","结束月份"));
    
	 
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("aaa103","区域"));
    header.add(new Formatter("store","惠耳点"));
    header.add(new Formatter("totalstore","合计"));


    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	

	pageContext.setAttribute("header",header);
	pageContext.setAttribute("editor",editors);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String sql = (String)request.getAttribute("sql");
	request.getSession().setAttribute("sql", sql);
	String path = request.getContextPath();
	String startyear = request.getParameter("startyear");
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
$(document).ready(function(e) {
    var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=store]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=store]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=store]").val(gnm);
			}
		});
});
</script>
<lemis:base />
<lemis:body>
	
	<lemis:title title="明细查看" />
<lemis:query action="/StatisticsAction.do?method=detailQuery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table topic="明细" action="/StatisticsAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
