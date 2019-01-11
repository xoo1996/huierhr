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

double total1=0;//总求职人数
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
double[] nd1=new double[10];

try{
  connection =DBUtil.getConnection();
String bm[]={"1","2","3","4","5","6","7","8","9","10"};

for(int i=0;i<10;i++){
String sql2 = "select count(*) from ac01 a,cc20 b where a.aac001=b.aac001 and  b.bac299="+bm[i]+ " and b.aae043<to_date('"+date2+"','yyyy-mm') and b.aae043>=to_date('"+date1+"','yyyy-mm') ";
if(a021!=null&&!a021.equals("")){
			
			sql2+=" and f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1' ";
}
System.out.println(sql2);
preparedstatement = connection.prepareStatement(sql2);
rs = preparedstatement.executeQuery();
rs.next();
nd1[i]= rs.getDouble(1);
if(i==1){
	nd1[0]=nd1[1]+nd1[0];
	
}

total1=nd1[i]+total1;
System.out.println(nd1[i]);
}
System.out.println(nd1[0]);
System.out.println(nd1[1]);
%>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <table width="560" border="0">
    <tr>
      <td align="center" colspan="2"><span class="title">表8：按求职人员类别分组的求职人数</span></td>
    </tr>
    <tr>
      <td height="30"><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="560" height="430" border="0" cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td colspan="2" rowspan="2" class="td">&nbsp;</td>
      <td width="132" align="center" class="td">求职人数</td>
      <td width="122" align="center" class="td">求职比重</td>
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
      <td class="td">新成长失业青年</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td"><%=df1.format(nd1[0])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[0]/total1*100))%></td>
    </tr>
    <tr class="td">
      <td class="td">其中：应届高校毕业生</td>
      <td align="center" class="td">2</td>
      <td align="center" class="td"><%=df1.format(nd1[1])%></td>
     <td align="center" class="td"><%=(String.valueOf(nd1[0]).equals("0.0")?"0":df.format(nd1[1]/nd1[0]*100))%></td>
      
    </tr>
    <tr>
      <td class="td"><div align="left">就业转失业人员</div></td>
      <td align="center" class="td"><div align="center">3</div></td>
      <td align="center" class="td"><%=df1.format(nd1[2])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[2]/total1*100))%></td>
    </tr>
    <tr>
      <td height="25" class="td"><div align="left">其他失业人员</div></td>
      <td align="center" class="td"><div align="center">4</div></td>
      <td align="center" class="td"><%=df1.format(nd1[3])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[3]/total1*100))%></td>
    </tr>
    <tr>
      <td height="34" class="td">
      <div align="left">在业人员</div></td>
      <td align="center" class="td"><div align="center">5</div></td>
      <td align="center" class="td"><%=df1.format(nd1[4])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[4]/total1*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="left">下岗职工</div></td>
      <td align="center" class="td"><div align="center">6</div></td>
      <td align="center" class="td"><%=df1.format(nd1[5])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[5]/total1*100))%></td>
    </tr>
    <tr>
      <td height="25" class="td">
      <div align="left">退休人员</div></td>
      <td align="center" class="td"><div align="center">7</div></td>
      <td align="center" class="td"><%=df1.format(nd1[6])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[6]/total1*100))%></td>
    </tr>
    <tr>
      <td height="34" class="td">
      在学人员</td>
      <td align="center" class="td"><div align="center">8</div></td>
      <td align="center" class="td"><%=df1.format(nd1[7])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[7]/total1*100))%></td>
    </tr>
    <tr>
      <td class="td"><div align="left">本市农村人员</div></td>
      <td align="center" class="td"><div align="center">9</div></td>
      <td align="center" class="td"><%=df1.format(nd1[8])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[8]/total1*100))%></td>
    </tr>
    <tr>
      <td height="25" class="td"><div align="left">外埠人员</div></td>
      <td align="center" class="td"><div align="center">10</div></td>
      <td align="center" class="td"><%=df1.format(nd1[9])%></td>
      <td align="center" class="td"><%=(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[9]/total1*100))%></td>
    </tr>
    <tr class="td">
      <td height="25" class="td"><div align="left">合计</div></td>
      <td align="center" class="td"><div align="center">11</div></td>
      <td align="center" class="td"><div align="center"><%=df1.format(total1)%></div></td>
      <td align="center" class="td"><div align="center">100.00</div></td>
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

