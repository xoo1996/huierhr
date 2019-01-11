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
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("bvgroup","所属团体",TagConstants.DF_CENTER));
	header.add(new Formatter("bvname","客户姓名",TagConstants.DF_CENTER));
	header.add(new Formatter("bvmethod","回访方法",TagConstants.DF_CENTER));
	header.add(new Formatter("bveffect", "效果评定",TagConstants.DF_CENTER));
	header.add(new Formatter("bvvisitor", "回访者",TagConstants.DF_CENTER));
	header.add(new Formatter("bvdate", "回访日期",TagConstants.DF_CENTER));
	header.add(new Formatter("bvnum","所属团体总回访次数",TagConstants.DF_CENTER));

	Map<String,String> hidden = new LinkedHashMap<String,String>();
	hidden.put("bvid", "客户代码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "gctnm", "所属团体"));
	editors.add(new Editor("date", "start", "回访时间从"));
	editors.add(new Editor("date", "end", "到"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("查看详情","querySctlDetail(document.all.tableform)");
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
<script language="javascript">
		//所属团体
		$(document).ready(function(){
			var grCli="";
			grCli=<%=dto.getBsc001()%>;
			if(grCli=="1501000000")
			{
				var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
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
						/* 	$("input[@type=hidden][name=folctid]").val(gid);
						$("input[@type=hidden][name=folctid]").val(); */
					
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
		function querySctlDetail(obj) {
			var t = editObj("chk");
			if (!t) {
				return t;
			}
			obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetailBV&type=show&"/>' + getAlldata(obj);
			obj.submit();
		};
	</script>		

<lemis:base />
<lemis:body>
	<lemis:title title="售后服务调查回访统计查询" />
	<lemis:query action="/SingleClientAction.do?method=bvCount" editorMeta="editor"
		topic="查询条件" hiddenMeta="hidden"/>
	<lemis:table action="SingleClientAction.do" headerMeta="header" topic="用户详细信息"
		mode="radio"  hiddenMeta="hidden" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


