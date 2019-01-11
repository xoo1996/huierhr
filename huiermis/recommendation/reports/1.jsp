<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="org.radf.plat.commons.DBUtil" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<link href='../../css/print.css' rel='stylesheet' type='text/css'>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<body onload='print();'>
<p>

  <OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WB width=0></object> <OBJECT id=factory style="DISPLAY:  none"
		codeBase=http: //<%=request.getServerName()%> :
		<%=request.getServerPort()+request.getContextPath()%>/scriptx/ScriptX.cab#Version=5,60,0,360
		classid=clsid:1663ed61-23eb-11d2-b92f-008048fdd814 viewastext>
		</OBJECT>
  
 <%
 LoginDTO loginForm = (LoginDTO) request.getSession().getAttribute(
        "LoginDTO");
        String aae011=loginForm.getBsc010();
 DecimalFormat df = new DecimalFormat("0.##");
int season=Integer.parseInt(request.getParameter("season"));
String year=request.getParameter("year");
String a021=request.getParameter("a021");
String sfbh=request.getParameter("sfbh");
String date1 = "";
String date2 = "";
System.out.println(a021);
int total1=0;//需求的人数
int total2=0;//求职的人数
double d1=0;//求人倍率
date1 = year+'-'+String.valueOf((season-1)*3+1);//季度开始的年月
if(season==1||season==2||season==3){
	date2 = year+'-'+String.valueOf((season-1)*3+4);//季度结束的年月
}
else{
	date2 = String.valueOf(Integer.parseInt(year)+1)+'-'+String.valueOf((season-4)*3+1);//季度结束的年月
}
System.out.println(date1);
System.out.println(date2);
//需求人数的查询
String sqlsum1="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b where a.acb200=b.acb200  "+
					  " and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm')";
//求职人数的查询
String sqlsum2="select nvl(count(*),0) from ac01 a,cc20 b where a.aac001=b.aac001"+
		              " and b.aae043<to_date('"+date2+"','yyyy-mm') and b.aae043>=to_date('"+date1+"','yyyy-mm')";
PreparedStatement preparedstatement = null;
ResultSet resultset = null;
Connection connection = null;
if(a021!=null&&!a021.equals("")){
	sqlsum1+=" and f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1' ";
	sqlsum2+=" and f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1'";
}
System.out.println(sqlsum1);
System.out.println(sqlsum2);
try
{ 
  connection =DBUtil.getConnection();
  preparedstatement = connection.prepareStatement(sqlsum1);
  resultset = preparedstatement.executeQuery();

  resultset.next();
 
  total1=resultset.getInt(1);

  preparedstatement = connection.prepareStatement(sqlsum2);
  resultset = preparedstatement.executeQuery();
  resultset.next();
 
  total2=resultset.getInt(1);

  if(total2==0){
  d1=0;
  }
  else{
  d1=(double)total1/total2;
  }
%>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <table width="620" border="0">
    <tr>
      <td width="620" align="center" colspan="2"><span class="title">表1：供求总体人数</span></td>
    </tr>
    <tr>
      <td height="30" align="left" nowrap><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="620" height="230" border="0" cellpadding="0" cellspacing="0"  class=table>
    <tr>
      <td colspan="2" rowspan="2" class="td">&nbsp;</td>
      <td width="114" align="center" class="td">需求人数</td>
      <td width="115" align="center" class="td">求职人数</td>
      <td width="116" align="center" class="td">求人倍率</td>
    </tr>
    <tr>
      <td height="25" align="center" class="td">人</td>
      <td align="center" class="td">人</td>
      <td align="center" class="td">倍</td>
    </tr>
    <tr>
      <td width="236" align="center" class="td">甲</td>
      <td width="95" align="center" class="td">序号</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td">2</td>
      <td align="center" class="td">3</td>
    </tr>
    <tr>
      <td class="td"><div align="center">本期有效数</div></td>
      <td align="center" class="td">1</td>
      <td align="center" class="td"><%=total1%></td>
      <td align="center" class="td"><%=total2%></td>
      <td align="center" class="td"><%=df.format(d1)%></td>
    </tr>
  </table>

<p class="style1">计算公式：求人倍率＝需求人数/求职人数。</p>
</div> 
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
  <script>
function print()
{ 
SetPrintSettings();
try{
WB.ExecWB(6,6);
}catch(e){}
//window.close();
//history.back();
}
 
       function SetPrintSettings() {  
              // -- advanced features  ，未曾使用过，有待确认。
             // factory.printing.SetMarginMeasure(2)    // measure margins in inches  
              //factory.SetPageRange(false, 1, 3)          // need pages from 1 to 3  
             // factory.printing.printer = "HP DeskJet 660C"  
             // factory.printing.copies = 2  
             // factory.printing.collate = true  
             // factory.printing.paperSize = "A4"  
             // factory.printing.paperSource = "Manual feed"  

              // -- basic features  使用过
             // factory.printing.header = "This is MeadCo"  
             // factory.printing.footer = "Advanced Printing by ScriptX"  
              factory.printing.portrait = false     //方向，true
              factory.printing.leftMargin = 1.0  
              factory.printing.topMargin = 1.0  
              factory.printing.rightMargin = 1.0  
              factory.printing.bottomMargin = 1.0 
     
           
       }  
    </script>

</html>

