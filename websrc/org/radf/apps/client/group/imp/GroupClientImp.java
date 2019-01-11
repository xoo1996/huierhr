package org.radf.apps.client.group.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Tblitsmci;

public class GroupClientImp extends IMPSupport implements GroupClientFacade {
	private String className = this.getClass().getName();

	/**
	 * 新增团体客户信息
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			GroupClient gc = (GroupClient) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 判断ID是否存在和重复
			if (null == gc.getGctid() || "".equals(gc.getGctid())) {
				throw new AppException("新加入的团体客户号不能为空!");
			}
			// 判断ID号是否重复
			gc.setFileKey("groupClient_select");
			if (getCount(con, gc, 0) > 0) {
				throw new AppException("该团体客户号已存在!");
			}
			gc.setFileKey("groupClient_insert");
			// 插入新团体客户记录
			store(con, gc, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("gctid", gc.getGctid());
			retmap.put("workString", "新增团体客户信息");
			response.setBody(retmap);
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
	 * 团体客户信息修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, GroupClient> map = (HashMap<String, GroupClient>) request
					.getBody();
			GroupClient dto = (GroupClient) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getGctid() == null || dto.getGctid().equals("")) {
				throw new AppException("该团体客户信息未登记");
			} else {
				dto.setFileKey("groupClient_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modify",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 配置项信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap<String, GroupClient> map = (HashMap<String, GroupClient>) request
					.getBody();
			GroupClient dto = map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getGctid() == null || dto.getGctid().equals("")) {
				throw new AppException("该团体客户信息未登记");
			} else {
				dto.setFileKey("groupClient_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 团体客户信息显示
	 */
	public ResponseEnvelop print(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			GroupClient dto = (GroupClient) map.get("beo");
			dto.setFileKey("groupClient_select");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 查询团体客户名称
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop queryGCName(RequestEnvelop request) {
		return find(request);
	}

	
	
	/**
	 * 根据团体客户名称查询客户ID
	 */
	public ResponseEnvelop query(RequestEnvelop request) {
		return find(request);
	}
}
