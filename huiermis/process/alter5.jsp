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
	<script language="javascript">
	 $(document).ready(function(){
		 var nempay = '<%=request.getSession().getAttribute("nempay")%>';
		  $("input[name='nempay'][value='" + nempay +"']").attr("checked","checked"); 
	 }); 
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
		<lemis:title title="员工续签申请流程修改" />
		<lemis:tabletitle title="员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=con" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="true" disable="true" />
					<lemis:texteditor property="nemname" label="姓名" required="true" disable="false"  />
				</tr>
				<tr>
					<lemis:formateditor property="nemcard" label="身份证号" disable="false" required="true"  mask="card"/>
					<lemis:texteditor property="nememployid" label="员工id" required="false" disable="false"  />
				</tr>
				<tr>
					<lemis:texteditor property="nempart" label="所在部门" required="true" disable="false" />
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="本单位入职时间" disable="false" format="true" />
					<lemis:texteditor property="nemjob" label="现任职务" required="false" disable="false" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>签订固定期限劳动合同</td>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="false" />
					<td><input type="radio" name="nempay" value="1"/>签订无固定期限劳动合同</td>
				</tr>
				<lemis:tabletitle title="原合同期限" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="第一次" required="false" disable="false" />
					<lemis:texteditor property="nemad2" label="第二次" required="false" disable="false" />
					<lemis:texteditor property="nemad3" label="第三次" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="第四次" required="false" disable="false" />
					<lemis:texteditor property="nemad5" label="第五次" required="false" disable="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

