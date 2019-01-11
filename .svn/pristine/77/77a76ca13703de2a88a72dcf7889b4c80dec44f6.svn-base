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
	/*buttons.put("新增","add(document.all.tableform)");
	buttons.put("惠耳打印","huier_print()");
	buttons.put("捷文打印","jiewen_print()");
	buttons.put("关 闭","closeWindow(\"\")");*/
	buttons.put("增加库存","storage_add(document.all.tableform)");
	buttons.put("库存明细","storage_detail(document.all.tableform)");
	
	List<Button> buttons1=new ArrayList<Button>();
	buttons1.add(new Button("z","库存修正（总部）","sto050001","storage_add(document.all.tableform)"));
	buttons1.add(new Button("zs","库存修正（加盟店）","jmd000002","storage_add(document.all.tableform)"));
	buttons1.add(new Button("kz","库存明细（总部）","sto050002","storage_detail(document.all.tableform)"));
	buttons1.add(new Button("k","库存明细","jmd000001","storage_detail(document.all.tableform)"));
	buttons1.add(new Button("pk","盘库","jmd000003","panku(document.all.tableform)"));

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("gctnm","所属团体"));
	header.add(new Formatter("stocllar","商品大类"));
	header.add(new Formatter("laramount","大类库存量"));
	header.add(new Formatter("stoclmid","商品中类"));
	header.add(new Formatter("midamount","中类库存量"));
	header.add(new Formatter("stoclsam","商品小类"));
	header.add(new Formatter("samamount","小类库存量"));
	header.add(new Formatter("stoproid","商品代码"));
	header.add(new Formatter("stoproname","商品名称"));
	header.add(new Formatter("amount","库存量"));
	header.add(new Formatter("isrepair","维修机"));
	//header.add(new Formatter("stodate","日期"));
	/* header.add(new Formatter("stoamountun","数量单位"));
	header.add(new Formatter("stotype","动作"));
	header.add(new Formatter("stodate","日期"));
	header.add(new Formatter("storemark","备注")); */
	
	
	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "stoproid", "年","true"));
	//editors.add(new Editor("text", "stoproname", "月","true")); 
	editors.add(new Editor("text","gctnm","所属团体"));
	editors.add(new Editor("text","stocllar","商品大类"));
	editors.add(new Editor("text","stoclmid","商品中类"));
	editors.add(new Editor("text","stoclsam","商品小类"));
	editors.add(new Editor("text","stoproid","商品代码"));
	editors.add(new Editor("text","stoproname","商品名称"));
	editors.add(new Editor("text","isrepair","是否维修机"));
	//editors.add(new Editor("date","start","日期从"));
	//editors.add(new Editor("date","end","到"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctnm", "所属团体");
	hidden.put("stoproid","商品代码");
	hidden.put("stoproname","商品名称");
	hidden.put("storemark","备注");
	hidden.put("isrepair","维修机");
	pageContext.setAttribute("hidden", hidden);


	pageContext.setAttribute("button", buttons1);
	pageContext.setAttribute("editor", editors);
	//pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<script language="javascript">	
$(document).ready(function(e) {
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
	
	//$("input[name=stoproid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=all",
			 dataType:'json',
			 error:function(){
			   alert('获取数据错误！');
			 },
			 success:function(data){
						$("input[name=stoproid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=stoproid]").result(function(event, data, formatted) {
							if (data){
								var pid = data.proid;
								var pnm = data.proname;
								$("input[name=stoproid]").val(pid);
								$("input[name=stoproname]").val(pnm);
									
							}
						});
					}
			});
	//});
});

function storage_detail(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=storageDetail&"/>' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
	obj.submit();
}
function storage_add(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=inDetail1&"/>' + getAlldata(obj);
	obj.submit();
}
function panku(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=panku&"/>' + getAlldata(obj);
	obj.submit();
}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="库存量查询" />
	<lemis:query action="/InStoreAction.do?method=queryStorageJmd"
		editorMeta="editor" topic="查询条件" />
	<lemis:table action="/InStoreAction.do" headerMeta="header"
	 topic="库存明细" 
		mode="radio" hiddenMeta="hidden"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
