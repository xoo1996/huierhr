<!--sysmanager/param/AddCodeList.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
 String addurl = "";
 if (request.getParameter("addurl")!=null){
     addurl = request.getParameter("addurl");
     request.getSession().setAttribute("addurl",addurl);
     }
            String stringData = request.getParameter("stringData");
            if ((null == stringData) || ("".equals(stringData)))
            {
                QueryInfo qi = (QueryInfo) pageContext
                        .findAttribute(GlobalNames.QUERY_INFO);
                if (null != qi)
                {
                    stringData = qi.getStringData();
                }
            }
            Map buttons=new LinkedHashMap();
            buttons.put("保 存", "saveData()");
			buttons.put("返 回", "go2Page(\"sysmanager\",\"\")");
            buttons.put("关 闭", "closeWindow(\"addcodeListForm\")");
            pageContext.setAttribute("button", buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:errors />
		<lemis:title title="添加代码" />
		<html:form action="/codeListAction.do?method=addaa10" method="POST">
			<lemis:tabletitle title="代码信息" />
			<table class="tableInput">
				<lemis:editorlayout />
			<tr >
				<html:hidden property="baa109" value="1"/>
				<lemis:texteditor property="aaa100" label="代码类别" required="true" disable="false" maxlength="20" />
				<lemis:texteditor property="aaa101" label="类别名称" required="true" disable="false" maxlength="50" />
				<lemis:texteditor property="aaa102" label="代码值" required="true" disable="false" maxlength="20" />
  			</tr>
			<tr >
				<lemis:texteditor property="aaa103" label="代码名称" required="true" disable="false" maxlength="50" />
			    <lemis:codelisteditor type="aae140" label="代码大类" isSelect="true"	redisplay="true" required="true" />
			</tr>
		</table>
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		</html:form>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom/>
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
     obj.action= '<html:rewrite page="/codeListAction.do?method=addaa10&"/>';
     obj.submit();
  }
</script>
