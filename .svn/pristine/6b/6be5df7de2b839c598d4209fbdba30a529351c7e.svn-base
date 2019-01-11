<!-- basicinfo/newEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ page import="org.radf.apps.basicinfo.form.EmployerForm" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="javascript">
	<%
		String  todayStr = DateUtil.getDate().toString(); //当前时间
		String url=request.getParameter("url");
		if (url==null) {
			url="";
		}
		Map buttons = new LinkedHashMap();
		if ("1".equals(request.getAttribute("alter"))) {
			buttons.put("保存","alterEmployer(document.forms[0])");
	%>
			// 变更单位基本信息
			function alterEmployer(obj){
				if (checkValue(obj)) {
					obj.action = '<html:rewrite page="/employerAction.do?method=updateEmployer&menuId=enterpriseAlter"/>'
					+ "&url=<%=url%>";
					obj.submit();
				}
			}			
	<%
	   }
	   else {
			buttons.put("保存","addEmployer(document.forms[0])");
			
	%>
			// 新增单位基本信息
			function addEmployer(obj){
				if (!checkValue(obj)) {
					return false;
				}
				obj.action = '<html:rewrite page="/employerAction.do?method=addEmployer&menuId=enterpriseReg"/>'
				+ "&url=<%=url%>";
				obj.submit();
			}
	<%
	   }
	   buttons.put("重置","document.forms[0].reset()");
	   if (!"".equals(url)) {
	     buttons.put("返回","javascript:location.href=\""+url.replaceAll(";amp;","&")+"\"");
	   }//javascript:location.href=\""+url.replaceAll(";amp;","&")+"\"
	   buttons.put("关 闭","closeWindow(\"queryEmployerForm,employerForm\")");
	   pageContext.setAttribute("buttons",buttons);
	   EmployerForm form = new EmployerForm();
		if(request.getSession().getAttribute("employerForm") != null)
			form = (EmployerForm)request.getSession().getAttribute("employerForm");

		System.out.println("经办人"+form.getAae011());
		if(form.getAab057() == null) form.setAab057(""); // 事业单位类型
		if(form.getAab021() == null) form.setAab021(""); // 隶属关系
		if(form.getAab020() == null) form.setAab020(""); // 经济类型
		if(form.getAab056() == null) form.setAab056(""); // 人员规模
		if(form.getAab013() == null) form.setAab013(""); // 法定代表人
		if(form.getAab014() == null) form.setAab014(""); // 法定代表公民身份号码
		if(form.getAae004() == null) form.setAae004(""); // 联系人
		//因指标体系调整,此处删除一行 登记原因
		if(form.getAab022() == null) form.setAab022(""); //行业代码
		if(form.getAab002() == null) form.setAab002(""); //社会保险登记证编号
		if(form.getAab036() == null) form.setAab036(""); //社会保险登记证发放日期
		if(form.getAae005() == null) form.setAae005(""); // 联系电话
		if(form.getAae014() == null) form.setAae014(""); // 传真
		if(form.getAab301() == null) form.setAab301(""); // 所在地行政区划代码
		if(form.getAaa021() == null) form.setAaa021(""); // 单位所在区
		if(form.getAae006() == null) form.setAae006(""); // 地址
		if(form.getAae007() == null) form.setAae007(""); // 邮政编码
		if(form.getAae015() == null) form.setAae015(""); // 个人电子信箱
		if(form.getAae016() == null) form.setAae016(""); // 单位网址
		if(form.getAab003() == null) form.setAab003("");  // 组织机构号码
		if(form.getAab004() == null) form.setAab004("");  // 单位名称
		if(form.getAab019() == null) form.setAab019("");  // 单位类型
%>
			function toBack(){
				window.location.href = "/queryEmployerAction.do?method=back&stringData=" +
				document.all("stringData").value + "&";
			}
