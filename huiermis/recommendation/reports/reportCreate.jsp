<!--/recommendation/reports/reportCreate.jsp-->
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
<script src='/lemis/js/prototype.js'></script> 
<lemis:body> 
<%
  TreeMap list = new TreeMap();
   list.put("01","一月");
   list.put("02","二月");   
   list.put("03","三月");
   list.put("04","四月");
   list.put("05","五月");
   list.put("06","六月");   
   list.put("07","七月");
   list.put("08","八月");
   list.put("09","九月");
   list.put("10","十月");   
   list.put("11","十一月");
   list.put("12","十二月");
   
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
   list2.put("yb01","人员类别＊文化程度＊性别");
   list2.put("yb02","职业(细)＊年龄＊文化程度＊性别");   
   list2.put("yb03","职业（中）＊文化程度＊技术等级");
   list2.put("yb04","户口");
   list2.put("yb05","流动状况");
   list2.put("yb11","行业＊经济类型＊隶属关系");   
   list2.put("yb12","行业＊企业规模");
   list2.put("yb13","单位性质");
   list2.put("yb21","职业（细）＊行业＊文化程度");
   list2.put("yb22","职业（中）＊行业＊年龄");
   list2.put("yb23","职业（中）＊技术等级");
   list2.put("yb24","职业（大）＊合同期限");
   list2.put("yb25","行业＊经济类型");
   list2.put("yb26","性别");   
   list2.put("yb27","用工形式");
   list2.put("yb28","单位性质");
   list2.put("yb29","隶属关系");
   
  pageContext.setAttribute("data2",list2);

%>
<lemis:title title="生成报表"/>
<div align="left">
  <form name="reportCreate" >
    <table  class="TableInput" >
       <lemis:editorlayout/>
      <tr>
        <lemis:codelisteditor type="type" redisplay="true" dataMeta="data2" label="类型" required="true" />
        <lemis:codelisteditor type="year" redisplay="true" dataMeta="data1" label="年度" required="true"/>
      </tr>
  <tr>
	    <lemis:codelisteditor type="month" redisplay="true" dataMeta="data" label="月份" required="true"/>	    
 </tr>
</table>
    <p align="center">
<div align="right">
<input type="button" name="openwin" value="生成报表"   class='buttonGray'  onclick="reportCreat()">      
<input type="reset" name="Submit" value="&nbsp;重&nbsp;&nbsp;置&nbsp;"  class='buttonGray' > 
</div>
</p>
  </form>
  <lemis:errors/>
  </lemis:body>
</div>
</html>

<script language="javascript">

function reportCreat()
{	
	if(document.forms[0].type.value==""){
		alert("请选择类型！")
		return false;
	}
	if(document.forms[0].year.value==""){
		alert("请选择年份！")
		return false;
	}
	if(document.forms[0].month.value==""){
		alert("请选择月份！")
		return false;
	}
	window.location.href="/" + lemis.WEB_APP_NAME +"/recommendation/Rec0604Action.do?method=reportCreate&year="+$F('year')+"&month="+$F('month')+"&type="+$F('type');
}

 </script>
