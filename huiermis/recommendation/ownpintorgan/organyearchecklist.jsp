<!--lemis/recommendation/ownpintorgan/organyearchecklist.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
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
  editors.add(new Editor("text", "aae001", "年检年份"));
  editors.add(new Editor("text", "acb251", "年检结果"));
  
  //定义查询结果显示区
  List header=new ArrayList();
  header.add(new Formatter("acb241", "机构名称"));
  header.add(new Formatter("aae001", "年检年份"));
  header.add(new Formatter("acb251", "年检结果"));
  header.add(new Formatter("acb252", "工作人员数量"));
  header.add(new Formatter("acb253", "有职介资格人数"));
  header.add(new Formatter("aae011", "经办人"));
  header.add(new Formatter("aae017", "经办机构"));
  header.add(new Formatter("aae036", "经办时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
  
  //定义隐藏数据
  Map hidden=new LinkedHashMap();
  hidden.put("acb240","内码");
  hidden.put("aae001","年检年份");
  pageContext.setAttribute("hidden",hidden);
  
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
   buttons.add(new Button("search","查看","rec080304","search(document.all.tableform)"));
  buttons.add(new Button("edit","修 改","rec080301","modify(document.all.tableform)"));
  buttons.add(new Button("del","删 除","rec080303","del(document.all.tableform)"));
  buttons.add(new Button("close","关 闭","rec999999","closeWindow(\"Ree08Form\")"));
  //放入context中以供标签库获取
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","机构年检信息表");

%>
<lemis:title title="机构年检维护"/>
<lemis:query action="/Rec0801Action.do?method=organManager"  editorMeta="editor" topic="查询条件"/>
<lemis:table topic="机构年检信息列表" action="/Rec0801Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script> 
  //修改机构年检信息
  function modify(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0802Action.do?method=initmodifyorganyc&"/>'+getAlldata(obj);
    obj.submit();
  }
  

//删除机构年检信息
 function del(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    if(confirm("此操作不能回退，确信要删除您选中的吗？")){
    obj.action= '<html:rewrite page="/Rec0802Action.do?method=delorganyc&"/>'+getAlldata(obj);
    obj.submit();
    }else{
    return false;
    }
  }
  
  //查看机构年检信息
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0802Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
</script>

