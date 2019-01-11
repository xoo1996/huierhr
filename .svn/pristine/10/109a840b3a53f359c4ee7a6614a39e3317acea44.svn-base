/**
 * Rec0701IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */

package org.radf.apps.recommendation.employassembly.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.recommendation.employassembly.facade.Rec0701Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 大会招聘登记
 */
public class Rec0701IMP extends IMPSupport implements Rec0701Facade {

	private String className = this.getClass().getName();

	/**
	 * 为初始化大会招聘信息新增界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、读取ab01单位基本信息，初始化页面<br>
     * 2、招聘类别为大会招聘<br>
     * 3、页面数据项包括表cb20得数据项<br></tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initaddEmployInvite(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 新增大会招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、数据保存后方能进入下一步登记招聘岗位信息<br>
     * 2、插入数据到cb20单位招聘信息表</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addEmployInvite(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Cb20 cb20 = (Cb20) map.get("beo");
			cb20.setFileKey("cb20_insert");
			con = DBUtil.getConnection();
			cb20.setAcb200(CommonDB.getSequence(con, "SEQ_ACB200", 10, "0"));
			store(con, cb20, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", cb20);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modPerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
