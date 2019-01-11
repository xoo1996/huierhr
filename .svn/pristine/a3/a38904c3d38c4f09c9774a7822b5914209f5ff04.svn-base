<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();

	header.add(new Formatter("tmkgctnm", "客户名称"));
	header.add(new Formatter("tmkcltnm", "用户姓名"));

	header.add(new Formatter("tmkebjsid","机身编号"));
	header.add(new Formatter("tmkpnm", "产品名称"));
	header.add(new Formatter("tmkrependdt", "保修截止日期"));
	header.add(new Formatter("tmkordate", "订购日期"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkfno", "订单号");
	hidden.put("tmkcltid", "用户代码");
	hidden.put("tmkpid", "耳机代码");
	hidden.put("tmkcltnm", "用户姓名");
	hidden.put("tmkpnm", "耳机名称");
	
	List<Editor> editors = new ArrayList<Editor>();

	editors.add(new Editor("text","tmkcltnm", "用户姓名"));
	editors.add(new Editor("date","start", "日期从"));
	editors.add(new Editor("date","end", "到"));

	 
	
	editors.add(new Editor("text","tmkgctnm", "客户名称"));
	editors.add(new Editor("text","tmkebjsid", "机身编号"));

	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>
<script language="javascript">
		//显示详细信息
  		function detailci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=print&"/>'+getAlldata(obj);			
			obj.submit();
  		};
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
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>	
<script language="javascript">
$(document).ready(function(){
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	if(grCli=="1501000000")
	{
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=tmkgctnm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
			return data[0].substring(0);
			}
		});
	
		$("input[name=tmkgctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=tmkgctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
				$("input[@type=hidden][name=folctid]").val(); */
			
				}
			});
	}
	else
	{
		$("input[name=tmkgctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=tmkgctnm]").attr("readonly","true");
		$("input[value=重置[R]]").bind("click",function(e){
		$("input[name=tmkgctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}
	
	
});
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="定制记录查询" />
	<lemis:query action="/CustomizationAction.do?method=query&order=erbeiji"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="CustomizationAction.do" headerMeta="header"
		topic="定制记录" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


