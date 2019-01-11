<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("打 印","print(document.forms[0])");
	//buttons.put("返 回","history.back()");
    buttons.put("关 闭","closeWindow(\"\")");
	
    pageContext.setAttribute("button", buttons);
%>


<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

<script language="javascript">

	function print(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		
		obj.submit();
	}
</script> 



<lemis:body>
	<lemis:base />
    <lemis:title title="普通商品收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveNormalCharge"
			method="POST">
			<tr>
				<html:hidden property = "ictid"/>
				<lemis:texteditor property="folctnm" label="所属团体客户" disable="true"/>
				<lemis:texteditor property="ictnm" label="用户名称" disable="true"/>
				<lemis:texteditor property="ictgender" label="性别" disable="true"/>
			</tr>
			<tr>
				<lemis:texteditor property="ictaddr" label="用户地址" disable="true" />
				<lemis:texteditor property="icttel" label="用户电话" disable="true"/>
				<lemis:texteditor property="ictpnm" label="家长姓名" disable="true"/>
			</tr>
			
			<tr>
				<lemis:texteditor property="pdtid" label="商品代码" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor property="pdtnm" label="商品名称" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor  property="fdtprc" label="商品售价" disable="true" />
				
			</tr>
			<tr>
				<lemis:texteditor property="fdtdisc" label="商品扣率" disable="true"
					 required="true" />
				<lemis:texteditor property="fdtmqnt" label="售出数量" disable="true"
					required="true" />
				<lemis:texteditor property="fdtrprc" label="实际收费" disable="true"
					required="true" />
			</tr>
			<tr>
				<td>收费日期</td>	
				<td><lemis:operdate /></td>
				<lemis:texteditor property="chgnt" label="备注" required="false"
					disable="true" maxlength="80" colspan="3" />
			</tr>
		</html:form>
	</table>
	<lemis:table topic="商品明细录入"
		action="/OrderDetailAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


