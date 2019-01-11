<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("fdtfno", "订单号"));
	header.add(new Formatter("gctnm", "所属团体"));
	//header.add(new Formatter("cltnm", "个人客户")); 
	header.add(new Formatter("fdtcltnm", "个人客户")); 
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtsqnt", "发货"));
	header.add(new Formatter("fdtdprc", "价格", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folsta", "订单状态"));
	header.add(new Formatter("foltype", "订单类型"));
	//header.add(new Formatter("folexaopr","订单审核员"));
	header.add(new Formatter("foloprnm", "订单审核员"));
	header.add(new Formatter("fdtnt", "备注"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("审核","print(document.all.tableform)");
	buttons.put("历史维修记录","weixiu(document.all.tableform)");
	buttons.put("打印定制订单","printCusOrder(document.all.tableform)"); 
	buttons.put("打印条形码","printBarcode(document.all.tableform)");
	/* buttons.put("杰闻打印","jiewen_print()"); */
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folno","订单号");
	hidden.put("foltype","订单类型");
	hidden.put("folindctid","个人客户编号");
	hidden.put("folsta","订单状态");
	hidden.put("foldt","订单状态");
	hidden.put("gctnm","团体客户");
	hidden.put("ictnm","客户名称");
	hidden.put("ictid","客户编号");
	hidden.put("fdtpid","商品编号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	editors.add(new Editor("text", "gctnm", "所属团体"));
	editors.add(new Editor("text", "foltype", "订单类型"));
	editors.add(new Editor("text", "fdtcltnm", "个人客户"));
	editors.add(new Editor("date","start","订货日期从"));   
	editors.add(new Editor("date","end","到"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
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
				$(this).parent().next().find("input").val(gid);
			}
		});
		
		 var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	     
			if(shops.length==1)
			{
				$("input[name=gctnm]").val(shops[0].substring(shops[0].indexOf("=")+1,shops[0].length));
				$("input[name=gctnm]").attr("readonly","true");
			}
			
			$("input[name=gctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0].substring(0);
				}
			});
			
			$("input[name=gctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=gctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
					$("input[@type=hidden][name=folctid]").val(); */
					
				}
			});
	});
	
	function printCusOrder(obj){
		var t = editObj("chk");
			if (!t) {
				return t;
			}
			else {
				obj.action = '<html:rewrite page="/OrderAction.do?method=cusOrderReport&tp=e&"/>'+getAlldata(obj);		
				obj.submit();
			}
	};
	
</script>
<script language="javascript">
		//查看订单明细
  		function print(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/OrderAction.do?method=orderDetail&tp2=e&"/>'+getAlldata(obj);	
			obj.submit();
  		};
  		
  		function printBarcode(obj) {
  			var t = editObj("chk");
  			if (!t) {
  				return t;
  			}
  			else {
  				obj.action = '<html:rewrite page="/OrderAction.do?method=barcode&"/>' + getAlldata(obj);
  				obj.submit();
  			} 
  		};
  		
  		function weixiu(obj) {
  			var t = editObj("chk");
  			if (!t) {
  				return t;
  			}
  			obj.action = '<html:rewrite page="/OrderAction.do?method=queryRep&"/>' + getAlldata(obj);
  			obj.submit();
  		};
  		
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="审核" />
	<lemis:query action="/OrderAction.do?method=query&tp=e" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="订单信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


