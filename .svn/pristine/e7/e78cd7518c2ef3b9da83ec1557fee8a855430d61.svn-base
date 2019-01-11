<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	
	//List<Button> buttons=new ArrayList<Button>();
	//buttons.add(new Button("hedz","惠耳定制","clt020201","creatOrderh(document.forms[0])"));
	//buttons.add(new Button("jwdz","杰闻定制","clt020204","creatOrderj(document.forms[0])"));
	//buttons.add(new Button("jcdz","寄厂定制","clt020202","creatOrder1(document.forms[0])"));
	//buttons.add(new Button("back","返回","clt020203","history.back()"));
	
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("惠耳定制","creatOrderh(document.forms[0])"); 
    buttons.put("杰闻定制","creatOrderj(document.forms[0])"); 
    buttons.put("寄厂定制","creatOrder1(document.forms[0])"); 
    buttons.put("返回","history.back()"); 
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("company", "厂商");
	
    pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
%>

<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function register(obj) {
		obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=enter"/>';
		obj.submit();
	};
	function creatOrderh(obj) {
		var cnm = $('input[name=ictnm]').val();
		var cid = $('input[name=ictid]').val();
		var gid = $('input[name=ictgctid]').val();
		var lpid = $('input[name=dgnlid]').val();
		var rpid = $('input[name=dgnrid]').val();
		if (!checkValue(obj)) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("无定制机信息，无法生成订单");
			return false;
		} else if (rpid == '') {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=h&"/>'
					+ 'num=1&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&plr=l&pid='
					+ lpid
					+ '&cnm=' + cnm;
		} else if (lpid == '') {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=h&"/>'
					+ 'num=1&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&plr=r&pid='
					+ rpid
					+ '&cnm=' + cnm;
		} else {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=h&"/>'
					+ 'num=2&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&lpid='
					+ lpid
					+ '&rpid=' + rpid + '&cnm=' + cnm;
		}
		if (confirm("确实要生成定制记录并录入订单吗？")) {
			obj.submit();
		}
	};
	function creatOrderj(obj) {
		var cnm = $('input[name=ictnm]').val();
		var cid = $('input[name=ictid]').val();
		var gid = $('input[name=ictgctid]').val();
		var lpid = $('input[name=dgnlid]').val();
		var rpid = $('input[name=dgnrid]').val();
		if (!checkValue(obj)) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("无定制机信息，无法生成订单");
			return false;
		} else if (rpid == '') {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=j&"/>'
					+ 'num=1&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&plr=l&pid='
					+ lpid
					+ '&cnm=' + cnm;
		} else if (lpid == '') {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=j&"/>'
					+ 'num=1&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&plr=r&pid='
					+ rpid
					+ '&cnm=' + cnm;
		} else {
			obj.action = '<html:rewrite href="/huiermis/customization/CustomizationAction.do?method=saveNew&type=j&"/>'
					+ 'num=2&gid='
					+ gid
					+ '&cid='
					+ cid
					+ '&lpid='
					+ lpid
					+ '&rpid=' + rpid + '&cnm=' + cnm;
		}
		if (confirm("确实要生成定制记录并录入订单吗？")) {
			obj.submit();
		}
	};
	function creatOrder1(obj) {
		var cnm = $('input[name=ictnm]').val();
		var gid = $('input[name=ictgctid]').val();
		var lpid = $('input[name=dgnlid]').val();
		var rpid = $('input[name=dgnrid]').val();
		if (!checkValue(obj)) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("无定制机信息，无法生成订单");
			return false;
		} else {
			obj.action = '<html:rewrite href="/huiermis/client/DiagnoseAction.do?method=saveNew1&"/>'
					+ 'gid=' + gid + '&cnm=' + cnm;
		}
		if (confirm("确实要录入订单吗？")) {
			obj.submit();
		}
	};
	function leftRepair(obj) {
		var cnm = $('input[name=ictnm]').val();
		var gid = $('input[name=ictgctid]').val();
		var lpid = $('input[name=dgnlid]').val();
		if (lpid == '') {
			alert("用户左耳无定制机信息，无法生成维修记录");
			return false;
		} else {
			obj.action = '<html:rewrite href="/huiermis/repair/RepairAction.do?method=enter&"/>'
					+ 'gid=' + gid + '&cnm=' + cnm + '&plr=l&pid=' + lpid;
		}
		if (confirm("确实要维修登记吗？")) {
			obj.submit();
		}
	};
	function rightRepair(obj) {
		var cnm = $('input[name=ictnm]').val();
		var gid = $('input[name=ictgctid]').val();
		var rpid = $('input[name=dgnrid]').val();
		if (rpid == '') {
			alert("用户右耳无定制机信息，无法生成维修记录");
			return false;
		} else {
			obj.action = '<html:rewrite href="/huiermis/repair/RepairAction.do?method=enter&"/>'
					+ 'gid=' + gid + '&cnm=' + cnm + '&plr=r&pid=' + rpid;
		}
		if (confirm("确实要维修登记吗？")) {
			obj.submit();
		}
	};
