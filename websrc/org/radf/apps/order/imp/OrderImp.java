package org.radf.apps.order.imp;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import net.sf.hibernate.hql.WhereParser;

import org.hibernate.dialect.function.NvlFunction;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.DisExamine;
import org.radf.apps.commons.entity.EarMould;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Store;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class OrderImp extends IMPSupport implements OrderFacade {
	private String className = this.getClass().getName();

	/**
	 * 新增订单基本信息
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Order dto = (Order) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 获取订单号
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String folno = id.toString();

			dto.setFolno(folno);
			dto.setFolsta("waiting");// 订单状态为"等待发货"
			dto.setFoltype("normal");// 订单类别为"普通订单"
			dto.setFileKey("order_insert");
			// 插入新订单
			store(con, dto, null, 0);
			
			
			DBUtil.commit(con);

			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("folno", dto.getFolno());
			retmap.put("beo", dto);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response; 
	}

	/**
	 * 订单明细批量修改（客服/供应部发货）
	 */
	public ResponseEnvelop modifyDetail(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<OrderDetail> collection = (Collection<OrderDetail>) map
					.get("collection");
			String opr = (String) map.get("opr");
			String way = (String) map.get("way");
			String sno = (String) map.get("sno");
			String des = (String) map.get("des");
			Date time = (Date) map.get("time");
			String folctid = (String)map.get("folctid");
			String folgrctid = (String)map.get("folgrctid");
			Order header = new Order();
			
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			List result = null;
			BigDecimal price = null;
			int i=1;
			for (OrderDetail od : collection) {
				Store store = new Store();
//				if("1".equals(od.getFdtsta()))
//				{
					//[start]
				result = (Vector) DBUtil.querySQL(con,
						"select t.pdtprc,t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtut from  tblproduct t left join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"
								+ od.getFdtpid() + "'");
				price = (BigDecimal) ((HashMap) result.get(0)).get("1");
				if(null == price || "".equals(price)){
					price = new BigDecimal(new Double("0.0"));
				}
				
				String pclid = (String)((HashMap)result.get(0)).get("2");
				String pcllar = null;
				String pclmid = null;
				String pclsam  = null;
				String pdtut = null;
				if(null != pclid && !"".equals(pclid)){	
					pcllar = ((HashMap)result.get(0)).get("3").toString();						
					pclmid = ((HashMap)result.get(0)).get("4").toString();						
					pclsam = ((HashMap)result.get(0)).get("5").toString();
					pdtut = ((HashMap)result.get(0)).get("6").toString();
				}
				
				od.setFdtdprc(price.doubleValue());
				od.setFileKey("ord04_000");
				modify(con, od, null, 0);
				
				
				header.setFolno(od.getFdtfno());// 订单号
				header.setFolsopr(opr); // 发货员
				header.setFolway(way);// 发货方式
				header.setFolsno(sno);// 快件号
				header.setFoldes(od.getFolctid());// 发货地点
				header.setFolsdt(time);// 发货时间戳
				header.setFolsta("finish");
				header.setFileKey("ord03_002");
				modify(con, header, null, 0);
				
				if(null == od.getFolctid() || "".equals(od.getFolctid())){
					od.setFolctid(folctid);
				}
				List result2 = (Vector)DBUtil.querySQL(con, "select gcttype from tblgrpclient where gctid='" + od.getFolctid() + "'");
				String gcttype = ((HashMap)result2.get(0)).get("1").toString();//团体客户类别
				//String prefix = od.getFolctid().substring(0, 1);//取客户代码的首字母
				String type = od.getFoltype();//订单类型
				//String prefix = des.substring(0, 1);//取客户代码的首字母
				Integer stoAmount = extendedAmount(con, od);
				
				if(type.equals("normal"))
				{
			        //获得最新库存编号
//					List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
//					String stoid = ((HashMap)result1.get(0)).get("1").toString();
					//总部出库
//					store.setStoid(Integer.parseInt(stoid));
					store.setStogrcliid("1501000000");
//					store.setStogrcliid(od.getFolctid());
					store.setStoproid(od.getFdtpid());
					store.setStoprotype("2");
					store.setStoproname(od.getPdtnm());
//					Integer stoAmount = extendedAmount(con, od);
//					if(null!=stoAmount&&stoAmount<od.getFdtsqnt())
//					{
//						throw new AppException("第"+i+"行商品库存量不足");
//						store.setStoamount(stoAmount);
//					}
//					else
//					{
//						if(null==stoAmount)
//						{
//							throw new AppException("第"+i+"行商品没有库存");
//						}
//						store.setStoamount(od.getFdtsqnt());
//					}
					    if(null!=stoAmount&&stoAmount<od.getFdtsqnt())//stoAmount 出库/入库数量 fdtsqnt 订单发货数量
	    				{
	    					store.setStoamount(od.getFdtsqnt());//发货不受库存数量限制 2015.2.4
	    				}
	    				else
	    				{
	    					store.setStoamount(od.getFdtsqnt());
	    				}
//					store.setStoprotype("4");
//					store.setStoproprice(od.getFdtdprc());
//					store.setStodate(DateUtil.getDate());
					store.setStoprotype(pclid);
					store.setStocllar(pcllar);
					store.setStoclmid(pclmid);
					store.setStoclsam(pclsam);
					store.setStoactype("-1");
	    			store.setStoremark("配货出库");
	    			store.setStooprcd(opr);
	    			store.setStodisc("0");
					store.setStodate(DateUtil.getDate());
					store.setFileKey("store_insert");
	    			store(con,store,null,0);
	    			
	    			
				}
	    			//直属店入库
	    			if(null != type && "A".equals(gcttype) && "make".equals(type) || "makeEar".equals(type) || "normal".equals(type) ||"repair".equals(type)|| "repairEar".equals(type)){//A代表该团体客户为直属店并且订单类型为定制订单、耳模订单或普通订单，则入其库。
	//	    			List result2 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
	//					String stoid2 = ((HashMap)result1.get(0)).get("1").toString();
		    			
//	    				Integer stoAmount = extendedAmount(con, od);
	    				
	    				if("normal".equals(type)){
	    					if(null!=stoAmount&&stoAmount<od.getFdtsqnt())
		    				{
		    					store.setStoamount(od.getFdtsqnt());//发货不受库存数量限制 2015.2.4
		    				}
		    				else
		    				{
		    					store.setStoamount(od.getFdtsqnt());
		    				}
	    				}else{
	    					store.setStoamount(od.getFdtsqnt());
	    				}
	    				
	    				
	    				
						store.setStogrcliid(folctid);
						store.setStoproid(od.getFdtpid());
						store.setStoproname(od.getPdtnm());
						store.setStoactype("1");
						store.setStoprotype(pclid);
						store.setStocllar(pcllar);
						store.setStoclmid(pclmid);
						store.setStoclsam(pclsam);
                        
						
						store.setStoamountun(pdtut);
		    		
						if(null != type && "make".equals(type) || "makeEar".equals(type) ||"repairEar".equals(type) ||"repair".equals(type)){
							if("make".equals(type)){
								store.setStoremark("直属店定制机入库");
							}
							else if("makeEar".equals(type)){
								store.setStoremark("直属店耳模入库");
							}
							else if("repairEar".equals(type)){
								store.setStoremark("直属店维修耳模入库");
							}
							else{
								store.setStoremark("直属店维修机入库");
								store.setIsrepair("1");
							}
						}else{
							store.setStoremark("直属店普通商品入库");
						}
						store.setStooprcd(opr);
						store.setStodisc("0");
						store.setStodate(DateUtil.getDate());
						store.setFileKey("store_insert");
						store(con, store, null, 0);
						i++;
	    			}
	    			//[end]
//				}
//				else                   //不发货处理
//				{
//					od.setFileKey("ord04_003");
//					modify(con, od, null, 0);         
//				}
				
			}  
//	    			String sql3="select l.fdtsta from tblfoldetail l where l.fdtfno='"+header.getFolno()+"'";
//	    			List result3 = (Vector)DBUtil.querySQL(con, sql3);
//	    			int j=0;
//	    			for(j=0;j<result3.size();j++)
//	    			{
//	    				String fdtsta = ((HashMap)result3.get(0)).get("1").toString();
//	    				if("0".equals(fdtsta))
//	    				{
//	    					break;
//	    				}
//	    			}
//	    			if(j==result3.size())
//	    			{
//	    				header.setFolsta("finish");
	    				
//	    			}
//	    			else
//	    			{
//	    				header.setFolsta("finishPart");
//	    			}
//	    			header.setFileKey("ord03_006");
//    				modify(con, header, null, 0);
				
		
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyDetail",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	private Integer extendedAmount(Connection con, OrderDetail od)
			throws SQLException {
//		PreparedStatement pstm = null;
//		ResultSet res = null;
		String sql1="select distinct sum(s.stoamount*s.stoactype)over(partition by s.stoproid) as amount from tblsto s where s.stogrcliid='1501000000' and s.stoproid='"+od.getFdtpid()+"'";	
//		pstm = con.prepareStatement(sql1);
//		res = pstm.executeQuery();
		List stoAmoList=(Vector)DBUtil.querySQL(con, sql1);
		Integer amount=null;
		if(0!=stoAmoList.size())
//		while(res.next())
		{
//		    amount=res.getInt("amount");
		    
		    amount=Integer.parseInt((((HashMap)stoAmoList.get(0)).get("1").toString()));
		}
		return amount;
	}
	
	
	public ResponseEnvelop checkStoAmount(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		Connection con = null;
		try{
			HashMap map = (HashMap) request.getBody();
//			List<OrderDetail> odList = (ArrayList<OrderDetail>) map.get("odList");
			Collection<OrderDetail> odList = (Collection<OrderDetail>) map.get("odList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			int i=1;
			boolean flag=false;
//			for(int i=0;i<odList.size();i++)
//			{
			for(OrderDetail od:odList)
			{
			Integer stoAmount = extendedAmount(con, od);
			if(null!=stoAmount&&stoAmount<od.getFdtsqnt())
			{
				od.setMinstoi(String.valueOf(i));
				od.setMinsto(stoAmount);
				flag=true;
//				throw new AppException("第"+i+"行商品库存量不足");
				//store.setStoamount(stoAmount);
			}
			else
			{
				if(null==stoAmount)
				{
					od.setMinstoi(String.valueOf(i));
					flag=true;
//					throw new AppException("第"+i+"行商品没有库存");
				}
			}
			i++;
			}
			
			
//		int j=1;
//		boolean flag=false;
//		for(Charge chg2:dtoList)
//		{	
//			String sql3 = "select g.gctprovince from tblgrpclient g where g.gctid='"
//				+ chg2.getStogrcliid() + "'";
//			List result3 = (Vector) DBUtil.querySQL(con, sql3);
//			String gctprovince = (String) ((HashMap) result3.get(0)).get("1");
//			checkDis(con,chg2,gctprovince);
//			if(null!=chg2.getTdspvo()&&(chg2.getTdspvo()>chg2.getNcddis()))
//			{	
//				chg2.setMindisi(String.valueOf(j));
//				flag=true;
//			}
//			j++;
//		
//		}
//	
		if(flag)
		{
			retmap.put("dto", odList);
			response.setBody(retmap);
			return response;
		}
//	}
		DBUtil.commit(con);
//	retmap.put("dto", odList);
//	response.setBody(retmap);
	
//}catch (AppException ae) {
//	response.setHead(ExceptionSupport(className, ae, request.getHead()));
//} 
		}catch (Exception ex) {
	response.setHead(ExceptionSupport(className, "save2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
} finally {
	DBUtil.rollback(con);
	DBUtil.closeConnection(con);
}
return response;

}

	/**
	 * 订单信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
        ResultSet res = null;
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		try {
			//HashMap map = (HashMap) request.getBody();
			//Order order = (Order) map.get("beo");
			//String grCli=(String)map.get("grCli");
			HashMap map = (HashMap) request.getBody();
			Order order = (Order) map.get("beo");
			OrderDetail dto1 = new OrderDetail();
			dto1.setFdtfno(order.getFolno());
			String grCli=(String)map.get("grCli");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if(order.getTp2().equals("m"))
			{
				String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
				pstm = con.prepareStatement(sql0);
	            res = pstm.executeQuery();
	            while(res.next())
	            {
	            	String sta=res.getString("folsta");
//	            	if(!(sta.equals("uncommited")||sta.equals("disback")||sta.equals("back")||sta.equals("")))
//            		{
//            			order.setTp2("false");
//            			retmap.put("beo6", order);
//            			response.setBody(retmap);
//            			return response;
//            		}
	            	if(!"1501000000".equals(grCli)){
	            		if(!(sta.equals("uncommited")||sta.equals("disback")||sta.equals("back")||sta.equals("")))
		            	{
		            		order.setTp2("false");
		            		retmap.put("beo6", order);
							response.setBody(retmap);
		            		return response;
		            	}
	            	}
	            	/*else{
	            		//if(!(sta.equals("uncommited")||sta.equals("commited")||sta.equals("back")||sta.equals("")))
	            		if(!(sta.equals("uncommited")||sta.equals("disback")||sta.equals("commited")||sta.equals("back")||sta.equals("")))
	            		{
	            			order.setTp2("false");
	            			retmap.put("beo6", order);
	            			response.setBody(retmap);
	            			return response;
	            		}
	            	} */
	            }
			}
			if (order.getFolno() == null || order.getFolno().equals("")) {
				throw new AppException("该订单信息未登记");
			} else {
				dto1.setFileKey("orderDetail_delete");
				order.setFileKey("order_delete");
				remove(con, dto1, null, 0);
				remove(con, order, null, 0);
//				remove(con, order, null, 0);
				if(order.getFoltype().equals("repair"))
				{
					order.setFileKey("ord11_019");
					remove(con, order, null, 0);
				}
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
			try {
				if(null!=res)
				{
				  res.close();
				}
				if(null!=pstm)
				{
					pstm.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Order dto = (Order) map.get("beo");
			OrderDetail dto1 = new OrderDetail();
			dto1.setFdtfno(dto.getFolno());
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getFolno() == null || dto.getFolno().equals("")) {
				throw new AppException("该订单信息未登记");
			} else {
				dto.setFileKey("order_delete");
				dto1.setFileKey("orderDetail_delete");
				remove(con, dto, null, 0);
				remove(con, dto1, null, 0);
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
	 * 订单明细批量保存
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<OrderDetail> dtoList = (Vector<OrderDetail>) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			// 插入订单明细
			store(con, dtoList, null, 0);
			DBUtil.commit(con);

			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("workString", "新增订单明细");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setHead(ExceptionSupport(className, "saveDetail",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 寄厂定制（新增完整订单）
	 */
	public ResponseEnvelop saveNew(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			Diagnose dto = (Diagnose) map.get("beo");
			String cnm = (String) map.get("cnm");
			String gid = (String) map.get("gid");
			String opr = (String) map.get("opr");
			Order header = new Order();
			OrderDetail detail = new OrderDetail();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 获取订单号
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String folno1 = id.toString();

			header.setFolno(folno1);
//			header.setFolctid(gid); // 团体客户
			header.setFoldt(DateUtil.getDate()); // 订单创建日期
			header.setFolsta("waiting");// 订单状态为"等待发货"
			header.setFoltype("make");// 订单类别为"定制订单" 准确说是寄厂定制订单，订单会缺少机身编号
			header.setFolopr(opr);
			header.setFileKey("order_insert");
			if (!"".equals(dto.getDgnlid()) && !"".equals(dto.getDgnrid())) {
				if (dto.getDgnlid().equals(dto.getDgnrid())) {
					detail.setFdtfno(folno1);// 订单号
					detail.setFdtcltnm(cnm);// 个人用户
					detail.setFdtpid(dto.getDgnlid());// 耳机代码
					detail.setFdtprc(dto.getDgnldprc()); // 单价
					detail.setFdtqnt(2);// 数量为2
					detail.setFdtsqnt(2);
					detail.setFdtdprc(dto.getDgnldprc());
					store(con, header, null, 0);
					store(con, detail, null, 0);
				} else {
					detail.setFdtfno(folno1);// 订单号
					detail.setFdtcltnm(cnm);// 个人用户
					detail.setFdtpid(dto.getDgnlid());// 耳机代码
					detail.setFdtprc(dto.getDgnldprc()); // 单价
					detail.setFdtqnt(1);// 数量为1
					detail.setFdtsqnt(1);
					detail.setFdtdprc(dto.getDgnldprc());
					store(con, header, null, 0);
					store(con, detail, null, 0);

					result = (Vector) DBUtil.querySQL(con,
							"select SEQ_FOLNO.NEXTVAL from dual");
					id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String folno2 = id.toString();

					header.setFolno(folno2);
					detail.setFdtfno(folno2);// 订单号
					detail.setFdtpid(dto.getDgnrid());// 耳机代码
					detail.setFdtprc(dto.getDgnrdprc()); // 单价
					detail.setFdtdprc(dto.getDgnrdprc());
					store(con, header, null, 0);
					store(con, detail, null, 0);
					retmap.put("folno2", folno2);
				}
			} else if ("".equals(dto.getDgnrid())) {
				detail.setFdtfno(folno1);// 订单号
				detail.setFdtcltnm(cnm);// 个人用户
				detail.setFdtpid(dto.getDgnlid());// 耳机代码
				detail.setFdtprc(dto.getDgnldprc()); // 单价
				detail.setFdtqnt(1);// 数量为1
				detail.setFdtsqnt(1);
				detail.setFdtdprc(dto.getDgnldprc());
				store(con, header, null, 0);
				store(con, detail, null, 0);
			} else if ("".equals(dto.getDgnlid())) {
				detail.setFdtfno(folno1);// 订单号
				detail.setFdtcltnm(cnm);// 个人用户
				detail.setFdtpid(dto.getDgnrid());// 耳机代码
				detail.setFdtprc(dto.getDgnrdprc()); // 单价
				detail.setFdtqnt(1);// 数量为1
				detail.setFdtsqnt(1);
				detail.setFdtdprc(dto.getDgnrdprc());
				store(con, header, null, 0);
				store(con, detail, null, 0);
			}
			DBUtil.commit(con);
			retmap.put("folno1", folno1);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 退机信息
	 */
	public ResponseEnvelop recoil(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			OrderDetail dto = (OrderDetail) map.get("beo");
			
			List result = (Vector) DBUtil.querySQL(con,
			"select SEQ_FOLNO.NEXTVAL from dual");
	        BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
	        String folno1 = id.toString();
	        dto.setFdtnt("退机");
	        dto.setFdtodt(DateUtil.getSystemCurrentTime());
	        dto.setFdtcltid(folno1);
			dto.setFileKey("ord04_001");// 插入退机订单信息
			store(con, dto, null, 0);
			Order or = new Order();
			or.setFolnt(folno1);
			or.setFolno(dto.getFdtfno());
			or.setFolopr(dto.getFdtcltnm());
	        or.setFolsdt(DateUtil.getSystemCurrentTime());
	        or.setFileKey("ord04_002");
	        store(con, or, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "recoil",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	
	/**
	 * 维修记录查询
	 *//*
	public ResponseEnvelop find(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		
		try {
			HashMap map = (HashMap) request.getBody();
			Order dto = (Order) map.get("beo");
			OrderDetail dto1 = new OrderDetail();
			dto1.setFdtfno(dto.getFolno());
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			dto1.setFileKey("repair_select");
			DBUtil.commit(con);

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
	}*/
	
	
		/**
		 * 个人客户信息显示
		 */
		public ResponseEnvelop printClient(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet res = null;
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				SingleClient dto = (SingleClient) map.get("beo");
//				String repid=(String)map.get("repid");
				dto.setFileKey("ord07_002");// 个人客户详细信息
				Object ci = find(con, dto, null, 0);
				dto.setFileKey("clt03_000");// 听力图详细信息
				Object ci2 = find(con, dto, null, 0);
//				Customization cz = (Customization) map.get("cz");
//				Customization cz=new Customization();
//				cz.setTmksid(repid);
//				cz.setFileKey("ord12_003");
//				Object foldt = find(con, cz, null, 0);
//                String foldt="";
//				String sql="select h.foldt from tblfolio h where h.folno=(select m.tmkfno from tblmaking m where m.tmksid='"+repid+"')";
//				pstm = con.prepareStatement(sql);
//	            res = pstm.executeQuery();
//	            while(res.next())
//	            {
//	            	foldt=res.getString("foldt");
//	            }
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("beo", ci);
				retmap.put("beo2", ci2);
//				retmap.put("foldt", foldt);
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
		
		
		public ResponseEnvelop print(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet res = null;
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				SingleClient dto = (SingleClient) map.get("beo");
				String repid=(String)map.get("repid");
				dto.setFileKey("ord07_000");// 个人客户详细信息
				Object ci = find(con, dto, null, 0);
				dto.setFileKey("clt03_000");// 听力图详细信息
				Object ci2 = find(con, dto, null, 0);
//				Customization cz = (Customization) map.get("cz");
//				Customization cz=new Customization();
//				cz.setTmksid(repid);
//				cz.setFileKey("ord12_003");
//				Object foldt = find(con, cz, null, 0);
                String foldt="";
                String reppid="";
                String pdtnm="";
//                String fdtsid="";
				//String sql="select h.foldt,p.pdtnm from tblmaking m left join tblproduct p on (p.pdtid=m.tmkpid),tblfolio h where h.folno=(select m.tmkfno from tblmaking m where m.tmksid='"+repid+"')";
				
				String sql="select h.foldt,p.pdtid,p.pdtnm from tblmaking m left outer join tblproduct p on (p.pdtid=m.tmkpid) left outer join tblfolio h on (h.folno=m.tmkfno) where  m.tmksid='"+repid+"'";
//				String sql2="select l.fdtsid from tblfoldetail l where l.fdtcltid='"+dto.getIctid()+"'";
				
				pstm = con.prepareStatement(sql);
	            res = pstm.executeQuery();
	            
	            while(res.next())
	            {
	            	foldt=res.getString("foldt");
	            	reppid=res.getString("pdtid");
	            	pdtnm=res.getString("pdtnm");
	            }
//	            pstm = con.prepareStatement(sql2);
//	            res = pstm.executeQuery();
//	            while(res.next())
//	            {
//	            	fdtsid=res.getString("fdtsid");
////	            	pdtnm=res.getString("pdtnm");
//	            }
	          
	            
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("beo", ci);
				retmap.put("beo2", ci2);
				retmap.put("foldt", foldt);
				retmap.put("reppid",reppid);
				retmap.put("pdtnm", pdtnm);
//				retmap.put("fdtsid",fdtsid);
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
		
	/*
	 * 显示定制机订单详细信息
	 */
		
		public ResponseEnvelop printCustomDetail(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				String grCli=(String)map.get("grCli");
				OrderDetail od=new OrderDetail();
				Diagnose dg=new Diagnose();
				SingleClient sc=new SingleClient();
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				PreparedStatement pstm = null;
		        ResultSet res = null;
		        if(!"1501000000".equals(grCli)){
		        	if(order.getTp2().equals("m"))
		        	{
		        		String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
		        		pstm = con.prepareStatement(sql0);
		        		res = pstm.executeQuery();
		        		while(res.next())
		        		{
//		            		if(res.getString("folsta").equals("pass")||res.getString("folsta").equals("commited"))
		        			if(!(order.getFolsta().equals("uncommited")||order.getFolsta().equals("back")||order.getFolsta().equals("wtddis")||order.getFolsta().equals("disback")))
		        			{
		        				order.setTp2("false");
		        				retmap.put("beo6", order);
		        				response.setBody(retmap);
		        				return response;
		        			}
		        		}
		        	}
		        }
		        DecimalFormat format=new DecimalFormat("0.00");  //保留两位小数 四舍五入
				//String sql1="select d.fdtmklr,d.fdtpid,d.fdtcltid,d.fdtsheltp from tblfoldetail d where d.fdtfno='"+order.getFolno()+"'";
		        String sql1=" select d.fdtmklr,d.fdtpid,d.fdtcltid,d.fdtcltnm,d.fdtsheltp,d.fdtdprc,d.fdtdisc,d.fdtprc,p.pdtid,p.pdtnm from tblfoldetail d,tblproduct p where d.fdtpid=p.pdtid and d.fdtfno='"+order.getFolno()+"'";
		        		
		         String fdtsheltp="";
//		         String sheltp="";
		         String fdtpid="";
		         String fdtcltid="";
		         String fdtcltnm="";//jia
		         String fdtmklr="";
		         String pdtnm="";
		         Double fdtdprc=0.0;
		         Double fdtdisc=0.0;
		         Double fdtprc=0.0;
//		         try {

		             pstm = con.prepareStatement(sql1);
		             res = pstm.executeQuery();
			     while(res.next())
		         {
		             fdtsheltp=res.getString("fdtsheltp");
		             fdtpid=res.getString("fdtpid");
		             fdtcltid=res.getString("fdtcltid");
		             fdtcltnm=res.getString("fdtcltnm");//jia
		             fdtmklr=res.getString("fdtmklr");
		             fdtdprc=Double.parseDouble(res.getString("fdtdprc"));
		             fdtdisc=res.getString("fdtdisc")==null?1.0:Double.parseDouble(res.getString("fdtdisc"));
		             pdtnm=res.getString("pdtnm");
		             fdtprc=Double.parseDouble(res.getString("fdtprc"));
		             if(fdtmklr!=null)
		             {
		            	 if(fdtmklr.equals("0"))
		            	 { 
		            		 dg.setDgnlnm(pdtnm);
		            		 dg.setDgnldprc(fdtdprc);
		            		 dg.setDgnldct(fdtdisc);
//		            		 dg.setDgncldprc(Double.parseDouble(format.format(fdtdprc*fdtdisc)));
		            		 dg.setDgncldprc(fdtprc);
		            		 od.setDgnlid(res.getString("pdtid"));
		            	 }
		            	 else if(fdtmklr.equals("1"))
		            	 {
		            		dg.setDgnrnm(pdtnm);
		            		dg.setDgnrdprc(fdtdprc);
		                 	dg.setDgnrdct(fdtdisc);
//		                 	dg.setDgncrdprc(Double.parseDouble(format.format(fdtdprc*fdtdisc)));
		                 	dg.setDgncrdprc(fdtprc);
		                 	od.setDgnrid(res.getString("pdtid"));
		            	 }
		             }
		             else{
		            	 //老订单统计显示左耳
		            	 fdtmklr="0";
		            	 dg.setDgnlnm(pdtnm);
	            		 dg.setDgnldprc(fdtdprc);
	            		 dg.setDgnldct(fdtdisc);
	            		 dg.setDgncldprc(fdtprc);
	            		 od.setDgnlid(res.getString("pdtid"));
		             }
		             
		            
//		             switch (Integer.parseInt(fdtsheltp))
//	            	 {
//	            	     case  1: sheltp="耳内式";
//	            	     case 2:sheltp="耳道式";
//	            	     case 3: sheltp="半耳式";
//	            	     case 4:sheltp="深耳道式";
//	            	 }
	             }
			     
			     if((null==fdtcltid || "".equals(fdtcltid)) && (null==fdtcltnm || "".equals(fdtcltnm))){
			    	 throw new AppException("用户信息不完整，无法显示");
			     }
			     if((null==fdtcltid || "".equals(fdtcltid)) && (null!=fdtcltnm && !"".equals(fdtcltnm))){
			    	 String sqlfid = "select t.ictid from tblindclient t where t.ictnm='"+fdtcltnm+"' and t.ictgctid='"+order.getFolctid()+"'";
			    	 pstm = con.prepareStatement(sqlfid);
		             res = pstm.executeQuery();
		             int ncount=0;
		             while(res.next())
		             {
		            	 fdtcltid=res.getString("ictid");
		            	 ncount++;
		             }
		             if(ncount>1 || (null==fdtcltid || "".equals(fdtcltid))){
		            	 throw new AppException("用户信息不完整，无法显示");
		             }
			     }
		         
		         Double dgnldprc=0d;
		         Double dgnldct=0d;
		         Double dgnrdprc=0d;
		         Double dgnrdct=0d;
//		         String pdtnm="";
//		         DecimalFormat format=new DecimalFormat("0.00");  //保留两位小数 四舍五入
		         String sql2="select e.dgnldprc,e.dgnldct,p.pdtnm,e.dgnrdprc,e.dgnrdct,e.dgnpulwire,e.dgnairvent,e.dgnvoswitch,e.dgnsdmd,e.dgnlid,e.dgnrid,e.dgnid,e.dgndoc from tbldiagnose e left join tblproduct p on (e.dgnlid=p.pdtid or e.dgnrid=p.pdtid)  where e.dgnlfdtfno='"+ order.getFolno()+"' or e.dgnrfdtfno='"+order.getFolno()+"'";
//		         if(fdtmklr.equals("0"))    //原来的订单号对应的fdtmklr为null
//	             {
//		            sql2+="and e.dgnlid='"+fdtpid+"'"; dgncldprc
//	             }
//		         else
//		         {
//		        	sql2+="and e.dgnrid='"+fdtpid+"'"; 
//		         }
		             pstm = con.prepareStatement(sql2);
		             res = pstm.executeQuery();
		             while(res.next())
		             {
		            	 pdtnm=res.getString("pdtnm");
//		            	 if(null!=fdtmklr)
//		            	 {
		            		 if(fdtmklr.equals("0"))
		            		 {
			            	  
			                  od.setFdtsheltpl(fdtsheltp);
//			                  dg.setDgnlnm(pdtnm);
//			                  dgnldprc=Double.parseDouble(res.getString("dgnldprc"));
//					          dgnldct=Double.parseDouble(res.getString("dgnldct"));
//			                  dg.setDgnldprc(dgnldprc);
//			                  dg.setDgnldct(dgnldct);
//			                  dg.setDgncldprc(Double.parseDouble(format.format(dgnldprc*dgnldct)));
			                  dg.setDgndoc(res.getString("dgndoc"));
//			                  dg.setDgnlid(res.getString("dgnlid"));
//			                  od.setDgnlid(res.getString("dgnlid"));    //返回时orderForm中的dgnlid不会为空
			                  
		            		 }
		            		 else
		            		 {
			            	 od.setFdtsheltpr(fdtsheltp);
//			            	 dg.setDgnrnm(pdtnm);
//			            	 dgnrdprc=Double.parseDouble(res.getString("dgnrdprc"));
//					         dgnrdct=Double.parseDouble(res.getString("dgnrdct"));
//			            	 dg.setDgnrdprc(dgnrdprc);
//			                 dg.setDgnrdct(dgnrdct);
//			                 dg.setDgncrdprc(Double.parseDouble(format.format(dgnrdprc*dgnrdct)));
//			                 dg.setDgnrid(res.getString("dgnrid"));
//			                 od.setDgnrid(res.getString("dgnrid"));
		            		 }
//		            	 }
//		            	 else
//		            	 {
//		            		 if(null!=res.getString("dgnlid")&&null==res.getString("dgnrid"))
//		            		 {
//		            			 dg.setDgnlnm(pdtnm);
//		            		 }
//		            		 else if(null!=res.getString("dgnrid")&&null==res.getString("dgnlid"))
//		            		 {
//		            			 dg.setDgnrnm(pdtnm);
//		            		 }
//		            	 } 
		            	 dg.setDgnpulwire(res.getString("dgnpulwire"));
			             dg.setDgnairvent(res.getString("dgnairvent"));
			             dg.setDgnvoswitch(res.getString("dgnvoswitch"));
			             dg.setDgnsdmd(res.getString("dgnsdmd"));
			             dg.setDgnid(res.getString("dgnid"));
		            
		             }
                     //订单明细，查询tblfolio表内的订单信息
		             String sql3="select h.feeleft,h.feeright,h.isleft,h.isright,h.delayleft,h.delayright,h.folurgent,d.fdtypname,i.gctnm,h.foldelay,h.foldps,h.folnt,h.foldt,h.foltype,(case when s.aab300='惠耳听力总部' then s.aab300 else i.gctnm end) as gctnm from tblfolio h left join tblgrpclient i on h.folctid=i.gctid left join sc01 s on h.folctid=s.bsc001 left join tblfoldetail d on d.fdtfno=h.folno where h.folno='"+order.getFolno()+"'";  

 	
		             pstm = con.prepareStatement(sql3);
		             res = pstm.executeQuery();
		             while(res.next())
		             {
		          
		            	 order.setisleft(res.getString("isleft"));		           
		            	 order.setisright(res.getString("isright"));		    
		            	 order.setdelayleft(res.getString("delayleft"));
		            	 order.setdelayright(res.getString("delayright"));
			             order.setFolurgent(res.getString("folurgent"));
			             order.setFoldelay(res.getString("foldelay"));
			             order.setDgndoc(res.getString("fdtypname"));
			             int feeleft = res.getInt("feeleft");
			             int feeright = res.getInt("feeright");
			             order.setfeeleft(feeleft);
			             order.setfeeright(feeright);
			             if(null!=fdtmklr)
			             {
			            	 if(fdtmklr.equals("0")){
				             order.setFolldps(Double.parseDouble(res.getString("foldps")));
			            	 }else{
				             order.setFolrdps(Double.parseDouble(res.getString("foldps")));
			            	 }
			             }
			             order.setFolnt(res.getString("folnt"));
			             order.setFoldt(DateUtil.converToDate(res.getString("foldt"))); 
			             order.setGctnm(res.getString("gctnm"));
			             order.setFoltype(res.getString("foltype"));
		             }
		             
		            
		            SingleClient dto = new SingleClient();
		            dto.setIctid(fdtcltid);
					dto.setFileKey("ord07_000");// 个人客户详细信息
					Object ci = find(con, dto, null, 0);
		            sc.setIctid(fdtcltid);
		 			sc.setFileKey("clt03_000");// 听力图详细信息
					Object agList = find(con, sc, null, 0);
					DBUtil.commit(con);
//					Object diagnose=dg;
					retmap.put("beo1", ci);
					retmap.put("beo2", agList);
					retmap.put("beo3", dg);
					retmap.put("beo4", od);
					retmap.put("beo5", order);
					response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "printCustomDetail",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
	}
		
		/*
		 * 显示耳模维修订单
		 */
		public ResponseEnvelop printEarRep(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
	        ResultSet res = null;
	    	HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				con=DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				String grCli=(String)map.get("grCli");
				if(!"1501000000".equals(grCli) && order.getTp2().equals("m"))
				{
					String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
					pstm = con.prepareStatement(sql0);
		            res = pstm.executeQuery();
		            while(res.next())
		            {
		            	if(!(order.getFolsta().equals("uncommited")||order.getFolsta().equals("back")))
		            	{
		            		order.setTp2("false");
		            		retmap.put("beo6", order);
							response.setBody(retmap);
		            		return response;
		            	}
		            }
				}
				order.setFileKey("ord11_022");
				Object od=find(con,order,null,0);
//				EarMould em=new EarMould();
//				em.setTmeno(order.getFolno());
//				em.setFileKey("ord11_020");
//				Object ear=find(con,em,null,0);
				if(null!=order.getFolindctid()&&!"".equals(order.getFolindctid()))
				{
				SingleClient dto = new SingleClient();
	            dto.setIctid(order.getFolindctid());
				dto.setFileKey("ord07_000");// 个人客户详细信息
				Object ci = find(con, dto, null, 0);
	            dto.setIctid(order.getFolindctid());
	 			dto.setFileKey("clt03_000");// 听力图详细信息
				Object agList = find(con, dto, null, 0);
				retmap.put("beo1", ci);
				retmap.put("beo2", agList);
				}
				 DBUtil.commit(con);
				
//			}
			   
//				retmap.put("beo4", ear);
				retmap.put("beo5", od); 
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "printEarDetail",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				try {
					if(null != res )
					{
						res.close();
					}
					if(null!=pstm)
					{
						pstm.close();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		
		
		/*
		 * 显示助听器维修订单
		 */
		public ResponseEnvelop printCusRep(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
	        ResultSet res = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				String grCli=(String)map.get("grCli");
//				OrderDetail od=new OrderDetail();
				Repair rep=new Repair();
				SingleClient sc=new SingleClient();
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				if(!"1501000000".equals(grCli) && order.getTp2().equals("m"))
				{
					String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
					pstm = con.prepareStatement(sql0);
		            res = pstm.executeQuery();
		            while(res.next())
		            {
//		            	if(res.getString("folsta").equals("pass")||res.getString("folsta").equals("commited"))
		            	if(!(order.getFolsta().equals("uncommited")||order.getFolsta().equals("back")))
		            	{
		            		order.setTp2("false");
		            		retmap.put("beo6", order);
							response.setBody(retmap);
		            		return response;
		            	}
		            }
				}
				
			    order.setFileKey("ord11_016");
			    Object repair=find(con, order, null, 0);
//		        if(!"repair".equals(order.getFoltype()))
//		        {
		            
			        
			        if((null==order.getFolindctid() || "".equals(order.getFolindctid())) && (null==order.getCltnm() || "".equals(order.getCltnm()))) 
			        {
			        	throw new AppException("用户信息不完整，无法显示");
			        }
			        if((null==order.getFolindctid() || "".equals(order.getFolindctid())) && (null!=order.getCltnm() && !"".equals(order.getCltnm())))
			        {
			        	String sqlfid = "select ictid from tblindclient where ictnm='"+order.getCltnm()+"' and ictgctid='"+order.getFolctid()+"'";
			        	pstm = con.prepareStatement(sqlfid);
			            res = pstm.executeQuery();
			            int ncount=0;
			             while(res.next())
			             {
			            	 order.setFolindctid(res.getString("ictid"));
			            	 ncount++;
			             }
			             if(ncount>1 || (null==order.getFolindctid() || "".equals(order.getFolindctid()))){
			            	 throw new AppException("用户信息不完整，无法显示");
			             }
			        }
		            SingleClient dto = new SingleClient();
		            dto.setIctid(order.getFolindctid());
					dto.setFileKey("ord07_000");// 个人客户详细信息
					Object ci = find(con, dto, null, 0);
		            sc.setIctid(order.getFolindctid());
		 			sc.setFileKey("clt03_000");// 听力图详细信息
					Object agList = find(con, sc, null, 0);
					DBUtil.commit(con);
//					Object diagnose=dg;
					retmap.put("beo1", ci);
					retmap.put("beo2", agList);
//		        }
					retmap.put("beo3", repair);
//					retmap.put("beo4", od);
//					retmap.put("beo5", order);
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
				try {
					if(null!=res)
					{
					  res.close();
					}
					if(null!=pstm)
					{
					  pstm.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		/*
		 * 显示耳模订单
		 */
		
		public ResponseEnvelop printEarDetail(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
	        ResultSet res = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				con=DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				String grCli=(String)map.get("grCli");
				if(!"1501000000".equals(grCli) && order.getTp2().equals("m"))
				{
					String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
					pstm = con.prepareStatement(sql0);
		            res = pstm.executeQuery();
		            while(res.next())
		            {
//		            	if(res.getString("folsta").equals("pass")||res.getString("folsta").equals("commited"))
		            	if(!(order.getFolsta().equals("uncommited")||order.getFolsta().equals("back")))
		            	{
		            		order.setTp2("false");
		            		retmap.put("beo6", order);
							response.setBody(retmap);
		            		return response;
		            	}
		            }
				}
				if(null!=order.getFolindctid()&&!"".equals(order.getFolindctid()))
				{
				SingleClient dto = new SingleClient();
	            dto.setIctid(order.getFolindctid());
				dto.setFileKey("ord07_000");// 个人客户详细信息
				Object ci = find(con, dto, null, 0);
	            dto.setIctid(order.getFolindctid());
	 			dto.setFileKey("clt03_000");// 听力图详细信息
				Object agList = find(con, dto, null, 0);
				DBUtil.commit(con);
//				Object diagnose=dg;
				retmap.put("beo1", ci);
				retmap.put("beo2", agList);
				}
//				order.setFileKey("ord11_016");
//				Object ord=find(con,order,null,0);
				order.setFileKey("ord11_022");
				Object od=find(con,order,null,0);
//				EarMould em=new EarMould();
//				em.setTmeno(order.getFolno());
//				em.setFileKey("ord11_020");
//				Object ear=find(con,em,null,0);
//				Earm
//				String sql1="select l.fdtprc,l.fdtqnt from tblfoldetail l where l.fdtfno='"+order.getFolno()+"'";  	
//	             pstm = con.prepareStatement(sql1);
//	             res = pstm.executeQuery();
//	             while(res.next())
//	             {
//	            	
//	             }
				
//			    Object repair=find(con, order, null, 0);
//				retmap.put("beo4", ear);
				retmap.put("beo5", od); 
//					retmap.put("beo3", repair);
////					retmap.put("beo4", od);
////					retmap.put("beo5", order);
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "printEarDetail",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
//				DBUtil.rollback(con);
				try {
					if(null != res )
					{
						res.close();
					}
					if(null!=pstm)
					{
						pstm.close();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		
		public ResponseEnvelop printNomDetail(RequestEnvelop request){
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
	        ResultSet res = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				con=DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				order.setFileKey("ord11_025");
				Object od=find(con,order,null,0);
//				if(order.getTp2().equals("m"))
//				{
//					String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
//					pstm = con.prepareStatement(sql0);
//		            res = pstm.executeQuery();
//		            while(res.next())
//		            {
//		            	if(res.getString("folsta").equals("pass")||res.getString("folsta").equals("commited"))
//		            	{
//		            		order.setTp2("false");
//		            		retmap.put("beo6", order);
//							response.setBody(retmap);
//		            		return response;
//		            	}
//		            }
//				}
//				order.setFileKey("ord11_016");
//				Object ord=find(con,order,null,0);
//				order.setFileKey("ord11_024");
//				Object od=find(con,order,null,0);
//				EarMould em=new EarMould();
//				em.setTmeno(order.getFolno());
//				em.setFileKey("sta01_000");   //明细查询
//				Object ear=find(con,em,null,0);
//				Earm
//				String sql1="select l.fdtprc,l.fdtqnt from tblfoldetail l where l.fdtfno='"+order.getFolno()+"'";  	
//	             pstm = con.prepareStatement(sql1);
//	             res = pstm.executeQuery();
//	             while(res.next())
//	             {
//	            	
//	             }
				
//			    Object repair=find(con, order, null, 0);
//				retmap.put("beo4", ear);
				retmap.put("beo6", od); 
//					retmap.put("beo3", repair);
////					retmap.put("beo4", od);
////					retmap.put("beo5", order);
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "print",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
//				DBUtil.rollback(con);
				try {
					if(null!=res)
					{
					res.close();
					}
					if(null!=pstm)
					{
						pstm.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		/*
		 * 显示定制机退机订单
		 */
		public ResponseEnvelop printCusRec(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			PreparedStatement pstm = null;
	        ResultSet res = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("beo");
				String grCli=(String)map.get("grCli");
//				OrderDetail od=new OrderDetail();
				Repair rep=new Repair();
				SingleClient sc=new SingleClient();
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				if(!"1501000000".equals(grCli) && order.getTp2().equals("m"))
				{
					String sql0="select o.folsta from tblfolio o where o.folno='"+order.getFolno()+"'";
					pstm = con.prepareStatement(sql0);
		            res = pstm.executeQuery();
		            while(res.next())
		            {
//		            	if(res.getString("folsta").equals("pass")||res.getString("folsta").equals("commited"))
		            	if(!(order.getFolsta().equals("uncommited")||order.getFolsta().equals("back")))
		            	{
		            		order.setTp2("false");
		            		retmap.put("beo6", order);
							response.setBody(retmap);
		            		return response;
		            	}
		            }
				}
				
				order.setFileKey("chg02_009");
				Object ci = find(con, order, null, 0);
//			    order.setFileKey("ord11_016");
//			    Object repair=find(con, order, null, 0);
//		             
//		            
//		            SingleClient dto = new SingleClient();
//		            dto.setIctid(order.getFolindctid());
//					dto.setFileKey("ord07_000");// 个人客户详细信息
//					Object ci = find(con, dto, null, 0);
//		            sc.setIctid(order.getFolindctid());
//		 			sc.setFileKey("clt03_000");// 听力图详细信息
//					Object agList = find(con, sc, null, 0);
//					DBUtil.commit(con);
//					Object diagnose=dg;
					retmap.put("beo1", ci);
//					retmap.put("beo2", agList);
//					retmap.put("beo3", repair);
//					retmap.put("beo4", od);
//					retmap.put("beo5", order);
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
				try {
					if(null!=res)
					{
					  res.close();
					}
					if(null!=pstm)
					{
					  pstm.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
	/*
	 * 	订单审核通过
	 */
		
		public ResponseEnvelop examine(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			CallableStatement proc = null;
			String retTmksid = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order ord = (Order) map.get("ord");
				OrderDetail od = (OrderDetail) map.get("od");
				Repair rep = (Repair) map.get("rep");
				String opr = (String)map.get("opr");
				EarMould em = (EarMould)map.get("em");
				String type=(String)map.get("type");
//				ord.setFileKey("ord11_001");// 修改主订单状态
				ord.setFolsta("pass");
				ord.setFolopr(opr);
				
				boolean flag=true;
				if(null != ord.getFoltype() && ord.getFoltype().equals("nom")){
//					ord.setFileKey("ord11_002");// 普通订单修改主订单状态的状态为“未配货”
//					String[] pidList = (String[]) map.get("pidList");
					/*
					 * 普通订单审核时最小库存量控制
					 */
					List<OrderDetail> odList =(List<OrderDetail>)map.get("odList");
					for(int i=1;i<=odList.size();i++)
					{
						String sql1="select distinct sum(s.stoamount*s.stoactype)over(partition by s.stoproid) as amount from tblsto s where s.stogrcliid='1501000000' and s.stoproid='"+odList.get(i-1).getFdtpid()+"'";		
						String sql2="select t.pdtminsto from tblproduct t where t.pdtid='"+odList.get(i-1).getFdtpid()+"'";
						List stoAmoList=(Vector)DBUtil.querySQL(con, sql1);
						Integer stoAmount=null;
						if(0!=stoAmoList.size())
						{
						    stoAmount=Integer.parseInt((((HashMap)stoAmoList.get(0)).get("1").toString()));
						    /*if(stoAmount==0)
						    {
						    	odList.get(i-1).setMinstoeq0(String.valueOf(i));
						    	flag=false;
						    }*/
						}
						else
						{
							stoAmount=0;
							odList.get(i-1).setMinstoeq0(String.valueOf(i));
					    	//flag=false;//20130517取消最小库存限制
//							odList.get(i-1).setMinstoi(String.valueOf(i));
////							odList.get(i-1).setStoAmo(stoAmount);
//							retmap.put("beo", odList);
//							response.setBody(retmap);
//		            		return response;
						}
						List minAmoList=(Vector)DBUtil.querySQL(con, sql2);
						Integer minAmount=null;
						if(null!=((HashMap)minAmoList.get(0)).get("1"))
						{
							minAmount = Integer.parseInt(((HashMap)minAmoList.get(0)).get("1").toString());
				            Integer qnt=odList.get(i-1).getFdtqnt();
				            if(0>(stoAmount-qnt))
				            {
				            	odList.get(i-1).setMinstoles0(String.valueOf(i));
				            }
				         
				            else if(minAmount>(stoAmount-qnt))
							{
								
								odList.get(i-1).setMinstoi(String.valueOf(i));
								odList.get(i-1).setMinsto(minAmount);	
			            		
							}
						}
						odList.get(i-1).setStoAmo(stoAmount);
						
					}
					
//					for(OrderDetail odl:odList)	
//					{
////						if(null!=odl.getMinstoi()&&!"".equals(odl.getMinstoi()))
//						if(odl.getStoAmo()==0)
//						{
//							flag=false;
//						}
//					}
				
					if(flag)
					{
						ord.setFolsta("waiting");
						ord.setFolexdt(DateUtil.getDate());
						ord.setFileKey("ord11_027");
						modify(con, ord, null, 0);  
						DBUtil.commit(con);
					}
					retmap.put("beo", odList);
					response.setBody(retmap);
            		return response;

				}
				else if(null !=ord.getFoltype() && ord.getFoltype().equals("repair"))
				{
//					Repair rep=new Repair();
//					rep.setRepfolid(ord.getFolno());
//					rep.setFileKey("ord11_028");
					
					
					if(rep.getRepfolid()==null||"".equals(rep.getRepfolid()))
					{
						throw new AppException("维修订单号不能为空(请通知开发人员)！");
					}
					if(null != ord.getRepcpy() && ord.getRepcpy().equals("惠耳")){
						rep.setFileKey("ord11_028");   //tblrep状态改为等待维修
					}else{
						rep.setFileKey("ord11_029");//tblrep状态改为厂修
					}
					modify(con, rep, null, 0);  
//					store(con, rep, null, 0);
				}
				else if(null != ord.getFoltype() && ord.getFoltype().equals("repairEar")){
					rep.setRepfolid(ord.getFolno());
//					rep.setFileKey("ord11_028");
//					modify(con,rep,null,0);
					store(con, rep, null, 0);
				}
				else if(null != ord.getFoltype() && ord.getFoltype().equals("make"))
				{

					if(type.equals("h")||type.equals("j"))
					{
					Customization cz=new Customization();
					// 生成定制机条形码
					proc = con.prepareCall("{ call PRC_CREATE_TMKSID(?, ?, ?) }");
//					if (null != od.getFdtsheltpl() && !"".equals(od.getFdtsheltpl())) {
					if(od.getFdtmklr().equals("0")){
						proc.setInt(1, 0);
						cz.setTmkplr("0");
						cz.setTmkpid(od.getDgnlid());
//						ord.setFileKey("ord11_003");// 修改诊断表状态
						
					} 
//					else if(null !=od.getFdtsheltpr()&&!"".equals(od.getFdtsheltpr())){
					else if(od.getFdtmklr().equals("1")){
						proc.setInt(1, 1);
						cz.setTmkplr("1");
						cz.setTmkpid(od.getDgnrid());
//						ord.setFileKey("ord11_004");// 修改诊断表状态
					}
//					modify(con, ord, null, 0);
					proc.registerOutParameter(2, Types.VARCHAR);
					proc.setString(3, type);
					proc.execute();
					retTmksid = proc.getString(2);
					cz.setTmksid(retTmksid);
					cz.setTmkfno(ord.getFolno());
					cz.setTmkcltid(ord.getIctid());
					cz.setTmkurg(ord.getFolurgent());
					cz.setFileKey("ord11_005");
					store(con, cz, null, 0);
					}
					else if(type.equals("c"))
					{
						ord.setFolsta("waiting");
					}
				}
				else if(null != ord.getFoltype() && ord.getFoltype().equals("Ear")){
					proc = con.prepareCall("{ call PRC_CREATE_TMESID(?) }");
					proc.registerOutParameter(1, Types.VARCHAR);
					proc.execute();
					String retTmesid = proc.getString(1);
					em.setTmeno(ord.getFolno());
					em.setTmesid(retTmesid);
					od.setFdtsid(retTmesid);
//					em.setTmepid(od.getFdtpid());
					
					em.setTmecls(od.getFdtcls());
//					em.setTmegctnm(ord.getFolctnm());
					em.setTmectid(ord.getFolctid());
					em.setTmecltid(od.getFdtcltid());
					em.setTmecltnm(od.getFdtcltnm());
					em.setTmemat(od.getFdtmat());
					em.setTmefree(od.getFdtfree());
					em.setTment(od.getFdtinnt());
					store(con,em,null,0);
//					modify(con,em,null,0);
					ord.setFolexdt(DateUtil.getDate());
					ord.setFileKey("ord11_027");
					modify(con, ord, null, 0);  
					od.setFileKey("ord11_030");
					modify(con, od, null, 0);
					DBUtil.commit(con);
					retmap.put("tmesid", retTmesid);
					response.setBody(retmap);
	        		return response;
					
				}
				else if(null != ord.getFoltype() && ord.getFoltype().equals("makeRec")){
				         ord.setFolsta("recpass");
				}
				ord.setFolexdt(DateUtil.getDate());
				ord.setFileKey("ord11_027");
				modify(con, ord, null, 0);  
				DBUtil.commit(con);
			
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "examine",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
	}
		
		
		/**
		 * 新增订单基本信息
		 */
		public ResponseEnvelop examineDis(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			HashMap map = (HashMap) request.getBody();
			try
				{
				Collection<DisExamine> collection = (Collection<DisExamine>) map.get("collection");
//				DisExamine die = (DisExamine) map.get("die");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				// 获取订单号
//				List result = (Vector) DBUtil.querySQL(con,
//						"select SEQ_FOLNO.NEXTVAL from dual");
//				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
//				String folno = id.toString();
				for(DisExamine dto : collection){
					dto.setTdeisexa("1");
					dto.setFileKey("ord12_004");
					// 插入新订单
					modify(con, dto, null, 0);
					Order order=new Order();
					order.setFolno(dto.getTdefolno());
					order.setFolsta("commited");             //总部已审核扣率
					order.setFileKey("ord11_027");
					modify(con, order, null, 0);
				}
			
				
				DBUtil.commit(con);

//				HashMap<String, Object> retmap = new HashMap<String, Object>();
//				retmap.put("folno", dto.getFolno());
//				retmap.put("beo", dto);
//				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "savePerson",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		/*
		 * 扣率审核回退(non-Javadoc)
		 * @see org.radf.apps.order.facade.OrderFacade#Disback(org.radf.plat.sieaf.envelop.RequestEnvelop)
		 */
		public ResponseEnvelop Disback(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			HashMap map = (HashMap) request.getBody();
			try
			{
			DisExamine die = (DisExamine) map.get("die");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			die.setTdeisexa("2");
			die.setFileKey("ord12_004");
			modify(con, die, null, 0);
			Order order=new Order();
			order.setFolno(die.getTdefolno());
			order.setFolsta("disback");             //disback  扣率审核回退的状态
			order.setFileKey("ord11_027");
			modify(con, order, null, 0);
			
			
			DBUtil.commit(con);

//			HashMap<String, Object> retmap = new HashMap<String, Object>();
//			retmap.put("folno", dto.getFolno());
//			retmap.put("beo", dto);
//			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			return response;
		}
		/*
		 * 订单审核回退
		 */
		public ResponseEnvelop rollBack(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			CallableStatement proc = null;
//			String retTmksid = null;
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order ord = (Order) map.get("ord");
//				OrderDetail od = (OrderDetail) map.get("od");
//				OrderDetail od = (OrderDetail) map.get("od");
				ord.setFileKey("ord11_006");// 修改主订单状态
				modify(con, ord, null, 0);  
//				if (od.getFdtsheltpl()!=null) 
//				{
//				   ord.setFileKey("ord11_007");// 修改诊断表状态
//				}
//				else
//				{
//					ord.setFileKey("ord11_008");// 修改诊断表状态
//				}
//				modify(con, ord, null, 0);
				
//				Customization cz=new Customization();
//				// 生成定制机条形码
//					proc = con.prepareCall("{ call PRC_CREATE_TMKSID(?, ?, ?) }");
//					if (od.getFdtsheltpl()!=null) {
//						proc.setInt(1, 0);
//						cz.setTmkplr("0");
//						cz.setTmkpid(od.getDgnlid());
//					} else{
//						proc.setInt(1, 1);
//						cz.setTmkplr("1");
//						cz.setTmkpid(od.getDgnrid());
//					}
//					proc.registerOutParameter(2, Types.VARCHAR);
//					proc.setString(3, "h");
//					proc.execute();
//					retTmksid = proc.getString(2);
//					cz.setTmksid(retTmksid);
//					cz.setTmkfno(ord.getFolno());
//					cz.setTmkcltid(ord.getIctid());
////					cz.setTmkpid(od.getFdtpid());
//					cz.setTmkurg(ord.getFolurgent());
//					cz.setFileKey("ord11_003");
//					store(con, cz, null, 0);
				DBUtil.commit(con);
//				HashMap<String, Object> retmap = new HashMap<String, Object>();
//				retmap.put("beo", ci);
//				retmap.put("beo2", ci2);
//				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "rollBack",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
	}
		
		
		public ResponseEnvelop addCustomOrder(RequestEnvelop request,Connection con,ResponseEnvelop response,int type)
		{
			try {
				
			BigDecimal[] price = new BigDecimal[2];
			HashMap map = (HashMap) request.getBody();
			HashMap<String, String> retmap = new HashMap<String, String>();
			List<OrderDetail> odList=(Vector<OrderDetail>)map.get("odList");
			String gid = (String) map.get("gid");
			String opr = (String) map.get("opr");
			String isExa = (String)map.get("isExa");
			Diagnose dg=(Diagnose)map.get("diagnose");
			Order header=(Order)map.get("order");
			String md = (String) map.get("md");
			String lr = (String) map.get("lr");
			Order order=new Order();
			Integer num = odList.size();
//			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
			// 生成订单号
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String tmkfno1 = id.toString();
			String tmkfno2="";
			// 生成订单号
		
			for (int i = 0; i < num; i++) {
				// 录入售价
				result = (Vector) DBUtil.querySQL(con,
						"select pdtprc from tblproduct where pdtid='"
								+ odList.get(i).getFdtpid() + "'");
				price[i] = (BigDecimal) ((HashMap) result.get(0)).get("1");
			}
//			header.setFolctid(gid); // 团体客户
			header.setFoldt(DateUtil.getDate()); // 订单创建日期

//			header.setFolno(tmkfno1); // 订单号
			header.setFolsta("uncommited"); // 定制机订单生成时状态为"定制中"
			header.setFoltype("make");// 订单类别为"定制机订单"
			//header.setFolopr(opr);
			if(header.getFolurgent().equals("1"))
		    {
				header.setFolurgfee(100.00);
			}
			else if(header.getFolurgent().equals("0"))
			{
				header.setFolurgfee(0.00);
			}
			if (num == 1) {     
				
				header.setFolno(tmkfno1); // 订单号
				odList.get(0).setFdtfno(tmkfno1);// 订单号
//				detail.setFdtcltid(odList.get(0).getFdtcltid());//个人编号
//				detail.setFdtcltnm(odList.get(0).getFdtcltnm()); // 个人用户
//				detail.setFdtpid(odList.get(0).getFdtpid()); // 耳机代码
				odList.get(0).setFdtprc(price[0].doubleValue());
				odList.get(0).setFdtqnt(1);// 数量为1
				odList.get(0).setFdtsqnt(1);
				odList.get(0).setFdtdprc(price[0].doubleValue());//原价
				
				
				
				if(odList.get(0).getFdtmklr().equals("0"))//0为左耳，1为右耳
				{
				    dg.setDgnldprc(price[0].doubleValue());
				    dg.setDgnlfdtfno(tmkfno1);

					odList.get(0).setFdtprc(dg.getDgncldprc());//左耳售价
				    //odList.get(0).setFdtdisco(dg.getDgnldct());//左耳扣率
					header.setFoldps(header.getFolldps());//左耳定金
					
				}
				else
				{
					dg.setDgnrdprc(price[0].doubleValue());
					dg.setDgnrfdtfno(tmkfno1);

					odList.get(0).setFdtprc(dg.getDgncrdprc());//右耳售价
					//odList.get(0).setFdtdisco(dg.getDgnrdct());//右耳扣率
					header.setFoldps(header.getFolrdps());//右耳定金
					
				}
				
				store(con, header, null, 0);
				store(con, odList.get(0), null, 0);
				
				if(type==1)            //1为提交
				{
					if(null!=isExa&&"y".equals(isExa))
					{
						DisExamine de=new DisExamine();
						de.setTdefolno(tmkfno1);
						de.setTdepdtid(odList.get(0).getFdtpid());
						de.setTdedis(odList.get(0).getFdtdisc());
						de.setTdegctid(gid);
						de.setTdeictid(odList.get(0).getFdtcltid());
						de.setTdeisexa("0");
						de.setTdedt(DateUtil.getDate());
						de.setTdedmindis(Double.parseDouble(md));
						store(con, de, null, 0);
						order.setFolsta("wtdis");  //等待总部审核扣率
						order.setFolno(tmkfno1);
						order.setFileKey("ord11_027");// 修改主订单状态
						modify(con, order, null, 0); 
						odList.get(0).setFdtisspdisc("1");
						
					}
					else
					{
						order.setFolsta("commited"); 
						order.setFolno(tmkfno1);
						order.setFileKey("ord11_027");// 修改主订单状态
						modify(con, order, null, 0);  //出错
					}
//					if (odList.get(0).getFdtsheltpl()!=null) 
//					{
//					   order.setFileKey("ord11_010");// 修改诊断表状态
//					}
//					else
//					{
//						order.setFileKey("ord11_011");// 修改诊断表状态
//					}
//					modify(con, order, null, 0);
					
					
				}
				
			} else if (num == 2) {
					odList.get(0).setFdtfno(tmkfno1);// 订单号
					odList.get(0).setFdtprc(dg.getDgncldprc());//左耳售价
					odList.get(0).setFdtqnt(1);// 数量为1
					odList.get(0).setFdtsqnt(1);
					odList.get(0).setFdtdprc(price[0].doubleValue());
					//dg.setDgnldprc(price[0].doubleValue());
					dg.setDgnlfdtfno(tmkfno1);
					header.setFolno(tmkfno1);
					header.setFoldps(header.getFolldps());//左耳定金
					String isright = header.getisright();//将右耳的续保费信息取出来，然后清除。
					int feeright = header.getfeeright();
					String delayright = header.getdelayright();
					int rightpay = header.getRightpay();
					header.setisright("");
					header.setfeeright(0);
					header.setdelayright("");
					header.setRightpay(0);
					store(con, header, null, 0);
					
					result = (Vector) DBUtil.querySQL(con,
							"select SEQ_FOLNO.NEXTVAL from dual");
					id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					tmkfno2 = id.toString();
					odList.get(1).setFdtfno(tmkfno2);// 订单号
					odList.get(1).setFdtprc(dg.getDgncrdprc());//右耳售价
					odList.get(1).setFdtqnt(1);// 数量为1
					odList.get(1).setFdtsqnt(1);
					odList.get(1).setFdtdprc(price[1].doubleValue());
					dg.setDgnrfdtfno(tmkfno2);                   
					header.setFolno(tmkfno2);
					header.setFoldps(header.getFolrdps());//右耳定金
					header.setisright(isright);//存入右耳详细信息，移除左耳信息
					header.setfeeright(feeright);
					header.setdelayright(delayright);
					header.setRightpay(rightpay);
					header.setisleft("");
					header.setfeeleft(0);
					header.setdelayleft("");
					header.setLeftpay(0);
					store(con, header, null, 0);
					store(con,odList.get(0), null, 0);
					store(con, odList.get(1), null, 0);
					if(type==1)            //1为提交
					{
						//[start]
						if(null!=isExa&&"y".equals(isExa))
						{
//							String [] lrs=null;
							if(lr.indexOf(',')==-1)
							{
//								 lrs=lr.split(",");
//							}
								if(lr.equals("0"))
								{
									DisExamine de1=new DisExamine();
									de1.setTdefolno(tmkfno1);
									de1.setTdepdtid(odList.get(0).getFdtpid());
									de1.setTdedis(odList.get(0).getFdtdisc());
									de1.setTdegctid(gid);
									de1.setTdeictid(odList.get(0).getFdtcltid());
									de1.setTdeisexa("0");
									de1.setTdedt(DateUtil.getDate());
									de1.setTdedmindis(Double.parseDouble(md));
									store(con, de1, null, 0);
								
									order.setFolsta("wtdis");  //等待总部审核扣率
									order.setFolno(tmkfno1);
									order.setFileKey("ord11_027");// 修改主订单状态
									modify(con, order, null, 0);  
								
									order.setFolsta("commited"); 
									order.setFolno(tmkfno2);
									order.setFileKey("ord11_027");// 修改主订单状态
									modify(con, order, null, 0);  
									odList.get(0).setFdtisspdisc("1");
								}
								else if(lr.equals("1"))
								{
									DisExamine de2=new DisExamine();
									de2.setTdefolno(tmkfno2);
									de2.setTdepdtid(odList.get(1).getFdtpid());
									de2.setTdedis(odList.get(1).getFdtdisc());
									de2.setTdegctid(gid);
									de2.setTdeictid(odList.get(1).getFdtcltid());
									de2.setTdeisexa("0");
									de2.setTdedt(DateUtil.getDate());
									de2.setTdedmindis(Double.parseDouble(md));
									store(con, de2, null, 0);
								
									order.setFolsta("wtdis");  //等待总部审核扣率
									order.setFolno(tmkfno2);
									order.setFileKey("ord11_027");// 修改主订单状态
									modify(con, order, null, 0); 
								
									order.setFolsta("commited"); 
									order.setFolno(tmkfno1);
									order.setFileKey("ord11_027");// 修改主订单状态
									modify(con, order, null, 0); 
									odList.get(1).setFdtisspdisc("1");
								}
							}
							
							else 
							{
								String [] mds=null;
								if(md.indexOf(',')!=-1)
								{
									 mds=md.split(",");
								}
								DisExamine de1=new DisExamine();
								de1.setTdefolno(tmkfno1);
								de1.setTdepdtid(odList.get(0).getFdtpid());
								de1.setTdedis(odList.get(0).getFdtdisc());
								de1.setTdegctid(gid);
								de1.setTdeictid(odList.get(0).getFdtcltid());
								de1.setTdeisexa("0");
								de1.setTdedt(DateUtil.getDate());
								de1.setTdedmindis(Double.parseDouble(mds[0]));
								store(con, de1, null, 0);
								odList.get(0).setFdtisspdisc("1");
								
								DisExamine de2=new DisExamine();
								de2.setTdefolno(tmkfno2);
								de2.setTdepdtid(odList.get(1).getFdtpid());
								de2.setTdedis(odList.get(1).getFdtdisc());
								de2.setTdegctid(gid);
								de2.setTdeictid(odList.get(1).getFdtcltid());
								de2.setTdeisexa("0");
								de2.setTdedt(DateUtil.getDate());
								de2.setTdedmindis(Double.parseDouble(mds[1]));
								store(con, de2, null, 0);
								odList.get(1).setFdtisspdisc("1");
								
								order.setFolsta("wtdis");  //等待总部审核扣率
								order.setFolno(tmkfno1);
								order.setFileKey("ord11_027");// 修改主订单状态
								modify(con, order, null, 0);  
//								order.setFolsta("wtdis");  //等待总部审核扣率
								order.setFolno(tmkfno2);
								order.setFileKey("ord11_027");// 修改主订单状态
								modify(con, order, null, 0); 
								
							}
							
							
						}
						else
						{
							order.setFolsta("commited"); 
							order.setFolno(tmkfno1);
							order.setFileKey("ord11_027");// 修改主订单状态
							modify(con, order, null, 0);  
//							order.setFolsta("commited"); 
							order.setFolno(tmkfno2);
							order.setFileKey("ord11_027");// 修改主订单状态
							modify(con, order, null, 0);  
						}
						//[end]
					}
					
					
//					store(con,odList.get(0), null, 0);
//					store(con, odList.get(1), null, 0);
//					retmap.put("tmkfno2", tmkfno2);
			}
			
			dg.setDgndt(DateUtil.getDate());
			store(con,dg,null,0);    
//			DBUtil.commit(con);
			retmap.put("tmkfno1", tmkfno1);
			retmap.put("tmkfno2", tmkfno2);
			response.setBody(retmap);
			} catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "addCustomOrder",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}
//			} finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
			return response;
		}
		/**
		 * 保存定制订单
		 */
		public ResponseEnvelop saveCustomOrder(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			addCustomOrder(request,con,response,0);
			DBUtil.commit(con);
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
		
		/*
		 * 提交订单
		 */
	public ResponseEnvelop commit(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Order ord = (Order)map.get("order");
			Repair rep = (Repair) map.get("rep");
//			EarMould em = (EarMould) map.get("em");
			OrderDetail orderDetail=(OrderDetail)map.get("od");
			String folurgent = (String) map.get("folurgent");
//			List<EarMould> emList = (List<EarMould>) map.get("emList");
			List<OrderDetail> odList = (List<OrderDetail>) map.get("odList");
			String type = (String) map.get("type");
			// OrderDetail od = (OrderDetail) map.get("od");
			if (null != ord) {
				if (null != ord.getFolno()) // ?
				{

//					ord.setFileKey("ord11_009");// 修改主订单状态
//					modify(con, ord, null, 0);
					updateCusOrder(con, request);
				} else {
					response = addCustomOrder(request, con, response, 1);
				}
			} else if (null != rep) {
				if (null != rep.getRepfolid()) {
					Order order = new Order();
					order.setFolno(rep.getRepfolid());		
					String sql = "select i.folsta from tblfolio i where i.folno='"+rep.getRepfolid()+"'";
					PreparedStatement pstm = null;
					ResultSet res = null;
					pstm = con.prepareStatement(sql);
		            res = pstm.executeQuery();
		            String folsta="";
		            while(res.next())
		            {
		            	folsta=res.getString("folsta");
		            }
					if(folsta.equals("back") || folsta.equals("uncommited")||folsta.equals("disback") ||folsta.equals("wtddis"))
					{
						order.setFileKey("ord11_009");
						modify(con, order, null, 0);
						updateCusRepOrder(con, request);
					}
					else
					{
					}
					
					String string="abd";
					rep.setFileKey("ord11_017");
					modify(con, rep, null, 0); 
					rep.setFileKey("ord11_018");
					modify(con, rep, null, 0);
					
				} else {
					response = addCusRep(request, con, response, 1);
				}
			} 
			else if (null != orderDetail) {
				if (null != orderDetail.getFdtfno()) {
					Order order = new Order();
					order.setFolno(orderDetail.getFdtfno());
					String sql = "select i.folsta from tblfolio i where i.folno='"+orderDetail.getFdtfno()+"'";
					PreparedStatement pstm = null;
					ResultSet res = null;
					pstm = con.prepareStatement(sql);
		            res = pstm.executeQuery();
		            String folsta="";
		            while(res.next())
		            {
		            	folsta=res.getString("folsta");
		            }
					if(folsta.equals("back") || folsta.equals("uncommited")||folsta.equals("disback") ||folsta.equals("wtddis"))
					{
						order.setFileKey("ord11_009");
						modify(con, order, null, 0);
						updateEarRepOrder(con, request);
					}
					else
					{
					}
					
					OrderDetail od=new OrderDetail();
					od.setFdtpid(orderDetail.getFdtpid());
					od.setFdtinnt(orderDetail.getFdtinnt());
					od.setFdtsid(orderDetail.getFdtsid());
					od.setFdtfno(orderDetail.getFdtfno());
					String sqlup="select l.fdtpid from tblfoldetail l where l.fdtfno='"+orderDetail.getFdtfno()+"'";
					List list=(Vector)DBUtil.querySQL(con, sqlup);
					String oldpid=((HashMap)list.get(0)).get("1").toString();
					od.setOldpid(oldpid);
					od.setFileKey("ord12_002");
					modify(con,od,null,0);
					
				} else {
					response = addEarRep(request, con, response, 1);
				}
			} 
			else if (null != odList) {
					if (null != odList.get(0).getFdtfno()) {
							Order order = new Order();
							order.setFolno(odList.get(0).getFdtfno());	
						if(type.equals("ear"))
						{
							updateEarOrder(con, request);
							order.setFolurgent(folurgent);
						}
						else if(type.equals("nom"))
						{
							updateNomOrder(con, request);
						}
						String sql = "select i.folsta from tblfolio i where i.folno='"+odList.get(0).getFdtfno()+"'";
						PreparedStatement pstm = null;
						ResultSet res = null;
						pstm = con.prepareStatement(sql);
			            res = pstm.executeQuery();
			            String folsta="";
			            while(res.next())
			            {
			            	folsta=res.getString("folsta");
			            }
						if(folsta.equals("back") || folsta.equals("uncommited")||folsta.equals("disback") ||folsta.equals("wtddis"))
						{
							order.setFileKey("ord11_009");
							modify(con, order, null, 0);
						}
						else
						{
						}
						
					} else {
						if(type.equals("ear"))
						{
							response = addEar(request, con, response, 1);
						}
						else if(type.equals("nom"))
						{
						    response = addNom(request, con, response, 1);
						}
					}
				}
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
		
		
		public ResponseEnvelop commitCusRep(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Order ord = (Order) map.get("order");
				Repair rep=(Repair)map.get("rep");
//				OrderDetail od = (OrderDetail) map.get("od");
				if(null!=ord)
				{
				  if(null!=ord.getFolno())         //?
				  {
					
					ord.setFileKey("ord11_009");// 修改主订单状态
					modify(con, ord, null, 0);  
//					if (od.getFdtsheltpl()!=null) 
//					{
//					  ord.setFileKey("ord11_010");// 修改诊断表状态
//					
//					}
//					else
//					{
//						 ord.setFileKey("ord11_011");// 修改诊断表状态
//					}
//				     modify(con, ord, null, 0);
//					update(request);
					updateCusOrder(con,request);
				  }
				  else
				  {
					addCustomOrder(request,con,response,1);
				   }
				}
				else if(null!=rep)
				{
					if(null!=rep.getRepfolid())
					{
						rep.setFileKey("ord11_009");
						modify(con, rep, null, 0);
						updateCusRepOrder(con,request);
					}
					else{
						addCusRep(request,con,response,1);
					}
				}
				DBUtil.commit(con);
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
		
		public ResponseEnvelop update(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				updateCusOrder(con,request);
				DBUtil.commit(con);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		
		
		
		/*
		 * 公共方法
		 */
		public void updateCusOrder(Connection con,RequestEnvelop request) {
//			Connection con = null;
			try {
//				con = DBUtil.getConnection();
				HashMap map = (HashMap) request.getBody();
				Order order = (Order) map.get("order");
				Diagnose dg = (Diagnose) map.get("dg");
				List<OrderDetail>  odList = (List<OrderDetail>)map.get("odList");
				String isExa = (String) map.get("isExa");
				String md = (String) map.get("md");
				String aga = (String) map.get("aga");
				String tp = (String) map.get("tp");
				if(order.getisleft().equals("1")){
					order.setLeftpay(0);
				}else{
					order.setLeftpay(order.getfeeleft());
				}
				if(order.getisright().equals("1")){
					order.setRightpay(0);
				}else{
					order.setRightpay(order.getfeeright());
				}
				order.setFileKey("ord11_012");
				modify(con, order, null, 0); 
				dg.setDgnid(order.getFolno());   //设置订单号（where条件）
				dg.setFileKey("ord11_014");
				modify(con, dg, null, 0); 
				PreparedStatement pstm = null;
		        ResultSet res = null;
		        String tdeisexa="";
				for(OrderDetail od:odList)
				{
					od.setFileKey("ord11_013");
					modify(con, od, null, 0); 
				}
				if(null!=isExa&&"y".equals(isExa))
				{
					String sql1="select d.tdeisexa from tbldisexa d where d.tdefolno='"+order.getFolno()+"' and d.tdepdtid='"+odList.get(0).getFdtpid()+"'";
					List result1=(Vector)DBUtil.querySQL(con, sql1);
					if(result1.size()>0)
					{
						tdeisexa=(String)((HashMap)result1.get(0)).get("1");
						if(null!=tdeisexa&&!"".equals(tdeisexa)&&(tdeisexa.equals("1")||(tdeisexa.equals("2")&&(null==aga||"".equals(aga)))))
						{
							order.setFolsta("commited"); 
//							order.setFolno(tmkfno1);
							order.setFileKey("ord11_027");// 修改主订单状态
							modify(con, order, null, 0);  
						}
						if(null!=tdeisexa&&!"".equals(tdeisexa)&&tdeisexa.equals("2")&&"a".equals(aga))
						{
							order.setFolsta("wtdis"); 
							order.setFileKey("ord11_027");// 修改主订单状态
							modify(con, order, null, 0);
							order.setFileKey("ord11_031");// 修改主订单状态
							modify(con, order, null, 0);
						}
				
						else if(null==tdeisexa||"".equals(tdeisexa))
						{
						
						DisExamine de=new DisExamine();
						de.setTdefolno(order.getFolno());
						de.setTdepdtid(odList.get(0).getFdtpid());
						de.setTdedis(odList.get(0).getFdtdisc());
						de.setTdegctid(order.getFolctid());
						de.setTdeictid(odList.get(0).getFdtcltid());
						de.setTdeisexa("0");
						de.setTdedt(DateUtil.getDate());
						de.setTdedmindis(Double.parseDouble(md));
						store(con, de, null, 0);
						order.setFolsta("wtdis");  //等待总部审核扣率
						//order.setFolno(tmkfno1);
						order.setFileKey("ord11_027");// 修改主订单状态
						modify(con, order, null, 0);  
						}
					}
					else
					{
						DisExamine de=new DisExamine();
						de.setTdefolno(order.getFolno());
						de.setTdepdtid(odList.get(0).getFdtpid());
						de.setTdedis(odList.get(0).getFdtdisc());
						de.setTdegctid(order.getFolctid());
						de.setTdeictid(odList.get(0).getFdtcltid());
						de.setTdeisexa("0");
						de.setTdedt(DateUtil.getDate());
						de.setTdedmindis(Double.parseDouble(md));
						store(con, de, null, 0);
						order.setFolsta("wtdis");  //等待总部审核扣率
						//order.setFolno(tmkfno1);
						order.setFileKey("ord11_027");// 修改主订单状态
						modify(con, order, null, 0);  
					}
						
//						String sql0="merge into tblfolio o using select distinct s.tdefolno from tbldisexa s where s.tdeisexa='1')a on (o.folno=a.tdefolno)when matched then"+
//						" update tblfolio o set o.folsta='commited' where o.folno='"+order.getFolno()+"' when not matched then"+
//					    "insert into tbldisexa(tdefolno,tdepdtid,tdegctid,tdeictid,tdedis,tdeisexa,tdedt,tdedmindis) values('"+order.getFolno()+"','"+odList.get(0).getFdtpid()+"','"+order.getFolctid()+"','"+odList.get(0).getFdtcltid()+"',"+odList.get(0).getFdtdisc()+","+"'0',"+"to_date('" +DateUtil.getDate()+"','yyyy-MM-DD'),"+Double.parseDouble(md)+")" +
//					    "update tblfolio o set o.folsta='wtdis' where o.folno='"+order.getFolno()+"';";
//						pstm = con.prepareStatement(sql0);
//		        		pstm.executeUpdate(sql0);		
						  
//					}
				}
				else
				{
					String sql = "select i.folsta from tblfolio i where i.folno='"+order.getFolno()+"'";
					pstm = con.prepareStatement(sql);
		            res = pstm.executeQuery();
		            String folsta="";
		            while(res.next())
		            {
		            	folsta=res.getString("folsta");
		            }
					if (tp.equals("b")) {  //若该订单的特殊扣率总部已审核通过，这状态应为总部已审核扣率
					    if(folsta.equals("back"))
					    {
					    	order.setFolsta("uncommited");
					    }
					    else 
					    {
					    	order.setFolsta(folsta);
						}	 
					}
					else if (tp.equals("c"))
					{
						if(folsta.equals("back") || folsta.equals("uncommited")||folsta.equals("disback") ||folsta.equals("wtddis"))
						{
							order.setFolsta("commited"); 
						}
						else
						{
							order.setFolsta(folsta);
						}
					}
//					order.setFolno(tmkfno1);
					order.setFileKey("ord11_027");// 修改主订单状态
					modify(con, order, null, 0);  
				}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}

		public ResponseEnvelop updateCusRep(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				HashMap map = (HashMap) request.getBody();
				Repair rep = (Repair) map.get("rep");
//				Diagnose dg = (Diagnose) map.get("dg");
				
				rep.setFileKey("ord11_017");
				modify(con, rep, null, 0); 
				rep.setFileKey("ord11_018");
				modify(con, rep, null, 0); 
				DBUtil.commit(con);
				} catch (Exception ex) {
					ex.printStackTrace();
					response.setHead(ExceptionSupport(className, "modify",
							ManageErrorCode.SQLERROR, ex.getMessage(), request
									.getHead()));
				} finally {
					DBUtil.rollback(con);
					DBUtil.closeConnection(con);
				}
				return response;
		}
		
		
		public ResponseEnvelop updateEar(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				updateEarOrder(con,request);
				DBUtil.commit(con);
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "updateEar",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} 
			finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		} 
				return response;
	}
		
		
		public ResponseEnvelop updateEarRep(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				updateEarRepOrder(con,request);
				DBUtil.commit(con);
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "updateEar",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} 
			finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		} 
				return response;
	}
		
		public ResponseEnvelop updateNom(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				updateNomOrder(con,request);
				DBUtil.commit(con);
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "modify",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} 
			finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		} 
				return response;
	}
		/*
		 * 公共方法
		 */
		public void updateNomOrder(Connection con,RequestEnvelop request) {
			try {
				HashMap map = (HashMap) request.getBody();
				List<OrderDetail> odList = (Vector<OrderDetail>) map.get("odList");
				String chk=(String)map.get("chk");
//				chk=chk.substring(chk.indexOf(",")+1,chk.length());
				String[] checked=null;
			    if(chk.indexOf(",")!=-1)
			    {
			    	checked=chk.split(",");
			    }
//			    int size=odList.size();
//			    int k=0;
//			    for(;k<checked.length;k++)
//			    {
//			    	if(Integer.parseInt(checked[k])>size)
//			    	{
//			    		break;
//			    	}
//			    }
				PreparedStatement pstm = null;
		        ResultSet res = null;
				String sql0="select l.fdtpid from tblfoldetail l where l.fdtfno='"+odList.get(0).getFdtfno()+"'";
				pstm = con.prepareStatement(sql0);
		        res = pstm.executeQuery();

//		        for(int i=1;i<=odList.size();i++)
//		        {
		        int i=1;
		        int j=1;
		        while(res.next())
		        {
//		        	res.next();
		        	if(j>checked.length)
		        	{
		        		break;
		        	}
		        	String oldpid=res.getString("fdtpid");
		        	if(Integer.parseInt(checked[j])==i)
		        	{
		        		odList.get(j-1).setOldpid(oldpid);
		        		modify(con, odList.get(j-1), null, 0); 
		        		j++;
		        	}
		        	i++;
		        } 
		        res.close();
		        pstm.close();
		        
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
//				// 生成订单号
//				List result = (Vector) DBUtil.querySQL(con,
//						"select SEQ_FOLNO.NEXTVAL from dual");
//				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
//				String folno = id.toString();
//                Order header=new Order();
				// 订单基本信息
//				header.setFolctid(rep.getRepgctid()); // 团体客户
//				header.setFoldt(DateUtil.getDate()); // 订单创建日期
//				header.setFolno(folno); // 订单号
//				header.setFolsta("uncommited"); // 定制机订单生成时状态为"维修中"
//				header.setFoltype("normal");// 订单类别为"维修机订单"
//				header.setFolopr(rep.getRepreger());//?
//				header.setFolindctid(rep.getRepcltid());
//				for(OrderDetail od:odList)
//				{
//					od.setFdtfno(folno);
//				}
					 
				// 插入订单明细
//                modify(con, odList, null, 0);  没有这种写法
//				for(OrderDetail od:odList)
//				{     
//					String sql0="select l.fdtpid  from tblfoldetail l where l.fdtfno='"+od.getFdtfno()+"'";
//					 modify(con, od, null, 0);  
//				}
					
//				store(con, odList, null, 0);
//				store(con, header, null, 0);
//				DBUtil.commit(con);
//				if(type==1)
//				{
//
//					header.setFileKey("ord11_009");// 修改主订单状态
//					modify(con, header, null, 0);  
//				}
//				HashMap<String, Object> retmap = new HashMap<String, Object>();
//				retmap.put("folno", folno);   
//				response.setBody(retmap);
//			} catch (AppException ae) {
//				response
//						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				ex.printStackTrace();
//				response.setHead(ExceptionSupport(className, "saveDetail",
//						ManageErrorCode.SQLERROR, ex.getMessage(), request
//								.getHead()));
			} 
//			finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
//			return response;
		}
		
		
		
		
		/*
		 * 公共方法
		 */
		public void updateCusRepOrder(Connection con,RequestEnvelop request) {
			try {
				HashMap map = (HashMap) request.getBody();
				Repair rep = (Repair) map.get("rep");
//				Diagnose dg = (Diagnose) map.get("dg");
				
				rep.setFileKey("ord11_017");
				modify(con, rep, null, 0); 
				rep.setFileKey("ord11_018");
				modify(con, rep, null, 0); 
				} catch (Exception ex) {
					ex.printStackTrace();
				} 
		}
		
		/*
		 * 公共方法
		 */
		public void updateEarOrder(Connection con,RequestEnvelop request) {
			try
			{
//			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
//			EarMould em = (EarMould) map.get("em");
			List<OrderDetail> odList=(List<OrderDetail>)map.get("odList");
//			Integer qnt = (Integer) map.get("qnt");
			String folurgent = (String)map.get("folurgent");
//			String folno = (String)map.get("folno");
			//Order order = (Order)map.get("order");
//			em.setFileKey("ord11_021");
//			modify(con,em,null,0);
			
//			Order order = new Order();
//			order.setFolurgent(folurgent);
//			order.setFolno(orderDetail.getFdtfno());
//			order.setFileKey("ord11_012");
//			modify(con,order,null,0);
			OrderDetail orderDetail=odList.get(0);
			OrderDetail od=new OrderDetail();
			od.setFdtpid(orderDetail.getFdtpid());
//			od.setFdtcltid(orderDetail.getFdtcltid());
			od.setFdtprc(orderDetail.getFdtprc());
			od.setFdtfree(orderDetail.getFdtfree());
			od.setFdtinnt(orderDetail.getFdtinnt());
			od.setFdtcls(orderDetail.getFdtcls());
//			od.setFdtdprc(Double.parseDouble(em.getTmeprc()));
//			od.setFdtqnt(qnt);
			od.setFdtfno(orderDetail.getFdtfno());
			String sql="select l.fdtpid from tblfoldetail l where l.fdtfno='"+orderDetail.getFdtfno()+"'";
			List list=(Vector)DBUtil.querySQL(con, sql);
			String oldpid=((HashMap)list.get(0)).get("1").toString();
			od.setOldpid(oldpid);
			od.setFileKey("ord11_023");
			modify(con,od,null,0);
//			rep.setFileKey("ord11_017");
//			modify(con, rep, null, 0); 
//			rep.setFileKey("ord11_018");
//			modify(con, rep, null, 0); 
//			DBUtil.commit(con);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
//			finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			} 
		}
		
		
		/*
		 * 公共方法
		 */
		public void updateEarRepOrder(Connection con,RequestEnvelop request) {
			try
			{
//			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
//			EarMould em = (EarMould) map.get("em");
			OrderDetail orderDetail=(OrderDetail)map.get("od");
//			Integer qnt = (Integer) map.get("qnt");
//			String folurgent = (String)map.get("folurgent");
//			String folno = (String)map.get("folno");
			//Order order = (Order)map.get("order");
//			em.setFileKey("ord11_021");
//			modify(con,em,null,0);
			
//			Order order = new Order();
//			order.setFolurgent(folurgent);
//			order.setFolno(orderDetail.getFdtfno());
//			order.setFileKey("ord11_012");
//			modify(con,order,null,0);
			
			OrderDetail od=new OrderDetail();
			od.setFdtpid(orderDetail.getFdtpid());
//			od.setFdtcltid(orderDetail.getFdtcltid());
//			od.setFdtprc(orderDetail.getFdtprc());
//			od.setFdtfree(orderDetail.getFdtfree());
			od.setFdtinnt(orderDetail.getFdtinnt());
//			od.setFdtcls(orderDetail.getFdtcls());
			od.setFdtsid(orderDetail.getFdtsid());
//			od.setFdtdprc(Double.parseDouble(em.getTmeprc()));
//			od.setFdtqnt(qnt);
			od.setFdtfno(orderDetail.getFdtfno());
			String sql="select l.fdtpid from tblfoldetail l where l.fdtfno='"+orderDetail.getFdtfno()+"'";
			List list=(Vector)DBUtil.querySQL(con, sql);
			String oldpid=((HashMap)list.get(0)).get("1").toString();
			od.setOldpid(oldpid);
			od.setFileKey("ord12_002");
			modify(con,od,null,0);
//			od.setFileKey("ord12_002");
//			modify(con,od,null,0);
//			rep.setFileKey("ord11_017");
//			modify(con, rep, null, 0); 
//			rep.setFileKey("ord11_018");
//			modify(con, rep, null, 0); 
//			DBUtil.commit(con);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
//			finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			} 
		}
		/*
		 * 保存助听器维修订单
		 */
		public ResponseEnvelop saveCusRep(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				addCusRep(request,con,response,2);
				DBUtil.commit(con);
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
		
		/*
		 * 公共方法
		 */
		public ResponseEnvelop addCusRep(RequestEnvelop request,Connection con,ResponseEnvelop response,int type)
		{
			try {
				
				HashMap map = (HashMap) request.getBody();
				Repair rep = (Repair) map.get("rep");
				Order header = new Order();
				OrderDetail detail = new OrderDetail();
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
				// 生成订单号
				List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_FOLNO.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String repfno = id.toString();

				// 订单基本信息
				header.setFolctid(rep.getRepgctid()); // 团体客户
				header.setFoldt(DateUtil.getDate()); // 订单创建日期
				header.setFolno(repfno); // 订单号
				header.setFolsta("uncommited"); // 定制机订单生成时状态为"维修中"
				header.setFoltype("repair");// 订单类别为"维修机订单"
				//header.setFolopr(rep.getRepreger());//?     应该在审核通过后再记录审核员姓名
				header.setFolindctid(rep.getRepcltid());
				// 订单明细
				detail.setFdtfno(repfno);// 订单号
				detail.setFdtcltnm(rep.getRepcltnm()); // 个人用户
				detail.setFdtpid(rep.getReppid()); // 耳机代码
				detail.setFdtqnt(1);// 数量为1
				detail.setFdtsqnt(1);
				
                
				rep.setRepdate(DateUtil.getDate());
				rep.setRepfolid(repfno);
				rep.setFileKey("ord11_015");
				store(con, rep, null, 0);  
				header.setFileKey("order_insert");
				store(con, header, null, 0);
				detail.setFileKey("orderDetail_insert");
				store(con, detail, null, 0);
				if(type==1)
				{

					header.setFileKey("ord11_009");// 修改主订单状态
					modify(con, header, null, 0);  
				}
//				DBUtil.commit(con);
				HashMap retmap = new HashMap();
				retmap.put("repfno", repfno);         
			response.setBody(retmap);
			} catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "save",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}
//			} finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
			return response;
		}
		
		/*
		 * 公共方法
		 */
		public ResponseEnvelop addNom(RequestEnvelop request,Connection con,ResponseEnvelop response,int type)
		{
			try {
				HashMap map = (HashMap) request.getBody();
				List<OrderDetail> odList = (Vector<OrderDetail>) map.get("odList");
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
				// 生成订单号
				List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_FOLNO.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String folno = id.toString();
                Order header=new Order();
				// 订单基本信息
//				header.setFolctid(rep.getRepgctid()); // 团体客户
				header.setFoldt(DateUtil.getDate()); // 订单创建日期
				header.setFolno(folno); // 订单号
				header.setFolsta("uncommited"); // 定制机订单生成时状态为"维修中"
				header.setFoltype("normal");// 订单类别为"维修机订单"
				header.setFolctid(odList.get(0).getFdtcltid());
//				header.setFolopr(rep.getRepreger());//?
//				header.setFolindctid(rep.getRepcltid());
				for(OrderDetail od:odList)
				{
					od.setFdtfno(folno);
				}
					 
				// 插入订单明细
				store(con, odList, null, 0);
				store(con, header, null, 0);
//				DBUtil.commit(con);
				if(type==1)
				{

					header.setFileKey("ord11_009");// 修改主订单状态
					modify(con, header, null, 0);  
				}
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("folno", folno);   
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "addNom",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} 
//			finally {
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
			return response;
		}
		
		/**
		 * 新增耳模信息
		 */
		public ResponseEnvelop saveEar(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
			   con = DBUtil.getConnection();
			   DBUtil.beginTrans(con);
			   addEar(request,con,response,2);
			   DBUtil.commit(con);
		    } catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveEar",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			return response;
		}
		/*
		 * 新增耳膜维修订单
		 */
		
		public ResponseEnvelop saveEarRep(RequestEnvelop request)
		{
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
			   con = DBUtil.getConnection();
			   DBUtil.beginTrans(con);
			   addEarRep(request,con,response,2);
			   DBUtil.commit(con);
		    } catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveEar",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			return response;
		}
		
        /*
         * 公共方法
         */
		public ResponseEnvelop addEar(RequestEnvelop request,Connection con,ResponseEnvelop response,int type)
		{
//			CallableStatement proc = null;
//			String retTmesid = null;
			try
			{
				HashMap map = (HashMap) request.getBody();
				List<OrderDetail> detailList = (Vector<OrderDetail>) map.get("odList");
//				Integer num = emList.size();
//				String gid = emList.get(0).getTmectid();
				String opr = (String) map.get("opr");
				String urgent = (String) map.get("urgent");
				String grCli = (String) map.get("grCli");
//				Double price = Double.valueOf(prc);
//				Integer earcount = Integer.valueOf((String) map.get("num"));
				
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
				// 生成订单号
				//List result = (Vector) DBUtil.querySQL(con,
					//	"select SEQ_FOLNO.NEXTVAL from dual");//select max(folno) from tblfolio
				//String tmefno = (String) ((HashMap) result.get(0)).get("1");
				//Integer id = Integer.valueOf(tmefno) + 1;
				//tmefno = String.format("%06d", id);
				//BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				//String tmefno = id.toString();
				// 生成耳模条形码
				
				// 要填充到Order表中的信息
			    List<Order> headerList = new Vector<Order>();
			    
			 // 要填充到OrderDetail表中的信息
//				List<OrderDetail> detailList = new Vector<OrderDetail>();
				for (OrderDetail od : detailList)
				{
					List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
					BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String tmefno = id.toString();
					/*proc = con.prepareCall("{ call PRC_CREATE_TMESID(?) }");
					proc.registerOutParameter(1, Types.VARCHAR);
					proc.execute();
					retTmesid = proc.getString(1);*/
//					em.setTmesid(" ");
//					em.setTmeno(tmefno);
					
					Order header = new Order();
					header.setFolctid(grCli);// 团体客户代码
					header.setFoldt(DateUtil.getDate()); // 订单创建日期
					header.setFolno(tmefno); // 订单号
					header.setFolsta("uncommited"); // 耳模订单生成时状态为"未提交"
					header.setFoltype("makeEar");// 订单类别为"定制订单"
					header.setFolindctid(od.getFdtcltid());
					//header.setFolopr(opr);//folopr应该在审核后输入审核员姓名
					header.setFolurgent(urgent);
					if(header.getFolurgent().equals("1"))
				    {
						header.setFolurgfee(50.00);
					}
					else if(header.getFolurgent().equals("0"))
					{
						header.setFolurgfee(0.00);
					}
					headerList.add(header);
					
//					OrderDetail detail = new OrderDetail();
					od.setFdtfno(tmefno);// 订单号
//					detail.setFdtcltid(em.getTmecltid());//个人客户代码
//					detail.setFdtcltnm(emList.get(0).getTmecltnm());// 客户姓名
//					detail.setFdtprc(price);// 单价
//					detail.setFdtdprc(price);
//					detail.setFdtpid(emList.get(0).getTmepid());
//					detail.setFdtqnt(earcount);// 订购数量
					//detail.setFdtsqnt(earcount);// 发货数量
//					detailList.add(detail);
//					if(type==1)
//					{
//							Order ord=new Order();
//							ord.setFolno(tmefno);
//							ord.setFileKey("ord11_009");// 修改主订单状态
//						    modify(con, ord, null, 0);  
//					}
				}

				//////header.setFileKey("order_insert");
				// 在tblearmaking表中插入记录 即耳模信息
//				store(con, emList, null, 0);
				// 在tblfolio表中插入记录 即订单信息
				store(con, headerList, null, 0);
				// 在tblfoldetail表中插入记录 即订单详细信息
				store(con, detailList, null, 0);
				if(type==1)
				{
					for (OrderDetail od : detailList)
					{
						Order ord=new Order();
						ord.setFolno(od.getFdtfno());
						ord.setFileKey("ord11_009");// 修改主订单状态
					    modify(con, ord, null, 0);  
					}
				}
//				DBUtil.commit(con);
				HashMap<String, String> retmap = new HashMap<String, String>();
				String fno="";
				for (Order em : headerList){
					fno=fno+em.getFolno()+',';
				}
				fno=fno.substring(0, fno.length()-1);
				retmap.put("tmeno", fno);
				retmap.put("earworkString", "新增耳模信息");
				response.setBody(retmap);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "addEar",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}
//			finally
//			{
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
			return response;
		}
		
		
		
		 /*
         * 公共方法
         */
		public ResponseEnvelop addEarRep(RequestEnvelop request,Connection con,ResponseEnvelop response,int type)
		{
//			CallableStatement proc = null;
//			String retTmesid = null;
			try
			{
				HashMap map = (HashMap) request.getBody();
//				List<OrderDetail> detailList = (Vector<OrderDetail>) map.get("detailList");
				OrderDetail od = (OrderDetail) map.get("od");
//				Integer num = emList.size();
//				String gid = emList.get(0).getTmectid();
				String opr = (String) map.get("opr");
//				String urgent = (String) map.get("urgent");
				String grCli = (String) map.get("grCli");
//				Double price = Double.valueOf(prc);
//				Integer earcount = Integer.valueOf((String) map.get("num"));
				
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
				// 生成订单号
				//List result = (Vector) DBUtil.querySQL(con,
					//	"select SEQ_FOLNO.NEXTVAL from dual");//select max(folno) from tblfolio
				//String tmefno = (String) ((HashMap) result.get(0)).get("1");
				//Integer id = Integer.valueOf(tmefno) + 1;
				//tmefno = String.format("%06d", id);
				//BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				//String tmefno = id.toString();
				// 生成耳模条形码
				
				// 要填充到Order表中的信息
//			    List<Order> headerList = new Vector<Order>();
			    
			 // 要填充到OrderDetail表中的信息
//				List<OrderDetail> detailList = new Vector<OrderDetail>();
//				for (OrderDetail od : detailList)
//				{
					List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
					BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String repfno = id.toString();
					/*proc = con.prepareCall("{ call PRC_CREATE_TMESID(?) }");
					proc.registerOutParameter(1, Types.VARCHAR);
					proc.execute();
					retTmesid = proc.getString(1);*/
//					em.setTmesid(" ");
//					em.setTmeno(tmefno);
					
					Order header = new Order();
					header.setFolctid(grCli);// 团体客户代码
					header.setFoldt(DateUtil.getDate()); // 订单创建日期
					header.setFolno(repfno); // 订单号
					header.setFolsta("uncommited"); // 耳模订单生成时状态为"未提交"
					header.setFoltype("repairEar");// 订单类别为"定制订单"
					header.setFolindctid(od.getFdtcltid());
					//header.setFolopr(opr);//folopr应该在审核后输入审核员姓名
//					header.setFolurgent(urgent);
//					headerList.add(header);
					
//					OrderDetail detail = new OrderDetail();
					od.setFdtfno(repfno);// 订单号
//					detail.setFdtcltid(em.getTmecltid());//个人客户代码
//					detail.setFdtcltnm(emList.get(0).getTmecltnm());// 客户姓名
//					detail.setFdtprc(price);// 单价
//					detail.setFdtdprc(price);
//					detail.setFdtpid(emList.get(0).getTmepid());
//					detail.setFdtqnt(earcount);// 订购数量
					//detail.setFdtsqnt(earcount);// 发货数量
//					detailList.add(detail);
//				}

				//////header.setFileKey("order_insert");
				// 在tblearmaking表中插入记录 即耳模信息
//				store(con, emList, null, 0);
				// 在tblfolio表中插入记录 即订单信息
				store(con, header, null, 0);
				// 在tblfoldetail表中插入记录 即订单详细信息
				store(con, od, null, 0);
				if(type==1)
				{
//					for (OrderDetail od : detailList)
//					{
//						Order ord=new Order();
//						ord.setFolno(od.getFdtfno());
						header.setFileKey("ord11_009");// 修改主订单状态
					    modify(con, header, null, 0); 
//					}
				}
//				DBUtil.commit(con);
				HashMap<String, String> retmap = new HashMap<String, String>();
//				String fno="";
//				for (Order em : headerList){
//					fno=fno+em.getFolno()+',';
//				}
//				fno=fno.substring(0, fno.length()-1);
				retmap.put("tmeno", repfno);
//				retmap.put("earworkString", "新增耳模信息");
				response.setBody(retmap);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "addEar",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}
//			finally
//			{
//				DBUtil.rollback(con);
//				DBUtil.closeConnection(con);
//			}
			return response;
		}
		
		/*
		 * 新增普通订单
		 */
		public ResponseEnvelop saveNom(RequestEnvelop request)
		{
			
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
			   con = DBUtil.getConnection();
			   DBUtil.beginTrans(con);
			   addNom(request,con,response,2);
			   DBUtil.commit(con);
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
		
		/*
		 * 增加设备维修订单
		 */
		public ResponseEnvelop saveDevRep(RequestEnvelop request)
		{
			
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try
			{
			   con = DBUtil.getConnection();
			   DBUtil.beginTrans(con);
			   HashMap map = (HashMap) request.getBody();
				Repair rep = (Repair) map.get("rep");
				List<OrderDetail> odList=(List<OrderDetail>)map.get("odList");
				Order header = new Order();
//				OrderDetail detail = new OrderDetail();
//				con = DBUtil.getConnection();
//				DBUtil.beginTrans(con);
				// 生成订单号
				List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_FOLNO.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String repfno = id.toString();

				// 订单基本信息
//				header.setFolctid(rep.getRepgctid()); // 团体客户
				header.setFoldt(DateUtil.getDate()); // 订单创建日期
				header.setFolno(repfno); // 订单号
				header.setFolsta("repairing"); // 定制机订单生成时状态为"维修中"
				header.setFoltype("repairDev");// 订单类别为"维修机订单"
				//header.setFolopr(rep.getRepreger());//?
//				header.setFolindctid(rep.getRepcltid());
				// 订单明细
				for(OrderDetail od:odList)
				{
					od.setFileKey("ord12_001");
					store(con,od,null,0);
				}
					
//				detail.setFdtfno(repfno);// 订单号
//				detail.setFdtcltnm(rep.getRepcltnm()); // 个人用户
//				detail.setFdtpid(rep.getReppid()); // 耳机代码
//				detail.setFdtqnt(1);// 数量为1
//				detail.setFdtsqnt(1);
               
				rep.setRepdate(DateUtil.getDate());
				rep.setRepfolid(repfno);
				rep.setFileKey("ord12_000");
				store(con, rep, null, 0);  
				header.setFileKey("order_insert");
				store(con, header, null, 0);
//				detail.setFileKey("orderDetail_insert");
//				store(con, detail, null, 0);
//				if(type==1)
//				{
//
//					header.setFileKey("ord11_009");// 修改主订单状态
//					modify(con, header, null, 0);  
//				}
				DBUtil.commit(con);
				HashMap retmap = new HashMap();
				retmap.put("repfno", repfno);         
			response.setBody(retmap);
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
		
		
		public ResponseEnvelop queryFoldt(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				HashMap map = (HashMap) request.getBody();
			    Customization cz = (Customization) map.get("cz");
				cz.setFileKey("ord12_003");
				Object fol = find(con, cz, null, 0);
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("fol", fol);
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "queryFoldt",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}


	public ResponseEnvelop queryMinDis(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			String grid = (String) map.get("grid");
			List<OrderDetail> odList = (ArrayList<OrderDetail>) map
					.get("odList");
			String folno = (String) map.get("folno");
			String discount = (String) map.get("discount");
			Double disco=null;
			if(null!=discount && !"".equals(discount))
			{
			    disco = Double.parseDouble(discount);
			}
			String sql2 = "select g.gctprovince from tblgrpclient g where g.gctid='"
					+ grid + "'";
			List result2 = (Vector) DBUtil.querySQL(con, sql2);
			String gctprovince = (String) ((HashMap) result2.get(0)).get("1");
			for (OrderDetail odl : odList) {
				if (null != folno && !"".equals(folno)) {
					String sql4 = "select d.tdeisexa,tdedis from tbldisexa d where d.tdefolno='"
							+ folno
							+ "' and d.tdepdtid='"
							+ odl.getFdtpid()
							+ "'";
					List result4 = (Vector) DBUtil.querySQL(con, sql4);
					if (result4.size() > 0) {
						String tdeisexa = (String) ((HashMap) result4.get(0))
								.get("1");
						Double tdedis = Double.parseDouble(((HashMap) result4.get(0))
								.get("2").toString());
						if(tdeisexa.equals("1"))
						{
							if(disco<tdedis)
							{
								retmap.put("alert2", "输入的扣率低于总部已审核的扣率!"+tdedis);
								response.setBody(retmap);
								return response;
							}
							if (null != tdeisexa && !"".equals(tdeisexa)
									&& tdeisexa.equals("1")) {
								retmap.put("alert1", "该商品扣率已通过总部审核！");
								response.setBody(retmap);
								return response;
							}
						}
						else if(tdeisexa.equals("2"))
						{
						      if(disco<=tdedis)
						      {
						    	  retmap.put("alert3", "该特殊扣率已被总部回退，比其小的扣率也不能提交！");
									response.setBody(retmap);
									return response;
						    	  //throw new AppException("该特殊扣率已被总部回退，比其小的扣率也不能提交！");
						      }
						      checkDis(con,odl,gctprovince);
						}
					}
					else
					{
						checkDis(con,odl,gctprovince);
					}
					
				} 
				else 
			    {
					 checkDis(con,odl,gctprovince);
//					Double pdtdiscount = null;
//					String sql1 = "select p.pdtdiscoin,p.pdtdiscoout from tblproduct p where p.pdtid='"
//							+ odl.getFdtpid() + "'";
//					List result1 = (Vector) DBUtil.querySQL(con, sql1);
//					if(result1.size()!=0)
//					{
//						if (gctprovince.equals("1")) {
//							if ((null != ((HashMap) result1.get(0)).get("1"))
//								&& !"".equals(((HashMap) result1.get(0))
//										.get("1"))) {
//							pdtdiscount = Double.parseDouble(((HashMap) result1
//									.get(0)).get("1").toString());
//							odl.setTdspvo(pdtdiscount);
//						}
//					} 
//					else 
//					{
//						if ((null != ((HashMap) result1.get(0)).get("2"))
//								&& !"".equals(((HashMap) result1.get(0))
//										.get("2"))) {
//							pdtdiscount = Double.parseDouble(((HashMap) result1
//									.get(0)).get("2").toString());
//							odl.setTdspvo(pdtdiscount);
//						}
//					}
//				 }
//					
//				if (null == pdtdiscount || "".equals(pdtdiscount)) {
//						String sql4 = "select count(*) from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
//								+ odl.getFdtpid() + "')";
//						List result4 = (Vector) DBUtil.querySQL(con, sql4);
//						if (!((HashMap) result4.get(0)).get("1").toString()
//								.equals("0")) {
//							String sql3 = "select d.tdspvoin,d.tdspvoout from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
//									+ odl.getFdtpid() + "')";
//							List result3 = (Vector) DBUtil.querySQL(con, sql3);
//							Double tdspvo = null;
//							if (gctprovince.equals("1")) {
//								if (null != ((HashMap) result3.get(0)).get("1")
//										&& !"".equals(((HashMap) result3.get(0))
//												.get("1"))) {
//									tdspvo = Double
//											.parseDouble(((HashMap) result3
//													.get(0)).get("1")
//													.toString());
//								}
//							} else {
//								if (null != ((HashMap) result3.get(0)).get("2")
//										&& !"".equals(((HashMap) result3.get(0))
//												.get("2"))) {
//									tdspvo = Double
//											.parseDouble(((HashMap) result3
//													.get(0)).get("2")
//													.toString());
//								}
//							}
//							odl.setTdspvo(tdspvo);
//						}
//					}
					if (null == odl.getTdspvo() && !"".equals(odl.getTdspvo())) {
						retmap.put("alert", "总部未设置助听器的最小扣率！");
						response.setBody(retmap);
						return response;
					}
				}

			}
			DBUtil.commit(con);

			retmap.put("odList", odList);
			response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "queryMinDis",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
		
		public void checkDis(Connection con,OrderDetail odl,String gctprovince)throws Exception
		{
			Double pdtdiscount = null;
			String sql1 = "select p.pdtdiscoin,p.pdtdiscoout from tblproduct p where p.pdtid='"
					+ odl.getFdtpid() + "'";
			List result1 = (Vector) DBUtil.querySQL(con, sql1);
			if(result1.size()!=0)
			{
				if (gctprovince.equals("1")) {
					if ((null != ((HashMap) result1.get(0)).get("1"))
						&& !"".equals(((HashMap) result1.get(0))
								.get("1"))) {
					pdtdiscount = Double.parseDouble(((HashMap) result1
							.get(0)).get("1").toString());
					odl.setTdspvo(pdtdiscount);
				}
			} 
			else 
			{
				if ((null != ((HashMap) result1.get(0)).get("2"))
						&& !"".equals(((HashMap) result1.get(0))
								.get("2"))) {
					pdtdiscount = Double.parseDouble(((HashMap) result1
							.get(0)).get("2").toString());
					odl.setTdspvo(pdtdiscount);
				}
			}
		 }
			
		if (null == pdtdiscount || "".equals(pdtdiscount)) {
				String sql4 = "select count(*) from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
						+ odl.getFdtpid() + "')";
				List result4 = (Vector) DBUtil.querySQL(con, sql4);
				if (!((HashMap) result4.get(0)).get("1").toString()
						.equals("0")) {
					String sql3 = "select d.tdspvoin,d.tdspvoout from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
							+ odl.getFdtpid() + "')";
					List result3 = (Vector) DBUtil.querySQL(con, sql3);
					Double tdspvo = null;
					if (gctprovince.equals("1")) {
						if (null != ((HashMap) result3.get(0)).get("1")
								&& !"".equals(((HashMap) result3.get(0))
										.get("1"))) {
							tdspvo = Double
									.parseDouble(((HashMap) result3
											.get(0)).get("1")
											.toString());
						}
					} else {
						if (null != ((HashMap) result3.get(0)).get("2")
								&& !"".equals(((HashMap) result3.get(0))
										.get("2"))) {
							tdspvo = Double
									.parseDouble(((HashMap) result3
											.get(0)).get("2")
											.toString());
						}
					}
					odl.setTdspvo(tdspvo);
				}
				else
				{
					String sql3 = "select d.tdspvoin,d.tdspvoout from tbltypdis d where d.tdsnm='其他'";
					List result3 = (Vector) DBUtil.querySQL(con, sql3);
					Double tdspvo = null;
					if (gctprovince.equals("1")) {
						if (null != ((HashMap) result3.get(0)).get("1")
								&& !"".equals(((HashMap) result3.get(0))
										.get("1"))) {
							tdspvo = Double
									.parseDouble(((HashMap) result3
											.get(0)).get("1")
											.toString());
						}
					} else {
						if (null != ((HashMap) result3.get(0)).get("2")
								&& !"".equals(((HashMap) result3.get(0))
										.get("2"))) {
							tdspvo = Double
									.parseDouble(((HashMap) result3
											.get(0)).get("2")
											.toString());
						}
					}
					odl.setTdspvo(tdspvo);
				}
			}
	    }
		public ResponseEnvelop bill(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				con = DBUtil.getConnection();
				HashMap map = (HashMap) request.getBody();
			    Order od = (Order) map.get("beo");
			    if(null == od.getIsbill()||od.getIsbill().equals("0")||"".equals(od.getIsbill())){
			    	od.setIsbill("1");
			    	od.setFileKey("ord13_001");
			    }else{
			    	od.setIsbill("0");
			    	od.setFileKey("ord13_001");
			    }
				modify(con, od, null, 0);
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				response.setBody(retmap);
			} catch (AppException ae) {
				response
						.setHead(ExceptionSupport(className, ae, request.getHead()));
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "bill",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
}
			

