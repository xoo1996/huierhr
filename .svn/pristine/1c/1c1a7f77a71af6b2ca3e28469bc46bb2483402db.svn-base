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

<html>
<link rel="stylesheet" type="text/css" href="/huiermis/css/jquery.autocomplete.css" />
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>


<%  
	String totalYSK = (String)request.getSession().getAttribute("totalYSK");

    Map<String, String> buttons = new LinkedHashMap<String, String>();
    buttons.put("惠耳打印","huier_print()");
	buttons.put("杰闻打印","jiewen_print()");
    buttons.put("月报表明细","report(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ivtyear","年"));
	header.add(new Formatter("ivtmonth","月"));
	header.add(new Formatter("gctnm", "客户名称"));
	header.add(new Formatter("gctprovince","所属省份"));
	header.add(new Formatter("gctarea", "所属区域"));

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","年","true"));
	editors.add(new Editor("text","ivtmonth","月","true"));
	editors.add(new Editor("text","gctnm","客户名称"));
	editors.add(new Editor("text","gctarea","所属区域"));
	editors.add(new Editor("text","gctprovince","所属省份"));
	
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtyear", "年");
	hidden.put("ivtmonth", "月");
	hidden.put("ivtgcltid", "客户代码");


	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String grCli_cx=dto.getBsc011();
%>
<script language="javascript">	
	$(document).ready(function(e) {

		//HF0001 销售一区(区域一) HF0010 销售二区（区域二）  HF0011销售三区 (区域三) HF0012 销售三区   HF0019 销售四区 (区域四)
		//HF0005  销售五区 (区域五)  HF0014 销售七区  (区域七)  销售六区（杭州区）
		var grCli_cx="<%=grCli_cx%>";
	    //$("input[name=gctnm]").val(grCli_cx);
		if(grCli_cx=="HF0001"){
			$("select[name=gctarea]").val("区域一");
			$("select[name=gctarea]").attr("disabled",true);
			
			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域一");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
			    $("select[name=gctarea]").val("区域一");
			});
		}
		if(grCli_cx=="HF0010"){
			$("select[name=gctarea]").val("区域二");
			$("select[name=gctarea]").attr("disabled",true);
			
			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域二");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
				$("select[name=gctarea]").val("区域二");
			});
		}
		if(grCli_cx=="HF0011" || grCli_cx=="HF0012"){
			$("select[name=gctarea]").val("区域三");
			$("select[name=gctarea]").attr("disabled",true);

			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域三");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
				$("select[name=gctarea]").val("区域三");
			});
		}
		if(grCli_cx=="HF0019"){
			$("select[name=gctarea]").val("区域四");
			$("select[name=gctarea]").attr("disabled",true);

			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域四");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
				$("select[name=gctarea]").val("区域四");
			});
		}
		if(grCli_cx=="HF0005"){
			$("select[name=gctarea]").val("区域五");
			$("select[name=gctarea]").attr("disabled",true);

			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域五");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
				$("select[name=gctarea]").val("区域五");
			});
		}
		if(grCli_cx=="HF0014"){
			$("select[name=gctarea]").val("区域七");
			$("select[name=gctarea]").attr("disabled",true);

			$("input[value=重置[R]]").bind("click",function(e){
				$("select[name=gctarea]").val("区域七");
				$("select[name=gctarea]").attr("disabled",true);
			});
			$("input[value=查询[Q]]").bind("click",function(e){
				$("select[name=gctarea]").attr("disabled",false);
				$("select[name=gctarea]").val("区域七");
			});
		}
		
		
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
					$("input[@type=hidden][name=ivtgcltid]").val(gid);
						/* $("input[@type=hidden][name=folctid]").val(); */
				
					}
				});
		}
		else
		{
			$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=gctnm]").attr("readonly","true");
			$("input[value=重置[R]]").bind("click",function(e){
			$("input[name=ivtgcltid]").val("<%=dto.getBsc012()%>");
					
			}); 
	 	}
		
	});
	function report(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=yuejiequery&mark=zhishu&"/>' + getAlldata(obj);
		obj.submit();
		//window.location.href = "/" + lemis.WEB_APP_NAME
		//		+ "/business/BusinessAction.do?method=batchSubmit&"
		//		+ getAlldata(document.all.tableform);
	}
	
	function huier_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=report&type=huier&"
				+ getAlldata(document.all.tableform);
	};
	function jiewen_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=report&type=jiewen&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="月度结账查询" />
	<lemis:query action="/BusinessAction.do?method=ZSyuejiequery"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"topic="月度结账明细" hiddenMeta="hidden"
		mode="radio"  />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
	<html:hidden property="ivtgcltid"/>
</lemis:body>
</html>
