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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("�� ��","window.history.back();");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtcltnm","�û�����"));
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtprc","�ۼ�",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","����"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("header",header);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<lemis:base />
<lemis:body>
	<lemis:title title="�鿴������ϸ" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="������" disable="true" />
				<lemis:texteditor property="folctid" label="�ͻ�����" disable="true" />
				<lemis:texteditor property="folctnm" label="�ͻ�����" disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="foloprnm" label="¼��Ա" disable="true" />
				<td>�������</td>
				<td><lemis:operorg /></td>
				<lemis:texteditor property="foldt" label="��������" disable="true" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="������ϸ" action="/OrderDetailAction.do"
		headerMeta="header" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


