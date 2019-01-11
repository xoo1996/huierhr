<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>

<%  
    Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictnm", "个人客户"));
	header.add(new Formatter("foltype", "订单类型"));
	header.add(new Formatter("pdtnm", "商品名称"));
	header.add(new Formatter("pdtprc", "价格","",TagConstants.DT_MONEY));
	header.add(new Formatter("ivtlmqnt", "结存数"));
	header.add(new Formatter("ivtlsqnt", "补入数"));
	header.add(new Formatter("temp01", "小计"));
	header.add(new Formatter("ivtpqnt", "回款数"));
	header.add(new Formatter("temp02", "本月结存"));
	header.add(new Formatter("ivtdiscount", "扣率"));
	header.add(new Formatter("ivtpamnt", "应收款"));
	header.add(new Formatter("ivtnote", "备注"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtpdtid","商品代码");
	hidden.put("ivtcltid","个人客户代码");
	hidden.put("ictnm", "个人客户");
	hidden.put("pdtnm", "商品名称");
	hidden.put("pdtprc", "价格");
	hidden.put("ivtlmqnt", "结存数");
	hidden.put("ivtlsqnt", "补入数");
	hidden.put("temp01", "小计");
	hidden.put("ivtpqnt", "回款数");
	hidden.put("ivtdiscount", "扣率");
	hidden.put("ivtpamnt", "应收款");
	hidden.put("ivtnote", "备注");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "年份","true"));
	editors.add(new Editor("text", "ivtmonth", "月份","true"));
	editors.add(new Editor("text", "ivtgcltid", "团体代码","true"));

	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
%>

<lemis:base />
<lemis:body>
	
	<lemis:title title="月结信息查询" />
		<lemis:query action="/BusinessAction.do?method=yuejiequery" editorMeta="editor" topic="月结信息查询" />
		<lemis:table action="BusinessAction.do" headerMeta="header" topic="月结信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
   <lemis:bottom />
</lemis:body>
</html>


