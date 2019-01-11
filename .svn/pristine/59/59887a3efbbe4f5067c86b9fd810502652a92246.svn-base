<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String[] gumo = (String[]) request.getSession().getAttribute("gumo");
	String[] jiancha = (String[]) request.getSession().getAttribute("jiancha");
	String[] chuandao = (String[]) request.getSession().getAttribute("chuandao");
	String[] ganyin = (String[]) request.getSession().getAttribute("ganyin");
	String[] chuli = (String[]) request.getSession().getAttribute("chuli");
	String ictpro = (String) request.getSession().getAttribute("ictpro");
	String ictcity = (String) request.getSession().getAttribute("ictcity");
	String ictcounty = (String) request.getSession().getAttribute("ictcounty");
	Map<String, String> buttons = new LinkedHashMap<String, String>();
//	buttons.put("保 存", "saveData(document.forms[0])");
	buttons.put("返 回", "history.back()");
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
    
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
	function createQueryString(e) {
		var pID = $(e.target).val();
		var queryString = {
			productID : pID
		};
		return queryString;
	};
	function startRequestLeft(e) {
		$.getJSON("/huiermis/order/OrderDetailAction.do?method=queryPdtName",
				createQueryString(e), function(data) {
					$('input[name=dgnldprc]').val(data[0].price);
					$('input[name=dgnldct]').val(1.0);
				});	
	};
	function startRequestRight(e) {
		$.getJSON("/huiermis/order/OrderDetailAction.do?method=queryPdtName",
				createQueryString(e), function(data) {
					$('input[name=dgnrdprc]').val(data[0].price);
					$('input[name=dgnrdct]').val(1.0);
				});	
	};
	$(document).ready( function() {
		$('#dgnlid').change(function(e) {
			startRequestLeft(e);
		});
		$('#dgnrid').change(function(e) {
			startRequestRight(e);
		});
	});
</script>
<script language="javascript">
	//复选框
	$(document).ready(function() {
		var gumo = new Array(); 
		var jiancha = new Array();
		var chuandao = new Array();
		var ganyin = new Array();
		var chuli = new Array();
		<%if (gumo != null && gumo.length > 0) {
				for (int i = 0; i < gumo.length; i++) {
					out.println("gumo[" + i + "] = '" + gumo[i] + "'");
				}
			}
		if (jiancha != null && jiancha.length > 0) {
			for (int i = 0; i < jiancha.length; i++) {
				out.println("jiancha[" + i + "] = '" + jiancha[i] + "'");
			}
		}
		if (chuandao != null && chuandao.length > 0) {
			for (int i = 0; i < chuandao.length; i++) {
				out.println("chuandao[" + i + "] = '" + chuandao[i] + "'");
			}
		}
		if (ganyin != null && ganyin.length > 0) {
			for (int i = 0; i < ganyin.length; i++) {
				out.println("ganyin[" + i + "] = '" + ganyin[i] + "'");
			}
		}
		if (chuli != null && chuli.length > 0) {
			for (int i = 0; i < chuli.length; i++) {
				out.println("chuli[" + i + "] = '" + chuli[i] + "'");
			}
		}
		%>
			 $.each(gumo,function(n,value) {
				 $("input[name='gumo'][value='"+value+"']").attr("checked","checked");
				   });
			 $.each(jiancha,function(n,value) {
				 $("input[name='jiancha'][value='"+value+"']").attr("checked","checked");
				   });
			 $.each(chuandao,function(n,value) {
				 $("input[name='chuandao'][value='"+value+"']").attr("checked","checked");
				   });
			 $.each(ganyin,function(n,value) {
				 $("input[name='ganyin'][value='"+value+"']").attr("checked","checked");
				   });
			 $.each(chuli,function(n,value) {
				 $("input[name='chuli'][value='"+value+"']").attr("checked","checked");
				   });
			
	});
