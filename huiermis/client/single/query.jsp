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
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("ictid", "�û����"));
	header.add(new Formatter("ictnm", "�û�����"));
	header.add(new Formatter("gctnm", "��������"));
	header.add(new Formatter("ictsid","������"));
	header.add(new Formatter("ictpnm", "�ҳ�����"));
	header.add(new Formatter("ictgender", "�Ա�"));
	header.add(new Formatter("ictbdt", "��������"));
	header.add(new Formatter("ictaddr", "��ϵ��ַ", true));
	header.add(new Formatter("ictpcd", "��������"));
	header.add(new Formatter("icttel", "��ϵ�绰"));
	header.add(new Formatter("ictphis", "ʹ�ù�����������"));
	header.add(new Formatter("ictsrc", "��Դ"));
	header.add(new Formatter("ictnt", "��ע"));
	header.add(new Formatter("ictdate", "�Ǽ�����"));
	header.add(new Formatter("bvassess", "�طü�¼Ч������"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�鿴�ͻ�����","showdetail(document.all.tableform)");
	buttons.put("�鿴������ʷ","dingzhi(document.all.tableform)");
	buttons.put("�鿴ά����ʷ","weixiu(document.all.tableform)");
	//buttons.put("�鿴�˻���ʷ","tuiji(document.all.tableform)");
	buttons.put("�鿴���μ�¼","detailci(document.all.tableform)");
    buttons.put("�鿴�����¼", "fuzhentl(document.all.tableform)");
    buttons.put("�鿴�طü�¼","backvisit(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
/* 	
	Map<String,String> buttons1=new LinkedHashMap<String,String>();
	buttons1.put("�鿴��������ͼ","fuzhentl(document.all.tableform)");
	buttons1.put("�鿴����������ʷ","fuzhenyypg(document.all.tableform)");
    buttons1.put("�鿴������ʷ","fuzhensc(document.all.tableform)"); */
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "�û����");
	hidden.put("ictnm", "�û�����");
	hidden.put("ictgctid", "��������");
	hidden.put("gctnm", "��������");
	hidden.put("ictbdt", "��������");
	hidden.put("ictgender", "�Ա�");
	hidden.put("ictaddr", "��ϵ��ַ");
	hidden.put("icttel", "��ϵ�绰");
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ictnm", "�û�����"));
	editors.add(new Editor("text", "gctnm", "��������"));
	editors.add(new Editor("text", "ictsid", "������"));
	editors.add(new Editor("text", "ictaddr", "��ϵ��ַ"));
	editors.add(new Editor("text","ages","�����"));
	editors.add(new Editor("text","aget","��"));
	editors.add(new Editor("date","start","�Ǽ����ڴ�"));
	editors.add(new Editor("date","end","��"));
	editors.add(new Editor("text","pjsta","�Ƿ����"));
	editors.add(new Editor("text","bvassess","�طü�¼Ч������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "���˿ͻ���Ϣ��");//��ͷ
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>

<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>


<script language="javascript">
	function dingzhi(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryCus&"/>' + getAlldata(obj);
		obj.submit();
	};
	function weixiu(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryRep&"/>' + getAlldata(obj);
		obj.submit();
	};
	function tuiji(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryTui&"/>' + getAlldata(obj);
		obj.submit();
	};
	function detailci(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=view&tp=m&"/>' + getAlldata(obj);
		obj.submit();
	};
	function showdetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=showdetail&tp=m&"/>' + getAlldata(obj);
		obj.submit();
	};
	function fuzhen(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=querySC&"/>' + getAlldata(obj);
		obj.submit();
	};
	function backvisit(obj) {//
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryBV&"/>' + getAlldata(obj);
		obj.submit();
	};
	function fuzhentl(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryFZTL&"/>' + getAlldata(obj);
		obj.submit();
	};
	function fuzhenyypg(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryFZYYPG&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	function fuzhensc(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/SingleClientAction.do?method=queryFZSC&"/>' + getAlldata(obj);
		obj.submit();
	};
</script>

<script language="javascript">
	$(document).ready(function(){
		
		var ages = $("input[name=ages]").val();
		var aget = $("input[name=aget]").val();
		if(ages == '0'){
			$("input[name=ages]").val("");
		}
		if(aget == '0'){
			$("input[name=aget]").val("");
		}

		
		var grCli="";
		grCli=<%=dto.getBsc001()%>;
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		if(grCli=="1501000000")
		{
			$("input[name=gctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
				return data[0].substring(0);
				}
			});
		
			$("input[name=gctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=gctnm]").val(gnm);
				
					}
				});
		}
		else
		{
			$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
			$("input[name=gctnm]").attr("readonly","true");
			$("input[value=����[R]]").bind("click",function(e){
			$("input[name=gctnm]").val("<%=dto.getBsc012()%>");
					
			}); 
	 	}

	});

  
	
</script>
				
<lemis:base />
<lemis:body>
	<lemis:title title="���˿ͻ���ѯ" />
	<lemis:query action="/SingleClientAction.do?method=query"
		editorMeta="editor" topic="���˿ͻ���Ϣ��ѯ" />
	<lemis:table action="SingleClientAction.do" headerMeta="header"
		topic="���˿ͻ���Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


