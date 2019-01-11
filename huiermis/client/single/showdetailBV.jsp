<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","history.back()");
	pageContext.setAttribute("buttons",buttons);
 %>
<html>
	<script src="/lemis/js/lemisTree.js"></script>
<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="�ݶ��ۺ������������" />
		<lemis:tabletitle title="�û�������Ϣ��Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/SingleClientAction.do?method=addBV" method="POST">
				<tr>
			    	 <lemis:texteditor property="ictid" label="�û����" required="false"
						disable="true" />
					<lemis:texteditor property="ictnm" label="�û�����" required="false"
						disable="true" />
					<lemis:texteditor property="gctnm" label="��������" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="ictage" label="����" required="false"
						disable="true" />
					<lemis:texteditor property="ictgender" label="�Ա�" required="false"
						disable="true" />
					<lemis:texteditor property="icttel" label="�绰" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="ictaddr" label="��ַ" required="false"
						disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:texteditor property="bvlefttype" label="����������ͺ�" required="false"
						disable="true" />
					<lemis:texteditor property="bvleftnum" label="���������������" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="bvrighttype" label="�Ҷ��������ͺ�" required="false"
						disable="true" />
					<lemis:texteditor property="bvrightnum" label="�Ҷ�������������" required="false"
						disable="true" />
				</tr>
				<th colspan="5">�طü�¼</th>
				<lemis:tabletitle title="��һ�λط�" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase1" label="��������봦��Բ�"
						required="true" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod1" isSelect="false" label="�ط÷���" redisplay="true" required="true" />
					<lemis:codelisteditor type="bveffect1" isSelect="false" label="Ч������" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor1" label="�ط���" required="true" disable="true" />
					<lemis:formateditor mask="date" property="bvdate1" required="true" label="�ط�����" disable="true" format="true"/>				
				</tr>	
				</table>
				<lemis:tabletitle title="�ڶ��λط�" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase2" label="��������봦��Բ�"
						required="false" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod2" isSelect="false" label="�ط÷���" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect2" isSelect="false" label="Ч������" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor2" label="�ط���" required="false" disable="true" />
					<lemis:formateditor mask="date" property="bvdate2" required="false" label="�ط�����" disable="true" format="true"/>			
				</tr>	
				</table>
				<lemis:tabletitle title="�����λط�" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase3" label="��������봦��Բ�"
						required="false" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod3" isSelect="false" label="�ط÷���" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect3" isSelect="false" label="Ч������" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor3" label="�ط���" required="false" disable="true" />
					<lemis:formateditor mask="date" property="bvdate3" required="false" label="�ط�����" disable="true" format="true"/>			
				</tr>	
				<tr>
					<lemis:codelisteditor type="bvassess" isSelect="false" label="����Ч������" redisplay="true" required="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="buttons" />
		<lemis:bottom />
	</lemis:body>
</html>
