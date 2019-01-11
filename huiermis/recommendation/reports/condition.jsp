<!--/recommendation/reports/condition.jsp-->
<%@ page import="java.util.TreeMap"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
 String today = DateUtil.getSystemCurrentTime().toString();
 String date = today.substring(0,4);
 
%>
<link href='/lemis/css/style.css' rel='stylesheet' type='text/css'>
 <lemis:base/> 
<script src="/lemis/js/lemisTree.js"></script>
<lemis:body> 
<%

    List editors = new ArrayList();
	editors.add(new Editor("year","year","���"));
	
   pageContext.setAttribute("editor",editors);
   	
   List header = new ArrayList();
   header.add(new Formatter("brt996","���"));  
   header.add(new Formatter("brt003","���ڵǼ���Ƹ����"));
   header.add(new Formatter("brt004","���ڵǼ���ְ����"));
   header.add(new Formatter("brt009","����ְҵָ������"));
   header.add(new Formatter("brt010","���ڽ��ܳɹ�����"));
   pageContext.setAttribute("header",header);

   Map hidden = new LinkedHashMap();
     hidden.put("brt996","���");
   pageContext.setAttribute("hidden",hidden);

	List buttons=new ArrayList();
	buttons.add(new Button("accept1", "�� ��", "lab020209",
			"accept1(document.forms[0])"));
	buttons.add(new Button("query", "�� ѯ", "lab020210",
			"query(document.forms[0])"));
	buttons.add(new Button("resetForm", "�� ��", "lab020211",
			"resetForm(document.forms[0])"));

   pageContext.setAttribute("buttons",buttons);




%>
<lemis:title title="ְҵ���ܹ������"/>
		<html:form action="/Rec06Action.do?method=query" method="POST"
			>
			<lemis:tabletitle title="�����ļ�" />
			<table class="tableinput">
				<lemis:editorlayout />

				<tr>
					<lemis:formateditor mask="year" property="year" label="���"
						disable="false" required="false" />



				</tr>


				</html:form>
				<lemis:buttons buttonMeta="buttons" />
			</TABLE>
<lemis:query action="/Rec06Action.do?method=query" editorMeta="editor" topic="ְҵ���ܹ������"/>
<lemis:table topic="ְҵ���ܹ������ͳ�Ʊ�����Ϣ" action="/Rec06Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio"/>

  <lemis:errors/>
  </lemis:body>

</html>

<script language="javascript">

function print()
{

	if(document.forms[0].year.value==""){
		alert("��ѡ����ݣ�")
		return false;
	}

	window.open('/'+lemis.WEB_APP_NAME+'/recommendation/reports/1p.jsp?year='+document.forms[0].year.value,'print1','height='+(screen.height-80)+ ',width='+(screen.width-20)+',top=5, left=5, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes');
 
}

 </script>
