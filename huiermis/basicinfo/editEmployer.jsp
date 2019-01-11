<!-- basicinfo/editEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String url=request.getParameter("url");
	String buttonId = request.getParameter("buttonId");
      if (url==null) {
          url="";
      }
	 String mod=(String)request.getSession().getAttribute("mod");
	List buttons = new ArrayList();
	if ("enterpriseReg".equals(request.getParameter("menuId"))){
		buttons.add(new Button("save","保 存","bas010104","save(document.forms[0])"));
	}else if ("enterpriseQuery".equals(request.getParameter("menuId"))) {
		if ("2".equals(request.getAttribute("alter"))) {
			buttons.add(new Button("save","保 存","bas010405","save(document.forms[0])"));
		} else {
			buttons.add(new Button("save","保 存","bas010404","save(document.forms[0])"));
		}
	}else if ("enterpriseAlter".equals(request.getParameter("menuId"))) {
		buttons.add(new Button("save","保 存",buttonId,"save(document.forms[0])"));
	}
	
	buttons.add(new Button("reset","重 置","bas999998","document.forms[0].reset()"));
	buttons.add(new Button("back","返 回","bas999997","go2Page(\""+mod+"\",\"1\")"));
	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"queryEmployerForm,employerForm\")"));
	pageContext.setAttribute("buttons",buttons);
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="维护单位基本信息"/>
		<html:form action="/employerAction.do?method=updateEmployer" method="post">
			<lemis:tabletitle title="单位基本信息"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property="aab001"/>
			<tr>
			    <lemis:texteditor property="aab004" label="单位名称" maxlength="100" colspan="3" required="false" disable="false"/>
				<lemis:codelisteditor type="aab006" label="工商登记执照种类" required="false" isSelect="true" redisplay="true"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab007" label="工商登记执照号码" disable="false" maxlength="20" required="false"/>					
				<!--
				<lemis:texteditor readonly="true" disable="false" label="单位所在区" required="true" property="aaa021" style="cursor: hand" styleClass="text"
				onclick="setRegionTree(this,document.all.aaa021,document.all.aab301)"/>
				-->
				 <lemis:texteditor property="ssjqnm" required="false" label="所属机构" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.aab301)" />
				<html:hidden property="aab301" />
				<lemis:texteditor property="aab013" label="法定代表人" disable="false" maxlength="20"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab022" label="行业代码" required="true" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab019" label="单位类型" required="true" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab020" label="经济类型" required="true" redisplay="true" isSelect="true"/>
		   </tr>
		   <tr>
		         <lemis:texteditor property="aab002" label="社会保险登记证编号" disable="false" maxlength="20" required="false"/>
				<lemis:formateditor mask="date" property="aab036" label="社会保险登记证发放日期" required="false" disable="false" format="true"/>
				<html:hidden property="aab054" />
				<lemis:texteditor readonly="true" disable="false" label="产业类别" required="false" property="bab055" style="cursor: hand" styleClass="text"
				onclick="setCYLBTree(this,document.all.bab055,document.all.aab054)"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab057" label="事业单位类型" redisplay="true" isSelect="true"/>
				<lemis:formateditor mask="date" property="aab011" label="批准日期" required="false" disable="false" format="true"/>
				<lemis:codelisteditor type="cab054" label="产业分类" required="false" redisplay="true" isSelect="true"/>	
		   </tr>
		   <tr>
				<lemis:texteditor property="aab010" label="批准成立单位" disable="false" maxlength="25" required="false"/>	
				<lemis:formateditor mask="date" property="aab008" label="工商登记执照日期" required="false" disable="false" format="true"/>				
				<lemis:texteditor property="aab003" label="组织机构代码" maxlength="15" required="false" disable="false"/>
			</tr>
		    <tr>
				<lemis:texteditor property="aae006" label="地址" required="false" disable="false" maxlength="80"/>
				<lemis:formateditor property="aab009" label="工商登记有效期限（年）" mask="nnn" required="false" disable="false"/>	
				<lemis:codelisteditor type="aab021" label="隶属关系" required="false" redisplay="true" isSelect="true"/>			
			</tr>
			<tr>
				<lemis:texteditor property="aab030" label="国税税号" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab031" label="税务机构编码" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab032" label="税务机构名称" disable="false" maxlength="20" required="false"/>	
			</tr>			
			<tr>
				<lemis:formateditor mask="card" property="aab014" label="法定代表公民身份号码" required="false" disable="false"/>
				<lemis:formateditor mask="date" property="aab037" label="税务证批准日期（国）" required="false" disable="false" format="true"/>	
				<lemis:texteditor property="aab343" label="一级单位编号" required="false" disable="false" maxlength="8"/>	
			</tr>		
			<tr>
				<lemis:texteditor property="aab046" label="地税税号" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab047" label="地税税务机构名称" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab012" label="批准文号" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab034" label="社会保险经办机构编码" disable="false" maxlength="8" required="false"/>	
				<lemis:codelisteditor type="aab341" label="单位年检情况" required="false" isSelect="true" redisplay="true"/>
				<lemis:codelisteditor type="aab342" label="单位级别" required="false" isSelect="true" redisplay="true"/>
				
			</tr>	
			<tr>
				<lemis:texteditor property="aab041" label="单位英文名称" maxlength="50" required="false" disable="false"/>			
				<lemis:texteditor property="aab042" label="单位简称" maxlength="10" required="false" disable="false"/>
				<lemis:texteditor property="aab043" label="单位拼音码" maxlength="50"  disable="false" onblur="this.value=this.value.toLowerCase()"/>
			</tr>			
			<tr>
				<lemis:texteditor property="aae005" label="联系电话" disable="false" maxlength="20" required="false"/>
				<lemis:texteditor property="aae004" label="联系人" disable="false" maxlength="20" required="false"/>
				<lemis:codelisteditor type="aab056" label="人员规模" redisplay="true" isSelect="true"/>
				
			</tr>			

		
			<tr>
				<lemis:formateditor mask="######" property="aae007" label="邮政编码" disable="false" required="false"/>
				<lemis:texteditor property="aae015" label="电子邮箱" disable="false" maxlength="30"/>
				<lemis:texteditor property="aae016" label="单位网址" disable="false" maxlength="30"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae008" label="银行代码" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae009" label="银行户名" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae010" label="银行帐号" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab027" label="支付开户银行行号" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab028" label="支付银行户名" disable="false" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab029" label="支付银行基本帐号" disable="false" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aae013" label="备注" required="false" disable="false" maxlength="200" colspan="3"/>
				<lemis:texteditor property="aae014" label="传真" disable="false" maxlength="15"/>
				
			</tr>
			<tr>
				<lemis:codelisteditor type="aae011" label="经办人" isSelect="false"/>
				<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false"/>
				<lemis:texteditor property="aae036" label="经办日期"/>
			</tr>
		</html:form>
		</table>
	<lemis:buttons buttonMeta="buttons"/>
	<lemis:bottom/>
	</lemis:body>
</html>
<script src="<html:rewrite forward="lemistree"/>"></script>
<script language="javascript">
		//查看单位基本工商信息
		function viewEmployer (obj){	
			obj.action = '<html:rewrite page="/employerGsAction.do?method=viewEmployer&"/>'+
				'aab001='+obj.aab001.value+'&operation=view&menuId='+'<%=request.getParameter("menuId")%>&'+'url=<%=url%>';
			location.href=obj.action;
		
		}			
		
		//保存单位基本信息
		function save(obj) {
			if (checkValue(obj)) {
				obj.action='<html:rewrite page="/employerAction.do?method=alterEmployer"/>'+'&menuId=<%=request.getParameter("menuId")%>'+'&url=<%=url%>';
				obj.submit();
			}
		}
</script>
