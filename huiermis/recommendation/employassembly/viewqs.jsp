<!--lemis/recommendation/employassembly/viewqs.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<% 

  List buttons=new ArrayList();//���尴ť����
  buttons.add(new Button("Btn_back","�� ��","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"Rec0703Form\")"));
  pageContext.setAttribute("buttons",buttons);
  %>
<html>
<lemis:base/>
<lemis:body>
<script src="/lemis/js/lemisTree.js"></script>

  <%//���ⲿ��%>
  <lemis:title title="�鿴�����Ƹ������Ϣ"/>
  <%//¼�벿��%>
  <html:form action="/Rec0703Action.do?method=addqs" method="POST">
  <lemis:tabletitle title="�����Ƹ����������Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>
     <lemis:texteditor property="ssjqnm" required="false" label="�����ֵ�" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
	 <html:hidden property="cce001" />
     <lemis:texteditor property="acb231" label="����" maxlength="30" disable="true" colspan="3" required="false"/>
    </tr>
   <tr>
     <lemis:formateditor mask="date" property="acb232" label="��Ƹ����" disable="true" required="false" format="true"/>
     <lemis:texteditor property="acb233" label="��Ƹ�ص�" maxlength="30" disable="true" colspan="3"/>
   </tr>   
    <tr>
      <lemis:texteditor property="aae013" label="��ע" maxlength="100"  disable="true" colspan="5"/> 
    </tr>
    			<tr>
					<td>������</td>
					<td><lemis:operator /></td>
					<td>�������</td>
					<td><lemis:operorg /></td>
					<td>��������</td>
					<td><lemis:operdate /></td> 			
			</tr>
     </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>
  <lemis:errors/>
  <lemis:bottom/></lemis:body>
  </html>

