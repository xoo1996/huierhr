<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    Map<String,String> buttons=new LinkedHashMap<String,String>();
    //buttons.put("修改后保存","xiugai(document.all.orderHeaderForm)");
    buttons.put("保 存","add(document.forms[0])");
    buttons.put("返 回","history.back()");


	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","商品代码"));
	header.add(new Formatter("pdtnm","商品名称"));
	header.add(new Formatter("fdtprc","单价",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","数量"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","商品代码","true"));
	batchInput.add(new Editor("text","pdtnm","商品名称","true"));
	batchInput.add(new Editor("money","fdtprc","单价","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","数量","true"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/client/";
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
	<script src="/huiermis/js/CityJson.js"></script>
<script src="/huiermis/js/DistrictJson.js"></script>
<script src="/huiermis/js/ProJson.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->

<script type="text/javascript">
	$(document).ready(function(){

		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		if(grCli=="1501000000")
		{
			var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
			$("input[name=ictgctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0];
				}
			});
			
			$("input[name=ictgctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1);
					$("input[name=ictgctnm]").val(gnm);
					$(this).parent().next().find("input").val(gid);
				}
			});
		}
		else
		{
			$("input[name=ictgctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=ictgctnm]").attr("readonly","true");
			$("input:hidden[name=ictgctid]").val("<%=dto.getBsc011()%>");
	 	}
		
	});
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
	
	function add(obj) {
 		if (!checkValue(obj)) {
			return false;
		}
 		if(($("input[name=ictphone]").val()=="")&&($("input[name=ictlandline]").val()=="")){
 			alert("手机或者固定电话必填一项！");
 			return false;
 		}
 		$.getJSON("<%=basePath%>SingleClientAction.do?method=checkSingleName&" +
				 getAlldata(document.forms[0])+"&ictnm=" + $("input[name=ictnm]").val()
				           +"&ictgender=" + $("select[name=ictgender]:isSelected").val()
				           +"&ictbdt=" + $("('#xmhk')[name=ictbdt]:isSelected").val()
				           ,
				 function(data) {
 			         var str="";
					 if(null!=data && data[0].tdspvo!='')
				     {
						 if(confirm(str+"已存在此用户，是否继续创建?","是","否"))
						 {  
						 	obj.submit();
						 }
				     }
					 else 
					 {
						 if(confirm("确认创建该用户？"))
						 { 
							 obj.submit(); 
						 }
					 }  
				 
			});
	};   
	function xiugai(obj){
		if (!checkValue(obj)) {
			return false;
		}
		if(confirm("！警示：此按钮为修改后保存！！确定已经创建过该用户?","是","否"))
		{  
			if(confirm("是否保存修改后的信息???","是","否"))
			{
				obj.action = '<html:rewrite page="/CustomOrderAction.do?method=addClient&tp=cus&type=firm&"/>' + getAlldata(obj);
			    obj.submit();
			}
		}
	};
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
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="新增客户" />
	<lemis:tabletitle title="客户基本信息" />
	
	<table class="tableInput">
		<lemis:editorlayout />
			<html:form action="/CustomOrderAction.do?method=addClient&tp=cus&type=o" method="POST">
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictgctnm" label="所属团体" disable="false" required="true"/>
				<html:hidden property="ictgctid"/>
				<lemis:texteditor property="ictnm" label="用户姓名" disable="false" required="true"/>
				<lemis:codelisteditor type="ictgender" label="性别" required="true" />
			</tr>
			<tr>
				<lemis:formateditor required="false" property="ictbdt" mask="date"
					label="出生日期" format="true" disable="false" required="true"/>
				<lemis:texteditor property="ictphone" label="手机" disable="false"/>
				<lemis:texteditor property="ictlandline" label="固定电话"disable="false"  />
				</tr>
			<tr>
			<tr>
			<td><font color='#FF0000'>*</font>省份</td><td colspan="1">
			<select style='font-size:12px' name='ictpro' id='ictpro'
					class='select'  required='true'><option
						value='' selected> 请选择</option></select></td>
			<td><font color='#FF0000'>*</font>市</td><td colspan="1">
			<select style='font-size:12px' name='ictcity' id='ictcity'
					class='select'  required='true'><option
						value='' selected> 请选择</option></select></td>
			<td><font color='#FF0000'>*</font>县（区）</td><td colspan="1">
			<select style='font-size:12px' name='ictcounty' id='ictcounty'
					class='select'  required='true'><option
						value='' selected> 请选择</option></select></td>
			</tr>
			<tr>
				<lemis:texteditor property="ictdetailaddr" label="联系地址" disable="false" required="true"/>
				<lemis:codelisteditor type="ictphis" label="使用过何种助听器" required="true"/>
				<lemis:texteditor property="ictsrc" label="来源" disable="false" />
				</tr>
				<tr>
				<lemis:texteditor property="ictnt" label="备注" disable="false" />
				<lemis:codelisteditor type="ictfrom" label="来源" required="true" />
			</tr>


			<lemis:tabletitle title="左耳骨导" />
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

			<lemis:tabletitle title="左耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq750" label="750" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq1500" label="1500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq3000" label="3000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lq6000" label="6000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="lqvag()" />
					<lemis:texteditor property="lqavg" label="平均" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="右耳骨导" />
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

			<lemis:tabletitle title="右耳气导" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq500" label="500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq750" label="750" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rq6000" label="6000" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onblur="rqvag()" />
					<lemis:texteditor property="rqavg" label="平均" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="rqvag()" />
				</tr>
			</table>
				<!-- 2015.6.19 新增加客户详细信息-->
			<lemis:tabletitle title="询问病史" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="losetime" label="听力损失时间" required="false"   />
					<lemis:texteditor property="lstnote"  label="备注" required="false"  disable="false" colspan="5"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="shoushushi" label="有无流脓或手术史" required="false"	  />
					<lemis:texteditor property="sssnote" label="备注" required="false"	 disable="false" colspan="5"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="yichuanshi" label="耳聋家族或遗传病史" required="false"	/>
					<lemis:texteditor property="ycsnote" label="备注" required="false" disable="false" colspan="5"/>
					</tr>
				<tr>
					<lemis:codelisteditor type="erming" label="耳鸣" required="false"	  />
					<lemis:codelisteditor type="xuanyun" label="眩晕" required="false" 	 />
					<lemis:codelisteditor type="gaoxueya" label="高血压" required="false"   />
					</tr>
					<tr>
					<lemis:codelisteditor type="gaoxuezhi" label="高血脂病" required="false"  />
					<lemis:codelisteditor type="tangniaobing" label="糖尿病" required="false"  />
					<lemis:texteditor property="tnbnote" label="其它" required="false"   disable="false" colspan="3"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="zhutingqi" label="有无助听器使用史" required="false"  />
					<lemis:texteditor property="ztqtime" label="使用时间" required="false"  disable="false" />
					<lemis:texteditor property="ztqtype" label="型号" required="false"  disable="false"/>
					<lemis:texteditor property="ztqxg" label="以往助听器使用效果" required="false"   disable="false"/>
					
				</tr>
			</table>
			
			<lemis:tabletitle title="听力检查" />
			<table class="tableinput">
			<COLGROUP><COL width='12%'><COL width='12%'><COL width='17%'><COL width='12%'><COL width='11%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="jixing" label="耳廓及外耳道有无畸形" required="false"   />
					<lemis:codelisteditor type="yanzheng" label="炎症" required="false"	  />
					<lemis:texteditor property="yznote"  label="备注" required="false"  disable="false" colspan="3"/>
				</tr>
				<tr>
					<td>鼓膜</td>
					<td  colspan="2" >
					未见异常<html:checkbox  property="gumo"  value="0"/>
					穿孔<html:checkbox   property="gumo"  value="1"/>
					积脓<html:checkbox   property="gumo"  value="2"/> </td>
					<td ></td>
					<lemis:texteditor property="gmnote"  label="备注" required="false"  disable="false" colspan="3"/>
				</tr>
				<tr>
					<td>听力检查（附）</td>
					<td  colspan="2" >
					纯音测听<html:checkbox  property="jiancha"  value="0"/>
					OAE<html:checkbox   property="jiancha"  value="1"/>
					ABR<html:checkbox   property="jiancha"  value="2"/>
					声导抗<html:checkbox   property="jiancha"  value="3"/>
					 </td>
					 <td></td>
					<lemis:texteditor property="jcnote"  label="其它" required="false"  disable="false" colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="听力诊断" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='25%'><COL width='19%'><COL width='30%'></COLGROUP>
				<tr>
					<td>传导性耳聋<html:checkbox  property="cdxing"  value="1"/>：</td>
					<td  colspan="1" >
					中耳畸形<html:checkbox  property="chuandao"  value="0"/>
					中耳炎<html:checkbox   property="chuandao"  value="1"/>
					耳硬化症<html:checkbox   property="chuandao"  value="2"/></td>
					<lemis:texteditor property="cdnote"  label="备注" required="false"  disable="false" colspan="1"/>
				</tr>
				<tr>
					<td>感音神经性聋<html:checkbox  property="gyxing"  value="1"/>：</td>
					<td  colspan="1" >
					内耳畸形<html:checkbox  property="ganyin"  value="0"/>
					药物性<html:checkbox   property="ganyin"  value="1"/>
					突发性<html:checkbox   property="ganyin"  value="2"/>
					老年性<html:checkbox   property="ganyin"  value="3"/>
					梅尼埃病<html:checkbox   property="ganyin"  value="4"/>
					 </td>
					<lemis:texteditor property="gynote"  label="备注" required="false"  disable="false" colspan="1"/>
				</tr>
				<tr>
					<td>混合性耳聋<html:checkbox  property="hunhexing"  value="1"/>：</td>
					<td></td>
					<lemis:texteditor property="hhxnote"  label="备注" required="false"  disable="false" colspan="1"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="处理情况" />
			<table class="tableinput">
			<COLGROUP><COL width='25%'><COL width='25%'><COL width='25%'></COLGROUP>
				<tr><td>向患者分析此听力损失的原因、部分及程度<html:checkbox  property="chuli"  value="1" /></td>
				<td>向患者陈述此听力损失对听觉的影响及危害<html:checkbox  property="chuli"  value="2"/></td>
				<td>解释对听障者持续听力补偿的必要性<html:checkbox  property="chuli"  value="3"/></td>
				<td>双耳补偿的重要性及双耳试听<html:checkbox  property="chuli"  value="4"/></td></tr>
				<tr><td>不同助听产品的适应症及康复措施与效果<html:checkbox  property="chuli"  value="5"/></td>
				<td>疑难听力损失会诊<html:checkbox  property="chuli"  value="6"/></td>
				<lemis:texteditor property="clnote"  label="备注：" required="false"  disable="false" colspan="1"/></tr>
			</table>
		</html:form>  
 	</table>
	<lemis:buttons buttonMeta="button" />
		
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>