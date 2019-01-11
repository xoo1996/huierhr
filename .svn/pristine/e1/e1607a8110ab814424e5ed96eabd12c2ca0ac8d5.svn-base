package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.radf.manage.role.entity.SysUser;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * 管理SYSUSER的DAO
 */
public class UserDAO extends org.radf.plat.util.dao.DAOSupport {

    private static final String INSERT_SQL =
        "insert into SYSUSER (USERID,LOGIN_NAME,OPERATORNAME,PASSWD,XQBM,XZBM,CBM,ORG,TYPE,ISLEADER,PINID,DESCRIPTION,AAB034) values (?,?,?,'123456',?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_SQL =
        "delete from  SYSUSER  where USERID = ?";
    private static final String UPDATE_PWD_SQL =
        "update SYSUSER set PASSWD=? where LOGIN_NAME = ?";
    private static final String RESET_PWD_SQL =
        "update SYSUSER set PASSWD='123456' where USERID = ?";
	private static final String UPDATE_SQL =
		"update SYSUSER set LOGIN_NAME = ?,OPERATORNAME =?,aab034 = ? where USERID = ?";
    private static final String SELECT_ALL_USERS_SQL = 
        "select * from SYSUSER";
    private static final String SELECT_ALL_MEMBERS_SQL =
        "select * from  SYSUSER  where ORG = ?";
    private static final String SELECT_SQL =
        "select * from  SYSUSER  where USERID = ?";
    private static final String FIND_SQL =
        "select * from  SYSUSER  where LOGIN_NAME = ?";
    private static final String Update_SQL_BYUSERID =
        "update SYSUSER set ORG=? where USERID =? ";
   
	/**
	 * 生成一条SYSUSER记录
     * @param Connection
     * @param Object        SysUser
     * @return Object       SysUser
	 * @see org.radf.plat.util.dao.DAOSupport#doCreate(java.sql.Connection, java.lang.Object)
	 */
	public Object doCreate(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysUser sysUser = (SysUser) obj;
        try{
            pstmt = con.prepareStatement(INSERT_SQL);
            sysUser.setUserID(DBUtil.getSequence(con,"\"SEQ_USER\""));
            
            pstmt.setString(1, sysUser.getUserID());
            pstmt.setString(2, sysUser.getLoginName());
            pstmt.setString(3, sysUser.getOperatorName());
            pstmt.setString(4, sysUser.getXQBM());
            pstmt.setString(5, sysUser.getXZBM());
            pstmt.setString(6, sysUser.getCBM());
            pstmt.setString(7, sysUser.getDeptID());
            pstmt.setString(8, sysUser.getType());
            pstmt.setString(9, sysUser.getIsleader());
            pstmt.setString(10,sysUser.getPinid());
            pstmt.setString(11,sysUser.getDESCRIPTION());
            pstmt.setString(12,sysUser.getAab034());
            pstmt.executeUpdate();
        }finally{
            DBUtil.closeStatement(pstmt);
        }
		return sysUser;
	}

    
	/**
	 * 修改SYSUSER记录
     * 此方法并不规范，将业务判断写在dao中
     * @param Connection
     * @param Object        SysUser
	 */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysUser dto = (SysUser) obj;
        
