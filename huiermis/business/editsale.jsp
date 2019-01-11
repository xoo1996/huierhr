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
    buttons.put("返回","window.history.back();");
    
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
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="修改客户费用信息" />
	<lemis:tabletitle title="修改费用信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/SaleAction.do?method=editsale" method="POST">
			
			<tr>
				<lemis:texteditor property="mgctid" label="客户代码" disable="true" 
				 required="true" />
				<lemis:texteditor property="myear" label="年" disable="true" 
				 required="true" />
				<lemis:texteditor property="mmonth" label="月" disable="true" 
				 required="true" />
				
			</tr>
		    <tr>
				<lemis:formateditor mask="bigmoney" format="true" required="false" property="mad"
				   label="广告费" disable="false" />
				<lemis:formateditor mask="bigmoney" format="true" required="false" property="mback" 
				   label="返利" disable="false" />
			    <lemis:formateditor mask="bigmoney" format="true" required="false" property="mothers"
				   label="其他款项" disable="false" />
			</tr>
			<tr>
			    <lemis:texteditor required="false" property="mnote" label="备注" disable="false" 
				  maxlength="42" colspan="3"/>
			</tr>
			<tr>	
			    <lemis:formateditor mask="bigmoney" format="true" required="false" property="mnsales" 
			     label="不计销售" disable="false" />	
			    <lemis:formateditor mask="bigmoney" format="true" required="false" property="msales" 
			     label="本月销售" disable="false" />			  		
				<lemis:formateditor mask="bigmoney" format="true" required="false" property="mpamnt"
				 label="实际回款" disable="false" />
		    </tr>
			<tr>
			    <lemis:texteditor required="false" property="mopnm" label="录入员" disable="true" 
				  maxlength="50"/>	
				<lemis:formateditor mask="date" format="true" required="false" property="mopdt"
				 label="录入时间" disable="true" />	
			</tr>
	</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
