<!--recommendation\query\queryPrint.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<lemis:base/>
<lemis:body>
<lemis:title title="张贴打印"/>
<% 
String menuId = (String)request.getSession().getAttribute("menuId");
//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("text","aab004","单位名称"));
  editors.add(new Editor("text","aab003","组织机构代码"));
  editors.add(new Editor("text","acb200","招工编号"));
  editors.add(new Editor("date","aae030","招聘开始日期：从"));
  editors.add(new Editor("date","a030ae","至"));
  editors.add(new Editor("text","acb216","工种描述"));
  editors.add(new Editor("date","aae043","登记日期从"));
  editors.add(new Editor("date","a043ae","到"));
  editors.add(new Editor("text","aac048","用工形式"));
  
  editors.add(new Editor("text","aae017","登记机构"));
  
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("aab003","组织机构代码"));
  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("acb200","招工编号"));
  header.add(new Formatter("acb210","岗位编号"));
  header.add(new Formatter("acb21h","工资"));
  header.add(new Formatter("acb216","工种描述"));
  header.add(new Formatter("acb21d","人数"));
  header.add(new Formatter("acb221","最低年龄"));
  header.add(new Formatter("acb222","最高年龄"));
  header.add(new Formatter("aac011","文化程度"));
  header.add(new Formatter("aae030","招聘开始日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aae031","招聘截止日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aae043","登记日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));

  header.add(new Formatter("aae004","联系人",true));
  header.add(new Formatter("acb202","联系地址",true));
  header.add(new Formatter("aae005","联系电话",true));
  header.add(new Formatter("cb21.acb21i","工资待遇说明",true));
  
  header.add(new Formatter("cb20.acb204","工作地",true));
  
  
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb210","空岗编号");
  //hidden.put("acb200","招工编号");
//将表头放入context中以供标签库获取
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
  	buttons.add(new Button("Btn_print","张贴打印A4竖","rec030101","printA4s(document.all.tableform)"));
	buttons.add(new Button("Btn_print","张贴打印A4横","rec030102","printA4h(document.all.tableform)"));
	buttons.add(new Button("Btn_print","张贴打印A3竖","rec030104","printA3s(document.all.tableform)"));
	buttons.add(new Button("Btn_print","张贴打印A3横","rec030103","printA3h(document.all.tableform)"));
  	buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"PrintForm\")"));
 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","张贴打印");
%>

<lemis:query action="/Rec03Action.do?method=queryPrint" editorMeta="editor" topic="查询条件"/>
<lemis:table topic="查询结果" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" mode="checkbox"/>
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
   //弹出张贴打印表   by iori
  function printA4s(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A4s','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
  function printA4h(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
 
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A4h','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
  function printA3s(obj){
 // var w=window.screen.width-150;
 // var h=window.screen.height-100;
 
//  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3s','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3s','print','height=450, width=800,  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  }
  function printA3h(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
 
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3h','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
</script>
