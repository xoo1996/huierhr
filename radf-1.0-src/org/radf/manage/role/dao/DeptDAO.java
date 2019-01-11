package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.radf.manage.role.entity.SysDept;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * 管理SYSGROUP的DAO
 */
public class DeptDAO extends org.radf.plat.util.dao.DAOSupport {

    private static final String INSERT_SQL =
        "insert into SYSGROUP (" +
        "GROUPID,ORG,NAME,DESCRIPTION,PARENTID,DISTRICTCODE,LICENSE,PRINCIPAL," +
        "SHORTNAME,ADDRESS,TEL,LINKMAN,TYPE,CHARGEDEPT,FRONTFORDEPT)" +
        " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ORG_SQL =
        "update SYSGROUP set " +
        "NAME=?,DESCRIPTION=?,PARENTID=?,DISTRICTCODE=?,LICENSE=?,PRINCIPAL=?," +
        "SHORTNAME=?,ADDRESS=?,TEL=?,LINKMAN=?,TYPE=?,CHARGEDEPT=?,FRONTFORDEPT=?"+
        " where ORG = ?";
    private static final String DELETE_ORG_SQL =
        "delete from  SYSGROUP  where ORG = ?";
    private static final String DELETE_GROUPID_SQL =
        "delete from  SYSGROUP  where GROUPID = ?";

	private static final String SELECT_ALL_DEPTS_SQL = 
        "select * from SYSGROUP";
	private static final String SELECT_ORG_SQL =
		"select * from  SYSGROUP  where ORG = ?";
    private static final String SELECT_GROUPID_SQL =
        "select * from  SYSGROUP  where GROUPID = ?";
	
    /**
     * 生成SYSGROUP记录，并返回SysDept
     * @param Connection
     * @param Object        SysDept
     * @return Object       SysDept
     */
    public Object doCreate(Connection con,Object obj)
    throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        SysDept sysDept = (SysDept) obj;
        sysDept.setGroupID(DBUtil.getSequence(con,"\"SEQ_DEPT\""));

        pstmt = con.prepareStatement(INSERT_SQL);
     
        pstmt.setString(1, sysDept.getGroupID());
        pstmt.setString(2, sysDept.getDeptID());       
        pstmt.setString(3, sysDept.getDeptName());
        pstmt.setString(4, sysDept.getComments());
        pstmt.setString(5, sysDept.getDeptPrivilege());
        pstmt.setString(6, sysDept.getDistrictCode());
        pstmt.setString(7, sysDept.getLicense());
        pstmt.setString(8, sysDept.getPrincipal());
        pstmt.setString(9, sysDept.getShortname());
        pstmt.setString(10, sysDept.getAddress());
        pstmt.setString(11, sysDept.getTel());
        pstmt.setString(12, sysDept.getLinkman());
        pstmt.setString(13, sysDept.getType());
        pstmt.setString(14, sysDept.getChargedept());
        pstmt.setString(15, sysDept.getFrontfordept());   
        pstmt.executeUpdate();
  
