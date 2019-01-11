<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ncdpid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "商品单价"));
	header.add(new Formatter("ncdqnt", "售出数量"));
	header.add(new Formatter("ncddis", "商品扣率"));
	header.add(new Formatter("ncdmon", "实际收费"));
	//header.add(new Formatter("ncdrecmon", "退货金额"));
	header.add(new Formatter("ncdnt", "备注"));
	header.add(new Formatter("ncdsta", "状态"));
	header.add(new Formatter("ncdrnt", "退货数量"));
	header.add(new Formatter("ncdrecmon", "退货金额"));
	

	List<Editor> batchInput = new ArrayList<Editor>();
	/* batchInput.add(new Editor("text", "pdtid", "商品代码", "false")); */
/* 	batchInput.add(new Editor("text", "ncdsta", "状态", "false")); */
	batchInput.add(new Editor("text", "ncdrnt", "退货数量", "true"));
	batchInput.add(new Editor("text", "ncdrecmon", "退货金额", "true"));

	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("打 印","print(document.forms[0])");
	buttons.put("退货到直属店", "saveData(document.forms[0])");
/* 	buttons.put("修改", "modify(document.forms[0])");
	buttons.put("退机到总部", "commit(document.forms[0])"); */
	buttons.put("返回","history.back()");

	 Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ncdid", "收费号"); 
	hidden.put("ncdpid", "商品代码"); 
	hidden.put("ncdsta", "状态"); 
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("batchInput", batchInput);
	pageContext.setAttribute("header", header);
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
function saveData(obj) {
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	if (confirm("确实要退机到直属店吗？")) {
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=s&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
	}
	//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
	else
		return t;
};

function modify(obj) {
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=m&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
};


function commit(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=c&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
};

function print(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=printNorRec"/>';
	obj.submit();
};
</script>

<lemis:body>
	<lemis:base />
    <lemis:title title="普通商品收费详情" />
	<lemis:tabletitle title="收费信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveNormalCharge"
			method="POST">
			<tr>
				<html:hidden property = "ictid"/>
				<lemis:texteditor property="chgid" label="收费号" disable="true" /> 
				<lemis:texteditor property="gctnm" label="所属团体" disable="true"/>
				<lemis:texteditor property="ictnm" label="用户名称" disable="true"/>
			</tr>
			<tr>
				<lemis:formateditor required="false" property="chgdt" mask="date" label="收费日期" format="true"/>
			<%-- 	<lemis:formateditor required="false" property="chgrecdt" mask="date" label="退机日期" format="true"/>
				<lemis:formateditor required="false" property="chgrecheaddt" mask="date" label="退回总部日期" format="true"/> --%>
			</tr>
		</html:form>
	</table>
	<lemis:table topic="商品明细"
		action="/ChargeAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" hiddenMeta="hidden"
		 batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


