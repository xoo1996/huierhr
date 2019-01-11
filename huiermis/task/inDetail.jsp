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
	buttons.put("�� ��", "save(document.all.tableform)");
	buttons.put("�� ��","history.back()");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("tsdtskid","���񵥺�"));
	header.add(new Formatter("tsdsid","������"));
	header.add(new Formatter("tsdpnlid","������"));
	header.add(new Formatter("tsksta","���״̬"));
	header.add(new Formatter("pnlqaopra","�ʼ�Ա"));
	header.add(new Formatter("qachka","������"));
	header.add(new Formatter("pnlqadt","��������"));
	header.add(new Formatter("pnlqant","��ע"));
	
	
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tskid", "���񵥺�");
	hidden.put("tskpnlnm","����ͺ�");
	hidden.put("tskpnlqnt","�������");
	hidden.put("tskdfdt","Ҫ���������");
	hidden.put("tskbilopr","�Ƶ���");
	hidden.put("tskbgndt","�����´�����");
	hidden.put("tskdmd","���Ҫ��");
	hidden.put("tsksta","���״̬");
	hidden.put("tsdpnlid","������");
	hidden.put("tsdsid","������");
	


	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("button", buttons);
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
		var pnlid = $("#pnlqapnl_hrow1");
		$("input:hidden[name=pnlqapnl]").val(pnlid);
		
		var tskid = $('#tskid_hrow1').val();
		var tskpnlnm = $("#tskpnlnm_hrow1").val();
		var tskpnlqnt = $("#tskpnlqnt_hrow1").val();
		var tskdfdt = $("#tskdfdt_hrow1").val();
		var tskbilopr = $("#tskbilopr_hrow1").val();
		var tskbgndt = $("#tskbgndt_hrow1").val();
		var tskdmd = $("#tskdmd_hrow1").val();
		$('.tableInput label:first').text(tskid);
		$('.tableInput label:eq(1)').text(tskpnlnm);
		$('.tableInput label:eq(2)').text(tskpnlqnt);
		$('.tableInput label:eq(3)').text(tskdfdt);
		$('.tableInput label:eq(4)').text(tskbilopr);
		$('.tableInput label:eq(5)').text(tskbgndt);
		$('.tableInput label:last').text(tskdmd);

		$('input[name=chk]').attr('checked','checked');
	});
	function save(obj) {
		var t = delObj("chk");
		if(!t){
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
			
	 	obj.action = '<html:rewrite page="/TaskAction.do?method=batchStore"/>';
		obj.submit();
		/*	 window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/task/TaskAction.do?method=store&tskid="
				+ $("#tskid_hrow1").val() + "&tskpnlnm="
				+ $("#tskpnlnm_hrow1").val() + "&tskpnlqnt="
				+ $("#tskpnlqnt_hrow1").val();  */
		};

</script>
<lemis:base />
<lemis:body>

	
		
		
	<lemis:title title="�����Ϣȷ��" />
	<lemis:tabletitle title="�ʼ������Ϣ" />

		
 	<table class="tableInput">
		<lemis:editorlayout />
		<html:form action="/TaskAction.do" method="POST">
			<tr>
				<html:hidden property="tskid"/>
				<lemis:texteditor property="tskid" label="���񵥺�" disable="true"/>
				<lemis:texteditor property="tskpnlnm" label="����ͺ�" disable="true" />
				<lemis:texteditor property="tskpnlqnt" label="�������" disable="true" />
			</tr>
			<tr>
				<lemis:formateditor  mask="date"  property="tskdfdt"  required="false"
					label="Ҫ���������" disable="true" format="true" />
				<lemis:texteditor property="tskbilopr" label="�Ƶ���" disable="true" />
				<lemis:formateditor  mask="date"  property="tskbgndt"  required="false"
					label="�����´�����" disable="true" format="true" />
			</tr>
			<tr>
				<lemis:texteditor property="tskdmd" label="���Ҫ��" disable="true"  colspan="5"/>
			</tr>
		</html:form>
	</table> 
	
	<lemis:table action="/TaskAction.do" headerMeta="header" topic="���������ϸ"
		hiddenMeta="hidden" mode="checkbox"  pilot="true" />

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>