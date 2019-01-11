<!--lemis/recommendation/ownpintorgan/organcomplaintlist.jsp-->
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
  header.add(new Formatter("cb24.acb241", "机构名称"));
  header.add(new Formatter("cb24.aab019", "企业性质"));
  header.add(new Formatter("cb24.aae005", "联系电话"));
  header.add(new Formatter("cb26.acb261", "投诉类型"));
  header.add(new Formatter("cb26.acb262", "分数"));
  header.add(new Formatter("cb26.aae011", "经办人"));
  header.add(new Formatter("cb26.aae017", "经办机构"));
  header.add(new Formatter("cb26.aae036", "经办时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
  
  //定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb260","内码");
  pageContext.setAttribute("hidden",hidden);
  
  //定义按钮
   List buttons=new ArrayList();//定义按钮属性
   buttons.add(new Button("search","查看","rec080504","search(document.all.tableform)"));
  buttons.add(new Button("edit","修 改","rec080501","modify(document.all.tableform)"));
  buttons.add(new Button("del","删 除","rec080503","del(document.all.tableform)"));
  buttons.add(new Button("close","关 闭","rec999999","closeWindow(\"Ree08Form\")"));
  //放入context中以供标签库获取
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","机构投诉表");

%>
<lemis:title title="机构日常投诉维护"/>
<lemis:query action="/Rec0803Action.do?method=organComplaint"  editorMeta="editor" topic="查询条件"/>
<lemis:table topic="机构列表" action="/Rec0804Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//修改机构投诉信息
  function modify(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=initmodifyorgancom&"/>'+getAlldata(obj);
    obj.submit();
  }
  

//删除机构投诉信息
 function del(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    if(confirm("此操作不能回退，确信要删除您选中的吗？")){
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=delorgancom&"/>'+getAlldata(obj);
    obj.submit();
    }else{
    return false;
    }
  }
  
  //查看机构投诉信息
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
</script>


