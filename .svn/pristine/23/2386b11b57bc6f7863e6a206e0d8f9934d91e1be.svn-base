/**
 * Rec0803IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.commons.entity.Cb26;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec0803Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 民办职业介绍机构日常投诉
 */
public class Rec0803IMP extends IMPSupport implements Rec0803Facade {
	private String className = this.getClass().getName();

	/**
	 * 增加一条日常投诉信息
	 * 
	 * @author Y.J
	 * @date 2008-3-19 18:38:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveorgancom(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb26 dto = (Cb26) map.get("beo");
			con = DBUtil.getConnection();
			dto.setAcb260(CommonDB.getSequence(con, "SEQ_ACB260", 10, "0"));
			dto.setFileKey("Cb26_insert");
			store(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveorgancom",
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
	 * 修改日常投诉
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
	 * 删除日常投诉
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
