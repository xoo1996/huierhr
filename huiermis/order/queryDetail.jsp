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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返 回","window.history.back();");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtcltnm","用户姓名"));
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtprc","售价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("header",header);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<lemis:base />
<lemis:body>
	<lemis:title title="查看订单明细" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<lemis:texteditor property="folctid" label="客户代码" disable="true" />
				<lemis:texteditor property="folctnm" label="客户名称" disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="foloprnm" label="录单员" disable="true" />
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<lemis:texteditor property="foldt" label="订货日期" disable="true" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细" action="/OrderDetailAction.do"
		headerMeta="header" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


