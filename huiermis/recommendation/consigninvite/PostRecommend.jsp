<!--recommendation\consigninvite\PostRecommend.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<lemis:base/>
<lemis:body>
<lemis:title title="ѡ���й���Ϣ"/>
<%
String menuId = (String)request.getSession().getAttribute("menuId");

//�����ѯ���������� 
  Editor editor;
  List editors=new ArrayList();//����༭������
            editors.add(new Editor("text","aab007","Ӫҵִ�պ�"));
			editors.add(new Editor("text","aab003","��֯��������"));
			editors.add(new Editor("text","aab002","��ᱣ�յǼ�֤����"));
			editors.add(new Editor("text","aab004","��λ����"));
			editors.add(new Editor("text","acb206","��������"));
			editors.add(new Editor("text","aac048","�ù���ʽ"));  
            editors.add(new Editor("text","aca111","רҵ����"));
			editors.add(new Editor("text","acb210","��λ���"));
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("acb210","��λ���"));
  header.add(new Formatter("aab004","��λ����"));  
  header.add(new Formatter("acb216","����˵��"));
  header.add(new Formatter("acb21d","��������"));
  header.add(new Formatter("acb21a","�ɹ�����"));
  header.add(new Formatter("acb218","���Ƽ�����"));
  header.add(new Formatter("acb211","���Ƽ�����"));
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();
  hidden.put("acb200","��Ƹ���");
  hidden.put("acb210","��λ���");
  hidden.put("aab001","��λ���");
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons=new ArrayList();//���尴ť���� 
	buttons.add(new Button("Btn_next","��һ��","rec010401","recommend(document.all.tableform)"));
    buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"searchPostFRForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","��λ��Ƹ��λ��Ϣ"); 
%>

<lemis:query action="/Rec0103Action.do?method=SearchPostFR" editorMeta="editor" topic="��ѯ����"/>
<lemis:table topic="��ѯ���" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/><lemis:clean names="Rec0402Form" />
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function recommend(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=PostRecommendFR&"/>'+getAlldata(obj);
    obj.action=obj.action+"SearchPostFRURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>

