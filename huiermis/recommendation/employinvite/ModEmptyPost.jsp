<!--/recommendation/employinvite/ModEmptyPost.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
	<lemis:base />
	<script src="/lemis/js/lemisTree.js"></script>
	<lemis:body>
		<%

			
			List buttons = new ArrayList();//���尴ť����
			
			buttons.add(new Button("Btn_save","�� ��", "rec040210", "save()"));
			
			buttons.add(new Button("Btn_back", "�� ��", "rec999997", "go2Page(\"recommendation\",\"2\")"));
			buttons.add(new Button("Btn_close", "�� ��", "rec999999", "closeWindow(\"modP,modEmptyPostForm\")"));
			pageContext.setAttribute("buttons", buttons);
%>
		<%//���ⲿ��%>
		<lemis:title title="�޸���Ƹ�ո���Ϣ" />
		<%//¼�벿��%>
		<lemis:tabletitle title="��Ƹ�ո���Ϣ" />
		<table class="tableInput">
			<lemis:editorlayout />
			<html:form action="/Rec0402Action.do" method="POST">
				<tr>
					<lemis:texteditor property="aab004" label="��λ����" maxlength="100" disable="true" colspan="3" />
					<lemis:codelisteditor type="acb201" label="��Ƹ��ʽ" isSelect="false" redisplay="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aca112" required="true" label="��������" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" disable="false" />
					<html:hidden property="aca111" />
					<lemis:texteditor property="acb216" label="����˵��" maxlength="50" disable="false" required="true" />
					<lemis:texteditor property="acb21o" label="�Ƽ�����" disable="true"  />
				</tr>
			    <tr>
					<lemis:formateditor mask="date" property="aae030"  label="��Ƹ��ʼ����" disable="false" format="true" required="true" />
					<lemis:formateditor mask="date" property="aae031" label="��Ƹ��ֹʱ��"  disable="false" format="true" onclick="addDateTime()" required="true"/>
					<html:hidden property="aaeyxq" />
					<lemis:formateditor mask="nnnnnnnn" property="acb21d" label="����" disable="false" required="true"/>
               </tr>
				<tr>
					
					<lemis:formateditor mask="nnnnnnnn" property="acb218" label="���Ƽ�����" disable="true" required="false" />
					<lemis:formateditor mask="nnnnnnnn" property="acb21a" label="�ѳɹ�����" disable="true" required="false" />
				    <lemis:texteditor property="acb217" label="��������" maxlength="30" disable="false" />
				</tr>
				
				<tr>
					
					<lemis:texteditor property="acb215" label="������������" maxlength="100" disable="false" />
					<lemis:codelisteditor type="aac048" label="�ù���ʽ" isSelect="true" redisplay="true" required="true" />
				    <lemis:formateditor mask="nn" property="acb214" label="��ͬ����(��)" disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="nnnnnn"  property="acb21h" label="�Ͷ����꣨Ԫ��" disable="false" required="false" onblur="zdgz()"/>
   					<html:hidden property="ac21hb" />
					<lemis:codelisteditor type="acb21c" label="�Ƿ�����������Ա" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="acb035" label="�Ƿ����մ�����Ա" isSelect="true" redisplay="true" />
					
				</tr>
				
				<tr>
					
					
					<lemis:texteditor property="acb21i" label="���ʴ���˵��" maxlength="100" disable="false" colspan="5" />
				</tr>
				

				<tr>
					<html:hidden property="aab001" />
					<html:hidden property="acb200" />
					<html:hidden property="acb208" />
					<html:hidden property="acb210" />
				</tr>
				<lemis:tabletitle title="�ո�������Ϣ" />
				<table class="tableInput">
					<lemis:editorlayout />
					<tr>



						<lemis:codelisteditor type="aac011" label="�Ļ��̶�" isSelect="true" redisplay="true" required="true" />
						<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="true" redisplay="true" required="false" />
						<lemis:codelisteditor type="aac015" label="ְҵ�ʸ�ȼ�" isSelect="true" required="false" />
					</tr>

					<tr>
						<lemis:formateditor mask="nnn" property="acb221" label="�������" disable="false" required="true" />
						<lemis:formateditor mask="nnn" property="acb222" label="�������" disable="false" required="true" />
						<lemis:formateditor mask="nnn.n" property="aac034" label="���(cm)" disable="false" required="false" />
					</tr>

					<tr>
						<lemis:formateditor mask="n.n" property="aac036" label="����" disable="false" required="false" />
						<lemis:codelisteditor type="aac009" label="��������" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="bac299" label="��Ա���" isSelect="true" redisplay="true" />
					</tr>
					<tr>
						
						<lemis:codelisteditor type="aac017" label="����״��" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="aac024" label="������ò" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="acb228" label="�Ƿ��ṩס��" isSelect="true" redisplay="true" />
					</tr>


					<tr>
						
						
						
					</tr>

					<tr>
						<lemis:codelisteditor type="aac014" label="רҵ����ְ��" isSelect="true" required="false" />
						<lemis:texteditor property="aae013" label="��ע" maxlength="100" disable="false" colspan="5" />

					</tr>
					<tr>
						
					</tr>
					
					
	<tr>
      <lemis:codelisteditor type="aae011" label="������" isSelect="false" />
        <lemis:codelisteditor type="aae017" label="�������" isSelect="false" />
      <lemis:formateditor mask="date" property="aae036" label="��������" disable="true" required="false" />
    </tr>

				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="buttons" />
		<%//����ģ�͹涨���������䲿��%>
		<%//����������״̬����%>
	

		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
  //����˵���ĸı�
