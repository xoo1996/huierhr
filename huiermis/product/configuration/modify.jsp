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
	buttons.put("批量保存","batchSave(document.all.tableform)");
	/* buttons.put("返 回","history.back()"); */
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>(); 
	header.add(new Formatter("pdtid","商品代码"));
	/* header.add(new Formatter("acypdtid","零件货号")); */
	header.add(new Formatter("pdtnm","零件名称"));
	header.add(new Formatter("pdtmod","零件型号"));
	header.add(new Formatter("cfgtemperature","温度"));
	header.add(new Formatter("cfgnt","备注"));
	//header.add(new Formatter("cfgacyid","原商品代码"));
	//header.add(new Formatter("cfgpnlnm","面板型号")); 
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtid","商品代码","true"));
/* 	batchInput.add(new Editor("text","acypdtid","零件货号","false")); */
	batchInput.add(new Editor("text","pdtnm","零件名称","true"));
	batchInput.add(new Editor("text","pdtmod","零件型号","false"));
	batchInput.add(new Editor("text","cfgtemperature","温度","false"));
	batchInput.add(new Editor("text","cfgnt","备注","false"));
	//batchInput.add(new Editor("text","cfgacyid","原商品代码","false"));
	//batchInput.add(new Editor("text","cfgpnlnm","面板型号","false")); 
	
	Map<String, String> hidden = new LinkedHashMap<String,String>();
	hidden.put("cfgpnlnm", "面板型号");
	//hidden.put("pdtid","商品代码");
	hidden.put("cfgacyid","原商品代码");
	hidden.put("pnlnm", "面板型号");
	

	
	pageContext.setAttribute("hidden", hidden);
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


	<%-- $(document).ready(function(e) {
		var parts = "<%=session.getServletContext().getAttribute("partList")%>".replace("{","").replace("}","").split(", ");

		/* $("input[name=acypdtid]").attr('readonly','readonly'); */
		$("input[name=pdtnm]").attr('readonly','readonly');
		$("input[name=pdtmod]").attr('readonly','readonly');
		$("input[name=cfgacyid]").attr('readonly','readonly');
		$("input[name=cfgpnlnm]").attr('readonly','readonly');
		
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
				var pdtnm = data[0].substring(data[0].indexOf("=")+1,data[0].lastIndexOf(":"));
				var pdtmod = data[0].substring(data[0].lastIndexOf(":")+1);
				var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(5);//此处“pdtid”有5个字母，所以substr里为5
				
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
			   alert('获取数据错误！');
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
								var suffix = id.substr(5);//此处“pdtid”有5个字母，所以substr里为5
								
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
	function batchSave(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		/* window.location.href = "/"
			+ lemis.WEB_APP_NAME
			+ "/product/ConfigurationAction.do?method=batchSave&"
			+ getAlldata(document.all.tableform);
			//+ "&pnlnm="+ $("input[name=pnlnm]").val();   */
		 obj.action = '<html:rewrite page="/ConfigurationAction.do?method=batchSave"/>';
		obj.submit();   
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="面板配置明细修改" />
	<lemis:tabletitle title="面板配置基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/ConfigurationAction.do" method="POST">
			<tr>
				<%-- <lemis:texteditor property="pnlid" label="面板编码" disable="true" /> --%>
				<lemis:texteditor property="pnlnm" label="面板型号" disable="true" />
				<td>经办日期</td>
				
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="零件信息明细录入"  
		action="/ConfigurationAction.do" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" pageSize="10"
		batchInputType="update" pilot="true" hiddenMeta="hidden"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>