</script>
<script type="text/javascript">
 //省份三级联动js
        $(function () {
			
            $.each(province, function (k, p) { 
                var option = "<option value='" + p.ProID + ',' + p.ProName +"'>" + p.ProName + "</option>";
                $("#ictpro").append(option);
            });
             
            $("#ictpro").change(function () {
                var selValue = $(this).val().split(","); 
                $("#ictcity option:gt(0)").remove();
                 
                $.each(city, function (k, p) { 
                    if (p.ProID == selValue[0]) {
                        var option = "<option value='" + p.CityID + ',' + p.CityName +"'>" + p.CityName + "</option>";
                        $("#ictcity").append(option);
                    }
                });
                 
            });
             
            $("#ictcity").change(function () {
                var selValue = $(this).val().split(",");
                $("#ictcounty option:gt(0)").remove(); 

                $.each(District, function (k, p) {
                    if (p.CityID == selValue[0]) {
                        var option = "<option value='" + p.Id + ',' + p.DisName +"'>" + p.DisName + "</option>";
                        $("#ictcounty").append(option);
                    }
                }); 
            }); 
        });
    </script>
    <script language="javascript">
	$(document).ready(function(){	

		 $("#ictpro").val("<%=ictpro%>");
		 $("#ictpro").change();
		$("#ictcity").val("<%=ictcity%>");  
		 $("#ictcity").change();
		$("#ictcounty").val("<%=ictcounty%>");
	}); 
	
			</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="个人客户信息修改" />
	<lemis:tabletitle title="个人客户信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/SingleClientAction.do?method=showdetail"
			method="POST">
			<tr>
				<lemis:texteditor property="ictid" label="用户编号" required="true"
					disable="true" maxlength="20" />
				<lemis:texteditor property="ictnm" label="姓名" required="true"
					disable="true" maxlength="20" />
				<lemis:texteditor property="ictgctid" label="团体代码" disable="true"
					required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="ictgender" isSelect="false" label="性别" 
					redisplay="true" required="false" />
				<lemis:formateditor mask="date" property="ictbdt" label="出生日期" 
					format="true" required="false" disable="true" />
				<lemis:texteditor property="ictpnm" label="家长姓名" required="false"
					disable="true" maxlength="20" />
			</tr>
			<td><font color='#FF0000'>*</font>省份</td><td colspan="1">
			<select style='font-size:12px' name='ictpro' id='ictpro' class='select'  required='true' disabled="disabled"><option
						value='' selected> 请选择</option></select></td>
			<td><font color='#FF0000'>*</font>市</td><td colspan="1">
			<select style='font-size:12px' name='ictcity' id='ictcity' disabled="disabled"
					class='select'  required='true'><option
						value='' selected> 请选择</option></select></td>
			<td><font color='#FF0000'>*</font>县（区）</td><td colspan="1">
			<select style='font-size:12px' name='ictcounty' id='ictcounty' disabled="disabled"
					class='select'  required='true'><option
						value='' selected> 请选择</option></select></td>
			</tr>
			<tr>
				<lemis:texteditor property="ictdetailaddr" label="详细地址" required="false"
					disable="true" maxlength="80" />
				<lemis:texteditor property="ictphone" label="手机" disable="true"/>
				<lemis:texteditor property="ictlandline" label="固定电话" disable="true"  />
				</tr>
			<tr>
				<lemis:texteditor property="ictpcd" label="邮政编码" required="false"
					disable="true" maxlength="20" />
			
				<lemis:codelisteditor type="ictphis" isSelect="false" label="用过何种助听器"
					redisplay="true" required="true" />
		
				<lemis:texteditor property="ictnt" label="备注" disable="true"
					maxlength="100" />
							</tr>
			<tr>
				<lemis:texteditor property="ictsrc" label="来源" required="false"
					disable="true" />
				<td>经办人</td>
				<td><lemis:operator /></td>
				<td>经办机构</td>
				<td><lemis:operorg /></td>
				</tr>
			<tr>
				<td>经办日期</td>
				<td><lemis:operdate /></td>
				<lemis:codelisteditor type="ictfrom" label="来源" required="true" />
			</tr>
			
	<lemis:tabletitle title="左耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg500" label="500" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg750" label="750" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg1000" label="1000" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg1500" label="1500" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg2000" label="2000" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg3000" label="3000" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg4000" label="4000" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lg6000" label="6000" required="false"
						disable="true" onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="平均" required="false"
						disable="true" onclick="lgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="左耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq500" label="500" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq750" label="750" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1500" label="1500" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq3000" label="3000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq6000" label="6000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lqavg" label="平均" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg500" label="500" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg750" label="750" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg1500" label="1500" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg3000" label="3000" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rg6000" label="6000" required="false"
						disable="true" onblur="rgvag()" />
					<lemis:texteditor property="rgavg" label="平均" required="false"
						disable="true" onclick="rgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq500" label="500" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq750" label="750" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq6000" label="6000" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rqavg" label="平均" required="false"
						disable="true" style="border-width:1px;border-color=black"
						onclick="rqvag()" />
				</tr>
			</table>
				<!-- 2015.6.19 新增加客户详细信息-->
			<lemis:tabletitle title="询问病史" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="losetime" label="听力损失时间" required="false"  isSelect="false" />
					<lemis:texteditor property="lstnote"  label="备注" required="false"  disable="true" colspan="5"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="shoushushi" label="有无流脓或手术史" required="false"	 isSelect="false" />
					<lemis:texteditor property="sssnote" label="备注" required="false"	 disable="true" colspan="5"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="yichuanshi" label="耳聋家族或遗传病史" required="false"	isSelect="false"/>
					<lemis:texteditor property="ycsnote" label="备注" required="false" disable="true" colspan="5"/>
					</tr>
				<tr>
					<lemis:codelisteditor type="erming" label="耳鸣" required="false"	 isSelect="false" />
					<lemis:codelisteditor type="xuanyun" label="眩晕" required="false" 	 isSelect="false"/>
					<lemis:codelisteditor type="gaoxueya" label="高血压" required="false"  isSelect="false" />
					</tr>
					<tr>
					<lemis:codelisteditor type="gaoxuezhi" label="高血脂病" required="false" isSelect="false" />
					<lemis:codelisteditor type="tangniaobing" label="糖尿病" required="false"  isSelect="false"/>
					<lemis:texteditor property="tnbnote" label="其它" required="false"   disable="true" colspan="3"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="zhutingqi" label="有无助听器使用史" required="false" isSelect="false" />
					<lemis:texteditor property="ztqtime" label="使用时间" required="false"  disable="true" />
					<lemis:texteditor property="ztqtype" label="型号" required="false"  disable="true"/>
					<lemis:texteditor property="ztqxg" label="以往助听器使用效果" required="false"   disable="true"/>
					
				</tr>
			</table>
			
			<lemis:tabletitle title="听力检查" />
			<table class="tableinput">
			<COLGROUP><COL width='12%'><COL width='12%'><COL width='17%'><COL width='12%'><COL width='11%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="jixing" label="耳廓及外耳道有无畸形" required="false"   isSelect="false"/>
					<lemis:codelisteditor type="yanzheng" label="炎症" required="false"	isSelect="false"  />
					<lemis:texteditor property="yznote"  label="备注" required="false"  disable="true" colspan="3"/>
				</tr>
				<tr>
					<td>鼓膜</td>
					<td  colspan="2" >
					未见异常<html:checkbox  property="gumo"  value="0" disabled="false" />
					穿孔<html:checkbox   property="gumo"  value="1" disabled="false"/>
					积脓<html:checkbox   property="gumo"  value="2" disabled="false"/> </td>
					<td ></td>
					<lemis:texteditor property="gmnote"  label="备注" required="false"  disable="true" colspan="3"/>
				</tr>
				<tr>
					<td>听力检查（附）</td>
					<td  colspan="2" >
					纯音测听<html:checkbox  property="jiancha"  value="0" disabled="false"/>
					OAE<html:checkbox   property="jiancha"  value="1" disabled="false"/>
					ABR<html:checkbox   property="jiancha"  value="2" disabled="false"/>
					声导抗<html:checkbox   property="jiancha"  value="3" disabled="false"/>
					 </td>
					 <td></td>
					<lemis:texteditor property="jcnote"  label="其它" required="false"  disable="true" colspan="3"/>
				</tr>
			</table>
			<lemis:tabletitle title="听力诊断" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='25%'><COL width='19%'><COL width='30%'></COLGROUP>
				<tr>
					<td>传导性耳聋<html:checkbox  property="cdxing"  value="1" disabled="false"/>：</td>
					<td  colspan="1" >
					中耳畸形<html:checkbox  property="chuandao"  value="0" disabled="false"/>
					中耳炎<html:checkbox   property="chuandao"  value="1" disabled="false"/>
					耳硬化症<html:checkbox   property="chuandao"  value="2" disabled="false"/></td>
					<lemis:texteditor property="cdnote"  label="备注" required="false"  disable="true" colspan="1"/>
				</tr>
				<tr>
					<td>感音神经性聋<html:checkbox  property="gyxing"  value="1" disabled="false"/>：</td>
					<td  colspan="1" >
					内耳畸形<html:checkbox  property="ganyin"  value="0" disabled="false"/>
					药物性<html:checkbox   property="ganyin"  value="1" disabled="false"/>
					突发性<html:checkbox   property="ganyin"  value="2" disabled="false"/>
					老年性<html:checkbox   property="ganyin"  value="3" disabled="false"/>
					梅尼埃病<html:checkbox   property="ganyin"  value="4" disabled="false"/>
					 </td>
					<lemis:texteditor property="gynote"  label="备注" required="false"  disable="true" colspan="1"/>
				</tr>
				<tr>
					<td>混合性耳聋<html:checkbox  property="hunhexing"  value="1" disabled="false"/>：</td>
					<td></td>
					<lemis:texteditor property="hhxnote"  label="备注" required="false"  disable="true" colspan="1"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="处理情况" />
			<table class="tableinput">
			<COLGROUP><COL width='25%'><COL width='25%'><COL width='25%'></COLGROUP>
				<tr><td>向患者分析此听力损失的原因、部分及程度<html:checkbox  property="chuli"  value="1" disabled="false"/></td>
				<td>向患者陈述此听力损失对听觉的影响及危害<html:checkbox  property="chuli"  value="2" disabled="false"/></td>
				<td>解释对听障者持续听力补偿的必要性<html:checkbox  property="chuli"  value="3" disabled="false"/></td>
				<td>双耳补偿的重要性及双耳试听<html:checkbox  property="chuli"  value="4" disabled="false"/></td></tr>
				<tr><td>不同助听产品的适应症及康复措施与效果<html:checkbox  property="chuli"  value="5" disabled="false"/></td>
				<td>疑难听力损失会诊<html:checkbox  property="chuli"  value="6" disabled="false"/></td>
				<lemis:texteditor property="clnote"  label="备注：" required="false"  disable="false" colspan="1"/></tr>
			</table>

		</html:form>
	</table>

	<table id="iframediv" border="0">
		<tr>
			<td><iframe
				src='<html:rewrite page="/DiagnoseAction.do?method=showLeft"/>'
				id="iframe1" name="iframe1" height="320"
				frameborder="0" width="100%"></iframe></td>
			<td><iframe
				src='<html:rewrite page="/DiagnoseAction.do?method=showRight"/>'
				id="iframe2" name="iframe2" height="320"
				frameborder="0" width="100%"></iframe></td>
		</tr>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>

</html>

