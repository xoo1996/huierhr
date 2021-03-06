<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%><%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("同 意","verifyapa(document.forms[0])");
buttons.put("不同意","backapa(document.forms[0])");
buttons.put("返 回","history.back()");

pageContext.setAttribute("button", buttons);

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
	<script src="/lemis/js/lemisTree.js"></script>
	<script language="javascript">
	$(document).ready(function(){
		var cvtfee=$('input[name=cvtfee]').val();
		if(cvtfee==""){
			$("input[name=cvtfee]").val("基本工资、岗位工资、绩效工资、提奖、其他");
		}
	});
		
		//审核信息
  		function verifyapa(obj){
  			var temp="基本工资、岗位工资、绩效工资、提奖、其他";
			var cvtfee=$('input[name=cvtfee]').val();
			if(temp==cvtfee){
				alert("请完善工资方案");
				return false;
			}
  			if(!checkValue(obj)){
				return false;
			}
			if(confirm("确定要审核通过该转正流程吗？")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=cvt&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  	//回退信息
  		function backapa(obj){
  			var temp="基本工资、岗位工资、绩效工资、提奖、其他";
			var cvtfee=$('input[name=cvtfee]').val();
			if(temp==cvtfee){
				alert("请完善工资方案");
				return false;
			}
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("确定要审核通过该转正流程吗？")){
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=cvt&"/>'+getAlldata(obj);			
			obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="转正流程审核" />
		<lemis:tabletitle title="新员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" >
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="false" disable="true" />
					<lemis:texteditor property="cvtname" label="姓名" required="false" disable="true" />
					<lemis:formateditor mask="date" property="cvtbdt" required="false" label="出生年月" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtedu" label="学历" required="false" disable="true" />
					<lemis:texteditor property="cvtsch" label="毕业学校" required="false" disable="true" />
					<lemis:formateditor mask="date" property="cvtsdt" required="false" label="入职时间" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemapplyid" label="员工id" required="true" disable="true" />
					<lemis:formateditor mask="date" property="cvtedt" required="true" label="转正时间" disable="false" format="true" />
					<lemis:texteditor property="cvtapt" label="所在门店" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtfee" label="转正工资方案" required="true" disable="false" colspan="5" />
				</tr>
				<lemis:tabletitle title="自我鉴定" />
				<tr>
					<html:textarea property="cvtintro" rows="5" cols="120" disabled="true"></html:textarea>
				</tr>
				<lemis:tabletitle title="审核" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="nemstate" isSelect="false" label="审核状态" redisplay="true" required="false" />
					<td>审核日期</td><td><lemis:operdate/></td>
				</tr>
				<tr>
					<lemis:texteditor property="nemapa" label="审核意见" required="true" disable="false" colspan="5"/>
				</tr>
				</table>
				<lemis:tabletitle title="附件" />
				<table class="tableinput">
				<lemis:editorlayout />
				</table>
				
				<TABLE class=tableList cellSpacing=0 cellPadding=0 style="width:400px" text-align="center" border=0>
				<TBODY>
					<TR>
						<TD>
							<TABLE id=resultset cellSpacing=1 width="100px" align=center border=0>
								<TBODY>				
									<tr >
										<c:forEach items="${requestScope.resList}" var="res" varStatus="obj">
											<c:if test="${obj.count%2 == '0'}">
												<TR class=listColorA text-align=center>
											</c:if>
											<c:if test="${obj.count%2 != '0'}">
												<TR class=listColorB >
											</c:if>
										
											<TD  style="text-align:center" style="WORD-BREAK:break-all" >
											
											<a id="href" href='/huiermis/process/ApaOperAction.do?method=downloadRes&nemid=${res.nemid }&id=${res.id }' >${res.name }</a>
											</TD>
											
										</c:forEach>
									</tr>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

