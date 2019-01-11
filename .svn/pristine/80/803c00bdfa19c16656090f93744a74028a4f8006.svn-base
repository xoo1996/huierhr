<!--/recommendation/employinvite/ViewEmptyPost.jsp-->
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

  String menuId = (String)request.getSession().getAttribute("menuId");
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
  if("queryRecommend".equals(menuId)||"queryEmployerRecommend".equals(menuId))
  {
	  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  }else
  {
	  
    buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"2\")"));
  }
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"viewP,Rec0402Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="查看招聘空位信息"/>

  <%//查看部分%>
  <lemis:tabletitle title="招聘空位信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0402Action.do?method=modEmptyPost" method="POST">

  
    <tr>
      <lemis:texteditor label="工种名称"  property="aca112" disabled="true"  />
      <html:hidden property="aca111" />
      <lemis:texteditor property="acb216" label="工种说明" maxlength="50"  disable="true" colspan="3" />		
       </tr>
       <tr>
          <lemis:formateditor mask="date" property="aae030"  label="招聘开始日期" disable="true" required="false" format="true"/>
	      <lemis:formateditor mask="date" property="aae031" label="招聘终止时间"  disable="true"  required="false" format="true"/>
         <lemis:formateditor mask="nnnnnnnn" property="acb21d" label="人数" disable="true" required="false"/>
       </tr>
       <tr>           
      	
      	<lemis:formateditor mask="nnnnnnnn" property="acb218" label="已推荐人数" disable="true" required="false"/>
      	<lemis:formateditor mask="nnnnnnnn" property="acb21a" label="已成功人数" disable="true" required="false"/>
        <lemis:texteditor property="acb217" label="招收区域" maxlength="30"  disable="true"/>
      </tr>
   
    	<tr>
 			
   		    <lemis:texteditor property="acb215" label="招收区域限制" maxlength="100"  disable="true"/>
   		    <lemis:codelisteditor type="aac048" label="用工形式"  isSelect="false"   redisplay="false" required="false" /> 
            <lemis:texteditor property="acb214" label="合同期限" maxlength="10"  disable="true" />
    </tr>
    <tr>    
     
     <lemis:formateditor   mask="nnnnnn"  property="acb21h" label="劳动报酬（元）" disable="true" required="false"/>    
     <lemis:codelisteditor type="acb21c" label="是否招收外来人员"  isSelect="false"   redisplay="true"/>
     <lemis:codelisteditor type="acb035" label="是否招收大龄人员"  isSelect="false"   redisplay="true"/>
     
    </tr>
     <tr>     
   	 <lemis:texteditor property="acb21i" label="工资待遇说明" maxlength="100"  disable="true" colspan="5" />       
     </tr>
    
    
<lemis:tabletitle title="空岗条件信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  
  
    <tr>
		<lemis:codelisteditor type="aac011" label="文化程度"  isSelect="false"   redisplay="true" required="false"/>
      <lemis:codelisteditor type="aac004" label="性别" isSelect="false"   redisplay="true" required="false"/>
      <lemis:codelisteditor type="aac015" label="职业资格等级" isSelect="false" required="false" />
      </tr>
    <tr>
      <lemis:formateditor mask="nnn" property="acb221" label="最低年龄" disable="true" required="false"/>
      <lemis:formateditor mask="nnn" property="acb222" label="最高年龄" disable="true" required="false"/>
	   <lemis:formateditor mask="nnn.n" property="aac034" label="身高(cm)" disable="true" required="false"/>
      </tr>
    <tr>
      <lemis:formateditor mask="n.n" property="aac036" label="视力" disable="true" required="false"/>
      <lemis:codelisteditor type="aac009" label="户口性质" isSelect="false"   redisplay="true"/>
      <lemis:codelisteditor type="bac299" label="人员类别" isSelect="false"   redisplay="true"/>
      </tr>
      <tr>
   		<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="false"   redisplay="true"/>
      <lemis:codelisteditor type="aac024" label="政治面貌" isSelect="false"   redisplay="true"/>
       <lemis:codelisteditor type="acb228" label="是否提供住宿" isSelect="false"   redisplay="true"/>
    </tr>     
  
    <tr>
    <lemis:codelisteditor type="aac014" label="专业技术职务" isSelect="false" required="false" />
       <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="true" colspan="3" />
    </tr>
<tr>
      <lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
        <lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" />
      <lemis:formateditor mask="date" property="aae036" label="经办日期" disable="true" required="false" format="true"/>
    </tr>
	  </table>
	  
	  
	  <lemis:tabletitle title="单位基本信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  
<tr>
      <lemis:texteditor property="aab003" label="组织结构代码"    disable="true"  />
      <lemis:texteditor property="aab004" label="单位名称" maxlength="100"    disable="true" />
      <lemis:texteditor property="aab019" label="单位类型"    disable="true"  />
</tr>
  <tr>
      
      <lemis:texteditor property="aab020" label="经济类型"     disable="true"  />
       <lemis:texteditor property="aab022" label="行业类型"     disable="true"  />
       <lemis:texteditor property="aab013" label="法人代表"     disable="true"  />
   </tr> 
     <tr>
      <lemis:texteditor property="aab015" label="法人代表电话"     disable="true"  />
       <lemis:texteditor property="aae004" label="联系人"     disable="true"  />
       <lemis:texteditor property="aae005" label="联系电话"     disable="true"  />
   </tr> 
        <tr>
      <lemis:texteditor property="aaa021" label="单位所在街区"     disable="true"  />
       <lemis:texteditor property="aae006" label="地址"     disable="true"  />
       <lemis:texteditor property="aae007" label="邮编"     disable="true"  />
   </tr> 
           <tr>
      <lemis:texteditor property="aae013" label="备注"     disable="true"  colspan ="3" />

   </tr> 
	  </table>
  </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>

  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //上一步
  function back(url){
    location.href=url;
  }


</script>

