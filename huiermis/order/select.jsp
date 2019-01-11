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
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("���ɶ���","saveData(document.forms[0])");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctid", "�ͻ�����");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.submit();
	};
</script>

<script language="javascript">
	$(document).ready(function(){
		$("input[name=folnt]").val("");
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=folctnm]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=folctnm]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=folctnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
	<div id="target"></div>
	<lemis:title title="��������" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=saveOrder" method="POST">
			<tr>
				<lemis:texteditor property="folctnm" label="����ͻ�" required="true"
					disable="false" maxlength="20" value=""/>
				<html:hidden property="folctid" />
				<lemis:texteditor property="folnt" label="��ע" disable="false"
					maxlength="20" colspan="3" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


