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
buttons.add(new Button("hedz","���","clt020201","examine(document.forms[0])"));
buttons.add(new Button("jcdz","����","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","����","clt020203","history.back()"));
	
	//List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "folno", "������"));

	 //pageContext.setAttribute("editors", editors);
    pageContext.setAttribute("button", buttons);
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folsta","����״̬");
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
		 if(confirm("ȷ���������ͨ����"))
	        {
	    		obj.submit(); 
	        }
	};
	
	function rollback(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action='<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&type=cus&folsta="/>' + $('input:hidden[name=folsta]').val();
		 if(confirm("ȷ��Ҫ����������"))
	        {
	    		obj.submit(); 
	        }
	};

</script>



<lemis:body>
	<lemis:base />
    <lemis:title title="���ƻ��շ�����" />
	<lemis:tabletitle title="�շ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=examineOrder"
			method="POST">
			<html:hidden property="folno" />
			<tr>
				<lemis:texteditor  property="folno" label="������" disable="true" required="true" 
					 maxlength="6" />
				
				<lemis:texteditor property="foltype" label="��������" disable="true"
					required="true" maxlength="20" />
				<lemis:texteditor property="folctnm" label="���Ƶ�λ" disable="true"
					required="true" />
				<html:hidden property="folctid"/>
			</tr>
			<tr>
			
				<lemis:codelisteditor type="folischg" isSelect="false" label="�Ƿ����շ�"
					redisplay="true" required="true" />
				<lemis:texteditor property="cltnm" label="�û�����" disable="true"
					 required="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true"
					 required="true"/>
			</tr>
			<tr>
				
				<lemis:texteditor property="pdtnm" label="���ƻ��ͺ�" disable="true"
					required="false"/>
				<lemis:texteditor property="ictpcd" label="�ʱ�" required="false"
					disable="true" maxlength="10" />
				<lemis:texteditor property="icttel" label="��ϵ�绰" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
				<lemis:formateditor required="true" property="folrecdt" mask="date" label="�˻�����"/>
				<lemis:formateditor required="true" property="folchgdt" mask="date" label="�շ�����"/>
				<lemis:texteditor property="ictaddr" label="�û���ַ" disable="true"
					required="false" colspan="3" maxlength="80" />
			</tr>
			
			<tr>
				<lemis:texteditor property="pdtprc" label="ԭ��" disable="true"
						required="false"  />
				<lemis:texteditor property="discount" label="����" disable="true"
						required="false"  />
				<lemis:texteditor property="deposit" label="����" required="true"
					disable="true" maxlength="30" />
			</tr>
			<tr>
						
				<%-- <lemis:formateditor mask="########" property="balance"  label="ʵ�����" disable="false" 
						required="true" /> --%>
				<lemis:texteditor property="balance"  label="ʵ�����" disable="true" 
						required="true" />		
				<lemis:texteditor property="folnt" label="��ע" required="false"
					disable="false" colspan="3" maxlength="80" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


