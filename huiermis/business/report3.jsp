<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<lemis:base />
<%		
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("aae011", "����Ա"));			
	header.add(new Formatter("temp03", "�Ƽ��˴�"));
	header.add(new Formatter("temp02", "�Ƽ����˴�"));
	header.add(new Formatter("temp04", "�ɹ��˴�"));
	header.add(new Formatter("temp05", "ʧ���˴�"));
	header.add(new Formatter("temp06", "�ɹ���"));

	Map<String, String> hidden = new LinkedHashMap<String, String>();

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("ͳ�Ʋ�ѯ","stat(document.forms[0])");
	buttons.put("�� ��","resetForm(document.forms[0])");

	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "��������ͳ�Ʊ�");//��ͷ
%>
<html>
<script language="javascript">
	//ͳ�Ʋ�ѯ
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=queryqyxs"/>';
		obj.submit();
	};
	//��ʾͼ��
	function showtx(obj) {
		if (document.all.iframediv.style.display == 'none') {
			document.all.iframediv.style.display = '';
			document.all.showtx.value = "�ر�ͼ��";
		} else {
			document.all.iframediv.style.display = 'none';
			document.all.showtx.value = "��ʾͼ��";
		}
	}
</script>
<lemis:body>
	<lemis:errors />
	<lemis:title title="��ѯ" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<td style="vertical-align: top"><lemis:tabletitle title="ͳ������" />
					<table class="TableInput">
						<html:form action="/BusinessAction.do" method="post">
							<lemis:editorlayout />
							<tr>
								<lemis:texteditor property="ivtyear" disable="false"
									required="true" label="���" />
								<lemis:texteditor property="ivtmonth" disable="false"
									required="false" label="�·�" />
							</tr>
						</html:form>
					</table>

					<lemis:buttons buttonMeta="button" /></td>
				</tr>
				<tr>
					<TD><lemis:table action="BusinessAction.do"
						headerMeta="header" hiddenMeta="hidden" topic="��������ͳ����Ϣ"
						mode="radio" pilot="true" orderResult="true" /></TD>
				</tr>
			</table>
			</TD>
			<TD style="vertical-align: top;" id="iframediv">
			<table border="0">
				<tr>
					<td><iframe
						src='<html:rewrite page="/ChartAction.do?method=show"/>'
						style="display: inline" align="center" id="iframe1" name="iframe1"
						frameborder="0" width="100%" framespacing="0"
						allowTransparency="true"></iframe></td>
				</tr>
			</table>
			</TD>
		</tr>
	</table>
	<lemis:bottom />
</lemis:body>
</html>

