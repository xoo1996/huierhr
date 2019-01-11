<%@page import="com.lbs.cp.taglib.Formatter"%>
<%@page import="org.radf.plat.taglib.Editor"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    List<Editor> editors = new ArrayList<Editor>();
    editors.add(new Editor("text","name","����"));
    editors.add(new Editor("text","useremployid","����"));
    editors.add(new Editor("text","store","����/�ݶ���"));
    editors.add(new Editor("text","startyear","��ʼ���","true"));
    editors.add(new Editor("text","startmonth","�·�","true"));
    editors.add(new Editor("text","endyear","�������","true"));
    editors.add(new Editor("text","endmonth","�·�","true"));
    
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("useremployid","����"));
    header.add(new Formatter("name","����"));
    header.add(new Formatter("store","�ݶ���"));
    header.add(new Formatter("year","��"));
    header.add(new Formatter("month","��"));
    header.add(new Formatter("yingfa","Ӧ������"));
    header.add(new Formatter("shifa","ʵ������"));


    
    Map<String, String> hidden = new LinkedHashMap<String, String>();

	

	pageContext.setAttribute("header",header);
	pageContext.setAttribute("editor",editors);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

</script>
<lemis:base />
<lemis:body>
	
	
	<lemis:title title="��ϸ�鿴" />
	<lemis:query action="/StatisticsAction.do?method=queryByPerson"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table topic="��ϸ" action="/StatisticsAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
