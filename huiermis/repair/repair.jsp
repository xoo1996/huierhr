<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> -->

<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("ά�����","finish(document.forms[0])");
    buttons.put("�ȴ�����֪ͨ","sleep(document.forms[0])");
	buttons.put("����","history.back()");
    pageContext.setAttribute("button", buttons);
    
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">

$(document).ready(function(){
		$.ajax({
			 type:'post',
			 url:'<%=basePath%>RepairAction.do?method=getRepair',
			 dataType:'json',
			 error:function(){
			   alert('��ȡ���ݴ���');
			 },
			 success:function(data){
				 var rep1 = data[0];
				 var rep2 = data[1];
				 var rep3 = data[2];
				 var rep4 = data[3];
				 var rep5 = data[4];
				 var rep6 = data[5];
						$("input[name=repaction1]").autocomplete(rep1,{
							max:1000,
							scroll:true,
							matchContains:true,
							formatItem:function(rep1,i,max){
								return (rep1.repactionCode+"="+rep1.repaction);
							}
						});	
						$("input[name=repaction1]").result(function(event, rep1, formatted) {
							if (rep1){
								var prc=rep1.repactionPrc;
								var repname = rep1.repaction;
								$("input[name=repaction1]").val(repname);
								$("input[name=repaction1prc]").val(prc);
								$("input[name=repaction1prc]").attr("readonly","true"); 
								getprc();

							}
						});
						$("input[name=repaction2]").autocomplete(rep2,{
							max:1000,
							matchContains:true,
							formatItem:function(rep2,i,max){
								return (rep2.repactionCode+"="+rep2.repaction);
							}
						});	
						$("input[name=repaction2]").result(function(event, rep2, formatted) {
							if (rep2){
								var prc=rep2.repactionPrc;
								var repname = rep2.repaction;
								$("input[name=repaction2]").val(repname);
								$("input[name=repaction2prc]").val(prc);
								$("input[name=repaction2prc]").attr("readonly","true"); 
								getprc();

							}
						});
						$("input[name=repaction3]").autocomplete(rep3,{
							max:1000,
							matchContains:true,
							formatItem:function(rep3,i,max){
								return (rep3.repactionCode+"="+rep3.repaction);
							}
						});	
						$("input[name=repaction3]").result(function(event, rep3, formatted) {
							if (rep3){
								var prc=rep3.repactionPrc;
								var repname = rep3.repaction;
								$("input[name=repaction3]").val(repname);
								$("input[name=repaction3prc]").val(prc);
								$("input[name=repaction3prc]").attr("readonly","true"); 
								getprc();

							}
						});
						$("input[name=repaction4]").autocomplete(rep4,{
							max:1000,
							matchContains:true,
							formatItem:function(rep4,i,max){
								return (rep4.repactionCode+"="+rep4.repaction);
							}
						});	
						$("input[name=repaction4]").result(function(event, rep4, formatted) {
							if (rep4){
								var prc=rep4.repactionPrc;
								var repname = rep4.repaction;
								$("input[name=repaction4]").val(repname);
								$("input[name=repaction4prc]").val(prc);
								$("input[name=repaction4prc]").attr("readonly","true"); 
								getprc();

							}
						});
						$("input[name=repaction5]").autocomplete(rep5,{
							max:1000,
							matchContains:true,
							formatItem:function(rep5,i,max){
								return (rep5.repactionCode+"="+rep5.repaction);
							}
						});	
						$("input[name=repaction5]").result(function(event, rep5, formatted) {
							if (rep5){
								var prc=rep5.repactionPrc;
								var repname = rep5.repaction;
								$("input[name=repaction5]").val(repname);
								$("input[name=repaction5prc]").val(prc);
								$("input[name=repaction5prc]").attr("readonly","true"); 
								getprc();

							}
						});
						$("input[name=repaction6]").autocomplete(rep6,{
							max:1000,
							matchContains:true,
							formatItem:function(rep6,i,max){
								return (rep6.repactionCode+"="+rep6.repaction);
							}
						});	
						$("input[name=repaction6]").result(function(event, rep6, formatted) {
							if (rep6){
								var prc=rep6.repactionPrc;
								var repname = rep6.repaction;
								$("input[name=repaction6]").val(repname);
								$("input[name=repaction6prc]").val(prc);
								$("input[name=repaction6prc]").attr("readonly","true"); 
								getprc();

							}
						});
					}
			});
	});	
	
 	
	function finish(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("�������ݲ�ȷ��ά����ɣ�")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=finish"/>' + getAlldata(obj);
			obj.submit();
		}
	};
	function getprc(){
		var v1 = $("input[name=repaction1prc]").val();
		var v2 = $("input[name=repaction2prc]").val();
		var v3 = $("input[name=repaction3prc]").val();
		var v4 = $("input[name=repaction4prc]").val();
		var v5 = $("input[name=repaction5prc]").val();
		var v6 = $("input[name=repaction6prc]").val();
		if(v1 == "")
			v1 = 0;
		if(v2 == "")
			v2 = 0;
		if(v3 == "")
			v3 = 0;
		if(v4 == "")
			v4 = 0;
		if(v5 == "")
			v5 = 0;
		if(v6 == "")
			v6 = 0;
		var v =  parseFloat(v1) +  parseFloat(v2) +  parseFloat(v3) +  parseFloat(v4) +  parseFloat(v5) +  parseFloat(v6);
		$("input[name=repaprc]").val(v);

	};
	function sleep(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		if (confirm("�������ݲ��ȴ�����֪ͨ��")) {
			obj.action = '<html:rewrite page="/RepairAction.do?method=saveModified&require=sleep"/>' + getAlldata(obj);
			obj.submit();
		}
	};
	/* function createQueryString(e,ac) {
		var pID = $(e.target).val();
		var queryString = {
			actionID : pID,
			ac : ac
		};
		return queryString; 
	};*/
	/* function startRequest(e,ac) {
		$.getJSON("/huiermis/repair/RepairAction.do?method=getOnAction",
				createQueryString(e,ac), function(data) {
			if(ac=="REPACTION1"){
				$('input[name=repaction1prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION2"){
				$('input[name=repaction2prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION3"){
				$('input[name=repaction3prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION4"){
				$('input[name=repaction4prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION5"){
				$('input[name=repaction5prc]').val(data[0].aaa006);
			}
			if(ac=="REPACTION6"){
				$('input[name=repaction6prc]').val(data[0].aaa006);
			}
			getprc();	
				});	
	}; */
	
	/* $(document).ready( function() {
		$('#repaction1').change(function(e) {
			startRequest(e,"REPACTION1");
		});
		$('#repaction2').change(function(e) {
			startRequest(e,"REPACTION2");
		});
		$('#repaction3').change(function(e) {
			startRequest(e,"REPACTION3");
		});
		$('#repaction4').change(function(e) {
			startRequest(e,"REPACTION4");
		});
		$('#repaction5').change(function(e) {
			startRequest(e,"REPACTION5");
		});
		$('#repaction6').change(function(e) {
			startRequestRight(e,"REPACTION6");
		}); 
	});  */
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="¼��ά�޴�ʩ" />
	<lemis:tabletitle title="ά����Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		<html:form action="/RepairAction.do?method=saveModified" method="POST">
			<tr>
				<html:hidden property="repidentity" />
				<lemis:texteditor property="repfolid" label="������" required="true"
					disable="true" />
				<lemis:texteditor property="repcltnm" label="�û�����" required="true"
					disable="true" />
				<lemis:texteditor property="reppnm" label="�������ͺ�" disable="true"
					required="true" />
				<html:hidden property="reppid" />
			</tr>
			<tr>
				<lemis:texteditor property="repid" label="������" disable="true"
					required="true" />
				<lemis:texteditor property="repgctnm" label="���޵�λ" disable="true"
					required="true" />
				<lemis:formateditor mask="date" format="true" property="repdate"
					label="��������" disable="true" required="false" />
			</tr>
			<tr>
				<lemis:codelisteditor type="repfree" isSelect="false" label="������"
					redisplay="true" required="false" />
				<lemis:codelisteditor type="reppreamt" isSelect="false"
					label="ά�޷�֪ͨ" redisplay="true" required="false" />
				<lemis:codelisteditor type="repcls" isSelect="false" label="�������"
					redisplay="true" required="false" />
			</tr>
			<tr>
				<lemis:texteditor property="repdeclare" label="��������" disable="true"
					required="false" colspan="5" />
			</tr>
			<tr>
				<lemis:texteditor property="repnote" label="��ע" disable="true"
					required="false" colspan="5" />
			</tr>
			<lemis:tabletitle title="ά�޽��" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="repconfirmed" label="����ȷ��"
						disable="false" required="false" colspan="5" />
				</tr>
				<tr>
				 <lemis:texteditor property="repaction1" label="ά�޴�ʩһ"  required="false" disable="false"/>
					<lemis:texteditor property="repaction1prc" label="��ʩһ����" required="false"	disable="false" />
				   <lemis:texteditor property="repaction2" label="ά�޴�ʩ��"  required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="repaction2prc" label="��ʩ������" required="false" disable="false"  />
				 <lemis:texteditor property="repaction3" label="ά�޴�ʩ��"  required="false" disable="false" />
					<lemis:texteditor property="repaction3prc" label="��ʩ������" required="false"
						disable="false"  />
				</tr>
				<tr>
				  <lemis:texteditor property="repaction4" label="ά�޴�ʩ��"  required="false" disable="false" />
					<lemis:texteditor property="repaction4prc" label="��ʩ�ķ���" required="false"
						disable="false"  />
				 <lemis:texteditor property="repaction5" label="ά�޴�ʩ��"  required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="repaction5prc" label="��ʩ�����" required="false"
						disable="false"  />
				<lemis:texteditor property="repaction6" label="ά�޴�ʩ��"  required="false" disable="false" />
					<lemis:texteditor property="repaction6prc" label="��ʩ������" required="false"
						disable="false"  />
				</tr>
				<tr>
				  <lemis:texteditor property="repaprc" label="����Ԥ��"
						disable="false" required="false"  onclick="getprc()"/>
					<%-- <lemis:formateditor mask="nnnn.nn" property="repamt" label="���úϼ�"
						disable="false" required="true" /> --%>
					<lemis:formateditor mask="nnnn.nn" property="repamt" label="ʵ�ʷ���"
						disable="false" required="true" />
					<td>ά��Ա</td>
					<td><lemis:operator /></td>
				</tr>
				<tr>
				    <lemis:codelisteditor type="repregnames" isSelect="true"
						label="����ά��Ա" redisplay="true" required="false"
						dataMeta="userList" />
					<lemis:texteditor property="repattention" label="ע������"
						disable="false" required="false" colspan="3" />
				</tr>
				<tr>
					<lemis:texteditor property="reptip" label="��ܰ��ʾ1"
						value="��ע�����ͷ�������" disable="false" required="false" />
					<lemis:texteditor property="reptip1" label="��ע" disable="false"
						required="false" />
					<html:hidden property="repcpy" />
				</tr>
			</table>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>

