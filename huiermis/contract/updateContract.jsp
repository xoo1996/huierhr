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
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","save(document.forms[0])"); 


	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtprc","单价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("money","fdtprc","单价","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","数量","true"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);

	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/client/";
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/CityJson.js"></script>
<script src="/huiermis/js/DistrictJson.js"></script>
<script src="/huiermis/js/ProJson.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!-- <script src="/huiermis/js/inputDetail.js"> -->


 <script language="javascript">
 function save(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if(confirm("是否保存修改？","是","否"))
		 {  
			obj.submit();
		 }
				 
			
	};  
</script>

</head>
<lemis:base />
<lemis:body>
	<lemis:title title="新增合同" />
	<lemis:tabletitle title="客户基本信息" />
	
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/ContractAction.do?method=saveContract" method="POST">
			<tr>
				<lemis:texteditor property="useremployid" label="员工工号" disable="true"
					required="true" />
				<lemis:texteditor property="username" label="员工姓名" disable="true"
					required="true" />
				<lemis:texteditor property="conid" label="合同编号" disable="true"
					required="true" />
			</tr>
			<tr>
			<lemis:codelisteditor type="contype" label="合同类型" required="true" />
				<lemis:formateditor required="false" property="condatestart" mask="date"
					label="合同开始日期" format="true" disable="false" required="true" />
					<lemis:formateditor required="false" property="condateend" mask="date"
					label="合同到期日期" format="true" disable="false" required="true" />
			</tr>
			<tr>
					<lemis:formateditor required="false" property="condatesign" mask="date"
					label="合同签约日期" format="true" disable="false" required="true" />
			</tr>

		</html:form>  
 	</table>
	<lemis:buttons buttonMeta="button" />
		
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>