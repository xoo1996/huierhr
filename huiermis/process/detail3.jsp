<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("�� ��","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="Ա�����������������" />
		<lemis:tabletitle title="���ʱ��" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="����id" required="false" disable="true" />
					<lemis:formateditor mask="date" property="restsdt" required="false" label="��ʼʱ��" disable="true" format="true" />
					<lemis:formateditor mask="date" property="restedt" required="false" label="����ʱ��" disable="true" format="true" />
				</tr>
				<lemis:tabletitle title="������" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="restnum" label="�������" required="false" disable="true" />
					<lemis:codelisteditor type="resttype" isSelect="false" label="������" redisplay="true" required="false" />
					<lemis:texteditor property="restother" label="����" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemname" label="������" required="false" disable="true" />
					<lemis:texteditor property="nemapplyid" label="Ա��id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="userjoindate" required="false" label="��ְʱ��" disable="true" format="true" />
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="����ʱ��" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="�������" />
				<table class="tableinput">
				<tr>
					<html:textarea property="restreason" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="��ע" />
				<table class="tableinput">
				<tr>
					<html:textarea property="restnote" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="����" />
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

