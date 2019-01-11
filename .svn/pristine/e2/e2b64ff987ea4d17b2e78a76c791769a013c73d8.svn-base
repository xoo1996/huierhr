/**
 * <p>标题: 用户－角色－模块对应关系处理类</p>
 * <p>说明: IMP</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-5-1912:04:03</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.manage.role.imp;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

import org.radf.manage.role.bpo.SysAclBPO;
import org.radf.manage.role.bpo.SysActBPO;
import org.radf.manage.role.bpo.SysRoleBPO;
import org.radf.manage.role.bpo.SysUserBPO;
import org.radf.manage.role.entity.SysAcl;
import org.radf.manage.role.entity.SysAct;
import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.AclFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class AclIMP extends org.radf.plat.util.imp.IMPSupport implements
		AclFacade {
	private String className = this.getClass().getName();

	private SysActBPO actBPO = new SysActBPO();

	private SysAclBPO aclBPO = new SysAclBPO();

	private SysUserBPO userBPO = new SysUserBPO();

	private SysRoleBPO roleBPO = new SysRoleBPO();

	/** ************************************关于SysACL的方法******************************* */
	/**
	 * 生成一条ACL记录
	 */
	public ResponseEnvelop createAcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		// SysAcl acl = (SysAcl) request.getBody();
		HashMap map = (HashMap) request.getBody();
		Vector vc = (Vector) map.get("ACl");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			if (vc.size() > 0) {
				for (int i = 0; i < vc.size(); i++) {
					SysAcl acl = (SysAcl) vc.get(i);
					SysRole role = (SysRole) roleBPO.findRoleByPK(con,
							new SysRole(acl.getRoleID()));
					if (role == null) {
						response.setHead(ExceptionSupport(className,
								"createAcl", ManageErrorCode.SQLERROR,
								GlobalErrorMsg.IMP_ROLE_NOEXIST, request
										.getHead()));
					} else {
						response.setBody(aclBPO.createAcl(con, acl));
					}
				}
			}
			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "createAcl",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 用主键查找指定ACL记录
	 */
	public ResponseEnvelop findAclByPK(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysAcl acl = (SysAcl) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			SysAcl sysAcl = aclBPO.findAclByPK(con, acl);
			response.setBody(sysAcl);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findAclByPK",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return response;

	}

	/**
	 * 查找roleID对应的ACL记录集
	 */
	public ResponseEnvelop findAclByRole(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysRole role = (SysRole) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			response.setBody(aclBPO.findByRole(con, role));
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findAclByRole",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return response;

	}

	/**
	 * 更新指定ACL记录
	 */
	public ResponseEnvelop modifyAcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysAcl acl = (SysAcl) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			aclBPO.modifyAcl(con, acl);
			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyAcl",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 删除指定ACL记录
	 */
	public ResponseEnvelop removeAcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		Vector vc = (Vector) map.get("ACl");
		SysAcl acl = new SysAcl();
         
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			if (vc.size() > 0) {
				for (int i = 0; i < vc.size(); i++) {
                    acl.setAclID((String) vc.get(i));
					aclBPO.removeAcl(con, acl);
				}
			}
			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "removeAcl",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/** ************************************关于SysACT的方法******************************* */
	/**
	 * 生成一条Act记录
	 */
	public ResponseEnvelop createAct(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		Vector vc = (Vector) map.get("ACT");

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			if (vc.size() > 0) {
				for (int i = 0; i < vc.size(); i++) {
					SysAct act = (SysAct) vc.get(i);

					SysUser dto = (SysUser) userBPO.findUserByPK(con,
							new SysUser(act.getUserID()));
					if (dto == null) {
						response.setHead(ExceptionSupport(className,
								"createAct", ManageErrorCode.SQLERROR,
								GlobalErrorMsg.IMP_USER_NOEXIST, request
										.getHead()));
					} else {
						SysRoleBPO roleBPO = new SysRoleBPO();
						SysRole dot = (SysRole) roleBPO.findRoleByPK(con,
								new SysRole(act.getRoleID()));
						if (dot == null) {
							response.setHead(ExceptionSupport(className,
									"createAct", ManageErrorCode.SQLERROR,
									GlobalErrorMsg.IMP_ROLE_NOEXIST, request
											.getHead()));
						} else {
							response.setBody(actBPO.createAct(con, act));
						}
					}
				}
			}
			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "createAct",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 查找roleid对应的Act记录集
	 */
	public ResponseEnvelop findAllActByRole(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysRole role = (SysRole) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			response.setBody(actBPO.findByRole(con, role));
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findAllActByRole",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 查找userID对应的Act记录集
	 */
	public ResponseEnvelop findAllActByUser(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysUser user = (SysUser) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			response.setBody(actBPO.findByUser(con, user));
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findAllActByUser",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 用主键查找指定Act记录
	 */
	public ResponseEnvelop findActByPK(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysAct act = (SysAct) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			SysAct sysAct = (SysAct) actBPO.findActByPK(con, act);
			response.setBody(sysAct);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "createAct",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null)
					con.rollback();
				con.close();
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 更新指定Act记录
	 */
	public ResponseEnvelop modifyAct(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		SysAct act = (SysAct) request.getBody();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);

			actBPO.modifyAct(con, act);

			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyAct",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
		return response;
	}

	/**
	 * 删除指定Act记录 删除用户－角色对应关系
	 */
	public ResponseEnvelop removeAct(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		Vector vc = (Vector) map.get("ACT");
        SysAct act = new SysAct();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			if (vc.size() > 0) {
				for (int i = 0; i < vc.size(); i++) {
					act.setActID((String) vc.get(i));
					actBPO.removeAct(con, act);
				}
			}
			con.commit();
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "createAct",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
		return response;
	}
}
