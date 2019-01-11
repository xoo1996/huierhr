<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("nemname", "姓名",TagConstants.DF_CENTER));
	header.add(new Formatter("nememployid", "人员id",TagConstants.DF_CENTER));
	header.add(new Formatter("nemcard", "身份证号",TagConstants.DF_CENTER));
	header.add(new Formatter("bsc009", "所属部门",TagConstants.DF_CENTER));
	header.add(new Formatter("nempart", "部门id",TagConstants.DF_CENTER));
	header.add(new Formatter("nemworkdt", "入职时间",TagConstants.DF_CENTER));

	Map<String,String> buttons = new LinkedHashMap<String,String>();
	buttons.put("新 建","verifyapa(document.all.tableform)");
	buttons.put("返 回","history.back()");
	buttons.put("关 闭", "closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("nemname", "姓名");
	hidden.put("nememployid", "人员id");
	hidden.put("nempart", "部门id");
	hidden.put("bsc009", "所属部门");
	hidden.put("nemcard", "身份证号");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "nemname", "姓名"));
	editors.add(new Editor("text", "nememployid", "人员id"));
	editors.add(new Editor("text", "nempart", "部门id"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "续签流程查询");//表头

%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
	
		//审核信息
  		function verifyapa(obj){
  			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/ApaOperAction.do?method=newApa&type=con&"/>'+getAlldata(obj);			
			obj.submit();
  		}
  		$(document).ready(function(){
  			/* 智能输入 */
  			var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
  			
  			$("input[name=nempart]").autocomplete(shops,{
  				max : 10,
  				matchContains : true
  			});
  			$("input[name=nempart]").result(function(event, data, formatted) {
  				if (data){
  					var gnm = data[0].substring(data[0].indexOf("=")+1);
  					var gid = data[0].substring(0,data[0].indexOf("="));
  					$("input[name=nempart]").val(gid);
  					$(this).parent().next().find("input").val(gnm);
  				}
  			});
  		});
	</script>
	
	<lemis:base />
	<lemis:body>
		<lemis:title title="续签流程新建" />
		<lemis:query action="/ApaOperAction.do?method=queryApa&type=con" editorMeta="editor" topic="员工信息查询" />
		<lemis:table action="ApaOperAction.do" headerMeta="header" topic="员工信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>


