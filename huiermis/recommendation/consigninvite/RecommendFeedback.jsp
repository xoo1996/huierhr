<!--recommendation\consigninvite\RecommendFeedback.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<lemis:base/>
<lemis:body>
<lemis:title title="ѡ���Ƽ���Ϣ"/>
<% 

String menuId = (String)request.getSession().getAttribute("menuId");
String acc223=(String)request.getSession().getAttribute("acc223");

//�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������

  editors.add(new Editor("text","acc223","״̬","true"));
  editors.add(new Editor("card","aac002","������ݺ���"));
  editors.add(new Editor("text","aac003","����"));
  editors.add(new Editor("text","aab004","��λ����"));
  editors.add(new Editor("date","ace014","�Ƽ���Ч���ڴ�"));
  editors.add(new Editor("date","a014ce","��")); 
  editors.add(new Editor("test","aca111","רҵ����"));
  editors.add(new Editor("text","acb200","�й����"));
  editors.add(new Editor("text","acb210","��λ����"));
  
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("aac002","������ݺ���"));
  header.add(new Formatter("aac003","����"));
  header.add(new Formatter("aac004","�Ա�"));
  header.add(new Formatter("aac006","��������",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("acb210","��λ����"));
  header.add(new Formatter("acb216","����˵��"));
  header.add(new Formatter("aab004","ӦƸ��λ����"));
  header.add(new Formatter("acb200","�й����"));
  header.add(new Formatter("aae004","��ϵ��"));
  header.add(new Formatter("aae005","��λ��ϵ�绰"));
  header.add(new Formatter("acc223","״̬"));
  header.add(new Formatter("ace014","�Ƽ���Ч����",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();
  hidden.put("acc220","�Ƽ����");
  hidden.put("acc200","��ְ���");
  hidden.put("acc223","״̬");
  hidden.put("aac001","��Ա���");
  hidden.put("acb200","�й����");
  hidden.put("acb210","��λ���");
  hidden.put("aab001","��λ���");
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons = new ArrayList();//���尴ť����
  if("0".equals(acc223))
  {
	      buttons.add(new Button("Btn_print","��ӡ�Ƽ�","rec010501", "print(document.all.tableform)"));
		  buttons.add(new Button("Btn_success","�� ��","rec010502", "success(document.all.tableform,\""+menuId+"\")"));
		  buttons.add(new Button("Btn_lost","ʧ ��","rec010503", "lost(document.all.tableform)"));
		  buttons.add(new Button("Btn_lost","ɾ ��","rec010504", "del(document.all.tableform)"));

  }else if("6".equals(acc223))
  {
      buttons.add(new Button("Btn_dwxr1","��ϵ�ɹ�","rec010505", "dwxr1(document.all.tableform)"));
	  buttons.add(new Button("Btn_dwxr3","��ϵʧ��","rec010507", "dwxr3(document.all.tableform)"));
	  buttons.add(new Button("Btn_lost","ɾ ��","rec010504", "del(document.all.tableform)"));
  }else if("7".equals(acc223))
  {
	  buttons.add(new Button("Btn_dwxr2","��λѡ���Ƽ�","rec010506", "dwxr2(document.all.tableform)"));
	  buttons.add(new Button("Btn_lost","ɾ ��","rec010504", "del(document.all.tableform)"));
 }

	  

  

  buttons.add(new Button("Btn_close","�� ��","rec999999", "closeWindow(\"sRecommendFeedbackForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","�Ƽ�����Ϣ"); 
%>
<lemis:query action="/Rec0105Action.do?method=SRecommendFeedback" editorMeta="editor" topic="��ѯ����"/>

<lemis:table topic="��ѯ���" action="/Rec0105Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 
 function success(obj,menuId) {
 var t = editObj("chk");
  if(!t){
    return t;
  }
	 var truthBeTold = window.confirm("�Ƿ�ȷ���ɹ���");
	 if (truthBeTold) {
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBSuccess&"/>'+getAlldata(obj)+ '&menuId='+menuId;
    obj.submit();
  }
else  window.alert("������������������"); 
  }

  function lost(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("�Ƿ�ȷ��ʧ�ܣ�");
	if(truthBeTold){
    var response = window.prompt("������ʧ�ܵ�ԭ��","");
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&"/>'+getAlldata(obj)+ "&respon="+response;
    obj.submit();
    }
	else  window.alert("������������������"); 
  }
 function del(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("�Ƿ�ȷ��ɾ����");
	if(truthBeTold){
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBdel&"/>'+getAlldata(obj);
    obj.submit();
    }
	else  window.alert("������������������"); 
  }
//��ӡ��ť
    function print(obj) {
	var truthBeTold = window.confirm("�Ƿ�ȷ����ӡ��");
if (truthBeTold) {
var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=viewRec&"/>'+getAlldata(obj);
    obj.action=obj.action+"RecommendURL="+location.href.replace(/\&/g,";amp;");
	obj.action=obj.action+"&acc220="+document.all("acc220").value;
    obj.submit();

}
else  window.alert("������������������"); 
  }
  function dwxr1(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("�Ƿ�ȷ����ϵ�ɹ���");
	
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&flag=7&"/>'+getAlldata(obj);
    obj.submit();
   
  }
   function dwxr3(obj) {
  var t = editObj("chk");
  if(!t){
    return t;
  }
	var truthBeTold = window.confirm("�Ƿ�ȷ����ϵʧ�ܣ�");
	
    obj.action= '<html:rewrite page="/Rec0105Action.do?method=RecommendFBLost&flag=8&"/>'+getAlldata(obj);
    obj.submit();
   
  }
  function dwxr2(obj) {
	var truthBeTold = window.confirm("�Ƿ�ȷ���Ƽ���");
	if (truthBeTold) {
	var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=Recommend&"/>'+getAlldata(obj);
    
    obj.submit();
	}

  }
 
 document.Rec0105Form.acc223.remove(2);
 document.Rec0105Form.acc223.remove(2);
 document.Rec0105Form.acc223.remove(4);
</script>


