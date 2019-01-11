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
	header.add(new Formatter("repfolid", "������"));
	header.add(new Formatter("repgctnm", "�ͻ�����"));
	header.add(new Formatter("repcltnm", "�û�����"));
	header.add(new Formatter("repid", "������"));
	header.add(new Formatter("reppnm", "�������ͺ�"));
	header.add(new Formatter("repdate", "��������"));
	//header.add(new Formatter("repgctid", "���޵�λ"));
	header.add(new Formatter("reppdate", "�ƻ��깤"));
	header.add(new Formatter("repsta", "ά��״̬"));
	header.add(new Formatter("repcpy", "ά�޳���"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("���ƻ���ϸ��Ϣ","detailci(document.all.tableform)");
	buttons.put("ά�޼�¼�޸�","modify(document.all.tableform)");
	buttons.put("ά�޼�¼ɾ��","deleteci(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Button> buttons1=new ArrayList<Button>();
	buttons1.add(new Button("a","ά�޼�¼�޸�","rep020001","modify(document.all.tableform)"));
	buttons1.add(new Button("b","ά�޼�¼ɾ��","rep020002","deleteci(document.all.tableform)"));
	buttons1.add(new Button("c","�� ��","rep020003","history.back()"));
	buttons1.add(new Button("d","�� ��","rep020004","closeWindow(\"\")"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "ά����ˮ��");
	hidden.put("repcltnm", "�û�����");
	hidden.put("reppnm", "�������ͺ�");
	hidden.put("repsta","ά��״̬");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","������"));
	editors.add(new Editor("text","repid","������"));
	editors.add(new Editor("text","repcltnm", "�û�����"));
	editors.add(new Editor("text","repcpy", "ά�޳���"));
	editors.add(new Editor("text","repsta", "ά��״̬"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons1);
%>
<html>
<script language="javascript">
		//�޸ļ�¼
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/RepairAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//ɾ����¼
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("ȷ��Ҫɾ����ά�޼�¼��?")) {
				obj.action = '<html:rewrite page="/RepairAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="ά�޼�¼�޸Ĳ�ѯ" />
	<lemis:query action="/RepairAction.do?method=query&order=modify"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="ά�޼�¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


