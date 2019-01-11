<!-- cfgmgmt/queryCI.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictid", "用户编号"));
	header.add(new Formatter("ictnm", "用户姓名"));
	header.add(new Formatter("gctnm", "所属团体"));
	header.add(new Formatter("ictpnm", "家长姓名"));
	header.add(new Formatter("ictgender", "性别"));
	header.add(new Formatter("ictbdt", "出生日期"));
	header.add(new Formatter("ictaddr", "联系地址", true));
	header.add(new Formatter("ictpcd", "邮政编码"));
	header.add(new Formatter("icttel", "联系电话"));
	header.add(new Formatter("ictphis", "使用过何种助听器"));
	header.add(new Formatter("ictsrc", "来源"));
	header.add(new Formatter("ictnt", "备注"));
	header.add(new Formatter("ictdate", "登记日期"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修改","modify(document.all.tableform)");
	buttons.put("删除","deleteci(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "客户ID");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ictnm", "用户姓名"));
	editors.add(new Editor("text", "gctnm", "所属团体"));
	editors.add(new Editor("text", "ictaddr", "联系地址"));
	editors.add(new Editor("text","ages","年龄从"));
	editors.add(new Editor("text","aget","到"));
	editors.add(new Editor("date","start","登记日期从"));
	editors.add(new Editor("date","end","到"));
	editors.add(new Editor("text","pjsta","是否配机"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

	<script language="javascript">
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/SingleClientAction.do?method=modify&tp=m&"/>'+getAlldata(obj);			
			obj.submit();
  		};
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该用户信息吗?")){
				obj.action = '<html:rewrite page="/SingleClientAction.do?method=delete&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
	</script>
	
	<script language="javascript">
	$(document).ready(function(){

		var ages = $("input[name=ages]").val();
		var aget = $("input[name=aget]").val();
		if(ages == '0'){
			$("input[name=ages]").val("");
		}
		if(aget == '0'){
			$("input[name=aget]").val("");
		}

		
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		if(grCli=="1501000000")
		{
			$("input[name=gctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
				return data[0].substring(0);
				}
			});
		
			$("input[name=gctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=gctnm]").val(gnm);
				
					}
				});
		}
		else
		{
			$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=gctnm]").attr("readonly","true");
			$("input[value=重置[R]]").bind("click",function(e){
			$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
					
			}); 
	 	}
	});
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="个人客户查询" />
		<lemis:query action="/SingleClientAction.do?method=query&order=modify" editorMeta="editor" topic="个人客户信息查询" />
		<lemis:table action="SingleClientAction.do" headerMeta="header" topic="个人客户信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


