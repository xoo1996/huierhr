<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String nemname=(String)request.getSession().getAttribute("nemname");
	String nemapplyid=(String)request.getSession().getAttribute("nemapplyid");
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("提 交","saveData(document.forms[0])");
	buttons.put("重 置","document.forms[0].reset();");//这个模块下公用的按钮
	buttons.put("返 回","history.back()");
	pageContext.setAttribute("button", buttons);

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>

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
			
			var li=$(".buttonGray");
			for(var i=0;i<li.length;i++){
				if(li[i].value=="提 交") {
					li[i].disabled=true;
				}
			}
			
			obj.submit();
		}
		//js天数 自动计算
		function computeL() {
			var sdt=$('#restsdt').val();
			var edt=$('#restedt').val();
			var day=DateDiff(sdt,edt);
			$("input[name=restnum]").val(day);
		}
		
		function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
		       var  aDate,  oDate1,  oDate2,  iDays  ;
		       aDate  =  sDate1.split("-")  ;
		       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])   ; //转换为12-18-2006格式  
		       aDate  =  sDate2.split("-")  ;
		       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  ;
		       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)+1 ;   //把相差的毫秒数转换为天数  
		       return  iDays  ;
		   }    
	
		function add(){
			$("form").submit();
		}
		
		
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工请假申请流程新增" />
		<lemis:tabletitle title="请假时间" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=rest" method="POST">
				<tr>
					<lemis:formateditor mask="date" property="restsdt" required="true" label="起始时间" disable="false" format="true"/>
					<lemis:formateditor mask="date" property="restedt" required="true" label="结束时间" disable="false" format="true" />
					<lemis:texteditor property="restnum" label="请假天数" required="true" disable="false" onclick="computeL()" />
				</tr>
				<lemis:tabletitle title="请假类别" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="resttype" isSelect="true" label="请假类别" redisplay="true" required="true"/>
					<lemis:texteditor property="restother" label="其他" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemname" label="申请人" required="false" disable="true" value="<%=nemname %>"/>
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" value="<%=nemapplyid %>" />
					<td>申请日期</td><td><lemis:operdate/></td>
				</tr>
				<tr>
					<lemis:texteditor property="restreason" label="请假事由" required="true" disable="false" colspan="5"/>
				</tr>
				</table>
				
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="restnote" rows="5" disabled="false"></html:textarea>
				</tr>
					
				</table>
				
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

