<!-- cfgmgmt/queryCI.jsp -->
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
	header.add(new Formatter("tmkfno", "������"));
	header.add(new Formatter("tmkgctnm", "�ͻ�����"));
	header.add(new Formatter("tmkcltnm", "�û�����"));
	header.add(new Formatter("tmksid", "���ƻ�������"));
	header.add(new Formatter("tmkplr", "��������"));
	header.add(new Formatter("tmkpnl","������"));
	header.add(new Formatter("pdtnm","�������ͺ�"));
	header.add(new Formatter("tmkpdt", "�ƻ��깤"));
	header.add(new Formatter("tmkpst", "����״̬"));
	header.add(new Formatter("tmkurg", "�Ƿ�Ӽ�"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("��ʼ��װ���","batchInstall(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmksid","���ƻ�������");
	hidden.put("tmkfno","������");
	hidden.put("tmkpnl","�����");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmksid","���ƻ�������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
		$("input[name=tmksid]:first").focus();
		$("input[name=tmksid]:first").val("");
		$("input[name=tmksid]").blur(function() {
			if ($("input[name=tmksid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function batchInstall() {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		if (confirm("ȷʵҪ��ʼ��װ�����")) {
			window.location.href = "/"
					+ lemis.WEB_APP_NAME
					+ "/customization/CustomizationAction.do?method=batchChange&"
					+ getAlldata(document.all.tableform)
					+ "require=startInstall";
		}
		//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
		else
			return t;
	}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="���ƻ���ѯ" />
	<lemis:query
		action="/CustomizationAction.do?method=query&order=install"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/CustomizationAction.do" headerMeta="header"
		topic="���ƻ���Ϣ" hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


