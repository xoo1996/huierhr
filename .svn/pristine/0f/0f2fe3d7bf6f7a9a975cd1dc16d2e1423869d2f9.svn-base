<!--lemis/recommendation/ownpintorgan/modifyorganyc.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<% 

  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_save","保 存","rec080202","addsave(document.forms[0])"));
  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"Rec08Form\")"));
  pageContext.setAttribute("buttons",buttons);
  %>
<html>
<lemis:base/>
<lemis:body>
<script src="/lemis/js/lemisTree.js"></script>

  <%//标题部分%>
  <lemis:title title="编辑机构年检信息"/>
  <%//录入部分%>
  <html:form action="/Rec0802Action.do?method=addorgan" method="POST">
  <lemis:tabletitle title="机构基本信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
   <html:hidden property="acb240"/>
   <html:hidden property="acb241"/>
   <tr>
      <lemis:texteditor property="acb241" label="机构名称" maxlength="30" disable="true" required="false"/>
      <lemis:texteditor property="ssjqnm" required="false" label="管理机构" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
	  <html:hidden property="cce001" />
	 <lemis:codelisteditor type="aab019" label="企业性质" required="false" isSelect="false" redisplay="true"/>
   </tr>
   <tr>
   <lemis:texteditor property="aae006" label="营业地址" maxlength="30" disable="true" colspan="3" required="false"/>
   <lemis:formateditor mask="money" property="acb242" label="面积" disable="true" required="false" format="false"/>
   </tr>
   <tr>
    <lemis:texteditor property="acb243" label="核准证编号" maxlength="30" disable="true"  required="false"/>
   <lemis:texteditor property="aae005" label="联系电话" maxlength="30" disable="true"  required="false"/>
   <lemis:formateditor mask="money" property="aab049" label="注册资金" disable="true" required="false" format="false" />
   </tr>
    <tr>
     <lemis:formateditor property="acb247" label='工作人员数量' disable="true" mask="nnnn" required="false"/>
     <lemis:formateditor property="acb248" label='有职介资格人数' disable="true" mask="nnnn" required="false"/>
     <lemis:texteditor property="acb243" label="核准证编号" maxlength="30" disable="true"  required="false"/>
    </tr>
     <tr>
      <lemis:texteditor property="acb24a" label="市、县劳人厅审批意见" maxlength="100"  disable="true" colspan="5"/> 
    </tr>  
    </table>
     <lemis:tabletitle title="机构年检基本信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
   <tr>
      <lemis:texteditor property="aae001" label="年检年份" maxlength="30" disable="true" required="false"/>
	 <lemis:codelisteditor type="acb251" label="年检结果" required="true" isSelect="true" redisplay="true"/>
	 <lemis:formateditor property="acb252" label='工作人员数量' disable="false" mask="nnnn" required="false"/>
   </tr>
   <tr> 
    <lemis:formateditor property="acb253" label='有职介资格人数' disable="false" mask="nnnn" required="false"/>
    <lemis:formateditor property="acb254" label='上年登记岗位数' disable="false" mask="nnnn" required="false"/>
    <lemis:formateditor property="acb255" label='登记人数' disable="false" mask="nnnn" required="false"/>
   </tr>
   <tr>
   <lemis:formateditor property="acb256" label='介绍成功人数' disable="false" mask="nnnn" required="false"/>
   <lemis:texteditor property="acb257" label="微机型号" maxlength="30" disable="false" colspan="3" required="false"/>
   </tr>
   <tr>
   <lemis:formateditor property="acb258" label='微机数量' disable="false" mask="nnnn" required="false"/>
   <lemis:texteditor property="acb259" label="许可证号" maxlength="30" disable="false"  required="false"/>
   <lemis:formateditor property="acb25a" label='市检总分' disable="false" mask="nnnn" required="false"/>
   </tr>
     <tr>
      <lemis:texteditor property="acb25b" label="市、区劳动部意见" maxlength="100"  disable="false" colspan="5"/> 
    </tr> 
    <tr>
      <lemis:texteditor property="acb25c" label="就业服务管理局意见" maxlength="100"  disable="false" colspan="5"/> 
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
	   obj.action = '<html:rewrite page="/Rec0802Action.do?method=modifyorganyc"/>';
	   obj.submit();
     }

   </script>
 

