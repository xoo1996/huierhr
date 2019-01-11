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
	<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
		 var nempay = '<%=request.getSession().getAttribute("nempay")%>';
		  $("input[name='nempay'][value='" + nempay +"']").attr("checked","checked"); 
	 });  
</script> 
	<script language="javascript">
		
		//审核信息
  		function verifyapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
			if(confirm("确定要审核通过该续签流程吗？")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=con&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  		//回退信息
  		function backapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("确定要审核通过该续签流程吗？")){
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=con&"/>'+getAlldata(obj);			
			obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工续签申请流程审核" />
		<lemis:tabletitle title="员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="true" disable="true" />
					<lemis:texteditor property="nemname" label="姓名" required="true" disable="true"  />
				</tr>
				<tr>
					<lemis:formateditor property="nemcard" label="身份证号" disable="true" required="true"  mask="card"/>
					<lemis:texteditor property="nememployid" label="员工id" required="false" disable="true"  />
				</tr>
				<tr>
					<lemis:texteditor property="bsc009" label="所在部门" required="true" disable="true" />
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="本单位入职时间" disable="true" format="true" />
					<lemis:texteditor property="nemjob" label="现任职务" required="false" disable="true" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>签订固定期限劳动合同</td>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="true" />
					<td><input type="radio" name="nempay" value="1"/>签订无固定期限劳动合同</td>
				</tr>
				<lemis:tabletitle title="原合同期限" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="第一次" required="false" disable="true" />
					<lemis:texteditor property="nemad2" label="第二次" required="false" disable="true" />
					<lemis:texteditor property="nemad3" label="第三次" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="第四次" required="false" disable="true" />
					<lemis:texteditor property="nemad5" label="第五次" required="false" disable="true" />
				</tr>
				</table>
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

