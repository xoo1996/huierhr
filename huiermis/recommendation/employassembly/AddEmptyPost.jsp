<!--/recommendation/employassembly/AddEmptyPost.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.recommendation.employassembly.dto.Rec0702DTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<script  src="/lemis/js/lemisTree.js" ></script>
<lemis:body>
<% 
    String menuId=request.getSession().getAttribute("menuId").toString();

  //��ȡ��Ϣ
  Rec0702DTO dto = null;
  String aab001 = "";    //��λ���
  String aab004 = "";    //��λ����
  String acb200 = "";    //��Ƹ���
  String acb201 = "";    //��Ƹ��ʽ
  
  dto = (Rec0702DTO)request.getSession().getAttribute("Rec0702DTO");
  if (dto.getAab001()!=null) aab001 = dto.getAab001().trim();
  if (dto.getAab004()!=null) aab004 = dto.getAab004().trim();
  if (dto.getAcb200()!=null) acb200 = dto.getAcb200().trim();
  if (dto.getAcb201()!=null) acb201 = dto.getAcb201().trim();

  List buttons=new ArrayList();//���尴ť����
  buttons.add(new Button("Btn_save","�� ��","rec070209","save()"));
 
  buttons.add(new Button("Btn_back","�� ��","rec999997","go2Page(\"recommendation\",\"2\")"));
  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"Rec0702DTO,Rec0702Form,addP\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//���ⲿ��%>
  <lemis:title title="������Ƹ�ո���Ϣ"/>

  <%//¼�벿��%>
  <lemis:tabletitle title="�ո���Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0702Action.do?method=addEmptyPost" method="POST">
   <tr>
      <lemis:texteditor property="aab004" label="��λ����" maxlength="100"  value="<%=aab004%>"  disable="true" colspan="3" />
      <lemis:codelisteditor type="acb201" label="��Ƹ��ʽ"   isSelect="false" key="<%=acb201%>"  redisplay="true"/>
   </tr>

    <tr>
   		 <lemis:texteditor property="aca112" required="true" label="��������" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" onkeypress="setWorkTypeTreequery(this,document.all.aca112,document.all.aca111)" disable="false" />
	  	<html:hidden property="aca111" />
      	<lemis:texteditor property="acb216" label="����˵��" maxlength="50"  disable="false" required="true" required="true" onfocus="change()"/>
	    <lemis:codelisteditor type="aac048" label="�ù���ʽ"  isSelect="true"   redisplay="true" required="true"  />

	</tr>
	   <tr>
		<lemis:formateditor mask="date" property="aae030"  label="��Ƹ��ʼ����" disable="false" required="true" format="true" />
		<lemis:formateditor mask="date" property="aae031" label="��Ƹ��ֹʱ��"  disable="false" onclick="addDateTime()" required="true"/>
		<html:hidden property="aaeyxq" />
		<lemis:formateditor mask="nnnnnnnn" property="acb21d" label="����" disable="false" required="true"/>
    </tr>
    <tr>
           

      <lemis:texteditor property="acb217" label="��������" maxlength="30"  disable="false"/>
      <lemis:texteditor property="acb215" label="������������" maxlength="100"  disable="false"/>
      <lemis:formateditor mask="nn" property="acb214" label="��ͬ���ޣ��£�"   disable="false" required="false"/>
      </tr>
       
    <tr>
     
     <lemis:texteditor property="acb21i" label="���ʴ���˵��" maxlength="100"  disable="false" colspan="5"/>
    </tr>
   
    <tr>
     <lemis:formateditor mask="nnnnnn"  property="acb21h" label="�Ͷ����꣨Ԫ��" disable="false" required="false" onblur="zdgz()"/>
     <html:hidden property="ac21hb" />
     <lemis:codelisteditor type="acb21c" label="�Ƿ�����������Ա"  isSelect="true"   redisplay="true"/>
     <lemis:codelisteditor type="acb035" label="�Ƿ����մ�����Ա"  isSelect="true"   redisplay="true"/>
    
    </tr>
	
     <tr>
     <html:hidden property="acb210" />
	  <html:hidden property="acb200" value="<%=acb200%>" />
	 <html:hidden property="aab001" value="<%=aab001%>" />
    </tr>

  </table>
   <lemis:tabletitle title="�ո�������Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>

   

      <lemis:codelisteditor type="aac011" label="�Ļ��̶�"  isSelect="true"   redisplay="true" required="true"/>
      <lemis:codelisteditor type="aac004" label="�Ա�" isSelect="true"   redisplay="true" required="false"/>
      <lemis:codelisteditor type="aac015" label="ְҵ�ʸ�ȼ�" isSelect="true" required="false" />
      </tr>

    <tr>
      <lemis:formateditor mask="nnn" property="acb221" label="�������" disable="false" required="true"/>
      <lemis:formateditor mask="nnn" property="acb222" label="�������" disable="false" required="true"/>
	   <lemis:formateditor mask="nnn.n" property="aac034" label="���(cm)" disable="false" required="false"/>
      </tr>

    <tr>
      <lemis:formateditor mask="n.n" property="aac036" label="����" disable="false" required="false"/>
      <lemis:codelisteditor type="aac009" label="��������" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="bac299" label="��ְ��Ա���" isSelect="true"   redisplay="true"/>
      </tr>
      <tr>
       <lemis:codelisteditor type="aac017" label="����״��" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="aac024" label="������ò" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="acb228" label="ʳ�����" isSelect="true"   redisplay="true"/>
    </tr>
    <tr>
       <lemis:codelisteditor type="aac014" label="רҵ����ְ��" isSelect="true" required="false" />
       <lemis:texteditor property="aae013" label="��ע" maxlength="100"  disable="false" colspan="5" />
       
    </tr>
    
    <tr>
						<td>
							������
						</td>
						<td>
							<lemis:operator />
						</td>
						<td>
							�������
						</td>
						<td>
							<lemis:operorg />
						</td>
						<td>
							��������
						</td>
						<td>
							<lemis:operdate />
						</td>
					</tr>
  </table>
    </html:form>
  <lemis:buttons buttonMeta="buttons"/>
  <%//����ģ�͹涨���������䲿��%>
 <%//����������״̬����%>

  <lemis:errors/>
  <lemis:bottom/></lemis:body>
  </html>
  <script language="javascript" >
