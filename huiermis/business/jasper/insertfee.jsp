<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","saveData(document.forms[0])");
    buttons.put("�� ��","document.forms[0].reset();");
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};

	Number.prototype.toFixed=function(num) 
	   { 
	   //���¹���toFixed����,IE5.0+ 
	   with(Math)this.NO=round(this.valueOf()*pow(10,num))/pow(10,num); 
	   return String(/\./g.exec(this.NO)?this.NO:this.NO+"."+String(Math.pow(10,num)).substr(1,num)); 
	   };
	function autoin () {
		var m = $("input[name=feesales]").val();
		var mon = m*0.05;
		$("input[name=feemanage]").val(mon.toFixed(2));
	};

		
	function createQueryString() {
		var queryString = {
			gctId : $("input[name=feegctid]").val(),
			year : $("input[name=feeyear]").val(),
			month : $("input[name=feemonth]").val()
		};
		return queryString;
	};
	
	function lastMonth() {
		var gctId = $("input[name=feegctid]").val();
		var year = $("input[name=feeyear]").val();
		var month = $("input[name=feemonth]").val();
		if(gctId != '' && year != '' && month != ''){
			$.getJSON("/huiermis/business/FeeAction.do?method=getLastMonth",
					createQueryString(), function(data) {
						$('input[name=feedevice]').val(data[0].feedevice);
						$('input[name=feepension]').val(data[0].feepension);
						$('input[name=feeassets]').val(data[0].feeassets);
						$('input[name=feeopen]').val(data[0].feeopen);
						$('input[name=feerent]').val(data[0].feerent);
						$('input[name=feetrans]').val(data[0].feetrans);
						$('input[name=feebuilt]').val(data[0].feebuilt);
					});	
		}
	};
	
	$(document).ready( function() {
	
       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=feegctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=feegctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=feegctid]").val(gid);
				$("input[name=feegctname]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="����ͻ�������Ϣ" />
	<lemis:tabletitle title="¼�������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/FeeAction.do?method=insert" method="POST">
		
				<tr>
				<lemis:texteditor property="feegctid" label="�ͻ�����" disable="false" 
				 required="true" />
				 <lemis:texteditor property="feegctname" label="�ͻ�����" disable="false" 
				 required="true" />
				<lemis:texteditor property="feeyear" label="��" disable="false" 
				 required="true" maxlength="4" />
				 	</tr>
			   <tr>
				<lemis:texteditor property="feemonth" label="��" disable="false" 
				 required="true" maxlength="2"  onclick="lastMonth()"/>
                <lemis:texteditor required="false" property="feesales" 
                label="���۶�" disable="false" />
                <lemis:texteditor  required="false" property="feemanage"
				 label="�����" disable="false" onclick="autoin()"/>
				  </tr>
			  <tr>
				 <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feeinvoice" 
			    label="��Ʊ˰" disable="false" />
			    <%--  <lemis:texteditor property="feedevice" label="�豸̯��" disable="false" 
				 required="false" /> --%>
			<%--  <lemis:texteditor property="feerent" label="����" disable="false" 
				 required="false" /> --%>
				<lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feetax" 
					label="˰��" disable="false" />
				 <lemis:texteditor property="feepension" label="�籣��" disable="false" 
				 required="false" />
				<%-- <lemis:texteditor property="feeassets" label="�̶��ʲ�" disable="false" 
				 required="false" /> --%>
			    </tr>
			<tr>
			 <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feepay" 
			    label="����" disable="false" />
			    			   <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feeaccount"
				 label="��ƹ���" disable="false" />
				 				<lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feeprocess"
				 label="������" disable="false" />
			<%-- <lemis:texteditor property="feebuilt" label="װ�޷�" disable="false" 
				 required="false" /> --%>
			<%--  <lemis:texteditor property="feeopen" label="�����" disable="false" 
				 required="false" /> --%>
		
		   <%--  <lemis:texteditor property="feetrans" label="ת�÷�" disable="false" 
				 required="false" /> --%>
				   </tr>
				   <tr>
		     <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feead" 
				label="����" disable="false" />
			   <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feephone" 
			   label="�绰��" disable="false" />
		      <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feewater"
			     label="ˮ���" disable="false" />
			 </tr>
		    <tr>
		       <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feetrip" 
				label="���÷�" disable="false" />
				<lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feepostage"
				 label="�ʷ�" disable="false" />
			  <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feeoffice" 
				label="�칫��" disable="false" />
								 </tr>
		    <tr>
			   <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feesocial" 
			     label="���ط�" disable="false" />	
			       <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feothersa"
				 label="��������" disable="false" cols="10"/>	
				 <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feemedical"
				 label="�Ż�" disable="false" />	
				</tr>
			<tr>		
		    <lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feepromotion"
				 label="����" disable="false" />
			
			<lemis:formateditor mask="-nnnnnnnnnn.nn" format="true" required="false" property="feeback"
				 label="�˻���" disable="false" />
			</tr>
	</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
