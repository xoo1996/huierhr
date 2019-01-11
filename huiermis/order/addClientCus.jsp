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
    //buttons.put("�޸ĺ󱣴�","xiugai(document.all.orderHeaderForm)");
    buttons.put("�� ��","add(document.forms[0])");
    buttons.put("�� ��","history.back()");


	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtprc","����",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","����"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","��Ʒ����","true"));
	batchInput.add(new Editor("text","pdtnm","��Ʒ����","true"));
	batchInput.add(new Editor("money","fdtprc","����","true"));
	batchInput.add(new Editor("-nnnnn","fdtqnt","����","true"));
	
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
 			alert("�ֻ����߹̶��绰����һ�");
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
						 if(confirm(str+"�Ѵ��ڴ��û����Ƿ��������?","��","��"))
						 {  
						 	obj.submit();
						 }
				     }
					 else 
					 {
						 if(confirm("ȷ�ϴ������û���"))
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
		if(confirm("����ʾ���˰�ťΪ�޸ĺ󱣴棡��ȷ���Ѿ����������û�?","��","��"))
		{  
			if(confirm("�Ƿ񱣴��޸ĺ����Ϣ???","��","��"))
			{
				obj.action = '<html:rewrite page="/CustomOrderAction.do?method=addClient&tp=cus&type=firm&"/>' + getAlldata(obj);
			    obj.submit();
			}
		}
	};
