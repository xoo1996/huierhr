<!-- basicinfo/personinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--������˻�����Ϣ����ǰ�����޸�-->
<%
    String stringData = request.getParameter("stringData");
	//��ȡ��һ����url
	PersonForm fm = (PersonForm)session.getAttribute("OperPersonForm");
	String url=request.getParameter("url2");
	if (url==null||"".equals(url)) {
		if (fm!=null) {
		  url=fm.getUrl();
		}
	}
	if (url==null) {
		url="";
	}

	List buttons=new ArrayList();
	buttons.add(new Button("mod","�� ��","bas020203","modi()"));
	buttons.add(new Button("goBack","�� ��","bas999997","toBack()"));
	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"PersonForm,OperPersonForm,OueryPersonForm\")"));

    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="�鿴���˻�����Ϣ"/>
		<html:form action="/operatePerson.do" onsubmit="return checkValue(this)" method="POST" >
			<lemis:tabletitle title="���˻�����Ϣ"/>
			<table class="tableinput" >
				<lemis:editorlayout/>				
				<tr>
					<html:hidden property="aac001"/>
					<lemis:formateditor mask="card" property="aac002" label="������ݺ���" required="false" disable="true"/>
					<lemis:texteditor property="aac003" label="����" required="false" disable="true"/>
					<lemis:codelisteditor type="aac004" isSelect="false" label="�Ա�" redisplay="true" required="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="����"  redisplay="true"/>
					<lemis:formateditor mask="date" property="aac006" required="false" disable="true" format="true" label="��������"/>
					<lemis:texteditor property="aac025" label="������" required="false" disable="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac024" isSelect="false" label="������ò"  redisplay="true"/>
					<lemis:formateditor property="aac034" label='���(CM)' disable="true" mask="nnnn.n" required="false"/>
					<lemis:formateditor property="aac035" label='����(KG)' disable="true" mask="nnnnn.nn" required="false"/>
				</tr>
				<tr>
					<lemis:formateditor property="aac036" label="����" disable="true" mask="n.n" required="false"/>
					<lemis:codelisteditor type="aac032" isSelect="false" redisplay="true" label="Ѫ��"/>
					<lemis:codelisteditor type="aac033" isSelect="false" label="����״��" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac017" label="����״��" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="ssjqnm" required="false" label="�����ֵ�" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:texteditor property="aac010" label="������������" disable="true"/>
				</tr>
				
				<tr>
					<lemis:formateditor mask="date" property="aac007" required="false" label="�μӹ�������" disable="true"/>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" redisplay="true" isSelect="false"/>
					<lemis:codelisteditor type="bac298" isSelect="false" label="��Ա���" redisplay="true"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="��ַ" disable="true" />
					<lemis:codelisteditor type="aac009" label="��������" isSelect="false" redisplay="true"/>
					<lemis:formateditor property="aae007" label="��������" required="false" mask="######"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae005" label=" ��ϵ�绰" disable="true"/>
					<lemis:texteditor property="aae015" label="���˵�������" disable="true"/>
					<lemis:texteditor property="aac021" disable="true" label="ʧҵ֤����"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac015" label="רҵ�����ȼ�" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="acc02i"  label="����ְҵ�ʸ�֤�����" disable="true"/>
					<lemis:codelisteditor type="aac014" isSelect="false" label="רҵ����ְ��" redisplay="true"/>
				</tr>
				<tr>
					<lemis:texteditor property="aac022" label="����ƴ����" disable="true" maxlength="100" />
					<lemis:texteditor property="aae013" label="��ע" disable="true" maxlength="100" />
					<lemis:codelisteditor type="bac297" label="�Ƿ���֤" redisplay="true" isSelect="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aae011" label="������" isSelect="false" />
					<lemis:codelisteditor type="aae017" label="�������" isSelect="false" redisplay="true"/>
					<lemis:formateditor mask="date" label="��������" property="aae036" required="false" disable="true"/>
				</tr>
			</table>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<lemis:buttons buttonMeta="button"/>
		</html:form>
		<!--��Ϣ�б�ֻ����-->	
		<lemis:errors/>
		<lemis:bottom/>
		</lemis:body>
</html>
<script language="javascript">
	// ��Ա������Ϣ�޸�
	function modi(){
		var obj = document.all.OperPersonForm;
		var t  = checkValue(obj);
		if(!t){
			return t;
		}
		obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/operatePerson.do?method=add&menuId=alter"+
		"&url2="+(location.href+"&menuId=alter").replace(/&/g,";amp;")+
		"&buttonId=bas020204"+
		"&";
		obj.submit();
	}

	// ����
	function toBack(){
		window.location.href="<%=url.replaceAll(";amp;","&")%>"+"&menuId=alter";
	}
</script>
