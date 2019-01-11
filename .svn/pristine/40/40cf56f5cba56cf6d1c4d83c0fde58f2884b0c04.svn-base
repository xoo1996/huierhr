<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","add(document.forms[0])"); 
    
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
	hidden.put("useremloyid","工号");
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
		alert("教育经历和工作经历必须完整填写一行！否则影响入职！");
	});

	function add(obj){
		var ed1=$("input[id=userschoolname_row1]").val().length;
		var ed2=document.getElementById("userschoolstartdate_row1").value;
		var ed3=document.getElementById("userschoolenddate_row1").value;
		var ed4=$("input[id=userschoolmajor_row1]").val().length;
		var ed5=$("input[id=userschooldegree_row1]").val().length;
		var ed6=$("input[id=userschooltype_row1]").val().length;
		var wo1=document.getElementById("workstartdate_row1").value;
		var wo2=document.getElementById("workenddate_row1").value;
		var wo3=$("input[id=workplace_row1]").val().length;
		var wo4=$("input[id=workposition_row1]").val().length;
		var wo5=$("input[id=worksalary_row1]").val().length;
		var wo6=$("input[id=workleavereason_row1]").val().length;
		var wo7=$("input[id=workprove_row1]").val().length;
		if(ed1==""||ed2==""||ed3==""||ed4<1||ed5<1||ed6<1||wo1==""||wo2==""||wo3<1||wo4<1||wo5<1||wo6<1||wo7<1){
			alert("教育经历和工作经历至少完整填写一行");
			return false;
		}
		var li=$(".buttonGray");
		
		for(var i=0;i<li.length;i++){
			if(li[i].value=="保 存") {
				li[i].disabled=true;
			}
		}
		obj.submit();
	}

</script>
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="用户新增" />
	<table class="tableinput">
	<COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
		<html:form action="/UserInfoAction.do?method=addMore" method="POST">
			<tr>
				<lemis:texteditor property="useremployid" label="工号" 
					required="true" disable="true" value="${sessionScope.useremployid}"/>
				<lemis:texteditor property="username" label="姓名" 
					required="true" disable="true" value="${sessionScope.username}"/>
				
				
			</tr>
		
	 	 <jsp:include page="family.jsp" /> 
	 	<jsp:include page="educate.jsp" />
	 	<jsp:include page="work.jsp"/>
		<jsp:include page="train.jsp"/>
		</html:form>

	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>