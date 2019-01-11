<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ivtyear","年"));
	header.add(new Formatter("gctid","客户代码"));
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gender", "性别"));
	header.add(new Formatter("amount", "人数"));
	header.add(new Formatter("total","总人数"));

	Map<String,String> hidden = new LinkedHashMap<String,String>();
	hidden.put("gctid", "客户代码");
	hidden.put("ivtyear", "年");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","年份","true"));
	editors.add(new Editor("text","ivtmonths","月从","true"));
	editors.add(new Editor("text","ivtmontht","到","true"));
	editors.add(new Editor("text","ivtgcltid","客户代码"));
	editors.add(new Editor("text","ages","年龄从"));
	editors.add(new Editor("text","aget","到"));
	
	editors.add(new Editor("money","prices","价格从"));
	editors.add(new Editor("money","pricet","到"));
	
	editors.add(new Editor("text","earcase","配机情况","false"));
	editors.add(new Editor("text","ictgender","性别"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("明 细","detail(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">

$(document).ready(function(){
	var ages = $("input[name=ages]").val();
	var aget = $("input[name=aget]").val();
	var prices = $("#prices").val();
	var pricet = $("#pricet").val();
	if(ages == '0'){
		$("input[name=ages]").val("");
	}
	if(aget == '0'){
		$("input[name=aget]").val("");
	}
	if(prices == '0.00'){
		$("#prices").val("");
	}
	if(pricet == '0.00'){
		$("#pricet").val("");
	}

	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	
	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");

	if(grCli=="1501000000")
	{
		$("input[name=ivtgcltid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=ivtgcltid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ivtgcltid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	}
	else{
		$("input[name=ivtgcltid]").val("<%=dto.getBsc011()%>");
		$("input[name=ivtgcltid]").attr("readonly","true");
		$("input[value=重置[R]]").bind("click",function(e){
			$("input[name=ivtgcltid]").val("<%=dto.getBsc011()%>");
					
			});
	}
});
</script>

<script type="text/javascript">
	function detail(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=detail&"/>'+getAlldata(obj);
		obj.submit();
	};
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="用户统计查询" />
	<lemis:query action="/BusinessAction.do?method=compositequery" editorMeta="editor"
		topic="查询条件" hiddenMeta="hidden"/>
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="用户详细信息"
		mode="radio"  hiddenMeta="hidden" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


