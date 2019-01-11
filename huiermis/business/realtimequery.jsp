<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("gctprovince", "����ʡ"));
	header.add(new Formatter("gctarea", "��������"));
	header.add(new Formatter("ncdypname","����ʦ����"));
	header.add(new Formatter("cltnm","�ͻ�����"));
	header.add(new Formatter("foltype","��������"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtprc", "ԭ��","color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt", "����"));
	header.add(new Formatter("fdtdisc","����"));
	header.add(new Formatter("fdtprc", "�ۼ�","color:#000000;", TagConstants.DT_MONEY));/*�ۼ�*/
	//header.add(new Formatter("recmoney", "�˻���","color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("sta", "״̬"));
	header.add(new Formatter("folchgdt", "��������"));
	//header.add(new Formatter("fdtodt", "�˻�����"));
	header.add(new Formatter("fdtisspdisc", "�Ƿ����ر�����"));
	header.add(new Formatter("folnt", "�շѱ�ע"));
	
	
	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("gctnm", "�ͻ�����"));
	hidden.add(new Formatter("pdtnm", "��Ʒ����"));
	hidden.add(new Formatter("ictgctid","��������"));
	hidden.add(new Formatter("cltnm","�ͻ�����"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","gctnm","�ͻ�����"));
	editors.add(new Editor("date","start","�������ڴ�"));
	editors.add(new Editor("date","end","��"));
	//editors.add(new Editor("date","stui","�˻����ڴ�"));
	//editors.add(new Editor("date","etui","��"));
	editors.add(new Editor("text","pdttype","Ʒ������"));
	editors.add(new Editor("text","pdtnm","��Ʒ����"));
	editors.add(new Editor("text","foltype","��������"));
	editors.add(new Editor("text","cltnm","�ͻ�����"));
	editors.add(new Editor("text","gctarea","��������"));
	editors.add(new Editor("text","ncdypname","����ʦ����"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	String grCli_cx=dto.getBsc011();
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">

$(document).ready(function(){
	//HF0001 ����һ��(����һ) HF0010 ���۶������������  HF0011�������� (������) HF0012 ��������   HF0019 �������� (������)
	//HF0005  �������� (������)  HF0014 ��������  (������)  ������������������
	var grCli_cx="<%=grCli_cx%>";
    //$("input[name=gctnm]").val(grCli_cx);
	if(grCli_cx=="HF0001"){
		$("select[name=gctarea]").val("����һ");
		$("select[name=gctarea]").attr("disabled",true);
		
		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("����һ");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
		    $("select[name=gctarea]").val("����һ");
		});
	}
	if(grCli_cx=="HF0010"){
		$("select[name=gctarea]").val("�����");
		$("select[name=gctarea]").attr("disabled",true);
		
		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("�����");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("�����");
		});
	}
	if(grCli_cx=="HF0011" || grCli_cx=="HF0012"){
		$("select[name=gctarea]").val("������");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("������");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("������");
		});
	}
	if(grCli_cx=="HF0019"){
		$("select[name=gctarea]").val("������");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("������");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("������");
		});
	}
	if(grCli_cx=="HF0005"){
		$("select[name=gctarea]").val("������");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("������");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("������");
		});
	}
	if(grCli_cx=="HF0014"){
		$("select[name=gctarea]").val("������");
		$("select[name=gctarea]").attr("disabled",true);

		$("input[value=����[R]]").bind("click",function(e){
			$("select[name=gctarea]").val("������");
			$("select[name=gctarea]").attr("disabled",true);
		});
		$("input[value=��ѯ[Q]]").bind("click",function(e){
			$("select[name=gctarea]").attr("disabled",false);
			$("select[name=gctarea]").val("������");
		});
	}
	/*
	else{
		var shops = "0123456789����һ��=������,0123456789���۶���=�����,0123456789��������=������,0123456789��������=������,0123456789��������=������,0123456789��������=������,0123456789��������=������,0123456789=������".split(",");
		alert(shops);
		$("input[name=gctarea]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=gctarea]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=gctarea]").val(gnm);
			}
		});
	}
    */
	
	
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	if(grCli=="1501000000")
	{
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
			
				}
			});
	}
	else
	{
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=gctnm]").attr("readonly","true");
		$("input[value=����[R]]").bind("click",function(e){
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}

});

	<%--var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=gctnm]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=gctnm]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			//var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=gctnm]").val(gnm);
			$(this).parent().next().find("input").val(gnm);
		}
	});  --%>


<%-- var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

$("input[name=pdtnm]").autocomplete(products,{
	max : 10,
	matchContains : true,
	formatItem: function(data, i, max) {
		return data[0].substring(0,data[0].indexOf(":"));
	}
});
$("input[name=pdtnm]").result(function(event, data, formatted) {
	if (data){
		var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
		$("input[name=pdtnm]").val(pnm);
	}
}); --%>
//$("input[name=pdtnm]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
		 dataType:'json',
		 error:function(){
		   alert('��ȡ���ݴ���');
		 },
		 success:function(data){
					$("input[name=pdtnm]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.proid + "=" + data.proname);
						}
					});	
					$("input[name=pdtnm]").result(function(event, data, formatted) {
						if (data){
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=pdtnm]").val(pnm);

						}
					});
				}
		//});
});


</script>
<lemis:base />
<lemis:body>
	<lemis:title title="ʵʱ���۲�ѯ" />
	<lemis:query action="/BusinessAction.do?method=realtimequery" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="������Ϣ"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


