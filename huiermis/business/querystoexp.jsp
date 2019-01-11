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
	//header.add(new Formatter("gctid","�ͻ�����"));
	header.add(new Formatter("gctnm", "�ͻ�����"));	
	header.add(new Formatter("gctarea", "��������"));
	header.add(new Formatter("ictnm", "���˿ͻ�"));	
	header.add(new Formatter("stogrpnm", "��Ʒ����"));
	header.add(new Formatter("pdtprc","���ۼ�"));
	header.add(new Formatter("storqnt", "ʣ������"));
	header.add(new Formatter("stodate", "�������"));
	header.add(new Formatter("days","ѹ������"));

	Map<String,String> hidden = new LinkedHashMap<String,String>();
	hidden.put("gctid","�ͻ�����");
	hidden.put("stogrpnm","��Ʒ����");
	hidden.put("gctarea","��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","gctnm","�ͻ�����"));
	editors.add(new Editor("text","stogrpnm","��Ʒ����"));
	editors.add(new Editor("money","prices","�������۸����"));
	//editors.add(new Editor("text","gcttype","��������"));
	editors.add(new Editor("money","pricesbox","��ʽ���۸����"));
	editors.add(new Editor("text","gctarea","��������"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�޸�ѹ����", "modify(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("hidden",hidden);
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
	
	$("input[name=gctnm]").autocomplete(shops,{
		max : 10,
		matchContains : true
	});
	$("input[name=gctnm]").result(function(event, data, formatted) {
		if (data){
			var gnm = data[0].substring(data[0].indexOf("=")+1);
			var gid = data[0].substring(0,data[0].indexOf("="));
			$("input[name=gctnm]").val(gnm);
			$(this).parent().next().find("input").val(gnm);
		}
	});
	

	


<%-- var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
$("input[name=stogrpnm]").autocomplete(products,{
	max : 10,
	matchContains : true
}); 

$("input[name=stogrpnm]").result(function(event, data, formatted) {
	if (data){
		var pid = data[0].substring(0,data[0].indexOf("="));
		var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
		$("input[name=stogrpnm]").val(pnm);
	}
}); --%>

//$("input[name=stogrpnm]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
		 dataType:'json',
		 error:function(){
		   alert('��ȡ���ݴ���');
		 },
		 success:function(data){
					$("input[name=stogrpnm]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.proid + "=" + data.proname);
						}
					});	
					$("input[name=stogrpnm]").result(function(event, data, formatted) {
						if (data){
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=stogrpnm]").val(pnm);

						}
					});
				}
		});
//});
});

</script>

<script language="javascript">
	function modify(obj)
	{
		obj.action='<html:rewrite page="/BusinessAction.do?method=queryExd"/>';
		obj.submit();
	};
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="��泬�ڲ�ѯ" />
	<lemis:query action="/BusinessAction.do?method=stoexpquery" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="�����ϸ��Ϣ"
		hiddenMeta="hidden"  mode="radio"  />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


