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
double total=0;//总需求人数
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
int[] nd2=new int[21];
try{
	connection =DBUtil.getConnection();
String bm[]={"010000","020000","030000","040000","050000"
,"060000","070000","080000","090000","100000","110000","120000","130000","140000"
,"150000","160000","170000","180000","190000","200000","900000"};

for(int i=0;i<=20;i++){
	String sql1="";
	if(i==20)
	{
		 sql1 = "select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 c where  b.aab001=c.aab001 and a.acb200=b.acb200"+
		  " and  (c.aab022='"+bm[i]+"' or c.aab022 is null) and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";

	}
	else
	{
		 sql1 = "select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 c where  b.aab001=c.aab001 and a.acb200=b.acb200"+
		  " and  c.aab022='"+bm[i]+"' and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";

	}
		if(a021!=null&&!a021.equals("")){
			
			sql1+=" and   f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1'";
		}
System.out.println(sql1);
preparedstatement = connection.prepareStatement(sql1);
rs = preparedstatement.executeQuery();
rs.next();
nd2[i]= rs.getInt(1);
total=rs.getInt(1)+total;
}

%>
</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <table width="560" border="0">
    <tr>
      <td align="center" colspan="2"><span class="title">表3：按行业分组的需求人数</span></td>
      
    </tr>
    <tr>
      <td height="30"><strong>填报单位：</strong></td>
      <td height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
    </tr>
  </table>
  <table width="560" height="327" border="0" cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td colspan="2" rowspan="2" class="td">&nbsp;</td>
      <td width="138" align="center" class="td">需求人数</td>
      <td width="116" align="center" class="td">需求比重</td>
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
      <td class="td">农，林，牧，渔业</td>
      <td align="center" class="td">1</td>
      <td align="center" class="td"><%=nd2[0]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[0]/total*100))%></td>
    </tr>
    <tr class="td">
      <td class="td">采矿业</td>
      <td align="center" class="td">2</td>
      <td align="center" class="td"><%=nd2[1]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[1]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">制造业</td>
      <td align="center" class="td">3</td>
      <td align="center" class="td"><%=nd2[2]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[2]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">电力，燃气及水的生产和供应业</td>
      <td align="center" class="td">4</td>
      <td align="center" class="td"><%=nd2[3]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[3]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">建筑业</td>
      <td align="center" class="td">5</td>
      <td align="center" class="td"><%=nd2[4]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[4]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">交通运输，仓储和邮政业</td>
      <td align="center" class="td">6</td>
      <td align="center" class="td"><%=nd2[5]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[5]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">信息传输，计算机服务和软件业</td>
      <td align="center" class="td">7</td>
      <td align="center" class="td"><%=nd2[6]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[6]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">批发和零售业</td>
      <td align="center" class="td">8</td>
      <td align="center" class="td"><%=nd2[7]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[7]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">住宿和餐饮业</td>
      <td align="center" class="td">9</td>
      <td align="center" class="td"><%=nd2[8]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[8]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">金融业</td>
      <td align="center" class="td">10</td>
      <td align="center" class="td"><%=nd2[9]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[9]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">房地产业</td>
      <td align="center" class="td">11</td>
      <td align="center" class="td"><%=nd2[10]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[10]/total*100))%></td>
    </tr>
    <tr class="td">
      <td class="td">租赁和商务服务业</td>
      <td align="center" class="td">12</td>
      <td align="center" class="td"><%=nd2[11]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[11]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">科学研究，技术服务和地质勘查业</td>
      <td align="center" class="td">13</td>
      <td align="center" class="td"><%=nd2[12]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[12]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">水利，环境和公共设施管理业</td>
      <td align="center" class="td">14</td>
      <td align="center" class="td"><%=nd2[13]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[13]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">居民服务和其他服务业</td>
      <td align="center" class="td">15</td>
      <td align="center" class="td"><%=nd2[14]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[14]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">教育</td>
      <td align="center" class="td">16</td>
      <td align="center" class="td"><%=nd2[15]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[15]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">卫生，社会保障和社会福利业</td>
      <td align="center" class="td">17</td>
      <td align="center" class="td"><%=nd2[16]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[16]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">文化，体育和娱乐业</td>
      <td align="center" class="td">18</td>
      <td align="center" class="td"><%=nd2[17]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[17]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">公共管理与社会组织</td>
      <td align="center" class="td">19</td>
      <td align="center" class="td"><%=nd2[18]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[18]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">国际组织</td>
      <td align="center" class="td">20</td>
      <td align="center" class="td"><%=nd2[19]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[19]/total*100))%></td>
    </tr>
      <tr>
      <td class="td">其他</td>
      <td align="center" class="td">21</td>
      <td align="center" class="td"><%=nd2[20]%></td>
      <td align="center" class="td"><%=(String.valueOf(total).equals("0.0")?"0":df.format(nd2[20]/total*100))%></td>
    </tr>
    <tr>
      <td class="td">合计</td>
      <td align="center" class="td">22</td>
      <td align="center" class="td"><%=df1.format(total)%></td>
      <td align="center" class="td">100.00</td>
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

