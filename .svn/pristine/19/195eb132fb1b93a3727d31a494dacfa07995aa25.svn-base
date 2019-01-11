package org.radf.manage.menu.imp;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.radf.manage.entity.Sc08;
import org.radf.manage.menu.dto.MenuDTO;
import org.radf.manage.menu.facade.MenuFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class MenuImp extends IMPSupport implements MenuFacade {
	private String className = this.getClass().getName();

	/**
	 * 查找记录
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findSc08(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}

	/**
	 * 保存修改菜单信息
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc08(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc08 sc08DTO = (Sc08) map.get("sc08DTO");
			String function = (String) map.get("function");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if ("add".equals(function)) {
				sc08DTO.setFileKey("sc08_insert");
				create(con, sc08DTO, null, 0);
			} else {
				sc08DTO.setFileKey("SC08_update");
				modify(con, sc08DTO, null, 0);
			}
			// 向外传递数据
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("bsc016", sc08DTO.getBsc016());
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifySc08",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 删除记录
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delSc08(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc08 sc08 = new Sc08();
			String[] bsc016 = (String[]) map.get("bsc016");
			con = DBUtil.getConnection();
			if (bsc016 != null && bsc016.length > 0) {
				for (int i = 0; i < bsc016.length; i++) {
					sc08.setBsc016(bsc016[i]);
					if (!sc08.getBsc016().equals("")
							&& sc08.getBsc016() != null) {
						sc08.setFileKey("sys04_003");
						if(getCount(con, sc08, 0)>0)
						{
							throw new AppException("你选择要删除的数据里有被使用过的菜单，不能删除");
						}
						sc08.setFileKey("SC08_delete");
						remove(con, sc08, null, 0);
					}
				}
			}else{
				throw new AppException("请选择你要删除的数据");
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delSc08",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop orderSc08(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection collection = (Collection) map.get("collection");
			con = DBUtil.getConnection();
			Iterator it = collection.iterator();			
			while (it.hasNext()) {
				MenuDTO menudto = new MenuDTO();
				ClassHelper.copyProperties(it.next(), menudto);
				if (!menudto.getBsc016().equals("")	&& menudto.getBsc016() != null) {
					menudto.setFileKey("sys04_004");
					modify(con, menudto, null, 0);
					}
			}
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delSc08",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
