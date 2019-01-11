<!--/lemis/recommendation/employinvite/QueryEmployInvite.jsp-->
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
  //定义查询条件

  List editors = new ArrayList();
    editors.add(new Editor("text","aab007","营业执照号"));
	editors.add(new Editor("text","aab003","组织机构代码"));
	editors.add(new Editor("text","aab002","社会保险登记证编码"));
	editors.add(new Editor("text","aab004","单位名称"));
	editors.add(new Editor("text","aab043","单位拼音码"));
  	editors.add(new Editor("text","acb200","招工编号"));

  pageContext.setAttribute("editor",editors);

  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();

  hidden.put("acb200","招聘编号");
  hidden.put("acb210","岗位编号");
  hidden.put("aab001","单位编号");

  List header = new ArrayList();
 header.add(new Formatter("aab004","单位名称"));
 header.add(new Formatter("aab003","组织机构代码"));
 header.add(new Formatter("acb206","发布名称"));
 header.add(new Formatter("acb200","招工编号"));
 header.add(new Formatter("acb204","工作地点"));
 header.add(new Formatter("acb201","招聘方式"));
 header.add(new Formatter("acb202","报名地点"));
 header.add(new Formatter("aae004","联系人"));
 header.add(new Formatter("aae005","联系电话"));
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

    //定义按钮
  List buttons = new ArrayList();
  	buttons.add(new Button("Btn_view", "查 看", "rec040201", "view(document.all.tableform)"));
  	buttons.add(new Button("Btn_edit", "修改招聘信息", "rec040202", "edit(document.all.tableform)"));
  	buttons.add(new Button("Btn_next", "修改岗位信息", "rec040203", "next(document.all.tableform)"));
    buttons.add(new Button("Btn_close", "关 闭", "rec999999", "closeWindow(\"queryEmployInviteForm\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","单位招聘信息表"); 
%>

  <lemis:title title="查询单位招聘信息"/>

  <lemis:query action="/Rec0402Action.do?method=queryEmployInvite"  editorMeta="editor" topic="查询条件"/>

  <lemis:table topic="单位招聘信息" action="/Rec0402Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //单位招聘查看
  function view(obj)
  {
    var t = editObj("chk");
    if (!t)
    {return t;
      }
      obj.action='<html:rewrite page="/Rec0402Action.do?method=viewEmployInvite&"/>'+getAlldata(obj);
      obj.action=obj.action+"viewE="+location.href.replace(/\&/g,";viewE;");
      obj.submit();
    }


  
  //单位招聘修改
  function edit(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
   obj.action= '<html:rewrite page="/Rec0402Action.do?method=modEmployInvite&"/>'+getAlldata(obj);
   obj.action=obj.action+"modE="+(location.href+"&"+getAlldata(obj)).replace(/\&/g,";modE;");
    obj.submit();
  }
//下一步

function next (obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=toNext&"/>'+getAlldata(obj);
    obj.action=obj.action+"nextP="+location.href.replace(/\&/g,";nextP;");
    obj.submit();
  }
</script>




