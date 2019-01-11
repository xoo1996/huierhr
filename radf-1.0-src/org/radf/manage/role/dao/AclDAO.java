package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.radf.manage.role.entity.SysAcl;
import org.radf.manage.role.entity.SysRole;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * ��ɫ��ģ���ϵ���ձ�ά��
 * ά��ACL���ݵ�DAO
 * @author zqb
 * @version 1.0
 */
public class AclDAO extends org.radf.plat.util.dao.DAOSupport {

    private static final String INSERT_SQL =
        "insert into SYSACL (ACLID,FUNCTIONID,ROLEID,CHECKTYPE) values (?,?,?,?)";
    private static final String UPDATE_SQL =
        "update SYSACL set ROLEID = ? ,FUNCTIONID = ? ,CHECKTYPE = ? where ACLID = ?";
    private static final String DELETE_ACL_SQL =
        "delete from  SYSACL  where ACLID = ?";
    private static final String DELETE_ROLE_SQL =
        "delete from  SYSACL  where ROLEID = ?";
    private static final String DELETE_FUNCTION_SQL = 
        "delete from  SYSACL  where FUNCTIONID = ?";
	private static final String SELECT_ACLID_SQL =
		"select * from  SYSACL  where ACLID = ?";
    private static final String SELECT_ROLEID_SQL =
        "SELECT * FROM SYSACL WHERE ROLEID = ?";
    private static final String SELECT_FUNCTIONID_SQL =
        "SELECT FUNCTIONID FROM SYSACL WHERE FUNCTIONID = ?";

    
	/**
	 * ����һ��ACL��¼
     * @param con
     * @param Object SysAcl
     * @return Object SysAcl
	 */
	public Object doCreate(Connection con, Object obj)
	throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysAcl sysAcl = (SysAcl) obj;

		pstmt = con.prepareStatement(INSERT_SQL);
        sysAcl.setAclID(DBUtil.getSequence(con,"\"SEQ_ACL\""));
        
		pstmt.setString(1, sysAcl.getAclID());
		pstmt.setString(2, sysAcl.getFunctionID());
		pstmt.setString(3, sysAcl.getRoleID());
        pstmt.setString(4, sysAcl.getCheckType());

		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
		return sysAcl;

	}
	/**
	 * ����һ��ACL��¼
     * @param con
     * @param Object SysAcl
	 */
	public void doStore(Connection con, Object obj)
	throws java.sql.SQLException {
        this.doCreate(con,obj);
	}
	/**
	 * ����ָ��ACL��¼
     * @param con
     * @param Object SysAcl
	 */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysAcl sysAcl = (SysAcl) obj;

		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, sysAcl.getRoleID());
		pstmt.setString(2, sysAcl.getFunctionID());
        pstmt.setString(3, sysAcl.getCheckType());
		pstmt.setString(4, sysAcl.getAclID());
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
	}
	/**
	 * ��������aclidɾ��ָ��ACL��¼
     * @param con
     * @param Object SysAcl
	 */
	public void doDelete(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		String PK = ((SysAcl) obj).getAclID();

		pstmt = con.prepareStatement(DELETE_ACL_SQL);
		pstmt.setString(1, PK);
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
	}
    
    /**
     * ɾ��ָ����ɫ���е�Acl��¼
     * @param Connection
     * @param Object        SysRole
     */
    public void doDeleteByRole(Connection con, SysRole obj)
        throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        String PK = obj.getRoleID();

        pstmt = con.prepareStatement(DELETE_ROLE_SQL);
        pstmt.setString(1, PK);
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
    }
    
	/**
	 * ��������aclid����ָ��ACL��¼
     * @param con
     * @param Object SysAcl
	 */
	public Object doFind(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysAcl sysAcl = null;

		pstmt = con.prepareStatement(SELECT_ACLID_SQL);
		pstmt.setString(1, ((SysAcl) obj).getAclID());
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		if (rs.next()) {
			sysAcl = new SysAcl();
			sysAcl.setAclID(rs.getString("ACLID"));
			sysAcl.setRoleID(rs.getString("roleID"));
			sysAcl.setFunctionID(rs.getString("functionID"));
            sysAcl.setCheckType(rs.getString("checktype"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysAcl;
	}

    
    /**
     * ����roleID��Ӧ��SysAcl��¼��
     * @param con
     * @param Object SysRole
     * @return Collection SysAclList
     */

    public Collection doFindByRole(Connection conn, SysRole obj)
        throws SQLException, NotFindException {

        Collection aclList = new Vector();
        SysAcl acl = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement(SELECT_ROLEID_SQL);
        ps.clearParameters();
        ps.setString(1, obj.getRoleID());
        rs = ps.executeQuery();
        if (rs == null)
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
        while (rs.next()) {
            acl = new SysAcl();
            acl.setAclID(rs.getString("ACLID"));
            acl.setRoleID(rs.getString("roleID"));
            acl.setFunctionID(rs.getString("functionID"));
            acl.setCheckType(rs.getString("checktype"));
            aclList.add(acl);
        }
        if (rs != null)
            rs.close();
        if (ps != null)
            ps.close();

        return aclList;
    }
    
	/**
	 * ����roleID��Ӧ��FunctionID��¼��
     * @param con
     * @param Object SysRole
     * @return Collection  String��ʽfunctionIdList
	 */

	public Collection doFindFunctionIDByRole(Connection conn, SysRole obj)
		throws SQLException, NotFindException {

		Collection list = new Vector();

		PreparedStatement ps = null;
		ResultSet rs = null;

 		ps = conn.prepareStatement(SELECT_ROLEID_SQL);
		ps.clearParameters();
		ps.setString(1, obj.getRoleID());
		rs = ps.executeQuery();
 		if (rs == null)
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			list.add(rs.getString("functionId"));
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();

		return list;
	}

	/**
	 * ����functionID��Ӧ��ACL��¼��
     * @param con
     * @param Object SysAcl
     * @return Collection SysAclList
	 */
	public Collection doFindByFunction(Connection conn, String functionID)
		throws SQLException, NotFindException {

		Collection aclList = new Vector();

		SysAcl acl = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ps = conn.prepareStatement(SELECT_FUNCTIONID_SQL);
		ps.clearParameters();
		ps.setString(1, functionID);
		rs = ps.executeQuery();
		if (rs == null)
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			acl = new SysAcl();
			acl.setAclID(rs.getString("ACLID"));
			acl.setFunctionID(functionID);
			acl.setRoleID(rs.getString("roleid"));
			aclList.add(acl);
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();

		return aclList;
	}
    
    /**
     * ��ҳ��ѯ��SELECT��䲿��
     */
    protected String doSelectSQL() throws SQLException{
        return "select * from  SYSACL ";
    }
    /**
     * ��ҳ��ѯʱ���ɵ��б���������
     */
    public Collection doBuildPage(ResultSet rs)throws SQLException{
        Collection aclList = new Vector();
        SysAcl acl = null;
        while (rs.next()) {
            acl = new SysAcl();
            acl.setAclID(rs.getString("ACLID"));
            acl.setFunctionID(rs.getString("functionID"));
            acl.setRoleID(rs.getString("roleid"));
            aclList.add(acl);
        }
        return aclList;
    }
}
