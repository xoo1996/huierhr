<!-- lemis/recommendation/query/querychart2.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map,java.util.LinkedHashMap"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<lemis:base />
<script src="<html:rewrite forward="lemistree"/>"></script>
<%
    String tmp001 = (String) request.getSession().getAttribute("tmp001");
			//tmp001 = tmp001.substring(5);
			if (tmp001 != null && !"".equals(tmp001)) {
				tmp001 = tmp001.toLowerCase();
			}
			System.out.println("tmp001: " + tmp001);

			String menuId = (String) request.getParameter("menuId");
		//	String type = (String) request.getSession().getAttribute("type");


			//设置统计类别 数据源
			TreeMap data = new TreeMap();
		//	if("qz".equals(type))
		//	{
		//		data.put("CC20.AAC011","文化程度");
		//		data.put("CC20.BAC299","人员类别");
		//		data.put("CC21.AAC048","用工形式");
		//		data.put("CC21.ACA111","工种");				
		//		data.put("CC21.AAB019","单位类型");	
		//		data.put("CC21.AAB020","经济类型");	
		//		data.put("AC01.AAC009","户口性质");	
		//		data.put("AC01.AAC033","健康状况");
		//		data.put("AC01.AAC004","性别");
		//		data.put("AC01.AAC005","民族");
		//		data.put("AC01.AAC024","政治面貌");
		//		data.put("AC01.AAC017","婚姻状况");
				
		//	}else
		//	{
				data.put("AB01.AAB019","单位性质");
				data.put("AB01.AAB020","经济类型");
				data.put("AB01.AAB048","经营方式");				
				data.put("CB21.BAC299","人员类别");
				data.put("CB21.AAC048","用工形式");
				data.put("CB21.ACA111","工种");
				data.put("CB21.AAC004","性别");
				data.put("CB21.AAC009","户口性质");	
				data.put("CB21.AAC014","专业技术职务");
				data.put("CB21.AAC015","国家职业资格等级");
				data.put("CB21.AAC017","婚姻状况");
				data.put("CB21.AAC024","政治面貌");
		//	}
					
			
			
			//data.put("CC03.BAC298","人员类别"); 后再加
			pageContext.setAttribute("data",data);
						
			
			List header = new ArrayList();
			header.add(new Formatter(""+tmp001+"", "类别"));
			header.add(new Formatter("tmp002", "人次"));
			header.add(new Formatter("tmp003", "比例"));
			header.add(new Formatter("tmp004", "平均工资"));
			pageContext.setAttribute("header", header);

			Map hidden = new LinkedHashMap();
			pageContext.setAttribute("hidden", hidden);

			//		定义按钮
			List buttons = new ArrayList();//定义按钮属性
		//	if("qz".equals(type))
		//	{
			buttons.add(new Button("stat", "统计查询", "rec030501","stat(document.forms[0])"));
			buttons.add(new Button("showtx", "显示图形", "rec030502","showtx(document.forms[0])"));
			buttons.add(new Button("resetForm", "重 置", "rec030503","resetForm(document.forms[0])"));
		//	}
		//	else
		//	{
		//		buttons.add(new Button("stat", "统计查询", "rec030601","stat(document.forms[0])"));
		//		buttons.add(new Button("showtx", "显示图形", "rec030602","showtx(document.forms[0])"));
		//		buttons.add(new Button("resetForm", "重 置", "rec030603","resetForm(document.forms[0])"));
		//	}
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
						action="/Rec0305Action.do?method=queryStat&" method="post">
						<lemis:tabletitle title="职业介绍信息统计" />
						<table class="TableInput">
							<lemis:editorlayout />
							<tr>
								
								<lemis:codelisteditor type="stat01" label="类别" required="true" isSelect="true" redisplay="true" dataMeta="data" colspan="3"/>

							</tr>
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
				hiddenMeta="hidden" topic="职业介绍统计信息" mode="radio" pilot="false"
				orderResult="false" /></TD>
		</tr>
	</table>
	</TD>
	<TD style="vertical-align:top;" id="iframediv">
	<table border="0">
		<tr>
			<td>
			    <iframe src='<html:rewrite page="/ChartAction.do?method=show"/>'
				style="display:inline" align="center" id="iframe1" name="iframe1"
				frameborder="0" width="100%" framespacing="0"
				allowTransparency="true"></iframe>
			</td>
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
    obj.action= '<html:rewrite page="/Rec0305Action.do?method=queryStat2"/>';             
    obj.submit();    
  }
  

	if("<%=menuId%>"!="null" &&"<%=menuId%>"!=""){
  
     document.all.iframediv.style.display='none';
     document.all.showtx.value="显示图形";
   }else
   {
   document.all.showtx.value="关闭图形";
   }
   
   //显示图形
	function showtx(obj){
	  if(document.all.iframediv.style.display=='none'){
	     document.all.iframediv.style.display='';
	     document.all.showtx.value="关闭图形";
	  }else{
	     document.all.iframediv.style.display='none';
	     document.all.showtx.value="显示图形";
	  }
	}
</SCRIPT>
</html>

