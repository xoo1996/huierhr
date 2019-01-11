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

//	List<Button> buttons=new ArrayList<Button>();
//	buttons.add(new Button("back","����","ord020003","history.back()"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("����","history.back()"); 

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
 $("input[name=fdtdprc]").attr('readonly','readonly');
	/* $("input[name=pdtnm]").attr('readonly','readonly'); */
	
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
	$("input[name=fdtqnt]").bind("blur",function(e){
		compute1(e);
	});
/* 	.bind("blur",function(e){
		compute2(e);
	}); */
	
	/* $("input[name=fdtprc]").bind("blur",function(e){
		compute2(e);
	}); */
 });
 
	function commit(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=c&tp2=s&"/>'+getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ¼�붩�����ύ��")) {
		obj.submit();
	}
};

function save(obj) {
	obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=saveNomOrder&tp=b&tp2=s&"/>'+getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ¼�붩����")) {
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
	<lemis:title title="������ϸ¼��" />
	<lemis:tabletitle title="����������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/OrderAction.do" method="POST">
			<tr>
				<lemis:texteditor property="folno" label="������" disable="true" />
			<%-- 	<lemis:texteditor property="folctid" label="�ͻ�����" disable="true" /> 
			<lemis:texteditor property="folctnm" label="��������" disable="true" />  --%>
		
			</tr>
			<tr>
				
			<%-- 	<td>��������</td>
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