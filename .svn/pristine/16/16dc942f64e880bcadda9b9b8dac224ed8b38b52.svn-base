<!--recommendation\query\printA4s.jsp-->
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="org.radf.apps.recommendation.query.dto.Rec0301DTO"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.commons.DateUtil"%>
<%@ page import="org.radf.plat.commons.ClassHelper"%>
<%@ page import="org.radf.apps.commons.entity.Cb21"%>
<%@ page import="org.radf.plat.commons.OptionDictSupport"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<style media=print> 
.Noprint{display:none;} 
.PageNext{page-break-after: always;} 
</style>
<style>
td {
	font-family: "黑体";
	font-size: 20px;
	font-weight: bold;
	color: #000000;
}
.table {
	border: 0px solid #FFFFFFF;
}
.td {
	border: 0px solid #FFFFFFF;
}
.td1 {
	font-family: "宋体";
	border: 1px solid #FFFFFF;
}
.STYLE1 {font-size: 20px}
</style>
<style media=print> 

.noprint{display:none;} 

.pagenext{page-break-after: always;} 

</style>

<html>
<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WB width=0></object> <OBJECT id=factory style="DISPLAY:  none"
		codeBase=http: //<%=request.getServerName()%> :
		<%=request.getServerPort()+request.getContextPath()%>/scriptx/ScriptX.cab#Version=5,60,0,360
		classid=clsid:1663ed61-23eb-11d2-b92f-008048fdd814 viewastext> </OBJECT>
	<body onload='print();'>

	<%ArrayList list = (ArrayList) request.getSession().getAttribute(
					"data");
			Rec0301DTO rec0301dto = (Rec0301DTO) request.getSession()
					.getAttribute("rec0301dto");

			%>
	<table width="599" border="0" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
		<tr height="45">
			<td>&nbsp;</td>
		</tr>
		<%if (rec0301dto.getAcb206() != null
					|| rec0301dto.getAab020() != null
					|| rec0301dto.getAab019() != null) {

				%>
		
		<tr height="45">
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style='border-collapse: collapse' bordercolor='#000000'>
				<tr height="45">
					<td align="center" colspan="3"><%=rec0301dto.getAcb206()%></td>
				</tr>
				<tr height="45">
					<td align="center">&nbsp;</td>
					<td align="right">单位性质：<%=OptionDictSupport.getCodeName(request, "AAB019",
								rec0301dto.getAab019())%></td>
				</tr>
				<tr height="45">

					<td align="left">经济类型：<%=OptionDictSupport.getCodeName(request, "AAB020",
								rec0301dto.getAab020())%></td>
					<td align="center">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<%}

			%>
		<tr>
			<td>
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				style='border-collapse: collapse' bordercolor='#000000'>
				<%
				int pagenum = 0;

				if (rec0301dto.getAcb206() != null
						|| rec0301dto.getAab020() != null
						|| rec0301dto.getAab019() != null) {
					 pagenum = 4;
				}else

				{
					pagenum = 5;
				}

					
			for (int i = 1; i <= list.size(); i++) {
				Cb21 cb21 = new Cb21();
				ClassHelper.copyProperties(list.get(i - 1), cb21);
				String acb21h = "";
				if (cb21.getAcb21h() != null) {
					acb21h = cb21.getAcb21h().toString();
				}

				%>
				<tr height="45">
					<td align="left" colspan="3">&nbsp;<%=i%>:<%=cb21.getAca112()%>(<%=cb21.getAcb216() %>)</td>
					<td align="left" width="180">&nbsp;编号:<%=cb21.getAcb210()%></td>
				</tr>
				<tr height="45">
					<td align="left">&nbsp;人数:<%=cb21.getAcb21d()%></td>
					<td align="center">&nbsp;年龄:<%=cb21.getAcb221()%>-<%=cb21.getAcb222()%></td>
					<td align="left">&nbsp;文化:<%=OptionDictSupport.getCodeName(request, "AAC011",cb21.getAac011())%></td>
					<td align="left">&nbsp;待遇:<%=acb21h%></td>
				</tr>
				<tr height="45">
					<td align="left" colspan="4">&nbsp;待遇描述:<%=cb21.getAcb21i()==null?"":cb21.getAcb21i()%></td>
				</tr>
				<tr height="45">
					<td align="left" colspan="4">&nbsp;报名时间:<%=DateUtil.converToString(cb21.getAae030(),"YYYY-MM-DD")%>到<%=DateUtil.converToString(cb21.getAae031(),"YYYY-MM-DD")%></td>
				</tr>
				<%if (i % pagenum == 0 && (list.size() > i)) {%>
			</table>
			</td>
		</tr>

	</table>
	<div class="PageNext"></div>
	<table width="599" border="0" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
        <tr height="45">
			<td>&nbsp;</td>
		</tr>
		<%if (rec0301dto.getAcb206() != null
					|| rec0301dto.getAab020() != null
					|| rec0301dto.getAab019() != null) {

				%>
		
		<tr height="45">
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style='border-collapse: collapse' bordercolor='#000000'>
				<tr height="45">
					<td align="center" colspan="3"><%=rec0301dto.getAcb206()%></td>
				</tr>
				<tr height="45">
					<td align="center">&nbsp;</td>
					<td align="right">单位性质：<%=OptionDictSupport.getCodeName(request, "AAB019",
									rec0301dto.getAab019())%></td>
				</tr>
				<tr height="45">

					<td align="left">经济类型：<%=OptionDictSupport.getCodeName(request, "AAB020",
									rec0301dto.getAab020())%></td>
					<td align="center">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<%} %>
        <tr>
			<td>
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				style='border-collapse: collapse' bordercolor='#000000'>
		<%}
			}

		%>

	</table>
	</td>
	</tr>

	</table>
	</body>
	<script>
	function print()
	{ 
	SetPrintSettings();
	try{WB.ExecWB(6,6);}catch(e){}
	}
 
   function SetPrintSettings() {  
          
          factory.printing.portrait = true     //方向，true
          factory.printing.leftMargin = 2.0  
          factory.printing.topMargin = 2.0  
          factory.printing.rightMargin = 2.0  
          factory.printing.bottomMargin = 1.0 
          factory.printing.header = ""
          factory.printing.footer = ""
 
       
   }  
</script>
</html>

