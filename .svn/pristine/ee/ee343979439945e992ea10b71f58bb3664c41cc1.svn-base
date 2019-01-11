<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("配置零件","saveData(document.forms[0])");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("cfgpnlnm", "面板型号");
	
	
	pageContext.setAttribute("hidden", hidden);
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
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		if($("input[name=cfgpnlnm]").val().indexOf('\'')!=-1|| $("input[name=cfgpnlnm]").val().indexOf('+')!=-1||$("input[name=cfgpnlnm]").val().indexOf('\"')!=-1||$("input[name=cfgpnlnm]").val().indexOf('\\')!=-1)
			$("input[name=cfgpnlnm]").val().replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
		obj.submit();
	};
</script>

<script language="javascript">
	<%-- $(document).ready(function(){
		var panels = "<%=session.getServletContext().getAttribute("pnlnmList")%>".replace("[","").replace("]","").split(", ");
		
		$("input[name=cfgpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=cfgpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0];
				$("input[name=cfgpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});
	});  --%>
$(document).ready(function(){
	//$("input[name=cfgpnlnm]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro&proType=oth',
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=cfgpnlnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "="+data.proname);
							}
						});	
						$("input[name=cfgpnlnm]").result(function(event, data, formatted) {
							if (data){
								var proname=data.proname;
								$("input[name=cfgpnlnm]").val(proname);
								$(this).parent().next().find("input").val(proname);

									
									
							}
						});
					}
			});
	//});
});
</script>

<lemis:base />
<lemis:body>
	<div id="target"></div>
	<lemis:title title="新增" />
	<lemis:tabletitle title="面板配置基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ConfigurationAction.do?method=saveConfiguration" method="POST">
			<tr>
				<lemis:texteditor property="cfgpnlnm" label="面板型号" required="true"
					disable="false" maxlength="100" colspan="1"/>
				<%-- <lemis:texteditor property="cfgnt" label="备注" disable="false"
					maxlength="20" colspan="3" /> --%>
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
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


