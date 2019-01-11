<!--lemis/recommendation/employassembly/enterqueryqs.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<html>
<lemis:base/>
<lemis:body>
<%
  //定义查询条件输入区
  List editors = new ArrayList();
  editors.add(new Editor("text", "cce001", "所属机构"));
  editors.add(new Editor("period", "acb231", "期数"));
  editors.add(new Editor("text", "aae100", "是否有效"));
  
  //定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("cce001", "招聘机构"));
  header.add(new Formatter("acb231", "期数"));
  header.add(new Formatter("acb232", "招聘日期"));
  header.add(new Formatter("acb233", "招聘地点"));
  header.add(new Formatter("aae011", "经办人"));
  header.add(new Formatter("aae019", "经办科室"));
  header.add(new Formatter("aae036", "经办时间"));
  header.add(new Formatter("aae100", "是否有效"));
  pageContext.setAttribute("header",header);
  
  //定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb230","内码");
  hidden.put("aae100","是否有效");
  hidden.put("cce001","招聘机构");
  pageContext.setAttribute("hidden",hidden);
  
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
   buttons.add(new Button("search","查看详细信息","rec070307","search(document.all.tableform)"));
  buttons.add(new Button("add","增 加","rec070301","add(document.all.tableform)"));
  buttons.add(new Button("edit","修 改","rec070303","modify(document.all.tableform)"));
  buttons.add(new Button("writeoff","注 销","rec070305","writeoff(document.all.tableform)"));
  buttons.add(new Button("writeon","启 用","rec070305","writeoff(document.all.tableform)"));
  buttons.add(new Button("del","删 除","rec070306","del(document.all.tableform)"));
 
  buttons.add(new Button("close","关 闭","rec999999","closeWindow(\"Ree0703Form\")"));
  //放入context中以供标签库获取
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","大会招聘期数信息表");

%>
<lemis:title title="大会招聘期数维护"/>
<lemis:query action="/Rec0703Action.do?method=enterquery"  editorMeta="editor" topic="查询条件"/>
<lemis:table topic="招聘期数列表" action="/Rec0703Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//增加大会招聘期数信息
function add(obj,obj2) {
    //if(document.all.cce001.value==''){
    //  alert('请选择所属机构');
    //  return false;
   // }
    //if(document.all.acb231.value==''){
    //  alert('请选择期数');
    //  return false;
    //}
    obj.action= '<html:rewrite page="/Rec0703Action.do?method=initAddqs&"/>'+getAlldata(obj);
    obj.submit();
  }
  
  //修改大会招聘期数信息
  function modify(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0703Action.do?method=initmodifyqs&"/>'+getAlldata(obj);
    obj.submit();
  }
  
  //注销/启用大会招聘期数信息
  function writeoff(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0703Action.do?method=writeoffqs&"/>'+getAlldata(obj);
    obj.submit();
  }

//删除大会招聘期数信息
 function del(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    if(confirm("此操作不能回退，确信要删除您选中的吗？")){
    obj.action= '<html:rewrite page="/Rec0703Action.do?method=delqs&"/>'+getAlldata(obj);
    obj.submit();
    }else{
    return false;
    }
  }
  
  //查看大会招聘期数信息
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0703Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
</script>


