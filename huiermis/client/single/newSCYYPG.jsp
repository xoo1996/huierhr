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
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
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
	<lemis:title title="用户复诊言语评估数据新增" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=addSCYYPG" method="POST">
		     <html:hidden property="ictgctid" />  
			<tr>
			
			     <lemis:texteditor property="ictid" label="用户编号" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false"
					disable="true" />
					<lemis:texteditor property="gctnm" label="所属团体" required="false"
					disable="true" />
			</tr>
			
			

     <lemis:tabletitle title="言语评估测试结果" />
			<table class="tableinput">
			<lemis:editorlayout />
	     	<tr>
					<lemis:texteditor property="fcypgl" label="给声强度左" required="false"
						disable="false" />
					<lemis:texteditor property="fcypgr" label="给声强度右" required="false"
						disable="false"  />
					<lemis:texteditor property="fcypwzt" label="未助听" required="false"
						disable="false" />
				</tr>
			
				<tr>
					<lemis:texteditor property="fcypdzl" label="单耳助听左" required="false"
						disable="false"  />
					<lemis:texteditor property="fcypdzr" label="单耳助听右" required="false"
						disable="false" />
					<lemis:texteditor property="fcypsz" label="双耳助听" required="false"
						disable="false" 
						/>
				</tr>
				<tr>
			   
				<lemis:formateditor mask="date" property="fctcdt" label="复诊日期"
					required="true" disable="false" />
				
			</tr>
			<tr>
				<lemis:texteditor property="fctnt" label="备注"
					required="false" disable="false" colspan="20"/>
			</tr>
			
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

