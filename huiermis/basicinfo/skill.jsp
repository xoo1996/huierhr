<!-- basicinfo/skill.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String stringData = "";
	QueryInfo qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
	if(null != qi){
		stringData = qi.getStringData();
	}

	//��ȡ��һ����url, url2Ϊ������SESSION�еĵ�ַ
    //��ǰ��ַ����url2����,url2�����ᱻ�ŵ�SESSION��,û�оͰ�SESSION���
	String backURL=request.getParameter("url1");
	String url2=request.getParameter("url2");
	if (backURL==null&&session.getAttribute("url1")!=null){
		backURL=(String)session.getAttribute("url1");
	}else{
		session.setAttribute("url1",backURL);
	}
	if(url2==null){
		session.removeAttribute("url2");
	}else if(session.getAttribute("url2")==null){
		session.setAttribute("url2",url2);
	}
	System.out.println("##sesurl22##"+session.getAttribute("url2"));

	List header=new ArrayList();
    header.add(new Formatter("acc0d1","���ܻ��س�"));
    header.add(new Formatter("acc0d5","֤����"));
    header.add(new Formatter("acc041","��֤����",TagConstants.DF_LEFT,
					TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("aca112","��ҵ����"));
	List buttons=new ArrayList();
	buttons.add(new Button("add","�� ��","bas020116","addData(document.all.tableform)"));
	buttons.add(new Button("mod","�� ��","bas020117","editData(document.all.tableform)"));
	buttons.add(new Button("del","ɾ ��","bas020118","delData(document.all.tableform)"));
	buttons.add(new Button("goBack","�� ��","bas999997","go2Page(\"basicinfo\",\"1\")"));
	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"queryPersonForm,SkillForm\")"));
    
    Map hidden=new LinkedHashMap();
    hidden.put("acc0d0","���ܱ��");
    hidden.put("acc0d1","���ܻ��س�");
    hidden.put("acc0d2","֤����");
    hidden.put("acc041","��֤����");
    hidden.put("aca111","��ҵ����");
    hidden.put("aae013","��ע");
    pageContext.setAttribute("hidden",hidden);
    pageContext.setAttribute("header",header);
    pageContext.setAttribute("button",buttons);
	session.setAttribute("tableheader", "���˼�����Ϣ��");//��ͷ
%>
<html>
	<lemis:base/>
	<lemis:body>
		<lemis:title title="���˼���"/>
		<lemis:tabletitle title="���˻�����Ϣ"/>
		<table class="tableinput">
			<html:form action="/queryskillAction.do" method="POST" >
				<lemis:editorlayout/>
				<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="������ݺ���"/>
					<lemis:texteditor property="aac003" label="����"/>
					<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="false" redisplay="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" label="����" isSelect="false" redisplay="true"/>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" isSelect="false" redisplay="true"/>
					
				</tr>
				<html:hidden property="stringData" value="<%=stringData%>"/>
			</html:form>
		</table>
		<lemis:table action="/skillAction.do" headerMeta="header" topic="���˼����б�" hiddenMeta="hidden" mode="radio" appendBlank="false"/>
		<lemis:buttons buttonMeta="button"/>
		<lemis:errors/>
		<lemis:bottom/>
		
	</lemis:body>
</html>
<script language="javascript">
	function getdata(){
		var str ="aac001="+document.all("aac001").value+"&aac002="
		+document.all("aac002").value+"&aac003="+document.all("aac003").value+"&aac004="+document.all("aac004").value
		+"&aac005="+document.all("aac005").value+"&aac011="+document.all("aac011").value
		+"&";
		return str;
	}
	//����	
	function addData(obj){
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/"+lemis.WEB_APP_NAME+"/basicinfo/skillAction.do?method=add&stringData=" +
		document.all("stringData").value + "&"+ getdata() +
		"&url1=<%=backURL%>" +
		"&url2=" + (loc[0]).replace(/&/g,";amp;");
	}
	//�޸�	
	function editData(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/skillAction.do?method=edit&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata()+
		"&url1=<%=backURL%>" +
		"&url2=" + (loc[0]).replace(/&/g,";amp;");
	}
	
	function delData(obj){
		var t = delObj("chk");
		if(!t){
			return t;
		}
		if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�е���")){
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/skillAction.do?method=clear&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata()+
		"&url1=<%=backURL%>" +
		"&url2=" + (loc[0]).replace(/&/g,";amp;");
		}else{
      		return false;
   		}
	}
</script>
