<!--sysmanager/dept/DeptGroupQuery.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
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
  	editors.add(new Editor("text","bsc006","虚拟统计名称"));

  	List header=new ArrayList();
	header.add(new Formatter("bsc006","虚拟统计名称"));
  	header.add(new Formatter("bsc007","虚拟统计位数"));
  	header.add(new Formatter("aae036","操作时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  	header.add(new Formatter("aae011","操作人"));

	Map hidden=new LinkedHashMap();
    hidden.put("bs006c","虚拟统计名称");
    hidden.put("bsc007","虚拟统计位数");

	List buttons=new ArrayList();
	buttons.add(new Button("add","增 加","sys010301","addData()"));
	buttons.add(new Button("mod","修 改","sys010302","editData()"));
	buttons.add(new Button("del","删 除","sys010303","delData()"));
	buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"deptForm\")"));

   	pageContext.setAttribute("header",header);
  	pageContext.setAttribute("editor",editors);
  	pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("button",buttons);
	 session.setAttribute("tableheader","虚拟统计设置");
%>
<html>
<lemis:base/>
<lemis:body>
	<html:hidden property="stringData" value="<%=stringData%>"/>
	<lemis:title title="虚拟统计"/>
		<lemis:query action="/deptAction.do?method=deptGroupQuery" editorMeta="editor" topic="虚拟统计信息查询"/>
		<lemis:table topic="虚拟统计信息查询" action="/deptAction.do" headerMeta="header" hiddenMeta="hidden" mode="radio"/>
   
		<lemis:buttons buttonMeta="button"/>
		<lemis:errors/>
		<lemis:bottom/>
	<lemis:clean names="sc03Form"/>
	</lemis:body>
</html>
<script language="javascript">
function addData(){

  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=deptGroupSav&function=" + "add&" +
    document.all("stringData").value + "&" + getAlldata(document.all.tableform);
}

function editData(){
   var t = editObj("chk");
  if(!t){
    return t;
  }
  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=deptGroupSav&function=" + "mod&" +
    document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    
}
function delData(){
  var t = delObj("chk");
  if(!t){
    return t;
  }
    if(confirm("此操作不能回退，确信要删除您选中的代码吗？")){
      window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=deptGroupDel&stringData=" +
      	document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
</script>

