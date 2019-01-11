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
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
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
		   alert('��ȡ���ݴ���');
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
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="��Ʒ��������" />
	<lemis:tabletitle title="��Ʒ������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ProductAction.do?method=saveNew&mark=make"
			method="POST">
			<tr>
				<lemis:texteditor property="pdtid" label="��Ʒ����" disable="false"
					required="true" />
				<lemis:texteditor property="pdtnm" label="��Ʒ����" disable="false"
					required="true" />
				<lemis:codelisteditor type="pdtcls" isSelect="true" label="�������"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="pdttype" isSelect="true" label="������Ʒ��"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="pdtut" isSelect="true" label="��λ"
					redisplay="true" required="true" />
				<lemis:texteditor property="pdtprc" label="����" required="true"
					disable="false" />
			</tr>

			<tr>
				<lemis:texteditor property="pdtnt" label="��ע" required="false"
					disable="false" />
			  <lemis:texteditor property="pdtclnm" label="��Ʒ���" disable="false"></lemis:texteditor>
			  <html:hidden property="pdtclid"/>
			  <lemis:formateditor mask="nn" required="false" property="pdtlmt" label="��������(��)" disable="false"/>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

