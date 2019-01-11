<%@ page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://cewolf.sourceforge.net/taglib/cewolf.tld"
	prefix="cewolf"%>
<html:html>
<lemis:base />
<lemis:body>
	<jsp:useBean id="dataProducer"
		class="org.radf.commons.chart.BaseDataProducer" scope="request" />
	<jsp:useBean id="chartEnhancer"
		class="org.radf.apps.commons.AudiogramExtraChartEnhancer" scope="request" />
	<jsp:useBean id="params" class="org.radf.commons.chart.ChartParams"
		scope="request" />
	<table border="0">
		<tr>
			<td>
				<cewolf:chart id="chart"
    				type="<%=params.getChartType()%>"
    				title='<%= params.getTitle() %>'
    				antialias='<%= params.getAntialias() %>'
    				showlegend='<%= params.isLegend() %>'
    				legendanchor='<%= params.getLegendAnchor() %>'
    				xaxislabel = '频率'
    				yaxislabel = '分贝' >
				<cewolf:data>
					<cewolf:producer id="dataProducer" usecache="false">
						<cewolf:param name="data" value="<%= params.getData()%>" />
						<cewolf:param name="sqlmap"
							value="<%= (java.io.Serializable)params.getSqlmap()%>" />
						<cewolf:param name="hqlmap"
							value="<%= (java.io.Serializable)params.getHqlmap()%>" />
					</cewolf:producer>
				</cewolf:data>
				<cewolf:chartpostprocessor id="chartEnhancer">
					<cewolf:param name="title" value="<%=params.getExtraTitle()%>" />
				</cewolf:chartpostprocessor>
			</cewolf:chart> 
			<cewolf:img chartid="chart" renderer="/cewolf"
				width="<%= params.getWidth() %>" 
				height="<%= params.getHeight() %>"
				mime="<%= params.getMimeType() %>">
			</cewolf:img> 
			</td>
		</tr>
	</table>



</lemis:body>
</html:html>
<script type="text/javascript">
	//parent.document.all("iframe1").height = document.body.scrollHeight;
	//parent.document.all("iframe2").height = document.body.scrollHeight;
	//生成图表
	function save(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite page="/ChartAction.do?method=show"/>';
		obj.submit();
		return true;
	}
</script>

