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
	};
	/* function createQueryString(e,ac) {
		var pID = $(e.target).val();
		var queryString = {
			actionID : pID,
			ac : ac
		};
		return queryString;
	};
	function createQueryString2(e,ac) {
		var queryString = {
			actionID : e,
			ac : ac
		};
		return queryString;
	};
	function startRequest(e,ac) {
		$.getJSON("/huiermis/repair/RepairAction.do?method=getOnAction",
				createQueryString(e,ac), function(data) {
			if(ac=="REPACTION1"){
				$('input[name=repaction1prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION2"){
				$('input[name=repaction2prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION3"){
				$('input[name=repaction3prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION4"){
				$('input[name=repaction4prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION5"){
				$('input[name=repaction5prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION6"){
				$('input[name=repaction6prc]').val(data[0].aaa006);
			}
			getprc();	
				});	
	};
	function startRequest2(e,ac) {
		$.getJSON("/huiermis/repair/RepairAction.do?method=getOnAction",
				createQueryString2(e,ac), function(data) {
			if(ac=="REPACTION1"){
				$('input[name=repaction1prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION2"){
				$('input[name=repaction2prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION3"){
				$('input[name=repaction3prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION4"){
				$('input[name=repaction4prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION5"){
				$('input[name=repaction5prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION6"){
				$('input[name=repaction6prc]').val(data[0].aaa006);
			}
					
				});	
	};
	function chuhua() {
		if($('input[name=repaction1]').val()!=""){
			startRequest2($('input[name=repaction1]').val(),"REPACTION1");
		};
		if($('input[name=repaction2]').val()!=""){
			startRequest2($('input[name=repaction2]').val(),"REPACTION2");
		};
		if($('input[name=repaction3]').val()!=""){
			startRequest2($('input[name=repaction3]').val(),"REPACTION3");
		};
		if($('input[name=repaction4]').val()!=""){
			startRequest2($('input[name=repaction4]').val(),"REPACTION4");
		};
		if($('input[name=repaction5]').val()!=""){
			startRequest2($('input[name=repaction5]').val(),"REPACTION5");
		};
		if($('input[name=repaction6]').val()!=""){
			startRequest2($('input[name=repaction6]').val(),"REPACTION6");
		};
	};
	function getprc(){
		var v1 = $("input[name=repaction1prc]").val();
		var v2 = $("input[name=repaction2prc]").val();
		var v3 = $("input[name=repaction3prc]").val();
		var v4 = $("input[name=repaction4prc]").val();
		var v5 = $("input[name=repaction5prc]").val();
		var v6 = $("input[name=repaction6prc]").val();
		if(v1 == "")
			v1 = 0;
		if(v2 == "")
			v2 = 0;
		if(v3 == "")
			v3 = 0;
		if(v4 == "")
			v4 = 0;
		if(v5 == "")
			v5 = 0;
		if(v6 == "")
			v6 = 0;
		var v =  parseFloat(v1) +  parseFloat(v2) +  parseFloat(v3) +  parseFloat(v4) +  parseFloat(v5) +  parseFloat(v6);
		$("input[name=repaprc]").val(v);

	};
	$(document).ready( function() {
		chuhua();
		$('#repaction1').change(function(e) {
			startRequest(e,"REPACTION1");
		});
		$('#repaction2').change(function(e) {
			startRequest(e,"REPACTION2");
		});
		$('#repaction3').change(function(e) {
			startRequest(e,"REPACTION3");
		});
		$('#repaction4').change(function(e) {
			startRequest(e,"REPACTION4");
		});
		$('#repaction5').change(function(e) {
			startRequest(e,"REPACTION5");
		});
		$('#repaction6').change(function(e) {
			startRequestRight(e,"REPACTION6");
		});
	}); */
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="修改维修记录" />
	<lemis:tabletitle title="维修信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/RepairAction.do?method=saveModified" method="POST">
			<tr>
				<html:hidden property="repidentity" />
				<lemis:texteditor property="repfolid" label="订单号" required="true"
					disable="true" />
				<lemis:texteditor property="repcltnm" label="用户姓名" required="true"
					disable="true" />
				<lemis:texteditor property="reppnm" label="助听器型号" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="repid" label="机身编号" disable="true"
					required="false" />
				<lemis:texteditor property="repgctid" label="送修单位" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="repdate"
					label="送修日期" disable="true" required="true" />
			</tr>
			<%-- <tr>
				<lemis:codelisteditor type="repfree" isSelect="true" label="保修期"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="reppreamt" isSelect="true" label="维修费通知"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="true" label="修理类别"
					redisplay="true" required="false" />
			</tr> --%>
			<tr>
				<lemis:codelisteditor type="repcpy" isSelect="true" label="送修厂商"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="repsta" isSelect="true" label="维修状态"
					redisplay="true" required="true" />
			</tr>
			<%-- <tr>
				<lemis:texteditor property="repdeclare" label="故障详情" disable="false"
					required="false" colspan="5" />
			</tr> --%>
			
			<lemis:tabletitle title="维修结果" />
			<table class="tableinput">
				<lemis:editorlayout />
				
				
				<tr>
					<lemis:texteditor property="repaction1" label="维修措施" disable="false"
						required="false"  />
					<lemis:formateditor mask="nnnn.nn" property="repamt" label="费用合计"
						disable="false" required="false" />
					<td>维修员</td>
					<td><lemis:operator/></td>
				</tr>
				<tr>
					<lemis:texteditor property="repnote" label="备注" disable="false"
						required="false" colspan="5" />
				</tr>
				
			
			
				<%-- <tr>
					<lemis:texteditor property="repconfirmed" label="故障确认"
						disable="false" required="false" colspan="5" />
				</tr>
				<tr>
				 <lemis:codelisteditor type="repaction1" isSelect="true"
						label="维修措施一" redisplay="true" required="false"
						dataMeta="action1List" />
					<lemis:texteditor property="repaction1prc" label="措施一费用" required="false"
						disable="false"  />
				   <lemis:codelisteditor type="repaction2" isSelect="true"
						label="维修措施二" redisplay="true" required="false"
						dataMeta="action2List" />
				</tr>
				<tr>
					<html:hidden property="repaction2" />
					<lemis:texteditor property="repaction2prc" label="措施二费用" required="false"
						disable="false"  />
				  <lemis:codelisteditor type="repaction3" isSelect="true"
						label="维修措施三" redisplay="true" required="false"
						dataMeta="action3List" />
					<html:hidden property="repaction3" />
					<lemis:texteditor property="repaction3prc" label="措施三费用" required="false"
						disable="false"  />
				</tr>
				<tr>
				   <lemis:codelisteditor type="repaction4" isSelect="true"
						label="维修措施四" redisplay="true" required="false"
						dataMeta="action4List" />
					<html:hidden property="repaction4" />
					<lemis:texteditor property="repaction4prc" label="措施四费用" required="false"
						disable="false"  />
				  <lemis:codelisteditor type="repaction5" isSelect="true"
						label="维修措施五" redisplay="true" required="false"
						dataMeta="action5List" />
				</tr>
				<tr>
					<html:hidden property="repaction5" />
					<lemis:texteditor property="repaction5prc" label="措施五费用" required="false"
						disable="false"  />
				<lemis:codelisteditor type="repaction6" isSelect="true"
						label="维修措施六" redisplay="true" required="false"
						dataMeta="action6List" />
					<html:hidden property="repaction6" />
					<lemis:texteditor property="repaction6prc" label="措施六费用" required="false"
						disable="false"  />
				</tr>
				<tr>
				    <lemis:texteditor property="repaprc" label="费用预计" disable="false"
						required="false" />
					<lemis:texteditor property="repamt" label="费用合计" disable="false"
						required="false" />
					<lemis:codelisteditor type="repoprcd" isSelect="true" label="维修员"
						redisplay="true" required="false" dataMeta="userList" />
				
				</tr>
				<tr>
				   	<lemis:codelisteditor type="repregnames" isSelect="true"
						label="其他维修员" redisplay="true" required="false"
						dataMeta="userList" />
					<lemis:texteditor property="repattention" label="注意事项"
						disable="false" required="false" colspan="3" />
				</tr>
				<tr>
					<lemis:texteditor property="reptip" label="温馨提示1" disable="false"
						required="false" />
					<lemis:texteditor property="reptip1" label="温馨提示2" disable="false"
						required="false" />
					<lemis:formateditor mask="date" format="true" property="repfdate"
						label="完工日期" required="false" />
				</tr> --%>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

