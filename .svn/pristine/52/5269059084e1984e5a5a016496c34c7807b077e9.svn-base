package org.radf.manage.department.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.radf.manage.department.dto.DeptDTO;
import org.radf.manage.entity.Sc01;
import org.radf.manage.entity.Sc03;
import org.radf.manage.entity.Sc04;
import org.radf.manage.department.facade.DepartmentFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;

public class DepartmentImp extends org.radf.plat.util.imp.IMPSupport implements
		DepartmentFacade {
	private String className = this.getClass().getName();

	/**
	 * 获取所有机构以及科室和人员
	 */
	public ResponseEnvelop findsc01andsc05(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			String sqlkey = (String) map.get("beo");
			DeptDTO dto = new DeptDTO();
			dto.setFileKey("sys01_000");
			if (sqlkey != null && sqlkey.equals("2")) {
				dto.setFileKey("sys01_001");
			}
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("list", list);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findsc01andsc05",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 获取所有机构以及科室
	 */
	public ResponseEnvelop findsc01andsc04(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc01 sc01 = (Sc01) map.get("beo");
			ArrayList list = new ArrayList();
			list = (ArrayList) find(con, sc01, null, 0);
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("list", list);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findsc01andsc05",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 获取所有机构以及科室
	 */
	public ResponseEnvelop findsc04(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc04 sc04 = (Sc04) map.get("beo");
			ArrayList list = new ArrayList();
			list = (ArrayList) find(con, sc04, null, 0);
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("list", list);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findsc04",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 增加机构
	 */
	public ResponseEnvelop addSc01(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc01 sc01 = (Sc01) map.get("sc01");
			if (sc01.getBsc001() != null && !sc01.getBsc001().equals("")) {
				sc01.setFileKey("SC01_update");
				sc01.setBsc003(null);
				modify(con, sc01, null, 0);
			} else {
				// 是否存在相同编号的机构
				if (sc01.getAab003() == null || sc01.getAab003().equals("")
						|| sc01.getAab003().trim().length() == 0) {
					throw new AppException("|保存失败！机构编码不能为空|");
				}
				sc01.setFileKey("sys01_013");
				ArrayList sc01List = (ArrayList) find(con, sc01, null, 0);
				if (sc01List == null) {
					sc01.setBsc001(DBUtil.getSequence(con, "SEQ_BSC001"));
					sc01.setFileKey("SC01_insert");
					sc01.setBsc003("00000000000000000000000000000000");
					create(con, sc01, null, 0);
				} else {
					throw new AppException("|保存失败！已存在此机构编码|");
				}
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addSc01",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 增加机构
	 */
	public ResponseEnvelop addSc04(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc04 sc04 = (Sc04) map.get("sc04");
			Sc01 sc01 = new Sc01();
			sc01.setFileKey("sys01_005");
			sc01.setAab003(sc04.getBsc001());
			ArrayList list = (ArrayList) find(con, sc01, null, 0);
			if (list != null && list.size() > 0)
				ClassHelper.copyProperties(list.get(0), sc04);
			if (sc04.getBsc008() != null && !sc04.getBsc008().equals("")) {
				sc04.setBsc001(null);
				sc04.setFileKey("SC04_update");
				modify(con, sc04, null, 0);
			} else {
				sc04.setBsc008(DBUtil.getSequence(con, "SEQ_BSC001"));
				sc04.setFileKey("SC04_insert");
				create(con, sc04, null, 0);
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addSc04",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 为机构统计查找机构表Sc01
	 * 
	 * @author syy
	 * @date 2007-10-23
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findSc01(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap retmap = new HashMap();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc01 sc01 = (Sc01) map.get("beo");
			String function = (String) map.get("function");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			if ("add".equals(function))
			{
				sc01.setFileKey("sys01_015");	
			}else
			{
				sc01.setFileKey("sys01_011");	
			}
		    ArrayList sc01List = (ArrayList) find(con, sc01, null, 0);
			retmap.put("sc01List", sc01List);
		
			if ("mod".equals(function)) {
				Sc01 sc012 = new Sc01();
				sc012.setBsc003(sc01.getBsc003());
				sc012.setFileKey("sys01_012");
				ArrayList sc01List2 = (ArrayList) find(con, sc012, null, 0);
				retmap.put("sc01List2", sc01List2);
			}

			DBUtil.commit(con);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findSc01",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存修改sc01并新增sc03
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc01NSc03(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc03 sc03 = (Sc03) map.get("sc03");
			Sc01 sc01 = new Sc01();
			String function = (String) map.get("function");
			String[] groupList = (String[]) map.get("groupList");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			if ("add".equals(function)) {
				if (sc03.getBsc006() != null && !sc03.getBsc006().equals("")) {
					if (sc03.getBsc007() != null
							&& !sc03.getBsc007().equals("")) {
						sc03.setFileKey("SC03_insert");
						create(con, sc03, null, 0);
					}
				} else {
					throw new AppException("|保存失败！机构统计名称与统计位数不能为空|");
				}

				// 更新机构表
				
				sc01.setBsc003(sc03.getBsc007());
				sc01.setFileKey("sys01_014");
				create(con, sc01, null, 0);
				for (int i = 0; i < groupList.length; i++) {
					if (!groupList[i].equals("") && groupList[i] != null) {
						sc01.setBsc003(sc03.getBsc007());
						sc01.setBsc001(groupList[i]);
						sc01.setFileKey("sys01_010");
						create(con, sc01, null, 0);
					}
				}

			} else {

				sc03.setFileKey("SC03_select");
				ArrayList al = (ArrayList) find(con, sc03, null, 0);
				if (al == null || al.size() == 0) {
					throw new AppException("|保存失败！无此机构统计名称|");
				}

				if (sc03.getBsc006() != null && !sc03.getBsc006().equals("")) {
					if (sc03.getBsc007() != null
							&& !sc03.getBsc007().equals("")) {
						sc03.setFileKey("SC03_update");
						modify(con, sc03, null, 0);
					}
				} else {
					throw new AppException("|保存失败！机构统计名称与统计位数不能为空|");
				}

				// 更新机构表
				sc01.setBsc003(sc03.getBsc007());
				sc01.setFileKey("sys01_014");
				create(con, sc01, null, 0);
				for (int i = 0; i < groupList.length; i++) {
					if (!groupList[i].equals("") && groupList[i] != null) {
						sc01.setBsc003(sc03.getBsc007());
						sc01.setBsc001(groupList[i]);
						sc01.setFileKey("sys01_010");
						create(con, sc01, null, 0);
					}
				}

			}

			// 向外传递数据
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifySc05",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}