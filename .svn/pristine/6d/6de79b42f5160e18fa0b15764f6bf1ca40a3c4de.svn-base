<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("返 回","history.back()");
    
    List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("familycall", "称呼"));
	header.add(new Formatter("familyname", "姓名"));
	header.add(new Formatter("familyworkplace", "工作单位"));
	header.add(new Formatter("familyphoneno", "联系电话"));
	

	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text", "familycall", "称呼", "false"));
	batchInput.add(new Editor("text", "familyname", "姓名", "false"));
	batchInput.add(new Editor("text", "familyworkplace", "工作单位", "false"));
	batchInput.add(new Editor("text", "familyphoneno", "联系电话", "false"));

 	List<Formatter> header2 = new ArrayList<Formatter>();
	header2.add(new Formatter("userschoolname", "学校名称"));
	header2.add(new Formatter("userschoolstartdate", "开始时间"));
	header2.add(new Formatter("userschoolenddate", "结束时间"));
	header2.add(new Formatter("userschoolmajor", "所学专业"));
	

	List<Editor> batchInput2 = new ArrayList<Editor>();
	batchInput2.add(new Editor("text", "userschoolname", "学校名称", "false"));
	batchInput2.add(new Editor("date", "userschoolstartdate", "开始时间", "false"));
	batchInput2.add(new Editor("date", "userschoolenddate", "结束时间", "false"));
	batchInput2.add(new Editor("text", "userschoolmajor", "所学专业", "false")); 

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("useremployid", "用户代码");
	
 	pageContext.setAttribute("hidden", hidden); 
	pageContext.setAttribute("batchInput", batchInput);
	pageContext.setAttribute("header", header);
 	pageContext.setAttribute("batchInput2", batchInput2);
	pageContext.setAttribute("header2", header2); 
	pageContext.setAttribute("button", buttons);
%>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("select").attr("disabled", "disabled");
						$("#href")
								.click(
										function() {
											window.location.href = "/"
													+ lemis.WEB_APP_NAME
													+ "/userinfo/UserInfoAction.do?method=download&nemid="
													+ $(this).attr("nemid")
													+ '&name='
													+ $(this).attr("name");
										});
					});

</script>
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="入职流程审核" />
	<lemis:tabletitle title="基础信息" />
	<table class="tableinput">
		<COLGROUP>
			<COL width='10%'>
			<COL width='15%'>
			<COL width='10%'>
			<COL width='15%'>
			<COL width='10%'>
			<COL width='15%'>
			<COL width='10%'>
			<COL width='15%'>
		</COLGROUP>
		<html:form action="/ApaOperAction.do" method="POST">
			<tr>
				<lemis:texteditor property="nemid" label="流程id" disable="true"
					required="true" />
				<lemis:texteditor property="useremployid" label="工号" required="true"
					disable="true" maxlength="10" />
			</tr>
			<tr>
				<lemis:texteditor property="bsc009" label="所属部门"
					disable="true" required="true" />
				<lemis:codelisteditor type="positionname" label="所属岗位"
					required="true" isSelect="false"/>
				<lemis:formateditor mask="date" property="writedate" label="填表日期"
					required="false" disable="true" />
				<lemis:texteditor property="danganid" label="档案号" required="true"
					disable="true" maxlength="20" />
			</tr>

			<tr>
				<lemis:texteditor property="username" label="姓名" required="true"
					disable="true" maxlength="20" />
				<lemis:codelisteditor type="usergender" label="性别" required="true" isSelect="false"/>
				<lemis:codelisteditor type="usernation" label="民族" required="false" isSelect="false"/>
				<lemis:texteditor property="userhometown" label="籍贯" disable="true"
					required="false" maxlength="100" />
			</tr>

			<tr>
				<lemis:texteditor property="userworkold" label="工龄" required="false"
					disable="true" maxlength="3" />

				<lemis:formateditor mask="date" property="userjoindate"
					format="true" label="入职日期" required="false" disable="true" />
				<lemis:texteditor property="userworktime" label="参加工作时间"
					required="false" disable="true" maxlength="80" />
				<lemis:codelisteditor type="useremployeestatus" label="员工状态"
					required="false" isSelect="false"/>
			</tr>
			<table class="tableinput">
				<COLGROUP>
					<COL width='10%'>
					<COL width='15%'>
					<COL width='10%'>
					<COL width='14%'>
					<COL width='10%'>
					<COL width='40%'>
				</COLGROUP>
				<tr>
					<lemis:texteditor property="usermobilephone" label="手机号"
						disable="true" required="false" maxlength="11" />
					<lemis:texteditor property="usertelephone" label="家庭电话"
						required="false" disable="true" maxlength="12" />
					<lemis:texteditor property="useremail" label="邮箱" required="false"
						disable="true" maxlength="80" />
				</tr>
			</table>


		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>