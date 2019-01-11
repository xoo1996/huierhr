<!-- basicinfo/skillinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  String stringData = request.getParameter("stringData");
  String method=request.getParameter("method");
  String  todayStr = DateUtil.getDate().toString(); //当前时间
  String title="";

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
  
  List buttons=new ArrayList();
	if("add".equals(method)){
		title="个人技能新增";
		buttons.add(new Button("editSave","保 存","bas020119","saveData(this.form)"));
	}else if("edit".equals(method)){
		title="个人技能维护";
		buttons.add(new Button("editSave","保 存","bas020120","saveData(this.form)"));
	}

  buttons.add(new Button("goBack","返 回","bas999997","go2Page(\"basicinfo\",\"2\")"));
  buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"operPersonForm,SkillForm\")"));
  pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:errors/>
	<lemis:body>	
		<lemis:title title="<%=title%>"/>
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
			</html:form>
		</table>
		<lemis:tabletitle title="技能信息"/>
		<table class="tableinput">
			<html:form action="/skillAction.do" onsubmit="return checkValue(this)" method="POST" >
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="acc0d1" label="技能或特长" required="true" disable="false" maxlength="100"/>
				<lemis:texteditor property="acc0d5" label="证书编号" required="false" disable="false" maxlength="30"/>
				<lemis:formateditor mask="date" property="acc041" required="false" label="获取证书日期" disable="false"/>
			</tr>
			<tr>
				<lemis:texteditor property="aca112" label="从业工种" disable="false" style="cursor: hand" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)"/>
				<html:hidden property="aca111"/>
				<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="3"/>
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator/></td>
				<td>经办机构</td>
				<td ><lemis:operorg/></td>
				<td>经办日期</td>
				<td ><lemis:operdate/></td>
			</tr>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<input name="todayStr" value="<%=todayStr%>" type="hidden"/>
			<html:hidden property="acc0d0"/>
			<lemis:buttons buttonMeta="button"/>
			</html:form>
		</table>	
	<lemis:bottom/>
	</lemis:body>
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		var acc041 = obj.acc041.value;
		var todayStr = obj.todayStr.value;
		if (!(compareDate(todayStr, acc041))) {
			alert("获取证书日期 不能晚于 当前日期，请确认！");
			obj.acc041.focus();
			return false;
		}
		obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/skillAction.do?method=save"+"&url1=<%=url1%>"+"&url2=<%=backURL%>"+'&';
		obj.submit();
	}
	function goback(){
		window.location.href="<%=backURL.replaceAll(";amp;","&")%>"+"&url1=<%=url1%>";
	}
</script>
