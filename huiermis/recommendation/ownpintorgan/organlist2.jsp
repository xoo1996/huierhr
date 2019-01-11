<!--lemis/recommendation/ownpintorgan/organlist2.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<html>
<lemis:base/>
<lemis:body>
<%
  //�����ѯ����������
  List editors = new ArrayList();
  editors.add(new Editor("text", "acb241", "��������"));
  editors.add(new Editor("text", "aab019", "��ҵ����"));
  
  
  //�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("acb241", "��������"));
  header.add(new Formatter("aab019", "��ҵ����"));
  header.add(new Formatter("aae005", "��ϵ�绰"));
  header.add(new Formatter("acb247", "������Ա����"));
  header.add(new Formatter("acb248", "��ְ���ʸ�����"));
  header.add(new Formatter("acb24a", "�С����������������"));
  header.add(new Formatter("aae011", "������"));
  header.add(new Formatter("aae017", "�������"));
  header.add(new Formatter("aae036", "����ʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
  
  //������������
  Map hidden=new LinkedHashMap();
  hidden.put("acb240","����");
  pageContext.setAttribute("hidden",hidden);
  
  //���尴ť
  List buttons=new ArrayList();//���尴ť����
   buttons.add(new Button("search","�鿴","rec080403","search(document.all.tableform)"));
  buttons.add(new Button("add","Ͷ�ߵǼ�","rec080401","tologin(document.all.tableform)"));
  buttons.add(new Button("close","�� ��","rec999999","closeWindow(\"Ree08Form\")"));
  //����context���Թ���ǩ���ȡ
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","������Ϣ��");

%>
<lemis:title title="�����ճ�Ͷ�ߵǼ�"/>
<lemis:query action="/Rec0803Action.do?method=enterqueryorgan"  editorMeta="editor" topic="��ѯ����"/>
<lemis:table topic="�����б�" action="/Rec0803Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//���ӻ���Ͷ����Ϣ
function tologin(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=initAddorgancom&"/>'+getAlldata(obj);
    obj.submit();
  }
  
    //�鿴������Ϣ
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec08Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
  
</script>

