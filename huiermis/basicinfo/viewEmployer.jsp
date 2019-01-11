<!-- basicinfo/viewEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.EmployerForm" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
   	EmployerForm fm = (EmployerForm)session.getAttribute("EmployerForm");
   	String url=request.getParameter("url");
	String buttonId="";
	 String dwldyw="0000000";
	 if(fm.getDwldyw()!=null){
	 	dwldyw=fm.getDwldyw();
	 }
   	List buttons=new ArrayList();
	//buttons.add(new Button("back","�� ��","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("back","�� ��","bas999997","history.back()"));
    buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"queryEmployerForm,employerForm\")"));
   pageContext.setAttribute("buttons",buttons);
   	
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="�鿴��λ������Ϣ"/>
		<html:form action="/employerAction.do?method=viewEmp" method="post">
			<lemis:tabletitle title="��λ������Ϣ"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property="aab001"/>
            <html:hidden property="aae119"/>
            <tr>
            <td >��λ��������ҵ��</td>
            <td colspan="5" ><div align="center">
              <input name="zyjs" class="child" type="checkbox" id="zyjs" value="1" <%=(dwldyw!=null&&dwldyw.substring(0,1).equals("1")?"checked":"")%>>
                ְҵ����
                <input name="sydj" type="checkbox" class="child" id="sydj" value="1" <%=(dwldyw!=null&&dwldyw.substring(1,2).equals("1")?"checked":"")%>>
                ʧҵ�Ǽ�
                <input name="jydj" type="checkbox" id="jydj" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(2,3).equals("1")?"checked":"")%>>
                ��ҵ�Ǽ� 
                <input name="sybx" type="checkbox" class="child" id="sybx" value="1" <%=(dwldyw!=null&&dwldyw.substring(3,4).equals("1")?"checked":"")%>>
                ʧҵ�������� 
                <input name="lwpq" type="checkbox" id="lwpq" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(4,5).equals("1")?"checked":"")%>>
                ������ǲ 
                <input name="ldbz" type="checkbox" id="ldbz" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(5,6).equals("1")?"checked":"")%>>
                �Ͷ����� 
                <input name="jwry" type="checkbox" id="jwry" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(6,7).equals("1")?"checked":"")%>>
                ������Ա 
               
            </div></td>
            </tr>
			<tr>
			    <lemis:texteditor property="aab004" label="��λ����" maxlength="100" colspan="3" required="false" disable="true"/>
				<lemis:codelisteditor type="aab006" label="���̵Ǽ�ִ������" required="false" isSelect="false" redisplay="false"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab007" label="���̵Ǽ�ִ�պ���" disable="true" maxlength="20" required="false"/>					
				<lemis:texteditor property="ssjqnm" required="false" label="��������" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.aab301)" />
				<html:hidden property="aab301" />
				<lemis:texteditor property="aab013" label="����������" disable="true" maxlength="20"/>
		   </tr>
		   <tr>
				<lemis:formateditor mask="card" property="aab014" label="������������ݺ���" required="false" disable="true"/>
				<lemis:codelisteditor type="aab019" label="��λ����" required="false" isSelect="false" redisplay="true"/>
				<lemis:codelisteditor type="aab020" label="��������" required="false" redisplay="true" isSelect="false"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab057" label="��ҵ��λ����" redisplay="true" isSelect="false"/>
				<lemis:formateditor mask="date" property="aab011" label="��׼����" required="false" disable="true" format="true"/>
				<lemis:formateditor property="aab009" label="���̵Ǽ���Ч���ޣ��꣩" mask="nnn" required="false" disable="true"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab010" label="��׼������λ" disable="true" maxlength="25" required="false"/>	
				<lemis:formateditor mask="date" property="aab008" label="���̵Ǽ�ִ������" required="false" disable="true" format="true"/>				
				<lemis:texteditor property="aab003" label="��֯��������" maxlength="15" required="false" disable="true"/>
			</tr>
		    <tr>
				<lemis:texteditor property="aae006" label="��ַ" required="false" disable="true" maxlength="80" colspan="3"/>
				<lemis:codelisteditor type="aab021" label="������ϵ" required="false" redisplay="true" isSelect="false"/>				
			</tr>
			<tr>
				<lemis:texteditor property="aab030" label="��˰˰��" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab031" label="˰���������" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab032" label="˰���������" disable="true" maxlength="20" required="false"/>	
			</tr>			
			<tr>
				<lemis:formateditor mask="date" property="aab037" label="˰��֤��׼���ڣ�����" required="false" disable="true" format="true"/>		
				<lemis:codelisteditor type="aab022" label="��ҵ����" required="false" isSelect="false" redisplay="true"/>
				<lemis:texteditor property="aab343" label="һ����λ���" required="false" disable="true" maxlength="8"/>
			</tr>		
			<tr>
				<lemis:texteditor property="aab046" label="��˰˰��" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab047" label="��˰˰���������" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab012" label="��׼�ĺ�" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab034" label="��ᱣ�վ����������" disable="true" maxlength="8" required="false"/>	
				<lemis:codelisteditor type="aab341" label="��λ������" required="false" isSelect="false" redisplay="true"/>
				<lemis:codelisteditor type="aab342" label="��λ����" required="false" isSelect="false" redisplay="true"/>
				
			</tr>	
			<tr>
				<lemis:texteditor property="aab041" label="��λӢ������" maxlength="50" required="false" disable="true"/>			
				<lemis:texteditor property="aab042" label="��λ���" maxlength="10" required="false" disable="true"/>
				<lemis:texteditor property="aab043" label="��λƴ����" maxlength="50"  required="false" disable="true" onblur="this.value=this.value.toLowerCase()"/>
			</tr>			
			<tr>
				<lemis:texteditor property="aae005" label="��ϵ�绰" disable="true" maxlength="20" required="false"/>
				<lemis:texteditor property="aae004" label="��ϵ��" disable="true" maxlength="20" required="false"/>
				<lemis:codelisteditor type="aab056" label="��Ա��ģ" redisplay="true" isSelect="false"/>
				
			</tr>
			
			<tr>
				<lemis:codelisteditor type="aab054" label="��ҵ���" required="false" redisplay="true" isSelect="false"/>
				<lemis:texteditor property="aab002" label="��ᱣ�յǼ�֤���" disable="true" maxlength="20" required="false"/>
				<lemis:formateditor mask="date" property="aab036" label="��ᱣ�յǼ�֤��������" required="false" disable="true" format="true"/>
			</tr>
			
			<tr>
				<lemis:formateditor mask="######" property="aae007" label="��������" disable="true" required="false"/>
				<lemis:texteditor property="aae015" label="��������" disable="true" maxlength="30"/>
				<lemis:texteditor property="aae016" label="��λ��ַ" disable="true" maxlength="30"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae008" label="���д���" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae009" label="���л���" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae010" label="�����ʺ�" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab027" label="֧�����������к�" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab028" label="֧�����л���" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab029" label="֧�����л����ʺ�" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
			    <lemis:texteditor property="aae014" label="����" disable="true" maxlength="15"/>
				<html:hidden property="aab301"/>
				<lemis:texteditor property="aae013" label="��ע" required="false" disable="true" maxlength="200" colspan="3"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aae011" label="������" isSelect="false"/>
				<lemis:codelisteditor type="aae017" label="�������" isSelect="false"/>
				<lemis:formateditor mask="date" property="aae036" label="��������" required="false" disable="true" format="true"/>
			
			</tr>
			<lemis:buttons buttonMeta="buttons"/>
		</html:form>
		</table>
	<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
		//�޸ĵ�λ������Ϣ
		function editEmployer (obj){
		
			if (checkValue(obj)) {
				obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+
				'aab001='+obj.aab001.value+'&operation=edit&menuId='+'<%=request.getParameter("menuId")%>'+
				"&buttonId=<%=buttonId%>"+
				'&url=<%=url%>';
				location.href=obj.action;
			}
		}
		//ע����λ������Ϣ
		function writeOffEmployer(obj) {
		  if(confirm("ȷ��Ҫע���õ�λ?"))
		  {
			if (checkValue(obj)) {
				obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer&"/>'+
					'aab001='+obj.aab001.value+'&aae119='+obj.aae119.value+'&operation=writeOff&menuId='+'<%=request.getParameter("menuId")%>&'+'url=<%=url%>';
				location.href=obj.action;
			}
		  }
		}
		function check(url) {
        	window.location.href=url;
        }	
</script>

