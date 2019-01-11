/**
 * Rec08IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.imp;

import java.sql.Connection;
import java.util.HashMap;
import org.radf.apps.commons.entity.Cb24;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec08Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 民办职业介绍机构管理
 */
public class Rec08IMP extends IMPSupport implements Rec08Facade {
	private String className = this.getClass().getName();

	/**
	 * 保存 机构信息
	 * 
	 * @author Y.J
	 * @date 2008-3-19 18:37:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveorgan(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb24 dto = (Cb24) map.get("beo");
			con = DBUtil.getConnection();
			dto.setFileKey("rec08_008");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("该机构下本单位已经存在！");
			}
			dto.setAcb240(CommonDB.getSequence(con, "SEQ_ACB240", 10, "0"));
			dto.setFileKey("Cb24_insert");
			store(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveorgan",
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
	 * @date 2008-3-19 18:38:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop search(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 修改民办职业介绍机构
	 * 
	 * @author Y.J
	 * @date 2008-3-19 18:39:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifyCommon(RequestEnvelop request) {
		return modify(request);
	}

	/**
	 * 删除民办职业介绍机构
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:15
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		return remove(request);
	}
}
