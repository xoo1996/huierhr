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
			if(confirm("ȷ��Ҫ���ͨ����ת��������")){
				obj.action = '<html:rewrite page="/ApaOperAction.do?method=verifyApa&type=cvt&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
  	//������Ϣ
  		function backapa(obj){
  			if(!checkValue(obj)){
				return false;
			}
  			if(confirm("ȷ��Ҫ���ͨ����ת��������")){
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=backApa&type=cvt&"/>'+getAlldata(obj);			
			obj.submit();
  			}
  		}
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="ת���������" />
		<lemis:tabletitle title="��Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?" >
				<tr>
					<lemis:texteditor property="nemid" label="����id" required="false" disable="true" />
					<lemis:texteditor property="cvtname" label="����" required="false" disable="true" />
					<lemis:formateditor mask="date" property="cvtbdt" required="false" label="��������" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtedu" label="ѧ��" required="false" disable="true" />
					<lemis:texteditor property="cvtsch" label="��ҵѧУ" required="false" disable="true" />
					<lemis:formateditor mask="date" property="cvtsdt" required="false" label="��ְʱ��" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemapplyid" label="Ա��id" required="true" disable="true" />
					<lemis:formateditor mask="date" property="cvtedt" required="false" label="ת��ʱ��" disable="true" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtfee" label="ת�����ʷ���" required="false" disable="true" colspan="3"/>
				</tr>
				<lemis:tabletitle title="���Ҽ���" />
				<tr>
					<html:textarea property="cvtintro" rows="5" cols="120" disabled="true"></html:textarea>
				</tr>
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

