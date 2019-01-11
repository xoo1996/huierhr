<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<lemis:base />
<%		
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("aae011", "操作员"));			
	header.add(new Formatter("temp03", "推荐人次"));
	header.add(new Formatter("temp02", "推荐中人次"));
	header.add(new Formatter("temp04", "成功人次"));
	header.add(new Formatter("temp05", "失败人次"));
	header.add(new Formatter("temp06", "成功率"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("统计查询","stat(document.forms[0])");
	buttons.put("重 置","resetForm(document.forms[0])");

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "区域销售统计表");//表头
%>
<html>
<script language="javascript">
	//统计查询
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=queryqyxs"/>';
		obj.submit();
	};
	//显示图形
	function showtx(obj) {
		if (document.all.iframediv.style.display == 'none') {
			document.all.iframediv.style.display = '';
			document.all.showtx.value = "关闭图形";
		} else {
			document.all.iframediv.style.display = 'none';
			document.all.showtx.value = "显示图形";
		}
	}
</script>
<lemis:body>
	<lemis:errors />
	<lemis:title title="查询" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<td style="vertical-align: top"><lemis:tabletitle title="统计条件" />
					<table class="TableInput">
						<html:form action="/BusinessAction.do" method="post">
							<lemis:editorlayout />
							<tr>
								<lemis:texteditor property="ivtyear" disable="false"
									required="true" label="年份" />
								<lemis:texteditor property="ivtmonth" disable="false"
									required="false" label="月份" />
							</tr>
						</html:form>
					</table>

					<lemis:buttons buttonMeta="button" /></td>
				</tr>
				<tr>
					<TD><lemis:table action="BusinessAction.do"
						headerMeta="header" hiddenMeta="hidden" topic="区域销售统计信息"
						mode="radio" pilot="true" orderResult="true" /></TD>
				</tr>
			</table>
			</TD>
			<TD style="vertical-align: top;" id="iframediv">
			<table border="0">
				<tr>
					<td><iframe
						src='<html:rewrite page="/ChartAction.do?method=show"/>'
						style="display: inline" align="center" id="iframe1" name="iframe1"
						frameborder="0" width="100%" framespacing="0"
						allowTransparency="true"></iframe></td>
				</tr>
			</table>
			</TD>
		</tr>
	</table>
	<lemis:bottom />
</lemis:body>
</html>

