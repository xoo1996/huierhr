<!-- basicinfo/resume.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.taglib.Button" %>
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
    
	String mod=request.getParameter("mod");	
	if(mod!=null){
		session.setAttribute("mod",mod);
	}
	mod=(String)session.getAttribute("mod");

    List header=new ArrayList();
    header.add(new Formatter("aae030","��ʼ����",TagConstants.DF_LEFT,
					TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("aae031","��ֹʱ��",TagConstants.DF_LEFT,
					TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("aac045","��ҵѧУ������λ"));
    header.add(new Formatter("acc0b4","��ְ��������"));
    header.add(new Formatter("aae013","��ע"));
    
	List buttons=new ArrayList();
	buttons.add(new Button("add","�� ��","bas020111","addData(document.all.tableform)"));
	buttons.add(new Button("mod","�� ��","bas020112","editData(document.all.tableform)"));
	buttons.add(new Button("del","ɾ ��","bas020113","delData(document.all.tableform)"));
	if("recommendation".equals(mod))
	{
		buttons.add(new Button("goBack","�� ��","bas999997","go2Page(\"recommendation\",\"1\")"));
	}else
	{
		buttons.add(new Button("goBack","�� ��","bas999997","go2Page(\"basicinfo\",\"1\")"));
	}
	
	buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"queryPersonForm,ResumesForm\")"));

    Map hidden=new LinkedHashMap();
    hidden.put("acc0b0","�������");
    hidden.put("aae030","��ʼ����");
    hidden.put("aae031","��ֹʱ��");
    hidden.put("aac045","��ҵѧУ������λ");
    hidden.put("aae013","��ע");
    hidden.put("acc0b4","��ְ��������");
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
			<html:form action="queryresumeAction.do" method="POST" >
				<lemis:editorlayout/>
				<tr>
					<html:hidden property="aac001"/>
					<lemis:texteditor property="aac002" label="������ݺ���"/>
					<lemis:texteditor property="aac003" label="����"/>
					<lemis:codelisteditor type="aac004" label="�Ա�" isSelect="false" redisplay="true"/>
				</tr>
			</html:form>
		</table>
		<html:hidden property="stringData" value="<%=stringData%>"/>
		<lemis:table action="queryresumeAction.do" headerMeta="header" topic="���˼�����Ϣ" hiddenMeta="hidden" mode="radio" appendBlank="false"/>
		<lemis:buttons buttonMeta="button"/>		
		<lemis:errors/>
		<lemis:bottom/>
	</lemis:body>
</html>
<script language="javascript">
	function getdata(){
		var str ="aac001="+document.all("aac001").value+"&aac002="
		+document.all("aac002").value+"&aac003="+document.all("aac003").value+"&aac004="+document.all("aac004").value;
		return str;
	}
	function editData(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/resumesAction.do?method=edit&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata()+
		"&option=edit" ;
	}
	function addData(obj){
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/"+lemis.WEB_APP_NAME+"/basicinfo/resumesAction.do?method=add&stringData=" +
		document.all("stringData").value + "&"+ getdata()+
		"&option=add" ;
	}
	function delData(obj){
		var t = delObj("chk");
		if(!t){
			return t;
		}
		if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�е�������")){
		var loc = location.href.split('&url1=');//���ȥ��URL����,�����ۼ�ʹ�õ�ַ��������ť��Ч
		window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/resumesAction.do?method=clear&stringData=" +
		document.all("stringData").value + "&" + getAlldata(document.all.tableform) + "&"+getdata();
		}else{
      		return false;
   		}
	}
</script>
