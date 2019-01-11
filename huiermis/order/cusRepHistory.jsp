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
	header.add(new Formatter("pdtnm", "����������"));
	header.add(new Formatter("repdate", "��������"));
	header.add(new Formatter("repamt", "ά�޷�"));
	
	
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

 	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkgctnm", "���޵�λ����");
	hidden.put("tmkgctid", "���޵�λ����");
	hidden.put("tmkcltid", "�û�����");
	hidden.put("tmkcltnm", "�û�����");
	hidden.put("tmkpid", "����������");
	hidden.put("tmksid", "������");
	
	hidden.put("pdtid", "��Ʒ����");
	hidden.put("pdtnm","��Ʒ����");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>


<lemis:base />
<lemis:body>
	<lemis:title title="ά�޼�¼��ѯ" />
	<lemis:tabletitle title="�û�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="pdtid" label="��Ʒ����" disable="true" />
				<lemis:texteditor property="pdtnm" label="����������" disable="true" />
			</tr>
		</html:form>
	</table>
	<lemis:table action="OrderAction.do" headerMeta="header" topic="ά�޼�¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


