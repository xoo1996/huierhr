package org.radf.apps.client.single.imp;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.text.SimpleDateFormat;



import oracle.sql.ARRAY;

import org.apache.struts.action.ActionForward;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.ReDiagnose;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Tblitsmci;

public class SingleClientImp extends IMPSupport implements SingleClientFacade {
	private String className = this.getClass().getName();

	/**
	 * 新增个人客户信息
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;

		try {
			HashMap map = (HashMap) request.getBody();
			SingleClient sc = (SingleClient) map.get("beo");
//			Diagnose dg = (Diagnose) map.get("beo1");
			List<Audiogram> agList = (Vector<Audiogram>) map.get("beo2");
			String type = (String) map.get("type");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String ictid="";
			if(type.equals("firm"))
			{
				ictid = (String) map.get("ictid");
				if (sc.getIctid() == null || sc.getIctid().equals("")) {
					List result1 = (Vector) DBUtil.querySQL(con,
							//"select ictid from tblindclient where ictgctid='"+sc.getIctgctid()+"'");
							"select max(ictid) from tblindclient");
					if(null==((HashMap) result1.get(0)).get("1"))
					{
						throw new AppException("该个人客户信息未登记,请先保存再修改");
					}
					else
					{
						ictid = (String) ((HashMap) result1.get(0)).get("1");
					}
				}else{
					
				}
				sc.setIctid(ictid);
				sc.setFileKey("singleClient_update");
//				dg.setFileKey("diagnose_update");
				modify(con, sc, null, 0);
				//modify(con, dg, null, 0);
				for (Audiogram ag : agList) {
					ag.setAdgctid(ictid);
				//	List result1 = (Vector) DBUtil.querySQL(con,"select * from tblaudgraph where adgctid='"+ag.getAdgctid()+"' and adglre='"+ag.getAdglre()+"' and adgtp='"+ag.getAdgtp()+"' and adgfq='"+ag.getAdgfq()+"'");
			//		if(result1.isEmpty()){
			//			if((ag.getAdgfq()==750||ag.getAdgfq()==1500||ag.getAdgfq()==3000||ag.getAdgfq()==6000)&&(ag.getAdgadt()==null||"".equals(ag.getAdgadt()))){
			//				continue;
			//			}
			//			ag.setFileKey("audiogram_insert");
			//			store(con, ag, null, 0);
			//		}else{
						ag.setFileKey("audiogram_update");
						modify(con, ag, null, 0);
			//		}
				}
			}
			else
			{
				// 获取最后一个客户ID号
				List result = (Vector) DBUtil.querySQL(con,
						"select max(ictid) from tblindclient");
				
				if(null==((HashMap) result.get(0)).get("1"))
				{
					ictid="0";
				}
				else
				{
					ictid = (String) ((HashMap) result.get(0)).get("1");
				}
//				String ictid = (String) ((HashMap) result.get(0)).get("1");
				// System.out.println(ictid);
				Integer id = Integer.valueOf(ictid) + 1;
			
				/*List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_ADGCTID.nextval from dual");
				
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				*/
				ictid=id.toString();		
				ictid = String.format("%06d", id);//以0开始的6位数字
				// System.out.println(ictid);
				sc.setIctid(ictid);
				sc.setFileKey("singleClient_insert");
				store(con, sc, null, 0);
				sc.setFileKey("singleClient_detail_insert");
				store(con,sc,null,0);
//				dg.setDgnctid(ictid);
//				dg.setFileKey("diagnose_insert");
//				store(con, dg, null, 0);
				for (Audiogram ag : agList) {
					ag.setAdgctid(ictid);
				//	if((ag.getAdgfq()==750||ag.getAdgfq()==1500||ag.getAdgfq()==3000||ag.getAdgfq()==6000)&&(ag.getAdgadt()==null||"".equals(ag.getAdgadt()))){
				//		continue;
				//	}
					ag.setFileKey("audiogram_insert");
					store(con, ag, null, 0);
				}	
			}
			
			sc.setFileKey("ord07_000");// 个人客户详细信息
			Object ci = find(con, sc, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("sc", ci);
			retmap.put("ictid", ictid);
			retmap.put("workString", "新增个人客户信息");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 个人客户信息修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			SingleClient sc = (SingleClient) map.get("beo");
			Diagnose dg = (Diagnose) map.get("beo1");
			List<Audiogram> agList = (Vector<Audiogram>) map.get("beo2");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (sc.getIctid() == null || sc.getIctid().equals("")) {
				throw new AppException("该个人客户信息未登记");
			} else {
				sc.setFileKey("singleClient_update");
//				dg.setFileKey("diagnose_update");
				modify(con, sc, null, 0);
				List result1 = (Vector) DBUtil.querySQL(con,"select ictid from tblinddetail where ictid = "+sc.getIctid());
				if(result1.isEmpty()){
					sc.setFileKey("singleClient_detail_insert");
				}else{
					sc.setFileKey("singleClient_detail_update");	
				}
					modify(con,sc,null,0);
				//modify(con, dg, null, 0);
				for (Audiogram ag : agList) {
					/*List result = (Vector) DBUtil.querySQL(con,"select * from tblaudgraph where adgctid='"+ag.getAdgctid()+"' and adglre='"+ag.getAdglre()+"' and adgtp='"+ag.getAdgtp()+"' and adgfq='"+ag.getAdgfq()+"'");
					if(result.isEmpty()){
						if((ag.getAdgfq()==750||ag.getAdgfq()==1500||ag.getAdgfq()==3000||ag.getAdgfq()==6000)&&(ag.getAdgadt()==null||"".equals(ag.getAdgadt()))){
							continue;
						}
						ag.setFileKey("audiogram_insert");
						store(con, ag, null, 0);
					}else{*/
						ag.setFileKey("audiogram_update");
						modify(con, ag, null, 0);
					/*}*/
				}
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modify",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 个人客户信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			SingleClient dto = (SingleClient) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getIctid() == null || dto.getIctid().equals("")) {
				throw new AppException("该客户信息未登记");
			} else {
				dto.setFileKey("singleClient_delete");
				remove(con, dto, null, 0);
				dto.setFileKey("diagnose_delete");
				remove(con, dto, null, 0);
				dto.setFileKey("singleClient_detail_delete");
				remove(con, dto, null, 0);
				dto.setFileKey("audiogram_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 个人客户信息显示
	 */
	public ResponseEnvelop print(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			SingleClient dto = (SingleClient) map.get("beo");
			String tp = (String) map.get("tp");
			dto.setFileKey("singleClient_select");// 个人客户详细信息
			Object ci = find(con, dto, null, 0);
			Object ci1=null;
            if(null!=tp&&!"".equals(tp)&&"m".equals(tp))
            {
            	dto.setFileKey("diagnose_select");// 门诊详细信息
            	ci1 = find(con, dto, null, 0);
            }

			dto.setFileKey("clt03_000");// 听力图详细信息
			Object ci2 = find(con, dto, null, 0);

			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			if(null!=ci1)
			{
			   retmap.put("beo1", ci1);
			}
			retmap.put("beo2", ci2);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 个人客户信息显示
	 */
	public ResponseEnvelop printSCTL(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			SingleClient dto = (SingleClient) map.get("beo");
			dto.setFileKey("fctldetail_select");// 个人客户详细信息
			Object ci = find(con, dto, null, 0);
            

			dto.setFileKey("cltfctl03_001");// 听力图详细信息
			Object ci2 = find(con, dto, null, 0);
			dto.setFileKey("cltfctl03_002");// 听力图详细信息
			Object ci3 = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			retmap.put("beo2", ci2);
			retmap.put("beo3", ci3);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printSCTL",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 个人售后服务调查表显示
	 */
	public ResponseEnvelop printBV(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			SingleClient dto = (SingleClient) map.get("beo");
			dto.setFileKey("bvdetail_select");// 个人客户详细信息
			Object ci = find(con, dto, null, 0);
            
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printBV",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * 个人客户信息显示
	 */
	public ResponseEnvelop printSCSC(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			SingleClient dto = (SingleClient) map.get("beo");
			dto.setFileKey("fcscdetail_select");// 个人客户详细信息
			Object ci = find(con, dto, null, 0);
            

			dto.setFileKey("cltfcsc03_000");// 听力图详细信息
			Object ci2 = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			retmap.put("beo2", ci2);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printSCTL",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop saveSC(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ReDiagnose rd = (ReDiagnose) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			rd.setFileKey("reDiagnose_insert");
			store(con, rd, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("fctctid", rd.getFctctid());
			retmap.put("workString", "新增个人客户信息");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveSC",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop deleteSC(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ReDiagnose rd = (ReDiagnose) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (rd.getFctctid() == null || rd.getFctctid().equals("")) {
				throw new AppException("该复诊信息未登记");
			} else {
				rd.setFileKey("reDiagnose_delete");
				remove(con, rd, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteSC",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop deleteSCTL(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			SingleClient singleClient = (SingleClient) map.get("beo");
			    con = DBUtil.getConnection();
		        DBUtil.beginTrans(con);
				singleClient.setFileKey("fctl_delete");
				remove(con, singleClient, null, 0);
				singleClient.setFileKey("fctlgraph_delete");
				remove(con, singleClient, null, 0);
				DBUtil.commit(con);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteSC",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	//删除言语评估
	public ResponseEnvelop deleteSCYYPG(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			   SingleClient singleClient = (SingleClient) map.get("beo");
			    con = DBUtil.getConnection();
		        DBUtil.beginTrans(con);
				singleClient.setFileKey("fcyp_delete");
				remove(con, singleClient, null, 0);
				/*singleClient.setFileKey("fctlgraph_delete");
				remove(con, singleClient, null, 0);*/
				DBUtil.commit(con);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteSC",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	//删除言语评估
		public ResponseEnvelop deleteSCSC(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				HashMap map = (HashMap) request.getBody();
				   SingleClient singleClient = (SingleClient) map.get("beo");
				    con = DBUtil.getConnection();
				    DBUtil.beginTrans(con);
					singleClient.setFileKey("fcsc_delete");
					remove(con, singleClient, null, 0);
					singleClient.setFileKey("fcscgraph_delete");
					remove(con, singleClient, null, 0);
					DBUtil.commit(con);
				
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "deleteSC",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
	public ResponseEnvelop saveSCTL(RequestEnvelop request) {
		// TODO Auto-generated method stub
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;

		try {
			    HashMap map = (HashMap) request.getBody();
	    		SingleClient sc = (SingleClient) map.get("beo");
		    	List<Audiogram> agList = (Vector<Audiogram>) map.get("beo2");
		    	String type=(String)map.get("beo3");
			    con = DBUtil.getConnection();
			    DBUtil.beginTrans(con);
			   //生成复诊听力编号
			    if("update".equals(type)){
			    	sc.setFileKey("fctl_update");
					modify(con, sc, null, 0);
					sc.setFileKey("fctlgraph_delete");
					remove(con, sc, null, 0);
					//将复诊数据保存到听力图表
					for (Audiogram ag : agList) {
						ag.setAdgctid(sc.getFcttlid());//设置听力图编号
						ag.setFileKey("fctlaudiogram_insert");
						store(con, ag, null, 0);
					}	
			    
			    }else {
			    	List result = (Vector) DBUtil.querySQL(con,
							"select SEQ_FCTL.NEXTVAL from dual");
					BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String fctlid = id.toString();
				    sc.setFcttlid(fctlid);
					sc.setFileKey("fctl_insert");
					store(con, sc, null, 0);
					//将复诊数据保存到听力图表
					for (Audiogram ag : agList) {
						ag.setAdgctid(fctlid);//设置听力图编号
				
						ag.setFileKey("fctlaudiogram_insert");
						store(con, ag, null, 0);
					}	
				}
		
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("workString", "操作成功");
			response.setBody(retmap);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop checkSingleName(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		Connection con = null;
		try{
			HashMap map = (HashMap) request.getBody();
			SingleClient scl = (SingleClient) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
		boolean flag=false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ictbdt = sdf.format(scl.getIctbdt());
		
			String queryhql ="select * from tblindclient where ictgctid='"+scl.getIctgctid()+"' and ictnm='"+scl.getIctnm()+"' and ictgender='"+scl.getIctgender()+"' and ictbdt=to_date('"+ictbdt+ "','yyyy-mm-dd HH24:mi:ss')";
			List result = (Vector) DBUtil.querySQL(con, queryhql);
			if(null != result && result.size() !=0){
				flag=true;
			}
		
		if(flag)
		{
			retmap.put("dto", scl);
			response.setBody(retmap);
			return response;
		}
	    DBUtil.commit(con);
	
       }catch (Exception ae) {
	       
    	   
       }finally {
	     DBUtil.rollback(con);
	     DBUtil.closeConnection(con);
       }
       return response;
	}

	public ResponseEnvelop saveSCYYPG(RequestEnvelop request) {
		// TODO Auto-generated method stub
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
	    		SingleClient sc = (SingleClient) map.get("beo");
			    con = DBUtil.getConnection();
			    DBUtil.beginTrans(con);
			   //生成复诊听力编号
			    List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_FCYYPG.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String fcypid = id.toString();
			    sc.setFcypid(fcypid);
				sc.setFileKey("fcyp_insert");
				store(con, sc, null, 0);
			/*sc.setFileKey("ord07_000");// 个人客户详细信息
			Object ci = find(con, sc, null, 0);*/
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("fcypid",fcypid);
			retmap.put("workString", "新增复诊言语评估成功");
			response.setBody(retmap);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop saveSCSC(RequestEnvelop request) {
		// TODO Auto-generated method stub
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
	    		SingleClient sc = (SingleClient) map.get("beo");
		    	List<Audiogram> agList = (Vector<Audiogram>) map.get("beo2");
			    con = DBUtil.getConnection();
			    DBUtil.beginTrans(con);
			   //生成复诊听力编号
			    List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_FCSC.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String fcscid = id.toString();
			    sc.setFcscid(fcscid);
				sc.setFileKey("fcsc_insert");
				store(con, sc, null, 0);
				//将复诊数据保存到听力图表
				for (Audiogram ag : agList) {
					ag.setAdgctid(fcscid);//设置听力图编号
				//	if((ag.getAdgfq()==750||ag.getAdgfq()==1500||ag.getAdgfq()==3000||ag.getAdgfq()==6000)&&(ag.getAdgadt()==null||"".equals(ag.getAdgadt()))){
				//		continue;
				//	}
					ag.setFileKey("fcscaudiogram_insert");
					store(con, ag, null, 0);
				}	
			
			
			/*sc.setFileKey("ord07_000");// 个人客户详细信息
			Object ci = find(con, sc, null, 0);*/
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("fctlid",fcscid);
			retmap.put("workString", "新增复诊声场成功");
			response.setBody(retmap);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop saveBV(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;

		try {
			    HashMap map = (HashMap) request.getBody();
	    		SingleClient sc = (SingleClient) map.get("beo");
			    con = DBUtil.getConnection();
			    DBUtil.beginTrans(con);
			    String type=(String)map.get("beo3");
			    if("update".equals(type)){
			    	sc.setFileKey("bv_update");
					modify(con, sc, null, 0);
			    }else {
			    	List result = (Vector) DBUtil.querySQL(con,
							"select SEQ_BV.NEXTVAL from dual");
					BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String bvid = id.toString();
				    sc.setBvid(bvid);
					sc.setFileKey("bv_insert");
					store(con, sc, null, 0);
			    }
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("workString", "操作成功");
			response.setBody(retmap);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop deleteBV(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			SingleClient singleClient = (SingleClient) map.get("beo");
			    con = DBUtil.getConnection();
		        DBUtil.beginTrans(con);
				singleClient.setFileKey("bv_delete");
				remove(con, singleClient, null, 0);
				DBUtil.commit(con);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteSC",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
}
