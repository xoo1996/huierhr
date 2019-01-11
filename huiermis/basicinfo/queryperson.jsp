<!-- basicinfo/queryperson.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%//��ȡminuId
			String menuId = request.getParameter("menuId");
			PersonForm fm = (PersonForm) session
					.getAttribute("QueryPersonForm");
			if (menuId == null || "".equals(menuId)) {
				if (fm != null) {
					menuId = fm.getMenuId();
				}
			}
			//��ȡ��һ����url
			String url = request.getParameter("url");
			if (url == null || "".equals(url)) {
				if (fm != null) {
					url = fm.getUrl();
				}
			}
			if (url == null) {
				url = "";
			}
			String stringData = "";
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			if (null != qi) {
				stringData = qi.getStringData();
			}
			System.out.println("##url1##"+request.getParameter("url1"));
			System.out.println("##url2##"+request.getParameter("url2"));
			List header = new ArrayList();
			header.add(new Formatter("aac002", "������ݺ���"));
			header.add(new Formatter("aac003", "����"));
			header.add(new Formatter("aac004", "�Ա�"));
			header.add(new Formatter("ac01.aac006", "��������",
			TagConstants.DF_LEFT, TagConstants.DT_YEAR_MONTH_DATE));
			header.add(new Formatter("aac005", "����"));
			header.add(new Formatter("aac011", "�Ļ��̶�"));

			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			qh.put("url", null);
			pageContext.setAttribute("queryHiddens", qh);
			List buttons = new ArrayList();
			buttons.add(new Button("printperson", "�鿴������ϸ", "bas020306", "detail(document.all.tableform)"));
			//buttons.add(new Button("detail", "�� ϸ", "bas020301", "detail(document.all.tableform)"));
//			buttons.add(new Button("edit", "�� ��", "bas020302","editData()"));
			buttons.add(new Button("close", "�� ��", "bas999999","closeWindow(\"PersonForm,OperPersonForm,QueryPersonForm\")"));

			Map hidden = new LinkedHashMap();
			hidden.put("aac001", "���˱��");
			hidden.put("aac002", "������ݺ���");
			List editors = new ArrayList();
			editors.add(new Editor("card", "aac002", "������ݺ���"));
			editors.add(new Editor("text", "aac003", "����"));
			editors.add(new Editor("text","acc025","�Ͷ��ֲ��"));
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "��Ա��Ϣ��");//��ͷ
			%>
<html>
<lemis:base />
<lemis:body>
	<lemis:title title="���˲�ѯ" />
	<lemis:query action="/queryPerson.do?method=query" editorMeta="editor" hiddenMeta="queryHiddens" topic="������Ϣ��ѯ" />
	<html:hidden property="stringData" value="<%=stringData%>" />
	<lemis:table action="queryPerson.do" headerMeta="header" topic="������Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:clean names="QueryPersonForm" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
<script language="javascript">
	function editData(){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=query&buttonId=bas020305&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform)+"&url1=<%=url%>&url2="
		+(location.href+"&menuId=<%=menuId%>&aac001="+document.all("aac001").value).replace(/&/g,";amp;")+'&';
	}
//	function detail(obj){
//		var t = editObj("chk");
//		if(!t){
//			return t;
//		}
//		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=query&stringData=" +
//		document.all("stringData").value + "&" + getAlldata(document.all.tableform)+
//		"&url2=" + (location.href+"&menuId=<%=menuId%>"+"&"+getAlldata(obj)).replace(/&/g,";amp;")+
//		"&url1=" + (location.href+"&menuId=<%=menuId%>").replace(/&/g,";amp;")+'&';
//	}
	
		  //��ӡ
  function detail(obj){
			var t = editObj("chk");
			if(!t){
			return t;
			}
			obj.action = '<html:rewrite page="/personOperAction.do?method=printPerson&"/>'+getAlldata(obj);			
			obj.submit();
  }
</script>

