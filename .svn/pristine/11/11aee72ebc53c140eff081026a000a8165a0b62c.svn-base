<!--recommendation\consigninvite\viewRecommendHistory.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.recommendation.consigninvite.form.Rec0105Form"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<%
//获取招聘信息

Rec0105Form fm = (Rec0105Form)session.getAttribute("Rec0105Form");
  String       ace014 = "" ;     // 推荐有效时间*
  String       acc229 = "" ;     // 反馈有效期*
  String       aac002 = "" ;     // 公民身份号码*
  String       aac003 = "" ;     // 姓名*
  String       aab004 = "" ;     // 单位名称*
  String       aca111 = "" ;     // 专业工种*
  String       aae011 = "" ;     // 推荐操作员*
  String       acc226 = "" ;     // 反馈操作员*
  String       acc223 = "" ;     // 反馈状态*
  String       acc22e = "" ;     // 失败原因*
  if (fm.getAce014()!=null) ace014 = fm.getAce014();
  if (fm.getAcc229()!=null) acc229 = fm.getAcc229();
  if (fm.getAac002()!=null) aac002 = fm.getAac002();
  if (fm.getAac003()!=null) aac003 = fm.getAac003();
  if (fm.getAab004()!=null) aab004 = fm.getAab004();
  if (fm.getAca111()!=null) aca111 = fm.getAca111();
  if (fm.getAae011()!=null) aae011 = fm.getAae011();
  if (fm.getAcc226()!=null) acc226 = fm.getAcc226();
  if (fm.getAcc223()!=null) acc223 = fm.getAcc223();
  if (fm.getAcc22e()!=null) acc22e = fm.getAcc22e();

//定义按钮
  List buttons=new ArrayList();
  buttons.add(new Button("Btn_view","返 回","rec999997","toback()"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"Rec0105Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>

<lemis:body>
<lemis:title title="查看历史反馈信息"/>
<lemis:tabletitle title="历史反馈信息"/>
<table class="tableInput">
      <lemis:editorlayout/>
      <tr>
        <lemis:texteditor property="aac002" label="公民身份号码" value="<%=aac002%>"  colspan="3" />
        <lemis:texteditor property="aac003" label="姓名" value="<%=aac003%>"   />
      </tr>
      <tr>
        
        <lemis:formateditor mask="date" property="ace014" label="推荐有效时间" value="<%=ace014%>" required="false" disable="true" format="true"/>
        <lemis:texteditor property="acc229" label="反馈有效期" value="<%=acc229%>"/>
        <lemis:texteditor property="aab004" label="单位名称"  value="<%=aab004%>"  />
      </tr>
      <tr>
        <lemis:texteditor property="aca111" label="专业工种" value="<%=aca111%>"  />
        <lemis:codelisteditor type="aae011" label="推荐操作员" key="<%=aae011%>" isSelect="false" />
        <lemis:texteditor property="acc226" label="反馈操作员" value="<%=acc226%>"  />
      </tr>
      <tr>
        
        <lemis:codelisteditor type="acc223" label="反馈状态" key="<%=acc223%>"  isSelect="false" />
         <lemis:texteditor property="acc22e" label="失败原因" value="<%=acc22e%>" colspan="3"  />
      </tr>
     
       
</table>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
function toback(){
window.history.back();
}
</script>

