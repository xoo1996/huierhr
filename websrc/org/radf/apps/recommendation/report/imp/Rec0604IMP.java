/**
 * Rec0604IMP.java 2008/04/28
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author sk
 * @version 1.0
 */
package org.radf.apps.recommendation.report.imp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.radf.apps.recommendation.report.facade.Rec0604Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 职业介绍综合月报
 */
public class Rec0604IMP extends org.radf.plat.util.imp.IMPSupport implements
		Rec0604Facade {
	
	private String className = this.getClass().getName();

	/**
	 * 统计并生成月报 2008-4-28 by sk
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop reportCreate(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		CallableStatement proc = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultset = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String year = (String) map.get("year");
			String month = (String) map.get("month");
			String type = (String) map.get("type");
			String areaCode = (String)map.get("areaCode");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if(type.equals("yb01")){
				proc = con.prepareCall("{ call p_yb01(?,?) }");			
			}if(type.equals("yb02")){
				proc = con.prepareCall("{ call p_yb02(?,?) }");
			}if(type.equals("yb03")){
				proc = con.prepareCall("{ call p_yb03(?,?) }");
			}else{
				proc = con.prepareCall("{ call p_yb01(?,?) }");
			}
			/*
			if(type.equals("yb04")){
				proc = con.prepareCall("{ call p_yb04(?,?) }");
			}if(type.equals("yb05")){
				proc = con.prepareCall("{ call p_yb05(?,?) }");
			}if(type.equals("yb11")){
				proc = con.prepareCall("{ call p_yb11(?,?) }");
			}if(type.equals("yb12")){
				proc = con.prepareCall("{ call p_yb12(?,?) }");
			}if(type.equals("yb13")){
				proc = con.prepareCall("{ call p_yb13(?,?) }");
			}if(type.equals("yb21")){
				proc = con.prepareCall("{ call p_yb21(?,?) }");
			}if(type.equals("yb22")){
				proc = con.prepareCall("{ call p_yb22(?,?) }");
			}if(type.equals("yb23")){
				proc = con.prepareCall("{ call p_yb23(?,?) }");
			}if(type.equals("yb24")){
				proc = con.prepareCall("{ call p_yb24(?,?) }");
			}if(type.equals("yb25")){
				proc = con.prepareCall("{ call p_yb25(?,?) }");
			}if(type.equals("yb26")){
				proc = con.prepareCall("{ call p_yb26(?,?) }");
			}if(type.equals("yb27")){
				proc = con.prepareCall("{ call p_yb27(?,?) }");
			}if(type.equals("yb28")){
				proc = con.prepareCall("{ call p_yb28(?,?) }");
			}if(type.equals("yb29")){
				proc = con.prepareCall("{ call p_yb29(?,?) }");
			}	
			*/	
			proc.setString(1, year+month);
			proc.setString(2, areaCode);
			proc.execute();	
			DBUtil.commit(con);
			if(type.equals("yb01")){
				String sql1 = "select sum(ajy010) sum1,sum(ajy011) sum2,sum(ajy012) sum3 from yb01";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy010 = String.valueOf(resultset.getInt(1));
				String totalAjy011 = String.valueOf(resultset.getInt(2));
				String totalAjy012 = String.valueOf(resultset.getInt(3));
				
				String sql2 = "select * from yb01";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb01\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy010+","+totalAjy011+","+totalAjy012+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append(",").append(resultset.getInt(8)).append("\r\n");		
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}	
			if(type.equals("yb02")){
				String sql1 = "select sum(ajy010) sum1,sum(ajy011) sum2,sum(ajy012) sum3 from yb02";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy010 = String.valueOf(resultset.getInt(1));
				String totalAjy011 = String.valueOf(resultset.getInt(2));
				String totalAjy012 = String.valueOf(resultset.getInt(3));
				
				String sql2 = "select * from yb02";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb02\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy010+","+totalAjy011+","+totalAjy012+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getObject(6)).append(",")
					.append(resultset.getInt(7)).append(",").append(resultset.getInt(8)).append(",")
					.append(resultset.getInt(9)).append("\r\n");		
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb03")){
				String sql1 = "select sum(ajy010) sum1,sum(ajy011) sum2,sum(ajy012) sum3 from yb03";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy010 = String.valueOf(resultset.getInt(1));
				String totalAjy011 = String.valueOf(resultset.getInt(2));
				String totalAjy012 = String.valueOf(resultset.getInt(3));
				
				String sql2 = "select * from yb03";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb03\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy010+","+totalAjy011+","+totalAjy012+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append(",").append(resultset.getInt(8)).append("\r\n");		
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb04")){
				String sql1 = "select sum(ajy010) sum1,sum(ajy011) sum2,sum(ajy012) sum3 from yb04";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy010 = String.valueOf(resultset.getInt(1));
				String totalAjy011 = String.valueOf(resultset.getInt(2));
				String totalAjy012 = String.valueOf(resultset.getInt(3));
				
				String sql2 = "select * from yb04";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb04\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy010+","+totalAjy011+","+totalAjy012+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb05")){
				String sql1 = "select sum(ajy010) sum1,sum(ajy011) sum2,sum(ajy012) sum3 from yb05";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy010 = String.valueOf(resultset.getInt(1));
				String totalAjy011 = String.valueOf(resultset.getInt(2));
				String totalAjy012 = String.valueOf(resultset.getInt(3));
				
				String sql2 = "select * from yb05";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb05\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy010+","+totalAjy011+","+totalAjy012+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb11")){
				String sql1 = "select sum(ajy020) sum1,sum(ajy021) sum2 from yb11";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy020 = String.valueOf(resultset.getInt(1));
				String totalAjy021 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb11";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb11\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy020+","+totalAjy021+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb12")){
				String sql1 = "select sum(ajy020) sum1,sum(ajy021) sum2 from yb12";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy020 = String.valueOf(resultset.getInt(1));
				String totalAjy021 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb12";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb12\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy020+","+totalAjy021+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb13")){
				String sql1 = "select sum(ajy020) sum1,sum(ajy021) sum2 from yb13";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy020 = String.valueOf(resultset.getInt(1));
				String totalAjy021 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb13";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb13\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy020+","+totalAjy021+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb21")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb21";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb21";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb21\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append(",").append(resultset.getInt(8)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb22")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb22";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb22";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb22\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getObject(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append(",").append(resultset.getInt(8)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb23")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb23";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb23";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb23\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb24")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb24";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb24";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb24\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb25")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb25";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb25";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb25\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getObject(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append(",")
					.append(resultset.getInt(7)).append("\r\n");
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb26")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb26";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb26";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb26\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");				
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb27")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb27";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb27";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb27\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");				
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb28")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb28";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb28";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb28\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");				
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
			if(type.equals("yb29")){
				String sql1 = "select sum(ajy030) sum1,sum(ajy031) sum2,sum(ajy032) sum3 from yb29";
				preparedstatement = con.prepareStatement(sql1);
				resultset = preparedstatement.executeQuery();
				resultset.next();
				String totalAjy030 = String.valueOf(resultset.getInt(1));
				String totalAjy031 = String.valueOf(resultset.getInt(2));
				String totalAjy032 = String.valueOf(resultset.getInt(2));
				
				String sql2 = "select * from yb29";
				preparedstatement = con.prepareStatement(sql2);
				resultset = preparedstatement.executeQuery();
				
				StringBuffer sb = new StringBuffer("yb29\r\n"+year+month+"\r\n"+areaCode+"\r\n"+totalAjy030+","+totalAjy031+","+totalAjy032+"\r\n");
				while(resultset.next()){
					sb.append(resultset.getObject(1)).append(",").append(resultset.getObject(2)).append(",")
					.append(resultset.getObject(3)).append(",").append(resultset.getInt(4)).append(",")
					.append(resultset.getInt(5)).append(",").append(resultset.getInt(6)).append("\r\n");				
				}
				HashMap retmap = new HashMap();
	            retmap.put("dto", sb);
	            response.setBody(retmap);
	            resultset.close();
	            preparedstatement.close();
			}
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "reportCreate",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
