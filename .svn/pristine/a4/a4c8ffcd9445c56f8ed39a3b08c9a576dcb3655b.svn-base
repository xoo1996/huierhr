<!-- /basicinfo/alterperinfo.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//修改个人信息（限制部分数据项）
	String stringData = request.getParameter("stringData");
	String buttonId = request.getParameter("buttonId");
	String menuId = "modify";
	if(buttonId==null){
		buttonId="";
	}
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
	buttons.add(new Button("save","保 存",buttonId,"saveData()"));
	buttons.add(new Button("reset","重 置","bas999998","document.forms[0].reset();"));
	buttons.add(new Button("goBack","返 回","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"OperPersonForm\")"));
    pageContext.setAttribute("button",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="个人基本信息维护"/>
		<html:form action="/operatePerson.do" onsubmit="return checkValue(this)">
			<lemis:tabletitle title="个人基本信息"/>
			<table class="tableinput" >
			<lemis:editorlayout/>
				<tr>
					<html:hidden property="aac001"/>
					<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="false" disable="true"/>
					<lemis:texteditor property="aac003" label="姓名" required="false" disable="true" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="false" label="性别" redisplay="false" required="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="民族"  redisplay="true" />
					<lemis:formateditor mask="date" property="aac006" required="fasle" disable="true" format="true" label="出生日期"/>
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="false" redisplay="false" required="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bac298" label="人员类别" redisplay="true" required="false" isSelect="false"/>
					<lemis:texteditor property="ssjqnm" required="true" label="人员辖地" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:texteditor  property="aac010" required="false" label="户口所在地区" disable="false" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)"/>
					<html:hidden property="aab301"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac024" isSelect="true" label="政治面貌"  redisplay="true"/>
					<lemis:texteditor property="acc025"  label="劳动手册号" disable="false"  maxlength="18"/>
					<lemis:formateditor property="aac034" label='身高(CM)' disable="false" mask="nnn.n" required="false"/>
				</tr>
				<tr>
					<lemis:formateditor property="aac035" label='体重(KG)' disable="false" mask="nnn.nn" required="false"/>
					<lemis:formateditor property="aac036" label="视力" disable="false" mask="n.n" required="false"/>
					<lemis:codelisteditor type="aac032" isSelect="true" redisplay="true" label="血型"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac033" isSelect="true" label="健康状况" redisplay="true"/>
					<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="true" redisplay="true" required="false"/>
					<lemis:formateditor mask="date" property="aac007" required="false" label="参加工作日期" disable="false" format="true"/>
					
				</tr>
				<tr>
					<lemis:codelisteditor type="aac011" label="文化程度" redisplay="true" required="false"/>
					<lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######"  disable="false"/>
					<lemis:texteditor property="aae005" label=" 联系电话" disable="false" maxlength="20"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae015" label="个人电子信箱" disable="false" maxlength="50"/>
					<lemis:codelisteditor type="aac015" label="专业技术等级" redisplay="true"/>
					<lemis:texteditor property="acc02i"  label="国家职业资格证书号码" disable="false" maxlength="20"/>
				</tr>
				<tr>		
					<lemis:codelisteditor type="aac014" label="专业技术职务" redisplay="true"/>
					<lemis:texteditor property="aac025" label="出生地" required="false" disable="false" maxlength="100"/>
				<lemis:texteditor property="aae006" label="地址" disable="false" maxlength="50" colspan="3"/>
				</tr>
				<tr>
					<lemis:texteditor property="aac022" label="个人拼音码" required="false" disable="false" maxlength="100" />
					<lemis:codelisteditor type="bac297" label="是否验证" redisplay="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="5" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
					<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" redisplay="true"/>
					<lemis:formateditor mask="date" label="经办日期" property="aae036" required="false" disable="true"/>
				</tr>
			</table>
	</html:form>
	<html:hidden property="stringData" value="<%=stringData%>"/>
	<lemis:buttons buttonMeta="button"/>
	<lemis:errors/>
	<lemis:bottom/></lemis:body>
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
	function saveData(){
		var obj = document.all.OperPersonForm;
		var t  = checkValue(obj);
		if(!t){
			return t;
		}
		obj.action="/"+lemis.WEB_APP_NAME+"/basicinfo/personOperAction.do?method=savePerson&menuId=<%=menuId%>";
		if("<%=buttonId%>"=="bas020306"){
			obj.action=obj.action+"&url2=" +
			(location.href+"&menuId=<%=menuId%>&aac001="+document.all("aac001").value).replace(/&/g,";amp;");
		}else{
			obj.action=obj.action+"&url2=<%=url%>";
		}
		obj.submit();
	}
	

</script>
