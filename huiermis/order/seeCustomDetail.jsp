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
//List<Button> buttons=new ArrayList<Button>();
//buttons.add(new Button("back","����","ord020003","history.back()"));
//pageContext.setAttribute("button", buttons);

Map<String,String> buttons = new LinkedHashMap<String,String>();
buttons.put("����", "history.back()");
pageContext.setAttribute("button", buttons);

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
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&"/>';
		obj.submit();
	};
	
	function examine(obj){
		obj.submit(); 
	}
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
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="���ƶ�������" />
	<html:form action="/OrderAction.do?method=examineOrder&tp1=e" method="POST">
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="�û�����" disable="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true" />
				<%-- <lemis:texteditor property="ictbdt" label="��������" disable="true" /> --%>
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="��������" format="true" disable="true"/>
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
              <html:hidden property="foltype"/>
            <lemis:texteditor property="folno" label="������" disable="true" />
            </tr>
			<tr>
				
				<lemis:texteditor property="gctnm" label="���Ƶ�λ" disable="true"></lemis:texteditor>
				<%-- <td>��������</td>
				<td><lemis:operdate /></td> --%>
				<lemis:formateditor required="false" property="foldt" mask="date" label="��������" format="true"/>
				<lemis:codelisteditor type="folurgent" label="�Ƿ�Ӽ�����" isSelect="false"></lemis:codelisteditor>
			</tr>
			<tr>
				<lemis:texteditor property="foldelay" label="�ӳ����޵�����" disable="true" />
				<%-- <lemis:texteditor property="foldps" label="����" disable="false" ></lemis:texteditor> --%>

				<lemis:texteditor property="folnt" label="��ע" required="false"
					disable="true" />
				<lemis:texteditor property="dgndoc" label="����ʦ����" required="false"
					disable="true" />
			</tr>
			</table>
			
			 <lemis:tabletitle title="���" />
			<table class="tableinput">

            <lemis:editorlayout />
            <tr>
                    <html:hidden property="dgnid" /> 
					<lemis:texteditor property="dgnlnm" label="���ƻ��ͺ�" required="false"
						disable="true" />
						
					<html:hidden property="dgnlid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpl" label="�������"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folldps" mask="money" label="�������" disable="true" />
					

				</tr>
				
				<tr>
					<lemis:texteditor property="dgnldprc" label="ԭ��" required="false"
						disable="true" />
					 <lemis:texteditor property="dgnldct" label="����" required="false"
						disable="true" onblur="computeL()"/> 
					<%-- <lemis:formateditor required="false" property="dgnldct" mask="n.nn" label="����" disable="false" onblur="computeL()"/>  --%>
					<lemis:texteditor property="dgncldprc" label="�ּ�" required="false" disable="true"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayleft" label="�ӳ����޵����ޣ��£�" required="false" isSelect="false"/><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeleft"  label="������"   required="false" maxlength="100" disable="true"/>
			  	 <lemis:codelisteditor  type="isleft" label="�����Ƿ�����" required="false" isSelect="false"/>
			  	 </tr>
			</table>
			
	 	 <lemis:tabletitle title="�Ҷ�" />
			<table class="tableinput">

            <lemis:editorlayout />
             <tr>
					<lemis:texteditor property="dgnrnm" label="���ƻ��ͺ�" required="false"
						disable="true" />
					<html:hidden property="dgnrid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpr" label="�������" dataMeta="r"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folrdps" mask="money" label="�Ҷ�����" disable="true" />
					
				</tr>
				<tr>
					<lemis:texteditor property="dgnrdprc" label="ԭ��" required="false"
						disable="true" />
					<lemis:texteditor property="dgnrdct" label="����" required="false"
						disable="true" onblur="computeR()"/> 
					<%-- <lemis:formateditor required="false" property="dgnrdct" mask="n.nn" label="����" disable="false" onblur="computeR()"/> --%>
					<lemis:texteditor property="dgncrdprc" label="�ּ�" required="false" disable="true"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayright" label="�ӳ����޵����ޣ��£�" required="false" isSelect="false"/><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeright"  label="������"   disable="true" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="�����Ƿ�����" required="false" isSelect="false"/>
			  	 </tr>
			</table>
			
			<lemis:tabletitle title="����Ҫ��" />
		    <table class="tableinput">

            <lemis:editorlayout />
             <tr>
						<lemis:codelisteditor type="dgnpulwire" label="����" isSelect="false"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnairvent" label="ͨ����" isSelect="false"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnvoswitch" label="��������" isSelect="false"></lemis:codelisteditor>

            </tr>
				<tr>       
				        <lemis:texteditor property="dgnsdmd" label="����Ҫ��" required="false"
						disable="true" />

            </tr>
				
			</table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>