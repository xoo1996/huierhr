<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	
	header.add(new Formatter("gcltnm", "�ͻ�����"));
	header.add(new Formatter("stoproid", "��Ʒ����"));
	header.add(new Formatter("stoproname", "��Ʒ����"));
	header.add(new Formatter("stoamount", "����"));
	header.add(new Formatter("stoamountun", "��Ʒ��λ"));
	header.add(new Formatter("stoproprice", "�ۼ�", "color:#000000;", TagConstants.DT_MONEY));
	header.add(new Formatter("stoactype", "����"));
	header.add(new Formatter("stodate","����","color:#000000;", TagConstants.DT_YEAR_MONTH_DATE));
	header.add(new Formatter("storemark","��ע"));


	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�û���ѯ","check(document.all.tableform)");
	buttons.put("�� ��","shoufei(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stoproid", "��Ʒ����");
	hidden.put("stoid","������");
	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "stoproid", "��Ʒ����"));
	editors.add(new Editor("text", "stoactype", "����"));	
	editors.add(new Editor("text", "pdtut", "��Ʒ��λ"));
	editors.add(new Editor("date","start","������ڴ�"));
	editors.add(new Editor("date","end","��"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
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
	function check(obj){
		obj.action='<html:rewrite href="/huiermis/switchAction.do?prefix=/charge&page=/clientquery.jsp"/>';
		obj.submit();
	}
	function shoufei(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/ChargeAction.do?method=normalChargeDetail&"/>' + getAlldata(obj);
		obj.submit();
	};
	
	<%--  $(document).ready(function(e) {
			var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");

			$("input[name=stoproid]").autocomplete(products,{
				max : 10,
				matchContains : true
			}); 
			$("input[name=stoproid]").result(function(event, data, formatted) {
				if (data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=stoproid]").val(gid);
					var id = $(this).parent().find("input").attr("gid");
				}
			});
		}); --%>
		
	$(document).ready(function(){	
		//$("input[name=stoproid]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('��ȡ���ݴ���');
				 },
				 success:function(data){
							$("input[name=stoproid]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=stoproid]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=stoproid]").val(pid);
									var id = $(this).parent().find("input").attr("pid");

								}
							});
						}
				});
		//});
	
	});
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="�շѲ�ѯ" />
	<lemis:query action="/ChargeAction.do?method=normalQuery" editorMeta="editor" topic="��ѯ����" />	
	<lemis:table action="ChargeAction.do" headerMeta="header" topic="�շ���Ϣ"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


