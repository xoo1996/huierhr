<!-- basicinfo/viewEmployer.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.basicinfo.form.EmployerForm" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
   	EmployerForm fm = (EmployerForm)session.getAttribute("EmployerForm");
   	String url=request.getParameter("url");
	String buttonId="";
	 String dwldyw="0000000";
	 if(fm.getDwldyw()!=null){
	 	dwldyw=fm.getDwldyw();
	 }
   	List buttons=new ArrayList();
	//buttons.add(new Button("back","返 回","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("back","返 回","bas999997","history.back()"));
    buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"queryEmployerForm,employerForm\")"));
   pageContext.setAttribute("buttons",buttons);
   	
%>
<html>
	<lemis:base/>
	<lemis:body>
	<lemis:errors/>
		<lemis:title title="查看单位基本信息"/>
		<html:form action="/employerAction.do?method=viewEmp" method="post">
			<lemis:tabletitle title="单位基本信息"/>
			<table class="TableInput">
			<lemis:editorlayout/>
			<html:hidden property="aab001"/>
            <html:hidden property="aae119"/>
            <tr>
            <td >单位操作过的业务</td>
            <td colspan="5" ><div align="center">
              <input name="zyjs" class="child" type="checkbox" id="zyjs" value="1" <%=(dwldyw!=null&&dwldyw.substring(0,1).equals("1")?"checked":"")%>>
                职业介绍
                <input name="sydj" type="checkbox" class="child" id="sydj" value="1" <%=(dwldyw!=null&&dwldyw.substring(1,2).equals("1")?"checked":"")%>>
                失业登记
                <input name="jydj" type="checkbox" id="jydj" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(2,3).equals("1")?"checked":"")%>>
                就业登记 
                <input name="sybx" type="checkbox" class="child" id="sybx" value="1" <%=(dwldyw!=null&&dwldyw.substring(3,4).equals("1")?"checked":"")%>>
                失业保险征缴 
                <input name="lwpq" type="checkbox" id="lwpq" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(4,5).equals("1")?"checked":"")%>>
                劳务派遣 
                <input name="ldbz" type="checkbox" id="ldbz" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(5,6).equals("1")?"checked":"")%>>
                劳动保障 
                <input name="jwry" type="checkbox" id="jwry" class="child" value="1" <%=(dwldyw!=null&&dwldyw.substring(6,7).equals("1")?"checked":"")%>>
                境外人员 
               
            </div></td>
            </tr>
			<tr>
			    <lemis:texteditor property="aab004" label="单位名称" maxlength="100" colspan="3" required="false" disable="true"/>
				<lemis:codelisteditor type="aab006" label="工商登记执照种类" required="false" isSelect="false" redisplay="false"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab007" label="工商登记执照号码" disable="true" maxlength="20" required="false"/>					
				<lemis:texteditor property="ssjqnm" required="false" label="所属机构" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.aab301)" />
				<html:hidden property="aab301" />
				<lemis:texteditor property="aab013" label="法定代表人" disable="true" maxlength="20"/>
		   </tr>
		   <tr>
				<lemis:formateditor mask="card" property="aab014" label="法定代表公民身份号码" required="false" disable="true"/>
				<lemis:codelisteditor type="aab019" label="单位类型" required="false" isSelect="false" redisplay="true"/>
				<lemis:codelisteditor type="aab020" label="经济类型" required="false" redisplay="true" isSelect="false"/>
		   </tr>
		   <tr>
				<lemis:codelisteditor type="aab057" label="事业单位类型" redisplay="true" isSelect="false"/>
				<lemis:formateditor mask="date" property="aab011" label="批准日期" required="false" disable="true" format="true"/>
				<lemis:formateditor property="aab009" label="工商登记有效期限（年）" mask="nnn" required="false" disable="true"/>
		   </tr>
		   <tr>
				<lemis:texteditor property="aab010" label="批准成立单位" disable="true" maxlength="25" required="false"/>	
				<lemis:formateditor mask="date" property="aab008" label="工商登记执照日期" required="false" disable="true" format="true"/>				
				<lemis:texteditor property="aab003" label="组织机构代码" maxlength="15" required="false" disable="true"/>
			</tr>
		    <tr>
				<lemis:texteditor property="aae006" label="地址" required="false" disable="true" maxlength="80" colspan="3"/>
				<lemis:codelisteditor type="aab021" label="隶属关系" required="false" redisplay="true" isSelect="false"/>				
			</tr>
			<tr>
				<lemis:texteditor property="aab030" label="国税税号" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab031" label="税务机构编码" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab032" label="税务机构名称" disable="true" maxlength="20" required="false"/>	
			</tr>			
			<tr>
				<lemis:formateditor mask="date" property="aab037" label="税务证批准日期（国）" required="false" disable="true" format="true"/>		
				<lemis:codelisteditor type="aab022" label="行业代码" required="false" isSelect="false" redisplay="true"/>
				<lemis:texteditor property="aab343" label="一级单位编号" required="false" disable="true" maxlength="8"/>
			</tr>		
			<tr>
				<lemis:texteditor property="aab046" label="地税税号" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab047" label="地税税务机构名称" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab012" label="批准文号" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab034" label="社会保险经办机构编码" disable="true" maxlength="8" required="false"/>	
				<lemis:codelisteditor type="aab341" label="单位年检情况" required="false" isSelect="false" redisplay="true"/>
				<lemis:codelisteditor type="aab342" label="单位级别" required="false" isSelect="false" redisplay="true"/>
				
			</tr>	
			<tr>
				<lemis:texteditor property="aab041" label="单位英文名称" maxlength="50" required="false" disable="true"/>			
				<lemis:texteditor property="aab042" label="单位简称" maxlength="10" required="false" disable="true"/>
				<lemis:texteditor property="aab043" label="单位拼音码" maxlength="50"  required="false" disable="true" onblur="this.value=this.value.toLowerCase()"/>
			</tr>			
			<tr>
				<lemis:texteditor property="aae005" label="联系电话" disable="true" maxlength="20" required="false"/>
				<lemis:texteditor property="aae004" label="联系人" disable="true" maxlength="20" required="false"/>
				<lemis:codelisteditor type="aab056" label="人员规模" redisplay="true" isSelect="false"/>
				
			</tr>
			
			<tr>
				<lemis:codelisteditor type="aab054" label="产业类别" required="false" redisplay="true" isSelect="false"/>
				<lemis:texteditor property="aab002" label="社会保险登记证编号" disable="true" maxlength="20" required="false"/>
				<lemis:formateditor mask="date" property="aab036" label="社会保险登记证发放日期" required="false" disable="true" format="true"/>
			</tr>
			
			<tr>
				<lemis:formateditor mask="######" property="aae007" label="邮政编码" disable="true" required="false"/>
				<lemis:texteditor property="aae015" label="电子邮箱" disable="true" maxlength="30"/>
				<lemis:texteditor property="aae016" label="单位网址" disable="true" maxlength="30"/>
			</tr>
			<tr>
				<lemis:texteditor property="aae008" label="银行代码" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae009" label="银行户名" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aae010" label="银行帐号" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
				<lemis:texteditor property="aab027" label="支付开户银行行号" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab028" label="支付银行户名" disable="true" maxlength="20" required="false"/>	
				<lemis:texteditor property="aab029" label="支付银行基本帐号" disable="true" maxlength="20" required="false"/>	
			</tr>
			<tr>
			    <lemis:texteditor property="aae014" label="传真" disable="true" maxlength="15"/>
				<html:hidden property="aab301"/>
				<lemis:texteditor property="aae013" label="备注" required="false" disable="true" maxlength="200" colspan="3"/>
			</tr>
			<tr>
				<lemis:codelisteditor type="aae011" label="经办人" isSelect="false"/>
				<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false"/>
				<lemis:formateditor mask="date" property="aae036" label="经办日期" required="false" disable="true" format="true"/>
			
			</tr>
			<lemis:buttons buttonMeta="buttons"/>
		</html:form>
		</table>
	<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
		//修改单位基本信息
		function editEmployer (obj){
		
			if (checkValue(obj)) {
				obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+
				'aab001='+obj.aab001.value+'&operation=edit&menuId='+'<%=request.getParameter("menuId")%>'+
				"&buttonId=<%=buttonId%>"+
				'&url=<%=url%>';
				location.href=obj.action;
			}
		}
		//注销单位基本信息
		function writeOffEmployer(obj) {
		  if(confirm("确定要注销该单位?"))
		  {
			if (checkValue(obj)) {
				obj.action = '<html:rewrite page="/employerAction.do?method=writeOffEmployer&"/>'+
					'aab001='+obj.aab001.value+'&aae119='+obj.aae119.value+'&operation=writeOff&menuId='+'<%=request.getParameter("menuId")%>&'+'url=<%=url%>';
				location.href=obj.action;
			}
		  }
		}
		function check(url) {
        	window.location.href=url;
        }	
</script>

