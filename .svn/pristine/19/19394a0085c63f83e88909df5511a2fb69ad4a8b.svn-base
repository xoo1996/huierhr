<!--/recommendation/statitics/condition.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="org.radf.plat.commons.DateUtil"%>
<%@ page import="org.radf.plat.commons.TypeCast"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.apps.commons.entity.Rt99"%>
<%@ page import="org.radf.plat.commons.ClassHelper"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="<html:rewrite forward="lemistree"/>"></script>
<html>
	<lemis:base />
	<lemis:body>
		<%
			List buttons = new ArrayList();
			buttons.add(new Button("query", "查 询", "rec060202",
					"query(document.forms[0])"));
			buttons.add(new Button("born", "报表生成", "rec060201",
					"born(document.forms[0])"));
			buttons.add(new Button("hz", "报表汇总", "rec060208",
					"hz(document.forms[0])"));
			buttons.add(new Button("resetForm", "重 置", "rec060203",
					"resetForm(document.forms[0])"));

			pageContext.setAttribute("buttons", buttons);

			List buttons1 = new ArrayList();
			buttons1.add(new Button("print", "打　印", "rec060205",
					"printreport()"));
			buttons1.add(new Button("update", "修　改", "rec060204",
					"saveinfo(document.forms[1])"));

			buttons1.add(new Button("upcast", "审核上报", "rec060206",
					"upcastc(document.forms[0])"));
			buttons1.add(new Button("Btn_close", "关 闭", "rec999999",
					"closeWindow(\"Rec06Form\")"));
			pageContext.setAttribute("buttons1", buttons1);

			session.setAttribute("tableheader", "职业介绍工作情况表");//表头
			List listrt99 = (List) session.getAttribute("listrt99");
		
               int cyear=DateUtil.getYear();
               TreeMap list1 = new TreeMap();
               for(int i=cyear-5;i<=cyear+5;i++)
	           {
   					String temp=TypeCast.intToString(i);
   					list1.put(temp,temp);
   			   }

         pageContext.setAttribute("data1",list1);
		%>

		<lemis:title title="职业介绍工作情况" />
		<html:form action="/Rec06Action.do?method=query" method="POST">
			<lemis:tabletitle title="职业介绍工作情况" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:hidden property="flag" value="1" />
					<lemis:codelisteditor type="year" redisplay="true" dataMeta="data1" label="年度" required="true"/>
					<lemis:texteditor property="ssjqnm" required="false" label="机构"
						disable="false" style="CURSOR:hand"
						onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:codelisteditor type="bsc006" label="虚拟机构" required="false"
						isSelect="true" redisplay="true" />
				</tr>
				</html:form>
				<lemis:buttons buttonMeta="buttons" />
			</TABLE>
			<%
					if (request.getParameter("flag") != null) {
					if (listrt99 != null) {
						HashMap report1 = new HashMap();
						HashMap report2 = new HashMap();
						HashMap report3 = new HashMap();
						HashMap report4 = new HashMap();
						HashMap report5 = new HashMap();
						HashMap report6 = new HashMap();
						HashMap report7 = new HashMap();
						HashMap report8 = new HashMap();
						HashMap reporttemp = new HashMap();
						for (int i = 0; i < listrt99.size(); i++) {
					Rt99 rt99 = new Rt99();
					ClassHelper.copyProperties(listrt99.get(i), rt99);
					String qmjggs = rt99.getBrt001();//期末职业机构个数
					String qmjgrs = rt99.getBrt002();//期末职业机构人数
					String bqzprs = rt99.getBrt003();//本期招聘人数
					String bqdjrs = rt99.getBrt004();//本期登记人数
					String bqdjnx = rt99.getBrt005();//本期登记女性
					String bqdjxg = rt99.getBrt006();//下岗
					String bqdjsy = rt99.getBrt007();//失业
					String bqdjzy = rt99.getBrt008();//职业资格
					String bqzjzd = rt99.getBrt009();//本期职业指导人数
					String bqjscg = rt99.getBrt010();//本期介绍成功人数
					String bqjsnx = rt99.getBrt011();//本期介绍成功人数(女性)
					String bqjsxg = rt99.getBrt012();//本期介绍成功人数(下岗)
					String bqjssy = rt99.getBrt013();//本期介绍成功人数(失业)
					String bqjszy = rt99.getBrt014();//本期介绍成功人数(职业资格)
					String brt999 = rt99.getBrt999();//流水号
					String xh = rt99.getBrt015();//序号
					Date sbrq = rt99.getAae036();//上报日期
					String brt996 = rt99.getBrt996();//统计时间
					String cce001 = rt99.getCce001();//统计机构
					String brt048 = rt99.getBrt048();//填报单位

					reporttemp.put("qmjggs", qmjggs);
					reporttemp.put("qmjgrs", qmjgrs);
					reporttemp.put("bqzprs", bqzprs);
					reporttemp.put("bqdjrs", bqdjrs);
					reporttemp.put("bqdjnx", bqdjnx);
					reporttemp.put("bqdjxg", bqdjxg);
					reporttemp.put("bqdjsy", bqdjsy);
					reporttemp.put("bqdjzy", bqdjzy);
					reporttemp.put("bqzjzd", bqzjzd);
					reporttemp.put("bqjscg", bqjscg);
					reporttemp.put("bqjsnx", bqjsnx);
					reporttemp.put("bqjsxg", bqjsxg);
					reporttemp.put("bqjssy", bqjssy);
					reporttemp.put("bqjszy", bqjszy);
					reporttemp.put("brt999", brt999);
					reporttemp.put("sbrq", sbrq);
					reporttemp.put("brt996", brt996);
					reporttemp.put("cce001", cce001);
					reporttemp.put("brt048", brt048);
					if (xh.equals("1")) {
						report1.putAll(reporttemp);
					}
					if (xh.equals("2")) {
						report2.putAll(reporttemp);
					}
					if (xh.equals("3")) {
						report3.putAll(reporttemp);
					}
					if (xh.equals("4")) {
						report4.putAll(reporttemp);
					}
					if (xh.equals("5")) {
						report5.putAll(reporttemp);
					}
					if (xh.equals("6")) {
						report6.putAll(reporttemp);
					}
					if (xh.equals("7")) {
						report7.putAll(reporttemp);
					}
					if (xh.equals("8")) {
						report8.putAll(reporttemp);
					}

						}
						String todayStr = DateUtil
						.convertDateToYearMonthDay((Date) report1
						.get("sbrq")); //当前时间
						String yyyy = todayStr.substring(0, 4);
						String mm = todayStr.substring(4, 6);
						String dd = todayStr.substring(6, 8);
			%>

			<html:form action="/Rec06Action.do?" method="POST">

				<table width="800" border="0" align="center">
					<tr>

						<td width="344">
							<div align="left">
								填报单位名称：
								<%=report1.get("brt048")%>
							</div>
						</td>
						<td width="61">
							<div align="right">
								<%=report1.get("brt996")%>
								年
							</div>
						</td>
						<td width="381">
							<div align="right">
								计量单位：个、人
							</div>
						</td>
					</tr>
				</table>
				<table width="800" border="1" align="center" cellpadding="0"
					cellspacing="0" style='border-collapse: collapse'
					bordercolor="#000000">
					<tr>
						<td rowspan="3" width="161"></td>
						<td rowspan="3" width="60"></td>
						<td rowspan="3" width="49">
							期末
							</br>
							职业
							</br>
							介绍
							</br>
							机构
							</br>
							个数
						</td>
						<td rowspan="3" width="45">
							期末
							</br>
							职业
							</br>
							介绍
							</br>
							机构
							</br>
							人数
						</td>
						<td rowspan="3" width="42" style="border-right:0px solid #ffffff;">
							本期
							</br>
							登记
							</br>
							招聘
							</br>
							人数
						</td>
						<td height="18" colspan="5"
							style="border-bottom:0px solid #ffffff;"></td>
						<td width="42" rowspan="3">
							本期
							</br>
							职业
							</br>
							指导
							</br>
							人数
						</td>
						<td rowspan="3" width="40" style="border-right:0px solid #ffffff;">
							本期
							</br>
							介绍
							</br>
							成功
							</br>
							人数
						</td>
						<td colspan="4" rowspan="2" style="border-left:0px solid #ffffff;"></td>
					</tr>
					<tr>
						<td width="40" rowspan="2" style="border-top:0px solid #ffffff;">
							本期
							</br>
							登记
							</br>
							求职
							</br>
							人数
						</td>

					</tr>

					<tr>
						<td width="28" height="111">
							女
							</br>
							性
						</td>
						<td width="28">
							下
							</br>
							岗
							</br>
							职
							</br>
							工
						</td>
						<td width="28">
							失
							</br>
							业
							</br>
							人
							</br>
							员
						</td>
						<td width="44">
							获得
							</br>
							职业
							</br>
							资格
							</br>
							人员
						</td>
						<td width="33">
							女
							</br>
							性
						</td>
						<td width="32">
							下
							</br>
							岗
							</br>
							职
							</br>
							工
						</td>
						<td width="30">
							失
							</br>
							业
							</br>
							人
							</br>
							员
						</td>
						<td width="36">
							获得
							</br>
							职业
							</br>
							资格
							</br>
							人员
						</td>
					</tr>
					<tr>
						<td>
							甲
						</td>
						<td>
							序号
						</td>
						<td>
							1
						</td>
						<td>
							2
						</td>
						<td>
							3
						</td>
						<td>
							4
						</td>
						<td>
							5
						</td>
						<td>
							6
						</td>
						<td>
							7
						</td>
						<td>
							8
						</td>
						<td>
							9
						</td>
						<td>
							10
						</td>
						<td>
							11
						</td>
						<td>
							12
						</td>
						<td>
							13
						</td>
						<td>
							14
						</td>
					</tr>
					<tr>
						<td>
							总 计
						</td>
						<td>
							1
						</td>
						<html:hidden property="brt999"
							value="<%=report1.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report1.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report1.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report1.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report1.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report1.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report1.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report1.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report1.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report1.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report1.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report1.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report1.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report1.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report1.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="left">
								劳动保障部门办
							</div>
						</td>
						<td>
							2
						</td>
						<html:hidden property="brt999"
							value="<%=report2.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report2.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report2.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report2.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report2.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report2.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report2.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report2.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report2.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report2.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report2.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report2.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report2.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report2.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report2.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="left">
								其中：县（区）及以上
							</div>
						</td>
						<td>
							3
						</td>
						<html:hidden property="brt999"
							value="<%=report3.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report3.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report3.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report3.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report3.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report3.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report3.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report3.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report3.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report3.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report3.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report3.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report3.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report3.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report3.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>
					<tr>
						<td>
							街道
						</td>
						<td>
							4
						</td>
						<html:hidden property="brt999"
							value="<%=report4.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report4.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report4.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report4.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report4.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report4.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report4.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report4.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report4.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report4.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report4.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report4.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report4.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report4.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report4.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>
					<tr>
						<td>
							乡镇
						</td>
						<td>
							5
						</td>
						<html:hidden property="brt999"
							value="<%=report5.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report5.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report5.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report5.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report5.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report5.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report5.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report5.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report5.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report5.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report5.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report5.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report5.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report5.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report5.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>

					</tr>
					<tr>
						<td>
							<div align="left">
								其他组织办
							</div>
						</td>
						<td>
							6
						</td>
						<html:hidden property="brt999"
							value="<%=report6.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report6.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report6.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report6.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report6.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report6.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report6.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report6.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report6.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report6.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report6.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report6.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report6.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report6.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report6.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="left">
								公民个人办
							</div>
						</td>
						<td>
							7
						</td>
						<html:hidden property="brt999"
							value="<%=report7.get("brt999").toString()%>" />
						<td>
							<html:text size="3" property="brt001"
								value="<%=report7.get("qmjggs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt002"
								value="<%=report7.get("qmjgrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt003"
								value="<%=report7.get("bqzprs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt004"
								value="<%=report7.get("bqdjrs").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt005"
								value="<%=report7.get("bqdjnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt006"
								value="<%=report7.get("bqdjxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt007"
								value="<%=report7.get("bqdjsy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt008"
								value="<%=report7.get("bqdjzy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt009"
								value="<%=report7.get("bqzjzd").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt010"
								value="<%=report7.get("bqjscg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt011"
								value="<%=report7.get("bqjsnx").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt012"
								value="<%=report7.get("bqjsxg").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt013"
								value="<%=report7.get("bqjssy").toString()%>"
								onblur="totalcompute()" />
						</td>
						<td>
							<html:text size="3" property="brt014"
								value="<%=report7.get("bqjszy").toString()%>"
								onblur="totalcompute()" />
						</td>
					</tr>

				</table>

				<table width="800" border="0" align="center">
					<tr>
						<td colspan="16">
							<html:hidden property="brt999"
								value="<%=report8.get("brt999").toString()%>" />
							<html:hidden property="brt004"
								value="<%=report8.get("bqdjrs").toString()%>" />
							<html:hidden property="brt005"
								value="<%=report8.get("bqdjnx").toString()%>" />
							<html:hidden property="brt006"
								value="<%=report8.get("bqdjxg").toString()%>" />
							<html:hidden property="brt007"
								value="<%=report8.get("bqdjsy").toString()%>" />
							<html:hidden property="brt008"
								value="<%=report8.get("bqdjzy").toString()%>" />
							<html:hidden property="brt009"
								value="<%=report8.get("bqzjzd").toString()%>" />
							<html:hidden property="brt010"
								value="<%=report8.get("bqjscg").toString()%>" />
							<html:hidden property="brt011"
								value="<%=report8.get("bqjsnx").toString()%>" />
							<html:hidden property="brt012"
								value="<%=report8.get("bqjsxg").toString()%>" />
							<html:hidden property="brt013"
								value="<%=report8.get("bqjssy").toString()%>" />
							<html:hidden property="brt014"
								value="<%=report8.get("bqjszy").toString()%>" />

							<p align="left">
								补充资料：县（区）及以上劳动保障部门办职业介绍机构中，财政全额拨款的
								<html:text size="3" property="brt001"
									value="<%=report8.get("qmjggs").toString()%>" />
								个，财政差额拨款的
								<html:text size="3" property="brt002"
									value="<%=report8.get("qmjgrs").toString()%>" />
								个，自收自支的
								<html:text size="3" property="brt003"
									value="<%=report8.get("bqzprs").toString()%>" />
								个
							</p>
							<p>
								&nbsp;
							</p>
						</td>

					</tr>
					<tr>
						<td>
							<div align="left">
								单位负责人签章：
							</div>
						</td>
						<td>
							处（科）负责人签章：
						</td>
						<td>
							制表人签章：
						</td>
						<td>
							<div align="right">
								报出日期： 年 月 日 
								
							</div>
						</td>
					</tr>
				</table>
				<lemis:buttons buttonMeta="buttons1" />
			</html:form>
			<%
				}
				}
			%>
			<lemis:errors />
	</lemis:body>

	<%
	session.setAttribute("listrt99", null);
	%>
