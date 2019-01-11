<!--sysmanager/dept/sc02add.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="org.radf.manage.department.form.Sc02Form"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%			
			String menuid = "reg";
			String title = "";
			menuid = request.getParameter("menuId");
			String stringData = request.getParameter("stringData");
			if ("alter".equals(menuid)) {
				title = "机构别名信息变更";
			} else {
				title = "机构别名信息新增";
			}
			Map buttons = new LinkedHashMap();
			buttons.put("保 存", "saveData(document.forms[0])");
			buttons.put("返 回", "go2Page(\"sysmanager\",\"2\")");
			//buttons.put("返回上上级", "goBack()");
			buttons.put("关 闭", "closeWindow(\"deptForm\")");
			pageContext.setAttribute("buttons", buttons);
			Sc02Form fm = new Sc02Form();
			if (request.getSession().getAttribute("sc02Form") != null)
				fm = (Sc02Form) session.getAttribute("sc02Form");
			fm.setBsc001((String) session.getAttribute("bsc001"));
%>
<html>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="<%=title%>" />
	<lemis:tabletitle title="机构别名信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/sc02Action.do?method=saveSc02">
			<tr>
				<html:hidden property="bsc001" />
				<input name="stringData" value="<%=stringData%>" type="hidden" />
				<lemis:codelisteditor type="bsc005" isSelect="true" label="业务类别"	redisplay="true" required="true" />
				<lemis:texteditor property="bsc004" label="机构别名" required="true"	disable="false" maxlength="100" colspan="3" />
			</tr>
	</table>
	<table width="95%" height="35" border="0" align="center" cellspacing="0" class="tableInput">
		<lemis:buttons buttonMeta="buttons" />
		</html:form>
	</table>
	<lemis:bottom />
	<lemis:errors />
</lemis:body>
</html>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script language="javascript">
	function page_init(){ //方法签名不能改变
  		 eapObjsMgr.onReady('sc02Form');//codeListForm为快速录入的标签所属的form
	}
	function goBack() {
 		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=DepartmentList&stringData=" +
      	document.all("stringData").value ;
	}
	function goBack1() {

 		window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/sc02Action.do?method=findSc02&stringData=" +
      	document.all("stringData").value+"&bsc001=" +<%=(String) session.getAttribute("bsc001")%>;
	}
	function saveData(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite page="/sc02Action.do?method=saveSc02"/>';
		obj.submit();
	}
</script>









