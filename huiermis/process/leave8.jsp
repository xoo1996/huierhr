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
	<script language="javascript">
		
		//�����Ϣ
  		function verifyapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
			if(confirm("ȷ��Ҫ���ͨ������ְ������")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=lea&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  	//������Ϣ
  		function backapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("ȷ��Ҫ���ͨ������ְ������")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=lea&"/>'+getAlldata(obj);			
				obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="Ա����ְ�����������" />
		<lemis:tabletitle title="������Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="����id" required="false" disable="true" />
					<lemis:texteditor property="nemname" label="����" required="false" disable="true" />
					<lemis:texteditor property="nemapplyid" label="Ա��id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="����ʱ��" disable="true" format="true" />
					<lemis:texteditor property="bsc009" label="�ŵ����" required="false" disable="true" />
					<lemis:codelisteditor type="nemtype" isSelect="false" label="Ա������" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="leasdt" required="false" label="��ְʱ��" disable="true" format="true" />
					<lemis:formateditor mask="date" property="leaedt" required="false" label="����ְʱ��" disable="true" format="true" />
					<lemis:codelisteditor type="leatype" isSelect="false" label="��ְ���" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemsoc" label="�籣��ͣ���" required="false" disable="true" colspan="3"/>
				</tr>
				<lemis:tabletitle title="��ְԭ�� " />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="leareason" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="���" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="nemstate" isSelect="false" label="���״̬" redisplay="true" required="false" />
					<lemis:texteditor property="nemadv" label="�ϼ��������" required="true" disable="true" colspan="3"/>
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemldt" required="false" label="��ְʱ��" disable="true" format="true" />
					<lemis:formateditor mask="date" property="nemedt" required="false" label="���ʷ�����" disable="true" format="true" />
					<lemis:texteditor property="nemday" label="���³�������" required="true" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemif" label="����Լ��" required="false" disable="true" colspan="3"/>
				</tr>
				</table>
				<%-- <tr>�ŵ���Ա�������ܲ���Ӧ���ź�ʵ������Ϣ</tr>
				<lemis:tabletitle title="����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="������" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren1" required="false" label="���ʱ��" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="���²�" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad2" label="������" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren2" required="false" label="���ʱ��" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="���缼����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad3" label="������" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren3" required="false" label="���ʱ��" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="��չ��" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad4" label="������" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren4" required="false" label="���ʱ��" disable="true" format="true" />
				</tr>
				</table>
				<lemis:tabletitle title="��Ӧ��" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad5" label="������" required="false" disable="true" colspan="3"/>
					<lemis:formateditor mask="date" property="nemren5" required="false" label="���ʱ��" disable="true" format="true" />
				</tr>
				</table> --%>
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

