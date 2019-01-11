<!--lemis/recommendation/statistics/viewreport.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.apps.commons.entity.Rt99" %>
<%@ page import="org.radf.plat.commons.ClassHelper" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<script src="/lemis/js/lemisTree.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>职业工作情况</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
<!--
BODY {font-size:13px;}
td {font-size:13px;text-align:center;line-height:18px;}
.style4 {font-family: "宋体"; font-size: 20px;}
.toptable td{font-size:11px;}
.table {
	border-top: 1px solid #000000;
	border-right: 1px solid #ACACAC;
	border-bottom: 1px solid #ACACAC;
	border-left: 1px solid #000000;
}
.td {
	font-family: "宋体";
	font-size: 20px;
	color: #000000;
	border-top: 1px solid #DEE4F1;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	border-left: 1px solid #DEE4F1;
text-align:center;
	font-weight:bold;
	font-size:16px;
}
-->
    </style>
  </head>

 <%
List listrt99 = (List)session.getAttribute("listrt99");
HashMap report1=new HashMap();
HashMap report2=new HashMap();
HashMap report3=new HashMap();
HashMap report4=new HashMap();
HashMap report5=new HashMap();
HashMap report6=new HashMap();
HashMap report7=new HashMap();
HashMap report8=new HashMap();
HashMap reporttemp=new HashMap();
for(int i=0;i<listrt99.size();i++){
	Rt99 rt99=new Rt99();
	ClassHelper.copyProperties(listrt99.get(i), rt99);
	String qmjggs=rt99.getBrt001();//期末职业机构个数
	String qmjgrs=rt99.getBrt002();//期末职业机构人数
	String bqzprs=rt99.getBrt003();//本期招聘人数
	String bqdjrs=rt99.getBrt004();//本期登记人数
	String bqdjnx=rt99.getBrt005();//本期登记女性
	String bqdjxg=rt99.getBrt006();//下岗
	String bqdjsy=rt99.getBrt007();//失业
	String bqdjzy=rt99.getBrt008();//职业资格
	String bqzjzd=rt99.getBrt009();//本期职业指导人数
	String bqjscg=rt99.getBrt010();//本期介绍成功人数
	String bqjsnx=rt99.getBrt011();//本期介绍成功人数(女性)
	String bqjsxg=rt99.getBrt012();//本期介绍成功人数(下岗)
	String bqjssy=rt99.getBrt013();//本期介绍成功人数(失业)
	String bqjszy=rt99.getBrt014();//本期介绍成功人数(职业资格)
	String brt999=rt99.getBrt999();//流水号
	String xh=rt99.getBrt015();//序号
	Date sbrq=rt99.getAae036();//上报日期
	String brt996=rt99.getBrt996();//统计时间
	String cce001=rt99.getCce001();//填报单位
	
	reporttemp.put("qmjggs",qmjggs);
	reporttemp.put("qmjgrs",qmjgrs);
	reporttemp.put("bqzprs",bqzprs);
	reporttemp.put("bqdjrs",bqdjrs);
	reporttemp.put("bqdjnx",bqdjnx);
	reporttemp.put("bqdjxg",bqdjxg);
	reporttemp.put("bqdjsy",bqdjsy);
	reporttemp.put("bqdjzy",bqdjzy);
	reporttemp.put("bqzjzd",bqzjzd);
	reporttemp.put("bqjscg",bqjscg);
	reporttemp.put("bqjsnx",bqjsnx);
	reporttemp.put("bqjsxg",bqjsxg);
	reporttemp.put("bqjssy",bqjssy);
	reporttemp.put("bqjszy",bqjszy);
	reporttemp.put("brt999",brt999);
	reporttemp.put("sbrq",sbrq);
	reporttemp.put("brt996",brt996);
	reporttemp.put("cce001",cce001);
	if(xh.equals("1")){
		report1.putAll(reporttemp);
	}
	if(xh.equals("2")){
		report2.putAll(reporttemp);
	}
	if(xh.equals("3")){
		report3.putAll(reporttemp);
	}
	if(xh.equals("4")){
		report4.putAll(reporttemp);
	}
	if(xh.equals("5")){
		report5.putAll(reporttemp);
	}
	if(xh.equals("6")){
		report6.putAll(reporttemp);
	}
	if(xh.equals("7")){
		report7.putAll(reporttemp);
	}
	if(xh.equals("8")){
		report8.putAll(reporttemp);
	}
	
}
	String  todayStr = DateUtil.convertDateToYearMonthDay((Date)report1.get("sbrq")); //当前时间
	String yyyy = todayStr.substring(0,4);
	String mm = todayStr.substring(4,6);
	String dd = todayStr.substring(6,8);
	List buttons=new ArrayList();
	buttons.add(new Button("save", "保存","rec060207","saveinfo(document.forms[0])"));

	buttons.add(new Button("Btn_back", "返 回", "rec999997","go2Page(\"recommendation\",\"1\")"));
	buttons.add(new Button("Btn_close", "关 闭", "rec999999","closeWindow(\"Rec06Form\")"));
	
   pageContext.setAttribute("buttons",buttons);


