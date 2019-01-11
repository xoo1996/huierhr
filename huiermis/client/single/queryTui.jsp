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
header.add(new Formatter("fdtfno", "������"));
header.add(new Formatter("folctnm", "����ͻ�"));
header.add(new Formatter("fdtcltnm", "���˿ͻ�"));
header.add(new Formatter("tmksid", "������"));
header.add(new Formatter("pdtnm", "��Ʒ����"));
header.add(new Formatter("fdtqnt", "����"));
header.add(new Formatter("fdtprc", "�ۼ�", "color:#000000;", TagConstants.DT_MONEY));
header.add(new Formatter("folsdt", "��������","", TagConstants.DT_YEAR_MONTH_DATE));
header.add(new Formatter("foltype", "��������"));
header.add(new Formatter("foloprnm", "¼��Ա"));
header.add(new Formatter("fdtodt", "�˻�����","", TagConstants.DT_YEAR_MONTH_DATE));
header.add(new Formatter("fdtnt", "��ע"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���˿ͻ��˻���¼��");//��ͷ
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="�˻���¼��ѯ" />
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
	<lemis:table action="OrderAction.do" headerMeta="header"
		topic="�˻���¼" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


