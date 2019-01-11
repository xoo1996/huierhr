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
	buttons.put("审核","examine(document.forms[0])");
	buttons.put("回退","rollback(document.forms[0])");
	buttons.put("返回","history.back()");

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "个人客户编号");
	hidden.put("repfolid","订单号");
	hidden.put("dgnlid","左定制机型号");
	hidden.put("dgnrid","右定制机型号");
	hidden.put("foltype","订单类型");
	hidden.put("folsta","订单状态");
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("hidden", hidden);
%>
<html>
<head>
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
<script src="/lemis/js/Globals.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	$(document).ready(function(){
		$("#repcpy").val("惠耳");
	});
	
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineRepair&tp=r"/>';
		 if(confirm("确定要订单回退吗？"))
	        {
	    		obj.submit(); 
	        }
	};
	
	function examine(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineRepair&tp=e"/>';
		 if(confirm("确定订单审核通过吗？"))
	        {
	    		obj.submit(); 
	        }
	};
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="助听器维修订单详情" />
	<%-- 
	    <lemis:query action="/OrderAction.do?method=examineRepairQuery" editorMeta="editor"
		topic="查询条件" />
	--%>
	<html:form action="/OrderAction.do?method=examineRepair" method="POST">
	<lemis:tabletitle title="客户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="用户姓名" disable="true" />
				<%-- <lemis:texteditor property="ictgender" label="性别" disable="true" />
				<lemis:texteditor property="ictbdt" label="出生日期" disable="true" />
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="出生日期" format="true"/> --%>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="联系电话" disable="true" />
			  <lemis:texteditor property="ictaddr" label="联系地址" disable="true" />
			</tr>
			<tr>
			</tr>
	</table>
			<lemis:tabletitle title="订单基本信息" />
			<table class="tableinput">
           <lemis:editorlayout />
           
           <tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<lemis:texteditor property="gctnm" label="送制单位" disable="false"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:formateditor mask="date"  property="foldt" label="经办日期" disable="true" required="false"/>
           </tr>
				<tr>
				  <html:hidden property="repfolid"/>
				  <lemis:codelisteditor type="repclear" label="是否已清洁"></lemis:codelisteditor>
				  <lemis:texteditor property="reppnm" label="助听器型号" disable="false" />
				  <html:hidden property="reppid"/>
			      <lemis:texteditor property="repid" label="机身编号" required="false" disable="false" />
				</tr>
				
				<tr>
			   <lemis:formateditor required="false" property="repcusdt" mask="date" label="验配日期" format="true" disable="true"/>
				</tr>
			</table>
			
		   <lemis:tabletitle title="维修信息" />
			<table class="tableinput">
            <lemis:editorlayout />
            <tr>
            <lemis:codelisteditor type="repsymptom" label="故障现象"></lemis:codelisteditor>
           <lemis:codelisteditor type="repshellsyp" label="故障现象：（耳道机外壳）"></lemis:codelisteditor>
<%--            <lemis:textareaeditor colspan="1" rowspan="2" property="repotsyp" label="其他故障现象和客户要求"></lemis:textareaeditor> --%>
                <lemis:texteditor property="repotsyp" label="其他故障现象和客户要求" disable="false"></lemis:texteditor>
            </tr>
            <tr>
     
            <lemis:codelisteditor type="reppossyp" label="外壳疼痛或胀，请标明位置"></lemis:codelisteditor>
            <lemis:codelisteditor type="repfree" label="是否在保修期内"></lemis:codelisteditor>
            <lemis:texteditor property="reptime" label="第几次维修" disable="false"></lemis:texteditor>
            </tr>
            <tr>
            <lemis:texteditor property="reppreamt" label="维修费超过（元）" disable="false"></lemis:texteditor>
             <lemis:formateditor  property="reppdate" mask="date"
					label="计划完工日期" format="true" disable="false" required="true"/>
             <lemis:codelisteditor type="repcpy" isSelect="true" label="送修厂商"
					redisplay="true" required="true" />
			 
            </tr>
            <tr>
            <lemis:codelisteditor type="repcls" isSelect="true" label="维修类别"
					redisplay="true" required="true" />
            </tr>
            </table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>