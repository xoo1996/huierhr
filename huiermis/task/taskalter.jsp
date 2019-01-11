<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�� ��","save(document.forms[0])");
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
			 url:'<%=basePath%>ProductAction.do?method=queryPanels',
			 dataType:'json',
			 error:function(){
			   alert('��ȡ���ݴ���');
			 },
			 success:function(data){
						$("input[name=tskpnlnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=tskpnlnm]").result(function(event, data, formatted) {
							if (data){
								var pnlnm=data.pnlnm;
								$("input[name=tskpnlnm]").val(pnlnm);
								$(this).parent().next().find("input").val(pnlnm);

									
									
							}
						});
					}
			});
	//});
});
</script>

<script language="javascript">
	function save(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		//obj.action = '<html:rewrite page="/TaskAction.do?method=modify&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="�޸�����" />
	<lemis:tabletitle title="������Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		  <html:form action="/TaskAction.do?method=modify" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="���񵥺�" disable="true" />
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="true" required="true"/>
				<lemis:texteditor property="tskpnlqnt" label="�������" disable="false" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="false"
					label="Ҫ���������" disable="true" format="true" />
				<lemis:texteditor property="tskbilopr" label="�Ƶ���" disable="true" />
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="�����´�����" disable="true" format="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="���Ҫ��" disable="false"  colspan="5"/>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
