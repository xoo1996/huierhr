<!--sysmanager/dept/ksaddmod.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="org.radf.manage.department.form.DeptForm"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			String menuid = "reg";
			String title = "";
			menuid = request.getParameter("menuId");
			 String stringData = request.getParameter("stringData");
			if ("alter".equals(menuid)) {
				title = "机构信息变更";
			} else {
				title = "科室信息新增";
			}
			DeptForm fm=null;
			if (request.getSession().getAttribute("deptForm") != null)
				  fm = (DeptForm) session.getAttribute("deptForm");
			  
			List buttons=new ArrayList();
			String hasChildren = request.getParameter("hasChildren");
    		if("false".equalsIgnoreCase(hasChildren)){
				buttons.add(new Button("delete","注销本科室","sys010105","delGroup()"));
    		}
			if((fm.getTreeid()!=null&&!fm.getTreeid().equals("00"))){
				buttons.add(new Button("add","保 存","sys010104","saveData(document.forms[0])"));
	    	} 
			buttons.add(new Button("delete","返 回","sys999999","history.back()"));
			pageContext.setAttribute("button", buttons);
%>
<html>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="<%=title%>" />
	<lemis:tabletitle title="机构信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/deptAction.do?method=saveKs">
			<tr>
				<html:hidden property="bsc001" />
				<html:hidden property="bsc008" />
				<html:hidden property="treeid" />
      			<html:hidden property="groupid" />
				<lemis:codelisteditor type="bsc005" isSelect="true" label="业务类别" redisplay="true" required="false" />
				<lemis:texteditor property="bsc009" label="部门名称" required="true" disable="false" maxlength="100" colspan="3" />
			</tr>
		</table>
		<table width="95%" height="35" border="0" align="center" cellspacing="0" class="tableInput">
  			<tr>
   				<lemis:buttons buttonMeta="button" />
  			</tr>
        </html:form>
		</table>
<lemis:bottom/>
</lemis:body>
</html>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script language="javascript">
	function page_init(){ //方法签名不能改变
   		eapObjsMgr.onReady('deptForm');//codeListForm为快速录入的标签所属的form
	}
	function addSubGroup(){
		location.href = "/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=findDept&treeid="+
		document.all("treeid").value + "&parentid="+document.all("groupid").value;
	}
	function delGroup(){
    	if(confirm("此操作不能回退，确信要注销此科室吗？")){
      	window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=zhuxiao&groupid="
      	+ document.all.groupid.value 
      	+ "&treeid=" + document.all.treeid.value;
    	}else{
     		return false;
    	}
	}
	function page_init(){
	<%
		String isRefreshTree = (String)pageContext.findAttribute("refreshTree");
		String treeid = (String)pageContext.findAttribute("treeid");
		if("true".equalsIgnoreCase(isRefreshTree)){%>
			parent.document.all("treeFrame").src="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=loadDepartmentTree&treeid=<%=treeid%>";
	<%  }%>
	}
	function saveData(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite page="/deptAction.do?method=saveKs"/>';
		obj.submit();
	}
</script>				
				
				
				
				
				
				
				
				
				
