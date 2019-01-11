<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("录 单","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("返 回","history.back()");
    buttons.put("关 闭","closeWindow(\"\")");
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtqnt", "订购数量");
	
	pageContext.setAttribute("hidden", hidden);
    pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		else {
			if (document.all("tmemat").value == '2') {
				alert("维修机的耳模类型不能是双耳!");
				return false;
			}
			obj.action = "/" + lemis.WEB_APP_NAME
					+ "/earmould/EarMouldAction.do?method=saveRepair";
		}

		obj.submit();
	}
</script>

<script language="javascript">
	$(document).ready(function() {
		$("input[name=tmeprc]").attr("readonly", true);
		/* if ($('#tmecls').val() != 'repair') {
			$("input[name=tmesid]").attr("disable", true);
		} */
		
		$("input[name=fdtqnt]").val("1");
		$("input[name=fdtqnt]").attr("disable", true);

		
		$('#tmepid').change(function(e) {
			if ($('#tmefree').val() == 'Y') {
				$("input[name=tmeprc]").val(0);
			} else {
				startRequest(e);
			}
		});
		$('#tmefree').change(function() {
			if ($(this).val() == 'Y') {
				$("input[name=tmeprc]").val(0);
			} else {
				//触发 tmepid 的change事件
				$('#tmepid').trigger("change");
			}
		});
		$('#tmecls').change(function() {
			if ($(this).val() == 'repair') {
				$("input[name=tmesid]").attr("disable", false);
				$("input[name=fdtqnt]").val("1");
				$("input[name=fdtqnt]").attr("disable", true);
			} else {
				$("input[name=tmesid]").attr("disable", true);
				$("input[name=fdtqnt]").val("");
				$("input[name=fdtqnt]").attr("disable", false);
			}
		}); 

		function createQueryString(e) {
			var eID = $(e.target).val();
			var queryString = {
				EarId : eID
			};
			return queryString;
		};
		function startRequest(e) {
			$.getJSON("/huiermis/earmould/EarMouldAction.do?method=queryEMPro",
					createQueryString(e), function(data) {
						$("input[name=tmeprc]").val(data[0].price);
					});
		};
	
	});
</script>

<script language="javascript">
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=tmectid]").autocomplete(shops,{
			max : 10,//下拉列表最多出现10项
			matchContains : true
		});
		$("input[name=tmectid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=tmectid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="耳模维修信息新增" />
	<lemis:tabletitle title="耳模维修项信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarMouldAction.do" method="POST">
			<tr>
				<lemis:texteditor property="tmecltnm" label="用户姓名" required="true"
					disable="false" maxlength="20" />
				<lemis:texteditor property="tmectid" label="客户代码" required="true"
					disable="false" maxlength="20" />
				<html:hidden property="tmectid"/>
				<lemis:formateditor mask="date" property="tmepdt" required="true"
					label="计划完工日期" disable="false" format="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
			<lemis:tabletitle title="耳模详情" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="tmepid" isSelect="true" label="耳模型号"
						redisplay="true" required="true" />
					<lemis:texteditor property="tmeprc" label="零售价" required="true"
						disable="false" />
					<lemis:codelisteditor type="tmemat" isSelect="true" label="耳模类型"
						redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="tmefree" isSelect="true" label="是否赠送"
						redisplay="true" required="true" />
					<lemis:texteditor property="fdtqnt" label="数量" required="true"
						disable="false" />
					<lemis:texteditor property='tmesid' label='耳模编号' disable='false' />
					
				</tr>
				<tr>
					<td>制作类别</td>
					<td><lemis:makerepair/></td>
					<lemis:texteditor property="tment" label="备注" required="false" disable="false" colspan="3"/>
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>