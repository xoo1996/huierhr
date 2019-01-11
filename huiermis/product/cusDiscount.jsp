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
	/* header.add(new Formatter("pdtid", "��Ʒ����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtcls", "�������")); */
	header.add(new Formatter("tdsnm", "������Ʒ��"));
	header.add(new Formatter("tdspvoin", "ʡ�ڿ���"));
	header.add(new Formatter("tdspvoout", "ʡ�����"));
	header.add(new Formatter("tdsnt", "��ע"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("��ϸ","detail(document.all.tableform)");
	buttons.put("�����޸�","batchmd(document.all.tableform)");
	 /*buttons.put("ɾ��","del(document.all.tableform)"); */
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tdsnm","������Ʒ��");
	hidden.put("pdttype","������Ʒ��");
	
	List<Editor> editors = new ArrayList<Editor>();
	/* editors.add(new Editor("text", "pdtid", "��Ʒ����"));
	editors.add(new Editor("text", "pdtnm", "��Ʒ����")); */
	editors.add(new Editor("text", "pdttype", "������Ʒ��"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
	batchInput.add(new Editor("text", "tdspvoin", "ʡ�ڿ���"));
	batchInput.add(new Editor("text", "tdspvoout", "ʡ�����"));
	batchInput.add(new Editor("text", "tdsnt", "��ע"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("batchInput", batchInput);
	pageContext.setAttribute("button", buttons);
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>


<script language="javascript">
	
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
			+ "/product/ProductAction.do?method=batchModTypDis&"
			+ getAlldata(document.all.tableform);
};

	function detail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=queryProByTp&"/>' + getAlldata(obj);
		obj.submit();
	};
	function del(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ProductAction.do?method=delete&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>



<lemis:base />
<lemis:body>
	<lemis:title title="������Ʒ����С���ʹ���" />
	<lemis:query action="/ProductAction.do?method=query&mark=typDis"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/ProductAction.do" headerMeta="header"
		topic="Ʒ����С������Ϣ" hiddenMeta="hidden" mode="checkbox" batchInputMeta="batchInput"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


