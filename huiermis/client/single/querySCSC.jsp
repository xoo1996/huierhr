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
String fcscictid=(String)request.getSession().getAttribute("fcscictid");
String fcscictnm=(String)request.getSession().getAttribute("fcscictnm");
String fcscictgctid=(String)request.getSession().getAttribute("fcscictgctid");
String fcscgctnm=(String)request.getSession().getAttribute("fcscgctnm");
	List<Formatter> header = new ArrayList<Formatter>();
	//header.add(new Formatter("fctctid", "�û����"));
	//header.add(new Formatter("ictnm", "�û�����"));
	//header.add(new Formatter("fcscid", "�����������"));
	header.add(new Formatter("ictnm","�û�����"));
	header.add(new Formatter("ictgctid","��������"));
	header.add(new Formatter("fctcdt", "��������"));
	header.add(new Formatter("fctnt", "��ע"));
  
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����","newSCSC(document.forms[0])");
	buttons.put("�鿴����","queryScscDetail(document.all.tableform)");
	buttons.put("ɾ��","deleteSCSC(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fcscid", "�����������");
	hidden.put("fctcdt", "��������");
	hidden.put("ictid", "�û����");
	hidden.put("ictgctid", "����������");
	hidden.put("gctnm", "��������");
	hidden.put("ictnm", "�û�����");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
/* 	session.setAttribute("tableheader", "���˿ͻ�������Ϣ��");//��ͷ */
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/CityJson.js"></script>
<script src="/huiermis/js/DistrictJson.js"></script>
<script src="/huiermis/js/ProJson.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<script language="javascript">
	function newSCSC(obj) {
		//var t = editObj("chk");
		//if(!t){
		//	return t;
		//}
		/*  var ictid=$("input[name=ictid]").val(); 
		alert(ictid); */
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=enterAddSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function deleteSCSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=deleteSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function queryScscDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetailSCSC&"/>' + getAlldata(obj);
		obj.submit();
	};
	$(document).ready(function(){	

		 $("#ictid").val("<%=fcscictid%>");
		 $("#ictnm").val("<%=fcscictnm%>");  
		 $("#ictgctid").val("<%=fcscictgctid%>");
		 $("#gctnm").val("<%=fcscgctnm%>");
	}); 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�û�����������ѯ" />
	<lemis:tabletitle title="�û�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do" method="POST">

			 <tr>
				<lemis:texteditor property="ictid" label="�û����" required="false" value="<%=fcscictid%>"
					disable="true" />
				<lemis:texteditor property="ictnm" label="�û�����" required="false" value="<%=fcscictnm%>"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="ictgctid" label="������" required="false" value="<%=fcscictgctid%>"
					disable="true" />
				<lemis:texteditor property="gctnm" label="��������" required="false" value="<%=fcscgctnm%>"
					disable="true" />
	
			</tr>
		</html:form>
	</table>
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="�û�����������Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


