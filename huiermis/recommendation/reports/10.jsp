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
<!-- 年龄按照最大年龄段来计算 -->
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
double total=0;//总需求人数
double total1=0;//总需求人数
int empty=0;
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
int[] nd2=new int[4];
int[] nd1=new int[4];
try{
	connection =DBUtil.getConnection();
int bm[]={16,25,35,46};
int bn[]={24,34,45,1000};


for(int i=0;i<4;i++){
String sql1 = "select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 d  where a.acb200=b.acb200 and a.aab001=d.aab001 "+
					  "  and  b.acb222>="+bm[i]+ "and b.acb222<="+bn[i]+" and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm')";
if(a021!=null&&!a021.equals("")){
			
			sql1+=" and  f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1' ";
		}

preparedstatement = connection.prepareStatement(sql1);
rs = preparedstatement.executeQuery();
rs.next();
nd2[i]= rs.getInt(1);
total=rs.getInt(1)+total;



String sql2 = "select count(*) from ac01 a,cc20 b where a.aac001=b.aac001 and ( '"+year+"' -to_char(a.aac006,'yyyy'))>="+bm[i]+"  and  ( '"+year+"' -to_char(a.aac006,'yyyy'))<="+bn[i]+" and b.aae043<to_date('"+date2+"','yyyy-mm') and b.aae043>=to_date('"+date1+"','yyyy-mm')";

if(a021!=null&&!a021.equals("")){
			
			sql1+=" and f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1' ";
		}



preparedstatement = connection.prepareStatement(sql2);
rs = preparedstatement.executeQuery();

rs.next();
nd1[i]= rs.getInt(1);
total1=rs.getInt(1)+total1;
}
String sql3="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 d  where a.acb200=b.acb200 and a.aab001=d.aab001 "+
					  "  and  (b.acb221 is null or b.acb222 is null or b.acb222<'16')and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
preparedstatement = connection.prepareStatement(sql3);
rs = preparedstatement.executeQuery();
rs.next();
empty=rs.getInt(1);
total=empty+total;
%>
</p>
<p>&nbsp;</p>
<div align="center">
  <table width="905" border="0">
    <tr>
      <td align="center" colspan="2"><span class="title">表10：按年龄分组的供求人数</span></td>
    </tr>
    <tr>
      <td height="30"><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="905" height="327" border="0" cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td colspan="2" rowspan="3" class="td">&nbsp;</td>
      <td colspan="5" align="center" class="td">劳动力供求人数比较</td>
    </tr>
    <tr>
      <td width="114" align="center" class="td">需求人数</td>
      <td width="115" align="center" class="td">需求比重</td>
      <td width="116" align="center" class="td">求职人数</td>
      <td width="112" align="center" class="td">求职比重</td>
      <td width="115" align="center" class="td">求人倍率</td>
    </tr>
    <tr>
      <td align="center" class="td">人</td>
      <td align="center" class="td">％</td>
      <td align="center" class="td">人</td>
      <td align="center" class="td">％</td>
      <td align="center" class="td">倍</td>
    </tr>
    <tr>
      <td width="236" align="center" class="td">甲</td>
      <td width="95" align="center" class="td">序号</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td">2</td>
      <td align="center" class="td">3</td>
      <td align="center" class="td">4</td>
      <td align="center" class="td">5</td>
    </tr>
    <tr>
      <td class="td">16－24岁</td>
      <td align="center" class="td">1</td>
        <td align="center" class="td"><%=nd2[0]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[0]/total*100))%></td>
      <td align="center" class="td"><%=nd1[0]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[0]/total1*100))%></td>
      <td align="center" class="td"><%=(String.valueOf(nd1[0]).equals("0")?"0":df.format(nd2[0]/nd1[0]))%></td>
    </tr>
    <tr class="td">
      <td class="td">25－34岁</td>
      <td align="center" class="td">2</td>
        <td align="center" class="td"><%=nd2[1]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[1]/total*100))%></td>
      <td align="center" class="td"><%=nd1[1]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[1]/total1*100))%></td>
      <td align="center" class="td"><%=(String.valueOf(nd1[0]).equals("0")?"0":df.format(nd2[1]/nd1[1]))%></td>
    </tr>
    <tr>
      <td class="td">35－44岁</td>
      <td align="center" class="td">3</td>
        <td align="center" class="td"><%=nd2[2]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[2]/total*100))%></td>
      <td align="center" class="td"><%=nd1[2]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[2]/total1*100))%></td>
      <td align="center" class="td"><%=(String.valueOf(nd1[2]).equals("0")?"0":df.format(nd2[2]/nd1[2]))%></td>
    </tr>
    <tr>
      <td class="td">45岁以上</td>
      <td align="center" class="td">4</td>
       <td align="center" class="td"><%=nd2[3]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[3]/total*100))%></td>
      <td align="center" class="td"><%=nd1[3]%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[3]/total1*100))%></td>
      <td align="center" class="td"><%=(String.valueOf(nd1[3]).equals("0")?"0":df.format(nd2[3]/nd1[3]))%></td>
    </tr>
    <tr>
      <td class="td">无需求</td>
      <td align="center" class="td">5</td>
      <td align="center" class="td"><%=empty%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(empty/total*100))%></td>
      <td align="center" class="td">/</td>
      <td align="center" class="td">/</td>
      <td align="center" class="td">/</td>
    </tr>
    <tr>
      <td class="td">合计</td>
      <td align="center" class="td">6</td>
      <td align="center" class="td"><%=df1.format(total)%></td>
      <td align="center" class="td">100.00</td>
      <td align="center" class="td"><%=df1.format(total1)%></td>
      <td align="center" class="td">100.00</td>
      <td align="center" class="td"><%=String.valueOf(total1).equals("0.0")?"0":df.format(total/total1)%></td>
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
try{WB.ExecWB(6,6);}catch(e){}
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

