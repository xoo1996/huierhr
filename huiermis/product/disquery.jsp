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
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("pdtid", "��Ʒ����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtcls", "�������"));
	header.add(new Formatter("pdttype", "������Ʒ��"));
	header.add(new Formatter("pdtprc", "����"));
	header.add(new Formatter("discount", "����"));
	header.add(new Formatter("disprice", "�ɱ���"));
	header.add(new Formatter("pdtnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("��ӿ���","add(document.all.tableform)");
	buttons.put("�����޸�","modify(document.all.tableform)");
	buttons.put("�����޸�","batchmd(document.all.tableform)");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("pdtid","��Ʒ����");
	hidden.put("pdtcls", "�������");
	hidden.put("pdttype", "������Ʒ��");
	hidden.put("pdtprc", "��������");
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text","pdtprc","����"));
	batchInput.add(new Editor("text","discount","����"));
	batchInput.add(new Editor("money","disprice","�ɱ���"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "pdtid", "��Ʒ����"));
	editors.add(new Editor("text", "pdtnm", "��Ʒ����"));
	editors.add(new Editor("text", "pdtp", "��Ʒ���"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	
%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function compute(e) {
		var id = $(e.target).attr("id");
		var suffix = id.substr(8);
		var value = $("#pdtprc" + suffix).val() * $("#discount" + suffix).val();
		$("#disprice" + suffix).val(value);
	};

	$(document).ready(function(e) {
		$("input[name=discount]").bind("blur", function(e) {
			compute(e);
		});
		$("input[name=pdtprc]").attr('disabled', 'disabled');
	});

	function modify(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=editdis&"/>' + getAlldata(obj);
		obj.submit();
	};
	function batchmd(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/product/ProductAction.do?method=add&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="��Ʒ����ά��" />
	<lemis:query action="/ProductAction.do?method=disquery&id=pdis"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="��Ʒ��Ϣ" hiddenMeta="hidden" mode="checkbox"
		batchInputMeta="batchInput" batchInputType="update" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>