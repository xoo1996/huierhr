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

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("配 货","allocate(document.forms[0])");
	buttons.put("返 回","history.back()");
	
	
	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtdprc","价格",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	header.add(new Formatter("fdtinnt","备注"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("text","fdtdprc","价格","true"));
	batchInput.add(new Editor("text","fdtqnt","数量","true"));
	batchInput.add(new Editor("text","fdtinnt","备注","false"));
	
	Map<String,String> hidden = new LinkedHashMap<String,String>();
	hidden.put("folno", "订单号");
	hidden.put("folctid","客户代码");
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
 <script language="javascript">

	function allocate(obj){
		obj.action='<html:rewrite href="/huiermis/store/AllocateAction.do?method=allocate"/>';
		obj.submit();
	};
		
</script> 

</head>
<lemis:base />
<lemis:body>
	<lemis:title title="配货明细" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/AllocateAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<lemis:texteditor property="folctnm" label="团体客户" disable="true" />
				<lemis:formateditor mask="date" property="foldt" label="订货日期" 
					required="false" disabled="true" />
			</tr>
			<tr>
				<td>经办人</td>
				<td><lemis:operator /></td>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单详情"
		action="/AllocateAction.do?" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>