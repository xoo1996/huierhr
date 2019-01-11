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
//buttons.add(new Button("hedz","保 存","ord020004","save(document.forms[0])"));
//buttons.add(new Button("tj","提 交","ord020005",""));
//buttons.add(new Button("back","返 回","ord020006","history.back()"));
Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("保存","save(document.forms[0])");
buttons.put("提交","startRequest()");//commit(document.forms[0])
buttons.put("返回","history.back()");
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "个人客户编号");
	hidden.put("folno","订单号");
	hidden.put("dgnlid","左定制机型号");
	hidden.put("dgnrid","右定制机型号");    
	hidden.put("dgnldct","左扣率"); 
	hidden.put("dgnrdct","右扣率");    
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
	
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
		

		$("input[name=dgnlnm]").autocomplete(products,{
			max : 20,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=dgnlnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=dgnlnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
				$("input[name=dgnldprc]").val(prc);
			}
		});
		
		$("input[name=dgnrnm]").autocomplete(products,{
			max : 20,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=dgnrnm]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=dgnrnm]").val(pnm);
				$(this).parent().next().find("input").val(pid);
				$("input[name=dgnrdprc]").val(prc);
			}
		});
		
		$("input[name=tj]").bind("click",function(e){
			startRequest(e);
			
		});
		

	}); --%>
 
