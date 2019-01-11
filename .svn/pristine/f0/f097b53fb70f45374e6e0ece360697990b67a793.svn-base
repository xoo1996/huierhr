<!--recommendation\consigninvite\PersonRecommendResult.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="选择招工信息"/>
<% 

String menuId = (String)request.getSession().getAttribute("menuId");

//URL

//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
  editors.add(new Editor("text","aab004","单位名称"));
  editors.add(new Editor("text","aac048","用工形式"));
  editors.add(new Editor("text","aac011","文化程度"));  
  editors.add(new Editor("test","aca111","专业工种")); 
  editors.add(new Editor("text","aac009","户口性质"));
  editors.add(new Editor("text","aac014","专业技术职务"));
  editors.add(new Editor("text","acb21h","工资待遇从"));
  editors.add(new Editor("text","a21hcb","到"));
  editors.add(new Editor("text","acb210","岗位编号"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("acb210","岗位编号"));
  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("acb216","工种说明"));
  header.add(new Formatter("acb21d","招收人数"));
  header.add(new Formatter("acb21a","成功人数"));
  header.add(new Formatter("acb218","已推荐人数"));
  header.add(new Formatter("acb211","可推荐人数"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb200","招聘编号");
  hidden.put("acb210","空位编号");
  hidden.put("aab001","单位编号");
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
  List data = (List)request.getAttribute(GlobalNames.QUERY_DATA);
	if(data!=null&&data.size()>0){
		  buttons.add(new Button("Btn_pp","匹配情况","rec010304","ppqk(document.all.tableform)"));
		  buttons.add(new Button("Btn_view","详 细","rec010302","view(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","推 荐","rec010303","recommend(document.all.tableform)"));

	}
  buttons.add(new Button("Btn_prev","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"personRecommendSPostForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","单位招聘空位信息"); 
%>
<lemis:query action="/Rec0103Action.do?method=PersonRecommendSPost" editorMeta="editor" topic="详细匹配条件"/>
<lemis:table topic="查询结果" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
function view(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"PersonRecommendViewURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }




//我个人加的对话框
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
else  window.alert("请继续您的下面操作！"); 
  }

  
</script>

