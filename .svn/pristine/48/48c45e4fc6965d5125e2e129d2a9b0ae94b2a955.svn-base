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
    buttons.put("退机到直属店","saveData(document.forms[0])");
    buttons.put("退机到总部","commit(document.forms[0])");
    buttons.put("打印","print(document.forms[0])");
	buttons.put("返 回","history.back()");
	
	//List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "folno", "订单号"));

	 //pageContext.setAttribute("editors", editors);
    pageContext.setAttribute("button", buttons);
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
    hidden.put("folno","订单号");
   	hidden.put("folsta","订单状态");
%>


<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

<script language="javascript">
	$(document).ready(function(){
		$("select[name=folischg]").val("1");
		var fdtrecmon=$("input[name=fdtrecmon]").val();
		if(fdtrecmon=='')
		{
			$("input[name=fdtrecmon]").attr("disable","disable");
		}
	});
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var zyer= $("input[name=fdtmklr]").val();
		if(zyer != '无' && zyer != '' && zyer != null){
			if(!confirm("退机提醒：该订单的左右耳类别为 '"+zyer+" '，是否继续？")){
				return false;
			}
		}
		if (confirm("确实要退机到直属店吗？")) {
			obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveCustomizedRec&tp=s&"/>';
			obj.submit();
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
		
	};
	
	function commit(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var zyer= $("input[name=fdtmklr]").val();
		if(zyer != '无' && zyer != '' && zyer != null){
			if(!confirm("退机提醒：该订单的左右耳类别为 '"+zyer+" '，是否继续？")){
				return false;
			}
		}
		if (confirm("确实要退机到总部吗？")) {
			obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveCustomizedRec&tp=c&"/>';
			obj.submit();
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
		
	};
	
	function print(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=printCusRec"/>';
		obj.submit();
	};

</script>



<lemis:body>
	<lemis:base />
    <lemis:title title="定制机退机收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveCustomizedCharge"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="订单号" disable="true" required="true" 
					 maxlength="6" />
	
				<lemis:codelisteditor type="foltype" label="订单类型" isSelect="false"></lemis:codelisteditor>
				<lemis:texteditor property="folctnm" label="送制单位" disable="true"
					required="true" />
				<html:hidden property="folctid"/>
				<html:hidden property="fdtmklr"/>
			</tr>
			<tr>
			
				<lemis:texteditor property="folischg"  label="是否已收费" disable="true"
					 required="true" />
				<lemis:texteditor property="cltnm" label="用户姓名" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				
				<lemis:texteditor property="ictpcd" label="邮编" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="true" maxlength="20" />
					<lemis:texteditor property="ictaddr" label="用户地址" disable="true"
					required="false" colspan="3" maxlength="80" />
					
			</tr>
			<tr>
				<html:hidden property="pdtid" />
			    <lemis:texteditor property="pdtnm" label="定制机型号" disable="true"
					required="false"/>
				<td>退机日期</td>
				<td><lemis:operdate/></td>
				<lemis:formateditor required="false" property="chgdt" mask="date" format="true" label="收费日期"/>
				
			</tr>
			
			<tr>
			    <lemis:codelisteditor type="folsta" label="状态" isSelect="false"/>
				<lemis:texteditor property="pdtprc" label="原价" disable="true"
						required="false"  />
				<lemis:texteditor property="discount" label="扣率" disable="true"
						required="false"  />
				
			</tr>
			<tr>
						
				<%-- <lemis:formateditor mask="########" property="balance"  label="实收余额" disable="false" 
						required="true" /> --%>
				<lemis:texteditor property="deposit" label="定金" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor property="folurgfee" label="加急费" required="true"
					disable="true" maxlength="30" />
				<lemis:texteditor property="xubaofee"  label="续保费" disable="true" 
						required="true" />	
						
			</tr>
			<tr>
			<lemis:texteditor property="balance"  label="实收余额" disable="true" 
						required="true" />	
			<lemis:texteditor  property="fdtmklr" label="左右耳" disable="true" required="false" 
					 maxlength="6" />
			<lemis:texteditor property="fdtrecmon"  label="退机金额" disable="false" 
						required="true" />
		
			
			</tr>
			<tr>
			<lemis:texteditor property="folnt" label="备注" required="false"
					disable="false" colspan="3" maxlength="80" />
				<html:hidden property="folsta"/>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
		<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


