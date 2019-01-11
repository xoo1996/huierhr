<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("nemid", "����ID",TagConstants.DF_CENTER));
	header.add(new Formatter("apatype", "��������",TagConstants.DF_CENTER));
	header.add(new Formatter("nemname", "����������",TagConstants.DF_CENTER));
	header.add(new Formatter("nemappdt", "����ʱ��",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("�� ��","verifyapa(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("nemid", "����ID");
	hidden.put("apatype", "��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "nemid", "����ID"));
	editors.add(new Editor("text", "nemname", "����������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "��ְ��������鿴");//��ͷ

%>
<html>
	<script language="javascript">
		//�����Ϣ
  		function verifyapa(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=printApa&type=rzsx&"/>'+getAlldata(obj);			
			obj.submit();
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="��ְ��������鿴" />
		<lemis:query action="/ApaOperAction.do?method=queryApa&type=rzsx" editorMeta="editor" topic="��ְ���������ѯ" />
		<lemis:table action="ApaOperAction.do" headerMeta="header" topic="������Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