</script>
 <script type="text/javascript">
 //ʡ����������js
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
	<lemis:title title="�����ͻ�" />
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	
	<table class="tableInput">
		<lemis:editorlayout />
			<html:form action="/CustomOrderAction.do?method=addClient&tp=cus&type=o" method="POST">
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictgctnm" label="��������" disable="false" required="true"/>
				<html:hidden property="ictgctid"/>
				<lemis:texteditor property="ictnm" label="�û�����" disable="false" required="true"/>
				<lemis:codelisteditor type="ictgender" label="�Ա�" required="true" />
			</tr>
			<tr>
				<lemis:formateditor required="false" property="ictbdt" mask="date"
					label="��������" format="true" disable="false" required="true"/>
				<lemis:texteditor property="ictphone" label="�ֻ�" disable="false"/>
				<lemis:texteditor property="ictlandline" label="�̶��绰"disable="false"  />
				</tr>
			<tr>
			<tr>
			<td><font color='#FF0000'>*</font>ʡ��</td><td colspan="1">
			<select style='font-size:12px' name='ictpro' id='ictpro'
					class='select'  required='true'><option
						value='' selected> ��ѡ��</option></select></td>
			<td><font color='#FF0000'>*</font>��</td><td colspan="1">
			<select style='font-size:12px' name='ictcity' id='ictcity'
					class='select'  required='true'><option
						value='' selected> ��ѡ��</option></select></td>
			<td><font color='#FF0000'>*</font>�أ�����</td><td colspan="1">
			<select style='font-size:12px' name='ictcounty' id='ictcounty'
					class='select'  required='true'><option
						value='' selected> ��ѡ��</option></select></td>
			</tr>
			<tr>
				<lemis:texteditor property="ictdetailaddr" label="��ϵ��ַ" disable="false" required="true"/>
				<lemis:codelisteditor type="ictphis" label="ʹ�ù�����������" required="true"/>
				<lemis:texteditor property="ictsrc" label="��Դ" disable="false" />
				</tr>
				<tr>
				<lemis:texteditor property="ictnt" label="��ע" disable="false" />
				<lemis:codelisteditor type="ictfrom" label="��Դ" required="true" />
			</tr>


			<lemis:tabletitle title="����ǵ�" />
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
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
						disable="false" onclick="lgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
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
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="lqvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
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
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
						disable="false" onclick="rgvag()" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
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
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
						disable="false" style="border-width:1px;border-color=black"
						onclick="rqvag()" />
				</tr>
			</table>
				<!-- 2015.6.19 �����ӿͻ���ϸ��Ϣ-->
			<lemis:tabletitle title="ѯ�ʲ�ʷ" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'><COL width='10%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="losetime" label="������ʧʱ��" required="false"   />
					<lemis:texteditor property="lstnote"  label="��ע" required="false"  disable="false" colspan="5"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="shoushushi" label="������ŧ������ʷ" required="false"	  />
					<lemis:texteditor property="sssnote" label="��ע" required="false"	 disable="false" colspan="5"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="yichuanshi" label="����������Ŵ���ʷ" required="false"	/>
					<lemis:texteditor property="ycsnote" label="��ע" required="false" disable="false" colspan="5"/>
					</tr>
				<tr>
					<lemis:codelisteditor type="erming" label="����" required="false"	  />
					<lemis:codelisteditor type="xuanyun" label="ѣ��" required="false" 	 />
					<lemis:codelisteditor type="gaoxueya" label="��Ѫѹ" required="false"   />
					</tr>
					<tr>
					<lemis:codelisteditor type="gaoxuezhi" label="��Ѫ֬��" required="false"  />
					<lemis:codelisteditor type="tangniaobing" label="����" required="false"  />
					<lemis:texteditor property="tnbnote" label="����" required="false"   disable="false" colspan="3"/>
					</tr>
					<tr>
					<lemis:codelisteditor type="zhutingqi" label="����������ʹ��ʷ" required="false"  />
					<lemis:texteditor property="ztqtime" label="ʹ��ʱ��" required="false"  disable="false" />
					<lemis:texteditor property="ztqtype" label="�ͺ�" required="false"  disable="false"/>
					<lemis:texteditor property="ztqxg" label="����������ʹ��Ч��" required="false"   disable="false"/>
					
				</tr>
			</table>
			
			<lemis:tabletitle title="�������" />
			<table class="tableinput">
			<COLGROUP><COL width='12%'><COL width='12%'><COL width='17%'><COL width='12%'><COL width='11%'></COLGROUP>
				<tr>
					<lemis:codelisteditor type="jixing" label="��������������޻���" required="false"   />
					<lemis:codelisteditor type="yanzheng" label="��֢" required="false"	  />
					<lemis:texteditor property="yznote"  label="��ע" required="false"  disable="false" colspan="3"/>
				</tr>
				<tr>
					<td>��Ĥ</td>
					<td  colspan="2" >
					δ���쳣<html:checkbox  property="gumo"  value="0"/>
					����<html:checkbox   property="gumo"  value="1"/>
					��ŧ<html:checkbox   property="gumo"  value="2"/> </td>
					<td ></td>
					<lemis:texteditor property="gmnote"  label="��ע" required="false"  disable="false" colspan="3"/>
				</tr>
				<tr>
					<td>������飨����</td>
					<td  colspan="2" >
					��������<html:checkbox  property="jiancha"  value="0"/>
					OAE<html:checkbox   property="jiancha"  value="1"/>
					ABR<html:checkbox   property="jiancha"  value="2"/>
					������<html:checkbox   property="jiancha"  value="3"/>
					 </td>
					 <td></td>
					<lemis:texteditor property="jcnote"  label="����" required="false"  disable="false" colspan="3"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="�������" />
			<table class="tableinput">
			<COLGROUP><COL width='10%'><COL width='25%'><COL width='19%'><COL width='30%'></COLGROUP>
				<tr>
					<td>�����Զ���<html:checkbox  property="cdxing"  value="1"/>��</td>
					<td  colspan="1" >
					�ж�����<html:checkbox  property="chuandao"  value="0"/>
					�ж���<html:checkbox   property="chuandao"  value="1"/>
					��Ӳ��֢<html:checkbox   property="chuandao"  value="2"/></td>
					<lemis:texteditor property="cdnote"  label="��ע" required="false"  disable="false" colspan="1"/>
				</tr>
				<tr>
					<td>����������<html:checkbox  property="gyxing"  value="1"/>��</td>
					<td  colspan="1" >
					�ڶ�����<html:checkbox  property="ganyin"  value="0"/>
					ҩ����<html:checkbox   property="ganyin"  value="1"/>
					ͻ����<html:checkbox   property="ganyin"  value="2"/>
					������<html:checkbox   property="ganyin"  value="3"/>
					÷�ᰣ��<html:checkbox   property="ganyin"  value="4"/>
					 </td>
					<lemis:texteditor property="gynote"  label="��ע" required="false"  disable="false" colspan="1"/>
				</tr>
				<tr>
					<td>����Զ���<html:checkbox  property="hunhexing"  value="1"/>��</td>
					<td></td>
					<lemis:texteditor property="hhxnote"  label="��ע" required="false"  disable="false" colspan="1"/>
				</tr>
			</table>
			
			<lemis:tabletitle title="�������" />
			<table class="tableinput">
			<COLGROUP><COL width='25%'><COL width='25%'><COL width='25%'></COLGROUP>
				<tr><td>���߷�����������ʧ��ԭ�򡢲��ּ��̶�<html:checkbox  property="chuli"  value="1" /></td>
				<td>���߳�����������ʧ��������Ӱ�켰Σ��<html:checkbox  property="chuli"  value="2"/></td>
				<td>���Ͷ������߳������������ı�Ҫ��<html:checkbox  property="chuli"  value="3"/></td>
				<td>˫����������Ҫ�Լ�˫������<html:checkbox  property="chuli"  value="4"/></td></tr>
				<tr><td>��ͬ������Ʒ����Ӧ֢��������ʩ��Ч��<html:checkbox  property="chuli"  value="5"/></td>
				<td>����������ʧ����<html:checkbox  property="chuli"  value="6"/></td>
				<lemis:texteditor property="clnote"  label="��ע��" required="false"  disable="false" colspan="1"/></tr>
			</table>
		</html:form>  
 	</table>
	<lemis:buttons buttonMeta="button" />
		
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>