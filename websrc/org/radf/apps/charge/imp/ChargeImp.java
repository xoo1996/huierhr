package org.radf.apps.charge.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.radf.apps.charge.facade.ChargeFacade;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Store;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class ChargeImp extends IMPSupport implements ChargeFacade{
	
	private String className = this.getClass().getName();
	
	
	public ResponseEnvelop save(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		return response;
		
	}
	
	/**
	 * 普通商品收费基本信息保存
	 */
	public ResponseEnvelop save2(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		Connection con = null;
		try{
			HashMap map = (HashMap) request.getBody();
			List<Charge> dtoList = (Vector<Charge>) map.get("beo");
			String tp = (String) map.get("tp");
			HttpServletRequest req=(HttpServletRequest)map.get("req");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
//			Integer amount=0;
//			String pdtnm = null;
//			String pdtun = null;
////			for (int i = 0; i < dtoList.size(); i++) 
//			for(Charge chg:dtoList)
//			{
//				
//				String sql1 = "select distinct nvl(sum(stoamount*stoactype)over(partition by stoproid,stogrcliid),0) as temp,stoproname,stoamountun from tblsto  where stoproid = '" + chg.getNcdpid() + "'" 
//						+ " and stogrcliid='" + chg.getStogrcliid() + "'";//查询商品剩余量
//				List result0 = (Vector)DBUtil.querySQL(con, sql1);
//				if(result0.size() > 0){
//
//					amount = Integer.parseInt(((HashMap)result0.get(0)).get("1").toString());//某商品代码所对应的商品剩余量
//					pdtnm = ((HashMap)result0.get(0)).get("2").toString();//商品名称
//					if(null == ((HashMap)result0.get(0)).get("3") || "".equals(((HashMap)result0.get(0)).get("3").toString())){//商品单位
//						pdtun = "无";
//					}else{
//						pdtun = ((HashMap)result0.get(0)).get("3").toString() ;
//					}
//					
//				}else{
//					throw new AppException("商品代码为：" + chg.getNcdpid() + "的商品无足够库存！");
////					String msg = pdtnm + "没有足够的库存量！";
//////					throw new AppException(msg);
////					super.saveSuccessfulMsg(req, msg);
////					String forward = "/charge/clientquery.jsp";
////					return mapping.findForward(forward);
//				}
//				
// 				if(amount < chg.getNcdqnt()){
//					String msg = pdtnm + "没有足够的库存量！";
//					throw new AppException(msg);
////					super.saveSuccessfulMsg(req, msg);
////					String forward = "/charge/clientquery.jsp";
////					return mapping.findForward(forward);
////					return go2Page(req, mapping, "charge");
//				}
//			
//			}
			
			
			
			Charge charge = new Charge();
			List resultC = (Vector)DBUtil.querySQL(con, "select SEQ_CHGID.NEXTVAL from dual");
			String id = ((BigDecimal)((HashMap)resultC.get(0)).get("1")).toString();
			charge.setChgid(id);
			charge.setChggcltid(dtoList.get(0).getStogrcliid());
			charge.setChgcltid(dtoList.get(0).getIctid());
			charge.setChgdt(DateUtil.getDate());
			charge.setFileKey("chg01_006");
			store(con, charge, null, 0);
			
//			for(int i = 0 ; i < dtoList.size(); i ++){
//				dtoList.get(i).setChgid(id);
//				dtoList.get(i).setNcdid(id);
			for(Charge chg2:dtoList)
			{
				chg2.setChgid(id);
				chg2.setNcdid(id);
				chg2.setNcdsta("finish");
//				if(null!=chg.getMindisi())
//				{
//					chg.setNcdisspdisc("1");
//				}
			}
			List<Charge> chgList = (Vector<Charge>)req.getSession().getAttribute("chgList");
			if(null!=chgList)
			{
				for(int i=0;i<chgList.size();i++)
				{
					if(null!=chgList.get(i).getMindisi())
					{
						dtoList.get(i).setNcdisspdisc("1");
					}
				}
			}
			// 插入普通商品收费明细
			store(con, dtoList, null, 0);
			

			//库存
			for(int i =0; i< dtoList.size(); i ++)
			{
				List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
				
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall from  tblproduct t join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+dtoList.get(i).getNcdpid()+"'");
				
				String pclid=null;
				if(result2.size() != 0)
				{
				  pclid=(String)(((HashMap)result2.get(0)).get("1"));
				}
				if(null!=pclid && !"".equals(pclid))
				{
					String pcllar = ((HashMap)result2.get(0)).get("2").toString();
					String pclmid = ((HashMap)result2.get(0)).get("3").toString();
					String pclsam = ((HashMap)result2.get(0)).get("4").toString();
					charge.setStoprotype(pclid);
					charge.setStocllar(pcllar);
					charge.setStoclmid(pclmid);
					charge.setStoclsam(pclsam);
				}
				//String stoid = ((BigDecimal)((HashMap)result1.get(0)).get("1")).toString();
				
				//插入库存表
				//charge.setStoid(Integer.parseInt(stoid));
				
				charge.setStoproid(dtoList.get(i).getNcdpid());
				charge.setStoamount(dtoList.get(i).getNcdqnt());
				charge.setStoproname(dtoList.get(i).getStoproname());
				charge.setStoamountun(dtoList.get(i).getStoamountun());
				charge.setStodate(dtoList.get(i).getStodate());
				charge.setStogrcliid(dtoList.get(i).getStogrcliid());
				charge.setStooprcd(dtoList.get(i).getStooprcd());
				charge.setStoactype("-1");
//				charge.setStoproname(pdtnm);
//				charge.setStoamountun(pdtun);
				
				charge.setFileKey("store_insert");
				store(con, charge, null, 0);
			}
			
			DBUtil.commit(con);
//			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("dto", dtoList);
			response.setBody(retmap);
			
		}catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
		
	}
	
	
	/**
	 * 耳背机收费信息保存
	 */
	public ResponseEnvelop save3(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		Connection con = null;
		try{
			HashMap map = (HashMap) request.getBody();
			List<Charge> dtoList = (Vector<Charge>) map.get("beo");
			String tp = (String) map.get("tp");
			HttpServletRequest req=(HttpServletRequest)map.get("req");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
//			Integer amount=0;
//			String pdtnm = null;
//			String pdtun = null;
////			for (int i = 0; i < dtoList.size(); i++) 
//			for(Charge chg:dtoList)
//			{
//				
//				String sql1 = "select distinct nvl(sum(stoamount*stoactype)over(partition by stoproid,stogrcliid),0) as temp,stoproname,stoamountun from tblsto  where stoproid = '" + chg.getNcdpid() + "'" 
//						+ " and stogrcliid='" + chg.getStogrcliid() + "'";//查询商品剩余量
//				List result0 = (Vector)DBUtil.querySQL(con, sql1);
//				if(result0.size() > 0){
//
//					amount = Integer.parseInt(((HashMap)result0.get(0)).get("1").toString());//某商品代码所对应的商品剩余量
//					pdtnm = ((HashMap)result0.get(0)).get("2").toString();//商品名称
//					if(null == ((HashMap)result0.get(0)).get("3") || "".equals(((HashMap)result0.get(0)).get("3").toString())){//商品单位
//						pdtun = "无";
//					}else{
//						pdtun = ((HashMap)result0.get(0)).get("3").toString() ;
//					}
//					
//				}else{
//					throw new AppException("商品代码为：" + chg.getNcdpid() + "的商品无足够库存！");
////					String msg = pdtnm + "没有足够的库存量！";
//////					throw new AppException(msg);
////					super.saveSuccessfulMsg(req, msg);
////					String forward = "/charge/clientquery.jsp";
////					return mapping.findForward(forward);
//				}
//				
// 				if(amount < chg.getNcdqnt()){
//					String msg = pdtnm + "没有足够的库存量！";
//					throw new AppException(msg);
////					super.saveSuccessfulMsg(req, msg);
////					String forward = "/charge/clientquery.jsp";
////					return mapping.findForward(forward);
////					return go2Page(req, mapping, "charge");
//				}
//			
//			}
			
			
			
			Charge charge = new Charge();
			List resultC = (Vector)DBUtil.querySQL(con, "select SEQ_CHGID.NEXTVAL from dual");
			String id = ((BigDecimal)((HashMap)resultC.get(0)).get("1")).toString();
			charge.setChgid(id);
			charge.setChggcltid(dtoList.get(0).getStogrcliid());
			charge.setChgcltid(dtoList.get(0).getIctid());
			charge.setChgdt(DateUtil.getDate());
			charge.setFileKey("chg01_006");
			store(con, charge, null, 0);
			
//			for(int i = 0 ; i < dtoList.size(); i ++){
//				dtoList.get(i).setChgid(id);
//				dtoList.get(i).setNcdid(id);
			for(Charge chg2:dtoList)
			{
				String pdtid=chg2.getNcdpid();
				String sqlqueryid="select t.PDTLMT from tblproduct t where t.pdtid='"+pdtid+"'";
				List resultD = (Vector)DBUtil.querySQL(con, sqlqueryid);
				String pdtlmt = ((BigDecimal)((HashMap)resultD.get(0)).get("1")).toString();
				int pdtmt=Integer.parseInt(pdtlmt);
				chg2.setBaoxiudate(DateUtil.getStepMonth(DateUtil.getDate(),pdtmt));
				chg2.setChgid(id);
				chg2.setNcdid(id);
				chg2.setNcdsta("finish");
				chg2.setFileKey("chg04_019");
//				if(null!=chg.getMindisi())
//				{
//					chg.setNcdisspdisc("1");
//				}
			}
			List<Charge> chgList = (Vector<Charge>)req.getSession().getAttribute("chgList");
			if(null!=chgList)
			{
				for(int i=0;i<chgList.size();i++)
				{
					if(null!=chgList.get(i).getMindisi())
					{
						dtoList.get(i).setNcdisspdisc("1");
					}
				}
			}
			// 插入普通商品收费明细
			store(con, dtoList, null, 0);
			

			//库存
			for(int i =0; i< dtoList.size(); i ++)
			{
				List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
				
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall from  tblproduct t join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+dtoList.get(i).getNcdpid()+"'");
				
				String pclid=null;
				if(result2.size() != 0)
				{
				  pclid=(String)(((HashMap)result2.get(0)).get("1"));
				}
				if(null!=pclid && !"".equals(pclid))
				{
					String pcllar = ((HashMap)result2.get(0)).get("2").toString();
					String pclmid = ((HashMap)result2.get(0)).get("3").toString();
					String pclsam = ((HashMap)result2.get(0)).get("4").toString();
					charge.setStoprotype(pclid);
					charge.setStocllar(pcllar);
					charge.setStoclmid(pclmid);
					charge.setStoclsam(pclsam);
				}
				//String stoid = ((BigDecimal)((HashMap)result1.get(0)).get("1")).toString();
				
				//插入库存表
				//charge.setStoid(Integer.parseInt(stoid));
				
				charge.setStoproid(dtoList.get(i).getNcdpid());
				charge.setStoamount(dtoList.get(i).getNcdqnt());
				charge.setStoproname(dtoList.get(i).getStoproname());
				charge.setStoamountun(dtoList.get(i).getStoamountun());
				charge.setStodate(dtoList.get(i).getStodate());
				charge.setStogrcliid(dtoList.get(i).getStogrcliid());
				charge.setStooprcd(dtoList.get(i).getStooprcd());
				charge.setStoactype("-1");
//				charge.setStoproname(pdtnm);
//				charge.setStoamountun(pdtun);
				
				charge.setFileKey("store_insert");
				store(con, charge, null, 0);
			}
			
			DBUtil.commit(con);
//			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("dto", dtoList);
			response.setBody(retmap);
			
		}catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
		
	}
	
	
	public ResponseEnvelop checkNormalDisc(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap<String, Object> retmap = new HashMap<String, Object>();
		Connection con = null;
		try{
			HashMap map = (HashMap) request.getBody();
			List<Charge> dtoList = (Vector<Charge>) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
		int j=1;
		boolean flag=false;
		for(Charge chg2:dtoList)
		{	
			String sql3 = "select g.gctprovince from tblgrpclient g where g.gctid='"
				+ chg2.getStogrcliid() + "'";
			List result3 = (Vector) DBUtil.querySQL(con, sql3);
			String gctprovince = (String) ((HashMap) result3.get(0)).get("1");
			checkDis(con,chg2,gctprovince);
			if(null!=chg2.getTdspvo()&&(chg2.getTdspvo()>chg2.getNcddis()))
			{	
				chg2.setMindisi(String.valueOf(j));
				flag=true;
			}
			j++;
		
		}
	
		if(flag)
		{
			retmap.put("dto", dtoList);
			response.setBody(retmap);
			return response;
		}
//	}
	DBUtil.commit(con);
//	retmap.put("dto", dtoList);
//	response.setBody(retmap);
	
}catch (AppException ae) {
	response.setHead(ExceptionSupport(className, ae, request.getHead()));
} catch (Exception ex) {
	response.setHead(ExceptionSupport(className, "save2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
} finally {
	DBUtil.rollback(con);
	DBUtil.closeConnection(con);
}
return response;

}
	public void checkDis(Connection con,Charge chg,String gctprovince)throws Exception
	{
		Double pdtdiscount = null;
		String sql1 = "select p.pdtdiscoin,p.pdtdiscoout from tblproduct p where p.pdtid='"
				+ chg.getNcdpid() + "'";
		List result1 = (Vector) DBUtil.querySQL(con, sql1);
		if(result1.size()!=0)
		{
			if (gctprovince.equals("1")) {
				if ((null != ((HashMap) result1.get(0)).get("1"))
					&& !"".equals(((HashMap) result1.get(0))
							.get("1"))) {
				pdtdiscount = Double.parseDouble(((HashMap) result1
						.get(0)).get("1").toString());
//				odl.setTdspvo(pdtdiscount);
				chg.setTdspvo(pdtdiscount);
			}
		} 
		else 
		{
			if ((null != ((HashMap) result1.get(0)).get("2"))
					&& !"".equals(((HashMap) result1.get(0))
							.get("2"))) {
				pdtdiscount = Double.parseDouble(((HashMap) result1
						.get(0)).get("2").toString());
//				odl.setTdspvo(pdtdiscount);
				chg.setTdspvo(pdtdiscount);
			}
		}
	 }
		
	if (null == pdtdiscount || "".equals(pdtdiscount)) {
			String sql4 = "select count(*) from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
					+ chg.getNcdpid() + "')";
			List result4 = (Vector) DBUtil.querySQL(con, sql4);
			if (!((HashMap) result4.get(0)).get("1").toString()
					.equals("0")) {
				String sql3 = "select d.tdspvoin,d.tdspvoout from tbltypdis d where d.tdsnm=(select p.pdttype from tblproduct p where p.pdtid='"
						+ chg.getNcdpid() + "')";
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
//				odl.setTdspvo(tdspvo);
				chg.setTdspvo(tdspvo);
			}
		}
    }
	
	
	
	/**
	 * 普通收费信息显示
	 */
	public ResponseEnvelop print2(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");	
			
			List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_CHGID.NEXTVAL from dual");
			String id = ((BigDecimal)((HashMap)result1.get(0)).get("1")).toString();
			dto.setChgid(id);
			dto.setFileKey("chg01_006");
			store(con, dto, null, 0);
			
			
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", dto);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 普通商品收费
	 */
	public ResponseEnvelop charge1(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		
		return response;
	}

	/**
	 * 定制商品收费信息保存前的查询操作
	 */
	public ResponseEnvelop charge2(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			//Charge charge = new Charge();
			Charge dto = (Charge) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getFolno() == null || dto.getFolno().equals("")) {	
				throw new AppException("无收费信息");
			} else {
				List result0 = (Vector)DBUtil.querySQL(con, "select f.folsta from tblfolio f where f.folno='" + dto.getFolno()+ "'");
				String folsta = (String)(((HashMap)result0.get(0)).get("1"));
				if (!("finish".equals(folsta))&&!("recback".equals(folsta)))
				{
					throw new AppException("此定制订单已收费或者已退机，不能再收费！");
				}
				dto.setFolsta("charged");// 改变订单状态为“已收费”
				dto.setFolischg("1");
				dto.setFileKey("chg02_002");
				modify(con, dto, null, 0);
				System.out.println(dto.getFdtprc());
				System.out.println(dto.getDiscount());
				System.out.println(dto.getFolno());
                //修改扣率
			    String sql="update TBLFOLDETAIL set FDTPRC='"+dto.getFdtprc()+"',fdtdisc='"+dto.getDiscount()+"' where FDTFNO='"+dto.getFolno()+"'";
			    System.out.println(sql);
			    Statement stmt=con.createStatement(); 
			    stmt.execute(sql);
				
				
				//定制机收费后商品出库
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='" + dto.getPdtid()+ "'");
				if(result2.size() > 0){
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
					}
				}
				
				
				dto.setStogrcliid(dto.getFolctid());
				
				dto.setStoproname(dto.getPdtnm());
				dto.setStoamount(new Integer("1"));
				dto.setStoamountun("块");
				dto.setStodate(DateUtil.getDate());
				dto.setStoactype("-1");
				dto.setStodisc("0");
				if(dto.getFoltype().equals("make")){
					dto.setStoremark("直属店定制机收费后出库");
				}
				else if(dto.getFoltype().equals("makeEar")){
					dto.setStoremark("直属店耳模收费后出库");
				}
				dto.setFileKey("store_insert");
				store(con, dto, null, 0);
				
				
				
				
			
				DBUtil.commit(con);
				
				//传递订单号参数
				HashMap<String, String> retmap = new HashMap<String, String>();
				retmap.put("folno", dto.getFolno());
				response.setBody(retmap);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 定制商品退机退费信息保存前的查询操作
	 */
	public ResponseEnvelop saveCustomizedRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			//Charge charge = new Charge();
			Charge dto = (Charge) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			String sta=dto.getFolsta();//新加
			
			if (dto.getFolno() == null || dto.getFolno().equals("")) {	
				throw new AppException("无收费信息");
			} else {
				dto.setFolsta("recoiled");// 改变订单状态为“已收费”
//				if(dto.getFoltype().equals("make"))
//				{
//					dto.setFoltype("makeRec");
//				}
//				else if(dto.getFoltype().equals("makeEar"))
//				{
//				    dto.setFoltype("makeEarRec");
//				}
				dto.setFileKey("chg02_008");
				modify(con, dto, null, 0);
				dto.setFdtodt(DateUtil.getDate());
				dto.setFileKey("chg02_012");
				modify(con, dto, null, 0);
				OrderDetail od=new OrderDetail();

				od.setFdtodt(DateUtil.getDate());
				
				//定制机退费后商品入直属店库
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='" + dto.getPdtid()+ "'");
				if(result2.size() > 0){
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					od.setFdtrecmon(dto.getFdtrecmon());
					od.setFdtfno(dto.getFolno());
					od.setFdtpid(pdtid);
					od.setFileKey("chg04_015");
					modify(con, od, null, 0);
					
				if(null!=dto.getFolischg()&&dto.getFolischg().equals("1"))
				{
					
					dto.setStoproid(pdtid);
					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
					}
				}
				
				if(!"finish".equals(sta)){//新加if
					dto.setStogrcliid(dto.getFolctid());
					dto.setStoproid(od.getFdtpid());
					dto.setStoproname(dto.getPdtnm());
					dto.setStoamount(new Integer("1"));
					dto.setStoamountun("块");
					dto.setStodate(DateUtil.getDate());
					dto.setStoactype("1");
					dto.setStodisc("0");
					if(dto.getFoltype().equals("make")){
						dto.setStoremark("直属店定制机收费后退机退费后入库");
					}
					else if(dto.getFoltype().equals("makeEar")){
						dto.setStoremark("直属店耳模收费后退机退费后入库");
					}
					dto.setFileKey("store_insert");
					store(con, dto, null, 0);
				}
//				od.setFdtnt("收费前退机");
				}
//				else
//				{
//					od.setFdtnt("收费后退机");
//				}
				
//				od.setFdtfno(dto.getFolno());
//				od.setFdtrecmon(dto.getFdtrecmon());
//			
//				od.setFileKey("chg04_007");
//				store(con, od, null, 0);
				DBUtil.commit(con);
				
				//传递订单号参数
				HashMap<String, String> retmap = new HashMap<String, String>();
				retmap.put("folno", dto.getFolno());
				response.setBody(retmap);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 雏形定制商品退机退费信息保存前的查询操作
	 */
//	public ResponseEnvelop saveNomRec(RequestEnvelop request)
//	{
//		ResponseEnvelop response = new ResponseEnvelop();
//		Connection con = null;
//		try {
//			HashMap<String, Object> map = (HashMap<String, Object>) request.getBody();
//			List<Charge> chgList = (List<Charge>) map.get("beo");
//			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
//			for(Charge dto:chgList)
//			{
//				dto.setNcdsta("nomrecoiled");
//				dto.setFileKey("chg04_004");
//				modify(con, dto, null, 0);
//				
//				
//				//普通商品退费后商品入直属店库
//				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
//						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtnm='" + dto.getPdtnm()+ "'");
//				if(result2.size() > 0){
//					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
//					dto.setStoproid(pdtid);
//					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
//					if(null!=pclid && !"".equals(pclid))
//					{
//						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
//						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
//						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
//						dto.setStoprotype(pclid);
//						dto.setStocllar(pcllar);
//						dto.setStoclmid(pclmid);
//						dto.setStoclsam(pclsam);
//					}
//				}
//				
//				
//				
//				List result3 = (Vector)DBUtil.querySQL(con, "select c.gctid from tblnorchg g left join tblgrpclient c on g.chggcltid=c.gctid where g.chgid='" +
//				dto.getNcdid()+ "'");
//				if(result3.size() > 0){
//					String gctid = ((HashMap)result3.get(0)).get("1").toString();
//					dto.setStogrcliid(gctid);
//				}
//				List result4 = (Vector)DBUtil.querySQL(con, "select p.pdtnm from tblproduct p where p.pdtid='" +
//				dto.getNcdpid()+ "'");
//				if(result4.size() > 0){
//					String pdtnm = ((HashMap)result4.get(0)).get("1").toString();
//					dto.setStoproname(pdtnm);
//				}
//				
//				
//				
//				dto.setStoamount(dto.getNcdrnt());
//				dto.setStoamountun("块");
//				dto.setStodate(DateUtil.getDate());
//				dto.setStoactype("1");
//				dto.setStodisc("0");
////				if(dto.getFoltype().equals("make")){
//					dto.setStoremark("直属店普通商品退货退费后入库");
////				}
////				else if(dto.getFoltype().equals("makeEar")){
////					dto.setStoremark("直属店耳模退机退费后入库");
////				}
//				dto.setFileKey("store_insert");
//				store(con, dto, null, 0);
//			}
//				DBUtil.commit(con);
//				
//		} catch (AppException ae) {
//			response.setHead(ExceptionSupport(className, ae, request.getHead()));
//		} catch (Exception ex) {
//			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
//		} finally {
//			DBUtil.rollback(con);
//			DBUtil.closeConnection(con);
//		}
//		return response;
//	}
	
	public ResponseEnvelop saveNomRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Object> map = (HashMap<String, Object>) request.getBody();
			Collection<Charge> chgList = (Collection<Charge>) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_CHGID.NEXTVAL from dual");
			        BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			        String chgid = id.toString();
			for(Charge dto:chgList)
			{
				dto.setNcdsta("nomrecoiled");
				dto.setNcdrecdt(DateUtil.getDate());
				dto.setFileKey("chg04_004");
				modify(con, dto, null, 0);
				
				
				//普通商品退费后商品入直属店库
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='" + dto.getNcdpid()+ "'");
				if(result2.size() > 0){
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
					}
				}
				
				
				
				List result3 = (Vector)DBUtil.querySQL(con, "select c.gctid from tblnorchg g left join tblgrpclient c on g.chggcltid=c.gctid where g.chgid='" +
				dto.getNcdid()+ "'");
				if(result3.size() > 0){
					String gctid = ((HashMap)result3.get(0)).get("1").toString();
					dto.setStogrcliid(gctid);
				}
				List result4 = (Vector)DBUtil.querySQL(con, "select p.pdtnm from tblproduct p where p.pdtid='" +
				dto.getNcdpid()+ "'");
				if(result4.size() > 0){
					String pdtnm = ((HashMap)result4.get(0)).get("1").toString();
					dto.setStoproname(pdtnm);
				}
				
				
				
				dto.setStoamount(dto.getNcdrnt());
				dto.setStoamountun("块");
				dto.setStodate(DateUtil.getDate());
				dto.setStoactype("1");
				dto.setStodisc("0");
//				if(dto.getFoltype().equals("make")){
					dto.setStoremark("直属店普通商品退货退费后入库");
//				}
//				else if(dto.getFoltype().equals("makeEar")){
//					dto.setStoremark("直属店耳模退机退费后入库");
//				}
				dto.setFileKey("store_insert");
				store(con, dto, null, 0);
				Charge chg=new Charge();
				chg.setNcdid(chgid);
				chg.setNcdoid(dto.getNcdid());
				chg.setNcdrecdt(DateUtil.getDate());
				chg.setNcdpid(dto.getNcdpid());
				chg.setNcdrnt(dto.getNcdrnt());
				chg.setNcdqnt(-dto.getNcdrnt());
				chg.setNcdrecmon(dto.getNcdrecmon());
				chg.setNcdmon(-dto.getNcdrecmon());
				chg.setFileKey("chg04_011");
				store(con, chg, null, 0);
//				dto.setNcdid(chgid);
//				dto.setNcdoid(dto.getNcdid());
//				dto.setNcdrecdt(DateUtil.getDate());
//				dto.setNcdpid(dto.getNcdpid());
//				dto.setFileKey("chg04_011");
//				store(con, dto, null, 0);
				
			}
			

			
				DBUtil.commit(con);
				
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	
	
	
	
	
	/**
	 * 定制商品退机退费信息保存前的查询操作
	 */
	public ResponseEnvelop commitCustomizedRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			//Charge charge = new Charge();
			Charge dto = (Charge) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getFolno() == null || dto.getFolno().equals("")) {	
				throw new AppException("无收费信息");
			} else {
				dto.setFolsta("recoiledhead");//
				dto.setFileKey("chg02_008");
				modify(con, dto, null, 0);
			    dto.setFdtrecheaddt(DateUtil.getDate());
			    dto.setFileKey("chg04_010");
			    modify(con, dto, null, 0);
			    dto.setFileKey("chg04_016");
			    modify(con, dto, null, 0);
				DBUtil.commit(con);
				
				//传递订单号参数
				HashMap<String, String> retmap = new HashMap<String, String>();
				retmap.put("folno", dto.getFolno());
				response.setBody(retmap);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 定制商品退机退费信息保存前的查询操作
	 */
	public ResponseEnvelop commitNomRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			List<Charge> chgList = (List<Charge>) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for(Charge dto:chgList)
			{
			    dto.setNcdsta("commited");
			    dto.setNcdrecheaddt(DateUtil.getDate());
				dto.setFileKey("chg04_013");
				modify(con, dto, null, 0);
				dto.setFileKey("chg04_017");
				modify(con, dto, null, 0);
			}
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
//	public ResponseEnvelop modifyNomRec(RequestEnvelop request)
//	{
//		ResponseEnvelop response = new ResponseEnvelop();
//		Connection con = null;
//		try {
//			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
//			List<Charge> chgList = (List<Charge>) map.get("beo");
//			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
//			for(Charge dto:chgList)
//			{
//			    dto.setNcdsta("modifyed");
//				dto.setFileKey("chg04_012");
//				modify(con, dto, null, 0);
//
//			}
//			
//			DBUtil.commit(con);
//		} catch (AppException ae) {
//			response.setHead(ExceptionSupport(className, ae, request.getHead()));
//		} catch (Exception ex) {
//			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
//		} finally {
//			DBUtil.rollback(con);
//			DBUtil.closeConnection(con);
//		}
//		return response;
//	}
	
	
	
	
	/**
	 * 定制商品退机退费信息保存前的查询操作
	 */
	public ResponseEnvelop exaNomRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Object> map = (HashMap<String, Object>) request.getBody();
			List<Charge> chgList = (List<Charge>) map.get("beo");
			String tp = (String) map.get("tp");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for(Charge dto:chgList)
			{
				String sql="select l.ncdsta from tblnorchgdetail l where l.ncdid='" +dto.getNcdid()+"' and l.ncdpid='"+dto.getNcdpid()+"'";	
				List result = (Vector)DBUtil.querySQL(con, sql);
				String ncdsta = (String) ((HashMap) result.get(0)).get("1");
				
				String sql0="select g.chggcltid g from tblnorchg g where g.chgid='" +dto.getNcdid()+"'";	
				List result0 = (Vector)DBUtil.querySQL(con, sql0);
				String chggcltid = (String) ((HashMap) result0.get(0)).get("1");
				
				
				if(tp.equals("e"))
				{
					
					if(!"".equals(ncdsta)&&"pass".equals(ncdsta))
					{
						throw new AppException("收费号为"+dto.getNcdoid()+"且商品代码为"+dto.getNcdpid()+"的商品不能重复审核！");
					}
					if(!"".equals(ncdsta)&&!"commited".equals(ncdsta))
					{
						throw new AppException("收费号为"+dto.getNcdoid()+"且商品代码为"+dto.getNcdpid()+"的商品审核时状态须为退机到总部!");
					}
					dto.setNcdsta("pass"); 

				//普通商品退费后商品入总部库存
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtnm='" + dto.getPdtnm()+ "'");
				if(result2.size() > 0){
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
					}
				}
				dto.setStogrcliid(chggcltid); 
				dto.setStoproid(dto.getNcdpid());
				
				List result4 = (Vector)DBUtil.querySQL(con, "select p.pdtnm from tblproduct p where p.pdtid='" +
						dto.getNcdpid()+ "'");
						if(result4.size() > 0){
							String pdtnm = ((HashMap)result4.get(0)).get("1").toString();
							dto.setStoproname(pdtnm);
						}
				
				dto.setStoamount(dto.getNcdrnt());
				dto.setStoamountun("块");
				dto.setStodate(DateUtil.getDate());
				dto.setStodisc("0");
				dto.setStoactype("-1");
				dto.setStoremark("普通商品退货退费后出直属店库存");
				dto.setFileKey("store_insert");
				store(con, dto, null, 0);
				dto.setStogrcliid("1501000000"); 
				dto.setStoactype("1");
				dto.setStoremark("普通商品退货退费后入总部库存");
				dto.setFileKey("store_insert");
				store(con, dto, null, 0);
				}
				else if(tp.equals("r"))
				{
					if(!"".equals(ncdsta)&&"back".equals(ncdsta))
					{
						throw new AppException("收费号为"+dto.getNcdoid()+"且商品代码为"+dto.getNcdpid()+"的商品不能重复回退！");
					}
					if(!"".equals(ncdsta)&&!"commited".equals(ncdsta))
					{
						throw new AppException("收费号为"+dto.getNcdoid()+"且商品代码为"+dto.getNcdpid()+"的商品回退时状态须为退机到总部!");
					}
					dto.setNcdsta("back"); 
				}
				
				dto.setNcdrecexadt(DateUtil.getDate());
				dto.setFileKey("chg04_006");
				modify(con, dto, null, 0);
				dto.setFileKey("chg04_018");
				modify(con, dto, null, 0);
			}
				DBUtil.commit(con);
				
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 定制商品退机退费信息保存前的查询操作
	 */
	public ResponseEnvelop exaCusRec(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Object> map = (HashMap<String, Object>) request.getBody();
			List<Order> odList = (List<Order>) map.get("beo");
			String tp = (String) map.get("tp");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if("e".equals(tp))
			{
				for(Order dto:odList)
				{
					String sql="select o.folsta,o.folctid from tblfolio o where o.folno='"+dto.getFolno()+"'";
					List result = (Vector)DBUtil.querySQL(con, sql);
					String folsta = (String) ((HashMap) result.get(0)).get("1");
					String folctid = (String) ((HashMap) result.get(0)).get("2");
					if(!"".equals(folsta)&&"recpass".equals(folsta))
					{
						throw new AppException("订单号为"+dto.getFolno()+"的商品不能重复审核！");
					}
					if(!"".equals(folsta)&&!"recoiledhead".equals(folsta))
					{
						throw new AppException("订单号为"+dto.getFolno()+"的商品审核时订单状态须为退机到总部！");
					}
					dto.setFolsta("recpass");
					dto.setFileKey("ord11_027");
					modify(con, dto, null, 0);
					dto.setFdtexadt(DateUtil.getDate());
					dto.setFileKey("ord12_005");
					modify(con, dto, null, 0);
					
					Store sto=new Store();
					List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
							"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtnm='" + dto.getPdtnm()+ "'");
					if(result2.size() > 0){
						String pdtid = ((HashMap)result2.get(0)).get("5").toString();
						sto.setStoproid(pdtid);
						String pclid=(String)(((HashMap)result2.get(0)).get("1"));
						if(null!=pclid && !"".equals(pclid))
						{
							String pcllar = ((HashMap)result2.get(0)).get("2").toString();
							String pclmid = ((HashMap)result2.get(0)).get("3").toString();
							String pclsam = ((HashMap)result2.get(0)).get("4").toString();
							sto.setStoprotype(pclid);
							sto.setStocllar(pcllar);
							sto.setStoclmid(pclmid);
							sto.setStoclsam(pclsam);
						}
					}
					//sto.setStogrcliid("1501000000"); 
					sto.setStogrcliid(folctid); 
					sto.setStoproid(dto.getFdtpid());
					
					List result4 = (Vector)DBUtil.querySQL(con, "select p.pdtnm from tblproduct p where p.pdtid='" +
							dto.getFdtpid()+ "'");
							if(result4.size() > 0){
								String pdtnm = ((HashMap)result4.get(0)).get("1").toString();
								sto.setStoproname(pdtnm);
							}
					List result5 = (Vector)DBUtil.querySQL(con, "select p.pdtnm from tblproduct p where p.pdtid='" +
									dto.getFdtpid()+ "'");
									if(result5.size() > 0){
										String pdtnm = ((HashMap)result5.get(0)).get("1").toString();
										sto.setStoproname(pdtnm);
									}
					
					sto.setStoamount(1);
					sto.setStoamountun("块");
					sto.setStodate(DateUtil.getDate());
					sto.setStodisc("0");
					sto.setStoactype("-1");
					sto.setStoremark("定制机退货退费后出直属店库存");
					sto.setFileKey("store_insert");
					store(con, sto, null, 0);
				
				}
			}
			else if("r".equals(tp))
			{
			
				
				for(Order dto:odList)
				{
					String sql="select o.folsta from tblfolio o where o.folno='"+dto.getFolno()+"'";
					List result = (Vector)DBUtil.querySQL(con, sql);
					String folsta = (String) ((HashMap) result.get(0)).get("1");
					if(!"".equals(folsta)&&"recback".equals(folsta))
					{
						throw new AppException("订单号为"+dto.getFolno()+"的商品不能重复回退！");
					}
					if(!"".equals(folsta)&&!"recoiledhead".equals(folsta))
					{
						throw new AppException("订单号为"+dto.getFolno()+"的商品回退时订单状态须为退机到总部！");
					}
					dto.setFolsta("recback");
					dto.setFileKey("ord11_027");
					modify(con, dto, null, 0);
				
				}
			}
			
			DBUtil.commit(con);
				
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge2",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	

	
	
	/**
	 * 退机收费信息保存前的查询操作
	 */
	public ResponseEnvelop charge4(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			Charge charge = new Charge();
			Charge dto = (Charge) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getFolno() == null || dto.getFolno().equals("")) {	//dto. 后面要修改
				throw new AppException("无收费信息");
			} else {
				List result = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
				String stoid = ((BigDecimal)((HashMap)result.get(0)).get("1")).toString();
				
				dto.setFileKey("chg04_001");
				modify(con, dto, null, 0);
				
				dto.setStoid(Integer.parseInt(stoid));
				dto.setFileKey("chg04_002");
				store(con, dto, null, 0);
				
				
				DBUtil.commit(con);
				
				//传递订单号参数
				HashMap<String, String> retmap = new HashMap<String, String>();
				retmap.put("folno", dto.getFolno());
				response.setBody(retmap);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge4",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 定制商品收费点击收费后显示的页面信息
	 */
	public ResponseEnvelop show(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");
			dto.setFileKey("chg02_004");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 定制商品收费点击退机退费后显示的页面信息
	 */
	public ResponseEnvelop show1(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");
			dto.setFileKey("chg02_005");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 定制商品收费信息显示
	 */
	public ResponseEnvelop print(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");
			dto.setFileKey("chg02_001");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 退机收费信息显示
	 */
	public ResponseEnvelop print3(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");
			dto.setFileKey("chg04_000");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print3",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 维修商品收费信息保存
	 */
	public ResponseEnvelop charge3(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Charge> map = (HashMap<String, Charge>) request.getBody();
			Charge dto = (Charge) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getRepfolid() == null || dto.getRepfolid().equals("")) {	//dto. 后面要修改
				throw new AppException("无收费信息");
			} else {
				dto.setFolischg("1");
				dto.setFileKey("chg03_002");
				modify(con, dto, null, 0);
				

				dto.setFolsta("charged");// 改变订单状态为“已收费”
				dto.setFileKey("chg03_003");
				modify(con, dto, null, 0);
				dto.setFdtdprc(dto.getRepamt());
				dto.setFileKey("chg03_004");
				modify(con, dto, null, 0);

				
				//维修收费后商品出库
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='" + dto.getPdtid()+ "'");
				if(result2.size() > 0){
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
					String pclid=(String)(((HashMap)result2.get(0)).get("1"));
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
					}
				}
				
				List result3 = (Vector)DBUtil.querySQL(con, "select foltype from tblfolio where folno = '"+dto.getRepfolid()+"'"); 
				String foltype = "";
				if(result3.size() > 0){
					foltype = ((HashMap)result3.get(0)).get("1").toString();
				}
				dto.setStogrcliid(dto.getRepgctid());
				
				dto.setStoproname(dto.getReppnm());
				dto.setStoamount(new Integer("1"));
				dto.setStoamountun("台");
				dto.setStodate(DateUtil.getDate());
				dto.setStoactype("-1");
				dto.setStodisc("0");
				dto.setFoltype(foltype);
				dto.setStoremark("维修商品收费后出库");
				if(dto.getFoltype().equals("repair")){
					dto.setIsrepair("1");
				}
//				if(dto.getFoltype().equals("make")){
//					dto.setStoremark("直属店定制机收费后出库");
//				}
//				else if(dto.getFoltype().equals("makeEar")){
//					dto.setStoremark("直属店耳模收费后出库");
//				}
				dto.setFileKey("store_insert");
				store(con, dto, null, 0);
				dto.setFdtdprc(dto.getRepamt());
				dto.setFileKey("chg03_004");
				modify(con, dto, null, 0);

				


				DBUtil.commit(con);
				HashMap<String, String> retmap = new HashMap<String, String>();
				retmap.put("folno", dto.getFolno());
				response.setBody(retmap);
				
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "charge3",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	
	
	/**
	 * 维修收费信息显示
	 */
	public ResponseEnvelop print1(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Charge dto = (Charge) map.get("beo");	//dto=null
			dto.setFileKey("chg03_001");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print1",ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 普通收费时增加用户信息
	 * 
	 */
	public ResponseEnvelop addClient(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			HashMap map = (HashMap)request.getBody();
			Charge dto = (Charge)map.get("beo");
			List<Audiogram> agList = (Vector<Audiogram>) map.get("beo1");
			
			DBUtil.beginTrans(con);
			// 获取最后一个客户ID号
			List result = (Vector) DBUtil.querySQL(con,
					"select max(ictid) from tblindclient");
			String ictid = (String) ((HashMap) result.get(0)).get("1");
			Integer id = Integer.valueOf(ictid) + 1;
			ictid = String.format("%06d", id);//6位数字,如果第一位是0则以0开始的6位数
			dto.setIctid(ictid);
			dto.setFileKey("chg01_003");
			store(con, dto, null, 0);
			dto.setFileKey("charge_detail_insert");
			store(con,dto,null,0);
			for (Audiogram ag : agList) {
				ag.setAdgctid(ictid);
				ag.setFileKey("audiogram_insert");
				store(con, ag, null, 0);
			}
			
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("ictid", dto.getIctid());
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop norChgRecDetail(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			HashMap map = (HashMap)request.getBody();
			Charge dto = (Charge)map.get("beo");
//			List<Audiogram> agList = (Vector<Audiogram>) map.get("beo1");
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			DBUtil.beginTrans(con);
			// 获取最后一个客户ID号
//			List result = (Vector) DBUtil.querySQL(con,
//					"select max(ictid) from tblindclient");
//			String ictid = (String) ((HashMap) result.get(0)).get("1");
//			Integer id = Integer.valueOf(ictid) + 1;
//			ictid = String.format("%06d", id);//6位数字,如果第一位是0则以0开始的6位数
//			dto.setIctid(ictid);
//			dto.setFileKey("chg01_003");
//			store(con, dto, null, 0);
			
//			for (Audiogram ag : agList) {
//				ag.setAdgctid(ictid);
//				ag.setFileKey("audiogram_insert");
//				store(con, ag, null, 0);
//			}
			dto.setFileKey("chg04_008");//
			Object chg = find(con, dto, null, 0);
			DBUtil.commit(con);
			retmap.put("beo1", chg);
//			HashMap<String, String> retmap = new HashMap<String, String>();
//			retmap.put("ictid", dto.getIctid());
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
}
