<!-- basicinfo/viewperson.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--������˻�����Ϣ����ǰ�����޸�-->
<lemis:base/>
<%
	String menuId = request.getParameter("menuId");
    String stringData = request.getParameter("stringData");
	//��ȡ��һ����url
	PersonForm fm = (PersonForm)session.getAttribute("OperPersonForm");
	String url2=request.getParameter("url2");
	String url1=request.getParameter("url1");
	if (url2==null||"".equals(url2)) {
		if (fm!=null) {
		  url2=fm.getUrl();
		}
	}
	if (url2==null) {
		url2="";
	}
	if (url1==null) {
		url1="";
	}
	List buttons=new ArrayList();
    if("writeOff".equalsIgnoreCase(menuId)){
		buttons.add(new Button("writeOff","ע ��","bas020403","writeoff()"));
    }else if("query".equalsIgnoreCase(menuId)) {
		buttons.add(new Button("mod", "�� ��", "bas020303","editData()"));
    }
	buttons.add(new Button("goBack","�� ��","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"PersonForm,OperPersonForm,QueryPersonForm\")"));
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:body>
		<lemis:title title="�鿴���˻�����Ϣ"/>
		<html:form action="/operatePerson.do" onsubmit="return checkValue(this)" method="POST" >
			<lemis:tabletitle title="���˻�����Ϣ"/>
			<table class="tableinput" >
				<lemis:editorlayout/>				
				<tr>
					<html:hidden property="aac001"/>
					<lemis:formateditor mask="card" property="aac002" label="������ݺ���" required="false" disable="true"/>
					<lemis:texteditor property="aac003" label="����" required="false" disable="true" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="false" label="�Ա�" redisplay="false" required="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="����"  redisplay="true" />
					<lemis:formateditor mask="date" property="aac006" required="fasle" disable="true" format="true" label="��������"/>
					<lemis:codelisteditor type="aac009" label="��������" isSelect="false" redisplay="false" required="false"/>
				</tr>
				<tr>
					
					<lemis:codelisteditor type="bac298" label="��Ա���" redisplay="false" required="false" isSelect="false"/>
					<lemis:texteditor property="ssjqnm" required="false" label="�����ֵ�" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:texteditor  property="aac010" required="false" label="�������ڵ���" disable="true" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)"/>
					<html:hidden property="aab301"/>
				</tr>
			<tr>
					<lemis:codelisteditor type="aac024" isSelect="false" label="������ò"  redisplay="true"/>
					<lemis:formateditor property="aac034" label='���(CM)' disable="true" mask="nnn.n" required="false"/>
					<lemis:formateditor property="aac035" label='����(KG)' disable="true" mask="nnn.nn" required="false"/>
				</tr>
				<tr>
					<lemis:formateditor property="aac036" label="����" disable="true" mask="n.n" required="false"/>
					<lemis:codelisteditor type="aac032" isSelect="false" redisplay="true" label="Ѫ��"/>
					<lemis:codelisteditor type="aac033" isSelect="false" label="����״��" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac017" label="����״��" isSelect="false" redisplay="true" required="false"/>
					<lemis:formateditor mask="date" property="aac007" required="false" label="�μӹ�������" disable="true" format="true"/>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" redisplay="true" required="false" isSelect="false" />	
				</tr>
				<tr>
					<lemis:formateditor property="aae007" label="��������" required="false" mask="######"  disable="true"/>
					<lemis:texteditor property="aae005" label=" ��ϵ�绰" disable="true" maxlength="20"/>
					<lemis:texteditor property="aae015" label="���˵�������" disable="true" maxlength="50"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac015" label="רҵ�����ȼ�" redisplay="true" isSelect="false"/>
					<lemis:texteditor property="acc02i"  label="����ְҵ�ʸ�֤�����" disable="true" maxlength="20"/>
					<lemis:codelisteditor type="aac014" label="רҵ����ְ��" redisplay="true" isSelect="false"/>
				</tr>
				<tr>		
					<lemis:texteditor property="aac025" label="������" required="false" disable="true" maxlength="100"/>
				<lemis:texteditor property="aae006" label="��ַ" disable="true" maxlength="50" colspan="3"/>
				
				</tr>
				<tr>
					<lemis:texteditor property="aae013" label="��ע" disable="true" maxlength="100" colspan="3"/>
					<lemis:codelisteditor type="bac297" label="�Ƿ���֤" redisplay="true" isSelect="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aae011" label="������" isSelect="false" />
					<lemis:codelisteditor type="aae017" label="�������" isSelect="false" />
					<lemis:texteditor property="aae036" label="��������" disable="true" />
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
	function editData(){
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=query&buttonId=bas020304&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.OperPersonForm)+
		"&url2=" + (location.href+"&"+getAlldata(document.all.OperPersonForm)).replace(/&/g,";amp;")+"&buttonId=bas020305";
	}
	// ��Աע��
	function writeoff(){
		if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�е���")){
			window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/personOperAction.do?method=writeOff&menuId=writeOff&aac001="+document.all("aac001").value+"&stringData=" +
			document.all("stringData").value +
			"&" + getAlldata(document.all.OperPersonForm)+"&url2=<%=url2%>"
			+'&' ;
		}else{
      		return false;
   		}
	}
	// ����
	function toBack(){
		window.location.href = "/"+lemis.WEB_APP_NAME+"/basicinfo/operatePerson.do?method=back&stringData=" +
		document.all("stringData").value + "&";
	}
</script>
