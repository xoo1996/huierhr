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
	header.add(new Formatter("tmksid", "������"));
	header.add(new Formatter("fdtqnt", "����"));
	header.add(new Formatter("fdtsqnt", "����"));
	header.add(new Formatter("fdtdprc", "�۸�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("foldt", "��������"));
	header.add(new Formatter("folexdt", "�������"));
	header.add(new Formatter("folsdt", "��������"));
	header.add(new Formatter("folway", "������ʽ"));
	header.add(new Formatter("folsno","�����"));
	header.add(new Formatter("folsta", "����״̬"));
	header.add(new Formatter("foltype", "��������"));
	header.add(new Formatter("foloprnm", "�������Ա"));
	header.add(new Formatter("fdtnt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("������ϸ","print(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

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
	hidden.put("pdtid","��Ʒ����");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","gctnm","��������"));
	editors.add(new Editor("text", "cltnm", "���˿ͻ�"));
	editors.add(new Editor("text", "foltype", "��������"));
	editors.add(new Editor("text", "pdtid", "��Ʒ����"));
	editors.add(new Editor("text", "pdtnm", "��Ʒ����"));
	editors.add(new Editor("date","exstart","���ͨ�����ڴ�","true"));
	editors.add(new Editor("date","exend","��"));
	
	
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
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

			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
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
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=pdtid]").val(pid);
									$("input[name=pdtnm]").val(pnm);
									$(this).parent().next().find("input").val(pid);

								}
							});
						}
				});
			//$("select[name=foltype]").val("make");
			
	});
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
	<lemis:query action="/OrderAction.do?method=querycount" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="������Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


