<!-- basicinfo/writeOffEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
   Map buttons = new LinkedHashMap();
   buttons.put("ע��","writeOffEmployer(document.all.employerForm)");
   String url=request.getParameter("url");
   if (url==null) {
     url="";
   }
   if (!"".equals(url)) {
   buttons.put("����","javascript:location.href=\""+url.replaceAll(";amp;","&")+"\"");
   }
   buttons.put("�� ��","closeWindow(\"queryEmployerForm,employerForm\")");
   pageContext.setAttribute("buttons",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="ע����λ"/>
		<html:form action="/employerAction.do?method=viewEmp" method="post">
			<lemis:tabletitle title="��λ������Ϣ"/>
			<table class="TableInput">
				<lemis:editorlayout/>
				<html:hidden property="aab001"/>
				<tr>
					<lemis:texteditor property="aab003" label="��֯��������" />
					<lemis:texteditor property="aab004" label="��λ����" colspan="3" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aab019" label="��λ����" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab023" label="���ܲ���" required="false" isSelect="false"/>
				</tr>
				<tr>
					<td>ע���ʽ�</td>
					<td colspan="1" >
						<table class="TableInput">
							<td>
								<lemis:text property="aab049" label="ע���ʽ�"/>
							</td>
							<td width="30%">��Ԫ</td>
						</table>
					</td>
					<lemis:codelisteditor type="aab022" label="��ҵ����" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab054" label="��ҵ���" required="false" isSelect="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aab021" label="������ϵ" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab020" label="��������" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab056" label="��Ա��ģ" redisplay="true" isSelect="false"/>
				</tr>
				<tr>
					<lemis:texteditor property="aab013" label="����������"/>
					<lemis:texteditor property="aab014" label="������������ݺ���"/>
					<lemis:texteditor property="aae004" label="��ϵ��"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae005" label="��ϵ�绰"/>
					<lemis:texteditor property="aae014" label="����"/>
					<lemis:texteditor property="aae015" label="��������"/>
				</tr>
				<tr>
					<lemis:texteditor property="aaa021" label="��λ��������" />
					<html:hidden property="aab301"/>
					<lemis:texteditor property="aae006" label="��ַ" colspan="3"/>
					<!--
					//��ָ����ϵ����,�˴�ɾȥһ��
					 -->
				</tr>
				<tr>
					<lemis:texteditor property="aae007" label="��������"/>
					<lemis:texteditor property="aae016" label="��λ��ַ" colspan="3"/>
				<html:hidden property="aae119"/>
				</tr>
				<tr>
				</tr>
				<tr>
					<lemis:codelisteditor type="aab006" label="���̵Ǽ�ִ������" required="false" isSelect="false"/>
					<lemis:texteditor property="aab007" label="���̵Ǽ�ִ�պ���"/>
					<lemis:texteditor property="aab008" label="���̵ǼǷ�������"/>
				</tr>	
				<tr>
					<lemis:formateditor mask="nnn" property="aab009" label="���̵Ǽ���Ч����(��)" disable="true" required="false"/>
					<lemis:texteditor property="aab010" label="��׼������λ"/>
					<lemis:texteditor property="aab011" label="��׼����"/>
				</tr>
				<tr>
				</tr>
			</table>
			<lemis:tabletitle title="��λע����Ϣ"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property='aae119' />  
			<tr>
				<lemis:formateditor property="aab101" mask="date" label="ע������" colspan="2" disable="false" required="true"/>
				<lemis:codelisteditor type="aab100" colspan="2" isSelect="true" label="ע��ԭ��" redisplay="true" required="true"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab102" disable="false" label="ע��ƾ֤" maxlength="50" colspan="5"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae013" label="��ע" disable="false" maxlength="100" colspan="5"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aae011" label="������" isSelect="false"/>
				<lemis:codelisteditor type="aae017" label="�������" isSelect="false"/>
				<lemis:texteditor property="aae036" label="��������"/>
			</tr>
		</html:form>
		</table>
	<lemis:buttons buttonMeta="buttons"/>
	<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	//ע����λ������Ϣ
	function writeOffEmployer(obj) {
	if (checkValue(obj)) {
	obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer"/>';
	obj.action=obj.action+'&menuId='+'<%=request.getParameter("menuId")%>';
	obj.submit();
	}
	}
</script>
