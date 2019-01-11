<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("repfolid", "订单号"));
	header.add(new Formatter("repgctnm", "客户名称"));
	header.add(new Formatter("repcltnm", "用户姓名"));
	header.add(new Formatter("repid", "机身编号"));
	header.add(new Formatter("reppnm", "产品名称"));
	header.add(new Formatter("repdate", "送修日期"));
	header.add(new Formatter("repgctid", "送修单位"));
	header.add(new Formatter("repfdate", "完工日期"));
	header.add(new Formatter("repkpdate","开票日期"));
	header.add(new Formatter("repamt", "维修费"));
	header.add(new Formatter("repcpy", "维修厂商"));
	header.add(new Formatter("repiskp", "是否开票"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("开票","input(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "维修流水号");
	hidden.put("repiskp","是否开票");
	//hidden.put("repkpdate","开票日期");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","订单号"));
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","repcltnm", "用户姓名"));
	editors.add(new Editor("text","repcpy", "维修厂商"));
	editors.add(new Editor("text","repiskp", "是否开票"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>

<script language="javascript">
	function input() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME + '/repair/RepairAction.do?method=batchkp&' + getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="维修记录查询" />
	<lemis:query action="/RepairAction.do?method=query&order=kaip"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="维修记录"
		hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


