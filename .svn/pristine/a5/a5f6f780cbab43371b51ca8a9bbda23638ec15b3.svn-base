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
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		};
</script>



<lemis:body>
	<lemis:base />
	<lemis:errors />
    <lemis:title title="定制机收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=print2" method="POST">
			<tr>
				<lemis:texteditor  property="folno" label="订单号" disable="true" required="true" 
					 maxlength="6" />
				<lemis:texteditor property="foltype" label="订单类型" disable="true"
					required="true" maxlength="20" />
				<lemis:texteditor property="folctnm" label="送制单位" disable="true"
					required="true" />
			</tr>
			<tr>
			
				<lemis:texteditor  property="folischg"  label="是否已收费"
					disable="true" required="true" />
				<lemis:texteditor property="cltnm" label="用户姓名" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				
				<lemis:texteditor property="pdtnm" label="定制机型号" disable="true"
					required="false"/>
				<lemis:texteditor property="ictpcd" label="邮编" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date" format="true" label="收费日期"
					property="chgdt"  required="true" disable="true" />
				<lemis:texteditor property="ictaddr" label="用户地址" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			<tr>
				
				<lemis:texteditor property="pdtprc" label="原价" disable="true"
					required="false"  />
				<lemis:texteditor property="discount" label="扣率" disable="true"
						required="false"  />
				<lemis:texteditor property="fdtprc" label="售价" disable="true"
					required="false"  />
			</tr>
			<tr>
				<lemis:texteditor property="deposit" label="定金" required="true"
					disable="true" maxlength="30" />
			    <lemis:texteditor property="xubaofee" label="续保费" required="true"
					disable="true" />
				<lemis:texteditor property="balance"  label="实收余额" disable="true" 
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="folurgfee" label="加急费" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor  property="folurgischg"  label="是否收加急费"
					disable="true" required="true" />
				
				
			</tr>
			<tr>
				<lemis:texteditor property="folnt" label="备注" required="false"
					disable="true" colspan="5" maxlength="80" />
				
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


