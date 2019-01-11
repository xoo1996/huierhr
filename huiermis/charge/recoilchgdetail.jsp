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
    buttons.put("保 存","saveData(document.forms[0])");
	buttons.put("返 回","history.back()");
	
	//List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "folno", "订单号"));

	 //pageContext.setAttribute("editors", editors);
    pageContext.setAttribute("button", buttons);
%>


<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('select[name=folischg]').val("1");
		$('input[name=fdtdprc]').attr("readonly","readonly");
		$('input[name=balance]').bind('blur',function(){
			var fdtprc = $('input[name=fdtprc]').val();
			var balance = $('input[name=balance]').val();
			$('input[name=fdtdprc]').val(fdtprc-balance);
		});
	});
</script>

<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>



<lemis:body>
	<lemis:base />
    <lemis:title title="定制机收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveRecoilCharge"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="订单号" disable="true" required="true" 
					 maxlength="6" />
				
				<lemis:texteditor property="foltype" label="订单类型" disable="true"
					required="true" maxlength="20" />
				<lemis:texteditor property="folctnm" label="送制单位" disable="true"
					required="true" />
			</tr>
			<tr>
			
				<lemis:codelisteditor type="folischg" isSelect="true" label="是否已收费"
					redisplay="true" required="true" />
				<lemis:texteditor property="cltnm" label="用户姓名" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				<%-- <lemis:texteditor property="deposit" label="定金" required="true"
					disable="true" maxlength="30" /> --%>
				<lemis:texteditor property="ictpcd" label="邮编" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="true" maxlength="20" />
				<td>收费日期</td>
				<td><lemis:operdate/></td>
			</tr>
			<tr>
				<lemis:texteditor property="pdtnm" label="定制机型号" disable="true"
					required="false"/>
				<lemis:texteditor property="ictaddr" label="用户地址" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			<tr>
				<html:hidden property="pdtid" />
				<html:hidden property="pdtut"/>
				<lemis:texteditor property="fdtprc" label="售价" disable="true"
					required="false"  />
				<lemis:texteditor property="balance" label="退机费" disable="false"
					required="true"  />
				<lemis:texteditor property="fdtdprc" label="退回用户费用" disable="false" 
					required="false"  />
				<%--  <lemis:formateditor mask="########" property="balance"  label="退机费" disable="false" 
					required="true" /> --%>
				
			</tr>
			<tr>
				<lemis:texteditor property="folnt" label="备注" required="false"
					disable="false" colspan="5" maxlength="80" />
				
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


