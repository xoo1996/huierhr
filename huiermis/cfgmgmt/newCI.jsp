<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

    List<Button> buttons=new ArrayList<Button>();
    buttons.add(new Button("save","保 存","cus010101","saveData(document.forms[0])"));//公共的按钮
    buttons.add(new Button("reset","重 置","cus999992","document.forms[0].reset();"));
    buttons.add(new Button("close","关 闭","cus999993","closeWindow(\"\")"));
    pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="耳机定制项信息新增" />
		<lemis:tabletitle title="耳机定制项信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/CIOperAction.do?method=saveNewCI" method="POST">
				<tr>
					<lemis:texteditor property="ciid" label="耳机ID" required="true" disable="false" maxlength="20" />
					<lemis:texteditor property="ciname" label="耳机名称" required="true" disable="false" maxlength="20" />
					<lemis:codelisteditor type="citype" isSelect="true" label="耳机类型" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cilocation" label="耳机位置" required="false" disable="false" maxlength="20" />
					<lemis:texteditor property="civersion" label="耳机版本" required="false" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:texteditor property="cidescription" label="耳机描述" required="false" disable="false" maxlength="60" colspan="5" />
				</tr>
				<tr>
					<lemis:codelisteditor type="cistate" isSelect="true" label="耳机状态" redisplay="true" required="true" />
					<lemis:texteditor property="cisupplier" label="耳机供应商" required="false" disable="false" maxlength="50" colspan="7" />					
				</tr>
				<tr>
					<lemis:texteditor property="cicharger" label="负责人" disable="false"  maxlength="20"/>
					<lemis:formateditor mask="date" property="cidepredt" required="false" label="折旧结束日期" disable="false" format="true"/>
					<lemis:formateditor mask="date" property="cigrtdt" required="false" label="保修日期" disable="false" format="true"/>
				</tr>
				<tr>
					<td>经办人</td>
					<td><lemis:operator/></td>
					<td>经办机构</td>
					<td ><lemis:operorg/></td>
					<td>经办日期</td>
					<td><lemis:operdate/></td>
				</tr>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

