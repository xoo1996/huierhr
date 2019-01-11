<!-- cfgmgmt/queryCI.jsp -->
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
	header.add(new Formatter("tmkfno", "订单号"));
	header.add(new Formatter("tmkgctnm", "客户名称"));
	header.add(new Formatter("tmkcltnm", "用户姓名"));
	header.add(new Formatter("tmksid", "定制机条形码"));
	header.add(new Formatter("tmkplr", "耳机类型"));
	header.add(new Formatter("tmkpnl","面板编码"));
	header.add(new Formatter("pdtnm","助听器型号"));
	header.add(new Formatter("tmkpdt", "计划完工"));
	header.add(new Formatter("tmkpst", "生产状态"));
	header.add(new Formatter("tmkurg", "是否加急"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("开始安装外壳","batchInstall(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmksid","定制机条形码");
	hidden.put("tmkfno","订单号");
	hidden.put("tmkpnl","面板编号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmksid","定制机条形码"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
		$("input[name=tmksid]:first").focus();
		$("input[name=tmksid]:first").val("");
		$("input[name=tmksid]").blur(function() {
			if ($("input[name=tmksid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function batchInstall() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		if (confirm("确实要开始安装外壳吗？")) {
			window.location.href = "/"
					+ lemis.WEB_APP_NAME
					+ "/customization/CustomizationAction.do?method=batchChange&"
					+ getAlldata(document.all.tableform)
					+ "require=startInstall";
		}
		//将数据提交到后台 注意：getAlldata(document.all.tableform)是得到所有要提交的数据。
		else
			return t;
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制机查询" />
	<lemis:query
		action="/CustomizationAction.do?method=query&order=install"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/CustomizationAction.do" headerMeta="header"
		topic="定制机信息" hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


