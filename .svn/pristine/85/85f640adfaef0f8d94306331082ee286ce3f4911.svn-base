<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.lbs.cp.taglib.Formatter"%>
<%@page import="org.radf.plat.taglib.Editor"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","commit(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("amotype","费用类型"));
    header.add(new Formatter("money","总额"));
    header.add(new Formatter("amomoney","月摊销金额"));
    header.add(new Formatter("feelong","摊销月份"));
    header.add(new Formatter("feestart","开始时间"));
    header.add(new Formatter("feeend","结束时间"));
    header.add(new Formatter("note","备注"));
    
    List<Editor> batchInput = new ArrayList<Editor>();
    batchInput.add(new Editor("yyyy-mm", "feestart", "开始时间", "true"));
    batchInput.add(new Editor("nnn", "feelong", "摊销月份", "true"));
    batchInput.add(new Editor("yyyy-mm", "feeend", "结束时间", "true"));
    batchInput.add(new Editor("text", "amotype", "费用类型", "true"));
    batchInput.add(new Editor("-nnnnnnnnnn.nn", "money", "总额", "true"));
    batchInput.add(new Editor("-nnnnnnnnnn.nn", "amomoney", "月摊销金额", "true"));
    batchInput.add(new Editor("text", "note", "备注", "false"));
	
    pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready( function() {
       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=feegctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=feegctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=feegctid]").val(gid);
				$("input[name=feegctname]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});

		$("textarea[name=feestart]").bind("blur", function(e) {
				diffNum = $(e.target).val();
				var id = $(e.target).attr("id");//获取id此处的id为ivtpamnt_rowx
				var suffix = id.substr(8);//id后的位置为8的后缀，id从0开始计算，此处suffix为_rowx
				var startYear,startMonth;
				var endYear,endMonth;
				var flong = parseInt($("#feelong" + suffix).val());
				if (($("#feestart" + suffix).val() != "")&&($("#feelong" + suffix).val() != "")) {
					startYear = parseInt($("#feestart" + suffix).val().substring(0,4));
					startMonth = parseInt($("#feestart" + suffix).val().substring(5,7));
					startMonth = parseInt($("#feestart" + suffix).val().substring(5));
					if(startMonth == 0){
						startMonth = parseInt($("#feestart" + suffix).val().substring(6));
					}
					endYear = startYear+parseInt((startMonth+flong)/12);
					endMonth = (startMonth+flong)%12-1;
					if(endMonth==0){
						endYear = endYear - 1;
						endMonth = 12;
					}
					if(endMonth==-1){
						endYear = endYear - 1;
						endMonth = 11;
					}
					 if(endMonth<10){
						endMonth = 0+endMonth.toString();
					} 
					$("#feeend" + suffix).val(endYear+"-"+endMonth);
				}
				if (($("#money" + suffix).val() != "")&&($("#feelong" + suffix).val() != "")) {
					var sum = parseInt($("#money" + suffix).val());
					var amo = sum/flong;
					$("#amomoney" + suffix).val(amo.toFixed(2));
				}
			});  
		$("textarea[name=feelong]").bind("blur", function(e) {
			diffNum = $(e.target).val();
			var id = $(e.target).attr("id");//获取id此处的id为ivtpamnt_rowx
			var suffix = id.substr(7);//id后的位置为8的后缀，id从0开始计算，此处suffix为_rowx
			var startYear,startMonth;
			var endYear,endMonth;
			var flong = parseInt($("#feelong" + suffix).val());
			if (($("#feestart" + suffix).val() != "")&&($("#feelong" + suffix).val() != "")) {
				startYear = parseInt($("#feestart" + suffix).val().substring(0,4));
				startMonth = parseInt($("#feestart" + suffix).val().substring(5));
				if(startMonth == 0){
					startMonth = parseInt($("#feestart" + suffix).val().substring(6));
				}
				endYear = startYear+parseInt((startMonth+flong)/12);
				endMonth = (startMonth+flong)%12-1;
				if(endMonth==0){
					endYear = endYear - 1;
					endMonth = 12;
				}
				if(endMonth==-1){
					endYear = endYear - 1;
					endMonth = 11;
				}
				 if(endMonth<10){
					endMonth = 0+endMonth.toString();
				} 
				$("#feeend" + suffix).val(endYear+"-"+endMonth);
			}
			if (($("#money" + suffix).val() != "")&&($("#feelong" + suffix).val() != "")) {
				var sum = parseInt($("#money" + suffix).val());
				var amo = sum/flong;
				$("#amomoney" + suffix).val(amo.toFixed(2));
			}
			
		});  
	});
	function commit(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		var t = delObj("chk");
		if(!t){
			return t;
		}
		t = preCheckForBatch();
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/FeeAction.do?method=setAmortize&"/>'+getAlldata(document.all.tableform);
	if (confirm("确实要录入订单并提交吗？")) {
		obj.submit();
	}
};
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="摊销费用录入" />
	<lemis:tabletitle title="录入摊销费用" />
	<table class="tableInput">
	<lemis:editorlayout/>
	<html:form action="/FeeAction.do" method="POST">
		<tr>
		<lemis:texteditor property="feegctid" label="客户代码" disable="false"
					required="true" />
				<lemis:texteditor property="feegctname" label="客户名称" disable="false"
					required="true" />
		</tr>
	</html:form>
    </table>
	<lemis:table topic="录入费用" action="/FeeAction.do" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
