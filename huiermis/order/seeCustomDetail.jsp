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
//List<Button> buttons=new ArrayList<Button>();
//buttons.add(new Button("back","返回","ord020003","history.back()"));
//pageContext.setAttribute("button", buttons);

Map<String,String> buttons = new LinkedHashMap<String,String>();
buttons.put("返回", "history.back()");
pageContext.setAttribute("button", buttons);

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
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&"/>';
		obj.submit();
	};
	
	function examine(obj){
		obj.submit(); 
	}
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
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="定制订单详情" />
	<html:form action="/OrderAction.do?method=examineOrder&tp1=e" method="POST">
	<lemis:tabletitle title="客户基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="用户姓名" disable="true" />
				<lemis:texteditor property="ictgender" label="性别" disable="true" />
				<%-- <lemis:texteditor property="ictbdt" label="出生日期" disable="true" /> --%>
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="出生日期" format="true" disable="true"/>
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
              <html:hidden property="foltype"/>
            <lemis:texteditor property="folno" label="订单号" disable="true" />
            </tr>
			<tr>
				
				<lemis:texteditor property="gctnm" label="送制单位" disable="true"></lemis:texteditor>
				<%-- <td>经办日期</td>
				<td><lemis:operdate /></td> --%>
				<lemis:formateditor required="false" property="foldt" mask="date" label="订货日期" format="true"/>
				<lemis:codelisteditor type="folurgent" label="是否加急订单" isSelect="false"></lemis:codelisteditor>
			</tr>
			<tr>
				<lemis:texteditor property="foldelay" label="延长保修的年限" disable="true" />
				<%-- <lemis:texteditor property="foldps" label="定金" disable="false" ></lemis:texteditor> --%>

				<lemis:texteditor property="folnt" label="备注" required="false"
					disable="true" />
				<lemis:texteditor property="dgndoc" label="验配师姓名" required="false"
					disable="true" />
			</tr>
			</table>
			
			 <lemis:tabletitle title="左耳" />
			<table class="tableinput">

            <lemis:editorlayout />
            <tr>
                    <html:hidden property="dgnid" /> 
					<lemis:texteditor property="dgnlnm" label="定制机型号" required="false"
						disable="true" />
						
					<html:hidden property="dgnlid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpl" label="外壳类型"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folldps" mask="money" label="左耳定金" disable="true" />
					

				</tr>
				
				<tr>
					<lemis:texteditor property="dgnldprc" label="原价" required="false"
						disable="true" />
					 <lemis:texteditor property="dgnldct" label="扣率" required="false"
						disable="true" onblur="computeL()"/> 
					<%-- <lemis:formateditor required="false" property="dgnldct" mask="n.nn" label="扣率" disable="false" onblur="computeL()"/>  --%>
					<lemis:texteditor property="dgncldprc" label="现价" required="false" disable="true"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayleft" label="延长保修的年限（月）" required="false" isSelect="false"/><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeleft"  label="续保费"   required="false" maxlength="100" disable="true"/>
			  	 <lemis:codelisteditor  type="isleft" label="保修是否赠送" required="false" isSelect="false"/>
			  	 </tr>
			</table>
			
	 	 <lemis:tabletitle title="右耳" />
			<table class="tableinput">

            <lemis:editorlayout />
             <tr>
					<lemis:texteditor property="dgnrnm" label="定制机型号" required="false"
						disable="true" />
					<html:hidden property="dgnrid" /> 
					<%-- <lemis:codelisteditor type="fdtsheltpr" label="外壳类型" dataMeta="r"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folrdps" mask="money" label="右耳定金" disable="true" />
					
				</tr>
				<tr>
					<lemis:texteditor property="dgnrdprc" label="原价" required="false"
						disable="true" />
					<lemis:texteditor property="dgnrdct" label="扣率" required="false"
						disable="true" onblur="computeR()"/> 
					<%-- <lemis:formateditor required="false" property="dgnrdct" mask="n.nn" label="扣率" disable="false" onblur="computeR()"/> --%>
					<lemis:texteditor property="dgncrdprc" label="现价" required="false" disable="true"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayright" label="延长保修的年限（月）" required="false" isSelect="false"/><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeright"  label="续保费"   disable="true" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="保修是否赠送" required="false" isSelect="false"/>
			  	 </tr>
			</table>
			
			<lemis:tabletitle title="定制要求" />
		    <table class="tableinput">

            <lemis:editorlayout />
             <tr>
						<lemis:codelisteditor type="dgnpulwire" label="拉线" isSelect="false"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnairvent" label="通气孔" isSelect="false"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnvoswitch" label="音量开关" isSelect="false"></lemis:codelisteditor>

            </tr>
				<tr>       
				        <lemis:texteditor property="dgnsdmd" label="特殊要求" required="false"
						disable="true" />

            </tr>
				
			</table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>