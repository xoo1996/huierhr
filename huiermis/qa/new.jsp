<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("ά�޵Ǽ�","saveData(document.forms[0])");
    buttons.put("�� ��","document.forms[0].reset();");
    buttons.put("�� ��","closeWindow(\"\")");
    pageContext.setAttribute("button", buttons);
    
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">

//$("input[name=reppid]").bind("click",function(){
	$.ajax({
		 type:'post',
		 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
		 dataType:'json',
		 error:function(){
		   alert('��ȡ���ݴ���');
		 },
		 success:function(data){
					$("input[name=reppid]").autocomplete(data,{
						max:10,
						matchContains:true,
						formatItem:function(data,i,max){
							return (data.proid + "=" + data.proname);
						}
					});	
					$("input[name=reppid]").result(function(event, data, formatted) {
						if (data){
							var pid=data.proid;
							var pnm = data.proname;
							$("input[name=reppid]").val(pid);

						}
					});
				}
		//});
});
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	}
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="����������ǼǱ�" />
	<lemis:tabletitle title="������ά����Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/RepairAction.do?method=saveNew" method="POST">
			<tr>
				<lemis:texteditor property="repgctid" label="���޵�λ" required="true"
					disable="true" />
				<lemis:texteditor property="repcltid" label="�û�����" required="true"
					disable="true" />
				<lemis:formateditor mask="date" property="repdate" label="��������"
					format="true" required="true" disable="false" />
			</tr>
			<tr>
			<%-- 	<lemis:codelisteditor type="reppid" isSelect="false" label="�������ͺ�"
						redisplay="true" required="false" dataMeta="productList" /> --%>
						
				<lemis:codelisteditor type="reppid" isSelect="false" label="�������ͺ�"
						redisplay="true" required="false"/>
						
				<lemis:texteditor property="repid" label="������������" required="true"
					disable="false" />
				<lemis:codelisteditor type="repfree" isSelect="true" label="�Ƿ�����"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:codelisteditor type="reppreamt" isSelect="true" label="ά�޷ѹ���"
					redisplay="true" required="true" />
				<lemis:codelisteditor type="repcls" isSelect="true" label="�������"
					redisplay="true" required="true" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="��������" required="false"
					disable="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repattention" label="�ͻ���ע" required="false"
					disable="false" colspan="5" />
			</tr>
			<tr>
				<td>������</td>
				<td><lemis:operator /></td>
				<td>�������</td>
				<td><lemis:operorg /></td>
				<td>��������</td>
				<td><lemis:operdate /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

