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
	header.add(new Formatter("tmkfno", "������"));;
	header.add(new Formatter("tmksid", "���ƻ�������"));
	header.add(new Formatter("tmkplr", "��������"));
	header.add(new Formatter("tmkpnm","��������"));
	header.add(new Formatter("tmkpst", "����״̬"));
	header.add(new Formatter("tmkurg", "�Ƿ�Ӽ�"));
	//header.add(new Formatter("tmkpst", "���ƻ�״̬", "color:#FF0000;"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.add(new Button("printCI", "��������ϸ��Ϣ", "cfg010201", "detailci(document.all.tableform)"));
	buttons.put("��ʼ�ʼ�", "test(document.all.tableform)");
	buttons.put("�� ��", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmksid","���ƻ�������");
	hidden.put("tmkfno","������");
	hidden.put("tmkpnm","��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tmksid", "���ƻ�������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	function test(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("ȷʵҪ��ʼ�ʼ���")) {
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=test&"/>' + getAlldata(obj);
			obj.submit();
		} else
			return t;
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="���ƻ���ѯ" />
	<lemis:query
		action="/CustomizationAction.do?method=query&order=test"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/CustomizationAction.do" headerMeta="header"
		topic="���ƻ���Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


