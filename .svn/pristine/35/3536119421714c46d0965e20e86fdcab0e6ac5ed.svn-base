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
	header.add(new Formatter("rsvid", "预约号"));
	header.add(new Formatter("rsvnm", "预约客户姓名"));
	header.add(new Formatter("gctnm", "预约单位"));
	header.add(new Formatter("rsvphone", "手机号码"));
	header.add(new Formatter("ypnm","验配师姓名"));
	header.add(new Formatter("rsvdate", "预约日期"));
	//header.add(new Formatter("rsvsta", "预约状态"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("新客户门诊","newmenzhen(document.all.tableform)");
	buttons.put("老客户门诊","oldmenzhen(document.all.tableform)");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("rsvid","预约号");
	hidden.put("rsvnm", "预约客户姓名");
	hidden.put("rsvphone","手机号码");
	//hidden.put("ypnm","验配师姓名");
	hidden.put("rsvgcltid", "预约单位");
	hidden.put("ictnm","用户姓名");
	hidden.put("ictgctid","所属团体");
	hidden.put("icttel","联系电话");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "rsvnm", "预约客户姓名"));
	editors.add(new Editor("text", "rsvphone", "手机号码"));
	//editors.add(new Editor("text", "rsvsta", "预约状态"));
	//editors.add(new Editor("date","start","预约日期从"));
	//editors.add(new Editor("date","end","到"));
	
	
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
	function newmenzhen(obj){
	
		//window.location.href = "/" + lemis.WEB_APP_NAME
		//+ "/client/SingleClientAction.do?method=forwardClient";
			window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/client/ReservationAction.do?method=newmenzhen&"
			+ getAlldata(document.all.tableform);
	};
	
	function oldmenzhen(obj){
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/client/ReservationAction.do?method=oldmenzhen&"
		+ getAlldata(document.all.tableform);
	};
</script>

<script language="javascript">
	$(document).ready(function(){
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
	<lemis:title title="预约客户查询" />
	<lemis:query action="/ReservationAction.do?method=menzhenquery"
		editorMeta="editor" topic="客户预约信息查询" />
	<lemis:table action="ReservationAction.do" headerMeta="header"
		topic="客户预约信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


