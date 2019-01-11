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
	header.add(new Formatter("ivtyear","��"));
	header.add(new Formatter("gctid","�ͻ�����"));
	header.add(new Formatter("gctnm", "�ͻ�����"));
	header.add(new Formatter("gender", "�Ա�"));
	header.add(new Formatter("amount", "����"));
	header.add(new Formatter("total","������"));

	List<Formatter> hidden = new ArrayList<Formatter>();
	hidden.add(new Formatter("gcltid", "�ͻ�����"));
	hidden.add(new Formatter("ivtyear", "��"));
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","ivtyear","���","true"));
	editors.add(new Editor("text","ivtmonths","�´�","true"));
	editors.add(new Editor("text","ivtmontht","��","true"));
	editors.add(new Editor("text","ivtgcltid","�ͻ�����"));
	editors.add(new Editor("text","ages","�����"));
	editors.add(new Editor("text","aget","��"));
	editors.add(new Editor("text","ictgender","�Ա�"));
	

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script type="text/javascript">

$(document).ready(function(){


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
	<lemis:title title="�û�ͳ�Ʋ�ѯ" />
	<lemis:query action="/BusinessAction.do?method=clientquery" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="BusinessAction.do" headerMeta="header" topic="�û���ϸ��Ϣ"
		mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