//����˵���ĸı�
function change(){
	document.all("acb216").value=document.all("aca112").value;
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
	var acb221=parseInt(document.all("acb221").value);
	var acb222=parseInt(document.all("acb222").value);
    var aca112 = document.all("aca112").value;
    var acb210 = document.all("acb210").value;
	if (acb221>acb222)
	  {
		alert("������䲻�ܴ����������");
		return false;
	}
    if (acb210!=null&&acb210!="")
    {
    return false;
    }
    if (aca112 =="")
    {
      alert("�������Ʋ���Ϊ�գ�");
      return false;
    }
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
    
    var acb21d = document.all("acb21d").value;//����
    if ((acb21d==""))
    {
    alert("��������");
    return false;
    }
    var beginDate = document.all("aae030").value;
    var endDate = document.all("aae031").value;
   ok = compareDate(endDate,beginDate);
   if (!ok){
     alert("����Ƹ��ʼ���ڡ��������ڡ���Ƹ��ֹʱ�䡱��������ȷ����Ƹʱ��Ρ�");
     document.all("aae031").focus();
      return ok;
   }
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=saveEmptyPost&"/>';
	 obj.action = obj.action+"aab001="+ document.all("aab001").value;
	 obj.action = obj.action+"&acb200="+ document.all("acb200").value;
    obj.submit();
  }
   //ʱ��ĸı�,��Ƹ��ֹʱ��ȿ�ʼʱ����15��

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

