<!-- recommendation\query\queryRecommend.jsp -->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="用人单位招工情况"/>
<% 
String menuId=request.getParameter("menuId");
if(menuId==null){
	menuId=request.getSession().getAttribute("menuId").toString();
}
//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("text","bac299","人员类别"));//人员 求职类别 
  editors.add(new Editor("text","aac011","文化程度"));
  editors.add(new Editor("text","acb203","招聘地区"));
  editors.add(new Editor("text","aca111","招聘工种"));
  editors.add(new Editor("text","aac048","用工形式"));
  editors.add(new Editor("date","aae030","招聘开始日期从"));
  editors.add(new Editor("date","a030ae","到"));
  editors.add(new Editor("date","aae031","招聘结束日期从"));
  editors.add(new Editor("date","a031ae","到"));
  editors.add(new Editor("text","aac004","性别"));
  editors.add(new Editor("text","aab004","单位关键字"));
  editors.add(new Editor("text","acc223","求职状态"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("acc220","职介号"));
  header.add(new Formatter("aac002","身份证号码"));
  header.add(new Formatter("aac003","姓名"));
  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("acc223","求职状态"));
  header.add(new Formatter("ace014","推荐截至日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aca112","应聘岗位"));
  header.add(new Formatter("aae005","求职者电话"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("aab001","单位编号");
  hidden.put("acb210","空位编号");
  hidden.put("acc200","求职编号");
  hidden.put("acb200","招聘编号");
  hidden.put("aac001","人员编号");
//将表头放入context中以供标签库获取
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
  	 buttons.add(new Button("Btn_match","查看单位详细","rec030301","match(document.all.tableform)"));
  	 buttons.add(new Button("Btn_match1","查看个人详细","rec030302","match1(document.all.tableform)"));
 	 buttons.add(new Button("Btn_match2","用人详细信息","rec030303","match2(document.all.tableform)"));
 	 buttons.add(new Button("Btn_close","关 闭","E999999","closeWindow(\"queryEmployerForm\")"));
 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","用人单位招工信息");
%>

<lemis:query action="/Rec03Action.do?method=queryRecommend1" editorMeta="editor" topic="查询条件"/>
<lemis:table topic="查询结果" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function match(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    //obj.action= '<html:rewrite page="/ViewAction.do?method=viewEmployer&"/>'+getAlldata(obj);
    // obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
    //obj.submit();
    window.location.href="/lemis/basicinfo/employerAction.do?method=viewEmployer&mod=recommendation&"+getAlldata(obj);;
  }


  //k1是做为返回的依据
   function match1(obj) {
  var t = editObj("chk");
    if (!t)
    {
		return t;
      }
      obj.action='<html:rewrite page="/Rec0201Action.do?method=viewPersonApply&"/>'+getAlldata(obj);
	    obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
      obj.submit();
  }
   function match2(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }
</script>
