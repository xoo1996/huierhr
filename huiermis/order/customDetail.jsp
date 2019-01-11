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
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","审核","clt020201","examine(document.forms[0])"));
/* buttons.add(new Button("jwdz","特殊扣率审核","ord110001","disExa(document.forms[0])"));   */
buttons.add(new Button("jcdz","回退","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","返回","clt020203","history.back()"));

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
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "个人客户编号");
	hidden.put("folno","订单号");
	hidden.put("dgnlid","左定制机型号");
	hidden.put("dgnrid","右定制机型号");
	hidden.put("dgnid","诊断流水号");
	hidden.put("folsta","订单状态");
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
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
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&folsta="/>' + $('input:hidden[name=folsta]').val();/*&repfolid="/>' + $('input:hidden[name=folno]').val();*/
        if(confirm("确定要订单回退吗？"))
        {
    		obj.submit(); 
        }
	};
	
	function examine(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=e&repfolid="/>' + $('input:hidden[name=folno]').val();
		if(confirm("确定订单审核通过吗？"))
	    {
	        obj.submit(); 
	    }
	}
	
	function disExa(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/order&page=/disExa.jsp"/>';
		obj.submit();
	};
	
	function computeL(obj) {
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgnldct=$('input[name=dgnldct]').val();
		$('input[name=dgncldprc]').val(parseFloat(dgnldprc)*parseFloat(dgnldct));
	}
	
	function computeR(obj) {
		 var dgnrdprc = $('input[name=dgnrdprc]').val();
		 var dgnrdct=$('input[name=dgnrdct]').val();
		/*  alert(dgnrdprc); */
		 $('input[name=dgncrdprc]').val(parseFloat(dgnrdprc)*parseFloat(dgnrdct));
		/*  alert(parseFloat(dgnrdct)); */
	}
	 $(document).ready(function(){
	//左耳续保费生成
	$('#delayleft').change(function(){
		var price = 25;
		var delay = $('#delayleft').val();
		$('input[name=feeleft]').val(price*delay);

	});
	$("input[name=feeleft]").attr("readonly","true"); 
	//右耳续保费生成
	$('#delayright').change(function(){
		var price = 25;
		var delay = $('#delayright').val();
		$('input[name=feeright]').val(price*delay);

	});
	$("input[name=feeright]").attr("readonly","true"); 
	 })
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="定制订单详情" />
	<html:form action="/OrderAction.do?" method="POST">
	
		<%-- <lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
			    <html:hidden property="foltype"/>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<lemis:texteditor property="gctnm" label="所属团体" disable="true" />
				<lemis:formateditor required="false" property="foldt" mask="date" label="订货日期" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="联系电话" disable="true" />
			  <lemis:texteditor property="ictaddr" label="联系地址" disable="true" />
			  <lemis:codelisteditor type="ictphis" label="使用过何种助听器"></lemis:codelisteditor>
			 <lemis:texteditor property="ictphis" label="使用过何种助听器" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="来源" disable="true" />
			  <lemis:texteditor property="ictnt" label="备注" disable="true" />
			  <lemis:texteditor property="ictphis" label="使用过何种助听器" disable="true" />
			</tr>
	</table> --%>
	
	<lemis:tabletitle title="客户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="用户姓名" disable="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true" />
				<%-- <lemis:texteditor property="ictbdt" label="出生日期" disable="true" /> --%>
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="出生日期" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="联系电话" disable="true" />
			  <lemis:texteditor property="ictaddr" label="联系地址" disable="true" />
			<%--   <lemis:codelisteditor type="ictphis" label="使用过何种助听器"></lemis:codelisteditor> --%>
			 <lemis:texteditor property="ictphis" label="使用过何种助听器" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="来源" disable="true" />
			  <lemis:texteditor property="ictnt" label="备注" disable="true" />
			  <lemis:codelisteditor type="ictfrom" label="来源" required="true" />
			 <%--  <lemis:texteditor property="ictphis" label="使用过何种助听器" disable="true" /> --%>
			</tr>
	</table>
	
		<lemis:tabletitle title="左耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
					/>
					<lemis:texteditor property="lg500" label="500" required="false"
					 />
					 <lemis:texteditor property="lg750" label="750" required="false"
					 />
					<lemis:texteditor property="lg1000" label="1000" required="false"
					 />
					 <lemis:texteditor property="lg1500" label="1500" required="false"
					 />
					<lemis:texteditor property="lg2000" label="2000" required="false"
					/>
					<lemis:texteditor property="lg3000" label="3000" required="false"
					 />
					<lemis:texteditor property="lg4000" label="4000" required="false"
					 onblur="lgvag()" />
					 <lemis:texteditor property="lg6000" label="6000" required="false"
					 onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="平均" required="false"
					 />
				</tr>
			</table>

			<lemis:tabletitle title="左耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						 style="border-width:1px;border-color=black" />
						 <lemis:texteditor property="lq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="lqvag()" />
						 <lemis:texteditor property="lq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lqavg" label="平均" required="false"
						 style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						/>
					<lemis:texteditor property="rg500" label="500" required="false"
						 />
				 <lemis:texteditor property="rg750" label="750" required="false"
						 />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						/>
				<lemis:texteditor property="rg1500" label="1500" required="false"
						 />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						 />
				<lemis:texteditor property="rg3000" label="3000" required="false"
						 />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						 onblur="rgvag()" />
				<lemis:texteditor property="rg6000" label="6000" required="false"
						 />
					<lemis:texteditor property="rgavg" label="平均" required="false"
						/>
				</tr>
			</table>

			<lemis:tabletitle title="右耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="rqvag()"/>
						 <lemis:texteditor property="rq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rqavg" label="平均" required="false"
						style="border-width:1px;border-color=black" />
				</tr>
			</table>
			
			<table id="iframediv" border="0">
		    <tr>
			<td><iframe
				src='<html:rewrite page="/CustomOrderAction.do?method=showLeft"/>'
				id="iframe1" name="iframe1" height="320" frameborder="0"
				width="100%"></iframe></td>
			<td><iframe
				src='<html:rewrite page="/CustomOrderAction.do?method=showRight"/>'
				id="iframe2" name="iframe2" height="320" frameborder="0"
				width="100%"></iframe></td>
		    </tr>
	       </table>
    
 
			<lemis:tabletitle title="订单基本信息" />
			<table class="tableinput">
           <lemis:editorlayout />
           <tr>
           <lemis:texteditor property="folno" label="订单号" disable="true" />
           </tr>
           <tr>
			    <html:hidden property="foltype"/>
				<lemis:texteditor property="gctnm" label="送制单位" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="经办日期" disable="true"></lemis:texteditor>
				 <lemis:codelisteditor type="folurgent" label="是否加急订单"  required="true"></lemis:codelisteditor>
				</tr>
				<tr>
			 <%--  <lemis:texteditor property="foldelay" label="延长保修的年限" disable="false" /> --%>
			  <%-- <lemis:texteditor property="foldps" label="定金" disable="false" ></lemis:texteditor> --%>
			   <lemis:texteditor property="folnt" label="备注" required="false" disable="false" />
			   <lemis:texteditor property="dgndoc" label="验配师姓名" required="false"
						disable="false" />
				</tr>
			</table>
			 <lemis:tabletitle title="左耳" />
			<table class="tableinput">

            <lemis:editorlayout />
            <tr>
                    <html:hidden property="dgnid" /> 
					<lemis:texteditor property="dgnlnm" label="定制机型号" required="false"
						disable="false" />
					<html:hidden property="dgnlid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpl" label="外壳类型"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folldps" mask="money" label="左耳定金" disable="false" />
					
				</tr>
				
				<tr>
					<lemis:texteditor property="dgnldprc" label="原价" required="false"
						disable="false" />
					 <lemis:texteditor property="dgnldct" label="扣率" required="false"
						disable="false" onblur="computeL()"/> 
					<%-- <lemis:formateditor required="false" property="dgnldct" mask="n.nn" label="扣率" disable="false" onblur="computeL()"/>  --%>
					<lemis:texteditor property="dgncldprc" label="现价" required="false" disable="false"/>
				</tr>
				<lemis:codelisteditor  type="delayleft" label="延长保修的年限（月）" required="false" /><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeleft"  label="续保费"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isleft" label="保修是否赠送" required="false" />
			</table>
			
	 	 <lemis:tabletitle title="右耳" />
			<table class="tableinput">

            <lemis:editorlayout />
             <tr>
					<lemis:texteditor property="dgnrnm" label="定制机型号" required="false"
						disable="false" />
					<html:hidden property="dgnrid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpr" label="外壳类型" dataMeta="r"/> --%>
					<lemis:formateditor required="false" property="folrdps" mask="money" label="右耳定金" disable="false" />
					
				</tr>
				<tr>
					<lemis:texteditor property="dgnrdprc" label="原价" required="false"
						disable="false" />
						 <lemis:texteditor property="dgnrdct" label="扣率" required="false"
						disable="false" onblur="computeR()"/> 
						<%-- <lemis:formateditor required="false" property="dgnrdct" mask="n.nn" label="扣率" disable="false" onblur="computeR()"/> --%>
						<lemis:texteditor property="dgncrdprc" label="现价" required="false" disable="false"/>
				</tr>
				<lemis:codelisteditor  type="delayright" label="延长保修的年限（月）" required="false" /><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeright"  label="续保费"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="保修是否赠送" required="false" />
			</table>
			
			<lemis:tabletitle title="定制要求" />
		    <table class="tableinput">

            <lemis:editorlayout />
             <tr>
						<lemis:codelisteditor type="dgnpulwire" label="拉线"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnairvent" label="通气孔"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnvoswitch" label="音量开关"></lemis:codelisteditor>

            </tr>
				<tr>       
				        <lemis:texteditor property="dgnsdmd" label="特殊要求" required="false"
						disable="false" />

            </tr>
			</table>
            <lemis:tabletitle title="定制类型" />
		   

            <lemis:editorlayout />
             <table class="tableinput" >
            <tr>
					<html:radio property="type" value="h" indexed="0" >惠耳定制</html:radio>
					<html:radio property="type" value="j" >杰闻定制</html:radio>
					<html:radio property="type" value="c" >寄厂定制</html:radio>
            </tr>
			</table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>