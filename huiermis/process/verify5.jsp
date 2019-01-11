<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("ͬ ��","verifyapa(document.forms[0])");
buttons.put("��ͬ��","backapa(document.forms[0])");
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
	<script language="javascript">
		
		//�����Ϣ
  		function verifyapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
			if(confirm("ȷ��Ҫ���ͨ������ǩ������")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=con&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  		//������Ϣ
  		function backapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("ȷ��Ҫ���ͨ������ǩ������")){
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=con&"/>'+getAlldata(obj);			
			obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="Ա����ǩ�����������" />
		<lemis:tabletitle title="Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="����id" required="true" disable="true" />
					<lemis:texteditor property="nemname" label="����" required="true" disable="true"  />
				</tr>
				<tr>
					<lemis:formateditor property="nemcard" label="���֤��" disable="true" required="true"  mask="card"/>
					<lemis:texteditor property="nememployid" label="Ա��id" required="false" disable="true"  />
				</tr>
				<tr>
					<lemis:texteditor property="bsc009" label="���ڲ���" required="true" disable="true" />
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="����λ��ְʱ��" disable="true" format="true" />
					<lemis:texteditor property="nemjob" label="����ְ��" required="false" disable="true" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>ǩ���̶������Ͷ���ͬ</td>
					<lemis:texteditor property="nemlimit" label="��ͬ���ޣ��꣩" required="false" disable="true" />
					<td><input type="radio" name="nempay" value="1"/>ǩ���޹̶������Ͷ���ͬ</td>
				</tr>
				<lemis:tabletitle title="ԭ��ͬ����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="��һ��" required="false" disable="true" />
					<lemis:texteditor property="nemad2" label="�ڶ���" required="false" disable="true" />
					<lemis:texteditor property="nemad3" label="������" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="���Ĵ�" required="false" disable="true" />
					<lemis:texteditor property="nemad5" label="�����" required="false" disable="true" />
				</tr>
				</table>
				<lemis:tabletitle title="���" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="nemstate" isSelect="false" label="���״̬" redisplay="true" required="false" />
					<td>�������</td><td><lemis:operdate/></td>
				</tr>
				<tr>
					<lemis:texteditor property="nemapa" label="������" required="true" disable="false" colspan="5"/>
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

