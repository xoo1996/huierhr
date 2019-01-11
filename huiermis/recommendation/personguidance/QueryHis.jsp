<!-- recommendation/personguidance/QueryHis.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<script language="javascript" >
function checkKey()
{
  if(13 == window.event.keyCode){
   document.forms[0].sumbit();
  }
}
document.onkeydown=checkKey;
</script>

<html>
<lemis:base/>
<lemis:body>
<%
 
	String menuId = (String)request.getSession().getAttribute("menuId");
  //�����ѯ����

  List editors = new ArrayList();
  editors.add(new Editor("card","aac002","������ݺ���"));
  editors.add(new Editor("text","aac003","����"));




  pageContext.setAttribute("editor",editors);

  //�����ѯ��ʾ�ֶ�����
   Map hidden = new LinkedHashMap();

 hidden.put("aac001","���˱��");
 hidden.put("acc230","ְҵָ�����");
 hidden.put("aac002","������ݺ���");
 hidden.put("aac003","����");
 hidden.put("aac004","�Ա�");

  List header = new ArrayList();
 header.add(new Formatter("cc23.acc230","ְҵָ�����"));
 header.add(new Formatter("ac01.aac002","������ݺ���"));
 header.add(new Formatter("ac01.aac003","����"));
 header.add(new Formatter("ac01.aac004","�Ա�")); 
 header.add(new Formatter("cc23.acc231","ָ������",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
 header.add(new Formatter("cc23.acc233","ָ����Ա"));
 header.add(new Formatter("cc23.aae011","������"));
 
  pageContext.setAttribute("header",header);
  pageContext.setAttribute("hidden",hidden);
  List buttons = new ArrayList();
	  buttons.add(new Button("Btn_view","�鿴��ϸ","rec050104","view(document.all.tableform)"));
	  buttons.add(new Button("Btn_add","ָ���Ǽ�","rec050105","add(document.all.tableform)"));
	  buttons.add(new Button("Btn_his","ָ���޸�","rec050106","edit(document.all.tableform)"));
	  buttons.add(new Button("Btn_back","����","rec999997","go2Page(\"recommendation\",\"1\")"));
  
  buttons.add(new Button("Btn_close","�ر�","rec999999","closeWindow(\"Rec02Form\")"));
  pageContext.setAttribute("buttons",buttons);
  
  session.setAttribute("tableheader","������ְָ����Ϣ��");
%>

  <lemis:title title="������ְָ����Ϣ��"/>

  <lemis:query action="/Rec05Action.do?method=queryhis" editorMeta="editor" topic="��ѯ����"/>

  <lemis:table topic="������ְָ����Ϣ��" action="/Rec05Action.do" headerMeta="header" hiddenMeta="hidden" 
     mode="radio"/>
   <lemis:buttons buttonMeta="buttons"/>

  <lemis:errors/>
<lemis:bottom/></lemis:body><lemis:clean/>
</html>

<script language="javascript" >
 
  function view(obj)
  {
  var t = editObj("chk");
    if (!t)
    {return t;
      }
      obj.action='<html:rewrite page="/Rec05Action.do?method=viewguidance&"/>'+getAlldata(obj);
      obj.submit();
    }
	
	function add(obj){
		var t = editObj("chk");
    	if(!t){
     	 return t;
    	}
   		obj.action= '/lemis/recommendation/Rec05Action.do?method=initadd&'+getAlldata(obj);
   		obj.submit();
	}

  
   function edit(obj){
    var t = editObj("chk");
    if(!t){
      return t;
    }
    var alldata = getAlldata(obj);    
   obj.action= '<html:rewrite page="/Rec05Action.do?method=initedit&"/>'+alldata;
   obj.submit();
  }
</script>




