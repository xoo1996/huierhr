package org.radf.apps.store.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.Store;
import org.radf.apps.store.facade.StoreFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class StoreImp extends IMPSupport implements StoreFacade{

	private String className = this.getClass().getName();
	
	/**
	 * 商品入库批量保存
	 */
	public ResponseEnvelop saveStore(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Store> stoList = (Vector<Store>) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 插入
			for(Store sto:stoList)
			{
				List result1 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall from  tblproduct t join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+sto.getStoproid()+"'");
				List result2 = (Vector)DBUtil.querySQL(con, "select p.pdtprc,p.pdtut from tblproduct p where p.pdtid='"+sto.getStoproid()+"'");
				String pclid=null;
				if(result1.size()!=0)
//				if(null!=result1.get(0))
				{
				  pclid=(String)(((HashMap)result1.get(0)).get("1"));
				}
				Double pdtprc=null;
				if(null!=((HashMap)result2.get(0)).get("1")&&!"".equals(((HashMap)result2.get(0)).get("1")))
				{
				   pdtprc=Double.parseDouble((((HashMap)result2.get(0)).get("1")).toString());
				}
				String pdtut=(String)(((HashMap)result2.get(0)).get("2"));
				if(null!=pclid && !"".equals(pclid))
				{
					String pcllar = ((HashMap)result1.get(0)).get("2").toString();
					String pclmid = ((HashMap)result1.get(0)).get("3").toString();
					String pclsam = ((HashMap)result1.get(0)).get("4").toString();
					sto.setStoprotype(pclid);
					sto.setStocllar(pcllar);
					sto.setStoclmid(pclmid);
					sto.setStoclsam(pclsam);
				}
				sto.setStoproprice(pdtprc);
				sto.setStoamountun(pdtut);
			}
			
			store(con, stoList, null, 0);
			DBUtil.commit(con);

			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("workString", "新增入库商品");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setHead(ExceptionSupport(className, "saveStore",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	public ResponseEnvelop modifyState(RequestEnvelop request,int sta) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			if(sta==1)           //报废
			{
				Store sto = (Store) map.get("beo");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				if (sto.getStoid() == null || sto.getStoid().equals("")) {
					throw new AppException("报废失败");
				} else {
				    sto.setFileKey("sto01_001");
//				    sto.setStoprotype("1");
				    sto.setStoactype("-1");
				    sto.setStodate(DateUtil.getDate());
				    sto.setStodisc("1");
				    List result1 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall from  tblproduct t join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+sto.getStoproid()+"'");
				    String pclid=(String)(((HashMap)result1.get(0)).get("1"));
				    if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result1.get(0)).get("2").toString();
						String pclmid = ((HashMap)result1.get(0)).get("3").toString();
						String pclsam = ((HashMap)result1.get(0)).get("4").toString();
						sto.setStoprotype(pclid);
						sto.setStocllar(pcllar);
						sto.setStoclmid(pclmid);
						sto.setStoclsam(pclsam);
					}
				    sto.setStoremark("报废出库");
				    //sto.setStopid(sto.getStoid());
				    store(con,sto,null,0);
					DBUtil.commit(con);
				}
			}
			else if(sta==2)   //配货
			{
				Order order=(Order)map.get("order");
				order.setFolsta("allocated");
				Store store=(Store)map.get("store");
				String grCli=store.getStogrcliid();
				String operCd=store.getStooprcd();
				
				//LoginDTO currentuser = (LoginDTO)pageContext.findAttribute("LoginDTO");
	           // String s = currentuser.getAab300();
//				if(result.size()>0)
//					pamnt = Double.parseDouble(((HashMap) result.get(0)).get("1").toString());
//				Store sto=new Store();
				//sto.setStoproid(stoproid);
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				if (order.getFolno() == null || order.getFolno().equals("")) {
					throw new AppException("配货失败");
				} else {
					order.setFileKey("ord03_004");
				    modify(con, order, null, 0);
					String sql="select l.fdtpid as fdtpid,t.pdtnm as pdtnm ,l.fdtqnt as fdtqnt,l.fdtprc as fdtprc from tblfoldetail l,tblproduct t where l.fdtpid=t.pdtid  and l.fdtfno='"+order.getFolno()+"'";
					 PreparedStatement pstm = null;
			         ResultSet res = null;
//			         try {
			             pstm = con.prepareStatement(sql);
			             res = pstm.executeQuery();
			             while(res.next())
			             {
			            	 Store sto=new Store();
			            	 sto.setFileKey("sto01_001");
			            	 sto.setStogrcliid(grCli);
								sto.setStoproid(res.getString("fdtpid"));
								sto.setStoprotype("2");
								sto.setStoproname(res.getString("pdtnm"));
								sto.setStoamount(res.getInt("fdtqnt"));
								sto.setStoproprice(res.getDouble("fdtprc"));	
								sto.setStoactype("-1");
								sto.setStooutdate(DateUtil.getSystemCurrentTime());
								sto.setStooprcd(operCd);
								sto.setStodisc("0");
								store(con,sto,null,0);
			             }
			             
//			         }catch(SQLException e){
//			        	 e.printStackTrace();
//			         }
			         
//			         }
			         
//						List result = (Vector) DBUtil.querySQL(con,sql);
//						for(int i=0;i<result.size();i++)
//						{
//							Store sto=new Store();	
//							sto.setStogrcliid(grCli);
//							sto.setStoproid(((HashMap)result.get(i)).get("0").toString());
//							sto.setStoprotype(2);
//							sto.setStoproname(((HashMap)result.get(i)).get("1").toString());
//							sto.setStoamount(Integer.parseInt(((HashMap)result.get(i)).get("2").toString()));
//							sto.setStoproprice(Double.parseDouble(((HashMap)result.get(i)).get("3").toString()));	
//							sto.setStoactype(-1);
//							sto.setStooutdate(DateUtil.getSystemCurrentTime());
//							sto.setStooprcd(operCd);
//							sto.setStodisc(0);
//							store(con,sto,null,0);
//							
//						}
			         
					DBUtil.commit(con);
					DBUtil.closeResStat(res, pstm);
				}
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
			DBUtil.rollback(con);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
			DBUtil.rollback(con);
		} finally {
		
		
			DBUtil.closeConnection(con);
		}
		return response;
	}

    public void outStore(Connection con,String sql,Order order,String grCli,String operCd)
    {
	  sql="select l.fdtpid as fdtpid,t.pdtnm as pdtnm ,l.fdtqnt as fdtqnt,l.fdtprc as fdtprc from tblfoldetail l,tblproduct t where l.fdtpid=t.pdtid  and l.fdtfno='"+order.getFolno()+"'";
	  PreparedStatement pstm = null;
      ResultSet res = null;
    try {
        pstm = con.prepareStatement(sql);
        res = pstm.executeQuery();
        while(res.next())
        {
       	 Store sto=new Store();
       	 sto.setFileKey("sto01_001");
       	 sto.setStogrcliid(grCli);
				sto.setStoproid(res.getString("fdtpid"));
				sto.setStoprotype("2");
				sto.setStoproname(res.getString("pdtnm"));
				sto.setStoamount(res.getInt("fdtqnt"));
				sto.setStoproprice(res.getDouble("fdtprc"));	
				sto.setStoactype("-1");
				sto.setStodate(DateUtil.getSystemCurrentTime());
				sto.setStooprcd(operCd);
				sto.setStodisc("0");
				store(con,sto,null,0);
        }
      }
    	  catch (Exception ex) {
  			ex.printStackTrace();
  			DBUtil.rollback(con);
  		}
    }

    public ResponseEnvelop allocate(RequestEnvelop request){
    	ResponseEnvelop response = new ResponseEnvelop();
    	Connection con = null;
    	Store store = new Store();
    	try{
    		con = DBUtil.getConnection();
    		DBUtil.beginTrans(con);
    		HashMap<String,Object> map = (HashMap<String,Object>)request.getBody();
    		Order order = (Order)map.get("beo");
    		String opr = (String)map.get("opr");
    		order.setFolsta("waiting");
    		order.setFileKey("ord11_027");
    		modify(con,order,null,0);
    		
    		String sql = "select h.folctid,d.fdtpid,p.pdtnm,d.fdtsqnt,p.pdtut,d.fdtdprc from tblfoldetail d left outer join tblfolio h on h.folno=d.fdtfno " +
    				" left outer join tblproduct p on p.pdtid=d.fdtpid where fdtfno='" + order.getFolno() + "'";
    		List result = (Vector)DBUtil.querySQL(con, sql);
    		for(int i = 0; i< result.size(); i++){
    			List result0 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
    			
    			Integer stoid = Integer.parseInt(((HashMap)result0.get(0)).get("1").toString());//库存编码
    			String gctid = ((HashMap)result.get(i)).get("1").toString();//客户代码
    			String pdtid = ((HashMap)result.get(i)).get("2").toString();//商品代码
    			
    			String pdtnm = ((HashMap)result.get(i)).get("3").toString();//商品名称
    			String fdtsqnt = ((HashMap)result.get(i)).get("4").toString();//数量
    			String pdtut = ((HashMap)result.get(i)).get("5").toString();//单位
    			String pdtprc = ((HashMap)result.get(i)).get("6").toString();//价格
    			
    			store.setStoid(stoid);
    			store.setStogrcliid("1501000000");
    			store.setStoproid(pdtid);
    			store.setStoprotype("2");
    			store.setStoproname(pdtnm);
    			store.setStoamount(Integer.parseInt(fdtsqnt));
    			store.setStoamountun(pdtut);
    			store.setStoproprice(Double.parseDouble(pdtprc));
    			store.setStoactype("-1");
    			store.setStoremark("配货出库");
    			store.setStooprcd(opr);
    			store.setStodate(DateUtil.getDate());
    			
    			store(con,store,null,0);
    			
    		}
    		
    		DBUtil.commit(con);
    		
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "allocate",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
    public ResponseEnvelop panku(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Store sto = new Store();
		try {
			HashMap map = (HashMap) request.getBody();
			 sto = (Store) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			sto.setFileKey("sto_panku");
			store(con, sto, null, 0);
			//将待删除数据备份到备份数据库
			sto.setFileKey("sto_panku1");
			store(con,sto,null,0);
			//删除数据
			sto.setFileKey("sto_panku2");
			remove(con,sto,null,0);
			DBUtil.commit(con);

			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("workString", "盘库");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setHead(ExceptionSupport(className, "saveStore",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
