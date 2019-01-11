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
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=ictgctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=ictgctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ictgctid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});

		$("input[name=dgnlnm]").autocomplete(products,{
			max : 20,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=dgnlnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=dgnlnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
				$("input[name=dgnldprc]").val(prc);
				
			}
		});
		
		$("input[name=dgnrnm]").autocomplete(products,{
			max : 20,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=dgnrnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=dgnrnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
				$("input[name=dgnrdprc]").val(prc);
			}
		});
	});
    function lgvag() {
		
		var l1 = $("input[name=lg250]").val();
		var l2 = $("input[name=lg500]").val();
		var l3 = $("input[name=lg1000]").val();
		var l4 = $("input[name=lg2000]").val();
		var l5 = $("input[name=lg4000]").val();
        var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
		$("input[name=lgavg]").val(lvag);
	};
    function lqvag() {
		
		var l1 = $("input[name=lq250]").val();
		var l2 = $("input[name=lq500]").val();
		var l3 = $("input[name=lq1000]").val();
		var l4 = $("input[name=lq2000]").val();
		var l5 = $("input[name=lq4000]").val();
        var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
		$("input[name=lqavg]").val(lvag);
	};
    function rgvag() {
		
		var l1 = $("input[name=rg250]").val();
		var l2 = $("input[name=rg500]").val();
		var l3 = $("input[name=rg1000]").val();
		var l4 = $("input[name=rg2000]").val();
		var l5 = $("input[name=rg4000]").val();
        var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
		$("input[name=rgavg]").val(lvag);
	};
    function rqvag() {
		
		var l1 = $("input[name=rq250]").val();
		var l2 = $("input[name=rq500]").val();
		var l3 = $("input[name=rq1000]").val();
		var l4 = $("input[name=rq2000]").val();
		var l5 = $("input[name=rq4000]").val();
        var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
		$("input[name=rqavg]").val(lvag);
	};
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="个人客户档案新增" />
	<lemis:tabletitle title="个人客户信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=add" method="POST">
			<html:hidden property="ictid" />
			<tr>
				<lemis:texteditor property="ictnm" label="姓名" required="true"
					disable="false" maxlength="20" />
				<lemis:texteditor property="ictgctid" label="所属团体" disable="false"
					required="true" />
				<html:hidden property="ictgctnm" />
				<lemis:codelisteditor type="ictphis" isSelect="true" label="用过何种助听器"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="ictgender" isSelect="true" label="性别"
					redisplay="true" required="true" />
				<lemis:formateditor mask="date" property="ictbdt" label="出生日期"
					required="true" disable="false" />
				<lemis:texteditor property="ictpnm" label="家长姓名" required="false"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<lemis:texteditor property="ictaddr" label="联系地址" required="false"
					disable="false" maxlength="40" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="false" maxlength="20" />
				<lemis:texteditor property="ictpcd" label="邮政编码" required="false"
					disable="false" maxlength="20" />
			</tr>
			<tr>
				<lemis:texteditor property="ictsrc" label="来源" required="false"
					disable="false" />
				<lemis:texteditor property="ictnt" label="备注" disable="false"
					colspan="3" maxlength="100" />
					<lemis:codelisteditor type="ictfrom" label="来源" required="true" />

			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
			<tr>
				<lemis:texteditor property="dgnmmk" label="耳印制作者" required="false"
					disable="false" maxlength="20" />
				<lemis:formateditor mask="date" property="dgndt" label="检查日期"
					format="true" required="false" disable="false" />
				<lemis:formateditor mask="date" property="dgnpfdt" label="预计取货日期"
					format="true" required="false" disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="dgnsdmd" label="特殊要求" required="false"
					disable="false" maxlength="100" />
				<lemis:texteditor property="dgnctimp" label="用户反映" required="false"
					disable="false" />
				<lemis:formateditor mask="date" property="dgnafdt" label="实际取货日期"
					format="true" required="false" disable="false" />
			</tr>
			<lemis:tabletitle title="左耳定制机" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="dgnlnm" label="助听器型号" required="false"
						disable="false" />
					<html:hidden property="dgnlid" />
					<lemis:texteditor property="dgnldprc" label="零售" required="false"
						disable="false" maxlength="20" />
					<lemis:texteditor property="dgnlufa" label="不适域" required="false"
						disable="false" maxlength="20" />
				</tr>
			</table>
			<lemis:tabletitle title="右耳定制机" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="dgnrnm" label="助听器型号" required="false"
						disable="false" />
					<html:hidden property="dgnrid" />
					<lemis:texteditor property="dgnrdprc" label="零售" required="false"
						disable="false" maxlength="20" />
					<lemis:texteditor property="dgnrufa" label="不适域" required="false"
						disable="false" maxlength="20" />
				</tr>
			</table>
			<lemis:tabletitle title="左耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
						disable="false" />
					<lemis:texteditor property="lg500" label="500" required="false"
						disable="false" />
					<lemis:texteditor property="lg1000" label="1000" required="false"
						disable="false" />
					<lemis:texteditor property="lg2000" label="2000" required="false"
						disable="false" />
					<lemis:texteditor property="lg4000" label="4000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="平均" required="false"
						disable="false"  />
				</tr>
			</table>

			<lemis:tabletitle title="左耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black" onblur="lqvag()" />
					<lemis:texteditor property="lqavg" label="平均" required="false"
						disable="false" style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						disable="false" />
					<lemis:texteditor property="rg500" label="500" required="false"
						disable="false" />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						disable="false" />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						disable="false" />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rgavg" label="平均" required="false"
						disable="false" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black" onblur="rqvag()"/>
					<lemis:texteditor property="rqavg" label="平均" required="false"
						disable="false" style="border-width:1px;border-color=black" />
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

