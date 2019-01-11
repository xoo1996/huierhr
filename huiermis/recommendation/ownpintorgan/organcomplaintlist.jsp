<!--lemis/recommendation/ownpintorgan/organcomplaintlist.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,org.radf.plat.commons.QueryInfo,com.lbs.cp.taglib.Formatter,org.radf.plat.util.global.GlobalNames,org.radf.plat.taglib.TagConstants" %>
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
  header.add(new Formatter("cb24.acb241", "��������"));
  header.add(new Formatter("cb24.aab019", "��ҵ����"));
  header.add(new Formatter("cb24.aae005", "��ϵ�绰"));
  header.add(new Formatter("cb26.acb261", "Ͷ������"));
  header.add(new Formatter("cb26.acb262", "����"));
  header.add(new Formatter("cb26.aae011", "������"));
  header.add(new Formatter("cb26.aae017", "�������"));
  header.add(new Formatter("cb26.aae036", "����ʱ��",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  pageContext.setAttribute("header",header);
  
  //������������
  Map hidden=new LinkedHashMap();
  hidden.put("acb260","����");
  pageContext.setAttribute("hidden",hidden);
  
  //���尴ť
   List buttons=new ArrayList();//���尴ť����
   buttons.add(new Button("search","�鿴","rec080504","search(document.all.tableform)"));
  buttons.add(new Button("edit","�� ��","rec080501","modify(document.all.tableform)"));
  buttons.add(new Button("del","ɾ ��","rec080503","del(document.all.tableform)"));
  buttons.add(new Button("close","�� ��","rec999999","closeWindow(\"Ree08Form\")"));
  //����context���Թ���ǩ���ȡ
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("editor",editors); 
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","����Ͷ�߱�");

%>
<lemis:title title="�����ճ�Ͷ��ά��"/>
<lemis:query action="/Rec0803Action.do?method=organComplaint"  editorMeta="editor" topic="��ѯ����"/>
<lemis:table topic="�����б�" action="/Rec0804Action.do" headerMeta="header" hiddenMeta="hidden"  mode="radio"/>
<lemis:buttons buttonMeta="buttons"/>

<lemis:errors/>
<lemis:bottom/>
</lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script>
//�޸Ļ���Ͷ����Ϣ
  function modify(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=initmodifyorgancom&"/>'+getAlldata(obj);
    obj.submit();
  }
  

//ɾ������Ͷ����Ϣ
 function del(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�е���")){
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=delorgancom&"/>'+getAlldata(obj);
    obj.submit();
    }else{
    return false;
    }
  }
  
  //�鿴����Ͷ����Ϣ
  function search(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0804Action.do?method=search&"/>'+getAlldata(obj);
    obj.submit();
  }
</script>


