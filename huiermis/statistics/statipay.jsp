<!-- lemis/recommendation/query/querychart.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<lemis:base />
<%
	String menuId = (String) request.getParameter("menuId");

    Integer ivtmonth = (Integer) request.getAttribute("ivtmonth");
    Integer ivtyear = (Integer) request.getAttribute("ivtyear");

    request.getSession().setAttribute("ivtyear",ivtyear);

    request.getSession().setAttribute("ivtmonth",ivtmonth);
    
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("temp2", "ͳ����Ŀ"));	
	header.add(new Formatter("temp1", "�����ܶ�",TagConstants.DF_CENTER));	
	header.add(new Formatter("percent", "��ռ�ٷֱ�", TagConstants.DF_CENTER));
	header.add(new Formatter("temp3", "����",TagConstants.DF_CENTER));
	header.add(new Formatter("temp4", "�˾�����",TagConstants.DF_CENTER));
	header.add(new Formatter("rank", "����ͳ��", TagConstants.DF_CENTER));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("temp2","��Ŀ");
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("ͳ�Ʋ�ѯ","stat(document.forms[0])");
	//buttons.put("��ϸ","detail(document.all.tableform)");
	buttons.put("��ʾͼ��","showtx(document.forms[0])");
	buttons.put("�� ��","resetForm(document.forms[0])");
	buttons.put("��ϸ","detail(document.all.tableform)");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "н��ͳ�Ʊ�");//��ͷ
%>
<html>
<lemis:body>
	<lemis:errors />
	<lemis:title title="н��ͳ�Ʋ�ѯ" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<TD style="vertical-align: top"><lemis:tabletitle title="ͳ������" />
					<table class="TableInput">
						<html:form action="/StatisticsAction.do?method=statiPay&"
							method="post">
							<lemis:editorlayout />
							<tr>
							<lemis:codelisteditor type="stipayitem" label="��Ŀ" required="true" />
							</tr>
							<tr>
							<lemis:texteditor property="startyear" disable="false" 
									required="true" label="��ʼ���"/>
								<lemis:texteditor property="startmonth" disable="false" 
									required="true" label="��ʼ�·�"/>		
							</tr>
							<tr>
							<lemis:texteditor property="endyear" disable="false" 
									required="true" label="�������"/>
								<lemis:texteditor property="endmonth" disable="false" 
									required="true" label="�����·�"/>		
							</tr>
						</html:form>
					</table>

					</TD>
				</tr>
				<tr>
					<TD><lemis:table action="Rec0305Action.do" headerMeta="header"
						hiddenMeta="hidden" topic="ͳ����Ϣ" mode="checkbox" pilot="false"
						orderResult="false" pageSize="11"/>
					 <lemis:buttons buttonMeta="button" />
					
					</TD>
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
	//ͳ�Ʋ�ѯ
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/StatisticsAction.do?method=statiPay"/>';
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
	function detail(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/StatisticsAction.do?method=detailPay&"/>'+'startyear='+$("input[name=startyear]").val()+'&startmonth='+$("input[name=startmonth]").val()+'&stipayitem='+$("#stipayitem").val()+'&endyear='+$("input[name=endyear]").val()+'&endmonth='+$("input[name=endmonth]").val();			
		obj.submit();
		/* window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/statistics/StatisticsAction.do?method=detail&"
		+ getAlldata(obj)+"&stistartdate="+$("#stistartdate").val()+"&stidate="+$("#stidate").val()+"&stiitem="+$("#stiitem").val(); */
	}; 
</script>
</html>

