package org.radf.apps.contract.imp;

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
import org.radf.apps.commons.entity.Contract;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.ReDiagnose;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Tblitsmci;
import org.radf.apps.contract.facade.ContractFacade;

public class ContractImp extends IMPSupport implements ContractFacade {
	private String className = this.getClass().getName();
	@Override
	public ResponseEnvelop addContract(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Contract contract = (Contract) map.get("dto");
			//判断合同编号是否存在
			String sql="select * from tblcontract where conid='" + contract.getConid() + "'";
			List result= (Vector)DBUtil.querySQL(con,sql) ;
			if (result.size() > 0) {
				throw new AppException("该合同编号已经存在！");
			}
			
			contract.setFileKey("con_000002");
			store(con, contract, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "addContract",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop saveContract(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Contract contract = (Contract) map.get("dto");
			
			contract.setFileKey("con_000004");
			modify(con, contract, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "saveContract",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	@Override
	public ResponseEnvelop delContract(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			Contract contract = (Contract) map.get("dto");
			contract.setFileKey("con_000005");
			remove(con, contract, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}catch (Exception ex) {
				ex.printStackTrace();
				response.setHead(ExceptionSupport(className, "delContract",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}


}
