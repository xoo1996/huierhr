<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %> 
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("返 回","history.back()");
/*     buttons.put("上 传","upload()"); */
    
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
	$(document).ready(function(){
		$("select").attr("disabled","disabled");
		$("#href").click(function(){
			window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/userinfo/UserInfoAction.do?method=download&nemid="
			+ $(this).attr("nemid") + '&name=' + $(this).attr("name");
		});
	});
	
	/* function modify(){
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/userinfo/UserInfoAction.do?method=showDetail&useremployid="
		+ $("input[name=useremployid]").val() + '&mtd=modifyUserInfo';
	} */
	
	/* function upload(){
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/userinfo/UserInfoAction.do?method=toUpload";
	} */
</script>
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="人员详情" />
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
		<html:form action="/UserInfoAction.do" method="POST">
			
			<tr>
				<lemis:texteditor property="bsc009" label="所属部门" disable="true" 
					required="true" />
				<lemis:codelisteditor type="positionname" label="所属岗位" required="true" />
				<lemis:formateditor mask="date" property="writedate"
						label="填表日期"  required="false" disable="true"/>
				<lemis:texteditor property="danganid" label="档案号" required="true"
					disable="true" maxlength="20" />
			</tr>
			
			<tr>
				<lemis:texteditor property="username" label="姓名" required="true"
					disable="true" maxlength="20" />
				<lemis:codelisteditor type="usergender" label="性别" required="true" />
				<lemis:codelisteditor type="usernation" label="民族" required="false" />
				<lemis:texteditor property="userhometown" label="籍贯" disable="true"
					required="false" maxlength="100"/>
			</tr>
			
			<tr>
					<lemis:texteditor property="useremployid" label="工号" 
						required="false" disable="true" maxlength="10" />
				
					<lemis:formateditor mask="date" property="userjoindate" format="true"
						label="入职日期" required="false" disable="true"/>
					<lemis:formateditor mask="date" property="userworktime"
						label="参加工作时间" format="true" required="true" disable="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="useremployeestatus" label="员工状态" required="false"/>
				<lemis:formateditor property="condatestart" mask="date"
					label="合同起始日期" format="true" disable="true" required="true" />
				<lemis:formateditor property="condateend" mask="date"
					label="合同到期日期" format="true" disable="true" required="true" />
				<lemis:codelisteditor type="contype" label="合同类型" required="true" isSelect="false" redisplay="true"/>
				</tr>
			<tr>

				<lemis:formateditor mask="date" property="userbirthday" label="出生年月"
					format="true" required="false" disable="true" />
				<lemis:formateditor property="useridno" label="身份证号" disable="true"
					required="false"  mask="card"/>
				
				<lemis:codelisteditor type="userismarry" label="婚姻状况" required="false"/>
				<lemis:texteditor property="userchildno" label="子女个数"
					disable="true" required="false" />
			</tr>
			<lemis:texteditor property="usergraduatefrom" label="毕业学校"
				disable="true" required="false" maxlength="100" />
			<lemis:formateditor mask="date" property="usergraduatedate"
				label="毕业时间" format="true" disable="true" required="false" />
			<lemis:codelisteditor type="userheightestedu" label="最高学历" required="false"  />
			<lemis:texteditor property="usermajor" label="专业" disable="true" required="false" maxlength="100" />
			<tr>
				<lemis:codelisteditor type="userpolitical" label="政治面貌" required="false" />
				<lemis:texteditor property="userforeignlanglevel" label="外语水平"
					disable="true" required="false" />
				<lemis:texteditor property="usercomputerlevel" label="计算机水平"
					disable="true" required="false" maxlength="100" />
				<lemis:texteditor property="userrankname" label="技术职称" disable="true" required="false" maxlength="100"/>
			</tr>
		
			<table class="tableinput">
				<COLGROUP><COL width='10%'><COL width='40%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
				<tr>
					<lemis:texteditor  property="userhousehold" label="户籍地址"
						disable="true" required="false" maxlength="200" />
					<lemis:texteditor property="userpostcode2" label="邮编"
						disable="true" required="false" maxlength="6" />
					<lemis:codelisteditor type="userhukoutype" label="户口性质"  required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="userresidence" label="居住地址"
						disable="true" required="false" maxlength="200" />
					<lemis:texteditor property="userpostcode1" label="邮编"
						disable="true" required="false" maxlength="6"/>
					<lemis:texteditor property="userworkold" label="工龄"
						required="false" disable="true" maxlength="3" />
				</tr>			
			</table>
			
			<table class="tableinput">
				<COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
				<tr>
					<lemis:texteditor property="usermobilephone" label="手机号"
						disable="true" required="false" maxlength="11" />
					<lemis:texteditor property="usertelephone" label="家庭电话"
						required="false" disable="true" maxlength="12" />
					<lemis:texteditor property="useremail" label="邮箱" required="false" disable="true" maxlength="80" />
				</tr>
			</table>
			
			<table class="tableinput">
				<COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
				<tr>
					<lemis:texteditor property="emepername" label="紧急联系人" required="false" disable="true" maxlength="20" />
						<lemis:texteditor property="emeperrelation" label="关系"
						required="false" disable="true" maxlength="100" />
						<lemis:texteditor property="emeperphone" label="联系方式"
							required="false" disable="true" maxlength="11" />
						<lemis:texteditor property="emeperaddress" label="联系地址"
							required="false" disable="true" maxlength="100" />
				</tr>
			</table>
			
			<table class="tableinput">
				<COLGROUP><COL width='10%'><COL width='30%'><COL width='10%'><COL width='25%'><COL width='10%'><COL width='15%'></COLGROUP>
				<lemis:texteditor property="jianshebank" label="建设银行"
							required="false" disable="true" maxlength="20" />
				<lemis:texteditor property="nongyebank" label="农业银行"
							required="false" disable="true" maxlength="20" />
				<lemis:codelisteditor type="usersocpro" isSelect="false" label="社保状态" redisplay="true" required="false" />
			</table>
			
			
			
			<lemis:tabletitle title="情况调查" />
			<table class="tableinput">
			<COLGROUP><COL width='20%'><COL width='15%'><COL width='20%'><COL width='15%'><COL width='20%'><COL width='15%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="investigate1" label="是否有亲属在本公司工作"
						required="false" />
					<lemis:codelisteditor type="investigate2"
						label="是否与其他企业签订保密、竞业限制协议"  required="false" />
					<lemiscodelisteditor type="investigate3" label="是否与其他单位有未尽法律事宜"
						required="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="investigate3" label="是否与其他单位有未尽法律事宜" required="false" />
					<lemis:codelisteditor type="investigate4" label="是否能接受工作地点调整或频繁出差" required="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="investigate5" label="是否有在外兼职"
						required="false"/>
					
				</tr>

		</html:form>

	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>