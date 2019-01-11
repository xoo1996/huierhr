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
    
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","审核","clt020201","examine(document.forms[0])"));
buttons.add(new Button("jcdz","回退","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","返回","clt020203","history.back()"));
	
	//List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "folno", "订单号"));

	 //pageContext.setAttribute("editors", editors);
    pageContext.setAttribute("button", buttons);
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folsta","订单状态");
	pageContext.setAttribute("hidden", hidden);
	
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
	});
	function examine(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action='<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=e&type=cus"/>';
		 if(confirm("确定订单审核通过吗？"))
	        {
	    		obj.submit(); 
	        }
	};
	
	function rollback(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action='<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&type=cus&folsta="/>' + $('input:hidden[name=folsta]').val();
		 if(confirm("确定要订单回退吗？"))
	        {
	    		obj.submit(); 
	        }
	};

</script>



<lemis:body>
	<lemis:base />
    <lemis:title title="定制机收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=examineOrder"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="订单号" disable="true" required="true" 
					 maxlength="6" />
				
				<lemis:texteditor property="foltype" label="订单类型" disable="true"
					required="true" maxlength="20" />
				<lemis:texteditor property="folctnm" label="送制单位" disable="true"
					required="true" />
				<html:hidden property="folctid"/>
			</tr>
			<tr>
			
				<lemis:codelisteditor type="folischg" isSelect="false" label="是否已收费"
					redisplay="true" required="true" />
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
				<lemis:formateditor required="true" property="folrecdt" mask="date" label="退机日期"/>
				<lemis:formateditor required="true" property="folchgdt" mask="date" label="收费日期"/>
				<lemis:texteditor property="ictaddr" label="用户地址" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			
			<tr>
				<lemis:texteditor property="pdtprc" label="原价" disable="true"
						required="false"  />
				<lemis:texteditor property="discount" label="扣率" disable="true"
						required="false"  />
				<lemis:texteditor property="deposit" label="定金" required="true"
					disable="true" maxlength="30" />
			</tr>
			<tr>
						
				<%-- <lemis:formateditor mask="########" property="balance"  label="实收余额" disable="false" 
						required="true" /> --%>
				<lemis:texteditor property="balance"  label="实收余额" disable="true" 
						required="true" />		
				<lemis:texteditor property="folnt" label="备注" required="false"
					disable="false" colspan="3" maxlength="80" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


