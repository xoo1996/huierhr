<!--recommendation\query\queryPrint.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<lemis:base/>
<lemis:body>
<lemis:title title="������ӡ"/>
<% 
String menuId = (String)request.getSession().getAttribute("menuId");
//�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������
  editors.add(new Editor("text","aab004","��λ����"));
  editors.add(new Editor("text","aab003","��֯��������"));
  editors.add(new Editor("text","acb200","�й����"));
  editors.add(new Editor("date","aae030","��Ƹ��ʼ���ڣ���"));
  editors.add(new Editor("date","a030ae","��"));
  editors.add(new Editor("text","acb216","��������"));
  editors.add(new Editor("date","aae043","�Ǽ����ڴ�"));
  editors.add(new Editor("date","a043ae","��"));
  editors.add(new Editor("text","aac048","�ù���ʽ"));
  
  editors.add(new Editor("text","aae017","�Ǽǻ���"));
  
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("aab003","��֯��������"));
  header.add(new Formatter("aab004","��λ����"));
  header.add(new Formatter("acb200","�й����"));
  header.add(new Formatter("acb210","��λ���"));
  header.add(new Formatter("acb21h","����"));
  header.add(new Formatter("acb216","��������"));
  header.add(new Formatter("acb21d","����"));
  header.add(new Formatter("acb221","�������"));
  header.add(new Formatter("acb222","�������"));
  header.add(new Formatter("aac011","�Ļ��̶�"));
  header.add(new Formatter("aae030","��Ƹ��ʼ����",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aae031","��Ƹ��ֹ����",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aae043","�Ǽ�����",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));

  header.add(new Formatter("aae004","��ϵ��",true));
  header.add(new Formatter("acb202","��ϵ��ַ",true));
  header.add(new Formatter("aae005","��ϵ�绰",true));
  header.add(new Formatter("cb21.acb21i","���ʴ���˵��",true));
  
  header.add(new Formatter("cb20.acb204","������",true));
  
  
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();
  hidden.put("acb210","�ոڱ��");
  //hidden.put("acb200","�й����");
//����ͷ����context���Թ���ǩ���ȡ
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons=new ArrayList();//���尴ť����
  	buttons.add(new Button("Btn_print","������ӡA4��","rec030101","printA4s(document.all.tableform)"));
	buttons.add(new Button("Btn_print","������ӡA4��","rec030102","printA4h(document.all.tableform)"));
	buttons.add(new Button("Btn_print","������ӡA3��","rec030104","printA3s(document.all.tableform)"));
	buttons.add(new Button("Btn_print","������ӡA3��","rec030103","printA3h(document.all.tableform)"));
  	buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"PrintForm\")"));
 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","������ӡ");
%>

<lemis:query action="/Rec03Action.do?method=queryPrint" editorMeta="editor" topic="��ѯ����"/>
<lemis:table topic="��ѯ���" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" mode="checkbox"/>
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
   //����������ӡ��   by iori
  function printA4s(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A4s','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
  function printA4h(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
 
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A4h','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
  function printA3s(obj){
 // var w=window.screen.width-150;
 // var h=window.screen.height-100;
 
//  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3s','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3s','print','height=450, width=800,  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  }
  function printA3h(obj){
  var w=window.screen.width-150;
  var h=window.screen.height-100;
 
  window.open('<html:rewrite page="/Rec03Action.do?method=viewPrintPost&"/>'+getAlldata(document.all.tableform)+'&page=A3h','print','height='+h+', width='+w+',  top=100, left=70, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes')
  
  }
</script>
