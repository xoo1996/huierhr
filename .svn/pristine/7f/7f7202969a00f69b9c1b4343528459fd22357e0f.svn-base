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
	//共10个属性项
	header.add(new Formatter("repfolid", "订单号"));
	header.add(new Formatter("repgctnm", "客户名称"));
	header.add(new Formatter("repcltnm", "个人客户"));
	header.add(new Formatter("repid", "机身编号"));
	header.add(new Formatter("reppnm", "助听器名称"));
	header.add(new Formatter("repdate", "送修日期","", TagConstants.DT_YEAR_MONTH_DATE));
	/* header.add(new Formatter("repgctid", "送修单位")); */
	header.add(new Formatter("foltype","订单类型"));
	header.add(new Formatter("repcpy", "维修厂商"));
	header.add(new Formatter("repamt", "维修费"));
	header.add(new Formatter("folischg", "是否已收费"));
	
	   
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("收 费","weixiushoufei(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repfolid","订单号");
	hidden.put("folischg","是否已收费");
	hidden.put("repgctnm","客户名称");
	hidden.put("repdate","送修日期");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "repfolid", "订单号"));
	editors.add(new Editor("text", "repcltnm", "个人客户"));	//repcltnm在数据表AA10中不存在，所以不显示下拉列表
	editors.add(new Editor("text", "folischg", "是否已收费"));	//此处folischg为超过保质期的维修费用，下拉列表，folischg在数据表AA10中存在，所以显示下拉列表
	editors.add(new Editor("date","start","送修日期从"));
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
	function weixiushoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ChargeAction.do?method=repairChargeDetail&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<script>
	$(document).ready(function(){
		$("select[name=folischg]").val("0");
	});
</script>
<lemis:base />
<lemis:body>

    
    <lemis:title title="收费查询" />
	<lemis:query action="/ChargeAction.do?method=query&charge=repair" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="收费信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


