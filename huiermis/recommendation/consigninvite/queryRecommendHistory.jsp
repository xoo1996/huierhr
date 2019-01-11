<!--recommendation\consigninvite\queryRecommendHistory.jsp-->
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
<lemis:title title="历史推荐和反馈的查询"/>
<% 
String menuId = (String)request.getSession().getAttribute("menuId");

//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("card","aac002","公民身份号码"));
  editors.add(new Editor("text","aac003","姓名"));
  editors.add(new Editor("text","aab004","单位名称"));
  editors.add(new Editor("test","aca111","专业工种"));
  editors.add(new Editor("date","ace014","推荐有效时间从"));
  editors.add(new Editor("date","a014ce","到"));
  editors.add(new Editor("test","acc223","反馈状态"));//可以自动转换！
  editors.add(new Editor("text","acb210","岗位编码"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("aac002","公民身份号码"));
  header.add(new Formatter("aac003","姓名"));
  header.add(new Formatter("acb210","岗位编码"));
  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("acb216","工种说明"));
  header.add(new Formatter("ace014","推荐有效时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aae011","推荐操作员"));
  header.add(new Formatter("acc226","反馈操作员"));
  header.add(new Formatter("acc223","反馈状态"));
  header.add(new Formatter("acc22e","失败原因"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acc220","推荐编号");
//将表头放入context中以供标签库获取
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
	  buttons.add(new Button("Btn_view","查 看","rec010601","match(document.all.tableform)"));
	  buttons.add(new Button("Btn_hftj","恢 复","rec010602","hftj(document.all.tableform)"));
      buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"Rec0105Form\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","推荐信息"); 
%>
<lemis:query action="/Rec0105Action.do?method=recommendHistory" editorMeta="editor" topic="查询条件"/>
<lemis:table topic="查询结果" action="/Rec0105Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio"  />
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
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=viewrecommendHistory&"/>'+getAlldata(obj);
    obj.action=obj.action+"PSPostURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }
function hftj(obj,menuId) {
 var t = editObj("chk");
  if(!t){
    return t;
  }
	 var truthBeTold = window.confirm("是否确定需要恢复到推荐中？");
	 if (truthBeTold) {
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBhftj&"/>'+getAlldata(obj)+ '&menuId='+menuId;
    obj.submit();
  }
else  window.alert("请继续您的下面操作！"); 
  }

</script>


