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
	header.add(new Formatter("folno", "������"));
	header.add(new Formatter("folctid", "�ͻ�����"));
	header.add(new Formatter("folctnm", "�ͻ�����"));
	header.add(new Formatter("foldt", "��������"));
	header.add(new Formatter("folsta", "����״̬"));
	header.add(new Formatter("foltype","��������"));
	header.add(new Formatter("foloprnm", "¼��Ա"));
	header.add(new Formatter("folnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("��ӡ��ͨ����","print_huier(document.all.tableform)");
	//buttons.put("���Ŵ�ӡ","print_jiewen(document.all.tableform)");
	buttons.put("����","delivery(document.all.tableform)");
	//buttons.put("�޸Ķ���","modify(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folno", "��������");
	hidden.put("folctid", "�ͻ�����");
	hidden.put("folctnm", "�ͻ�����");
	hidden.put("foltype", "��������");
	hidden.put("foldt", "��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "������"));
	editors.add(new Editor("text", "folctid", "�ͻ�����"));
	editors.add(new Editor("date","foldt", "��������"));
	
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
	function print_huier(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=huier&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	function print_jiewen(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=jiewen&"/>' + getAlldata(obj);
		obj.submit();
	};
	//����
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	};
	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=folctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=folctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=folctid]").val(gid);
				$(this).parent().next().find("input").val(gnm);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="������������ѯ" />
	<lemis:query action="/OrderAction.do?method=query&order=delivery"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="������Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


