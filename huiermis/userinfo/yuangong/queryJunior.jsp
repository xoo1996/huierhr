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
 String tar=(String)request.getAttribute("taraccount");
	List<Formatter> header = new ArrayList<Formatter>();
	
	header.add(new Formatter("useremployid", "����",TagConstants.DF_CENTER));
	header.add(new Formatter("username", "�û�����",TagConstants.DF_CENTER));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
 	buttons.put("����","showDetail(document.all.tableform)"); 
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
  	hidden.put("account", "�Ѱ��˺�");
/* 	hidden.put("username", "�û�����"); */
	hidden.put("useremployid", "�û�����"); 
/* 	hidden.put("taraccount", "�����˺�");  */

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "taraccount", "�˺�"));
	
	List<Editor> batchInput = new ArrayList<Editor>();
/* 	batchInput.add(new Editor("text","useremployid", "����","false"));	
	batchInput.add(new Editor("text","account", "����","false")); */	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("batchInput",batchInput);	
	
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script language="javascript">

	$(document).ready(function(){
		var taraccount = '<%=request.getAttribute("taraccount")%>'; 
 		$("input[name='taraccount']").val(taraccount);
		$("input[name='taraccount']").attr("readonly","true");
	});
	
	/* ���� */
	function showDetail(obj) {
		var t = editObj("chk");
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/UserInfoAction.do?method=showDetail&tp=normal&"/>' + getAlldata(obj) +'&mtd=geren';
		obj.submit();
	};

/* 	function setValue(){
		var val = $("input[name='taraccount']").eq(0);
		var val2 = $("input[name='taraccount']").eq(1);
		
		val2.attr("value",val.attr("value"));
	};
 */
/* �� */
/* 	function bind(obj) {	
		obj.action = '<html:rewrite page="/BindAccAction.do?method=toQueryAccount&"/>' + "taraccount=" + $("input[name='taraccount']").eq(0).attr("value") + "";
		obj.submit();
	}; */

	/* ������� */
 /* 	function unwrap(obj) {
 		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BindAccAction.do?method=batchUnwrapAccUser&"/>' + "taraccount=" + $("input[name='taraccount']").eq(0).attr("value");
		obj.submit();
	};  */
/* 	function news(obj) {
		obj.action = '<html:rewrite href="/huiermis/switchAction.do?prefix=/userinfo&page=/addUser.jsp"/>';
		obj.submit();
	} */
	
	
	
	
</script>

<lemis:base />
<lemis:body>

    
    <lemis:title title="�¼�������Ϣ" />
    <table class="tableinput">
				<COLGROUP><COL width='12%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			</table>
	<lemis:query action="/UserInfoAction.do?method=queryJuniorInfo" editorMeta="editor" topic="��¼�˺�" />	
	<lemis:table action="UserInfoAction.do" headerMeta="header" topic="�¼���Ϣ" 
		hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


