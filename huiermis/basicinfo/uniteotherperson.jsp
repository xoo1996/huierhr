<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,org.radf.apps.basicinfo.form.UnitePersonForm,org.radf.plat.commons.QueryInfo,com.lbs.cp.taglib.Formatter,org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants,org.radf.login.dto.LoginDTO,org.radf.plat.taglib.Button" %>
<!-- /lemis/basicinfo/uniteotherperson.jsp -->
<script type="text/javascript" src="/lemis/js/proxyRequest.js"></script>
<%//��ȡminuId

//���Ի������ˡ��������������ʱ��
LoginDTO lf = (LoginDTO) request.getSession().getAttribute("LoginDTO");
String aae011 = lf.getBsc012();  //	  �û�����  
System.out.println("-----------------"+aae011);
         
			UnitePersonForm fm = (UnitePersonForm) request.getSession().getAttribute("UnitePersonForm");
            
            
           
            List header = new ArrayList();
            header.add(new Formatter("ac01.aac002", "������ݺ���"));
            header.add(new Formatter("ac01.aac003", "����"));
            header.add(new Formatter("ac01.aac004", "�Ա�"));
			header.add(new Formatter("ac01.aac006","��������",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
            header.add(new Formatter("ac01.aac005", "����"));
            header.add(new Formatter("ac01.aac011", "�Ļ��̶�"));
			pageContext.setAttribute("header", header);
            
           
           
            List editors = new ArrayList();
            editors.add(new Editor("card", "a002ac", "������ݺ���"));
            editors.add(new Editor("text", "a003ac", "����"));
			editors.add(new Editor("text","acc025","�Ͷ��ֲ��"));
			pageContext.setAttribute("editor", editors);
			
            Map hidden = new LinkedHashMap();
            hidden.put("tac001", "���˱��");
			//hidden.put("aac002", "���֤��");
			//hidden.put("aac003", "����");
            pageContext.setAttribute("hidden", hidden);
            
            
			List buttons=new ArrayList();
			buttons.add(new Button("unite","��  ��","bas020605","unite(document.all.tableform)"));
			buttons.add(new Button("toback","��  ��","bas020604","go2Page(\"basicinfo\",\"1\")"));
			//buttons.add(new Button("detail","��  ��","bas020606","detail(document.all.tableform)"));
			buttons.add(new Button("toback","��  ��","bas020607","closeWindow(\"UnitePersonForm\")"));
			
            pageContext.setAttribute("button", buttons);
            
            session.setAttribute("tableheader","��Ա��Ϣ��");//��ͷ
%>
<html>
	<lemis:base />
	<lemis:body>
<lemis:title title="��Ա�ϲ�" />
<lemis:tabletitle title="Ŀ��ϲ���Ա������Ϣ"/>
<table class="TableInput">
<html:form action="/queryUnitePerson.do?method=queryUniteOtherPerson">
<lemis:editorlayout/>
  <tr>
  <html:hidden property="aac001" value="<%=fm.getNewaac001()%>"/>
    <td width="16%">���֤��</td>
    <td width="17%"><lemis:text property="aac002" disable="true"></lemis:text></td>
    <td width="16%">�� ��</td>
    <td width="50%"><lemis:text property="aac003" disable="true"></lemis:text></td>
  </tr>
</html:form>
</table>

		
		<lemis:query action="/queryUnitePerson.do?method=queryUniteOtherPerson" editorMeta="editor" topic="���ϲ���Ա��Ϣ��ѯ" />
		<lemis:table action="/queryUnitePerson.do" headerMeta="header" topic="���ϲ���Ա��Ϣ" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
	// �ϲ�
	function unite(obj){
	var t = editObj("chk");
		if(!t){
			return t;
		}
		var newaac001 = document.forms[0].aac001.value;
		var oldaac001 = getSelectData(obj,'tac001');
		//alert(newaac001+"__________"+oldaac001);
		if(newaac001==oldaac001){
		  alert('�Լ����ܺϲ��Լ�,��˶�!');
		  return false;
		}
    if(window.confirm('��ȷ��Ҫ�ϲ���������Ա��')){
    
    var v = false;
    
	var para = getAlldata(obj);
	var url = "/basicinfo/queryUnitePerson.do?method=validateUnitePerson&newaac001=<%=fm.getNewaac001()%>&oldaac001="+getSelectData(obj,'tac001')+"&aae011=<%=aae011%>";
	var value = proxyRequest(url);
	var res = value.split("$");
	for(var i=0;i<res.length;i++){
	  var a = res[i];
	  eval(a);
	  if(!v){
	   return;
	  }
	}
    
	obj.action = '<html:rewrite page="/queryUnitePerson.do?method=saveUnitePerson"/>'
	+"&newaac001=<%=fm.getNewaac001()%>&oldaac001="+getSelectData(obj,'tac001')+"&aae011=<%=aae011%>";
	obj.submit();
	return true;
	
	} else {
	 return false;
	}
	}

   function detail(){
	var t = editObj("chk");
	if(!t){
	return t;
	}
	window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=alter&"+ getAlldata(document.all.tableform) ;
	}

</script>