function change(obj){
	obj.acb216.value=obj.aca112.value;
}
  function zdgz(){
	if(parseInt(document.all("ac21hb").value)>parseInt(format(document.all("acb21h").value)))
	{
	   alert("���ܵ��ڵ�����͹���Ҫ��"+document.all("ac21hb").value+"Ԫ");
	   return false;
	}
}
  //����
  function save(){
    var obj = document.forms[0];
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
     var aca112= document.all("aca112").value;
     var aca111= document.all("aca111").value;
     var acb221=parseInt(document.all("acb221").value);
	 var acb222=parseInt(document.all("acb222").value);
	if (acb221>acb222)
	  {
		alert("������䲻�ܴ����������");
		return false;
	}
    if (aca112=="")
    {
    alert ("�������Ʋ���Ϊ�ա�");
    return false;
    }
    var acb21d = document.all("acb21d").value;
    if ((acb21d==""))
    {
    alert("��������");
    return false;
    }
    var acb218 = document.all("acb218").value;
    var acb21a = document.all("acb21a").value;
    var acb21o = document.all("acb21o").value;
    if(acb21d=="")acb21d="0";
 
    if (parseInt(acb21a)>parseInt(acb21d)*parseInt(acb21o)){alert("�ѳɹ�����ӦС������Ƽ�������������ȷ�����ݣ�");document.all("acb21a").focus();return;}
    if (parseInt(acb218)>parseInt(acb21d)*parseInt(acb21o)){alert("���Ƽ�����ӦС������Ƽ�������������ȷ�����ݣ�");document.all("acb218").focus();return;}
    var beginDate = document.all("aae030").value;
    var endDate = document.all("aae031").value;
   ok = compareDate(endDate,beginDate);
   if (!ok){
     alert("����Ƹ��ʼ���ڡ��������ڡ���Ƹ��ֹʱ�䡱��������ȷ����Ƹʱ��Ρ�");
     document.all("aae031").focus();
      return ok;
   }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=modsaveEmptyPost"/>';
    obj.submit();
  }
  function addDateTime(){
  var str =  document.all.aae030.value;
  //var addt = 20;
  var addt =  document.all.aaeyxq.value;
  var reg1 = /^(\d{4,4})-(\d{2,2})-(\d{2,2})$/; 
  var r = str.match(reg1); 
  d= new Date(r[1], --r[2],r[3]*1+addt); 
  var iFullYear=d.getFullYear()
  var iMonth=parseInt(d.getMonth())+1;
  var iDate=parseInt(d.getDate());
  if(iMonth<10){
  	iMonth="0"+iMonth;
  }
  if(iDate<10){
  	iDate="0"+iDate;
  }
  
  var FullDate=iFullYear+"-"+iMonth+"-"+iDate;
  document.all.aae031.value = FullDate;
//  return FullDate;
}

function addDateTime(){
  var str =  document.all.aae030.value;
 // var addt = 20;
  var addt =  1*document.all.aaeyxq.value;
  
  var reg1 = /^(\d{4,4})-(\d{2,2})-(\d{2,2})$/; 
  var r = str.match(reg1); 
  d= new Date(r[1], --r[2],r[3]*1+addt); 
  var iFullYear=d.getFullYear()
  var iMonth=parseInt(d.getMonth())+1;
  var iDate=parseInt(d.getDate());
  if(iMonth<10){
  	iMonth="0"+iMonth;
  }
  if(iDate<10){
  	iDate="0"+iDate;
  }
  
  var FullDate=iFullYear+"-"+iMonth+"-"+iDate;
  document.all.aae031.value = FullDate;
//  return FullDate;
}
</script>

