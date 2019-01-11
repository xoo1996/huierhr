<!--/emis2005hz/lemis/basicinfo/modiperson.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--��������ģ��-->
<%
    String menuid = "reg";
    String title = "";
    menuid = request.getParameter("menuId");
	String buttonId = request.getParameter("buttonId");
    String stringData = request.getParameter("stringData");
    if ("alter".equals(menuid))
    {
        title = "���˻�����Ϣ���";
    }
    else
    {
        title = "���˻�����Ϣ����";
    }
    if ((null == stringData) || ("".equals(stringData)))
    {
        QueryInfo qi = (QueryInfo) pageContext
                .findAttribute(GlobalNames.QUERY_INFO);
        if (null != qi)
        {
            stringData = qi.getStringData();
        }
    }
    //��ȡ��һ����url
    PersonForm fm = (PersonForm) request.getAttribute("OperPersonForm");
    String url = request.getParameter("url2");
    if (url == null || "".equals(url))
    {
        if (fm != null)
        {
            url = fm.getUrl2();
        }
    }
    if (url == null)
    {
        url = "";
    }
    String rurl = null;
    if ("".equals(url))
    {
        url = "queryPerson.do?method=findPerson;amp;menuId=" + menuid;
        rurl = "queryPerson.do?method=enterPerson;amp;menuId=" + menuid;
    }
    List buttons=new ArrayList();
    if ("alter".equals(menuid))
    {
    	buttons.add(new Button("alterSave","�� ��",buttonId,"saveData(document.forms[0])"));
    }
    else
    {
    	buttons.add(new Button("save","�� ��","bas020122","saveData(document.forms[0])"));
    }
    
    buttons.add(new Button("reset","�� ��","bas999998","document.forms[0].reset();"));
    buttons.add(new Button("goBack","�� ��","bas999997","go2Page(\"basicinfo\",\"1\")"));
    buttons.add(new Button("close","�� ��","bas999999","closeWindow(\"OperPersonForm\")"));
   
   
    String s = rurl;
    if (s == null)
    {
        s = url;
    }
    
    pageContext.setAttribute("button", buttons);

	System.out.println("##sesurl11##"+request.getParameter("url1"));
	System.out.println("##sesurl22222##"+request.getParameter("url2"));
%>
<html>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="<%=title%>" />
		<lemis:tabletitle title="���˻�����Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/operatePerson.do" method="POST">
				<tr>
					<html:hidden property="aac001" />
					<lemis:formateditor mask="card" property="aac002" label="������ݺ���" required="true" disable="false" />
					<lemis:texteditor property="aac003" label="����" required="true" disable="false" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="true" label="�Ա�" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="true" required="true" label="����" redisplay="true"/>
					<lemis:formateditor mask="date" property="aac006" required="true" disable="ture" label="��������" format="true"/>
					<lemis:codelisteditor type="aac009" label="��������" isSelect="true" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="bac298" label="��Ա���" redisplay="true" required="true" />
					<lemis:texteditor property="ssjqnm" required="true" label="��ԱϽ��" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					
					<lemis:texteditor property="acc025"  label="�Ͷ��ֲ��" disable="false"  maxlength="18"/>
					
					<html:hidden property="cce001" />
				</tr>
				<tr>
					<lemis:texteditor property="aac010" required="false" label="�������ڵ���" disable="false" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)" />
					<html:hidden property="aab301" />
					<lemis:codelisteditor type="aac024" isSelect="true" label="������ò" redisplay="true" />
					<lemis:formateditor property="aac034" label='���(CM)' disable="false" mask="nnn.n" required="false" />
				</tr>
				<tr>
					<lemis:formateditor property="aac035" label='����(KG)' disable="false" mask="nnn.nn" required="false" />
					<lemis:formateditor property="aac036" label="����" disable="false" mask="n.n" required="false" />
					<lemis:codelisteditor type="aac032" isSelect="true" redisplay="true" label="Ѫ��" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac033" isSelect="true" label="����״��" redisplay="true" />
					<lemis:codelisteditor type="aac017" label="����״��" redisplay="true"/>
					<lemis:formateditor mask="date" property="aac007" required="false" label="�μӹ�������" disable="false" format="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" redisplay="true" required="false" />
					<lemis:formateditor property="aae007" label="��������" required="false" mask="######" disable="false" />
					<lemis:texteditor property="aae005" label="��ϵ�绰" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:texteditor property="aae015" label="���˵�������" disable="false" maxlength="50" />
					<lemis:codelisteditor type="aac015" label="רҵ�����ȼ�" redisplay="true" />
					<lemis:texteditor property="acc02i" label="����ְҵ�ʸ�֤�����" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac014" label="רҵ����ְ��" redisplay="true" />
					<lemis:texteditor property="aac021" label="ʧҵ֤����" disable="false" maxlength="14"/>
					<lemis:texteditor property="aac025" label="������" required="false" disable="false" maxlength="100" />
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="��ַ" disable="false" maxlength="50" />
					<lemis:texteditor property="aac022" label="����ƴ����" required="false" disable="false" maxlength="100" />
					<lemis:codelisteditor type="bac297" label="�Ƿ���֤" redisplay="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aae013" label="��ע" disable="false" maxlength="100" colspan="5" />
				</tr>
				<tr>
					<td>������</td>
					<td><lemis:operator/></td>
					<td>�������</td>
					<td ><lemis:operorg/></td>
					<td>��������</td>
					<td><lemis:operdate/></td>
				</tr>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function page_init(){ //����ǩ�����ܸı�
  	 	eapObjsMgr.onReady('OperPersonForm');//codeListFormΪ����¼��ı�ǩ������form
	}
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/basicinfo/personOperAction.do?method=savePerson&menuId=<%=menuid%>&url2=<%=url%>;amp;aac003=;amp;aac021=;amp;aac002="+document.all("aac002").value+"&";
		obj.submit();
	}
	function toBack(){
		window.location.href="<%=url.replaceAll(";amp;","&")%>";
	}
</script>

