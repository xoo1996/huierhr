<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("保 存","saveData(document.forms[0])");
    buttons.put("重 置","document.forms[0].reset();");
    buttons.put("关 闭","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<script language="javascript">
 function lgvag() {
		
		var l1 = $("input[name=lg250]").val()==""?0:$("input[name=lg250]").val();
		var l2 = $("input[name=lg500]").val()==""?0:$("input[name=lg500]").val();
		var l3 = $("input[name=lg1000]").val()==""?0:$("input[name=lg1000]").val();
		var l4 = $("input[name=lg2000]").val()==""?0:$("input[name=lg2000]").val();
		var l5 = $("input[name=lg4000]").val()==""?0:$("input[name=lg4000]").val();
     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lgavg]").val(lvag.toFixed(2));
	};
 function lqvag() {
		
		var l1 = $("input[name=lq250]").val()==""?0:$("input[name=lq250]").val();
		var l2 = $("input[name=lq500]").val()==""?0:$("input[name=lq500]").val();
		var l3 = $("input[name=lq1000]").val()==""?0:$("input[name=lq1000]").val();
		var l4 = $("input[name=lq2000]").val()==""?0:$("input[name=lq2000]").val();
		var l5 = $("input[name=lq4000]").val()==""?0:$("input[name=lq4000]").val();
     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
      var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lqavg]").val(lvag.toFixed(2));
	};
 function rgvag() {
		
		var l1 = $("input[name=rg250]").val()==""?0:$("input[name=rg250]").val();
		var l2 = $("input[name=rg500]").val()==""?0:$("input[name=rg500]").val();
		var l3 = $("input[name=rg1000]").val()==""?0:$("input[name=rg1000]").val();
		var l4 = $("input[name=rg2000]").val()==""?0:$("input[name=rg2000]").val();
		var l5 = $("input[name=rg4000]").val()==""?0:$("input[name=rg4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rgavg]").val(lvag.toFixed(2));
	};
 function rqvag() {
		
		var l1 = $("input[name=rq250]").val()==""?0:$("input[name=rq250]").val();
		var l2 = $("input[name=rq500]").val()==""?0:$("input[name=rq500]").val();
		var l3 = $("input[name=rq1000]").val()==""?0:$("input[name=rq1000]").val();
		var l4 = $("input[name=rq2000]").val()==""?0:$("input[name=rq2000]").val();
		var l5 = $("input[name=rq4000]").val()==""?0:$("input[name=rq4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rqavg]").val(lvag.toFixed(2));
	};
	</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="用户复诊声场数据新增" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=addSCSC" method="POST">
		     <html:hidden property="ictgctid" />  
			<tr>
			
			     <lemis:texteditor property="ictid" label="用户编号" required="false"
					disable="true" />
				<lemis:texteditor property="ictnm" label="用户姓名" required="false"
					disable="true" />
					<lemis:texteditor property="gctnm" label="所属团体" required="false"
					disable="true" />
			</tr>
			
			<tr>
			   
				<lemis:formateditor mask="date" property="fctcdt" label="复诊日期"
					required="true" disable="false" />
				
			</tr>
			<tr>
				<lemis:texteditor property="fctnt" label="备注"
					required="false" disable="false" colspan="20"/>
			</tr>
     <lemis:tabletitle title="左耳声场" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg500" label="500" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg750" label="750" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg1000" label="1000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg1500" label="1500" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg2000" label="2000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg3000" label="3000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg4000" label="4000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lg6000" label="6000" required="false"
						disable="false" onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="平均" required="false"
						disable="false" onclick="lgvag()" />
				</tr>
			</table>
			<lemis:tabletitle title="右耳声场" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg500" label="500" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg750" label="750" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg1500" label="1500" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg3000" label="3000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rg6000" label="6000" required="false"
						disable="false" onblur="rgvag()" />
					<lemis:texteditor property="rgavg" label="平均" required="false"
						disable="false" onclick="rgvag()" />
				</tr>
			</table>
			
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

