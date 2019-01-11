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
	//header.add(new Formatter("fctctid", "�û����"));
	//header.add(new Formatter("ictnm", "�û�����"));
	header.add(new Formatter("fctcdt", "��������"));
	header.add(new Formatter("fctreason", "����ԭ��"));
	header.add(new Formatter("fctdgn", "������"));
	header.add(new Formatter("fcttmt", "�������"));
	header.add(new Formatter("fctdoc", "ҽ��"));
	header.add(new Formatter("fctnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����","newSC(document.all.tableform)");
	buttons.put("ɾ��","deleteSC(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fctctid", "�û����");
	hidden.put("fctcdt", "��������");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���˿ͻ�������Ϣ��");//��ͷ
%>
<html>
<script language="javascript">
	function newSC(obj) {
		//var t = editObj("chk");
		//if(!t){
		//	return t;
		//}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=enterAddSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function deleteSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=deleteSC&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�û������ѯ" />
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
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="�û�������Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


