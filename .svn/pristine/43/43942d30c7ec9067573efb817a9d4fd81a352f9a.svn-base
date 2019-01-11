package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.radf.manage.role.entity.SysAct;
import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * 用户－角色关系对照表维护
 * 维护Act数据的DAO
 * @param zqb
 * @version 1.0
 */
public class ActDAO extends org.radf.plat.util.dao.DAOSupport {

    private static final String INSERT_SQL =
        "insert into SYSACT (ROLEID,USERID,ACTID ) values (?,?,?)";
    private static final String UPDATE_SQL =
        "update SYSACT set ROLEID = ?, USERID = ? where ACTID = ?";
	private static final String DELETE_ACT_SQL =
		"delete from  SYSACT  where ACTID = ?";
    private static final String DELETE_USER_SQL =
        "delete from  SYSACT  where USERID = ?";
    private static final String DELETE_ROLE_SQL =
        "delete from  SYSACT  where ROLEID = ?";
    private static final String SELECT_ACT_SQL =
        "select * from  SYSACT  where ACTID = ?";
    private static final String SELECT_USER_SQL =
        "select * from sysact  where userid = ? ";
    private static final String SELECT_ROLE_SQL =
        "select * from sysact  where roleid = ? ";

    /**
	 * 生成一条Act记录
     * @param con
     * @param Object SysAct
     * @param return SysAct
	 */
	public Object doCreate(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysAct sysAct = (SysAct) obj;
        sysAct.setActID(DBUtil.getSequence(con,"\"SEQ_ACT\""));
        
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setString(1, sysAct.getRoleID());
		pstmt.setString(2, sysAct.getUserID());
		pstmt.setString(3, sysAct.getActID());

		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
		return sysAct;
	}
	/**
	 * 生成一条Act记录
	 */
	public void doStore(Connection con, Object obj)
		throws java.sql.SQLException {
        this.doCreate(con,obj);
	}
	/**
	 * 根据主键ACTID更新指定Act记录
     * @param con
     * @param Object SysAct
	 */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysAct sysAct = (SysAct) obj;

		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, sysAct.getRoleID());
		pstmt.setString(2, sysAct.getUserID());
		pstmt.setString(3, sysAct.getActID());
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();

	}
	/**
	 * 根据主键ACTID删除指定Act记录
     * @param con
     * @param Object SysAct
	 */
	public void doDelete(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		String PK = ((SysAct) obj).getActID();

		pstmt = con.prepareStatement(DELETE_ACT_SQL);
		pstmt.setString(1, PK);
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();

	}
    /**
     * 删除指定用户所有的Act记录
     * @param Connection
     * @param Object        SysUser
     */
    public void doDeleteByUser(Connection con, Object obj)
        throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        String PK = ((SysUser) obj).getUserID();

        pstmt = con.prepareStatement(DELETE_USER_SQL);
        pstmt.setString(1, PK);
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
    }
    
    /**
     * 删除指定角色所有的Act记录
     * @param Connection
     * @param Object        SysRole
     */
    public void doDeleteByRole(Connection con, Object obj)
        throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        String PK = ((SysRole) obj).getRoleID();

        pstmt = con.prepareStatement(DELETE_ROLE_SQL);
        pstmt.setString(1, PK);
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
    }
    
	/**
	 * 根据actid查找指定Act记录
     * @param Connection
     * @param Object        SysAct
     * @return Object
	 */
	public Object doFind(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysAct sysAct = null;

		pstmt = con.prepareStatement(SELECT_ACT_SQL);
		pstmt.setString(1, ((SysAct) obj).getActID());
		rs = pstmt.executeQuery();
        if (rs == null){
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
        }
		if (rs.next()) {
			sysAct = new SysAct();
			sysAct.setActID(rs.getString("actID"));
			sysAct.setRoleID(rs.getString("roleID"));
			sysAct.setUserID(rs.getString("userID"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysAct;
	}

	/**
	 * 查找userID对应的Act记录集
     * @param Connection
     * @param Object        SysUser
     * @return Collection   SysActList
	*/
	public Collection doFindByUser(Connection conn, Object obj)
		throws SQLException, NotFindException {
		Collection actList = new Vector();

		PreparedStatement ps = null;
		ResultSet rs = null;
		SysAct act = null;
        
		ps = conn.prepareStatement(SELECT_USER_SQL);
		ps.clearParameters();
		ps.setString(1, ((SysUser) obj).getUserID());
		rs = ps.executeQuery();

		while (rs.next()) {
			act = new SysAct();
			act.setActID(rs.getString("actid"));
			act.setUserID(rs.getString("userid"));
			act.setRoleID(rs.getString("roleid"));
			actList.add(act);
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
        
		return actList;
	}

	/**
	 *查找roleid对应的Act记录集
     * @param Connection
     * @param Object        SysRole
     * @return Collection   SysActList
	*/
	public Collection doFindByRole(Connection conn, Object obj)
		throws SQLException, NotFindException {
		Collection actList = new Vector();

		SysAct act = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ps = conn.prepareStatement(SELECT_ROLE_SQL);
		ps.clearParameters();
		ps.setString(1, ((SysRole)obj).getRoleID());
		rs = ps.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			act = new SysAct();
			act.setActID(rs.getString("actid"));
			act.setUserID(rs.getString("userID"));
			act.setRoleID(rs.getString("roleid"));
			actList.add(act);
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();

		return actList;
	}

}
