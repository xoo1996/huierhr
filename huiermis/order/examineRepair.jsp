<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("���","examine(document.forms[0])");
	buttons.put("����","rollback(document.forms[0])");
	buttons.put("����","history.back()");

	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "folno", "������"));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("ictid", "���˿ͻ����");
	hidden.put("repfolid","������");
	hidden.put("dgnlid","���ƻ��ͺ�");
	hidden.put("dgnrid","�Ҷ��ƻ��ͺ�");
	hidden.put("foltype","��������");
	hidden.put("folsta","����״̬");
	pageContext.setAttribute("button", buttons);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("hidden", hidden);
%>
<html>
<head>
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
<script src="/lemis/js/Globals.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->
 <script language="javascript">
	$(document).ready(function(){
		$("#repcpy").val("�ݶ�");
	});
	
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineRepair&tp=r"/>';
		 if(confirm("ȷ��Ҫ����������"))
	        {
	    		obj.submit(); 
	        }
	};
	
	function examine(obj){
		if (!checkValue(obj)) {
			return false;
		}
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineRepair&tp=e"/>';
		 if(confirm("ȷ���������ͨ����"))
	        {
	    		obj.submit(); 
	        }
	};
</script>
</head>
<lemis:base />
<lemis:body>
	<lemis:title title="������ά�޶�������" />
	<%-- 
	    <lemis:query action="/OrderAction.do?method=examineRepairQuery" editorMeta="editor"
		topic="��ѯ����" />
	--%>
	<html:form action="/OrderAction.do?method=examineRepair" method="POST">
	<lemis:tabletitle title="�ͻ�������Ϣ" />
	<table class="tableInput">
		<lemis:editorlayout />
			<tr>
				<html:hidden property="ictid" />
				<lemis:texteditor property="ictnm" label="�û�����" disable="true" />
				<%-- <lemis:texteditor property="ictgender" label="�Ա�" disable="true" />
				<lemis:texteditor property="ictbdt" label="��������" disable="true" />
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="��������" format="true"/> --%>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="��ϵ�绰" disable="true" />
			  <lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="true" />
			</tr>
			<tr>
			</tr>
	</table>
			<lemis:tabletitle title="����������Ϣ" />
			<table class="tableinput">
           <lemis:editorlayout />
           
           <tr>
				<lemis:texteditor property="folno" label="������" disable="true" />
				<lemis:texteditor property="gctnm" label="���Ƶ�λ" disable="false"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:formateditor mask="date"  property="foldt" label="��������" disable="true" required="false"/>
           </tr>
				<tr>
				  <html:hidden property="repfolid"/>
				  <lemis:codelisteditor type="repclear" label="�Ƿ������"></lemis:codelisteditor>
				  <lemis:texteditor property="reppnm" label="�������ͺ�" disable="false" />
				  <html:hidden property="reppid"/>
			      <lemis:texteditor property="repid" label="������" required="false" disable="false" />
				</tr>
				
				<tr>
			   <lemis:formateditor required="false" property="repcusdt" mask="date" label="��������" format="true" disable="true"/>
				</tr>
			</table>
			
		   <lemis:tabletitle title="ά����Ϣ" />
			<table class="tableinput">
            <lemis:editorlayout />
            <tr>
            <lemis:codelisteditor type="repsymptom" label="��������"></lemis:codelisteditor>
           <lemis:codelisteditor type="repshellsyp" label="�������󣺣���������ǣ�"></lemis:codelisteditor>
<%--            <lemis:textareaeditor colspan="1" rowspan="2" property="repotsyp" label="������������Ϳͻ�Ҫ��"></lemis:textareaeditor> --%>
                <lemis:texteditor property="repotsyp" label="������������Ϳͻ�Ҫ��" disable="false"></lemis:texteditor>
            </tr>
            <tr>
     
            <lemis:codelisteditor type="reppossyp" label="�����ʹ���ͣ������λ��"></lemis:codelisteditor>
            <lemis:codelisteditor type="repfree" label="�Ƿ��ڱ�������"></lemis:codelisteditor>
            <lemis:texteditor property="reptime" label="�ڼ���ά��" disable="false"></lemis:texteditor>
            </tr>
            <tr>
            <lemis:texteditor property="reppreamt" label="ά�޷ѳ�����Ԫ��" disable="false"></lemis:texteditor>
             <lemis:formateditor  property="reppdate" mask="date"
					label="�ƻ��깤����" format="true" disable="false" required="true"/>
             <lemis:codelisteditor type="repcpy" isSelect="true" label="���޳���"
					redisplay="true" required="true" />
			 
            </tr>
            <tr>
            <lemis:codelisteditor type="repcls" isSelect="true" label="ά�����"
					redisplay="true" required="true" />
            </tr>
            </table>
 			</html:form>   
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />

</lemis:body>
</html>