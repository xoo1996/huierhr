/**
 * 项目: Rapid Application Development Framework
 * 所在包名称:   org.radf.manage.param.imp
 * 类名称:      ParamIMP.java
 * 创建者:      syg
 * 创建时间:     2006-11-14
 */
package org.radf.manage.param.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.radf.manage.param.entity.Aa01;
import org.radf.manage.param.entity.Aa10;
import org.radf.manage.param.facade.ParamFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;

/**
 * 创建者 syg
 */
public class ParamIMP extends org.radf.plat.util.imp.IMPSupport implements
		ParamFacade {
	private String className = this.getClass().getName();
	/**
	 * 增加代码信息
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop AddAa01(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		// 得到从request中的HashMap
		HashMap map = (HashMap) request.getBody();
		Aa01 aa01 = (Aa01) map.get("beo");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			aa01.setFileKey("selectAA01");
			// 获取当前数据
			ArrayList list = (ArrayList) find(con, aa01, null, 0);
			Aa01 aa = null;
			if (list != null && list.size() > 0) {
				aa = new Aa01();
				ClassHelper.copyProperties(list.get(0), aa);
			}
			if (aa != null) {
				throw new AppException("您要保存的数据已存在,请核对你输入数据项");
			}
			aa01.setFileKey("insertAA01");
			create(con, aa01, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "AddAa10",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
			return response;
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 增加代码信息
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop AddAa10(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		// 得到从request中的HashMap
		HashMap map = (HashMap) request.getBody();
		Aa10 aa10 = (Aa10) map.get("beo");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			aa10.setFileKey("aa10_select");
			// 获取当前数据
			ArrayList list = (ArrayList) find(con, aa10, null, 0);
			Aa10 aa = null;
			if (list != null && list.size() > 0) {
				aa = new Aa10();
				ClassHelper.copyProperties(list.get(0), aa);
			}
			if (aa != null) {
				throw new AppException("您要保存的数据已存在,请核对你输入数据项");
			}
			aa10.setFileKey("aa10_insert");
			aa10.setBaa108("1");
			aa10.setAaa104("1");aa10.setBaa100(StringUtil.MnemonicWords(aa10.getAaa103()));
			create(con, aa10, null, 0);
			OptionDictSupport.add(aa10.getAaa100(), aa10.getAaa102(), aa10
					.getAaa103(), false, aa10.getAae013(), true);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "AddAa10",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
			return response;
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 修改代码信息
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifAa10(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		// 得到从request中的HashMap
		HashMap map = (HashMap) request.getBody();
		Aa10 aa10 = (Aa10) map.get("beo");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			aa10.setFileKey("aa10_select");
			// 获取当前数据
			ArrayList list = (ArrayList) find(con, aa10, null, 0);
			Aa10 aa = null;
			if (list != null && list.size() > 0) {
				aa = new Aa10();
				ClassHelper.copyProperties(list.get(0), aa);
			}
			aa10.setBaa100(StringUtil.MnemonicWords(aa10.getAaa103()));
			aa10.setFileKey("aa10_update");
			if (aa != null) {
				if (aa.getAaa104() != null && aa.getAaa104().equals("1")
						&& aa.getAaa104().equals(aa10.getAaa104())) {
					modify(con, aa10, null, 0);
					boolean isView = false;
					if (aa10.getBaa109() != null
							&& aa10.getBaa109().equals("1")) {
						isView = true;
					}

					OptionDictSupport.update(aa10.getAaa100(),
							aa10.getAaa102(), aa10.getAaa103(), false, aa10
									.getAae013(), isView);
				} else {
					if (aa10.getAaa104() != null
							&& aa10.getAaa104().equals("1")) {
						modify(con, aa10, null, 0);
						boolean isView = false;
						if (aa10.getBaa109() != null
								&& aa10.getBaa109().equals("1")) {
							isView = true;
						}

						OptionDictSupport.update(aa10.getAaa100(), aa10
								.getAaa102(), aa10.getAaa103(), false, aa10
								.getAae013(), isView);
					} else {
						throw new AppException("您所需要修改的数据项是不可维护,不能修改");
					}
				}
			}

			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifAa10",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
			return response;
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
