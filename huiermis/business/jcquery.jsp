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
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtprc","���ۼ�"));
	header.add(new Formatter("gctarea", "��������"));
	header.add(new Formatter("gcttype", "��������"));
	header.add(new Formatter("pdttype", "��ƷƷ��"));
	header.add(new Formatter("temp01", "�����"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("��ʾ����","batchmd(document.all.tableform)");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ivtpdtid","��Ʒ����");
	hidden.put("ivtyear", "��");
	hidden.put("ivtmonth", "��");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "ivtyear", "��","true"));
	editors.add(new Editor("text", "ivtmonth", "��","true"));
	editors.add(new Editor("text", "gctarea", "��������"));
	editors.add(new Editor("text", "gcttype", "��������"));
	editors.add(new Editor("text", "pdttype", "��ƷƷ��"));
	editors.add(new Editor("text", "pdtnm", "��Ʒ����"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

<%-- 	$(document).ready(function(){
		
		var products = "<%=session.getServletContext().getAttribute("productList")%>".replace("{","").replace("}","").split(", ");
	
		$("input[name=pdtnm]").autocomplete(products,{
			max : 10,
			matchContains : true,
			formatItem: function(data, i, max) {
				return data[0].substring(0,data[0].indexOf(":"));
			}
		});
		$("input[name=pdtnm]").result(function(event, data, formatted) {
			if (data){
				var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
				$("input[name=pdtnm]").val(pnm);
			}
		});
	}); --%>
	
	
	$(document).ready(function(){
		$("input[name=pdtnm]").bind("click",function(){
			$.ajax({
				 type:'post',
				 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
				 dataType:'json',
				 error:function(){
				   alert('��ȡ���ݴ���');
				 },
				 success:function(data){
							$("input[name=pdtnm]").autocomplete(data,{
								max:10,
								matchContains:true,
								formatItem:function(data,i,max){
									return (data.proid + "=" + data.proname);
								}
							});	
							$("input[name=pdtnm]").result(function(event, data, formatted) {
								if (data){
									var pid=data.proid;
									var pnm = data.proname;
									$("input[name=pdtnm]").val(pnm);
	
								}
							});
						}
				});
		});
	});
	function batchmd(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		window.location.href = "/" + lemis.WEB_APP_NAME
				+ "/business/BusinessAction.do?method=jcdetail&"
				+ getAlldata(document.all.tableform);
	};
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="��Ʒ����ѯ" />
	<lemis:query action="/BusinessAction.do?method=jcquery"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/BusinessAction.do" headerMeta="header"
		topic="�����Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>