<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

<%  

	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("rsvnm", "预约客户姓名"));
	//header.add(new Formatter("rsvsta", "预约状态"));
	//header.add(new Formatter("rsvtodayid", "当天号码"));
	header.add(new Formatter("rsvgctnm", "预约单位"));
	header.add(new Formatter("ypnm","预约专家"));
	header.add(new Formatter("rsvgctzhuannm", "转诊单位"));
	header.add(new Formatter("rsvphone", "手机号码"));
	
	header.add(new Formatter("rsvdate", "预约日期"));
	header.add(new Formatter("rsvnote","备注"));
	
	
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	//hidden.put("stogrcliid", "");
	

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	//pageContext.setAttribute("editor", editors);
	//pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	
%> 


<lemis:base />
<lemis:body>
	<lemis:title title="预约明细" />

	<lemis:table action="/ReservationAction.do" headerMeta="header"
	 topic="预约明细" mode="radio" orderResult="false"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
