<!--sysmanager/dept/GroupTree.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="org.radf.plat.util.global.GlobalNames,org.radf.plat.cp.tree.Trees"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<lemis:base/>
<BODY onload="onLoad()" vLink="blue" aLink="blue" link="blue" leftMargin="0"  topMargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
<form name = "treeform" method = "post">
  <tr>
    <td height="25" class="bg_l2" align="center" colspan="2" >
      <span class="navigation">机 构</span><span class="navigation">&nbsp;</span>
    </td>
  </tr>
  <tr>
    <td bgcolor="#FFFCED" valign="top" align="left">&nbsp;<br>
     <div id="div_tree_deptTree" style="behavior:url(<%=GlobalNames.WEB_APP%>/htc/Tree.htc)" class="BaseTree"nowrap />
     <%
     	Trees groupTree = (Trees)pageContext.findAttribute("deptTree");
     %>
     <%=groupTree.print().toString()%>
    </td>
    <td bgcolor="#CCCCCC" valign="top" align="left" width="1"></td>
  </tr>
  </form>
</table>
</BODY>
</HTML>
</html>
<script src="<html:rewrite forward="groupTree"/>"></script>
<script Language="JavaScript">
    //展开默认选中的树节点
    function onLoad(){
   	        var treeid = "<%=request.getParameter("treeid")%>";
            if( "" != treeid && null != treeid){
                selectDefaultNode( treeid );
            }else{
                selectDefaultNode("01");
            }
    }
</script>
