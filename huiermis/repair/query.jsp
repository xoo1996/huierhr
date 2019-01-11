<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("repfolid", "订单号"));
	header.add(new Formatter("repgctnm", "客户名称"));
	header.add(new Formatter("repcltnm", "用户姓名"));
	header.add(new Formatter("repid", "机身编号"));
	header.add(new Formatter("reppnm", "助听器名称"));
	header.add(new Formatter("pdtcls", "耳机类别"));
	header.add(new Formatter("repdate", "送修日期","", TagConstants.DT_YEAR_MONTH_DATE));
	//header.add(new Formatter("repgctid", "送修单位"));
	header.add(new Formatter("reppdate","计划完工"));
	header.add(new Formatter("repsta", "维修状态"));
	header.add(new Formatter("repcls", "维修类别"));
	header.add(new Formatter("repcpy", "维修厂商"));
	header.add(new Formatter("repamt", "维修费"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("新增","newSC(document.all.tableform)");
	buttons.put("维修详情历史","weixiu(document.all.tableform)");
	buttons.put("定制机维修历史","history(document.all.tableform)");
    buttons.put("维修记录详细信息","detailci(document.all.tableform)");
	buttons.put("打印条形码","printer(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "维修流水号");
	hidden.put("repgctnm", "送修单位名称");
	hidden.put("repgctid", "送修单位代码");
	hidden.put("repcltnm", "用户姓名");
	hidden.put("reppid", "助听器代码");
	hidden.put("repid", "机身编号");
	hidden.put("repcpy", "维修厂商");
	hidden.put("reppnm", "助听器名称");
	hidden.put("repcltid","用户代码");
	
	hidden.put("tmksid","机身编号");
	hidden.put("tmkcltid","用户代码");
	hidden.put("tmkcltnm","用户姓名");
	hidden.put("tmkgctnm","所属团体");
	hidden.put("tmkfno","订单号");
	hidden.put("tmkpnm","助听器名称");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","订单号"));
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","repcltnm", "用户姓名"));
	editors.add(new Editor("text","repgctnm", "客户名称"));
	editors.add(new Editor("text","repcpy", "维修厂商"));
	editors.add(new Editor("text","repsta", "维修状态"));
	editors.add(new Editor("text","reppid", "产品代码"));
	editors.add(new Editor("text","reppnm", "产品名称"));
	editors.add(new Editor("text","pdtcls", "耳机类别"));	
	editors.add(new Editor("text","repcls", "维修类别"));
	editors.add(new Editor("date","start", "完工日期从"));
	editors.add(new Editor("date","end", "到"));
	editors.add(new Editor("date","reppdate", "计划完工日期"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
		$("input[name=repid]:first").focus();
		$("input[name=repid]:first").val("");
		$("input[name=repid]").blur(function() {
			if ($("input[name=repid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function newSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		
		obj.action = '<html:rewrite page="/RepairAction.do?method=enter2&"/>' + getAlldata(obj);
		obj.submit();
	};
	function weixiu(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=queryRep&"/>' + getAlldata(obj);
		obj.submit();
	};
	//维修历史
	function history(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		//obj.action = '<html:rewrite page="/RepairAction.do?method=history&"/>' + getAlldata(obj);
		obj.action='<html:rewrite href="../customization/CustomizationAction.do?method=queryHistory&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	
	//显示详细信息
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		getAlldata(obj);
		obj.action = '<html:rewrite page="/RepairAction.do?method=print&"/>' + getAlldata(obj);
		obj.submit();
	};
	function printer(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=barcode&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<script language="javascript">
$(document).ready(function(){
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	if(grCli=="1501000000")
	{
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=repgctnm]").autocomplete(shops,{
			max:1000,
			matchContains:true,
			formatItem:function(data,i,max){
			return data[0].substring(0);
			}
		});
	
		$("input[name=repgctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=repgctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
				$("input[@type=hidden][name=folctid]").val(); */
			
				}
			});
	}
	else
	{
		$("input[name=repgctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=repgctnm]").attr("readonly","true");
		$("input[value=重置[R]]").bind("click",function(e){
		$("input[name=repgctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}
	
	
});
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:query action="/RepairAction.do?method=query" editorMeta="editor"
		topic="查询条件" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


