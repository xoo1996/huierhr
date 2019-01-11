<%@ page contentType="text/html; charset=GBK"%>
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
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">

//$("input[name=reppid]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
		 dataType:'json',
		 error:function(){
		   alert('获取数据错误！');
		 },
		 success:function(data){
					$("input[name=reppid]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.proid + "=" + data.proname);
						}
					});	
					$("input[name=reppid]").result(function(event, data, formatted) {
						if (data){
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=reppid]").val(pid);

						}
					});
				}
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
				<lemis:texteditor property="repgctid" label="送修单位" required="true"
					disable="true" />
				<lemis:texteditor property="repcltid" label="用户代码" required="true"
					disable="true" />
				<lemis:formateditor mask="date" property="repdate" label="送修日期"
					format="true" required="true" disable="false" />
			</tr>
			<tr>
			<%-- 	<lemis:codelisteditor type="reppid" isSelect="false" label="助听器型号"
						redisplay="true" required="false" dataMeta="productList" /> --%>
						
				<lemis:codelisteditor type="reppid" isSelect="false" label="助听器型号"
						redisplay="true" required="false"/>
						
				<lemis:texteditor property="repid" label="助听器条形码" required="true"
					disable="false" />
				<lemis:codelisteditor type="repfree" isSelect="true" label="是否保修期"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="reppreamt" isSelect="true" label="维修费估计"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="repcls" isSelect="true" label="修理类别"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="故障详情" required="false"
					disable="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repattention" label="客户备注" required="false"
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

