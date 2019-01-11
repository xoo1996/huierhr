<!--sysmanager/dept/deptaddmod.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="org.radf.manage.department.form.DeptForm"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%			
			String menuid = "reg";
			String title = "";
			menuid = request.getParameter("menuId");
			 String stringData = request.getParameter("stringData");
			if ("add".equals(menuid)) {
				title = "机构信息新增";
			} else {
				title = "机构信息变更";
			}
			List buttons=new ArrayList();
			DeptForm fm=null;
			if (request.getSession().getAttribute("deptForm") != null)
				  fm = (DeptForm) session.getAttribute("deptForm");
%>
<html>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="<%=title%>" />
	<lemis:tabletitle title="机构信息" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/deptAction.do?method=saveDept">
			<tr>
				<html:hidden property="bsc001" />
				<html:hidden property="treeid" />
      			<html:hidden property="groupid" />
					
					<lemis:texteditor property="aab003" label="机构编码" required="true" disable="false" maxlength="10" />
					<lemis:texteditor property="aab300" label="机构名称" required="true" disable="false" maxlength="64" colspan="3" />
			</tr>
			<tr>
					<lemis:formateditor property="bsc051" label="机构人数" required="false" mask="nnnn" disable="false" />
					<lemis:formateditor property="bsc052" label="建立机构时间" required="false" mask="date" disable="false" format="true"/>
					<lemis:codelisteditor type="bsc053" label="是否市区机构" required="false" redisplay="true" isSelect="true"/>	
			</tr>
			<tr>
					<lemis:texteditor property="aae005" label="联系电话" required="false" disable="false" maxlength="64" />
					<lemis:texteditor property="aae006" label="机构地址" disable="false" maxlength="64" colspan="3" />
			</tr>
			<tr>
					<lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######" disable="false" />
					<lemis:texteditor property="aab304" label=" 联系人" disable="false" maxlength="32" />
					<lemis:texteditor property="bsc002" label="机构简称" disable="false" maxlength="32" />
			</tr>
				<tr>
					<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="200" colspan="5" />
				</tr>
				<input name="stringData" value="<%=stringData%>" type="hidden" />
				
		</table>
		<table width="95%" height="35" border="0" align="center" cellspacing="0" class="tableInput">
  <tr>
    <td class="action">
    	<%if(fm.getTreeid()!=null&&!fm.getTreeid().substring(0,1).equals("K")&&!"add".equals(menuid)&&!fm.getTreeid().equals("01")&&!fm.getTreeid().equals("00"))
    	{
    		buttons.add(new Button("del","注销本机构","sys010103","delGroup()"));
    	}%>
    	<%if(fm.getTreeid()!=null&&!fm.getTreeid().substring(0,1).equals("K")&&!"add".equals(menuid)&&!fm.getTreeid().equals("00")) {
    		buttons.add(new Button("add","增加子机构","sys010101","addSubGroup()"));
	    	}
    	  if(fm.getTreeid()!=null&&!fm.getTreeid().substring(0,1).equals("K")&&!"add".equals(menuid)&&!fm.getTreeid().equals("01")&&!fm.getTreeid().equals("00")) {
    		buttons.add(new Button("add","增加部门","sys010104","addSubGroup1()"));
	    	}
	      if(fm.getTreeid()!=null&&!fm.getTreeid().equals("00")) {
			buttons.add(new Button("save","保 存","sys010102","saveData(document.forms[0])"));
			buttons.add(new Button("reset","重 置","sys888888","document.forms[0].reset()"));
    	 }
		  pageContext.setAttribute("button", buttons);
    	 %>
    </td>
  </tr>
</html:form>
</table>
<lemis:buttons buttonMeta="button"/>
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
		document.all("treeid").value + "&parentid="+document.all("groupid").value+"&menuId=add";
}
function addSubGroup1(){
	location.href = "/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=findDept1&treeid="+
		document.all("treeid").value + "&parentid="+document.all("groupid").value+"&menuId=add";
}
function page_init(){
	<%
		String isRefreshTree = (String)pageContext.findAttribute("refreshTree");
		String treeid = (String)pageContext.findAttribute("treeid");
		if("true".equalsIgnoreCase(isRefreshTree)){%>
			parent.document.all("treeFrame").src="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=loadDepartmentTree&treeid=<%=treeid%>";
	<%  }%>
}
function delGroup(){
    if(confirm("此操作不能回退，确信要注销此机构吗？")){
      window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=zhuxiao&groupid="
      	+ document.all.groupid.value 
      	+ "&treeid=" + document.all.treeid.value;
    }else{
      return false;
    }
}
function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=saveDept";
		obj.submit();
}
</script>				
				
				
				
				
				
				
				
				
				
