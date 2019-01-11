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
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
		 $(document).ready(function(){
			 var nempay = '<%=request.getSession().getAttribute("nempay")%>';
			  $("input[name='nempay'][value='" + nempay +"']").attr("checked","checked"); 
		 }); 
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="��Ա��¼�����������޸�" />
		<lemis:tabletitle title="��Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=nem" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="��Ա��¼���������̺�id" required="false" disable="true"  />
					<lemis:texteditor property="nemname" label="����" required="true" disable="false"  />
					<lemis:codelisteditor type="nemsex" isSelect="true" label="�Ա�" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nembirthpl" label="����" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nembirthdt" required="true" label="��������" disable="false" format="true"/>
					<lemis:codelisteditor type="userheightestedu" isSelect="true" label="���ѧ��" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemmajor" label="��ѧרҵ" required="false" disable="false"  />
					<lemis:texteditor property="nemschool" label="����ҵѧУ" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nemedudt" required="false" label="��ҵʱ��" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="��λ��Ϣ" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nempart" label="��¼�ò���" required="true" disable="false" />
					<lemis:texteditor property="nemjob" label="��¼�ø�λ" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="����ְʱ��" disable="false" format="true" />
					<lemis:codelisteditor type="nemtype" isSelect="true" label="Ա������" redisplay="true" required="true" />
				</tr>
				</table>
				<lemis:tabletitle title="н�����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<td><input type="radio" name="nempay" value="0"/>��н��</td>
					<lemis:texteditor property="nemmon1" label="���ù���ÿ��˰ǰ��Ԫ��" required="false" disable="false" />
					<lemis:texteditor property="nemmon2" label="ת������ÿ��˰ǰ��Ԫ��" required="false" disable="false" />					
				</tr>
				<tr>  
                    <td><input type="radio" name="nempay" value="1"/>��н��</td>
					<lemis:texteditor property="nemyear1" label="��н��׼Ϊ˰ǰ��Ԫ��" required="false" disable="false" />
					<lemis:texteditor property="nemyear2" label="ÿ�·���˰ǰ��Ԫ��" required="false" disable="false" />	
				</tr>
				<tr>
					<lemis:texteditor property="nemwelfare" label="�������" required="false" disable="false" colspan="5"/>
				</tr>
				</table>
				<lemis:tabletitle title="�Ͷ���ͬǩ��" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemlimit" label="��ͬ���ޣ��꣩" required="false" disable="false" />	
					<lemis:texteditor property="nemtry" label="�����ڣ��£�" required="false" disable="false" />	
					<td>ע����ְһ���������ǩ��</td>
				</tr>
				</table>
				<lemis:tabletitle title="��ע" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="nemnote" rows="5" disabled="false"></html:textarea>
					</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

