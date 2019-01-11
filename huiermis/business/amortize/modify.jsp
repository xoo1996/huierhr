<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">

	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<lemis:body>
<lemis:errors />
<lemis:title title="�޸�̯����Ϣ" />
	<lemis:tabletitle title="̯����Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=modifyAmortize" method="POST">
			<tr>
				<lemis:texteditor property="arzgctnm" label="�ͻ�����" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="arzdt"
					label="�Ǽ�����" disable="true" required="true" />
				<lemis:texteditor property="arzoprnm" label="�Ǽ�Ա" disable="true"
					required="true" />
			</tr>
			
			<tr>
				<lemis:texteditor property="arzitem" label="̯����Ŀ" disable="true"
					required="true" />
				<lemis:texteditor property="arzamount" label="̯���ܽ��" disable="true"
					required="true" />
				<lemis:texteditor property="arzmonth" label="̯���·�" disable="true"
					required="true" />
			</tr>
			
			<tr>
				<lemis:texteditor property="arzmon" label="̯�����" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="arzstdt" 
					label="̯����ʼʱ��" disable="true" required="true" />
				<lemis:formateditor mask="date" format="true" property="arzexpdt" 
					label="̯������ʱ��" disable="true" required="true" />
			</tr>
			
			<tr>
				<lemis:codelisteditor type="arzisexp" label="�Ƿ���" isSelect="true" redisplay="true"
					required="true" />
				<lemis:texteditor property="arzcontract" label="��ͬ����" disable="false"
					required="false" />
				<lemis:texteditor property="arznt" label="��ע" disable="false"
					required="false" />
			</tr>
			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
<lemis:base/>

</lemis:body>

</html>