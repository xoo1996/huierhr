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
	header.add(new Formatter("repdate", "送修日期"));
	header.add(new Formatter("repdeclare", "修理原因"));
	header.add(new Formatter("repconfirmed", "故障确认"));
	header.add(new Formatter("repaction1", "措施一"));
	header.add(new Formatter("repaction2", "措施二"));
	header.add(new Formatter("repaction3", "措施三"));
	header.add(new Formatter("repaction4", "措施四"));
	header.add(new Formatter("repaction5", "措施五"));
	header.add(new Formatter("repaction6", "措施六"));
	header.add(new Formatter("repcls", "修理类别"));
	header.add(new Formatter("repamt", "金额"));
	header.add(new Formatter("repoprnm", "修理员"));
	header.add(new Formatter("repfdate", "完工日期"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "个人客户维修记录表");//表头
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="用户编号" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false"
					disable="true" />
				<lemis:texteditor property="gctnm" label="所属团体" required="false"
					disable="true" /><%-- property="ictgctid" --%>
			</tr>
		</html:form>
	</table>
	<lemis:table action="RepairAction.do" headerMeta="header"
		topic="维修记录" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


