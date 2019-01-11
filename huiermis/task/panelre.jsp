<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�������","save(document.forms[0])");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("pnlqapnl","������"));
	
	header.add(new Formatter("pnlqant","��ע"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pnlqapnl","������","true"));
	

	batchInput.add(new Editor("text","pnlqant","��ע","false"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	
 $(document).ready(function(e) {

		<%-- var nomProducts = "<%=session.getServletContext().getAttribute("nomProductList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=fdtpid]").autocomplete(nomProducts,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		}); 
		$("input[name=fdtpid]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(6);
				$("#fdtpid" + suffix).val(pid);
				$("#pdtnm" + suffix).val(pnm);
				$("#fdtdprc" + suffix).val(prc);
			/* 	$("#fdtqnt" + suffix).val(1); */
			}
		}); --%>
		
		//$("input[name=fdtpid]").bind("click",function(){
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
		
		var pnlqapnl = $("#pnlqapnl_hrow1").val();
		var pnlqant = $("#pnlqant_hrow1").val();
	
		$('.tableInput label:first').text(pnlqapnl);
		$('.tableInput label:last').text(pnlqant);
	
		//$('input[name=chk]').attr('checked','checked');
	
 });
 
 

function save(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite href="/huiermis/task/TaskAction.do?method=batchStorere&"/>'+getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ������������")) {
		obj.submit();
	}
}
	
		
</script> 

</head>
<lemis:base />
<lemis:body>
	<lemis:title title="������ϸ¼��" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
			
			<tr>
				<html:hidden property="pnlproid" />
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="false"
					required="true" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="������ϸ¼��"
		action="/TaskAction.do" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>