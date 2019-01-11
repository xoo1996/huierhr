<!--sysmanager/param/ParameterList.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String stringData = "";
	QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
    if(null != qi){
    	stringData = qi.getStringData();
    }
	String addCodeList = "window.location.href=\"" +
           GlobalNames.WEB_APP + "/sysmanager/ParameterAction.do.do?stringData=" + stringData + "\"";
    String menuId=(String)request.getSession().getAttribute("menuId");
  	List<Object> editors=new ArrayList<Object>();

	editors.add(new Editor("text","aae140","参数类型"));
	if("gs".equals(menuId)){
	   editors.add(new Editor("text","aa001a","公式类别"));
	}
	else if("lc".equals(menuId)){
		editors.add(new Editor("text","a001aa","流程类别"));
	}else
	{
	   editors.add(new Editor("text","aaa001","参数类别代码"));
	   editors.add(new Editor("text","aaa002","参数类别名称"));
	}
  	
  	editors.add(new Editor("text","aaa003","参数代码"));
    editors.add(new Editor("text","aaa004","参数名称"));
   
	if("lc".equals(menuId)){
		 editors.add(new Editor("text","aa005a","参数值"));
	}else
	{
		 editors.add(new Editor("text","aaa005","参数值"));
	}
  	List<Object> header=new ArrayList<Object>();
	header.add(new Formatter("aae140","参数类型"));
	if("gs".equals(menuId)){
		   header.add(new Formatter("aa001a","公式类别"));
		   	}
	else if("lc".equals(menuId)){
			header.add(new Formatter("a001aa","流程类别"));
	}else
		{
			header.add(new Formatter("aaa001","参数类别代码"));
			header.add(new Formatter("aaa002","参数类别名称"));
		}
	
	
	header.add(new Formatter("aaa003","参数代码"));
	header.add(new Formatter("aaa004","参数名称"));
	
	if("gs".equals(menuId)){
		header.add(new Formatter("aaa005","参数值"));
		header.add(new Formatter("aae030","开始时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		header.add(new Formatter("aae031","终止时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		   	}
	else if("lc".equals(menuId)){
		header.add(new Formatter("aa005a","参数值"));
	}else
		{
		header.add(new Formatter("aaa005","参数值"));
		header.add(new Formatter("aaa006","维修费用"));
		header.add(new Formatter("aae030","开始时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		header.add(new Formatter("aae031","终止时间",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		}
	
	header.add(new Formatter("aae013","备注",true));

	Map<String,String> hidden=new LinkedHashMap<String,String>();
    hidden.put("aae140","参数类型");
  	hidden.put("aaa001","参数类别代码");
	hidden.put("aa001a","参数类别代码");
	hidden.put("a001aa","参数类别代码");
    hidden.put("aaa003","参数代码");
  	hidden.put("aae030","开始时间");

	List<Object> buttons=new ArrayList<Object>();
	buttons.add(new Button("add","增 加","sys060201","addParameter(document.all.tableform)"));
	buttons.add(new Button("mod","修 改","sys060202","editData()"));
	buttons.add(new Button("del","删 除","sys060203","delData()"));
	buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"aa01Form\")"));
		
   	pageContext.setAttribute("header",header);
  	pageContext.setAttribute("editor",editors);
  	pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:title title="综合参数管理"/>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:query action="/ParameterAction.do?method=selectaa01" editorMeta="editor" topic="综合参数查询"/>
		<lemis:table topic="综合参数信息" action="/ParameterAction.do" headerMeta="header" hiddenMeta="hidden" mode="radio"/>
		<lemis:buttons buttonMeta="button"/>
		<lemis:bottom/>
	<lemis:errors/>
	</lemis:body>
	<lemis:clean names="aa01Form" />
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
	function editData(){
  		var t = editObj("chk");
 		if(!t){
			return t;
		}
  		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/ParameterAction.do?method=findaa01&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform).replace(/\+/g, '%2B')+"&addurl="+location.href.replace(/\&/g,";amp;");
	}
	function delData(){
 		var t = delObj("chk");
 		if(!t){
			return t;
		}
		if(confirm("此操作不能回退，确信要删除您选中的参数吗？")){
			window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/ParameterAction.do?method=deleteaa01&stringData=" +
			document.all("stringData").value + "&" + getAlldata(document.all.tableform).replace(/\+/g, '%2B');
    	}else{
     		return false;
		}
	}
	function addParameter(obj) {
		obj.action= '<html:rewrite page="/ParameterAction.do?method=enteraa01&"/>';
		obj.action=obj.action+"addurl="+location.href.replace(/\&/g,";amp;")+ "&" + getAlldata(document.all.tableform);
		obj.submit();
	}
</script>

