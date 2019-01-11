<!-- basicinfo/enterpriseList.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.apps.basicinfo.form.QueryEmployerForm" %>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	QueryEmployerForm empForm = (QueryEmployerForm) session
	.getAttribute("QueryEmployerForm");
	Editor editor;
	String title = "";
	String buttonId="";
	String menuid = request.getParameter("menuId");
	if (menuid == null || "".equals(menuid)) {
		if(session.getAttribute("menuId")!=null){
			menuid = (String)session.getAttribute("menuId");
			request.setAttribute("1", menuid);
		}
	}
	
	List editors = new ArrayList();
	editors.add(new Editor("text", "aab007", "Ӫҵִ�պ�"));
	editors.add(new Editor("text", "aab003", "��֯��������"));
	editors.add(new Editor("text", "aab002", "��ᱣ�յǼ�֤����"));
	editors.add(new Editor("text", "aab004", "��λ����"));
	editors.add(new Editor("text", "aab043", "��λƴ����"));
	editor = new Editor("text", "aae119", "��λ״̬");
	


	if ((!"enterpriseWriteOff".equals(request.getParameter("menuId")))
	&& (!"enterpriseQuery".equals(request.getParameter("menuId")))
	&& (!"enterpriseReg".equals(request.getParameter("menuId")))
	&& (!"enterpriseAlter".equals(request.getParameter("menuId")))
	&& (!"enterpriseWriteOff".equals(request.getAttribute("1")))
	&& (!"enterpriseQuery".equals(request.getAttribute("1")))
	&& (!"enterpriseReg".equals(request.getAttribute("1")))
	&& (!"enterpriseAlter".equals(request.getAttribute("1")))) {
	editor.setDisable(false);
	}
	else if ("enterpriseReg".equals(menuid)
	|| "empObtainWork".equals(menuid)) {
	title = "��λ�Ǽ�";
	}
	else if ("enterpriseAlter".equals(menuid)) {
	title = "��λ���";
	buttonId = "bas010205";
	}
	else if ("enterpriseWriteOff".equals(menuid)) {
	title = "��λע��";
	}
	else if ("enterpriseQuery".equals(menuid)) {
	title = "��λ��ѯ";
	}
	if ("uniteCop".equals(menuid)){
	title="Ŀ��ϲ���λ��ѯ";
    }
    if("enterpriseDel".equals(menuid)){
    title = "��λ��ѯ";
    }
    
    
	

	Map hiddens = new LinkedHashMap();
	hiddens.put("menuId", menuid);
	pageContext.setAttribute("hiddens", hiddens);

	Map hidden = new LinkedHashMap();
	hidden.put("aab001", "��λ���");
	hidden.put("aae119", "��λ״̬");
	
	List header = new ArrayList();
	header.add(new Formatter("ab01.aab003", "��֯��������"));
	header.add(new Formatter("ab01.aab004", "��λ����"));
	header.add(new Formatter("ab01.aab020", "��������"));
	header.add(new Formatter("ab01.aab019", "��λ����",
	TagConstants.DF_CENTER));
	header.add(new Formatter("ab01.aae004", "��ϵ��"));
	header.add(new Formatter("ab01.aae005", "��ϵ�绰"));
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden", hidden);
	
	List buttons=new ArrayList();//ʹ��ArrayList
	QueryInfo queryinfo = (QueryInfo) request.getAttribute("queryinfo");
	String tableMode = "radio";
	
	if (queryinfo != null && queryinfo.getTotalNum() > 0) {
	if ("enterpriseReg".equals(menuid)) {
	buttons.add(new Button("view", "�鿴��λ��ϸ", "bas010101", "viewEmp(document.all.tableform);"));
	} else if ("enterpriseAlter".equals(menuid)) {
	buttons.add(new Button("view", "�鿴��λ��ϸ", "bas010201", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("alter", "�� ��", "bas010202", "alterEmp(document.all.tableform);"));	
	} else if ("enterpriseWriteOff".equals(menuid)) {
	buttons.add(new Button("view", "�鿴��λ��ϸ","bas010301", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("writeOff","ע ��","bas010302", "writeOffEmp(document.all.tableform);"));
	} else if ("enterpriseQuery".equals(menuid)) {
	buttons.add(new Button("view","�鿴��λ��ϸ","bas010401", "viewEmp(document.all.tableform);"));
//	buttons.add(new Button("edit","�� ��","bas010402", "editEmp(document.all.tableform);"));
	}else if("enterpriseDel".equals(menuid)){
	buttons.add(new Button("view","�鿴��λ��ϸ","bas010101", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("del","ɾ ��","bas010501", "delEmp(document.all.tableform);"));
	} 
	else if("uniteCop".equals(menuid)){
	buttons.add(new Button("view","�鿴��λ��ϸ","bas010601", "viewEmp(document.all.tableform);"));
	buttons.add(new Button("uniteCop","��һ��","bas010602", "uniteCop(document.all.tableform);"));
	
	}
	
	}

	if ("enterpriseReg".equals(menuid)
	|| "empObtainWork".equals(menuid)) {
		if(session.getAttribute("queryInfo1")!=null){
			buttons.add(new Button("add","�� ��","bas010102","addEmp(document.forms[0]);"));
		}
	}
	
	buttons.add(new Button("close", "�� ��", "bas999999", "closeWindow(\"QueryEmployerForm\")"));
	pageContext.setAttribute("buttons", buttons);
	session.setAttribute("tableheader", "��λ��Ϣ��");//��ͷ
	session.setAttribute("menuId", menuid);
	
	
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="<%=title%>" />
		<lemis:query action="/queryEmployerAction.do?method=queryEnterprise" editorMeta="editor" hiddenMeta="hiddens" topic="��λ��ѯ" />
		<lemis:table topic="��λ�б�" action="/queryEmployerAction.do" headerMeta="header" hiddenMeta="hidden" mode="<%=tableMode%>" />
		<lemis:buttons buttonMeta="buttons" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">		
		// �鿴��λ������Ϣ
		function viewEmp (obj){
			var para = getAlldata(obj);
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+'menuId=<%=menuid%>&'+para;
			// ����URL��ַ������URL��ַ��&����ת����";amp;"��
			obj.action = obj.action + "&url=" + location.href.replace(/&/g,";amp;")+';amp;menuId=<%=menuid%>';
			location.href=obj.action;
		}
				
		//������λ������Ϣ
		function addEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=entryAddEmployer&"/>'+'menuId=<%=menuid%>'
			+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}

		//�����λ������Ϣ
		function alterEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+'menuId=<%=menuid%>'+
			"&buttonId=<%=buttonId%>"+
			"&operation=edit2&"+getAlldata(obj)+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}
			
			
		//ע����λ������Ϣ
		function writeOffEmp(obj) {
			if(confirm("ȷ��Ҫע���õ�λ?"))
			{
				obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer&"/>'+'menuId=<%=menuid%>&'
				+getAlldata(obj)+ "operation=writeOff&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
				location.href=obj.action;
			}
		}
			
		function editEmp(obj) {
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&operation=edit2&"/>'+'menuId=<%=menuid%>&'
			+getAlldata(obj)+ "&url=" + (location.href+'&menuId=<%=menuid%>').replace(/&/g,";amp;");
			location.href=obj.action;
		}
		//ɾ����λ������Ϣ
		function delEmp(obj) {
			if(confirm("ȷ��Ҫɾ���õ�λ?"))
			{
			obj.action = '<html:rewrite page="/employerAction.do?method=delEmployer&"/>'+getAlldata(obj);
			obj.submit();
			}
		}
		
	//��λ�ϲ�	
	function uniteCop(obj){
	   obj.action = '<html:rewrite page="/UniteEmployAction.do?method=initEnterEmploy&"/>'+getAlldata(obj)+"&newaab001="+getSelectData(obj,'aab001');
	   location.href=obj.action;
	}
	
</script>
