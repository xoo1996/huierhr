<!--recommendation\consigninvite\PostRecommendResult.jsp-->
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
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
  //URL
  String url = "";
  if (request.getParameter("SearchPostFRURL")!=null){
     url = request.getParameter("SearchPostFRURL");
     request.getSession().setAttribute("SearchPostFRURL",url);
     }
     
  String menuId = (String)request.getSession().getAttribute("menuId");

  //�����ѯ����������
  Editor editor;
  List editors=new ArrayList();//����༭������
  editors.add(new Editor("card","aac002","������ݺ���"));
  editors.add(new Editor("text","aac003","����"));
  editors.add(new Editor("text","aca111","����"));
  editors.add(new Editor("text","acc034","���ʴ�����"));
  editors.add(new Editor("text","a034cc","��"));
  editors.add(new Editor("text","aac017","����״��"));
  editors.add(new Editor("nn","nnn001","�����"));
  editors.add(new Editor("nn","nnn002","��"));
  editors.add(new Editor("text","aac011","�Ļ��̶�"));
  editors.add(new Editor("text","aac009","��������"));
  editors.add(new Editor("text","aac048","�ù���ʽ"));
  editors.add(new Editor("text","aac014","רҵ����ְ��"));
  editors.add(new Editor("test","bac298","��Ա���"));
  editors.add(new Editor("text","aac004","�Ա�"));
  pageContext.setAttribute("editor",editors); //����context���Թ���ǩ���ȡ
//�����ѯ�����ʾ��
  List header=new ArrayList();
  header.add(new Formatter("aac002","������ݺ���"));
  header.add(new Formatter("aac003","����"));
  header.add(new Formatter("aac004","�Ա�"));
  header.add(new Formatter("aac006","��������",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
  header.add(new Formatter("aac011","�Ļ��̶�"));
  header.add(new Formatter("bac298","��Ա���"));
  header.add(new Formatter("aac009","��������"));
  header.add(new Formatter("aca112","����˵��"));
  header.add(new Formatter("acc034","���ʴ���"));
  pageContext.setAttribute("header",header);
//������������
  Map hidden=new LinkedHashMap();
  hidden.put("acc200","��ְ���");
  hidden.put("acc210","��ְ������");
  hidden.put("aac001","���˱��");
//����ͷ����context���Թ���ǩ���ȡ
  pageContext.setAttribute("hidden",hidden);
//���尴ť
  List buttons=new ArrayList();//���尴ť����
  List data = (List)request.getAttribute(GlobalNames.QUERY_DATA);
	if(data!=null&&data.size()>0){
		  buttons.add(new Button("Btn_pp","ƥ�����","rec010405","ppqk(document.all.tableform)"));
		  buttons.add(new Button("Btn_view","�� ϸ","rec010402","view(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","�� ��","rec010403","recommend(document.all.tableform)"));
		  buttons.add(new Button("Btn_recommend","��λѡ��","rec010404","dwxr(document.all.tableform)"));
		
	}
  buttons.add(new Button("Btn_prev","����","rec999997","go2Page(\"recommendation\",\"1\")"));

  buttons.add(new Button("Btn_close","�� ��","rec999999","closeWindow(\"postRecommendSPersonForm\")"));
  pageContext.setAttribute("buttons",buttons);
  session.setAttribute("tableheader","������ְҵ��Ϣ"); 
%>
<lemis:query action="/Rec0103Action.do?method=PostRecommendSPerson" editorMeta="editor" topic="��ϸƥ������"/>
<lemis:table topic="��ѯ���" action="/Rec0103Action.do" headerMeta="header" hiddenMeta="hidden" mode="checkbox" />
<lemis:buttons buttonMeta="buttons"/>
<lemis:errors/>
<lemis:bottom/></lemis:body>
</html>

<script language="javascript">
function view(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=viewEmptyPost&"/>'+getAlldata(obj);
    obj.action=obj.action+"PostRecommendViewURL="+location.href.replace(/\&/g,";amp;");
    obj.submit();
  }
function ppqk(obj) {
    var t = editObj("chk");
    if(!t){
      return t;
    }
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=viewppqk&"/>'+getAlldata(obj);
    obj.submit();
  }
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

  }
function dwxr(obj) {
	var truthBeTold = window.confirm("�Ƿ�ȷ��ѡ��");
	if (truthBeTold) {
	var t = delObj("chk");
    if(!t){
      return t;
    }
    //alert(getAlldata(obj));
    obj.action= '<html:rewrite page="/Rec0103Action.do?method=dwxr&"/>'+getAlldata(obj);
    location.href=obj.action;
    //obj.submit();
    }

  }

</script>
<script type="text/javascript" src="/lemis/js/lemisTree.js"></script>

