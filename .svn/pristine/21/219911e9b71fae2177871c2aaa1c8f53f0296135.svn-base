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
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%  

	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("输入维修订单","add()");
	buttons.put("增加客户","addClient()");
	//buttons.put("捷文打印","jiewen_print()");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
    header.add(new Formatter("ictnm", "用户姓名")); 
	header.add(new Formatter("gctnm","所属团体"));
	header.add(new Formatter("repid","机身编号"));
	header.add(new Formatter("ictgender", "性别"));
	header.add(new Formatter("ictbdt", "出生日期"));
	header.add(new Formatter("icttel", "联系电话"));
	header.add(new Formatter("ictaddr","联系地址"));
	header.add(new Formatter("ictphis", "使用过何种助听器"));
	header.add(new Formatter("ictsrc", "来源"));
	header.add(new Formatter("ictnt", "备注"));
	header.add(new Formatter("ictdate", "登记日期"));
	
	
	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "stoproid", "年","true"));
	/* editors.add(new Editor("text","ictgctid","客户代码")); */
	editors.add(new Editor("text", "gctnm", "所属团体")); 
	editors.add(new Editor("text", "ictnm", "用户姓名")); 
	editors.add(new Editor("text", "ictgender", "性别")); 
	editors.add(new Editor("text","repid","机身编号"));
	editors.add(new Editor("text","ictaddr","联系地址"));
	/* editors.add(new Editor("text","stoproname","商品名称"));
	editors.add(new Editor("date","start","入库日期从"));
	editors.add(new Editor("date","end","到")); */
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "用户编号");
	hidden.put("repid","机身编号");
	//hidden.put("repid","验配日期");
	

    pageContext.setAttribute("button", buttons); 
	pageContext.setAttribute("editor", editors);
	//pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	pageContext.setAttribute("hidden",hidden);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%> 

 <script language="javascript">
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
 </script>
<script language="javascript">	
	function add() {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/order/OrderAction.do?method=printClient&tp=rep&"
		+ getAlldata(document.all.tableform);
	
	};  
	
	function addClient(obj) {
	
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/order/CustomOrderAction.do?method=forwardClient&tp=cusRep&ictnm=" + $("input[name=ictnm]").val();
	}; 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="客户查询" />
	<lemis:query action="/CustomOrderAction.do?method=queryClient&tp=rep"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/CustomOrderAction.do" headerMeta="header" hiddenMeta="hidden"
	 topic="客户明细" 
		mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
