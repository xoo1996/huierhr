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
	header.add(new Formatter("ncdpid", "��Ʒ����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("pdtprc", "��Ʒ����"));
	header.add(new Formatter("ncdqnt", "�۳�����"));
	header.add(new Formatter("ncddis", "��Ʒ����"));
	header.add(new Formatter("ncdmon", "ʵ���շ�"));
	//header.add(new Formatter("ncdrecmon", "�˻����"));
	header.add(new Formatter("ncdnt", "��ע"));
	header.add(new Formatter("ncdsta", "״̬"));
	header.add(new Formatter("ncdrnt", "�˻�����"));
	header.add(new Formatter("ncdrecmon", "�˻����"));
	

	List<Editor> batchInput = new ArrayList<Editor>();
	/* batchInput.add(new Editor("text", "pdtid", "��Ʒ����", "false")); */
/* 	batchInput.add(new Editor("text", "ncdsta", "״̬", "false")); */
	batchInput.add(new Editor("text", "ncdrnt", "�˻�����", "true"));
	batchInput.add(new Editor("text", "ncdrecmon", "�˻����", "true"));

	Map<String, String> buttons = new LinkedHashMap<String, String>();
	buttons.put("�� ӡ","print(document.forms[0])");
	buttons.put("�˻���ֱ����", "saveData(document.forms[0])");
/* 	buttons.put("�޸�", "modify(document.forms[0])");
	buttons.put("�˻����ܲ�", "commit(document.forms[0])"); */
	buttons.put("����","history.back()");

	 Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ncdid", "�շѺ�"); 
	hidden.put("ncdpid", "��Ʒ����"); 
	hidden.put("ncdsta", "״̬"); 
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("batchInput", batchInput);
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
function saveData(obj) {
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	if (confirm("ȷʵҪ�˻���ֱ������")) {
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=s&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
	}
	//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
	else
		return t;
};

function modify(obj) {
	t = preCheckForBatch();
	if(!t){
		return t;
	}
	if (!checkValue(obj)) {
		return false;
	}
	var t = delObj("chk");
	if(!t){
		return t;
	}
	
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=m&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
};


function commit(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=saveNomRec&tp=c&"/>'+ getAlldata(document.all.tableform);
	obj.submit();
};

function print(obj) {
	if (!checkValue(obj)) {
		return false;
	}
	obj.action='<html:rewrite href="/huiermis/charge/ChargeAction.do?method=printNorRec"/>';
	obj.submit();
};
</script>

<lemis:body>
	<lemis:base />
    <lemis:title title="��ͨ��Ʒ�շ�����" />
	<lemis:tabletitle title="�շ���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/ChargeAction.do?method=saveNormalCharge"
			method="POST">
			<tr>
				<html:hidden property = "ictid"/>
				<lemis:texteditor property="chgid" label="�շѺ�" disable="true" /> 
				<lemis:texteditor property="gctnm" label="��������" disable="true"/>
				<lemis:texteditor property="ictnm" label="�û�����" disable="true"/>
			</tr>
			<tr>
				<lemis:formateditor required="false" property="chgdt" mask="date" label="�շ�����" format="true"/>
			<%-- 	<lemis:formateditor required="false" property="chgrecdt" mask="date" label="�˻�����" format="true"/>
				<lemis:formateditor required="false" property="chgrecheaddt" mask="date" label="�˻��ܲ�����" format="true"/> --%>
			</tr>
		</html:form>
	</table>
	<lemis:table topic="��Ʒ��ϸ"
		action="/ChargeAction.do?method=batchSubmit" headerMeta="header"
		mode="checkbox" batchInputMeta="batchInput" orderResult="false" hiddenMeta="hidden"
		 batchInputType="update" pilot="false" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


