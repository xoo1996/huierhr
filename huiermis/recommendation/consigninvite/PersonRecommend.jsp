<!--recommendation\consigninvite\PersonRecommend.jsp-->
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
<lemis:title title="ѡ����ְ��Ϣ"/>
<% 

String menuId = (String)request.getSession().getAttribute("menuId");

//�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������
  editors.add(new Editor("card","aac002","������ݺ���"));
  editors.add(new Editor("text","aac003","����"));
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("aac002","������ݺ���"));
  header.add(new Formatter("aac003","����"));
  header.add(new Formatter("aac004","�Ա�"));
  header.add(new Formatter("aac006","��������" ,TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  
  header.add(new Formatter("aca112","��ְ����"));
  header.add(new Formatter("aac011","�Ļ��̶�"));
  header.add(new Formatter("aac009","��������"));
  header.add(new Formatter("aae036","�Ǽ�����",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aac014","רҵ����ְ��"));
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();
  hidden.put("acc200","��ְ���");
  hidden.put("aae043","�Ǽ�����");
  hidden.put("acc210","��ҵ���ֱ��");
  hidden.put("aac001","���˱��");
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons=new ArrayList();//���尴ť����
	buttons.add(new Button("Btn_next","��һ��","rec010301","match(document.all.tableform,\""+menuId+"\")"));
    buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"searchPersonFRForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","������ְҵ��Ϣ"); 
%>
<lemis:query action="/Rec0103Action.do?method=SearchPersonFR" editorMeta="editor" topic="��ְ�߲�ѯ"/>
<lemis:table topic="��ѯ���" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/><lemis:clean names="Rec0402Form" />
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
 function match(obj,menuId) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=PersonRecommendFR&"/>'+getAlldata(obj);
    obj.action=obj.action+'menuId='+menuId+"&SearchPersonFRURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }

</script>


