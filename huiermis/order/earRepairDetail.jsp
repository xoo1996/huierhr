<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
List<Button> buttons=new ArrayList<Button>();
buttons.add(new Button("hedz","���","clt020201","examine(document.forms[0])"));
buttons.add(new Button("jcdz","����","clt020202","rollback(document.forms[0])")); 
buttons.add(new Button("back","����","clt020203","history.back()"));
    
    Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("fdtqnt", "��������");
	hidden.put("folsta","����״̬");
	
	pageContext.setAttribute("hidden", hidden);
    pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/lemis/js/Globals.js"></script>

<script>
	$(document).ready(function(){
		$("input[name=tmecls]").attr("readonly",true);
		$("input[name=fdtqnt]").attr("readonly",true);
		$("input[name=tmecls]").val("ά��");
		$("input[name=fdtqnt]").val("1");
		$("#repcpy").val("�ݶ�");
	});
</script>
<script language="javascript">
	$(document).ready(function() {
		$("input[name=tmeprc]").attr("readonly", true);
		if ($('#tmecls').val() != 'repair') {
			$("input[name=tmesid]").attr("disable", true);
		}
		$('#tmepid').change(function(e) {
			if ($('#tmefree').val() == 'Y') {
				$("input[name=tmeprc]").val(0);
			} else {
				startRequest(e);
			}
		});
		$('#tmefree').change(function() {
			if ($(this).val() == 'Y') {
				$("input[name=tmeprc]").val(0);
			} else {
				//���� tmepid ��change�¼�
				$('#tmepid').trigger("change");
			}
		});
		$('#tmecls').change(function() {
			if ($(this).val() == 'repair') {
				$("input[name=tmesid]").attr("disable", false);
				$("input[name=fdtqnt]").val("1");
				$("input[name=fdtqnt]").attr("disable", true);
			} else {
				$("input[name=tmesid]").attr("disable", true);
				$("input[name=fdtqnt]").val("");
				$("input[name=fdtqnt]").attr("disable", false);
			}
		});

		function createQueryString(e) {
			var eID = $(e.target).val();
			var queryString = {
				EarId : eID
			};
			return queryString;
		};
		function startRequest(e) {
			$.getJSON("/huiermis/order/OrderAction.do?method=queryEMPro",
					createQueryString(e), function(data) {
						$("input[name=tmeprc]").val(data[0].price);
					});
		};
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		
		$("input[name=tmectnm]").autocomplete(shops,{
			max : 10,
			matchContains : true
		});
		$("input[name=tmectnm]").result(function(event, data, formatted) {
			if (data){
				var gnm = data[0].substring(data[0].indexOf("=")+1);
				var gid = data[0].substring(0,data[0].indexOf("="));
				$("input[name=tmectnm]").val(gnm);
				$(this).parent().next().find("input").val(gid);
			}
		});
	});
	
	function rollback(obj) {
		obj.action = '<html:rewrite href="/huiermis/order/OrderAction.do?method=examineOrder&tp1=r&folType=repairEar&type=earRep&folsta="/>' + $('input:hidden[name=folsta]').val();
		 if(confirm("ȷ��Ҫ����������"))
	        {
	    		obj.submit(); 
	        }
	};
	
	function examine(obj){
		if (!checkValue(obj)) {
			return false;
		} 
		if(confirm("ȷ���������ͨ����"))
	        {
	    		obj.submit(); 
	        }
	}
