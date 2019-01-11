<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
//List<Button> buttons=new ArrayList<Button>();
//buttons.add(new Button("hedz","保存","clt020201","save(document.forms[0])"));
//buttons.add(new Button("jwdz","提交","clt020204","commit(document.forms[0])"));
/*buttons.add(new Button("jcdz","寄厂定制","clt020202","creatOrder1(document.forms[0])")); */
//buttons.add(new Button("back","返 回","clt020203","history.back()"));
Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("保存","save(document.forms[0])");
buttons.put("提交","commit(document.forms[0])");
buttons.put("返回","history.back()");

	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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
<%--  $(document).ready(function(){
		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
		

		$("input[name=reppnm]").autocomplete(products,{
			max : 20,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		
		$("input[name=reppnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=reppnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
				/* $("input[name=dgnldprc]").val(prc); */
			}
		});
		
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		if(grCli!="1501000000")
		{
			$("select[name=repcpy]").attr("disabled",true);
		}
	}); --%>
$(document).ready(function(){	
 //$("input[name=reppnm]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=rep",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=reppnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=reppnm]").result(function(event, data, formatted) {
							if (data){
								var pid=data.proid;
								var pnm = data.proname;
								$("input[name=reppnm]").val(pnm);
								$(this).parent().next().find("input").val(pid);

							}
						});
					}
			});
	//});
 
}); 
	function commit(obj) {
		if (!checkValue(obj)) {
			return false;
		}
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=modifyCusRep&tp=c"/>';
		if (confirm("确实要录入订单并提交吗？")) {
			obj.submit();
		}
	};
	
	function save(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=modifyCusRep&tp=b"/>';
		if (confirm("确实要修改并保存订单吗？")) {
			obj.submit();
		}
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
	<html:form action="/OrderAction.do" method="POST">
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
			<%--   <lemis:codelisteditor type="ictphis" label="使用过何种助听器"></lemis:codelisteditor> --%>
		<%-- 	 <lemis:texteditor property="ictphis" label="使用过何种助听器" disable="true" />   --%>
			</tr>
			<tr>
			 <%--  <lemis:texteditor property="ictsrc" label="来源" disable="true" />
			  <lemis:texteditor property="ictnt" label="备注" disable="true" /> --%>
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
			<%-- <table id="iframediv" border="0">
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
	       </table> --%>
    
 
			<lemis:tabletitle title="订单基本信息" />
			<table class="tableinput">
           <lemis:editorlayout />

           <tr>
				<%-- <td>送修单位</td>
				<td><lemis:operorg /></td>
				<td>经办日期</td>
				<td><lemis:operdate /></td> --%>
				<lemis:texteditor property="gctnm" label="送修单位" disable="true" required="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="经办日期"></lemis:texteditor>
			   
				</tr>
				<tr>
				  <html:hidden property="repfolid"/>
				  <lemis:codelisteditor type="repclear" label="是否已清洁"></lemis:codelisteditor>
				  <lemis:texteditor property="reppnm" label="助听器型号" disable="true" />
				  <html:hidden property="reppid" />
			      <lemis:texteditor property="repid" label="机身编号" required="false" disable="true" />
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
            <%-- <lemis:codelisteditor type="reppreamt" label="维修费超过"></lemis:codelisteditor>--%>
            <lemis:texteditor property="reppreamt" label="维修费超过（元）" disable="false"></lemis:texteditor>
        <%--     <lemis:codelisteditor type="repcpy" label="送修厂商" redisplay="true"/> --%>
            </tr>
            </table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>