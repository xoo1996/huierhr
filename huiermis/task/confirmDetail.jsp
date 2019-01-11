<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	//buttons.put("�����ύ","con()");
	buttons.put("ȷ ��", "con(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("pdtid","�������"));
	header.add(new Formatter("pdtnm","�������"));
	header.add(new Formatter("pdtmod","����ͺ�"));
	header.add(new Formatter("tcfnum","�������"));
	header.add(new Formatter("tcfbth","�������"));
	header.add(new Formatter("tcfnt","��ע"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
 	batchInput.add(new Editor("text","pdtid","�������","false"));
 	/*batchInput.add(new Editor("text","acypdtnm","�������","true"));
	batchInput.add(new Editor("text","acytyp","����ͺ�","true")); */
	batchInput.add(new Editor("text","tcfnum","�������","false"));
	batchInput.add(new Editor("text","tcfbth","�������","true"));//����
	batchInput.add(new Editor("text","tcfnt","��ע","false"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid","���񵥺�");
	
	hidden.put("tskpnlnm","����ͺ�");
	hidden.put("tskpnlqnt","�������");

	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);
	pageContext.setAttribute("header",header);
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
		var panels = "<%=session.getServletContext().getAttribute("panelList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=acyid]").attr('readonly','readonly');
		
		$("input[name=tskpnlnm]").autocomplete(panels,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskpnlnm]").result(function(event, data, formatted) {
			if (data){
				var pnlnm = data[0].substring(data[0].indexOf("=")+1);
				//var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=tskpnlnm]").val(pnlnm);
				$(this).parent().next().find("input").val(pnlnm);
			}
		});

	});
</script>
<script language="javascript">
	$(document).ready(function(){
		var users = "<%=session.getServletContext().getAttribute("userList")%>".replace("{","").replace("}","").split(", ");
		
		$(":checkbox:enabled").attr('checked', true);
		
		$("input[name=tskbilopr]").autocomplete(users,{
			max : 10,
			matchContains : true
		});
		$("input[name=tskbilopr]").result(function(event, data, formatted) {
			if (data){
				var gid = data[0].substring(data[0].indexOf(":")+1);
				$("input[name=tskbilopr]").val(gid);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
</script>
<script language="javascript">
	function con(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		if(!confirm("ȷ���������?")){
			window.event.returnValue = false;
		}else{
			/* window.location.href = "/" + lemis.WEB_APP_NAME
					+ "/task/TaskAction.do?method=confirm&tskid="
					+ $("input[name=tskid]").val() + "&tskpnlqnt=" + $("input[name=tskpnlqnt]").val() + "&"
					+ getAlldata(document.all.tableform); */
			obj.action='<html:rewrite page="/TaskAction.do?method=confirm"/>';
			obj.submit();
		}
	}

</script>

<lemis:base />
<lemis:body>
	<lemis:title title="���������Ϣȷ��" />
	<lemis:tabletitle title="������û�����Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
			<tr>
				<lemis:texteditor property="tskid" label="���񵥺�" disable="true"/>
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="true" />
				<lemis:texteditor property="tskpnlqnt" label="�������" disable="true" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="false"
					label="Ҫ���������" disable="true" format="true" />
				<lemis:texteditor property="tskbilopr" label="�Ƶ���" disable="true" />
				<td>�����</td>
				<td><lemis:operator /></td>
			</tr>
			
			<tr>
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="�����´�����" disable="true" format="true" />
				<lemis:texteditor property="tskdmd" label="���Ҫ��" disable="true"  colspan="3"/>
			</tr>
		</html:form>
	</table>

	<lemis:table topic="�����Ϣ��ϸ"  
		action="/TaskAction.do" headerMeta="header" hiddenMeta="hidden"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" pageSize="20"
		batchInputType="update" pilot="true" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>