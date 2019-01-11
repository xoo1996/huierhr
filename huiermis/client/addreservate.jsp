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
    buttons.put("保 存","saveData(document.forms[0])");
    //buttons.put("重 置","document.forms[0].reset();");
    buttons.put("返 回","history.back()");
    //buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		if(grCli=="1501000000")
		{
			$("input[name=rsvgctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0];
				}
			});
			
			$("input[name=rsvgctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1);
					$("input[name=rsvgctnm]").val(gnm);
					$(this).parent().next().find("input").val(gid);
				}
			});
		}
		else
		{
			$("input[name=rsvgctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=rsvgctnm]").attr("readonly","true");
			$("input:hidden[name=rsvgcltid]").val("<%=dto.getBsc011()%>");
			
			
	 	}
		
		$("input[name=rsvgctzhuannm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
				return data[0];
			}
		});
		
		$("input[name=rsvgctzhuannm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1);
				$("input[name=rsvgctzhuannm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
		
		
	});
	
	
    
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="个人客户档案新增" />
	<lemis:tabletitle title="个人客户信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ReservationAction.do?method=yuyueSave" method="POST">
			
			<tr>
				<lemis:texteditor property="rsvnm" label="客户姓名" required="true"
					disable="false" maxlength="20" />
				<lemis:texteditor property="rsvphone" label="手机号码" disable="false"
					required="true" />
				<lemis:texteditor property="rsvgctnm" label="预约单位" disable="false"
					required="true" />
				<html:hidden property="rsvgcltid" />

			</tr>
			 
			<tr>
				<lemis:texteditor property="rsvgctzhuannm" label="转诊单位" disable="false"
					required="true" />
				<html:hidden property="rsvgczhuanid" />
				<lemis:formateditor required="false" property="rsvdate" mask="date"
					label="预约日期" format="true" disable="false" required="true"/>
				<lemis:texteditor property="ypnm" label="预约专家" required="false"
					disable="false" maxlength="20" />
				
			</tr>
			<tr>
				<lemis:texteditor property="rsvnote" label="预约备注" required="false"
					disable="false" colspan="5" />
			</tr>
			<%--<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>--%>

			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

