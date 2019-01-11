<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
header.add(new Formatter("fdtfno", "订单号"));
header.add(new Formatter("folctnm", "团体客户"));
header.add(new Formatter("fdtcltnm", "个人客户"));
header.add(new Formatter("tmksid", "机身编号"));
header.add(new Formatter("pdtnm", "商品名称"));
header.add(new Formatter("fdtqnt", "数量"));
header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
header.add(new Formatter("folsdt", "发货日期","", TagConstants.DT_YEAR_MONTH_DATE));
header.add(new Formatter("foltype", "订单类型"));
header.add(new Formatter("foloprnm", "录单员"));
header.add(new Formatter("fdtodt", "退机日期","", TagConstants.DT_YEAR_MONTH_DATE));
header.add(new Formatter("fdtnt", "备注"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "个人客户退机记录表");//表头
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="退机记录查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="用户编号" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false"
					disable="true" />
				<lemis:texteditor property="ictgctid" label="所属团体" required="false"
					disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="OrderAction.do" headerMeta="header"
		topic="退机记录" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


