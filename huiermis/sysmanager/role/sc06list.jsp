<!--sysmanager/role/sc06list.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.manage.role.form.Sc06Form"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			Sc06Form fm = (Sc06Form) session.getAttribute("sc06Form");
			List buttons=new ArrayList();
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			String stringData = "";
			if(qi!=null){
				stringData = qi.getStringData();
			}
			

			buttons.add(new Button("add","�� ��","sys030101","addData(document.forms[0])"));
			buttons.add(new Button("del","�� ��","sys030102","editData(document.all.tableform)"));
			buttons.add(new Button("del","ɾ ��","sys030103","deletData(document.all.tableform)"));
			buttons.add(new Button("close","�� ��","sys999999","closeWindow(\"sc06Form\")"));

			
			List header = new ArrayList();
			header.add(new Formatter("sc06.bsc014", "��ɫ���"));
			header.add(new Formatter("sc06.bsc015", "��ɫ����"));

			Map qh = new LinkedHashMap();
			qh.put("menuId", null);
			pageContext.setAttribute("queryHiddens", qh);
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc014", "��ɫ���"));
			editors.add(new Editor("text", "bsc015", "��ɫ����"));

			Map hidden = new LinkedHashMap();
			hidden.put("bsc014", "���");
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("header", header);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "��ɫ��Ϣ��");

			%>
<html>
<lemis:base />
<lemis:body>
	<lemis:title title="��ɫ��Ϣ�Ǽ�" />
	<lemis:query action="/roleAction.do?method=findAllSc06"
		editorMeta="editor" hiddenMeta="queryHiddens" topic="��ɫ��Ϣ��ѯ" />
	<input name="stringData" value="<%=stringData%>" type="hidden" />
	<lemis:table action="roleAction.do" headerMeta="header" topic="��ɫ��Ϣ�б�"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
<lemis:clean names="sc06Form" />

</html>
<script language="javascript">
	// ����
	function addData(obj){
		obj.action = '<html:rewrite page="/roleAction.do?method=Sc06find&menuId=add"/>'
		+"&stringData=" +document.all("stringData").value;
		obj.submit();
	}
	//�޸�
	function editData(obj){
		obj.action = '<html:rewrite page="/roleAction.do?method=Sc06find&menuId=mod"/>'
		+"&stringData=" +document.all("stringData").value+"&"+getAlldata(obj);
		obj.submit();
	}
	//ɾ��
	function deletData(obj){
	  var t = delObj("chk");
	  if(!t){
    	return t;
	   }
	    if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�еĴ�����")){
		obj.action = '<html:rewrite page="/roleAction.do?method=deleteSc06"/>'
		+"&stringData=" +document.all("stringData").value+"&"+getAlldata(obj);
		obj.submit();
		}else{
      		return false;
    	}
	}
</script>

