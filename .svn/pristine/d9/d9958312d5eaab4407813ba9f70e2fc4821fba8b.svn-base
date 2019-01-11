<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >

<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("批量录入","add(document.forms[0])");
	buttons.put("返 回","back(document.forms[0])");
	buttons.put("关 闭","closeWindow(\"\")");
	
	List<Formatter> header=new ArrayList<Formatter>();
	/* header.add(new Formatter("stoprotype","商品类别")); */
	header.add(new Formatter("stoproid","商品代码"));
	header.add(new Formatter("stoproname","商品名称"));
/* 	header.add(new Formatter("stoprodsc","描述")); */
	/* header.add(new Formatter("stoproprice","单价",TagConstants.DF_LEFT,TagConstants.DT_MONEY)); */
	header.add(new Formatter("stoamount","数量"));
	/* header.add(new Formatter("stoamountun","数量单位")); */
	header.add(new Formatter("storemark","备注"));
	header.add(new Formatter("isrepair","是否维修机"));
	
	
	
	List<Editor> batchInput = new ArrayList<Editor>();
	/* batchInput.add(new Editor("text","stoprotype","商品类别","true")); */
	batchInput.add(new Editor("text","stoproid","商品代码","true"));
	batchInput.add(new Editor("text","stoproname","商品名称","true"));
/* 	batchInput.add(new Editor("text","stoprodsc","描述","true")); */
/* 	batchInput.add(new Editor("money","stoproprice","单价","true")); */
	batchInput.add(new Editor("text","stoamount","数量","true"));
	/* batchInput.add(new Editor("text","stoamountun","数量单位","true")); */
	batchInput.add(new Editor("text","storemark","备注"));
	batchInput.add(new Editor("text","isrepair","是否维修机"));
	
	/* Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stoprotype", "商品类别编号");
	hidden.put("folno","订单号");
	hidden.put("dgnlid","左定制机型号");
	hidden.put("dgnrid","右定制机型号"); */

	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	String gctnm = (String)request.getSession().getAttribute("gctnm");
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/Globals.js"></script>
<%@ page import="org.radf.login.dto.LoginDTO"%>

<script language="javascript">
$(document).ready(function(e) {
	var grCli="";
    grCli=<%=dto.getBsc001()%>;
    if(grCli=="1501000000")
    {                                                            /*grCliList*/
    	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	    $("input[name=gctnm]").autocomplete(shops,{
		max:10,
		matchContains:true,
		formatItem:function(data,i,max){
			return data[0];
			}
		});
	
		$("input[name=gctnm]").result(function(event,data,formatted){
		if(data){
			var gid = data[0].substring(0,data[0].indexOf("="));
			var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
			$("input[name=gctnm]").val(gnm);
			$(this).parent().next().find("input").val(gid);
			}
		});
    }
    else
    {
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=gctnm]").attr("readonly","true");
    }


	
	$("input[name=chk][value=1]").attr("checked",true);
	
<%-- 	var classes="<%=session.getServletContext().getAttribute("classesList")%>".replace("{","").replace("}","").split(", ");
	$("input[name=stoprotype]").autocomplete(classes,{
		max : 10,
		matchContains : true,
		formatItem: function(data, i, max) {
			return data[0].substring(0,data[0].indexOf("!"));
		}
	});
	
	
	$("input[name=stoprotype]").result(function(event, data, formatted) {
		if (data){
			var pcl = data[0].substr(0,data[0].length-1);
			var id = $(this).parent().find("input").attr("id");
			var suffix = id.substr(10);
			$("#stoprotype" + suffix).val(pcl);
		}
	}); --%>
	function chk(e) {
		var id = $(e.target).attr("id");  //拿到控件id值id='fdtpid_row1'
		var suffix = id.substr(12); 
		var suffix2 = id.substr(8); 
		var fdtid=$("#stoproid" + suffix2).val();
        if(fdtid!='')
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",true);
        }
        else
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",false);
        }
			
	};
	$("input[name=stoproid]").bind("blur",function(e){
		chk(e);
	});
/* 	 url:'http://localhost:8080/huiermis/product/ProductAction.do?method=queryEMPro', */
	
	//$("input[name=stoproid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=store",
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
								var id = $(this).parent().find("input").attr("id");
									var suffix = id.substr(8);
									$("#stoproid" + suffix).val(pid);
									$("#stoproname" + suffix).val(pnm);
									
							}
						});
					}
			});
	//});
	
});	
/* 	$("input[name=stoproid]").result(function(event, data, formatted) {
		if (data){
			var pid = data[0].substring(0,data[0].indexOf("="));
			var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
			var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(8);
				$("#stoproid" + suffix).val(pid);
				$("#stoproname" + suffix).val(pnm);
		}
	});
 */
	
	function startRequest() {
		$.getJSON("/huiermis/product/ProductAction.do?method=queryEMPro",
				createQueryString(),function(data) {	
				$("input[name=stoproid]").autocomplete(data,{
					max : 10,
					matchContains : true,
					formatItem: function(data, i, max) {
						return data.proid;
					}
				});

			});
	}
	
	function createQueryString() {
		/* var eID = $(e.target).val(); */
		/* var pclid=$(pid).val(); */
		var queryString = {
			PclId : $("input[name=stoproid]").val()
		};
		return queryString;
	};
	

function add(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	t = preCheckForBatch();//对必录项校验
	if (!t) {
		return t;
	}
	
	/*window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/store/InStoreAction.do?method=add&"
	+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
	*/ 
	obj.action = '<html:rewrite href="/huiermis/store/InStoreAction.do?method=add1&"/>' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
	obj.submit(); 

};

function query(obj) {
	
	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/store/InStoreAction.do?method=addQuery&"
	+ getAlldata(document.all.tableform);
};
function back(obj){
	obj.action = '<html:rewrite page="/InStoreAction.do?method=queryStorage"/>';
	obj.submit();
};
$(document).ready(function() {
	if($("input[name=gctnm]").val()==""||$("input[name=gctnm]").val()==null){
		$("input[name=gctnm]").val("<%=gctnm%>");
	}
})	
</script>






<lemis:base />
<lemis:body>
<lemis:editorlayout />
	<lemis:title title="商品入库信息" />

<%-- 	经办机构：<lemis:operorg />
	 <lemis:query action="/InStoreAction.do?method=addQuery"
		editorMeta="editor" topic="查询条件" /> --%>
			
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/InStoreAction.do" method="POST">
		    <tr>
<%-- 		    <td align="left"> <div class="alignCon"><lemis:texteditor property="stoproname" label="商品名称" required="true"
					disable="false" maxlength="50" colspan="5" /> </div>    </td> --%>

				<%-- 
				<td>所属团体</td>
				<td><lemis:operorg/></td>
				--%>
				
				<lemis:texteditor property="gctnm" label="所属团体" disable="false" required="true"></lemis:texteditor>
				<html:hidden property="stogrcliid"/>
				
				<%--  <td>经办机构</td>
				<td><lemis:operorg /></td>  --%>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>


		<lemis:table topic="商品明细录入" action="/InStoreAction.do?method=add1"
			 headerMeta="header" orderResult="false"
			mode="checkbox" batchInputMeta="batchInput" 
			pilot="true" />

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom /> 
</lemis:body>
</html>