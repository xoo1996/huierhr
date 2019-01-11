<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("repfolid", "订单号"));
	header.add(new Formatter("repgctnm", "送修单位"));
	header.add(new Formatter("repcltnm", "用户姓名"));
	header.add(new Formatter("repid", "机身编号"));
	header.add(new Formatter("pdtnm", "助听器名称"));
	header.add(new Formatter("repdate", "送修日期"));
	header.add(new Formatter("repamt", "维修费"));
	
	
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

 	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkgctnm", "送修单位名称");
	hidden.put("tmkgctid", "送修单位代码");
	hidden.put("tmkcltid", "用户代码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkpid", "助听器代码");
	hidden.put("tmksid", "机身编号");
	
	hidden.put("pdtid", "商品代码");
	hidden.put("pdtnm","商品名称");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>


<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="pdtid" label="商品代码" disable="true" />
				<lemis:texteditor property="pdtnm" label="助听器名称" disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="OrderAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


