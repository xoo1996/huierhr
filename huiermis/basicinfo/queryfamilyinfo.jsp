<!-- basicinfo/queryfamilyinfo.jsp -->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String stringData = "";
	QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
	if(null != qi){
		stringData = qi.getStringData();
	}
 	//��ȡ��һ����url
	String url=request.getParameter("url2");
	if (url==null||"".equals(url)) {
			url="";
	}
	if (url==null) {
		url="";
	}
	List header=new ArrayList();
	header.add(new Formatter("aac002","���֤����"));
    header.add(new Formatter("aac003","����"));
    header.add(new Formatter("acc0c1","��ϵ"));
    header.add(new Formatter("aab004","��λ����"));
    header.add(new Formatter("aae036","��������"));
    Map buttons=new LinkedHashMap();
    buttons.put("�� ��","addData()");
    buttons.put("�� ��","editData()");
    buttons.put("ɾ ��","delData()");
    buttons.put("�� ��","closeWindow(\"queryPersonForm,PersonFamilyForm\")");
    Map hidden=new LinkedHashMap();
    hidden.put("aac001","���˱��");				
    hidden.put("acc0c0","��ϵ���");
    pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("header",header);
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="����ϵ"/>
		<lemis:tabletitle title="���˻�����Ϣ"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST">
				<lemis:editorlayout/>
				<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="������ݺ���"/>
					<lemis:texteditor property="aac003" label="����"/>
					<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="false" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" label="����" isSelect="false" redisplay="true"/>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="aac021" label="ʧҵ֤����" />
				</tr>
				<html:hidden property="stringData" value="<%=stringData%>"/>
			</html:form>
		</table>
		<lemis:table action="/queryFamily.do" headerMeta="header" topic="��������ϵ�б�" hiddenMeta="hidden" mode="radio" appendBlank="false"/>
		<lemis:buttons buttonMeta="button"/>
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
    function getaac001(){
     	return document.forms(0).aac001.value;
    }
	function addData(){
		window.location.href="/"+lemis.WEB_APP_NAME+"/basicinfo/queryFamily.do?method=addFamily&stringData="+document.all("stringData").value + "&"+getAlldata(document.all.tableform)+"&aac001="+getaac001();
	}
	function editData(){
		var t = editObj("chk");
		if(!t){
		return t;
		}
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/queryFamily.do?method=editFamily&stringData=" +document.all("stringData").value + "&" + getAlldata(document.all.tableform);
	}
	function delData(){
		var t = delObj("chk");
		if(!t){
		return t;
		}
		if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�еļ�¼��")){
			window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/queryFamily.do?method=clearFamily&stringData=" +document.all("stringData").value + "&" + getAlldata(document.all.tableform);
		}else{
			return false;
		}
	}
	function toBack(obj){
		obj.action="/"+lemis.WEB_APP_NAME+"/queryFamily.do?method=back"+"&";
		obj.submit();
	}
</script>
