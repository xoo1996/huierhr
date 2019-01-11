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
	buttons.put("返回","window.history.back();");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("ictnm","客户名称"));
	header.add(new Formatter("pdtnm","商品名称"));
	//header.add(new Formatter("ivtoprdt","回款时间"));
	header.add(new Formatter("ivtpqnt","回款数"));
	header.add(new Formatter("ivtpamnt","回款金额",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("header",header);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<lemis:base />
<lemis:body>
	<lemis:title title="查看区域销售明细" />
    <lemis:tabletitle title="销售区域详细信息" />
    <table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ivtnote" label="销售区域" disable="true" />
			   <lemis:texteditor property="ivtyear" label="年" disable="true" />
			   <lemis:texteditor property="ivtmonth" label="月" disable="true" />
			</tr>
		</html:form>
	</table>
    
	<lemis:table topic="区域销售基本信息" action="/BusinessAction.do"
		headerMeta="header" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