</script>		
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="单位基本信息"/>
		<html:form action="/employerAction.do?method=addEmployer&menuId=enterpriseReg" method="post">
			<lemis:tabletitle title="单位基本信息"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<input name="todayStr" value="<%=todayStr%>" type="hidden"/>
			<html:hidden property="aab001"/>
			<html:hidden property="aae119"/>
			<html:hidden property="aab043"/>
			<tr>
				<lemis:texteditor property="aab003" label="组织机构代码" maxlength="15" required="false" disable="false"/>
				<lemis:texteditor property="aab004" label="单位名称" maxlength="100" colspan="3" required="true" disable="false"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab041" label="单位英文名称" maxlength="50" required="false" disable="false"/>			
				<lemis:texteditor property="aab042" label="单位简称" maxlength="10" required="false" disable="false"/>
				<lemis:texteditor property="aab043" label="单位拼音码" maxlength="50" colspan="3" required="true" disable="false" onblur="this.value=this.value.toLowerCase()"/>
			</tr>			
			<tr>
				<lemis:codelisteditor type="aab019" label="单位类型" required="true" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab057" label="事业单位类型" redisplay="true" isSelect="true"/>
				<lemis:codelisteditor type="aab020" label="经济类型" required="true" redisplay="true" isSelect="true"/>
				
				
			</tr>
			<tr>
				<lemis:texteditor property="aae005" label="联系电话" disable="false" maxlength="20" required="false"/>
				<lemis:texteditor property="aae004" label="联系人" disable="false" maxlength="20" required="false"/>
				<lemis:codelisteditor type="aab056" label="人员规模" redisplay="true" isSelect="true"/>
				
			</tr>
			<tr>
				<lemis:texteditor property="aab013" label="法定代表人" disable="false" maxlength="20"/>
				<lemis:formateditor mask="card" property="aab014" label="法定代表公民身份号码" required="false" disable="false"/>
				<lemis:texteditor property="aab022" label="行业代码" required="false" disable="false"/>
				
				<!--
				//因指标体系调整,此处删去一行
				-->
				
			</tr>
			<tr>
			    <lemis:texteditor readonly="true" disable="false" label="产业类别" required="false" property="bab055" style="cursor: hand" styleClass="text"
				onclick="setCYLBTree(this,document.all.bab055,document.all.aab054)"/><lemis:texteditor property="aab002" label="社会保险登记证编号" disable="false" maxlength="20" required="true"/>
				<lemis:formateditor mask="date" property="aab036" label="社会保险登记证发放日期" required="false" disable="false"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aab021" label="隶属关系" required="false" redisplay="true" isSelect="true"/>
				<lemis:texteditor property="aae014" label="传真" disable="false" maxlength="15"/>
				<lemis:texteditor readonly="true" disable="false" label="单位所在区" required="true" property="aaa021" style="cursor: hand" styleClass="text"
				onclick="setRegionTree(this,document.all.aaa021,document.all.aab301)"/>
				<html:hidden property="aab301"/>
				
			</tr>
			<tr>
				<lemis:texteditor property="aae006" label="地址" required="false" disable="false" maxlength="80" colspan="5"/>
			</tr>
			<tr>
				<lemis:formateditor mask="######" property="aae007" label="邮政编码" disable="false" required="false"/>
				<lemis:texteditor property="aae015" label="电子邮箱" disable="false" maxlength="30"/>
				<lemis:texteditor property="aae016" label="单位网址" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td> 
			</tr>
			<input name="aab057t" type="hidden" value="<%=form.getAab057()%>"/>
			<input name="aab022t" type="hidden" value="<%=form.getAab022()%>"/>
			<input name="aab054t" type="hidden" value="<%=form.getAab054()%>"/>
			<input name="aab021t" type="hidden" value="<%=form.getAab021()%>"/>
			<input name="aab020t" type="hidden" value="<%=form.getAab020()%>"/>
			<input name="aab056t" type="hidden" value="<%=form.getAab056()%>"/>
			<input name="aab013t" type="hidden" value="<%=form.getAab013()%>"/>
			<input name="aab014t" type="hidden" value="<%=form.getAab014()%>"/>
			<%//因指标体系调整,此处删除一行 登记原因%>
			<input name="aae004t" type="hidden" value="<%=form.getAae004()%>"/>
			<input name="aae005t" type="hidden" value="<%=form.getAae005()%>"/>
			<input name="aae014t" type="hidden" value="<%=form.getAae014()%>"/>
			<input name="aab301t" type="hidden" value="<%=form.getAab301()%>"/>
			<input name="aaa021t" type="hidden" value="<%=form.getAaa021()%>"/>
			<input name="aae006t" type="hidden" value="<%=form.getAae006()%>"/>
			<input name="aae007t" type="hidden" value="<%=form.getAae007()%>"/>
			<input name="aae015t" type="hidden" value="<%=form.getAae015()%>"/>
			<input name="aae016t" type="hidden" value="<%=form.getAae016()%>"/>
			<input name="aab002t" type="hidden" value="<%=form.getAab002()%>"/>
			<input name="aab036t" type="hidden" value="<%=form.getAab036()%>"/>
			<input name="aab003t" type="hidden" value="<%=form.getAab003()%>"/>
			<input name="aab004t" type="hidden" value="<%=form.getAab004()%>"/>
			<input name="aab019t" type="hidden" value="<%=form.getAab019()%>"/>
		</html:form>
		</table>
	<lemis:buttons buttonMeta="buttons"/>
	<lemis:bottom/>
	</lemis:body>
</html>
<script src="<html:rewrite forward="lemistree"/>"></script>
<script language="javascript">
	function resetForm(obj) {
	obj.aab057.value = obj.aab057t.value;
	obj.aab022.value = obj.aab022t.value;
	obj.aab054.value = obj.aab054t.value;
	obj.aab021.value = obj.aab021t.value;
	obj.aab020.value = obj.aab020t.value;
	obj.aab056.value = obj.aab056t.value;
	obj.aab013.value = obj.aab013t.value;
	obj.aab014.value = obj.aab014t.value;
	//因指标体系调整,此处删去一行
	obj.aae004.value = obj.aae004t.value;
	obj.aae005.value = obj.aae005t.value;
	obj.aae014.value = obj.aae014t.value;
	obj.aab301.value = obj.aab301t.value;
	obj.aaa021.value = obj.aaa021t.value;
	obj.aae006.value = obj.aae006t.value;
	obj.aae007.value = obj.aae007t.value;
	obj.aae015.value = obj.aae015t.value;
	obj.aae016.value = obj.aae016t.value;
	obj.aab002.value = obj.aab002t.value;
	obj.aab036.value = obj.aab036t.value;
	obj.aab003.value = obj.aab003t.value;
	obj.aab004.value = obj.aab004t.value;
	obj.aab019.value = obj.aab019t.value;
	if(obj.aab022.value == "") obj.aab022.value = '';
	if(obj.aab054.value == "") obj.aab054.value = '';
	if(obj.aab021.value == "") obj.aab021.value = '';
	if(obj.aab020.value == "") obj.aab020.value = '';
	if(obj.aab056.value == "") obj.aab056.value = '';
	if(obj.aab019.value == "") obj.aab019.value = '';
	}
</script>
