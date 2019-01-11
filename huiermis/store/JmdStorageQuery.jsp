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

<html>
<link href='/huimis/css/style.css' rel='stylesheet' type='text/css'>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huimis/js/lemisTree.js"></script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<%  

	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	/*buttons.put("����","add(document.all.tableform)");
	buttons.put("�ݶ���ӡ","huier_print()");
	buttons.put("���Ĵ�ӡ","jiewen_print()");
	buttons.put("�� ��","closeWindow(\"\")");*/
	buttons.put("���ӿ��","storage_add(document.all.tableform)");
	buttons.put("�����ϸ","storage_detail(document.all.tableform)");
	
	List<Button> buttons1=new ArrayList<Button>();
	buttons1.add(new Button("z","����������ܲ���","sto050001","storage_add(document.all.tableform)"));
	buttons1.add(new Button("zs","������������˵꣩","jmd000002","storage_add(document.all.tableform)"));
	buttons1.add(new Button("kz","�����ϸ���ܲ���","sto050002","storage_detail(document.all.tableform)"));
	buttons1.add(new Button("k","�����ϸ","jmd000001","storage_detail(document.all.tableform)"));
	buttons1.add(new Button("pk","�̿�","jmd000003","panku(document.all.tableform)"));

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("gctnm","��������"));
	header.add(new Formatter("stocllar","��Ʒ����"));
	header.add(new Formatter("laramount","��������"));
	header.add(new Formatter("stoclmid","��Ʒ����"));
	header.add(new Formatter("midamount","��������"));
	header.add(new Formatter("stoclsam","��ƷС��"));
	header.add(new Formatter("samamount","С������"));
	header.add(new Formatter("stoproid","��Ʒ����"));
	header.add(new Formatter("stoproname","��Ʒ����"));
	header.add(new Formatter("amount","�����"));
	header.add(new Formatter("isrepair","ά�޻�"));
	//header.add(new Formatter("stodate","����"));
	/* header.add(new Formatter("stoamountun","������λ"));
	header.add(new Formatter("stotype","����"));
	header.add(new Formatter("stodate","����"));
	header.add(new Formatter("storemark","��ע")); */
	
	
	List<Editor> editors = new ArrayList<Editor>();
	//editors.add(new Editor("text", "stoproid", "��","true"));
	//editors.add(new Editor("text", "stoproname", "��","true")); 
	editors.add(new Editor("text","gctnm","��������"));
	editors.add(new Editor("text","stocllar","��Ʒ����"));
	editors.add(new Editor("text","stoclmid","��Ʒ����"));
	editors.add(new Editor("text","stoclsam","��ƷС��"));
	editors.add(new Editor("text","stoproid","��Ʒ����"));
	editors.add(new Editor("text","stoproname","��Ʒ����"));
	editors.add(new Editor("text","isrepair","�Ƿ�ά�޻�"));
	//editors.add(new Editor("date","start","���ڴ�"));
	//editors.add(new Editor("date","end","��"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("gctnm", "��������");
	hidden.put("stoproid","��Ʒ����");
	hidden.put("stoproname","��Ʒ����");
	hidden.put("storemark","��ע");
	hidden.put("isrepair","ά�޻�");
	pageContext.setAttribute("hidden", hidden);


	pageContext.setAttribute("button", buttons1);
	pageContext.setAttribute("editor", editors);
	//pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<script language="javascript">	
$(document).ready(function(e) {
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
	
	//$("input[name=stoproid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=all",
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
								var pid = data.proid;
								var pnm = data.proname;
								$("input[name=stoproid]").val(pid);
								$("input[name=stoproname]").val(pnm);
									
							}
						});
					}
			});
	//});
});

function storage_detail(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=storageDetail&"/>' + getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
	obj.submit();
}
function storage_add(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=inDetail1&"/>' + getAlldata(obj);
	obj.submit();
}
function panku(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/InStoreAction.do?method=panku&"/>' + getAlldata(obj);
	obj.submit();
}
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�������ѯ" />
	<lemis:query action="/InStoreAction.do?method=queryStorageJmd"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="/InStoreAction.do" headerMeta="header"
	 topic="�����ϸ" 
		mode="radio" hiddenMeta="hidden"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
