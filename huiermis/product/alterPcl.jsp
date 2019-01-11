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
	buttons.put("返回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script language="javascript">
<%-- $(document).ready(function(e) {
	var classes="<%=session.getServletContext().getAttribute("classesList")%>".replace("{","").replace("}","").split(", ");
	$("input[name=pdtclnm]").autocomplete(classes,{
		max : 10,
		matchContains : true,
		formatItem: function(data, i, max) {
			return data[0].substring(0,data[0].indexOf("!"));
		}
	});
	
	
	$("input[name=pdtclnm]").result(function(event, data, formatted) {
		if (data){
			var pclid = data[0].substring(0,data[0].indexOf("="));
			var pclnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf("!"));
			/* var id = $(this).parent().find("input").attr("id"); */
		    $(this).parent().next().find("input").val(pclid);
			/* $("#pdtclid").val(pclid); */
		/* 	$("input:hidden[name=pdtclid]").val(pclid); */
			$("input[name=pdtclnm]").val(pclnm);
		/* 	$("#fdtprc" + suffix).val(prc);
			$("#fdtqnt" + suffix).val(1); */
		}
	});
}); --%>
$(document).ready(function(e) {
//$("input[name=pdtclnm]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryClasses',
		 dataType:'json',
		 error:function(){
		   alert('获取数据错误！');
		 },
		 success:function(data){
					$("input[name=pdtclnm]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.pclid + "=" + data.pcllarge+"="+data.pclmid+"="+data.pclsmall);
						}
					});	
					$("input[name=pdtclnm]").result(function(event, data, formatted) {
						if (data){
							var pclid=data.pclid;
							var pclnm=data.pcllarge+"="+data.pclmid+"="+data.pclsmall;
							$(this).parent().next().find("input").val(pclid);
							$("input[name=pdtclnm]").val(pclnm);

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
		obj.action = '<html:rewrite href="/huiermis/product/ProductAction.do?method=saveModifiedPcl"/>';
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="商品类别修改" />
	<lemis:tabletitle title="商品类别信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ProductAction.do?method=saveModifiedPcl"
			method="POST">
			<tr>
				<lemis:texteditor property="pclid" label="商品类别代码" disable="true"
					required="true" />
				<lemis:texteditor property="pcllarge" label="商品大类" disable="false"
					required="true" />
				<lemis:texteditor property="pclmid" label="商品中类" disable="false"
					required="true" />
			<%-- 	<lemis:codelisteditor type="pclmid" isSelect="true" label="商品中类"
					redisplay="true" required="true" /> --%>
			</tr>
			<tr>
				<%-- <lemis:codelisteditor type="pdttype" isSelect="true" label="助听器品牌"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="pdtut" isSelect="true" label="单位"
					redisplay="true" required="true" /> --%>
				<lemis:texteditor property="pclsmall" label="商品小类" required="true"
					disable="false" />
			</tr>
<%-- 			<tr>
				<lemis:texteditor property="pdtclnm" label="商品类别" disable="false"></lemis:texteditor>
			     <html:hidden property="pdtclid"/>
				<lemis:texteditor property="pdtnt" label="备注" required="false"
					disable="false" />
			</tr> --%>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

