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
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	//header.add(new Formatter("rsvid", "预约号"));
	//header.add(new Formatter("rsvnm", "预约客户姓名"));
	//header.add(new Formatter("rsvsta", "预约状态"));
	//header.add(new Formatter("rsvtodayid", "当天号码"));
	header.add(new Formatter("rsvgctnm", "预约单位"));
	//header.add(new Formatter("rsvgctzhuannm", "转诊单位"));
	//header.add(new Formatter("rsvphone", "手机号码"));
	header.add(new Formatter("ypnm","预约专家"));
	header.add(new Formatter("qdmount","签到人数"));
	header.add(new Formatter("rsvmount","预约人数"));
	header.add(new Formatter("totalmount","总人数"));
	//header.add(new Formatter("rsvdate", "预约日期"));
	//header.add(new Formatter("rsvnote","备注"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("查看明细","statistics_detail(document.all.tableform)");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ypnm","预约专家");
	hidden.put("rsvgctnm", "预约单位名称");
	hidden.put("start", "预约日期从");
	hidden.put("end", "到");
	
	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "rsvnm", "预约客户姓名"));
	editors.add(new Editor("text","rsvgctnm","预约单位"));
	//editors.add(new Editor("text", "rsvphone", "手机号码"));
	//editors.add(new Editor("text","rsvgctzhuannm","转诊单位"));
	//editors.add(new Editor("text", "rsvsta", "预约状态"));
	editors.add(new Editor("date","start","预约日期从"));
	editors.add(new Editor("date","end","到"));
	editors.add(new Editor("text","ypnm","预约专家"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "预约信息");//表头
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>


<script language="javascript">
	
	function statistics_detail(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/ReservationAction.do?method=statistics_detail&"/>'+getAlldata(obj);			
		obj.submit();
		
	}
	
</script>

<script language="javascript">
	$(document).ready(function(){
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		if(grCli=="1501000000")
		{
			$("input[name=rsvgctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
				return data[0].substring(0);
				}
			});
		
			$("input[name=rsvgctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=rsvgctnm]").val(gnm);
				
					}
				});
		}
		else
		{
			$("input[name=rsvgctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=rsvgctnm]").attr("readonly","true");
			$("input[value=重置[R]]").bind("click",function(e){
			$("input[name=rsvgctnm]").val("<%=dto.getBsc012()%>");
					
			}); 
	 	}
		
		//var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
		//var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=rsvgctzhuannm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
				return data[0];
			}
		});
		
		$("input[name=rsvgctzhuannm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1);
				$("input[name=rsvgctzhuannm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});

  
	
</script>
				
<lemis:base />
<lemis:body>
	<lemis:title title="预约客户查询" />
	<lemis:query action="/ReservationAction.do?method=statistics_query"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="ReservationAction.do" headerMeta="header"
		topic="客户预约信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


