package org.radf.apps.business.imp;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Fee;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Sale;

public class BusinessImp extends IMPSupport implements BusinessFacade {
	private String className = this.getClass().getName();

	/**
	 * 月结信息修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Business> collection = (Collection<Business>) map
					.get("collection");
			String userId = (String)map.get("userId");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Business dto : collection) {
				dto.setIvtoprcd(userId);
				dto.setFileKey("business_update");
				modify(con, dto, null, 0);
				dto.setFileKey("bus02_000");
				modify(con, dto, null, 0);
				dto.setActsta("1");
				dto.setFileKey("bus01_007");
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
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
 * 插入客户均摊费用
 */
	public ResponseEnvelop insertAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Fee> feeList = (List<Fee>) map.get("feeList");
			String fagctid =(String)map.get("feegctid");
			String feegctname = (String)map.get("feegctname");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Fee fee : feeList) {
				String str = DBUtil.getSequence(con, "s_tblfeeamortize");
				int seq = Integer.parseInt(str)+1;
				String sql1 = "insert into tblfeeamortize (feegctid,feestart,feeend,amotype,money,note,operdate,feegctname,feeamoid,feelong,amomoney) values (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql1);
				pstmt.setString(1,fagctid);
				pstmt.setDate(2, (Date) fee.getFeestart());
				pstmt.setDate(3, (Date) fee.getFeeend());
				pstmt.setString(4, fee.getAmotype());
				pstmt.setDouble(5, fee.getMoney());
				pstmt.setString(6, fee.getNote());
				pstmt.setDate(7,(Date) fee.getOperDate());
				pstmt.setString(8,feegctname);
				pstmt.setInt(9, seq);
				pstmt.setInt(10, fee.getFeelong());
				pstmt.setDouble(11, fee.getAmomoney());
				pstmt.execute();
			}
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "insert",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * 客户费用信息
	 */
	public ResponseEnvelop insert(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Fee fe = (Fee) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 判断ID是否存在和重复
			if (null == fe.getFeegctid() || "".equals(fe.getFeegctid())) {
				throw new AppException("录入费用的团体客户号不能为空!");
			}
			// 判断费用是否已经录入
			fe.setFileKey("fee_select");
			if (getCount(con, fe, 0) > 0) {
				//super.saveSuccessfulMsg(req, "该团体客户费用已录入!");
				throw new AppException("该团体客户费用已录入!");
			
			}
/*			fe.setFeetype("0");
			fe.setFileKey("fee_insert1");
			// 插入团体客户费用
			store(con, fe, null, 0);
			
			fe.setFeetype("1");
			fe.setFileKey("fee_insert2");
			// 插入团体客户费用
			store(con, fe, null, 0);
			
			
			fe.setFeetype("2");
			fe.setFileKey("fee_insert3");
			// 插入团体客户费用
			store(con, fe, null, 0);*/
			
			fe.setFileKey("fee_insert");
			store(con,fe,null,0);
			
			
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("feegctid", fe.getFeegctid());
			retmap.put("workString", "录入团体客户费用信息");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "insert",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop query(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Fee dto = (Fee) map.get("beo");
			dto.setFileKey("fe2_select");// 客户费用信息
			Object ci = find(con, dto, null, 0);
			if ("".equals(ci) || ci == null)
				throw new AppException("没有查询到符合条件的信息!");
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			// response.setHead(ExceptionSupport(className, "print",
			// ManageErrorCode.SQLERROR, ex.getMessage(), request
			// .getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 库存超期查询
	 */
	public ResponseEnvelop stoexpquery(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		//CallableStatement proc = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			
			
			/*proc = con.prepareCall("{call PRC_STOEXPCHECK}");
			proc.execute();
			
			business.setFileKey("bus01_002");
			Object ci = find(con, business, null, 0);*/
			
			
			business.setFileKey("bus02_001");
			remove(con,business,null,0);
			
			/*if ("".equals(ci) || ci == null)
				throw new AppException("没有查询到符合条件的信息!");*/
		
			DBUtil.commit(con);
			/*HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);*/
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			// response.setHead(ExceptionSupport(className, "print",
			// ManageErrorCode.SQLERROR, ex.getMessage(), request
			// .getHead()));
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
			HashMap map = (HashMap) request.getBody();
			Fee dtoList = (Fee) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			/*dtoList.setFeetype("0");
			dtoList.setFileKey("fee_update1");
			modify(con, dtoList, null, 0);
			
			dtoList.setFeetype("1");
			dtoList.setFileKey("fee_update2");
			modify(con, dtoList, null, 0);
			
			dtoList.setFeetype("2");
			dtoList.setFileKey("fee_update3");
			modify(con, dtoList, null, 0);*/
			
			dtoList.setFileKey("fee_update");
			modify(con,dtoList,null,0);

			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "update",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 补充费用信息
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sale sa = (Sale) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			//获取
			String sql = "select nvl(sum(ivtpamnt),0) as temp from tblinventory where ivtgcltid='"+sa.getMgctid()+"' and ivtyear='"+sa.getMyear()+"' and ivtmonth='"+sa.getMmonth()+"'";
			List result = (Vector) DBUtil.querySQL(con,sql);
			Double pamnt = null;
			if(result.size()>0)
				pamnt = Double.parseDouble(((HashMap) result.get(0)).get("1").toString()); 
	        Integer lyear = null;
	        Integer lmonth = null;
	        Integer year = sa.getMyear();
	        Integer month = sa.getMmonth();
	        if(month == 12){
	        	lyear = year - 1;
	        	lmonth = 1;
	        }else{
	        	lmonth = month - 1;
	        	lyear = year;
	        }
	        sql = "select nvl(m.marrears,0) as temp from tblmonth m where m.mgctid='"+sa.getMgctid()+"' and m.myear='"+lyear+"' and m.mmonth='"+lmonth+"'";
	        result = (Vector) DBUtil.querySQL(con,sql);
	        Double arrears = null;
	        if(result.size()>0){
		       arrears = Double.parseDouble(((HashMap) result.get(0)).get("1").toString()) ;
	        }
	        else
	        	arrears = new Double(0);
	        sa.setMarrears(arrears+pamnt-sa.getMad()-sa.getMback()+sa.getMothers());
			sa.setFileKey("sale_insert");
			store(con, sa, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("mgctid", sa.getMgctid());
			retmap.put("workString", "团体客户月结费用信息");
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
	 * 保存固定资产信息
	 */
	public ResponseEnvelop saveAsset(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			//获取固定资产流水号
			List result = (Vector) DBUtil.querySQL(con,"select SEQ_ASTID.NEXTVAL from dual");
			BigDecimal id = (BigDecimal)((HashMap)result.get(0)).get("1");
			
			String astid = id.toString();
			business.setAstid(astid);
			business.setFileKey("bus04_001");
			
			store(con, business, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("astid", business.getAstid());
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveAsset",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 *显示固定资产修改页面
	 */
	public ResponseEnvelop showAsset(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus04_002");
			
			Object ob = find(con, business, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ob);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "showAsset",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 *保存修改后的固定资产信息
	 */
	public ResponseEnvelop modifyAsset(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus04_003");
			
			modify(con, business, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyAsset",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 删除固定资产信息
	 */
	public ResponseEnvelop deleteAsset(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus04_004");
			
			remove(con, business, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteAsset",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 保存摊销费用信息
	 */
	public ResponseEnvelop saveAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		String date = null;
		Integer year = null;
		Integer month = null;
		Integer day = null;
		Integer total = null;
		List result = null;
		BigDecimal id = null;
		String arzid = null;
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);


			// 获取摊销管理流水号1
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getHarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getHarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setHarzexpdt(calendar.getTime());
			business.setArzitem("房租");
			business.setHarzisexp("0");
			business.setHarzmon(business.getHarzamount()/Integer.parseInt(business.getHarzmonth()));
			business.setFileKey("bus05_001");
			store(con, business, null, 0);
			
			
			// 获取摊销管理流水号2
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getFarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getFarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setFarzexpdt(calendar.getTime());
			business.setArzitem("装修费");
			business.setFarzisexp("0");
			business.setFarzmon(business.getFarzamount()/Integer.parseInt(business.getFarzmonth()));
			business.setFileKey("bus05_002");
			store(con, business, null, 0);
			
			// 获取摊销管理流水号3
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getDarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getDarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setDarzexpdt(calendar.getTime());
			business.setArzitem("设备");
			business.setDarzisexp("0");
			business.setDarzmon(business.getDarzamount()/Integer.parseInt(business.getDarzmonth()));
			business.setFileKey("bus05_003");
			store(con, business, null, 0);
			
			// 获取摊销管理流水号4
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getAarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getAarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setAarzexpdt(calendar.getTime());
			business.setArzitem("固定资产");
			business.setAarzisexp("0");
			business.setAarzmon(business.getAarzamount()/Integer.parseInt(business.getAarzmonth()));
			business.setFileKey("bus05_004");
			store(con, business, null, 0);
			
			// 获取摊销管理流水号5
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getTarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getTarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setTarzexpdt(calendar.getTime());
			business.setArzitem("转让费");
			business.setTarzisexp("0");
			business.setTarzmon(business.getTarzamount()/Integer.parseInt(business.getTarzmonth()));
			business.setFileKey("bus05_005");
			store(con, business, null, 0);
			
			// 获取摊销管理流水号6
			result = (Vector) DBUtil.querySQL(con,"select SEQ_ARZID.NEXTVAL from dual");
			id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			arzid = id.toString();
			business.setArzid(arzid);
			date = format.format(business.getOarzstdt());
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			total = year * 12 + month + Integer.parseInt(business.getOarzmonth());
			year = total / 12;
			month = total % 12 - 2;
			calendar.set(year, month, day);
			business.setOarzexpdt(calendar.getTime());
			business.setArzitem("开办费");
			business.setOarzisexp("0");
			business.setOarzmon(business.getOarzamount()/Integer.parseInt(business.getOarzmonth()));
			business.setFileKey("bus05_006");
			store(con, business, null, 0);
			
			
			
			DBUtil.commit(con);
			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveAmortize",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 显示修改摊销管理信息页面
	 */
	public ResponseEnvelop showAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus05_007");
			
			Object ob = find(con, business, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ob);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "showAmortize",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 保存修改后的摊销信息
	 */
	public ResponseEnvelop modifyAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus05_008");
			
			modify(con, business, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyAmortize",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 删除摊销管理信息
	 */
	public ResponseEnvelop deleteAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Fee fee = (Fee) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			fee.setFileKey("del_amo");
			
			remove(con, fee, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteAmortize",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
/*	public ResponseEnvelop deleteAmortize(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Business business = (Business) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			business.setFileKey("bus05_009");
			
			remove(con, business, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteAmortize",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}*/
	
	
	/**
	 * 保存月度结账备注
	 */
	public ResponseEnvelop savent(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sale sa = (Sale) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			sa.setFileKey("bus01_006");
			
			modify(con, sa, null, 0);
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savent",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop edit(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sale sa = (Sale) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
            //获取数据
			String sql = "select nvl(sum(ivtpamnt),0) as temp from tblinventory where ivtgcltid='"+sa.getMgctid()+"' and ivtyear='"+sa.getMyear()+"' and ivtmonth='"+sa.getMmonth()+"'";
			List result = (Vector) DBUtil.querySQL(con,sql);
			Double pamnt = null;
			if(result.size()>0)
				pamnt = Double.parseDouble(((HashMap) result.get(0)).get("1").toString());
	        Integer lyear = null;
	        Integer lmonth = null;
	        Integer year = sa.getMyear();
	        Integer month = sa.getMmonth();
	        if(month == 12){
	        	lyear = year - 1;
	        	lmonth = 1;
	        }else{
	        	lmonth = month - 1;
	        	lyear = year;
	        }
	        sql = "select nvl(m.marrears,0) as temp from tblmonth m where m.mgctid='"+sa.getMgctid()+"' and m.myear='"+lyear+"' and m.mmonth='"+lmonth+"'";
	        result = (Vector) DBUtil.querySQL(con,sql);
	        Double arrears = null;
	        if(result.size()>0){
		       arrears = Double.parseDouble(((HashMap) result.get(0)).get("1").toString()) ;
	        }
	        else
	        	arrears = new Double(0);
	        sa.setMarrears(arrears+pamnt-sa.getMad()-sa.getMback()+sa.getMothers());
			sa.setFileKey("sale_update");
			modify(con, sa, null, 0);

			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "edit",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop querysale(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sale dto = (Sale) map.get("beo");
			dto.setFileKey("sale_sale");// 客户费用信息
			Object ci = find(con, dto, null, 0);
			if ("".equals(ci) || ci == null)
				throw new AppException("该月费用信息还没录入!");
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			 response.setHead(ExceptionSupport(className, "query",
			 ManageErrorCode.SQLERROR, ex.getMessage(), request
			 .getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 保存成本价
	 */
	public ResponseEnvelop modifydis(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Business> collection = (Collection<Business>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Business dto : collection) {
				if(!dto.getIvttype().equals("repair")){
				dto.setFileKey("bus03_000");
				}else{
					dto.setIvtyear((Integer)map.get("ivtyear"));
					dto.setIvtmonth((Integer)map.get("ivtmonth"));
					String sql = "select * from TBLREPDISCOUNT where rivtid='"+dto.getIvtid()+"' and rdispdtid ='"+dto.getIvtpdtid() +"' and rdisgctid='"+dto.getIvtgcltid()+"' and rdisyear='"+dto.getIvtyear()+"' and rdismonth='"+dto.getIvtmonth()+"'";
					List result = (Vector) DBUtil.querySQL(con,sql);
					if(result.size() > 0){
						dto.setFileKey("bus03_0005");
					}else{
						dto.setFileKey("bus03_0004");
					}
				}
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifydis",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 上个月的费用
	 */
	public ResponseEnvelop lastMonth(RequestEnvelop request) {
		return find(request);
	}
	
	
	
	/**
	 * 月度结账
	 */
	public ResponseEnvelop account(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Business bi = (Business)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			//获取刚插入的tblinventory表中的数据记录
			
//			String sql0="select y.ivtyear,y.ivtmonth,y.ivtgcltid,y.ivtpdtid,y.ivttype,sum (nvl(ivtlmqnt,0))over(partition by ivtyear,ivtmonth,ivtgcltid,ivtpdtid) as mqnt,"+
//			"(case y.ivttype when 'make' then sum(nvl(y.ivtlsqnt,0))over(partition by ivtyear,ivtmonth,ivtgcltid,ivtpdtid) when 'normal'then nvl(y.ivtlsqnt,0) end) as sqnt,"+
//			"(case y.ivttype when 'make' then sum(nvl(y.ivtpqnt,0))over(partition by ivtyear,ivtmonth,ivtgcltid,ivtpdtid) when 'normal'then nvl(y.ivtpqnt,0) end) as pqnt,"+
//			"(case y.ivttype when 'make' then sum(nvl(y.ivtpamnt,0))over(partition by ivtyear,ivtmonth,ivtgcltid,ivtpdtid) when 'normal'then nvl(y.ivtpamnt,0) end) as pamnt"+
//		            "from tblinventory where ivtyear = '"+bi.getIvtyear()+"' and ivtmonth ='" + bi.getIvtmonth() + "'"; 
//					List result0 = (Vector)DBUtil.querySQL(con, sql0);
//			for(int i=0;i<result0.size();i++)
//			{
//				String ty=((HashMap)result0.get(i)).get("5").toString()
//				if("make".equals(ty))
//				{
//					String sql4="update tblinventory y set y.ivtlmqnt="+Integer.parseInt(((HashMap)result0.get(i)).get("5").toString())+
//				    ",y.sqnt where ivtyear = '"+bi.getIvtyear()+"' and ivtmonth ='" + bi.getIvtmonth() + "' and y.ivtgcltid='"+((HashMap)result0.get(i)).get("3").toString()+"'and y.ivtpdtid = '"+((HashMap)result0.get(i)).get("4").toString()+"'";
//				}
//				else if("normal".equals(ty))
//				{
//				String sql4="update tblinventory y set y.ivtlmqnt="+Integer.parseInt(((HashMap)result0.get(i)).get("6").toString())+
//			  " where ivtyear = '"+bi.getIvtyear()+"' and ivtmonth ='" + bi.getIvtmonth() + "' and y.ivtgcltid='"+((HashMap)result0.get(i)).get("3").toString()+"'and y.ivtpdtid = '"+((HashMap)result0.get(i)).get("4").toString()+"'";
//				}
//			}
			
			
			
			String sql1 = "select distinct ivtyear,ivtmonth,ivtgcltid,nvl(sum(ivtlmqnt)over(partition by ivtyear,ivtmonth,ivtgcltid),0) as mqnt" +
					",nvl(sum(ivtlsqnt)over(partition by ivtyear,ivtmonth,ivtgcltid),0)  as sqnt from tblinventory where ivtyear = '" + bi.getIvtyear()
				+ "' and ivtmonth ='" + bi.getIvtmonth() + "'"; 
//			String sql2 = "select distinct actstayear,actstamonth from tblactsta where actstayear ='"  + bi.getIvtyear() 
//					+ "' and actstamonth ='" + bi.getIvtmonth() + "'";
			String sql21 = "delete from tblactsta where actstayear ='"  + bi.getIvtyear() 
					+ "' and actstamonth ='" + bi.getIvtmonth() + "'";
			List result = (Vector)DBUtil.querySQL(con, sql1);
//			List result2 =(Vector)DBUtil.querySQL(con, sql2);
			
			pstm = con.prepareStatement(sql21);
            pstm.execute();
            
			for(int i = 0; i < result.size(); i ++){
//				if(result2.size() > 0){
//					break;
//				}
				Integer year = Integer.parseInt(((HashMap)result.get(i)).get("1").toString());
				Integer month = Integer.parseInt(((HashMap)result.get(i)).get("2").toString());
				String gcltid = ((HashMap)result.get(i)).get("3").toString();
				Integer mqnt = Integer.parseInt(((HashMap)result.get(i)).get("4").toString());
				Integer sqnt = Integer.parseInt(((HashMap)result.get(i)).get("5").toString()) ;
				List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_ACTSTAID.NEXTVAL from dual");
				BigDecimal id = (BigDecimal)((HashMap)result1.get(0)).get("1");
				String staid = id.toString(); 
				
				bi.setActstaid(staid);
				bi.setActstayear(year);
				bi.setActstamonth(month);
				bi.setActstagcltid(gcltid);
				bi.setActmqnt(mqnt);
				bi.setActsqnt(sqnt);
				
				String sql3 = "select distinct ivtyear,ivtmonth,ivtgcltid,sum(ivtlmqnt)over(partition by ivtpdtid) as mmqnt," + 
						"sum(ivtlsqnt)over(partition by ivtpdtid)as ssqnt from tblinventory where ivtyear = '" + year + 
							"' and ivtmonth='" +
						month + "' and ivtgcltid='" + gcltid + "'";
				List result3 = (Vector)DBUtil.querySQL(con, sql3);
				int count = 0;
				bi.setActsta("1");
				Integer mmqnt = null;
				Integer ssqnt = null;
				if(result3.size() > 0){
					for(int j = 0; j< result3.size(); j++){
//						mmqnt = Integer.parseInt(((HashMap)result3.get(i)).get("4").toString());
//						ssqnt = Integer.parseInt(((HashMap)result3.get(i)).get("5").toString());//i错误，报out of index错误
						mmqnt = Integer.parseInt(((HashMap)result3.get(j)).get("4").toString());
						ssqnt = Integer.parseInt(((HashMap)result3.get(j)).get("5").toString());
						if(mmqnt != 0 || ssqnt != 0){//只要有结存数或者补入数则设为“未结账”
							bi.setActsta("0");
							count ++;
							break;
						}
//						if(count==0){
//							bi.setActsta("1");
//						}
					}
				/*
					if(count<result3.size())
					{
						bi.setActsta("1");
					}
				*/
				}
//				else{//如果没有结果，说明没有记录则不用结账
//					bi.setActsta("1");
//				}
				bi.setFileKey("bus01_004");
				
				store(con, bi, null, 0);
			}
			
			DBUtil.commit(con);
			
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "account",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 显示修改库存期限页面
	 */
	public ResponseEnvelop print(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Business dto = (Business) map.get("beo");
			dto.setFileKey("bus02_002");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print1",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * 保存修改的库存期限
	 */
	public ResponseEnvelop saveExd(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Business dto = (Business) map.get("beo");
			dto.setFileKey("bus02_003");
			modify(con,dto,null,0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveExd",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * 查询加盟店还是直属店
	 */
	public String queryStoreType(String Gctnm) {
		String type = null;
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql= "select t.GCTTYPE from TBLGRPCLIENT t where t.GCTNM='"+ Gctnm+"'"; 
			List result = (Vector) DBUtil.querySQL(con,sql);
			
			if(result.size()>0){
//				pamnt = Double.parseDouble(((HashMap) result.get(0)).get("1").toString()); 
				type = ((HashMap)result.get(0)).get("1").toString();
			}
			System.out.println(type);
			DBUtil.commit(con);
		} catch (Exception ex) {
		
			
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		
		return type;
	}
	
	
	
	
}
