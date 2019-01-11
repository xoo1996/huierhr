<!--/recommendation/reports/report.jsp-->
<%@ page import="java.util.TreeMap"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
 String today = DateUtil.getSystemCurrentTime().toString();
 String date = today.substring(0,4);
 int date1=Integer.parseInt(date);
%>
<link href='/lemis/css/style.css' rel='stylesheet' type='text/css'>
 <lemis:base/> 
<script src="/lemis/js/lemisTree.js"></script>
<lemis:body> 
<%
  TreeMap list = new TreeMap();
   list.put("1","第一季度");
   list.put("2","第二季度");   
   list.put("3","第三季度");
   list.put("4","第四季度");
  

   pageContext.setAttribute("data",list);
   TreeMap list1 = new TreeMap();
   list1.put("2005","2005");
   list1.put("2006","2006");   
   list1.put("2007","2007");
   list1.put("2008","2008");
   list1.put("2009","2009");
   list1.put("2010","2010");   
   list1.put("2011","2011");
   list1.put("2012","2012");

   pageContext.setAttribute("data1",list1);

   TreeMap list2 = new TreeMap();
   list2.put("1","供求总体人数");
   list2.put("2","按产业分组的需求人数");   
   list2.put("3","按行业分组的需求人数");
   list2.put("4","按用人单位性质划分的需求人数");
   list2.put("5","按职业分组的供求人数");
   list2.put("8","按求职人员类别分组的求职人数");   
   list2.put("9","按性别分组的供求人数");
   list2.put("10","按年龄分组的供求人数");
   list2.put("11","按文化程度分组的供求人数");
   list2.put("12","按技术等级分组的供求人数");
   list2.put("6","需求大于求职缺口最大的十个职业");
   list2.put("7","需求小于求职缺口最大的十个职业");
  pageContext.setAttribute("data2",list2);

%>
<lemis:title title="季度统计报表"/>
<div align="left">
  <form name="report" >
    <table  class="TableInput" >
       <lemis:editorlayout/>
      <tr>
        <lemis:codelisteditor type="a003" redisplay="true" dataMeta="data2" label="统计项目" required="true" colspan="3"/>
        <lemis:codelisteditor type="year" redisplay="true" dataMeta="data1" label="年度" required="true"/>
      </tr>
  <tr>
	    <lemis:texteditor property="ssjqnm" required="true" label="所属机构" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
		<input type="hidden" name="cce001" >
		<td colspan="2">是否包含下级数据<INPUT type="checkbox" name="sfbh"  value="1" checked="1"/></td>
	    <lemis:codelisteditor type="season" redisplay="true" dataMeta="data" label="季度" required="true"/>
	    
 </tr>
</table>
    <p align="center">
<div align="right">
<input type="button" name="openwin" value="&nbsp;打&nbsp;&nbsp;印&nbsp;"   class='buttonGray'  onclick="print()">      
<input type="reset" name="Submit" value="&nbsp;重&nbsp;&nbsp;置&nbsp;"  class='buttonGray' > 
</div>
</p>
  </form>
  <lemis:errors/>
  </lemis:body>
</div>
</html>

<script language="javascript">

function print()
{

	
	if(document.forms[0].a003.value==""){
		alert("请选则要打印统计的项目！")
		return false;
	}
	if(document.forms[0].year.value==""){
		alert("请选则年份！")
		return false;
	}

	if(document.forms[0].season.value==""){
		alert("请选则季度！")
		return false;
	}
	window.open('/'+lemis.WEB_APP_NAME+'/recommendation/reports/'+document.forms[0].a003.value+'.jsp?sfbh='+document.forms[0].sfbh.value+'&year='+document.forms[0].year.value+'&season='+document.forms[0].season.value+'&a021='+document.forms[0].cce001.value,'print1','height='+(screen.height-80)+ ',width='+(screen.width-20)+',top=5, left=5, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes');
 
}

 </script>
