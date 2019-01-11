<!--/lemis/recommendation/employassembly/enterqueryDhzp.jsp-->
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
	//editors.add(new Editor("text","aab002","社会保险登记证编码"));
	editors.add(new Editor("text","aab004","单位名称"));
	//editors.add(new Editor("text","aab043","单位拼音码"));
  	//editors.add(new Editor("text","acb200","招工编号"));
	editors.add(new Editor("text","acb231","大会招聘期数"));

  pageContext.setAttribute("editor",editors);

  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();

  hidden.put("acb200","招聘编号");
  hidden.put("acb210","岗位编号");
  hidden.put("aab001","单位编号");

  List header = new ArrayList();
 header.add(new Formatter("ab01.aab004","企业名称"));
 header.add(new Formatter("cb21.aca112","岗位"));
 header.add(new Formatter("cb21.acb21d","人数"));
 header.add(new Formatter("cb21.acb221","最低年龄"));
 header.add(new Formatter("cb21.acb222","最高年龄"));
 header.add(new Formatter("cb21.aac011","学历要求"));
 header.add(new Formatter("cb21.acb21h","待遇(元/月)"));
 header.add(new Formatter("cb20.aae004","联系人"));
 header.add(new Formatter("cb20.aae005","电话/传真"));
 header.add(new Formatter("ab01.aae006","地址"));
 header.add(new Formatter("cb21.aae013","备注"));
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

    //定义按钮
  List buttons = new ArrayList();
  	buttons.add(new Button("Btn_view", "查 看", "rec070201", "view(document.all.tableform)"));
    buttons.add(new Button("Btn_close", "关 闭", "rec999999", "closeWindow(\"queryEmployInviteForm\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","单位招聘信息表"); 
%>

  <lemis:title title="大会单位招聘信息"/>

  <lemis:query action="/Rec0702Action.do?method=viewEmployDhzp"  editorMeta="editor" topic="查询条件"/>

  <lemis:table topic="大会招聘信息" action="/Rec0702Action.do" headerMeta="header" hiddenMeta="hidden" 
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
      obj.action='<html:rewrite page="/Rec0702Action.do?method=viewEmployDhzp&"/>'+getAlldata(obj);
      obj.action=obj.action+"viewE="+location.href.replace(/\&/g,";viewE;");
      obj.submit();
    }
</script>




