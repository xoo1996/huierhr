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

	editors.add(new Editor("text","aae140","��������"));
	if("gs".equals(menuId)){
	   editors.add(new Editor("text","aa001a","��ʽ���"));
	}
	else if("lc".equals(menuId)){
		editors.add(new Editor("text","a001aa","�������"));
	}else
	{
	   editors.add(new Editor("text","aaa001","����������"));
	   editors.add(new Editor("text","aaa002","�����������"));
	}
  	
  	editors.add(new Editor("text","aaa003","��������"));
    editors.add(new Editor("text","aaa004","��������"));
   
	if("lc".equals(menuId)){
		 editors.add(new Editor("text","aa005a","����ֵ"));
	}else
	{
		 editors.add(new Editor("text","aaa005","����ֵ"));
	}
  	List<Object> header=new ArrayList<Object>();
	header.add(new Formatter("aae140","��������"));
	if("gs".equals(menuId)){
		   header.add(new Formatter("aa001a","��ʽ���"));
		   	}
	else if("lc".equals(menuId)){
			header.add(new Formatter("a001aa","�������"));
	}else
		{
			header.add(new Formatter("aaa001","����������"));
			header.add(new Formatter("aaa002","�����������"));
		}
	
	
	header.add(new Formatter("aaa003","��������"));
	header.add(new Formatter("aaa004","��������"));
	
	if("gs".equals(menuId)){
		header.add(new Formatter("aaa005","����ֵ"));
		header.add(new Formatter("aae030","��ʼʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		header.add(new Formatter("aae031","��ֹʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		   	}
	else if("lc".equals(menuId)){
		header.add(new Formatter("aa005a","����ֵ"));
	}else
		{
		header.add(new Formatter("aaa005","����ֵ"));
		header.add(new Formatter("aaa006","ά�޷���"));
		header.add(new Formatter("aae030","��ʼʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		header.add(new Formatter("aae031","��ֹʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
		}
	
	header.add(new Formatter("aae013","��ע",true));

	Map<String,String> hidden=new LinkedHashMap<String,String>();
    hidden.put("aae140","��������");
  	hidden.put("aaa001","����������");
	hidden.put("aa001a","����������");
	hidden.put("a001aa","����������");
    hidden.put("aaa003","��������");
  	hidden.put("aae030","��ʼʱ��");

	List<Object> buttons=new ArrayList<Object>();
	buttons.add(new Button("add","�� ��","sys060201","addParameter(document.all.tableform)"));
	buttons.add(new Button("mod","�� ��","sys060202","editData()"));
	buttons.add(new Button("del","ɾ ��","sys060203","delData()"));
	buttons.add(new Button("close","�� ��","sys999999","closeWindow(\"aa01Form\")"));
		
   	pageContext.setAttribute("header",header);
  	pageContext.setAttribute("editor",editors);
  	pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:title title="�ۺϲ�������"/>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:query action="/ParameterAction.do?method=selectaa01" editorMeta="editor" topic="�ۺϲ�����ѯ"/>
		<lemis:table topic="�ۺϲ�����Ϣ" action="/ParameterAction.do" headerMeta="header" hiddenMeta="hidden" mode="radio"/>
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
		if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�еĲ�����")){
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

