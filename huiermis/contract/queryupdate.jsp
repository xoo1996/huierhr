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
	buttons.put("修改","update(document.all.tableform)");
	buttons.put("删除","del(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("username","员工姓名"));
	header.add(new Formatter("useremployid","工号"));
	header.add(new Formatter("bsc009","所属部门"));
	header.add(new Formatter("conid","合同编号"));
	header.add(new Formatter("contype","合同类型"));
	header.add(new Formatter("condatestart","合同开始日期"));
	header.add(new Formatter("condateend","合同到期日期"));
	header.add(new Formatter("condatesign","合同签约日期"));
	

	
	
	List<Editor> editors = new ArrayList<Editor>();
	
	editors.add(new Editor("text", "username", "员工姓名")); 
	editors.add(new Editor("text","useremployid","工号"));
	editors.add(new Editor("text","conid","合同编号"));
	editors.add(new Editor("text","contype","合同类型"));
	editors.add(new Editor("date","condatestart","合同开始日期"));
	editors.add(new Editor("date","condateend","合同到期日期"));
	editors.add(new Editor("date","condatesignstart","签约日期从"));
	editors.add(new Editor("date","condatesignend","到"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("username", "员工姓名");
	hidden.put("useremployid", "工号");
	hidden.put("conid", "合同编号");
	hidden.put("condatestart", "合同开始日期");
	hidden.put("condateend", "合同到期日期");
	hidden.put("condatesign", "合同签约日期");
	hidden.put("contype", "合同类型");
	

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
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	window.location.href = "/" + lemis.WEB_APP_NAME
	+ "/contract/ContractAction.do?method=update&"
	+ getAlldata(obj);
}; 
function del(obj) {
	var t = delObj("chk");//校验有没有可提交项
	if (!t) {
		return t;
	}
	if(confirm("确实要删除该合同吗？")){
		window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/contract/ContractAction.do?method=delete&"
		+ getAlldata(obj);
	}
}; 
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="修改劳动合同" />
	<lemis:query action="/ContractAction.do?method=query&tp=update" 
		editorMeta="editor" topic="查询条件"/>
	<lemis:table action="/ContractAction.do" headerMeta="header"
	 topic="合同列表" hiddenMeta="hidden" mode="radio"/>
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
