<!-- basicinfo/resumesinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ page import="org.radf.plat.taglib.Button" %>
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
		buttons.add(new Button("addSave","保 存","bas020114","saveData(this.form)"));
	}else if(option.equals("edit")){
		buttons.add(new Button("editSave","保 存","bas020115","saveData(this.form)"));
	}
	buttons.add(new Button("goBack","返 回","bas999997","go2Page(\"basicinfo\",\"2\")"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"queryPersonForm,ResumesForm\")"));
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人简历"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aac002" label="公民身份号码" />
				<lemis:texteditor property="aac003" label="姓名" />
				<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true"/>
			</tr>
			</html:form>
		</table>
		<lemis:tabletitle title="简历信息"/>
		<table class="tableinput">
			<html:form action="/resumesAction.do" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:formateditor mask="date" property="aae030" required="true" format="true" label="起始日期" colspan="3" disable="false"/>
				<lemis:formateditor mask="date" property="aae031" required="false" format="true" label="终止日期" colspan="4" disable="false"/>
			</tr>
			<tr>
				<lemis:texteditor property="aac045" label="毕业学校或工作单位"  required="true" disable="false" maxlength="50" colspan="3"/>
				<lemis:texteditor property="acc0b4" label="任职或工作描述" disable="false" colspan="4" maxlength="30"/>
			</tr>
			<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="5"/>
			<tr>
				<td>经办人</td>
				<td><lemis:operator/></td>
				<td>经办机构</td>
				<td ><lemis:operorg/></td>
				<td>经办日期</td>
				<td><lemis:operdate/></td>
			</tr>
			<html:hidden property="aac001"/>
			<html:hidden property="acc0b0"/>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<INPUT name="today" value="<%=today%>" type="hidden" >
			<lemis:buttons buttonMeta="button"/>
			</html:form>
		</table>		
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		var aae030 = obj.aae030.value;
		var aae031 = obj.aae031.value;
		var today  = obj.today.value;
		if (!(compareDate(today, aae030))) {
			alert("起始日期不能晚于当前日期，请确认！");
			obj.aae030.focus();
			return false;
		}
		if(aae031 != "" ){
			if (!(compareDate(aae031, aae030))) {
				alert("终止日期不能早于起始日期，请确认！");
				obj.aae031.focus();
				return false;
			}
		}
		obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/resumesAction.do?method=save"+"&url1=<%=url1%>"+"&url2=<%=backURL%>"+'&';
		obj.submit();
	}
	function goback(){
		window.location.href="<%=backURL.replaceAll(";amp;","&")%>"+"&url1=<%=url1%>";
	}
</script>
