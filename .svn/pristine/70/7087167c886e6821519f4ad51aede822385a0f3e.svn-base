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
	header.add(new Formatter("temp2", "统计项目"));	
	header.add(new Formatter("temp1", "数量"));	
	header.add(new Formatter("percent", "所占百分比", TagConstants.DF_RIGHT));
	header.add(new Formatter("rank", "排名统计", TagConstants.DF_CENTER));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("temp2","项目");
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	
	buttons.put("统计查询","stat(document.forms[0])");
	//buttons.put("明细","detail(document.all.tableform)");
	buttons.put("显示图形","showtx(document.forms[0])");
	buttons.put("重 置","resetForm(document.forms[0])");
	buttons.put("明细","detail(document.all.tableform)");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "人事统计表");//表头
%>
<html>
<lemis:body>
	<lemis:errors />
	<lemis:title title="人事统计查询" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<TD style="vertical-align: top"><lemis:tabletitle title="统计条件" />
					<table class="TableInput">
						<html:form action="/StatisticsAction.do?method=stati&"
							method="post">
							<lemis:editorlayout />
							<tr>
							<lemis:formateditor property="stistartdate" disable="false"
									required="true" label="开始" mask="date"/>
								<lemis:formateditor property="stidate" disable="false"
									required="true" label="结束" mask="date"/></tr>
								<tr>
								<!-- <td>状态</td>
								<td>
								<select id="status" name="status">  
								  <option value ="1">在职</option>  
								  <option value ="0">离职</option>  
								 <option value="2">全部</option>  
								</select>  </td> -->
								<lemis:codelisteditor type="status" label="状态" required="false" />
								<lemis:codelisteditor type="stiitem" label="项目" required="true" onchange="onValueChange(this.value)"/>
							</tr>
						</html:form>
					</table>

					</TD>
				</tr>
				<tr>
					<TD><lemis:table action="Rec0305Action.do" headerMeta="header"
						hiddenMeta="hidden" topic="统计信息" mode="checkbox" pilot="false"
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
	function onValueChange(obj){
		if(obj == 4 || obj == 5 || obj ==6 || obj ==8){
			
			$("#status").attr("disabled",true);
		}else{
			$("#status").attr("disabled",false);
		}
	}
	//统计查询
	function stat(obj) {
		var t = checkValue(obj);
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/StatisticsAction.do?method=stati"/>';
		obj.submit();
	}
	if("<%=menuId%>"!="null" && "<%=menuId%>"!="") {
		document.all.iframediv.style.display = 'none';
		$(':button:eq(1)').val("显示图形");
	} else {
		$(':button:eq(1)').val("关闭图形");
	}
	//显示图形
	function showtx(obj) {
		if (document.all.iframediv.style.display == 'none') {
			document.all.iframediv.style.display = '';
			$(':button:eq(1)').val("关闭图形");
		} else {
			document.all.iframediv.style.display = 'none';
			$(':button:eq(1)').val("显示图形");
		}
	}
	function detail(obj) {
		var t = delObj("chk");//校验有没有可提交项
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//对必录项校验
		if (!t) {
			return t;
		}
		obj.action = '<html:rewrite page="/StatisticsAction.do?method=detail&"/>'+'stistartdate='+$("#stistartdate").val()+'&stidate='+$("#stidate").val()+'&stiitem='+$("#stiitem").val()+'&status='+$("#status").val();			
		obj.submit();
		/* window.location.href = "/" + lemis.WEB_APP_NAME
		+ "/statistics/StatisticsAction.do?method=detail&"
		+ getAlldata(obj)+"&stistartdate="+$("#stistartdate").val()+"&stidate="+$("#stidate").val()+"&stiitem="+$("#stiitem").val(); */
	}; 
</script>
</html>

