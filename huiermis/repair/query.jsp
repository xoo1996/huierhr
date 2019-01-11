<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
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
	header.add(new Formatter("reppnm", "����������"));
	header.add(new Formatter("pdtcls", "�������"));
	header.add(new Formatter("repdate", "��������","", TagConstants.DT_YEAR_MONTH_DATE));
	//header.add(new Formatter("repgctid", "���޵�λ"));
	header.add(new Formatter("reppdate","�ƻ��깤"));
	header.add(new Formatter("repsta", "ά��״̬"));
	header.add(new Formatter("repcls", "ά�����"));
	header.add(new Formatter("repcpy", "ά�޳���"));
	header.add(new Formatter("repamt", "ά�޷�"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("����","newSC(document.all.tableform)");
	buttons.put("ά��������ʷ","weixiu(document.all.tableform)");
	buttons.put("���ƻ�ά����ʷ","history(document.all.tableform)");
    buttons.put("ά�޼�¼��ϸ��Ϣ","detailci(document.all.tableform)");
	buttons.put("��ӡ������","printer(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("repidentity", "ά����ˮ��");
	hidden.put("repgctnm", "���޵�λ����");
	hidden.put("repgctid", "���޵�λ����");
	hidden.put("repcltnm", "�û�����");
	hidden.put("reppid", "����������");
	hidden.put("repid", "������");
	hidden.put("repcpy", "ά�޳���");
	hidden.put("reppnm", "����������");
	hidden.put("repcltid","�û�����");
	
	hidden.put("tmksid","������");
	hidden.put("tmkcltid","�û�����");
	hidden.put("tmkcltnm","�û�����");
	hidden.put("tmkgctnm","��������");
	hidden.put("tmkfno","������");
	hidden.put("tmkpnm","����������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","repfolid","������"));
	editors.add(new Editor("text","repid","������"));
	editors.add(new Editor("text","repcltnm", "�û�����"));
	editors.add(new Editor("text","repgctnm", "�ͻ�����"));
	editors.add(new Editor("text","repcpy", "ά�޳���"));
	editors.add(new Editor("text","repsta", "ά��״̬"));
	editors.add(new Editor("text","reppid", "��Ʒ����"));
	editors.add(new Editor("text","reppnm", "��Ʒ����"));
	editors.add(new Editor("text","pdtcls", "�������"));	
	editors.add(new Editor("text","repcls", "ά�����"));
	editors.add(new Editor("date","start", "�깤���ڴ�"));
	editors.add(new Editor("date","end", "��"));
	editors.add(new Editor("date","reppdate", "�ƻ��깤����"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>

<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function() {
		$(":checkbox:enabled").attr('checked', true);
		$("input[name=repid]:first").focus();
		$("input[name=repid]:first").val("");
		$("input[name=repid]").blur(function() {
			if ($("input[name=repid]:first").val() != "") {
				$("#queryForm").submit();
			}
		});
	});
	function newSC(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		
		obj.action = '<html:rewrite page="/RepairAction.do?method=enter2&"/>' + getAlldata(obj);
		obj.submit();
	};
	function weixiu(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=queryRep&"/>' + getAlldata(obj);
		obj.submit();
	};
	//ά����ʷ
	function history(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		//obj.action = '<html:rewrite page="/RepairAction.do?method=history&"/>' + getAlldata(obj);
		obj.action='<html:rewrite href="../customization/CustomizationAction.do?method=queryHistory&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	
	//��ʾ��ϸ��Ϣ
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		getAlldata(obj);
		obj.action = '<html:rewrite page="/RepairAction.do?method=print&"/>' + getAlldata(obj);
		obj.submit();
	};
	function printer(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/RepairAction.do?method=barcode&"/>' + getAlldata(obj);
		obj.submit();
	}
</script>

<script language="javascript">
$(document).ready(function(){
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	if(grCli=="1501000000")
	{
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=repgctnm]").autocomplete(shops,{
			max:1000,
			matchContains:true,
			formatItem:function(data,i,max){
			return data[0].substring(0);
			}
		});
	
		$("input[name=repgctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=repgctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
				$("input[@type=hidden][name=folctid]").val(); */
			
				}
			});
	}
	else
	{
		$("input[name=repgctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=repgctnm]").attr("readonly","true");
		$("input[value=����[R]]").bind("click",function(e){
		$("input[name=repgctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}
	
	
});
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="ά�޼�¼��ѯ" />
	<lemis:query action="/RepairAction.do?method=query" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="RepairAction.do" headerMeta="header" topic="ά�޼�¼"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


