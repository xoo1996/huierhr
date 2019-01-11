<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
	<script language="javascript">
		function saveData(obj){
			var count1=$("textarea[name=cvtintro]").val().length; 
			
			if(count1<100){
			alert('自我鉴定不得少于100个字');return false;}
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="新员工转正申请流程修改" />
		<lemis:tabletitle title="基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=cvt" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="false" disable="true" />
					<lemis:texteditor property="cvtname" label="姓名" required="true" disable="true" />
					<lemis:formateditor mask="date" property="cvtbdt" required="false" label="出生年月" disable="false" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtedu" label="学历" required="false" disable="false" />
					<lemis:texteditor property="cvtsch" label="毕业学校" required="false" disable="false" />
					<lemis:formateditor mask="date" property="cvtsdt" required="false" label="入职时间" disable="false" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" />
					<lemis:formateditor mask="date" property="cvtedt" required="false" label="转正时间" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtfee" label="转正工资方案" required="false" disable="true" colspan="3"/>
				</tr>
				<lemis:tabletitle title="自我鉴定" />
				<tr>
					<html:textarea property="cvtintro" rows="4" cols="120"></html:textarea>			
				</tr>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