        StringBuffer sb = new StringBuffer(256);
        try{
            sb.append("update SYSUSER set ");
            if(dto.getLoginName()!=null){
                sb.append("LOGIN_NAME = '");
                sb.append(dto.getLoginName());
                sb.append("',");
            }
            
            if(dto.getAab034()!=null&&!dto.getAab034().equalsIgnoreCase("")){
                sb.append("AAB034 = '");
                sb.append(dto.getAab034());
                sb.append("',");
            }
            if(dto.getOperatorName()!=null&&!dto.getOperatorName().equalsIgnoreCase("")){
                sb.append("OPERATORNAME = '");
                sb.append(dto.getOperatorName());
                sb.append("',");
            }
            if(dto.getType()!=null&&!dto.getType().equalsIgnoreCase("")){
                sb.append("TYPE = '");
                sb.append(dto.getType());
                sb.append("',");
            }
            
            if(dto.getXQBM()!=null&&!dto.getXQBM().equalsIgnoreCase("")){
                sb.append("XQBM = '");
                sb.append(dto.getXQBM());
                sb.append("',");
            }
            if(dto.getXZBM()!=null&&!dto.getXZBM().equalsIgnoreCase("")){
                sb.append("XZBM = '");
                sb.append(dto.getXZBM());
                sb.append("',");
            }
            if(dto.getCBM()!=null&&!dto.getCBM().equalsIgnoreCase("")){
                sb.append("CBM = '");
                sb.append(dto.getCBM());
                sb.append("',");
            }
            if(dto.getPinid()!=null&&!dto.getPinid().equalsIgnoreCase("")){
                sb.append("PINID = '");
                sb.append(dto.getPinid());
                sb.append("',");
            }
            if(dto.getDESCRIPTION()!=null&&!dto.getDESCRIPTION().equalsIgnoreCase("")){
                sb.append("DESCRIPTION = '");
                sb.append(dto.getDESCRIPTION());
                sb.append("',");
            }
            
            sb.append("USERID = '");
            sb.append(dto.getUserID());
            sb.append("' where USERID = '");
            sb.append(dto.getUserID());
            sb.append("'");
            
            pstmt = con.prepareStatement(sb.toString());
            pstmt.executeUpdate();
        }finally{
            DBUtil.closeStatement(pstmt);
        }
	}
	/**
	 * 用单位编号DEPT_ID查询所有满足条件的SYSUSER记录
     * @param Connection
     * @param Object        String 单位编号
     * @return Collection   SysUserList
	 */
	public Collection doFindAllMembers(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysUser sysUser = null;
		Collection usrList = new Vector();

		pstmt = con.prepareStatement(SELECT_ALL_MEMBERS_SQL);
		pstmt.setString(1, (String) obj);
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			sysUser = new SysUser();
			sysUser.setUserID(rs.getString("USERID"));
			sysUser.setLoginName(rs.getString("LOGIN_NAME"));
			sysUser.setPassWD(rs.getString("PASSWD"));
			sysUser.setDeptID(rs.getString("ORG"));
			sysUser.setOperatorName(rs.getString("OPERATORNAME"));
			sysUser.setCertName(rs.getString("CERTNAME"));
            sysUser.setXQBM(rs.getString("XQBM"));
            sysUser.setXZBM(rs.getString("XZBM"));
            sysUser.setCBM(rs.getString("CBM"));
            sysUser.setDESCRIPTION(rs.getString("DESCRIPTION"));
            sysUser.setIsleader(rs.getString("ISLEADER"));
            sysUser.setPinid(rs.getString("PINID"));
            sysUser.setType(rs.getString("TYPE"));
            sysUser.setAab034(rs.getString("AAB034"));
            usrList.add(sysUser);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return usrList;
	}
    
    
	/**
	 * 用主键UserID删除SYSUSER记录，传入的内容仍为SysUser对象
     * @param Connection
     * @param Object        SysUser
	 */
 	public void doDelete(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
        SysUser user = (SysUser) obj;
        String pk = user.getUserID();
        
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, pk);
		pstmt.executeUpdate();
        if (pstmt != null)
            pstmt.close(); 
		//删除ACT中的关联记录
        ActDAO actDao = new ActDAO();
        actDao.doDeleteByUser(con,user);        
    }

	/**
	 * 用主键查询对应的SYSUSER记录
     * @param Connection
     * @param Object        SysUser
	 */
	public Object doFind(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysUser sysUser = null;

		pstmt = con.prepareStatement(SELECT_SQL);
        String pk = ((SysUser) obj).getUserID();
		pstmt.setString(1, pk);
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		if (rs.next()) {
			sysUser = new SysUser();
            sysUser.setUserID(rs.getString("USERID"));
            sysUser.setLoginName(rs.getString("LOGIN_NAME"));
            sysUser.setPassWD(rs.getString("PASSWD"));
            sysUser.setDeptID(rs.getString("ORG"));
            sysUser.setOperatorName(rs.getString("OPERATORNAME"));
            sysUser.setCertName(rs.getString("CERTNAME"));
            sysUser.setXQBM(rs.getString("XQBM"));
            sysUser.setXZBM(rs.getString("XZBM"));
            sysUser.setCBM(rs.getString("CBM"));
            sysUser.setDESCRIPTION(rs.getString("DESCRIPTION"));
            sysUser.setIsleader(rs.getString("ISLEADER"));
            sysUser.setPinid(rs.getString("PINID"));
            sysUser.setType(rs.getString("TYPE"));
            sysUser.setAab034(rs.getString("AAB034"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysUser;
	}
	/**
	 * 查询所有SYSUSER记录
     * @param Connection
	 */
	public Collection doFindAllUsers(Connection con)
		throws java.sql.SQLException, NotFindException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysUser sysUser = null;
		Collection list = new Vector();

		pstmt = con.prepareStatement(SELECT_ALL_USERS_SQL);
		rs = pstmt.executeQuery();
		if (rs == null)
			throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		while (rs.next()) {
			sysUser = new SysUser();
            sysUser.setUserID(rs.getString("USERID"));
            sysUser.setLoginName(rs.getString("LOGIN_NAME"));
            sysUser.setPassWD(rs.getString("PASSWD"));
            sysUser.setDeptID(rs.getString("ORG"));
            sysUser.setOperatorName(rs.getString("OPERATORNAME"));
            sysUser.setCertName(rs.getString("CERTNAME"));
            sysUser.setXQBM(rs.getString("XQBM"));
            sysUser.setXZBM(rs.getString("XZBM"));
            sysUser.setCBM(rs.getString("CBM"));
            sysUser.setDESCRIPTION(rs.getString("DESCRIPTION"));
            sysUser.setIsleader(rs.getString("ISLEADER"));
            sysUser.setPinid(rs.getString("PINID"));
            sysUser.setType(rs.getString("TYPE"));
            sysUser.setAab034(rs.getString("AAB034"));
			list.add(sysUser);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return list;
	}
	/**
	 * 用登录名查询对应的SYSUSER记录
     * @param Connection
     * @param Object        SysUser
	 */
	public Object doFindByLoginName(Connection con, Object obj)
		throws java.sql.SQLException, NotFindException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysUser sysUser = null;

		pstmt = con.prepareStatement(FIND_SQL);
		pstmt.setString(1, ((SysUser) obj).getLoginName());
		rs = pstmt.executeQuery();
        if (rs == null)
            throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
		if (rs.next()) {
			sysUser = new SysUser();
            sysUser.setUserID(rs.getString("USERID"));
            sysUser.setLoginName(rs.getString("LOGIN_NAME"));
            sysUser.setPassWD(rs.getString("PASSWD"));
            sysUser.setDeptID(rs.getString("ORG"));
            sysUser.setOperatorName(rs.getString("OPERATORNAME"));
            sysUser.setCertName(rs.getString("CERTNAME"));
            sysUser.setXQBM(rs.getString("XQBM"));
            sysUser.setXZBM(rs.getString("XZBM"));
            sysUser.setCBM(rs.getString("CBM"));
            sysUser.setDESCRIPTION(rs.getString("DESCRIPTION"));
            sysUser.setIsleader(rs.getString("ISLEADER"));
            sysUser.setPinid(rs.getString("PINID"));
            sysUser.setType(rs.getString("TYPE"));
            sysUser.setAab034(rs.getString("AAB034"));
		}
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();

		return sysUser;
	}
	/**
	 * 根据登录名，修改SYSUSER记录的密码
     * @param Connection
     * @param Object        SysUser
	 */
	public void doUpdatePWD(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysUser sysUser = (SysUser) obj;
		pstmt = con.prepareStatement(UPDATE_PWD_SQL);

		pstmt.setString(1, sysUser.getPassWD());
		pstmt.setString(2, sysUser.getLoginName());
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();

	}
	/**
     * 根据用户id恢复用户初始化密码
     * @param Connection
     * @param Object        SysUser
	 */
	public void doResetPWD(Connection con, Object obj)
		throws java.sql.SQLException {
        String pk = ((SysUser) obj).getUserID();
		PreparedStatement pstmt = null;
		pstmt = con.prepareStatement(RESET_PWD_SQL);
		pstmt.setString(1, pk);
		pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();

	}
   
 
    /**
     * 根据UserID,修改用户部门编号
     * @param Connection
     * @param String loginId
     * @param String Org
     */
    public void doUpdateDeptIDByUserID(Connection con, SysUser sysUser)
        throws java.sql.SQLException {

        PreparedStatement pstmt = null;
       
        String loginId =sysUser.getUserID();//用户代码
        String Org=sysUser.getDeptID();//机构编码
        
        pstmt = con.prepareStatement(Update_SQL_BYUSERID);
           pstmt.setString(1, Org);
           pstmt.setString(2, loginId);
           pstmt.executeUpdate(); 
        if (pstmt != null)
            pstmt.close();

    }
}
