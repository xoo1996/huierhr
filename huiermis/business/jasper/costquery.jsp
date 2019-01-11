<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Object> buttons = new ArrayList<Object>();
	buttons.add(new Button("add","增加客户费用","bus090701","add(document.all.tableform)"));
	buttons.add(new Button("edit","修改客户费用","bus090702","edit(document.all.tableform)"));
	buttons.add(new Button("print","打印客户费用","bus090703","print()"));
	buttons.add(new Button("close", "关 闭","bus090704","closeWindow(\"\")"));
    
    List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "feeyear", "年","true"));
	editors.add(new Editor("text", "feemonth", "月","true"));
	editors.add(new Editor("text","feegctid","客户名称"));
	editors.add(new Editor("text","gctarea","所属区域"));
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("feegctid","客户名称"));
	header.add(new Formatter("feesales","销售额",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feemanage","管理费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feetype","支出类型"));
	header.add(new Formatter("feeinvoice","开票税",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feerent","房租",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feedevice","设备摊销",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feeassets","固定资产",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feebuilt","装修费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feetrans","转让费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feeopen","开办费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feepension","社保费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feetax","税收",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feepay","工资",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	
	header.add(new Formatter("feead","广告费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	header.add(new Formatter("feephone","电话费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY));
	
	header.add(new Formatter("feewater","水电费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feetrip","差旅费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feepostage","邮费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feeoffice","办公费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feesocial","公关费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feemedical","转诊费",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feeaccount","会计工资",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("feothersa","其他费用",TagConstants.DF_RIGHT,TagConstants.DT_MONEY,true));
	header.add(new Formatter("bsc012","录入员",true));
	header.add(new Formatter("feeopdata","录入时间","",TagConstants.DT_YEAR_MONTH_DATE,true));
	header.add(new Formatter("feent","备注",true));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("feegctid", "用户编号");
	hidden.put("feeyear", "年");
	hidden.put("feemonth", "月");

	
	pageContext.setAttribute("editor",editors);
    pageContext.setAttribute("button", buttons);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("hidden",hidden);
%>


<%@page import="org.radf.plat.taglib.Editor"%>
<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

	function add(obj){
		//obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/jasper/insertfee.jsp"/>';
		obj.action='<html:rewrite page="/FeeAction.do?method=enter&menuId=fee"/>';
		obj.submit();
	};
	function edit(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/FeeAction.do?method=edit&"/>'+getAlldata(obj);			
		obj.submit();
	};
	function print(){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME 
				+"/business/FeeAction.do?method=report&" 
				+ getAlldata(document.all.tableform);
	};
		$(document).ready( function() {
		       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
				
				$("input[name=feegctid]").autocomplete(shops,{
					max : 10,
					matchContains : true
				});
				$("input[name=feegctid]").result(function(event, data, formatted) {
					if (data){
						var gnm = data[0].substring(data[0].indexOf("=")+1);
						var gid = data[0].substring(0,data[0].indexOf("="));
						$("input[name=feegctid]").val(gid);
						$(this).parent().next().find("input").val(gid);
					}
				});
			});

		function saveData(obj) {
			if (!checkValue(obj)) {
				return false;
			}
			obj.submit();
		};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="团体客户费用信息" />
	<lemis:query action="/FeeAction.do?method=query" editorMeta="editor" topic="客户费用信息查询" />
	<lemis:table action="/FeeAction.do" topic="查询费用信息" headerMeta="header" hiddenMeta="hidden" 
	   mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
