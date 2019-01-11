<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
   String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
	String total = (String)request.getSession().getAttribute("total");
    String deliveryTotal = (String)request.getSession().getAttribute("deliveryTotal");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("folno", "订单号"));
	header.add(new Formatter("gctnm", "团体客户"));
	header.add(new Formatter("fdtcltnm", "个人客户"));
	header.add(new   Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtsqnt", "发货"));
	header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folsdt", "发货日期", "color:#000000;", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("folway", "发货方式")); 
	header.add(new Formatter("folsno", "快件号"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("folsoprnm", "发货员"));
	header.add(new Formatter("fdtnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("发货","delivery(document.all.tableform)");
	//buttons.put("修改订单","modify(document.all.tableform)");
	buttons.put("维修记录查询","repaircheck(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
/* 	hidden.put("repfolid", "订单号");
	hidden.put("repidentity", "维修流水号");
	hidden.put("repgctnm", "送修单位名称");
	hidden.put("repgctid", "送修单位代码");
	hidden.put("repcltnm", "用户姓名");
	hidden.put("reppid", "助听器代码");
	hidden.put("repid", "机身编号");
	hidden.put("repcpy", "维修厂商");
	hidden.put("reppnm", "助听器名称");
	hidden.put("fdtpid", "商品代码"); */
	hidden.put("pdtid", "商品代码");
	hidden.put("pdtnm","商品名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folctid", "客户代码"));
	editors.add(new Editor("date", "start", "发货时间从"));
	editors.add(new Editor("date", "end", "到"));
	editors.add(new Editor("text", "pdtid", "商品代码"));
	editors.add(new Editor("text", "foltype", "订单类型"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	

%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript"><!--
/* 	//发货
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		alert(getAlldata(obj));
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	};  */
	
	 //维修记录查询
	function repaircheck(obj) {
 		var t = editObj("chk");
		if (!t) {
			return t;
		} 
		//obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/query.jsp"/>';
		obj.action = '<html:rewrite page="/OrderAction.do?method=queryHistory&"/>' + getAlldata(obj);
		obj.submit(); 
		/* window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/order/OrderAction.do?method=queryHistory&reppid="
		+ $("#pdtid").val();  */
	}; 
/* 	
	//维修记录查询
	function repaircheck(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		} 
		//alert(request.getParameter("folno"));
		alert(getAlldata(obj));
		obj.action = '<html:rewrite page="/OrderAction.do?method=repairDetail&"/>' + getAlldata(obj);
		
		obj.submit();
	}; */
	
	$(document).ready(function(e){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");

		
		$("input[name=folctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=folctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=folctid]").val(gid);
				$(this).parent().next().find("input").val(gnm);
			}
		});
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=all",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=pdtid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=pdtid]").result(function(event, data, formatted) {
							if (data){
								var pid = data.proid;
								$("input[name=pdtid]").val(pid)
                        }
						});
					}
			});
		
	});
--></script>

<lemis:base />
<lemis:body>
	<lemis:title title="已发货订单明细" />
	<lemis:query action="/OrderAction.do?method=queryAllDetail&require=check" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="订单明细信息"
		hiddenMeta="hidden"  mode="radio" />
	数量合计：<input type="text" disabled="disabled" value="<%=total%>">
	发货合计：<input type="text" disabled="disabled" value="<%=deliveryTotal%>">
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


