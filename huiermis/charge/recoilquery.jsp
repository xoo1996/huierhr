<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("chgid", "�շѺ�"));                       
	header.add(new Formatter("gctnm", "����ͻ�"));
	header.add(new Formatter("ictnm", "���˿ͻ�"));
	header.add(new Formatter("chgdt", "�շ�����"));
/* 	header.add(new Formatter("chgrecdt", "�˻�����"));
	header.add(new Formatter("chgrecheaddt", "�˻��ܲ�����")); */

	//List<Button> buttons=new ArrayList<Button>();
	//buttons.add(new Button("tui","�� ��","ord020001","tuiji(document.all.tableform)"));
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�շ���ϸ","detail(document.forms[0])");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("chgid", "�շѺ�");
	hidden.put("gctnm", "����ͻ�");
	hidden.put("ictnm", "���˿ͻ�");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "chgid", "�շѺ�"));
	editors.add(new Editor("text", "gctnm", "����ͻ�"));
	editors.add(new Editor("text", "ictnm", "���˿ͻ�"));
	editors.add(new Editor("date","start","�շ����ڴ�"));
	editors.add(new Editor("date","end","��"));

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
$(document).ready(function(){
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	if(grCli=="1501000000")
	{
		$("input[name=gctnm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
				return data[0];
			}
		});
		
		$("input[name=gctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1);
				$("input[name=gctnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
	}
	else
	{
		$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=gctnm]").attr("readonly","true");
		$("input:hidden[name=gctnm]").val("<%=dto.getBsc011()%>");
		
		
 	}
	
	
	
	
	
});



function detail(obj) {
	var t = delObj("chk");
	if(!t){
		return t;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=norChgRecDetail&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
};

function commit(obj) {
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=c&"/>' + getAlldata(document.all.tableform);
	if (confirm("ȷʵҪ�ύ��")) {
		obj.submit();
	}
};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�˻���ѯ" />
	<lemis:query action="/ChargeAction.do?method=query&charge=recoil" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="�˻�������Ϣ" 
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


