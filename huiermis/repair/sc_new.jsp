<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("维修登记","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
	<%-- 	var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", "); --%>
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=repgctnm]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=repgctnm]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=repgctnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
		/* $("input[name=reppnm]").autocomplete(products,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=reppnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				$("input[name=reppnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
			}
		}); */
	
	
	
	// $("input[name=reppnm]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('获取数据错误！');
				 },
				 success:function(data){
							$("input[name=reppnm]").autocomplete(data,{
								max:1000,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=reppnm]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=reppnm]").val(pnm);
									$(this).parent().next().find("input").val(pid);
	
								}
							});
						}
				});
		//});
	}); 
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="助听器修理登记表" />
	<lemis:tabletitle title="助听器维修信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/RepairAction.do?method=saveNew" method="POST">
			<tr>
				<lemis:texteditor property="repgctnm" label="送修单位" required="true"
					disable="false" />
				<html:hidden property="repgctid" />
				<lemis:texteditor property="repcltnm" label="用户姓名" required="true"
					disable="false" />
				<html:hidden property="repcltid" />	
				<lemis:formateditor mask="date" property="repdate" label="送修日期"
					format="true" required="true" disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="reppnm" label="维修产品" required="true"
					disable="false" />
				<html:hidden property="reppid" />
				<lemis:texteditor property="repid" label="机身编号" required="false"
					disable="false" />
				<lemis:codelisteditor type="repfree" isSelect="true" label="是否保修期"
					redisplay="true" required="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="reppreamt" isSelect="true" label="维修费通知"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="true" label="修理类别"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="repcpy" isSelect="true" label="送修厂商"
					redisplay="true" required="true" />
			</tr>
			<tr>
			    <lemis:formateditor mask="date" property="reppdate" label="计划完工日期"
					format="true" required="true" disable="false" /> 
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="故障详情"
					required="false" disable="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repnote" label="客户备注" required="false"
					disable="false" colspan="5" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

