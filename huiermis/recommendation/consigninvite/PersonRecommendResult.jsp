<!--recommendation\consigninvite\PersonRecommendResult.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
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

//URL

//�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������
  editors.add(new Editor("text","aab004","��λ����"));
  editors.add(new Editor("text","aac048","�ù���ʽ"));
  editors.add(new Editor("text","aac011","�Ļ��̶�"));  
  editors.add(new Editor("test","aca111","רҵ����")); 
  editors.add(new Editor("text","aac009","��������"));
  editors.add(new Editor("text","aac014","רҵ����ְ��"));
  editors.add(new Editor("text","acb21h","���ʴ�����"));
  editors.add(new Editor("text","a21hcb","��"));
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
  List data = (List)request.getAttribute(GlobalNames.QUERY_DATA);
	if(data!=null&&data.size()>0){
		  buttons.add(new Button("Btn_pp","ƥ�����","rec010304","ppqk(document.all.tableform)"));
		  buttons.add(new Button("Btn_view","�� ϸ","rec010302","view(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","�� ��","rec010303","recommend(document.all.tableform)"));

	}
  buttons.add(new Button("Btn_prev","�� ��","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"personRecommendSPostForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","��λ��Ƹ��λ��Ϣ"); 
%>
<lemis:query action="/Rec0103Action.do?method=PersonRecommendSPost" editorMeta="editor" topic="��ϸƥ������"/>
<lemis:table topic="��ѯ���" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="radio" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>
<script language="javascript">
function view(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"PersonRecommendViewURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }




//�Ҹ��˼ӵĶԻ���
 function recommend(obj) {
var truthBeTold = window.confirm("�Ƿ�ȷ���Ƽ���");
if (truthBeTold) {
var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=Recommend&"/>'+getAlldata(obj);
    obj.action=obj.action+"RecommendURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
	//window.open ("consigninvite/print.jsp", "newwindow", "height=700, width=520, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
}
else  window.alert("������������������"); 
  }

  
</script>

