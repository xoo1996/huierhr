<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("taraccount", "�˺�",TagConstants.DF_CENTER));
	header.add(new Formatter("bsc012", "�˺�����",TagConstants.DF_CENTER));
	header.add(new Formatter("useremployid", "�ϼ�����",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "Ա������",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("�� ��","bind(document.all.tableform)");
	buttons.put("�� ��","disbind(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("taraccount", "�˺�");
	hidden.put("useremployid", "�ϼ�����");
	hidden.put("bsc012", "�˺�����");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "�˺�"));
	editors.add(new Editor("text", "useremployid", "�ϼ�����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "�˺�-�ϼ����Ű�");//��ͷ

%>
<html>
	<script language="javascript">
	
		//����Ϣ
  		function bind(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/UserInfoAction.do?method=tobindAccSup&"/>'+getAlldata(obj);			
			obj.submit();
  		}
  	//����Ϣ
  		function disbind(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("ȷ��Ҫ�����˺�-�ϼ�������")){
				obj.action = '<html:rewrite page="/UserInfoAction.do?method=disbindAccSup&"/>'+getAlldata(obj);			
				obj.submit();
			}
  		}
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="�˺�-�ϼ����Ű�" />
		<lemis:query action="/UserInfoAction.do?method=queryAccSup&tp=acc" editorMeta="editor" topic="�˺���Ϣ��ѯ" />
		<lemis:table action="UserInfoAction.do" headerMeta="header" topic="�˺�-�ϼ�������Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


