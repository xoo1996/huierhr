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
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","add(document.forms[0])"); 


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
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->

<script type="text/javascript">
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{", "").replace("}", "").split(", ");
		
		$("input[name=ictgctid]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
				return data[0].substring(0);
			}
		});
		
		$("input[name=ictgctid]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ictgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});
</script>
 <script language="javascript">


	function lgvag() {
		
		var l1 = $("input[name=lg250]").val();
		var l2 = $("input[name=lg500]").val();
		var l3 = $("input[name=lg1000]").val();
		var l4 = $("input[name=lg2000]").val();
		var l5 = $("input[name=lg4000]").val();
        //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
        var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lgavg]").val(lvag);
	};
    function lqvag() {
		
		var l1 = $("input[name=lq250]").val();
		var l2 = $("input[name=lq500]").val();
		var l3 = $("input[name=lq1000]").val();
		var l4 = $("input[name=lq2000]").val();
		var l5 = $("input[name=lq4000]").val();
        //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
         var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lqavg]").val(lvag);
	};
    function rgvag() {
		
		var l1 = $("input[name=rg250]").val();
		var l2 = $("input[name=rg500]").val();
		var l3 = $("input[name=rg1000]").val();
		var l4 = $("input[name=rg2000]").val();
		var l5 = $("input[name=rg4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
        var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rgavg]").val(lvag);
	};
    function rqvag() {
		
		var l1 = $("input[name=rq250]").val();
		var l2 = $("input[name=rq500]").val();
		var l3 = $("input[name=rq1000]").val();
		var l4 = $("input[name=rq2000]").val();
		var l5 = $("input[name=rq4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
        var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rqavg]").val(lvag);
	};
	
 	function add(obj) {
 		if (!checkValue(obj)) {
			return false;
		}
		obj.submit(); 
	};  
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="�����ͻ�" />
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	
	<table class="tableInput">
		<lemis:editorlayout />
			<html:form action="/SingleClientAction.do?method=addClient1&type=1" method="POST">
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictgctid" label="��������" disable="false" required="true"/>
				<lemis:texteditor property="ictnm" label="�û�����" disable="false" required="true"/>
				<lemis:codelisteditor type="ictgender" label="�Ա�" required="true" />
			</tr>
			<tr>
				<lemis:formateditor required="false" property="ictbdt" mask="date"
					label="��������" format="true" disable="false" required="true"/>
				<lemis:texteditor property="icttel" label="��ϵ�绰" disable="false" required="true"/>
				<lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="false" required="true"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="ictphis" label="ʹ�ù�����������" required="true"/>
				<lemis:texteditor property="ictsrc" label="��Դ" disable="false" />
				<lemis:texteditor property="ictnt" label="��ע" disable="false" />
			</tr>


			<lemis:tabletitle title="����ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
					disable="false"/>
					<lemis:texteditor property="lg500" label="500" required="false"
					 disable="false"/>
					<lemis:texteditor property="lg1000" label="1000" required="false"
					 disable="false"/>
					<lemis:texteditor property="lg2000" label="2000" required="false"
					disable="false"/>
					<lemis:texteditor property="lg4000" label="4000" required="false"
					 disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
					 disable="false"/>
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
					disable="false"	style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq500" label="500" required="false"
					disable="false"	 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
					disable="false"	 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
					disable="false"	style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
					disable="false"	 style="border-width:1px;border-color=black" onblur="lqvag()" />
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
					disable="false"	 style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
					disable="false"	/>
					<lemis:texteditor property="rg500" label="500" required="false"
					disable="false"	 />
					<lemis:texteditor property="rg1000" label="1000" required="false"
					disable="false"	/>
					<lemis:texteditor property="rg2000" label="2000" required="false"
					disable="false"	 />
					<lemis:texteditor property="rg4000" label="4000" required="false"
					disable="false"	 onblur="rgvag()" />
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
					disable="false"	/>
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
					disable="false"	style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq500" label="500" required="false"
					disable="false"	 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
					disable="false"	style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
					disable="false"	 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
					disable="false"	 style="border-width:1px;border-color=black" onblur="rqvag()"/>
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
					disable="false"	style="border-width:1px;border-color=black" />
				</tr>
			</table>
		</html:form>  
 	</table>
	<lemis:buttons buttonMeta="button" />
		
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>