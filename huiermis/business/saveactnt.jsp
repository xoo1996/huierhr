<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("返 回","window.history.back();");
    
    List<Editor> editors = new ArrayList<Editor>();
	pageContext.setAttribute("editor",editors);
    pageContext.setAttribute("button", buttons);
%>


<%@page import="org.radf.plat.taglib.Editor"%><html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
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
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="录入销账信息" />
	<lemis:tabletitle title="销账信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/SaleAction.do?method=savent" method="POST">
			<tr>
				<lemis:texteditor property="mgctid" label="客户代码" disable="true" 
				 required="true" />
				<lemis:texteditor property="myear" label="年" disable="true" 
				 required="true" />
				<lemis:texteditor property="mmonth" label="月" disable="true" 
				 required="true" />
			</tr>
			<tr>
			    <lemis:textareaeditor required="false" property="mnote" label="备注" disable="false"
				  maxlength="400" colspan="5" rowspan="5"/>
			</tr>
	</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