</html>

<script language="javascript">
//打印报表
function printreport()
{
	var para="cce001="+document.all.cce001.value;
    para+="&brt996="+document.all.year.value;

	window.open('/'+lemis.WEB_APP_NAME+'/recommendation/Rec06Action.do?method=printRt99&'+para,'print1','height='+(screen.height-80)+ ',width='+(screen.width-20)+',top=5, left=5, toolbar=no, menubar=yes, scrollbars=yes,resizable=yes,location=no, status=yes');
 
}
	//查询报表
    function query(obj){
	 var para = getAlldata(obj);
	     if (!checkValue(obj)) {
	     	return false;
	     }
	  	 var cce001=obj.cce001.value;
	     var bsc006=obj.bsc006.value;
	     if(cce001=="" && bsc006=="")
	     {
	       alert("机构或者虚拟机构必须选择一个");
	       return false;
	     }else if(cce001!="" && bsc006!=""){
	       alert("机构或者虚拟机构只能选择一个");
	       return false;
	     }
		 obj.action = '<html:rewrite page="/Rec06Action.do?method=initUpdate&"/>'+para;
	     obj.submit();	  
	}
	//报表生成
	function born(obj){

	 var para = getAlldata(obj);
		  if (!checkValue(obj)) {
		    return false;
		  }
		 var cce001=obj.cce001.value;
	     var bsc006=obj.bsc006.value;
	     if(cce001=="" && bsc006=="")
	     {
	       alert("机构或者虚拟机构必须选择一个");
	       return false;
	     }else if(cce001!="" && bsc006!=""){
	       alert("机构或者虚拟机构只能选择一个");
	       return false;
	     }
		 obj.action = '<html:rewrite page="/Rec06Action.do?method=born&"/>'+para;
	     obj.submit();	  
	}
	//汇总数据
	function hz(obj){

	 var para = getAlldata(obj);
		  if (!checkValue(obj)) {
		    return false;
		  }
		  if(document.all.bsc006.value!=""){
		  		alert("虚拟机构不能汇总!");
		  		return false;
		 }	
		  obj.action = '<html:rewrite page="/Rec06Action.do?method=born_hz&"/>'+para;
	      obj.submit();	  
	}
	//修改保存
	function saveinfo(obj){
		
	   if(!checkinput()){
       		return false;
       }
		if (!checkValue(obj)) {
			return false;
		}

		obj.action = '<html:rewrite page="/Rec06Action.do?method=save&"/>'+getAlldata(obj);		
		obj.submit();
	}
	//审核上报
	function upcastc(obj){
	    if(!checkinput()){
       		return false;
        }
	    if(document.all.bsc006.value!=""){
		  		alert("虚拟机构不能上报!");
		  		return false;
		 }
		 var truthBeTold = window.confirm("是否确定审核上报？");
        if (truthBeTold){			
		 obj.action = '<html:rewrite page="/Rec06Action.do?method=upcast&"/>'+getAlldata(obj);	
	     obj.submit();
	     }	  
	}
	
	// 检查是否是数字 
