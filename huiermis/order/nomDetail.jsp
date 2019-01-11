<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","���","clt020201","examine(document.all.tableform)")); //tableform\orderHeaderForm
buttons.add(new Button("jcdz","����","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","����","clt020203","history.back()"));

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("pdtnm","��Ʒ����"));
	header.add(new Formatter("fdtdprc","�۸�",TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	header.add(new Formatter("fdtqnt","����"));
	header.add(new Formatter("fdtinnt","��ע"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","fdtpid","��Ʒ����","true"));
	batchInput.add(new Editor("text","pdtnm","��Ʒ����","true"));
	batchInput.add(new Editor("text","fdtdprc","�۸�","true"));
	batchInput.add(new Editor("text","fdtqnt","����","true"));
	batchInput.add(new Editor("text","fdtinnt","��ע","false"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "��������");
	hidden.put("folno","������");
	hidden.put("foltype","��������");
	hidden.put("folsta","����״̬");
	hidden.put("gctnm","����ͻ�");
	hidden.put("foldt","��������");
	hidden.put("folsta","����״̬");
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
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
	<%-- 	var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
         
		 $("input[name=chk]").attr("checked",true);
		
		$("input[name=fdtpid]").autocomplete(products,{
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
			}
		}); --%>
	 $("input[name=fdtdprc]").attr('readonly','readonly');
	 $("input[name=pdtnm]").attr('readonly','readonly'); 
	 $(":checkbox:enabled").attr('checked', true);
// 	 $('input[type="checkbox"][name="chk"]').each(
//              function(i) {
//             	 var fdtpid=$("input[name=fdtpid]").val();
//             	 alert(i);
//             	 var fdtpid=$("#fdtpid_row" + (i+1)).val();
//             	 alert(fdtpid);
//             	 if(fdtpid!='')
//             	 {
//             		 $("input[name=chk][value="+i+"]").attr("checked",true);
//             		 alert(fdtpid);
//             	 }
//             	 else if(fdtpid == ''){
//             		 $("input[name=chk][value="+i+"]").attr("checked",false);
//             		 alert("success");
//             	 }
            	
//              }
//          );
	/*  var id = $("input[name=fdtpid]").attr("id");
		var suffix = id.substr(6);
		$("#subCheckbox" + suffix).attr("checked",true); */
		
	
	/*  function createQueryString(e) {
			var eID = $(e.target).val();
			var queryString = {
				EarId : eID
			};
			return queryString;
		};
		function startRequest(e) {
			$.getJSON("/huiermis/order/OrderAction.do?method=queryMinSto",
					createQueryString(e), function(data) {
						$("input[name=tmeprc]").val(data[0].price);
					});
		}; */
	
 });
 
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&folType=nom&type=o&folsta="/>' + $('input:hidden[name=folsta]').val();
        if(confirm("ȷ��Ҫ���˶�����"))
        {
		   obj.submit();
        }
	};
	
	function examine(obj){
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=e&folType=nom&type=o"/>';
		if(confirm("ȷ���������ͨ����"))
        {
		   obj.submit();
        }
	}
	
		
</script> 
<script language="javascript">
	function batchSubmit() {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/order/OrderAction.do?method=saveNomOrder&"
				+ getAlldata(document.forms[0]);
		//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
	}
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="������ϸ" />
	<html:form action="/OrderAction.do?method=examineOrder&tp1=e&folType=nom&type=o" method="POST">
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />

			 <tr>
				<lemis:texteditor property="folno" label="������" disable="true" />
				<%--<lemis:texteditor property="folctid" label="�ͻ�����" disable="true" /> 
			<lemis:texteditor property="folctnm" label="��������" disable="true" /> --%>
		       <html:hidden property="foltype"/>
			</tr>
			<tr>
				
				<%-- <td>��������</td>
				<td><lemis:operorg /></td>
				<lemis:formateditor mask="date" property="foldt" label="��������"
					required="false" disable="true" />
				<td>��������</td>
					<td><lemis:operdate />
					<td>������</td>
				<td><lemis:operator /></td> --%>
				<lemis:texteditor property="gctnm" label="���뵥λ" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="��������"></lemis:texteditor>
			</tr>
	</table>

	<lemis:table topic="������ϸ¼��"
		action="/OrderAction.do?method=batchSubmit" headerMeta="header" hiddenMeta="hidden"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false"
		batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
	</html:form>
</lemis:body>
</html>