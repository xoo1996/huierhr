<!-- basicinfo/employer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<lemis:base/>
	<lemis:body><lemis:errors/>
		<%
		   Map buttons = new LinkedHashMap();
			/*   QueryInfo queryinfo=(QueryInfo)request.getAttribute("queryinfo");
		 	 	//单位登记
			  	if ("enterpriseReg".equals(menuid)&&queryinfo!=null&&queryinfo.getTotalNum()>0) {
				    buttons.put("查看","viewEmp(document.all.tableform);");
				    buttons.put("增加","addEmp(document.forms[0],document.all.tableform);");
				}
		   */
		  
		   buttons.put("返 回","javascript:window.history.back(1)");
		    buttons.put("关 闭","closeWindow(\"queryEmployerForm,employerForm\")");
		   pageContext.setAttribute("buttons",buttons);
		   String disable="false";
		%>
		<lemis:title title="单位基本信息"/>
		<html:form action="/employerAction.do?method=viewEmp" method="post">
			<lemis:tabletitle title="单位基本信息"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<tr>
				<lemis:texteditor property="aab003" label="组织机构号码" required="true" disable="<%=disable%>"/>
				<lemis:texteditor property="aab004" label="单位名称" colspan="3" required="true" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab019" label="单位类型" required="true" disable="<%=disable%>"/>
				<lemis:texteditor property="aab023" label="主管部门" required="true" disable="<%=disable%>"/>
			</tr>
			<tr>
				<td>
					注册资金
				</td>
				<td colspan="1" >
					<table class="TableInput">
						<td>
							<lemis:text property="aab049" label="注册资金"  disable="<%=disable%>"/>
						</td>
						<td width="30%" >万元</td>
					</table>
				</td>
				<lemis:texteditor property="aab022" label="行业类型" required="true" disable="<%=disable%>"/>
				<lemis:texteditor property="aab054" label="产业类别" required="true" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab021" label="隶属关系" required="true" disable="<%=disable%>"/>
				<lemis:texteditor property="aab020" label="经济类型" required="false" disable="<%=disable%>"/>
				<lemis:texteditor property="aab056" label="人员规模" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab013" label="法定代表人" disable="<%=disable%>"/>
				<lemis:texteditor property="aab014" label="法定代表公民身份号码" disable="<%=disable%>"/>
				<!--
				//因指标体系调整,此处删去一行
				-->
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<lemis:texteditor property="aae004" label="联系人" disable="<%=disable%>"/>
				<lemis:texteditor property="aae005" label="联系电话" disable="<%=disable%>"/>
				<lemis:texteditor property="aae014" label="传真" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab301" label="单位所在区" required="true" disable="<%=disable%>"/>
				<lemis:texteditor property="aae006" label="地址" required="true" colspan="3"  disable="<%=disable%>"/>
				
			</tr>
			<tr>
				<lemis:texteditor property="aae007" label="邮政编码" disable="<%=disable%>"/>
				<lemis:texteditor property="aae015" label="电子邮箱" disable="<%=disable%>"/>
				<lemis:texteditor property="aae016" label="单位网址"  disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab006" label="工商登记执照种类" disable="<%=disable%>"/>
				<lemis:texteditor property="aab007" label="工商登记执照号码" disable="<%=disable%>"/>
				<lemis:texteditor property="aab008" label="工商登记发照日期" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:formateditor mask="nnn" property="aab009" label="工商登记有效期限(年)" disable="<%=disable%>" required="false"/>
				<lemis:texteditor property="aab010" label="批准成立单位" disable="<%=disable%>" maxlength="50"/>
				<lemis:texteditor property="aab011" label="批准日期" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae008" label="银行行号" disable="<%=disable%>"/>
				<lemis:texteditor property="aae009" label="银行户名" disable="<%=disable%>"/>
				<lemis:texteditor property="aae010" label="银行帐号" disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab027" label="开户银行" disable="<%=disable%>"/>
				<lemis:texteditor property="aab029" label="支付银行基本帐号" colspan="3"  disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab030" label="税号" colspan="2"  disable="<%=disable%>"/>
				<lemis:texteditor property="aab031" label="税务机构编号" colspan="2"  disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab032" label="税务机构名称" colspan="2"  disable="<%=disable%>"/>
				<lemis:texteditor property="aab037" label="税务证批准日期(国)" colspan="2"  disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aab047" label="地税税务机构名称" colspan="2"  disable="<%=disable%>"/>
				<lemis:texteditor property="aab046" label="地税税号" colspan="2"  disable="<%=disable%>"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae013" label="备注" disable="<disable" maxlength="100" colspan="5"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae011" label="经办人" disable="<%=disable%>"/>
				<lemis:texteditor property="aae017" label="经办机构" disable="<%=disable%>"/>
				<lemis:texteditor property="aae036" label="经办日期" disable="<%=disable%>"/>
			</tr>
			<lemis:buttons buttonMeta="buttons"/>
		</html:form>
		</table>	
	<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	// 查看单位基本信息
	function viewEmp (obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/searchBaseAction.do?method=searchBase&"/>'+para;
		// 增加URL地址，并将URL地址的&符号转化成";amp;"。
		obj.action = obj.action + "&url=" + location.href.replace(/&/g,";amp;");
		obj.submit();
	}
</script>
