<!-- basicinfo/society.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String stringData = "";
    QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
    if(null != qi){
    	stringData = qi.getStringData();
    }
	//获取上一步的url, url2为备份在SESSION中的地址
    //当前地址中有url2参数,url2参数会被放到SESSION中,没有就把SESSION清空
	String backURL=request.getParameter("url1");
	String url2=request.getParameter("url2");
	if (backURL==null&&session.getAttribute("url1")!=null){
		backURL=(String)session.getAttribute("url1");
	}else{
		session.setAttribute("url1",backURL);
	}
	if(url2==null){
		session.removeAttribute("url2");
	}else if(session.getAttribute("url2")==null){
		session.setAttribute("url2",url2);
	}

    List header=new ArrayList();
    header.add(new Formatter("aac002","身份证"));
    header.add(new Formatter("aac003","姓名"));
    header.add(new Formatter("acc0c1","关系"));
    header.add(new Formatter("aab004","单位"));
	List buttons=new ArrayList();
	buttons.add(new Button("add","增 加","bas020106","addData(document.all.tableform)"));
	buttons.add(new Button("mod","修 改","bas020107","editData(document.all.tableform)"));
	buttons.add(new Button("del","删 除","bas020108","delData(document.all.tableform)"));
	buttons.add(new Button("goBack","返 回","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"queryPersonForm,ResumesForm\")"));
    Map hidden=new LinkedHashMap();
    hidden.put("acc0c0","社会编号");
    pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("header",header);
    pageContext.setAttribute("button",buttons);

	System.out.println("##url1##"+backURL);
	session.setAttribute("tableheader", "个人社会关系信息表");//表头

%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人社会关系"/>
		<lemis:tabletitle title="个人基本信息"/>
		<table class="tableinput">
			<html:form action="queryPerson.do" method="POST" >
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
					
				</tr>
			</html:form>
		</table>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:table action="querysocietyAction.do" headerMeta="header" topic="个人社会关系信息" hiddenMeta="hidden"  mode="radio" appendBlank="false"/>
		<lemis:buttons buttonMeta="button"/>		
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	function getdata(){
		var str ="aac001="+document.all("aac001").value+"&aac002="
		+document.all("aac002").value+"&aac003="+document.all("aac003").value+"&aac004="+document.all("aac004").value
		+"&aac005="+document.all("aac005").value+"&aac011="+document.all("aac011").value+"&";
		return str;
	}
	function editData(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		var loc = location.href.split('&url1=');//拆分去掉URL参数,避免累加使得地址过长引起按钮无效
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/societyAction.do?method=edit&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata()+
		"&option=edit" +
		"&url1=<%=backURL%>" + 
		"&url2=" + (loc[0]).replace(/&/g,";amp;");
	}
	function addData(obj){
		var loc = location.href.split('&url1=');//拆分去掉URL参数,避免累加使得地址过长引起按钮无效
		window.location.href="/"+lemis.WEB_APP_NAME+"/basicinfo/societyAction.do?method=add&stringData=" +
		document.all("stringData").value + "&"+getdata()+
		"&option=add" +
		"&url1=<%=backURL%>" +
		"&url2=" + (loc[0]).replace(/&/g,";amp;");
	}
	function delData(obj){
		var t = delObj("chk");
		if(!t){
			return t;
		}
	    if(confirm("此操作不能回退，确信要删除您选中的数据吗？")){
	    	var loc = location.href.split('&url1=');//拆分去掉URL参数,避免累加使得地址过长引起按钮无效
			window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/societyAction.do?method=clear&stringData=" +
			document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata()+
			"&url1=<%=backURL%>" + 
			"&url2=" + (loc[0]).replace(/&/g,";amp;");
		}else{
      		return false;
   		}
	}
</script>
