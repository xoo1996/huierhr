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
   // String pdtid = (String)request.getSession().getAttribute("pdtid");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("ivtfee", "�۸�"));
	header.add(new Formatter("temp01", "�����"));
	header.add(new Formatter("ivttype", "��������"));
	
	List<Editor> editor = new ArrayList<Editor>();
	editor.add(new Editor("text","ivtgcltid","�ͻ�����"));
	editor.add(new Editor("text", "ivtyear", "��"));
	editor.add(new Editor("text", "ivtmonth", "��"));
	editor.add(new Editor("text","ivtpdtid","��Ʒ����",true));
	
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
	
    pageContext.setAttribute("button", buttons);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("editor", editor);
    
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(e) {
		
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=ivtgcltid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=ivtgcltid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=ivtgcltid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>

<lemis:base />
<lemis:body>
    <lemis:title title="�������" />
   <lemis:query action="/BusinessAction.do?method=jcdetail"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/BusinessAction.do" headerMeta="header" topic="�����Ϣ" 
	     mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>