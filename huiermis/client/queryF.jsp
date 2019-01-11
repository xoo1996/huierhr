<!-- client/queryF.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictnm", "�û�����"));
	header.add(new Formatter("icttel", "�û��绰"));
	header.add(new Formatter("ictaddr", "�û���ַ"));
	header.add(new Formatter("fdtpid", "��Ʒ����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("fdtqnt", "����"));
	header.add(new Formatter("pdtprc", "��Ʒ����"));
	header.add(new Formatter("foldt", "ѡ��ʱ��"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("����Excel","statistics_dc(document.all.tableform)");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("start", "ͳ�����ڴ�");
	hidden.put("end", "��");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("date","start","ͳ�����ڴ�"));
	editors.add(new Editor("date","end","��"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "ͳ�Ƶ���");//��ͷ
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
 
<script language="javascript">
	
	function statistics_dc(obj){
		obj.action = '<html:rewrite page="/ReservationAction.do?method=statistics_query"/>'+getAlldata(obj);			
		obj.submit();
	}
	
</script>
				
<lemis:base />
<lemis:body>
	<lemis:title title="�ͻ�ͳ�Ʋ�ѯ" />
	<lemis:query action="/TjAction.do?method=tj"
		editorMeta="editor" topic="ͳ������" />
	<lemis:table action="TjAction.do" headerMeta="header"
		topic="�ͻ�ͳ����Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


