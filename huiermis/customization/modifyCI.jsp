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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tmkfno", "订单号"));
	header.add(new Formatter("tmkgctnm", "客户名称"));
	header.add(new Formatter("tmkcltnm", "用户姓名"));
	header.add(new Formatter("tmksid", "定制机条形码"));
	header.add(new Formatter("tmkplr", "耳机类型"));
	header.add(new Formatter("tmkpnm", "助听器型号"));
	header.add(new Formatter("tmkpdt", "计划完工"));
	header.add(new Formatter("tmkpst", "定制机状态"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("定制机详细信息","detailci(document.all.tableform)");
	buttons.put("打印条形码","print(document.all.tableform)");
	buttons.put("修改定制记录","modify(document.all.tableform)");
	buttons.put("删除定制记录","deleteci(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Button> buttons1=new ArrayList<Button>();
	buttons1.add(new Button("a","打印条形码","cus010201","print(document.all.tableform)"));
	buttons1.add(new Button("b","修改定制记录","cus010202","modify(document.all.tableform)"));
	buttons1.add(new Button("c","删除定制记录","cus010203","deleteci(document.all.tableform)"));
	buttons1.add(new Button("d","关 闭","cus010204","closeWindow(\"\")"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkfno", "订单号");
	hidden.put("tmkcltid", "用户代码");
	hidden.put("tmkpid", "耳机代码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkpnm", "耳机名称");
	hidden.put("tmksid","定制机条形码");
	hidden.put("tmkpst","定制机状态");
	hidden.put("folsta","订单状态");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tmkfno", "订单号"));
	editors.add(new Editor("text","tmksid","定制机条形码"));
	editors.add(new Editor("text","tmkpst", "定制机状态"));
	editors.add(new Editor("text","tmkcltnm", "用户姓名"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons1);
%>
<html>
<script language="javascript">
		//修改定制机详细信息
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//删除定制机
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该定制记录吗?")) {
				obj.action = '<html:rewrite page="/CustomizationAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
  		function print(obj) {
  			var t = editObj("chk");
  			if (!t) {
  				return t;
  			}
  			else {
  				obj.action = '<html:rewrite page="/CustomizationAction.do?method=barcode&"/>' + getAlldata(obj);
  				obj.submit();
  			} 
  		};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制记录修改查询" />
	<lemis:query action="/CustomizationAction.do?method=query&order=modify"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="CustomizationAction.do" headerMeta="header"
		topic="定制记录" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