</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="诊治信息查看" />
	<lemis:tabletitle title="个人客户信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=saveModified"
			method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="用户编号" required="true"
					disable="true" maxlength="20" />
				<lemis:texteditor property="ictnm" label="姓名" required="true"
					disable="true" maxlength="20" />
				<lemis:texteditor property="ictgctid" label="团体代码" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="ictgender" isSelect="false" label="性别"
					redisplay="true" required="false" />
				<lemis:formateditor mask="date" property="ictbdt" label="出生日期"
					format="true" required="false" disable="true" />
				<lemis:texteditor property="ictpnm" label="家长姓名" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
				<lemis:texteditor property="ictaddr" label="联系地址" required="false"
					disable="true" maxlength="40" />
				<lemis:texteditor property="icttel" label="联系电话" required="false"
					disable="true" maxlength="20" />
				<lemis:texteditor property="ictpcd" label="邮政编码" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
				<lemis:codelisteditor type="ictphis" isSelect="false"
					label="用过何种助听器" redisplay="true" required="true" />
				<lemis:texteditor property="ictnt" label="备注" disable="true"
					maxlength="20" />
				<lemis:texteditor property="ictsrc" label="来源" required="false"
					disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="dgnmmk" label="耳印制作者" required="false"
					disable="true" maxlength="20" />
				<lemis:formateditor mask="date" property="dgndt" label="检查日期"
					format="true" required="false" disable="true" />
				<lemis:formateditor mask="date" property="dgnpfdt" label="预计取货日期"
					format="true" required="false" disable="true" />
			</tr>
			<tr>
				<lemis:texteditor property="dgnsdmd" label="特殊要求" required="false"
					disable="true" maxlength="20" />
				<lemis:texteditor property="dgnctimp" label="用户反映" required="false"
					disable="true" />
				<lemis:formateditor mask="date" property="dgnafdt" label="实际取货日期"
					format="true" required="false" disable="true" />
			</tr>
			
			<table id="iframediv" border="0">
				<tr>
					<td><iframe
					src='<html:rewrite page="/DiagnoseAction.do?method=showLeft"/>'
					id="iframe1" name="iframe1" height="320" frameborder="0"
					width="100%"></iframe></td>
					<td><iframe
					src='<html:rewrite page="/DiagnoseAction.do?method=showRight"/>'
					id="iframe2" name="iframe2" height="320" frameborder="0"
					width="100%"></iframe></td>
				</tr>
			</table>
			
			
			<lemis:tabletitle title="左耳定制机" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="dgnlid" isSelect="false" label="助听器型号"
						redisplay="true" required="false" dataMeta="productList" />
					<lemis:texteditor property="dgnldprc" label="零售" required="false"
						disable="true" maxlength="20" />
				</tr>
				<tr>
					<lemis:texteditor property="dgnlufa" label="不适域" required="false"
						disable="true" maxlength="20" />
				</tr>
			</table>
			<lemis:tabletitle title="右耳定制机" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="dgnrid" isSelect="false" label="助听器型号"
						redisplay="true" required="false" dataMeta="productList" />
					<lemis:texteditor property="dgnrdprc" label="零售" required="false"
						disable="true" maxlength="20" />
					<tr>
						<tr>
							<lemis:texteditor property="dgnrufa" label="不适域" required="false"
								disable="true" maxlength="20" />
						</tr>
			</table>
			<!--<lemis:tabletitle title="定制厂商" />
			<table class="tableinput">
			<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="company" isSelect="true" label="厂商"
						redisplay="true" required="true" />
				</tr>
			</table>-->
		</html:form>
	</table>

	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

