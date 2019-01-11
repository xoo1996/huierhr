<!--recommendation\consigninvite\PersonRecommend.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="选择求职信息"/>
<% 

String menuId = (String)request.getSession().getAttribute("menuId");

//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("card","aac002","公民身份号码"));
  editors.add(new Editor("text","aac003","姓名"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("aac002","公民身份号码"));
  header.add(new Formatter("aac003","姓名"));
  header.add(new Formatter("aac004","性别"));
  header.add(new Formatter("aac006","出生日期" ,TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  
  header.add(new Formatter("aca112","求职工种"));
  header.add(new Formatter("aac011","文化程度"));
  header.add(new Formatter("aac009","户口性质"));
  header.add(new Formatter("aae036","登记日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aac014","专业技术职务"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acc200","求职编号");
  hidden.put("aae043","登记日期");
  hidden.put("acc210","择业工种编号");
  hidden.put("aac001","个人编号");
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
	buttons.add(new Button("Btn_next","下一步","rec010301","match(document.all.tableform,\""+menuId+"\")"));
    buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"searchPersonFRForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","个人求职业信息"); 
%>
<lemis:query action="/Rec0103Action.do?method=SearchPersonFR" editorMeta="editor" topic="求职者查询"/>
<lemis:table topic="查询结果" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/><lemis:clean names="Rec0402Form" />
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function match(obj,menuId) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=PersonRecommendFR&"/>'+getAlldata(obj);
    obj.action=obj.action+'menuId='+menuId+"&SearchPersonFRURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>


