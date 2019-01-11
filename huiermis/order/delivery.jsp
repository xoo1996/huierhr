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
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("确 定", "chkStoAmount(document.forms[0])");
	buttons.put("返 回", "window.history.back();");

	List<Formatter> header = new ArrayList<Formatter>();
	//header.add(new Formatter("fdtcltnm", "用户姓名"));
	header.add(new Formatter("fdtpid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "原价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtdprc", "售价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtsqnt", "发货数量"));
	//header.add(new Formatter("fdtsta", "状态"));
	header.add(new Formatter("fdtnt", "备注"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folctid","客户代码");
	hidden.put("fdtfno", "订单号码");
	hidden.put("fdtpid", "商品代码");
	hidden.put("foltype","订单类型");
	hidden.put("pdtnm","商品名称");
	

	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtsqnt","发货数量","true"));
	//batchInput.add(new Editor("text","fdtsta","状态","true"));
	batchInput.add(new Editor("text","fdtnt","备注"));
	
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("hidden", hidden);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
	});
	
	function chkStoAmount(obj)
	{
		if (!checkValue(obj)) {
			return false;
		}
		if (!delObj("chk")) {
			return false;
		}
		if (!preCheckForBatch()) {
			return false;
		}
	    $.getJSON("<%=basePath%>order/OrderDetailAction.do?method=checkStoAmount&" +
			 getAlldata(document.all.tableform),
			 function(data) {
				 var flag=true;
				 var flag1=true;
				 var flag2=true;
				 var str="";
				 if(null!=data && data.tdspvo!='')
			     {
					 $.each(data,function(i,minsto)
					 {
						 if(null!=data[i].minstoi&&null!=data[i].minsto&&data[i].minsto!='0')
						 {
							 str+="第"+data[i].minstoi+"行商品库存量不足，现库存量为"+data[i].minsto+"\n";
							 flag=false;
						 }
						 else if(null!=data[i].minstoi && data[i].minsto=='0')
						{
							 str+="第"+data[i].minstoi+"行商品没有库存,现库存量为0\n";
							 flag1=false;
						}
						else if(null!=data[i].minstoi && null==data[i].minsto)
							{
								 str+="第"+data[i].minstoi+"行商品没有库存,先添加库存才能发货\n";
								 flag2=true;//发货不受库存数量限制 2015.2.4
							}
						 
					 });
					
			     }
				 if(flag==false || flag1==false)
				 {
				 	if(confirm(str+"确实要批量发货吗?","是","否"))
				 	{  
				 		delivery(obj);
				 	}
				 }
				 else if(flag2==false)
				 {
					 alert(str);
					 return;
				 }
				 else if(flag&&flag1&&flag2)
				 {
					 delivery(obj);
				 }  
			 
		});
	}
	
	
	
	function delivery(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (!delObj("chk")) {
			return false;
		}
		if (!preCheckForBatch()) {
			return false;
		}
		/*
		 if (confirm("确认发货吗？")) { 
			//window.location.href = "/" + lemis.WEB_APP_NAME
					//+ "/order/OrderDetailAction.do?method=batchDelivery&way="
					//+ $("#folway").val() + "&sno=" + $("input[name=folsno]").val() + "&folctid=" + $("input:hidden[name=folctid]").val()
					//+ "&foltype=" + $("input:hidden[name=foltype]").val() + "&"
					//+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.action='<html:rewrite page="/OrderDetailAction.do?method=batchDelivery&way="/>'
				+ $("#folway").val() + "&sno=" + $("input[name=folsno]").val() + "&folctid=" + $("input:hidden[name=folctid]").val()
				+ "&foltype=" + $("input:hidden[name=foltype]").val() + "&"
				+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
			obj.submit();
		 } 
		*/
		
		$("input[value=确 定]").attr("disabled","true");
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>order/OrderDetailAction.do?method=before_batchDelivery&require=kefu&type=huier&way=' + $("#folway").val() + '&sno='
					+ $("input[name=folsno]").val() + '&des=' + $("input[name=foldes]").val() 
					+ '&' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F'),
			 //data:"proType=nom",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
				 if(data == null || data == ''){
					 if (confirm("确认发货吗？")) { 
							window.location.href = "/" + lemis.WEB_APP_NAME
									+ "/order/OrderDetailAction.do?method=batchDelivery&way="
									+ $("#folway").val() + "&sno=" + $("input[name=folsno]").val() + "&folctid=" + $("input:hidden[name=folctid]").val()
									+ "&foltype=" + $("input:hidden[name=foltype]").val() + "&" + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
						 } 
					 else{
						 $("input[value=确 定]").attr("disabled","");
					 }
				 }else{
					 alert("订单已发货不能重复发货");
			     }
			}
		});
		
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="出货" />
	<lemis:tabletitle title="订单基本信息" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do?method=modifyOrder" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="订单号" disable="true" />
				<%-- <lemis:texteditor property="folctid" label="客户代码" disable="true" /> --%>
				<%-- <html:hidden property="folctid"/>  --%>
				<lemis:texteditor property="folctnm" label="客户名称" disable="true" />
				<lemis:formateditor mask="date" property="foldt" label="订货日期" 
					required="false" disabled="true" format="true"/>
			</tr>
			<tr>
				<td>发货员</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				<td>发货日期</td>
				<td><lemis:operdate /></td>
			</tr>
			<tr>

				<lemis:codelisteditor type="folway" isSelect="true" label="发货方式"
					redisplay="true" required="true" dataMeta="folwayList" />
				<lemis:texteditor property="folsno" label="快件号" disable="false"
					required="false" />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="订单明细" action="/OrderDetailAction.do"
		headerMeta="header" hiddenMeta="hidden" mode="checkbox"
		batchInputMeta="batchInput" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


