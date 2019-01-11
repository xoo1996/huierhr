<!--/recommendation/employassembly/ManageInvitePosition.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.recommendation.employassembly.dto.Rec0702DTO" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<% 
  String menuId=request.getSession().getAttribute("menuId").toString();
  
LoginDTO userform = (LoginDTO) request.getSession()
            .getAttribute("LoginDTO");
  //String dept = TypeCast.getDept(userform.getBsc001());
  
  String url=(String)request.getAttribute("nextP");
  if (url!=null)
  {
  request.getSession().setAttribute("nextP",url);
  } else 
 url = (String)request.getSession().getAttribute("nextP");

  //获取委托招聘信息
  Rec0702DTO dto = null;
  String aab004 = "";    //单位名称
  String aab003 = "";    //组织机构代码
  String acb206 = "";    //发布名称
  String aab001 = "";    //单位编号
  String acb204 = "";    //工作地点
  String acb202 = "";    //招聘地点
  String aae004 = "";    //联系人
  String aae005 = "";    //联系电话
  String aae030 = "";    //招聘开始日期
  String aae031 = "";    //招聘终止时间
  dto = (Rec0702DTO)request.getSession().getAttribute("Rec0702DTO");
  if (dto.getAab004()!=null) aab004 = dto.getAab004().trim();
  if (dto.getAab003()!=null) aab003 = dto.getAab003().trim();
  if (dto.getAcb204()!=null) acb204 = dto.getAcb204().trim();
  if (dto.getAcb202()!=null) acb202 = dto.getAcb202().trim();
  if (dto.getAae004()!=null) aae004 = dto.getAae004().trim();
  if (dto.getAae005()!=null) aae005 = dto.getAae005().trim();
  if (dto.getAae030()!=null) aae030 = dto.getAae030().toString();
  if (dto.getAae031()!=null) aae031 = dto.getAae031().toString();
  if (dto.getAcb206()!=null) acb206 = dto.getAcb206().trim();
  if (dto.getAab001()!=null) aab001 = dto.getAab001().trim();



  //显示招聘空位信息
  List header = new ArrayList();
  Map hidden = new LinkedHashMap();
  hidden.put("acb200","招聘编号");
  hidden.put("acb210","空位编号");
  hidden.put("aab001","单位编号");



  header.add(new Formatter("aab004","单位名称"));
  header.add(new Formatter("aca112","工种名称"));
  header.add(new Formatter("acb216","工种说明"));
  header.add(new Formatter("aac048","用工形式"));
  header.add(new Formatter("acb21h","劳动报酬"));



  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

  List buttons = new ArrayList();
	
	
    buttons.add(new Button("Btn_view", "查 看","rec070205", "view(document.all.tableform)"));
  	buttons.add(new Button("Btn_ad", "增 加","rec070206", "add(document.all.tableform)"));
  	buttons.add(new Button("Btn_edit", "修 改","rec070207", "edit(document.all.tableform)"));
  	buttons.add(new Button("Btn_del", "停止招工","rec070208", "stop()"));
  	buttons.add(new Button("Btn_delete", "删除招工","rec070211", "del()"));
  	
  buttons.add(new Button("Btn_back", "返 回","rec999997", "go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close", "关 闭","rec999999", "closeWindow(\"Rec0702DTO,Rec0702Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="查询招聘空岗信息"/>  
  <%//显示委托招聘信息部分%>
  <lemis:tabletitle title="招聘单位基本信息"/>  
  <table class="tableInput">
  <html:form action="/Rec0702Action.do" method="POST">
  <lemis:editorlayout/>
    <tr>
      <lemis:texteditor property="aab004"  colspan="2" label="单位名称" value="<%=aab004%>" />
      <lemis:texteditor property="aab003" label="组织机构代码" value="<%=aab003%>"/>
   </tr>
   </table>

  <lemis:tabletitle title="本次招聘联系信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>
      <lemis:texteditor property="acb206" label="发布名称" value="<%=acb206%>"/>
      <lemis:texteditor property="acb204" label="工作地点" value="<%=acb204%>"/>
      <lemis:texteditor property="acb202" label="招聘地点" value="<%=acb202%>"/>
    </tr>
    <tr>
      <lemis:texteditor property="aae004" label="联系人" value="<%=aae004%>"/>
      <lemis:texteditor property="aae005" label="联系电话" value="<%=aae005%>" colspan="5"/>
      
    </tr>
   
    </html:form>
  </table>
  <%//显示批量查询结果信息%>
  <lemis:table topic="空岗信息" action="/Rec0702Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" appendBlank="false"/>
  <lemis:buttons buttonMeta="buttons"/>

 
  <%//界面模型规定的其他标配部分%>
  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>

<script language="javascript" >
  

  // 新增招聘空位信息
  function add(obj){
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=addEmptyPost&"/>'+getAlldata(document.all.tableform);
    obj.submit();
  }

  // 修改招聘空位信息
  function edit(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=modEmptyPost&"/>'+getAlldata(document.all.tableform);   
    obj.submit();
  }
  // 删除空位信息
  function stop(){
    var t = delObj("chk");
    if(!t){
      return t;
    }

    if(confirm("此操作不能回退，确信要停止招工吗？")){
      var url = '<html:rewrite page="/Rec0702Action.do?method=delEmptyPost&"/>'+getAlldata(document.all.tableform);
      
      location.href = url ;
    }else{
      return false;
    }
  }
  // 删除空位信息
  function del(){
    var t = delObj("chk");
    if(!t){
      return t;
    }

    if(confirm("此操作不能回退，确信要删除您选中的信息吗？")){
      var url = '<html:rewrite page="/Rec0702Action.do?method=deleteEmptyPost&"/>'+getAlldata(document.all.tableform);
      
      location.href = url 
    }else{
      return false;
    }
  }
  // 查看招聘空位信息
   function view(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=viewEmptyPost&"/>'+getAlldata(document.all.tableform);
   
    obj.submit();
  }

  //下一步
  function next (obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/goToPageAction.do?method=goToPage&"/>'+getAlldata(obj);
   
    obj.submit();
  }
</script>


