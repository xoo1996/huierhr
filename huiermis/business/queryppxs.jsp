<!-- lemis/recommendation/query/querychart.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<lemis:base />
<%
	String menuId = (String) request.getParameter("menuId");
   
     Integer ivtmonth = (Integer) request.getAttribute("ivtmonth");
     Integer ivtyear = (Integer) request.getAttribute("ivtyear");

     String gctid = (String)request.getAttribute("gctid");
     
     request.getSession().setAttribute("ivtyear",ivtyear);
     //if(!"".equals(gctid))
     request.getSession().setAttribute("ivtgcltid",gctid);
     //if(!"".equals(ivtmonth))
     request.getSession().setAttribute("ivtmonth",ivtmonth);

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("temp01", "����������"));
	header.add(new Formatter("temp03", "�ؿ���"));
	header.add(new Formatter("temp02", "���۶�", TagConstants.DF_RIGHT, TagConstants.DT_MONEY));
	header.add(new Formatter("percent", "��ռ�ٷֱ�", TagConstants.DF_RIGHT));
	header.add(new Formatter("rank", "����ͳ��", TagConstants.DF_CENTER));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	//hidden.put("ivtgcltid", "�ͻ�����");
	//hidden.put("ivtyear", "���");
	//hidden.put("ivtmonth", "�·�");
	hidden.put("temp01", "����������");
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("ͳ�Ʋ�ѯ","stat(document.forms[0])");
	buttons.put("��ʾͼ��","showtx(document.forms[0])");
	buttons.put("�� ��","resetForm(document.forms[0])");
    buttons.put("��ʾ��������","delivery(document.all.tableform)");
    buttons.put("�� ��","history.back()");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "Ʒ�Ʋ�Ʒ����ͳ�Ʊ�");//��ͷ
%>
<html>
<lemis:body>
	<lemis:errors />
	<lemis:title title="Ʒ�Ʋ�Ʒ����ͳ�Ʋ�ѯ" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<TD style="vertical-align: top"><lemis:tabletitle title="ͳ������" />
					<table class="TableInput">
						<html:form action="/BusinessAction.do?method=querycpxs&"
							method="post">
							<lemis:editorlayout />
							<tr>
								<lemis:texteditor property="ivtgcltid" disable="false"
									required="false" label="�ͻ�����" />
								<lemis:texteditor property="ivtyear" disable="false"
									required="true" label="���" />
								<lemis:formateditor property="ivtmonth" disable="false"
									required="true" mask="nn" label="�·�" />
							</tr>
						</html:form>
					</table>

					</TD>
				</tr>
				<tr>
					<TD><lemis:table action="BusinessAction.do"
						headerMeta="header" hiddenMeta="hidden" topic="Ʒ������ͳ����Ϣ"
						mode="radio" pilot="false" orderResult="false" /> <lemis:buttons
						buttonMeta="button" /></TD>

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
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">

     //�鿴������ϸ
	function delivery(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=saleDetail&"/>'+getAlldata(obj);		
		obj.submit();
		}

	//ͳ�Ʋ�ѯ
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=querycpxs"/>';
		obj.submit();
	}
	if("<%=menuId%>"!="null" && "<%=menuId%>"!="") {
		document.all.iframediv.style.display = 'none';
		$(':button:eq(1)').val("��ʾͼ��");
	} else {
		$(':button:eq(1)').val("�ر�ͼ��");
	}
	//��ʾͼ��
	function showtx(obj) {
		if (document.all.iframediv.style.display == 'none') {
			document.all.iframediv.style.display = '';
			$(':button:eq(1)').val("�ر�ͼ��");
		} else {
			document.all.iframediv.style.display = 'none';
			$(':button:eq(1)').val("��ʾͼ��");
		}
	}
</script>
</html>

