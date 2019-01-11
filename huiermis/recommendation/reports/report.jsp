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
   list.put("1","��һ����");
   list.put("2","�ڶ�����");   
   list.put("3","��������");
   list.put("4","���ļ���");
  

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
   list2.put("1","������������");
   list2.put("2","����ҵ�������������");   
   list2.put("3","����ҵ�������������");
   list2.put("4","�����˵�λ���ʻ��ֵ���������");
   list2.put("5","��ְҵ����Ĺ�������");
   list2.put("8","����ְ��Ա���������ְ����");   
   list2.put("9","���Ա����Ĺ�������");
   list2.put("10","���������Ĺ�������");
   list2.put("11","���Ļ��̶ȷ���Ĺ�������");
   list2.put("12","�������ȼ�����Ĺ�������");
   list2.put("6","���������ְȱ������ʮ��ְҵ");
   list2.put("7","����С����ְȱ������ʮ��ְҵ");
  pageContext.setAttribute("data2",list2);

%>
<lemis:title title="����ͳ�Ʊ���"/>
<div align="left">
  <form name="report" >
    <table  class="TableInput" >
       <lemis:editorlayout/>
      <tr>
        <lemis:codelisteditor type="a003" redisplay="true" dataMeta="data2" label="ͳ����Ŀ" required="true" colspan="3"/>
        <lemis:codelisteditor type="year" redisplay="true" dataMeta="data1" label="���" required="true"/>
      </tr>
  <tr>
	    <lemis:texteditor property="ssjqnm" required="true" label="��������" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
		<input type="hidden" name="cce001" >
		<td colspan="2">�Ƿ�����¼�����<INPUT type="checkbox" name="sfbh"  value="1" checked="1"/></td>
	    <lemis:codelisteditor type="season" redisplay="true" dataMeta="data" label="����" required="true"/>
	    
 </tr>
</table>
    <p align="center">
<div align="right">
<input type="button" name="openwin" value="&nbsp;��&nbsp;&nbsp;ӡ&nbsp;"   class='buttonGray'  onclick="print()">      
<input type="reset" name="Submit" value="&nbsp;��&nbsp;&nbsp;��&nbsp;"  class='buttonGray' > 
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
		alert("��ѡ��Ҫ��ӡͳ�Ƶ���Ŀ��")
		return false;
	}
	if(document.forms[0].year.value==""){
		alert("��ѡ����ݣ�")
		return false;
	}

	if(document.forms[0].season.value==""){
		alert("��ѡ�򼾶ȣ�")
		return false;
	}
	window.open('/'+lemis.WEB_APP_NAME+'/recommendation/reports/'+document.forms[0].a003.value+'.jsp?sfbh='+document.forms[0].sfbh.value+'&year='+document.forms[0].year.value+'&season='+document.forms[0].season.value+'&a021='+document.forms[0].cce001.value,'print1','height='+(screen.height-80)+ ',width='+(screen.width-20)+',top=5, left=5, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes');
 
}

 </script>
