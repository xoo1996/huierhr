<!-- basicinfo/societyinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String option = request.getParameter("option");
	if(option==null){
		option="";
	}
	String stringData = request.getParameter("stringData");

	//获取上一步的url, url2为备份在SESSION中的地址
    //当前SESSION中没有url2就把当前地址中的url2参数放到SESSION中
    //当前SESSION中已有url2就将返回地址设为url2
	String url1=request.getParameter("url1");
	String backURL=request.getParameter("url2");
	if (backURL==null){backURL="";}
	if (url1==null){url1="";}
	if(session.getAttribute("url2")==null){
		session.setAttribute("url2",backURL);
	}else{
		backURL=(String)session.getAttribute("url2");
		session.removeAttribute("url2");
	}
	
	String today = DateUtil.getDate().toString();
	List buttons=new ArrayList();
	if(option.equals("add")){
		buttons.add(new Button("addSave","保 存","bas020109","saveData(document.all.SocietyForm)"));
	}else if(option.equals("edit")){
		buttons.add(new Button("editSave","保 存","bas020110","saveData(document.all.SocietyForm)"));
	}

	buttons.add(new Button("goBacka","返 回","bas999997","go2Page(\"basicinfo\",\"2\")"));

	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"queryPersonForm,SocietyForm\")"));
    pageContext.setAttribute("button",buttons);

	System.out.println("##url1oooooooooo##"+url1);
	System.out.println("##url2ooooooooosession##"+session.getAttribute("url2"));
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人社会关系"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac002" label="公民身份号码" />
				<lemis:texteditor property="aac003" label="姓名" />
				<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aac005" label="民族" isSelect="false" redisplay="true"/>
				<lemis:codelisteditor type="aac011" label="文化程度" isSelect="false" redisplay="true"/>
				
			</tr>
		</table>
		</html:form>
		<lemis:tabletitle title="社会关系信息"/>
		<table class="tableinput">
			<html:form action="/societyAction.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="true" disable="false"/>
				<lemis:texteditor property="aac003" label="姓名" disable="false" colspan="6" maxlength="16" required="true"/>
			</tr>
			<tr>
				<lemis:texteditor property="acc0c1" label="社会关系" disable="false" maxlength="16" required="true"/>
				<lemis:texteditor property="aab004" label="工作单位"  required="false" disable="false" colspan="6" maxlength="64"/>
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator/></td>
				<td>经办机构</td>
				<td ><lemis:operorg/></td>
				<td>经办日期</td>
				<td><lemis:operdate/></td>
			</tr>
			<html:hidden property="aac001"/>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<INPUT name="today" value="<%=today%>" type="hidden" >
			<lemis:buttons buttonMeta="button"/>
			</html:form>
		</table>		
		<lemis:errors/>
		<lemis:bottom/></lemis:body>
</html>
<script language="javascript">
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/societyAction.do?method=save"+"&url1=<%=url1%>"+"&url2=<%=backURL%>"+'&';
		obj.submit();
	}
	function goback(){
		window.location.href="<%=backURL.replaceAll(";amp;","&")%>"+"&url1=<%=url1%>";
	}
</script>
