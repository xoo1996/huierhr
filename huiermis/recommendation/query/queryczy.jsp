<!-- lemis/recommendation/query/queryczy.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<lemis:base />
<script src="<html:rewrite forward="lemistree"/>"></script>
<%
String menuId = (String) request.getParameter("menuId");
			
			List header = new ArrayList();
			header.add(new Formatter("aae011", "操作员"));			
			header.add(new Formatter("temp03", "推荐人次"));
			header.add(new Formatter("temp02", "推荐中人次"));
			header.add(new Formatter("temp04", "成功人次"));
			header.add(new Formatter("temp05", "失败人次"));
			header.add(new Formatter("temp06", "成功率"));
			pageContext.setAttribute("header", header);

			Map hidden = new LinkedHashMap();
			pageContext.setAttribute("hidden", hidden);

			//		定义按钮
			List buttons = new ArrayList();//定义按钮属性
			buttons.add(new Button("stat", "统计查询", "rec030701","stat(document.forms[0])"));
			buttons.add(new Button("resetForm", "重 置", "rec030703","resetForm(document.forms[0])"));

			pageContext.setAttribute("button", buttons);

			session.setAttribute("tableheader", "职业介绍统计信息表");//表头
			%>
<html>
<lemis:body>
	<!--信息列表（只读）-->
	<lemis:errors />
	<lemis:title title="职业介绍统计查询" />
	<table border="1" cellspacing="0" cellpadding="2" style="">
		<tr>
			<TD style="vertical-align:top;width:45%">
			<table border="0" width="100%">
				<tr>
					<TD style="vertical-align:top"><html:form
						action="/Rec0305Action.do?method=queryczy&" method="post">
						<lemis:tabletitle title="职业介绍信息统计" />
						<table class="TableInput">
							<lemis:editorlayout />
							
							<tr>
								<lemis:formateditor property="date01" mask="date" disable="false" required="true" label="时间从" />
								<lemis:formateditor property="date02" mask="date" disable="false" required="true" label="到" />

							</tr>
							<tr>
							   <lemis:texteditor property="ssjqnm" required="false" label="机构"
									disable="false" style="CURSOR:hand"
									onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
								<html:hidden property="cce001" />
								<lemis:codelisteditor type="bsc006" label="虚拟机构" required="false" isSelect="true" redisplay="true" />

							</tr>
					</html:form>
			</table>

			<lemis:buttons buttonMeta="button" /></TD>
		</tr>
		<tr>
			<TD><lemis:table action="Rec0305Action.do" headerMeta="header"
				hiddenMeta="hidden" topic="职业介绍统计信息" mode="radio" pilot="true"
				orderResult="true" /></TD>
		</tr>
	</table>
	</TD>	
	</tr>
	</table>

	<lemis:bottom />
</lemis:body>

<SCRIPT language="javascript">
//统计查询
  function stat(obj){
     var t = checkValue(obj);
     if(!t){
       return t;
     }
     var cce001=obj.cce001.value;
     var bsc006=obj.bsc006.value;
     //alert(cce001);
     //alert(bsc006);
     if(cce001=="" && bsc006=="")
     {
       alert("机构或者虚拟机构必须选择一个");
       return null;
     }
    obj.action= '<html:rewrite page="/Rec0305Action.do?method=queryczy"/>';             
    obj.submit();    
  }
</SCRIPT>
</html>

