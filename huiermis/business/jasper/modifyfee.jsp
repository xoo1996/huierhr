<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
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
	   //重新构造toFixed方法,IE5.0+ 
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
	<lemis:title title="修改客户费用信息" />
	<lemis:tabletitle title="修改费用信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/FeeAction.do?method=saveModified" method="POST">

			<tr>
				<lemis:texteditor property="feegctid" label="客户代码" disable="true"
					required="false" />
				<lemis:texteditor property="feeyear" label="年" disable="true"
					required="true" maxlength="4" />
				<lemis:texteditor property="feemonth" label="月" disable="true"
					required="true" maxlength="2" onclick="lastMonth()" />
			</tr>
			<tr>
				<lemis:texteditor property="feesales" label="销售额" disable="false" 
				 required="true" />
				 <lemis:texteditor property="feemanage" label="管理费" disable="false" 
				 required="true" onclick="autoin()" />
			</tr>

	
			<lemis:tabletitle title="商务支出" />
			<table class="tableinput">
				<lemis:editorlayout />
				
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="bfeerent" 
					label="房租(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeepension" 
					label="社保费(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeetax" label="税收(不可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="bfeeassets" 
					label="固定资产(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeepay" 
					label="工资" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeebuilt" 
					label="装修费(不可控)" disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="bfeeopen" 
					label="开办费" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeothersa" 
					label="13%以外其他费用" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeead" 
					label="广告费(可控)" disable="false" required="false" />
				</tr>
				
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeephone" label="电话费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeewater" label="水电费(可控)" disable="false" />
						<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeetrip" label="差旅费(可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeepostage" label="邮费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeeoffice" label="办公费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
					property="bfeesocial" label="公关费(可控)" disable="false" />
				</tr>
				<tr>
					
					<lemis:formateditor mask="bigmoney" format="true" property="bfeemedical"
					label="额外费用(可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeeaccount"
					label="会计工资 " disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="bfeeothersb"
					label="13%以内的其他费用 " disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeedevice" label="设备摊销" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeeinvoice" label="开票费" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="bfeetrans" label="转让费" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="bfeeprocess" 
					label="手续费(不可控)" disable="false" required="false" />
				</tr>
				<tr> 
					<lemis:textareaeditor required="false" property="bfeent" label="备注" disable="false"
					  maxlength="200" colspan="5" rowspan="5"/>
				</tr> 
			</table>


			<lemis:tabletitle title="财税支出" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="ffeerent" 
					label="房租(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeepension" 
					label="社保费(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeetax" label="税收(不可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="ffeeassets" 
					label="固定资产(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeepay" 
					label="工资" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeebuilt" 
					label="装修费(不可控)" disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="ffeeopen" 
					label="开办费" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeothersa" 
					label="13%以外其他费用" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeead" 
					label="广告费(可控)" disable="false" required="false" />
				</tr>
				
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeephone" label="电话费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeewater" label="水电费(可控)" disable="false" />
						<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeetrip" label="差旅费(可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeepostage" label="邮费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeeoffice" label="办公费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
					property="ffeesocial" label="公关费(可控)" disable="false" />
				</tr>
				<tr>
					
					<lemis:formateditor mask="bigmoney" format="true" property="ffeemedical"
					label="额外费用(可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeeaccount"
					label="会计工资 " disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="ffeeothersb"
					label="13%以内的其他费用 " disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeedevice" label="设备摊销" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeeinvoice" label="开票费" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="ffeetrans" label="转让费" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="ffeeprocess" 
					label="手续费(不可控)" disable="false" required="false" />
				</tr>
				<tr> 
					<lemis:textareaeditor required="false" property="ffeent" label="备注" disable="false"
					  maxlength="200" colspan="5" rowspan="5"/>
				</tr> 
			</table>


			<lemis:tabletitle title="下月预算" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="nfeerent" 
					label="房租(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeepension" 
					label="社保费(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeetax" label="税收(不可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="nfeeassets" 
					label="固定资产(不可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeepay" 
					label="工资" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeebuilt" 
					label="装修费(不可控)" disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="nfeeopen" 
					label="开办费" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeothersa" 
					label="13%以外其他费用" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeead" 
					label="广告费(可控)" disable="false" required="false" />
				</tr>
				
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeephone" label="电话费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeewater" label="水电费(可控)" disable="false" />
						<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeetrip" label="差旅费(可控)" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeepostage" label="邮费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeeoffice" label="办公费(可控)" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
					property="nfeesocial" label="公关费(可控)" disable="false" />
				</tr>
				<tr>
					
					<lemis:formateditor mask="bigmoney" format="true" property="nfeemedical"
					label="额外费用(可控)" disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeeaccount"
					label="会计工资 " disable="false" required="false" />
					<lemis:formateditor mask="bigmoney" format="true" property="nfeeothersb"
					label="13%以内的其他费用 " disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeedevice" label="设备摊销" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeeinvoice" label="开票费" disable="false" />
					<lemis:formateditor mask="bigmoney" format="true" required="false"
						property="nfeetrans" label="转让费" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" property="nfeeprocess" 
					label="手续费(不可控)" disable="false" required="false" />
				</tr>
				<tr> 
					<lemis:textareaeditor required="false" property="nfeent" label="备注" disable="false"
					  maxlength="200" colspan="5" rowspan="5"/>
				</tr> 
			</table>


		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
