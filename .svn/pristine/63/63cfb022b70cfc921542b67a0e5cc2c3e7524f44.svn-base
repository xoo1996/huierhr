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
	//共12个属性项
	header.add(new Formatter("folno", "订单号",TagConstants.DF_CENTER));
	header.add(new Formatter("folctnm", "客户名称"));
	header.add(new Formatter("cltnm", "个人客户"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("fdtqnt", "数量"));
	header.add(new Formatter("fdtprc", "售价", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("discount", "扣率"));
	header.add(new Formatter("deposit", "定金", "color:#000000;", TagConstants.DT_MONEY)); 
	header.add(new Formatter("xubaofee", "续保费", "color:#000000;", TagConstants.DT_MONEY)); 
	header.add(new Formatter("balance", "应付余额", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "订货日期"));
	header.add(new Formatter("folchgdt", "收费日期"));
	header.add(new Formatter("fdtodt", "退机日期"));
	header.add(new Formatter("fdtrecheaddt", "退机到总部日期"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("fdtmklr", "左耳右耳"));
	header.add(new Formatter("folischg", "是否已收费"));
	header.add(new Formatter("folnt", "备注"));
	header.add(new Formatter("foladdr", "联系地址", true)); 
	header.add(new Formatter("folsta", "状态")); 

	//Map button1 = new LinkedHashMap();
	//button1.put("查询[Q]","return query(this.form)");
	//button1.put("重置[R]","resetForm(this.form);");
	//pageContext.setAttribute("button1",button1);
	   
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("收 费","dingzhishoufei(document.all.tableform)");
    buttons.put("退机退费","recoil(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "订单号码");
	hidden.put("folno","订单号");
	hidden.put("folischg","是否已收费");
	hidden.put("foltype","订单类型");
	hidden.put("folsta","订单状态");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "订单号"));
	editors.add(new Editor("text", "foltype", "订单类型"));
	editors.add(new Editor("text", "cltnm", "个人客户"));	//cltnm在数据表AA10中不存在，所以不显示下拉列表
	editors.add(new Editor("text", "folischg", "是否已收费"));	//下拉列表，folischg在数据表AA10中存在，所以显示下拉列表
	editors.add(new Editor("text", "foldtty", "日期类型"));
	editors.add(new Editor("date","start","日期从"));
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
	function dingzhishoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}

		obj.action = '<html:rewrite page="/ChargeAction.do?method=customizedChargeDetail&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	
	function recoil(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}

		obj.action = '<html:rewrite page="/ChargeAction.do?method=customizedRecoilDetail&"/>' + getAlldata(obj);
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
	<lemis:query action="/ChargeAction.do?method=query&charge=search" editorMeta="editor" topic="查询条件" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="收费信息"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


