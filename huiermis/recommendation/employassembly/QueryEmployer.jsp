<!--/lemis/recommendation/employassembly/QueryEmployer.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<%
String menuId = (String)request.getSession().getAttribute("menuId");
String url = "";
   //定义查询条件
  Editor editor;
  List editors = new ArrayList();
            editors.add(new Editor("text","aab007","营业执照号"));
			editors.add(new Editor("text","aab003","组织机构代码"));
			editors.add(new Editor("text","aab002","社会保险登记证编码"));
			editors.add(new Editor("text","aab004","单位名称"));
			editors.add(new Editor("text","aab043","单位拼音码"));
  pageContext.setAttribute("editor",editors);


  //定义查询显示字段内容
  Map hidden = new LinkedHashMap();
  hidden.put("aab004","单位名称");
  hidden.put("aab003","组织机构代码");
  hidden.put("aab001","单位编号");
 


  List header = new ArrayList();
  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("aab003","组织机构代码"));
 
   header.add(new Formatter("aae006","单位地点"));

  header.add(new Formatter("aae004","联系人"));
  header.add(new Formatter("aae005","联系电话"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

    //定义按钮
  List buttons = new ArrayList();
 
  buttons.add(new Button("Btn_next","招聘登记","rec070101","next(document.all.tableform)"));
  
  buttons.add(new Button("Btn_close", "关 闭","rec999999","closeWindow(\"addEr\")"));
  
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","单位基本信息表"); 
%>

  <lemis:title title="查询单位基本信息"/>

  <lemis:query action="/Rec07Action.do?method=queryEmployer"  editorMeta="editor" topic="查询条件"/>
  
  <lemis:table topic="单位基本信息" action="/Rec07Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>
  
  <lemis:errors/><lemis:clean names="Rec0701Form"/><lemis:clean names="Rec0702Form"/>
<lemis:bottom/></lemis:body>
</html>

<script language="javascript" >

 
  //下一步

function next (obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0701Action.do?method=initaddEmployInvite&"/>'+getAlldata(obj);
    obj.action=obj.action+"addE="+(location.href+"&"+getAlldata(obj)).replace(/\&/g,";addE;");
    obj.submit();
  }
  
  //上一步
  function back(url){
  
    location.href=url;
  }

</script>




