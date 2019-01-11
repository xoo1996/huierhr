<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String tmecltid = (String) request.getSession().getAttribute("tmecltid");
	String ictnm = (String) request.getSession().getAttribute("ictnm");
	String tmectid = (String) request.getSession().getAttribute("tmectid");
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tmeno", "������"));
	header.add(new Formatter("tmecltid","�û�����"));
	header.add(new Formatter("foldt", "¼������"));
	header.add(new Formatter("pdtnm", "��ģ����"));
	header.add(new Formatter("tmesta","��ģ״̬"));
	header.add(new Formatter("tmemat", "��ģ����"));
	header.add(new Formatter("tmegif", "���"));
	header.add(new Formatter("tmemaker", "����Ա"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����","newEM(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	buttons.put("����","history.back()");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmecltid", "�û����");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	request.getSession().setAttribute("tmecltid",tmecltid);
	
%>
<html>
<script language="javascript">
  		function newEM(obj){
			obj.action = '<html:rewrite href="/huiermis/earmould/EarMouldAction.do?method=addNew&"/>'+getAlldata(obj);			
            if(confirm("ȷʵҪ������ģ������"))
            {
			  obj.submit();
            }
  		};
</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="���˿ͻ���ģ��ѯ" />
		<lemis:tabletitle title="���˿ͻ�������Ϣ" />
		<table class="tableInput">
			<lemis:editorlayout />
			<html:form action="/EarMouldAction.do" method="POST">
				<tr>
					<lemis:texteditor property="tmecltid" label="�û����" value="<%=tmecltid %>" disable="true" />
					<lemis:texteditor property="ictnm" label="�û�����" value="<%=ictnm %>" disable="true" />
					<lemis:texteditor property="tmectid" label="��������" value="<%=tmectid %>" disable="true" />
				</tr>
			</html:form>
		</table>
		<lemis:table action="/EarMouldAction.do" headerMeta="header" topic="���˿ͻ���ģ��Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>