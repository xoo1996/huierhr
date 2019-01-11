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
	header.add(new Formatter("folctnm", "团体客户"));
	header.add(new Formatter("fdtcltnm", "个人客户"));
	header.add(new Formatter("tmksid", "机身编号"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("folsdt", "发货日期","", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("foloprnm", "录单员"));
	header.add(new Formatter("fdtodt", "退机日期","", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("fdtnt", "备注"));

	//List<Button> buttons=new ArrayList<Button>();
	//buttons.add(new Button("tui","退 机","ord020001","tuiji(document.all.tableform)"));
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("退  机","tuiji(document.all.tableform)");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folno","订单号");
	hidden.put("fdtqnt","数量");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folctid", "客户代码"));
	editors.add(new Editor("text", "cltnm", "个人客户"));
	editors.add(new Editor("text", "sid", "机身编号"));
	editors.add(new Editor("text", "pdtid", "商品代码"));
	editors.add(new Editor("date","start","退机从"));
	editors.add(new Editor("date","end","到"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	   
	
	
	<%--  var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
    $("input[name=pdtid]").autocomplete(products,{
		max : 10,
		matchContains : true,
		formatItem: function(data, i, max) {
			return data[0].substring(0,data[0].indexOf(":"));
		}
	});
	$("input[name=pdtid]").result(function(event, data, formatted) {
		if (data){
			var pid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=pdtid]").val(pid);
		}
	}); --%>
	 //$("input[name=pdtid]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
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
									var pid=data.proid;
									$("input[name=pdtid]").val(pid);
	
								}
							});
						}
				});
		//});
	});
</script>
<script language="javascript">
  		function tuiji(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/OrderAction.do?method=recoil&"/>'+getAlldata(obj);	
            if(confirm("确定要退机吗？"))
			{
    			obj.submit();
			}
  		};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="退机查询" />
	<lemis:query action="/OrderAction.do?method=requery" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="退机订单信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


