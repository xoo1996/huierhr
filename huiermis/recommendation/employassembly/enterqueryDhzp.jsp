<!--/lemis/recommendation/employassembly/enterqueryDhzp.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<html>
<lemis:base/>
<lemis:body>
<%
String menuId = (String)request.getSession().getAttribute("menuId");
  //�����ѯ����

  List editors = new ArrayList();
    editors.add(new Editor("text","aab007","Ӫҵִ�պ�"));
	editors.add(new Editor("text","aab003","��֯��������"));
	//editors.add(new Editor("text","aab002","��ᱣ�յǼ�֤����"));
	editors.add(new Editor("text","aab004","��λ����"));
	//editors.add(new Editor("text","aab043","��λƴ����"));
  	//editors.add(new Editor("text","acb200","�й����"));
	editors.add(new Editor("text","acb231","�����Ƹ����"));

  pageContext.setAttribute("editor",editors);

  //�����ѯ��ʾ�ֶ�����
   Map hidden = new LinkedHashMap();

  hidden.put("acb200","��Ƹ���");
  hidden.put("acb210","��λ���");
  hidden.put("aab001","��λ���");

  List header = new ArrayList();
 header.add(new Formatter("ab01.aab004","��ҵ����"));
 header.add(new Formatter("cb21.aca112","��λ"));
 header.add(new Formatter("cb21.acb21d","����"));
 header.add(new Formatter("cb21.acb221","�������"));
 header.add(new Formatter("cb21.acb222","�������"));
 header.add(new Formatter("cb21.aac011","ѧ��Ҫ��"));
 header.add(new Formatter("cb21.acb21h","����(Ԫ/��)"));
 header.add(new Formatter("cb20.aae004","��ϵ��"));
 header.add(new Formatter("cb20.aae005","�绰/����"));
 header.add(new Formatter("ab01.aae006","��ַ"));
 header.add(new Formatter("cb21.aae013","��ע"));
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);

    //���尴ť
  List buttons = new ArrayList();
  	buttons.add(new Button("Btn_view", "�� ��", "rec070201", "view(document.all.tableform)"));
    buttons.add(new Button("Btn_close", "�� ��", "rec999999", "closeWindow(\"queryEmployInviteForm\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","��λ��Ƹ��Ϣ��"); 
%>

  <lemis:title title="��ᵥλ��Ƹ��Ϣ"/>

  <lemis:query action="/Rec0702Action.do?method=viewEmployDhzp"  editorMeta="editor" topic="��ѯ����"/>

  <lemis:table topic="�����Ƹ��Ϣ" action="/Rec0702Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //��λ��Ƹ�鿴
  function view(obj)
  {
    var t = editObj("chk");
    if (!t)
    {return t;
      }
      obj.action='<html:rewrite page="/Rec0702Action.do?method=viewEmployDhzp&"/>'+getAlldata(obj);
      obj.action=obj.action+"viewE="+location.href.replace(/\&/g,";viewE;");
      obj.submit();
    }
</script>




