<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","modifyMore()"); 
    
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
	function modifyMore(){
		$("form").submit();
	}
	
	
/* 	在id为table的标签后面添加一行，用于修改页面中多一天可添加记录 */
	function append(table){
		var size = $("." + table).find("tr").size();
		var maxLength = 6;
		if(size < maxLength){
			var index = 1;
			while(size < maxLength){

				if(size % 2 == 0)
					index = 2;
				else
					index = 1;
				
				var html = $("." + table).find("tr").eq(size-1).html();
/* 				alert(size);
				alert($(".family").find("tr").eq(size-1).html()); */
				$("." + table).find("tr").eq(size-1).after("<tr>"+html + "</tr>");
				size++;
			}
		}
	}
	$(document).ready(function(){
		
		append("family");
		append("educate");
		append("work");
		append("train");
		/* var size = $(".family").find("tr").size();

		if(size < 7){

			var index = 1;
			while(size < 7){

				if(size % 2 == 0)
					index = 2;
				else
					index = 1;			
				var html = $(".family").find("tr").eq(size-1).html();
				$(".family").find("tr").eq(size-1).after("<tr>"+html + "</tr>");
				size++;
			}
		} */
	});
	
</script>
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="更新用户信息" />
	<table class="tableinput">
	<COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'><COL width='10%'><COL width='15%'></COLGROUP>
		<html:form action="/UserInfoAction.do?method=modifyMore" method="POST">
			<tr>
				<lemis:texteditor property="useremployid" label="工号"   
					required="true"  />
				<lemis:texteditor property="username" label="姓名"   
					required="true"  />
				
				
			</tr>
		
		
		<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0'>
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>教育经历</TD>
								</TR>
							</TABLE></td>
						<td width='10' class=''>&nbsp;</td>
						<td>&nbsp;</td>
						<td width='40%'>&nbsp;</td>
				
				</tr>
			</table></td>
	</tr>
</table>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' class='tableList' >
	<tr>
		<td class=''>
			<table class='family' width='100%' border='0' align='center' cellspacing='1'>
				<tr align='center'>
					
					<td height='0' nowrap class='tableHead'>姓名</td>
					<td height='0' nowrap class='tableHead'>与本人关系</td>
					<td height='0' nowrap class='tableHead'>出身年月</td>
					<td height='0' nowrap class='tableHead'>工作或学习单位</td>
					<td height='0' nowrap class='tableHead'>联系方式</td>
				</tr>
			 	<c:forEach items="${requestScope.familyList}" var="family" varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB>
					</c:if>
						
						<td height='0' style='word-break: break-all;'><input
							name='familyname' maxlength='50' id='familyname_row1'
							value='${family.familyname }' required='false' label='姓名' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='familycall' maxlength='50' id='familycall_row2'
							value='${family.familycall }' required='false' label='与本人关系' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='出身年月'
								rows=1 cols=20 mask='date' name='familybirthday'
								maxlength='50' id='familybirthday_row1'>${family.familybirthdayStr }</textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='familyworkplace' maxlength='50' id='familyworkplace_row1'
							value='${family.familyworkplace }' required='false' label='工作或学习单位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='familyphoneno' maxlength='50' id='familyphoneno_row1'
							value='${family.familyphoneno }' required='false' label='联系方式' type='text' class='text' />
						</td>
					</TR>
				</c:forEach> 
			</table>
		</td>
	</tr>
</table>



<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' >
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>教育经历</TD>
								</TR>
							</TABLE></td>
						<td width='10' class=''>&nbsp;</td>
						<td>&nbsp;</td>
						<td width='40%'>&nbsp;</td>
				
				</tr>
			</table></td>
	</tr>
</table>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' class='tableList' >
	<tr>
		<td class=''>
			<table  class="educate" width='100%' border='0' align='center' cellspacing='1'>
				<tr align='center'>
					
					<td height='0' nowrap class='tableHead'>学校名称</td>
					<td height='0' nowrap class='tableHead'>开始时间</td>
					<td height='0' nowrap class='tableHead'>结束时间</td>
					<td height='0' nowrap class='tableHead'>所学专业</td>
					<td height='0' nowrap class='tableHead'>学位</td>
					<td height='0' nowrap class='tableHead'>培养方式</td>
				</tr>
				
				<c:forEach items="${requestScope.educateList}" var="educate"
					varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB>
					</c:if>
					
						<td height='0' style='word-break: break-all;'><input  
							name='userschoolname' maxlength='50' id='userschoolname_row1'
							value='${educate.userschoolname }' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea  
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row1'>${educate.userschoolstartdateStr}</textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row1'>${educate.userschoolenddateStr }</textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row1'
							value='${educate.userschoolmajor }' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschoolmajor_row1'
							value='${educate.userschooldegree }' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschoolmajor_row1'
							value='${educate.userschooltype }' required='false' label='培养方式' type='text' class='text' />
						</td>
					</TR>
				</c:forEach> 
			</table>
		</td>
	</tr>
