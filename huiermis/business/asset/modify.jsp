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
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
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
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=astgctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=astgctid]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=astgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});

	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<lemis:body>
<lemis:errors />
<lemis:title title="修改固定资产" />
	<lemis:tabletitle title="固定资产信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=modifyAsset" method="POST">
			<tr>
				<lemis:texteditor property="astgctnm" label="客户名称" disable="true"
					required="true" />
				<lemis:texteditor property="astpdtnm" label="固定资产名称" disable="true"
					required="true" />
				<lemis:texteditor property="astut" label="单位" disable="true"
					required="true" />
		        <%-- <html:hidden property="pdtcls" value="OTH"/> --%>
			</tr>
			<tr>
				
				<lemis:formateditor mask="bigmoney" format="true" 
					property="astprc" label="固定资产金额" required="true" disable="false" />
				<lemis:texteditor property="astqnt" label="数量" required="true"
					disable="false" />
				<%-- <lemis:texteditor property="astdt" label="登记日期" required="true"
					disable="false" /> --%>
				<lemis:formateditor mask="date" format="true" required="false"
					property="astdt" label="登记日期" disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="astopr" label="登记员" 
					disable="true" />
				<lemis:texteditor property="astnote" label="备注" required="false"
					disable="false"  colspan="3"/>
			</tr>
			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
<lemis:base/>

</lemis:body>

</html>