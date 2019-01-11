<!--recommendation\consigninvite\PostRecommend.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
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

//定义查询条件输入区 
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性
            editors.add(new Editor("text","aab007","营业执照号"));
			editors.add(new Editor("text","aab003","组织机构代码"));
			editors.add(new Editor("text","aab002","社会保险登记证编码"));
			editors.add(new Editor("text","aab004","单位名称"));
			editors.add(new Editor("text","acb206","发布名称"));
			editors.add(new Editor("text","aac048","用工形式"));  
            editors.add(new Editor("text","aca111","专业工种"));
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
	buttons.add(new Button("Btn_next","下一步","rec010401","recommend(document.all.tableform)"));
    buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"searchPostFRForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","单位招聘空位信息"); 
%>

<lemis:query action="/Rec0103Action.do?method=SearchPostFR" editorMeta="editor" topic="查询条件"/>
<lemis:table topic="查询结果" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/><lemis:clean names="Rec0402Form" />
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function recommend(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=PostRecommendFR&"/>'+getAlldata(obj);
    obj.action=obj.action+"SearchPostFRURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>

