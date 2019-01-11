package org.radf.apps.commons.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NoConnectionException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ReportFactory implements JRDataSource {

	private List<Report> data ;
	
	 public List<Report> setCollection(String gcltid,Integer year,Integer month) throws Exception{

		 List<Report> data = new ArrayList<Report>();
		 int maxfee = 0;
		 String[] feename = {
				 "管理费","开票税","税收","社保费","工资","广告费","电话费","水电费",
				 "差旅费","邮费","办公费","公关费","会计工资","手续费","促销","退机费","其他","优惠"
		 };//21
		 String[] feefile = {
				 "feemanage","feeinvoice","feetax","feepension","feepay","feead","feephone","feewater",
				 "feetrip","feepostage","feeoffice","feesocial","feeaccount","feeprocess","feepromotion","feeback","feothersa","feemedical"
		 };
		 String[] amortizename = {
				 "房租费","转让费","装修费","开办费","固定资产(电脑)","固定资产(空调)","固定资产","设备"
		 };
		 String[] amofile = {
				 "rent","trans","built","open","assetscom","assetscon","other","assetsmach",
		 };
		 Connection con = null;
		 try {
			con = DBUtil.getConnection();
			//String sql1 = "select  count(*) as maxrow from tblinventory i left outer join tblproduct p on p.pdtid= i.ivtpdtid  where i.ivtpamnt>0 and i.ivtgcltid='"+gcltid+"' and i.ivtyear='"+year+"' and i.ivtmonth='"+month+"' ";
			String sql2 = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid,i.ivttype order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid,i.ivttype order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice, p.pdtprc, p.pdtcls, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE (i.ivtpqnt <>0 or i.ivtpamnt<>0) AND i.ivttype <>'repair' AND i.ivtgcltid ='"+gcltid+"' AND i.ivtyear ='"+year+"' AND i.ivtmonth ='"+month+"' union all SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm||' '||i.ivtnote, i.ivtfee as pdtprc, i.ivtfee, p.pdtcls, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivttype <>'repair' and i.ivttype <> 'repairEar' AND i.ivtgcltid ='"+gcltid+"' AND i.ivtyear ='"+year+"' AND i.ivtmonth ='"+month+"' and ivtnote in ('续保费','加急费') ORDER BY 7, 8, 4 DESC ";
			String sql3 = "select * from tblfee where feegctid='"+gcltid+"' and feeyear='"+year+"' and feemonth='"+month+"'";
			//sql4意思按i.ivttype分组将i.ivtpqnt连续求和，按照i.ivttype排序，并重复行只显示一行
			String sql4 = "select distinct nvl(sum(i.ivtpqnt)over(partition by i.ivttype order by i.ivttype),0) as temp01,nvl(sum(i.ivtpamnt)over(partition by i.ivttype order by i.ivttype),0) as temp02 from tblinventory i where i.ivtpamnt>0 and i.ivttype='repair' and i.ivtgcltid='"+gcltid+"' and i.ivtyear='"+year+"' and i.ivtmonth='"+month+"' ";
			String sql5 = "SELECT SUM(rdisprice) AS chengben FROM tblrepdiscount where rdisgctid='"+gcltid+"' and rdisyear='"+year+"' and rdismonth='"+month+"' ";
			String sql6 = "SELECT NVL(ROUND(SUM( DECODE(amotype,'trans',meiyue,NULL)),2),0)  AS trans, " +
					"  NVL(ROUND(SUM( DECODE(amotype,'assetscom',meiyue,NULL)),2),0)   AS assetscom, " +
					"  NVL( ROUND( SUM( DECODE(amotype,'assetscon',meiyue,NULL)),2),0) AS assetscon, " +
					"  NVL(ROUND(SUM(DECODE(amotype,'assetsmach',meiyue,NULL)),2),0)   AS assetsmach, " +
					"  NVL(ROUND(SUM( DECODE(amotype,'built',meiyue,NULL)),2),0)       AS built, " +
					"  NVL(ROUND(SUM( DECODE(amotype,'open',meiyue,NULL)),2),0)        AS OPEN, " +
					"  NVL(ROUND(SUM( DECODE(amotype,'other',meiyue,NULL)),2),0)       AS other, " +
					"  NVL(ROUND(SUM( DECODE(amotype,'rent',meiyue,NULL)),2),0)        AS rent " +
					"FROM " +
					"  (SELECT t.amotype                                      AS amotype, " +
					"    t.feegctid                                           AS id, " +
					"    SUM(t.money/(months_between(t.feeend,t.feestart)+1)) AS meiyue " +
					"  FROM tblfeeamortize t " +
					"  WHERE t.feegctid = '" + gcltid +
					"' AND feestart    <= to_date('" +year+
					"'||'-"+month+"','yyyy-mm') " +
					"  AND feeend >= to_date('" +year+
					"'||'-"+month+"','yyyy-mm') " +
					"  GROUP BY amotype, " +
					"    t.feegctid " +
					"  )";
			Statement pstmt;
			pstmt = con.createStatement();
//			ResultSet rs1 = pstmt.executeQuery(sql1);
//			while(rs1.next()){
//				String smaxrow = rs1.getString("maxrow");
//				Integer maxrow = Integer.valueOf(smaxrow);
//			}
//			rs1.close();
			//固定摊销费用
			ResultSet rs6 = pstmt.executeQuery(sql6);
			while (rs6.next()) {
				for (int i = 0; i < 8; i++) {
					Report report = new Report();
					report.setFname(amortizename[i]);
					if (rs6.getString(amofile[i]) == null) {
						report.setOutfee(new Double(0));
					} else
						report.setOutfee(Double.valueOf(rs6
								.getString(amofile[i])));
					data.add(report);
				}
				maxfee =8;
			}
		   rs6.close();
		
			ResultSet rs3 = pstmt.executeQuery(sql3);
			while (rs3.next()) {
				for (int i = 0; i < 18; i++) {
					Report report = new Report();
					report.setFname(feename[i]);
					if (rs3.getString(feefile[i]) == null) {
						report.setOutfee(new Double(0));
					} else
						report.setOutfee(Double.valueOf(rs3
								.getString(feefile[i])));
					data.add(report);
				}
				maxfee += 18;
			}
		   rs3.close();

		   
			ResultSet rs2 = pstmt.executeQuery(sql2);
			int count = 0;
			
			while(rs2.next()){
				if(count >= maxfee){
					Report report0 = new Report();
					if(rs2.getString("disprice")== null)
						report0.setDiscount(new Double(0));
					else
						report0.setDiscount(Double.valueOf(rs2.getString("disprice")));
					report0.setPname(rs2.getString("pdtnm"));
					report0.setNumber(Integer.valueOf(rs2.getString("temp01")));
					if(rs2.getString("pdtprc") == null)
						report0.setPrice(new Double(0));
					else
					    report0.setPrice(Double.valueOf(rs2.getString("pdtprc")));
					report0.setSales(Double.valueOf(rs2.getString("temp02")));
					//report0.setFname("");
					//report0.setOutfee(new Double(0));	
					data.add(report0);
					}
				else{
					Report re = data.get(count);
					if(rs2.getString("disprice")== null)
						re.setDiscount(new Double(0));
					else
					    re.setDiscount(Double.valueOf(rs2.getString("disprice")));
					re.setPname(rs3.getString("pdtnm"));
					re.setNumber(Integer.valueOf(rs2.getString("temp01")));
					if(rs2.getString("pdtprc") == null)
						re.setPrice(new Double(0));
					else
					    re.setPrice(Double.valueOf(rs2.getString("pdtprc")));
					re.setSales(Double.valueOf(rs2.getString("temp02")));
				}
				count ++;
				
					
			}
			rs2.close();
			Statement pstmt2;
			pstmt2 = con.createStatement();
			ResultSet rs4 = pstmt.executeQuery(sql4);
			ResultSet rs5 = pstmt2.executeQuery(sql5);
			while(rs4.next()){ 
				if(count >= maxfee){
					Report report0 = new Report();
					report0.setDiscount(new Double(0));
					report0.setPname("维修费");
					report0.setNumber(Integer.valueOf(rs2.getString("temp01")));
					report0.setPrice(new Double(0));
					report0.setSales(Double.valueOf(rs2.getString("temp02")));
					while(rs5.next()){
						report0.setDiscount(Double.valueOf(rs5.getString("chengben")==null?"0":rs5.getString("chengben")));
					}
					
					//report0.setFname("");
					//report0.setOutfee(new Double(0));	
					data.add(report0);
					}
				else{
					Report re = data.get(count);
					re.setDiscount(new Double(0));
					re.setPname("维修费");
					re.setNumber(Integer.valueOf(rs2.getString("temp01")));
					re.setPrice(new Double(0));
					re.setSales(Double.valueOf(rs2.getString("temp02")));
				}
				count ++;
			}
			rs4.close();
			pstmt.close();
		} catch (NoConnectionException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		  
	      this.data = data;
	      return data;
	 }
	 
	private int index = -1;   
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		
		Object value = null;   
        
        String fieldName = field.getName();   
           
        if ("pname".equals(fieldName))   
        {   
            value = data.get(index).getPname();   
        }   
        else if ("number".equals(fieldName))   
        {   
            value = data.get(index).getNumber();   
        }   
        else if ("price".equals(fieldName))   
        {   
            value = data.get(index).getPrice();   
        }   
        else if ("sales".equals(fieldName))   
        {   
            value = data.get(index).getSales();   
        }   
        else if ("discount".equals(fieldName))   
        {   
            value = data.get(index).getDiscount(); 
        }   
        else if ("pmoney".equals(fieldName))   
        {   
            value = data.get(index).getPmoney();   
        }
        else if ("fname".equals(fieldName))   
        {   
            value = data.get(index).getFname();
        }   
        else if ("outfee".equals(fieldName))   
        {   
            value = data.get(index).getOutfee();   
        }   
        return value;   

	}

	@Override
    public boolean next() throws JRException   
    {   
        index++;   
        return (index < data.size());   
    }   

}
