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
	buttons.put("�޸�","update(document.all.tableform)");
	buttons.put("ɾ��","del(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("username","Ա������"));
	header.add(new Formatter("useremployid","����"));
	header.add(new Formatter("bsc009","��������"));
	header.add(new Formatter("conid","��ͬ���"));
	header.add(new Formatter("contype","��ͬ����"));
	header.add(new Formatter("condatestart","��ͬ��ʼ����"));
	header.add(new Formatter("condateend","��ͬ��������"));
	header.add(new Formatter("condatesign","��ͬǩԼ����"));
	

	
	
	List<Editor> editors = new ArrayList<Editor>();
	
	editors.add(new Editor("text", "username", "Ա������")); 
	editors.add(new Editor("text","useremployid","����"));
	editors.add(new Editor("text","conid","��ͬ���"));
	editors.add(new Editor("text","contype","��ͬ����"));
	editors.add(new Editor("date","condatestart","��ͬ��ʼ����"));
	editors.add(new Editor("date","condateend","��ͬ��������"));
	editors.add(new Editor("date","condatesignstart","ǩԼ���ڴ�"));
	editors.add(new Editor("date","condatesignend","��"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("username", "Ա������");
	hidden.put("useremployid", "����");
	hidden.put("conid", "��ͬ���");
	hidden.put("condatestart", "��ͬ��ʼ����");
	hidden.put("condateend", "��ͬ��������");
	hidden.put("condatesign", "��ͬǩԼ����");
	hidden.put("contype", "��ͬ����");
	

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header",header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<script language="javascript">	
function update(obj) {
	var t = delObj("chk");//У����û�п��ύ��
	if (!t) {
		return t;
	}
	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/contract/ContractAction.do?method=update&"
	+ getAlldata(obj);
}; 
function del(obj) {
	var t = delObj("chk");//У����û�п��ύ��
	if (!t) {
		return t;
	}
	if(confirm("ȷʵҪɾ���ú�ͬ��")){
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/contract/ContractAction.do?method=delete&"
		+ getAlldata(obj);
	}
}; 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="�޸��Ͷ���ͬ" />
	<lemis:query action="/ContractAction.do?method=query&tp=update" 
		editorMeta="editor" topic="��ѯ����"/>
	<lemis:table action="/ContractAction.do" headerMeta="header"
	 topic="��ͬ�б�" hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
