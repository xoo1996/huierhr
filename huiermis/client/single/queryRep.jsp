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
	header.add(new Formatter("repdate", "��������"));
	header.add(new Formatter("repdeclare", "����ԭ��"));
	header.add(new Formatter("repconfirmed", "����ȷ��"));
	header.add(new Formatter("repaction1", "��ʩһ"));
	header.add(new Formatter("repaction2", "��ʩ��"));
	header.add(new Formatter("repaction3", "��ʩ��"));
	header.add(new Formatter("repaction4", "��ʩ��"));
	header.add(new Formatter("repaction5", "��ʩ��"));
	header.add(new Formatter("repaction6", "��ʩ��"));
	header.add(new Formatter("repcls", "�������"));
	header.add(new Formatter("repamt", "���"));
	header.add(new Formatter("repoprnm", "����Ա"));
	header.add(new Formatter("repfdate", "�깤����"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���˿ͻ�ά�޼�¼��");//��ͷ
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="ά�޼�¼��ѯ" />
	<lemis:tabletitle title="�û�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="�û����" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="�û�����" required="false"
					disable="true" />
				<lemis:texteditor property="gctnm" label="��������" required="false"
					disable="true" /><%-- property="ictgctid" --%>
			</tr>
		</html:form>
	</table>
	<lemis:table action="RepairAction.do" headerMeta="header"
		topic="ά�޼�¼" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


