<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("qafno", "������"));
	header.add(new Formatter("qacltnm", "�û�����"));
	header.add(new Formatter("qasid", "������"));
	header.add(new Formatter("qapnm", "�������ͺ�"));
	header.add(new Formatter("qatype", "�ʼ����"));
	header.add(new Formatter("qastatus", "�ʼ�״̬"));
	header.add(new Formatter("bsc012", "�ʼ�Ա"));
	header.add(new Formatter("qachkdt", "�ʼ�����"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�ʼ��¼�޸�","modify(document.all.tableform)");
	buttons.put("�ʼ��¼ɾ��","deleteci(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("qaid", "�ʼ���ˮ��");
	hidden.put("qacltnm", "�û�����");
	hidden.put("qapnm", "�������ͺ�");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","qafno","������"));
	editors.add(new Editor("text","qasid","������"));
	editors.add(new Editor("text","qacltnm", "�û�����"));
	editors.add(new Editor("text","qatype", "�ʼ����"));
	editors.add(new Editor("text","qastatus", "�ʼ�״̬"));
	editors.add(new Editor("date","qachkdt", "�ʼ�����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
		//�޸ļ�¼
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/QAAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//ɾ����¼
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("ȷ��Ҫɾ�����ʼ��¼��")) {
				obj.action = '<html:rewrite page="/QAAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�ʼ��¼�޸Ĳ�ѯ" />
	<lemis:query action="/QAAction.do?method=query&order=modify"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="QAAction.do" headerMeta="header" topic="�ʼ��¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


