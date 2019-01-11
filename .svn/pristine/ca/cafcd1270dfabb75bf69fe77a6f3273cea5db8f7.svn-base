<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
		 $(document).ready(function(){
			 var nempay = '<%=request.getSession().getAttribute("nempay")%>';
			  $("input[name='nempay'][value='" + nempay +"']").attr("checked","checked"); 
		 }); 
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="新员工录用审批流程修改" />
		<lemis:tabletitle title="新员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/ApaOperAction.do?method=saveModifiedApa&type=nem" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="新员工录用审批流程号id" required="false" disable="true"  />
					<lemis:texteditor property="nemname" label="姓名" required="true" disable="false"  />
					<lemis:codelisteditor type="nemsex" isSelect="true" label="性别" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nembirthpl" label="籍贯" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nembirthdt" required="true" label="出生年月" disable="false" format="true"/>
					<lemis:codelisteditor type="userheightestedu" isSelect="true" label="最高学历" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemmajor" label="所学专业" required="false" disable="false"  />
					<lemis:texteditor property="nemschool" label="最后毕业学校" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nemedudt" required="false" label="毕业时间" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="岗位信息" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nempart" label="拟录用部门" required="true" disable="false" />
					<lemis:texteditor property="nemjob" label="拟录用岗位" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="拟入职时间" disable="false" format="true" />
					<lemis:codelisteditor type="nemtype" isSelect="true" label="员工类型" redisplay="true" required="true" />
				</tr>
				</table>
				<lemis:tabletitle title="薪资情况" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<td><input type="radio" name="nempay" value="0"/>月薪制</td>
					<lemis:texteditor property="nemmon1" label="试用工资每月税前（元）" required="false" disable="false" />
					<lemis:texteditor property="nemmon2" label="转正工资每月税前（元）" required="false" disable="false" />					
				</tr>
				<tr>  
                    <td><input type="radio" name="nempay" value="1"/>年薪制</td>
					<lemis:texteditor property="nemyear1" label="年薪标准为税前（元）" required="false" disable="false" />
					<lemis:texteditor property="nemyear2" label="每月发放税前（元）" required="false" disable="false" />	
				</tr>
				<tr>
					<lemis:texteditor property="nemwelfare" label="福利情况" required="false" disable="false" colspan="5"/>
				</tr>
				</table>
				<lemis:tabletitle title="劳动合同签订" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="false" />	
					<lemis:texteditor property="nemtry" label="试用期（月）" required="false" disable="false" />	
					<td>注：入职一个月内完成签订</td>
				</tr>
				</table>
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="nemnote" rows="5" disabled="false"></html:textarea>
					</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

