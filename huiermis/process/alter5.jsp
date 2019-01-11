<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("�� ��","saveData(document.forms[0])");
buttons.put("�� ��","history.back()");
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
		<lemis:title title="Ա����ǩ���������޸�" />
		<lemis:tabletitle title="Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=con" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="����id" required="true" disable="true" />
					<lemis:texteditor property="nemname" label="����" required="true" disable="false"  />
				</tr>
				<tr>
					<lemis:formateditor property="nemcard" label="���֤��" disable="false" required="true"  mask="card"/>
					<lemis:texteditor property="nememployid" label="Ա��id" required="false" disable="false"  />
				</tr>
				<tr>
					<lemis:texteditor property="nempart" label="���ڲ���" required="true" disable="false" />
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="����λ��ְʱ��" disable="false" format="true" />
					<lemis:texteditor property="nemjob" label="����ְ��" required="false" disable="false" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>ǩ���̶������Ͷ���ͬ</td>
					<lemis:texteditor property="nemlimit" label="��ͬ���ޣ��꣩" required="false" disable="false" />
					<td><input type="radio" name="nempay" value="1"/>ǩ���޹̶������Ͷ���ͬ</td>
				</tr>
				<lemis:tabletitle title="ԭ��ͬ����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="��һ��" required="false" disable="false" />
					<lemis:texteditor property="nemad2" label="�ڶ���" required="false" disable="false" />
					<lemis:texteditor property="nemad3" label="������" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="���Ĵ�" required="false" disable="false" />
					<lemis:texteditor property="nemad5" label="�����" required="false" disable="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

