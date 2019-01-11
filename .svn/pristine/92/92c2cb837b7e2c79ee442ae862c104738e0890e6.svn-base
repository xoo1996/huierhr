/**
 * Rec0801IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.recommendation.ownpintorgan.dto.Rec0801DTO;
import org.radf.apps.recommendation.ownpintorgan.facade.Rec0801Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 民办职业介绍机构年检
 */
public class Rec0801IMP extends IMPSupport implements Rec0801Facade {
	private String className = this.getClass().getName();

	/**
	 * 查询机构信息，初始化增加页面
	 * 
	 * @author Y.J
	 * @date 2008-3-19 18:38:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initaddorganyc(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Rec0801DTO dto = (Rec0801DTO) map.get("beo");
			con = DBUtil.getConnection();
			dto.setFileKey("rec08_002");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("该机构在该年度已经年检了!");
			}
			dto.setFileKey("rec08_003");
			Object obj = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", obj);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "initaddorganyc",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存机构年检
	 * 
	 * @author Y.J
	 * @date 2008-3-19 18:38:12
	 * @param request
	 * @return
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		return store(request);
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
	 * 修改机构年检
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
	 * 删除机构年检
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
