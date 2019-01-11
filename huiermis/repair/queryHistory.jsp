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
	header.add(new Formatter("reppnm", "助听器名称"));
	header.add(new Formatter("repcls", "维修类别"));
	header.add(new Formatter("repdate", "送修日期"));
	header.add(new Formatter("repfdate", "完工日期"));
	header.add(new Formatter("repoprnm", "维修人员"));
	header.add(new Formatter("repamt", "维修费"));
	header.add(new Formatter("repaction1", "维修措施一", true));
	header.add(new Formatter("repaction2", "维修措施二", true));
	header.add(new Formatter("repaction3", "维修措施三", true));
	header.add(new Formatter("repaction4", "维修措施四", true));
	header.add(new Formatter("repaction5", "维修措施五", true));
	header.add(new Formatter("repaction6", "维修措施六", true));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	/* buttons.put("新增","newSC(document.all.tableform)"); */
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkgctnm", "送修单位名称");
	hidden.put("tmkgctid", "送修单位代码");
	hidden.put("tmkcltid", "用户代码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkpid", "助听器代码");
	hidden.put("tmksid", "机身编号");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>


<lemis:base />
<lemis:body>
	<lemis:title title="维修历史查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do" method="POST">
			<tr>
				<lemis:texteditor property="tmkcltid" label="用户代码" disable="true" />
				<lemis:texteditor property="tmkcltnm" label="用户姓名" disable="true" />
				<lemis:texteditor property="tmkgctnm" label="所属团体" disable="true" />
				<html:hidden property="tmkgctid" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkfno" label="订单号" disable="true" />
				<lemis:texteditor property="tmkpnm" label="助听器名称" disable="true" />
				<html:hidden property="tmkpid" />
				<lemis:texteditor property="tmksid" label="机身编号" disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="RepairAction.do" headerMeta="header" topic="维修历史信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


