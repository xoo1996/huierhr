<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String bsc009=(String)request.getSession().getAttribute("bsc009");
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("�� ��","document.forms[0].reset();");//���ģ���¹��õİ�ť
	buttons.put("�� ��","history.back()");
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
				if(li[i].value=="�� ��") {
					li[i].disabled=true;
				}
			}
			
			obj.submit();
		}
		$(document).ready(function(){
			/* �������� */
			var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
			
			$("input[name=nempart]").autocomplete(shops,{
				max : 10,
				matchContains : true
			});
			$("input[name=nempart]").result(function(event, data, formatted) {
				if (data){
					var gnm = data[0].substring(data[0].indexOf("=")+1);
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=nempart]").val(gid);
					$("input[name=bsc009]").val(gnm);
				}
			});
		});
		function add(){
			$("form").submit();
		}
		
	</script>
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="Ա����ǩ������������" />
		<lemis:tabletitle title="Ա����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=con" method="POST">
				<tr>
					<lemis:texteditor property="nemname" label="����" required="false" disable="true" />
					<lemis:formateditor property="nemcard" label="���֤��" disable="false" required="true"  mask="card" />
					<lemis:texteditor property="nememployid" label="Ա��id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nempart" label="���ڲ���id" required="true" disable="false" />
					<lemis:texteditor property="bsc009" label="���ڲ���" required="true" disable="false" value="<%=bsc009%>"/>
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="����λ��ְʱ��" disable="false" format="true" />
					<lemis:texteditor property="nemjob" label="����ְ��" required="false" disable="false" />
				</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>ǩ���̶������Ͷ���ͬ</td>
					<lemis:texteditor property="nemlimit" label="��ͬ���ޣ��꣩" required="false" disable="false" />
					<td><input type="radio" name="nempay" value="1"/>ǩ���޹̶������Ͷ���ͬ</td>
				</tr>
				<lemis:tabletitle title="ԭ��ͬ����" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemad1" label="��һ��" required="false" disable="false" />
					<lemis:texteditor property="nemad2" label="�ڶ���" required="false" disable="false" />
					<lemis:texteditor property="nemad3" label="������" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemad4" label="���Ĵ�" required="false" disable="false" />
					<lemis:texteditor property="nemad5" label="�����" required="false" disable="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

