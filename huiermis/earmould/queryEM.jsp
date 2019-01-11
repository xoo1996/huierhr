<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String tmecltid = (String) request.getSession().getAttribute("tmecltid");
	String ictnm = (String) request.getSession().getAttribute("ictnm");
	String tmectid = (String) request.getSession().getAttribute("tmectid");
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tmeno", "订单号"));
	header.add(new Formatter("tmecltid","用户代码"));
	header.add(new Formatter("foldt", "录单日期"));
	header.add(new Formatter("pdtnm", "耳模名称"));
	header.add(new Formatter("tmesta","耳模状态"));
	header.add(new Formatter("tmemat", "耳模类型"));
	header.add(new Formatter("tmegif", "类别"));
	header.add(new Formatter("tmemaker", "生产员"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新增","newEM(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	buttons.put("返回","history.back()");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmecltid", "用户编号");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	request.getSession().setAttribute("tmecltid",tmecltid);
	
%>
<html>
<script language="javascript">
  		function newEM(obj){
			obj.action = '<html:rewrite href="/huiermis/earmould/EarMouldAction.do?method=addNew&"/>'+getAlldata(obj);			
            if(confirm("确实要新增耳模订单吗？"))
            {
			  obj.submit();
            }
  		};
</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="个人客户耳模查询" />
		<lemis:tabletitle title="个人客户基本信息" />
		<table class="tableInput">
			<lemis:editorlayout />
			<html:form action="/EarMouldAction.do" method="POST">
				<tr>
					<lemis:texteditor property="tmecltid" label="用户编号" value="<%=tmecltid %>" disable="true" />
					<lemis:texteditor property="ictnm" label="用户姓名" value="<%=ictnm %>" disable="true" />
					<lemis:texteditor property="tmectid" label="所属团体" value="<%=tmectid %>" disable="true" />
				</tr>
			</html:form>
		</table>
		<lemis:table action="/EarMouldAction.do" headerMeta="header" topic="个人客户耳模信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>