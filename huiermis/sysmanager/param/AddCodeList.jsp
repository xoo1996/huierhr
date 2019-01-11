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
            buttons.put("�� ��", "saveData()");
			buttons.put("�� ��", "go2Page(\"sysmanager\",\"\")");
            buttons.put("�� ��", "closeWindow(\"addcodeListForm\")");
            pageContext.setAttribute("button", buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:errors />
		<lemis:title title="��Ӵ���" />
		<html:form action="/codeListAction.do?method=addaa10" method="POST">
			<lemis:tabletitle title="������Ϣ" />
			<table class="tableInput">
				<lemis:editorlayout />
			<tr >
				<html:hidden property="baa109" value="1"/>
				<lemis:texteditor property="aaa100" label="�������" required="true" disable="false" maxlength="20" />
				<lemis:texteditor property="aaa101" label="�������" required="true" disable="false" maxlength="50" />
				<lemis:texteditor property="aaa102" label="����ֵ" required="true" disable="false" maxlength="20" />
  			</tr>
			<tr >
				<lemis:texteditor property="aaa103" label="��������" required="true" disable="false" maxlength="50" />
			    <lemis:codelisteditor type="aae140" label="�������" isSelect="true"	redisplay="true" required="true" />
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
