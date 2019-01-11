<!-- recommendation/personapply/QueryPersonApply.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<script language="javascript" >
//判断是否为回车键，它触发查询按钮
function checkKey()
{
  if(13 == window.event.keyCode){
   document.forms[0].sumbit();
  }
}
document.onkeydown=checkKey;
</script>

<html>
<lemis:base/>
<lemis:body>
<%
 
	String menuId = (String)request.getSession().getAttribute("menuId");
  //定义查询条件

  List editors = new ArrayList();
  editors.add(new Editor("card","aac002","公民身份号码"));
  editors.add(new Editor("text","aac003","姓名"));
  editors.add(new Editor("text","acc025","劳动手册号"));
  //editors.add(new Editor("text","aac004","性别"));
  
  
  //editors.add(new Editor("text","aac011","文化程度"));
  //editors.add(new Editor("text","aac009","户口性质"));
  //editors.add(new Editor("text","aac014","专业技术职务"));




  pageContext.setAttribute("editor",editors);

  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();

 hidden.put("aac001","个人编号");
 hidden.put("aac002","公民身份号码");
 hidden.put("aac003","姓名");
  hidden.put("aac004","性别");
 //添加一个隐藏的域
 hidden.put("acc200","求职编号");

  List header = new ArrayList();
 header.add(new Formatter("ac01.aac003","姓名"));
 header.add(new Formatter("ac01.aac004","性别"));
 header.add(new Formatter("ac01.aac002","公民身份号码"));
 header.add(new Formatter("ac01.aac011","文化程度"));
 header.add(new Formatter("ac01.aac009","户口性质"));
 header.add(new Formatter("ac01.aac014","专业技术职务"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

    //定义按钮/searchPersonApplyAction.do?method=searchPersonApply
  List buttons = new ArrayList();
	  buttons.add(new Button("Btn_view","查看个人详细","rec020101","view(document.all.tableform)"));
	  buttons.add(new Button("Btn_qzyx","求职意向","rec020102","qzyx(document.all.tableform)"));
	  buttons.add(new Button("Btn_edit","修改个人求职信息","rec020103","edit(document.all.tableform)"));
	  buttons.add(new Button("Btn_resume","个人简历", "rec020104","resume(document.all.tableform)"));
	  buttons.add(new Button("Btn_resume","删除求职信息", "rec020108","del(document.all.tableform)"));
  buttons.add(new Button("Btn_close","关闭","rec999999","closeWindow(\"Rec02Form\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","个人求职基本信息表");
%>

  <lemis:title title="查询个人求职信息"/>

  <lemis:query action="/Rec02Action.do?method=searchPersonApply" editorMeta="editor" topic="查询条件"/>

  <lemis:table topic="个人求职基本信息" action="/Rec02Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>

  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>

<script language="javascript" >
  //求职登记查看
  function view(obj)
  {
  var t = editObj("chk");
    if (!t)
    {return t;
      }
      obj.action='<html:rewrite page="/Rec0201Action.do?method=viewPersonApply&"/>'+getAlldata(obj);
      obj.action=obj.action+"viewPA="+location.href.replace(/\&/g,";viewPA;");
      obj.submit();
    }
	
	//求职意向
	function qzyx(obj){
		var t = editObj("chk");
    	if(!t){
     	 return t;
    	}
   		obj.action= '/lemis/recommendation/Rec0201Action.do?method=modPer&'+getAlldata(obj);
   		obj.action=obj.action+"modPA="+location.href.replace(/\&/g,";modPA;");
   		obj.submit();
	}

  //求职登记修改
   function edit(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    var alldata = getAlldata(obj);    
   obj.action= '<html:rewrite page="/Rec0201Action.do?method=modPersonApply&"/>'+alldata;
   obj.action=obj.action+"modPA="+location.href.replace(/\&/g,";modPA;");
   obj.submit();
  }
  
  //求职登记删除
   function del(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    var alldata = getAlldata(obj);    
   obj.action= '<html:rewrite page="/Rec0201Action.do?method=delPersonApply&"/>'+alldata;
   obj.submit();
  }
  function resume(obj){
  		var t = editObj("chk");
    	if(!t){
     	 return t;
    	}
		var para = getAlldata(obj);
		obj.action = '/lemis/basicinfo/queryresumeAction.do?method=findresume&'+para+
		"mod=recommendation";
		obj.submit();
	}
</script>




