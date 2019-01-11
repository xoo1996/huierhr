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
   String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
	String total = (String)request.getSession().getAttribute("total");
    String deliveryTotal = (String)request.getSession().getAttribute("deliveryTotal");

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("folno", "������"));
	header.add(new Formatter("gctnm", "����ͻ�"));
	header.add(new Formatter("fdtcltnm", "���˿ͻ�"));
	header.add(new   Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("fdtqnt", "����"));
	header.add(new Formatter("fdtsqnt", "����"));
	header.add(new Formatter("fdtprc", "�ۼ�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "��������"));
	header.add(new Formatter("folsdt", "��������", "color:#000000;", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("folway", "������ʽ")); 
	header.add(new Formatter("folsno", "�����"));
	header.add(new Formatter("folsta", "����״̬"));
	header.add(new Formatter("foltype", "��������"));
	header.add(new Formatter("folsoprnm", "����Ա"));
	header.add(new Formatter("fdtnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("����","delivery(document.all.tableform)");
	//buttons.put("�޸Ķ���","modify(document.all.tableform)");
	buttons.put("ά�޼�¼��ѯ","repaircheck(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
/* 	hidden.put("repfolid", "������");
	hidden.put("repidentity", "ά����ˮ��");
	hidden.put("repgctnm", "���޵�λ����");
	hidden.put("repgctid", "���޵�λ����");
	hidden.put("repcltnm", "�û�����");
	hidden.put("reppid", "����������");
	hidden.put("repid", "������");
	hidden.put("repcpy", "ά�޳���");
	hidden.put("reppnm", "����������");
	hidden.put("fdtpid", "��Ʒ����"); */
	hidden.put("pdtid", "��Ʒ����");
	hidden.put("pdtnm","��Ʒ����");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folctid", "�ͻ�����"));
	editors.add(new Editor("date", "start", "����ʱ���"));
	editors.add(new Editor("date", "end", "��"));
	editors.add(new Editor("text", "pdtid", "��Ʒ����"));
	editors.add(new Editor("text", "foltype", "��������"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	

%>
<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript"><!--
/* 	//����
	function delivery(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		alert(getAlldata(obj));
		obj.action = '<html:rewrite page="/OrderAction.do?method=enterDelivery&"/>' + getAlldata(obj);
		obj.submit();
	};  */
	
	 //ά�޼�¼��ѯ
	function repaircheck(obj) {
 		var t = editObj("chk");
		if (!t) {
			return t;
		} 
		//obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/repair&page=/query.jsp"/>';
		obj.action = '<html:rewrite page="/OrderAction.do?method=queryHistory&"/>' + getAlldata(obj);
		obj.submit(); 
		/* window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/order/OrderAction.do?method=queryHistory&reppid="
		+ $("#pdtid").val();  */
	}; 
/* 	
	//ά�޼�¼��ѯ
	function repaircheck(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		} 
		//alert(request.getParameter("folno"));
		alert(getAlldata(obj));
		obj.action = '<html:rewrite page="/OrderAction.do?method=repairDetail&"/>' + getAlldata(obj);
		
		obj.submit();
	}; */
	
	$(document).ready(function(e){
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");

		
		$("input[name=folctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=folctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=folctid]").val(gid);
				$(this).parent().next().find("input").val(gnm);
			}
		});
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=all",
			 dataType:'json',
			 error:function(){
			   alert('��ȡ���ݴ���');
			 },
			 success:function(data){
						$("input[name=pdtid]").autocomplete(data,{
							max:10,
							matchContains:true,
							formatItem:function(data,i,max){
								return (data.proid + "=" + data.proname);
							}
						});	
						$("input[name=pdtid]").result(function(event, data, formatted) {
							if (data){
								var pid = data.proid;
								$("input[name=pdtid]").val(pid)
                        }
						});
					}
			});
		
	});
--></script>

<lemis:base />
<lemis:body>
	<lemis:title title="�ѷ���������ϸ" />
	<lemis:query action="/OrderAction.do?method=queryAllDetail&require=check" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="������ϸ��Ϣ"
		hiddenMeta="hidden"  mode="radio" />
	�����ϼƣ�<input type="text" disabled="disabled" value="<%=total%>">
	�����ϼƣ�<input type="text" disabled="disabled" value="<%=deliveryTotal%>">
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


