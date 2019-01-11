<!-- recommendation/personguidance/QueryHis.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<script language="javascript" >
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




  pageContext.setAttribute("editor",editors);

  //定义查询显示字段内容
   Map hidden = new LinkedHashMap();

 hidden.put("aac001","个人编号");
 hidden.put("acc230","职业指导编号");
 hidden.put("aac002","公民身份号码");
 hidden.put("aac003","姓名");
 hidden.put("aac004","性别");

  List header = new ArrayList();
 header.add(new Formatter("cc23.acc230","职业指导编号"));
 header.add(new Formatter("ac01.aac002","公民身份号码"));
 header.add(new Formatter("ac01.aac003","姓名"));
 header.add(new Formatter("ac01.aac004","性别")); 
 header.add(new Formatter("cc23.acc231","指导日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
 header.add(new Formatter("cc23.acc233","指导人员"));
 header.add(new Formatter("cc23.aae011","经办人"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);
  List buttons = new ArrayList();
	  buttons.add(new Button("Btn_view","查看详细","rec050104","view(document.all.tableform)"));
	  buttons.add(new Button("Btn_add","指导登记","rec050105","add(document.all.tableform)"));
	  buttons.add(new Button("Btn_his","指导修改","rec050106","edit(document.all.tableform)"));
	  buttons.add(new Button("Btn_back","返回","rec999997","go2Page(\"recommendation\",\"1\")"));
  
  buttons.add(new Button("Btn_close","关闭","rec999999","closeWindow(\"Rec02Form\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","个人求职指导信息表");
%>

  <lemis:title title="个人求职指导信息表"/>

  <lemis:query action="/Rec05Action.do?method=queryhis" editorMeta="editor" topic="查询条件"/>

  <lemis:table topic="个人求职指导信息表" action="/Rec05Action.do" headerMeta="header" hiddenMeta="hidden" 
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
      obj.action='<html:rewrite page="/Rec05Action.do?method=viewguidance&"/>'+getAlldata(obj);
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

  
   function edit(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    var alldata = getAlldata(obj);    
   obj.action= '<html:rewrite page="/Rec05Action.do?method=initedit&"/>'+alldata;
   obj.submit();
  }
</script>




