<!-- basicinfo/writeOffEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
   Map buttons = new LinkedHashMap();
   buttons.put("注销","writeOffEmployer(document.all.employerForm)");
   String url=request.getParameter("url");
   if (url==null) {
     url="";
   }
   if (!"".equals(url)) {
   buttons.put("返回","javascript:location.href=\""+url.replaceAll(";amp;","&")+"\"");
   }
   buttons.put("关 闭","closeWindow(\"queryEmployerForm,employerForm\")");
   pageContext.setAttribute("buttons",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="注销单位"/>
		<html:form action="/employerAction.do?method=viewEmp" method="post">
			<lemis:tabletitle title="单位基本信息"/>
			<table class="TableInput">
				<lemis:editorlayout/>
				<html:hidden property="aab001"/>
				<tr>
					<lemis:texteditor property="aab003" label="组织机构号码" />
					<lemis:texteditor property="aab004" label="单位名称" colspan="3" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aab019" label="单位类型" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab023" label="主管部门" required="false" isSelect="false"/>
				</tr>
				<tr>
					<td>注册资金</td>
					<td colspan="1" >
						<table class="TableInput">
							<td>
								<lemis:text property="aab049" label="注册资金"/>
							</td>
							<td width="30%">万元</td>
						</table>
					</td>
					<lemis:codelisteditor type="aab022" label="行业类型" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab054" label="产业类别" required="false" isSelect="false"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aab021" label="隶属关系" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab020" label="经济类型" required="false" isSelect="false"/>
					<lemis:codelisteditor type="aab056" label="人员规模" redisplay="true" isSelect="false"/>
				</tr>
				<tr>
					<lemis:texteditor property="aab013" label="法定代表人"/>
					<lemis:texteditor property="aab014" label="法定代表公民身份号码"/>
					<lemis:texteditor property="aae004" label="联系人"/>
				</tr>
				<tr>
					<lemis:texteditor property="aae005" label="联系电话"/>
					<lemis:texteditor property="aae014" label="传真"/>
					<lemis:texteditor property="aae015" label="电子邮箱"/>
				</tr>
				<tr>
					<lemis:texteditor property="aaa021" label="单位所在区街" />
					<html:hidden property="aab301"/>
					<lemis:texteditor property="aae006" label="地址" colspan="3"/>
					<!--
					//因指标体系调整,此处删去一行
					 -->
				</tr>
				<tr>
					<lemis:texteditor property="aae007" label="邮政编码"/>
					<lemis:texteditor property="aae016" label="单位网址" colspan="3"/>
				<html:hidden property="aae119"/>
				</tr>
				<tr>
				</tr>
				<tr>
					<lemis:codelisteditor type="aab006" label="工商登记执照种类" required="false" isSelect="false"/>
					<lemis:texteditor property="aab007" label="工商登记执照号码"/>
					<lemis:texteditor property="aab008" label="工商登记发照日期"/>
				</tr>	
				<tr>
					<lemis:formateditor mask="nnn" property="aab009" label="工商登记有效期限(年)" disable="true" required="false"/>
					<lemis:texteditor property="aab010" label="批准成立单位"/>
					<lemis:texteditor property="aab011" label="批准日期"/>
				</tr>
				<tr>
				</tr>
			</table>
			<lemis:tabletitle title="单位注销信息"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property='aae119' />  
			<tr>
				<lemis:formateditor property="aab101" mask="date" label="注销日期" colspan="2" disable="false" required="true"/>
				<lemis:codelisteditor type="aab100" colspan="2" isSelect="true" label="注销原因" redisplay="true" required="true"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab102" disable="false" label="注销凭证" maxlength="50" colspan="5"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="5"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aae011" label="经办人" isSelect="false"/>
				<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false"/>
				<lemis:texteditor property="aae036" label="经办日期"/>
			</tr>
		</html:form>
		</table>
	<lemis:buttons buttonMeta="buttons"/>
	<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	//注销单位基本信息
	function writeOffEmployer(obj) {
	if (checkValue(obj)) {
	obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer"/>';
	obj.action=obj.action+'&menuId='+'<%=request.getParameter("menuId")%>';
	obj.submit();
	}
	}
</script>
