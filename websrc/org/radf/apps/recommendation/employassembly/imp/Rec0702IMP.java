/**
 * Rec0702IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.recommendation.employassembly.dto.Rec0702DTO;
import org.radf.apps.recommendation.employassembly.facade.Rec0702Facade;
import org.radf.apps.recommendation.employassembly.form.Rec0702Form;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * 大会招聘岗位信息管理
 */
public class Rec0702IMP extends IMPSupport implements Rec0702Facade {
	private String className = this.getClass().getName();

	/**
	 * 进入空岗位位页面<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、以分页标签新式查询cb21中对应岗位信息<br>
     * 2、设置新增 、修改、删除、查看等功能</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop toNext(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 为初始化招聘空位修改界面提供数据<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、查询出cb21表中的指定数据，并显示页面上</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modEmptyPost(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Rec0702Form result = new Rec0702Form();
		Ab01 ab01 = new Ab01();
		Cb20 cb20 = new Cb20();
		Cb21 cb21 = new Cb21();
		try {
			HashMap map = (HashMap) request.getBody();
			Rec0702Form form = (Rec0702Form) map.get("beo");
			ab01.setAab001(form.getAab001());
			ab01.setFileKey("ab01_select");
			cb20.setAcb200(form.getAcb200());
			cb20.setFileKey("cb20_select");
			cb21.setAcb210(form.getAcb210());
			cb21.setFileKey("cb21_select");
			con = DBUtil.getConnection();
			List list = (ArrayList) find(con, ab01, null, 0);
			ClassHelper.copyProperties(list.get(0), result);
			list = (ArrayList) find(con, cb20, null, 0);
			ClassHelper.copyProperties(list.get(0), result);
			list = (ArrayList) find(con, cb21, null, 0);
			ClassHelper.copyProperties(list.get(0), result);
			DBUtil.closeConnection(con);
			ParaUtil pu = new ParaUtil();
			String acb21o = (pu.getParaV("tjbl", "tjbl", "rec")).toString();
			result.setAcb21o(acb21o);
			HashMap retmap = new HashMap();
			retmap.put("beo", result);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modEmptyPost",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 新增招聘空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、对应cb20表主建将数据插入cb21表中</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addEmptyPost(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Cb21 cb21 = new Cb21();
		try {
			String newdate = DateUtil.getSystemCurrentTime().toString();
			HashMap map = (HashMap) request.getBody();
			Rec0702DTO dto = (Rec0702DTO) map.get("beo");
			ClassHelper.copyProperties(dto, cb21);
			cb21.setAcb208("0");
			// dto.setTmp002(GlobalNames.get_INITCONFIG("NAME_CONFIG_COUNT"));
			cb21.setFileKey("cb21_insert");
			// 人数
			if (cb21.getAcb21d() == null)
				cb21.setAcb21d(Short.valueOf("0"));
			ParaUtil pu = new ParaUtil();
			int acb21o = Integer.parseInt((pu.getParaV("tjbl", "tjbl", "rec"))
					.toString());
			if (!cb21.getAcb21d().toString().equals("")) {
				int acb21d = cb21.getAcb21d().intValue();
				int acb21l = acb21d * acb21o;
				cb21.setAcb211(Short.valueOf(acb21l + ""));
			}

			if (cb21.getAcb218() == null)
				cb21.setAcb218(Short.valueOf("0"));
			con = DBUtil.getConnection();
			cb21.setAcb210(newdate.substring(2, 4)
					+ newdate.substring(5, 7)
					+ CommonDB.getSequence(con, "SEQ_ACB210", 8, "0")
							.substring(3, 8));

			store(con, cb21, null, 0);

			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addEmptyPost",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * * 修改招聘空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将用户修改后的数据保存到cb21表中，具体数据项参照表结构</tt>
	 */
	public ResponseEnvelop modsaveEmptyPost(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Cb21 cb21 = new Cb21();
		String newdate = DateUtil.getSystemCurrentTime().toString();
		try {
			HashMap map = (HashMap) request.getBody();
			Rec0702DTO input = (Rec0702DTO) map.get("beo");
			ClassHelper.copyProperties(input, cb21);

			// 人数
			if (cb21.getAcb21d() == null)
				cb21.setAcb21d(Short.valueOf("0"));
			ParaUtil pu = new ParaUtil();
			int acb21o = Integer.parseInt((pu.getParaV("tjbl", "tjbl", "rec"))
					.toString());
			if (!cb21.getAcb21d().toString().equals("")) {
				int acb21d = cb21.getAcb21d().intValue();
				int acb21l = acb21d * acb21o;
				cb21.setAcb211(Short.valueOf(acb21l + ""));
			}

			con = DBUtil.getConnection();
			cb21.setFileKey("cb21_update");
			modify(con, cb21, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modEmptyPost",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return null;
	}

	/**
	 * 注销一个空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、将选中的岗位信息，注销掉，即把岗位状态改为注销标记</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delEmptyPost(RequestEnvelop request) {
		return modify(request);
	}

	/**
	 * 删除一个空位信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除岗位信息</br>
     * 2、删除的岗位信息必须是没有推荐过的，即cc22表中没有该岗位编号对应的纪录</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop deleteEmptyPost(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Cb21 cb21 = (Cb21) map.get("beo");
		try {
			con = DBUtil.getConnection();
			cb21.setFileKey("rec07_006");
			if (getCount(con, cb21, 0) > 0) {
				throw new AppException("该岗位已经被推荐，不能删除");
			}
			cb21.setFileKey("cb21_delete");
			remove(con, cb21, null, 0);
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
	 * 查看单位招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、以详细信息的形势显示单位本次招聘的信息，数据来源于cb20、ab01</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewEmployInvite(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 查看大会招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、以详细信息的形势显示单位大会招聘的信息，数据来源于cb20、ab01</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewEmployInviteDhzp(RequestEnvelop request) {
		return find(request);
	}
	/**
	  * 修改大会招聘信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据录入信息修改cb20数据</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modEmployInvite(RequestEnvelop request) {
		return modify(request);
	}
}
