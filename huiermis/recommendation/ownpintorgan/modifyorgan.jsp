<!--lemis/recommendation/employassembly/modifyorgan.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<% 

  List buttons=new ArrayList();//���尴ť����
  buttons.add(new Button("Btn_save","�� ��","rec080102","addsave(document.forms[0])"));
  buttons.add(new Button("Btn_back","�� ��","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"Rec08Form\")"));
  pageContext.setAttribute("buttons",buttons);
  %>
<html>
<lemis:base/>
<lemis:body>
<script src="/lemis/js/lemisTree.js"></script>

  <%//���ⲿ��%>
  <lemis:title title="���ӻ�����Ϣ"/>
  <%//¼�벿��%>
  <html:form action="/Rec08Action.do?method=addorgan" method="POST">
  <lemis:tabletitle title="����������Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
   <tr>
      <lemis:texteditor property="acb241" label="��������" maxlength="30" disable="false" required="true"/>
      <lemis:texteditor property="ssjqnm" required="true" label="�������" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
	  <html:hidden property="cce001" />
	 <lemis:codelisteditor type="aab019" label="��ҵ����" required="true" isSelect="true" redisplay="true"/>
   </tr>
   <tr>
   <lemis:texteditor property="aae006" label="Ӫҵ��ַ" maxlength="30" disable="false" colspan="3" required="true"/>
   <lemis:formateditor mask="money" property="acb242" label="���" disable="false" required="false" format="false"/>
   </tr>
   <tr>
    <lemis:texteditor property="acb243" label="��׼֤���" maxlength="30" disable="false"  required="false"/>
   <lemis:texteditor property="aae005" label="��ϵ�绰" maxlength="30" disable="false"  required="false"/>
   <lemis:texteditor property="acb244" label="̯λ��" maxlength="30" disable="false"  required="false"/> 
   </tr>
   <tr>
   <lemis:texteditor property="acb245" label="΢���ͺ�" maxlength="30" disable="false"  required="false"/>
    <lemis:texteditor property="aac003" label="����" maxlength="30" disable="false"  required="true"/>
    <lemis:formateditor mask="card" property="aac002" label="������ݺ���" required="true" disable="false"/>
   </tr>
   <tr> 
     <lemis:codelisteditor type="aac004" label="�Ա�" required="false" isSelect="true" redisplay="false"/>
     <lemis:formateditor mask="date" property="aac006" label="��������" disable="false" required="false" format="true"/>
     <lemis:codelisteditor type="aac011" label="�Ļ��̶�" required="false" isSelect="true" redisplay="false"/>
   </tr>
   <tr>
      <lemis:texteditor property="acb246" label="���˼���" maxlength="100"  disable="false" colspan="5"/> 
    </tr>  
    <tr>
    <lemis:formateditor mask="money" property="aab049" label="ע���ʽ�" disable="false" required="false" format="false" />
     <lemis:formateditor property="acb247" label='������Ա����' disable="false" mask="nnnn" required="false"/>
     <lemis:formateditor property="acb248" label='��ְ���ʸ�����' disable="false" mask="nnnn" required="false"/>
    </tr>
     <tr>
      <lemis:texteditor property="acb249" label="ҵ��Χ" maxlength="100"  disable="false" colspan="5"/> 
    </tr>  
     <tr>
      <lemis:texteditor property="acb24a" label="�С����������������" maxlength="100"  disable="false" colspan="5"/> 
    </tr>  
    <tr>
      <lemis:texteditor property="aae013" label="��ע" maxlength="100"  disable="false" colspan="5"/> 
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
   <script language="javascript">
     function addsave(obj)
     {
      var t  = checkValue(obj);
          if(!t){
                  return t;
          }  
	   obj.action = '<html:rewrite page="/Rec08Action.do?method=modifyorgan"/>';
	   obj.submit();
     }

   </script>
 

