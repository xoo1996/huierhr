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
    //buttons.put("�� ��","save(document.forms[0])");
    buttons.put("�� ��","commit(document.forms[0])");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtdprc","�۸�",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	/* header.add(new Formatter("fdtdisc","����")); */
	header.add(new Formatter("fdtqnt","����"));
	/* header.add(new Formatter("fdtprc","�ּ�")); */
	header.add(new Formatter("fdtinnt","��ע"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","��Ʒ����","true"));
	batchInput.add(new Editor("text","pdtnm","��Ʒ����","true"));
	batchInput.add(new Editor("text","fdtdprc","�۸�","true"));
	/* batchInput.add(new Editor("text","fdtdisc","����","true")); */
	batchInput.add(new Editor("text","fdtqnt","����","true"));
	/* batchInput.add(new Editor("text","fdtprc","�ּ�","true")); */

	batchInput.add(new Editor("text","fdtinnt","��ע","false"));
	
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	function huier_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderAction.do?method=print&type=huier&"
				+ getAlldata(document.all.tableform);
	};
	function jiewen_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderAction.do?method=print&type=jiewen&"
				+ getAlldata(document.all.tableform);
	};

 $(document).ready(function(e) {

		<%-- var nomProducts = "<%=session.getServletContext().getAttribute("nomProductList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=fdtpid]").autocomplete(nomProducts,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		}); 
		$("input[name=fdtpid]").result(function(event, data, formatted) {
			if (data){
				var pid = data[0].substring(0,data[0].indexOf("="));
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				var prc = data[0].substring(data[0].indexOf(":")+1);
				var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(6);
				$("#fdtpid" + suffix).val(pid);
				$("#pdtnm" + suffix).val(pnm);
				$("#fdtdprc" + suffix).val(prc);
			/* 	$("#fdtqnt" + suffix).val(1); */
			}
		}); --%>
		
		//$("input[name=fdtpid]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 data:"proType=nom",
				 dataType:'json',
				 error:function(){
				   alert('��ȡ���ݴ���');
				 },
				 success:function(data){
							$("input[name=fdtpid]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=fdtpid]").result(function(event, data, formatted) {
								if (data){
									var pid = data.proid;
									var pnm = data.proname;
									var prc=data.proprc;
									var id = $(this).parent().find("input").attr("id");
										var suffix = id.substr(6);
										$("#fdtpid" + suffix).val(pid);
										$("#pdtnm" + suffix).val(pnm);
										$("#fdtdprc" + suffix).val(prc);
										
								}
							});
						}
				//});
		});
		
 	$("input[name=fdtdprc]").attr('readonly','readonly');
	$("input[name=pdtnm]").attr('readonly','readonly');
	
	
	  var grCli="";
	    grCli=<%=dto.getBsc001()%>;
	    if(grCli=="1501000000")
	    {                                                            /*grCliList*/
	    	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		    $("input[name=folctnm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
				return data[0];
				}
			});
		
			$("input[name=folctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=folctnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
				}
			});
	    }
	    else
	    {
	    	
	    	$("input[name=folctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=folctnm]").attr("readonly","true");
	    }
	
		function compute1(e) {
		var id = $(e.target).attr("id");
  	  var suffix = id.substr(6); 
	/* 	var id=id.split("-");
		var suffix=id[1];  */
		var value = $("#fdtdprc" + suffix).val()*$("#fdtdisc" + suffix).val()*$("#fdtqnt" + suffix).val();
		$("#fdtprc" + suffix).val(value);
	};
	function compute2(e) {
		var id = $(e.target).attr("id");  //�õ��ؼ�idֵid='fdtpid_row1'
		var suffix = id.substr(6); 
		/* var id=id.split("-");
		var suffix=id[1]; */
		var value = $("#fdtprc" + suffix).val()/$("#fdtqnt" + suffix).val()/$("#fdtdprc" + suffix).val();
		//value = changeTwoDecimal(value);
		//value = value.toString().substring(0, value.toString().indexOf(".")+ 3);
		value = value.toFixed(2);//������λ��Ч����
		$("#fdtdisc" + suffix).val(value);
		
	};
	
	function chk(e) {
		var id = $(e.target).attr("id");  //�õ��ؼ�idֵid='fdtpid_row1'
		var suffix = id.substr(10); 
		var suffix2 = id.substr(6); 
		var fdtid=$("#fdtpid" + suffix2).val();
        if(fdtid!='')
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",true);
        }
        else
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",false);
        }
			
	};
	$("input[name=fdtpid]").bind("blur",function(e){
		chk(e);
	});
	
	/* $("input[name=fdtqnt]").bind("blur",function(e){
		var id = $(this).parent().find("input").attr("id");
		var suffix = id.substr(6);
		$("#subCheckbox" + suffix).attr("checked",true);
	}); */
/* 	.bind("blur",function(e){
		compute2(e);
	}); */
	
	/* $("input[name=fdtprc]").bind("blur",function(e){
		compute2(e);
	}); */
	$("input[name=fdtqnt]").bind("blur",function(e){
		compute1(e);
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
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=c&"/>'+getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ¼�붩�����ύ��")) {
		obj.submit();
	}
};

function save(obj) {
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
	obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=b&"/>'+getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ¼�붩����")) {
		obj.submit();
	}
}
	
		
</script> 

</head>
<lemis:base />
<lemis:body>
	<lemis:title title="������ϸ¼��" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<%-- <tr>
				<lemis:texteditor property="folno" label="������" disable="true" />
				<lemis:texteditor property="folctid" label="�ͻ�����" disable="true" /> 
			<lemis:texteditor property="folctnm" label="��������" disable="true" /> 
		
			</tr>--%>
			<tr>
				
				<%-- <td>ֱ��������</td>
				<td><lemis:operorg /></td> --%>
				<lemis:texteditor property="folctnm" label="���Ƶ�λ" disable="false" required="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<td>��������</td>
					<td><lemis:operdate />
			</tr>
		</html:form>
	</table>

	<lemis:table topic="������ϸ¼��"
		action="/OrderAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="insert" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>