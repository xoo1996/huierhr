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
	header.add(new Formatter("useremployid", "�¼�����",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "�û�����",TagConstants.DF_CENTER));
	header.add(new Formatter("superiorid", "�ϼ�����",TagConstants.DF_CENTER));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("��Ӱ�","bind(document.all.tableform)");
 	buttons.put("�������","batchunwrap(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
/*  	hidden.put("username", "�û�����"); */
	hidden.put("useremployid", "������"); 
	hidden.put("superiorid", "�ϼ�����"); 
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "qsuperiorid", "��󶨹���"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script language="javascript">

/* function setValue(){
	var val = $("input[name='taraccount']").eq(0);
	var val2 = $("input[name='taraccount']").eq(1);
	
	val2.attr("value",val.attr("value"));
}; */

/* �� */
function bind(obj) {
	var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
	if(qsuperiorid=="undefined" || qsuperiorid==""){
		alert("�˺Ų���Ϊ�գ�");
		return false;
	}
	obj.action = '<html:rewrite page="/BindSXJAction.do?method=toBindJsp&"/>' + 'qsuperiorid=' + qsuperiorid;
	obj.submit();
	
	
	/*  setValue();
	var t = editObj("chk");
	if (!t) {
		 obj.action = '<html:rewrite page="/BindAction.do?method=unWrapAccUser&"/>' + getAlldata(obj);
		obj.submit();
		
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/userinfo/bindAccount.jsp?"
		+ getAlldata(obj); 
	}else{
		var taraccount = $("input[name='taraccount']").eq(0);
		obj.action = '<html:rewrite page="/BindAction.do?method=unWrapAccUser&"/>' + 'taraccount=' + taraccount.attr("value");
		obj.submit();
	} */
	
};

	/* ��� */
	function batchunwrap(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		var qsuperiorid = $("input[name='qsuperiorid']").eq(0).attr("value");
		obj.action = '<html:rewrite page="/BindSXJAction.do?method=batchUnwrapSXJ&"/>' + "qsuperiorid=" + qsuperiorid;
		obj.submit();

	}; 
/* 	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/userinfo&page=/addUser.jsp"/>';
		obj.submit();
	} */
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="���¼���Ϣ��ѯ" />
	<lemis:query action="/BindSXJAction.do?method=queryInfo" editorMeta="editor" topic="��ѯ����" />	
	<lemis:table action="BindSXJAction.do" headerMeta="header" topic="�¼���Ϣ"
		hiddenMeta="hidden" mode="checkbox"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


