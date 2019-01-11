<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%
 Map<String,String> buttons=new LinkedHashMap<String,String>();
 buttons.put("保存修改","saveData(document.forms[0])");   
 buttons.put("关 闭","closeWindow(\"\")");
 buttons.put("返回","history.back()");
 pageContext.setAttribute("button", buttons);
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/huiermis/js/CityJson.js"></script>
<script src="/huiermis/js/DistrictJson.js"></script>
<script src="/huiermis/js/ProJson.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>
<script language="javascript">


$(document).ready(function(){	

		
		var l1 = $("input[name=lg250]").val()==""?0:$("input[name=lg250]").val();
		var l2 = $("input[name=lg500]").val()==""?0:$("input[name=lg500]").val();
		var l3 = $("input[name=lg1000]").val()==""?0:$("input[name=lg1000]").val();
		var l4 = $("input[name=lg2000]").val()==""?0:$("input[name=lg2000]").val();
		var l5 = $("input[name=lg4000]").val()==""?0:$("input[name=lg4000]").val();
     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lgavg]").val(lvag.toFixed(2));

		
		var l1 = $("input[name=lq250]").val()==""?0:$("input[name=lq250]").val();
		var l2 = $("input[name=lq500]").val()==""?0:$("input[name=lq500]").val();
		var l3 = $("input[name=lq1000]").val()==""?0:$("input[name=lq1000]").val();
		var l4 = $("input[name=lq2000]").val()==""?0:$("input[name=lq2000]").val();
		var l5 = $("input[name=lq4000]").val()==""?0:$("input[name=lq4000]").val();
     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
      var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lqavg]").val(lvag.toFixed(2));

		var l1 = $("input[name=rg250]").val()==""?0:$("input[name=rg250]").val();
		var l2 = $("input[name=rg500]").val()==""?0:$("input[name=rg500]").val();
		var l3 = $("input[name=rg1000]").val()==""?0:$("input[name=rg1000]").val();
		var l4 = $("input[name=rg2000]").val()==""?0:$("input[name=rg2000]").val();
		var l5 = $("input[name=rg4000]").val()==""?0:$("input[name=rg4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rgavg]").val(lvag.toFixed(2));

		
		var l1 = $("input[name=rq250]").val()==""?0:$("input[name=rq250]").val();
		var l2 = $("input[name=rq500]").val()==""?0:$("input[name=rq500]").val();
		var l3 = $("input[name=rq1000]").val()==""?0:$("input[name=rq1000]").val();
		var l4 = $("input[name=rq2000]").val()==""?0:$("input[name=rq2000]").val();
		var l5 = $("input[name=rq4000]").val()==""?0:$("input[name=rq4000]").val();
		//var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=rqavg]").val(lvag.toFixed(2));
});
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
 function lsvag() {
		
		var l1 = $("input[name=ls250]").val()==""?0:$("input[name=ls250]").val();
		var l2 = $("input[name=ls500]").val()==""?0:$("input[name=ls500]").val();
		var l3 = $("input[name=ls1000]").val()==""?0:$("input[name=ls1000]").val();
		var l4 = $("input[name=ls2000]").val()==""?0:$("input[name=ls2000]").val();
		var l5 = $("input[name=ls4000]").val()==""?0:$("input[name=ls4000]").val();
     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
		$("input[name=lsavg]").val(lvag.toFixed(2));
	};
	 function rsvag() {
			
			var l1 = $("input[name=rs250]").val()==""?0:$("input[name=rs250]").val();
			var l2 = $("input[name=rs500]").val()==""?0:$("input[name=rs500]").val();
			var l3 = $("input[name=rs1000]").val()==""?0:$("input[name=rs1000]").val();
			var l4 = $("input[name=rs2000]").val()==""?0:$("input[name=rs2000]").val();
			var l5 = $("input[name=rs4000]").val()==""?0:$("input[name=rs4000]").val();
	     //var lvag = (parseInt(l1) + parseInt(l2) + parseInt(l3) + parseInt(l4) + parseInt(l5))/5;
	     var lvag = (parseInt(l2) + parseInt(l3) + parseInt(l4))/3;
			$("input[name=rsavg]").val(lvag.toFixed(2));
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
	<lemis:title title="用户复诊听力数据详情" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=addSCTL&type=update" method="POST">
		   <html:hidden property="fcttlid" />  
		   <html:hidden property="ictgctid" />  
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
			   
				<lemis:formateditor mask="date" property="fctcdt" format="true"  label="复诊日期"
					required="true" disable="false" />
			</tr>
			<tr>
				<lemis:texteditor property="fctnt" label="备注"
					required="false" disable="false" colspan="20"/>
			</tr>
    
			<lemis:tabletitle title="左耳骨导(掩蔽打钩)" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
						disable="false" onblur="lgvag()"/>
					<td width="5px"><html:checkbox  property="lgyb250"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg500" label="500" required="false"
						disable="false" onblur="lgvag()" colspan="1"/>
					<td width="5px"><html:checkbox  property="lgyb500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg750" label="750" required="false"
						disable="false" onblur="lgvag()" />
					<td width="5px"><html:checkbox  property="lgyb750"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg1000" label="1000" required="false"
						disable="false" onblur="lgvag()" />
					<td width="10px"><html:checkbox  property="lgyb1000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg1500" label="1500" required="false"
						disable="false" onblur="lgvag()" />
					<td width="10px"><html:checkbox  property="lgyb1500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg2000" label="2000" required="false"
						disable="false" onblur="lgvag()" />
					<td width="10px"><html:checkbox  property="lgyb2000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg3000" label="3000" required="false"
						disable="false" onblur="lgvag()" />
				    <td width="10px"><html:checkbox  property="lgyb3000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg4000" label="4000" required="false"
						disable="false" onblur="lgvag()" />
					<td width="10px"><html:checkbox  property="lgyb4000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lg6000" label="6000" required="false"
						disable="false" onblur="lgvag()" />
					<td width="10px"><html:checkbox  property="lgyb6000"  value="1" disabled="false" /></td>
					<lemis:texteditor property="lgavg" label="平均" required="false"
						disable="false" onclick="lgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="左耳气导(掩蔽打钩)" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						disable="false" onblur="lqvag()"/>
					<td width="5px"><html:checkbox  property="lqyb250"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq500" label="500" required="false"
						disable="false" onblur="lqvag()" colspan="1"/>
					<td width="5px"><html:checkbox  property="lqyb500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq750" label="750" required="false"
						disable="false" onblur="lqvag()" />
					<td width="5px"><html:checkbox  property="lqyb750"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq1000" label="1000" required="false"
						disable="false" onblur="lqvag()" />
					<td width="10px"><html:checkbox  property="lqyb1000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq1500" label="1500" required="false"
						disable="false" onblur="lqvag()" />
					<td width="10px"><html:checkbox  property="lqyb1500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq2000" label="2000" required="false"
						disable="false" onblur="lqvag()" />
					<td width="10px"><html:checkbox  property="lqyb2000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq3000" label="3000" required="false"
						disable="false" onblur="lqvag()" />
				    <td width="10px"><html:checkbox  property="lqyb3000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq4000" label="4000" required="false"
						disable="false" onblur="lqvag()" />
					<td width="10px"><html:checkbox  property="lqyb4000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="lq6000" label="6000" required="false"
						disable="false" onblur="lqvag()" />
					<td width="10px"><html:checkbox  property="lqyb6000"  value="1" disabled="false" /></td>
					<lemis:texteditor property="lqavg" label="平均" required="false"
						disable="false" onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导(掩蔽打钩)" />
			<table class="tableinput">
			<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						disable="false" onblur="rgvag()"/>
					<td width="5px"><html:checkbox  property="rgyb250"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg500" label="500" required="false"
						disable="false" onblur="rgvag()" colspan="1"/>
					<td width="5px"><html:checkbox  property="rgyb500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg750" label="750" required="false"
						disable="false" onblur="rgvag()" />
					<td width="5px"><html:checkbox  property="rgyb750"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg1000" label="1000" required="false"
						disable="false" onblur="rgvag()" />
					<td width="10px"><html:checkbox  property="rgyb1000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg1500" label="1500" required="false"
						disable="false" onblur="rgvag()" />
					<td width="10px"><html:checkbox  property="rgyb1500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg2000" label="2000" required="false"
						disable="false" onblur="rgvag()" />
					<td width="10px"><html:checkbox  property="rgyb2000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg3000" label="3000" required="false"
						disable="false" onblur="rgvag()" />
				    <td width="10px"><html:checkbox  property="rgyb3000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg4000" label="4000" required="false"
						disable="false" onblur="rgvag()" />
					<td width="10px"><html:checkbox  property="rgyb4000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rg6000" label="6000" required="false"
						disable="false" onblur="rgvag()" />
					<td width="10px"><html:checkbox  property="rgyb6000"  value="1" disabled="false" /></td>
					<lemis:texteditor property="rgavg" label="平均" required="false"
						disable="false" onclick="rgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳气导(掩蔽打钩)" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						disable="false" onblur="rqvag()"/>
					<td width="5px"><html:checkbox  property="rqyb250"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq500" label="500" required="false"
						disable="false" onblur="rqvag()" colspan="1"/>
					<td width="5px"><html:checkbox  property="rqyb500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq750" label="750" required="false"
						disable="false" onblur="rqvag()" />
					<td width="5px"><html:checkbox  property="rqyb750"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq1000" label="1000" required="false"
						disable="false" onblur="rqvag()" />
					<td width="10px"><html:checkbox  property="rqyb1000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq1500" label="1500" required="false"
						disable="false" onblur="rqvag()" />
					<td width="10px"><html:checkbox  property="rqyb1500"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq2000" label="2000" required="false"
						disable="false" onblur="rqvag()" />
					<td width="10px"><html:checkbox  property="rqyb2000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq3000" label="3000" required="false"
						disable="false" onblur="rqvag()" />
				    <td width="10px"><html:checkbox  property="rqyb3000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq4000" label="4000" required="false"
						disable="false" onblur="rqvag()" />
					<td width="10px"><html:checkbox  property="rqyb4000"  value="1" disabled="false" /></td>
						
					<lemis:texteditor property="rq6000" label="6000" required="false"
						disable="false" onblur="rqvag()" />
					<td width="10px"><html:checkbox  property="rqyb6000"  value="1" disabled="false" /></td>
					<lemis:texteditor property="rqavg" label="平均" required="false"
						disable="false" onclick="rqvag()" />
				</tr>
			</table>
			 <lemis:tabletitle title="左耳声场" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="ls250" label="250" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls500" label="500" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls750" label="750" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls1000" label="1000" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls1500" label="1500" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls2000" label="2000" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls3000" label="3000" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls4000" label="4000" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="ls6000" label="6000" required="false"
						disable="false" onblur="lsvag()" />
					<lemis:texteditor property="lsavg" label="平均" required="false"
						disable="false" onclick="lsvag()" />
				</tr>
			</table>
			<lemis:tabletitle title="右耳声场" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rs250" label="250" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs500" label="500" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs750" label="750" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs1000" label="1000" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs1500" label="1500" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs2000" label="2000" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs3000" label="3000" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs4000" label="4000" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rs6000" label="6000" required="false"
						disable="false" onblur="rsvag()" />
					<lemis:texteditor property="rsavg" label="平均" required="false"
						disable="false" onclick="rsvag()" />
				</tr>
			</table>
			 <lemis:tabletitle title="言语评估测试结果" />
			<table class="tableinput">
	     	    <tr>
					<lemis:texteditor property="fcypgl" label="给声强度左" required="false"
						disable="false" />
						<td width="5px">(db)</td>
					<lemis:texteditor property="fcypgr" label="给声强度右" required="false"
						disable="false"  />
						<td width="5px">(db)</td>
					<lemis:texteditor property="fcypwzt" label="未助听" required="false"
						disable="false" />
						<td width="5px">%</td>
					<lemis:texteditor property="fcypdzl" label="单耳助听左" required="false"
						disable="false"  />
						<td width="5px">%</td>
					<lemis:texteditor property="fcypdzr" label="单耳助听右" required="false"
						disable="false" />
						<td width="5px">%</td>
					<lemis:texteditor property="fcypsz" label="双耳助听" required="false"
						disable="false" 
						/>
						<td width="5px">%</td>
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

