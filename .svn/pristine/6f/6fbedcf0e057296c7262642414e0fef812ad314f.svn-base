<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
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
DecimalFormat df1 = new DecimalFormat("0");
List list = new ArrayList();
String year = request.getParameter("year");
int season=Integer.parseInt(request.getParameter("season"));
String a021=request.getParameter("a021");
String sfbh=request.getParameter("sfbh");
String date1 = "";
String date2 = "";

int total1=0;//总求职人数
int qy=0;//企业的人数
int lzqy=0;//内资企业
int qt1=0;//
int qt2=0;
date1 = year+'-'+String.valueOf((season-1)*3+1);//季度开始的年月
if(season==1||season==2||season==3){
	date2 = year+'-'+String.valueOf((season-1)*3+4);//季度结束的年月
}
else{
	date2 = String.valueOf(Integer.parseInt(year)+1)+'-'+String.valueOf((season-4)*3+1);//季度结束的年月
}
PreparedStatement preparedstatement = null;
ResultSet rs = null;
Connection connection = null;
int[] nd1=new int[16];
String bm[]={"10","100","110","120","130","140","150","160","170","900","200","300","175","20","30","90",};

try{
	connection =DBUtil.getConnection();

for(int i=0;i<16;i++){

String sql1 ="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 c where  b.aab001=c.aab001 and a.acb200=b.acb200 and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
if(a021!=null&&!a021.equals("")){
			sql1+=" and   f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1'";
		}
if(i>=2&&i<=12)sql1+=" and c.aab020='"+bm[i]+"'";
if(i==13||i==14) sql1+=" and c.aab020 is null and c.aab019 ='"+bm[i]+"'";
if(i==15) sql1+=" and c.aab020 is null and c.aab019 in('40','50','60','70','90')";
	preparedstatement = connection.prepareStatement(sql1);
	rs = preparedstatement.executeQuery();
	rs.next();
if(i>=2&&i<=12){
	if (i>=2&&i<=9){
	lzqy=lzqy+rs.getInt(1);
	nd1[i]= rs.getInt(1);
	}else{
	nd1[i]= rs.getInt(1);	
	qt1=qt1+rs.getInt(1);
	}
}
else{
	nd1[i]= rs.getInt(1);	
}
}
qy=qy+qt1+lzqy;
total1=rs.getInt(1)+total1+qy;
%>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <table width="560" border="0">
    <tr>
      <td align="center" colspan="2"><span class="title">表4：按用人单位性质划分的需求人数</span></td>
    </tr>
    <tr>
      <td height="30"><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="560" height="514" border="0" cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td colspan="2" rowspan="2" class="td">&nbsp;</td>
      <td width="132" align="center" class="td">需求人数</td>
      <td width="122" align="center" class="td">需求比重</td>
    </tr>
    <tr>
      <td align="center" class="td">人</td>
      <td align="center" class="td">％</td>
    </tr>
    <tr>
      <td width="255" align="center" class="td">甲</td>
      <td width="49" align="center" class="td">序号</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td">2</td>
    </tr>
    <tr>
      <td class="td">企业</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td"><%=qy%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(qy/total1*100))%></td>
    </tr>
    <tr class="td">
      <td class="td">其中：内资企业</td>
      <td align="center" class="td">2</td>
     <td align="center" class="td"><%=lzqy%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(lzqy/total1*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">国有企业</div></td>
      <td align="center" class="td"><div align="center">3</div></td>
      <td align="center" class="td"><%=nd1[2]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[2]/qy*100))%></td>
    </tr>
    <tr>
      <td height="37" class="td"><div align="center">集体企业</div></td>
      <td align="center" class="td"><div align="center">4</div></td>
     <td align="center" class="td"><%=nd1[3]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[3]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;股份合作企业</div></td>
      <td align="center" class="td"><div align="center">5</div></td>
      <td align="center" class="td"><%=nd1[4]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[4]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">联营企业</div></td>
      <td align="center" class="td"><div align="center">6</div></td>
   <td align="center" class="td"><%=nd1[5]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[5]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;有限责任公司</div></td>
      <td align="center" class="td"><div align="center">7</div></td>
      <td align="center" class="td"><%=nd1[6]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[6]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;股份有限公司</div></td>
      <td align="center" class="td"><div align="center">8</div></td>
    <td align="center" class="td"><%=nd1[7]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[7]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">私营企业</div></td>
      <td align="center" class="td"><div align="center">9</div></td>
   <td align="center" class="td"><%=nd1[8]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[8]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="center">其他企业</div></td>
      <td align="center" class="td"><div align="center">10</div></td>
  <td align="center" class="td"><%=nd1[9]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[9]/qy*100))%></td>
    </tr>
    <tr class="td">
      <td class="td"><div align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;港，澳，台商投资企业</div></td>
      <td align="center" class="td"><div align="center">11</div></td>
     <td align="center" class="td"><%=nd1[10]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[10]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"> <div align="left">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外商企业</div></td>
      <td align="center" class="td"><div align="center">12</div></td>
      <td align="center" class="td"><%=nd1[11]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[11]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个体经营</td>
      <td align="center" class="td">13</td>
     <td align="center" class="td"><%=nd1[12]%></td>
      <td align="center" class="td"><%=(String.valueOf(qy).equals("0.0")?"0":df.format(nd1[12]/qy*100))%></td>
    </tr>
    <tr>
      <td class="td">事业</td>
      <td align="center" class="td">14</td>
      <td align="center" class="td"><%=nd1[13]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[13]/total1*100))%></td>
    </tr>
    <tr>
      <td class="td">机关</td>
      <td align="center" class="td">15</td>
     <td align="center" class="td"><%=nd1[14]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[14]/total1*100))%></td>
    </tr>
    <tr>
      <td class="td">其他</td>
      <td align="center" class="td">16</td>
      <td align="center" class="td"><%=nd1[15]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[15]/total1*100))%></td>
    </tr>
    <tr>
      <td class="td">合计</td>
      <td align="center" class="td">17</td>
      <td align="center" class="td"><%=df1.format(total1)%></td>
      <td align="center" class="td">100.0</td>
    </tr>
  </table>
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
         if(rs!=null)
             rs.close();
         if(preparedstatement!= null)
             preparedstatement.close();
         if(connection != null)
            connection.close();
     }
     catch(java.lang.Exception exception2) {exception2.printStackTrace(); }
 }
%>
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
</body>
</html>

