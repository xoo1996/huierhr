<!--/recommendation/employinvite/ModEmployInvite.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<% 
    String menuId=request.getSession().getAttribute("menuId").toString();
  String modE = (String)request.getSession().getAttribute("modE");
  String date = DateUtil.getSystemCurrentTime().toString();
  //���尴ť
  List buttons=new ArrayList();//���尴ť����
  buttons.add(new Button("Btn_save", "�� ��", "rec040204", "save()"));
  buttons.add(new Button("Btn_back", "�� ��", "rec999997", "go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close", "�� ��", "rec999999", "closeWindow(\"modE,Rec0402Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//���ⲿ��%>
  <lemis:title title="�޸ĵ�λ��Ƹ��Ϣ"/>

  <%//¼�벿��%>
  <lemis:tabletitle title="��λ��Ƹ��Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0402Action.do?method=modEmployInvite" method="POST">
  <tr>
      <html:hidden property="aab001"/>
      <html:hidden  property="acb200" />
      <lemis:texteditor property="aab004" label="��λ����" maxlength="100" disable="true" colspan="3" />
      <lemis:codelisteditor type="aab019" label="��λ����"  isSelect="false"  redisplay="true"/>
      </tr>
    <tr>
     <lemis:codelisteditor type="aab020" label="��������"  isSelect="false"  redisplay="true"/>
      <lemis:codelisteditor type="aab022" label="��ҵ����"   isSelect="false"  redisplay="true"/>
      <lemis:texteditor property="aab003" label="��֯��������" maxlength="15" disable="true"/>
      </tr> 
  </table>
  <lemis:tabletitle title="��λ��Ƹ��Ϣ"/>
  <table class="tableInput">
  <lemis:editorlayout/>
     <tr>      
      <lemis:texteditor  property="acb206" label="��������" maxlength="20" disable="false" required="true" colspan="3" />
      <lemis:formateditor mask="money"  property="acb209" label="�շѽ��" disable="false" required="false"/>
    </tr>
    <tr>
      <lemis:codelisteditor type="acb201" label="��Ƹ��ʽ"  isSelect="false"  key="1" redisplay="true" required="false" />
	     <lemis:texteditor property="aae004" label="��ϵ��" maxlength="20" disable="false" required="true" />
		  <lemis:texteditor property="aae005" label="��ϵ�绰" maxlength="20"  disable="false" required="true" />
    
    </tr>

	<tr>
     <lemis:formateditor mask="date" property="aae043" label="�Ǽ�����"  disable="false" required="true" format="true"/>
      <lemis:texteditor property="acb204" label="�����ص�" maxlength="80" disable="false" colspan="3" />
    </tr>
    <tr>
      
      <lemis:texteditor property="acb205" label="�˳�·��" maxlength="200" disable="false"/>
      <lemis:texteditor property="acb202" label="�����ص�" maxlength="30" disable="false" colspan="3"/>
    </tr>
    <tr>
      
     
    </tr>
    <tr>
      <lemis:texteditor property="acb203" label="��Ƹ����" maxlength="30" disable="false" colspan="3"/>
      <lemis:formateditor mask="card" property="aac002" label="��ϵ����ݺ���" disable="false" required="false"/>
     
    </tr>
    
    <tr>
      <lemis:formateditor mask="date" property="acb207" label="��������" disable="false" required="false" format="true"/>
      <lemis:texteditor property="acb20c" label="���Եص�" maxlength="50"  disable="false" colspan="3" />
    </tr>
    <tr>    
      <lemis:texteditor property="acb20f" label="������ϸ˵��" maxlength="200" disable="false" colspan="3" />
      <lemis:texteditor property="acb20b" label="����ְ��Ա" maxlength="30"  disable="false"/>
    </tr> 
    <tr>
     
      <lemis:texteditor property="aae013" label="��ע" maxlength="100"  disable="false" colspan="5"/>
	
    </tr>
    <tr>
       <html:hidden property="aae011"/>
       <html:hidden property="aae017"/>
       <html:hidden property="aae036"/>
    </tr> 
  </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>

  <%//����ģ�͹涨���������䲿��%>
  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //��һ��
  function back(url){
    location.href=url;
  }
  //����
  function save(){
	
    var obj = document.forms[0];
	//addDateTime()
	var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
   
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=modsaveEmployInvite"/>';
    obj.submit();
  }
  // �ж���ǰ���Ⱥ�˳��
  function checkDate(obj) {
  	if (!compareDate(obj.aae030.value,obj.aae043.value)) {
  		alert("��Ƹ��ʼ����Ӧ�����ڵǼ����ڣ�");
  		obj.aae030.focus();
  		return false;
  	}
  	
  	if(obj.acb207.value!="")
  	{
  	if (!compareDate(obj.acb207.value,obj.aae030.value)) {
  		alert("��������Ӧ��������Ƹ��ʼ���ڣ�");
  		obj.acb207.focus();
  		return false;
  	}
  	if (!compareDate(obj.aae031.value,obj.acb207.value)) {
  		alert("��Ƹ��ֹ����Ӧ�������������ڣ�");
  		obj.aae031.focus();
  		return false;
  	}
  }
  	return true;
  }


    //ʱ��ĸı�,��Ƹ��ֹʱ��ȿ�ʼʱ����15��

function addDateTime(){
  var str =  document.all.aae030.value;
  var addt = 20;
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
  // �����ǰ���Ⱥ�˳�� form���ַ��������ڵ��Ⱥ�˳���ַ������Ӵ�С
  function checkDates(obj,obj2) {
  	if (obj2==null) {
  		alert("��Ҫ�Ƚϵ����ڲ��㣬���ܱȽϣ�");
	  	return 	false;
  	}
  	var cDate = obj2.split(";");
  	if (obj2.length < 2) {
  		alert("��Ҫ�Ƚϵ����ڲ��㣬���ܱȽϣ�");
	  	return 	false;
  	}
  	var oDate	=	new Array();
  	for (var i=0;i<cDate.length;i++) {
  		oDate[i]	=	eval(obj+"."+cDate[i]);
  	}
  	for (var i=1;i<oDate.length;i++) {
  		if (checkDate(oDate[i-1],oDate[i])) {
  		} else {
  			return false;
  		}
  	}
  	return true;
  }
 
</script>

