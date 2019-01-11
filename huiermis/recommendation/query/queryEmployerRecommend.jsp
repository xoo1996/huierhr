<!-- recommendation\query\queryEmployerRecommend.jsp -->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.apps.recommendation.query.form.Rec03Form" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="单位用人信息"/>
<% 
Rec03Form fm = (Rec03Form) session.getAttribute("Rec03Form");
//定义查询条件输入区
  Editor editor;
  List editors=new ArrayList();//定义编辑框属性

  //editors.add(new Editor("text","aab004","单位名称"));
  
  editors.add(new Editor("text","aab019","单位性质"));//ab01表
  editors.add(new Editor("text","aab020","经济类型"));//ab01表
  editors.add(new Editor("text","aab054","行业类别"));//ab01表
  editors.add(new Editor("text","aac048","用工形式"));//下面都是cb21表
  editors.add(new Editor("text","aca112","招聘工种"));
  editors.add(new Editor("text","acb216","工种描述"));
  editors.add(new Editor("text","acb217","招聘范围"));
  editors.add(new Editor("text","aab004","单位名称"));//已经有了
  editors.add(new Editor("text","aab001","单位代码"));//已经有了
  //editors.add(new Editor("text","aaa021","单位辖地"));
  editors.add(new Editor("text","aae017","登记机构"));//??
  editors.add(new Editor("date","aae036","登记日期"));
  editors.add(new Editor("text","acb210","招工编号"));
   editors.add(new Editor("text","acb208","有效信息查询"));
   editors.add(new Editor("text","acb221","最低年龄"));
   editors.add(new Editor("text","acb222","最高年龄"));
  pageContext.setAttribute("editor",editors); //放入context中以供标签库获取
//定义查询结果显示区
  List header=new ArrayList();
  //header.add(new Formatter("aab001","单位代码"));
  header.add(new Formatter("aab004","单位名称"));
  //header.add(new Formatter("aae006","单位地址"));
  //header.add(new Formatter("aae004","联系人"));
  //header.add(new Formatter("aae005","联系电话"));
  //header.add(new Formatter("acb210","空位信息编码"));
   //header.add(new Formatter("aca111","招聘岗位"));
   //新的列表，凸现岗位信息
 header.add(new Formatter("acb210","招工编号"));
   header.add(new Formatter("acb216","招聘工种说明"));
   header.add(new Formatter("acb21d","人数"));
   header.add(new Formatter("acb221","最低年龄"));
   header.add(new Formatter("acb222","最高年龄"));
   header.add(new Formatter("aac011","学历"));
   header.add(new Formatter("acb21h","工资待遇"));//
   //header.add(new Formatter("aae013","备注"));
  pageContext.setAttribute("header",header);
//定义隐藏数据
  Map hidden=new LinkedHashMap();

    hidden.put("aab001","单位编号");
  hidden.put("acb210","空位编号");
   hidden.put("acb200","招聘编号");
  pageContext.setAttribute("hidden",hidden);
//定义按钮
  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_view","查 看","rec030401","match(document.all.tableform)"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"QueryRecEmpForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","单位推荐情况汇总表");
%>
<lemis:query action="/Rec03Action.do?method=queryEmployerRecommend" editorMeta="editor" topic="输入单位的编号"/>
<lemis:table topic="查询单位基本信息和用人的信息" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
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
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>
