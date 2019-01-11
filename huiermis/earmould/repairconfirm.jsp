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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("repfolid", "������"));
	header.add(new Formatter("repgctnm", "�ͻ�����"));
	header.add(new Formatter("repcltnm", "�û�����"));
	header.add(new Formatter("repid", "������"));
	header.add(new Formatter("reppnm", "��Ʒ����"));
	header.add(new Formatter("repdate", "��������"));
	header.add(new Formatter("repgctid", "���޵�λ"));
	header.add(new Formatter("reppdate", "�ƻ��깤"));
	header.add(new Formatter("repsta", "ά��״̬"));
	header.add(new Formatter("repcpy", "ά�޳���"));
	header.add(new Formatter("repamt", "ά�޷�", TagConstants.DF_LEFT,TagConstants.DT_MONEY));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("¼��ά�޴�ʩ","input(document.all.tableform)");
	buttons.put("�鿴","detailci(document.all.tableform)");
	buttons.put("ά�����","finish(document.all.tableform)");
	buttons.put("��ά��","stop(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "ά����ˮ��");
	hidden.put("repid", "��Ʒ���");
	hidden.put("reppid", "��ƷID");
	hidden.put("repamt", "ά�޷�");
	hidden.put("repfolid", "������");
	hidden.put("repcpy", "ά�޳���");
	hidden.put("repcltnm", "�û�����");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","������"));
	editors.add(new Editor("text","repid","������"));
	editors.add(new Editor("text","repcltnm", "�û�����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=repid]:first").focus();
		$("input[name=repid]:first").val("");
		$("input[name=repid]").blur(function() {
			if ($("input[name=repid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	//��ʾ��ϸ��Ϣ
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=print&"/>' + getAlldata(obj);
		obj.submit();
	};
	function input(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=repair&require=repair&"/>' + getAlldata(obj);
		obj.submit();
	};
	function finish(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("ȷʵҪȷ��ά�������")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=change&require=finish&tp=ear&"/>' + getAlldata(obj);
			obj.submit();
		} else
			return t;
	};
	function stop(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		if (confirm("��ά�޲�ȡ��������")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=change&require=stop&tp=ear&"/>' + getAlldata(obj);
			obj.submit();
		}
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="ά�޼�¼��ѯ" />
	<lemis:query action="/RepairAction.do?method=query&order=repconfirm"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="ά�޼�¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


