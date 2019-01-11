<!-- basicinfo/editEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String url=request.getParameter("url");
	String buttonId = request.getParameter("buttonId");
      if (url==null) {
          url="";
      }
	 String mod=(String)request.getSession().getAttribute("mod");
	List buttons = new ArrayList();
	if ("enterpriseReg".equals(request.getParameter("menuId"))){
		buttons.add(new Button("save","�� ��","bas010104","save(document.forms[0])"));
	}else if ("enterpriseQuery".equals(request.getParameter("menuId"))) {
		if ("2".equals(request.getAttribute("alter"))) {
			buttons.add(new Button("save","�� ��","bas010405","save(document.forms[0])"));
		} else {
			buttons.add(new Button("save","�� ��","bas010404","save(document.forms[0])"));
		}
	}else if ("enterpriseAlter".equals(request.getParameter("menuId"))) {
		buttons.add(new Button("save","�� ��",buttonId,"save(document.forms[0])"));
	}
	
	buttons.add(new Button("reset","�� ��","bas999998","document.forms[0].reset()"));
	buttons.add(new Button("back","�� ��","bas999997","go2Page(\""+mod+"\",\"1\")"));
	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"queryEmployerForm,employerForm\")"));
	pageContext.setAttribute("buttons",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="ά����λ������Ϣ"/>
		<html:form action="/employerAction.do?method=updateEmployer" method="post">
			<lemis:tabletitle title="��λ������Ϣ"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property="aab001"/>
			<tr>
			    <lemis:texteditor property="aab004" label="��λ����" maxlength="100" colspan="3" required="false" disable="false"/>
				<lemis:codelisteditor type="aab006" label="���̵Ǽ�ִ������" required="false" isSelect="true" redisplay="true"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab007" label="���̵Ǽ�ִ�պ���" disable="false" maxlength="20" required="false"/>					
				<!--
				<lemis:texteditor readonly="true" disable="false" label="��λ������" required="true" property="aaa021" style="cursor: hand" styleClass="text"
				onclick="setRegionTree(this,document.all.aaa021,document.all.aab301)"/>
				-->
				 <lemis:texteditor property="ssjqnm" required="false" label="��������" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.aab301)" />
				<html:hidden property="aab301" />
				<lemis:texteditor property="aab013" label="����������" disable="false" maxlength="20"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab022" label="��ҵ����" required="true" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab019" label="��λ����" required="true" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab020" label="��������" required="true" redisplay="true" isSelect="true"/>
		   </tr>
		   <tr>
		         <lemis:texteditor property="aab002" label="��ᱣ�յǼ�֤���" disable="false" maxlength="20" required="false"/>
				<lemis:formateditor mask="date" property="aab036" label="��ᱣ�յǼ�֤��������" required="false" disable="false" format="true"/>
				<html:hidden property="aab054" />
				<lemis:texteditor readonly="true" disable="false" label="��ҵ���" required="false" property="bab055" style="cursor: hand" styleClass="text"
				onclick="setCYLBTree(this,document.all.bab055,document.all.aab054)"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab057" label="��ҵ��λ����" redisplay="true" isSelect="true"/>
				<lemis:formateditor mask="date" property="aab011" label="��׼����" required="false" disable="false" format="true"/>
				<lemis:codelisteditor type="cab054" label="��ҵ����" required="false" redisplay="true" isSelect="true"/>	
		   </tr>
		   <tr>
				<lemis:texteditor property="aab010" label="��׼������λ" disable="false" maxlength="25" required="false"/>	
				<lemis:formateditor mask="date" property="aab008" label="���̵Ǽ�ִ������" required="false" disable="false" format="true"/>				
				<lemis:texteditor property="aab003" label="��֯��������" maxlength="15" required="false" disable="false"/>
			</tr>
		    <tr>
				<lemis:texteditor property="aae006" label="��ַ" required="false" disable="false" maxlength="80"/>
				<lemis:formateditor property="aab009" label="���̵Ǽ���Ч���ޣ��꣩" mask="nnn" required="false" disable="false"/>	
				<lemis:codelisteditor type="aab021" label="������ϵ" required="false" redisplay="true" isSelect="true"/>			
			</tr>
			<tr>
				<lemis:texteditor property="aab030" label="��˰˰��" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab031" label="˰���������" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab032" label="˰���������" disable="false" maxlength="20" required="false"/>	
			</tr>			
			<tr>
				<lemis:formateditor mask="card" property="aab014" label="������������ݺ���" required="false" disable="false"/>
				<lemis:formateditor mask="date" property="aab037" label="˰��֤��׼���ڣ�����" required="false" disable="false" format="true"/>	
				<lemis:texteditor property="aab343" label="һ����λ���" required="false" disable="false" maxlength="8"/>	
			</tr>		
			<tr>
				<lemis:texteditor property="aab046" label="��˰˰��" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab047" label="��˰˰���������" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab012" label="��׼�ĺ�" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab034" label="��ᱣ�վ����������" disable="false" maxlength="8" required="false"/>	
				<lemis:codelisteditor type="aab341" label="��λ������" required="false" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab342" label="��λ����" required="false" isSelect="true" redisplay="true"/>
				
			</tr>	
			<tr>
				<lemis:texteditor property="aab041" label="��λӢ������" maxlength="50" required="false" disable="false"/>			
				<lemis:texteditor property="aab042" label="��λ���" maxlength="10" required="false" disable="false"/>
				<lemis:texteditor property="aab043" label="��λƴ����" maxlength="50"  disable="false" onblur="this.value=this.value.toLowerCase()"/>
			</tr>			
			<tr>
				<lemis:texteditor property="aae005" label="��ϵ�绰" disable="false" maxlength="20" required="false"/>
				<lemis:texteditor property="aae004" label="��ϵ��" disable="false" maxlength="20" required="false"/>
				<lemis:codelisteditor type="aab056" label="��Ա��ģ" redisplay="true" isSelect="true"/>
				
			</tr>			

		
			<tr>
				<lemis:formateditor mask="######" property="aae007" label="��������" disable="false" required="false"/>
				<lemis:texteditor property="aae015" label="��������" disable="false" maxlength="30"/>
				<lemis:texteditor property="aae016" label="��λ��ַ" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae008" label="���д���" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae009" label="���л���" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae010" label="�����ʺ�" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab027" label="֧�����������к�" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab028" label="֧�����л���" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab029" label="֧�����л����ʺ�" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aae013" label="��ע" required="false" disable="false" maxlength="200" colspan="3"/>
				<lemis:texteditor property="aae014" label="����" disable="false" maxlength="15"/>
				
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
<script src="<html:rewrite forward="lemistree"/>"></script>
<script language="javascript">
		//�鿴��λ����������Ϣ
		function viewEmployer (obj){	
			obj.action = '<html:rewrite page="/employerGsAction.do?method=viewEmployer&"/>'+
				'aab001='+obj.aab001.value+'&operation=view&menuId='+'<%=request.getParameter("menuId")%>&'+'url=<%=url%>';
			location.href=obj.action;
		
		}			
		
		//���浥λ������Ϣ
		function save(obj) {
			if (checkValue(obj)) {
				obj.action='<html:rewrite page="/employerAction.do?method=alterEmployer"/>'+'&menuId=<%=request.getParameter("menuId")%>'+'&url=<%=url%>';
				obj.submit();
			}
		}
</script>
