<!--sysmanager/param/CodeList.jsp-->
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script src="/lemis/js/proxyRequest.js"> </script>
<lemis:base />
<lemis:body>
<%
			String stringData = "";
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			if (null != qi) {
				stringData = qi.getStringData();
			}
			String addCodeList = "window.location.href=\""
					+ GlobalNames.WEB_APP
					+ "/parametermanagement/addcodeListAction.do?stringData="
					+ stringData + "\"";

			//List editors = new ArrayList();
			//editors.add(new Editor("text","aae140","代码大类"));
			//editors.add(new Editor("text", "aaa100", "代码类别"));
			//editors.add(new Editor("text", "aaa101", "类别名称"));
			//editors.add(new Editor("text", "aaa102", "代码值"));
			//editors.add(new Editor("text", "aaa103", "代码名称"));
			//editors.add(new Editor("text", "aaa104", "维护标识"));
			List header = new ArrayList();
			header.add(new Formatter("aae140", "代码大类"));
			header.add(new Formatter("aaa100", "代码类别"));
			header.add(new Formatter("aaa101", "类别名称"));
			header.add(new Formatter("aaa102", "代码值"));
			header.add(new Formatter("aaa103", "代码名称"));
			header.add(new Formatter("aaa104", "可维护标识"));
			Map hidden = new LinkedHashMap();
			hidden.put("aaa100", "代码类别");
			hidden.put("aaa102", "代码值");

			List buttons = new ArrayList();
			buttons.add(new Button("add", "增 加", "sys060101",
					"addCodeList(document.all.tableform)"));
			buttons.add(new Button("mod", "修 改", "sys060102", "editData()"));
			buttons.add(new Button("del", "删 除", "sys060103", "delData()"));
			buttons.add(new Button("close", "关 闭", "sys999999",
					"closeWindow(\"codeListForm\")"));

			   Map button1 = new LinkedHashMap();
			   button1.put("查询[Q]","return query(this.form)");
			   button1.put("重置[R]","resetForm(this.form);");
			   pageContext.setAttribute("button1",button1);
			
			pageContext.setAttribute("header", header);
			//pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("button", buttons);
%>
	<lemis:title title="代码管理" />
	<html:hidden property="stringData" value="<%=stringData%>" />
	
	 <!--查询部分1-->
  <lemis:tabletitle title="查询条件"/>
    <table class="TableInput">
    <html:form action="/codeListAction.do?method=selectaa10" method="POST">
    <lemis:editorlayout/>
    
      <tr>
         
         <lemis:codelisteditor type="aae140" redisplay="true"  label="代码大类" required="false" onchange="change(this)"/>
         <td>代码类别</td><td id="dmdl"><lemis:codelist type="aaa100" redisplay="true"  label="代码类别" isSelect="true"/></td>
         <lemis:texteditor property="aaa101" disable="false" label="类别名称" required="false" maxlength="20"/>
      </tr>
      <tr>
         <lemis:texteditor property="aaa102" disable="false" label="代码值" required="false" maxlength="20"/>
         <lemis:texteditor property="aaa103" disable="false" label="代码名称" required="false" maxlength="20"/>
         <lemis:codelisteditor type="aaa104" redisplay="true"  label="维护标识" isSelect="true"/>
      </tr>
      
      <tr>
   			 <td colspan="6"><lemis:buttons buttonMeta="button1"/> </TD>  
  	  </tr>
    </html:form>
    </table>
    
	
	<lemis:table topic="代码信息" action="/codeListAction.do"
		headerMeta="header" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
<lemis:clean names="aa10Form" />
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
function editData(){
   var t = editObj("chk");
  if(!t){
    return t;
  }
  	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/codeListAction.do?method=findaa10&stringData=" +
	document.all("stringData").value + "&" + getAlldata(document.all.tableform)+"&addurl="+location.href.replace(/\&/g,";amp;");
}
function delData(){
  var t = delObj("chk");
  if(!t){
    return t;
  }
    if(confirm("此操作不能回退，确信要删除您选中的代码吗？")){
      window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/codeListAction.do?method=deleteaa10&stringData=" +
      	document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
function addCodeList(obj) {
    obj.action= '<html:rewrite page="/codeListAction.do?method=enteraa10&"/>';
    obj.action=obj.action+"addurl="+location.href.replace(/\&/g,";amp;")+ "&" + getAlldata(document.all.tableform);
    obj.submit();
}
function change(obj) {
  
    var value = proxyRequest("/sysmanager/codeListAction.do?method=selectdmdl&aae140="+ obj.value);//请求自己后台的程序（和普通请求一样），获取基本信息,必须引入
    //alert(value);
    document.all.dmdl.innerHTML=value;
}
function query(obj){ 
    obj.action = '<html:rewrite page="/codeListAction.do?method=selectaa10&"/>';
    obj.submit();
    return true;
  }
</script>

