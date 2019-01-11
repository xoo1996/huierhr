<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("ȷ��","add(document.all.orderHeaderForm)"); 
    buttons.put("����","history.back()"); 
    
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
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
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

		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		if(grCli=="1501000000")
		{
			var shops = "<%=session.getServletContext().getAttribute("grCliList")%>".replace("{","").replace("}","").split(", ");
			$("input[name=ictgctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0];
				}
			});
			
			$("input[name=ictgctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1);
					$("input[name=ictgctnm]").val(gnm);
					$(this).parent().next().find("input").val(gid);
				}
			});
		}
		else
		{
			$("input[name=ictgctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=ictgctnm]").attr("readonly","true");
			$("input:hidden[name=ictgctid]").val("<%=dto.getBsc011()%>");
	 	}
		
		
		
		
	});
</script>
 <script language="javascript">
	
function lgvag() {
		
	var l1 = $("input[name=lg250]").val()==""?0:$("input[name=lg250]").val();
	var l2 = $("input[name=lg500]").val()==""?0:$("input[name=lg500]").val();
	var l3 = $("input[name=lg1000]").val()==""?0:$("input[name=lg1000]").val();
	var l4 = $("input[name=lg2000]").val()==""?0:$("input[name=lg2000]").val();
	var l5 = $("input[name=lg4000]").val()==""?0:$("input[name=lg4000]").val();
    //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
    var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
	$("input[name=lgavg]").val(lvag.toFixed(2));
	};
    function lqvag() {
		
    	var l1 = $("input[name=lq250]").val()==""?0:$("input[name=lq250]").val();
		var l2 = $("input[name=lq500]").val()==""?0:$("input[name=lq500]").val();
		var l3 = $("input[name=lq1000]").val()==""?0:$("input[name=lq1000]").val();
		var l4 = $("input[name=lq2000]").val()==""?0:$("input[name=lq2000]").val();
		var l5 = $("input[name=lq4000]").val()==""?0:$("input[name=lq4000]").val();
        //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
         var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lqavg]").val(lvag.toFixed(2));
	};
	 function rgvag() {
			
			var l1 = $("input[name=rg250]").val()==""?0:$("input[name=rg250]").val();
			var l2 = $("input[name=rg500]").val()==""?0:$("input[name=rg500]").val();
			var l3 = $("input[name=rg1000]").val()==""?0:$("input[name=rg1000]").val();
			var l4 = $("input[name=rg2000]").val()==""?0:$("input[name=rg2000]").val();
			var l5 = $("input[name=rg4000]").val()==""?0:$("input[name=rg4000]").val();
			//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
	        var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
			$("input[name=rgavg]").val(lvag.toFixed(2));
		};
	    function rqvag() {
			
			var l1 = $("input[name=rq250]").val()==""?0:$("input[name=rq250]").val();
			var l2 = $("input[name=rq500]").val()==""?0:$("input[name=rq500]").val();
			var l3 = $("input[name=rq1000]").val()==""?0:$("input[name=rq1000]").val();
			var l4 = $("input[name=rq2000]").val()==""?0:$("input[name=rq2000]").val();
			var l5 = $("input[name=rq4000]").val()==""?0:$("input[name=rq4000]").val();
			//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
	        var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
			$("input[name=rqavg]").val(lvag.toFixed(2));
		};
	
 	function add(obj) {
 		if (!checkValue(obj)) {
			return false;
		}
		obj.submit(); 
		/* window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/order/CustomOrderAction.do?method=inputOrder&tp=cus&"+getAlldata(document.all.orderHeaderForm); */
	};  
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="�����ͻ�" />
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	
	<table class="tableInput">
		<lemis:editorlayout />
			<html:form action="/CustomOrderAction.do?method=inputOrder&tp=cus" method="POST">
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictgctnm" label="��������" disable="false" required="true"/>
				<html:hidden property="ictgctid"/>
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
			<tr>
			<lemis:codelisteditor type="ictfrom" label="��Դ" required="true" />
			</tr>


			<lemis:tabletitle title="����ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg500" label="500" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg750" label="750" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg1000" label="1000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg1500" label="1500" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg2000" label="2000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg3000" label="3000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg4000" label="4000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg6000" label="6000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
						disable="false" onclick="lgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq750" label="750" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1500" label="1500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq3000" label="3000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq6000" label="6000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg500" label="500" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg750" label="750" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg1500" label="1500" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg3000" label="3000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg6000" label="6000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
						disable="false" onclick="rgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq750" label="750" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq6000" label="6000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="rqvag()" />
				</tr>
			</table>
		</html:form>  
 	</table>
	<lemis:buttons buttonMeta="button" />
		
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>