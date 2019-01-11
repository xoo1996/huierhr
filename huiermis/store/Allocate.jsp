<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
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
	/* buttons.put("配 货","discard(document.all.tableform)"); */
	buttons.put("订单明细","detail(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");

	List<Formatter> header=new ArrayList<Formatter>();
	header.add(new Formatter("folno","订单号"));
	header.add(new Formatter("folctnm","客户名称"));
	header.add(new Formatter("folsta","订单状态"));
	header.add(new Formatter("foldt","订货日期"));
	header.add(new Formatter("folnt","备注"));

	
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","folno","订单号"));
	editors.add(new Editor("text","folctnm","客户名称"));
	editors.add(new Editor("date","start","订货日期从"));
	editors.add(new Editor("date","end","到"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("folno", "订单号");
	
	

	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("hidden",hidden);
	pageContext.setAttribute("header",header);
%> 

<script language="javascript">	


		//查看订单明细
		function detail(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/AllocateAction.do?method=allocateDetail&"/>'+getAlldata(obj);	
			obj.submit();
		};
		
	   function discard(obj) {
		 	obj.action = '<html:rewrite page="/AllocateAction.do?method=modifyState&"/>' + getAlldata(obj);
			obj.submit(); 
		
		 /* window.location.href = "/" + lemis.WEB_APP_NAME
			+ "/store/AllocateAction.do?method=modifyState&"
			+ getAlldata(document.all.tableform); */
      };
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="普通商品配货" />
	<lemis:query action="/AllocateAction.do?method=query" editorMeta="editor" topic="查询条件" />
	
	<lemis:table action="/AllocateAction.do" headerMeta="header" hiddenMeta="hidden" 
	 topic="配货明细" mode="radio"/>

	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>