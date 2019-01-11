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
	header.add(new Formatter("repfolid", "������"));
	header.add(new Formatter("repgctnm", "�ͻ�����"));
	header.add(new Formatter("repcltnm", "�û�����"));
	header.add(new Formatter("repid", "��ģ���"));
	header.add(new Formatter("reppnm", "����������"));
	header.add(new Formatter("repdate", "��������","", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("repgctid", "���޵�λ"));
	header.add(new Formatter("reppdate","�ƻ��깤"));
	header.add(new Formatter("repsta", "ά��״̬"));
	header.add(new Formatter("repcls", "ά�����"));
	header.add(new Formatter("repcpy", "ά�޳���"));
	header.add(new Formatter("repamt", "ά�޷�"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("��ģά�޵Ǽ�","weixiudengji(document.all.tableform)");
	buttons.put("��ģά�޼�¼�޸�","alterweixiujilu(document.all.tableform)");
	buttons.put("��ģ�ݶ�ά��","huierweixiu(document.all.tableform)");
	buttons.put("��ģά�����ȷ��","finishedconfirm(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "ά����ˮ��");
	hidden.put("repgctnm", "���޵�λ����");
	hidden.put("repgctid", "���޵�λ����");
	hidden.put("repcltnm", "�û�����");
	hidden.put("reppid", "����������");
	hidden.put("repid", "��ģ���");
	hidden.put("repcpy", "ά�޳���");
	hidden.put("reppnm", "����������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","������"));
	editors.add(new Editor("text","repid","��ģ���"));
	editors.add(new Editor("text","repcltnm", "�û�����"));
	editors.add(new Editor("text","repcpy", "ά�޳���"));
	editors.add(new Editor("text","repsta", "ά��״̬"));
	editors.add(new Editor("text","reppid", "����������"));
	editors.add(new Editor("text","reppnm", "����������"));
	editors.add(new Editor("date","start", "�깤���ڴ�"));
	editors.add(new Editor("date","end", "��"));
	editors.add(new Editor("text","repcls", "ά�����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">

	//��ģά�޵Ǽ�
	function weixiudengji(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/earmould&page=/earrepregister.jsp"/>';
		obj.submit();
	};
	
	//��ģά�޼�¼�޸�
	function alterweixiujilu(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/earmould&page=/modify.jsp"/>';
		obj.submit();
	};
	
	
	//��ģ�ݶ�ά��
	function huierweixiu(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/querywx.jsp"/>';
		obj.submit();
	};
	
	//��ģά�����ȷ��
	function finishedconfirm(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/confirm.jsp"/>';
		obj.submit();
	};
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="��ģά�޼�¼��ѯ" />
	<lemis:query action="/EarMouldAction.do?method=queryRepair" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="EarMouldAction.do" headerMeta="header" topic="ά�޼�¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


