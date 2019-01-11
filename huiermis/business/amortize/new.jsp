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
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=arzgctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=arzgctid]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=arzgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});

	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<lemis:body>
<lemis:errors />
<lemis:title title="新增摊销信息" />
	<lemis:tabletitle title="摊销信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/BusinessAction.do?method=saveAmortize" method="POST">
			<tr>
				<lemis:texteditor property="arzgctid" label="客户代码" disable="false"
					required="true" />
				<td>登记日期</td>
				<td><lemis:operdate/></td>
				<td>登记员</td>
				<td><lemis:operator/></td>
			</tr>
			
			
			<lemis:tabletitle title="房租摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="harzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
						property="harzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="harzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="harzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="harznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="装修费摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="farzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
						property="farzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="farzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="farzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="farznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="设备摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="darzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
						property="darzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="darzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="darzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="darznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="固定资产摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="aarzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
						property="aarzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="aarzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="aarzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="aarznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="转诊费摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="tarzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
						property="tarzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="tarzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="tarzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="tarznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="开办费摊销" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:formateditor mask="bigmoney" format="true" 
						property="oarzamount" label="摊销总金额" required="true" disable="false" />
					<lemis:formateditor mask="date" format="true" 
							property="oarzstdt" label="摊销开始时间" required="true" disable="false" />
					<lemis:texteditor property="oarzmonth" label="摊销月份" required="true"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="oarzcontract" label="合同期限" required="false"
						disable="false" />
					<lemis:texteditor property="oarznt" label="备注" required="false"
						disable="false"  colspan="3"/>
				</tr>
			</table>	

		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
<lemis:base/>

</lemis:body>

</html>