<%@page import="com.lbs.cp.taglib.Formatter"%>
<%@page import="org.radf.plat.taglib.Editor"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("ɾ��","del(document.all.tableform)");
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    List<Editor> editors = new ArrayList<Editor>();
	 editors.add(new Editor("text","feegctid","�ͻ�����"));
	 editors.add(new Editor("text","feegctname","�ͻ�����"));
	 editors.add(new Editor("text","gctarea","�ͻ�����"));
	 
    List<Formatter> header = new ArrayList<Formatter>();
    header.add(new Formatter("gctarea","�ͻ�����"));
    header.add(new Formatter("feegctid","�ͻ�����"));
    header.add(new Formatter("feegctname","�ͻ�����"));
    header.add(new Formatter("amotype","��������"));
    header.add(new Formatter("money","�ܶ�"));
    header.add(new Formatter("amomoney","��̯�����"));
    header.add(new Formatter("feelong","̯���·�"));
    header.add(new Formatter("feestart","��ʼʱ��"));
    header.add(new Formatter("feeend","����ʱ��")); 
    header.add(new Formatter("note","��ע"));
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("feeamoid", "id");
	

	pageContext.setAttribute("header",header);
	pageContext.setAttribute("editor",editors);
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/lemis/js/lemisTree.js"></script>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready( function() {
       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=feegctid]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=feegctid]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=feegctid]").val(gid);
				$("input[name=feegctname]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
function del(obj){
	 var t = editObj("chk");
	if(!t){
		return t;
	} 
	if(confirm("ȷ��Ҫɾ���ö�����?")) {
		obj.action = '<html:rewrite page="/FeeAction.do?method=deleteAmortize&"/>'+getAlldata(obj);		
		obj.submit();
	}
};
</script>
<lemis:base />
<lemis:body>
	
	
	<lemis:title title="̯�����ò�ѯ" />
	<lemis:query action="/FeeAction.do?method=queryamo" editorMeta="editor" topic="̯�����ò�ѯ" />	
	<lemis:table topic="̯�����ò�ѯ" action="/FeeAction.do" mode="radio" headerMeta="header" orderResult="false" hiddenMeta="hidden" pilot="true"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>

</html>


		
		
