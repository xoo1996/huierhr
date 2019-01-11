<!-- client/queryF.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("icttel", "用户电话"));
	header.add(new Formatter("ictaddr", "用户地址"));
	header.add(new Formatter("fdtpid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("pdtprc", "商品单价"));
	header.add(new Formatter("foldt", "选配时间"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("导出Excel","statistics_dc(document.all.tableform)");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("start", "统计日期从");
	hidden.put("end", "到");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("date","start","统计日期从"));
	editors.add(new Editor("date","end","到"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "统计导出");//表头
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
 
<script language="javascript">
	
	function statistics_dc(obj){
		obj.action = '<html:rewrite page="/ReservationAction.do?method=statistics_query"/>'+getAlldata(obj);			
		obj.submit();
	}
	
</script>
				
<lemis:base />
<lemis:body>
	<lemis:title title="客户统计查询" />
	<lemis:query action="/TjAction.do?method=tj"
		editorMeta="editor" topic="统计条件" />
	<lemis:table action="TjAction.do" headerMeta="header"
		topic="客户统计信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


