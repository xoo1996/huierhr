<!--lemis/recommendation/ownpintorgan/enterqueryorgan.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,org.radf.plat.commons.QueryInfo,com.lbs.cp.taglib.Formatter,org.radf.plat.util.global.GlobalNames,org.radf.plat.taglib.TagConstants" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<html>
<lemis:base/>
<lemis:body>
<%
  //定义查询条件输入区
  List editors = new ArrayList();
  editors.add(new Editor("text", "acb241", "机构名称"));
  editors.add(new Editor("text", "aab019", "企业性质"));
  
  
  //定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("acb241", "机构名称"));
  header.add(new Formatter("aab019", "企业性质"));
  header.add(new Formatter("aae005", "联系电话"));
  header.add(new Formatter("acb247", "工作人员数量"));
  header.add(new Formatter("acb248", "有职介资格人数"));
  header.add(new Formatter("acb24a", "市、县劳人厅审批意见"));
  header.add(new Formatter("aae011", "经办人"));
  header.add(new Formatter("aae017", "经办机构"));
  header.add(new Formatter("aae036", "经办时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
  
  //定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb240","内码");
  pageContext.setAttribute("hidden",hidden);
  
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("add","增 加","rec080101","add(document.all.tableform)"));
  buttons.add(new Button("edit","修 改","rec080103","modify(document.all.tableform)"));
  buttons.add(new Button("del","删 除","rec080105","del(document.all.tableform)"));
  buttons.add(new Button("search","查看","rec080106","search(document.all.tableform)"));
  buttons.add(new Button("close","关 闭","rec999999","closeWindow(\"Ree08Form\")"));
  //放入context中以供标签库获取
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","机构信息表");

%>
<lemis:title title="机构维护"/>
<lemis:query action="/Rec08Action.do?method=enterquery"  editorMeta="editor" topic="查询条件"/>
<lemis:table topic="机构列表" action="/Rec08Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//增加机构信息
function add(obj) {
    obj.action= '<html:rewrite page="/Rec08Action.do?method=initAddorgan&"/>'+getAlldata(obj);
    obj.submit();
  }
  
  //修改机构信息
  function modify(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec08Action.do?method=initmodifyorgan&"/>'+getAlldata(obj);
    obj.submit();
  }
  

//删除机构信息
 function del(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    if(confirm("此操作不能回退，确信要删除您选中的吗？")){
    obj.action= '<html:rewrite page="/Rec08Action.do?method=delorgan&"/>'+getAlldata(obj);
    obj.submit();
    }else{
    return false;
    }
  }
  
  //查看机构信息
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec08Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
</script>