</table>

<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0'>
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>工作经历</TD>
								</TR>
							</TABLE></td>
						<td width='10' class=''>&nbsp;</td>
						<td>&nbsp;</td>
						<td width='40%'>&nbsp;</td>
				
				</tr>
			</table></td>
	</tr>
</table>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' class='tableList' >
	<tr>
		<td class=''>
			<table class="work" width='100%' border='0' align='center' cellspacing='1' >
				<tr align='center'>
					<td height='0' nowrap class='tableHead'>开始时间</td>
					<td height='0' nowrap class='tableHead'>结束时间</td>
					<td height='0' nowrap class='tableHead'>工作单位</td>
					<td height='0' nowrap class='tableHead'>职位</td>
					<td height='0' nowrap class='tableHead'>薪资标准</td>
					<td height='0' nowrap class='tableHead'>离职原因</td>
					<td height='0' nowrap class='tableHead'>证明人/电话</td>
				</tr>
				
			<c:forEach items="${requestScope.workList}" var="work" varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA text-align=center>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB >
					</c:if>
						<td height='0' style='word-break: break-all;'><textarea  
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='userschoolstartdate_row1'>${work.workstartdateStr }</textarea></td>					
						<td height='0' style='word-break: break-all;'><textarea  
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='userschoolstartdate_row1'>${work.workenddateStr }</textarea></td>
						
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='userschoolmajor_row1'
							value='${work.workplace }' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='userschoolmajor_row1'
							value='${work.workposition }' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='userschoolmajor_row1'
							value='${work.worksalary }' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='userschoolmajor_row1'
							value='${work.workleavereason }' required='false' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='userschoolmajor_row1'
							value='${work.workprove }' required='false' label='培养方式' type='text' class='text' />
						</td>
					<%-- <TD  style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workstartdateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workenddateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workplace }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workposition }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.worksalary }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workleavereason }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workprove }" /></TD> --%>
					</tr>
				</c:forEach>  
			</table>
		</td>
	</tr>
</table>


<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0'>
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>培训经历</TD>
								</TR>
							</TABLE></td>
						<td width='10' class=''>&nbsp;</td>
						<td>&nbsp;</td>
						<td width='40%'>&nbsp;</td>
				
				</tr>
			</table></td>
	</tr>
</table>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' class='tableList' >
	<tr>
		<td class=''>
			<table class="train" width='100%' border='0' align='center' cellspacing='1' >
				<tr align='center'>
					<td height='0' nowrap class='tableHead'>开始时间</td>
					<td height='0' nowrap class='tableHead'>结束时间</td>
					<td height='0' nowrap class='tableHead'>培训地点</td>
					<td height='0' nowrap class='tableHead'>培训内容</td>
					<td height='0' nowrap class='tableHead'>培训证书</td>
				</tr>
				
			<c:forEach items="${requestScope.trainList}" var="train" varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA text-align=center>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB >
					</c:if>
						<td height='0' style='word-break: break-all;'><textarea  
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='trainstartdate'
								maxlength='50' id='userschoolstartdate_row1'>${train.trainstartdateStr }</textarea></td>					
						<td height='0' style='word-break: break-all;'><textarea  
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='trainenddate'
								maxlength='50' id='userschoolstartdate_row1'>${train.trainenddateStr }</textarea></td>
						
						<td height='0' style='word-break: break-all;'><input
							name='trainplace' maxlength='50' id='userschoolmajor_row1'
							value='${train.trainplace }' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='traincontent' maxlength='50' id='userschoolmajor_row1'
							value='${train.traincontent }' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='traincertificate' maxlength='50' id='userschoolmajor_row1'
							value='${train.traincertificate }' required='false' label='学位' type='text' class='text' />
						</td>
					<%-- <TD  style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workstartdateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workenddateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workplace }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workposition }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.worksalary }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workleavereason }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workprove }" /></TD> --%>
					</tr>
				</c:forEach>  
			</table>
		</td>
	</tr>
</table>
				
	 	<%--  <jsp:include page="family.jsp" /> 
	 	<jsp:include page="educate.jsp" />
	 	<jsp:include page="work.jsp"/>
	 --%>
		</html:form>

	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>