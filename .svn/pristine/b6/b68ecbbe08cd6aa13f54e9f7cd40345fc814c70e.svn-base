/**
 * Rec05IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personguidance.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.radf.apps.commons.entity.Cc23;
import org.radf.apps.recommendation.personguidance.facade.Rec05Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 职业指导管理
 */
public class Rec05IMP extends IMPSupport implements Rec05Facade {
	private String className = this.getClass().getName();

	/**
	 * 保存职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Cc23 cc23 = (Cc23) map.get("beo");

		try {
			con = DBUtil.getConnection();
			cc23.setFileKey("rec05_003");
			List list = (ArrayList) find(con, cc23, null, 0);
			if (list != null) {
				throw new AppException("该人员今天已经进行了一次就业指导，不能保存，请进行修改！");
			}
			cc23.setAcc230(CommonDB.getSequence(con, "SEQ_ACC230", 10, "0"));
			cc23.setFileKey("cc23_insert");
			store(con, cc23, null, 0);
			DBUtil.commit(con);

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
	 * 修改职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop edit(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Cc23 cc23 = (Cc23) map.get("beo");

		try {
			con = DBUtil.getConnection();
			cc23.setFileKey("rec05_004");
			List list = (ArrayList) find(con, cc23, null, 0);
			if (list != null) {
				throw new AppException("该人员今天已经进行了一次就业指导，不能保存，请进行修改！");
			}

			cc23.setFileKey("cc23_update");
			store(con, cc23, null, 0);
			DBUtil.commit(con);

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
	 * 查询职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findpersonguidance(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Cc23 cc23 = (Cc23) map.get("beo");
		try {
			con = DBUtil.getConnection();
			cc23.setFileKey("cc23_select");
			List list = (ArrayList) find(con, cc23, null, 0);
			if (list == null) {
				throw new AppException("该人员无就业指导信息！");
			} else {
				ClassHelper.copyProperties(list.get(0), cc23);

			}

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findPersonInfo",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.closeConnection(con);
		}
		HashMap retmap = new HashMap();
		retmap.put("beo", cc23);
		response.setBody(retmap);
		return response;
	}

}
