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
	header.add(new Formatter("tdefolno", "订单号"));
	header.add(new Formatter("tdepdtid", "商品代码"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("gctnm", "所属团体")); 
	header.add(new Formatter("ictnm", "个人客户"));
	header.add(new Formatter("tdedis", "现扣率"));
	header.add(new Formatter("tdedmindis", "原最小扣率"));
	header.add(new Formatter("tdeisexa", "是否已审核"));
	header.add(new Formatter("tdedt", "申请日期"));
	
/* 	List<Button> buttons=new ArrayList<Button>();
	buttons.add(new Button("back","返回","ord020003","history.back()")); */
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("审核","examine(document.all.tableform)");
	buttons.put("回退","Disback(document.all.tableform)");
	/* buttons.put("返回","history.back()"); */

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tdefolno", "订单号"); 
	hidden.put("tdepdtid", "商品代码"); 
	hidden.put("tdeisexa","是否已审核");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tdefolno", "订单号"));
	editors.add(new Editor("text", "tdepdtid", "商品代码"));
	editors.add(new Editor("text","gctnm","所属团体"));
	editors.add(new Editor("text", "tdeisexa", "是否已审核"));
	editors.add(new Editor("date","start","申请日期从"));
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
	function examine(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确实要审核通过该特殊申请吗？")) {
		obj.action = '<html:rewrite page="/OrderAction.do?method=examineDis"/>';			
		obj.submit();
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
	};
	
	function Disback(obj) {
		var t = editObj("chk");
		if(!t){
			return	t;
		}
		if (confirm("确实要回退该扣率申请吗？")) {
		obj.action='<html:rewrite page="/OrderAction.do?method=Disback&"/>'+getAlldata(obj);
		obj.submit();
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
	};
</script>
<script language="javascript">
//查看订单明细
	function print(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/OrderAction.do?method=orderDetail&tp2=s&"/>'+getAlldata(obj);	
	obj.submit();
	};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="订单查询" />
	<lemis:query action="/OrderAction.do?method=queryDisExa" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="订单信息"
		hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


