<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link rel="stylesheet" type="text/css" href="/huiermis/css/jquery.autocomplete.css" />
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>

<%  
	String totalYSK = (String)request.getSession().getAttribute("totalYSK");
	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("�ݶ���ӡ","huier_print()");
	buttons.put("���Ŵ�ӡ","jiewen_print()");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
/*     Map<String, String> buttons = new LinkedHashMap<String, String>();
    buttons.put("��ӡ�±���","print()");
    //buttons.add(new Button("cs","��ӡ�±���","bus110100","delData()"));
    buttons.put("�ݶ���ӡ","huier_print() ");
    buttons.put("���Ŵ�ӡ","jiewen_print()");
    
    //buttons.put("��ӡ�±���","");
    buttons.put("��������","batchSubmit(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")"); */
	/*
	//����Ȩ�޵İ�ť
	List<Object> button = new ArrayList<Object>();
	//button.add(new Button("print","��ӡ�±���","bus110100","print(document.all.tableform)"));
	button.add(new Button("huier","�ݶ���ӡ","bus110200","huier_print()"));
	button.add(new Button("jiewen","���Ŵ�ӡ","bus110300","jiewen_print()"));
	//button.add(new Button("save","��������","bus110400","batchSubmit(document.all.tableform)")); 
	
	button.add(new Button("close", "�� ��","bus110400","closeWindow(\"\")"));  */
	
	
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "����ͻ�"));
	header.add(new Formatter("ivtcltnm", "���˿ͻ�"));
	header.add(new Formatter("ivttype", "�ʵ�����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("ivtfee", "����"));
	header.add(new Formatter("ivturgfee", "�Ӽ�����"));
	header.add(new Formatter("ivtlmqnt", "�����"));
	header.add(new Formatter("ivtlsqnt", "������"));
	header.add(new Formatter("temp01", "С��"));
	header.add(new Formatter("ivtpqnt", "�ؿ�̨��"));
	header.add(new Formatter("discount", "����"));
	header.add(new Formatter("ivtpamnt", "�ؿ���"));
	
	
	//header.add(new Formatter("ivtfree","����"));
	//header.add(new Formatter("ivttry","�Ի�"));
	
	header.add(new Formatter("temp02", "�½��", TagConstants.DF_CENTER));
	header.add(new Formatter("ivtnote", "��ע"));

/* 	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text", "ivtfee", "����"));
	batchInput.add(new Editor("text", "ivtpqnt", "���»ؿ���"));
	batchInput.add(new Editor("text", "discount", "�ۿ���"));
	batchInput.add(new Editor("text", "ivtpamnt", "�ؿ���"));
	batchInput.add(new Editor("text", "ivtnote", "��ע"));
	batchInput.add(new Editor("text", "ivtcltnm", "���˿ͻ�"));
	
	//batchInput.add(new Editor("text", "ivtfree", "����"));
	//batchInput.add(new Editor("text", "ivttry", "�Ի�"));
	
	batchInput.add(new Editor("text", "temp02", "�½��"));
	batchInput.add(new Editor("text", "temp01", "С��")); */
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtid", "id");
	hidden.put("ivtpdtid", "��ƷID");
	hidden.put("ivtgcltid", "����ͻ�ID");
	hidden.put("ivtyear","��");
	hidden.put("ivtmonth", "��");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtgcltid", "�������","true"));
	editors.add(new Editor("text", "ivtyear", "��","true"));
	editors.add(new Editor("text", "ivtmonth", "��","true"));
	editors.add(new Editor("text", "ivttype", "�˵�����"));
	editors.add(new Editor("text", "pdtnm", "��Ʒ����"));
	/* 
	editors.add(new Editor("date","start","��ʼ����","true"));
	editors.add(new Editor("date","end","��������","true")); 
	editors.add(new Editor("text", "ivtyear", "��","true"));
	editors.add(new Editor("text", "ivtmonth", "��","true")); */

	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	//pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("hidden", hidden);
	//pageContext.setAttribute("batchInput", batchInput);
%>
<script language="javascript">	
	$(document).ready(function(e) {
		var totalYSK = parseFloat(<%=totalYSK%>);
		
		var diffNum = parseFloat(0);//�ؿ���
		
		$("input[name=ivtfee]").attr('disabled','disabled');
		
		$("input[value=��������]").after("&nbsp;&nbsp;&nbsp;&nbsp;<input id='totalYSK' disabled='disabled' value="+ totalYSK +"></input>");

		/* $("input[name=ivtpamnt]").bind("focus", function(e) {
			diffNum = $(e.target).val();
		});*/
		
		
		//2012-3-29����ؿ���󴥷��¼�
		 $("input[name=ivtpamnt]").bind("click", function(e) {
			diffNum = $(e.target).val();
			var id = $(e.target).attr("id");//��ȡid�˴���idΪivtpamnt_rowx
			var suffix = id.substr(8);//id���λ��Ϊ8�ĺ�׺��id��0��ʼ���㣬�˴�suffixΪ_rowx
			var discount = parseFloat(0);
			if ($("#discount" + suffix).val() != "") {
				discount = $("#discount" + suffix).val();
			}
			
			var value = Math.round($("#ivtfee" + suffix).val() * $("#ivtpqnt" + suffix).val()* discount);
			if ( value != 0) {
				$("#ivtpamnt" + suffix).val(value);
			}
		});  
		

		$("input[name=ivtpamnt]").bind("blur", function(e) {
			totalYSK =  totalYSK - diffNum + parseFloat($(e.target).val());
			$("#totalYSK").val(totalYSK);
		}); 
		
		//ԭ������
		 /* $("input[name=ivtpqnt]").bind("blur", function(e) {
			var id = $(e.target).attr("id");
			var suffix = id.substr(7);
			alert("id:" + id);
			alert("suffix:" + suffix);
			var discount = parseFloat(0);
			if ($("#discount" + suffix).val() != "") {
				discount = $("#discount" + suffix).val();
				alert("discount:" + discount);
			}
			alert("discount1:" + discount);
			 var value = Math.round($("#ivtfee" + suffix).val() * $("#ivtpqnt" + suffix).val()
					* discount);
			 alert("value2:" + value);
			var leftNum = $("#temp01" + suffix).val() - $("#ivtpqnt" + suffix).val()- $("#ivtfree" + suffix).val()
			- $("#ivttry" + suffix).val();
			alert("ivtfree:"+$("#ivtfree" + suffix).val());
			alert("leftNum:" + leftNum);
			diffNum = $("#ivtpamnt" + suffix).val();
			alert("diffNum:" + diffNum);
			if (value != 0) {
				$("#ivtpamnt" + suffix).val(value);
			}
			$("#temp02" + suffix).val(leftNum);
			totalYSK = totalYSK - diffNum + value;
			$("#totalYSK").val(totalYSK);
		});    */
		
		//�������ͺ��Ի���2012-3-29���Ի���ʧȥ����������¼�
		$("input[name=ivttry]").bind("blur", function(e) {
			var id = $(e.target).attr("id");
			var suffix = id.substr(6);
			var leftNum = $("#temp01" + suffix).val() - $("#ivtpqnt" + suffix).val() - $("#ivtfree" + suffix).val()
			- $("#ivttry" + suffix).val(); 
			$("#temp02" + suffix).val(leftNum);
			totalYSK = totalYSK - diffNum + value;
			$("#totalYSK").val(totalYSK);
		}); 

       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=ivtgcltid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=ivtgcltid]").result(function(event, data, formatted) {
			if (data){
				//var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ivtgcltid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		/*$("input[name=ivtpamnt]").bind("propertychange",function(e) {
			alert('Handler for .change() called.');
			$("input[name=ivtpamnt]").each(function(index) {
				alert(index + ': ' + $(this).val());
				totalYSK += $(this).val();
			});
		});*/
	});
	function batchSubmit(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=batchSubmit&year="/>' + $("input");
		obj.submit();
		//window.location.href = "/" + lemis.WEB_APP_NAME
		//		+ "/business/BusinessAction.do?method=batchSubmit&"
		//		+ getAlldata(document.all.tableform);
	}
</script>

<script language="javascript">

	function print(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/ZSreport.jsp"/>';
		obj.submit();
	};
	function huier_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=report&type=huier&"
				+ getAlldata(document.all.tableform);
	};
	function jiewen_print() {
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=report&type=jiewen&"
				+ getAlldata(document.all.tableform);
	};
	
	function save(obj){
		obj.action = '<html:rewrite page="/SaleAction.do?method=enter&menuId=savent&"/>' + 'mgctid='+$("input[name=ivtgcltid]").val()
        +'&myear='+$("input[name=ivtyear]").val()+'&mmonth='+$("input[name=ivtmonth]").val();
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�±����ѯ" />
	<lemis:query action="/BusinessAction.do?method=yuejiequery&mark=zhishu"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		batchInputMeta="batchInput" topic="�±�����ϸ" hiddenMeta="hidden"
		mode="radio" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
