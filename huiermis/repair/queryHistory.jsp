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
	header.add(new Formatter("repgctnm", "���޵�λ"));
	header.add(new Formatter("repcltnm", "�û�����"));
	header.add(new Formatter("repid", "������"));
	header.add(new Formatter("reppnm", "����������"));
	header.add(new Formatter("repcls", "ά�����"));
	header.add(new Formatter("repdate", "��������"));
	header.add(new Formatter("repfdate", "�깤����"));
	header.add(new Formatter("repoprnm", "ά����Ա"));
	header.add(new Formatter("repamt", "ά�޷�"));
	header.add(new Formatter("repaction1", "ά�޴�ʩһ", true));
	header.add(new Formatter("repaction2", "ά�޴�ʩ��", true));
	header.add(new Formatter("repaction3", "ά�޴�ʩ��", true));
	header.add(new Formatter("repaction4", "ά�޴�ʩ��", true));
	header.add(new Formatter("repaction5", "ά�޴�ʩ��", true));
	header.add(new Formatter("repaction6", "ά�޴�ʩ��", true));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	/* buttons.put("����","newSC(document.all.tableform)"); */
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkgctnm", "���޵�λ����");
	hidden.put("tmkgctid", "���޵�λ����");
	hidden.put("tmkcltid", "�û�����");
	hidden.put("tmkcltnm", "�û�����");
	hidden.put("tmkpid", "����������");
	hidden.put("tmksid", "������");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>


<lemis:base />
<lemis:body>
	<lemis:title title="ά����ʷ��ѯ" />
	<lemis:tabletitle title="�û�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/CustomizationAction.do" method="POST">
			<tr>
				<lemis:texteditor property="tmkcltid" label="�û�����" disable="true" />
				<lemis:texteditor property="tmkcltnm" label="�û�����" disable="true" />
				<lemis:texteditor property="tmkgctnm" label="��������" disable="true" />
				<html:hidden property="tmkgctid" />
			</tr>
			<tr>
				<lemis:texteditor property="tmkfno" label="������" disable="true" />
				<lemis:texteditor property="tmkpnm" label="����������" disable="true" />
				<html:hidden property="tmkpid" />
				<lemis:texteditor property="tmksid" label="������" disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="RepairAction.do" headerMeta="header" topic="ά����ʷ��Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


