<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ciid", "���ƻ�ID"));
	header.add(new Formatter("ciname", "���ƻ�����"));
	header.add(new Formatter("citype", "���ƻ�����"));
	header.add(new Formatter("cilocation", "���ƻ�λ��"));
	header.add(new Formatter("cistate", "���ƻ�״̬"));
	header.add(new Formatter("cisupplier", "���ƻ���Ӧ��"));

	List<Button> buttons = new ArrayList<Button>();
	buttons.add(new Button("printCI", "���ƻ���ϸ��Ϣ", "cus010201", "detailci(document.all.tableform)"));
	buttons.add(new Button("modifyCI", "�޸Ķ��ƻ�", "cus010301", "modifyci(document.all.tableform)"));
	buttons.add(new Button("deleteCI", "ɾ�����ƻ�", "cus010302", "deleteci(document.all.tableform)"));
	buttons.add(new Button("close", "�� ��", "cus999993","closeWindow(\"\")"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ciid", "���ƻ�ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ciid", "���ƻ�ID"));
	editors.add(new Editor("text", "ciname", "���ƻ�����"));
	editors.add(new Editor("text","citype","���ƻ�����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���ƻ���Ϣ��");//��ͷ

%>
<html>
	<script language="javascript">
		//��ʾ��ϸ��Ϣ
  		function detailci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CIOperAction.do?method=printCI&"/>'+getAlldata(obj);			
			obj.submit();
  		}
		//�޸���������ϸ��Ϣ
  		function modifyci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CIOperAction.do?method=modifyCI&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//ɾ��������
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("ȷ��Ҫɾ���ö��ƻ���Ϣ��?")) {
				obj.action = '<html:rewrite page="/CIOperAction.do?method=deleteCI&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="���ƻ��޸Ĳ�ѯ" />
		<lemis:query action="/CIOperAction.do?method=queryCI&menuId=modifyCI" editorMeta="editor" topic="���ƻ���Ϣ��ѯ" />
		<lemis:table action="CIOperAction.do" headerMeta="header" topic="���ƻ���Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


