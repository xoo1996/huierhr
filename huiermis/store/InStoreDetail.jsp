<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >

<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("����¼��","add(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	/* header.add(new Formatter("stoprotype","��Ʒ���")); */
	header.add(new Formatter("stoproid","��Ʒ����"));
	header.add(new Formatter("stoproname","��Ʒ����"));
/* 	header.add(new Formatter("stoprodsc","����")); */
	/* header.add(new Formatter("stoproprice","����",TagConstants.DF_LEFT,TagConstants.DT_MONEY)); */
	header.add(new Formatter("stoamount","����"));
	/* header.add(new Formatter("stoamountun","������λ")); */
	header.add(new Formatter("storemark","��ע"));
	header.add(new Formatter("isrepair","�Ƿ�ά�޻�"));
	
	
	
	List<Editor> batchInput = new ArrayList<Editor>();
	/* batchInput.add(new Editor("text","stoprotype","��Ʒ���","true")); */
	batchInput.add(new Editor("text","stoproid","��Ʒ����","true"));
	batchInput.add(new Editor("text","stoproname","��Ʒ����","true"));
/* 	batchInput.add(new Editor("text","stoprodsc","����","true")); */
/* 	batchInput.add(new Editor("money","stoproprice","����","true")); */
	batchInput.add(new Editor("text","stoamount","����","true"));
	/* batchInput.add(new Editor("text","stoamountun","������λ","true")); */
	batchInput.add(new Editor("text","storemark","��ע"));
	batchInput.add(new Editor("text","isrepair","�Ƿ�ά�޻�"));
	
	/* Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("stoprotype", "��Ʒ�����");
	hidden.put("folno","������");
	hidden.put("dgnlid","���ƻ��ͺ�");
	hidden.put("dgnrid","�Ҷ��ƻ��ͺ�"); */

	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
	

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

<script language="javascript">
$(document).ready(function(e) {
	<%--$("input[name=chk][value=1]").attr("checked",true);--%>
	
<%-- 	var classes="<%=session.getServletContext().getAttribute("classesList")%>".replace("{","").replace("}","").split(", ");
	$("input[name=stoprotype]").autocomplete(classes,{
		max : 10,
		matchContains : true,
		formatItem: function(data, i, max) {
			return data[0].substring(0,data[0].indexOf("!"));
		}
	});
	
	
	$("input[name=stoprotype]").result(function(event, data, formatted) {
		if (data){
			var pcl = data[0].substr(0,data[0].length-1);
			var id = $(this).parent().find("input").attr("id");
			var suffix = id.substr(10);
			$("#stoprotype" + suffix).val(pcl);
		}
	}); --%>
	
	function chk(e) {
		var id = $(e.target).attr("id");  //�õ��ؼ�idֵid='fdtpid_row1'
		var suffix = id.substr(12); 
		var suffix2 = id.substr(8); 
		var fdtid=$("#stoproid" + suffix2).val();
        if(fdtid!='')
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",true);
        }
        else
        {
        	$("input[name=chk][value="+suffix+"]").attr("checked",false);
        }
			
	};
	$("input[name=stoproid]").bind("blur",function(e){
		chk(e);
	});
/* 	 url:'http://localhost:8080/huiermis/product/ProductAction.do?method=queryEMPro', */
	
	//$("input[name=stoproid]").bind("click",function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
			 data:"proType=store",
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
								var id = $(this).parent().find("input").attr("id");
									var suffix = id.substr(8);
									$("#stoproid" + suffix).val(pid);
									$("#stoproname" + suffix).val(pnm);
									
							}
						});
					}
			});
	//});
	
});	
/* 	$("input[name=stoproid]").result(function(event, data, formatted) {
		if (data){
			var pid = data[0].substring(0,data[0].indexOf("="));
			var pnm = data[0].substring(data[0].indexOf("=")+1,data[0].indexOf(":"));
			var id = $(this).parent().find("input").attr("id");
				var suffix = id.substr(8);
				$("#stoproid" + suffix).val(pid);
				$("#stoproname" + suffix).val(pnm);
		}
	});
 */
	
	function startRequest() {
		$.getJSON("/huiermis/product/ProductAction.do?method=queryEMPro",
				createQueryString(),function(data) {	
				$("input[name=stoproid]").autocomplete(data,{
					max : 10,
					matchContains : true,
					formatItem: function(data, i, max) {
						return data.proid;
					}
				});

			});
	}
	
	function createQueryString() {
		/* var eID = $(e.target).val(); */
		/* var pclid=$(pid).val(); */
		var queryString = {
			PclId : $("input[name=stoproid]").val()
		};
		return queryString;
	};
	

function add(obj) {
	
	var t = delObj("chk");//У����û�п��ύ��
	if (!t) {
		return t;
	}
	t = preCheckForBatch();//�Ա�¼��У��
	if (!t) {
		return t;
	}
	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/store/InStoreAction.do?method=add&"
	+ getAlldata(document.all.tableform).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g,'%27').replace(/\//g,'%2F');
	/* obj.action = '<html:rewrite href="/huiermis/store/InStoreAction.do?method=add"/>';
	obj.submit(); */ 
};

function query(obj) {
	
	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/store/InStoreAction.do?method=addQuery&"
	+ getAlldata(document.all.tableform);
}

</script>






<lemis:base />
<lemis:body>
<lemis:editorlayout />
	<lemis:title title="��Ʒ�����Ϣ" />

<%-- 	���������<lemis:operorg />
	 <lemis:query action="/InStoreAction.do?method=addQuery"
		editorMeta="editor" topic="��ѯ����" /> --%>
		
		
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/InStoreAction.do?method=addQuery" method="POST">
		    <tr>
<%-- 		    <td align="left"> <div class="alignCon"><lemis:texteditor property="stoproname" label="��Ʒ����" required="true"
					disable="false" maxlength="50" colspan="5" /> </div>    </td> --%>

				<td>��������</td>
				<td><lemis:operorg/></td>
				
				<%--  <td>�������</td>
				<td><lemis:operorg /></td>  --%>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		<tr>
				
<%-- 				<lemis:buttons buttonMeta="buttons" /> --%>
			</tr>

		</html:form>
	</table>
		
		<lemis:table topic="��Ʒ��ϸ¼��" action="/InStoreAction.do?method=add"
			 headerMeta="header"
			mode="checkbox" batchInputMeta="batchInput" orderResult="false"
			 pilot="true" />

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom /> 
</lemis:body>
</html>