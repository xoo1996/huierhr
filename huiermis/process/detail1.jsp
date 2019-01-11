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
buttons.put("�� ��","history.back()");
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
		<lemis:title title="��Ա��¼��������������" />
		<lemis:tabletitle title="��Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" >
				<tr>
					<lemis:texteditor property="nemid" label="��Ա��¼���������̺�id" required="false" disable="true"  />
					<lemis:texteditor property="nemname" label="����" required="false" disable="true"  />
					<lemis:codelisteditor type="nemsex" isSelect="false" label="�Ա�" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nembirthpl" label="����" required="false" disable="true"  />
					<lemis:formateditor mask="date" property="nembirthdt" required="false" label="��������" disable="true" format="true" />
					<lemis:codelisteditor type="userheightestedu" isSelect="false" label="���ѧ��" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemmajor" label="��ѧרҵ" required="false" disable="true"  />
					<lemis:texteditor property="nemschool" label="����ҵѧУ" required="false" disable="true"  />
					<lemis:formateditor mask="date" property="nemedudt" required="false" label="��ҵʱ��" disable="true" format="true" />
				</tr>
				<lemis:tabletitle title="��λ��Ϣ" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bsc009" label="��¼�ò���" required="false" disable="true" />
					<lemis:texteditor property="nemjob" label="��¼�ø�λ" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="����ְʱ��" disable="true" format="true"/>
					<lemis:codelisteditor type="nemtype" isSelect="false" label="Ա������" redisplay="true" required="false" />
				</tr>
				</table>
				<lemis:tabletitle title="н�����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<td><input type="radio" name="nempay" value="0"/>��н��</td>
					<lemis:texteditor property="nemmon1" label="���ù���ÿ��˰ǰ��Ԫ��" required="false" disable="true" />
					<lemis:texteditor property="nemmon2" label="ת������ÿ��˰ǰ��Ԫ��" required="false" disable="true" />					
				</tr>
				<tr>  
                    <td><input type="radio" name="nempay" value="1"/>��н��</td>
					<lemis:texteditor property="nemyear1" label="��н��׼Ϊ˰ǰ��Ԫ��" required="false" disable="true" />
					<lemis:texteditor property="nemyear2" label="ÿ�·���˰ǰ��Ԫ��" required="false" disable="true" />	
				</tr>
				<tr>
					<lemis:texteditor property="nemwelfare" label="�������" required="false" disable="true" colspan="4"/>
				</tr>
				</table>
				<lemis:tabletitle title="�Ͷ���ͬǩ��" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemlimit" label="��ͬ���ޣ��꣩" required="false" disable="true" />	
					<lemis:texteditor property="nemtry" label="�����ڣ��£�" required="false" disable="true" />	
					<td>ע����ְһ���������ǩ��</td>
				</tr>
				</table>
				<lemis:tabletitle title="��ע" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="nemnote" rows="5" disabled="true"></html:textarea>
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