        if (pstmt != null)
            pstmt.close();
        return sysDept;
    }
    
    /**
     * 生成一条SYSGROUP记录
     * @param Connection
     * @param Object        SysDept
     */
	public void doStore(Connection con, Object obj)
		throws java.sql.SQLException {
        this.doCreate(con,obj);
	}
    
   /**
     * 根据单位编号DeptID修改SYSGROUP记录
     * @param Connection
     * @param Object        SysDept
     */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysDept sysDept = (SysDept) obj;

		pstmt = con.prepareStatement(UPDATE_ORG_SQL);
		pstmt.setString(1, sysDept.getDeptName());
		pstmt.setString(2, sysDept.getComments());
		pstmt.setString(3, sysDept.getDeptPrivilege());
		pstmt.setString(4, sysDept.getDistrictCode());
		pstmt.setString(5, sysDept.getLicense());
		pstmt.setString(6, sysDept.getPrincipal());
		pstmt.setString(7, sysDept.getShortname());
		pstmt.setString(8, sysDept.getAddress());
		pstmt.setString(9, sysDept.getTel());
		pstmt.setString(10, sysDept.getLinkman());
		pstmt.setString(11, sysDept.getType());
		pstmt.setString(12, sysDept.getChargedept());
		pstmt.setString(13, sysDept.getFrontfordept());
        pstmt.setString(14, sysDept.getDeptID());
        

        pstmt.executeUpdate();
		if (pstmt != null)
			pstmt.close();
	}
    
    /**
     * 根据单位编号DeptID删除SYSGROUP记录
     * @param Connection
     * @param Object        SysDept
     */
	public void doDeleteByDeptID(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		String PK = ((SysDept) obj).getDeptID();
		pstmt = con.prepareStatement(DELETE_ORG_SQL);
		pstmt.setString(1, PK);
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();
	}
    
    /**
     * 根据groupid编号删除SYSGROUP记录
     * @param Connection
     * @param Object        SysDept
     */
    public void doDelete(Connection con, Object obj)
        throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        String PK = ((SysDept) obj).getGroupID();

        pstmt = con.prepareStatement(DELETE_GROUPID_SQL);
        pstmt.setString(1, PK);
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
    }

    /**
     * 查询所有的SYSGROUP记录
     * @param Connection
     * @param Object        SysDept
     */
	public Collection doFindAllDepts(Connection con)
		throws java.sql.SQLException, NotFindException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysDept sysDept = null;
		Collection list = new Vector();

		pstmt = con.prepareStatement(SELECT_ALL_DEPTS_SQL);
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			sysDept = new SysDept();
			sysDept.setGroupID(rs.getString("GROUPID"));
			sysDept.setDeptID(rs.getString("ORG"));
			sysDept.setDeptName(rs.getString("NAME"));
			sysDept.setDeptPrivilege(rs.getString("PARENTID"));
			sysDept.setComments(rs.getString("DESCRIPTION"));
			sysDept.setAddress(rs.getString("Address"));
			sysDept.setChargedept(rs.getString("Chargedept"));
			sysDept.setDistrictCode(rs.getString("DistrictCode"));
			sysDept.setFrontfordept(rs.getString("Frontfordept"));
			sysDept.setLicense(rs.getString("License"));
			sysDept.setLinkman(rs.getString("Linkman"));
			sysDept.setPrincipal(rs.getString("Principal"));
			sysDept.setShortname(rs.getString("Shortname"));
			sysDept.setTel(rs.getString("Tel"));
			sysDept.setType(rs.getString("Type"));
			
			list.add(sysDept);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return list;
	}
   /**
     * 根据单位编号DeptID查询对应的SYSDEPT记录
     * @param Connection
     * @param Object        SysDept
     */
	public Object doFindByDeptID(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysDept sysDept = null;

		pstmt = con.prepareStatement(SELECT_ORG_SQL);
		pstmt.setString(1, ((SysDept) obj).getDeptID());
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		if (rs.next()) {
			sysDept = new SysDept();
			sysDept.setGroupID(rs.getString("GROUPID"));
			sysDept.setDeptID(rs.getString("ORG"));
			sysDept.setDeptName(rs.getString("NAME"));
			sysDept.setDeptPrivilege(rs.getString("PARENTID"));
			sysDept.setComments(rs.getString("DESCRIPTION"));
			sysDept.setAddress(rs.getString("Address"));
			sysDept.setChargedept(rs.getString("Chargedept"));
			sysDept.setDistrictCode(rs.getString("DistrictCode"));
			sysDept.setFrontfordept(rs.getString("Frontfordept"));
			sysDept.setLicense(rs.getString("License"));
			sysDept.setLinkman(rs.getString("Linkman"));
			sysDept.setPrincipal(rs.getString("Principal"));
			sysDept.setShortname(rs.getString("Shortname"));
			sysDept.setTel(rs.getString("Tel"));
			sysDept.setType(rs.getString("Type"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysDept;
	}
    /**
     * 根据流水号GroupID查询对应的SYSDEPT记录
     * @param Connection
     * @param Object        SysDept
     */
    public Object doFind(Connection con, Object obj)
        throws java.sql.SQLException, NotFindException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SysDept sysDept = null;

        pstmt = con.prepareStatement(SELECT_GROUPID_SQL);
        pstmt.setString(1, ((SysDept) obj).getGroupID());
        rs = pstmt.executeQuery();
        if (rs == null)
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
        if (rs.next()) {
            sysDept = new SysDept();
            sysDept.setGroupID(rs.getString("GROUPID"));
            sysDept.setDeptID(rs.getString("ORG"));
            sysDept.setDeptName(rs.getString("NAME"));
            sysDept.setDeptPrivilege(rs.getString("PARENTID"));
            sysDept.setComments(rs.getString("DESCRIPTION"));
            sysDept.setAddress(rs.getString("Address"));
            sysDept.setChargedept(rs.getString("Chargedept"));
            sysDept.setDistrictCode(rs.getString("DistrictCode"));
            sysDept.setFrontfordept(rs.getString("Frontfordept"));
            sysDept.setLicense(rs.getString("License"));
            sysDept.setLinkman(rs.getString("Linkman"));
            sysDept.setPrincipal(rs.getString("Principal"));
            sysDept.setShortname(rs.getString("Shortname"));
            sysDept.setTel(rs.getString("Tel"));
            sysDept.setType(rs.getString("Type"));
        }

        if (rs != null)
            rs.close();
        if (pstmt != null)
            pstmt.close();

        return sysDept;
    }
}
