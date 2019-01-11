<!--/recommendation/statistic/printreport.jsp-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ page import="org.radf.apps.commons.entity.Rt99" %>
<%@ page import="org.radf.plat.commons.ClassHelper" %>

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
  
<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WB width=0></object> <OBJECT id=factory style="DISPLAY:  none"
		codeBase=http: //<%=request.getServerName()%> :
		<%=request.getServerPort()+request.getContextPath()%>/scriptx/ScriptX.cab#Version=5,60,0,360
		classid=clsid:1663ed61-23eb-11d2-b92f-008048fdd814 viewastext> </OBJECT>
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
	
	java.util.Date sbrq=rt99.getAae036();//上报日期
	String brt996=rt99.getBrt996();//统计时间
	String cce001=rt99.getCce001();//填报单位
	String brt048 = rt99.getBrt048();//填报单位
	
	
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
	reporttemp.put("brt048", brt048);

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
	String  todayStr = DateUtil.convertDateToYearMonthDay((java.util.Date)report1.get("sbrq")); //当前时间
	String yyyy = todayStr.substring(0,4);
	String mm = todayStr.substring(4,6);
	String dd = todayStr.substring(6,8);

%>
	<body >
  <table align="center">
    <tr>
	<td colspan="3" class="style4">&nbsp;	  </td>
	</tr>
	  <tr>
	<td colspan="3" class="style4">&nbsp;	  </td>
	</tr>
	  <tr>
	<td colspan="3" class="style4">&nbsp;	  </td>
	</tr>  <tr>
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
    <td width="62" ><div align="left">表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</div></td>
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
    <td><div align="left">2005年12月11日</div></td>
  </tr>
    <tr>
  <td></td>
    <td>&nbsp;</td>
    <td><div align="left"></div></td>
  </tr>
