<!--lemis/statistics/zjgqseason/1p.jsp-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="org.radf.plat.commons.DBUtil" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
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
 //LoginDTO loginForm = (LoginDTO) request.getSession().getAttribute(
//        "LoginDTO");
//        String aae011=loginForm.getBsc010();
// DecimalFormat df = new DecimalFormat("0.##");
String year=request.getParameter("year");
String bn=request.getParameter("bn");
String date1 = "";
String date2 = "";
int bqzprs=0;//本期招聘人数
int bqdjrs=0;//本期登记人数
int bqdjnx=0;//本期登记女性
int bqdjxg=0;//下岗
int bqdjsy=0;//失业
int bqdjzy=0;//职业资格
int bqzjzd=0;//本期职业指导人数
int bqjscg=0;//本期介绍成功人数
int bqjsnx=0;//本期介绍成功人数(女性)
int bqjsxg=0;//本期介绍成功人数(下岗)
int bqjssy=0;//本期介绍成功人数(失业)
int bqjszy=0;//本期介绍成功人数(职业资格)

	date1 = year+'-'+"01";//开始的年月
	date2 = year+'-'+"12";//结束的年月

System.out.println(date1);
System.out.println(date2);
//本招聘人数查询
String sql_bqzprs="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b "
					+"where a.acb200=b.acb200  "+
					" and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm')";

//本期登记人数
String sql_bqdjrs="select count(*) from cc20, ac01  where cc20.aac001 = ac01.aac001"
+" and cc20.aae043<to_date('"+date2+"','yyyy-mm') and cc20.aae043>=to_date('"+date1+"','yyyy-mm')";

//本期登记人数(女性)
String sql_bqdjnx=sql_bqdjrs+" and ac01.aac004='2'";

//本期登记人数(下岗)
String sql_bqdjxg=sql_bqdjrs+" and cc20.bac299 = '6'";

//本期登记人数(失业)
String sql_bqdjsy=sql_bqdjrs+" and (cc20.bac299='1' or cc20.bac299='3' or cc20.bac299='4')";

//本期登记人数(职业资格)
String sql_bqdjzy=sql_bqdjrs+" and ac01.acc02i is not null";

//本期职业指导人数
String sql_bqzjzd="select count(*) from cc23 where cc23.acc231<to_date('"+date2+"','yyyy-mm') and cc23.acc231>=to_date('"+date1+"','yyyy-mm')";

//本期介绍成功人数
String sql_bqjscg="select count(*) from ac01, cc20, cc22 where ac01.aac001 = cc20.aac001 and cc20.acc200 = cc22.acc200 and cc22.acc223 = '1'"
+" and cc20.aae043<to_date('"+date2+"','yyyy-mm') and cc20.aae043>=to_date('"+date1+"','yyyy-mm')";
//本期介绍成功人数(女性)
String sql_bqjsnx=sql_bqjscg+" and ac01.aac004='2'";

//本期介绍成功人数(下岗)
String sql_bqjsxg=sql_bqjscg+" and cc20.bac299 = '6'";

//本期介绍成功人数(失业)
String sql_bqjssy=sql_bqjscg+" and (cc20.bac299='1' or cc20.bac299='3' or cc20.bac299='4')";

//本期介绍成功人数(职业资格)
String sql_bqjszy=sql_bqjscg+" and ac01.acc02i is not null";

PreparedStatement preparedstatement = null;
ResultSet resultset = null;
Connection connection = null;

//System.out.println(sql_bqzprs);
//System.out.println(sql_bqdjrs);
System.out.println(sql_bqdjnx);
try
{ 
  connection =DBUtil.getConnection();
  //本期登记招聘人数
  preparedstatement = connection.prepareStatement(sql_bqzprs);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqzprs=resultset.getInt(1);
  //本期登记求职人数
  preparedstatement = connection.prepareStatement(sql_bqdjrs);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqdjrs=resultset.getInt(1);
  
   //本期登记求职人数(女性)
  preparedstatement = connection.prepareStatement(sql_bqdjnx);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqdjnx=resultset.getInt(1);
  
  //本期登记求职人数(下岗)
  preparedstatement = connection.prepareStatement(sql_bqdjxg);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqdjxg=resultset.getInt(1);
  
  //本期登记求职人数(失业)
  preparedstatement = connection.prepareStatement(sql_bqdjsy);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqdjsy=resultset.getInt(1);
  
  //本期登记求职人数(职业)
  preparedstatement = connection.prepareStatement(sql_bqdjzy);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqdjzy=resultset.getInt(1);
  
  //本期职业指导人数
  preparedstatement = connection.prepareStatement(sql_bqzjzd);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqzjzd=resultset.getInt(1);
  
  //本期介绍成功人数
  preparedstatement = connection.prepareStatement(sql_bqjscg);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqjscg=resultset.getInt(1);
  
     //本期介绍成功人数(女性)
  preparedstatement = connection.prepareStatement(sql_bqjsnx);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqjsnx=resultset.getInt(1);
  
  //本期介绍成功人数(下岗)
  preparedstatement = connection.prepareStatement(sql_bqjsxg);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqjsxg=resultset.getInt(1);
  
  //本期介绍成功人数(失业)
  preparedstatement = connection.prepareStatement(sql_bqjssy);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqjssy=resultset.getInt(1);
  
  //本期介绍成功人数(职业)
  preparedstatement = connection.prepareStatement(sql_bqjszy);
  resultset = preparedstatement.executeQuery();
  resultset.next();
  bqjszy=resultset.getInt(1);


%>
	<body onload='print();'>
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
 <table width="800"  border="0" align="center">
   <tr>
    
     <td width="344" ><div align="left">填报单位名称：</div></td>
     <td width="61"> <div align="right">年 </div></td>
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
     <td></td>
     <td></td>
     <td><%=bqzprs%></td>
     <td><%=bqdjrs%></td>
     <td><%=bqdjnx%></td>
     <td><%=bqdjxg%></td>
     <td><%=bqdjsy%></td>
     <td><%=bqdjzy%></td>
     <td><%=bqzjzd%></td>
     <td><%=bqjscg%></td>
     <td><%=bqjsnx%></td>
     <td><%=bqjsxg%></td>
     <td><%=bqjssy%></td>
     <td><%=bqjszy%></td>
   </tr>
   <tr>
     <td><div align="left">劳动保障部门办</div></td>
     <td>2</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td><div align="left">其中：县（区）及以上</div></td>
     <td>3</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td>街道</td>
     <td>4</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td>乡镇</td>
     <td>5</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td><div align="left">其他组织办</div></td>
     <td>6</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td><div align="left">公民个人办</div></td>
     <td>7</td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
     <td></td>
   </tr>

 </table>

 <table width="800"  border="0" align="center">
 	   <tr>
   <td colspan="16">
     <p align="left">补充资料：县（区）及以上劳动保障部门办职业介绍机构中，财政全额拨款的___个，财政差额拨款的___个，自收自支的___个</p>
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
  	  <%
}
catch(Exception e)
{
  e.printStackTrace();
}
finally
 {
     try
     {
         if(resultset != null)
             resultset.close();
         if(preparedstatement != null)
             preparedstatement.close();
         if(connection != null)
            connection.close();
     }
     catch(java.lang.Exception exception2) { }
 }
%> 
  </body>
</html>
<script>
	function print()
	{ 
	SetPrintSettings();
	try{WB.ExecWB(6,6);}catch(e){}
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

