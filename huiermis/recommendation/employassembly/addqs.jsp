<!--lemis/recommendation/employassembly/addqs.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<% 

  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_save","保 存","rec070302","addsave(document.forms[0])"));
  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"Rec0703Form\")"));
  pageContext.setAttribute("buttons",buttons);
  %>
<html>
<lemis:base/>
<lemis:body>
<script src="/lemis/js/lemisTree.js"></script>

  <%//标题部分%>
  <lemis:title title="增加大会招聘期数信息"/>
  <%//录入部分%>
  <html:form action="/Rec0703Action.do?method=addqs" method="POST">
  <lemis:tabletitle title="大会招聘期数基本信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>
     <lemis:texteditor property="ssjqnm" required="true" label="所属机构" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
	 <html:hidden property="cce001" />
	 <lemis:formateditor property="acb231" label='期数' disable="false" mask="period" required="false"/>
    </tr>
   <tr>
     <lemis:formateditor mask="date" property="acb232" label="招聘日期" disable="false" required="true"/>
     <lemis:texteditor property="acb233" label="招聘地点" maxlength="30" disable="false" colspan="3"/>
   </tr>   
    <tr>
      <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="false" colspan="5"/> 
    </tr>
    			<tr>
					<td>经办人</td>
					<td><lemis:operator /></td>
					<td>经办机构</td>
					<td><lemis:operorg /></td>
					<td>经办日期</td>
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
	   obj.action = '<html:rewrite page="/Rec0703Action.do?method=saveqs"/>';
	   obj.submit();
     }

   </script>
 

