<!--sysmanager/menu/MenuQuery.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String stringData = "";
	QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
    if(null != qi){
    	stringData = qi.getStringData();
    }

  	List editors=new ArrayList();
  	editors.add(new Editor("text","bsc016","菜单ID"));
  	editors.add(new Editor("text","bsc018","标题"));
  	editors.add(new Editor("text","bsc019","父ID"));
    editors.add(new Editor("text","bsc021","类型"));
  	//editors.add(new Editor("text","bsc024","是否记日志"));
    editors.add(new Editor("text","bsc023","开发人员"));

  	List header=new ArrayList();
	header.add(new Formatter("bsc016","菜单ID"));
  	header.add(new Formatter("bsc018","标题"));
  	header.add(new Formatter("bsc017","链接"));
  	header.add(new Formatter("bsc019","父ID"));
  	header.add(new Formatter("bsc021","类型"));
  	header.add(new Formatter("bsc020","菜单序号"));
  	header.add(new Formatter("bsc022","菜单描述"));
  	//header.add(new Formatter("bsc024","是否记日志"));
	header.add(new Formatter("bsc023","开发人员"));

	Map hidden=new LinkedHashMap();
    hidden.put("bsc016","权限ID");

	  List batchInput = new ArrayList();
		batchInput.add(new Editor("nnnn","bsc020", "菜单序号","false"));	
	 pageContext.setAttribute("batchInput",batchInput);	
	
	List<Object> buttons=new ArrayList<Object>();
	buttons.add(new Button("add","增 加","sys040101","addData()"));
	buttons.add(new Button("mod","修 改","sys040102","editData()"));
	buttons.add(new Button("order","排 序","sys040104","orderData()"));
	buttons.add(new Button("del","删 除","sys040103","delData()"));
	buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"menuForm\")"));
	

   	pageContext.setAttribute("header",header);
  	pageContext.setAttribute("editor",editors);
  	pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("button",buttons);

%>
<html>
	<lemis:base/>
	<lemis:body>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:title title="菜单管理"/>
			<lemis:query action="/menuAction.do?method=menuQuery" editorMeta="editor" topic="菜单信息查询"/>
			<lemis:table topic="菜单信息" action="/menuAction.do" headerMeta="header" hiddenMeta="hidden" mode="checkbox"  batchInputMeta="batchInput" batchInputType="update"/>
			<lemis:buttons buttonMeta="button"/>
			<lemis:errors/>
			<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
function addData(){
  	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=menuSav&function=" + "add&" +
    document.all("stringData").value + "&" + getAlldata(document.all.tableform);
}
function editData(){
	var t = editObj("chk");
 	if(!t){
   		return t;
  	}
  	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=menuSav&function=" + "mod&" +
    document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    
}
function delData(){
	var t = delObj("chk");
	if(!t){
    	return t;
	}
    if(confirm("此操作不能回退，确信要删除您选中的代码吗？")){
    	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=menuDel&stringData=" +
      	document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
function orderData(){
	var t = delObj("chk");
	if(!t){
    	return t;
	}
    if(confirm("确信要重新排序吗？")){
    	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/menuAction.do?method=menuorder&stringData=" +
      	document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
</script>
