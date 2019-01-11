<!-- basicinfo/personReg.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%//��ȡminuId
	String menuId = request.getParameter("menuId");
	String stringData = "";
    PersonForm fm = (PersonForm) session
            .getAttribute("QueryPersonForm");
	
    if (menuId == null || "".equals(menuId))
    {
        if (fm != null)
        {
            menuId = fm.getMenuId();
        }
    }
	//��ȡ��һ����url, url2Ϊ������SESSION�еĵ�ַ
    //��ǰ��ַ����url2����,url2�����ᱻ�ŵ�SESSION��,û�оͰ�SESSION���
	String url2=request.getParameter("url2");
	if(url2==null){
		session.removeAttribute("url2");
	}else{
		session.setAttribute("url2",url2);
	}
	//System.out.println("##sesurl22##"+session.getAttribute("url2"));
	String url1=request.getParameter("url1");
	if(url1==null){
		session.removeAttribute("url1");
	}else{
		session.setAttribute("url1",url1);
	}
	//System.out.println("##sesurl11##"+session.getAttribute("url1"));
	
 	List buttons=new ArrayList();
    QueryInfo qi = (QueryInfo) pageContext
            .findAttribute(GlobalNames.QUERY_INFO);
    buttons.add(new Button("printperson", "�鿴������ϸ", "bas020306", "detail(document.all.tableform)"));
    buttons.add(new Button("add","�� ��","bas020105","addData(document.forms[0])"));
    if (null != qi)
    {
        stringData = qi.getStringData();	
        buttons.add(new Button("mod","�� ��","bas020104","editData(document.all.tableform)"));	
 		buttons.add(new Button("family","����ϵ","bas020101","family(document.all.tableform)"));
 		buttons.add(new Button("resume","���˼���","bas020102","resume(document.all.tableform)"));
 		buttons.add(new Button("skill","רҵ����","bas020103","skill(document.all.tableform)"));		      
    }
    
			
 	
 	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"QueryPersonForm\")"));
    
    List header = new ArrayList();
    header.add(new Formatter("ac01.aac002", "������ݺ���"));
    header.add(new Formatter("ac01.aac003", "����"));
    header.add(new Formatter("ac01.aac004", "�Ա�"));
 	header.add(new Formatter("ac01.aac006","��������",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("ac01.aac005", "����"));
    header.add(new Formatter("ac01.aac011", "�Ļ��̶�"));

	Map qh = new LinkedHashMap();
    qh.put("menuId", null);
    qh.put("url", null);
    qh.put("url2", null);
    pageContext.setAttribute("queryHiddens", qh);
    String urlString = "";
    String action = null;//��ʧҵ������һ������Ӧ��Action
    if ("reg".equals(menuId))
    {//�Ǽ�
        action = "/employs/queryPerson.do?method=enterPerson&menuId=reg";
    }
    List editors = new ArrayList();
    editors.add(new Editor("card", "aac002", "������ݺ���"));
    editors.add(new Editor("text", "aac003", "����"));
    Map hidden = new LinkedHashMap();
    hidden.put("aac001", "���˱��");
    hidden.put("aac002", "������ݺ���");
    hidden.put("aac003", "����");
    hidden.put("aac004", "�Ա�");
    hidden.put("aac005", "����");
    hidden.put("aac011", "�Ļ��̶�");
    hidden.put("aac021", "ʧҵ֤����");
    hidden.put("aac026", "��ͥסַ");
 
    pageContext.setAttribute("hidden", hidden);
    pageContext.setAttribute("editor", editors);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("button", buttons);
    
    session.setAttribute("tableheader","��Ա��Ϣ��");//��ͷ
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="���˵Ǽ�" />
		<lemis:query action="/queryPerson.do?method=findPerson" editorMeta="editor" hiddenMeta="queryHiddens" topic="������Ϣ��ѯ" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="queryPerson.do" headerMeta="header" topic="������Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="QueryPersonForm" />
</html>
<script language="javascript">
	// ����
	function addData(obj){
		obj.action = '<html:rewrite page="/queryPerson.do?method=findPerson&fg=add"/>'
		+"&stringData=" +document.all("stringData").value;
		obj.submit();
	}
	//�޸�
	function editData(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		obj.action = "/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=reg&buttonId=bas020121"+
		"&stringData=" +document.all("stringData").value +
		'&' +getAlldata(obj) +
		"&url1=&url2="+(loc[0]).replace(/&/g,";amp;");
        obj.submit();
	}
	// רҵ����
	function skill(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/queryskillAction.do?method=findskill&"/>'+para+
		"stringData=" +document.all("stringData").value + 
		"&url1=" + (location.href).replace(/&/g,";amp;");
		obj.submit();
	}	
	// ���˼���
	function resume(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/queryresumeAction.do?method=findresume&"/>'+para+
		"stringData=" +document.all("stringData").value+
		"&mod=basicinfo";
		obj.submit();
	}
	//�����ӵ�����ϵ��2005-5-25
	function family(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/querysocietyAction.do?method=findsociety&"/>'+para+
		"stringData=" +document.all("stringData").value + 
		"&url1=" + (location.href).replace(/&/g,";amp;");
		obj.submit();
	}
	  function detail(obj){
			var t = editObj("chk");
			if(!t){
			return t;
			}
			obj.action = '<html:rewrite page="/personOperAction.do?method=printPerson&"/>'+getAlldata(obj);			
			obj.submit();
  }
	
</script>

