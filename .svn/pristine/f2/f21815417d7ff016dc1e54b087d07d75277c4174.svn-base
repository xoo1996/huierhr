package org.radf.apps.qa.imp;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.qa.facade.QAFacade;

public class QAImp extends IMPSupport implements QAFacade {
	private String className = this.getClass().getName();
	
	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			QA qadto = (QA) map.get("beo");
			String fileKey = "qa_update";
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			qadto.setFileKey(fileKey);
			modify(con, qadto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateqa",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop finish1before(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			QA qadto = (QA) map.get("beo");
			Boolean flag = (Boolean)map.get("flag");
			//String pnm = (String)map.get("pnm");
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			qadto.setFileKey("qa_update");
			modify(con, qadto, null, 0);
			if(null != qadto.getQatype() && !("make".equals(qadto.getQatype()))){
				qadto.setFileKey("qat02_000");
				modify(con, qadto, null, 0);
			}
			if(null != qadto.getQapid() && ("唯听".equals(qadto.getPdttype()) || qadto.getQapid().equals("999906")||qadto.getQapid().equals("999907")||qadto.getQapid().equals("999812")||qadto.getQapid().equals("999813")||qadto.getQapid().equals("999720")||qadto.getQapid().equals("999721")||qadto.getQapid().equals("069950")||qadto.getQapid().equals("069951")||qadto.getQapid().equals("069952")||qadto.getQapid().equals("069870")||qadto.getQapid().equals("069871")||qadto.getQapid().equals("069872")||qadto.getQapid().equals("069750")||qadto.getQapid().equals("069751")||qadto.getQapid().equals("069752")))
			{
				qadto.setFileKey("qat02_000");
				modify(con, qadto, null, 0);
			}
			
			DBUtil.commit(con);
			
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateqa",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop finish2before(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			QA qadto = (QA) map.get("beo");
			Boolean flag = (Boolean)map.get("flag");
			//String pnm = (String)map.get("pnm");
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			qadto.setFileKey("qa_update");
			modify(con, qadto, null, 0);
			if(null != qadto.getQatype() && !("make".equals(qadto.getQatype()))){
				qadto.setFileKey("qat02_000");
				modify(con, qadto, null, 0);
			}
			if(null != qadto.getQapid() && ("唯听".equals(qadto.getPdttype()) || qadto.getQapid().equals("999906")||qadto.getQapid().equals("999907")||qadto.getQapid().equals("999812")||qadto.getQapid().equals("999813")||qadto.getQapid().equals("999720")||qadto.getQapid().equals("999721")||qadto.getQapid().equals("069950")||qadto.getQapid().equals("069951")||qadto.getQapid().equals("069952")||qadto.getQapid().equals("069870")||qadto.getQapid().equals("069871")||qadto.getQapid().equals("069872")||qadto.getQapid().equals("069750")||qadto.getQapid().equals("069751")||qadto.getQapid().equals("069752")))
			{
				qadto.setFileKey("qat02_000");
				modify(con, qadto, null, 0);
			}
			
			DBUtil.commit(con);
			
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateqa",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop finish(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			QA qadto = (QA) map.get("beo");
			Boolean flag = (Boolean)map.get("flag");
			//String pnm = (String)map.get("pnm");
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			//2013/11/30
			String sql0 ="select q.qastatus from tblqa  q where q.qafno='" + qadto.getQafno() + "'";
			List result0 = (Vector)DBUtil.querySQL(con, sql0);
			String qastatus = ((HashMap)result0.get(0)).get("1").toString();
			if(qastatus.equals("finish"))
			{
				throw new AppException("本产品已质检");
			}
			if(flag)
			{
				if(null != qadto.getQatype() && "make".equals(qadto.getQatype())){//如果是定制订单，则进行检测
					String sql = "select m.tmkpnl,p.pdtid from tblfoldetail d left outer join tblproduct p on p.pdtid=d.fdtpid" +
						" left outer join tblmaking m on m.tmkfno=d.fdtfno where d.fdtfno='" + qadto.getQafno() + "'";
				
					String pnl = (String)map.get("pnl");//9.1
					String pnm = (String)map.get("pnm");//9.1
					List result = (Vector)DBUtil.querySQL(con, sql);
					String pnl0 = ((HashMap)result.get(0)).get("1").toString();
					String pid0 = ((HashMap)result.get(0)).get("2").toString();
					pnl0=pnl0.substring(2,pnl0.length());
					if(!pnl.trim().equals(pnl0) ||!pnm.trim().equals(pid0)){
						throw new AppException("质检信息与订单信息不匹配!");
					
					}
				}
			}
			
			qadto.setFileKey("qa_update2");
			modify(con, qadto, null, 0);
			qadto.setFileKey("qat02_000");
			modify(con, qadto, null, 0);
			
			DBUtil.commit(con);
			
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateqa",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
