/**
 * Rec03IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.radf.apps.recommendation.query.dto.Rec03DTO;
import org.radf.apps.recommendation.query.facade.Rec03Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 职业综合信息查询
 */
public class Rec03IMP extends IMPSupport implements Rec03Facade {
	private String className = this.getClass().getName();

	/**
	 * 查询单位信息 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop queryEmployer(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Rec03DTO dto = (Rec03DTO) map.get("beo");
		String temp = (String) map.get("beo1");
		ArrayList list = new ArrayList();
		try {
			con = DBUtil.getConnection();
			String hql002 = DBUtil.getSQLByObject(dto, 4);
			hql002 = hql002 + temp;

			list = (ArrayList) findBySQL(con, hql002, -1, -1, null, 0);

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
		retmap.put("beo", list);
		response.setBody(retmap);
		return response;
	}

}
