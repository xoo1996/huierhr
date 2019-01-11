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
	header.add(new Formatter("qafno", "订单号"));
	header.add(new Formatter("qacltnm", "用户姓名"));
	header.add(new Formatter("qasid", "机身编号"));
	header.add(new Formatter("qapnm", "助听器型号"));
	header.add(new Formatter("qatype", "质检类别"));
	header.add(new Formatter("qastatus", "质检状态"));
	header.add(new Formatter("bsc012", "质检员"));
	header.add(new Formatter("qachkdt", "质检日期"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("质检记录修改","modify(document.all.tableform)");
	buttons.put("质检记录删除","deleteci(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("qaid", "质检流水号");
	hidden.put("qacltnm", "用户姓名");
	hidden.put("qapnm", "助听器型号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","qafno","订单号"));
	editors.add(new Editor("text","qasid","机身编号"));
	editors.add(new Editor("text","qacltnm", "用户姓名"));
	editors.add(new Editor("text","qatype", "质检类别"));
	editors.add(new Editor("text","qastatus", "质检状态"));
	editors.add(new Editor("date","qachkdt", "质检日期"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script language="javascript">
		//修改记录
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/QAAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//删除记录
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该质检记录吗？")) {
				obj.action = '<html:rewrite page="/QAAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="质检记录修改查询" />
	<lemis:query action="/QAAction.do?method=query&order=modify"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="QAAction.do" headerMeta="header" topic="质检记录"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


