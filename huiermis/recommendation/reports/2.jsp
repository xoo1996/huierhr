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
String year = request.getParameter("year");
int season=Integer.parseInt(request.getParameter("season"));
String a021=request.getParameter("a021");
String sfbh=request.getParameter("sfbh");
String date1 = "";
String date2 = "";
date1 = year+'-'+String.valueOf((season-1)*3+1);//季度开始的年月
if(season==1||season==2||season==3){
	date2 = year+'-'+String.valueOf((season-1)*3+4);//季度结束的年月
}
else{
	date2 = String.valueOf(Integer.parseInt(year)+1)+'-'+String.valueOf((season-4)*3+1);//季度结束的年月
}
PreparedStatement preparedstatement = null;
ResultSet rs = null;
ResultSet rs3 = null;
Connection connection = null;

int length=10;//项目数
double[] nd1=new double[length];//需求人数
//double[] nd2=new double[length];//求职人数
int total1=0;//总需求人数
//double total2=0;//总求职人数

try{

	connection =DBUtil.getConnection();
		String sql1 = "select aa15.aab054 aaa103,tb.* from  (select case  when substr(c.aab054,1,3) in ('101' ,'102','103')  then substr(c.aab054,1,3) else '104' end as aab054 ,nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 c where  b.aab001=c.aab001 and a.acb200=b.acb200"+
					  " and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
		if(a021!=null&&!a021.equals("")){
			
			sql1+=" and f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1'";
		}
		sql1+=" group by c.aab054)tb,(select substr(bab055,1,3) bab055,aab054 from aa15 t where t.bab055 like '___000000')aa15 where aa15.bab055=tb.aab054(+) order by aa15.bab055";
		System.out.println(sql1);
		preparedstatement = connection.prepareStatement(sql1);
		rs = preparedstatement.executeQuery();
		String sqlsum1="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 c where  b.aab001=c.aab001 and a.acb200=b.acb200"+
						  " and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
		  if(a021!=null&&!a021.equals("")){
			sqlsum1+=" and  f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1'";
		}				
		preparedstatement = connection.prepareStatement(sqlsum1);
		rs3=preparedstatement.executeQuery();
		rs3.next();
		total1=rs3.getInt(1);
		System.out.println(sqlsum1);
%>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <table width="561" border="0">
    <tr>
      <td width="669" align="center" colspan="2"><span class="title">表2：按产业分组的需求人数</span></td>
    </tr>
    <tr>
      <td height="30"><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="561" height="214" border="0" cellpadding="0" cellspacing="0"  class=table>
    <tr>
      <td colspan="2" rowspan="2" class="td">&nbsp;</td>
      <td width="189" align="center" class="td">需求人数</td>
      <td width="117" align="center" class="td">需求比重</td>
    </tr>
    <tr>
      <td height="25" align="center" class="td">人</td>
      <td align="center" class="td">％</td>
    </tr>
    <tr>
      <td width="178" height="41" align="center" class="td">甲</td>
      <td width="75" align="center" class="td">序号</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td">2</td>
    </tr>
   <%
    int i=0; 
    while(rs.next())
    {
    i++;
   %>
    <tr>
      <td height="41" class="td"><div align="center"><%=rs.getString(1)%></div></td>
      <td align="center" class="td"><div align="center"><%=i%></div></td>
      <td align="center" class="td"><%=rs.getInt(3)%></td>
      <td align="center" class="td"><%=(String.valueOf(rs.getInt(3)).equals("0")?"0":df.format(rs.getDouble(3)/total1*100))%></td>
    </tr>
    <%}%>
    <tr>
      <td height="41" class="td"><div align="center">合计</div></td>
      <td align="center" class="td">5</td>
      <td align="center" class="td"><%=total1%></td>
      <td align="center" class="td">100.00</td>
    </tr>
  </table>
  <p>
	
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
         if(rs != null)
             rs.close();	 
         if(rs3 != null)
             rs3.close();
         if(preparedstatement != null)
             preparedstatement.close();
         if(connection != null)
            connection.close();
     }
     catch(java.lang.Exception exception2) { }
 }
%>
  </p>
</div>
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

