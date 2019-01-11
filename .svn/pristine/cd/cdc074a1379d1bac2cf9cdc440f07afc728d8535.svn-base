<!--/lemis/recommendation/employassembly/ViewEmployInvite.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<% 
  String viewE = (String)request.getSession().getAttribute("viewE");
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_back","返 回","rec999997", "back(\""+viewE.replaceAll(";viewE;","&")+"\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999", "closeWindow(\"viewE,Rec0702Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="查看单位招聘信息"/>

  <%//录入部分%>
  <lemis:tabletitle title="单位招聘信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0702Action.do" method="POST">
     <tr>
      <lemis:texteditor property="aab004" label="单位名称" maxlength="100" disable="true" colspan="3" />
      <lemis:texteditor  property="acb206" label="发布名称" maxlength="20" disable="true"  />     
      </tr>

    <tr>
      
      <lemis:codelisteditor type="acb201" label="招聘方式"  isSelect="false"   redisplay="true"/>
      <lemis:texteditor property="acb202" label="招聘地点" maxlength="30" disable="true"/>
      <lemis:texteditor property="acb203" label="招聘地区" maxlength="30" disable="true"/>
      </tr>

    <tr>
      <lemis:texteditor property="acb204" label="工作地点" maxlength="80" disable="true" colspan="3"/>
      <lemis:texteditor property="acb205" label="乘车路线" maxlength="200" disable="true"  />
      </tr>
    <tr>
      <lemis:texteditor property="aae004" label="联系人" maxlength="20" disable="true"  />
      <lemis:texteditor property="aac002" label="公民身份号码" maxlength="18"  disable="true"/>
      <lemis:texteditor property="aae005" label="联系电话" maxlength="20"  disable="true"/>
      </tr>
    <tr>
      <lemis:formateditor mask="date" property="acb207" label="面试日期" disable="true" required="false" format="true"/>
      <lemis:texteditor property="acb20c" label="面试地点" maxlength="50"  disable="true" colspan="3" />
      
    </tr>
    <tr>
      <lemis:texteditor property="acb20f" label="面试详细说明" maxlength="200" disable="true"/>
      <lemis:formateditor mask="money"  property="acb209" label="收费金额" disable="true" required="false"/>
    <lemis:formateditor mask="date" property="aae043" label="登记日期" disable="true" required="false" format="true"/>
    
    </tr>
   
  
    <tr>
     <lemis:texteditor property="acb20b" label="分配职介员" maxlength="30"  disable="true"/>
       <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="true" colspan="3"/>
    </tr>
    <tr>

      <lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
        <lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" />
      <lemis:formateditor mask="date" property="aae036" label="经办日期" disable="true" required="false" format="true"/>
    </tr>

  </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>

  <%//界面模型规定的其他标配部分%>
  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //上一步
  function back(url){
    location.href=url;
  }
 </script>