$(document).ready(function(){
 //$("input[name=dgnlnm]").bind("click",function(){
	$("input[name=dgnldprc]").attr('readonly','readonly');
	$("input[name=dgnrdprc]").attr('readonly','readonly');
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=cus",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=dgnlnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=dgnlnm]").result(function(event, data, formatted) {
							if (data){
								var pid=data.proid;
								var pnm = data.proname;
								var prc=data.proprc;
								$("input[name=dgnlnm]").val(pnm);
								$("input[name=dgnldprc]").val(prc);
								$(this).parent().next().find("input").val(pid);
								$("input[name=dgncldprc]").val(prc);

							}
						});
					}
			//});
	});
	
	//$("input[name=dgnrnm]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=cus",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=dgnrnm]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=dgnrnm]").result(function(event, data, formatted) {
							if (data){
								var pid=data.proid;
								var pnm = data.proname;
								var prc=data.proprc;
								$("input[name=dgnrnm]").val(pnm);
								$("input[name=dgnrdprc]").val(prc);
								$(this).parent().next().find("input").val(pid);
								$("input[name=dgncrdprc]").val(prc);
							}
						});
					}
			//});
	}); 
	
	$("input[name=tj]").bind("click",function(e){
		startRequest(e);
		
	});
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
	
});
	function createQueryString(e) {
		var lpid = $('input:hidden[name=dgnlid]').val();  //定制机编号
		var rpid = $('input:hidden[name=dgnrid]').val();
		var grid=$('input:hidden[name=folctid]').val(); 
		var folno=$('input[name=folno]').val(); 
		var discount;
		if( $('input[name=dgnldct]').val()!="")
		{
			discount=$('input[name=dgnldct]').val();
		}
		else if($('input[name=dgnrdct]').val()!="")
	    {
			discount=$('input[name=dgnrdct]').val();
		}
		var queryString;
		queryString = {
			Grid:grid,
			Lpid : lpid,
			Rpid: rpid,
			Folno:folno,
			Discount:discount
		 };
		return queryString;
	};
	function startRequest(e) {
		$.getJSON("/huiermis/order/OrderAction.do?method=queryDis",
				createQueryString(e), function(data) {
			   var flag=true;
			   var str="";
			  
			   if(data.length==2)
			   {
			   		/* $(data).each(function(i){
				   
		          	alert(data[i].tdspvo); 
			   		}); */
			   		
			   		if(data[0].tdspvo>parseFloat($("input[name=dgnldct]").val()))
			   		{
			   			flag=false;
			   			str="左耳定制机";
			   		}
			   		if(data[1].tdspvo>parseFloat($("input[name=dgnrdct]").val()))
			   		{
			   			flag=false;
			   			str+="、右耳定制机";
			   		}
			   }
			   else if(data.length==1&&data[0].lr=='0')
			  {
				   if(data[0].tdspvo>parseFloat($("input[name=dgnldct]").val()))
				   {
					   flag=false;
					   str="左耳定制机";
				    }
			   }
			   else if(data.length==1&&data[0].lr=='1')
			   {
					   if(data[0].tdspvo>parseFloat($("input[name=dgnrdct]").val()))
					   {
						   flag=false;
						   str="右耳定制机";
					    }
			  }
			   else if(data.length==1 && data[0].lr=='6')
			  {
				   alert("输入的扣率低于总部已审核的扣率，无法提交！");
				  /*  if(confirm("输入的扣率低于总部已审核的扣率，无法提交！"))
				   {
					   var minDis=data[0].tdspvo;
					   commit(document.forms[0],'y',minDis);
				   }
				   else
				   {
						  save(document.forms[0]);
				    } */
				   return;
			  }
			   else if(data.length==1 && data[0].lr=='7')
				  {
					   alert("该特殊扣率已被总部回退，比其小的扣率也不能提交！");
					   return;
				  }
			   
			  if(flag==false)
			  {
				  if(confirm(str+"输入的扣率已低于最低扣率，是否向总部申请?","是","否"))
				  {  
					  var minDis=new Array();
				      if(data.length==2)
					  {
					  	if(str.indexOf('左')!=-1&&str.indexOf('右')==-1)
					  	{
						  	minDis[0]=data[0].tdspvo;
					  	}
					  	else if(str.indexOf('左')==-1&&str.indexOf('右')!=-1)
						{
							minDis[0]=data[1].tdspvo;
						}
					  	else if(str.indexOf('左')!=-1&&str.indexOf('右')!=-1)
					  	{
					  		minDis[0]=data[0].tdspvo;
					  		minDis[1]=data[1].tdspvo;
					  	}
					  }
				      else if(data.length==1)
				      {
				    	  minDis[0]=data[0].tdspvo;
				      }
					  
					  commit(document.forms[0],'y',minDis,'a');
				  }
				  //else
				  //{
				//	  save(document.forms[0]);
				  //}
			  }
			  
			  else
			  {
				  commit(document.forms[0],'n');
			  }  
				 
		 });
	};
		
	function save(obj){
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("确实要修改并保存订单吗？")) {
			obj.submit();
		}
	};
	
	function commit(obj,isExa,md,aga){
		var cnm = $('input[name=ictnm]').val();
		var cid = $('input[name=ictid]').val();   //客户编号
		var lpid = $('input[name=dgnlid]').val();  //定制机编号
		var rpid = $('input[name=dgnrid]').val();
		
		if (!checkValue(obj)) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("无定制机信息，无法生成订单");
			return false;
		} else if (rpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=modify&tp=c&type=h&isExa='+isExa+'&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=l&pid='
					+ lpid
					+ '&cnm=' + cnm+'&md='+md
					+ '&aga=' + aga;
		} else if (lpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=modify&tp=c&type=h&isExa='+isExa+'&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=r&pid='
					+ rpid
					+ '&cnm=' + cnm+'&md='+md
					+ '&aga=' + aga;
		} else {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=modify&tp=c&type=h&isExa='+isExa+'&"/>'
					+ 'num=2'
					+ '&cid='
					+ cid
					+ '&lpid='
					+ lpid
					+ '&rpid=' + rpid
					+ '&cnm=' + cnm+'&md='+md
					+ '&aga=' + aga;
		}
		if (confirm("确实要录入订单并提交吗？")) {
			obj.submit();
		}
	};
	
	function computeL(obj) {
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgnldct=$('input[name=dgnldct]').val();
		$('input[name=dgncldprc]').val((parseFloat(dgnldprc)*parseFloat(dgnldct)).toFixed(2));
	};
	
	function computeR(obj) {
		 var dgnrdprc = $('input[name=dgnrdprc]').val();
		 var dgnrdct=$('input[name=dgnrdct]').val();
		 $('input[name=dgncrdprc]').val((parseFloat(dgnrdprc)*parseFloat(dgnrdct)).toFixed(2));
	};

	function computeLdiscount()
	{
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgncldprc = $('input[name=dgncldprc]').val();
		$('input[name=dgnldct]').val((parseFloat(dgncldprc)/parseFloat(dgnldprc)).toFixed(2));
	};
	
	function computeRdiscount()
	{
		var dgnrdprc = $('input[name=dgnrdprc]').val();
		var dgncrdprc = $('input[name=dgncrdprc]').val();
		$('input[name=dgnrdct]').val((parseFloat(dgncrdprc)/parseFloat(dgnrdprc)).toFixed(2));
	};
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="定制订单修改" />
	<html:form action="/CustomOrderAction.do?method=modify&tp=b" method="POST">
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
				
				<lemis:texteditor property="gctnm" label="送制单位" disable="true" required="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="经办日期"></lemis:texteditor>
			   <lemis:codelisteditor type="folurgent" label="是否加急订单" required="true"></lemis:codelisteditor>
				</tr>
				<tr>
			  		<%-- <lemis:texteditor property="foldelay" label="延长保修的年限" disable="false" /> --%>
			   		<lemis:texteditor property="folnt" label="备注" required="false" disable="false" />
					<lemis:texteditor property="dgndoc" label="验配师姓名" required="false"
						disable="false" />
				</tr>
			</table>
			 <lemis:tabletitle title="左耳" />
			<table class="tableinput">

            <lemis:editorlayout />
            	<tr>
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
					<lemis:texteditor property="dgncldprc" label="现价" required="false" disable="false" onblur="computeLdiscount()" />
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayleft" label="延长保修的年限（月）" required="false" /><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeleft"  label="续保费"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isleft" label="保修是否赠送" required="false" />
			  	 </tr>
			</table>
			
	 	 <lemis:tabletitle title="右耳" />
		<table class="tableinput">

			<lemis:editorlayout />
			<tr>
				<lemis:texteditor property="dgnrnm" label="定制机型号" required="false"
					disable="false" />
				<html:hidden property="dgnrid" />
				<%-- <lemis:codelisteditor type="fdtsheltpr" label="外壳类型" dataMeta="r" /> --%>
				<lemis:formateditor required="false" property="folrdps" mask="money"
					label="右耳定金" disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="dgnrdprc" label="原价" required="false"
					disable="false" />
				<lemis:texteditor property="dgnrdct" label="扣率" required="false"
					disable="false" onblur="computeR()" />
				<lemis:texteditor property="dgncrdprc" label="现价" required="false"
					disable="false" onblur="computeRdiscount()" />
			</tr>
			<tr>
				<lemis:codelisteditor  type="delayright" label="延长保修的年限（月）" required="false" /><!-- 该处只能输入整数 -->
			  	<lemis:texteditor property="feeright"  label="续保费"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="保修是否赠送" required="false" />
			  	 </tr>
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
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>