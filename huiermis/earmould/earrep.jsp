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
	header.add(new Formatter("repgctnm", "客户名称"));
	header.add(new Formatter("repcltnm", "用户姓名"));
	header.add(new Formatter("repid", "耳模编号"));
	header.add(new Formatter("reppnm", "助听器名称"));
	header.add(new Formatter("repdate", "送修日期","", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("repgctid", "送修单位"));
	header.add(new Formatter("reppdate","计划完工"));
	header.add(new Formatter("repsta", "维修状态"));
	header.add(new Formatter("repcls", "维修类别"));
	header.add(new Formatter("repcpy", "维修厂商"));
	header.add(new Formatter("repamt", "维修费"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("耳模维修登记","weixiudengji(document.all.tableform)");
	buttons.put("耳模维修记录修改","alterweixiujilu(document.all.tableform)");
	buttons.put("耳模惠耳维修","huierweixiu(document.all.tableform)");
	buttons.put("耳模维修完成确认","finishedconfirm(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "维修流水号");
	hidden.put("repgctnm", "送修单位名称");
	hidden.put("repgctid", "送修单位代码");
	hidden.put("repcltnm", "用户姓名");
	hidden.put("reppid", "助听器代码");
	hidden.put("repid", "耳模编号");
	hidden.put("repcpy", "维修厂商");
	hidden.put("reppnm", "助听器名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","订单号"));
	editors.add(new Editor("text","repid","耳模编号"));
	editors.add(new Editor("text","repcltnm", "用户姓名"));
	editors.add(new Editor("text","repcpy", "维修厂商"));
	editors.add(new Editor("text","repsta", "维修状态"));
	editors.add(new Editor("text","reppid", "助听器代码"));
	editors.add(new Editor("text","reppnm", "助听器名称"));
	editors.add(new Editor("date","start", "完工日期从"));
	editors.add(new Editor("date","end", "到"));
	editors.add(new Editor("text","repcls", "维修类别"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">

	//耳模维修登记
	function weixiudengji(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/earmould&page=/earrepregister.jsp"/>';
		obj.submit();
	};
	
	//耳模维修记录修改
	function alterweixiujilu(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/earmould&page=/modify.jsp"/>';
		obj.submit();
	};
	
	
	//耳模惠耳维修
	function huierweixiu(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/querywx.jsp"/>';
		obj.submit();
	};
	
	//耳模维修完成确认
	function finishedconfirm(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/confirm.jsp"/>';
		obj.submit();
	};
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="耳模维修记录查询" />
	<lemis:query action="/EarMouldAction.do?method=queryRepair" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="EarMouldAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


