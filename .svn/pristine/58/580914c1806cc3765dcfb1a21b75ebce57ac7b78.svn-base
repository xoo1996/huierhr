<!-- recommendation/personguidance/QueryPerson.jsp -->
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
  pageContext.setAttribute("editor",editors);

  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();

 hidden.put("aac001","个人编号");
 hidden.put("aac002","公民身份号码");
 hidden.put("aac003","姓名");
  hidden.put("aac004","性别");

  List header = new ArrayList();
 header.add(new Formatter("ac01.aac003","姓名"));
 header.add(new Formatter("ac01.aac004","性别"));
 header.add(new Formatter("ac01.aac002","公民身份号码"));
 header.add(new Formatter("ac01.aac011","文化程度"));
 header.add(new Formatter("ac01.aac009","户口性质"));
 header.add(new Formatter("ac01.aac014","专业技术职务"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);
  List buttons = new ArrayList();
	  buttons.add(new Button("Btn_view","查看","rec050101","view(document.all.tableform)"));
	  buttons.add(new Button("Btn_qzyx","指导新增","rec050102","add(document.all.tableform)"));
	  buttons.add(new Button("Btn_edit","指导历史","rec050103","his(document.all.tableform)"));
  buttons.add(new Button("Btn_close","关闭","rec999999","closeWindow(\"Rec05Form\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","个人求职基本信息表");
%>

  <lemis:title title="查询个人求职信息"/>

  <lemis:query action="/Rec05Action.do?method=searchPerson" editorMeta="editor" topic="查询条件"/>

  <lemis:table topic="个人求职基本信息" action="/Rec05Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>

  <lemis:errors/>
<lemis:bottom/></lemis:body><lemis:clean/>
</html>

<script language="javascript" >

  function view(obj)
  {
  var t = editObj("chk");
    if (!t)
    {return t;
      }
      obj.action='<html:rewrite page="/Rec0201Action.do?method=viewPersonApply&"/>'+getAlldata(obj);
      obj.submit();
    }

	function add(obj){
		var t = editObj("chk");
    	if(!t){
     	 return t;
    	}
   		obj.action= '/lemis/recommendation/Rec05Action.do?method=initadd&'+getAlldata(obj);
   		obj.submit();
	}

 
   function his(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    var alldata = getAlldata(obj);    
   obj.action= '<html:rewrite page="/Rec05Action.do?method=queryhis&"/>'+alldata;
   obj.submit();
  }
  
  
</script>




