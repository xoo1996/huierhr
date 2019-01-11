<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("folno", "������"));
	header.add(new Formatter("folctid", "����ͻ�"));
	header.add(new Formatter("ictnm", "���˿ͻ�"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("fdtsqnt", "����"));
	header.add(new Formatter("fdtprc", "�ۼ�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "��������"));
	header.add(new Formatter("folsdt", "��������"));
	header.add(new Formatter("folway", "������ʽ"));
	header.add(new Formatter("folsno", "�����"));
	header.add(new Formatter("folsta", "����״̬"));
	header.add(new Formatter("folnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("����","delivery(document.all.tableform)");
	//buttons.put("�޸Ķ���","modify(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folctid", "�ͻ�����", "true"));
	editors.add(new Editor("date", "start", "ʱ���", "true"));
	editors.add(new Editor("date", "end", "��", "true"));
	
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
	//����
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="����" />
	<lemis:query action="/BusinessAction.do?method=check" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="������ϸ��Ϣ"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