%>
<lemis:body>
<lemis:base />
<lemis:title title="职业介绍工作情况"/>
  <table align="center">
<tr>
	<td colspan="3" class="style4">
	  职业介绍工作情况</td>
	</tr>
  <tr>
	<td colspan="3" class="style4">&nbsp;	  </td>
	</tr>
  </table>
 <table width="800"  border="0" align="center" class="toptable">
  <tr>
    <td width="505" ></td>
    <td width="62" >表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
    <td width="219"> <div align="left">劳社统就7号</div></td>
  </tr>
  <tr>
  <td></td>
    <td> 制表机关：</td>
    <td><div align="left">劳动和社会保障部</div></td>
  </tr>
  <tr>
  <td height="20"></td>
    <td>批准机关：</td>
    <td><div align="left">国家统计局</div></td>
  </tr>
  <tr>
  <td></td>
    <td>批准文号：</td>
    <td><div align="left">国统函【2003】232号</div></td>
  </tr>
  <tr>
  <td></td>
    <td>有效期止：</td>
    <td><div align="left">有效期止：2005年12月11日</div></td>
  </tr>
    <tr>
  <td></td>
    <td>&nbsp;</td>
    <td><div align="left"></div></td>
  </tr>
</table>
<html:form action="/Rec06Action.do?" method="POST">
 <table width="800"  border="0" align="center">
   <tr>
    
     <td width="344" ><div align="left">填报单位名称：<%=report1.get("cce001")%></div></td>
     <td width="61"> <div align="right"><%=report1.get("brt996")%>年 </div></td>
     <td width="381" ><div align="right">计量单位：个、人</div></td>
   </tr>
 </table>
 <table width="800"  border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse' bordercolor="#000000">
   <tr >
     <td rowspan="3" width="161"></td>
     <td rowspan="3" width="60"></td>
     <td rowspan="3" width="49">期末</br>职业</br>介绍</br>机构</br>个数
     </td>
     <td rowspan="3" width="45">期末</br>职业</br>介绍</br>机构</br>人数</td>
     <td rowspan="3" width="42" style="border-right:0px solid #ffffff;">本期</br>登记</br>招聘</br>人数</td>
     <td height="18" colspan="5" style="border-bottom:0px solid #ffffff;"></td>
     <td width="42" rowspan="3"  >本期</br>职业</br>指导</br>人数</td>
     <td rowspan="3" width="40" style="border-right:0px solid #ffffff;">本期</br>介绍</br>成功</br>人数</td>
     <td colspan="4" rowspan="2" style="border-left:0px solid #ffffff;" ></td>
   </tr>
   <tr >
     <td width="40" rowspan="2" style="border-top:0px solid #ffffff;">本期</br>登记</br>求职</br>人数</td>
     
   </tr>
   
   <tr>
     <td width="28" height="111" >女</br>性</td>
     <td width="28">下</br>岗</br>职</br>工</td>
     <td width="28">失</br>业</br>人</br>员</td>
     <td width="44">获得</br>职业</br>资格</br>人员</td>
     <td width="33" >女</br>性</td>
     <td width="32">下</br>岗</br>职</br>工</td>
     <td width="30">失</br>业</br>人</br>员</td>
     <td width="36">获得</br>职业</br>资格</br>人员</td>
   </tr>
   <tr>
     <td>甲</td>
     <td>序号</td>
     <td>1</td>
     <td>2</td>
     <td>3</td>
     <td>4</td>
     <td>5</td>
     <td>6</td>
     <td>7</td>
     <td>8</td>
     <td>9</td>
     <td>10</td>
     <td>11</td>
     <td>12</td>
     <td>13</td>
     <td>14</td>
   </tr>
   <tr>
     <td>总　计</td>
     <td>1</td>
  		<html:hidden property="brt999" value="<%=report1.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report1.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report1.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report1.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report1.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report1.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report1.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report1.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report1.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report1.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report1.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report1.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report1.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report1.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report1.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>
   <tr>
     <td><div align="left">劳动保障部门办</div></td>
     <td>2</td>
 		<html:hidden property="brt999" value="<%=report2.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report2.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report2.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report2.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report2.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report2.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report2.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report2.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report2.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report2.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report2.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report2.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report2.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report2.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report2.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>
   <tr>
     <td><div align="left">其中：县（区）及以上</div></td>
     <td>3</td>
     <html:hidden property="brt999" value="<%=report3.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report3.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report3.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report3.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report3.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report3.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report3.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report3.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report3.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report3.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report3.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report3.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report3.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report3.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report3.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>
   <tr>
     <td>街道</td>
     <td>4</td>
     <html:hidden property="brt999" value="<%=report4.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report4.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report4.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report4.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report4.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report4.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report4.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report4.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report4.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report4.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report4.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report4.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report4.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report4.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report4.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>
   <tr>
     <td>乡镇</td>
     <td>5</td>
     <html:hidden property="brt999" value="<%=report5.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report5.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report5.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report5.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report5.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report5.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report5.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report5.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report5.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report5.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report5.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report5.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report5.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report5.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report5.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   
   </tr>
   <tr>
     <td><div align="left">其他组织办</div></td>
     <td>6</td>
     <html:hidden property="brt999" value="<%=report6.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report6.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report6.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report6.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report6.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report6.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report6.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report6.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report6.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report6.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report6.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report6.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report6.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report6.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report6.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>
   <tr>
     <td><div align="left">公民个人办</div></td>
     <td>7</td>
     <html:hidden property="brt999" value="<%=report7.get("brt999").toString()%>"/>
     <td><html:text  size="3" property="brt001" value="<%=report7.get("qmjggs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt002" value="<%=report7.get("qmjgrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt003" value="<%=report7.get("bqzprs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt004" value="<%=report7.get("bqdjrs").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt005" value="<%=report7.get("bqdjnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt006" value="<%=report7.get("bqdjxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt007" value="<%=report7.get("bqdjsy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt008" value="<%=report7.get("bqdjzy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt009" value="<%=report7.get("bqzjzd").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt010" value="<%=report7.get("bqjscg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt011" value="<%=report7.get("bqjsnx").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt012" value="<%=report7.get("bqjsxg").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt013" value="<%=report7.get("bqjssy").toString()%>" onblur="totalcompute()"/></td>
     <td><html:text  size="3" property="brt014" value="<%=report7.get("bqjszy").toString()%>" onblur="totalcompute()"/></td>
   </tr>

 </table>

 <table width="800"  border="0" align="center">
 	   <tr>
   <td colspan="16">
   <html:hidden property="brt999" value="<%=report8.get("brt999").toString()%>"/>
   <html:hidden property="brt004" value="<%=report8.get("bqdjrs").toString()%>"/>
   <html:hidden property="brt005" value="<%=report8.get("bqdjnx").toString()%>"/>
   <html:hidden property="brt006" value="<%=report8.get("bqdjxg").toString()%>"/>
   <html:hidden property="brt007" value="<%=report8.get("bqdjsy").toString()%>"/>
   <html:hidden property="brt008" value="<%=report8.get("bqdjzy").toString()%>"/>
   <html:hidden property="brt009" value="<%=report8.get("bqzjzd").toString()%>"/>
   <html:hidden property="brt010" value="<%=report8.get("bqjscg").toString()%>"/>
   <html:hidden property="brt011" value="<%=report8.get("bqjsnx").toString()%>"/>
   <html:hidden property="brt012" value="<%=report8.get("bqjsxg").toString()%>"/>
   <html:hidden property="brt013" value="<%=report8.get("bqjssy").toString()%>"/>
   <html:hidden property="brt014" value="<%=report8.get("bqjszy").toString()%>"/>
   
     <p align="left">补充资料：县（区）及以上劳动保障部门办职业介绍机构中，财政全额拨款的<html:text  size="3" property="brt001" value="<%=report8.get("qmjggs").toString()%>"/>个，财政差额拨款的<html:text  size="3" property="brt002" value="<%=report8.get("qmjgrs").toString()%>"/>个，自收自支的<html:text  size="3" property="brt003" value="<%=report8.get("bqzprs").toString()%>"/>个</p>
     <p>&nbsp;</p></td>

   </tr>
    <tr>
      <td><div align="left">单位负责人签章：</div></td>
      <td>处（科）负责人签章：</td>
      <td>制表人签章：</td>
      <td><div align="right">报出日期：<%=yyyy %> 年 <%=mm %>月 <%=dd %>日 </div></td>
    </tr>
  </table>
<lemis:buttons buttonMeta="buttons" />
</html:form>

  </lemis:body>
</html>
<script>
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
       var temp1=0;
       var temp2=0;
       var temp3=0;
       var temp4=0;
       var temp5=0;
       var temp6=0;
       var temp7=0;
       var temp8=0;
       var temp9=0;
       var temp10=0;
       var temp11=0;
       var temp12=0;
       var temp13=0;
       var temp14=0;
       
       for (i=1;i<length-1;i++){
           temp1+=parseInt(brt001[i].value);
           temp2+=parseInt(brt002[i].value);
           temp3+=parseInt(brt003[i].value);
           temp4+=parseInt(brt004[i].value);
           temp5+=parseInt(brt005[i].value);
           temp6+=parseInt(brt006[i].value);
           temp7+=parseInt(brt007[i].value);
           temp8+=parseInt(brt008[i].value);
           temp9+=parseInt(brt009[i].value);
           temp10+=parseInt(brt010[i].value);
           temp11+=parseInt(brt011[i].value);
           temp12+=parseInt(brt012[i].value);
           temp13+=parseInt(brt013[i].value);
           temp14+=parseInt(brt014[i].value);
           
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
   	
   	function saveinfo(obj){
		
		if (!checkValue(obj)) {
			return false;
		}

		obj.action = '<html:rewrite page="/Rec06Action.do?method=save&"/>'+getAlldata(obj);		
		obj.submit();
	}
</script>

