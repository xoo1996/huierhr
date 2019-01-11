package org.radf.apps.cfgmgmt.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.cfgmgmt.facade.CIFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.commons.entity.Tblitsmci;

public class CIImp extends IMPSupport implements CIFacade {
	private String className = this.getClass().getName();

	/**
	 * 新增配置项信息
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop saveCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;

		try {
			HashMap map = (HashMap) request.getBody();
			Tblitsmci tblitsmci = (Tblitsmci) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 判断ID是否存在和重复
			if (null == tblitsmci.getCiid() || "".equals(tblitsmci.getCiid())) {
				throw new AppException("新加入的配置项ID号不能为空!");
			}
			//判断配置项ID号是否重复
			tblitsmci.setFileKey("tblitsmci_select");
			if (getCount(con, tblitsmci, 0) > 0) {
				throw new AppException("该配置项ID号已存在!");
			}
			tblitsmci.setFileKey("tblitsmci_insert");
			//插入新配置项记录
			store(con, tblitsmci, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("ciid", tblitsmci.getCiid());
			retmap.put("workString", "新增配置项信息");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 配置项信息修改
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop modifyCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Tblitsmci> map = (HashMap<String, Tblitsmci>) request.getBody();
			Tblitsmci dto = (Tblitsmci) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getCiid() == null || dto.getCiid().equals("")) {
				throw new AppException("该配置项信息未登记");
			} else {
				dto.setFileKey("tblitsmci_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 配置项信息删除
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop deleteCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, Tblitsmci> map = (HashMap<String, Tblitsmci>) request.getBody();
			Tblitsmci dto = (Tblitsmci) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getCiid() == null || dto.getCiid().equals("")) {
				throw new AppException("该配置项信息未登记");
			} else {
				dto.setFileKey("tblitsmci_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * 配置项信息显示
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop printCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Tblitsmci dto = (Tblitsmci) map.get("beo");
			dto.setFileKey("tblitsmci_select");// 配置项详细信息
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
