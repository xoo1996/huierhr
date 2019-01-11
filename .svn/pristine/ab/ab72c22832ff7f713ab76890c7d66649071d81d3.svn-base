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
<script src='/huiermis/js/Globals.js'></script>
<script language="javascript">
	$(document).ready(function(){
		//$("select[name=folischg]").val("1");
		$("select[name=folurgischg]").val("0");
		$("input[name=total]").val(parseFloat($("input[name=balance]").val()));
		$('#folurgischg').change(function() {
			if ($('#folurgischg').val() == '1') {
				 var balance=$('input[name=balance]').val();//应付余额
				 var folurgfee=$('input[name=folurgfee]').val();//加急费
				 //var xubaofee=$('input[name=xubaofee]').val();//续保费
				 var total=parseFloat(balance)+parseFloat(folurgfee);
				 $("input[name=total]").val(total);
	    	}
			else{
				var balance=$('input[name=balance]').val();//应付余额
				 //var xubaofee=$('input[name=xubaofee]').val();//续保费
				 var total=parseFloat(balance);
				$("input[name=total]").val(total);
			}
     
			});
	});
	function compute1(obj) {
		 var pdtprc = $('input[name=pdtprc]').val();
		 var discount=$('input[name=discount]').val();
		 var deposit=$('input[name=deposit]').val();//定金
		 var folurgfee=$('input[name=folurgfee]').val();//加急费
		 var xubaofee=$('input[name=xubaofee]').val();//续保费
		 var fdtprc=(parseFloat(pdtprc)*parseFloat(discount)).toFixed(2);
		 $('input[name=fdtprc]').val(fdtprc);//售价
		 var balance=parseFloat(fdtprc)+parseFloat(xubaofee)-parseFloat(deposit);
		 $('input[name=balance]').val(balance);
		 if ($('#folurgischg').val() == '1') {
			 var total=parseFloat(balance)+parseFloat(folurgfee);
		} else {
			var total=parseFloat(balance);
		}
		 $("input[name=total]").val(total);	
		/*  alert(parseFloat(dgnrdct)); */
	}
	function compute2(obj) {
		 var pdtprc = $('input[name=pdtprc]').val();//原价
		 var fdtprc=$('input[name=fdtprc]').val();//现价
		 var deposit=$('input[name=deposit]').val();//定金
		 var folurgfee=$('input[name=folurgfee]').val();//加急费
		 var xubaofee=$('input[name=xubaofee]').val();//续保费
		 var value =(parseFloat(fdtprc)/parseFloat(pdtprc));
		 $('input[name=discount]').val(value.toFixed(2));
		 var balance=parseFloat(fdtprc)+parseFloat(xubaofee)-parseFloat(deposit);
		 $('input[name=balance]').val(balance);
		/*  alert(parseFloat(dgnrdct)); */
		 if ($('#folurgischg').val() == '1') {
			 var total=parseFloat(balance)+parseFloat(folurgfee);
		} else {
			var total=parseFloat(balance);
		}
		 $("input[name=total]").val(total);	
	}
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("确实要收费吗？")) {
			obj.submit();
		}
		else
			return t;
	};
	

</script>



<lemis:body>
	<lemis:base />
    <lemis:title title="定制机收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveCustomizedCharge"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="订单号" disable="true" required="true" 
					 maxlength="6" />
				
				<lemis:codelisteditor type="foltype" isSelect="false" label="订单类型" redisplay="true" required="true" />
				<lemis:texteditor property="folctnm" label="送制单位" disable="true"
					required="true" />
				<html:hidden property="folctid"/>
			</tr>
			<tr>
			
				<%-- <lemis:codelisteditor type="folischg" isSelect="true" label="是否已收费"
					redisplay="true" required="true" /> --%>
				<lemis:texteditor property="cltnm" label="用户姓名" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				<lemis:texteditor property="pdtnm" label="定制机型号" disable="true"
					required="false"/>
				<html:hidden property="pdtid"/>
				<lemis:texteditor property="ictpcd" label="邮编" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
				<td>收费日期</td>
				<td><lemis:operdate/></td>
				<lemis:texteditor property="ictaddr" label="用户地址" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			
			<tr>
				<lemis:texteditor property="pdtprc" label="原价" disable="true"
						required="false"  />
			   
                <lemis:texteditor required="false" property="discount"  label="扣率" disable="false" onblur="compute1()"/> 
                 
				<lemis:texteditor property="fdtprc"  label="售价" disable="false"
						required="false"  onblur="compute2()" />
			</tr>
			<tr>	
				<lemis:texteditor property="deposit" label="定金" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor property="xubaofee"  label="续保费"
					required="true" />
				<lemis:texteditor property="balance"  label="应付余额" disable="false" 
						onclick="this.blur()" required="true" />
			</tr>
			<tr>
						
				<%-- <lemis:formateditor mask="########" property="balance"  label="实收余额" disable="false" 
						required="true" /> --%>
						
				<lemis:texteditor property="folurgfee" label="加急费" required="true"
					disable="true" maxlength="30" />
				<lemis:codelisteditor type="folurgischg" isSelect="true" label="是否收加急费"
					redisplay="true" required="true" />
				
			</tr>
			<tr>
		    	<lemis:texteditor property="total" label="总计" required="false"
					disable="false" onclick="this.blur()" maxlength="30" />
				<lemis:texteditor property="folnt" label="备注" required="false"
					disable="false" colspan="3" maxlength="80" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


