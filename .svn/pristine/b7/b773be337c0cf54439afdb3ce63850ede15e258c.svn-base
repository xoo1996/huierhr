<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工请假申请流程详情" />
		<lemis:tabletitle title="请假时间" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="false" disable="true" />
					<lemis:formateditor mask="date" property="restsdt" required="false" label="起始时间" disable="true" format="true" />
					<lemis:formateditor mask="date" property="restedt" required="false" label="结束时间" disable="true" format="true" />
				</tr>
				<lemis:tabletitle title="请假类别" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="restnum" label="请假天数" required="false" disable="true" />
					<lemis:codelisteditor type="resttype" isSelect="false" label="请假类别" redisplay="true" required="false" />
					<lemis:texteditor property="restother" label="其他" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemname" label="申请人" required="false" disable="true" />
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="userjoindate" required="false" label="入职时间" disable="true" format="true" />
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="申请时间" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="请假事由" />
				<table class="tableinput">
				<tr>
					<html:textarea property="restreason" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<tr>
					<html:textarea property="restnote" rows="5" disabled="true"></html:textarea>
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
							<TABLE id=resultset cellSpacing=1 width="400px" align=center border=0>
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

