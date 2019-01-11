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

//    List<Button> buttons=new ArrayList<Button>();
//    buttons.add(new Button("bc","����","ord120101","save(document.forms[0])"));
//    buttons.add(new Button("tj","�ύ","ord120102",""));
//    /*buttons.add(new Button("jcdz","�ĳ�����","clt020202","creatOrder1(document.forms[0])")); */
//    buttons.add(new Button("back","�޸��û���Ϣ","ord120103","history.back()"));
    
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    //buttons.put("�� ��","save(document.forms[0])"); 
    buttons.put("�� ��","startRequest()"); 
    buttons.put("�� ��","closeWindow(\"\")");
    //buttons.put("�޸��û���Ϣ","history.back()"); 

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtprc","����",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","����"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","��Ʒ����","true"));
	batchInput.add(new Editor("text","pdtnm","��Ʒ����","true"));
	batchInput.add(new Editor("money","fdtprc","����","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","����","true"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "�û����");
	pageContext.setAttribute("hidden",hidden);
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
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
 
 $(document).ready(function(){
		<%-- var products = "<%=session.getServletContext().getAttribute("cusProductList")%>".replace("{","").replace("}","").split(", ");  --%>
		
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		$("input[name=dgnldct]").val("1.0");
		$("input[name=dgnrdct]").val("1.0");
		if(grCli=="1501000000")
		{
			var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
			$("input[name=folctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0];
				}
			});
			
			$("input[name=folctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=folctnm]").val(gnm);
					$(this).parent().next().find("input").val(gid);
				}
			});
		}
		else
		{
			$("input[name=folctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=folctnm]").attr("readonly","true");
			$("input:hidden[name=folctid]").val("<%=dto.getBsc011()%>");
	 	}
		
		$("input[name=dgnldprc]").attr("readonly","true"); 
		$("input[name=dgnrdprc]").attr("readonly","true"); 

	/*    $("input[name=dgnlnm]").autocomplete(products,{
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
		});  */
		
		 //$("input[name=dgnlnm]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 data:"proType=cus",
				 dataType:'json',
				 error:function(){
				   alert('��ȡ���ݴ���');
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
				   alert('��ȡ���ݴ���');
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
		
		
		/* $("input[name=dgnrnm]").autocomplete(products,{
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
		}); */
		
		/*$("input[name=tj]").bind("click",function(e){
			startRequest(e);
			
		});//��ť��ʾ����*/
		//�������������
		$('#delayleft').change(function(){
			var price = 25;
			var delay = $('#delayleft').val();
			$('input[name=feeleft]').val(price*delay);

		});
		$("input[name=feeleft]").attr("readonly","true"); 
		//�Ҷ�����������
		$('#delayright').change(function(){
			var price = 25;
			var delay = $('#delayright').val();
			$('input[name=feeright]').val(price*delay);

		});
		$("input[name=feeright]").attr("readonly","true"); 
				
	});
	
 	function createQueryString(e) {
		var lpid = $('input:hidden[name=dgnlid]').val();  //���ƻ����
		var rpid = $('input:hidden[name=dgnrid]').val();
		var grid=$('input:hidden[name=folctid]').val(); 
		var queryString;
		queryString = {
			Grid:grid,
			Lpid : lpid,
			Rpid: rpid
		 };
		return queryString;
	};
	function startRequest(e) {
		$.getJSON("/huiermis/order/OrderAction.do?method=queryDis",
				createQueryString(e), function(data) {
			   var flag;
			   var str="";
			   
			   if(data.length==2)
			   {
			   		/* $(data).each(function(i){
				   
		          	alert(data[i].tdspvo); 
			   		}); */
			   		if(data[0].tdspvo>parseFloat($("input[name=dgnldct]").val()))
			   		{
			   			flag=false;
			   			str="������ƻ�";
			   		}
			   		if(data[1].tdspvo>parseFloat($("input[name=dgnrdct]").val()))
			   		{
			   			flag=false;
			   			str+="���Ҷ����ƻ�";
			   		}
			   }
			   else if(data.length==1&&data[0].lr==0)
			  {
				   if(data[0].tdspvo>parseFloat($("input[name=dgnldct]").val()))
				   {
					   flag=false;
					   str="������ƻ�";
				    }
			   }
			   else if(data.length==1&&data[0].lr=='1')
			   {
					   if(data[0].tdspvo>parseFloat($("input[name=dgnrdct]").val()))
					   {
						   flag=false;
						   str="�Ҷ����ƻ�";
					    }
			  }
			   
			  if(flag==false)
			  {
				  if(confirm(str+"����Ŀ����ѵ�����Ϳ��ʣ��Ƿ����ܲ�����?","��","��"))
				  {  
					  var minDis=new Array();
					  var cusLr=new Array();
				      if(data.length==2)
					  {
					  	if(str.indexOf('��')!=-1&&str.indexOf('��')==-1)
					  	{
						  	minDis[0]=data[0].tdspvo;
						  	cusLr[0]='0';
					  	}
					  	else if(str.indexOf('��')==-1&&str.indexOf('��')!=-1)
						{
							minDis[0]=data[1].tdspvo;
							cusLr[0]='1';
						}
					  	else if(str.indexOf('��')!=-1&&str.indexOf('��')!=-1)
					  	{
					  		minDis[0]=data[0].tdspvo;
					  		minDis[1]=data[1].tdspvo;
					  		cusLr[0]='0';
					  		cusLr[1]='1';
					  	}
					  }
				      else if(data.length==1)
				      {
				    	  minDis[0]=data[0].tdspvo;
				      }
					  
					  commit(document.forms[0],'y',minDis,cusLr);
				  }
				  else
				  {
					  save(document.forms[0]);
				  }
			  }
			  
			  else
			  {  var lname = $("input[name=dgnlnm]").val();
			  	var rname = $("input[name=dgnrnm]").val();
			  	var lkoulv = $("input[name=dgnldct]").val();
			 	 var rkoulv = $("input[name=dgnrdct]").val();
			 	var lmoney = $("input[name=dgncldprc]").val();
			 	var rmoney = $("input[name=dgncrdprc]").val();
			 	
			 	 var str = "";
			  	if (lname!=null&&lname!=""){
					  str =str+"����ͺţ�"+lname;
					  str =str+"�������"+lmoney;
					  str =str+"��������ʣ�"+lkoulv+"\n";
				  }
				  if (rname!=null&&rname!=""){
					  str =str + "�Ҷ��ͺţ�"+rname;
					  str =str+"���Ҷ���"+rmoney;
					  str =str +"���Ҷ����ʣ�"+rkoulv+"\n";
				  }
				  if (confirm(str+"ȷʵҪ¼�붩�����ύ��")) {
					  commit(document.forms[0],'n');
					} 
			  }  
				 
		 });
	};
	
	function commit(obj,isExa,md,lr) {
		var cnm = $('input[name=ictnm]').val();
		var cid = $('input[name=ictid]').val();   //�ͻ����
		/* var gid = $('input[name=ictgctid]').val();  */
		var lpid = $('input[name=dgnlid]').val();  //���ƻ����
		var rpid = $('input[name=dgnrid]').val();
		if (!checkValue(document.forms[0])) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("�޶��ƻ���Ϣ���޷����ɶ���");
			return false;
		} else if (rpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=c&isExa='+isExa+'&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=l&pid='
					+ lpid
					+ '&cnm=' + cnm+'&md='+md+"&lr="+lr;
		} else if (lpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=c&isExa='+isExa+'&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=r&pid='
					+ rpid
					+ '&cnm=' + cnm+'&md='+md+"&lr="+lr;
		} else {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=c&isExa='+isExa+'&"/>'
					+ 'num=2'
					+ '&cid='
					+ cid
					+ '&lpid='
					+ lpid
					+ '&rpid=' + rpid
					+ '&cnm=' + cnm+'&md='+md+"&lr="+lr;
		}

			obj.submit();
	}
	
	
	
	function save(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var cnm = $('input[name=ictnm]').val();
		var cid = $('input[name=ictid]').val();   //�ͻ����
		/* var gid = $('input[name=ictgctid]').val();  */
		var lpid = $('input[name=dgnlid]').val();  //���ƻ����
		var rpid = $('input[name=dgnrid]').val();
/* 		if($('input[name=pdtsheltp]').attr("dataMeta")=="r")
		{
			 var rsheltp=$('input[name=fdtsheltpl]').val();
		}
		else
		{
			var lsheltp=$('input[name=fdtsheltpr]').val();
		} */
		
	/* 	$("pdtsheltp").each(function(i){
			   this.src = "test" + i + ".jpg";
			   sheltp[i]=$('input[name=pdtsheltp][i]').val();
			 }); */
		
		if (!checkValue(obj)) {
			return false;
		}
		if (lpid == '' && rpid == '') {
			alert("�޶��ƻ���Ϣ���޷����ɶ���");
			return false;
		} else if (rpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=b&type=h&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=l&pid='
					+ lpid
					+ '&cnm=' + cnm;
		} else if (lpid == '') {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=b&type=h&"/>'
					+ 'num=1'
					+ '&cid='
					+ cid
					+ '&plr=r&pid='
					+ rpid
					+ '&cnm=' + cnm;
		} else {
			obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=b&type=h&"/>'
					+ 'num=2'
					+ '&cid='
					+ cid
					+ '&lpid='
					+ lpid
					+ '&rpid=' + rpid
					+ '&cnm=' + cnm;
		}
		if (confirm("ȷʵҪ¼�붩����")) {
			obj.submit();
		}
/* 		obj.action = '<html:rewrite href="/huiermis/order/CustomOrderAction.do?method=add&tp=c&type=h&"/>'
		obj.submit(); */
	}
	
	function computeL(obj) {
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgnldct=$('input[name=dgnldct]').val();
		$('input[name=dgncldprc]').val((parseFloat(dgnldprc)*parseFloat(dgnldct)).toFixed(2));
	}
	
	function computeR(obj) {
		 var dgnrdprc = $('input[name=dgnrdprc]').val();
		 var dgnrdct=$('input[name=dgnrdct]').val();
		/*  alert(dgnrdprc); */
		 $('input[name=dgncrdprc]').val((parseFloat(dgnrdprc)*parseFloat(dgnrdct)).toFixed(2));
		/*  alert(parseFloat(dgnrdct)); */
	}
	
	function computeLdiscount()
	{
		var dgnldprc = $('input[name=dgnldprc]').val();
		var dgncldprc = $('input[name=dgncldprc]').val();
		$('input[name=dgnldct]').val((parseFloat(dgncldprc)/parseFloat(dgnldprc)).toFixed(2));
	}
	
	function computeRdiscount()
	{
		var dgnrdprc = $('input[name=dgnrdprc]').val();
		var dgncrdprc = $('input[name=dgncrdprc]').val();
		$('input[name=dgnrdct]').val((parseFloat(dgncrdprc)/parseFloat(dgnrdprc)).toFixed(2));
	}
	
	
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="���ƶ���¼��" />
	<html:form action="/CustomOrderAction.do" method="POST">
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<html:hidden property="ictgctid"/>
				<lemis:texteditor property="ictnm" label="�û�����" disable="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true" />
				<%-- <lemis:texteditor property="ictbdt" label="��������" disable="true" /> --%>
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="��������" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="��ϵ�绰" disable="true" />
			  <lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="true" />
			<%--   <lemis:codelisteditor type="ictphis" label="ʹ�ù�����������"></lemis:codelisteditor> --%>
			 <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="��Դ" disable="true" />
			  <lemis:texteditor property="ictnt" label="��ע" disable="true" />
			  <lemis:codelisteditor type="ictfrom" label="��Դ" required="true" />
			 <%--  <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" /> --%>
			</tr>
	</table>
	
		<lemis:tabletitle title="����ǵ�" />
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
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
					 />
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
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
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
						 style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
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
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
						/>
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
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
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
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
    
 
			<lemis:tabletitle title="����������Ϣ" />
			<table class="tableinput">
           <lemis:editorlayout />

           <tr>
				<!-- <td>���Ƶ�λ</td> -->
				<%-- <td><lemis:operorg /></td> --%>
				<lemis:texteditor property="folctnm" label="���Ƶ�λ" disable="false" required="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<td>��������</td>
				<td><lemis:operdate /></td>
			   <lemis:codelisteditor type="folurgent" label="�Ƿ�Ӽ�������֧��100Ԫ/̨��" required="true"/>
				</tr>
				
			<%--   <lemis:texteditor property="foldelay" label="�ӳ����޵����ޣ��£�" disable="false" /> --%>
			  
			  	 <tr>
			  <%-- <lemis:texteditor property="foldps" label="����" disable="false" ></lemis:texteditor> --%>
			  <%-- <lemis:formateditor required="false" property="foldps" mask="money" label="����" disable="false" /> --%>
			 	<lemis:texteditor property="folnt" label="��ע" required="false" disable="false" maxlength="100"/>
				<lemis:texteditor property="dgndoc" label="����ʦ����" size="" required="true" maxlength="6"
						disable="false" />
			</tr>
			</table>
			
			 <lemis:tabletitle title="���" />
			<table class="tableinput">

            <lemis:editorlayout />
            <tr>
					<lemis:texteditor property="dgnlnm" label="���ƻ��ͺ�" required="false"
						disable="false" />
					<html:hidden property="dgnlid" />
					<%-- <lemis:codelisteditor type="fdtsheltpl" label="�������"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folldps" mask="money" label="�������" disable="false" />
					
				</tr>
				
				<tr>
					<lemis:texteditor property="dgnldprc" label="ԭ��" required="false"
						disable="false" />
					 <lemis:texteditor property="dgnldct" label="����" required="false"
						disable="false" onkeyup="computeL()"/> 
					<%-- <lemis:formateditor required="false" property="dgnldct" mask="n.nn" label="����" disable="false" onblur="computeL()"/>  --%>
					<lemis:texteditor property="dgncldprc" label="�ּ�" required="false" disable="false" onkeyup="computeLdiscount()"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayleft" label="�ӳ����޵����ޣ��£�" required="false" /><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeleft"  label="������"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isleft" label="�����Ƿ�����" required="false" />
			  	 </tr>
			</table>   
			
	 	 <lemis:tabletitle title="�Ҷ�" />
			<table class="tableinput">

            <lemis:editorlayout />
             <tr>
					<lemis:texteditor property="dgnrnm" label="���ƻ��ͺ�" required="false"
						disable="false" />
					<html:hidden property="dgnrid" />
					<%-- <lemis:codelisteditor type="fdtsheltpr" label="�������" dataMeta="r"></lemis:codelisteditor> --%>
					<lemis:formateditor required="false" property="folrdps" mask="money" label="�Ҷ�����" disable="false" />
					
				</tr>
				<tr>
					<lemis:texteditor property="dgnrdprc" label="ԭ��" required="false"
						disable="false" />
						 <lemis:texteditor property="dgnrdct" label="����" required="false"
						disable="false" onkeyup="computeR()"/> 
						<%-- <lemis:formateditor required="false" property="dgnrdct" mask="n.nn" label="����" disable="false" onblur="computeR()"/> --%>
						<lemis:texteditor property="dgncrdprc" label="�ּ�" required="false" disable="false" onkeyup="computeRdiscount()"/>
				</tr>
				<tr>
				<lemis:codelisteditor  type="delayright" label="�ӳ����޵����ޣ��£�" required="false" /><!-- �ô�ֻ���������� -->
			  	<lemis:texteditor property="feeright"  label="������"   disable="false" required="false" maxlength="100"/>
			  	 <lemis:codelisteditor  type="isright" label="�����Ƿ�����" required="false" />
			  	 </tr>
			</table>
			
			<lemis:tabletitle title="����Ҫ��"/>
		    <table class="tableinput">

            <lemis:editorlayout />
             <tr>
						<lemis:codelisteditor type="dgnpulwire" label="����"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnairvent" label="ͨ����"></lemis:codelisteditor>
						<lemis:codelisteditor type="dgnvoswitch" label="��������"></lemis:codelisteditor>

            </tr>
				<tr>       
				        <lemis:texteditor property="dgnsdmd" label="����Ҫ��" required="false"
						disable="false" />

            </tr>
				
			</table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>