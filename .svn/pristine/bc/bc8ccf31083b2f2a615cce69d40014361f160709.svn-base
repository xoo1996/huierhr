package org.radf.apps.earmould.imp;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.EarMould;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.earmould.facade.EarmouldFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class EarmouldImp extends IMPSupport implements EarmouldFacade
{
	private String className = this.getClass().getName();

	/**
	 * 新增耳模信息
	 */
	public ResponseEnvelop save(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		CallableStatement proc = null;
		String retTmesid = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			List<EarMould> emList = (Vector<EarMould>) map.get("beo");
			Integer num = emList.size();
			String gid = emList.get(0).getTmectid();
			String opr = (String) map.get("opr");
			String prc = (String) map.get("price");
			Double price = Double.valueOf(prc);
			Integer earcount = Integer.valueOf((String) map.get("num"));
			
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
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
			List<OrderDetail> detailList = new Vector<OrderDetail>();
			for (EarMould em : emList)
			{
				List result = (Vector) DBUtil.querySQL(con,
				"select SEQ_FOLNO.NEXTVAL from dual");
				BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
				String tmefno = id.toString();
				proc = con.prepareCall("{ call PRC_CREATE_TMESID(?) }");
				proc.registerOutParameter(1, Types.VARCHAR);
				proc.execute();
				retTmesid = proc.getString(1);
				em.setTmesid(retTmesid);
				em.setTmeno(tmefno);
				
				Order header = new Order();
				header.setFolctid(gid);// 团体客户代码
				header.setFoldt(DateUtil.getDate()); // 订单创建日期
				header.setFolno(tmefno); // 订单号
				header.setFolsta("making"); // 耳模订单生成时状态为"制作中"
				header.setFoltype("makeEar");// 订单类别为"定制订单"
				header.setFolopr(opr);
				headerList.add(header);
				
				OrderDetail detail = new OrderDetail();
				detail.setFdtfno(tmefno);// 订单号
				// detail.setFdtcltid(cid);//个人客户代码
				detail.setFdtcltnm(emList.get(0).getTmecltnm());// 客户姓名
				detail.setFdtprc(price);// 单价
				detail.setFdtdprc(price);
				detail.setFdtpid(emList.get(0).getTmepid());
				detail.setFdtqnt(earcount);// 订购数量
				//detail.setFdtsqnt(earcount);// 发货数量
				detailList.add(detail);
			}

			//////header.setFileKey("order_insert");
			// 在tblearmaking表中插入记录 即耳模信息
			store(con, emList, null, 0);
			// 在tblfolio表中插入记录 即订单信息
			store(con, headerList, null, 0);
			// 在tblfoldetail表中插入记录 即订单详细信息
			store(con, detailList, null, 0);
			DBUtil.commit(con);
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
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 添加耳模维修记录
	 */
	public ResponseEnvelop saveRepair(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			Repair rep = (Repair) map.get("beo");
			EarMould emould = (EarMould)map.get("ear");
			String opr = (String)map.get("opr");
			String tp = (String)map.get("tp");
			Order header = new Order();
			OrderDetail detail = new OrderDetail();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 生成订单号
			List result = (Vector) DBUtil.querySQL(con,
			"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String repfno = id.toString();

			// 订单基本信息
			header.setFolctid(emould.getTmectid()); // 团体客户
			header.setFoldt(DateUtil.getDate()); // 订单创建日期
			header.setFolno(repfno); // 订单号
			if(null != tp && tp.equals("b")){
				header.setFolsta("uncommited"); // 耳模维修订单生成时状态为"未提交"
			}else if(null != tp && tp.equals("c")){
				header.setFolsta("commited"); // 耳模维修订单生成时状态为"已提交"
			}
			//header.setFolsta("repairing"); // 耳模维修订单生成时状态为"维修中"
			header.setFoltype("repairEar");// 订单类别为"耳模维修订单"
			header.setFolopr(opr);//操作员代码
			header.setFolctid(rep.getRepgctid());//客户代码
			// 订单明细
			detail.setFdtfno(repfno);// 订单号
			detail.setFdtcltid(emould.getTmecltid()); // 个人用户代码
			detail.setFdtcltnm(rep.getRepcltnm());// 个人客户姓名
			detail.setFdtpid(emould.getTmepid()); // 耳模型号
//			detail.setFdtdprc(Double.parseDouble(emould.getTmeprc()));//价格
//			detail.setFdtprc(Double.parseDouble(emould.getTmeprc()));//价格
			detail.setFdtqnt(1);// 数量为1
			detail.setFdtsqnt(1);

			
			
			emould.setTmeno(repfno);
			emould.setFileKey("ear01_008");
			store(con, emould, null, 0);
			
			/**
			 * tblrep都在审核通过后插入
			 */
			/*//tblrep表
			rep.setRepfolid(repfno);
			store(con, rep, null, 0);*/
			
			//tblfolio表
			header.setFileKey("order_insert");
			store(con, header, null, 0);
			
			//tblfoldetail表
			detail.setFileKey("orderDetail_insert");
			store(con, detail, null, 0);
			
			
			
			DBUtil.commit(con);
			
			
			HashMap<String,String> retMap = new HashMap<String,String>();
			retMap.put("repfno", repfno);
			response.setBody(retMap);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "saveRepair",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 耳模制作－更新耳模信息
	 */
	public ResponseEnvelop updateProduce(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			List<EarMould> dtoList = (Vector<EarMould>) map.get("beo");
			String fileKey = "ear02_000";
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			for (EarMould em : dtoList)
			{
				if (em.getTmeno() == null || em.getTmeno().equals(""))
				{
					throw new AppException("该耳模信息未登记");
				}
				else
				{
					em.setFileKey(fileKey);
					modify(con, em, null, 0);
				}
			}
			DBUtil.commit(con);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "updateProduce",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 耳模确认制作完成
	 */
	public ResponseEnvelop complete(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			List<EarMould> dtoList = (Vector<EarMould>) map.get("beo");
			String fileKey = "ear03_000";
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			for (EarMould em : dtoList)
			{
				if (em.getTmeno() == null || em.getTmeno().equals(""))
				{
					throw new AppException("该耳模信息未登记");
				}
				else
				{
					em.setFileKey(fileKey);
					modify(con, em, null, 0);
				}
			}
			DBUtil.commit(con);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "complete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 根据用户姓名查询该用户的详细信息
	 */
	public ResponseEnvelop find(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap retmap = new HashMap();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			EarMould em = (EarMould) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			em.setFileKey("ear04_000");
			ArrayList emList = (ArrayList) find(con, em, null, 0);
			if (emList.size() > 1)
			{
				throw new AppException("该用户名对应的记录不唯一");
			}
			retmap.put("beo", emList);
			DBUtil.commit(con);
			response.setBody(retmap);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "find",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 耳模信息显示
	 */
	public ResponseEnvelop printCI(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			EarMould em = (EarMould) map.get("beo");

			List result = (Vector) DBUtil.querySQL(con,
					"select fdtprc from tblfoldetail where fdtfno='"
							+ em.getTmeno() + "'");
			BigDecimal prc = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String price = String.valueOf(prc);

			em.setFileKey("earmould_select");
			ArrayList emList = (ArrayList) find(con, em, null, 0);
			//EarMould emList = (EarMould) find(con, em, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", emList);
			retmap.put("price", price);
			response.setBody(retmap);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "printCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 耳模信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			EarMould em = (EarMould) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			em.setFileKey("earmould_delete");
			remove(con, em, null, 0);
			DBUtil.commit(con);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存耳模质检信息
	 */
	public ResponseEnvelop updateCheckup(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			EarMould em = (EarMould) map.get("beo");
			QA qa = (QA)map.get("qa");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (em.getTmeno() == null || em.getTmeno().equals(""))
			{
				throw new AppException("该耳模信息未登记");
			}
			else
			{
				String sta;
				// 标记相同订单里有没有未质检的耳模
				boolean flag = false;
				// 查找与该耳模同订单号的耳模状态
				List result = (Vector) DBUtil.querySQL(con,
						"select tmesta from tblearmaking where tmeno='"
								+ em.getTmeno() + "' and tmesid != '"
								+ em.getTmesid() + "'");
				if (result.size() >= 1)
				{
					for (int i = 0; i < result.size(); i++)
					{
						sta = (String) ((HashMap) result.get(i)).get("1");
						if (!sta.equals("3"))
						{
							flag = true;
						}
					}
				}
				if (!flag)
				{
					// 同订单的耳模都质检完成的情况下修改订单状态为等待发货
					em.setFileKey("ear03_002");
					modify(con, em, null, 0);
				}

				// 修改耳模状态为质检完成
				em.setTmesta("3");
				em.setFileKey("ear02_001");
				modify(con, em, null, 0);
				
				//修改质检记录
				qa.setQastatus("finish");
				qa.setFileKey("ear02_006");
				modify(con, qa, null, 0);
				// 添加质检记录
				/*
				 * qa.setFileKey("ear05_001"); store(con, qa, null, 0);
				 */

			}
			DBUtil.commit(con);
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "updateCheckup",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存修改后的耳模信息
	 */
	public ResponseEnvelop savemodify(RequestEnvelop request)
	{
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try
		{
			HashMap map = (HashMap) request.getBody();
			EarMould em = (EarMould) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (em.getTmeno() == null || em.getTmeno().equals(""))
			{
				throw new AppException("该耳模信息未登记");
			}
			else
			{
				if (em.getTmesta().equals("3"))
				{
					String sta;
					// 标记相同订单里有没有未质检的耳模
					boolean flag = false;
					// 查找与该耳模同订单号的耳模状态
					List result = (Vector) DBUtil.querySQL(con,
							"select tmesta from tblearmaking where tmeno='"
									+ em.getTmeno() + "' and tmesid != '"
									+ em.getTmesid() + "'");
					if (result.size() >= 1)
					{
						for (int i = 0; i < result.size(); i++)
						{
							sta = (String) ((HashMap) result.get(i)).get("1");
							if (!sta.equals("3"))
							{
								flag = true;
							}
						}
					}
					if (!flag)
					{
						// 同订单的耳模都质检完成的情况下修改订单状态为等待发货
						em.setFileKey("ear03_002");
						modify(con, em, null, 0);
					}
				}
				else
				{
					em.setFileKey("ear03_003");
					modify(con, em, null, 0);
				}
				
				// 修改同一订单的是否赠送字段
				em.setFileKey("ear04_002");
				modify(con, em, null, 0);
				// 修改当前耳模的信息
				if(em.getTmeappear().equals("") || em.getTmechkdt().equals("") || em.getTmechkoprcd().equals("") || em.getTmeden().equals(""))
				{
					//质检信息为空
					em.setFileKey("ear02_005");
				}
				else
				{   //质检信息不为空
					em.setFileKey("ear02_003");
				}
				modify(con, em, null, 0);
				DBUtil.commit(con);
			}
		}
		catch (AppException ae)
		{
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
		catch (Exception ex)
		{
			response.setHead(ExceptionSupport(className, "savemodify",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}
		finally
		{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
