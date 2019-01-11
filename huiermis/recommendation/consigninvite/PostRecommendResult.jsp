<!--recommendation\consigninvite\PostRecommendResult.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
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
  //URL
  String url = "";
  if (request.getParameter("SearchPostFRURL")!=null){
     url = request.getParameter("SearchPostFRURL");
     request.getSession().setAttribute("SearchPostFRURL",url);
     }
     
  String menuId = (String)request.getSession().getAttribute("menuId");

  //定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("card","aac002","公民身份号码"));
  editors.add(new Editor("text","aac003","姓名"));
  editors.add(new Editor("text","aca111","工种"));
  editors.add(new Editor("text","acc034","工资待遇从"));
  editors.add(new Editor("text","a034cc","到"));
  editors.add(new Editor("text","aac017","婚姻状况"));
  editors.add(new Editor("nn","nnn001","年龄从"));
  editors.add(new Editor("nn","nnn002","到"));
  editors.add(new Editor("text","aac011","文化程度"));
  editors.add(new Editor("text","aac009","户口性质"));
  editors.add(new Editor("text","aac048","用工形式"));
  editors.add(new Editor("text","aac014","专业技术职务"));
  editors.add(new Editor("test","bac298","人员类别"));
  editors.add(new Editor("text","aac004","性别"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("aac002","公民身份号码"));
  header.add(new Formatter("aac003","姓名"));
  header.add(new Formatter("aac004","性别"));
  header.add(new Formatter("aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aac011","文化程度"));
  header.add(new Formatter("bac298","人员类别"));
  header.add(new Formatter("aac009","户口性质"));
  header.add(new Formatter("aca112","工种说明"));
  header.add(new Formatter("acc034","工资待遇"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acc200","求职编号");
  hidden.put("acc210","求职意向编号");
  hidden.put("aac001","个人编号");
//将表头放入context中以供标签库获取
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
  List data = (List)request.getAttribute(GlobalNames.QUERY_DATA);
	if(data!=null&&data.size()>0){
		  buttons.add(new Button("Btn_pp","匹配情况","rec010405","ppqk(document.all.tableform)"));
		  buttons.add(new Button("Btn_view","详 细","rec010402","view(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","推 荐","rec010403","recommend(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","单位选人","rec010404","dwxr(document.all.tableform)"));
		
	}
  buttons.add(new Button("Btn_prev","返回","rec999997","go2Page(\"recommendation\",\"1\")"));

  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"postRecommendSPersonForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","个人求职业信息"); 
%>
<lemis:query action="/Rec0103Action.do?method=PostRecommendSPerson" editorMeta="editor" topic="详细匹配条件"/>
<lemis:table topic="查询结果" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="checkbox" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>

<script language="javascript">
function view(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"PostRecommendViewURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }
function ppqk(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=viewppqk&"/>'+getAlldata(obj);
    obj.submit();
  }
 function recommend(obj) {
	var truthBeTold = window.confirm("是否确定推荐？");
	if (truthBeTold) {
	var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=Recommend&"/>'+getAlldata(obj);
    obj.action=obj.action+"RecommendURL="+location.href.replace(/\&/g,";amp;");
    
    obj.submit();
	//window.open ("consigninvite/print.jsp", "newwindow", "height=700, width=520, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}

  }
function dwxr(obj) {
	var truthBeTold = window.confirm("是否确定选择？");
	if (truthBeTold) {
	var t = delObj("chk");
    if(!t){
      return t;
    }
    //alert(getAlldata(obj));
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=dwxr&"/>'+getAlldata(obj);
    location.href=obj.action;
    //obj.submit();
    }

  }

</script>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>

