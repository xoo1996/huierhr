<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
	buttons.put("返 回","history.back()");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<script type="text/javascript">
   $(document).ready(function(){
    	$("input[name=tmeprc]").attr("readonly",true);
    	$('#tmefree').change(function(e){
    		if($(this).val() == 'Y')
		    {
		    	$("input[name=tmeprc]").val(0);
			}
    		else
    		{
        		startRequest();
        	}

        })
    })
    function createQueryString() {
		var eID = $("input[name=tmepid]").val();
		var queryString = {EarId : eID};
		return queryString;
	};
	function startRequest(){
		$.getJSON("/huiermis/earmould/EarMouldAction.do?method=queryEMPro",createQueryString(),
				function(data){
					$("input[name=tmeprc]").val(data[0].price);
				});
	};
</script>
<lemis:body>
    <lemis:base/>
	<lemis:errors />
	<lemis:title title="耳模信息修改" />
	<lemis:tabletitle title="耳模信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/EarMouldAction.do?method=saveModifiedEM"
			method="POST">
			<tr>
				<lemis:texteditor property="tmeno" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="tmecltnm" label="用户姓名" required="true"
					disable="true" />
			    <lemis:texteditor property="tmesid" label="耳模编号" 	required="true" disable="true"/>
			</tr>   
			<tr>
				<lemis:codelisteditor type="tmepid" label="耳模型号" isSelect="false"
					required="true" />
				<lemis:codelisteditor type="tmemat" label="耳模类型" isSelect="false"
					required="true" />
				<lemis:codelisteditor type="tmefree" label="是否赠送" isSelect="true"
					required="true" />
			</tr>
			<tr>
			    <lemis:codelisteditor type="tmecls" label="类别" isSelect="false" required="true"/>
				<lemis:codelisteditor type="tmesta" label="生产状态" isSelect="true"
					required="true" />
				<lemis:texteditor property="tmeprc" label="售价" disable="false"/>
				
			</tr>
			<tr>
				<lemis:formateditor mask="date" format="true" property="tmepdt"
					label="计划完工日期" disable="false" required="false" />
				<lemis:codelisteditor type="tmemaker" label="制作者" required="false"
					isSelect="true" dataMeta="userList" />
				<lemis:formateditor mask="date" format="true" property="tmefmdt"
					label="完工日期" disable="true" required="false" />
			</tr>
			<tr>
			    <lemis:texteditor property="tment"  label="备注" disable="false" colspan="3"/>	
			</tr>
			  <lemis:tabletitle title="质检信息" />
			  <table class="tableinput">
			  <lemis:editorlayout />
			    <tr>
			      <lemis:codelisteditor type="tmeappear" label="外观检验" required="false"
					isSelect="true" />
				  <lemis:codelisteditor type="tmeden" label="气密性检验" required="false"
					isSelect="true" />
				  <lemis:codelisteditor type="tmeckt" label="质检结果" required="false"
					isSelect="true" />
			    </tr>
			    <tr>
			      <lemis:formateditor mask="date" format="true" property="tmechkdt"
					label="质检完成日期" disable="true" required="false" />
				  <lemis:codelisteditor type="tmechkoprcd" label="质检员"
					required="false" isSelect="true" dataMeta="userList" />
			    </tr>
			  </table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom/>
</lemis:body>
</html>