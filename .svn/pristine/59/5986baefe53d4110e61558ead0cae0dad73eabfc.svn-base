/**
 * Rec0703IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.commons.entity.Cb23;
import org.radf.apps.recommendation.employassembly.facade.Rec0703Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 大会招聘岗位信息管理
 */
public class Rec0703IMP extends IMPSupport implements Rec0703Facade {

	private String className = this.getClass().getName();

	/**
	 * 初始化大会招聘期数信息
	 * 
	 * @author Y.J
	 * @date 2008-3-19 12:50:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initddqs(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb23 dto = (Cb23) map.get("beo");
			con = DBUtil.getConnection();
			dto.setFileKey("rec07_010");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("该机构在该期数里已经登记了!");
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "initddqs",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存 大会招聘期数信息
	 * 
	 * @author Y.J
	 * @date 2008-3-19 12:50:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveqs(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb23 dto = (Cb23) map.get("beo");
			con = DBUtil.getConnection();
			dto.setAae100("1");// 默认有效
			if ("1".equals(dto.getAae100())) {
				dto.setFileKey("rec07_011");
				if (getCount(con, dto, 0) > 0) {
					throw new AppException("该机构下已有有效纪录!，请先注销原有有效信息");
				}
			}
			dto.setFileKey("rec07_010");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("该机构在该期数里已经登记了!");
			}
			dto.setAcb230(CommonDB.getSequence(con, "SEQ_ACB230", 10, "0"));
			dto.setFileKey("cb23_insert");
			store(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveqs",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 所有的查询
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:13:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop search(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 修改
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:08
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifyCommon(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb23 dto = (Cb23) map.get("beo");
			String zx = (String) map.get("beo1");
			con = DBUtil.getConnection();
			if ("1".equals(dto.getAae100())) {
				dto.setFileKey("rec07_011");
				if (getCount(con, dto, 0) > 0) {
					throw new AppException("该机构下已有有效纪录!，请先注销原有有效信息");
				}
			}
			if ("zx".equals(zx)) {
				dto.setFileKey("rec07_012");
			} else {
				dto.setFileKey("cb23_update");
			}

			modify(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveqs",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 删除
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:15
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb23 dto = (Cb23) map.get("beo");
			con = DBUtil.getConnection();
			dto.setFileKey("rec07_013");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("该大会期数已经被使用,不能删除!");
			}

			dto.setFileKey("cb23_delete");

			remove(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveqs",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
