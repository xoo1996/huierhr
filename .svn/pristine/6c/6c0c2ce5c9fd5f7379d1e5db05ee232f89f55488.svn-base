<!--lemis/recommendation/ownpintorgan/organlist.jsp-->
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
   buttons.add(new Button("search","查看","rec080203","search(document.all.tableform)"));
   buttons.add(new Button("add","年检登记","rec080201","tologin(document.all.tableform)"));
  buttons.add(new Button("close","关 闭","rec999999","closeWindow(\"Ree08Form\")"));
  //放入context中以供标签库获取
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","机构信息表");

%>
<lemis:title title="机构年检登记"/>
<lemis:query action="/Rec0801Action.do?method=enterqueryorgan"  editorMeta="editor" topic="查询条件"/>
<lemis:table topic="机构列表" action="/Rec0801Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
  <table class="tableInput">
<lemis:editorlayout/>
<tr>
<lemis:codelisteditor type="aae001" label="年份" required="true" isSelect="true" redisplay="false" />
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
  </tr>
  </table>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//增加机构信息
function tologin(obj) {
     var t = editObj("chk");
    if(!t){
      return t;
    }
    if(document.all.aae001.value==''){
     alert('请选择相应的年份！');
     return false;
    }
    obj.action= '<html:rewrite page="/Rec0802Action.do?method=initAddorganyc&"/>'+getAlldata(obj)+'&aae001='+document.all.aae001.value;
    obj.submit();
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


