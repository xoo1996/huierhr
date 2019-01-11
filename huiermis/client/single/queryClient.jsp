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

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<%  

	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("增加客户","addClient()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("gctnm","所属团体"));
	header.add(new Formatter("ictgender", "性别"));
	header.add(new Formatter("ictbdt", "出生日期"));
	header.add(new Formatter("icttel", "联系电话"));
	header.add(new Formatter("ictaddr","联系地址"));
	header.add(new Formatter("ictphis", "使用过何种助听器"));
	header.add(new Formatter("ictsrc", "来源"));
	header.add(new Formatter("ictnt", "备注"));
	header.add(new Formatter("ictdate", "登记日期"));
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ictgctid","客户代码"));
	editors.add(new Editor("text", "ictnm", "用户姓名")); 
	editors.add(new Editor("text", "ictgender", "性别")); 
	editors.add(new Editor("text","ictaddr","联系地址"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "用户编号");
	hidden.put("ictgctid","客户代码");
	

    pageContext.setAttribute("button", buttons); 
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header",header);
	pageContext.setAttribute("hidden",hidden);
    
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%> 
<script type="text/javascript">
	$(document).ready(function(){
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{", "").replace("}", "").split(", ");
		
		if(grCli=="1501000000")
		{
			$("input[name=ictgctid]").autocomplete(shops,{
				max:10,
				matchContains:true
			});
			
			$("input[name=ictgctid]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=ictgctid]").val(gid);
					$(this).parent().next().find("input").val(gid);
				}
			});
		}
		else{
			$("input[name=ictgctid]").val("<%=dto.getBsc011()%>");
			$("input[name=ictgctid]").attr("readonly","true");
			$("input[value=重置[R]]").bind("click",function(e){
			$("input[name=ictgctid]").val("<%=dto.getBsc011()%>");
					
			});
		}
		
	});
</script>

<script language="javascript">	

	
	function addClient(obj) {
	
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/client/SingleClientAction.do?method=forwardClient";
	}; 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="客户查询" />
	<lemis:query action="/SingleClientAction.do?method=queryClient"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/CustomOrderAction.do" headerMeta="header" hiddenMeta="hidden"
	 topic="客户明细" 
		mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
