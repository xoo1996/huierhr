<!--recommendation\consigninvite\RecommendFeedback.jsp-->
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
<lemis:title title="选择推荐信息"/>
<% 

String menuId = (String)request.getSession().getAttribute("menuId");
String acc223=(String)request.getSession().getAttribute("acc223");

//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性

  editors.add(new Editor("text","acc223","状态","true"));
  editors.add(new Editor("card","aac002","公民身份号码"));
  editors.add(new Editor("text","aac003","姓名"));
  editors.add(new Editor("text","aab004","单位名称"));
  editors.add(new Editor("date","ace014","推荐有效日期从"));
  editors.add(new Editor("date","a014ce","到")); 
  editors.add(new Editor("test","aca111","专业工种"));
  editors.add(new Editor("text","acb200","招工编号"));
  editors.add(new Editor("text","acb210","岗位编码"));
  
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("aac002","公民身份号码"));
  header.add(new Formatter("aac003","姓名"));
  header.add(new Formatter("aac004","性别"));
  header.add(new Formatter("aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("acb210","岗位编码"));
  header.add(new Formatter("acb216","工种说明"));
  header.add(new Formatter("aab004","应聘单位名称"));
  header.add(new Formatter("acb200","招工编号"));
  header.add(new Formatter("aae004","联系人"));
  header.add(new Formatter("aae005","单位联系电话"));
  header.add(new Formatter("acc223","状态"));
  header.add(new Formatter("ace014","推荐有效日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acc220","推荐编号");
  hidden.put("acc200","求职编号");
  hidden.put("acc223","状态");
  hidden.put("aac001","人员编号");
  hidden.put("acb200","招工编号");
  hidden.put("acb210","岗位编号");
  hidden.put("aab001","单位编号");
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons = new ArrayList();//定义按钮属性
  if("0".equals(acc223))
  {
	      buttons.add(new Button("Btn_print","打印推荐","rec010501", "print(document.all.tableform)"));
		  buttons.add(new Button("Btn_success","成 功","rec010502", "success(document.all.tableform,\""+menuId+"\")"));
		  buttons.add(new Button("Btn_lost","失 败","rec010503", "lost(document.all.tableform)"));
		  buttons.add(new Button("Btn_lost","删 除","rec010504", "del(document.all.tableform)"));

  }else if("6".equals(acc223))
  {
      buttons.add(new Button("Btn_dwxr1","联系成功","rec010505", "dwxr1(document.all.tableform)"));
	  buttons.add(new Button("Btn_dwxr3","联系失败","rec010507", "dwxr3(document.all.tableform)"));
	  buttons.add(new Button("Btn_lost","删 除","rec010504", "del(document.all.tableform)"));
  }else if("7".equals(acc223))
  {
	  buttons.add(new Button("Btn_dwxr2","单位选人推荐","rec010506", "dwxr2(document.all.tableform)"));
	  buttons.add(new Button("Btn_lost","删 除","rec010504", "del(document.all.tableform)"));
 }

	  

  

  buttons.add(new Button("Btn_close","关 闭","rec999999", "closeWindow(\"sRecommendFeedbackForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","推荐中信息"); 
%>
<lemis:query action="/Rec0105Action.do?method=SRecommendFeedback" editorMeta="editor" topic="查询条件"/>

<lemis:table topic="查询结果" action="/Rec0105Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 
 function success(obj,menuId) {
 var t = editObj("chk");
  if(!t){
    return t;
  }
	 var truthBeTold = window.confirm("是否确定成功？");
	 if (truthBeTold) {
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBSuccess&"/>'+getAlldata(obj)+ '&menuId='+menuId;
    obj.submit();
  }
else  window.alert("请继续您的下面操作！"); 
  }

  function lost(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("是否确定失败？");
	if(truthBeTold){
    var response = window.prompt("输入你失败的原因！","");
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&"/>'+getAlldata(obj)+ "&respon="+response;
    obj.submit();
    }
	else  window.alert("请继续您的下面操作！"); 
  }
 function del(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("是否确定删除？");
	if(truthBeTold){
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBdel&"/>'+getAlldata(obj);
    obj.submit();
    }
	else  window.alert("请继续您的下面操作！"); 
  }
//打印按钮
    function print(obj) {
	var truthBeTold = window.confirm("是否确定打印？");
if (truthBeTold) {
var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=viewRec&"/>'+getAlldata(obj);
    obj.action=obj.action+"RecommendURL="+location.href.replace(/\&/g,";amp;");
	obj.action=obj.action+"&acc220="+document.all("acc220").value;
    obj.submit();

}
else  window.alert("请继续您的下面操作！"); 
  }
  function dwxr1(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("是否确定联系成功？");
	
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&flag=7&"/>'+getAlldata(obj);
    obj.submit();
   
  }
   function dwxr3(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("是否确定联系失败？");
	
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&flag=8&"/>'+getAlldata(obj);
    obj.submit();
   
  }
  function dwxr2(obj) {
	var truthBeTold = window.confirm("是否确定推荐？");
	if (truthBeTold) {
	var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=Recommend&"/>'+getAlldata(obj);
    
    obj.submit();
	}

  }
 
 document.Rec0105Form.acc223.remove(2);
 document.Rec0105Form.acc223.remove(2);
 document.Rec0105Form.acc223.remove(4);
</script>


