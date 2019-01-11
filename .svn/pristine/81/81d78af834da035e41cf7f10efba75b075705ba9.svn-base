<!--sysmanager/role/RightTree.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ page import="org.radf.manage.entity.Sc06"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单</title>
<link href="<html:rewrite forward="css"/>" rel="stylesheet"
	type="text/css">
<script	language="javascript">
<%
Sc06 sc06 = (Sc06)pageContext.findAttribute(GlobalNames.UPDATE_DATA);
	//response.setCharacterEncoding("gbk");
	String rootFuncID = (String)pageContext.findAttribute("rootFuncID");
    String roleid = "";
    String roledesc = "";
    String roledescription = "";
    String ls_card = "";
    boolean hasRightTree = false;
    boolean refresh = false;
    if(null != sc06){
      roleid =sc06.getBsc014();
      ls_card = sc06.getBsc015(); 
      
      roledescription = "角色\"" + ls_card + "\"的";
      hasRightTree = true;
    }

    if(hasRightTree){
		pageContext.getOut().println(pageContext.findAttribute(GlobalNames.RIGHT_TREE_XML));
    }

	//buttons
	List buttons=new ArrayList();
	buttons.add(new Button("save","保 存","sys050101","saveData(document.forms[0])"));
	buttons.add(new Button("close","关 闭","sys999999","closeWindow(\"sc06Form\")"));
	pageContext.setAttribute("button", buttons);
%>
function init() {
  <%
  if(hasRightTree){
  %>
   document.all.item("dialog").style.display="block";
   document.all.item("rightLabel").style.display="none";
   document.all.item("rightSave").style.display="none";
  t = new Bs_Tree();
  t.imageDir =  "/" + lemis.WEB_APP_NAME + "/images/righttree/tree/";
  t.checkboxSystemImgDir = "/" + lemis.WEB_APP_NAME + "/images/righttree/checkbox/";
  t.useCheckboxSystem      = true;
  t.checkboxSystemWalkTree = 3;
  t.initByArray(tree);
  t.drawInto('treeDiv');

  <%
  }
  %>
}
/**
 * 保存设置的权限
 */
function saveRight() {

}
function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/roleAction.do?method=saveFunctionList";
		obj.submit();
}

</script>
</head>
<body onLoad="init();">
<html:form action="/roleAction.do?method=saveFunctionList" method="POST"
	target="hiddenFrame">
	<table>
		<tr>
			<tr>
				<td align="center" id="rightLabel">
					<%if ("".equals(roledescription)) {%>
					请点击角色列表和一级模块列表，显示相应权限信息！
					 <%} else {%>
					  <%=roledescription%>权限列表
					   <%}%>
				</td>
			</tr>
			<td>
			<div id="treeDiv"></div>
			</td>
		</tr>
		<tr>
			<td>
			<table width="30%" border="0" align="center">
				<%if (hasRightTree) {%>
				<tr>
					<td width="40%"></td>
					<td id="rightSave" align="center" width="20%" style="display:none">
						<input type="hidden" name="roleid" value="<%=roleid%>"> 
						<input type="hidden" name="roledesc" value="<%=sc06.getBsc015()%>"> 
						<input type="hidden" name="rootFuncID" value="<%=rootFuncID%>"> 
						<lemis:buttons buttonMeta="button" />&nbsp;
					</td>
					<td width="40%"></td>
				</tr>
				<%}%>
			</table>
			</td>
		</tr>
	</table>
</html:form>

<%if (hasRightTree) {%>
<div id="dialog" align="center"
	style="height:50px;border-width: 1px;border-color: red;border-style: ridge;">
<table align="center">
	<tr>
		<td style="height:10px"></td>
	</tr>
	<tr>
		<td align="center" valign="middle"
			style="height:30px;color:'blue';font-size:13pt;font-family: 新宋体;"><strong>正在处理权限相关数据,请稍候...
		</strong></td>
	</tr>
	<tr>
		<td style="height:10px"></td>
	</tr>
</table>
</div>
<%}%>
<lemis:bottom />
<lemis:errors />
<iframe name="hiddenFrame" height="0" width="0"> </iframe>
<form name="hiddenForm" action="" method="POST"></form>
</body>
</html>
<script src="<html:rewrite forward="globals"/>"></script>
<script src="<html:rewrite forward="lemis_Array"/>"></script>
<script src="<html:rewrite forward="lemis_Checkbox"/>"></script>
<script src="<html:rewrite forward="lemis_Misc"/>"></script>
<script src="<html:rewrite forward="lemis_Tree"/>"></script>
<script src="<html:rewrite forward="lemis_TreeElement"/>"></script>


