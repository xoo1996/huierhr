<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="org.radf.plat.commons.DBUtil"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<link href='../../css/print.css' rel='stylesheet' type='text/css'>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<body onload='print();'>
<p align="center">

<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WB width=0></object> <OBJECT id=factory style="DISPLAY:  none"
		codeBase=http: //<%=request.getServerName()%> :
		<%=request.getServerPort()+request.getContextPath()%>/scriptx/ScriptX.cab#Version=5,60,0,360
		classid=clsid:1663ed61-23eb-11d2-b92f-008048fdd814 viewastext>
		</OBJECT>
 <%DecimalFormat df = new DecimalFormat("0.##");
			String year = request.getParameter("year");
			int season = Integer.parseInt(request.getParameter("season"));
			String a021 = request.getParameter("a021");
			String sfbh = request.getParameter("sfbh");
			String date1 = "";
			String date2 = "";
			date1 = year + '-' + String.valueOf((season - 1) * 3 + 1);//季度开始的年月
			if (season == 1 || season == 2 || season == 3) {
				date2 = year + '-' + String.valueOf((season - 1) * 3 + 4);//季度结束的年月
			} else {
				date2 = String.valueOf(Integer.parseInt(year) + 1) + '-'
						+ String.valueOf((season - 4) * 3 + 1);//季度结束的年月
			}
			PreparedStatement preparedstatement = null;
			ResultSet rs = null;
			Connection connection = null;
%></p>
<p align="center">&nbsp;</p>
<div align="center">
<table width="905" border="0">
	<tr>
		<td align="center" colspan="2"><span class="title">表7：需求小于求职缺口最大的十个职业（职业小类）</span></td>
	</tr>
	<tr>
		<td width="446" height="30"><strong>填报单位：</strong></td>
		<td width="449" height="30" align="right" nowrap><strong><%=year%>年第<%=season%>季度</strong></td>
	</tr>
</table>
<table width="905" height="79" border="0" cellpadding="0"
	cellspacing="0" class="table">
	<tr>
		<td width="148" rowspan="2" class="td">
		<div align="center">职业</div>
		</td>

		<td width="129" rowspan="2" class="td">
		<div align="center">职业代码</div>
		</td>
		<td colspan="5" align="center" class="td">劳动力供求人数比较</td>
	</tr>
	<tr>
		<td width="153" height="30" align="center" class="td">需求人数</td>

		<td width="177" align="center" class="td">求职人数</td>
		<td width="142" align="center" class="td">缺口数</td>
		<td width="154" align="center" class="td">求人倍率</td>
	</tr>
	<%try {

				connection = DBUtil.getConnection();
				String sql = "";
				sql = "select tb.*,rownum from (select b.aca111,b.aca112,b.xqrs,a.qzrs,nvl(a.qzrs,0)-nvl(b.xqrs,0) as qk,round(case nvl(a.qzrs,0) when 0 then 0 else nvl(b.xqrs,0)/nvl(a.qzrs,0) end,2) as bl from (select aca111,max(aca112) as aca112,sum(nvl(acb21d,0)) as xqrs from cb21 ,cb20 where cb21.acb200=cb20.acb200 and aae043<to_date('"
						+ date2
						+ "','yyyy-mm') and aae043>=to_date('"
						+ date1
						+ "','yyyy-mm')  and f_getdata_area('"+a021+"',cb21.aae017,"+sfbh+")='1' group by aca111)b,(select cc21.aca111,count(*) as qzrs from cc21,cc20  where cc21.acc200=cc20.acc200 and aae043<to_date('"
						+ date2
						+ "','yyyy-mm') and aae043>=to_date('"
						+ date1
						+ "','yyyy-mm') and f_getdata_area('"+a021+"',cc20.aae017,"+sfbh+")='1' group by cc21.aca111)a where a.aca111(+)=b.aca111 and nvl(b.xqrs,0)-nvl(a.qzrs,0)<0 order by nvl(b.xqrs,0)-nvl(a.qzrs,0))tb where rownum<11";
				System.out.println("sql07==" + sql);
				preparedstatement = connection.prepareStatement(sql);
				rs = preparedstatement.executeQuery();
				while (rs.next()) {

					%>
	<tr>
		<td class="td">
		<div align="left"><%=rs.getInt(7)%>.<%=rs.getString(2)%></div>
		</td>
		<td align="center" class="td"><%=rs.getString(1)%></td>
		<td align="center" class="td"><%=rs.getInt(3)%></td>
		<td align="center" class="td"><%=rs.getInt(4)%></td>
		<td align="center" class="td"><%=rs.getInt(5)%></td>
		<td align="center" class="td"><%=rs.getDouble(6)%></td>
	</tr>
	<%}%>
</table>
<%} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (preparedstatement != null)
						preparedstatement.close();
					if (connection != null)
						connection.close();
				} catch (java.lang.Exception exception2) {
					exception2.printStackTrace();
				}
			}

		%> <script>
function print()
{ 
SetPrintSettings();
try{WB.ExecWB(6,6);}catch(e){}

}
 
       function SetPrintSettings() {  
              factory.printing.portrait = false     //方向，true
              factory.printing.leftMargin = 1.0  
              factory.printing.topMargin = 1.0  
              factory.printing.rightMargin = 1.0  
              factory.printing.bottomMargin = 1.0 
     
           
       }  
    </script></div>
</body>
</html>
