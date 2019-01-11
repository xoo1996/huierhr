<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","configure(document.forms[0])");
    buttons.put("�� ��","document.forms[0].reset();");
    buttons.put("�� ��","history.back()");
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<%-- $(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("panelList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=tskpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0].substring(data[0].indexOf("=")+1);
				$("input[name=tskpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});
	}); --%>
</script>

<script language="javascript">
	function configure(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite page="/TaskAction.do?method=configure&"/>' + getAlldata(obj).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
		obj.submit();
	};
</script>

<script language="javascript">
	<%-- $(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("pnlnmList")%>".replace("[","").replace("]","").split(", ");
		
		$("input[name=tskpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0];
				$("input[name=tskpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});
	});  --%>
$(document).ready(function(){
	//$("input[name=tskpnlnm]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro1',
			 dataType:'json',
			 error:function(){
			   alert('��ȡ���ݴ���');
			 },
			 success:function(data){
						$("input[name=tskpnlnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.cfgpnlnm);
							}
						});	
						$("input[name=tskpnlnm]").result(function(event, data, formatted) {
							if (data){
								var proname=data.cfgpnlnm;
								var proid=data.proid;
								$("input[name=tskpnlnm]").val(proname);
								$("input:hidden[name=pnlproid]").val(proid);
								$(this).parent().next().find("input").val(proname);

									
									
							}
						});
					}
			});
	//});
});
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="��������" />
	<lemis:tabletitle title="������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/TaskAction.do" method="POST">
			<tr>
				<html:hidden property="pnlproid" />
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="false"
					required="true" />
				<lemis:texteditor property="tskpnlqnt" label="�������" disable="false"
					required="true" maxlength="4" />
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="true"
					label="Ҫ���������" disable="false" format="true" />
			</tr>
			<tr>
				<td>�Ƶ���</td>
				<td><lemis:operator /></td>
				<td>�����´�����</td>
				<td><lemis:operdate /></td>
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="���Ҫ��" disable="false"
					required="false" colspan="5" />
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
