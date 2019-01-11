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
	header.add(new Formatter("stogrcliid", "�ͻ�����"));
	header.add(new Formatter("stogrpnm","�ͻ�����"));
	header.add(new Formatter("stoproname", "��Ʒ����"));
	header.add(new Formatter("storqnt", "ʣ������"));
	header.add(new Formatter("stoexp", "�������"));
	header.add(new Formatter("stodate", "�������","color:#000000;",TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("stoisexp", "�Ƿ���"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ӡ","print()"); 
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stogrcliid","�ͻ�����");
	hidden.put("stoproid","��Ʒ����");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "stogrcliid", "�ͻ�����"));
	editors.add(new Editor("text", "stoproid", "��Ʒ����"));
	editors.add(new Editor("text", "stoisexp", "�Ƿ���"));
 	editors.add(new Editor("date", "start", "������ڴ�"));
	editors.add(new Editor("date", "end", "��"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

	$(document).ready(function(){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=stogrcliid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=stogrcliid]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=stogrcliid]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
		
	});
	
	function add(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/business&page=/amortize/new.jsp"/>';
		obj.submit();
	}
	
	function modify(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=showAmortize&"/>' + getAlldata(obj);
		obj.submit();
	}
	
	function del(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		if(!confirm("ȷ��ɾ��������¼��")){
			return false;
		}
		obj.action='<html:rewrite page="/BusinessAction.do?method=deleteAmortize&"/>' + getAlldata(obj);
		obj.submit();
	}
	
	function print() {
		var t = editObj("chk");
		if(!t){
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=amortizeReport&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="��泬�ڲ�ѯ" />
	<lemis:query action="/BusinessAction.do?method=amortizeQuery"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		topic="�����Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>