</table>
 <table width="800"  border="0" align="center">
   <tr>
    
     <td width="344" ><div align="left">填报单位名称：<%=report1.get("brt048")%></div></td>
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
     <td><%=report1.get("qmjggs").toString()%></td>
     <td><%=report1.get("qmjgrs").toString()%></td>
     <td><%=report1.get("bqzprs").toString()%></td>
     <td><%=report1.get("bqdjrs").toString()%></td>
     <td><%=report1.get("bqdjnx").toString()%></td>
     <td><%=report1.get("bqdjxg").toString()%></td>
     <td><%=report1.get("bqdjsy").toString()%></td>
     <td><%=report1.get("bqdjzy").toString()%></td>
     <td><%=report1.get("bqzjzd").toString()%></td>
     <td><%=report1.get("bqjscg").toString()%></td>
     <td><%=report1.get("bqjsnx").toString()%></td>
     <td><%=report1.get("bqjsxg").toString()%></td>
     <td><%=report1.get("bqjssy").toString()%></td>
     <td><%=report1.get("bqjszy").toString()%></td>
   </tr>
   <tr>
     <td><div align="left">劳动保障部门办</div></td>
     <td>2</td>
     <td><%=report2.get("qmjggs").toString()%></td>  
     <td><%=report2.get("qmjgrs").toString()%></td>  
     <td><%=report2.get("bqzprs").toString()%></td>  
     <td><%=report2.get("bqdjrs").toString()%></td>  
     <td><%=report2.get("bqdjnx").toString()%></td>  
     <td><%=report2.get("bqdjxg").toString()%></td>  
     <td><%=report2.get("bqdjsy").toString()%></td>  
     <td><%=report2.get("bqdjzy").toString()%></td>  
     <td><%=report2.get("bqzjzd").toString()%></td>  
     <td><%=report2.get("bqjscg").toString()%></td>  
     <td><%=report2.get("bqjsnx").toString()%></td>  
     <td><%=report2.get("bqjsxg").toString()%></td>  
     <td><%=report2.get("bqjssy").toString()%></td>  
     <td><%=report2.get("bqjszy").toString()%></td>  

   </tr>
   <tr>
     <td><div align="left">其中：县（区）及以上</div></td>
     <td>3</td>
     <td><%=report3.get("qmjggs").toString()%></td>
     <td><%=report3.get("qmjgrs").toString()%></td>
     <td><%=report3.get("bqzprs").toString()%></td>
     <td><%=report3.get("bqdjrs").toString()%></td>
     <td><%=report3.get("bqdjnx").toString()%></td>
     <td><%=report3.get("bqdjxg").toString()%></td>
     <td><%=report3.get("bqdjsy").toString()%></td>
     <td><%=report3.get("bqdjzy").toString()%></td>
     <td><%=report3.get("bqzjzd").toString()%></td>
     <td><%=report3.get("bqjscg").toString()%></td>
     <td><%=report3.get("bqjsnx").toString()%></td>
     <td><%=report3.get("bqjsxg").toString()%></td>
     <td><%=report3.get("bqjssy").toString()%></td>
     <td><%=report3.get("bqjszy").toString()%></td>

   </tr>
   <tr>
     <td>街道</td>
     <td>4</td>
     <td><%=report4.get("qmjggs").toString()%></td>
     <td><%=report4.get("qmjgrs").toString()%></td>  
     <td><%=report4.get("bqzprs").toString()%></td>  
     <td><%=report4.get("bqdjrs").toString()%></td>  
     <td><%=report4.get("bqdjnx").toString()%></td>  
     <td><%=report4.get("bqdjxg").toString()%></td>  
     <td><%=report4.get("bqdjsy").toString()%></td>  
     <td><%=report4.get("bqdjzy").toString()%></td>  
     <td><%=report4.get("bqzjzd").toString()%></td>  
     <td><%=report4.get("bqjscg").toString()%></td>  
     <td><%=report4.get("bqjsnx").toString()%></td>  
     <td><%=report4.get("bqjsxg").toString()%></td>  
     <td><%=report4.get("bqjssy").toString()%></td>  
     <td>0</td>  

   </tr>
   <tr>
     <td>乡镇</td>
     <td>5</td>
     <td><%=report5.get("qmjggs").toString()%></td>
     <td><%=report5.get("qmjgrs").toString()%></td>  
     <td><%=report5.get("bqzprs").toString()%></td>  
     <td><%=report5.get("bqdjrs").toString()%></td>  
     <td><%=report5.get("bqdjnx").toString()%></td>  
     <td><%=report5.get("bqdjxg").toString()%></td>  
     <td><%=report5.get("bqdjsy").toString()%></td>  
     <td><%=report5.get("bqdjzy").toString()%></td>  
     <td><%=report5.get("bqzjzd").toString()%></td>  
     <td><%=report5.get("bqjscg").toString()%></td>  
     <td><%=report5.get("bqjsnx").toString()%></td>  
     <td><%=report5.get("bqjsxg").toString()%></td>  
     <td><%=report5.get("bqjssy").toString()%></td>  
     <td><%=report5.get("bqjszy").toString()%></td>  

   </tr>
   <tr>
     <td><div align="left">其他组织办</div></td>
     <td>6</td>
     <td><%=report6.get("qmjggs").toString()%></td>
     <td><%=report6.get("qmjgrs").toString()%></td>     
     <td><%=report6.get("bqzprs").toString()%></td>     
     <td><%=report6.get("bqdjrs").toString()%></td>     
     <td><%=report6.get("bqdjnx").toString()%></td>     
     <td><%=report6.get("bqdjxg").toString()%></td>     
     <td><%=report6.get("bqdjsy").toString()%></td>     
     <td><%=report6.get("bqdjzy").toString()%></td>     
     <td><%=report6.get("bqzjzd").toString()%></td>     
     <td><%=report6.get("bqjscg").toString()%></td>     
     <td><%=report6.get("bqjsnx").toString()%></td>     
     <td><%=report6.get("bqjsxg").toString()%></td>     
     <td><%=report6.get("bqjssy").toString()%></td>     
     <td><%=report6.get("bqjszy").toString()%></td>     

   </tr>
   <tr>
     <td><div align="left">公民个人办</div></td>
     <td>7</td>
     <td><%=report7.get("qmjggs").toString()%></td>
     <td><%=report7.get("qmjgrs").toString()%></td>
	<td><%=report7.get("bqzprs").toString()%></td>
	<td><%=report7.get("bqdjrs").toString()%></td>
	<td><%=report7.get("bqdjnx").toString()%></td>
	<td><%=report7.get("bqdjxg").toString()%></td>
	<td><%=report7.get("bqdjsy").toString()%></td>
	<td><%=report7.get("bqdjzy").toString()%></td>
	<td><%=report7.get("bqzjzd").toString()%></td>
	<td><%=report7.get("bqjscg").toString()%></td>
	<td><%=report7.get("bqjsnx").toString()%></td>
	<td><%=report7.get("bqjsxg").toString()%></td>
	<td><%=report7.get("bqjssy").toString()%></td>
	<td><%=report7.get("bqjszy").toString()%></td>

   </tr>

 </table>

 <table width="800"  border="0" align="center">
 	   <tr>
   <td colspan="16">
     <p align="left">补充资料：县（区）及以上劳动保障部门办职业介绍机构中，财政全额拨款的<%=report8.get("qmjggs").toString()%>个，财政差额拨款的<%=report8.get("qmjgrs").toString()%>个，自收自支的<%=report8.get("bqzprs").toString()%>个</p>
     <p>&nbsp;</p></td>

   </tr>
    <tr>
      <td><div align="left">单位负责人签章：</div></td>
      <td>处（科）负责人签章：</td>
      <td>制表人签章：</td>
      <td><div align="right">报出日期： 年 月 日 </div></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <div align="center" id="ppp"><input type="button" value="打 印" onclick="print()"></div>	 
  </body>
</html>
<script>
function print()
{ 
document.getElementById('ppp').innerHTML=''; 
SetPrintSettings();
try{
WB.ExecWB(6,6);
}catch(e){}
//window.close();
//history.back();
}
 
   function SetPrintSettings() {  
          
          factory.printing.portrait = false     //方向，true
          factory.printing.leftMargin = 2.0  
          factory.printing.topMargin = 2.0  
          factory.printing.rightMargin = 2.0  
          factory.printing.bottomMargin = 1.0 
          factory.printing.header = ""
          factory.printing.footer = ""
 
       
   }  
</script>

