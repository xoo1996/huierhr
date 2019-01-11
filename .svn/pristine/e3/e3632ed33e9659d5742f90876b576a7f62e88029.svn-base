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
<div align="center">
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
String date1 = "";
String date2 = "";
date1 = year+'-'+String.valueOf((season-1)*3+1);//季度开始的年月
if(season==1||season==2||season==3){
	date2 = year+'-'+String.valueOf((season-1)*3+4);//季度结束的年月
}
else{
	date2 = String.valueOf(Integer.parseInt(year)+1)+'-'+String.valueOf((season-4)*3+1);//季度结束的年月
}
String a021=request.getParameter("a021");
String sfbh=request.getParameter("sfbh");
PreparedStatement preparedstatement = null;
ResultSet rs = null;
Connection connection = null;

int length=7;//项目数
double[] nd1=new double[length];//需求人数
double[] nd2=new double[length];//求职人数
double total1=0;//总需求人数
double total2=0;//总求职人数

try{
	connection =DBUtil.getConnection();
	
	for(int i=0;i<length;i++)
	{
		String sql1 = "select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 d where a.acb200=b.acb200 and a.aab001=d.aab001 "+
					  " and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
if(a021!=null&&!a021.equals("")){
			
			sql1+=" and  f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1' ";
		}
		if(i==0)//初中及以下
		sql1+=" and b.aac011 in('70','80','90')";
		if(i==1)//高中
		sql1+=" and b.aac011 in('61')";
		if(i==2)//职高，技校，中专
		sql1+=" and b.aac011 in('40','50','62')";
		if(i==3)//大专
		sql1+=" and b.aac011 in('31')";
		if(i==4)//大学
		sql1+=" and b.aac011 in('21')";
		if(i==5)//硕士以上
		sql1+=" and b.aac011 in('11','12')";
		if(i==6)//无需求
		sql1+=" and (b.aac011 in('10') or b.aac011 is null)";
		
		preparedstatement = connection.prepareStatement(sql1);
		System.out.println(sql1);
		rs = preparedstatement.executeQuery();
		rs.next();
		nd1[i]= rs.getDouble(1);
		
		String sql2 = "select count(*) from cc20 b,ac01 a  where  a.aac001=b.aac001 and "+
		              "b.aae043<to_date('"+date2+"','yyyy-mm') and b.aae043>=to_date('"+date1+"','yyyy-mm') and substr(b.aae017,13,2)='03'";
if(a021!=null&&!a021.equals("")){
			
			sql1+=" and  f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1' ";
		}
		if(i==0)//初中及以下
		sql2+=" and a.aac011 in('70','80','90')";
		if(i==1)//高中
		sql2+=" and a.aac011 in('61','40','50','62')";
		if(i==2)//职高，技校，中专
		sql2+=" and a.aac011 in('40','50','62')";
		if(i==3)//大专
		sql2+=" and a.aac011 in('31')";
		if(i==4)//大学
		sql2+=" and a.aac011 in('21')";
		if(i==5)//硕士以上
		sql2+=" and a.aac011 in('11','12')";
		if(i==6)//无需求
		sql2+=" and a.aac011 in('10')";
		
		preparedstatement = connection.prepareStatement(sql2);
		rs = preparedstatement.executeQuery();	
		System.out.println(sql2);
		rs.next();
		nd2[i]= rs.getDouble(1);
	}
	String sqlsum1="select nvl(sum(b.acb21d),0) from cb20 a,cb21 b,ab01 d  where a.acb200=b.acb200 and  a.aab001=d.aab001 "+
					  " and a.aae043<to_date('"+date2+"','yyyy-mm') and a.aae043>=to_date('"+date1+"','yyyy-mm') ";
	String sqlsum2="select count(*) from ac01 a,cc20 b where a.aac001=b.aac001"+
		              " and b.aae043<to_date('"+date2+"','yyyy-mm') and b.aae043>=to_date('"+date1+"','yyyy-mm') ";
	if(a021!=null&&!a021.equals("")){
		sqlsum1+=" and f_getdata_area('"+a021+"',a.aae017,"+sfbh+")='1' ";
		sqlsum2+=" and  f_getdata_area('"+a021+"',b.aae017,"+sfbh+")='1' ";
	}
	System.out.println("sqlsum1="+sqlsum1);
	preparedstatement = connection.prepareStatement(sqlsum1);
	rs=preparedstatement.executeQuery();
	rs.next();
	total1=rs.getDouble(1);	
	
	System.out.println("sqlsum2="+sqlsum2);              
	preparedstatement = connection.prepareStatement(sqlsum2);              
	rs=preparedstatement.executeQuery();
	rs.next();
	total2=rs.getDouble(1);
	
		              
%>

<p>&nbsp;</p>
  <table width="905" border="0">
    <tr>
      <td align="center" colspan="2"><span class="title">表11：按文化程度分组的供求人数</span></td>
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
    <%
  for(int j=0;j<length;j++)
  {
    String title="";
    if(j==0)
    	title="初中及以下";
    if(j==1)
    	title="高中";
    if(j==2)
    	title="其中职高，技校，中专";
    if(j==3)
    	title="大专";
    if(j==4)
    	title="大学";
    if(j==5)
    	title="硕士以上";
    if(j==6)
    	title="无需求";

    out.println("<tr>");
    out.println("<td class='td'>"+title+"</td>");
    out.println("<td align='center' class='td'>"+(j+1)+"</td>"); 
    out.println("<td align='center' class='td'>"+df.format(nd1[j])+"</td>");	
    out.println("<td align='center' class='td'>"+(String.valueOf(total1).equals("0.0")?"0":df.format(nd1[j]/total1*100))+"%</td>");		
    out.println("<td align='center' class='td'>"+df.format(nd2[j])+"</td>");	
    out.println("<td align='center' class='td'>"+(String.valueOf(total2).equals("0.0")?"0":df.format(nd2[j]/total2*100))+"%</td>");		
    out.println("<td align='center' class='td'>"+(String.valueOf(nd2[j]).equals("0.0")?"0":df.format(nd1[j]/nd2[j]))+"</td>");
    out.println("</tr>"); 												
  }
  %>
    
  <tr>
      <td class="td">合计</td>
      <td align="center" class="td">8</td>
      <td align="center" class="td"><%=df.format(total1)%></td>
      <td align="center" class="td">100.00</td>
      <td align="center" class="td"><%=df.format(total2)%></td>
      <td align="center" class="td">100.00</td>
      <td align="center" class="td"><%=String.valueOf(total2).equals("0.0")?"0":df.format(total1/total2)%></td>
    </tr>
  </table>
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
</div>
</body>
</html>