function isDigit(str)
{
	var flag=true
	if (str!=null)
	{
		for(var loc=0;loc<str.length;loc++)
		{
			if((str.charAt(loc)<'0')||(str.charAt(loc)>'9'))
			{
				flag=false
				return flag;
			}
		}		
	}

	return flag;
	
}
function isNumbercheck(s)
{
   var flag=true;
   var patrn=/^[0]{1}$|^[1-9]*[1-9][0-9]*$/;
   //var patrn=/^\\d+$/;
   if (!patrn.exec(s)){ 
      flag=false;
	  return flag;
   }
   return flag;
}
 //检查输入格式
  function checkinput(){
       var flag=true
  	   var obj=eval(document.all.brt999);
       var brt001=eval(document.all.brt001);
       var brt002=eval(document.all.brt002);
       var brt003=eval(document.all.brt003);
       var brt004=eval(document.all.brt004);
       var brt005=eval(document.all.brt005);
       var brt006=eval(document.all.brt006);
       var brt007=eval(document.all.brt007);
       var brt008=eval(document.all.brt008);
       var brt009=eval(document.all.brt009);
       var brt010=eval(document.all.brt010);
       var brt011=eval(document.all.brt011);
       var brt012=eval(document.all.brt012);
       var brt013=eval(document.all.brt013);
       var brt014=eval(document.all.brt014);
       var length=obj.length;

       //检查是否数字型
       for (i=1;i<length-1;i++){
               
           
           if(brt001[i].value==null||brt001[i].value==""){
           		alert("输入不能为空!");
           		brt001[i].focus();
           		brt001[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt001[i].value)){
           		alert("请输入0到n的数字!");
           		brt001[i].focus();
           		brt001[i].value="0";
           		flag=false
				return flag;
           }
           if(brt002[i].value==null||brt002[i].value==""){
           		alert("输入不能为空!");
           		brt002[i].focus();
           		brt002[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt002[i].value)){
           		alert("请输入0到n的数字!");
           		brt002[i].focus();
           		brt002[i].value="0";
           		flag=false
				return flag;
           }
           if(brt003[i].value==null||brt003[i].value==""){
           		alert("输入不能为空!");
           		brt003[i].focus();
           		brt003[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt003[i].value)){
           		alert("请输入0到n的数字!");
           		brt003[i].focus();
           		brt003[i].value="0";
           		flag=false
				return flag;
           }
           if(brt004[i].value==null||brt004[i].value==""){
           		alert("输入不能为空!");
           		brt004[i].focus();
           		brt004[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt004[i].value)){
           		alert("请输入0到n的数字!");
           		brt004[i].focus();
           		brt004[i].value="0";
           		flag=false
				return flag;
           }
           
           if(brt005[i].value==null||brt005[i].value==""){
           		alert("输入不能为空!");
           		brt005[i].focus();
           		brt005[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt005[i].value)){
           		alert("请输入0到n的数字!");
           		brt005[i].focus();
           		brt005[i].value="0";
           		flag=false
				return flag;
           }
                   
          if(brt006[i].value==null||brt006[i].value==""){
           		alert("输入不能为空!");
           		brt006[i].focus();
           		brt006[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt006[i].value)){
           		alert("请输入0到n的数字!");
           		brt006[i].focus();
           		brt006[i].value="0";
           		flag=false
				return flag;
           }
           if(brt007[i].value==null||brt007[i].value==""){
           		alert("输入不能为空!");
           		brt007[i].focus();
           		brt007[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt007[i].value)){
           		alert("请输入0到n的数字!");
           		brt007[i].focus();
           		brt007[i].value="0";
           		flag=false
				return flag;
           }
           if(brt008[i].value==null||brt008[i].value==""){
           		alert("输入不能为空!");
           		brt008[i].focus();
           		brt008[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt008[i].value)){
           		alert("请输入0到n的数字!");
           		brt008[i].focus();
           		brt008[i].value="0";
           		flag=false
				return flag;
           }
           if(brt009[i].value==null||brt009[i].value==""){
           		alert("输入不能为空!");
           		brt009[i].focus();
           		brt009[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt009[i].value)){
           		alert("请输入0到n的数字!");
           		brt009[i].focus();
           		brt009[i].value="0";
           		flag=false
				return flag;
           }
           if(brt010[i].value==null||brt010[i].value==""){
           		alert("输入不能为空!");
           		brt010[i].focus();
           		brt010[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt010[i].value)){
           		alert("请输入0到n的数字!");
           		brt010[i].focus();
           		brt010[i].value="0";
           		flag=false
				return flag;
           }
          if(brt011[i].value==null||brt011[i].value==""){
           		alert("输入不能为空!");
           		brt011[i].focus();
           		brt011[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt011[i].value)){
           		alert("请输入0到n的数字!");
           		brt011[i].focus();
           		brt011[i].value="0";
           		flag=false
				return flag;
           }
           if(brt012[i].value==null||brt012[i].value==""){
           		alert("输入不能为空!");
           		brt012[i].focus();
           		brt012[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt012[i].value)){
           		alert("请输入0到n的数字!");
           		brt012[i].focus();
           		brt012[i].value="0";
           		flag=false
				return flag;
           }
           
          if(brt013[i].value==null||brt013[i].value==""){
           		alert("输入不能为空!");
           		brt013[i].focus();
           		brt013[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt013[i].value)){
           		alert("请输入0到n的数字!");
           		brt013[i].focus();
           		brt013[i].value="0";
           		flag=false
				return flag;
           }
           
           if(brt014[i].value==null||brt014[i].value==""){
           		alert("输入不能为空!");
           		brt014[i].focus();
           		brt014[i].value="0";
           		flag=false
				return flag;           		
           }
           if(!isNumbercheck(brt014[i].value)){
           		alert("请输入0到n的数字!");
           		brt014[i].focus();
           		brt014[i].value="0";
           		flag=false
				return flag;
           }
           
       }
       return flag;
     }
   
   //总计计算
   function totalcompute(){
       var obj=eval(document.all.brt999);
       var brt001=eval(document.all.brt001);
       var brt002=eval(document.all.brt002);
       var brt003=eval(document.all.brt003);
       var brt004=eval(document.all.brt004);
       var brt005=eval(document.all.brt005);
       var brt006=eval(document.all.brt006);
       var brt007=eval(document.all.brt007);
       var brt008=eval(document.all.brt008);
       var brt009=eval(document.all.brt009);
       var brt010=eval(document.all.brt010);
       var brt011=eval(document.all.brt011);
       var brt012=eval(document.all.brt012);
       var brt013=eval(document.all.brt013);
       var brt014=eval(document.all.brt014);
       var length=obj.length;

       if(!checkinput()){
       		return false;
       }
       //劳动保障部门办=县(区)+街道+乡镇(3+4+5)
       brt001[1].value=parseInt(brt001[2].value)+parseInt(brt001[3].value)+parseInt(brt001[4].value);
       brt002[1].value=parseInt(brt002[2].value)+parseInt(brt002[3].value)+parseInt(brt002[4].value);
       brt003[1].value=parseInt(brt003[2].value)+parseInt(brt003[3].value)+parseInt(brt003[4].value);
       brt004[1].value=parseInt(brt004[2].value)+parseInt(brt004[3].value)+parseInt(brt004[4].value);
       brt005[1].value=parseInt(brt005[2].value)+parseInt(brt005[3].value)+parseInt(brt005[4].value);
       brt006[1].value=parseInt(brt006[2].value)+parseInt(brt006[3].value)+parseInt(brt006[4].value);
       brt007[1].value=parseInt(brt007[2].value)+parseInt(brt007[3].value)+parseInt(brt007[4].value);
       brt008[1].value=parseInt(brt008[2].value)+parseInt(brt008[3].value)+parseInt(brt008[4].value);
       brt009[1].value=parseInt(brt009[2].value)+parseInt(brt009[3].value)+parseInt(brt009[4].value);
       brt010[1].value=parseInt(brt010[2].value)+parseInt(brt010[3].value)+parseInt(brt010[4].value);
       brt011[1].value=parseInt(brt011[2].value)+parseInt(brt011[3].value)+parseInt(brt011[4].value);
       brt012[1].value=parseInt(brt012[2].value)+parseInt(brt012[3].value)+parseInt(brt012[4].value);
       brt013[1].value=parseInt(brt013[2].value)+parseInt(brt013[3].value)+parseInt(brt013[4].value);
       brt014[1].value=parseInt(brt014[2].value)+parseInt(brt014[3].value)+parseInt(brt014[4].value);
       //总计=劳动保障部门办+其他组织办+公民个人办(2+6+7)
       brt001[0].value=parseInt(brt001[1].value)+parseInt(brt001[5].value)+parseInt(brt001[6].value);
       brt002[0].value=parseInt(brt002[1].value)+parseInt(brt002[5].value)+parseInt(brt002[6].value);
       brt003[0].value=parseInt(brt003[1].value)+parseInt(brt003[5].value)+parseInt(brt003[6].value);
       brt004[0].value=parseInt(brt004[1].value)+parseInt(brt004[5].value)+parseInt(brt004[6].value);
       brt005[0].value=parseInt(brt005[1].value)+parseInt(brt005[5].value)+parseInt(brt005[6].value);
       brt006[0].value=parseInt(brt006[1].value)+parseInt(brt006[5].value)+parseInt(brt006[6].value);
       brt007[0].value=parseInt(brt007[1].value)+parseInt(brt007[5].value)+parseInt(brt007[6].value);
       brt008[0].value=parseInt(brt008[1].value)+parseInt(brt008[5].value)+parseInt(brt008[6].value);
       brt009[0].value=parseInt(brt009[1].value)+parseInt(brt009[5].value)+parseInt(brt009[6].value);
       brt010[0].value=parseInt(brt010[1].value)+parseInt(brt010[5].value)+parseInt(brt010[6].value);
       brt011[0].value=parseInt(brt011[1].value)+parseInt(brt011[5].value)+parseInt(brt011[6].value);
       brt012[0].value=parseInt(brt012[1].value)+parseInt(brt012[5].value)+parseInt(brt012[6].value);
       brt013[0].value=parseInt(brt013[1].value)+parseInt(brt013[5].value)+parseInt(brt013[6].value);
       brt014[0].value=parseInt(brt014[1].value)+parseInt(brt014[5].value)+parseInt(brt014[6].value);


       
   }

 </script>

