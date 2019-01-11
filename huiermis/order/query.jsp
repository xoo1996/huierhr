<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("fdtfno", "������"));
	header.add(new Formatter("gctnm", "��������"));
	header.add(new Formatter("cltnm", "���˿ͻ�")); 
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("fdtpid","��Ʒ����"));
	header.add(new Formatter("tmksid", "������"));
	header.add(new Formatter("fdtqnt", "����"));
	header.add(new Formatter("fdtsqnt", "����"));
	header.add(new Formatter("fdtdprc", "�۸�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "��������"));
	header.add(new Formatter("tmkpdt","�ƻ��깤����"));
	header.add(new Formatter("folsdt", "��������"));
	header.add(new Formatter("folway", "������ʽ"));
	header.add(new Formatter("folsno","�����"));
	header.add(new Formatter("folsta", "����״̬"));
	header.add(new Formatter("foltype", "��������"));
	header.add(new Formatter("foloprnm", "�������Ա"));
	header.add(new Formatter("fdtnt", "��ע"));
	header.add(new Formatter("isbill", "�Ƿ��ѿ�Ʊ"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
/* 	buttons.put("�ݶ���ӡ","huier_print()");*/
	buttons.put("��ӡ��ͨ�������ݶ���","print_huier(document.all.tableform)");
	buttons.put("��ӡ��ͨ���������ţ�","print_jiewen(document.all.tableform)");
	buttons.put("��ӡ���ƶ���","printCusOrder(document.all.tableform)"); 
	buttons.put("������ϸ","print(document.all.tableform)");
	buttons.put("��Ʊ/ȡ����Ʊ","bill(document.all.tableform)");
	buttons.put("Ǩ����ͨ������ʷ����","trans(document.all.tableform)");
	buttons.put("�޸�","modify(document.all.tableform)");
	buttons.put("ɾ��","deleteOrder(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	
	List<Button> buttons1=new ArrayList<Button>();
	buttons1.add(new Button("a","��ӡ��ͨ�������ݶ���","ord020007","print_huier(document.all.tableform)"));
	buttons1.add(new Button("b","��ӡ��ͨ���������ţ�","ord020008","print_jiewen(document.all.tableform)"));
	buttons1.add(new Button("c","��ӡ���ƶ���","ord020009","printCusOrder(document.all.tableform)"));
	buttons1.add(new Button("d","������ϸ","ord020010","print(document.all.tableform)"));
	buttons1.add(new Button("h","��Ʊ/ȡ����Ʊ","ord020014","bill(document.all.tableform)"));
	buttons1.add(new Button("i","Ǩ����ͨ������ʷ����","ord020015","trans(document.all.tableform)"));
	buttons1.add(new Button("e","�޸�","ord020011","modify(document.all.tableform)"));
	buttons1.add(new Button("f","ɾ��","ord020012","deleteOrder(document.all.tableform)"));
	buttons1.add(new Button("g","�� ��","ord020013","closeWindow(\"\")"));
	
	buttons1.add(new Button("a","��ӡ��ͨ�������ݶ���","zsd030101","print_huier(document.all.tableform)"));
	buttons1.add(new Button("b","��ӡ��ͨ���������ţ�","zsd030102","print_jiewen(document.all.tableform)"));
	buttons1.add(new Button("c","��ӡ���ƶ���","zsd030103","printCusOrder(document.all.tableform)"));
	buttons1.add(new Button("d","������ϸ","zsd030104","print(document.all.tableform)"));
	buttons1.add(new Button("e","�޸�","zsd030105","modify(document.all.tableform)"));
	buttons1.add(new Button("f","ɾ��","zsd030106","deleteOrder(document.all.tableform)"));
	buttons1.add(new Button("g","�� ��","zsd030107","closeWindow(\"\")"));

	buttons1.add(new Button("a","��ӡ��ͨ�������ݶ���","jmd030101","print_huier(document.all.tableform)"));
	buttons1.add(new Button("b","��ӡ��ͨ���������ţ�","jmd030102","print_jiewen(document.all.tableform)"));
	buttons1.add(new Button("c","��ӡ���ƶ���","jmd030103","printCusOrder(document.all.tableform)"));
	buttons1.add(new Button("d","������ϸ","jmd030104","print(document.all.tableform)"));
	buttons1.add(new Button("e","�޸�","jmd030105","modify(document.all.tableform)"));
	buttons1.add(new Button("f","ɾ��","jmd030106","deleteOrder(document.all.tableform)"));
	buttons1.add(new Button("g","�� ��","jmd030107","closeWindow(\"\")"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtfno", "��������");
	hidden.put("folno","������");
	hidden.put("foltype","��������");
	hidden.put("folindctid","���˿ͻ�����");
	hidden.put("folsta","����״̬");
	hidden.put("gctnm","��������");
	hidden.put("gctid","��������");
	hidden.put("folctid","����ͻ����");
	hidden.put("foldt","��������");
	hidden.put("folsta","����״̬");
	hidden.put("cltnm","���˿ͻ�");
	hidden.put("fdtpid","��Ʒ����");
	hidden.put("isbill","�Ƿ��ѿ�Ʊ");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "������"));
	editors.add(new Editor("text","gctnm","��������"));
	editors.add(new Editor("text", "cltnm", "���˿ͻ�"));
	editors.add(new Editor("text", "folsta", "����״̬"));
	
	editors.add(new Editor("date","start","�������ڴ�"));
	editors.add(new Editor("date","end","��"));
	editors.add(new Editor("text", "foltype", "��������"));
	editors.add(new Editor("text","folsno","�����"));
	editors.add(new Editor("text","fdtpid","��Ʒ����"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons1);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
			var grCli="";
			grCli=<%=dto.getBsc001()%>;
			if(grCli=="1501000000")
			{
				var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
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
						/* 	$("input[@type=hidden][name=folctid]").val(gid);
						$("input[@type=hidden][name=folctid]").val(); */
					
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
	//��Ʒ����������
	$(document).ready(function(){
		//$("input[name=pdtid]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('��ȡ���ݴ���');
				 },
				 success:function(data){
							$("input[name=fdtpid]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=fdtpid]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									$("input[name=fdtpid]").val(pid);
									

								}
							});
						}
				});
		//});
		});	
	
	function modify(obj) {
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=orderDetail&tp2=m&"/>'+getAlldata(obj);			
		obj.submit();
	};
	function bill(obj) {
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=bill&"/>'+getAlldata(obj);			
		obj.submit();
	};
	
	function deleteOrder(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		if(confirm("ȷ��Ҫɾ���ö�����?")) {
			obj.action = '<html:rewrite page="/OrderAction.do?method=delete&order=del&tp2=m&"/>'+getAlldata(obj);		
			obj.submit();
		}
	};
	function print_huier(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=huier&"/>' + getAlldata(obj);
		obj.submit();
	};
	function print_jiewen(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/OrderAction.do?method=print&type=jiewen&"/>' + getAlldata(obj);
		obj.submit();
	};
	function printCusOrder(obj){
		var t = editObj("chk");
			if (!t) {
				return t;
			}
			else {
				obj.action = '<html:rewrite page="/OrderAction.do?method=cusOrderReport&tp=q&"/>'+getAlldata(obj);		
				obj.submit();
			}
	};
	function trans(obj){
		var t = editObj("chk");
			if (!t) {
				return t;
			}
			else {
				obj.action = '<html:rewrite page="/OrderAction.do?method=trans"/>';		
				obj.submit();
			}
	};
</script>
<script language="javascript">
//�鿴������ϸ
	function print(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/OrderAction.do?method=orderDetail&tp2=s&"/>'+getAlldata(obj);	
	obj.submit();
	};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="������ѯ" />
	<lemis:query action="/OrderAction.do?method=query&tp=q" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="������Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


