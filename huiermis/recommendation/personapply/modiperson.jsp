<!--/lemis/recommendation/personapply/modiperson.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!--��������ҳ��-->
<lemis:base />
<script src="/lemis/js/lemisTree.js"></script>
<%
			String date = DateUtil.getSystemCurrentTime().toString();
           
			String menuId=request.getSession().getAttribute("menuId").toString();
            List buttons = new ArrayList();
            buttons.add(new Button("Btn_save","�� ��", "rec020105", "saveData(document.forms[0])"));
            buttons.add(new Button("Btn_back","�� ��", "rec999997", "go2Page(\"recommendation\",\"1\")"));
            buttons.add(new Button("Btn_close","�� ��", "rec999999", "closeWindow(\"Rec0201Form\")"));
            pageContext.setAttribute("button", buttons);

            %>
<html>
	<lemis:body>
		<lemis:errors />
		<lemis:title title="���˻�����Ϣ����" />
		<html:form action="/Rec0201Action.do" method="POST">
			<lemis:tabletitle title="������ְ����" />
			<table class="tableInput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="aca112" required="true" label="��һ����" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" onkeypress="setWorkTypeTreequery(this,document.all.aca112,document.all.aca111)"disable="false" />
					<html:hidden property="aca111" />
					
					<lemis:texteditor property="aca112a" label="�ڶ�����" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112a,document.all.aca111a)" onkeypress="setWorkTypeTreequery(this,document.all.aca112a,document.all.aca111a)" disable="false" />
						<html:hidden property="aca111a" />
					
						<lemis:texteditor property="aca112b" label="��������" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112b,document.all.aca111b)" onkeypress="setWorkTypeTreequery(this,document.all.aca112b,document.all.aca111b)" disable="false" />
						<html:hidden property="aca111b" />
				</tr>
					<tr>
					<lemis:codelisteditor type="aab019" label="��λ����" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="aac048" label="�ù���ʽ" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="aab020" label="��������" isSelect="true" redisplay="true" />
					
				</tr>
				<tr>
				    <lemis:formateditor mask="nnnnnn"  property="acc034" label="���ʴ�����Ԫ��" disable="false" required="false"/>
					<lemis:texteditor property="acc213" label="��ְ����Ҫ��" required="false" disable="false" maxlength="20" />
					
				</tr>
			</table>


			<lemis:tabletitle title="���˻�����Ϣ" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:hidden property="aac001" />
					<lemis:formateditor mask="card" property="aac002" label="�������ݺ���" required="true" disable="false" />
					<lemis:texteditor property="aac003" label="����" required="true" disable="false" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="true" label="�Ա�" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="true" required="true" key="01" label="����" redisplay="true" />
					<lemis:formateditor mask="date" property="aac006" required="false" disable="false" label="��������" required="true" />
					<lemis:codelisteditor type="aac017" label="����״��" isSelect="true" redisplay="true" required="true" />

				</tr>
				<tr>
					<html:hidden property="bac298" value="" />
					<lemis:codelisteditor type="aac009" label="��������" isSelect="true" redisplay="false" required="true" />
					<lemis:codelisteditor type="aac011" label="�Ļ��̶�" redisplay="true" required="true" />

					<lemis:codelisteditor type="bac299" label="��ְ��Ա���" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aac010" required="false" label="�������ڵ�" disable="false" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)" />
					<html:hidden property="aab301" />

					<lemis:formateditor mask="date" property="aae043" required="false" label="�Ǽ�ʱ��" disable="false" value="<%=date%>" required="true" />
					<lemis:texteditor property="aae005" label=" ��ϵ�绰" disable="false" maxlength="20" required="true" />
					<input name="aae005" type="hidden" />
				</tr>
				<tr>
					<lemis:texteditor property="aac025" label="������" required="false" disable="false" maxlength="100" />
					<lemis:codelisteditor type="aac024" isSelect="true" label="������ò" redisplay="true" />
					<lemis:formateditor property="aac036" label="����" disable="false" mask="n.n" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="aac007" required="false" label="�μӹ�������" disable="false" />
					<lemis:formateditor property="aac034" label='����(CM)' disable="false" mask="nnn.n" required="false" />
					<lemis:formateditor property="aac035" label='����(KG)' disable="false" mask="nnn.nn" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="��ַ" disable="false" maxlength="50" colspan="5" />
					
				</tr>
				<tr>
					<lemis:codelisteditor type="aac033" isSelect="true" label="����״��" redisplay="true" />
					<lemis:texteditor property="aae015" label="���˵�������" disable="false" maxlength="30" /> 
					<lemis:codelisteditor type="aac032" isSelect="true" redisplay="true" label="Ѫ��" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac015" label="רҵ�����ȼ�" redisplay="true" required="false" />
					<lemis:texteditor property="acc02i" label="����ְҵ�ʸ�֤�����" disable="false" maxlength="20" />
					<lemis:codelisteditor type="aac014" label="רҵ����ְ��" redisplay="true" />
				</tr>
			
				<tr>
				  <lemis:formateditor property="aae007" label="��������" required="false" mask="######" disable="false" />
				  <lemis:texteditor property="aae013" label="��ע" disable="false" maxlength="100" colspan="3"/>
				</tr>

				<tr>
					<td>
						������
					</td>
					<td>
						<lemis:operator />
					</td>
					<td>
						�������
					</td>
					<td>
						<lemis:operorg />
					</td>
					<td>
						��������
					</td>
					<td>
						<lemis:operdate />
					</td>
				</tr>
			</table>
		
		</html:form>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">


	function saveData(obj){
	
	if(!checkValue(obj)){
	return false;
	}
	obj.action ="/" + lemis.WEB_APP_NAME + "/recommendation/Rec0201Action.do?method=savePerson&aac002="+document.all("aac002").value+"&";
	obj.submit();
	}
	function resetForm(obj) {
	
	}
</script>
