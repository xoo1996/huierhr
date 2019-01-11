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
	header.add(new Formatter("tmkfno", "订单号"));
	header.add(new Formatter("tmkgctnm", "客户名称"));
	header.add(new Formatter("tmkcltnm", "用户姓名"));
	header.add(new Formatter("tmksid", "机身编号"));
	header.add(new Formatter("tmkplr", "类型"));
	header.add(new Formatter("tmkpnm", "助听器名称"));
	
	header.add(new Formatter("tmksta", "定制机状态"));
	header.add(new Formatter("tmkshmknm", "外壳制作"));
	header.add(new Formatter("tmkshinstnm", "外壳安装"));
	header.add(new Formatter("tmkqanm", "质检员"));
	header.add(new Formatter("tmkpdt", "计划完工"));
	header.add(new Formatter("tmkfmdt", "完工日期"));
	header.add(new Formatter("tmknt", "备注"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkfno", "订单号");
	hidden.put("tmksid", "定制机条形码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkpnm", "耳机名称");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "个人客户定制记录表");//表头
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="定制记录查询" />
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
	<lemis:table action="CustomizationAction.do" headerMeta="header"
		topic="定制记录" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


