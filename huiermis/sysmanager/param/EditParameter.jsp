<!--sysmanager/param/EditParameter.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			String addurl = "";
			if (request.getParameter("addurl") != null) {
				addurl = request.getParameter("addurl");
				request.getSession().setAttribute("addurl", addurl);
			}
			System.out.println(addurl);
			System.out.println("@@@@@@");
			String stringData = request.getParameter("stringData");
			if ((null == stringData) || ("".equals(stringData))) {
				QueryInfo qi = (QueryInfo) pageContext
						.findAttribute(GlobalNames.QUERY_INFO);
				if (null != qi) {
					stringData = qi.getStringData();
				}
			}
			Map buttons = new LinkedHashMap();
			buttons.put("保 存", "saveData()");
			buttons.put("返 回", "go2Page(\"sysmanager\",\"\")");
			buttons.put("关 闭", "closeWindow(\"editcodeListForm\")");
			pageContext.setAttribute("button", buttons);
			String menuId=(String)request.getSession().getAttribute("menuId");
%>
<html>
<lemis:base />
<lemis:body>
	<lemis:errors />
	<lemis:title title="修改参数" />
	<html:form action="/ParameterAction.do" method="POST">
		<lemis:tabletitle title="参数信息" />
		<table class="tableInput">
			<lemis:editorlayout />

			<tr>
				<html:hidden property="aae100" value="1" />
				<lemis:codelisteditor type="aae140" label="参数类型" isSelect="false"
					redisplay="true" required="false" />
				
					<%if("gs".equals(menuId)){ %>
				<lemis:codelisteditor type="aa001a" label="公式类别" isSelect="false"
					redisplay="true" required="true" />
				<%}else if("lc".equals(menuId)){ %>
				<lemis:codelisteditor type="a001aa" label="流程类别" isSelect="false"
					redisplay="true" required="true" />
				<%}else{ %>
				<lemis:texteditor property="aaa001" label="参数类别代码" required="false"
					disable="false" maxlength="20" />
				<%} %>
					<lemis:texteditor property="aaa003" label="参数代码" required="false"
					disable="true" maxlength="20" />
			</tr>
			<tr>
			<%if(!"lc".equals(menuId)&&!"gs".equals(menuId)){ %>
					<lemis:texteditor property="aaa002" label="参数类别名称" required="false"
					disable="false" maxlength="20" />
					<%} %>
					
				<lemis:texteditor property="aaa004" label="参数名称" required="false"
					disable="false" maxlength="20" />
					<%if("lc".equals(menuId)){  %>
				<lemis:codelisteditor type="aa005a" label="参数值" isSelect="true"
					redisplay="true" required="false" />
					<%}else{ %>
						<lemis:texteditor property="aaa005" label="参数值" required="false"
					disable="false" maxlength="20" />
			</tr>
			<tr>
					<lemis:texteditor property="aaa006" label="维修费用" required="false"
					disable="false" maxlength="20" />
					<%} %>
			
				<%if(!"lc".equals(menuId)){ %>
			
				<lemis:formateditor mask="date" property="aae030" required="false"
					disable="false" label="开始时间" format="true" />
				<lemis:formateditor mask="date" property="aae031" required="false"
					disable="false" label="终止时间" format="true" />
				
			</tr>
			<%} %>
			<tr>
				<lemis:texteditor property="aae013" label="备注" required="false"
					disable="false" maxlength="100" colspan="5" />
			</tr>
		</table>
		<input name="stringData" value="<%=stringData%>" type="hidden" />
	</html:form>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
   function saveData() {
       var obj = document.forms[0];   
       var ok = checkValue(obj);
   if (!ok){
      return ok;
    }
     obj.action= '<html:rewrite page="/ParameterAction.do?method=updateaa01&"/>';
     obj.submit();
  }
</script>

