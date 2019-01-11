<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String bsc009=(String)request.getSession().getAttribute("bsc009");
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("提 交","saveData(document.forms[0])");
	buttons.put("重 置","document.forms[0].reset();");//这个模块下公用的按钮
	buttons.put("返 回","history.back()");
	pageContext.setAttribute("button", buttons);

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			
			var li=$(".buttonGray");
			for(var i=0;i<li.length;i++){
				if(li[i].value=="提 交") {
					li[i].disabled=true;
				}
			}
			
			obj.submit();
		}
		$(document).ready(function(){
			/* 智能输入 */
			var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
			
			$("input[name=nempart]").autocomplete(shops,{
				max : 10,
				matchContains : true
			});
			$("input[name=nempart]").result(function(event, data, formatted) {
				if (data){
					var gnm = data[0].substring(data[0].indexOf("=")+1);
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=nempart]").val(gid);
					$("input[name=bsc009]").val(gnm);
				}
			});
		});
		function add(){
			$("form").submit();
		}
		
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工续签申请流程新增" />
		<lemis:tabletitle title="员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=con" method="POST">
				<tr>
					<lemis:texteditor property="nemname" label="姓名" required="false" disable="true" />
					<lemis:formateditor property="nemcard" label="身份证号" disable="false" required="true"  mask="card" />
					<lemis:texteditor property="nememployid" label="员工id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nempart" label="所在部门id" required="true" disable="false" />
					<lemis:texteditor property="bsc009" label="所在部门" required="true" disable="false" value="<%=bsc009%>"/>
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="本单位入职时间" disable="false" format="true" />
					<lemis:texteditor property="nemjob" label="现任职务" required="false" disable="false" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>签订固定期限劳动合同</td>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="false" />
					<td><input type="radio" name="nempay" value="1"/>签订无固定期限劳动合同</td>
				</tr>
				<lemis:tabletitle title="原合同期限" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="第一次" required="false" disable="false" />
					<lemis:texteditor property="nemad2" label="第二次" required="false" disable="false" />
					<lemis:texteditor property="nemad3" label="第三次" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="第四次" required="false" disable="false" />
					<lemis:texteditor property="nemad5" label="第五次" required="false" disable="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

