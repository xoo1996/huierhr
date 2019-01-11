<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","���","clt020201","examine(document.forms[0])"));
/* buttons.add(new Button("jwdz","����������","ord110001","disExa(document.forms[0])"));   */
buttons.add(new Button("jcdz","����","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","����","clt020203","history.back()"));

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtprc","����",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","����"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","��Ʒ����","true"));
	batchInput.add(new Editor("text","pdtnm","��Ʒ����","true"));
	batchInput.add(new Editor("money","fdtprc","����","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","����","true"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "���˿ͻ����");
	hidden.put("folno","������");
	hidden.put("dgnlid","���ƻ��ͺ�");
	hidden.put("dgnrid","�Ҷ��ƻ��ͺ�");
	hidden.put("dgnid","�����ˮ��");
	hidden.put("folsta","����״̬");
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	pageContext.setAttribute("hidden", hidden);
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&folsta="/>' + $('input:hidden[name=folsta]').val();/*&repfolid="/>' + $('input:hidden[name=folno]').val();*/
        if(confirm("ȷ��Ҫ����������"))
        {
    		obj.submit(); 
        }
	};
	
	function examine(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=e&repfolid="/>' + $('input:hidden[name=folno]').val();
		if(confirm("ȷ���������ͨ����"))
	    {
	        obj.submit(); 
	    }
	}
	
	function disExa(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/order&page=/disExa.jsp"/>';
		obj.submit();
	};
	
	function computeL(obj) {
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgnldct=$('input[name=dgnldct]').val();
		$('input[name=dgncldprc]').val(parseFloat(dgnldprc)*parseFloat(dgnldct));
	}
	
	function computeR(obj) {
		 var dgnrdprc = $('input[name=dgnrdprc]').val();
		 var dgnrdct=$('input[name=dgnrdct]').val();
		/*  alert(dgnrdprc); */
		 $('input[name=dgncrdprc]').val(parseFloat(dgnrdprc)*parseFloat(dgnrdct));
		/*  alert(parseFloat(dgnrdct)); */
	}
	 $(document).ready(function(){
	//�������������
	$('#delayleft').change(function(){
		var price = 25;
		var delay = $('#delayleft').val();
		$('input[name=feeleft]').val(price*delay);

	});
	$("input[name=feeleft]").attr("readonly","true"); 
	//�Ҷ�����������
	$('#delayright').change(function(){
		var price = 25;
		var delay = $('#delayright').val();
		$('input[name=feeright]').val(price*delay);

	});
	$("input[name=feeright]").attr("readonly","true"); 
	 })
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="���ƶ�������" />
	<html:form action="/OrderAction.do?" method="POST">
	
		<%-- <lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
			    <html:hidden property="foltype"/>
				<lemis:texteditor property="folno" label="������" disable="true" />
				<lemis:texteditor property="gctnm" label="��������" disable="true" />
				<lemis:formateditor required="false" property="foldt" mask="date" label="��������" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="��ϵ�绰" disable="true" />
			  <lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="true" />
			  <lemis:codelisteditor type="ictphis" label="ʹ�ù�����������"></lemis:codelisteditor>
			 <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="��Դ" disable="true" />
			  <lemis:texteditor property="ictnt" label="��ע" disable="true" />
			  <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" />
			</tr>
	</table> --%>
	
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="�û�����" disable="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true" />
				<%-- <lemis:texteditor property="ictbdt" label="��������" disable="true" /> --%>
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="��������" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="��ϵ�绰" disable="true" />
			  <lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="true" />
			<%--   <lemis:codelisteditor type="ictphis" label="ʹ�ù�����������"></lemis:codelisteditor> --%>
			 <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="��Դ" disable="true" />
			  <lemis:texteditor property="ictnt" label="��ע" disable="true" />
			  <lemis:codelisteditor type="ictfrom" label="��Դ" required="true" />
			 <%--  <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" /> --%>
			</tr>
	</table>
	
		<lemis:tabletitle title="����ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
					/>
					<lemis:texteditor property="lg500" label="500" required="false"
					 />
					 <lemis:texteditor property="lg750" label="750" required="false"
					 />
					<lemis:texteditor property="lg1000" label="1000" required="false"
					 />
					 <lemis:texteditor property="lg1500" label="1500" required="false"
					 />
					<lemis:texteditor property="lg2000" label="2000" required="false"
					/>
					<lemis:texteditor property="lg3000" label="3000" required="false"
					 />
					<lemis:texteditor property="lg4000" label="4000" required="false"
					 onblur="lgvag()" />
					 <lemis:texteditor property="lg6000" label="6000" required="false"
					 onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
					 />
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						 style="border-width:1px;border-color=black" />
						 <lemis:texteditor property="lq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="lqvag()" />
						 <lemis:texteditor property="lq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
						 style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						/>
					<lemis:texteditor property="rg500" label="500" required="false"
						 />
				 <lemis:texteditor property="rg750" label="750" required="false"
						 />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						/>
				<lemis:texteditor property="rg1500" label="1500" required="false"
						 />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						 />
				<lemis:texteditor property="rg3000" label="3000" required="false"
						 />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						 onblur="rgvag()" />
				<lemis:texteditor property="rg6000" label="6000" required="false"
						 />
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
						/>
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="rqvag()"/>
						 <lemis:texteditor property="rq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
						style="border-width:1px;border-color=black" />
				</tr>
			</table>
			
			<table id="iframediv" border="0">
		    <tr>
			<td><iframe
				src='<html:rewrite page="/CustomOrderAction.do?method=showLeft"/>'
				id="iframe1" name="iframe1" height="320" frameborder="0"
				width="100%"></iframe></td>
			<td><iframe
				src='<html:rewrite page="/CustomOrderAction.do?method=showRight"/>'
				id="iframe2" name="iframe2" height="320" frameborder="0"
				width="100%"></iframe></td>
		    </tr>
	       </table>
    
 
			<lemis:tabletitle title="����������Ϣ" />
			<table class="tableinput">
           <lemis:editorlayout />
           <tr>
           <lemis:texteditor property="folno" label="������" disable="true" />
           </tr>
           <tr>
			    <html:hidden property="foltype"/>
				<lemis:texteditor property="gctnm" label="���Ƶ�λ" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="��������" disable="true"></lemis:texteditor>
				 <lemis:codelisteditor type="folurgent" label="�Ƿ�Ӽ�����"  required="true"></lemis:codelisteditor>
				</tr>
				<tr>
			 <%--  <lemis:texteditor property="foldelay" label="�ӳ����޵�����" disable="false" /> --%>
			  <%-- <lemis:texteditor property="foldps" label="����" disable="false" ></lemis:texteditor> --%>
			   <lemis:texteditor property="folnt" label="��ע" required="false" disable="false" />
			   <lemis:texteditor property="dgndoc" label="����ʦ����" required="false"
						disable="false" />
				</tr>
			</table>
			 <lemis:tabletitle title="���" />
			<table class="tableinput">

            <lemis:editorlayout />
            <tr>
                    <html:hidden property="dgnid" /> 
					<lemis:texteditor property="dgnlnm" label="���ƻ��ͺ�" required="false"
						disable="false" />
					<html:hidden property="dgnlid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpl" label="�������"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folldps" mask="money" label="�������" disable="false" />
					
				</tr>
				
				<tr>
					<lemis:texteditor property="dgnldprc" label="ԭ��" required="false"
						disable="false" />
					 <lemis:texteditor property="dgnldct" label="����" required="false"
						disable="false" onblur="computeL()"/> 
					<%-- <lemis:formateditor required="false" property="dgnldct" mask="n.nn" label="����" disable="false" onblur="computeL()"/>  --%>
					<lemis:texteditor property="dgncldprc" label="�ּ�" required="false" disable="false"/>
				</tr>
				<lemis:codelisteditor  type="delayleft" label="�ӳ����޵����ޣ��£�" required="false" /><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeleft"  label="������"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isleft" label="�����Ƿ�����" required="false" />
			</table>
			
	 	 <lemis:tabletitle title="�Ҷ�" />
			<table class="tableinput">

            <lemis:editorlayout />
             <tr>
					<lemis:texteditor property="dgnrnm" label="���ƻ��ͺ�" required="false"
						disable="false" />
					<html:hidden property="dgnrid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpr" label="�������" dataMeta="r"/> --%>
					<lemis:formateditor required="false" property="folrdps" mask="money" label="�Ҷ�����" disable="false" />
					
				</tr>
				<tr>
					<lemis:texteditor property="dgnrdprc" label="ԭ��" required="false"
						disable="false" />
						 <lemis:texteditor property="dgnrdct" label="����" required="false"
						disable="false" onblur="computeR()"/> 
						<%-- <lemis:formateditor required="false" property="dgnrdct" mask="n.nn" label="����" disable="false" onblur="computeR()"/> --%>
						<lemis:texteditor property="dgncrdprc" label="�ּ�" required="false" disable="false"/>
				</tr>
				<lemis:codelisteditor  type="delayright" label="�ӳ����޵����ޣ��£�" required="false" /><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeright"  label="������"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="�����Ƿ�����" required="false" />
			</table>
			
			<lemis:tabletitle title="����Ҫ��" />
		    <table class="tableinput">

            <lemis:editorlayout />
             <tr>
						<lemis:codelisteditor type="dgnpulwire" label="����"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnairvent" label="ͨ����"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnvoswitch" label="��������"></lemis:codelisteditor>

            </tr>
				<tr>       
				        <lemis:texteditor property="dgnsdmd" label="����Ҫ��" required="false"
						disable="false" />

            </tr>
			</table>
            <lemis:tabletitle title="��������" />
		   

            <lemis:editorlayout />
             <table class="tableinput" >
            <tr>
					<html:radio property="type" value="h" indexed="0" >�ݶ�����</html:radio>
					<html:radio property="type" value="j" >���Ŷ���</html:radio>
					<html:radio property="type" value="c" >�ĳ�����</html:radio>
            </tr>
			</table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>