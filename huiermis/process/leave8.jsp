<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
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
	<script src="/lemis/js/lemisTree.js"></script>
	<script language="javascript">
		
		//审核信息
  		function verifyapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
			if(confirm("确定要审核通过该离职流程吗？")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=lea&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  	//回退信息
  		function backapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("确定要审核通过该离职流程吗？")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=lea&"/>'+getAlldata(obj);			
				obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工离职申请流程审核" />
		<lemis:tabletitle title="基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="false" disable="true" />
					<lemis:texteditor property="nemname" label="姓名" required="false" disable="true" />
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="申请时间" disable="true" format="true" />
					<lemis:texteditor property="bsc009" label="门店或部门" required="false" disable="true" />
					<lemis:codelisteditor type="nemtype" isSelect="false" label="员工类型" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="leasdt" required="false" label="入职时间" disable="true" format="true" />
					<lemis:formateditor mask="date" property="leaedt" required="false" label="拟离职时间" disable="true" format="true" />
					<lemis:codelisteditor type="leatype" isSelect="false" label="离职类别" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemsoc" label="社保办停情况" required="false" disable="true" colspan="3"/>
				</tr>
				<lemis:tabletitle title="离职原因 " />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="leareason" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="审核" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="nemstate" isSelect="false" label="审核状态" redisplay="true" required="false" />
					<lemis:texteditor property="nemadv" label="上级主管意见" required="true" disable="true" colspan="3"/>
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemldt" required="false" label="离职时间" disable="true" format="true" />
					<lemis:formateditor mask="date" property="nemedt" required="false" label="工资发放至" disable="true" format="true" />
					<lemis:texteditor property="nemday" label="本月出勤天数" required="true" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemif" label="其他约定" required="false" disable="true" colspan="3"/>
				</tr>
				</table>
				<%-- <tr>门店人员申请需总部相应部门核实以下信息</tr>
				<lemis:tabletitle title="商务部" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="审核意见" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren1" required="false" label="审核时间" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="人事部" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad2" label="审核意见" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren2" required="false" label="审核时间" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="网络技术部" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad3" label="审核意见" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren3" required="false" label="审核时间" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="拓展部" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad4" label="审核意见" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren4" required="false" label="审核时间" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="供应部" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad5" label="审核意见" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren5" required="false" label="审核时间" disable="true" format="true" />
				</tr>
				</table> --%>
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