</script>
<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="��ģά����Ϣ����" />
	<html:form action="/OrderAction.do?method=examineOrder&tp1=e&folType=repairEar&type=earRep" method="POST">
	<lemis:tabletitle title="�û���Ϣ" />
	<table class="tableinput">
		<lemis:editorlayout />
		
		<tr>
				<html:hidden property="ictid" />
				<html:hidden property="ictgctid" />
				<lemis:texteditor property="ictnm" label="�û�����" disable="true" />
				<lemis:texteditor property="ictgender" label="�Ա�" disable="true" />
				<lemis:formateditor required="false" property="ictbdt" mask="date" label="��������" format="true"/>
			</tr>
			<tr>
			  <lemis:texteditor property="icttel" label="��ϵ�绰" disable="true" />
			  <lemis:texteditor property="ictaddr" label="��ϵ��ַ" disable="true" />
			 <lemis:texteditor property="ictphis" label="ʹ�ù�����������" disable="true" />  
			</tr>
			<tr>
			  <lemis:texteditor property="ictsrc" label="��Դ" disable="true" />
			  <lemis:texteditor property="ictnt" label="��ע" disable="true" />
			  <lemis:codelisteditor type="ictfrom" label="��Դ" required="true" />
			</tr>
	</table>
	
		<lemis:tabletitle title="����ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lg250" label="250" required="false"
					/>
					<lemis:texteditor property="lg500" label="500" required="false"
					 />
					 <lemis:texteditor property="lg750" label="750" required="false"
					 />
					<lemis:texteditor property="lg1000" label="1000" required="false"
					 />
					 <lemis:texteditor property="lg1500" label="1500" required="false"
					 />
					<lemis:texteditor property="lg2000" label="2000" required="false"
					/>
					<lemis:texteditor property="lg3000" label="3000" required="false"
					 />
					<lemis:texteditor property="lg4000" label="4000" required="false"
					 onblur="lgvag()" />
					 <lemis:texteditor property="lg6000" label="6000" required="false"
					 onblur="lgvag()" />
					<lemis:texteditor property="lgavg" label="ƽ��" required="false"
					 />
				</tr>
			</table>

			<lemis:tabletitle title="�������" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="lq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq1000" label="1000" required="false"
						 style="border-width:1px;border-color=black" />
						 <lemis:texteditor property="lq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq2000" label="2000" required="false"
						style="border-width:1px;border-color=black" />
						<lemis:texteditor property="lq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="lqvag()" />
						 <lemis:texteditor property="lq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="lqavg" label="ƽ��" required="false"
						 style="border-width:1px;border-color=black" />
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ��ǵ�" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rg250" label="250" required="false"
						/>
					<lemis:texteditor property="rg500" label="500" required="false"
						 />
				 <lemis:texteditor property="rg750" label="750" required="false"
						 />
					<lemis:texteditor property="rg1000" label="1000" required="false"
						/>
				<lemis:texteditor property="rg1500" label="1500" required="false"
						 />
					<lemis:texteditor property="rg2000" label="2000" required="false"
						 />
				<lemis:texteditor property="rg3000" label="3000" required="false"
						 />
					<lemis:texteditor property="rg4000" label="4000" required="false"
						 onblur="rgvag()" />
				<lemis:texteditor property="rg6000" label="6000" required="false"
						 />
					<lemis:texteditor property="rgavg" label="ƽ��" required="false"
						/>
				</tr>
			</table>

			<lemis:tabletitle title="�Ҷ�����" />
			<table class="tableinput">
				<tr>
					<lemis:texteditor property="rq250" label="250" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq500" label="500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq750" label="750" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1000" label="1000" required="false"
						style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq1500" label="1500" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq2000" label="2000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq3000" label="3000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rq4000" label="4000" required="false"
						 style="border-width:1px;border-color=black" onblur="rqvag()"/>
						 <lemis:texteditor property="rq6000" label="6000" required="false"
						 style="border-width:1px;border-color=black" />
					<lemis:texteditor property="rqavg" label="ƽ��" required="false"
						style="border-width:1px;border-color=black" />
				</tr>
			</table>
			
			<lemis:tabletitle title="����������Ϣ" />
			<table class="tableinput">
           <lemis:editorlayout />
           <tr>
				<lemis:texteditor property="folno" label="������" disable="true"> </lemis:texteditor>
				<lemis:texteditor property="folctnm" label="���Ƶ�λ" disable="true"></lemis:texteditor>
				<html:hidden property="folctid"/>
				<lemis:texteditor property="foldt" label="��������"></lemis:texteditor>
		  </tr>
			</table>
			<lemis:tabletitle title="��ģ����" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:codelisteditor type="tmepid" isSelect="true" label="��ģ�ͺ�"
						redisplay="true" required="true" />
					<html:hidden property="tmepid" />
					<%-- <lemis:texteditor property="tmeprc" label="���ۼ�" required="true"
						disable="false" /> --%>
					<lemis:codelisteditor type="fdtmat" isSelect="false" label="��ģ����"
						redisplay="true" required="true"/>
				</tr>
				<tr>
					<%-- <lemis:codelisteditor type="tmefree" isSelect="true" label="�Ƿ�����"
						redisplay="true" required="true" /> --%>
					<%-- <lemis:texteditor property="fdtcls" label="�������" required="false"
						disable="false" /> --%>
					<lemis:codelisteditor type="fdtcls" label="�������" isSelect="true"></lemis:codelisteditor>
					<lemis:texteditor property="fdtqnt" label="����" required="true"
						disable="false" />
				</tr>
				<tr>
				    <lemis:texteditor property='fdtsid' label='��ģ���' disable='false' />
					<lemis:formateditor  property="reppdate" mask="date"
					label="�ƻ��깤����" format="true" disable="false" required="true"/>
					<lemis:codelisteditor type="repcpy" isSelect="true" label="���޳���"
					redisplay="true" required="true" />
					
				</tr>
				<tr>
					<lemis:texteditor property="fdtinnt" label="��ע" required="false"
						disable="false" />
				</tr>
			</table>
		</html:form>
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>
