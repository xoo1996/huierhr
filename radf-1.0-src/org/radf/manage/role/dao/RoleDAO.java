package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * ����SYSROLE��DAO
 * ���з������ݵ���ڲ�����Ҫ����Connection��Object
 * ����Objectһ��Ϊʵ�����
 */
public class RoleDAO extends org.radf.plat.util.dao.DAOSupport {

    private static final String INSERT_SQL =
        "insert into SYSROLE (RoleId,roledesc,PARENT,CREATOR ) values (?,?,?,?)";
    private static final String UPDATE_SQL =
        "update SYSROLE set ROLEDesc = ?,PARENT = ? where ROLEId = ?";
    private static final String DELETE_SQL =
        "delete from  SYSROLE  where ROLEId = ?";
    
    private static final String SELECT_SQL =
		"select * from  SYSROLE  where ROLEID = ?";
	private static final String SELECT__ROLE_SQL =
		"select * from  SYSROLE  where ROLEDESC = ?";
	private static final String SELECT_ALL_ROLES_SQL = 
        "select * from SYSROLE";
	private static final String SELECT_ALL_LEAF_ROLES_SQL =
		"select a.* from SYSROLE a SYSACT b where a.roleid = b.roleid and b.userid = ?";
    
	/**
	 * ����һ��SYSROLE��¼�������ش���id�ļ�¼
     * @param Connection
     * @param Object        SysRole
     * @return Object       SysRole
	 */
	public Object doCreate(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysRole sysRole = (SysRole) obj;

		pstmt = con.prepareStatement(INSERT_SQL);
        sysRole.setRoleID(DBUtil.getSequence(con,"\"SEQ_ROLE\""));
        
		pstmt.setString(1, sysRole.getRoleID());
		pstmt.setString(2, sysRole.getRoleDesc());
		pstmt.setString(3, sysRole.getParentID());
        pstmt.setString(4, sysRole.getCreateUserID());

		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
		return sysRole;
	}
    
	/**
	 * ����һ��SYSROLE��¼
     * @param Connection
     * @param Object        SysRole
	 */
	public void doStore(Connection con, Object obj)
		throws java.sql.SQLException {
        doCreate(con,obj);
	}
    
	/**
	 * ����ROLEID�޸�SYSROLE��¼�������޸Ĵ�����
     * @param Connection
     * @param Object        SysRole
	 */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysRole sysRole = (SysRole) obj;

		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, sysRole.getRoleDesc());
		pstmt.setString(2, sysRole.getParentID());
        pstmt.setString(3, sysRole.getRoleID());
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
	}
    
	/**
	 * ����roleidɾ��SYSROLE��¼
     * @param Connection
     * @param Object        SysRole
	 */
	public void doDelete(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
        SysRole role = (SysRole) obj;
		String PK = role.getRoleID();

		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, PK);
		pstmt.executeUpdate();
        if (pstmt != null)
            pstmt.close();
		//ɾ��ACL�еĹ�����¼
        AclDAO aclDao = new AclDAO();
        aclDao.doDeleteByRole(con,role);
		//ɾ��ACT�еĹ�����¼
        ActDAO actDao = new ActDAO();
        actDao.doDeleteByRole(con,role);
	}
	/**
	 * ������RoleID��ѯ��Ӧ��SYSROLE��¼
     * @param Connection
     * @param Object        SysRole
	 */
	public Object doFind(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysRole sysRole = null;

		pstmt = con.prepareStatement(SELECT_SQL);
		pstmt.setString(1, ((SysRole) obj).getRoleID());
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		if (rs.next()) {
			sysRole = new SysRole();
			sysRole.setRoleID(rs.getString("ROLEId"));
			sysRole.setRoleDesc(rs.getString("ROLEDesc"));
			sysRole.setCreateUserID(rs.getString("Creator"));
            sysRole.setParentID(rs.getString("Parent"));
		}
        
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysRole;
	}
	/**
	 * ��ѯ���е�SYSROLE��¼
     * @param Connection
     * @return Collection sysRoleList
	 */
	public Collection doFindAllRoles(Connection con)
		throws java.sql.SQLException, NotFindException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysRole sysRole = null;
		Collection list = new Vector();

		pstmt = con.prepareStatement(SELECT_ALL_ROLES_SQL);
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			sysRole = new SysRole();
			sysRole.setRoleID(rs.getString("ROLEId"));
			sysRole.setRoleDesc(rs.getString("ROLEDesc"));
			sysRole.setCreateUserID(rs.getString("Creator"));
            sysRole.setParentID(rs.getString("Parent"));
			list.add(sysRole);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return list;
	}
	/**
	 * ��ѯָ���û����о���Ȩ�޵�SYSROLE��¼
     * @param Connection
	 */
	public Collection doFindAllLeafRoles(Connection con,SysUser obj)
		throws java.sql.SQLException, NotFindException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysRole sysRole = null;
		Collection list = new Vector();

		pstmt = con.prepareStatement(SELECT_ALL_LEAF_ROLES_SQL);
        pstmt.setString(1, obj.getUserID());
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			sysRole = new SysRole();
			sysRole.setRoleID(rs.getString("ROLEId"));
			sysRole.setRoleDesc(rs.getString("ROLEDesc"));
			sysRole.setCreateUserID(rs.getString("Creator"));
            sysRole.setParentID(rs.getString("Parent"));
			list.add(sysRole);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return list;
	}
    /**
     * @param Connection
     * @param Object        SysRole
     * @return
     * @throws java.sql.SQLException
     * @throws NotFindException
     */
	public Object doFindByDesc(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysRole sysRole = null;

		pstmt = con.prepareStatement(SELECT__ROLE_SQL);
		pstmt.setString(1, ((SysRole) obj).getRoleDesc());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			sysRole = new SysRole();
			sysRole.setRoleID(rs.getString("ROLEId"));
			sysRole.setRoleDesc(rs.getString("ROLEDesc"));
			sysRole.setCreateUserID(rs.getString("Creator"));
            sysRole.setParentID(rs.getString("Parent"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysRole;
	}
}
