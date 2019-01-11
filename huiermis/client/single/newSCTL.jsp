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
	<lemis:title title="用户复诊听力数据新增" />
	<lemis:tabletitle title="用户基本信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=addSCTL" method="POST">
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
			

     <lemis:tabletitle title="左耳骨导(掩蔽请打钩)" />
			<table class="tableinput">
				<tr>
					<td width="90px"><input type="checkbox" name="lgyb250" value="1" >250</td>
					<td colspan="1" width="35px"><input type="text" name="lg250" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb500" value="1" >500</td>
					<td colspan="1" width="35px"><input type="text" name="lg500" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb750" value="1" >750</td>
					<td colspan="1" width="35px"><input type="text" name="lg750" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb1000" value="1" >1000</td>
					<td colspan="1" width="35px"><input type="text" name="lg1000" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb1500" value="1" >1500</td>
					<td colspan="1" width="35px"><input type="text" name="lg1500" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
				    <td width="90px"><input type="checkbox" name="lgyb2000" value="1" >2000</td>
					<td colspan="1" width="35px"><input type="text" name="lg2000" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb3000" value="1" >3000</td>
					<td colspan="1" width="35px"><input type="text" name="lg3000" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb4000" value="1" >4000</td>
					<td colspan="1" width="35px"><input type="text" name="lg4000" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lgyb6000" value="1" >6000</td>
					<td colspan="1" width="35px"><input type="text" name="lg6000" required="false" disable="false" value="" onblur="lgvag()"  class="text"></td>
					<lemis:texteditor property="lgavg" label="平均" required="false"
						disable="false" onclick="lgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="左耳气导(掩蔽请打钩)" />
			<table class="tableinput">
				<tr>
					<td width="90px"><input type="checkbox" name="lqyb250" value="1" >250</td>
					<td colspan="1" width="35px"><input type="text" name="lq250" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb500" value="1" >500</td>
					<td colspan="1" width="35px"><input type="text" name="lq500" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb750" value="1" >750</td>
					<td colspan="1" width="35px"><input type="text" name="lq750" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb1000" value="1" >1000</td>
					<td colspan="1" width="35px"><input type="text" name="lq1000" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb1500" value="1" >1500</td>
					<td colspan="1" width="35px"><input type="text" name="lq1500" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
				    <td width="90px"><input type="checkbox" name="lqyb2000" value="1" >2000</td>
					<td colspan="1" width="35px"><input type="text" name="lq2000" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb3000" value="1" >3000</td>
					<td colspan="1" width="35px"><input type="text" name="lq3000" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb4000" value="1" >4000</td>
					<td colspan="1" width="35px"><input type="text" name="lq4000" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="lqyb6000" value="1" >6000</td>
					<td colspan="1" width="35px"><input type="text" name="lq6000" required="false" disable="false" value="" onblur="lqvag()"  class="text"></td>
					<lemis:texteditor property="lqavg" label="平均" required="false"
						disable="false" onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导(掩蔽请打钩)" />
			<table class="tableinput">
				<tr>
					<td width="90px"><input type="checkbox" name="rgyb250" value="1" >250</td>
					<td colspan="1" width="35px"><input type="text" name="rg250" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb500" value="1" >500</td>
					<td colspan="1" width="35px"><input type="text" name="rg500" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb750" value="1" >750</td>
					<td colspan="1" width="35px"><input type="text" name="rg750" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb1000" value="1" >1000</td>
					<td colspan="1" width="35px"><input type="text" name="rg1000" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb1500" value="1" >1500</td>
					<td colspan="1" width="35px"><input type="text" name="rg1500" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
				    <td width="90px"><input type="checkbox" name="rgyb2000" value="1" >2000</td>
					<td colspan="1" width="35px"><input type="text" name="rg2000" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rg3000yb" value="1" >3000</td>
					<td colspan="1" width="35px"><input type="text" name="rg3000" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb4000" value="1" >4000</td>
					<td colspan="1" width="35px"><input type="text" name="rg4000" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rgyb6000" value="1" >6000</td>
					<td colspan="1" width="35px"><input type="text" name="rg6000" required="false" disable="false" value="" onblur="rgvag()"  class="text"></td>
					<lemis:texteditor property="rgavg" label="平均" required="false"
						disable="false" onclick="rgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳气导(掩蔽请打钩)" />
			<table class="tableinput">
				<tr>
						<td width="90px"><input type="checkbox" name="rqyb250" value="1" >250</td>
					<td colspan="1" width="35px"><input type="text" name="rq250" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb500" value="1" >500</td>
					<td colspan="1" width="35px"><input type="text" name="rq500" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb750" value="1" >750</td>
					<td colspan="1" width="35px"><input type="text" name="rq750" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb1000" value="1" >1000</td>
					<td colspan="1" width="35px"><input type="text" name="rq1000" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb1500" value="1" >1500</td>
					<td colspan="1" width="35px"><input type="text" name="rq1500" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
				    <td width="90px"><input type="checkbox" name="rqyb2000" value="1" >2000</td>
					<td colspan="1" width="35px"><input type="text" name="rq2000" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb3000" value="1" >3000</td>
					<td colspan="1" width="35px"><input type="text" name="rq3000" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb4000" value="1" >4000</td>
					<td colspan="1" width="35px"><input type="text" name="rq4000" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
					<td width="90px"><input type="checkbox" name="rqyb6000" value="1" >6000</td>
					<td colspan="1" width="35px"><input type="text" name="rq6000" required="false" disable="false" value="" onblur="rqvag()"  class="text"></td>
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

