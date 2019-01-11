<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	//��10��������
	header.add(new Formatter("repfolid", "������"));
	header.add(new Formatter("repgctnm", "�ͻ�����"));
	header.add(new Formatter("repcltnm", "���˿ͻ�"));
	header.add(new Formatter("repid", "������"));
	header.add(new Formatter("reppnm", "����������"));
	header.add(new Formatter("repdate", "��������","", TagConstants.DT_YEAR_MONTH_DATE));
	/* header.add(new Formatter("repgctid", "���޵�λ")); */
	header.add(new Formatter("foltype","��������"));
	header.add(new Formatter("repcpy", "ά�޳���"));
	header.add(new Formatter("repamt", "ά�޷�"));
	header.add(new Formatter("folischg", "�Ƿ����շ�"));
	
	   
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","weixiushoufei(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repfolid","������");
	hidden.put("folischg","�Ƿ����շ�");
	hidden.put("repgctnm","�ͻ�����");
	hidden.put("repdate","��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "repfolid", "������"));
	editors.add(new Editor("text", "repcltnm", "���˿ͻ�"));	//repcltnm�����ݱ�AA10�в����ڣ����Բ���ʾ�����б�
	editors.add(new Editor("text", "folischg", "�Ƿ����շ�"));	//�˴�folischgΪ���������ڵ�ά�޷��ã������б�folischg�����ݱ�AA10�д��ڣ�������ʾ�����б�
	editors.add(new Editor("date","start","�������ڴ�"));
	editors.add(new Editor("date","end","��"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function weixiushoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ChargeAction.do?method=repairChargeDetail&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<script>
	$(document).ready(function(){
		$("select[name=folischg]").val("0");
	});
</script>
<lemis:base />
<lemis:body>

    
    <lemis:title title="�շѲ�ѯ" />
	<lemis:query action="/ChargeAction.do?method=query&charge=repair" editorMeta="editor" topic="��ѯ����" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="�շ���Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


