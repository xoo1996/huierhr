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
 	//获取上一步的url
	String url=request.getParameter("url2");
	if (url==null||"".equals(url)) {
			url="";
	}
	if (url==null) {
		url="";
	}
	List header=new ArrayList();
	header.add(new Formatter("aac002","身份证号码"));
    header.add(new Formatter("aac003","姓名"));
    header.add(new Formatter("acc0c1","关系"));
    header.add(new Formatter("aab004","单位名称"));
    header.add(new Formatter("aae036","经办日期"));
    Map buttons=new LinkedHashMap();
    buttons.put("增 加","addData()");
    buttons.put("修 改","editData()");
    buttons.put("删 除","delData()");
    buttons.put("关 闭","closeWindow(\"queryPersonForm,PersonFamilyForm\")");
    Map hidden=new LinkedHashMap();
    hidden.put("aac001","个人编号");				
    hidden.put("acc0c0","关系编号");
    pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("header",header);
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="社会关系"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="/queryPerson.do" method="POST">
				<lemis:editorlayout/>
				<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="公民身份号码"/>
					<lemis:texteditor property="aac003" label="姓名"/>
					<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" label="民族" isSelect="false" redisplay="true"/>
					<lemis:codelisteditor type="aac011" label="文化程度" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="aac021" label="失业证号码" />
				</tr>
				<html:hidden property="stringData" value="<%=stringData%>"/>
			</html:form>
		</table>
		<lemis:table action="/queryFamily.do" headerMeta="header" topic="个人社会关系列表" hiddenMeta="hidden" mode="radio" appendBlank="false"/>
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
		if(confirm("此操作不能回退，确信要删除您选中的记录吗？")){
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
