<!-- recommendation\query\queryEmployerRecommend.jsp -->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.apps.recommendation.query.form.Rec03Form" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="��λ������Ϣ"/>
<% 
Rec03Form fm = (Rec03Form) session.getAttribute("Rec03Form");
//�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������

  //editors.add(new Editor("text","aab004","��λ����"));
  
  editors.add(new Editor("text","aab019","��λ����"));//ab01��
  editors.add(new Editor("text","aab020","��������"));//ab01��
  editors.add(new Editor("text","aab054","��ҵ���"));//ab01��
  editors.add(new Editor("text","aac048","�ù���ʽ"));//���涼��cb21��
  editors.add(new Editor("text","aca112","��Ƹ����"));
  editors.add(new Editor("text","acb216","��������"));
  editors.add(new Editor("text","acb217","��Ƹ��Χ"));
  editors.add(new Editor("text","aab004","��λ����"));//�Ѿ�����
  editors.add(new Editor("text","aab001","��λ����"));//�Ѿ�����
  //editors.add(new Editor("text","aaa021","��λϽ��"));
  editors.add(new Editor("text","aae017","�Ǽǻ���"));//??
  editors.add(new Editor("date","aae036","�Ǽ�����"));
  editors.add(new Editor("text","acb210","�й����"));
   editors.add(new Editor("text","acb208","��Ч��Ϣ��ѯ"));
   editors.add(new Editor("text","acb221","�������"));
   editors.add(new Editor("text","acb222","�������"));
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  //header.add(new Formatter("aab001","��λ����"));
  header.add(new Formatter("aab004","��λ����"));
  //header.add(new Formatter("aae006","��λ��ַ"));
  //header.add(new Formatter("aae004","��ϵ��"));
  //header.add(new Formatter("aae005","��ϵ�绰"));
  //header.add(new Formatter("acb210","��λ��Ϣ����"));
   //header.add(new Formatter("aca111","��Ƹ��λ"));
   //�µ��б�͹�ָ�λ��Ϣ
 header.add(new Formatter("acb210","�й����"));
   header.add(new Formatter("acb216","��Ƹ����˵��"));
   header.add(new Formatter("acb21d","����"));
   header.add(new Formatter("acb221","�������"));
   header.add(new Formatter("acb222","�������"));
   header.add(new Formatter("aac011","ѧ��"));
   header.add(new Formatter("acb21h","���ʴ���"));//
   //header.add(new Formatter("aae013","��ע"));
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();

    hidden.put("aab001","��λ���");
  hidden.put("acb210","��λ���");
   hidden.put("acb200","��Ƹ���");
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons=new ArrayList();//���尴ť����
  buttons.add(new Button("Btn_view","�� ��","rec030401","match(document.all.tableform)"));
  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"QueryRecEmpForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","��λ�Ƽ�������ܱ�");
%>
<lemis:query action="/Rec03Action.do?method=queryEmployerRecommend" editorMeta="editor" topic="���뵥λ�ı��"/>
<lemis:table topic="��ѯ��λ������Ϣ�����˵���Ϣ" action="/Rec03Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function match(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"oldURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>
