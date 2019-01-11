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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("gctprovince", "����ʡ"));
	header.add(new Formatter("gctarea", "��������"));
	header.add(new Formatter("gcttype", "��������"));
	header.add(new Formatter("pdttype", "��ƷƷ��"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtprc","���ۼ�"));
	header.add(new Formatter("temp02", "�ؿ�����"));
	header.add(new Formatter("temp01", "�ؿ���", "color:#000000;", TagConstants.DT_MONEY));
	//header.add(new Formatter("temp12", "ά�޷�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("temp04", "ʡ�ؿ���", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp06", "����ؿ���", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp08", "Ʒ�ƻؿ���", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp10", "��Ʒ�ؿ���", "color:#000000;", TagConstants.DT_MONEY,true));
	
	//�����ĸ�ͷ
 	header.add(new Formatter("temp13", "���ƻ�����", "color:#aaaaaa;", true));
	header.add(new Formatter("temp14", "���ƻ����", "color:#000000;", TagConstants.DT_MONEY,true));
	header.add(new Formatter("temp15", "��������ʽ������", "color:#000000;",true));
	header.add(new Formatter("temp16", "��������ʽ�����", "color:#000000;", TagConstants.DT_MONEY,true)); 

	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("ivtgcltid", "�ͻ�����"));
	hidden.add(new Formatter("ivtyear", "��"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","�����","true"));
	editors.add(new Editor("text","ivtmonths","��","true"));
	editors.add(new Editor("text","ivtyearEnd","����","true"));
	editors.add(new Editor("text","ivtmontht","��","true"));
	editors.add(new Editor("text","ivtgcltid","�ͻ�����"));
	editors.add(new Editor("text","gctarea","��������"));
	editors.add(new Editor("text","gcttype","��������"));
	editors.add(new Editor("text","gctprovince","����ʡ"));
	editors.add(new Editor("text","pdttype","��ƷƷ��"));
	editors.add(new Editor("text","pdtnm","��Ʒ����"));
	//editors.add(new Editor("text","repfee","ά������"));
	
	//2012-3-26�����ֶ�
	editors.add(new Editor("text","ivttype","��Ʒ����"));
	editors.add(new Editor("text","pdtcls","�������"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
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
	

	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	
	$("input[name=ivtgcltid]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=ivtgcltid]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=ivtgcltid]").val(gid);
			$(this).parent().next().find("input").val(gid);
		}
	});


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
		});
//});

});
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="��Ʒ���۲�ѯ" />
	<lemis:query action="/BusinessAction.do?method=salequery" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="������ϸ��Ϣ"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


