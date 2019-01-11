<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String nempay=(String)request.getSession().getAttribute("nempay");
Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>


<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
		 var nempay = '<%=request.getSession().getAttribute("nempay")%>';
		  $("input[name='nempay'][value='" + nempay +"']").attr("checked","checked"); 
	 });  
</script> 
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="新员工录用审批流程详情" />
		<lemis:tabletitle title="新员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" >
				<tr>
					<lemis:texteditor property="nemid" label="新员工录用审批流程号id" required="false" disable="true"  />
					<lemis:texteditor property="nemname" label="姓名" required="false" disable="true"  />
					<lemis:codelisteditor type="nemsex" isSelect="false" label="性别" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nembirthpl" label="籍贯" required="false" disable="true"  />
					<lemis:formateditor mask="date" property="nembirthdt" required="false" label="出生年月" disable="true" format="true" />
					<lemis:codelisteditor type="userheightestedu" isSelect="false" label="最高学历" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemmajor" label="所学专业" required="false" disable="true"  />
					<lemis:texteditor property="nemschool" label="最后毕业学校" required="false" disable="true"  />
					<lemis:formateditor mask="date" property="nemedudt" required="false" label="毕业时间" disable="true" format="true" />
				</tr>
				<lemis:tabletitle title="岗位信息" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bsc009" label="拟录用部门" required="false" disable="true" />
					<lemis:texteditor property="nemjob" label="拟录用岗位" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="拟入职时间" disable="true" format="true"/>
					<lemis:codelisteditor type="nemtype" isSelect="false" label="员工类型" redisplay="true" required="false" />
				</tr>
				</table>
				<lemis:tabletitle title="薪资情况" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<td><input type="radio" name="nempay" value="0"/>月薪制</td>
					<lemis:texteditor property="nemmon1" label="试用工资每月税前（元）" required="false" disable="true" />
					<lemis:texteditor property="nemmon2" label="转正工资每月税前（元）" required="false" disable="true" />					
				</tr>
				<tr>  
                    <td><input type="radio" name="nempay" value="1"/>年薪制</td>
					<lemis:texteditor property="nemyear1" label="年薪标准为税前（元）" required="false" disable="true" />
					<lemis:texteditor property="nemyear2" label="每月发放税前（元）" required="false" disable="true" />	
				</tr>
				<tr>
					<lemis:texteditor property="nemwelfare" label="福利情况" required="false" disable="true" colspan="4"/>
				</tr>
				</table>
				<lemis:tabletitle title="劳动合同签订" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="true" />	
					<lemis:texteditor property="nemtry" label="试用期（月）" required="false" disable="true" />	
					<td>注：入职一个月内完成签订</td>
				</tr>
				</table>
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="nemnote" rows="5" disabled="true"></html:textarea>
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

