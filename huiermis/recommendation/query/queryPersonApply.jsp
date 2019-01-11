<!--recommendation\query\queryPersonApply.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<%
 
    String menuId=request.getParameter("menuId");
    if(menuId==null){
		menuId=request.getSession().getAttribute("menuId").toString();
    }
	request.getSession().setAttribute("menuId",menuId);
  //定义查询条件

  List editors = new ArrayList();
  editors.add(new Editor("card","aac002","身份证"));
  editors.add(new Editor("text","aac003","姓名"));
  editors.add(new Editor("text","acc200","求职编号"));
  editors.add(new Editor("text","bac299","人员类别"));//人员 求职类别 
  editors.add(new Editor("text","aac011","文化程度"));
  editors.add(new Editor("text","aac010","户籍所属区"));
  editors.add(new Editor("text","aca111","岗位工种"));
  editors.add(new Editor("text","aac004","性别"));
  editors.add(new Editor("text","acb208","求职状态"));
  editors.add(new Editor("nn","nnn001","年龄从"));
  editors.add(new Editor("nn","nnn002","到"));
  pageContext.setAttribute("editor",editors);
  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();
 //添加一个隐藏的域
 hidden.put("acc200","求职编号");
 hidden.put("aac001","人员编号");
  List header = new ArrayList();
   header.add(new Formatter("acc200","求职编号"));
 header.add(new Formatter("aac003","姓名"));
 header.add(new Formatter("aac004","性别"));
 header.add(new Formatter("aac002","公民身份号码"));
 header.add(new Formatter("aae005","联系电话"));
 header.add(new Formatter("aac011","文化程度"));
 header.add(new Formatter("aca112","求职工种"));
 header.add(new Formatter("aac010","户籍所属区"));
 header.add(new Formatter("bac299","人员类别"));
 header.add(new Formatter("acb208","求职状态"));
 header.add(new Formatter("aae013","备注"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);


    //定义按钮
  List buttons = new ArrayList();
  	buttons.add(new Button("Btn_view","查 看","rec030201","view(document.all.tableform)"));
  	buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"queryPersonApplyForm\")"));
  
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","求职登记信息表");
%>
  <lemis:title title="查询个人求职信息"/>
  <lemis:query action="/Rec03Action.do?method=queryPersonApply"  editorMeta="editor" topic="查询条件"/>
  <lemis:table topic="个人求职基本信息" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>

  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript" >
  //求职登记查看
  function view(obj)
  {
  var t = editObj("chk");
    if (!t)
    {
		return t;
      }
      obj.action='<html:rewrite page="/Rec0201Action.do?method=viewPersonApply&"/>'+getAlldata(obj);
     obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
      obj.submit();
    }
</script>




