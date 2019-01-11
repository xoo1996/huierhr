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
	header.add(new Formatter("temp01", "助听器分类"));
	header.add(new Formatter("temp03", "回款数"));
	header.add(new Formatter("temp02", "销售额", TagConstants.DF_RIGHT, TagConstants.DT_MONEY));
	header.add(new Formatter("percent", "所占百分比", TagConstants.DF_RIGHT));
	header.add(new Formatter("rank", "排名统计", TagConstants.DF_CENTER));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	//hidden.put("ivtgcltid", "客户代码");
	//hidden.put("ivtyear", "年份");
	//hidden.put("ivtmonth", "月份");
	hidden.put("temp01", "助听器分类");
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("统计查询","stat(document.forms[0])");
	buttons.put("显示图形","showtx(document.forms[0])");
	buttons.put("重 置","resetForm(document.forms[0])");
    buttons.put("显示销售详情","delivery(document.all.tableform)");
    buttons.put("返 回","history.back()");
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	session.setAttribute("tableheader", "品牌产品销售统计表");//表头
%>
<html>
<lemis:body>
	<lemis:errors />
	<lemis:title title="品牌产品销售统计查询" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align: top; width: 45%">
			<table border="0" width="100%">
				<tr>
					<TD style="vertical-align: top"><lemis:tabletitle title="统计条件" />
					<table class="TableInput">
						<html:form action="/BusinessAction.do?method=querycpxs&"
							method="post">
							<lemis:editorlayout />
							<tr>
								<lemis:texteditor property="ivtgcltid" disable="false"
									required="false" label="客户代码" />
								<lemis:texteditor property="ivtyear" disable="false"
									required="true" label="年份" />
								<lemis:formateditor property="ivtmonth" disable="false"
									required="true" mask="nn" label="月份" />
							</tr>
						</html:form>
					</table>

					</TD>
				</tr>
				<tr>
					<TD><lemis:table action="BusinessAction.do"
						headerMeta="header" hiddenMeta="hidden" topic="品牌销售统计信息"
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

     //查看订单明细
	function delivery(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		obj.action = '<html:rewrite page="/BusinessAction.do?method=saleDetail&"/>'+getAlldata(obj);		
		obj.submit();
		}

	//统计查询
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
</script>
</html>

