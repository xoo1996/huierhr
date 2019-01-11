<!-- basicinfo/personinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--浏览个人基本信息，当前不能修改-->
<%
    String stringData = request.getParameter("stringData");
	//获取上一步的url
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
	buttons.add(new Button("mod","变 更","bas020203","modi()"));
	buttons.add(new Button("goBack","返 回","bas999997","toBack()"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"PersonForm,OperPersonForm,OueryPersonForm\")"));

    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="查看个人基本信息"/>
		<html:form action="/operatePerson.do" onsubmit="return checkValue(this)" method="POST" >
			<lemis:tabletitle title="个人基本信息"/>
			<table class="tableinput" >
				<lemis:editorlayout/>				
				<tr>
					<html:hidden property="aac001"/>
					<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="false" disable="true"/>
					<lemis:texteditor property="aac003" label="姓名" required="false" disable="true"/>
					<lemis:codelisteditor type="aac004" isSelect="false" label="性别" redisplay="true" required="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="民族"  redisplay="true"/>
					<lemis:formateditor mask="date" property="aac006" required="false" disable="true" format="true" label="出生日期"/>
					<lemis:texteditor property="aac025" label="出生地" required="false" disable="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac024" isSelect="false" label="政治面貌"  redisplay="true"/>
					<lemis:formateditor property="aac034" label='身高(CM)' disable="true" mask="nnnn.n" required="false"/>
					<lemis:formateditor property="aac035" label='体重(KG)' disable="true" mask="nnnnn.nn" required="false"/>
				</tr>
				<tr>
					<lemis:formateditor property="aac036" label="视力" disable="true" mask="n.n" required="false"/>
					<lemis:codelisteditor type="aac032" isSelect="false" redisplay="true" label="血型"/>
					<lemis:codelisteditor type="aac033" isSelect="false" label="健康状况" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="ssjqnm" required="false" label="所属街道" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:texteditor property="aac010" label="户口所在区街" disable="true"/>
				</tr>
				
				<tr>
					<lemis:formateditor mask="date" property="aac007" required="false" label="参加工作日期" disable="true"/>
					<lemis:codelisteditor type="aac011" label="文化程度" redisplay="true" isSelect="false"/>
					<lemis:codelisteditor type="bac298" isSelect="false" label="人员类别" redisplay="true"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="地址" disable="true" />
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="false" redisplay="true"/>
					<lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae005" label=" 联系电话" disable="true"/>
					<lemis:texteditor property="aae015" label="个人电子信箱" disable="true"/>
					<lemis:texteditor property="aac021" disable="true" label="失业证号码"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac015" label="专业技术等级" isSelect="false" redisplay="true"/>
					<lemis:texteditor property="acc02i"  label="国家职业资格证书号码" disable="true"/>
					<lemis:codelisteditor type="aac014" isSelect="false" label="专业技术职务" redisplay="true"/>
				</tr>
				<tr>
					<lemis:texteditor property="aac022" label="个人拼音码" disable="true" maxlength="100" />
					<lemis:texteditor property="aae013" label="备注" disable="true" maxlength="100" />
					<lemis:codelisteditor type="bac297" label="是否验证" redisplay="true" isSelect="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
					<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" redisplay="true"/>
					<lemis:formateditor mask="date" label="经办日期" property="aae036" required="false" disable="true"/>
				</tr>
			</table>
			<html:hidden property="stringData" value="<%=stringData%>"/>
			<lemis:buttons buttonMeta="button"/>
		</html:form>
		<!--信息列表（只读）-->	
		<lemis:errors/>
		<lemis:bottom/>
		</lemis:body>
</html>
<script language="javascript">
	// 人员基本信息修改
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

	// 返回
	function toBack(){
		window.location.href="<%=url.replaceAll(";amp;","&")%>"+"&menuId=alter";
	}
</script>
