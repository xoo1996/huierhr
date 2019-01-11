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
    buttons.put("�� ��","saveData(document.forms[0])");
    //buttons.put("�� ��","document.forms[0].reset();");
    buttons.put("�� ��","history.back()");
    //buttons.put("�� ��","closeWindow(\"\")");
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
	<lemis:title title="���˿ͻ���������" />
	<lemis:tabletitle title="���˿ͻ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ReservationAction.do?method=yuyueSave" method="POST">
			
			<tr>
				<lemis:texteditor property="rsvnm" label="�ͻ�����" required="true"
					disable="false" maxlength="20" />
				<lemis:texteditor property="rsvphone" label="�ֻ�����" disable="false"
					required="true" />
				<lemis:texteditor property="rsvgctnm" label="ԤԼ��λ" disable="false"
					required="true" />
				<html:hidden property="rsvgcltid" />

			</tr>
			 
			<tr>
				<lemis:texteditor property="rsvgctzhuannm" label="ת�ﵥλ" disable="false"
					required="true" />
				<html:hidden property="rsvgczhuanid" />
				<lemis:formateditor required="false" property="rsvdate" mask="date"
					label="ԤԼ����" format="true" disable="false" required="true"/>
				<lemis:texteditor property="ypnm" label="ԤԼר��" required="false"
					disable="false" maxlength="20" />
				
			</tr>
			<tr>
				<lemis:texteditor property="rsvnote" label="ԤԼ��ע" required="false"
					disable="false" colspan="5" />
			</tr>
			<%--<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>--%>

			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

