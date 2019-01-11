<!-- cfgmgmt/queryCI.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctid", "�ͻ����"));
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("gcttype", "����"));
	header.add(new Formatter("gctcnm", "��ϵ��"));
	header.add(new Formatter("gcttel", "��ϵ�绰"));
	header.add(new Formatter("gctfax", "����"));
	header.add(new Formatter("gctptcd", "�ʱ�"));
	header.add(new Formatter("gctstart", "��ҵʱ��"));
	header.add(new Formatter("gctaddr", "��ϵ��ַ", true));
	header.add(new Formatter("gctarea", "��������"));
	header.add(new Formatter("gctprovince", "ʡ��"));
	header.add(new Formatter("gctsname", "ȫ��", true));
	header.add(new Formatter("gctnt", "��ע", true));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("���˿ͻ���ϸ��Ϣ","detailci(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctid", "�ͻ�ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "gctid", "�ͻ����"));
	editors.add(new Editor("text", "gctnm", "�ͻ�����"));
	editors.add(new Editor("text","gcttype","�ͻ����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);

%>
<html>
<script language="javascript">
	//��ʾ��ϸ��Ϣ
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/CIOperAction.do?method=printCI&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="����ͻ���ѯ" />
	<lemis:query action="/GroupClientAction.do?method=query"
		editorMeta="editor" topic="����ͻ���Ϣ��ѯ" />
	<lemis:table action="GroupClientAction.do" headerMeta="header"
		topic="����ͻ���Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


