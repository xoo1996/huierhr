<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����¼��","batchSubmit(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("pdtid","��Ʒ����"));
	/* header.add(new Formatter("acypdtid","�������")); */
	header.add(new Formatter("pdtnm","�������"));
	header.add(new Formatter("pdtmod","����ͺ�"));
	header.add(new Formatter("cfgnt","��ע"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtid","��Ʒ����","true"));
	/* batchInput.add(new Editor("text","acypdtid","�������","false")); */
	batchInput.add(new Editor("text","pdtnm","�������","true"));
	batchInput.add(new Editor("text","pdtmod","����ͺ�","true"));
	batchInput.add(new Editor("text","cfgnt","��ע","false"));
	

	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script language="javascript">

<%-- 
	$(document).ready(function(e) {
		var parts = "<%=session.getServletContext().getAttribute("partList")%>".replace("{","").replace("}","").split(", ");

		$("input[name=pdtid]").autocomplete(parts,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0);
			}
		});
		$("input[name=pdtid]").result(function(event, data, formatted) {
			if (data){
				var pdtid = data[0].substring(0,data[0].indexOf("="));
				var pdtnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var pdtmod = data[0].substring(data[0].indexOf(":")+1);
				var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(5);//�˴���pdtid����5����ĸ������substr��Ϊ5
				
				$("#pdtid" + suffix).val(pdtid);
				$("#pdtnm" + suffix).val(pdtnm);
				$("#pdtmod" + suffix).val(pdtmod);
			}
		}); 
		
	}); --%>
	$(document).ready(function(e) {
	//$("input[name=pdtid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryParts',
			 dataType:'json',
			 error:function(){
			   alert('��ȡ���ݴ���');
			 },
			 success:function(data){
						$("input[name=pdtid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=pdtid]").result(function(event, data, formatted) {
							if (data){
								var pdtid=data.proid;
								var pdtnm = data.proname;
								var pdtmod=data.promod;
								
								var id = $(this).parent().find("input").attr("id");
								var suffix = id.substr(5);//�˴���pdtid����5����ĸ������substr��Ϊ5
								
								$("#pdtid" + suffix).val(pdtid);
								$("#pdtnm" + suffix).val(pdtnm);
								$("#pdtmod" + suffix).val(pdtmod);
								
							
									
									
							}
						});
					}
			});
	//}); 
	});
</script>
<script language="javascript">
	function batchSubmit() {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/product/ConfigurationAction.do?method=batchSubmit&"
				+ getAlldata(document.all.tableform) + "&cfgpnlnm="
				+ $("input[name=cfgpnlnm]").val().replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
		//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
	}
	function URLencode(sStr) 
	{
	    return escape(sStr).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g, '%27').replace(/\//g,'%2F');
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="���������ϸ¼��" />
	<lemis:tabletitle title="������û�����Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/ConfigurationAction.do" method="POST">
			<tr>
				<lemis:texteditor property="cfgpnlnm" label="����ͺ�" disable="true" />
				<html:hidden property="cfgpnlnm" />
				<td>��������</td>
				
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="�����Ϣ��ϸ¼��"  
		action="/ConfigurationAction.do&method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" pageSize="20"
		batchInputType="insert" pilot="true" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body> 
</html>