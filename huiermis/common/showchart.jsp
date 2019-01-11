<!--lemis/common/showchart.jsp  -->
<%@ page pageEncoding="GBK" %>
<%@ page contentType="text/html; charset=GBK" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://cewolf.sourceforge.net/taglib/cewolf.tld" prefix="cewolf" %>
<html:html>
<lemis:base/>
<lemis:body>
<jsp:useBean id="dataProducer" class="org.radf.commons.chart.BaseDataProducer" scope="request"/>
<jsp:useBean id="chartEnhancer" class="org.radf.commons.chart.ExtraChartEnhancer" scope="request"/>
<jsp:useBean id="params" class="org.radf.commons.chart.ChartParams" scope="request"/>

<%


	List buttons=new ArrayList();//���尴ť����
	buttons.add(new Button("closeWindow","�� ��","emp010403","closeWindow(\"Emp01Form\")"));
    pageContext.setAttribute("button",buttons);

    Map buttonb = new LinkedHashMap();
	buttonb.put("����ͼ��", "save(document.forms[0])");
	//buttonb.put("�� ��", "resetForm(this.form)"); һ����,������ͼ��ͱ���
	pageContext.setAttribute("buttonb", buttonb);
	

 %>

<table border="0">
        <tr>
          <TD>
          
       <html:form action="/ChartAction.do" method="POST">
		<html:hidden property="expressID" />
		<html:hidden property="dblevelFlag" />
		
		<table class="tableinput">
			<lemis:editorlayout />
			<tr>
			 <lemis:texteditor property="title" label="����" disable="false" maxlength="100" />
			 <lemis:texteditor property="extraTitle" label="������" disable="false" maxlength="100"/>
			</tr>	
			<tr>
			<lemis:texteditor property="length" label="��" disable="false" maxlength="100" />
			 <lemis:texteditor property="height" label="��" disable="false" maxlength="100"/>
			</tr>		
		</table>
		<table class="tableinput">
			<tr>
				<td>ͼ������</td>
				<td>ͼ����ʽ</td>
				<td>ͼ�η���</td>
			</tr>
			<tr>
				<td align="left">
					<html:radio property="chartType" value="bar">��״</html:radio>
					<html:radio property="chartType" value="pie">��ͼ</html:radio><br>
				<td align="left"><html:radio property="chart3dType" value="2D"> ��ά</html:radio>
				<html:radio property="chart3dType" value="3D">��ά</html:radio> <br>
				<td align="left"><html:radio property="direction" value="vertical">����</html:radio>
				<html:radio property="direction" value="horizontal">����</html:radio>
				
			</tr>
			
		</table>
		</html:form>
	<lemis:buttons buttonMeta="buttonb" />		
          </TD>
        </tr>
        <tr>
          <td>
<cewolf:chart
    id="chart"
    type="<%=params.getChartType()%>"
    title='<%= params.getTitle() %>'
    antialias='<%= params.getAntialias() %>'
    showlegend='<%= params.isLegend() %>'
    legendanchor='<%= params.getLegendAnchor() %>'>
    <cewolf:data>
        <cewolf:producer
            id="dataProducer" usecache="false">
            <cewolf:param
                name="data"
                value="<%= params.getData()%>"/>
            <cewolf:param
                name="sqlmap"
                value="<%= (java.io.Serializable)params.getSqlmap()%>"/>
            <cewolf:param
                name="hqlmap"
                value="<%= (java.io.Serializable)params.getHqlmap()%>"/>
        </cewolf:producer>
    </cewolf:data>
    <cewolf:chartpostprocessor
        id="chartEnhancer">
        <cewolf:param
            name="title"
            value="<%=params.getExtraTitle()%>"/>
    </cewolf:chartpostprocessor>
</cewolf:chart>
<%
String chartsql = (String)request.getSession().getAttribute("chartsql");
 if(chartsql!=null && !"".equals(chartsql)){
%>
<cewolf:img
    chartid="chart"
    renderer="/cewolf"
    width="<%= params.getWidth() %>"
    height="<%= params.getHeight() %>"
    mime="<%= params.getMimeType() %>">
</cewolf:img>
<%
 }
%>


  </td>
        </tr>
      </table>
      
      
      
    </lemis:body>
</html:html>
<script type="text/javascript">
parent.document.all("iframe1").height=document.body.scrollHeight;

		//����ͼ��
	function save(obj){
   	if (!checkValue(obj)) {
    	return false;
   	}    
 	obj.action = '<html:rewrite page="/ChartAction.do?method=show"/>';
   	obj.submit();
  	return true;
	}
</script>

