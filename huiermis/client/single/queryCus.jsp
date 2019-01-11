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
	header.add(new Formatter("tmkfno", "������"));
	header.add(new Formatter("tmkgctnm", "�ͻ�����"));
	header.add(new Formatter("tmkcltnm", "�û�����"));
	header.add(new Formatter("tmksid", "������"));
	header.add(new Formatter("tmkplr", "����"));
	header.add(new Formatter("tmkpnm", "����������"));
	
	header.add(new Formatter("tmksta", "���ƻ�״̬"));
	header.add(new Formatter("tmkshmknm", "�������"));
	header.add(new Formatter("tmkshinstnm", "��ǰ�װ"));
	header.add(new Formatter("tmkqanm", "�ʼ�Ա"));
	header.add(new Formatter("tmkpdt", "�ƻ��깤"));
	header.add(new Formatter("tmkfmdt", "�깤����"));
	header.add(new Formatter("tmknt", "��ע"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkfno", "������");
	hidden.put("tmksid", "���ƻ�������");
	hidden.put("tmkcltnm", "�û�����");
	hidden.put("tmkpnm", "��������");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���˿ͻ����Ƽ�¼��");//��ͷ
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="���Ƽ�¼��ѯ" />
	<lemis:tabletitle title="�û�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="�û����" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="�û�����" required="false"
					disable="true" />
				<lemis:texteditor property="ictgctid" label="��������" required="false"
					disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="CustomizationAction.do" headerMeta="header"
		topic="���Ƽ�¼" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


