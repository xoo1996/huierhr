<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("qafno", "������"));
	header.add(new Formatter("qacltnm", "�û�����"));
	header.add(new Formatter("qasid", "������"));
	header.add(new Formatter("qapnm", "�������ͺ�"));
	header.add(new Formatter("qatype", "�ʼ����"));
	header.add(new Formatter("qastatus", "�ʼ�״̬"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�ʼ첽��1","test1before(document.all.tableform)");
	buttons.put("�ʼ첽��2","test(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("qaid", "�ʼ���ˮ��");
	hidden.put("qafno", "������");
	hidden.put("qasid", "������");
	hidden.put("qapnm", "�������ͺ�");
	hidden.put("qacltnm", "�û�����");
	hidden.put("qatype", "�ʼ����");
	hidden.put("qatest1","���");
	hidden.put("qatest2","���������");
	hidden.put("qatest3","���ֵ����");
	hidden.put("qatest4","1600Hz����");
	hidden.put("qatest5","Ƶ����Ӧ��Χ");
	hidden.put("qatest6","��Ч��������");
	hidden.put("qatest7","��ص���");
	hidden.put("qatest8","��г��ʧ��(500Hz)");
	hidden.put("qatest9","��г��ʧ��(800Hz)");
	hidden.put("qatest10","��г��ʧ��(1600Hz)");
	hidden.put("qachk","������");
	hidden.put("qapid","��Ʒ����");
	hidden.put("pdttype","��ƷƷ������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","qafno","������"));
	editors.add(new Editor("text","qasid","������"));
	editors.add(new Editor("text","qacltnm", "�û�����"));
	editors.add(new Editor("text","qatype", "�ʼ����"));
	editors.add(new Editor("text","pdtcls","�������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=qasid]:first").focus();
		$("input[name=qasid]:first").val("");
		$("input[name=qasid]").blur(function() {
			if ($("input[name=qasid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function test1before(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/QAAction.do?method=test1before&"/>' + getAlldata(obj);
		obj.submit();
	}
	function test(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/QAAction.do?method=test&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�ʼ�" />
	<lemis:query action="/QAAction.do?method=query&order=test"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="QAAction.do" headerMeta="header" topic="�ȴ��ʼ��¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


